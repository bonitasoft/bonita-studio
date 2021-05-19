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
package org.bonitasoft.studio.engine.command;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.DeployProcessOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.bonitasoft.studio.validation.common.operation.BatchValidationOperation;
import org.bonitasoft.studio.validation.common.operation.BatchValidatorFactory;
import org.bonitasoft.studio.validation.common.operation.OffscreenEditPartFactory;
import org.bonitasoft.studio.validation.common.operation.RunProcessesValidationOperation;
import org.bonitasoft.studio.validation.common.operation.ValidationMarkerProvider;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DeployDiagramHandler {

    public static final String PROCESS_UUID_PARAMETER = "process";
    public static final String DISABLE_POPUP_PARAMETER = "disablePopup";
    public static final String VALIDATE_DIAGRAM_PARAMETER = "validateDiagram";
    public static final String FILENAME_PARAMETER = "fileName";

    @Execute
    public IStatus execute(RepositoryAccessor repositoryAccessor,
            @Named(FILENAME_PARAMETER) String fileName,
            @Optional @Named(DISABLE_POPUP_PARAMETER) String disablePopup,
            @Optional @Named(VALIDATE_DIAGRAM_PARAMETER) String validateDiagram,
            @Optional @Named(PROCESS_UUID_PARAMETER) String processUUID) {
        boolean shouldDisablePopup = shouldDisablePopup(disablePopup);
        String configurationId = retrieveDefaultConfiguration();
        DiagramRepositoryStore diagamStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        boolean resetComputedProcesses = false;
        if(!diagamStore.hasComputedProcesses()) {
            diagamStore.computeProcesses(AbstractRepository.NULL_PROGRESS_MONITOR);
            resetComputedProcesses = true;
        }

        DiagramFileStore diagramFileStore = retrieveDiagram(repositoryAccessor, fileName);
        List<AbstractProcess> processes = processUUID != null ? diagamStore.getComputedProcesses().stream()
                .filter(process -> Objects.equals(processUUID, ModelHelper.getEObjectID(process)))
                .collect(Collectors.toList()) : diagramFileStore.getProcesses(false);
        IStatus validationStatus = ValidationStatus.ok();
        if (validateDiagram == null || Boolean.valueOf(validateDiagram)) {
            validationStatus = validateDiagram(processes);
        }
        if (validationStatus.isOK()) {
            DeployProcessOperation deployOperation = createDeployProcessOperation();
            deployOperation.setConfigurationId(configurationId);
            deployOperation.setDisablePopup(shouldDisablePopup);
            processes.forEach(deployOperation::addProcessToDeploy);
            if (!shouldDisablePopup) {
                runInJob(diagramFileStore, deployOperation, diagamStore, resetComputedProcesses);
            } else {
                repositoryAccessor.getCurrentRepository().build(AbstractRepository.NULL_PROGRESS_MONITOR);
                deployOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
                if(resetComputedProcesses) {
                    diagamStore.resetComputedProcesses();
                }
                return deployOperation.getStatus();
            }
        }
        return Status.OK_STATUS;
    }

    private void runInJob(DiagramFileStore diagramFileStore, DeployProcessOperation deployOperation, DiagramRepositoryStore diagamStore, boolean resetComputedProcesses) {
        Job deployJob = new Job(String.format(Messages.deployingProcessesFrom, diagramFileStore.getName())) {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                diagramFileStore.getRepository().build(monitor);
                return deployOperation.run(monitor);
            }
        };
        deployJob.addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
                if(resetComputedProcesses) {
                    diagamStore.resetComputedProcesses();
                }
                Display.getDefault().syncExec(() -> displayDeployResult(event.getResult()));
            }
        });
        deployJob.setUser(true);
        deployJob.schedule();
    }

    private boolean shouldDisablePopup(String disablePopup) {
        return disablePopup == null || Boolean.valueOf(disablePopup);
    }

    private void displayDeployResult(IStatus deployStatus) {
        Shell shell = Display.getDefault().getActiveShell();
        switch (deployStatus.getSeverity()) {
            case IStatus.OK:
                MessageDialog.openInformation(shell, Messages.deployDoneTitle, Messages.deployDoneMessage);
                break;
            case IStatus.WARNING:
                if (deployStatus instanceof MultiStatus) {
                    new MultiStatusDialog(shell, Messages.deployDoneTitle, Messages.deployWithWarningMessage,
                            new String[] { IDialogConstants.OK_LABEL }, (MultiStatus) deployStatus).open();
                } else {
                    MessageDialog.openWarning(shell, Messages.deployDoneTitle, deployStatus.getMessage());
                }
                break;
            case IStatus.CANCEL:
                break;
            default:
                MessageDialog.openError(shell, Messages.deploymentFailed, deployStatus.getMessage());
                break;
        }
    }

    protected DeployProcessOperation createDeployProcessOperation() {
        return new DeployProcessOperation();
    }

    private IStatus validateDiagram(List<AbstractProcess> processes) {
        RunProcessesValidationOperation validationOperation = new RunProcessesValidationOperation(
                new BatchValidationOperation(
                        new OffscreenEditPartFactory(
                                org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory.getInstance()),
                        new ValidationMarkerProvider(),
                        new BatchValidatorFactory().create()));
        validationOperation.addProcesses(processes);
        try {
            new SkippableProgressMonitorJobsDialog(Display.getDefault().getActiveShell()).canBeSkipped().run(true,
                    false,
                    validationOperation);
        } catch (InvocationTargetException | InterruptedException e) {
            return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
        }finally {
            RepositoryManager.getInstance().getCurrentRepository().clearGroovySnippetCompiler();
        }
        return validationOperation.displayConfirmationDialog()
                ? ValidationStatus.ok()
                : ValidationStatus.cancel("");
    }

    protected String retrieveDefaultConfiguration() {
        return ConfigurationPlugin.getDefault().getPreferenceStore()
                .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
    }

    protected DiagramFileStore retrieveDiagram(RepositoryAccessor repositoryAccessor, String diagramFileStoreName) {
        DiagramFileStore diagramFileStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class)
                .getChild(diagramFileStoreName, true);
        if (diagramFileStore == null) {
            throw new IllegalArgumentException(String.format(Messages.diagramDoesntExist, diagramFileStoreName));
        }
        return diagramFileStore;
    }

}
