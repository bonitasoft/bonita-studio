/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.expression.editor.viewer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ExpressionViewerTest extends AbstractSWTTestCase {

    private ExpressionViewer expressionViewer;
    private DataBindingContext dbc;
    @Mock
    private ValidationStatusProvider validationStatusProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final Composite composite = createDisplayAndRealm();
        dbc = new EMFDataBindingContext();
        expressionViewer = new ExpressionViewer(composite, SWT.BORDER);
        when(validationStatusProvider.getValidationStatus()).thenReturn(new WritableValue(ValidationStatus.ok(), IStatus.class));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }

    @Test
    public void should_validateExternalDatabindingContextTargets_validate_binding_target_to_model() throws Exception {
        dbc.addValidationStatusProvider(validationStatusProvider);

        expressionViewer.validateExternalDatabindingContextTargets(dbc);

        verify(validationStatusProvider).getValidationStatus();
    }

    @Test
    public void should_erase_reset_expression() throws Exception {
        final Expression selectedExpression = ExpressionBuilder.anExpression().withName("name").withContent("name").withReturnType(Integer.class.getName())
                .withExpressionType(ExpressionConstants.VARIABLE_TYPE).havingReferencedElements(DataBuilder.aData().build()).build();

        expressionViewer.erase(selectedExpression);

        ExpressionAssert.assertThat(selectedExpression).hasName("");
        ExpressionAssert.assertThat(selectedExpression).hasContent("");
        ExpressionAssert.assertThat(selectedExpression).hasReturnType(String.class.getName());
        ExpressionAssert.assertThat(selectedExpression).hasNoReferencedElements();
        ExpressionAssert.assertThat(selectedExpression).hasType(ExpressionConstants.CONSTANT_TYPE);
    }

    @Test
    public void should_erase_reset_expression_with_fixed_return_type() throws Exception {
        final Expression selectedExpression = ExpressionBuilder.anExpression().withName("name").withContent("name").withReturnType(Integer.class.getName())
                .fixedReturnType()
                .withExpressionType(ExpressionConstants.VARIABLE_TYPE).havingReferencedElements(DataBuilder.aData().build()).build();

        expressionViewer.erase(selectedExpression);

        ExpressionAssert.assertThat(selectedExpression).hasName("");
        ExpressionAssert.assertThat(selectedExpression).hasContent("");
        ExpressionAssert.assertThat(selectedExpression).hasReturnType(Integer.class.getName());
        ExpressionAssert.assertThat(selectedExpression).hasNoReferencedElements();
        ExpressionAssert.assertThat(selectedExpression).hasType(ExpressionConstants.CONSTANT_TYPE);
    }

}
