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
package org.bonitasoft.studio.exporter.bpmn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;

import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AddBpmnBarResourceRunnableTest {

    @Mock
    AddBpmnBarResourceRunnable runnable;
    @Mock
    Diagram diagramFor;
    @Mock
    Resource resource;
    @Mock
    MainProcessEditPart mped1, mped2;

    @Before
    public void setup() {
        doCallRealMethod().when(runnable).findOrCreateMainProcessEditPart(any(Diagram.class));
        doReturn(mped2).when(runnable).createMainEditPart(diagramFor, resource);
        doReturn(resource).when(diagramFor).eResource();
    }

    @Test
    public void testFindOrCreateMainProcessEditPartWithEditorOpened() {
        doReturn(mped1).when(runnable).findInOpenedEditor(resource);

        assertThat(runnable.findOrCreateMainProcessEditPart(diagramFor)).isEqualTo(mped1);
    }

    @Test
    public void testFindOrCreateMainProcessEditPartWithNoEditorOpened() {
        doReturn(null).when(runnable).findInOpenedEditor(resource);

        assertThat(runnable.findOrCreateMainProcessEditPart(diagramFor)).isEqualTo(mped2);
    }

}
