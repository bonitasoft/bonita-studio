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
package org.bonitasoft.studio.properties.filters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ActivityBuilder.anActivity;
import static org.bonitasoft.studio.model.process.builders.LaneBuilder.aLane;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.when;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
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
public class FormMappingSectionFilterTest {

    @Mock
    private IGraphicalEditPart ep;
    
    private FormMappingSectionFilter formMappingSectionFilter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        formMappingSectionFilter = new  FormMappingSectionFilter();
    }

    
    @Test
    public void should_accept_graphical_ep_bind_to_a_pool() throws Exception {
        when(ep.resolveSemanticElement()).thenReturn(aPool().build());
        
        assertThat(formMappingSectionFilter.select(ep)).isTrue();
    }
    
    @Test
    public void should_accept_graphical_ep_bind_to_a_lane() throws Exception {
        when(ep.resolveSemanticElement()).thenReturn(aLane().build());
        
        assertThat(formMappingSectionFilter.select(ep)).isTrue();
    }
    
    @Test
    public void should_accept_graphical_ep_bind_to_a_task() throws Exception {
        when(ep.resolveSemanticElement()).thenReturn(aTask().build());
        
        assertThat(formMappingSectionFilter.select(ep)).isTrue();
    }
    
    @Test
    public void should_reject_graphical_ep_bind_to_a_activity() throws Exception {
        when(ep.resolveSemanticElement()).thenReturn(anActivity().build());
        
        assertThat(formMappingSectionFilter.select(ep)).isFalse();
    }

}


