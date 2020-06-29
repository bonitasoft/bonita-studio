/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
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

package org.bonitasoft.studio.engine;

import static org.bonitasoft.studio.engine.server.ClientBonitaHomeBuildler.newClientBonitaHomeBuilder;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.BonitaHomeUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.IBonitaProjectListener;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.engine.server.PortConfigurator;
import org.bonitasoft.studio.engine.server.StartEngineJob;
import org.bonitasoft.studio.engine.server.WatchdogManager;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.dialog.BonitaPreferenceDialog;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.server.tomcat.core.internal.ITomcatServer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.IServerType;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.internal.ProjectProperties;

public class BOSWebServerManager implements IBonitaProjectListener {

    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private static final String BONITA_TOMCAT_SERVER_ID = "bonita-tomcat-server-id";
    private static final String BONITA_TOMCAT_RUNTIME_ID = "bonita-tomcat-runtime-id";
    public static final String SERVER_CONFIGURATION_PROJECT = "server_configuration";
    private static final String LOGINSERVICE_PATH = "/bonita/loginservice?";
    protected static final String WEBSERVERMANAGER_EXTENSION_ID = "org.bonitasoft.studio.engine.bonitaWebServerManager";
    protected static final String TOMCAT_SERVER_TYPE = "org.eclipse.jst.server.tomcat.85";
    protected static final String TOMCAT_RUNTIME_TYPE = "org.eclipse.jst.server.tomcat.runtime.85";
    protected static final String START_TIMEOUT = "start-timeout";

