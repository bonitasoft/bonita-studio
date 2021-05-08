/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.ui.view;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.validation.ModelFileCompatibilityValidator;
import org.bonitasoft.studio.validation.common.operation.BatchValidationOperation;
import org.bonitasoft.studio.validation.common.operation.BatchValidatorFactory;
import org.bonitasoft.studio.validation.common.operation.OffscreenEditPartFactory;
import org.bonitasoft.studio.validation.common.operation.ValidationMarkerProvider;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Florine Boudin
 */
public class ValidationViewAction extends Action {

    private TableViewer tableViewer;
    private IWorkbenchPage activePage;
    private BatchValidatorFactory batchValidatorFactory = new BatchValidatorFactory();

    public void setTableViewer(final TableViewer tableViewer) {
        this.tableViewer = tableViewer;
    }

    public void setActivePage(final IWorkbenchPage activePage) {
        setText(Messages.validationViewValidateButtonLabel);
        this.activePage = activePage;
    }

    public ValidationViewAction() {
        super();
    }

    @Override
    public void run() {
        DiagramRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        try {
            PlatformUI.getWorkbench().getProgressService().run(true, false, store::computeProcesses);
        } catch (InvocationTargetException | InterruptedException e) {
           BonitaStudioLog.error(e);
        }
        final BatchValidationOperation validateOperation = new BatchValidationOperation(new OffscreenEditPartFactory(
                org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory.getInstance()),
                new ValidationMarkerProvider(),
                batchValidatorFactory.create());
        final IEditorPart ieditor = activePage.getActiveEditor();
        if (ieditor instanceof DiagramEditor) {
            final Resource resource = ((DiagramEditor) ieditor).getDiagramEditPart().resolveSemanticElement()
                    .eResource();
            for (final EObject content : resource.getContents()) {
                if (content instanceof Diagram) {
                    validateOperation.addDiagram((Diagram) content);
                }
            }
            String filename = URI.decode(resource.getURI().lastSegment());
            try {
                ModelFileCompatibilityValidator modelValidator = new ModelFileCompatibilityValidator(
                        RepositoryManager.getInstance().getCurrentRepository())
                                .addResourceMarkers()
                                .addFile(store.getChild(filename, false).getResource().getLocation().toFile());
                modelValidator.run(AbstractRepository.NULL_PROGRESS_MONITOR);
                IStatus status = modelValidator.getStatus();
                if (status.getSeverity() == IStatus.ERROR) {
                    store.resetComputedProcesses();
                    MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.validationFailedTitle,
                            status.getChildren()[0].getMessage());
                    Display.getDefault().asyncExec(() -> activePage.closeEditor(ieditor, false));
                    return;
                }
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }

        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        try {
            service.run(true, true, validateOperation);
        } catch (final InvocationTargetException e) {
            BonitaStudioLog.error(e);
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
        }
        store.resetComputedProcesses();
        if (tableViewer != null) {
            tableViewer.setInput(ieditor);
        }
    }

}
