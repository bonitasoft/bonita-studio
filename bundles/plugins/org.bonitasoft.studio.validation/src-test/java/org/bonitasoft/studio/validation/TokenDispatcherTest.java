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
package org.bonitasoft.studio.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.StartEventBuilder.aStartEvent;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.builders.TaskBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class TokenDispatcherTest {

    private TokenDispatcher tokenDispatcher;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        tokenDispatcher = new TokenDispatcher();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_a_XORGateway_propagate_token_continously() throws Exception {
        assertThat(tokenDispatcher.isContinuous(ProcessFactory.eINSTANCE.createXORGateway())).isTrue();
    }

    @Test
    public void should_a_FlowElement_with_be_continous() throws Exception {
        final TaskBuilder t1 = aTask();
        final TaskBuilder t2 = aTask();
        t1.goingTo(t2).goingTo(t1);

        aPool()
                .havingElements(aStartEvent().goingTo(t1), t1, t2).build();

        assertThat(tokenDispatcher.isMerging(t1.build())).isFalse();
        assertThat(tokenDispatcher.isMerging(t2.build())).isFalse();

        assertThat(tokenDispatcher.isContinuous(t1.build())).isTrue();
        assertThat(tokenDispatcher.isContinuous(t2.build())).isTrue();
    }

}
