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

import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.SequenceFlowBuilder.aSequenceFlow;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SequenceFlowContainmentConsitencyConstraintTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_throw_an_IllegalArgumentException_if_target_is_not_a_SequenceFlow() throws Exception {
        final SequenceFlowContainmentConsitencyConstraint constraint = new SequenceFlowContainmentConsitencyConstraint();

        thrown.expect(IllegalArgumentException.class);

        constraint.performBatchValidation(aValidationContext(null));
    }

    @Test
    public void should_create_failure_status_if_target_element_is_null() throws Exception {
        final SequenceFlowContainmentConsitencyConstraint constraint = new SequenceFlowContainmentConsitencyConstraint();
        final IValidationContext validationContext = aValidationContext(aSequenceFlow().havingSource(aTask()).build());

        constraint.performBatchValidation(validationContext);

        verify(validationContext).createFailureStatus(Messages.sequenceFlow_Without_Target_Element);
    }

    @Test
    public void should_create_failure_status_if_source_element_is_null() throws Exception {
        final SequenceFlowContainmentConsitencyConstraint constraint = new SequenceFlowContainmentConsitencyConstraint();
        final IValidationContext validationContext = aValidationContext(aSequenceFlow().havingTarget(aTask()).build());

        constraint.performBatchValidation(validationContext);

        verify(validationContext).createFailureStatus(Messages.sequenceFlow_Without_Source_Element);
    }

    @Test
    public void should_create_failure_status_if_sequence_flow_element_is_not_contained_in_a_pool() throws Exception {
        final SequenceFlowContainmentConsitencyConstraint constraint = new SequenceFlowContainmentConsitencyConstraint();
        final IValidationContext validationContext = aValidationContext(aSequenceFlow().havingSource(aTask()).havingTarget(aTask()).build());

        constraint.performBatchValidation(validationContext);

        verify(validationContext).createFailureStatus(Messages.sequenceFlow_Without_Container);
    }

    @Test
    public void should_create_failure_status_if_sequence_flow_element_is_not_contained_in_the_same_pool_than_source_element() throws Exception {
        final SequenceFlowContainmentConsitencyConstraint constraint = new SequenceFlowContainmentConsitencyConstraint();
        final Pool sequenceFlowContainer = aPool().build();
        final IValidationContext validationContext = aValidationContext(aSequenceFlow()
                .havingSource(aTask().in(aPool()))
                .havingTarget(aTask().in(sequenceFlowContainer))
                .in(sequenceFlowContainer).build());

        constraint.performBatchValidation(validationContext);

        verify(validationContext).createFailureStatus(Messages.sequenceFlow_And_SourceElement_Not_In_The_Same_Container);
    }

    @Test
    public void should_create_failure_status_if_sequence_flow_element_is_not_contained_in_the_same_pool_than_target_element() throws Exception {
        final SequenceFlowContainmentConsitencyConstraint constraint = new SequenceFlowContainmentConsitencyConstraint();
        final Pool sequenceFlowContainer = aPool().build();
        final IValidationContext validationContext = aValidationContext(aSequenceFlow()
                .havingSource(aTask().in(sequenceFlowContainer))
                .havingTarget(aTask().in(aPool()))
                .in(sequenceFlowContainer).build());

        constraint.performBatchValidation(validationContext);

        verify(validationContext).createFailureStatus(Messages.sequenceFlow_And_TargetElement_Not_In_The_Same_Container);
    }

    @Test
    public void should_create_success_status_if_sequence_flow_element_is_contained_in_the_same_pool_than_target_element_and_source_element() throws Exception {
        final SequenceFlowContainmentConsitencyConstraint constraint = new SequenceFlowContainmentConsitencyConstraint();
        final Pool sequenceFlowContainer = aPool().build();
        final IValidationContext validationContext = aValidationContext(aSequenceFlow()
                .havingSource(aTask().in(sequenceFlowContainer))
                .havingTarget(aTask().in(sequenceFlowContainer))
                .in(sequenceFlowContainer).build());

        constraint.performBatchValidation(validationContext);

        verify(validationContext).createSuccessStatus();
    }

    private IValidationContext aValidationContext(final EObject target) {
        final IValidationContext context = mock(IValidationContext.class);
        doReturn(target).when(context).getTarget();
        return context;
    }
}
