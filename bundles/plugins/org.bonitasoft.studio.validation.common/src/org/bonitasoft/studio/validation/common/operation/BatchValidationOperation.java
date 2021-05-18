/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.common.operation;

import static org.bonitasoft.studio.model.process.diagram.providers.ProcessValidationProvider.runWithConstraints;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.common.ValidationCommonPlugin;
import org.bonitasoft.studio.validation.common.i18n.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * @author Romain Bioteau
 */
public class BatchValidationOperation extends WorkspaceModifyOperation {

    private final Map<Diagram, DiagramEditPart> diagramsToDiagramEditPart = new HashMap<>();
    private final List<IFile> fileProcessed = new ArrayList<>(); //Avoid duplicate
    private final OffscreenEditPartFactory offscreenEditPartFactory;
    private final ValidationMarkerProvider validationMarkerProvider;
    private IStatus status;

    public BatchValidationOperation(final OffscreenEditPartFactory offscreenEditPartFactory,
            final ValidationMarkerProvider validationMarkerProvider) {
        this.offscreenEditPartFactory = offscreenEditPartFactory;
        this.validationMarkerProvider = validationMarkerProvider;
    }

    @Override
    protected void execute(final IProgressMonitor monitor)
            throws CoreException, InvocationTargetException, InterruptedException {
        try {
            if(diagramsToDiagramEditPart.isEmpty()) {
                return;
            }
            buildEditPart();
            validationMarkerProvider.clearMarkers(diagramsToDiagramEditPart);
            for (final Entry<Diagram, DiagramEditPart> entry : diagramsToDiagramEditPart.entrySet()) {
                final DiagramEditPart diagramEp = entry.getValue();
                final Diagram diagram = entry.getKey();
                if (diagramEp != null && !monitor.isCanceled()) {
                    monitor.setTaskName(NLS.bind(
                            Messages.validatingProcess, ((MainProcess) diagramEp.resolveSemanticElement()).getName(),
                            ((MainProcess) diagramEp.resolveSemanticElement()).getVersion()));
                    final TransactionalEditingDomain txDomain = TransactionUtil.getEditingDomain(diagram);
                    runWithConstraints(txDomain, () -> validate(diagramEp, diagram, monitor));
                    monitor.worked(1);
                }
            }
            status = createStatus();
        }finally {
            offscreenEditPartFactory.dispose();
            diagramsToDiagramEditPart.clear();
        }
    }

    private void buildEditPart() {
        for (final Diagram diagram : diagramsToDiagramEditPart.keySet()) {
            diagramsToDiagramEditPart.put(diagram, getDiagramEditPart(diagram));
        }

    }

    protected void validate(final DiagramEditPart diagramEditPart, final View view, final IProgressMonitor monitor) {
        final IFile target = view.eResource() != null ? WorkspaceSynchronizer.getFile(view.eResource()) : null;
        final Diagnostic diagnostic = validationMarkerProvider.runEMFValidator(view);
        validationMarkerProvider.createMarkers(target, diagnostic, diagramEditPart);
        final IBatchValidator validator = (IBatchValidator) ModelValidationService.getInstance().newValidator(
                EvaluationMode.BATCH);
        validator.setIncludeLiveConstraints(true);
        if (view.isSetElement() && view.getElement() != null && view.getElement().eResource() != null) {
            validationMarkerProvider.createMarkers(target, validator.validate(view.getElement(), monitor), diagramEditPart);
        }
    }

    private DiagramEditPart getDiagramEditPart(final Diagram d) {
        DiagramEditPart res = retrieveEditPartFromOpenedEditors(d);
        if (res == null) {
            res = createDiagramEditPart(d);
        }
        return res;
    }

    protected DiagramEditPart retrieveEditPartFromOpenedEditors(final Diagram d) {
        final IWorkbenchPage activePage = getActivePage();
        if (activePage != null) {
            for (final IEditorReference ep : activePage.getEditorReferences()) {
                final IEditorPart editor = ep.getEditor(false);
                if (editor instanceof DiagramEditor
                        && ((DiagramEditor) editor).getDiagram().equals(d)
                        && ((DiagramEditor) editor).getDiagramEditPart() != null) {
                        return ((DiagramEditor) editor).getDiagramEditPart();
                    }
            }
        }
        return null;
    }

    protected DiagramEditPart createDiagramEditPart(final Diagram d) {
        if (d != null && d.eResource() != null) {
            DiagramEditPart offscreenDiagramEditPart = null;
            try {
                offscreenDiagramEditPart = offscreenEditPartFactory.createOffscreenDiagramEditPart(d);
            } catch (final Exception e) {
                //Need to call twice because sometimes for unknown reasons, the first time is not working
                offscreenDiagramEditPart = offscreenEditPartFactory.createOffscreenDiagramEditPart(d);
            }
            return offscreenDiagramEditPart;
        }
        return null;
    }

    protected IWorkbenchPage getActivePage() {
        if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
            return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        }
        return null;
    }

    public IStatus getResult() {
        return status;
    }

    private IStatus createStatus() {
        final MultiStatus result = new MultiStatus(ValidationCommonPlugin.PLUGIN_ID, IStatus.OK, "", null);
        fileProcessed.clear();
        for (final Diagram d : diagramsToDiagramEditPart.keySet()) {
            final EObject element = d.getElement();
            if (element != null) {
                final IFile target = d.eResource() != null ? WorkspaceSynchronizer.getFile(d.eResource()) : null;
                if (target != null && !fileProcessed.contains(target)) {
                    try {
                        buildMultiStatus(target, result);
                    } catch (final CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }
        return result;
    }
    
    private void buildMultiStatus(final IFile target, final MultiStatus result) throws CoreException {
        String fileName = target.getName();
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        addStatusForMarkerType(target, result, fileName, ProcessMarkerNavigationProvider.MARKER_TYPE);
        fileProcessed.add(target);
    }

    private void addStatusForMarkerType(final IFile target, final MultiStatus result, final String fileName,
            final String markerType) throws CoreException {
        for (final IMarker m : target.findMarkers(markerType, true, IResource.DEPTH_ZERO)) {
            final int severity = (Integer) m.getAttribute(IMarker.SEVERITY);
            if (severity == IMarker.SEVERITY_WARNING || severity == IMarker.SEVERITY_INFO
                    || severity == IMarker.SEVERITY_ERROR) {
                final String fullMessage = computeMarkerMessage(fileName, m);
                if (!statusExists(result, fullMessage)) {
                    result.add(new Status(toStatusSeverity(severity), ValidationCommonPlugin.PLUGIN_ID, fullMessage));
                }
            }
        }
    }

    private String computeMarkerMessage(final String fileName, final IMarker m) throws CoreException {
        final String location = (String) m.getAttribute(IMarker.LOCATION);
        final String message = (String) m.getAttribute(IMarker.MESSAGE);
        return fileName + ":" + location + " : " + message;
    }

    private int toStatusSeverity(final int markerSeverity) {
        switch (markerSeverity) {
            case IMarker.SEVERITY_INFO:
                return IStatus.INFO;
            case IMarker.SEVERITY_WARNING:
                return IStatus.WARNING;
            case IMarker.SEVERITY_ERROR:
                return IStatus.ERROR;
            default:
                return IStatus.INFO;
        }
    }

    private boolean statusExists(final MultiStatus multi, final String message) {
        for (final IStatus s : multi.getChildren()) {
            if (s.getMessage().equals(message)) {
                return true;
            }
        }
        return false;
    }

    public void addDiagram(final Diagram diagramToValidate) {
        diagramsToDiagramEditPart.put(diagramToValidate, null);
    }

}
