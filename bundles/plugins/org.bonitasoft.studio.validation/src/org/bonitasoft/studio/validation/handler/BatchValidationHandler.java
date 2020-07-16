/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation.handler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.jface.ValidationDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.core.operation.IndexingUIDOperation;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.bonitasoft.studio.validation.ModelFileCompatibilityValidator;
import org.bonitasoft.studio.validation.ValidationPlugin;
import org.bonitasoft.studio.validation.common.operation.ProcessValidationOperation;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.bonitasoft.studio.validation.ui.view.ValidationViewPart;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
public class BatchValidationHandler extends AbstractHandler {

    private static final String CHECK_ALL_MODEL_VERSION_PARAM = "checkAllModelVersion";
    private DiagramFileStore currentDiagramStore;

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        if (!PlatformUI.isWorkbenchRunning()) {
            return IStatus.OK;
        }
        final Map<?, ?> parameters = event.getParameters();
        Repository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        ProcessValidationOperation validateOperation = new ProcessValidationOperation().forceMarkerUpdate();
        ModelFileCompatibilityValidator validateModelCompatibility = new ModelFileCompatibilityValidator(
                currentRepository.getProject().getLocation().toFile(), currentRepository)
                        .addResourceMarkers();
        boolean checkAllModelVersion = checkAllModelVersion(parameters);
        if (parameters != null && parameters.containsKey("diagrams")) {
            computeDiagramsToValidate(event, validateOperation, validateModelCompatibility);
        }
        if (checkAllModelVersion) {
            addAllProjectDiagrams(validateOperation, currentRepository);
        }

        MultiStatus aggregatedStatus = new MultiStatus(ValidationPlugin.PLUGIN_ID, -1, null, null);
        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        try {
            service.run(true, true, monitor -> {
                validateModelCompatibility.run(monitor);
                aggregatedStatus.addAll(validateModelCompatibility.getStatus());
                if (!checkAllModelVersion && aggregatedStatus.getSeverity() == IStatus.ERROR) {
                    return;
                }
                new IndexingUIDOperation().run(monitor);
                validateOperation.run(monitor);
                aggregatedStatus.addAll(validateOperation.getStatus());
            });
        } catch (final InvocationTargetException e) {
            throw new ExecutionException("Error during Validation", e);
        } catch (final InterruptedException e) {
            //Validation cancelled
        }

