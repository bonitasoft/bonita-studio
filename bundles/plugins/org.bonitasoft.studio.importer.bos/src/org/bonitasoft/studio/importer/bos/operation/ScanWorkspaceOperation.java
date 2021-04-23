package org.bonitasoft.studio.importer.bos.operation;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.ImportRepositoryModel;
import org.bonitasoft.studio.importer.bos.model.ImportWorkspaceModel;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.IStreamListener;
import org.eclipse.debug.core.model.IStreamMonitor;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.pde.launching.IPDELauncherConstants;

public class ScanWorkspaceOperation implements IRunnableWithProgress {

    private static final String APPLICATION_ID = "org.bonitasoft.studio.importer.bos.ImportWorkspaceApplication";
    public static final String TMP_WS_FOLDER = ".tmpWS";
    private final ImportWorkspaceModel workspaceModel;
    protected AbstractRepository repository;

    public ScanWorkspaceOperation(File folder, AbstractRepository repository) {
        this.workspaceModel = new ImportWorkspaceModel(requireNonNull(folder.getAbsolutePath()));
        this.repository = requireNonNull(repository);
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.scanningWorkspace, IProgressMonitor.UNKNOWN);
        final ILaunchManager manager = getLaunchManager();
        final ILaunchConfigurationType ltype = manager
                .getLaunchConfigurationType(IPDELauncherConstants.ECLIPSE_APPLICATION_LAUNCH_CONFIGURATION_TYPE);
        try {
            final ILaunchConfigurationWorkingCopy workingCopy = ltype.newInstance(null, "Scan workspace");
            workingCopy.setAttribute(IPDELauncherConstants.APPLICATION,
                    applicationId());
            workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS, "-scan");
            if (Objects.equals(Platform.getOS(), Platform.OS_MACOSX)) {
                workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_USE_START_ON_FIRST_THREAD, true);
            }
            workingCopy.setAttribute(IPDELauncherConstants.CONFIG_CLEAR_AREA, false);
            workingCopy.setAttribute(IPDELauncherConstants.RUN_IN_UI_THREAD, true);
            workingCopy.setAttribute(IPDELauncherConstants.DOCLEAR, false);
            workingCopy.setAttribute(IPDELauncherConstants.LOCATION, tmpWorskpaceFolder());
            workingCopy.setAttribute(IPDELauncherConstants.USE_PRODUCT, false);
            final ILaunch launch = workingCopy.launch("run", AbstractRepository.NULL_PROGRESS_MONITOR);
            launch.getProcesses()[0].getStreamsProxy().getOutputStreamMonitor().addListener(new IStreamListener() {

                @Override
                public void streamAppended(String text, IStreamMonitor streamMonitor) {
                    if (text.startsWith("$SCAN_PROGRESS_")) {
                        scanStatus(text);
                    }
                }
            });
            while (!launch.isTerminated()) {
                Thread.sleep(1000);
            }
            if (workspaceModel.isEmpty()) {
                workspaceModel.setStatus(ValidationStatus.error(Messages.noRepositoryFoundAtLocation));
            }
            if (launch.isTerminated()) {
                monitor.done();
            }
        } catch (final CoreException | IOException e) {
            throw new InvocationTargetException(e);
        }
    }

    private String tmpWorskpaceFolder() throws IOException {
        File wsFolder = new File(workspaceModel.getWorksapceFolder());
        File tmpFolder = new File(System.getProperty("java.io.tmpdir"), TMP_WS_FOLDER);
        if (tmpFolder.exists()) {
            PlatformUtil.delete(tmpFolder, AbstractRepository.NULL_PROGRESS_MONITOR);
        }
        Files.walkFileTree(wsFolder.toPath(), new SimpleFileVisitor<Path>() {

            /*
             * (non-Javadoc)
             * @see java.nio.file.SimpleFileVisitor#preVisitDirectory(java.lang.Object,
             * java.nio.file.attribute.BasicFileAttributes)
             */
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (dir.endsWith(Paths.get("workspace", "tomcat")) //Do not copy tomcat folder
                        || dir.endsWith(Paths.get(".plugins", "org.eclipse.jdt.core"))) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                Files.createDirectories(tmpFolder.toPath().resolve(wsFolder.toPath()
                        .relativize(dir)));
                return FileVisitResult.CONTINUE;
            }

            /*
             * (non-Javadoc)
             * @see java.nio.file.SimpleFileVisitor#visitFile(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
             */
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.endsWith(Paths.get("workspace", ".metadata", ".log"))) {
                    return FileVisitResult.CONTINUE;
                }
                Files.copy(file, tmpFolder.toPath().resolve(wsFolder.toPath().relativize(file)));
                return FileVisitResult.CONTINUE;
            }
        });
        return tmpFolder.getAbsolutePath();
    }

    protected void scanStatus(String text) {
        final String[] scannedRepo = text.replace("$SCAN_PROGRESS_", "").split(":");
        final String repoName = scannedRepo[0];
        final String repoVersion = scannedRepo[1];
        final String connected = scannedRepo[2].trim();
        final ImportRepositoryModel repositoryModel = new ImportRepositoryModel(repoName, repoVersion);
        final MultiStatus repoStatus = new MultiStatus(BosArchiveImporterPlugin.PLUGIN_ID, 0, "", null);
        if ("Shared".equals(connected)) {
            repoStatus.add(ValidationStatus.error(String.format(Messages.projectConnectorToVCS, repoName)));
        }
        if (!ProductVersion.canBeImported(repoVersion)) {
            repoStatus.add(ValidationStatus
                    .error(String.format(Messages.cannotImportWorkspaceWithVersion, repoName, repoVersion)));
        }

        if (repoStatus.getChildren().length == 0) {
            repoStatus.add(
                    new Status(IStatus.OK, BosArchiveImporterPlugin.PLUGIN_ID, validRepositoryMessage(repositoryModel)));
        }
        repositoryModel.setStatus(repoStatus);
        workspaceModel.addRepository(repositoryModel);
    }

    private String validRepositoryMessage(final ImportRepositoryModel repositoryModel) {
        final IProject project = repository.getProject().getWorkspace().getRoot().getProject(repositoryModel.getName());
        return project.exists() ? String.format(Messages.validRepositoryOverwritten, repositoryModel.getName())
                : String.format(Messages.validRepository, repositoryModel.getName());
    }

    protected String applicationId() {
        return APPLICATION_ID;
    }

    protected ILaunchManager getLaunchManager() {
        return DebugPlugin.getDefault().getLaunchManager();
    }

    public ImportWorkspaceModel getImportWorkspaceModel() {
        return workspaceModel;
    }
}
