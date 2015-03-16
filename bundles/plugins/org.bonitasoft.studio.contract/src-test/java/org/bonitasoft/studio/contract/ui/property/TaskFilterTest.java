/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskFilterTest {

    private TaskFilter filter;

    @Mock
    private IGraphicalEditPart taskEditPart;

    @Mock
    private IGraphicalEditPart poolEditPart;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        filter = new TaskFilter();
        when(taskEditPart.resolveSemanticElement()).thenReturn(ProcessFactory.eINSTANCE.createTask());
        when(poolEditPart.resolveSemanticElement()).thenReturn(ProcessFactory.eINSTANCE.createPool());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_select_accept_TaskEditPart() throws Exception {
        assertThat(filter.select(taskEditPart)).isTrue();
    }

    @Test
    public void should_select_notaccept_PoolEditPart() throws Exception {
        assertThat(filter.select(poolEditPart)).isFalse();
    }

    @Test
    public void should_select_notaccept_other_type_than_editpart() throws Exception {
        assertThat(filter.select(ProcessFactory.eINSTANCE.createTask())).isFalse();
    }
}
