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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

import org.bonitasoft.engine.api.ApiAccessType;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.util.APITypeManager;
import org.bonitasoft.studio.common.Activator;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.IBonitaProjectListener;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.engine.server.PortConfigurator;
import org.bonitasoft.studio.engine.server.StartEngineJob;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.launching.RuntimeClasspathEntry;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.server.tomcat.core.internal.ITomcatServer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.IServerType;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.internal.ProjectProperties;
import org.eclipse.wst.server.core.internal.ServerType;
import org.osgi.framework.Bundle;

public class BOSWebServerManager implements IBonitaProjectListener {

    private static final String BONITA_TOMCAT_SERVER_ID = "bonita-tomcat-server-id";
    private static final String BONITA_TOMCAT_RUNTIME_ID = "bonita-tomcat-runtime-id";
    public static final String SERVER_CONFIGURATION_PROJECT = "server_configuration";
    private static final String LOGINSERVICE_PATH = "/bonita/loginservice";
    protected static final String WEBSERVERMANAGER_EXTENSION_ID = "org.bonitasoft.studio.engine.bonitaWebServerManager";
    protected static final String TOMCAT_SERVER_TYPE = "org.eclipse.jst.server.tomcat.90";
    protected static final String TOMCAT_RUNTIME_TYPE = "org.eclipse.jst.server.tomcat.runtime.90";
    protected static final String START_TIMEOUT = "start-timeout";
    private static final String HTTP = "HTTP";
    private static final String SERVER_URL = "server.url";
    private static final String APPLICATION_NAME = "application.name";
    private static final String BONITA_APPLICATION = "bonita";
    private static final String BONITA_CLIENT_HOST_DEFAULT = "bonita.client.host.default";
    private static final String BONITA_CLIENT_PORT_DEFAULT = "bonita.client.port.default";


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
    private final ReentrantLock startStoplock = new ReentrantLock();

