/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.eclipse.core.externaltools.internal.IExternalToolConstants;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jdt.internal.launching.StandardVMType;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.SocketUtil;

import com.google.common.base.Joiner;

public class OpenH2ConsoleHandler {

    private static final String URL = "\"jdbc:h2:file:%s/%s;MVCC=TRUE;DB_CLOSE_ON_EXIT=TRUE;IGNORECASE=TRUE;AUTO_SERVER=TRUE;\"";
    private static final String DRIVER = "org.h2.Driver";
    private static final String USER = "sa";
    private static final int PORT = SocketUtil.findFreePort();
    private IProcess currentProcess;

    @Execute
    public void execute(final RepositoryAccessor repositoryAccessor) {
        if (currentProcess != null) {
            try {
                currentProcess.terminate();
            } catch (DebugException e) {
                BonitaStudioLog.error(e);
            }
        }
        final ILaunchManager manager = getLaunchManager();
        final ILaunchConfigurationType ltype = manager
                .getLaunchConfigurationType(IExternalToolConstants.ID_PROGRAM_LAUNCH_CONFIGURATION_TYPE);
        try {
            final ILaunchConfigurationWorkingCopy workingCopy = ltype.newInstance(null, "Open h2 web console");
            workingCopy.setAttribute(IExternalToolConstants.ATTR_LOCATION, javaBinaryLocation());
            workingCopy.setAttribute(IExternalToolConstants.ATTR_TOOL_ARGUMENTS,
                    Joiner.on(" ").join(buildCommand(repositoryAccessor)));
            workingCopy.setAttribute(IDebugUIConstants.ATTR_APPEND_TO_FILE, true);
            workingCopy.setAttribute(IDebugUIConstants.ATTR_CAPTURE_IN_FILE, logFile().getAbsolutePath());
            ILaunch launch = workingCopy.launch(ILaunchManager.RUN_MODE, AbstractRepository.NULL_PROGRESS_MONITOR);
            IProcess[] processes = launch.getProcesses();
            if (processes != null && processes.length == 1) {
                currentProcess = processes[0];
            }
        } catch (final CoreException | IOException e) {
            BonitaStudioLog.error("Failed to run h2 console", e);
        }
    }

    protected String javaBinaryLocation() throws FileNotFoundException {
        File javaBinaryPath = StandardVMType.findJavaExecutable(JavaRuntime.getDefaultVMInstall().getInstallLocation());
        if (javaBinaryPath == null || !javaBinaryPath.exists()) {
            throw new FileNotFoundException(
                    String.format("Java binary not found at '%s'", javaBinaryPath.getAbsolutePath()));
        }
        return javaBinaryPath.getAbsolutePath();
    }

    protected ILaunchManager getLaunchManager() {
        return DebugPlugin.getDefault().getLaunchManager();
    }

    protected List<String> buildCommand(final RepositoryAccessor repositoryAccessor) throws IOException {
        return Arrays.asList(
                "-jar",
                "\"" + locateH2jar(repositoryAccessor) + "\"",
                "-browser",
                "-webPort",
                String.valueOf(PORT),
                "-tcp",
                "-user",
                USER,
                "-url",
                String.format(URL, pathToDBFolder(repositoryAccessor), getDatabaseHandler(repositoryAccessor).getBusinessDataDBName()),
                "-driver",
                DRIVER);
    }

    DatabaseHandler getDatabaseHandler(final RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().getDatabaseHandler();
    }

    protected File logFile() throws CoreException {
        final IFileStore fileStore = EFS.getLocalFileSystem().getStore(Platform.getLogFileLocation());
        return fileStore.toLocalFile(EFS.NONE, AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    protected String pathToDBFolder(final RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().getDatabaseHandler().getDBLocation().getAbsolutePath();
    }

    protected String locateH2jar(RepositoryAccessor repositoryAccessor) throws IOException {
        final File root = rootFile(repositoryAccessor);
        final Path path = Paths.get(root.toURI()).resolve(Paths.get("tomcat", "server", "lib", "bonita"));
        return Stream.of(path.toFile().listFiles(this::h2Jar))
                .findFirst()
                .orElseThrow(() -> new FileNotFoundException(String.format("Cannot find h2 jar file in %s folder.", path)))
                .getAbsolutePath();
    }

    protected File rootFile(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getWorkspace().getRoot().getLocation().toFile();
    }

    private boolean h2Jar(File dir, String name) {
        return name.startsWith("h2-") && name.endsWith(".jar");
    }
    
    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return DatabaseHandler.DEFAULT_DB_VENDOR
                .equals(repositoryAccessor.getCurrentRepository().getDatabaseHandler().getBDMDBVendor());
    }

}