    protected static final String TMP_DIR = ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath();
    protected final String tomcatInstanceLocation = new File(new File(ResourcesPlugin
            .getWorkspace().getRoot().getLocation().toFile(), "tomcat"), "server")
                    .getAbsolutePath();
    protected final String bundleLocation = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(),
            "tomcat")
                    .getAbsolutePath();
    private static final String TOMCAT_LOG_FILE = "tomcat.log";

    private static final int MAX_LOGGING_TRY = 50;

    private static BOSWebServerManager INSTANCE;
    private IServer tomcat;
    private PortConfigurator portConfigurator;
    private IStatus startResult;

    public synchronized static BOSWebServerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = createInstance();
        }
        return INSTANCE;
    }

    protected static BOSWebServerManager createInstance() {
        for (final IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(WEBSERVERMANAGER_EXTENSION_ID)) {
            try {
                return (BOSWebServerManager) element.createExecutableExtension("class");
            } catch (final CoreException e) {
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            }
        }

        return new BOSWebServerManager();
    }

    protected BOSWebServerManager() {

    }

    public void copyTomcatBundleInWorkspace(final IProgressMonitor monitor) {
        File tomcatFolder = null;
        try {
            final File tomcatFolderInWorkspace = new File(tomcatInstanceLocation);
            final File tomcatLib = new File(tomcatFolderInWorkspace, "lib");
            if (!tomcatLib.exists()) {
                BonitaStudioLog.debug("Copying tomcat bundle in workspace...", EnginePlugin.PLUGIN_ID);
                final URL url = ProjectUtil.getConsoleLibsBundle().getResource("tomcat");
                tomcatFolder = new File(FileLocator.toFileURL(url).getFile());
                PlatformUtil.copyResource(new File(bundleLocation), tomcatFolder, monitor);
                BonitaStudioLog.debug("Tomcat bundle copied in workspace.",
                        EnginePlugin.PLUGIN_ID);
                addBonitaWar(tomcatFolderInWorkspace, monitor);
            }

        } catch (final IOException e) {
            BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
        }
    }

    protected void addBonitaWar(final File targetFolder, final IProgressMonitor monitor) throws IOException {
        final File webappDir = new File(targetFolder, "webapps");
        final File targetBonitaWarFile = new File(webappDir, "bonita.war");
        if (!targetBonitaWarFile.exists()) {
            BonitaStudioLog.debug("Copying Bonita war in tomcat/server/webapps...", EnginePlugin.PLUGIN_ID);
            final URL url = ProjectUtil.getConsoleLibsBundle().getResource("tomcat/server/webapp");
            final File bonitaWarFile = new File(FileLocator.toFileURL(url).getFile(), "bonita.war");
            PlatformUtil.copyResource(webappDir, bonitaWarFile, monitor);
            BonitaStudioLog.debug("Bonita war copied in tomcat/server/webapps.",
                    EnginePlugin.PLUGIN_ID);
        }
    }

    public synchronized void startServer(Repository repository, IProgressMonitor monitor) {
        if (!serverIsStarted()) {
            BonitaHomeUtil.configureBonitaClient();
            copyTomcatBundleInWorkspace(monitor);
            monitor.subTask(Messages.startingWebServer);
            if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                BonitaStudioLog.debug("Starting tomcat...",
                        EnginePlugin.PLUGIN_ID);
            }
            WatchdogManager.getInstance().startWatchdog();
            updateRuntimeLocationIfNeeded();
            final IRuntimeType type = ServerCore.findRuntimeType(TOMCAT_RUNTIME_TYPE);

            try {
                final IProject confProject = createServerConfigurationProject(Repository.NULL_PROGRESS_MONITOR);
                final IRuntime runtime = createServerRuntime(type, Repository.NULL_PROGRESS_MONITOR);
                tomcat = createServer(monitor, confProject, runtime);
                UIDesignerServerManager uidManager = UIDesignerServerManager.getInstance();
                if (uidManager.getPortalPort() != portConfigurator.getHttpPort()) {
                    uidManager.setPortalPort(portConfigurator.getHttpPort());
                    uidManager.stop();
                    uidManager.start(repository, monitor);
                }
                createLaunchConfiguration(tomcat, Repository.NULL_PROGRESS_MONITOR);
                confProject.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, Repository.NULL_PROGRESS_MONITOR);
                startResult = null;
                tomcat.start(ILaunchManager.RUN_MODE, result -> startResult = result);
                waitServerRunning();
            } catch (final CoreException e) {
                if (tomcat != null) {
                    try {
                        tomcat.delete();
                        tomcat = null;
                    } catch (CoreException e1) {
                        BonitaStudioLog.error(e1);
                    }
                }
                handleCoreExceptionWhileStartingTomcat(e);
            }
        }
    }

    private void handleCoreExceptionWhileStartingTomcat(final CoreException e) {
        BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
        Display.getDefault().asyncExec(
                () -> MessageDialog.openError(Display.getDefault().getActiveShell(), "Tomcat server startup error",
                        e.getMessage()));
    }

    private void updateRuntimeLocationIfNeeded() {
        for (final IRuntime runtime : ServerCore.getRuntimes()) {
            if (runtime instanceof org.eclipse.wst.server.core.internal.Runtime && runtime.getLocation() != null
                    && !runtime.getLocation().toFile().getAbsolutePath().equals(tomcatInstanceLocation)) {
                final IRuntimeWorkingCopy copy = runtime.createWorkingCopy();
                final String oldLocation = copy.getLocation().toFile()
                        .getAbsolutePath();
                copy.setLocation(Path.fromOSString(tomcatInstanceLocation));
                final File serverXmlFile = new File(tomcatInstanceLocation, "conf" + File.separatorChar + "server.xml");
                try {
                    com.google.common.io.Files.write(
                            com.google.common.io.Files.toString(serverXmlFile, UTF8_CHARSET).replace(oldLocation,
                                    tomcatInstanceLocation),
                            serverXmlFile,
                            UTF8_CHARSET);
                } catch (final IOException e1) {
                    BonitaStudioLog.error(e1, EnginePlugin.PLUGIN_ID);
                }
                try {
                    copy.save(true, Repository.NULL_PROGRESS_MONITOR);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
                }
            }
        }
    }

    private void waitServerRunning() {
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException ex) {
            BonitaStudioLog.error(ex, EnginePlugin.PLUGIN_ID);
        }
        while (startResult == null) {
            try {
                Thread.sleep(500);
            } catch (final InterruptedException ex) {
                BonitaStudioLog.error(ex, EnginePlugin.PLUGIN_ID);
            }
        }
        if (startResult.isOK()) {
            connectWithRetries();
            BonitaStudioLog.debug("Tomcat server started.",
                    EnginePlugin.PLUGIN_ID);
        } else {
            BonitaStudioLog.debug(
                    "Tomcat failed to start. Check the log file for more informations. (bonita.log or catalina.log)",
                    EnginePlugin.PLUGIN_ID);
            if (!PlatformUtil.isHeadless()) {
                Display.getDefault().asyncExec(() -> MessageDialog.openError(PlatformUI.getWorkbench()
                        .getActiveWorkbenchWindow().getShell(),
                        Messages.cannotStartTomcatTitle,
                        Messages.cannotStartTomcatMessage));
            }
        }
    }

    private void connectWithRetries() {
        int loginTry = 0;
        APISession session = null;
        while (session == null && loginTry < MAX_LOGGING_TRY) {
            try {
                session = BOSEngineManager.getInstance().getLoginAPI().login(BOSEngineManager.BONITA_TECHNICAL_USER,
                        BOSEngineManager.BONITA_TECHNICAL_USER_PASSWORD);
            } catch (final Exception e) {
                loginTry++;
                try {
                    Thread.sleep(500);
                } catch (final InterruptedException ex) {
                    BonitaStudioLog.error(ex, EnginePlugin.PLUGIN_ID);
                }
            }
        }
        if (session != null) {
            try {
                BOSEngineManager.getInstance().getLoginAPI().logout(session);
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        } else {
            BonitaStudioLog.error("Failed to login to engine after " + MAX_LOGGING_TRY + " tries", EnginePlugin.PLUGIN_ID);
        }
    }

    protected void createLaunchConfiguration(final IServer server, final IProgressMonitor monitor)
            throws CoreException {
        ILaunchConfiguration conf = server.getLaunchConfiguration(false, Repository.NULL_PROGRESS_MONITOR);
        if (conf == null) {
            conf = server.getLaunchConfiguration(true,
                    Repository.NULL_PROGRESS_MONITOR);
        }
        ILaunchConfigurationWorkingCopy workingCopy = conf.getWorkingCopy();
        final String args = workingCopy.getAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, "");
        if (!args.contains(tomcatInstanceLocation)) {
            conf = server.getLaunchConfiguration(true,
                    Repository.NULL_PROGRESS_MONITOR);
            workingCopy = conf.getWorkingCopy();
        }
        configureLaunchConfiguration(workingCopy);
    }

    protected ILaunchConfiguration configureLaunchConfiguration(ILaunchConfigurationWorkingCopy workingCopy)
            throws CoreException {
        final RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();

        workingCopy.setAttribute(
                IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,
                getTomcatVMArgsBuilder(repositoryAccessor, EnginePlugin.getDefault().getPreferenceStore())
                        .getVMArgs(bundleLocation));
        workingCopy.setAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_FILE,
                getTomcatLogFile());
        workingCopy.setAttribute(IDebugUIConstants.ATTR_APPEND_TO_FILE, true);
        workingCopy.setAttribute(DebugPlugin.ATTR_CONSOLE_ENCODING, "UTF-8");
        return workingCopy.doSave();
    }

    protected TomcatVmArgsBuilder getTomcatVMArgsBuilder(final RepositoryAccessor repositoryAccessor,
            IPreferenceStore enginePreference) {
        return new TomcatVmArgsBuilder(repositoryAccessor, enginePreference);
    }

    protected String getTomcatLogFile() {
        final File parentDir = Platform.getLogFileLocation().toFile().getParentFile();
        return new File(parentDir, TOMCAT_LOG_FILE).getAbsolutePath();
    }

    protected IServer createServer(final IProgressMonitor monitor, final IProject confProject, final IRuntime runtime)
            throws CoreException {
        final IServerType sType = ServerCore.findServerType(TOMCAT_SERVER_TYPE);
        final IFile file = confProject.getFile("bonitaTomcatServerSerialization");

        final IFolder configurationFolder = confProject
                .getFolder("tomcat_conf");
        final File sourceFolder = new File(tomcatInstanceLocation, "conf");
        PlatformUtil.copyResource(configurationFolder.getLocation().toFile(),
                sourceFolder, Repository.NULL_PROGRESS_MONITOR);
        configurationFolder.refreshLocal(IResource.DEPTH_INFINITE,
                Repository.NULL_PROGRESS_MONITOR);
        final IServer server = configureServer(runtime, sType, file,
                configurationFolder, monitor);
        portConfigurator = newPortConfigurator(server);
        portConfigurator.configureServerPort(Repository.NULL_PROGRESS_MONITOR);
        return server;
    }

    private PortConfigurator newPortConfigurator(final IServer server) {
        return new PortConfigurator(server, newClientBonitaHomeBuilder(),
                BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore());
    }

    protected IServer configureServer(final IRuntime runtime, final IServerType sType, final IFile file,
            final IFolder configurationFolder,
            final IProgressMonitor monitor)
            throws CoreException {
        final IServer server = ServerCore.findServer(BONITA_TOMCAT_SERVER_ID);
        IServerWorkingCopy serverWC = null;
        if (server != null) {
            serverWC = server.createWorkingCopy();
        }
        if (serverWC == null) {
            serverWC = sType.createServer(BONITA_TOMCAT_SERVER_ID, file, runtime, monitor);
        }
        serverWC.setServerConfiguration(configurationFolder);
        serverWC.setAttribute(ITomcatServer.PROPERTY_INSTANCE_DIR,
                tomcatInstanceLocation);
        serverWC.setAttribute(ITomcatServer.PROPERTY_DEPLOY_DIR,
                tomcatInstanceLocation + File.separatorChar + "webapps");
        serverWC.setAttribute(START_TIMEOUT, 300);
        return serverWC.save(true, monitor);
    }

    protected IRuntime createServerRuntime(final IRuntimeType type, final IProgressMonitor monitor) throws CoreException {
        IRuntime runtime = ServerCore.findRuntime(BONITA_TOMCAT_RUNTIME_ID);
        if (runtime == null) {
            runtime = type.createRuntime(BONITA_TOMCAT_RUNTIME_ID, monitor);
        }
        final IRuntimeWorkingCopy tomcatRuntimeWC = runtime.createWorkingCopy();
        tomcatRuntimeWC.setLocation(Path.fromOSString(tomcatInstanceLocation));
        final IStatus status = tomcatRuntimeWC.validate(null);
        if (!status.isOK()) {
            throw new RuntimeException("Failed to create a tomcat server : "
                    + status.getMessage());
        }
        return tomcatRuntimeWC.save(true, monitor);
    }

    protected IProject createServerConfigurationProject(final IProgressMonitor monitor) throws CoreException {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProject confProject = workspace.getRoot().getProject(SERVER_CONFIGURATION_PROJECT);
        if (!confProject.exists()) {
            confProject.create(Repository.NULL_PROGRESS_MONITOR);
            confProject.open(Repository.NULL_PROGRESS_MONITOR);
            final ProjectProperties projectProperties = new ProjectProperties(confProject);
            confProject.getWorkspace().run(new IWorkspaceRunnable() {

                @Override
                public void run(final IProgressMonitor monitor) throws CoreException {
                    projectProperties.setServerProject(true, monitor);
                }
            }, monitor);
        }
        confProject.open(Repository.NULL_PROGRESS_MONITOR);
        return confProject;
    }

    private void waitServerStopped(final IProgressMonitor monitor) throws CoreException {
        while (tomcat != null
                && tomcat.getServerState() != IServer.STATE_STOPPED) {
            try {
                Thread.sleep(1000);
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            }
        }
    }

    public boolean serverIsStarted() {
        return tomcat != null
                && (tomcat.getServerState() == IServer.STATE_STARTED);
    }

    public void resetServer(IProgressMonitor monitor) {
        BonitaNotificator.openNotification(Messages.restartServerNotificationTitle,
                Messages.restartServerNotificationMessage);
        stopServer(monitor);
        startServer(RepositoryManager.getInstance().getCurrentRepository(), monitor);
        BonitaNotificator.openNotification(Messages.restartServerCompletedNotificationTitle,
                Messages.serverRunningNotificationMessage);
    }

    public synchronized void stopServer(final IProgressMonitor monitor) {
        if (serverIsStarted()) {
            monitor.subTask(Messages.stoppingWebServer);
            if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                BonitaStudioLog.debug("Stopping tomcat server...", EnginePlugin.PLUGIN_ID);
            }
            WatchdogManager.getInstance().stopWatchdog();
            tomcat.stop(true);
            try {
                waitServerStopped(monitor);
                tomcat.delete();
            } catch (final CoreException e) {
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            }
            if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                BonitaStudioLog.debug("Tomcat server stopped",
                        EnginePlugin.PLUGIN_ID);
            }
        }
    }

    public String generateUrlBase() {
        final IPreferenceStore store = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
        final String port = store.getString(BonitaPreferenceConstants.CONSOLE_PORT);
        final String host = store.getString(BonitaPreferenceConstants.CONSOLE_HOST);
        return "http://" + host + ":" + port;
    }

    public String generateLoginURL(final String username, final String password) {
        return generateUrlBase() + LOGINSERVICE_PATH + "username=" + username + "&password=" + password;
    }

    public void cleanBeforeShutdown() throws IOException {
        final Repository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        final DatabaseHandler bonitaHomeHandler = currentRepository.getDatabaseHandler();
        if (BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getBoolean(BonitaPreferenceConstants.DELETE_TENANT_ON_EXIT)) {
            bonitaHomeHandler.removeEngineDatabase();
        }
        if (dropBusinessDataDBOnExit()) {
            bonitaHomeHandler.removeBusinessDataDatabase();
        }
    }

    public File getBonitaLogFile() {
        final File logDir = new File(tomcatInstanceLocation, "logs");
        final File[] list = logDir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(final File file, final String fileName) {
                return fileName.contains("bonita");
            }
        });
        Date fileDate = new Date(0);
        File lastLogFile = null;
        for (final File f : list) {
            final Date d = new Date(f.lastModified());
            if (d.after(fileDate)) {
                fileDate = d;
                lastLogFile = f;
            }
        }
        return lastLogFile;
    }

    private boolean dropBusinessDataDBOnExit() {
        final IPreferenceStore preferenceStore = EnginePlugin.getDefault().getPreferenceStore();
        return preferenceStore.getBoolean(EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_EXIT_PREF);
    }

    @Override
    public void projectOpened(Repository repository, IProgressMonitor monitor) {
        if (PlatformUtil.isHeadless()) {
            return;
        }
        IPreferenceStore preferenceStore = EnginePlugin.getDefault().getPreferenceStore();
        if (!isLazyModeEnabled(preferenceStore)) {
            BonitaNotificator.openNotification(Messages.startServerNotificationTitle,
                    Messages.engineLazyModeNotificationLink, e -> {
                        BonitaPreferenceDialog dialog = new BonitaPreferenceDialog(new Shell(Display.getDefault()));
                        dialog.create();
                        dialog.setSelectedPreferencePage(BonitaPreferenceDialog.SERVER_SETTINGS_PAGE_ID);
                        dialog.open();
                    });
            final StartEngineJob job = new StartEngineJob(Messages.startingEngineServer);
            job.setPriority(Job.LONG);
            job.setUser(false);
            job.schedule();
        }
    }

    private boolean isLazyModeEnabled(IPreferenceStore preferenceStore) {
        return preferenceStore.getBoolean(EnginePreferenceConstants.LAZYLOAD_ENGINE)
                || System.getProperty(EnginePreferenceConstants.LAZYLOAD_ENGINE) != null;
    }

    @Override
    public void projectClosed(Repository repository, IProgressMonitor monitor) {
        stopServer(monitor);
    }

}
