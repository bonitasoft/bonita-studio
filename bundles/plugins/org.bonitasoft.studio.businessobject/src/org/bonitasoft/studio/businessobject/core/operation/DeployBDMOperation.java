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

import static com.google.common.io.ByteStreams.toByteArray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PlatformUI;


public class DeployBDMOperation implements IRunnableWithProgress {

    private static final String BDM_DEPLOYED_TOPIC = "bdm/deployed";

    private static final String BDM_CLIENT = "bdm-client";

    private static final String BDM_DAO = "bdm-dao";

    private static final String MODEL = "model";

    private final BusinessObjectModelFileStore fileStore;

    private APISession session;

    private boolean flushSession = false;

    private static Object deployLock = new Object();

    public DeployBDMOperation(final BusinessObjectModelFileStore fileStore) {
        this.fileStore = fileStore;
    }

    public void setSession(final APISession session) {
        this.session = session;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        login(monitor);
        synchronized (deployLock) {
            doDeployBDM(monitor);
        }
    }

    protected void login(final IProgressMonitor monitor) throws InvocationTargetException {
        final BOSEngineManager engineManagerEx = getBOSEngineManagerEx();
        flushSession = false;
        if (session == null) {
            try {
                session = engineManagerEx.loginDefaultTenant(monitor);
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
        final BOSEngineManager engineManagerEx = getBOSEngineManagerEx();
        TenantAdministrationAPI tenantManagementAPI = null;
        try {
            tenantManagementAPI = engineManagerEx.getTenantAdministrationAPI(session);
            if (!tenantManagementAPI.isPaused()) {
                tenantManagementAPI.pause();
            }
            try {
                if (dropDBOnInstall()) {
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

            if (containsBusinessObjects(bom)) {
                final byte[] zipContent = tenantManagementAPI.getClientBDMZip();
                final Map<String, byte[]> jarContent = retrieveContent(zipContent);
                updateDependency(jarContent.get(BDM_CLIENT));
                final Map<String, Object> data = new HashMap<>();
                data.put(MODEL, bom);
                data.put(BDM_DAO, jarContent.get(BDM_DAO));
                eventBroker().send(BDM_DEPLOYED_TOPIC, data);
            } else {
                removeDependency();
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
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

    protected IEventBroker eventBroker() {
        return (IEventBroker) PlatformUI.getWorkbench().getService(IEventBroker.class);
    }

    protected void removeDependency() {
        final DependencyRepositoryStore dependencyRepositoryStore = RepositoryManager.getInstance()
                .getRepositoryStore(DependencyRepositoryStore.class);
        final DependencyFileStore bdmFileStore = dependencyRepositoryStore.getChild(BusinessObjectModelFileStore.DEFAULT_BDM_FILENAME);
        if (bdmFileStore != null) {
            bdmFileStore.delete();
        }
    }

    private boolean containsBusinessObjects(final BusinessObjectModel bom) {
        return bom != null && !bom.getBusinessObjects().isEmpty();
    }

    protected boolean dropDBOnInstall() {
        final IPreferenceStore preferenceStore = EnginePlugin.getDefault().getPreferenceStore();
        return preferenceStore.getBoolean(EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_INSTALL);
    }

    protected Map<String, byte[]> retrieveContent(final byte[] zipContent) throws IOException {
        Assert.isNotNull(zipContent);
        ByteArrayInputStream is = null;
        ZipInputStream zis = null;
        final ByteArrayOutputStream out = null;
        final Map<String, byte[]> result = new HashMap<>();
        try {
            is = new ByteArrayInputStream(zipContent);
            zis = new ZipInputStream(is);
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                final String entryName = entry.getName();
                if (entryName.contains(MODEL) && entryName.endsWith(".jar")) {
                    result.put(BDM_CLIENT, toByteArray(zis));
                }
                if (entryName.contains("dao") && entryName.endsWith(".jar")) {
                    result.put(BDM_DAO, toByteArray(zis));
                }
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (zis != null) {
                zis.close();
            }
            if (out != null) {
                out.close();
            }
        }

        return result;
    }


    protected void updateDependency(final byte[] jarContent) throws InvocationTargetException {
        ByteArrayInputStream is = null;
        try {
            final DependencyRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
            is = new ByteArrayInputStream(jarContent);
            final DependencyFileStore depFileStore = store.getChild(fileStore.getDependencyName());
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

    protected BOSEngineManager getBOSEngineManagerEx() {
        return BOSEngineManager.getInstance();
    }

}
