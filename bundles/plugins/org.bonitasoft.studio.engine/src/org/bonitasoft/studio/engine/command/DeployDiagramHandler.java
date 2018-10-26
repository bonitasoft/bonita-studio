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

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.DeployProcessOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.validation.common.operation.BatchValidationOperation;
import org.bonitasoft.studio.validation.common.operation.OffscreenEditPartFactory;
import org.bonitasoft.studio.validation.common.operation.RunProcessesValidationOperation;
import org.bonitasoft.studio.validation.common.operation.ValidationMarkerProvider;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.progress.ProgressMonitorFocusJobDialog;

public class DeployDiagramHandler extends AbstractHandler {

    private RepositoryAccessor repositoryAccessor;

    public DeployDiagramHandler() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        String configurationId = retrieveDefaultConfiguration();
        DiagramFileStore diagramFileStore = retrieveDiagram(event);
        List<AbstractProcess> processes = diagramFileStore.getProcesses();
        if (validateDiagram(processes).isOK()) {
            DeployProcessOperation deployOperation = createDeployProcessOperation();
            deployOperation.setConfigurationId(configurationId);
            deployOperation.setDisablePopup(false);
            processes.forEach(deployOperation::addProcessToDeploy);
            Job deployJob = new Job(String.format(Messages.deployingProcessesFrom, diagramFileStore.getName())) {

                @Override
                protected IStatus run(IProgressMonitor monitor) {
                    deployOperation.run(monitor);
                    return deployOperation.getStatus();
                }
            };
            deployJob.addJobChangeListener(new JobChangeAdapter() {

                @Override
                public void done(IJobChangeEvent event) {
                    Display.getDefault().syncExec(() -> displayDeployResult(deployOperation));
                }
            });
            deployJob.setUser(true);
            deployJob.schedule();
            Shell activeShell = Display.getDefault().getActiveShell();
            ProgressMonitorFocusJobDialog dialog = new ProgressMonitorFocusJobDialog(activeShell);
            dialog.show(deployJob, activeShell);
        }
        return null;
    }

    private void displayDeployResult(DeployProcessOperation deployOperation) {
        IStatus deployStatus = deployOperation.getStatus();
        Shell shell = Display.getDefault().getActiveShell();
        switch (deployStatus.getSeverity()) {
            case IStatus.OK:
                MessageDialog.openInformation(shell, Messages.deployDoneTitle, Messages.deployDoneMessage);
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

    private IStatus validateDiagram(List<AbstractProcess> processes) throws ExecutionException {
        if (BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getBoolean(BonitaPreferenceConstants.VALIDATION_BEFORE_RUN)) {
            RunProcessesValidationOperation validationOperation = new RunProcessesValidationOperation(
                    new BatchValidationOperation(
                            new OffscreenEditPartFactory(
                                    org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory.getInstance()),
                            new ValidationMarkerProvider()));
            validationOperation.addProcesses(processes);
            try {
                PlatformUI.getWorkbench().getProgressService().run(true, false, validationOperation);
            } catch (InvocationTargetException | InterruptedException e) {
                throw new ExecutionException("An error occured during validation", e);
            }
            return validationOperation.displayConfirmationDialog()
                    ? ValidationStatus.ok()
                    : ValidationStatus.cancel("");
        }
        return ValidationStatus.ok();
    }

    protected String retrieveDefaultConfiguration() {
        return ConfigurationPlugin.getDefault().getPreferenceStore()
                .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
    }

    protected DiagramFileStore retrieveDiagram(ExecutionEvent event) {
        String diagramFileStoreName = event.getParameter("diagram");
        DiagramFileStore diagramFileStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class)
                .getChild(diagramFileStoreName);
        if (diagramFileStore == null) {
            throw new IllegalArgumentException(String.format(Messages.diagramDoesntExist, diagramFileStoreName));
        }
        return diagramFileStore;
    }

}
