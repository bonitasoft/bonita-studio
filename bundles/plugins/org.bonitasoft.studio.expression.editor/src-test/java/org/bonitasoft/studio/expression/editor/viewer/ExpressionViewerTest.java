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
package org.bonitasoft.studio.expression.editor.viewer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ExpressionViewerTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    private ExpressionViewer expressionViewer;

    private DataBindingContext dbc;

    @Mock
    private ValidationStatusProvider validationStatusProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        dbc = new EMFDataBindingContext();
        expressionViewer = new ExpressionViewer(realm.createComposite(), SWT.BORDER);
        when(validationStatusProvider.getValidationStatus()).thenReturn(new WritableValue(ValidationStatus.ok(), IStatus.class));
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

    @Mock
    EObject input1, input2, context;
    @Mock
    ExpressionViewer mockedExprViewer;

    @Test
    public void setInputShouldUpdateContextIfTheyAreTheSame() {//for legacy purpose
        doCallRealMethod().when(mockedExprViewer).setContext(any(EObject.class));
        doCallRealMethod().when(mockedExprViewer).isOldContextAndInputSimilar(any(EObject.class));
        doReturn(input1).when(mockedExprViewer).getInput();

        mockedExprViewer.setContext(input1);

        assertThat(mockedExprViewer.isOldContextAndInputSimilar(input2)).isTrue();
    }

    @Test
    public void setInputShouldNOTUpdateContextIfTheyAreDifferent() {//for legacy purpose
        doCallRealMethod().when(mockedExprViewer).setContext(any(EObject.class));
        doCallRealMethod().when(mockedExprViewer).isOldContextAndInputSimilar(any(EObject.class));
        doReturn(context).when(mockedExprViewer).getInput();

        mockedExprViewer.setContext(input1);

        assertThat(mockedExprViewer.isOldContextAndInputSimilar(input2)).isFalse();
    }

    @Test
    public void getContentTypeFromInputNullSupport() throws Exception {
        doCallRealMethod().when(mockedExprViewer).getContentTypeFromInput(anyString());
        final Expression selectedExpression = ExpressionBuilder.aConstantExpression().build();
        doReturn(selectedExpression).when(mockedExprViewer).getSelectedExpression();

        assertThat(mockedExprViewer.getContentTypeFromInput("test")).isEqualTo(ExpressionConstants.CONSTANT_TYPE);
    }

    @Test
    public void getContentTypeFromInputMessageContent() throws Exception {
        doCallRealMethod().when(mockedExprViewer).getContentTypeFromInput(anyString());
        final Expression selectedExpression = ExpressionBuilder.anExpression().withExpressionType(ExpressionConstants.MESSAGE_ID_TYPE).build();
        doReturn(selectedExpression).when(mockedExprViewer).getSelectedExpression();

        assertThat(mockedExprViewer.getContentTypeFromInput("test")).isEqualTo(ExpressionConstants.MESSAGE_ID_TYPE);
    }

}
