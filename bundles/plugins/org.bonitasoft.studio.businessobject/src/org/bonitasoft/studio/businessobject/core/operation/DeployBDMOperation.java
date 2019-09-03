/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.core.operation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.engine.api.TenantAdministrationAPI;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.business.data.BusinessDataRepositoryDeploymentException;
import org.bonitasoft.engine.exception.UpdateException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.Workbench;

public class DeployBDMOperation implements IRunnableWithProgress {

    private static final String UNINSTALL_BDM_AC_CMD = "org.bonitasoft.studio.bdm.access.control.command.uninstall.headless";

    private final BusinessObjectModelFileStore fileStore;
    private APISession session;

    private boolean flushSession = false;

    private static Object deployLock = new Object();

    private boolean dropDatabase = false;

    public DeployBDMOperation(final BusinessObjectModelFileStore fileStore) {
        this(fileStore, false);
    }

    public DeployBDMOperation(final BusinessObjectModelFileStore fileStore, boolean dropDatabase) {
        this.fileStore = fileStore;
        this.dropDatabase = dropDatabase;
    }

    public DeployBDMOperation reuseSession(final APISession session) {
        this.session = session;
        return this;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        login(monitor);
        synchronized (deployLock) {
            uninstallBDMAccessControl(monitor);
            doDeployBDM(monitor);
        }
    }

    protected void uninstallBDMAccessControl(IProgressMonitor monitor) {
        EHandlerService handlerService = handlerService();
        IEclipseContext e4Context = e4Context();
        e4Context.set(APISession.class, session);
        Optional.ofNullable(ParameterizedCommand.generateCommand(
                commandService().getCommand(UNINSTALL_BDM_AC_CMD), null))
                .filter(handlerService::canExecute)
                .ifPresent(handlerService::executeHandler);
    }

    protected IEclipseContext e4Context() {
        Workbench workbench = (Workbench) PlatformUI.getWorkbench();
        return workbench.getContext();
    }

    protected EHandlerService handlerService() {
        Workbench workbench = (Workbench) PlatformUI.getWorkbench();
        return workbench.getService(EHandlerService.class);
    }

    protected ECommandService commandService() {
        Workbench workbench = (Workbench) PlatformUI.getWorkbench();
        return workbench.getService(ECommandService.class);
    }

    protected void login(final IProgressMonitor monitor) throws InvocationTargetException {
        final BOSEngineManager engineManager = getEngineManager();
        flushSession = false;
        if (session == null) {
            try {
                session = engineManager.loginDefaultTenant(monitor);
            } catch (final Exception e) {
                throw new InvocationTargetException(e);
            }
            flushSession = true;
        }
    }

    private String progressMessage(BusinessObjectModel bom) {
        return containsBusinessObjects(bom) ? Messages.deployingBusinessObjects : Messages.cleaningBusinessObjects;
    }

    protected void doDeployBDM(IProgressMonitor monitor) throws InvocationTargetException {
        if (monitor == null) {
            monitor = Repository.NULL_PROGRESS_MONITOR;
        }

        final BusinessObjectModel bom = fileStore.getContent();
        final String progressMessage = progressMessage(bom);
        monitor.beginTask(progressMessage, IProgressMonitor.UNKNOWN);
        BonitaStudioLog.debug(progressMessage, BusinessObjectPlugin.PLUGIN_ID);
        final BOSEngineManager engineManagerEx = getEngineManager();
        TenantAdministrationAPI tenantManagementAPI = null;
        try {
            tenantManagementAPI = engineManagerEx.getTenantAdministrationAPI(session);
            if (!tenantManagementAPI.isPaused()) {
                tenantManagementAPI.pause();
            }
            try {
                if (dropDBOnInstall()) {
                    forceH2Drop();
                    tenantManagementAPI.cleanAndUninstallBusinessDataModel();
                } else {
                    tenantManagementAPI.uninstallBusinessDataModel();
                }
            } catch (final BusinessDataRepositoryDeploymentException bdrde) {
                // ignore exception
            }
            if (containsBusinessObjects(bom)) {
                tenantManagementAPI.installBusinessDataModel(fileStore.toByteArray());
            }
            tenantManagementAPI.resume();
        } catch (final Exception e) {
            try {
                tenantManagementAPI.uninstallBusinessDataModel();
            } catch (final BusinessDataRepositoryDeploymentException e1) {
                // ignore exception
            }
            throw new InvocationTargetException(e);
        } finally {
            if (tenantManagementAPI != null && tenantManagementAPI.isPaused()) {
                try {
                    tenantManagementAPI.resume();
                } catch (final UpdateException e) {
                    throw new InvocationTargetException(e);
                }
            }
            if (flushSession && session != null) {
                engineManagerEx.logoutDefaultTenant(session);
                session = null;
            }
        }
    }

