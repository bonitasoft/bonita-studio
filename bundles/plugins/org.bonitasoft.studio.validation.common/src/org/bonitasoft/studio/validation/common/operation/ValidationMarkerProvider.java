/*
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.common.operation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.studio.common.Triple;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Some validation marker util generated from GMF
 *
 * @author Romain Bioteau
 */
public class ValidationMarkerProvider {

    private static final String CONSTRAINT_ID = "constraintId";

    public Diagnostic runEMFValidator(final View target) {
        if (target.isSetElement() && target.getElement() != null) {
            return new Diagnostician() {

                @Override
                public String getObjectLabel(final EObject eObject) {
                    return EMFCoreUtil.getQualifiedName(eObject, true);
                }
            }.validate(target.getElement());
        }
        return Diagnostic.OK_INSTANCE;
    }

    public void createMarkers(final IFile
            target, final IStatus validationStatus, final DiagramEditPart diagramEditPart) {
        if (validationStatus.isOK()) {
            return;
        }
        final IStatus rootStatus = validationStatus;
        final List allStatuses = new ArrayList();
        final ProcessDiagramEditorUtil.LazyElement2ViewMap element2ViewMap = new ProcessDiagramEditorUtil.LazyElement2ViewMap(
                diagramEditPart.getDiagramView(),
                collectTargetElements(rootStatus, new HashSet<EObject>(), allStatuses));
        final List<Triple<String, String, String>> createdMarkers = new ArrayList<Triple<String, String, String>>();
        for (final Iterator it = allStatuses.iterator(); it.hasNext();) {
            final IConstraintStatus nextStatus =
                    (IConstraintStatus) it.next();
            final String constraintId = nextStatus.getConstraint().getDescriptor().getId();
            final View view = ProcessDiagramEditorUtil.findView(
                    diagramEditPart, nextStatus.getTarget(), element2ViewMap);
            final Triple<String, String, String> triple = new Triple<String, String, String>(constraintId, nextStatus.getMessage(), view.eResource()
                    .getURIFragment(view));
            if (!createdMarkers.contains(triple)) {
                addMarker(diagramEditPart, constraintId, diagramEditPart.getViewer(), target, view.eResource().getURIFragment(view),
                        EMFCoreUtil.getQualifiedName(nextStatus.getTarget(), true),
                        nextStatus.getMessage(), nextStatus.getSeverity());
                createdMarkers.add(triple);
            }
        }
    }

    public void createMarkers(final IFile
            target, final Diagnostic emfValidationStatus, final DiagramEditPart diagramEditPart) {
        if (emfValidationStatus.getSeverity() == Diagnostic.OK) {
            return;
        }
        final Diagnostic rootStatus = emfValidationStatus;
        final List allDiagnostics = new ArrayList();
        final ProcessDiagramEditorUtil.LazyElement2ViewMap element2ViewMap =
                new ProcessDiagramEditorUtil.LazyElement2ViewMap(
                        diagramEditPart.getDiagramView(),
                        collectTargetElements(rootStatus, new HashSet<EObject>(), allDiagnostics));
        for (final Iterator it = emfValidationStatus.getChildren().iterator(); it.hasNext();) {
            final Diagnostic nextDiagnostic = (Diagnostic) it.next();
            final List data = nextDiagnostic.getData();
            if (data != null && !data.isEmpty() && data.get(0) instanceof EObject) {
                final EObject element = (EObject) data.get(0);
                final View view = ProcessDiagramEditorUtil.findView(
                        diagramEditPart, element, element2ViewMap);
                addMarker(diagramEditPart, null, diagramEditPart.getViewer(), target, view.eResource().getURIFragment(view),
                        EMFCoreUtil.getQualifiedName(element, true),
                        nextDiagnostic.getMessage(), diagnosticToStatusSeverity(nextDiagnostic.getSeverity()));
            }
        }
    }

    private static synchronized void addMarker(final DiagramEditPart diagramEP, final String constaintId, final EditPartViewer viewer, final IFile
            target, final String elementId, final String location, final String message, final int statusSeverity) {
        if (target == null) {
            return;
        }
        addProcessMarker(constaintId, viewer, target, elementId, location, message, statusSeverity);
    }

    private static void addProcessMarker(final String constraintId, final EditPartViewer viewer, final IFile
            target, final String elementId, final String location, final String message, final int statusSeverity) {
        if (target == null) {
            return;
        }

        final IMarker marker = org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider.addMarker(target, elementId, location,
                message, statusSeverity);
        addConstraintId(constraintId, marker);
    }

    private static void addConstraintId(final String constraintId, final IMarker marker) {
        try {
            marker.setAttribute(CONSTRAINT_ID, constraintId);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }


    private static int diagnosticToStatusSeverity(final int diagnosticSeverity) {
        if (diagnosticSeverity == Diagnostic.OK) {
            return IStatus.OK;
        } else if (diagnosticSeverity == Diagnostic.INFO) {
            return IStatus.INFO;
        } else if (diagnosticSeverity == Diagnostic.WARNING) {
            return IStatus.WARNING;
        } else if (diagnosticSeverity == Diagnostic.ERROR
                || diagnosticSeverity == Diagnostic.CANCEL) {
            return IStatus.ERROR;
        }
        return IStatus.INFO;
    }

    private static Set<EObject> collectTargetElements(final IStatus status,
            final Set<EObject> targetElementCollector, final List allConstraintStatuses) {
        if (status instanceof IConstraintStatus) {
            targetElementCollector.add(((IConstraintStatus) status).getTarget());
            allConstraintStatuses.add(status);
        }
        if (status.isMultiStatus()) {
            final IStatus[] children = status.getChildren();
            for (int i = 0; i < children.length; i++) {
                collectTargetElements(children[i], targetElementCollector, allConstraintStatuses);
            }
        }
        return targetElementCollector;
    }

    private static Set<EObject> collectTargetElements(final Diagnostic diagnostic,
            final Set<EObject> targetElementCollector, final List allDiagnostics) {
        final List data = diagnostic.getData();
        EObject target = null;
        if (data != null && !data.isEmpty() && data.get(0) instanceof EObject) {
            target = (EObject) data.get(0);
            targetElementCollector.add(target);
            allDiagnostics.add(diagnostic);
        }
        if (diagnostic.getChildren() != null && !diagnostic.getChildren().isEmpty()) {
            for (final Iterator it = diagnostic.getChildren().iterator(); it.hasNext();) {
                collectTargetElements((Diagnostic) it.next(),
                        targetElementCollector, allDiagnostics);
            }
        }
        return targetElementCollector;
    }

    public void clearMarkers(final Map<Diagram, DiagramEditPart> diagramsToDiagramEditPart) {
        final Iterator<Entry<Diagram, DiagramEditPart>> iterator = diagramsToDiagramEditPart.entrySet().iterator();
        while (iterator.hasNext()) {
            final Entry<Diagram, DiagramEditPart> entry = iterator.next();
            final Diagram d = entry.getKey();
            final DiagramEditPart de = entry.getValue();
            if (de != null) {
                final EObject resolvedSemanticElement = de.resolveSemanticElement();
                if (resolvedSemanticElement instanceof MainProcess) {
                    final IFile target = d.eResource() != null ? WorkspaceSynchronizer.getFile(d.eResource()) : null;
                    if (target != null) {
                        org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider.deleteMarkers(target);
                        break;
                    }
                }
            }
        }
    }

}
