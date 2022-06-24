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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.bonitasoft.studio.common.repository.provider.ConnectorDefinitionRegistry;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
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
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ConnectorDefinitionAndConfigurationInputConsistencyConstraintTest {


    private ConnectorDefinitionAndConfigurationInputConsistencyConstraint constraintUnderTest;

    @Mock
    private IValidationContext context;

    @Mock
    private ConnectorDefRepositoryStore connectorDefStore;

    @Mock
    private ActorFilterDefRepositoryStore actorFilterDefStore;

    @Mock
    private ConnectorDefinitionRegistry defRegistry;

    @Mock
    private DefinitionResourceProvider defResourceProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        constraintUnderTest = spy(new ConnectorDefinitionAndConfigurationInputConsistencyConstraint());
        when(context.createSuccessStatus()).thenReturn(Status.OK_STATUS);
        when(context.createFailureStatus(any())).thenReturn(new Status(IStatus.ERROR, "unknown", ""));
        doReturn(connectorDefStore).when(constraintUnderTest).getConnectorDefinitionRepositoryStore();
        doReturn(actorFilterDefStore).when(constraintUnderTest).getActorFilterDefinitionStore();
        
        when(defResourceProvider.getConnectorDefinitionRegistry()).thenReturn(defRegistry);
        when(connectorDefStore.getResourceProvider()).thenReturn(defResourceProvider);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldCheckInputConsistency_ReturnOkStatus() throws Exception {
        final ConnectorConfiguration config = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        config.setDefinitionId("myDef");
        config.setVersion("1.0.0");
        config.getParameters().add(createConnectorParameter("input1"));
        config.getParameters().add(createConnectorParameter("input2"));

        final ConnectorDefinition def = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName()));
        def.getInput().add(createInput("input2", Boolean.class.getName()));
        assertThat(constraintUnderTest.checkInputConsistency(config, def, context).isOK()).isTrue();
    }

    @Test
    public void shouldCheckInputConsistency_ReturnFailureStatusForRemovedInput() throws Exception {
        final ConnectorConfiguration config = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        config.setDefinitionId("myDef");
        config.setVersion("1.0.0");
        config.getParameters().add(createConnectorParameter("input1"));
        config.getParameters().add(createConnectorParameter("input2"));

        final ConnectorDefinition def = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName()));
        final IStatus checkInputConsistencyStatus = constraintUnderTest.checkInputConsistency(config, def, context);
        assertThat(checkInputConsistencyStatus.isOK()).isFalse();
    }

    @Test
    public void shouldCheckInputConsistency_ReturnFailureStatusForRenamedInput() throws Exception {
        final ConnectorConfiguration config = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        config.setDefinitionId("myDef");
        config.setVersion("1.0.0");
        config.getParameters().add(createConnectorParameter("input1"));
        config.getParameters().add(createConnectorParameter("input2"));

        final ConnectorDefinition def = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName()));
        def.getInput().add(createInput("input3", Boolean.class.getName()));
        assertThat(constraintUnderTest.checkInputConsistency(config, def, context).isOK()).isFalse();
    }

    @Test
    public void shouldCheckInputConsistency_ReturnFailureStatusForNewMandatoryInput() throws Exception {
        final ConnectorConfiguration config = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        config.setDefinitionId("myDef");
        config.setVersion("1.0.0");
        config.getParameters().add(createConnectorParameter("input1"));
        config.getParameters().add(createConnectorParameter("input2"));

        final ConnectorDefinition def = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName()));
        def.getInput().add(createInput("input2", Boolean.class.getName()));
        def.getInput().add(createInput("input3", String.class.getName(), true));
        assertThat(constraintUnderTest.checkInputConsistency(config, def, context).isOK()).isFalse();
    }

    @Test
    public void shouldCheckInputConsistency_ReturnSuccesStatusForNewOptionalInput() throws Exception {
        final ConnectorConfiguration config = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        config.setDefinitionId("myDef");
        config.setVersion("1.0.0");
        config.getParameters().add(createConnectorParameter("input1"));
        config.getParameters().add(createConnectorParameter("input2"));

        final ConnectorDefinition def = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName()));
        def.getInput().add(createInput("input2", Boolean.class.getName()));
        def.getInput().add(createInput("input3", String.class.getName()));
        assertThat(constraintUnderTest.checkInputConsistency(config, def, context).isOK()).isTrue();
    }

    private ConnectorParameter createConnectorParameter(final String inputName) {
        final ConnectorParameter connectorParameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        connectorParameter.setKey(inputName);
        connectorParameter.setExpression(null);
        return connectorParameter;
    }

    private Input createInput(final String name, final String returnType) {
        final Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        input.setName(name);
        input.setType(returnType);
        return input;
    }

    private Input createInput(final String name, final String returnType, final boolean isMandatory) {
        final Input input = createInput(name, returnType);
        input.setMandatory(isMandatory);
        return input;
    }

    private Output createOutput(final String name, final String returnType) {
        final Output connectorOutput = ConnectorDefinitionFactory.eINSTANCE.createOutput();
        connectorOutput.setName(name);
        connectorOutput.setType(returnType);
        return connectorOutput;
    }

    private Operation createOutputOperation(final String leftOperand, final String rightOperand) {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        operation.setLeftOperand(createExpression(leftOperand));
        operation.setRightOperand(createConnectotOutputExpression(rightOperand));
        return operation;
    }

    private Expression createExpression(final String leftOperand) {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setContent(leftOperand);
        return expression;
    }

    private Expression createConnectotOutputExpression(final String content) {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setContent(content);
        final Output connectorOutput = createOutput(content, String.class.getName());
        expression.getReferencedElements().add(connectorOutput);
        return expression;
    }

    @Test
    public void shouldPerformBatchValidation_ReturnSuccessStatusForConnector() throws Exception {
        final Connector connector = ProcessFactory.eINSTANCE.createConnector();
        final ConnectorConfiguration config = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        config.setDefinitionId("myDef");
        config.setVersion("1.0.0");
        config.getParameters().add(createConnectorParameter("input1"));
        config.getParameters().add(createConnectorParameter("input2"));
        connector.setConfiguration(config);
        connector.getOutputs().add(createOutputOperation("myData", "success"));
        when(context.getTarget()).thenReturn(connector);

        final ConnectorDefinition def = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName()));
        def.getInput().add(createInput("input2", Boolean.class.getName()));
        def.getOutput().add(createOutput("success", Boolean.class.getName()));
        when(defRegistry.find("myDef", "1.0.0")).thenReturn(Optional.of(new ExtendedConnectorDefinition(def, null, null)));
        assertThat(constraintUnderTest.performBatchValidation(context).isOK()).isTrue();
    }

    @Test
    public void shouldPerformBatchValidation_ReturnFailureStatusForConnectorWithModifiedInput() throws Exception {
        final Connector connector = ProcessFactory.eINSTANCE.createConnector();
        final ConnectorConfiguration config = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        config.setDefinitionId("myDef");
        config.setVersion("1.0.0");
        config.getParameters().add(createConnectorParameter("input1"));
        config.getParameters().add(createConnectorParameter("input2"));
        connector.setConfiguration(config);
        connector.getOutputs().add(createOutputOperation("myData", "success"));
        when(context.getTarget()).thenReturn(connector);

        final ConnectorDefinition def = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName()));
        def.getInput().add(createInput("input3", Boolean.class.getName()));
        def.getOutput().add(createOutput("success", Boolean.class.getName()));
        when(defRegistry.find("myDef", "1.0.0")).thenReturn(Optional.of(new ExtendedConnectorDefinition(def, null, null)));
        assertThat(constraintUnderTest.performBatchValidation(context).isOK()).isFalse();
    }

    @Test
    public void shouldPerformBatchValidation_UseActorFilterDefStore() throws Exception {
        final Connector connector = ProcessFactory.eINSTANCE.createActorFilter();
        final ConnectorConfiguration config = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        config.setDefinitionId("myDef");
        config.setVersion("1.0.0");
        when(context.getTarget()).thenReturn(connector);
        constraintUnderTest.performBatchValidation(context);
        verify(constraintUnderTest).getActorFilterDefinitionStore();
    }

    @Test
    public void shouldCheckNewMandatoryInputs_ReturnAStringContainingNewMandatoryInputOnly() throws Exception {
        final Set<String> connectorParamKey = new HashSet<>();
        connectorParamKey.add("input2");
        connectorParamKey.add("input3");

        final ConnectorDefinition def = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName(), true));
        def.getInput().add(createInput("input2", Boolean.class.getName()));
        def.getInput().add(createInput("input3", String.class.getName()));

        assertThat(constraintUnderTest.checkNewMandatoryInputs(def.getInput(), connectorParamKey).toString())
                .contains("input1").doesNotContain("input2")
                .doesNotContain("input3");
    }

    @Test
    public void shouldCheckNewMandatoryInputs_ReturnAnEmptyString() throws Exception {
        final Set<String> connectorParamKey = new HashSet<>();
        connectorParamKey.add("input2");
        connectorParamKey.add("input3");

        final ConnectorDefinition def = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName()));
        def.getInput().add(createInput("input2", Boolean.class.getName(), true));
        def.getInput().add(createInput("input3", String.class.getName()));

        assertThat(constraintUnderTest.checkNewMandatoryInputs(def.getInput(), connectorParamKey).toString()).isEmpty();
    }

    @Test
    public void shouldCheckRemovedInputs_ReturnAnEmptyString() throws Exception {
        final Set<String> connectorParamKey = new HashSet<>();
        connectorParamKey.add("input1");
        connectorParamKey.add("input2");
        connectorParamKey.add("input3");

        final ConnectorDefinition def = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName()));
        def.getInput().add(createInput("input2", Boolean.class.getName(), true));
        def.getInput().add(createInput("input3", String.class.getName()));
        def.getInput().add(createInput("input4", String.class.getName()));

        assertThat(constraintUnderTest.checkRemovedInputs(def.getInput(), connectorParamKey).toString()).isEmpty();
    }

    @Test
    public void shouldCheckRemovedInputs_ReturnAStringContainingRemovedInputs() throws Exception {
        final Set<String> connectorParamKey = new HashSet<>();
        connectorParamKey.add("input1");
        connectorParamKey.add("input2");
        connectorParamKey.add("input3");

        final ConnectorDefinition def = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        def.setId("myDef");
        def.setVersion("1.0.0");
        def.getInput().add(createInput("input1", String.class.getName()));
        def.getInput().add(createInput("input3", String.class.getName()));
        def.getInput().add(createInput("input4", String.class.getName()));

        assertThat(constraintUnderTest.checkRemovedInputs(def.getInput(), connectorParamKey).toString()).contains("input2");
    }

}
