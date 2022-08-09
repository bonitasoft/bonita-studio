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
package org.bonitasoft.studio.common.emf.tools;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class DiagramForElementRunnableTest {


    private Resource resource;

    private MainProcess element;

    private DiagramForElementRunnable diagramForElementRunnable;

    private Diagram diagram;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        resource = new ResourceImpl();
        element = ProcessFactory.eINSTANCE.createMainProcess();
        element.setName("Diagram1");
        diagram = NotationFactory.eINSTANCE.createDiagram();
        diagram.setElement(element);
        resource.getContents().add(element);
        resource.getContents().add(diagram);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_run_find_the_element_corresponding_diagram() throws Exception {
        diagramForElementRunnable = new DiagramForElementRunnable(resource, element);
        diagramForElementRunnable.run();
        assertThat(diagramForElementRunnable.getResult()).isEqualTo(diagram);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_run_throw_an_IllegalArgumentException_for_null_element() throws Exception {
        diagramForElementRunnable = new DiagramForElementRunnable(resource, null);
        diagramForElementRunnable.run();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_run_throw_an_IllegalArgumentException_for_null_resource() throws Exception {
        diagramForElementRunnable = new DiagramForElementRunnable(null, element);
        diagramForElementRunnable.run();
    }

    @Test
    public void should_run_return_null_if_no_diagram_found_for_given_element() throws Exception {
        diagramForElementRunnable = new DiagramForElementRunnable(resource, ProcessFactory.eINSTANCE.createMainProcess());
        diagramForElementRunnable.run();
        assertThat(diagramForElementRunnable.getResult()).isNull();
    }

}
