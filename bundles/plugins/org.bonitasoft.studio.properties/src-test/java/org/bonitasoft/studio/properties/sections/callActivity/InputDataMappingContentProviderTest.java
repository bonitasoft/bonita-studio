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
package org.bonitasoft.studio.properties.sections.callActivity;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.data.provider.DataExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.builders.CallActivityBuilder;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class InputDataMappingContentProviderTest {

    private InputDataMappingContentProvider inputDataMappingContentProvider;

    @Mock
    private IObservableValue observable;

    @Mock
    private DataExpressionProvider expressionProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        inputDataMappingContentProvider = new InputDataMappingContentProvider(observable, expressionProvider);

        when(observable.getValue()).thenReturn(CallActivityBuilder.aCallActivity().build());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_variableExpressions_returns_expressions_with_variable_type() throws Exception {
        final ArrayList<Expression> expressions = newArrayList(
                anExpression().withExpressionType(ExpressionConstants.VARIABLE_TYPE).build(),
                anExpression().withExpressionType(ExpressionConstants.VARIABLE_TYPE).build(),
                anExpression().withExpressionType(ExpressionConstants.CONSTANT_TYPE).build(),
                anExpression().withExpressionType(null).build());

        assertThat(inputDataMappingContentProvider.variableExpressions(expressions)).hasSize(2);
    }

    @Test
    public void should_getElements_return_a_data_array() throws Exception {
        final Data data1 = aData().withName("data1").build();
        final Data data2 = aData().withName("data2").build();
        final Set<Expression> expressions = newHashSet(
                anExpression().withExpressionType(ExpressionConstants.VARIABLE_TYPE).havingReferencedElements(data1).build(),
                anExpression().withExpressionType(ExpressionConstants.VARIABLE_TYPE).havingReferencedElements(data2).build(),
                anExpression().withExpressionType(ExpressionConstants.CONSTANT_TYPE).build());

        when(expressionProvider.getExpressions(any(EObject.class))).thenReturn(expressions);

        assertThat(inputDataMappingContentProvider.getElements(null)).hasSize(2);
    }

}
