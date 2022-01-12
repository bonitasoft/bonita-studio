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
package org.bonitasoft.studio.data.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DataDefaultValueExpressionFilterTest {

    @Mock
    private IExpressionProvider contractExpressionProvider;

    @Test
    public void testSelect_Contract_OnPool() throws Exception {
        doReturn(ExpressionConstants.CONTRACT_INPUT_TYPE).when(contractExpressionProvider).getExpressionType();

        final DataDefaultValueExpressionFilter dataDefaultValueExpressionFilter = new DataDefaultValueExpressionFilter(mock(DataWizardPage.class),
                aPool().build(), false, false);

        assertThat(dataDefaultValueExpressionFilter.select(null, null, contractExpressionProvider)).isTrue();
    }

    @Test
    public void testSelect_Contract_OnTask() throws Exception {
        doReturn(ExpressionConstants.CONTRACT_INPUT_TYPE).when(contractExpressionProvider).getExpressionType();

        final DataDefaultValueExpressionFilter dataDefaultValueExpressionFilter = new DataDefaultValueExpressionFilter(mock(DataWizardPage.class),
                aTask().build(), false, false);

        assertThat(dataDefaultValueExpressionFilter.select(null, null, contractExpressionProvider)).isFalse();
    }

    @Test
    public void should_not_allow_query_expression_type() throws Exception {
        doReturn(ExpressionConstants.QUERY_TYPE).when(contractExpressionProvider).getExpressionType();

        final DataDefaultValueExpressionFilter dataDefaultValueExpressionFilter = new DataDefaultValueExpressionFilter(mock(DataWizardPage.class),
                aTask().build(), false, false);

        assertThat(dataDefaultValueExpressionFilter.select(null, null, contractExpressionProvider)).isFalse();
    }

    @Test
    public void should_allow_query_expression_type_in_pageflow_context() throws Exception {
        doReturn(ExpressionConstants.QUERY_TYPE).when(contractExpressionProvider).getExpressionType();

        final DataDefaultValueExpressionFilter dataDefaultValueExpressionFilter = new DataDefaultValueExpressionFilter(mock(DataWizardPage.class),
                aTask().build(), false, true);

        assertThat(dataDefaultValueExpressionFilter.select(null, null, contractExpressionProvider)).isTrue();
    }

}
