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
package org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import org.junit.Test;

public class DataTabbedPropertyProviderTest {

    @Test
    public void should_return_data_viewId() throws Exception {
        final DataTabbedPropertyProvider provider = new DataTabbedPropertyProvider();

        assertThat(provider.viewId()).isEqualTo("org.bonitasoft.studio.views.properties.process.data");
    }

    @Test
    public void should_return_data_tab_id() throws Exception {
        final DataTabbedPropertyProvider provider = new DataTabbedPropertyProvider();

        assertThat(provider.tabId(null)).isEqualTo("tab.data");
    }

    @Test
    public void should_appliesTo_process_Data() throws Exception {
        final DataTabbedPropertyProvider provider = new DataTabbedPropertyProvider();

        assertThat(provider.appliesTo(aData().in(aTask()).build(), null)).isTrue();
    }

    @Test
    public void should_appliesTo_process_data_child() throws Exception {
        final DataTabbedPropertyProvider provider = new DataTabbedPropertyProvider();

        assertThat(provider.appliesTo(aData().havingDefaultValue(anExpression()).in(aTask()).build().getDefaultValue(), null)).isTrue();
    }


    @Test
    public void should_not_appliesTo_to_element_not_contained_in_a_data() throws Exception {
        final DataTabbedPropertyProvider provider = new DataTabbedPropertyProvider();

        assertThat(provider.appliesTo(aPool().build(), null)).isFalse();
    }
}
