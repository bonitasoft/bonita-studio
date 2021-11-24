package org.bonitasoft.studio.connector.model.definition.migration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.bonitasoft.studio.model.expression.builders.OperationBuilder.anOperation;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.Before;
import org.junit.Test;

public class ConnectorConfigurationToConnectorDefinitionConverterTest {

    private static final String DEFINITION_ID = "defId";
    private static final String DEFINITION_VERSION = "1.0.0";

    private Connector connector;
    private ConnectorConfigurationToConnectorDefinitionConverter converter;

    @Before
    public void init() {
        connector = ProcessFactory.eINSTANCE.createConnector();
        connector.setDefinitionId(DEFINITION_ID);
        connector.setDefinitionVersion(DEFINITION_VERSION);

        var configuration = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        configuration.setDefinitionId(DEFINITION_ID);
        configuration.setVersion(DEFINITION_VERSION);

        connector.setConfiguration(configuration);

        converter = new ConnectorConfigurationToConnectorDefinitionConverter();
    }

    @Test
    public void should_create_a_definition_from_connector() {
        var definition = converter.convert(connector);

        assertThat(definition.getId()).isEqualTo(DEFINITION_ID);
        assertThat(definition.getVersion()).isEqualTo(DEFINITION_VERSION);
    }

    @Test
    public void should_create_an_input_from_connector_parameter_with_expression() {
        var parameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        parameter.setKey("inputName");
        parameter.setExpression(
                ExpressionHelper.createConstantExpression("expressionName", "expressionContent", String.class.getName()));
        connector.getConfiguration().getParameters().add(parameter);

        var definition = converter.convert(connector);

        assertThat(definition.getInput()).extracting("name", "type")
                .containsExactly(tuple("inputName", String.class.getName()));
    }

    @Test
    public void should_create_an_input_from_connector_parameter_with_list_expression() {
        var parameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        parameter.setKey("inputName");

        ListExpression listExpression = ExpressionFactory.eINSTANCE.createListExpression();
        parameter.setExpression(listExpression);
        connector.getConfiguration().getParameters().add(parameter);

        var definition = converter.convert(connector);

        assertThat(definition.getInput()).extracting("name", "type")
                .containsExactly(tuple("inputName", List.class.getName()));
    }

    @Test
    public void should_create_an_input_from_connector_parameter_with_table_expression() {
        var parameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        parameter.setKey("inputName");

        TableExpression tableExpression = ExpressionFactory.eINSTANCE.createTableExpression();
        parameter.setExpression(tableExpression);
        connector.getConfiguration().getParameters().add(parameter);

        var definition = converter.convert(connector);

        assertThat(definition.getInput()).extracting("name", "type")
                .containsExactly(tuple("inputName", List.class.getName()));
    }

    @Test
    public void should_create_an_output_from_connector() {
        var output = ConnectorDefinitionFactory.eINSTANCE.createOutput();
        output.setName("outputName");
        output.setType(String.class.getName());

        connector.getOutputs().add(anOperation()
                .havingRightOperand(ExpressionHelper.createConnectorOutputExpression(output))
                .build());

        var definition = converter.convert(connector);

    }

}
