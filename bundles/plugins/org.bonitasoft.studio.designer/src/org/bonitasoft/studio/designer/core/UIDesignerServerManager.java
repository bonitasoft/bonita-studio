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
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
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

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.net.PortSelector;
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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

public class UIDesignerServerManager implements IBonitaProjectListener {

    private static final String UI_DESIGNER_JAR = "ui-designer-backend-webapp.jar";
    private static final String UID_SERVER_PORT = "server.port";
    private static final String UID_LOGGING_FILE = "logging.file.name";
    private static UIDesignerServerManager INSTANCE;
    private int port = -1;
    private ILaunch launch;
    private int runtimePort = 8080;
    private static final String PORTAL_BASE_URL = "designer.bonita.portal.url";
    private static final String BONITA_DATA_REPOSITORY_ORIGIN = "designer.bonita.bdm.url";
    private static final int UID_DEFAULT_PORT = 8081;
    private PageDesignerURLFactory pageDesignerURLBuilder;
    private boolean started = false;
    private UIDWorkspaceSynchronizer synchronizer;

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
        this.runtimePort = portalPort;
    }

    public int getPortalPort() {
        return runtimePort;
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
            int healthCheckServerPort = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                    .getInt(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT);
            DataRepositoryServerManager dataRepositoryServerManager = DataRepositoryServerManager.getInstance();
            int dataRepositoryPort = dataRepositoryServerManager.selectPort(healthCheckServerPort);
            if (!dataRepositoryServerManager.isStarted()) {
                schedule(healthCheckServerPort, dataRepositoryPort,
                        dataRepositoryServerManager);
            }
            this.runtimePort = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                    .getInt(BonitaPreferenceConstants.CONSOLE_PORT);
            final ILaunchManager manager = getLaunchManager();
            final ILaunchConfigurationType ltype = manager
                    .getLaunchConfigurationType(IExternalToolConstants.ID_PROGRAM_LAUNCH_CONFIGURATION_TYPE);
            try {
                final ILaunchConfigurationWorkingCopy workingCopy = ltype.newInstance(null, "Standalone UI Designer");
                workingCopy.setAttribute(IExternalToolConstants.ATTR_LOCATION, javaBinaryLocation());
                workingCopy.setAttribute(IExternalToolConstants.ATTR_TOOL_ARGUMENTS,
                        buildCommand(repository, healthCheckServerPort, dataRepositoryPort).stream()
                                .collect(Collectors.joining(" ")));
                Map<String, String> env = new HashMap<>();
                env.put("JAVA_TOOL_OPTIONS", "-Dfile.encoding=UTF-8");
                workingCopy.setAttribute(ILaunchManager.ATTR_ENVIRONMENT_VARIABLES, env);
                launch = workingCopy.launch(ILaunchManager.RUN_MODE, AbstractRepository.NULL_PROGRESS_MONITOR);
                pageDesignerURLBuilder = new PageDesignerURLFactory(getPreferenceStore());
                if (waitForUID(pageDesignerURLBuilder)) {
                    schedule(healthCheckServerPort,
                            dataRepositoryPort,
                            dataRepositoryServerManager);
                    started = true;
                    BonitaStudioLog.info(
                            String.format("UI Designer has been started on http://localhost:%s/bonita in %ss", port,
                                    Duration.between(start, Instant.now()).getSeconds()),
                            UIDesignerPlugin.PLUGIN_ID);
                    synchronizer = new UIDWorkspaceSynchronizer(repository);
                    synchronizer.connect();
                } else {
                    Display.getDefault().asyncExec(() -> {
                        int res = MessageDialog.open(MessageDialog.ERROR,
                                Display.getDefault().getActiveShell(),
                                Messages.uidStartupFailedTitle,
                                Messages.uidStartupFailedMsg,
                                SWT.NONE,
                                IDialogConstants.CLOSE_LABEL, Messages.openLogFile);
                        if (res == 1) {
                            new CommandExecutor().executeCommand("org.bonitasoft.studio.application.openUidLog",
                                    Map.of());
                        }
                    });
                }
            } catch (final CoreException | IOException e) {
                BonitaStudioLog.error("Failed to run ui designer war", e);
            }
        }
    }

    private void schedule(int healthCheckServerPort,
            int dataRepositoryPort,
            DataRepositoryServerManager dataRepositoryServerManager) {
        new Job("Starting UID services...") {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                try {
                    dataRepositoryServerManager.start(healthCheckServerPort, dataRepositoryPort, monitor);
                } catch (Exception e) {
                    BonitaStudioLog.error(e);
                    return new Status(IStatus.ERROR, getClass(), "Failed to start Data repository service.", e);
                }
                return Status.OK_STATUS;
            }
        }.schedule();
    }

    protected IEclipsePreferences getPreferenceStore() {
        return InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID);
    }

    public boolean waitForUID(final PageDesignerURLFactory pageDesignerURLBuilder) {
        try {
            return connectToURL(pageDesignerURLBuilder.openPageDesignerHome());
        } catch (URISyntaxException | MalformedURLException e) {
            return false;
        }
    }

    private boolean connectToURL(final URL url) throws URISyntaxException {
        var httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
        var request = HttpRequest.newBuilder()
                .method("HEAD", HttpRequest.BodyPublishers.noBody())
                .uri(url.toURI())
                .build();
        HttpResponse<Void> response = retriesUntil(httpClient, request, 200, 30, 2000); // 1 minute timeout (30*2000ms)
        return response != null;
    }

    private HttpResponse<Void> retriesUntil(HttpClient client, HttpRequest request, int expectedStatus, int nbOfRetries,
            int delayBetweenRetries) {
        BonitaStudioLog.debug("Waiting for UI Designer to be up and running...", UIDesignerPlugin.PLUGIN_ID);
        int retry = nbOfRetries;
        while (retry >= 0) {
            try {
                HttpResponse<Void> httpResponse = client.send(request, BodyHandlers.discarding());
                if (expectedStatus == httpResponse.statusCode()) {
                    return httpResponse;
                } else {
                    retry--;
                    BonitaStudioLog
                            .debug(String.format("Failed to reach UI Designer (status = %s). Number of retries = %s",
                                    httpResponse.statusCode(), retry), UIDesignerPlugin.PLUGIN_ID);
                    Thread.sleep(delayBetweenRetries);
                }
            } catch (IOException | InterruptedException e) {
                // Connection refused, the UID is not started yet
                retry--;
                try {
                    Thread.sleep(delayBetweenRetries);
                } catch (InterruptedException e1) {
                    BonitaStudioLog.error(e);
                }
                BonitaStudioLog.debug(String.format("Failed to reach UI Designer (%s). Number of retries = %s",
                        e.getMessage(), retry), UIDesignerPlugin.PLUGIN_ID);
            }
        }
        BonitaStudioLog.error(
                "The UI Designer failed to start and cannot be reached. Check UI Designer logs for more information.",
                UIDesignerPlugin.PLUGIN_ID);
        return null;
    }

    public File getLogFile() {
        IPath location = Platform.getLogFileLocation();
        IPath path = location.removeLastSegments(1).append("ui-designer.log");
        return new File(path.toOSString());
    }

    public synchronized void stop() {
        if (DataRepositoryServerManager.isStarted()) {
            DataRepositoryServerManager.getInstance().stop();
        }
        if (launch != null) {
            if (synchronizer != null) {
                try {
                    synchronizer.disconnect();
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
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

    protected List<String> buildCommand(AbstractRepository repository,
            int workspaceResourceServerPort,
            int dataRepositoryPort) throws IOException {
        final WorkspaceSystemProperties workspaceSystemProperties = new WorkspaceSystemProperties(repository);
        port = getPreferenceStore().getInt(BonitaPreferenceConstants.UID_PORT, UID_DEFAULT_PORT);
        if (isPortInUse(port)) {
            port = PortSelector.findFreePort();
            getPreferenceStore().putInt(BonitaPreferenceConstants.UID_PORT, port);
        }
        return Arrays.asList(
                getPreferenceStore().get(BonitaPreferenceConstants.UID_JVM_OPTS, "-Xmx256m"),
                workspaceSystemProperties.getWorspacePathLocation(),
                workspaceSystemProperties.getRestAPIURL(workspaceResourceServerPort),
                workspaceSystemProperties.activateSpringProfile("studio"),
                aSystemProperty(PORTAL_BASE_URL,
                        String.format("http://%s:%s", InetAddress.getLoopbackAddress().getHostAddress(),
                                runtimePort)),
                aSystemProperty(BONITA_DATA_REPOSITORY_ORIGIN, String.format("http://%s:%s",
                        InetAddress.getLoopbackAddress().getHostAddress(),
                        dataRepositoryPort)),
                aSystemProperty(UID_SERVER_PORT, String.valueOf(port)),
                aSystemProperty(UID_LOGGING_FILE, String.format("\"%s\"", getLogFile().getAbsolutePath())),
                "-jar",
                "\"" + locateUIDjar() + "\"");
    }

    private static boolean isPortInUse(int port) {
        return org.eclipse.wst.server.core.util.SocketUtil.isPortInUse(InetAddress.getLoopbackAddress(), port)
                || org.eclipse.wst.server.core.util.SocketUtil.isPortInUse(port);
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
            start(repository, monitor);
        }
    }

    @Override
    public void projectClosed(AbstractRepository repository, IProgressMonitor monitor) {
        if (PlatformUI.isWorkbenchRunning()) {
            stop();
        }
    }

}
