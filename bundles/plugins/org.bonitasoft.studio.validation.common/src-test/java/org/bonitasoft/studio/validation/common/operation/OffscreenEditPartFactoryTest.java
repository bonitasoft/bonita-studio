/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.common.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class OffscreenEditPartFactoryTest {

    private OffscreenEditPartFactory offscreenEditPartFactory;
    @Mock
    private org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory factory;
    @Mock
    private DiagramEditPart dEp;

    @Rule
    public RealmWithDisplay realWithDisplay = new RealmWithDisplay();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        offscreenEditPartFactory = spy(new OffscreenEditPartFactory(factory));
    }

    @Before
    public void tearDown() throws Exception {
        if (offscreenEditPartFactory != null) {
            offscreenEditPartFactory.dispose();
        }
    }

    @Test
    public void should_create_an_Offscreen_Diagram_EditPart() throws Exception {
        final Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
        diagram.setElement(ProcessFactory.eINSTANCE.createMainProcess());

        doReturn(dEp).when(factory).createDiagramEditPart(eq(diagram), notNull(Shell.class));

        final DiagramEditPart offscreenDiagramEditPart = offscreenEditPartFactory.createOffscreenDiagramEditPart(diagram);

        assertThat(offscreenDiagramEditPart).isEqualTo(dEp);
    }

    @Test
    public void should_run_in_UI_thread() throws Exception {
        final Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
        diagram.setElement(ProcessFactory.eINSTANCE.createMainProcess());

        doReturn(false).when(offscreenEditPartFactory).inUIThread();
        doReturn(dEp).when(factory).createDiagramEditPart(eq(diagram), notNull(Shell.class));

        final DiagramEditPart offscreenDiagramEditPart = offscreenEditPartFactory.createOffscreenDiagramEditPart(diagram);

        assertThat(offscreenDiagramEditPart).isEqualTo(dEp);
        verify(offscreenEditPartFactory).runInUI(notNull(Runnable.class));
    }

}
