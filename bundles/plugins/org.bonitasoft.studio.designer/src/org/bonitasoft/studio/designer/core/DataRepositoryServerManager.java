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
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.externaltools.internal.IExternalToolConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.internal.core.IInternalDebugCoreConstants;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.preferences.IDebugPreferenceConstants;
import org.eclipse.jdt.launching.SocketUtil;
import org.eclipse.jface.preference.IPreferenceStore;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.google.common.base.Joiner;

public class DataRepositoryServerManager {

    private static final String DATA_REPOSITORY_ARCHIVE_BINARY_PATH = "repository/bonita-data-repository.zip";
    private static final String PORT_ARG = "port";
    private static final String HEALTH_CHECK_PORT_ARG = "healthCheckPort";
    private static final String HEALTH_CHECK_URL_ARG = "healthCheckUrl";
    private static final String LOG_FILE_ARG = "logFile";
    private static final String DATA_REPOSITOR_LOG_BASE_NAME = "data-repository";

    private static DataRepositoryServerManager INSTANCE;

    private int port = -1;
    private ILaunch launch;

    private DataRepositoryServerManager() {
        addShutdownHook();
        initializeDebugPreferences();
    }

    private void initializeDebugPreferences() {
        IPreferenceStore store = DebugUIPlugin.getDefault().getPreferenceStore();
        IEclipsePreferences prefNode = DefaultScope.INSTANCE.getNode(DebugPlugin.getUniqueIdentifier());
        prefNode.putBoolean(IInternalDebugCoreConstants.PREF_ENABLE_STATUS_HANDLERS, false);
        store.setValue(IDebugPreferenceConstants.CONSOLE_OPEN_ON_OUT, false);
        store.setValue(IDebugPreferenceConstants.CONSOLE_OPEN_ON_ERR, false);
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
    }

    public static synchronized DataRepositoryServerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataRepositoryServerManager();
        }
        return INSTANCE;
    }

    public synchronized void start(IProgressMonitor monitor) {
        if (launch == null
                || Stream.of(launch.getProcesses())
                        .findFirst()
                        .map(IProcess::isTerminated)
                        .orElse(false)) {
            monitor.beginTask(Messages.startingDataRepositoryService, IProgressMonitor.UNKNOWN);
            BonitaStudioLog.info(Messages.startingDataRepositoryService, UIDesignerPlugin.PLUGIN_ID);
            final ILaunchManager manager = getLaunchManager();
            final ILaunchConfigurationType ltype = manager
                    .getLaunchConfigurationType(IExternalToolConstants.ID_PROGRAM_LAUNCH_CONFIGURATION_TYPE);

            try {
                final ILaunchConfigurationWorkingCopy workingCopy = ltype.newInstance(null, "Data Repository Service");
                workingCopy.setAttribute(IExternalToolConstants.ATTR_LOCATION, dataRepositoryBinaryLocation());
                workingCopy.setAttribute(IExternalToolConstants.ATTR_TOOL_ARGUMENTS,
                        Joiner.on(" ").join(buildCommand()));
                launch = workingCopy.launch(ILaunchManager.RUN_MODE, Repository.NULL_PROGRESS_MONITOR);
                if (launch != null && launch.getProcesses().length > 0) {
                    BonitaStudioLog.info(
                            String.format("Data Repository Service has been started on http://localhost:%s/", port),
                            UIDesignerPlugin.PLUGIN_ID);
                } else {
                    BonitaStudioLog.error(
                            String.format("Data Repository Service failed to start on http://localhost:%s/", port),
                            UIDesignerPlugin.PLUGIN_ID);
                }
            } catch (final CoreException | IOException e) {
                BonitaStudioLog.error("Failed to run data repository service", e);
            } finally {
                monitor.done();
            }
        }
    }

    protected IEclipsePreferences getPreferenceStore() {
        return InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID);
    }

    private String logLocation() {
        return Platform.getLogFileLocation().toFile().getParentFile().getAbsolutePath();
    }

    public Optional<File> getLogFile() {
        final File logDir = new File(logLocation());
        final List<File> list = Arrays
                .asList(logDir.listFiles(
                        (FilenameFilter) (file, fileName) -> fileName.contains(DATA_REPOSITOR_LOG_BASE_NAME)));

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
        if (launch != null) {
            try {
                launch.terminate();
                BonitaStudioLog.info("Data Repository Service has been stopped.", UIDesignerPlugin.PLUGIN_ID);
                launch = null;
            } catch (DebugException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    private Path extractLocation() {
        Bundle bundle = FrameworkUtil.getBundle(UIDesignerServerManager.class);
        IPath stateLoc = Platform.getStateLocation(bundle);
        return stateLoc.toFile().toPath();
    }

    protected String dataRepositoryBinaryLocation() throws IOException {
        Optional<File> binaryFile = searchBinaryFile();
        if (!binaryFile.isPresent()) {
            URL binArchiveLocation = UIDesignerPlugin.getDefault().getBundle()
                    .getResource(DATA_REPOSITORY_ARCHIVE_BINARY_PATH);
            if (binArchiveLocation == null) {
                throw new FileNotFoundException("Data repository binary archive not found");
            }
            try {
                PlatformUtil.unzipZipFiles(new File(FileLocator.toFileURL(binArchiveLocation).getFile()),
                        extractLocation().toFile(), Repository.NULL_PROGRESS_MONITOR);
            } catch (Exception e) {
                BonitaStudioLog.error(e);
            }
            binaryFile = searchBinaryFile();
            if (!binaryFile.isPresent()) {
                throw new FileNotFoundException("Data repository binary not found");
            }
            binaryFile.get().setExecutable(true);
        }
        return binaryFile.get().getAbsolutePath();
    }

    private Optional<File> searchBinaryFile() {
        return Stream.of(extractLocation().toFile().listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("bonita-data-repository-");
            }
        })).findFirst();
    }

    protected List<String> buildCommand() throws IOException {
        port = getPreferenceStore().getInt(BonitaPreferenceConstants.DATA_REPOSITORY_PORT, -1);
        if (port == -1 || isPortInUse(port)) {
            port = SocketUtil.findFreePort();
            getPreferenceStore().putInt(BonitaPreferenceConstants.DATA_REPOSITORY_PORT, port);
        }
        return Arrays.asList(
                withArgument(PORT_ARG, String.valueOf(port)),
                withArgument(HEALTH_CHECK_URL_ARG, "/api/workspace/status/"),
                withArgument(HEALTH_CHECK_PORT_ARG,
                        String.valueOf(WorkspaceResourceServerManager.getInstance().runningPort())),
                withArgument(LOG_FILE_ARG, String.format("\"%s\"", logLocation())));
    }

    private String withArgument(String key, String value) {
        return String.format("%s=%s", key, value);
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

    protected ILaunchManager getLaunchManager() {
        return DebugPlugin.getDefault().getLaunchManager();
    }

    public boolean isStarted() {
        return launch != null;
    }

}
