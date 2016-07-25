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
package org.bonitasoft.studio.connector.model.implementation.wizard;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class ImplementationJarFilterTest {

    private ImplementationJarFilter filter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final ConnectorImplementation impl = ConnectorImplementationFactory.eINSTANCE.createConnectorImplementation();
        impl.setImplementationId("test-impl");
        impl.setImplementationVersion("1.2.0");
        filter = new ImplementationJarFilter(impl);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_select_returns_false_for_implementation_jarname_true_otherwise() throws Exception {
        assertThat(filter.select(null, null, "test-impl-1.2.0.jar")).isFalse();
        assertThat(filter.select(null, null, "toto.jar")).isTrue();
        assertThat(filter.select(null, null, null)).isTrue();
    }

}
