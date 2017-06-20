package org.bonitasoft.studio.importer.bos.operation;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.Repository;
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
    private final ImportWorkspaceModel workspaceModel;
    protected Repository repository;

    public ScanWorkspaceOperation(File folder, Repository repository) {
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
            workingCopy.setAttribute(IPDELauncherConstants.LOCATION, workspaceModel.getWorksapceFolder());
            workingCopy.setAttribute(IPDELauncherConstants.USE_PRODUCT, false);
            final ILaunch launch = workingCopy.launch("run", Repository.NULL_PROGRESS_MONITOR);
            launch.getProcesses()[0].getStreamsProxy().getOutputStreamMonitor().addListener(new IStreamListener() {

                @Override
                public void streamAppended(String text, IStreamMonitor streamMonitor) {
                    if (text.startsWith("$SCAN_PROGRESS_")) {
                        scanStatus(text);
                    }
                }
            });
            while (!launch.isTerminated()) {
                Thread.sleep(100);
            }
            if (workspaceModel.isEmpty()) {
                workspaceModel.setStatus(ValidationStatus.error(Messages.noRepositoryFoundAtLocation));
            }
            if (launch.isTerminated()) {
                monitor.done();
            }
        } catch (final CoreException e) {
            throw new InvocationTargetException(e);
        }
    }

    protected void scanStatus(String text) {
        final String[] scannedRepo = text.replace("$SCAN_PROGRESS_", "").split(":");
        final String repoName = scannedRepo[0];
        final String repoVersion = scannedRepo[1];
        final String repoEdition = scannedRepo[2];
        final ImportRepositoryModel repositoryModel = new ImportRepositoryModel(repoName, repoVersion, repoEdition);
        final MultiStatus repoStatus = new MultiStatus(BosArchiveImporterPlugin.PLUGIN_ID, 0, "", null);
        if (!ProductVersion.canBeImported(repoVersion)) {
            repoStatus.add(ValidationStatus
                    .error(String.format(Messages.cannotImportWorkspaceWithVersion, repoName, repoVersion)));
        }
        final IStatus editionValid = isEditionValid(repoName, repoEdition);
        if (!editionValid.isOK()) {
            repoStatus.add(editionValid);
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

    protected IStatus isEditionValid(final String repoName, final String repoEdition) {
        if (!"Community".equals(repoEdition)) {
            return ValidationStatus
                    .error(String.format(Messages.cannotImportWorkspaceWithEdition, repoName, repoEdition));
        }
        return ValidationStatus.ok();
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
