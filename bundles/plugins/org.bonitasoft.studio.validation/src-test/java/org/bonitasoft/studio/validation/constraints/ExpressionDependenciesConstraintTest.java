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
package org.bonitasoft.studio.validation.constraints;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aConstantExpression;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aVariableExpression;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.expression.editor.provider.ExpressionTypeLabelProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ExpressionDependenciesConstraintTest {

    private ExpressionDependenciesConstraint expressionDependenciesConstraint;
    @Mock
    private IValidationContext ctx;
    @Mock
    private ExpressionTypeLabelProvider labelProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        expressionDependenciesConstraint = new ExpressionDependenciesConstraint(labelProvider);
        doReturn(ValidationStatus.ok()).when(ctx).createSuccessStatus();
        doReturn(ValidationStatus.error("error")).when(ctx).createFailureStatus(any(Object.class));
    }

    @Test
    public void should_return_a_valid_status_for_constant_expression_without_dependencies() throws Exception {
        //Given
        final Expression constantExpression = aConstantExpression().withContent("hello").build();
        when(ctx.getTarget()).thenReturn(constantExpression);

        //When
        final IStatus status = expressionDependenciesConstraint.performBatchValidation(ctx);

        //Then
        assertThat(status).isOK();
    }

    @Test
    public void should_return_an_error_status_for_variable_expression_without_dependencies() throws Exception {
        //Given
        final Expression variableExpression = aVariableExpression().withContent("myVar").build();
        when(ctx.getTarget()).thenReturn(variableExpression);

        //When
        final IStatus status = expressionDependenciesConstraint.performBatchValidation(ctx);

        //Then
        assertThat(status).isNotOK();
        verify(labelProvider).getText(variableExpression.getType());
    }

    @Test
    public void should_return_an_valid_status_for_variable_expression_with_dependencies() throws Exception {
        //Given
        final Expression variableExpression = aVariableExpression().withContent("myVar")
                .havingReferencedElements(aData().build()).build();
        when(ctx.getTarget()).thenReturn(variableExpression);

        //When
        final IStatus status = expressionDependenciesConstraint.performBatchValidation(ctx);

        //Then
        assertThat(status).isOK();
    }

}
