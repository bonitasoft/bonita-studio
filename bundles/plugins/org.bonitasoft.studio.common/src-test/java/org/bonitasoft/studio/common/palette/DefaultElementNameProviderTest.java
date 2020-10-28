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
package org.bonitasoft.studio.common.palette;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.builders.ActivityBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class DefaultElementNameProviderTest {

    private DefaultElementNameProvider defaultElementNameProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        defaultElementNameProvider = new DefaultElementNameProvider();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getNameFor_a_process_element() throws Exception {
        assertThat(defaultElementNameProvider.getNameFor(ActivityBuilder.anActivity().build())).isEqualTo(Messages.StepDefaultName);
        final FlowElement flowElement = ProcessFactory.eINSTANCE.createFlowElement();
        assertThat(defaultElementNameProvider.getNameFor(flowElement)).isEqualTo(flowElement.eClass().getName());
    }

}
