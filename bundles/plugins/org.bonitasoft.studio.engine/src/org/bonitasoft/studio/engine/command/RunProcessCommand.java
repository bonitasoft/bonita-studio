/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.designer.core.operation.IndexingUIDOperation;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.ProcessSelector;
import org.bonitasoft.studio.engine.operation.RunOperationExecutionContext;
import org.bonitasoft.studio.engine.operation.RunProcessOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.bonitasoft.studio.validation.common.operation.BatchValidationOperation;
import org.bonitasoft.studio.validation.common.operation.BatchValidatorFactory;
import org.bonitasoft.studio.validation.common.operation.OffscreenEditPartFactory;
import org.bonitasoft.studio.validation.common.operation.RunProcessesValidationOperation;
import org.bonitasoft.studio.validation.common.operation.ValidationMarkerProvider;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class RunProcessCommand extends AbstractHandler {

    protected static final String APPLI_PATH = "/bonita?"; //$NON-NLS-1$;
    protected boolean runSynchronously;
    private IStatus status;
    private URL url;
    private FileStoreFinder fileStoreFinder;

    public RunProcessCommand() {
        this(false);
    }

    public RunProcessCommand(final boolean runSynchronously) {
        this.runSynchronously = runSynchronously;
        fileStoreFinder = new FileStoreFinder();
    }

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final String configurationId = retrieveConfigurationId(event);
        ProcessSelector processSelector = new ProcessSelector(event);
        DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        if (runSynchronously) {
            diagramStore.computeProcesses(AbstractRepository.NULL_PROGRESS_MONITOR);
        } else {
            try {
                PlatformUI.getWorkbench().getProgressService().run(true, false, diagramStore::computeProcesses);
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }
       final Set<AbstractProcess> executableProcesses = processSelector.getExecutableProcesses();
        if (executableProcesses.isEmpty()) {
            MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.noProcessToRunTitle,
                    Messages.noProcessToRun);
            return ValidationStatus.cancel(Messages.noProcessToRunTitle);
        }

        final List<AbstractProcess> processes = new ArrayList<>(executableProcesses);
        final RunProcessesValidationOperation validationOperation = new RunProcessesValidationOperation(
                new BatchValidationOperation(
                        new OffscreenEditPartFactory(
                                org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory.getInstance()),
                        new ValidationMarkerProvider(),
                        new BatchValidatorFactory().create()));
        validationOperation.addProcesses(processes);
        try {
            new IndexingUIDOperation().run(AbstractRepository.NULL_PROGRESS_MONITOR);
            if (runSynchronously) {
                validationOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
            } else {
                new SkippableProgressMonitorJobsDialog(Display.getDefault().getActiveShell()).canBeSkipped().run(true,
                        false,
                        validationOperation);
            }
        } catch (final InvocationTargetException e) {
            throw new ExecutionException("Error occured during validation", e);
        } catch (final InterruptedException e) {
            //Continue
        }finally {
            RepositoryManager.getInstance().getCurrentRepository().clearGroovySnippetCompiler();
        }
        if (!validationOperation.displayConfirmationDialog()) {
            diagramStore.resetComputedProcesses();
            return null;
        }

        final RunOperationExecutionContext executionContext = new RunOperationExecutionContext(configurationId);
        executionContext.setSynchronousExecution(runSynchronously);
        final RunProcessOperation runProcessOperation = createRunProcessOperation(event, executionContext);

        try {
            if (runSynchronously) {
                Display.getDefault().syncExec(runProcessOperation);
                url = runProcessOperation.getUrl();
                status = runProcessOperation.getStatus();
                diagramStore.resetComputedProcesses();
            } else {
                Job job = new Job(Messages.running) {

                    @Override
                    public IStatus run(IProgressMonitor monitor) {
                        try {
                            runProcessOperation.run(monitor);
                            url = runProcessOperation.getUrl();
                            status = runProcessOperation.getStatus();
                            diagramStore.resetComputedProcesses();
                        } catch (InvocationTargetException | InterruptedException e) {
                            return new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
                        }
                        //Run status has already been reported in RunProcessOperation operation
                        return Status.OK_STATUS;
                    }
                };
                job.setUser(true);
                job.schedule();
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, e.getMessage(), e);
            if (!runSynchronously && !status.isOK()) {
                Display.getDefault().syncExec(() -> {
                    StringBuilder sb = new StringBuilder(Messages.deploymentFailedMessage);
                    sb.append("\n");
                    sb.append(status.getMessage());
                    new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.deploymentFailedMessage,
                            sb.toString(), status,
                            IStatus.ERROR)
                                    .open();
                });
            }
        }
        return status;
    }

    protected RunProcessOperation createRunProcessOperation(final ExecutionEvent event,
            final RunOperationExecutionContext executionContext) {
        return new RunProcessOperation(executionContext, new ProcessSelector(event));
    }

    protected String retrieveConfigurationId(final ExecutionEvent event) {
        String configurationId = null;
        if (event != null) {
            configurationId = event.getParameter("configuration");
        }
        if (configurationId == null) {
            configurationId = ConfigurationPlugin.getDefault().getPreferenceStore()
                    .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        }
        return configurationId;
    }

    @Override
    public boolean isEnabled() {
        if (RepositoryManager.getInstance().hasActiveRepository()
                && RepositoryManager.getInstance().getCurrentRepository().isLoaded()) {
            boolean diagramSelectedInExplorer = fileStoreFinder
                    .findSelectedFileStore(RepositoryManager.getInstance().getCurrentRepository())
                    .filter(DiagramFileStore.class::isInstance).isPresent();
            if (diagramSelectedInExplorer) {
                return true;
            } else if (PlatformUI.isWorkbenchRunning()
                    && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
                final MainProcess process = ProcessSelector.getProcessInEditor();
                return process != null && process.isEnableValidation();
            }
        }
        return false;
    }

    public URL getUrl() {
        return url;
    }

    public void setRunSynchronously(final boolean runSynchronously) {
        this.runSynchronously = runSynchronously;
    }

}
