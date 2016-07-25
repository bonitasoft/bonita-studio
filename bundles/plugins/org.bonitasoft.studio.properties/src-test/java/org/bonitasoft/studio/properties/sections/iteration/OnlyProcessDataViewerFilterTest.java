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
package org.bonitasoft.studio.properties.sections.iteration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class OnlyProcessDataViewerFilterTest {

    private OnlyProcessDataViewerFilter onlyProcessDataViewerFilter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        onlyProcessDataViewerFilter = new OnlyProcessDataViewerFilter();
    }

    @Test
    public void should_hide_step_data() throws Exception {
        assertThat(onlyProcessDataViewerFilter.select(null, aTask().havingData(aData().withName("stepData")).build(),
                anExpression().withName("stepData").withExpressionType(ExpressionConstants.VARIABLE_TYPE).build())).isFalse();
    }

    @Test
    public void should_show_process_data() throws Exception {
        assertThat(onlyProcessDataViewerFilter.select(null, aTask().havingData(aData().withName("stepData")).build(),
                anExpression().withName("poolData").withExpressionType(ExpressionConstants.VARIABLE_TYPE).build())).isTrue();
    }

    @Test
    public void should_show_element_if_parent_is_not_an_Activity() throws Exception {
        assertThat(onlyProcessDataViewerFilter.select(null, null, null)).isTrue();
    }

    @Test
    public void should_show_element_if_element_is_not_an_Expression() throws Exception {
        assertThat(onlyProcessDataViewerFilter.select(null, aTask().build(), null)).isTrue();
    }

    @Test
    public void should_show_element_if_element_is_an_Expression_without_name() throws Exception {
        assertThat(onlyProcessDataViewerFilter.select(null, aTask().build(), anExpression().build())).isTrue();
    }

    @Test
    public void should_show_element_if_element_is_an_Expression_that_is_not_a_variable() throws Exception {
        assertThat(
                onlyProcessDataViewerFilter.select(null, aTask().build(),
                        anExpression().withName("poolData").withExpressionType(ExpressionConstants.CONSTANT_TYPE).build())).isTrue();
    }
}
