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

import org.junit.Test;

public class PageflowDataTabbedPropertyProviderTest {

    @Test
    public void should_return_application_viewId() throws Exception {
        final PageflowDataTabbedPropertyProvider provider = new PageflowDataTabbedPropertyProvider();

        assertThat(provider.viewId()).isEqualTo("org.bonitasoft.studio.views.properties.application");
    }

    @Test
    public void should_return_overview_form_tab_id_for_overview_pageflow_data() throws Exception {
        final PageflowDataTabbedPropertyProvider provider = new PageflowDataTabbedPropertyProvider();

        assertThat(provider.tabId(aData().inOverviewPageflow(aPool()).build())).isEqualTo("tab.forms.overview");
    }

    @Test
    public void should_return_entry_form_tab_id_for_pageflow_data() throws Exception {
        final PageflowDataTabbedPropertyProvider provider = new PageflowDataTabbedPropertyProvider();

        assertThat(provider.tabId(aData().inPageflow(aPool()).build())).isEqualTo("tab.forms.entry");
    }

    @Test
    public void should_return_tab_index_for_pageflow_data_section() throws Exception {
        final PageflowDataTabbedPropertyProvider provider = new PageflowDataTabbedPropertyProvider();

        assertThat(provider.tabIndex()).isEqualTo(1);
    }

    @Test
    public void should_appliesTo_Data_in_a_Pageflow() throws Exception {
        final PageflowDataTabbedPropertyProvider provider = new PageflowDataTabbedPropertyProvider();

        assertThat(provider.appliesTo(aData().inPageflow(aPool()).build(), null)).isTrue();
    }

    @Test
    public void should_appliesTo_Data_in_an_overview_Pageflow() throws Exception {
        final PageflowDataTabbedPropertyProvider provider = new PageflowDataTabbedPropertyProvider();

        assertThat(provider.appliesTo(aData().inOverviewPageflow(aPool()).build(), null)).isTrue();
    }

    @Test
    public void should_appliesTo__Data_child_in_a_Pageflow() throws Exception {
        final PageflowDataTabbedPropertyProvider provider = new PageflowDataTabbedPropertyProvider();

        assertThat(provider.appliesTo(aData().havingDefaultValue(anExpression()).inPageflow(aPool()).build().getDefaultValue(), null)).isTrue();
    }

    @Test
    public void should_not_appliesTo_to_process_data() throws Exception {
        final PageflowDataTabbedPropertyProvider provider = new PageflowDataTabbedPropertyProvider();

        assertThat(provider.appliesTo(aData().in(aPool()).build(), null)).isFalse();
    }

    @Test
    public void should_not_appliesTo_to_element_not_contained_in_a_data_pageflow() throws Exception {
        final PageflowDataTabbedPropertyProvider provider = new PageflowDataTabbedPropertyProvider();

        assertThat(provider.appliesTo(aPool().build(), null)).isFalse();
    }
}
