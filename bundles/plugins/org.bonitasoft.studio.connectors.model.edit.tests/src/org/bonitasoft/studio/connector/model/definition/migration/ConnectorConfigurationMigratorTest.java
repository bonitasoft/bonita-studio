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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.DocumentRoot;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.definition.util.ConnectorDefinitionXMLProcessor;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.util.ConnectorConfigurationXMLProcessor;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

public class ConnectorConfigurationMigratorTest {

    private ConnectorDefinitionXMLProcessor defXmlProcessor = new ConnectorDefinitionXMLProcessor();
    private ConnectorConfigurationXMLProcessor configXmlProcessor = new ConnectorConfigurationXMLProcessor();
    private ConnectorConfigurationMigratorFactory migratorFactory;

    @Before
    public void createFactory() throws Exception {
        migratorFactory = new ConnectorConfigurationMigratorFactory(new ConnectorDefinitionComparator(new DefaultValueExpressionFactory()));
    }

    @Test
    public void should_update_configuration_definition_version() throws Exception {
        // Given
        ConnectorConfiguration configuration = loadConfiguration("/email-1.0.0.connectorconfig");
        ConnectorDefinition currentDefinition = loadDefinition("/email-1.0.0.def");
        ConnectorDefinition newDefinition = loadDefinition("/email-1.2.0.def");

        // When 
        migratorFactory.create(currentDefinition, newDefinition).migrate(configuration);

        // Then 
        assertThat(configuration.getVersion()).isEqualTo("1.2.0");
    }

    public void should_update_connector_definition_version() throws Exception {
        // Given
        ConnectorConfiguration configuration = loadConfiguration("/email-1.0.0.connectorconfig");
        Connector c = ProcessFactory.eINSTANCE.createConnector();
        c.setConfiguration(configuration);
        ConnectorDefinition currentDefinition = loadDefinition("/email-1.0.0.def");
        ConnectorDefinition newDefinition = loadDefinition("/email-1.2.0.def");

        // When 
        migratorFactory.create(currentDefinition, newDefinition).migrate(configuration);

        // Then 
        assertThat(c.getDefinitionVersion()).isEqualTo("1.2.0");
    }

    @Test
    public void should_update_configuration_parameters_with_new_parameters() throws Exception {
        // Given
        ConnectorConfiguration configuration = loadConfiguration("/email-1.0.0.connectorconfig");
        ConnectorDefinition currentDefinition = loadDefinition("/email-1.0.0.def");
        ConnectorDefinition newDefinition = loadDefinition("/email-1.2.0.def");
        assertThat(configuration.getParameters())
                .extracting("key")
                .doesNotContain("returnPath", "trustCertificate");

        // When 
        migratorFactory.create(currentDefinition, newDefinition).migrate(configuration);

        // Then 
        assertThat(configuration.getParameters())
                .extracting("key")
                .contains("returnPath", "trustCertificate");
    }

    @Test
    public void should_update_configuration_parameters_and_remove_old_parameters() throws Exception {
        // Given
        ConnectorConfiguration configuration = loadConfiguration("/email-1.0.0.connectorconfig");
        ConnectorDefinition currentDefinition = loadDefinition("/email-1.0.0.def");
        ConnectorDefinition newDefinition = loadDefinition("/fake-email-1.0.1.def");
        assertThat(configuration.getParameters())
                .extracting("key")
                .contains("to");

        // When 
        migratorFactory.create(currentDefinition, newDefinition).migrate(configuration);

        // Then 
        assertThat(configuration.getParameters())
                .extracting("key")
                .doesNotContain("to");
    }

    @Test
    public void should_update_configuration_parameters_and_change_expression_returnType() throws Exception {
        // Given
        ConnectorConfiguration configuration = loadConfiguration("/email-1.0.0.connectorconfig");
        ConnectorDefinition currentDefinition = loadDefinition("/email-1.0.0.def");
        ConnectorDefinition newDefinition = loadDefinition("/fake-email-1.0.1.def");
        assertThat(configuration.getParameters())
                .extracting("key")
                .contains("to");

        // When 
        migratorFactory.create(currentDefinition, newDefinition).migrate(configuration);

        // Then 
        assertThat(configuration.getParameters().stream()
                .filter(cp -> "smtpPort".equals(cp.getKey()))
                .findFirst())
                        .hasValueSatisfying(cp -> ExpressionAssert.assertThat((Expression) cp.getExpression())
                                .hasReturnType(Double.class.getName()));
    }

