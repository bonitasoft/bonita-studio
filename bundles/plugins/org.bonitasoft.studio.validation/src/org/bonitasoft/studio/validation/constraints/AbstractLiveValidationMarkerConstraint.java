/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorUtil;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.ValidationPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractLiveValidationMarkerConstraint extends AbstractModelConstraint {

    private static final String CONSTRAINT_ID = "constraintId";

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    public final IStatus validate(final IValidationContext ctx) {
        IStatus status = null;
        final EMFEventType eType = ctx.getEventType();
        final EStructuralFeature featureTriggered = ctx.getFeature();
        if (featureTriggered != null && featureTriggered.equals(NotationPackage.Literals.VIEW__ELEMENT)) {
            final EObject eobject = (EObject) ctx.getFeatureNewValue();
            if (eobject != null) {
                final MainProcess mainProc = ModelHelper.getMainProcess(eobject);
                if (mainProc != null && !mainProc.isEnableValidation()) {
                    return Status.OK_STATUS;
                }
            }
        }
        final EObject target = ctx.getTarget();
        if (isAExpressionReferencedElement(target)) {
            return ctx.createSuccessStatus();
        }
        if (eType != EMFEventType.NULL) { //LIVE
            status = performLiveValidation(ctx);
            updateValidationMarkersOnDiagram(status, ctx);
        } else { //Batch
            status = performBatchValidation(ctx);
        }

        return status;
    }

    protected boolean isAExpressionReferencedElement(final EObject target) {
        if (target != null) {
            EObject current = target;
            EReference ref = current.eContainmentFeature();
            while (ref != null && !ref.equals(ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS)) {
                current = current.eContainer();
                ref = current.eContainmentFeature();
            }
            return ref != null;
        }
        return false;
    }

    private void updateValidationMarkersOnDiagram(final IStatus status, final IValidationContext context) {
        if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow() != null && PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage() != null) {
            final IEditorPart editorPart = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow().getActivePage()
                    .getActiveEditor();

            if (editorPart instanceof DiagramEditor) {
                EObject validatedObject = context.getTarget();
                if (status instanceof IConstraintStatus) {
                    validatedObject = ((IConstraintStatus) status).getTarget();
                }
                if (validatedObject == null) {
                    return;
                }
                View view = null;
                if (validatedObject instanceof View) {
                    view = (View) validatedObject;
                } else {
                    view = getViewFor((DiagramEditor) editorPart, validatedObject);
                }
                if (view == null) {
                    return;
                }
                final String viewId = ViewUtil.getIdStr(view);

                final DiagramEditPart diagramEditPart = ((DiagramEditor) editorPart).getDiagramEditPart();
                if (diagramEditPart == null) {
                    BonitaStudioLog.error("DiagramEditPart is null. Ignoring validation marker update.", ValidationPlugin.PLUGIN_ID);
                    return;
                }
                final IFile target = diagramEditPart.getDiagramView().eResource() != null ? WorkspaceSynchronizer.getFile(diagramEditPart.getDiagramView()
                        .eResource()) : null;
                if (target != null) {
                    try {
                        for (final IMarker marker : target.findMarkers(getMarkerType((DiagramEditor) editorPart), false, IResource.DEPTH_ZERO)) {
                            final String elementId = (String) marker.getAttribute(org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID);
                            final String constraintId = (String) marker.getAttribute(CONSTRAINT_ID);
                            if (elementId != null && elementId.equals(viewId) && getConstraintId().equals(constraintId)) {
                                marker.delete();
                            }
                        }
                    } catch (final CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                }

                // create problem markers on the appropriate resources
                if (status != null && !status.isOK()) {
                    createMarkers(target, status, diagramEditPart, (DiagramEditor) editorPart);
                }
            }
        }
    }

    protected IStatus performLiveValidation(final IValidationContext context) {
        return context.createSuccessStatus();
    }

    protected abstract IStatus performBatchValidation(IValidationContext context);

    protected String getMarkerType(final DiagramEditor editor) {
        if (editor instanceof ProcessDiagramEditor) {
            return ProcessMarkerNavigationProvider.MARKER_TYPE;
        }
        return null;
    }

    protected abstract String getConstraintId();

    private View getViewFor(final DiagramEditor editor, EObject validatedObject) {
        if (editor instanceof ProcessDiagramEditor) {
            if (!(validatedObject instanceof FlowElement
                    || validatedObject instanceof BoundaryEvent
                    || validatedObject instanceof Container
                    || validatedObject instanceof Connection)) {
                validatedObject = ModelHelper.getParentFlowElement(validatedObject);
            }
        }
        for (final Object ep : editor.getDiagramGraphicalViewer().getEditPartRegistry().values()) {
            if (!(ep instanceof ITextAwareEditPart) && !(ep instanceof ShapeCompartmentEditPart) && ep instanceof IGraphicalEditPart
                    && ((IGraphicalEditPart) ep).resolveSemanticElement() != null && ((IGraphicalEditPart) ep).resolveSemanticElement().equals(validatedObject)) {
                return ((IGraphicalEditPart) ep).getNotationView();
            }
        }
        return null;
    }

    private void createMarkers(final IFile target, final IStatus validationStatus,
            final DiagramEditPart diagramEditPart, final DiagramEditor editor) {
        if (validationStatus.isOK()) {
            return;
        }
        final IStatus rootStatus = validationStatus;
        final List allStatuses = new ArrayList();
        final ProcessDiagramEditorUtil.LazyElement2ViewMap element2ViewMap = new ProcessDiagramEditorUtil.LazyElement2ViewMap(
                diagramEditPart.getDiagramView(), collectTargetElements(
                        rootStatus, new HashSet<EObject>(), allStatuses));
        for (final Iterator it = allStatuses.iterator(); it.hasNext();) {
            final IConstraintStatus nextStatus = (IConstraintStatus) it.next();
            final EObject targetEObject = nextStatus.getTarget();
            View view = null;
            if (targetEObject instanceof View) {
                view = (View) targetEObject;
            } else {
                view = ProcessDiagramEditorUtil.findView(diagramEditPart,
                        targetEObject, element2ViewMap);
            }
            addMarker(editor, diagramEditPart.getViewer(), target, ViewUtil.getIdStr(view), EMFCoreUtil.getQualifiedName(
                    nextStatus.getTarget(), true), nextStatus.getMessage(),
                    nextStatus.getSeverity());
        }
    }

    private static Set<EObject> collectTargetElements(final IStatus status,
            final Set<EObject> targetElementCollector, final List allConstraintStatuses) {
        if (status instanceof IConstraintStatus) {
            targetElementCollector
                    .add(((IConstraintStatus) status).getTarget());
            allConstraintStatuses.add(status);
        }
        if (status.isMultiStatus()) {
            final IStatus[] children = status.getChildren();
            for (int i = 0; i < children.length; i++) {
                collectTargetElements(children[i], targetElementCollector,
                        allConstraintStatuses);
            }
        }
        return targetElementCollector;
    }

    private void addMarker(final DiagramEditor editor, final EditPartViewer viewer, final IFile target,
            final String elementId, final String location, final String message,
            final int statusSeverity) {
        if (target == null) {
            return;
        }
        IMarker marker = null;
        try {
            marker = target.createMarker(getMarkerType(editor));
            marker.setAttribute(IMarker.MESSAGE, message);
            marker.setAttribute(IMarker.LOCATION, location);
            marker.setAttribute(
                    org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID,
                    elementId);
            marker.setAttribute(CONSTRAINT_ID, getConstraintId());
            int markerSeverity = IMarker.SEVERITY_INFO;
            if (statusSeverity == IStatus.WARNING) {
                markerSeverity = IMarker.SEVERITY_WARNING;
            } else if (statusSeverity == IStatus.ERROR
                    || statusSeverity == IStatus.CANCEL) {
                markerSeverity = IMarker.SEVERITY_ERROR;
            }
            marker.setAttribute(IMarker.SEVERITY, markerSeverity);
        } catch (final CoreException e) {
            ProcessDiagramEditorPlugin.getInstance().logError("Failed to create validation marker", e); //$NON-NLS-1$
        }
    }

}
