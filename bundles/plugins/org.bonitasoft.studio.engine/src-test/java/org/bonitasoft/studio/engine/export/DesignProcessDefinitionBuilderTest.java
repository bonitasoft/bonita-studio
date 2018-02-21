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
package org.bonitasoft.studio.engine.export;

import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.engine.export.builder.EngineProcessBuilder;
import org.bonitasoft.studio.engine.export.builder.IEngineDefinitionBuilderProvider;
import org.bonitasoft.studio.model.process.Pool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DesignProcessDefinitionBuilderTest {

    @Mock
    private EngineProcessBuilder engineProcessBuilder;

    @Mock
    private IEngineDefinitionBuilderProvider builderProvider;



    @Test
    public void should_export_pool_element_using_EngineProcessBuilder() throws Exception {
        final DesignProcessDefinitionBuilder definitionBuilder = spy(
                new DesignProcessDefinitionBuilder(builderProvider, new ModelSearch(Collections::emptyList)));
        doReturn(engineProcessBuilder).when(definitionBuilder).newEngineProcessBuilder(any(ProcessDefinitionBuilder.class));

        final Pool pool = aPool().withName("myProcess").withVersion("1.0").build();
        definitionBuilder.createDefinition(pool);

        verify(engineProcessBuilder).doSwitch(pool);
    }
}
