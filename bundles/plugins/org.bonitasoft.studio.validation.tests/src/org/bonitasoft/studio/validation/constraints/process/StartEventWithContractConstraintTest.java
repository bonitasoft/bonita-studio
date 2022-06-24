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

import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.StartEventBuilder.aStartEvent;
import static org.bonitasoft.studio.model.process.builders.StartMessageEventBuilder.aStartMessageEvent;
import static org.bonitasoft.studio.model.process.builders.StartSignalEventBuilder.aStartSignalEvent;
import static org.bonitasoft.studio.model.process.builders.StartTimerEventBuilder.aStartTimerEvent;
import static org.bonitasoft.studio.model.process.builders.SubProcessEventBuilder.aSubProcessEvent;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Test;

public class StartEventWithContractConstraintTest {

    @Test
    public void should_fail_if_process_have_a_start_timer_event_and_has_a_non_empty_contract() throws Exception {
        final StartEventWithContractConstraint constraint = newConstraint();

        final IValidationContext ctx = aValidationContext(aStartTimerEvent().in(aPool().havingContract(aContract().havingInput(aContractInput()))).build());
        constraint.performBatchValidation(ctx);

        verify(ctx).createFailureStatus(Messages.cannotUseThisStartEventTypeWithAContract);
    }

    @Test
    public void should_fail_if_process_have_a_start_message_event_and_has_a_non_empty_contract() throws Exception {
        final StartEventWithContractConstraint constraint = newConstraint();

        final IValidationContext ctx = aValidationContext(aStartMessageEvent().in(aPool().havingContract(aContract().havingInput(aContractInput()))).build());
        constraint.performBatchValidation(ctx);

        verify(ctx).createFailureStatus(Messages.cannotUseThisStartEventTypeWithAContract);
    }

    @Test
    public void should_fail_if_process_have_a_start_signal_event_and_has_a_non_empty_contract() throws Exception {
        final StartEventWithContractConstraint constraint = newConstraint();

        final IValidationContext ctx = aValidationContext(aStartSignalEvent().in(aPool().havingContract(aContract().havingInput(aContractInput()))).build());
        constraint.performBatchValidation(ctx);

        verify(ctx).createFailureStatus(Messages.cannotUseThisStartEventTypeWithAContract);
    }

    @Test
    public void should_not_fail_if_process_have_a_start_timer_event_and_has_an_empty_contract() throws Exception {
        final StartEventWithContractConstraint constraint = newConstraint();

        final IValidationContext ctx = aValidationContext(aStartTimerEvent().in(aPool().havingContract(aContract())).build());
        constraint.performBatchValidation(ctx);

        verify(ctx).createSuccessStatus();
    }

    @Test
    public void should_not_fail_if_process_have_a_none_event_and_has_a_contract() throws Exception {
        final StartEventWithContractConstraint constraint = newConstraint();

        final IValidationContext ctx = aValidationContext(aStartEvent().in(aPool().havingContract(aContract().havingInput(aContractInput()))).build());
        constraint.performBatchValidation(ctx);

        verify(ctx).createSuccessStatus();
    }

    @Test
    public void should_not_fail_if_process_have_a_signal_event_in_an_event_subprocess_and_parent_process_has_a_contract() throws Exception {
        final StartEventWithContractConstraint constraint = newConstraint();

        final StartMessageEvent startMessageEvent = aStartMessageEvent().build();
        aStartEvent().in(aPool()
                .havingContract(aContract().havingInput(aContractInput()))
                .havingElements(aSubProcessEvent().havingElements(startMessageEvent))).build();
        final IValidationContext ctx = aValidationContext(startMessageEvent);
        constraint.performBatchValidation(ctx);

        verify(ctx).createSuccessStatus();
    }

    private StartEventWithContractConstraint newConstraint() {
        return new StartEventWithContractConstraint();
    }

    private IValidationContext aValidationContext(final EObject eObject) {
        final IValidationContext validationContext = mock(IValidationContext.class);
        when(validationContext.getTarget()).thenReturn(eObject);
        return validationContext;
    }

}
