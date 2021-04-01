/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ConnectorDefinitionAndConfigurationOutputConsistencyConstraintTest {

    private static final ExpressionFactory expressionFactory = ExpressionFactory.eINSTANCE;

    private static final ConnectorDefinitionFactory connectorDefFactory = ConnectorDefinitionFactory.eINSTANCE;

    private static final ConnectorConfigurationFactory connectorConfigFactory = ConnectorConfigurationFactory.eINSTANCE;

    private static final ProcessFactory processFactory = ProcessFactory.eINSTANCE;

    private ConnectorDefinitionAndConfigurationOutputConsistencyConstraint constraintUnderTest;

    @Mock
    private IValidationContext context;

    @Mock
    private ConnectorDefRepositoryStore connectorDefStore;

    @Mock
    private ActorFilterDefRepositoryStore actorFilterDefStore;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        constraintUnderTest = spy(new ConnectorDefinitionAndConfigurationOutputConsistencyConstraint());
        when(context.createSuccessStatus()).thenReturn(Status.OK_STATUS);
        when(context.createFailureStatus(anyObject())).thenReturn(new Status(IStatus.ERROR, "unknown", ""));
        doReturn(connectorDefStore).when(constraintUnderTest).getConnectorDefinitionRepositoryStore();
        doReturn(actorFilterDefStore).when(constraintUnderTest).getActorFilterDefinitionStore();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    private ConnectorParameter createConnectorParameter(final String inputName) {
        final ConnectorParameter connectorParameter = connectorConfigFactory.createConnectorParameter();
        connectorParameter.setKey(inputName);
        connectorParameter.setExpression(null);
        return connectorParameter;
    }

    private Input createInput(final String name, final String returnType) {
        final Input input = connectorDefFactory.createInput();
        input.setName(name);
        input.setType(returnType);
        return input;
    }

    @Test
    public void shouldCheckOutputConsistency_ReturnSuccesStatus() throws Exception {
        final Connector connector = processFactory.createConnector();
        connector.setDefinitionId("myDef");
        connector.setDefinitionVersion("1.0.0");
        connector.getOutputs().add(createOutputOperation("myData", "myOutput"));

        final ConnectorDefinition def = connectorDefFactory.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getOutput().add(createOutput("myOutput", String.class.getName()));
        assertThat(constraintUnderTest.checkOutputConsistency(connector, def, context).isOK()).isTrue();
    }

    @Test
    public void shouldCheckOutputConsistency_ReturnFailureStatusIfOutputRemoved() throws Exception {
        final Connector connector = processFactory.createConnector();
        connector.setDefinitionId("myDef");
        connector.setDefinitionVersion("1.0.0");
        connector.getOutputs().add(createOutputOperation("myData", "myOutput"));

        final ConnectorDefinition def = connectorDefFactory.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        assertThat(constraintUnderTest.checkOutputConsistency(connector, def, context).isOK()).isFalse();
    }

    @Test
    public void shouldCheckOutputConsistency_ReturnFailureStatusIfOutputRenamed() throws Exception {
        final Connector connector = processFactory.createConnector();
        connector.setDefinitionId("myDef");
        connector.setDefinitionVersion("1.0.0");
        connector.getOutputs().add(createOutputOperation("myData", "myOutput"));

        final ConnectorDefinition def = connectorDefFactory.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getOutput().add(createOutput("myModifiedOutput", String.class.getName()));
        assertThat(constraintUnderTest.checkOutputConsistency(connector, def, context).isOK()).isFalse();
    }

    private Output createOutput(final String name, final String returnType) {
        final Output connectorOutput = connectorDefFactory.createOutput();
        connectorOutput.setName(name);
        connectorOutput.setType(returnType);
        return connectorOutput;
    }

    private Operation createOutputOperation(final String leftOperand, final String rightOperand) {
        final Operation operation = expressionFactory.createOperation();
        operation.setLeftOperand(createExpression(leftOperand));
        operation.setRightOperand(createConnectotOutputExpression(rightOperand));
        return operation;
    }

    private Expression createExpression(final String leftOperand) {
        final Expression expression = expressionFactory.createExpression();
        expression.setContent(leftOperand);
        return expression;
    }

    private Expression createConnectotOutputExpression(final String content) {
        final Expression expression = expressionFactory.createExpression();
        expression.setContent(content);
        final Output connectorOutput = createOutput(content, String.class.getName());
        expression.getReferencedElements().add(connectorOutput);
        return expression;
    }

    @Test
    public void shouldPerformBatchValidation_ReturnSuccessStatusForConnector() throws Exception {
        final Connector connector = processFactory.createConnector();
        final ConnectorConfiguration config = connectorConfigFactory.createConnectorConfiguration();
        config.setDefinitionId("myDef");
        config.setVersion("1.0.0");
        config.getParameters().add(createConnectorParameter("input1"));
        config.getParameters().add(createConnectorParameter("input2"));
        connector.setConfiguration(config);
        connector.getOutputs().add(createOutputOperation("myData", "success"));
        when(context.getTarget()).thenReturn(connector);

        final ConnectorDefinition def = connectorDefFactory.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName()));
        def.getInput().add(createInput("input2", Boolean.class.getName()));
        def.getOutput().add(createOutput("success", Boolean.class.getName()));
        when(connectorDefStore.getDefinition("myDef", "1.0.0")).thenReturn(def);
        assertThat(constraintUnderTest.performBatchValidation(context).isOK()).isTrue();
    }

    @Test
    public void shouldPerformBatchValidation_ReturnFailureStatusForConnectorWithModifiedOutput() throws Exception {
        final Connector connector = processFactory.createConnector();
        final ConnectorConfiguration config = connectorConfigFactory.createConnectorConfiguration();
        config.setDefinitionId("myDef");
        config.setVersion("1.0.0");
        config.getParameters().add(createConnectorParameter("input1"));
        config.getParameters().add(createConnectorParameter("input2"));
        connector.setConfiguration(config);
        connector.getOutputs().add(createOutputOperation("myData", "success"));
        when(context.getTarget()).thenReturn(connector);

        final ConnectorDefinition def = connectorDefFactory.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName()));
        def.getInput().add(createInput("input2", Boolean.class.getName()));
        def.getOutput().add(createOutput("newSuccess", Boolean.class.getName()));
        when(connectorDefStore.getDefinition("myDef", "1.0.0")).thenReturn(def);
        assertThat(constraintUnderTest.performBatchValidation(context).isOK()).isFalse();
    }

    @Test
    public void shouldPerformBatchValidation_UseActorFilterDefStore() throws Exception {
        final Connector connector = processFactory.createActorFilter();
        final ConnectorConfiguration config = connectorConfigFactory.createConnectorConfiguration();
        config.setDefinitionId("myDef");
        config.setVersion("1.0.0");
        when(context.getTarget()).thenReturn(connector);
        constraintUnderTest.performBatchValidation(context);
        verify(constraintUnderTest).getActorFilterDefinitionStore();
    }

}