    @Test
    public void should_update_configuration_parameters_and_change_expression_kind() throws Exception {
        // Given
        ConnectorConfiguration configuration = loadConfiguration("/email-1.0.0.connectorconfig");
        ConnectorDefinition currentDefinition = loadDefinition("/email-1.0.0.def");
        ConnectorDefinition newDefinition = loadDefinition("/fake-email-1.0.1.def");
        assertThat(configuration.getParameters())
                .extracting("key")
                .contains("headers");

        // When 
        migratorFactory.create(currentDefinition, newDefinition).migrate(configuration);

        // Then 
        assertThat(configuration.getParameters().stream()
                .filter(cp -> "headers".equals(cp.getKey()))
                .findFirst())
                        .hasValueSatisfying(cp -> ExpressionAssert.assertThat((Expression) cp.getExpression())
                                .hasReturnType(List.class.getName()));
    }

    @Test
    public void should_update_connector_output_operations_when_output_type_changed() throws Exception {
        // Given
        ConnectorDefinition currentDefinition = createDefinition("custom-connector", "1.0");
        Output output = createOutput("myOutput", String.class.getName());
        currentDefinition.getOutput().add(output);
        ConnectorConfiguration configuration = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        configuration.setDefinitionId("custom-connector");
        configuration.setVersion("1.0");
        Connector connector = ProcessFactory.eINSTANCE.createConnector();
        connector.setDefinitionId("custom-connector");
        connector.setDefinitionVersion("1.0");
        connector.setConfiguration(configuration);
        Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        Expression rightOperand = ExpressionHelper.createConnectorOutputExpression(output);
        operation.setRightOperand(rightOperand);
        connector.getOutputs().add(operation);

        ConnectorDefinition newDefinition = createDefinition("custom-connector", "1.1");
        Output newOutput = createOutput("myOutput", Integer.class.getName());
        newDefinition.getOutput().add(newOutput);

        // When 
        migratorFactory.create(currentDefinition, newDefinition).migrate(configuration);

        // Then 
        Expression outputExpression = connector.getOutputs().get(0).getRightOperand();
        assertThat(outputExpression.getReturnType()).isEqualTo(Integer.class.getName());
        EObject reference = outputExpression.getReferencedElements().get(0);
        assertThat(reference)
                .isInstanceOf(Output.class);
        assertThat(((Output) reference).getType())
                .isEqualTo(Integer.class.getName());
    }
    
    @Test
    public void should_update_connector_output_operations_when_output_removed() throws Exception {
        // Given
        ConnectorDefinition currentDefinition = createDefinition("custom-connector", "1.0");
        Output output = createOutput("myOutput", String.class.getName());
        currentDefinition.getOutput().add(output);
        ConnectorConfiguration configuration = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        configuration.setDefinitionId("custom-connector");
        configuration.setVersion("1.0");
        Connector connector = ProcessFactory.eINSTANCE.createConnector();
        connector.setDefinitionId("custom-connector");
        connector.setDefinitionVersion("1.0");
        connector.setConfiguration(configuration);
        Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        Expression rightOperand = ExpressionHelper.createConnectorOutputExpression(output);
        operation.setRightOperand(rightOperand);
        connector.getOutputs().add(operation);

        ConnectorDefinition newDefinition = createDefinition("custom-connector", "1.1");

        // When 
        migratorFactory.create(currentDefinition, newDefinition).migrate(configuration);

        // Then 
        assertThat(connector.getOutputs()).isEmpty();
    }

    private Output createOutput(String name, String type) {
        Output output = ConnectorDefinitionFactory.eINSTANCE.createOutput();
        output.setName(name);
        output.setType(type);
        return output;
    }

    private ConnectorDefinition createDefinition(String id, String version) {
        ConnectorDefinition connectorDefinition = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        connectorDefinition.setId(id);
        connectorDefinition.setVersion(version);
        return connectorDefinition;
    }

    private ConnectorConfiguration loadConfiguration(String resourcePath) throws IOException {
        try (InputStream is = ConnectorConfigurationMigratorTest.class.getResourceAsStream(resourcePath)) {
            Resource emfResource = configXmlProcessor.load(is, Collections.emptyMap());
            return (ConnectorConfiguration) emfResource.getContents().get(0);
        }
    }

    private ConnectorDefinition loadDefinition(String resourcePath) throws IOException {
        try (InputStream is = ConnectorConfigurationMigratorTest.class.getResourceAsStream(resourcePath)) {
            Resource emfResource = defXmlProcessor.load(is, Collections.emptyMap());
            return ((DocumentRoot) emfResource.getContents().get(0)).getConnectorDefinition();
        }
    }

}
