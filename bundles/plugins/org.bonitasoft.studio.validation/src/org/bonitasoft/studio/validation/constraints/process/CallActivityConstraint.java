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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Baptiste Mesta
 */
public class CallActivityConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final CallActivity callActivity = (CallActivity) ctx.getTarget();
        final Expression subprocessName = callActivity.getCalledActivityName();
        if (subprocessName == null || subprocessName.getContent() == null || subprocessName.getContent().isEmpty()) {
            return ctx.createFailureStatus(new Object[] { Messages.bind(Messages.Validation_NoSubProcess, callActivity.getName()) });
        }
        final Expression subprocessVersion = callActivity.getCalledActivityVersion();
        final AbstractProcess subProc = findSubProcTargeted(subprocessName, subprocessVersion);

        if (subProc == null) {
            return ctx.createSuccessStatus();
        }
        final List<Data> data = ModelHelper.getAccessibleData(subProc);
        for (final OutputMapping out : callActivity.getOutputMappings()) {
            if (out.getProcessTarget() == null) {
                return ctx.createFailureStatus(new Object[] { Messages.bind(Messages.Validation_Subprocess_OutputMapping_SourceData_Not_Found,
                        callActivity.getName()) });
            }
            if (!exist(out.getSubprocessSource(), data)) {
                return ctx.createFailureStatus(new Object[] { Messages.bind(Messages.Validation_Subprocess_OutputMapping_SourceData_Not_Found,
                        callActivity.getName()) });
            }
        }
        for (final InputMapping in : callActivity.getInputMappings()) {
            if (in.getProcessSource() == null) {
                return ctx.createFailureStatus(new Object[] { Messages.bind(Messages.Validation_Subprocess_InputMapping_TargetData_Not_Found,
                        callActivity.getName()) });
            }
            if (InputMappingAssignationType.DATA == in.getAssignationType() && !exist(in.getSubprocessTarget(), data)) {
                return ctx.createFailureStatus(new Object[] { Messages.bind(Messages.Validation_Subprocess_InputMapping_TargetData_Not_Found,
                        callActivity.getName()) });
            }
        }

        return ctx.createSuccessStatus();
    }

    protected AbstractProcess findSubProcTargeted(final Expression subprocessName, final Expression subprocessVersion) {
        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        return diagramStore.findProcess(subprocessName.getContent(), subprocessVersion.getContent());
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