    protected void forceH2Drop() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            BonitaStudioLog.error(e);
        }
        try (Connection conn = DriverManager.getConnection(String.format(
                "jdbc:h2:file:%s/business_data.db;MVCC=TRUE;DB_CLOSE_ON_EXIT=TRUE;IGNORECASE=TRUE;AUTO_SERVER=TRUE;",
                pathToDBFolder(fileStore.getRepositoryAccessor())), "sa", "");
                Statement stmt = conn.createStatement();) {
            stmt.executeUpdate("DROP ALL OBJECTS");
        } catch (SQLException e) {
            BonitaStudioLog.error(e);
        }
    }

    protected String locateH2jar(RepositoryAccessor repositoryAccessor) throws IOException {
        final File root = rootFile(repositoryAccessor);
        final Path path = Paths.get(root.toURI()).resolve(Paths.get("tomcat", "server", "lib", "bonita"));
        return Stream.of(path.toFile().listFiles(this::h2Jar))
                .findFirst()
                .orElseThrow(
                        () -> new FileNotFoundException(String.format("Cannot find h2 jar file in %s folder.", path)))
                .getAbsolutePath();
    }

    protected File rootFile(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getWorkspace().getRoot().getLocation().toFile();
    }

    private boolean h2Jar(File dir, String name) {
        return name.startsWith("h2-") && name.endsWith(".jar");
    }

    protected String pathToDBFolder(final RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().getDatabaseHandler().getDBLocation().getAbsolutePath();
    }

    protected IEventBroker eventBroker() {
        return PlatformUI.getWorkbench().getService(IEventBroker.class);
    }

    protected void removeDependency() {
        final DependencyRepositoryStore dependencyRepositoryStore = RepositoryManager.getInstance()
                .getRepositoryStore(DependencyRepositoryStore.class);
        final DependencyFileStore bdmFileStore = dependencyRepositoryStore
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        if (bdmFileStore != null) {
            bdmFileStore.delete();
        }
    }

    private boolean containsBusinessObjects(final BusinessObjectModel bom) {
        return bom != null && !bom.getBusinessObjects().isEmpty();
    }

    protected boolean dropDBOnInstall() {
        return dropDatabase;
    }

    protected void updateDependency(final byte[] jarContent) throws InvocationTargetException {
        ByteArrayInputStream is = null;
        try {
            final DependencyRepositoryStore store = RepositoryManager.getInstance()
                    .getRepositoryStore(DependencyRepositoryStore.class);
            is = new ByteArrayInputStream(jarContent);
            final DependencyFileStore depFileStore = store.getChild(fileStore.getDependencyName(), true);
            if (depFileStore != null) {
                depFileStore.delete();
            }
            final DependencyFileStore bdmJarFileStore = store.createRepositoryFileStore(fileStore.getDependencyName());
            bdmJarFileStore.save(is);
            RepositoryManager.getInstance().getCurrentRepository().build(Repository.NULL_PROGRESS_MONITOR);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (final IOException e) {
                    throw new InvocationTargetException(e);
                }
            }
        }
    }

    protected File getTargetFolder() {
        return ProjectUtil.getBonitaStudioWorkFolder();
    }

    protected BOSEngineManager getEngineManager() {
        return BOSEngineManager.getInstance();
    }

}
