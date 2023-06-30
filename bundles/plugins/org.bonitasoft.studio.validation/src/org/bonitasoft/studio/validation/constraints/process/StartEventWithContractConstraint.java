/**
 * Copyright (C) 2015 Bonitasoft S.A.
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

import static com.google.common.base.Preconditions.checkArgument;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.model.process.StartSignalEvent;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.util.ProcessSwitch;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

public class StartEventWithContractConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        checkArgument(eObj instanceof Event);
        return new ProcessSwitch<IStatus>() {

            @Override
            public IStatus caseStartMessageEvent(final StartMessageEvent event) {
                return startsProcessWithContract(ctx, event);
            }

            @Override
            public IStatus caseStartTimerEvent(final StartTimerEvent event) {
                return startsProcessWithContract(ctx, event);
            }

            @Override
            public IStatus caseStartSignalEvent(final StartSignalEvent event) {
                return startsProcessWithContract(ctx, event);
            }

            @Override
            public IStatus caseStartEvent(final StartEvent object) {
                return ctx.createSuccessStatus();
            }

        }.doSwitch(eObj);
    }

    private IStatus startsProcessWithContract(final IValidationContext ctx, final Event event) {
        if (!ModelHelper.isInEvenementialSubProcessPool(event)) {
            final ContractContainer contractContainer = ModelHelper.getFirstContainerOfType(event, ContractContainer.class);
            if (!contractContainer.getContract().getInputs().isEmpty()) {
                return ctx.createFailureStatus(Messages.cannotUseThisStartEventTypeWithAContract);
            }
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.startEventAndContract";
    }

}
