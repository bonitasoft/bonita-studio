/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.model.expression.Expression;
import org.junit.Test;

public class DefaultDatabaseOutputAvailableExpressionTypeFilterTest {

    DefaultDatabaseOutputAvailableExpressionTypeFilter filter = new DefaultDatabaseOutputAvailableExpressionTypeFilter();

    @Test
    public void should_accept_output_with_valid_name() {
        Output element = mock(Output.class);
        when(element.getName()).thenReturn(DatabaseConnectorConstants.RESULTSET_OUTPUT);
        assertThat(filter.validateResultSetOutput(element)).isTrue();
    }

    @Test
    public void should_refuse_output_with_invalid_name() {
        Output element = mock(Output.class);
        when(element.getName()).thenReturn(DatabaseConnectorConstants.SINGLE_RESULT_OUTPUT);
        assertThat(filter.validateResultSetOutput(element)).isFalse();
    }

    @Test
    public void should_accept_expression_with_valid_name() {
        Expression element = mock(Expression.class);
        when(element.getType()).thenReturn(ExpressionConstants.CONNECTOR_OUTPUT_TYPE);
        when(element.getName()).thenReturn(DatabaseConnectorConstants.RESULTSET_OUTPUT);
        assertThat(filter.validateResultSetOutput(element)).isTrue();
    }

    @Test
    public void should_refuse_expression_with_invalid_name() {
        Expression element = mock(Expression.class);
        when(element.getType()).thenReturn(ExpressionConstants.CONNECTOR_OUTPUT_TYPE);
        when(element.getName()).thenReturn(DatabaseConnectorConstants.ONE_ROW);
        assertThat(filter.validateResultSetOutput(element)).isFalse();
    }

}
