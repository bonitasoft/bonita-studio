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
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.bonitasoft.studio.model.kpi.DatabaseKPIBinding;
import org.bonitasoft.studio.model.kpi.KPIParameterMapping;
import org.bonitasoft.studio.model.kpi.KpiFactory;
import org.junit.Test;

public class KPITabbedPropertyProviderTest {

    @Test
    public void should_return_execution_view_id() throws Exception {
        final KPITabbedPropertyProvider provider = new KPITabbedPropertyProvider();

        assertThat(provider.viewId()).isEqualTo("org.bonitasoft.studio.views.properties.process.execution");
    }

    @Test
    public void should_return_kpi_tab_id() throws Exception {
        final KPITabbedPropertyProvider provider = new KPITabbedPropertyProvider();

        assertThat(provider.tabId(null)).isEqualTo("tab.kpi");
    }

    @Test
    public void should_applies_to_KPIBinding() throws Exception {
        final KPITabbedPropertyProvider provider = new KPITabbedPropertyProvider();

        assertThat(provider.appliesTo(KpiFactory.eINSTANCE.createDatabaseKPIBinding(), null)).isTrue();
    }

    @Test
    public void should_applies_to_KPIBinding_child() throws Exception {
        final KPITabbedPropertyProvider provider = new KPITabbedPropertyProvider();

        final KpiFactory kpiFactory = KpiFactory.eINSTANCE;
        final DatabaseKPIBinding kpiBinding = kpiFactory.createDatabaseKPIBinding();
        final KPIParameterMapping param = kpiFactory.createKPIParameterMapping();
        kpiBinding.getParameters().add(param);
        assertThat(provider.appliesTo(param, null)).isTrue();
    }

    @Test
    public void should_not_applies_to_element_not_in_a_KPIBinding() throws Exception {
        final KPITabbedPropertyProvider provider = new KPITabbedPropertyProvider();

        assertThat(provider.appliesTo(aPool().build(), null)).isFalse();
    }
}
