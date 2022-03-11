/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.ui.property.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import org.bonitasoft.studio.designer.ui.property.filter.TaskFilter;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class TaskFilterTest {

    private TaskFilter taskFilter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        taskFilter = new TaskFilter();
    }

    @Test
    public void should_accept_task_adaptable_only() throws Exception {
        assertThat(taskFilter.select(new EObjectAdapter(aTask().build()))).isTrue();
        assertThat(taskFilter.select(new EObjectAdapter(aPool().build()))).isFalse();
        assertThat(taskFilter.select(aTask().build())).isFalse();
    }
}
