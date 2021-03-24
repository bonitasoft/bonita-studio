/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.operation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class ProcessSelector {

    public static final String PROCESS = "process";
    private final ExecutionEvent event;

    public ProcessSelector(final ExecutionEvent event) {
        this.event = event;
    }

    public AbstractProcess getSelectedProcess() {
        Optional<AbstractProcess> processFromEvent = getProcessFromEvent();
        if (processFromEvent.isPresent()) {
            return processFromEvent.get();
        }
        final IEditorPart editor = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor();
        final boolean isADiagram = editor instanceof DiagramEditor;
        if (isADiagram) {
            final List<?> selectedEditParts = ((DiagramEditor) editor).getDiagramGraphicalViewer()
                    .getSelectedEditParts();
            if (selectedEditParts != null && !selectedEditParts.isEmpty()) {
                final Object selectedEp = selectedEditParts.iterator().next();
                if (selectedEp != null) {
                    return ModelHelper.getParentProcess(((IGraphicalEditPart) selectedEp).resolveSemanticElement());
                }
            } else {
                final EObject element = ((DiagramEditor) editor).getDiagramEditPart().resolveSemanticElement();
                return ModelHelper.getParentProcess(element);
            }
        }
        return null;
    }

    private Optional<AbstractProcess> getProcessFromEvent() {
        return Optional.ofNullable(event)
                .map(e -> e.getParameters().get(PROCESS))
                .filter(AbstractProcess.class::isInstance)
                .map(AbstractProcess.class::cast);
    }

    public Set<AbstractProcess> getExecutableProcesses() {
        final Set<AbstractProcess> result = new TreeSet<>((p1, p2) -> {
            final String s1 = p1.getName() + "--" + p1.getVersion();
            final String s2 = p2.getName() + "--" + p2.getVersion();
            return s1.compareTo(s2);
        });
        Optional<AbstractProcess> processFromEvent = getProcessFromEvent();
        DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        List<AbstractProcess> allProcesses = diagramStore.hasComputedProcesses() ? diagramStore.getComputedProcesses() : diagramStore.getAllProcesses();
        if (processFromEvent.isPresent()) {
            AbstractProcess selectedProcess = processFromEvent.get();
            final Set<AbstractProcess> calledProcesses = new HashSet<>();
            findCalledProcesses(selectedProcess, allProcesses, calledProcesses);
            if (!calledProcesses.isEmpty()) {
                result.addAll(calledProcesses);
            }
            if (selectedProcess != null) {
                result.add(selectedProcess);
            }
        } else {
            final MainProcess processInEditor = getProcessInEditor();
            if (processInEditor instanceof MainProcess) {
                for (final EObject p : ModelHelper.getAllItemsOfType(processInEditor, ProcessPackage.Literals.POOL)) {
                    result.add((AbstractProcess) p);
                    final Set<AbstractProcess> calledProcesses = new HashSet<>();
                    findCalledProcesses((AbstractProcess) p, allProcesses, calledProcesses);
                    if (!calledProcesses.isEmpty()) {
                        result.addAll(calledProcesses);
                    }
                }
            } else if (processInEditor != null) {
                final Set<AbstractProcess> calledProcesses = new HashSet<>();
                findCalledProcesses(processInEditor, allProcesses, calledProcesses);
                if (!calledProcesses.isEmpty()) {
                    result.addAll(calledProcesses);
                }
                result.add(processInEditor);
            }
        }
        return result;
    }

    private void findCalledProcesses(final AbstractProcess process, List<AbstractProcess> allProcesses,
            final Set<AbstractProcess> result) {
        final List<CallActivity> callActivities = ModelHelper.getAllItemsOfType(process,
                ProcessPackage.Literals.CALL_ACTIVITY);
        for (final CallActivity callActivity : callActivities) {
            final Expression calledName = callActivity.getCalledActivityName();
            if (calledName != null && calledName.getContent() != null && !calledName.getContent().isEmpty()) {
                final Expression calledVersion = callActivity.getCalledActivityVersion();
                String version = null;
                if (calledVersion != null && calledVersion.getContent() != null
                        && !calledVersion.getContent().isEmpty()) {
                    version = calledVersion.getContent();
                }
                final AbstractProcess subProcess = ModelHelper.findProcess(calledName.getContent(), version,
                        allProcesses);
                if (subProcess != null && !containsSubProcess(subProcess, result)) {
                    result.add(subProcess);
                    findCalledProcesses(subProcess, allProcesses, result);
                }
            }
        }
    }

    private boolean containsSubProcess(final AbstractProcess subProcess, final Set<AbstractProcess> result) {
        final String currentSubProcessId = subProcess.getName() + "--" + subProcess.getVersion();
        for (final AbstractProcess process : result) {
            final String id = process.getName() + "--" + process.getVersion();
            if (id.equals(currentSubProcessId)) {
                return true;
            }
        }
        return false;
    }

    public static MainProcess getProcessInEditor() {
        final IEditorPart editor = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor();
        if (editor instanceof DiagramEditor) {
            final EObject root = ((DiagramEditor) editor).getDiagramEditPart().resolveSemanticElement();
            if (root.eResource() == null) {
                return null;
            }
            return ModelHelper.getMainProcess(root);
        }
        return null;
    }

    public static ExecutionEvent createExecutionEvent(final AbstractProcess processToRun) {
        final Map<String, Object> param = new HashMap<>();
        param.put(PROCESS, processToRun);
        return new ExecutionEvent(null, param, null, null);
    }
}