        if (!checkAllModelVersion && aggregatedStatus.getSeverity() == IStatus.ERROR) {
            MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.validationFailedTitle,
                    aggregatedStatus.getChildren()[0].getMessage());
            if (currentDiagramStore != null) {
                Display.getDefault()
                        .asyncExec(() -> PlatformUI.getWorkbench()
                                .getActiveWorkbenchWindow()
                                .getActivePage()
                                .closeEditor(currentDiagramStore.getOpenedEditor(), false));
            }
        } else if (shouldDisplayReportDialog(parameters)) {
            showReport(aggregatedStatus, checkAllModelVersion, validateModelCompatibility);
        }

        refreshViewerPropertyPart();

        return aggregatedStatus;
    }

    private boolean showMigrateButton(ModelFileCompatibilityValidator validateModelCompatibility) {
        return Stream.of(validateModelCompatibility.getStatus().getChildren())
                .anyMatch(s -> s.matches(IStatus.WARNING));
    }

    private boolean checkAllModelVersion(final Map<?, ?> parameters) {
        return parameters.containsKey(CHECK_ALL_MODEL_VERSION_PARAM)
                && Boolean.valueOf((String) parameters.get(CHECK_ALL_MODEL_VERSION_PARAM));
    }

    private boolean shouldDisplayReportDialog(final Map<?, ?> parameters) {
        Object showReport = parameters.get("showReport");
        if (showReport == null) {
            return true;
        }
        if (showReport instanceof Boolean) {
            return ((Boolean) showReport).booleanValue();
        }
        if (showReport instanceof String) {
            return Boolean.valueOf((String) showReport);
        }
        return false;
    }

    protected void refreshViewerPropertyPart() {
        if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
            final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            if (activePage != null) {
                final IViewPart part = activePage.findView("org.bonitasoft.studio.validation.view");
                if (part instanceof ValidationViewPart) {
                    ((ValidationViewPart) part).refreshViewer();
                }
            }
        }
    }

    private void addAllProjectDiagrams(final ProcessValidationOperation validateOperation,
            Repository currentRepository) {
        DiagramRepositoryStore diagramRepositoryStore = currentRepository
                .getRepositoryStore(DiagramRepositoryStore.class);
        diagramRepositoryStore.getChildren().stream()
                .forEach(fStore -> {
                    validateOperation.addProcesses(fStore.getProcesses());
                });
    }

    protected void computeDiagramsToValidate(final ExecutionEvent event,
            final ProcessValidationOperation validateOperation,
            ModelFileCompatibilityValidator validateModelCompatibility) {
        final String files = event.getParameter("diagrams");
        if (files != null) {
            try {
                addDiagramsToValidate(validateOperation, validateModelCompatibility, toFileNames(files));
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    protected void addDiagramsToValidate(final ProcessValidationOperation validateOperation,
            ModelFileCompatibilityValidator validateModelCompatibility,
            final String[] files)
            throws IOException {
        final DiagramRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        for (final String fName : files) {
            final String fileName = fName.trim();
            final DiagramFileStore fileStore = store.getChild(fileName, true);
            if (fileStore == null) {
                throw new IOException(fileName + " does not exists in " + store.getResource().getLocation());
            }
            currentDiagramStore = fileStore;
            validateOperation.addProcesses(fileStore.getProcesses());
            validateModelCompatibility.addFile(fileStore.getResource().getLocation().toFile());
        }
    }

    protected String[] toFileNames(String files) {
        if (files.trim().startsWith("[")) {
            files = files.trim().substring(1, files.length() - 1);
        }
        return files.trim().split(",");
    }

    private void showReport(MultiStatus aggregatedStatus, boolean checkAllModelVersion,
            ModelFileCompatibilityValidator validator) {
        if (checkAllModelVersion) {
            boolean showMigrateButton = showMigrateButton(validator);
            if (statusContainsError(aggregatedStatus) || showMigrateButton) {
                MultiStatusDialog multiStatusDialog = new MultiStatusDialog(Display.getDefault().getActiveShell(),
                        Messages.validationTitle,
                        statusContainsError(aggregatedStatus) ? Messages.validationErrorMessage
                                : Messages.validationWarningMessage,
                        showMigrateButton ? new String[] { org.bonitasoft.studio.common.repository.Messages.updateAllModels, IDialogConstants.CLOSE_LABEL }
                                : new String[] { IDialogConstants.CLOSE_LABEL },
                        aggregatedStatus);
                multiStatusDialog.setLevel(IStatus.WARNING);
                if (multiStatusDialog.open() == 0) {
                    IProgressService service = PlatformUI.getWorkbench().getProgressService();
                    try {
                        service.run(true, false, monitor -> {
                            try {
                                RepositoryManager.getInstance().getCurrentRepository().migrate(monitor);
                            } catch (CoreException | MigrationException e) {
                                throw new InvocationTargetException(e);
                            }
                            validator.run(monitor);
                        });
                    } catch (InvocationTargetException | InterruptedException e) {
                        BonitaStudioLog.error(e);
                        MessageDialog.openError(Display.getDefault().getActiveShell(),
                                org.bonitasoft.studio.common.repository.Messages.migrationError, e.getMessage());
                    }
                }
            } else {
                MessageDialog.openInformation(Display.getDefault().getActiveShell(),
                        Messages.validationTitle,
                        Messages.validationSuccessMsg);
            }
        } else if (statusContainsError(aggregatedStatus)) {
            final String errorMessage = Messages.validationErrorFoundMessage + " "
                    + currentDiagramStore.getDisplayName();
            final int result = new ValidationDialog(Display.getDefault().getActiveShell(),
                    Messages.validationFailedTitle, errorMessage,
                    ValidationDialog.OK_SEEDETAILS).open();

            if (result == ValidationDialog.SEE_DETAILS) {
                IWorkbenchPart openedEditor = currentDiagramStore.getOpenedEditor();
                if (openedEditor == null) {
                    openedEditor = currentDiagramStore.open();
                }
                final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                activePage.activate(openedEditor);
                Display.getDefault().asyncExec(() -> {
                    try {
                        activePage.showView("org.bonitasoft.studio.validation.view");
                    } catch (final PartInitException e) {
                        BonitaStudioLog.error(e);
                    }
                });
            }
        }
    }

    private boolean statusContainsError(final IStatus validationStatus) {
        if (validationStatus != null) {
            return Stream.of(validationStatus.getChildren()).anyMatch(s -> s.matches(IStatus.ERROR));
        }
        return false;
    }

    @Override
    public boolean isEnabled() {
        return PlatformUI.isWorkbenchRunning();
    }

}
