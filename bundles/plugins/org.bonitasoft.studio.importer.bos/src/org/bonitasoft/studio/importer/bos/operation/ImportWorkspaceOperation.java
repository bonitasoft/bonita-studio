package org.bonitasoft.studio.importer.bos.operation;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.ImportRepositoryModel;
import org.bonitasoft.studio.importer.bos.model.ImportWorkspaceModel;
import org.bonitasoft.studio.ui.util.StatusCollectors;
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

public class ImportWorkspaceOperation implements IRunnableWithProgress {

    private static final String APPLICATION_ID = "org.bonitasoft.studio.importer.bos.ImportWorkspaceApplication";

    private final ImportWorkspaceModel workspaceModel;
    protected RepositoryAccessor repositoryAccessor;
    private MultiStatus status = new MultiStatus(BosArchiveImporterPlugin.PLUGIN_ID, 0, "", null);

    public ImportWorkspaceOperation(ImportWorkspaceModel workspaceModel, RepositoryAccessor repositoryAccessor) {
        this.workspaceModel = requireNonNull(workspaceModel);
        this.repositoryAccessor = requireNonNull(repositoryAccessor);
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.importingWorkspace, IProgressMonitor.UNKNOWN);
        final ILaunchManager manager = getLaunchManager();
        final ILaunchConfigurationType ltype = manager
                .getLaunchConfigurationType(IPDELauncherConstants.ECLIPSE_APPLICATION_LAUNCH_CONFIGURATION_TYPE);

        final String currentRepository = repositoryAccessor.getCurrentRepository().getName();
        try {
            final ILaunchConfigurationWorkingCopy workingCopy = ltype.newInstance(null, "Import workspace");
            workingCopy.setAttribute(IPDELauncherConstants.APPLICATION,
                    applicationId());
            workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS,
                    String.format("-export=\"%s\"", workspaceModel.getRepositories()
                            .filter(repo -> repo.getStatus().isOK())
                            .map(ImportRepositoryModel::getName)
                            .collect(Collectors.joining(":"))));
            if (Objects.equals(Platform.getOS(), Platform.OS_MACOSX)) {
                workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_USE_START_ON_FIRST_THREAD, true);
            }
            workingCopy.setAttribute(IPDELauncherConstants.CONFIG_CLEAR_AREA, false);
            workingCopy.setAttribute(IPDELauncherConstants.RUN_IN_UI_THREAD, true);
            workingCopy.setAttribute(IPDELauncherConstants.DOCLEAR, false);
            workingCopy.setAttribute(IPDELauncherConstants.LOCATION,
                    new File(System.getProperty("java.io.tmpdir"), ScanWorkspaceOperation.TMP_WS_FOLDER).getAbsolutePath());
            workingCopy.setAttribute(IPDELauncherConstants.USE_PRODUCT, false);
            final ILaunch launch = workingCopy.launch("run", AbstractRepository.NULL_PROGRESS_MONITOR);
            launch.getProcesses()[0].getStreamsProxy().getOutputStreamMonitor().addListener(new IStreamListener() {

                @Override
                public void streamAppended(String text, IStreamMonitor streamMonitor) {
                    if (text.startsWith("$EXPORT_PROGRESS_")) {
                        monitor.subTask(text.replace("$EXPORT_PROGRESS_", "").split(System.lineSeparator())[0]);
                    }
                }
            });
            while (!launch.isTerminated()) {
                Thread.sleep(100);
            }
            if (launch.isTerminated()) {
                status = workspaceModel.getRepositories()
                        .filter(repo -> repo.getStatus().isOK())
                        .map(repo -> {
                            final ImportBosArchiveOperation operation = new ImportBosArchiveOperation(repositoryAccessor);
                            operation.setArchiveFile(repo.getArchiveFile().getAbsolutePath());
                            operation.disableValidation();
                            repositoryAccessor.setRepository(repo.getName());
                            operation.setCurrentRepository(repositoryAccessor.getRepository(repo.getName()));
                            return operation;
                        })
                        .map(op -> {
                            try {
                                op.run(monitor);
                                return op.getStatus();
                            } catch (InvocationTargetException | InterruptedException e) {
                                return new Status(IStatus.ERROR, BosArchiveImporterPlugin.PLUGIN_ID, e.getMessage(), e);
                            }
                        })
                        .collect(StatusCollectors.toMultiStatus());
            }
        } catch (final CoreException e) {
            throw new InvocationTargetException(e);
        } finally {
            repositoryAccessor.setRepository(currentRepository);
            monitor.done();
        }
    }

    public IStatus getStatus() {
        return status;
    }

    protected String applicationId() {
        return APPLICATION_ID;
    }

    protected ILaunchManager getLaunchManager() {
        return DebugPlugin.getDefault().getLaunchManager();
    }

}
