/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.ui.action;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.migration.model.report.Change;
import org.bonitasoft.studio.migration.ui.view.PropertySelectionProvider;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jdt.internal.ui.actions.AbstractToggleLinkingAction;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class ToggleLinkingAction extends AbstractToggleLinkingAction {

    public static final String PROCESS_ID = "org.bonitasoft.studio.diagram"; //$NON-NLS-1$
    public static final String FORM_ID = "org.bonitasoft.studio.diagram.form"; //$NON-NLS-1$

    private DiagramEditor editor;
    private StructuredViewer viewer;
    private final ISelectionChangedListener listener = new ISelectionChangedListener() {

        @Override
        public void selectionChanged(final SelectionChangedEvent event) {
            Display.getDefault().asyncExec(new Runnable() {

                @Override
                public void run() {
                    if (editor != null && viewer != null && !viewer.getSelection().isEmpty()) {
                        final Change change = (Change) ((IStructuredSelection) viewer.getSelection()).getFirstElement();
                        final String uuid = change.getElementUUID();
                        if (uuid != null) {
                            EObject element = editor.getDiagram().eResource().getEObject(uuid);
                            if (PROCESS_ID.equals(editor.getContributorId())) {
                                while (element != null && !(element instanceof SequenceFlow || element instanceof Container || element instanceof FlowElement)) {
                                    element = element.eContainer();
                                }
                            } else {
                                return;
                            }

                            if (element != null) {
                                final EditPart ep = findEditPart(editor.getDiagramEditPart(), element);
                                if (ep != null) {
                                    final EditPartViewer viewerDiagram = editor.getDiagramEditPart().getViewer();
                                    viewerDiagram.select(ep);
                                    viewerDiagram.reveal(ep);
                                    PropertySelectionProvider.getInstance().fireSelectionChanged((IGraphicalEditPart) ep, null);
                                }
                            }
                        }
                    }
                }
            });
        }
    };

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.internal.ui.actions.AbstractToggleLinkingAction#run()
     */
    @Override
    public void run() {
        if (isChecked()) {
            activateLinking();
        } else {
            deactivateLinking();
        }
    }

    protected void deactivateLinking() {
        if (viewer != null) {
            viewer.removeSelectionChangedListener(listener);
        }
    }

    protected void activateLinking() {
        if (viewer != null) {
            viewer.addSelectionChangedListener(listener);
            listener.selectionChanged(null);
        }
    }

    public void setEditor(final DiagramEditor editor) {
        this.editor = editor;
    }

    public void setViewer(final StructuredViewer viewer) {
        this.viewer = viewer;
    }

    private static IGraphicalEditPart findEditPart(final EditPart containerEditPart, final EObject elementToFind) {

        final EObject containerElement = ((IGraphicalEditPart) containerEditPart).resolveSemanticElement();
        if (ModelHelper.getEObjectID(containerElement) != null && ModelHelper.getEObjectID(containerElement).equals(ModelHelper.getEObjectID(elementToFind))) {
            return (IGraphicalEditPart) containerEditPart;
        }

        for (final Object child : containerEditPart.getChildren()) {
            if (child instanceof IGraphicalEditPart) {
                final EObject childResolvedSemanticElement = ((IGraphicalEditPart) child).resolveSemanticElement();
                final String eObjectID = ModelHelper.getEObjectID(childResolvedSemanticElement);
                if (eObjectID != null && eObjectID.equals(ModelHelper.getEObjectID(elementToFind))) {
                    return (IGraphicalEditPart) child;
                } else {

                    if (!((IGraphicalEditPart) child).getTargetConnections().isEmpty() || !((IGraphicalEditPart) child).getSourceConnections().isEmpty()) {
                        for (final Object ep : ((IGraphicalEditPart) child).getTargetConnections()) {
                            final EObject resolveSemanticElement = ((IGraphicalEditPart) ep).resolveSemanticElement();
                            if (resolveSemanticElement != null) {
                                if (ModelHelper.getEObjectID(resolveSemanticElement).equals(ModelHelper.getEObjectID(elementToFind))) {
                                    return (IGraphicalEditPart) ep;
                                }
                            }
                        }

                        for (final Object ep : ((IGraphicalEditPart) child).getSourceConnections()) {
                            final EObject resolveSemanticElement = ((IGraphicalEditPart) ep).resolveSemanticElement();
                            if (resolveSemanticElement != null) {
                                if (ModelHelper.getEObjectID(resolveSemanticElement).equals(ModelHelper.getEObjectID(elementToFind))) {
                                    return (IGraphicalEditPart) ep;
                                }
                            }
                        }
                    }

                    final IGraphicalEditPart ep = findEditPart((EditPart) child, elementToFind);
                    if (ep != null) {
                        return ep;
                    }
                }
            }
        }
        return null;
    }

}
