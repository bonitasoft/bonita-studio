/**
 * Copyright (C) 2016 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.gmf.tools.tree;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.junit.Test;

public class SimulationViewerFilterTest {

    @Test
    public void should_filter_simulation_package_object() throws Exception {
        SimulationViewerFilter filter = new SimulationViewerFilter();

        assertThat(filter.select(null, null, SimulationFactory.eINSTANCE.createDataChange())).isFalse();
    }
    
    @Test
    public void should_not_filter_other_object() throws Exception {
        SimulationViewerFilter filter = new SimulationViewerFilter();

        assertThat(filter.select(null, null, ProcessFactory.eINSTANCE.createActor())).isTrue();
    }

}
