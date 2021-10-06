/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.definition.migration.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.expression.builders.OperationBuilder.anOperation;
import static org.bonitasoft.studio.model.process.builders.ConnectorBuilder.aConnector;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.model.process.Connector;
import org.junit.Test;

public class OutputTypeChangeTest {

    @Test
    public void should_not_be_a_breaking_change_if_output_is_Not_referenced_in_connector_outputs() throws Exception {
        OutputTypeChange change = new OutputTypeChange("myOutput", null, Boolean.class.getName());

        assertThat(change.isBreakingChange(aConnector().build())).isFalse();
    }

    @Test
    public void should_be_a_breaking_change_if_output_is_referenced_in_connector_outputs() throws Exception {
        Output output = ConnectorDefinitionFactory.eINSTANCE.createOutput();
        output.setName("myOutput");
        OutputTypeChange change = new OutputTypeChange(output.getName(), null, Boolean.class.getName());

        assertThat(change.isBreakingChange(aConnector()
                .havingOutput(anOperation()
                        .havingRightOperand(anExpression()
                                .havingReferencedElements(output)))
                .build())).isTrue();
    }

    @Test
    public void should_update_output_reference_type_when_applying_change() throws Exception {
        // Given
        Output output = ConnectorDefinitionFactory.eINSTANCE.createOutput();
        output.setName("myOutput");
        output.setType(String.class.getName());
        OutputTypeChange change = new OutputTypeChange(output.getName(), null, Boolean.class.getName());
        Connector connector = aConnector()
                .havingOutput(anOperation()
                        .havingRightOperand(anExpression()
                                .havingReferencedElements(output)))
                .build();

        // When
        change.apply(connector);

        // Then
        assertThat(connector.getOutputs().stream().flatMap(op -> op.getRightOperand().getReferencedElements().stream()))
                .extracting("name", "type")
                .containsExactly(tuple("myOutput", Boolean.class.getName()));

    }

}
