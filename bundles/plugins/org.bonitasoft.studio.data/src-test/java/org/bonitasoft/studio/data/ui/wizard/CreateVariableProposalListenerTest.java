/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.data.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.builders.ActivityBuilder;
import org.bonitasoft.studio.model.process.builders.PoolBuilder;
import org.bonitasoft.studio.model.process.builders.ReceiveTaskBuilder;
import org.bonitasoft.studio.model.process.builders.SendTaskBuilder;
import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class CreateVariableProposalListenerTest {

    private CreateVariableProposalListener createVariableProposalListener;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        createVariableProposalListener = new CreateVariableProposalListener();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getDataContainer_return_process_if_in_a_send_task() throws Exception {
        final SendTaskBuilder sendTask = SendTaskBuilder.aSendTask();
        PoolBuilder.aPool().havingElements(sendTask).build();
        final EObject dataContainer = createVariableProposalListener.getDataContainer(sendTask.build());
        assertThat(dataContainer).isInstanceOf(Pool.class);
    }

    @Test
    public void should_getDataContainer_return_process_if_in_a_receive_task() throws Exception {
        final ReceiveTaskBuilder receiveTask = ReceiveTaskBuilder.createReceiveTaskBuilder();
        PoolBuilder.aPool().havingElements(receiveTask).build();
        final EObject dataContainer = createVariableProposalListener.getDataContainer(receiveTask.build());
        assertThat(dataContainer).isInstanceOf(Pool.class);
    }

    @Test
    public void should_getDataContainer_return_activity_if_in_an_activity() throws Exception {
        final EObject dataContainer = createVariableProposalListener.getDataContainer(ActivityBuilder.anActivity().build());
        assertThat(dataContainer).isInstanceOf(Activity.class);
    }

    @Test
    public void should_getDataContainer_return_pool_if_at_pool_level() throws Exception {
        final EObject dataContainer = createVariableProposalListener.getDataContainer(PoolBuilder.aPool().build());
        assertThat(dataContainer).isInstanceOf(Pool.class);
    }

}
