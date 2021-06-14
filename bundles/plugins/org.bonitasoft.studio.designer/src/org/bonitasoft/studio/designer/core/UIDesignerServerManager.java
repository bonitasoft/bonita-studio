/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core;

import static org.bonitasoft.studio.designer.core.WorkspaceSystemProperties.aSystemProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.IBonitaProjectListener;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.externaltools.internal.IExternalToolConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.jdt.internal.launching.StandardVMType;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.SocketUtil;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class UIDesignerServerManager implements IBonitaProjectListener {

    private static final String UI_DESIGNER_JAR = "ui-designer-backend-webapp.jar";
    private static final String UID_SERVER_PORT = "server.port";
    private static final String UID_LOGGING_FILE = "logging.file.name";
    private static UIDesignerServerManager INSTANCE;
    private int port = -1;
    private ILaunch launch;
    private int portalPort = 8080;
    private static final String PORTAL_BASE_URL = "designer.bonita.portal.url";
    private static final String BONITA_DATA_REPOSITORY_ORIGIN = "designer.bonita.bdm.url";
    private PageDesignerURLFactory pageDesignerURLBuilder;
    private boolean started = false;

    private UIDesignerServerManager() {
        addShutdownHook();
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
    }

    public static synchronized UIDesignerServerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UIDesignerServerManager();
        }
        return INSTANCE;
    }

    public void setPortalPort(int portalPort) {
        this.portalPort = portalPort;
    }

    public int getPortalPort() {
        return portalPort;
    }

    public synchronized void start(AbstractRepository repository, IProgressMonitor monitor) {
        if (launch == null
                || Stream.of(launch.getProcesses())
                        .findFirst()
                        .map(IProcess::isTerminated)
                        .orElse(false)) {
            monitor.beginTask(Messages.startingUIDesigner, IProgressMonitor.UNKNOWN);
            BonitaStudioLog.info(Messages.startingUIDesigner, UIDesignerPlugin.PLUGIN_ID);
            Instant start = Instant.now();
            try {
                if (!WorkspaceResourceServerManager.getInstance().isRunning()) {
                    WorkspaceResourceServerManager.getInstance().start(SocketUtil.findFreePort());
                }
            } catch (Exception e1) {
                BonitaStudioLog.error(e1);
                return;
            }
            try {
                if (!DataRepositoryServerManager.getInstance().isStarted()) {
                    DataRepositoryServerManager.getInstance().start(AbstractRepository.NULL_PROGRESS_MONITOR);
                }
            } catch (Exception e1) {
                BonitaStudioLog.error(e1);
                return;
            }
            this.portalPort = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                    .getInt(BonitaPreferenceConstants.CONSOLE_PORT);
            final ILaunchManager manager = getLaunchManager();
            final ILaunchConfigurationType ltype = manager
                    .getLaunchConfigurationType(IExternalToolConstants.ID_PROGRAM_LAUNCH_CONFIGURATION_TYPE);
            try {
                final ILaunchConfigurationWorkingCopy workingCopy = ltype.newInstance(null, "Standalone UI Designer");
                workingCopy.setAttribute(IExternalToolConstants.ATTR_LOCATION, javaBinaryLocation());
                workingCopy.setAttribute(IExternalToolConstants.ATTR_TOOL_ARGUMENTS,
                        buildCommand(repository).stream().collect(Collectors.joining(" ")));
                Map<String, String> env = new HashMap<>();
                env.put("JAVA_TOOL_OPTIONS", "-Dfile.encoding=UTF-8");
                workingCopy.setAttribute(ILaunchManager.ATTR_ENVIRONMENT_VARIABLES, env);
                launch = workingCopy.launch(ILaunchManager.RUN_MODE, AbstractRepository.NULL_PROGRESS_MONITOR);
                pageDesignerURLBuilder = new PageDesignerURLFactory(getPreferenceStore());
                if (waitForUID(pageDesignerURLBuilder)) {
                    started = true;
                    BonitaStudioLog.info(
                            String.format("UI Designer has been started on http://localhost:%s/bonita in %ss", port,
                                    Duration.between(start, Instant.now()).getSeconds()),
                            UIDesignerPlugin.PLUGIN_ID);
                }
            } catch (final CoreException | IOException e) {
                BonitaStudioLog.error("Failed to run ui designer war", e);
            }
        }
    }

    protected IEclipsePreferences getPreferenceStore() {
        return InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID);
    }

    protected boolean waitForUID(final PageDesignerURLFactory pageDesignerURLBuilder) {
        try {
            connectToURL(pageDesignerURLBuilder.openPageDesignerHome());
        } catch (ResourceException | URISyntaxException | MalformedURLException e) {
            return false;
        }
        return true;
    }

    private void connectToURL(final URL url) throws URISyntaxException {
        ClientResource cr = new ClientResource(url.toURI());
        cr.setRetryOnError(true);
        cr.setRetryDelay(500);
        cr.setRetryAttempts(120);
        cr.get();
    }

    public File getLogFile() {
        IPath location = Platform.getLogFileLocation();
        IPath path = location.removeLastSegments(1).append("ui-designer.log");
        return new File(path.toOSString());
    }

    public synchronized void stop() {
        WorkspaceResourceServerManager resourceServer = WorkspaceResourceServerManager.getInstance();
        if (resourceServer.isRunning()) {
            try {
                resourceServer.stop();
            } catch (Exception e1) {
                BonitaStudioLog.error(e1);
            }
        }
        DataRepositoryServerManager dataRepositoryServerManager = DataRepositoryServerManager.getInstance();
        if (dataRepositoryServerManager.isStarted()) {
            dataRepositoryServerManager.stop();
        }
        if (launch != null) {
            try {
                launch.terminate();
                BonitaStudioLog.info("UI Designer has been stopped.", UIDesignerPlugin.PLUGIN_ID);
                launch = null;
                started = false;
            } catch (DebugException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    protected String javaBinaryLocation() throws FileNotFoundException {
        IVMInstall defaultVMInstall = JavaRuntime.getDefaultVMInstall();
        if (defaultVMInstall == null) {
            throw new FileNotFoundException("Default VM not installed");
        }
        File javaBinaryPath = StandardVMType.findJavaExecutable(defaultVMInstall.getInstallLocation());
        if (javaBinaryPath == null) {
            throw new FileNotFoundException("Java binary not configured");
        } else if (!javaBinaryPath.exists()) {
            throw new FileNotFoundException(
                    String.format("Java binary not found at '%s'", javaBinaryPath.getAbsolutePath()));
        }
        return javaBinaryPath.getAbsolutePath();
    }

    protected List<String> buildCommand(AbstractRepository repository) throws IOException {
        final WorkspaceSystemProperties workspaceSystemProperties = new WorkspaceSystemProperties(repository);
        port = getPreferenceStore().getInt(BonitaPreferenceConstants.UID_PORT, -1);
        if (port == -1 || isPortInUse(port)) {
            port = SocketUtil.findFreePort();
            getPreferenceStore().putInt(BonitaPreferenceConstants.UID_PORT, port);
        }
        return Arrays.asList(
                getPreferenceStore().get(BonitaPreferenceConstants.UID_JVM_OPTS, "-Xmx256m"),
                workspaceSystemProperties.getWorspacePathLocation(),
                workspaceSystemProperties.getRestAPIURL(WorkspaceResourceServerManager.getInstance().runningPort()),
                workspaceSystemProperties.activateSpringProfile("studio"),
                aSystemProperty(PORTAL_BASE_URL,
                        String.format("http://%s:%s", InetAddress.getByName(null).getHostAddress(),
                                portalPort)),
                aSystemProperty(BONITA_DATA_REPOSITORY_ORIGIN, String.format("http://%s:%s",
                        InetAddress.getByName(null).getHostAddress(),
                        DataRepositoryServerManager.getInstance().getPort())),
                aSystemProperty(UID_SERVER_PORT, String.valueOf(port)),
                aSystemProperty(UID_LOGGING_FILE, String.format("\"%s\"", getLogFile().getAbsolutePath())),
                "-jar",
                "\"" + locateUIDjar() + "\"");
    }

    private static boolean isPortInUse(int port) {
        try {
            return org.eclipse.wst.server.core.util.SocketUtil.isPortInUse(InetAddress.getByName(null), port)
                    || org.eclipse.wst.server.core.util.SocketUtil.isPortInUse(port);
        } catch (UnknownHostException e) {
            return org.eclipse.wst.server.core.util.SocketUtil.isPortInUse(port);
        }
    }

    public int getPort() {
        return port;
    }

    protected String locateUIDjar() throws IOException {
        Bundle uiDesignerBundle = Platform.getBundle(UIDesignerPlugin.PLUGIN_ID);
        IPath stateLocation = Platform.getStateLocation(uiDesignerBundle);
        Path uiDesignerJar = stateLocation.toFile().toPath().resolve(UI_DESIGNER_JAR);

        if (!uiDesignerJar.toFile().exists()) {
            final URL url = Platform.getBundle(UIDesignerPlugin.PLUGIN_ID).getResource("webapp");
            File webappFolder = new File(URLDecoder.decode(FileLocator.toFileURL(url).getFile(), "UTF-8"));
            File execJar = new File(webappFolder, UI_DESIGNER_JAR);
            if (!execJar.exists()) {
                throw new FileNotFoundException(
                        String.format("Cannot find ui designer jar file in %s folder.",
                                webappFolder.getAbsolutePath()));
            }
            Files.copy(execJar.toPath(), uiDesignerJar);
        }
        return uiDesignerJar.toFile().getCanonicalFile().getAbsolutePath();
    }

    protected ILaunchManager getLaunchManager() {
        return DebugPlugin.getDefault().getLaunchManager();
    }

    public PageDesignerURLFactory getPageDesignerURLBuilder() {
        return pageDesignerURLBuilder;
    }

    public boolean isStarted() {
        return started;
    }

    @Override
    public void projectOpened(AbstractRepository repository, IProgressMonitor monitor) {
        if (PlatformUI.isWorkbenchRunning()) {
            new Job("Starting UID...") {
                
                @Override
                protected IStatus run(IProgressMonitor monitor) {
                    start(repository, monitor);
                    return Status.OK_STATUS;
                }
            }.schedule();
        }
    }

    @Override
    public void projectClosed(AbstractRepository repository, IProgressMonitor monitor) {
        if (PlatformUI.isWorkbenchRunning()) {
            stop();
        }
    }

}
