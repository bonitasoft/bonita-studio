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
package org.bonitasoft.studio.common.gmf.tools.tree;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorParameterBuilder.aConnectorParameter;

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class ParametersViewFilterTest {

    private ParametersViewFilter parametersViewFilter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        parametersViewFilter = new ParametersViewFilter();
    }

    @Test
    public void should_not_select_connector_parameters() throws Exception {
        //Given
        final ConnectorParameter parameter = aConnectorParameter().build();

        //When
        final boolean isSelected = parametersViewFilter.select(null, null, parameter);

        //Then
        assertThat(isSelected).isFalse();
    }

    @Test
    public void should_select_everything_else() throws Exception {
        //When Then
        assertThat(parametersViewFilter.select(null, null, null)).isTrue();
    }
}
