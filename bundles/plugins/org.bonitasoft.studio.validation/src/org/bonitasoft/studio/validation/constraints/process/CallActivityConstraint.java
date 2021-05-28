/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation.constraints.process;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.properties.sections.callActivity.CallActivityHelper;
import org.bonitasoft.studio.properties.sections.callActivity.CallActivitySelectionProvider;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;

/**
 * @author Baptiste Mesta
 */
public class CallActivityConstraint extends AbstractLiveValidationMarkerConstraint {

    private RepositoryAccessor repositoryAccessor = RepositoryManager.getInstance().getAccessor();

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final CallActivity callActivity = (CallActivity) ctx.getTarget();
        CallActivityHelper callActivityHelper = createHelper(callActivity);
        final Expression subprocessName = callActivity.getCalledActivityName();
        if (subprocessName == null || subprocessName.getContent() == null || subprocessName.getContent().isEmpty()) {
            return ctx.createFailureStatus(NLS.bind(Messages.Validation_NoSubProcess, callActivity.getName()));
        }
        final AbstractProcess subProc = callActivityHelper.getCalledProcess();
        if (subProc == null) {
            return ctx.createSuccessStatus();
        }

        final List<Data> data = callActivityHelper.getCallActivityData();
        for (final OutputMapping out : callActivity.getOutputMappings()) {
            if (out.getProcessTarget() == null) {
                return ctx
                        .createFailureStatus(NLS.bind(Messages.Validation_Subprocess_OutputMapping_SourceData_Not_Found,
                                callActivity.getName()));
            }
            if (data.stream()
                    .map(Data::getName)
                    .noneMatch(dataName -> Objects.equals(out.getSubprocessSource(), dataName))) {
                return ctx
                        .createFailureStatus(NLS.bind(Messages.Validation_Subprocess_OutputMapping_SourceData_Not_Found,
                                callActivity.getName()));
            }
        }
        for (final InputMapping in : callActivity.getInputMappings()) {
            if (in.getProcessSource() == null) {
                return ctx
                        .createFailureStatus(NLS.bind(Messages.Validation_Subprocess_InputMapping_TargetData_Not_Found,
                                callActivity.getName()));
            }
            if (InputMappingAssignationType.DATA == in.getAssignationType() && !exist(in.getSubprocessTarget(), data)) {
                return ctx
                        .createFailureStatus(NLS.bind(Messages.Validation_Subprocess_InputMapping_TargetData_Not_Found,
                                callActivity.getName()));
            }
        }

        return ctx.createSuccessStatus();
    }

    CallActivityHelper createHelper(final CallActivity callActivity) {
        CallActivitySelectionProvider callActivitySelectionProvider = new CallActivitySelectionProvider();
        callActivitySelectionProvider.setSelection(new StructuredSelection(new IAdaptable() {
            
            @Override
            public <T> T getAdapter(Class<T> adapter) {
                if(EObject.class.equals(adapter)) {
                    return (T) callActivity;
                }
                return null;
            }
        }));
        
        DiagramRepositoryStore diagramRepoStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        List<AbstractProcess> allProcesses = diagramRepoStore.hasComputedProcesses() ? diagramRepoStore.getComputedProcesses() : diagramRepoStore.getAllProcessesWithoutReoload();
        return new CallActivityHelper(allProcesses, callActivitySelectionProvider);
    }

    private boolean exist(final String subprocessTarget, final List<Data> data) {
        for (final Data d : data) {
            if (subprocessTarget != null && subprocessTarget.equals(d.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.CallActivity";
    }

}
