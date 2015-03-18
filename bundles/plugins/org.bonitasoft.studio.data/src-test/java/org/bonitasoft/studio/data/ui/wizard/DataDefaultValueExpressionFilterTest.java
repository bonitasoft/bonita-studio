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

import static org.mockito.Mockito.doReturn;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DataDefaultValueExpressionFilterTest {

    @Mock
    private DataWizardPage wizardPage;

    @Mock
    IExpressionProvider contractExpressionProvider;


    @Before
    public void setUp() {
        doReturn(ExpressionConstants.CONTRACT_INPUT_TYPE).when(contractExpressionProvider).getExpressionType();
    }

    @Test
    public void testSelect_Contract_OnPool() throws Exception {
        final DataDefaultValueExpressionFilter dataDefaultValueExpressionFilter = new DataDefaultValueExpressionFilter(wizardPage,
                ProcessFactory.eINSTANCE.createPool(), false);
        Assertions.assertThat(
                dataDefaultValueExpressionFilter
                        .select(null, null, contractExpressionProvider)).isTrue();
    }

    @Test
    public void testSelect_Contract_OnTask() throws Exception {
        final DataDefaultValueExpressionFilter dataDefaultValueExpressionFilter = new DataDefaultValueExpressionFilter(wizardPage,
                ProcessFactory.eINSTANCE.createTask(), false);
        Assertions.assertThat(
                dataDefaultValueExpressionFilter
                        .select(null, null, contractExpressionProvider)).isFalse();
    }

}
