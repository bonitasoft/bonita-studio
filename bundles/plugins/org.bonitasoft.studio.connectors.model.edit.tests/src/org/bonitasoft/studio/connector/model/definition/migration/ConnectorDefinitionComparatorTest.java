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
package org.bonitasoft.studio.connector.model.definition.migration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorParameterBuilder.aConnectorParameter;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aConstantExpression;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.DocumentRoot;
import org.bonitasoft.studio.connector.model.definition.migration.model.InputTypeChange;
import org.bonitasoft.studio.connector.model.definition.migration.model.NewOutputChange;
import org.bonitasoft.studio.connector.model.definition.util.ConnectorDefinitionXMLProcessor;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

public class ConnectorDefinitionComparatorTest {

    private ConnectorDefinitionXMLProcessor xmlProcessor = new ConnectorDefinitionXMLProcessor();
    private ConnectorDefinitionComparator comparator;

    @Before
    public void createFixture() throws Exception {
        comparator = new ConnectorDefinitionComparator(new DefaultValueExpressionFactory());
    }

    @Test
    public void should_collect_added_inputs() throws Exception {
        // Given
        ConnectorDefinition reference = loadDefinition("/email-1.0.0.def");
        assertThat(reference).isNotNull();
        ConnectorDefinition latest = loadDefinition("/email-1.2.0.def");
        assertThat(latest).isNotNull();

        // When
        var visitor = comparator.compare(latest, reference);

        // Then
        assertThat(visitor.getInputChanges()).extracting("newInput.name", "newInput.type")
                .contains(tuple("trustCertificate", Boolean.class.getName()),
                        tuple("returnPath", String.class.getName()));
    }

    @Test
    public void should_collect_changed_input_type() throws Exception {
        // Given
        ConnectorDefinition reference = loadDefinition("/email-1.0.0.def");
        assertThat(reference).isNotNull();
        ConnectorDefinition latest = loadDefinition("/fake-email-1.0.1.def");
        assertThat(latest).isNotNull();

        // When
        var visitor = comparator.compare(latest, reference);

        // Then
        assertThat(visitor.getInputChanges())
                .filteredOn(InputTypeChange.class::isInstance)
                .extracting("inputName", "type")
                .contains(tuple("smtpPort", Double.class.getName()));
    }

    @Test
    public void should_collect_added_output() throws Exception {
        // Given
        ConnectorDefinition reference = loadDefinition("/email-1.0.0.def");
        assertThat(reference).isNotNull();
        ConnectorDefinition latest = loadDefinition("/fake-email-1.0.1.def");
        assertThat(latest).isNotNull();

        // When
        var visitor = comparator.compare(latest, reference);

        // Then
        assertThat(visitor.getOutputChanges())
                .filteredOn(NewOutputChange.class::isInstance)
                .extracting("newOutput.name", "newOutput.type")
                .contains(tuple("sent", Boolean.class.getName()));
    }

    @Test
    public void should_create_changes_even_when_definition_version_matches() throws Exception {
        // Given
        ConnectorDefinition reference = loadDefinition("/email-1.0.0.def");
        assertThat(reference).isNotNull();
        ConnectorDefinition latest = loadDefinition("/custom-email-1.0.0.def");
        assertThat(latest).isNotNull();

        // When
        var diff = comparator.compare(latest, reference);
        
        // Then
        assertThat(diff.getInputChanges()).isEmpty();
        assertThat(diff.getOutputChanges()).isEmpty();
    }

    @Test
    public void should_collect_breaking_changes() throws Exception {
        // Given
        ConnectorDefinition reference = loadDefinition("/email-1.0.0.def");
        assertThat(reference).isNotNull();
        ConnectorDefinition latest = loadDefinition("/fake-email-1.0.1.def");
        assertThat(latest).isNotNull();

        // When
        var visitor = comparator.compare(latest, reference);

        // Then
        ConnectorConfiguration configuration = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        assertThat(visitor.hasInputBreakingChanges(configuration)).isTrue();
        configuration.getParameters().add(aConnectorParameter().withKey("userName")
                .havingExpression(aConstantExpression().withContent("romain")).build());
        assertThat(visitor.hasInputBreakingChanges(configuration)).isFalse();
    }

    private ConnectorDefinition loadDefinition(String resourcePath) throws IOException {
        try (InputStream is = ConnectorDefinitionComparatorTest.class.getResourceAsStream(resourcePath)) {
            Resource emfResource = xmlProcessor.load(is, Collections.emptyMap());
            return ((DocumentRoot) emfResource.getContents().get(0)).getConnectorDefinition();
        }
    }

}