    public static synchronized BOSWebServerManager getInstance() {
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
                final URL url = getTomcatBundle().getResource("tomcat");
                tomcatFolder = new File(FileLocator.toFileURL(url).getFile());
                PlatformUtil.copyResource(new File(bundleLocation), tomcatFolder, monitor);
                BonitaStudioLog.debug("Tomcat bundle copied in workspace.",
                        EnginePlugin.PLUGIN_ID);
            }
        } catch (final IOException e) {
            BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
        }
    }

    public void startServer(IRepository repository, IProgressMonitor monitor) {
        startStoplock.lock();
        try {
            if (!serverIsStarted()) {
                monitor.subTask(Messages.startingWebServer);
                if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                    BonitaStudioLog.debug("Starting tomcat...",
                            EnginePlugin.PLUGIN_ID);
                }
                try {
                    setupLaunchConfiguration(monitor);
                    UIDesignerServerManager uidManager = UIDesignerServerManager.getInstance();
                    if (uidManager.getPortalPort() != portConfigurator.getHttpPort()) {
                        uidManager.setPortalPort(portConfigurator.getHttpPort());
                        uidManager.stop();
                        uidManager.start(repository, monitor);
                    }

                    startResult = null;
                  
                    tomcat.start(ILaunchManager.RUN_MODE, result -> startResult = result);
                    waitServerRunning();
                } catch (final CoreException e) {
                    clearConfiguration();
                    handleCoreExceptionWhileStartingTomcat(e);
                }
            }
        } finally {
            startStoplock.unlock();
        }
    }

    public void clearConfiguration() {
        if (tomcat != null) {
            try {
                tomcat.delete();
                tomcat = null;
            } catch (CoreException e1) {
                BonitaStudioLog.error(e1);
            }
        }
    }

    public void setupLaunchConfiguration(IProgressMonitor monitor) throws CoreException {
        if (tomcat == null) {
            var rule = ResourcesPlugin.getWorkspace().getRoot();
            // Avoid workspace deadlock
            Job.getJobManager().beginRule(rule, monitor);
            configureBonitaClient();
            copyTomcatBundleInWorkspace(monitor);
            updateRuntimeLocationIfNeeded();
            var type = ServerCore.findRuntimeType(TOMCAT_RUNTIME_TYPE);
            var runtime = createServerRuntime(type, new NullProgressMonitor());
            var confProject = createServerConfigurationProject(new NullProgressMonitor());
         
            tomcat = createServer(monitor, confProject, runtime);
            createLaunchConfiguration(tomcat, new NullProgressMonitor());
            confProject.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, new NullProgressMonitor());
            Job.getJobManager().endRule(rule);
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
                    Files.writeString(serverXmlFile.toPath(),
                            Files.readString(serverXmlFile.toPath(), Charset.forName("UTF-8")).replace(oldLocation,
                                    tomcatInstanceLocation),
                            Charset.forName("UTF-8"));
                } catch (final IOException e1) {
                    BonitaStudioLog.error(e1, EnginePlugin.PLUGIN_ID);
                }
                try {
                    copy.save(true, AbstractRepository.NULL_PROGRESS_MONITOR);
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
            BonitaStudioLog.error("Failed to login to engine after " + MAX_LOGGING_TRY + " tries",
                    EnginePlugin.PLUGIN_ID);
        }
    }

    protected ILaunchConfiguration createLaunchConfiguration(final IServer server, final IProgressMonitor monitor)
            throws CoreException {
        ILaunchConfigurationType launchConfigType = ((ServerType) server.getServerType()).getLaunchConfigurationType();
        ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
        ILaunchConfiguration[] configurations = launchManager.getLaunchConfigurations(launchConfigType);
        if (configurations != null) {
            for (var existingConf : configurations) {
                existingConf.delete();
            }
        }
        ILaunchConfiguration conf = server.getLaunchConfiguration(true, AbstractRepository.NULL_PROGRESS_MONITOR);
        return configureLaunchConfiguration(conf.getWorkingCopy());
    }

    protected ILaunchConfiguration configureLaunchConfiguration(ILaunchConfigurationWorkingCopy workingCopy)
            throws CoreException {
        final RepositoryAccessor repositoryAccessor = RepositoryManager.getInstance().getAccessor();

        workingCopy.setAttribute(
                IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,
                getTomcatVMArgsBuilder(repositoryAccessor, EnginePlugin.getDefault().getPreferenceStore())
                        .getVMArgs(bundleLocation));
        workingCopy.setAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_FILE,
                getTomcatLogFile());
        workingCopy.setAttribute(IDebugUIConstants.ATTR_APPEND_TO_FILE, true);
        workingCopy.setAttribute(DebugPlugin.ATTR_CONSOLE_ENCODING, "UTF-8");
        final List<String> classpathEntries = workingCopy.getAttribute(IJavaLaunchConfigurationConstants.ATTR_CLASSPATH,
                new ArrayList<>());
        // Contains log4j jars
        File[] extraJars = new File(tomcatInstanceLocation).toPath()
                .resolve("lib")
                .resolve("ext")
                .toFile()
                .listFiles(file -> file.getName().endsWith(".jar"));
        Stream.of(extraJars).map(file -> {
            try {
                return new RuntimeClasspathEntry(
                        JavaCore.newLibraryEntry(Path.fromOSString(file.getAbsolutePath()), null, null, true))
                                .getMemento();
            } catch (CoreException e) {
                throw new RuntimeException(e);
            }
        }).forEach(classpathEntries::add);
        workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_CLASSPATH, classpathEntries);
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
                sourceFolder, AbstractRepository.NULL_PROGRESS_MONITOR);
        configurationFolder.refreshLocal(IResource.DEPTH_INFINITE,
                AbstractRepository.NULL_PROGRESS_MONITOR);
        final IServer server = configureServer(runtime, sType, file,
                configurationFolder, monitor);
        portConfigurator = newPortConfigurator(server);
        portConfigurator.configureServerPort(AbstractRepository.NULL_PROGRESS_MONITOR);
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

    protected IRuntime createServerRuntime(final IRuntimeType type, final IProgressMonitor monitor)
            throws CoreException {
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
            confProject.create(AbstractRepository.NULL_PROGRESS_MONITOR);
            confProject.open(AbstractRepository.NULL_PROGRESS_MONITOR);
            final ProjectProperties projectProperties = new ProjectProperties(confProject);
            confProject.getWorkspace().run(new IWorkspaceRunnable() {

                @Override
                public void run(final IProgressMonitor monitor) throws CoreException {
                    projectProperties.setServerProject(true, monitor);
                }
            }, monitor);
        } else if (!confProject.isOpen()) {
            confProject.open(AbstractRepository.NULL_PROGRESS_MONITOR);
        }
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
    
    public void resetServer(boolean cleanConfiguration, IProgressMonitor monitor) {
        boolean notifying = notifyRestartServer();
        stopServer(monitor);
        if(cleanConfiguration) {
            try {
                tomcat.delete();
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
            tomcat = null;
        }
        startServer(RepositoryManager.getInstance().getCurrentRepository().orElseThrow(), monitor);
        if (notifying) {
            notifyRestartServerCompleted();
        }
    }

    private void notifyRestartServerCompleted() {
        BonitaNotificator.openInfoNotification(Messages.restartServerCompletedNotificationTitle,
                Messages.serverRunningNotificationMessage);
        EngineNotificationSemaphore.getInstance().release();
    }

    private boolean notifyRestartServer() {
        if (EngineNotificationSemaphore.getInstance().tryAcquire()) {
            BonitaNotificator.openInfoNotification(Messages.restartServerNotificationTitle,
                    Messages.restartServerNotificationMessage);
            return true;
        }
        return false;
    }

    public void stopServer(final IProgressMonitor monitor) {
        startStoplock.lock();
        try {
            if (serverIsStarted()) {
                monitor.subTask(Messages.stoppingWebServer);
                if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                    BonitaStudioLog.debug("Stopping tomcat server...", EnginePlugin.PLUGIN_ID);
                }
                tomcat.stop(true);
                try {
                    waitServerStopped(monitor);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
                }
                BonitaStudioLog.debug("Tomcat server stopped",
                        EnginePlugin.PLUGIN_ID);
            }
        } finally {
            startStoplock.unlock();
        }
    }

    public String generateUrlBase() {
        final IPreferenceStore store = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
        final String port = store.getString(BonitaPreferenceConstants.CONSOLE_PORT);
        final String host = store.getString(BonitaPreferenceConstants.CONSOLE_HOST);
        return "http://" + host + ":" + port;
    }

    public String generateLoginURL(final String username, final String password) {
        return generateUrlBase() + LOGINSERVICE_PATH + "?username=" + username + "&password=" + password;
    }
    
    public String generateLoginURL() {
        return generateUrlBase() + LOGINSERVICE_PATH;
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

    @Override
    public void projectOpened(IRepository repository, IProgressMonitor monitor) {
        if (PlatformUtil.isHeadless()) {
            return;
        }
        if (!isLazyModeEnabled()) {
            try {
                clearConfiguration();
                setupLaunchConfiguration(monitor);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
            final StartEngineJob job = new StartEngineJob(Messages.startingEngineServer, repository);
            job.setPriority(Job.LONG);
            job.setUser(false);
            job.schedule();
        }
    }

    public boolean isLazyModeEnabled() {
        IPreferenceStore preferenceStore = EnginePlugin.getDefault().getPreferenceStore();
        return preferenceStore.getBoolean(EnginePreferenceConstants.LAZYLOAD_ENGINE)
                || System.getProperty(EnginePreferenceConstants.LAZYLOAD_ENGINE) != null;
    }

    @Override
    public void projectClosed(IRepository repository, IProgressMonitor monitor) {
        stopServer(monitor);
    }

    private static Bundle getTomcatBundle() {
        return EnginePlugin.getDefault().getBundle();
    }
    
    private static synchronized void configureBonitaClient() {
        try {
            final String host = System.getProperty(BONITA_CLIENT_HOST_DEFAULT, "localhost");
            final int serverPort = Integer
                    .parseInt(System.getProperty(BONITA_CLIENT_PORT_DEFAULT, "8080"));
            BonitaStudioLog.debug("Configuring bonita client on host " + host + ":" + serverPort + " with API_TYPE=" + HTTP,
                    Activator.PLUGIN_ID);
            final Map<String, String> parameters = new HashMap<>();
            parameters.put(SERVER_URL, "http://" + host + ":" + serverPort);
            parameters.put(APPLICATION_NAME, BONITA_APPLICATION);
            parameters.put("basicAuthentication.active", "true");
            parameters.put("basicAuthentication.username", "http-api");
            parameters.put("basicAuthentication.password", "h11p-@p1");
            APITypeManager.setAPITypeAndParams(ApiAccessType.valueOf(HTTP), parameters);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }
}
