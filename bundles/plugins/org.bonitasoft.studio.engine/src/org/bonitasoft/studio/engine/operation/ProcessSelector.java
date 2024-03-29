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
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.process.CallActivity;
import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessPackage;
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
    private Optional<Pool> processFromEvent;

    public ProcessSelector(final ExecutionEvent event) {
        this.event = event;
    }

    public Pool getSelectedProcess() {
        Optional<Pool> processFromEvent = getProcessFromEvent();
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
                    return ModelHelper.getParentPool(((IGraphicalEditPart) selectedEp).resolveSemanticElement());
                }
            } else {
                final EObject element = ((DiagramEditor) editor).getDiagramEditPart().resolveSemanticElement();
                return ModelHelper.getParentPool(element);
            }
        }
        return null;
    }

    private Optional<Pool> getProcessFromEvent() {
        if (processFromEvent == null) {
            DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                    .getRepositoryStore(DiagramRepositoryStore.class);
            List<Pool> allProcesses = diagramStore.hasComputedProcesses()
                    ? diagramStore.getComputedProcesses()
                    : diagramStore.getAllProcesses();
            processFromEvent = Optional.ofNullable(event)
                    .map(e -> e.getParameters().get(PROCESS))
                    .filter(Pool.class::isInstance)
                    .map(Pool.class::cast)
                    .map(p -> getProcessToRun(p, allProcesses));
        }
        return processFromEvent;

    }

    public Set<Pool> getExecutableProcesses() {
        final Set<Pool> result = new TreeSet<>((p1, p2) -> {
            final String s1 = p1.getName() + "--" + p1.getVersion();
            final String s2 = p2.getName() + "--" + p2.getVersion();
            return s1.compareTo(s2);
        });
        Optional<Pool> processFromEvent = getProcessFromEvent();
        DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        List<Pool> allProcesses = diagramStore.hasComputedProcesses() ? diagramStore.getComputedProcesses()
                : diagramStore.getAllProcesses();
        if (processFromEvent.isPresent()) {
        	Pool selectedProcess = processFromEvent.get();
            final Set<Pool> calledProcesses = new HashSet<>();
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
                    result.add((Pool) p);
                    final Set<Pool> calledProcesses = new HashSet<>();
                    findCalledProcesses((Pool) p, allProcesses, calledProcesses);
                    if (!calledProcesses.isEmpty()) {
                        result.addAll(calledProcesses);
                    }
                }
            } else if (processInEditor instanceof Pool) {
                final Set<Pool> calledProcesses = new HashSet<>();
                findCalledProcesses((Pool) processInEditor, allProcesses, calledProcesses);
                if (!calledProcesses.isEmpty()) {
                    result.addAll(calledProcesses);
                }
                result.add((Pool) processInEditor);
            }
        }
        return result;
    }

    private Pool getProcessToRun(Pool processFromEvent, List<Pool> allProcesses) {
        if (processFromEvent.eIsProxy()) {
            return allProcesses.stream()
                    .filter(p -> Objects.equals(p.getName(), processFromEvent.getName())
                            && Objects.equals(p.getVersion(), processFromEvent.getVersion()))
                    .findFirst()
                    .orElseThrow();
        }
        return processFromEvent;
    }

    private void findCalledProcesses(final Pool process, List<Pool> allProcesses,
            final Set<Pool> result) {
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
                final Pool subProcess = ModelHelper.findProcess(calledName.getContent(), version,
                        allProcesses);
                if (subProcess != null && !containsSubProcess(subProcess, result)) {
                    result.add(subProcess);
                    findCalledProcesses(subProcess, allProcesses, result);
                }
            }
        }
    }

    private boolean containsSubProcess(final Pool subProcess, final Set<Pool> result) {
        final String currentSubProcessId = subProcess.getName() + "--" + subProcess.getVersion();
        for (final Pool process : result) {
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

    public static ExecutionEvent createExecutionEvent(final Pool processToRun) {
        final Map<String, Object> param = new HashMap<>();
        param.put(PROCESS, processToRun);
        return new ExecutionEvent(null, param, null, null);
    }
}
