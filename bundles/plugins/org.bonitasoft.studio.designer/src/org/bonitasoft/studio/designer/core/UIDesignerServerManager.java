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
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.IBonitaProjectListener;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.core.repository.MigrateUIDOperation;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
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
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.SocketUtil;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.common.base.Joiner;

public class UIDesignerServerManager implements IBonitaProjectListener {

    private static final String UI_DESIGNER_BASE_NAME = "ui-designer";
    private static UIDesignerServerManager INSTANCE;
    private int port = -1;
    private ILaunch launch;
    private int portalPort = 8080;
    private static final String BONITA_CLIENT_HOME = "bonita.client.home";
    private static final String PORTAL_BASE_URL = "bonita.portal.origin";
    private PageDesignerURLFactory pageDesignerURLBuilder;

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

    public synchronized void start(Repository repository, IProgressMonitor monitor) {
        if (launch == null
                || Stream.of(launch.getProcesses())
                        .findFirst()
                        .map(IProcess::isTerminated)
                        .orElse(false)) {
            monitor.beginTask(Messages.startingUIDesigner, IProgressMonitor.UNKNOWN);
            BonitaStudioLog.info(Messages.startingUIDesigner, UIDesignerPlugin.PLUGIN_ID);
            try {
                if (!WorkspaceResourceServerManager.getInstance().isRunning()) {
                    WorkspaceResourceServerManager.getInstance().start(SocketUtil.findFreePort());
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
                        Joiner.on(" ").join(buildCommand(repository)));
                Map<String, String> env = new HashMap<>();
                env.put("JAVA_TOOL_OPTIONS", "-Dfile.encoding=UTF-8");
                workingCopy.setAttribute(ILaunchManager.ATTR_ENVIRONMENT_VARIABLES, env);
                launch = workingCopy.launch(ILaunchManager.RUN_MODE, Repository.NULL_PROGRESS_MONITOR);
                pageDesignerURLBuilder = new PageDesignerURLFactory(getPreferenceStore());
                waitForUID(pageDesignerURLBuilder);
                BonitaStudioLog.info(String.format("UI Designer has been started on http://localhost:%s/bonita", port),
                        UIDesignerPlugin.PLUGIN_ID);
                new MigrateUIDOperation(pageDesignerURLBuilder).run(monitor);
            } catch (final CoreException | IOException | InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error("Failed to run ui designer war", e);
            } finally {
                monitor.done();
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
        final List<File> list = Arrays
                .asList(logDir.listFiles((FilenameFilter) (file, fileName) -> fileName.contains(UI_DESIGNER_BASE_NAME)));

        return list.stream()
                .sorted((file1, file2) -> file1.lastModified() > file2.lastModified()
                        ? -1
                        : compareLastModified(file1, file2))
                .findFirst();
    }

    private int compareLastModified(File file1, File file2) {
        return file1.lastModified() < file2.lastModified() ? 1 : 0;
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

    protected List<String> buildCommand(Repository repository) throws IOException {
        final WorkspaceSystemProperties workspaceSystemProperties = new WorkspaceSystemProperties(repository);
        port = getPreferenceStore().getInt(BonitaPreferenceConstants.UID_PORT, -1);
        if (port == -1 || !isPortAvailable(port)) {
            port = SocketUtil.findFreePort();
            getPreferenceStore().putInt(BonitaPreferenceConstants.UID_PORT, port);
        }
        File cpJar = configureClasspath();
        return Arrays.asList(
                getPreferenceStore().get(BonitaPreferenceConstants.UID_JVM_OPTS, "-Xmx256m"),
                "-classpath",
                cpJar == null ? "\"" + locateUIDjar() + "\""
                        : "\"" + locateUIDjar() + "\"" + System.getProperty("path.separator") + "\""
                                + cpJar.getAbsolutePath() + "\"",
                "org.apache.tomcat.maven.runner.Tomcat7RunnerCli",
                workspaceSystemProperties.getPageRepositoryLocation(),
                workspaceSystemProperties.getWidgetRepositoryLocation(),
                workspaceSystemProperties.getFragmentRepositoryLocation(),
                workspaceSystemProperties.getRestAPIURL(WorkspaceResourceServerManager.getInstance().runningPort()),
                workspaceSystemProperties.activateSpringProfile("studio"),
                String.format("-D%s=http://localhost:%s", PORTAL_BASE_URL, portalPort),
                "-Declipse.product=\"" + getProductApplicationId() + "\"",
                "-Dbonita.client.home=\"" + System.getProperty(BONITA_CLIENT_HOME) + "\"",
                " -extractDirectory",
                String.format("\"%s\"", extractLocation().toFile().getAbsolutePath()),
                "-httpPort",
                String.valueOf(port));
    }

    private String getProductApplicationId() {
        return Platform.getProduct() != null ? Platform.getProduct().getApplication() : null;
    }

    private static boolean isPortAvailable(int port) {
        try {
            return org.eclipse.wst.server.core.util.SocketUtil.isPortInUse(InetAddress.getByName("localhost"), port)
                    || org.eclipse.wst.server.core.util.SocketUtil.isPortInUse(port);
        } catch (UnknownHostException e) {
            return org.eclipse.wst.server.core.util.SocketUtil.isPortInUse(port);
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
        return execJar.getCanonicalFile().getAbsolutePath();
    }

    protected ILaunchManager getLaunchManager() {
        return DebugPlugin.getDefault().getLaunchManager();
    }

    protected File configureClasspath() {
        final Bundle bundle = Platform.getBundle("org.bonitasoft.studio.common.ex");
        if (bundle != null) {
            if (Platform.inDevelopmentMode()) {
                try {
                    return new File(new File(FileLocator.getBundleFile(bundle), "lib"), "addons.jar").getCanonicalFile();
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            } else {
                try {
                    final URL fileUrl = bundle.loadClass("org.bonitasoft.studio.common.ex.contribution.SilentException")
                            .getClassLoader()
                            .getResource("com/bonitasoft/studio/logger/LoggerException.class");
                    URL addonsResource = FileLocator.resolve(fileUrl);
                    if (addonsResource.toString().startsWith("jar:file")) {
                        addonsResource = new URL(addonsResource.toString().substring("jar:".length(),
                                addonsResource.toString().lastIndexOf('!')));
                    }
                    return new File(addonsResource.getFile());
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        return null;
    }

    public PageDesignerURLFactory getPageDesignerURLBuilder() {
        return pageDesignerURLBuilder;
    }

    public boolean isStarted() {
        return launch != null;
    }

    @Override
    public void projectOpened(Repository repository, IProgressMonitor monitor) {
        if (PlatformUI.isWorkbenchRunning()) {
            start(repository, monitor);
        }
    }

    @Override
    public void projectClosed(Repository repository, IProgressMonitor monitor) {
        if (PlatformUI.isWorkbenchRunning()) {
            stop();
        }
    }

}
