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
import static org.bonitasoft.studio.model.process.builders.ActorFilterBuilder.anActorFilter;
import static org.bonitasoft.studio.model.process.builders.ConnectorBuilder.aConnector;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.junit.Test;

public class ConnectorTabbedPropertyProviderTest {

    @Test
    public void should_return_execution_viewId() throws Exception {
        final ConnectorTabbedPropertyProvider provider = new ConnectorTabbedPropertyProvider();

        assertThat(provider.viewId()).isEqualTo("org.bonitasoft.studio.views.properties.process.execution");
    }

    @Test
    public void should_return_connector_out_tab_id_if_connector_has_ON_FINISH_event() throws Exception {
        final ConnectorTabbedPropertyProvider provider = new ConnectorTabbedPropertyProvider();

        assertThat(provider.tabId(aConnector().onEvent(ConnectorEvent.ON_FINISH.name()).build())).isEqualTo("tab.connectors.out");
    }

    @Test
    public void should_return_connector_in_tab_id_if_connector_has_ON_ENTER_event() throws Exception {
        final ConnectorTabbedPropertyProvider provider = new ConnectorTabbedPropertyProvider();

        assertThat(provider.tabId(aConnector().onEvent(ConnectorEvent.ON_ENTER.name()).build())).isEqualTo("tab.connectors.in");
    }

    @Test
    public void should_appliesTo_Connector() throws Exception {
        final ConnectorTabbedPropertyProvider provider = new ConnectorTabbedPropertyProvider();

        assertThat(provider.appliesTo(aTask().havingConnector(aConnector()).build().getConnectors().get(0), null)).isTrue();
        assertThat(provider.appliesTo(aTask().havingActorFilter(anActorFilter()).build().getFilters().get(0), null)).isFalse();
    }

}
