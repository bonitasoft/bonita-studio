/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Arrays;

import org.bonitasoft.studio.model.process.AbstractProcess;
import org.junit.Before;
import org.junit.Test;

public class DiagramRepositoryStoreTest {

    private DiagramRepositoryStore store;

    @Before
    public void setUp() throws Exception {
        store = spy(new DiagramRepositoryStore());
        doReturn(false).when(store).hasComputedProcesses();
    }

    @Test
    public void should_find_process_with_highest_version() throws Exception {
        doReturn(Arrays.asList(process("A", "1.0"), process("A", "1.1"), process("A", "10.0"))).when(store)
                .getAllProcesses();

        AbstractProcess process = store.findProcess("A", null);

        assertThat(process)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "A")
                .hasFieldOrPropertyWithValue("version","10.0");
    }
    
    @Test
    public void should_find_process_with__version() throws Exception {
        doReturn(Arrays.asList(process("A", "1.0"), process("A", "1.1"), process("B", "10.0"))).when(store)
                .getAllProcesses();

        AbstractProcess process = store.findProcess("A", "1.1");

        assertThat(process)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "A")
                .hasFieldOrPropertyWithValue("version","1.1");
    }

    private AbstractProcess process(String name, String version) {
        return aPool()
                .withName(name)
                .withVersion(version)
                .build();
    }

}
