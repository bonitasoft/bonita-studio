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
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process.FormMappingTabbedPropertyProvider;
import org.junit.Test;

public class FormMappingTabbedPropertyProviderTest {

    @Test
    public void should_return_execution_view_id() throws Exception {
        final FormMappingTabbedPropertyProvider formMappingPropertyProvider = new FormMappingTabbedPropertyProvider();

        assertThat(formMappingPropertyProvider.viewId()).isEqualTo("org.bonitasoft.studio.views.properties.process.execution");
    }

    @Test
    public void should_return_case_instantiation_tab_if_form_mapping_is_contained_in_a_pool() throws Exception {
        final FormMappingTabbedPropertyProvider formMappingPropertyProvider = new FormMappingTabbedPropertyProvider();

        assertThat(formMappingPropertyProvider.tabId(aPool().havingFormMapping(aFormMapping()).build().getFormMapping())).isEqualTo(
                "tab.form.mapping.caseStart");
    }

    @Test
    public void should_return_case_overview_tab_if_form_mapping_is_contained_in_a_pool_in_overview_feature() throws Exception {
        final FormMappingTabbedPropertyProvider formMappingPropertyProvider = new FormMappingTabbedPropertyProvider();

        assertThat(formMappingPropertyProvider.tabId(aPool().havingOverviewFormMapping(aFormMapping()).build().getOverviewFormMapping())).isEqualTo(
                "tab.form.mapping.overview");
    }

    @Test
    public void should_return_form_tab_if_form_mapping_is_contained_in_a_task() throws Exception {
        final FormMappingTabbedPropertyProvider formMappingPropertyProvider = new FormMappingTabbedPropertyProvider();

        assertThat(formMappingPropertyProvider.tabId(aTask().havingFormMapping(aFormMapping()).build().getFormMapping())).isEqualTo(
                "tab.form.mapping.entry");
    }
}
