/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.gmf.tools.convert;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.builders.MainProcessBuilder;
import org.bonitasoft.studio.model.process.builders.PoolBuilder;
import org.bonitasoft.studio.model.process.builders.SendTaskBuilder;
import org.bonitasoft.studio.model.process.builders.SequenceFlowBuilder;
import org.bonitasoft.studio.model.process.builders.TaskBuilder;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RemoveBoundaryWithItsFlowsTest {

    @Mock
    private InternalTransactionalEditingDomain domain;

    @Test
    public void testBoundaryRemovedWithItsUniqueExceptionFlow() throws Exception {
        final TaskBuilder taskTargetOfExceptionFlow = TaskBuilder.aTask().withName("Targeted Task of exception flow");
        final TaskBuilder taskWithABoundary = TaskBuilder.aTask().withName("Task with Boundary");
        final Pool pool = PoolBuilder.aPool().havingElements(taskWithABoundary, taskTargetOfExceptionFlow).build();
        final Activity boundaryHolder = (Activity) pool.getElements().get(0);
        final Activity boundaryTarget = (Activity) pool.getElements().get(1);
        final BoundaryEvent boundaryToRemove = ProcessFactory.eINSTANCE.createBoundaryEvent();
        boundaryHolder.getBoundaryIntermediateEvents().add(boundaryToRemove);
        pool.getConnections().add(SequenceFlowBuilder.aSequenceFlow().havingSource(boundaryToRemove).havingTarget(boundaryTarget).build());

        new RemoveBoundaryWithItsFlows(domain, boundaryToRemove, boundaryHolder).doExecute(null, null);

        Assertions.assertThat(pool.getConnections()).isEmpty();
        Assertions.assertThat(boundaryTarget.getIncoming()).isEmpty();
    }

    @Test
    public void testBoundaryRemovedWithSeveralExceptionFlow() throws Exception {
        final TaskBuilder firsTaskTargetOfExceptionFlow = TaskBuilder.aTask().withName("First Targeted Task of exception flow");
        final TaskBuilder secondTargetOfExceptionFlow = TaskBuilder.aTask().withName("Second Targeted Task of exception flow");
        final TaskBuilder taskWithABoundary = TaskBuilder.aTask().withName("Task with Boundary");
        final Pool pool = PoolBuilder.aPool().havingElements(taskWithABoundary, firsTaskTargetOfExceptionFlow, secondTargetOfExceptionFlow).build();
        final Activity boundaryHolder = (Activity) pool.getElements().get(0);
        final Activity firstBoundaryTarget = (Activity) pool.getElements().get(1);
        final Activity secondBoundaryTarget = (Activity) pool.getElements().get(2);
        final BoundaryEvent boundaryToRemove = ProcessFactory.eINSTANCE.createBoundaryEvent();
        boundaryHolder.getBoundaryIntermediateEvents().add(boundaryToRemove);
        pool.getConnections().add(SequenceFlowBuilder.aSequenceFlow().havingSource(boundaryToRemove).havingTarget(firstBoundaryTarget).build());
        pool.getConnections().add(SequenceFlowBuilder.aSequenceFlow().havingSource(boundaryToRemove).havingTarget(secondBoundaryTarget).build());

        new RemoveBoundaryWithItsFlows(domain, boundaryToRemove, boundaryHolder).doExecute(null, null);

        Assertions.assertThat(pool.getConnections()).isEmpty();
        Assertions.assertThat(firstBoundaryTarget.getIncoming()).isEmpty();
        Assertions.assertThat(secondBoundaryTarget.getIncoming()).isEmpty();
    }

    @Test
    public void testBoundaryMessageRemovedWithMessageFlow() throws Exception {
        final SendTaskBuilder taskSourceOfMessageFlow = SendTaskBuilder.aSendTask().withName("Source Task of message flow");
        final TaskBuilder taskWithABoundary = TaskBuilder.aTask().withName("Task with Boundary Message");
        final MainProcess diagram = MainProcessBuilder.aMainProcess()
                .havingElements(
                        PoolBuilder.aPool().havingElements(taskWithABoundary),
                        PoolBuilder.aPool().havingElements(taskSourceOfMessageFlow)).build();
        final Pool poolContainingBoundary = (Pool) diagram.getElements().get(0);
        final Activity boundaryHolder = (Activity) poolContainingBoundary.getElements().get(0);
        final BoundaryMessageEvent boundaryToRemove = ProcessFactory.eINSTANCE.createBoundaryMessageEvent();
        boundaryHolder.getBoundaryIntermediateEvents().add(boundaryToRemove);

        final Pool poolContainingSourceMessagFlow = (Pool) diagram.getElements().get(1);
        final SendTask messageFlowSource = (SendTask) poolContainingSourceMessagFlow.getElements().get(0);
        final MessageFlow messageFlow = ProcessFactory.eINSTANCE.createMessageFlow();
        messageFlow.setSource(messageFlowSource);
        messageFlow.setTarget(boundaryToRemove);
        messageFlowSource.getOutgoingMessages().add(messageFlow);

        new RemoveBoundaryWithItsFlows(domain, boundaryToRemove, boundaryHolder).doExecute(null, null);

        Assertions.assertThat(poolContainingBoundary.getConnections()).isEmpty();
        Assertions.assertThat(messageFlowSource.getOutgoingMessages()).isEmpty();
    }

}
