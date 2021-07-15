/**
 * Copyright (C) 2009-2013 BonitaSoft S.A.
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

package org.bonitasoft.studio.diagram.custom.clipboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Florine Boudin
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomPasteCommandTest {

    private CustomPasteCommand customPasteCommand;

    @Mock
    private IGraphicalEditPart editPart;

    private MainProcess diagram;

    private Pool pool;

    private Task task;
    private Task taskInLane;

    private Pool targetPool;

    private Pool poolWithLane;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        diagram = ProcessFactory.eINSTANCE.createMainProcess();

        // source Pool with original Task and its initiator Actor
        pool = ProcessFactory.eINSTANCE.createPool();
        final Actor initiatorActor = createActor("myActor", true);
        pool.getActors().add(initiatorActor);
        task = ProcessFactory.eINSTANCE.createTask();
        task.setActor(initiatorActor);
        pool.getElements().add(task);
        diagram.getElements().add(pool);

        // source pool with original tasks that use Lane actor as Actor
        taskInLane = ProcessFactory.eINSTANCE.createTask();
        taskInLane.setOverrideActorsOfTheLane(true);
        final Lane lane = ProcessFactory.eINSTANCE.createLane();
        final Actor actorLane = createActor("ActorLane", true);
        lane.setActor(actorLane);
        lane.getElements().add(taskInLane);
        poolWithLane = ProcessFactory.eINSTANCE.createPool();
        poolWithLane.getElements().add(lane);
        diagram.getElements().add(poolWithLane);

        // target Pool when to copy the original Task
        targetPool = ProcessFactory.eINSTANCE.createPool();
        diagram.getElements().add(targetPool);

        // mock behaviour
        when(editPart.resolveSemanticElement()).thenReturn(targetPool);

        // create the command
        customPasteCommand = new CustomPasteCommand("Paste", editPart);

    }

    private Actor createActor(final String actorName, final boolean isInitiator) {
        final Actor actor = ProcessFactory.eINSTANCE.createActor();
        actor.setName(actorName);
        actor.setInitiator(isInitiator);
        return actor;
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link org.bonitasoft.studio.diagram.custom.clipboard.CustomPasteCommand#copyOnePart(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart, java.util.List)}
     * .
     */
    @Test
    public void shouldCopyActorsAndActorsMapping_DisableProcessInitiator() throws Exception {
        final Task taskCopy = EcoreUtil.copy(task);
        customPasteCommand.copyActorsAndActorsMapping(task, taskCopy, diagram, targetPool);
        assertThat(taskCopy.getActor().isInitiator()).isFalse();
    }

    @Test
    public void should_CopyActorFromLaneToAnotherPool_DisableProcessInitiator() throws Exception {
        final Task taskCopy = EcoreUtil.copy(taskInLane);
        customPasteCommand.copyActorsAndActorsMapping(taskInLane, taskCopy, diagram, targetPool);
        assertThat(taskCopy.getActor().isInitiator()).isFalse();
    }

}
