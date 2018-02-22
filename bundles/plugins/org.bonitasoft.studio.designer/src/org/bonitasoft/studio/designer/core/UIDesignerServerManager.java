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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.externaltools.internal.IExternalToolConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
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
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.SocketUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.google.common.base.Joiner;

public class UIDesignerServerManager {

    private static final String UI_DESIGNER_BASE_NAME = "ui-designer";
    private static UIDesignerServerManager INSTANCE;
    private RepositoryAccessor repositoryAccessor;
    private int port = -1;
    private ILaunch launch;
    private static final String BONITA_CLIENT_HOME = "bonita.client.home";

    private UIDesignerServerManager(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
        addShutdownHook();
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> stop()));
    }

    public synchronized static UIDesignerServerManager getInstance() {
        if (INSTANCE == null) {
            RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
            repositoryAccessor.init();
            INSTANCE = new UIDesignerServerManager(repositoryAccessor);
        }
        return INSTANCE;
    }

    public synchronized void start(IProgressMonitor monitor) {
        if (launch == null
                || Stream.of(launch.getProcesses())
                        .findFirst()
                        .map(IProcess::isTerminated)
                        .orElse(false)) {
            try {
                if (!WorkspaceResourceServerManager.getInstance().isRunning()) {
                    WorkspaceResourceServerManager.getInstance().start(SocketUtil.findFreePort());
                }
            } catch (Exception e1) {
                BonitaStudioLog.error(e1);
                return;
            }
            final ILaunchManager manager = getLaunchManager();
            final ILaunchConfigurationType ltype = manager
                    .getLaunchConfigurationType(IExternalToolConstants.ID_PROGRAM_LAUNCH_CONFIGURATION_TYPE);
            try {
                final ILaunchConfigurationWorkingCopy workingCopy = ltype.newInstance(null, "Standalone UI Designer");
                workingCopy.setAttribute(IExternalToolConstants.ATTR_LOCATION, javaBinaryLocation());
                workingCopy.setAttribute(IExternalToolConstants.ATTR_TOOL_ARGUMENTS,
                        Joiner.on(" ").join(buildCommand(repositoryAccessor)));
                launch = workingCopy.launch(ILaunchManager.RUN_MODE, monitor);
                waitForUID(new PageDesignerURLFactory(getPreferenceStore()));
                BonitaStudioLog.info(String.format("UI Designer has been started on http://localhost:%s/designer", port),
                        UIDesignerPlugin.PLUGIN_ID);
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
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }

    private void connectToURL(final URL url) {
        try {
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
            connection.setRequestMethod("HEAD");
            connection.setReadTimeout(200);
            final int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                connectToURL(url);
            }
        } catch (final IOException e) {
            try {
                Thread.sleep(500);
            } catch (final InterruptedException e1) {
            }
            connectToURL(url);
        }
    }

    private Path logLocation() {
        return extractLocation().resolve("logs");
    }

    private Path extractLocation() {
        Bundle bundle = FrameworkUtil.getBundle(UIDesignerServerManager.class);
        IPath stateLoc = Platform.getStateLocation(bundle);
        return stateLoc.toFile().toPath().resolve(".extract");
    }

    public Optional<File> getLogFile() {
        final File logDir = logLocation().toFile();
        final List<File> list = Arrays.asList(logDir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(final File file, final String fileName) {
                return fileName.contains(UI_DESIGNER_BASE_NAME);
            }
        }));

        return list.stream()
                .sorted(new Comparator<File>() {

                    @Override
                    public int compare(File file1, File file2) {
                        return file1.lastModified() > file2.lastModified()
                                ? -1
                                : file1.lastModified() < file2.lastModified()
                                        ? 1
                                        : 0;
                    }
                })
                .findFirst();
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
        if (launch != null) {
            try {
                launch.terminate();
                BonitaStudioLog.info("UI Designer has been stopped.", UIDesignerPlugin.PLUGIN_ID);
                launch = null;
            } catch (DebugException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    public synchronized void restart(IProgressMonitor monitor) {
        stop();
        start(monitor);
    }

    protected String javaBinaryLocation() throws FileNotFoundException {
        File javaBinaryPath = StandardVMType.findJavaExecutable(JavaRuntime.getDefaultVMInstall().getInstallLocation());
        if (javaBinaryPath == null || !javaBinaryPath.exists()) {
            throw new FileNotFoundException(
                    String.format("Java binary not found at '%s'", javaBinaryPath.getAbsolutePath()));
        }
        return javaBinaryPath.getAbsolutePath();
    }

    protected List<String> buildCommand(final RepositoryAccessor repositoryAccessor) throws IOException {
        final WorkspaceSystemProperties workspaceSystemProperties = new WorkspaceSystemProperties(repositoryAccessor);
        if (port == -1 || !isPortAvailable(port)) {
            port = SocketUtil.findFreePort();
        }
        return Arrays.asList(
                "-jar",
                "\"" + locateUIDjar() + "\"",
                workspaceSystemProperties.getPageRepositoryLocation(),
                workspaceSystemProperties.getWidgetRepositoryLocation(),
                workspaceSystemProperties.getFragmentRepositoryLocation(),
                workspaceSystemProperties.getRestAPIURL(WorkspaceResourceServerManager.getInstance().runningPort()),
                workspaceSystemProperties.activateSpringProfile("studio"),
                "-Dbonita.client.home=\"" + System.getProperty(BONITA_CLIENT_HOME) + "\"",
                " -extractDirectory",
                extractLocation().toFile().getAbsolutePath(),
                "-httpPort",
                String.valueOf(port));
    }

    private static boolean isPortAvailable(int port) {
        try (Socket ignored = new Socket("localhost", port)) {
            return false;
        } catch (IOException ignored) {
            return true;
        }
    }

    public int getPort() {
        return port;
    }

    protected String locateUIDjar() throws IOException {
        final URL url = Platform.getBundle(UIDesignerPlugin.PLUGIN_ID).getResource("webapp");
        File webappFolder = new File(URLDecoder.decode(FileLocator.toFileURL(url).getFile(), "UTF-8"));
        File execJar = new File(webappFolder, "ui-designer-backend-webapp.jar");
        if (!execJar.exists()) {
            throw new FileNotFoundException(
                    String.format("Cannot find ui designer jar file in %s folder.", webappFolder.getAbsolutePath()));
        }
        return execJar.getAbsolutePath();
    }

    protected ILaunchManager getLaunchManager() {
        return DebugPlugin.getDefault().getLaunchManager();
    }
}
