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
package org.bonitasoft.studio.validation.constraints.process;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class MultiInstanceIteratorConstraintTest {

    private MultiInstanceIteratorConstraint multiInstanceIteratorConstraint;

    @Mock
    private IValidationContext context;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(context.createSuccessStatus()).thenReturn(ValidationStatus.ok());
        when(context.createFailureStatus(any(Object.class))).thenReturn(ValidationStatus.error("failed!"));

        multiInstanceIteratorConstraint = new MultiInstanceIteratorConstraint();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_accept_valid_iterator_name() throws Exception {
        when(context.getTarget()).thenReturn(
                aTask().withMultiInstanceType(MultiInstanceType.PARALLEL).basedOnCollection()
                        .havingIteratorExpression(anExpression().withName("aValidIteratorName")).build());

        final IStatus iStatus = multiInstanceIteratorConstraint.performBatchValidation(context);

        assertThat(iStatus).isOK();
    }

    @Test
    public void should_fail_empty_iterator_name() throws Exception {
        when(context.getTarget()).thenReturn(
                aTask().withMultiInstanceType(MultiInstanceType.PARALLEL).basedOnCollection()
                        .havingIteratorExpression(anExpression().withName("")).build());

        final IStatus iStatus = multiInstanceIteratorConstraint.performBatchValidation(context);

        assertThat(iStatus).isNotOK();
    }

    @Test
    public void should_fail_iterator_name_starting_with_a_capital_letter() throws Exception {
        when(context.getTarget()).thenReturn(
                aTask().withMultiInstanceType(MultiInstanceType.PARALLEL).basedOnCollection()
                        .havingIteratorExpression(anExpression().withName("Iterator")).build());

        final IStatus iStatus = multiInstanceIteratorConstraint.performBatchValidation(context);

        assertThat(iStatus).isNotOK();
    }

    @Test
    public void should_fail_iterator_name_starting_with_a_spaces() throws Exception {
        when(context.getTarget()).thenReturn(
                aTask().withMultiInstanceType(MultiInstanceType.SEQUENTIAL).basedOnCollection()
                        .havingIteratorExpression(anExpression().withName("an iterator")).build());

        final IStatus iStatus = multiInstanceIteratorConstraint.performBatchValidation(context);

        assertThat(iStatus).isNotOK();
    }

    @Test
    public void should_accept_other_iteration_type() throws Exception {
        when(context.getTarget()).thenReturn(
                aTask().withMultiInstanceType(MultiInstanceType.STANDARD).basedOnCollection()
                        .havingIteratorExpression(anExpression().withName("an invalid iterator")).build());

        final IStatus iStatus = multiInstanceIteratorConstraint.performBatchValidation(context);

        assertThat(iStatus).isOK();
    }

    @Test
    public void should_accept_based_on_cardianlity() throws Exception {
        when(context.getTarget()).thenReturn(
                aTask().withMultiInstanceType(MultiInstanceType.SEQUENTIAL).basedOnCardinality()
                        .havingIteratorExpression(anExpression().withName("an invalid iterator")).build());

        final IStatus iStatus = multiInstanceIteratorConstraint.performBatchValidation(context);

        assertThat(iStatus).isOK();
    }

}
