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
package org.bonitasoft.studio.validation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.bonitasoft.studio.common.jface.ValidationDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.validation.common.operation.BatchValidationOperation;
import org.bonitasoft.studio.validation.common.operation.FindDiagramRunnable;
import org.bonitasoft.studio.validation.common.operation.ValidationMarkerProvider;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.bonitasoft.studio.validation.ui.view.ValidationViewPart;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
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
        final BatchValidationOperation validateOperation = new BatchValidationOperation(
                new org.bonitasoft.studio.validation.common.operation.OffscreenEditPartFactory(OffscreenEditPartFactory.getInstance()),
                new ValidationMarkerProvider());
        if (parameters != null && !parameters.isEmpty()) {
            computeDiagramsToValidate(event, validateOperation);
        } else if (currentEditorIsADiagram()) {
            computeDiagramToValidate(validateOperation);
        }

        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        try {
            service.run(true, true, validateOperation);
        } catch (final InvocationTargetException e) {
            throw new ExecutionException("Error during Validation", e);
        } catch (final InterruptedException e) {
            //Validation cancelled
        }

        Object showReport = parameters.get("showReport");
        if (showReport == null) {
            showReport = Boolean.TRUE;
        }
        if (showReport instanceof Boolean) {
            if (((Boolean) showReport).booleanValue()) {
                showReport(validateOperation);
            }
        }

        refreshViewerPropertyPart();

        return validateOperation.getResult();
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

    protected void computeDiagramToValidate(final BatchValidationOperation validateOperation) {
        final DiagramEditor part = (DiagramEditor) getActiveEditor();
        final IFile resource = (IFile) part.getEditorInput().getAdapter(IFile.class);
        if (resource != null) {
            try {
                addDiagramsToValidate(validateOperation, new String[] { resource.getLocation().toFile().getName() });
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    protected void computeDiagramsToValidate(final ExecutionEvent event, final BatchValidationOperation validateOperation) {
        final String files = event.getParameter("diagrams");
        if (files != null) {
            try {
                addDiagramsToValidate(validateOperation, toFileNames(files));
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    protected IEditorPart getActiveEditor() {
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
            return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        }
        return null;
    }

    protected boolean currentEditorIsADiagram() {
        return getActiveEditor() instanceof DiagramEditor;
    }

    protected void addDiagramsToValidate(final BatchValidationOperation validateOperation, final String[] files) throws IOException {
        final DiagramRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        for (final String fName : files) {
            final String fileName = fName.trim();
            final DiagramFileStore fileStore = store.getChild(fileName);
            if (fileStore == null) {
                throw new IOException(fileName + " does not exists in " + store.getResource().getLocation());
            }
            currentDiagramStore = fileStore;
            final Resource eResource = fileStore.getEMFResource();
            final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(eResource);
            final FindDiagramRunnable runnable = new FindDiagramRunnable(eResource, validateOperation);
            if (editingDomain != null) {
                try {
                    editingDomain.runExclusive(runnable);
                } catch (final InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
            } else {
                runnable.run();
            }
        }
    }

    protected String[] toFileNames(String files) {
        if (files.trim().startsWith("[")) {
            files = files.trim().substring(1, files.length() - 1);
        }
        return files.trim().split(",");
    }

    private void showReport(final BatchValidationOperation validateOperation) {
        if (statusContainsError(validateOperation.getResult())) {
            final String errorMessage = Messages.validationErrorFoundMessage + " "
                    + currentDiagramStore.getDisplayName();
            final int result = new ValidationDialog(Display.getDefault().getActiveShell(), Messages.validationFailedTitle, errorMessage,
                    ValidationDialog.OK_SEEDETAILS).open();

            if (result == ValidationDialog.SEE_DETAILS) {
                IWorkbenchPart openedEditor = currentDiagramStore.getOpenedEditor();
                if (openedEditor == null) {
                    openedEditor = currentDiagramStore.open();
                }
                final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                activePage.activate(openedEditor);

                Display.getDefault().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            activePage.showView("org.bonitasoft.studio.validation.view");
                        } catch (final PartInitException e) {
                            BonitaStudioLog.error(e);
                        }
                    }
                });
            }
        }

    }

    private boolean statusContainsError(final IStatus validationStatus) {
        if (validationStatus != null) {
            for (final IStatus s : validationStatus.getChildren()) {
                if (s.getSeverity() == IStatus.ERROR) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isEnabled() {
        return PlatformUI.isWorkbenchRunning();
    }

}
