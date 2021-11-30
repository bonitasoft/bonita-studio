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
package org.bonitasoft.studio.engine.export.builder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.engine.bpm.process.impl.ConnectorDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.FlowElementBuilder;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.model.kpi.AbstractKPIBinding;
import org.bonitasoft.studio.model.kpi.KpiFactory;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.builders.TaskBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AbstractProcessBuilderTest {

    private AbstractProcessBuilder abstractSwitch;
    @Mock
    private FlowElementBuilder builder;
    @Mock
    private ConnectorDefinitionBuilder connectorBuilder;


    @Before
    public void setup() {
        doReturn(connectorBuilder).when(builder).addConnector(anyString(), anyString(), anyString(), any(ConnectorEvent.class));
        abstractSwitch = spy(new AbstractProcessBuilder(new RCPEngineDefintionBuilderProvider(),
                new ModelSearch(Collections::emptyList)) {
        });
    }

    @Test
    public void testAddKPIBindingCallIgnoreState() throws Exception {
        final AbstractKPIBinding kpiBinding = KpiFactory.eINSTANCE.createDatabaseKPIBinding();
        kpiBinding.setName("test");
        kpiBinding.setIgnoreError(true);
        kpiBinding.setEvent(ConnectorEvent.ON_FINISH.name());
        final ConnectableElement element = TaskBuilder
                .anActivity()
                .havingKPI(kpiBinding)
                .withName("elemtnName")
                .build();

        abstractSwitch.addKPIBinding(builder, element);

        verify(connectorBuilder).ignoreError();
    }

    @Test
    public void testAddKPIBindingDontCallIgnoreState() throws Exception {
        final AbstractKPIBinding kpiBinding = KpiFactory.eINSTANCE.createDatabaseKPIBinding();
        kpiBinding.setName("test");
        kpiBinding.setIgnoreError(false);
        kpiBinding.setEvent(ConnectorEvent.ON_FINISH.name());
        final ConnectableElement element = TaskBuilder
                .anActivity()
                .havingKPI(kpiBinding)
                .withName("elemtnName")
                .build();

        abstractSwitch.addKPIBinding(builder, element);

        verify(connectorBuilder, never()).ignoreError();
    }

}
