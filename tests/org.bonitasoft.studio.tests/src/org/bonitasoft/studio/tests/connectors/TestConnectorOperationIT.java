/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.connectors;

import static org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorParameterBuilder.aConnectorParameter;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aConstantExpression;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.operation.TestConnectorOperation;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestConnectorOperationIT {

    private static final String GROOVY_DEF_VERSION = "1.0.1";
    private static final String GROOVY_DEF_ID = "scripting-groovy-script";

    @Before
    public void init() throws Exception {
        AddDependencyOperation addDependencyOperation = new AddDependencyOperation("org.bonitasoft.connectors", "bonita-connector-groovy", "1.1.2");
        addDependencyOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
    }
    
    @Test
    public void testBasicGroovyScript() throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        TestConnectorOperation testConnectorOperation = createOperation();

        testConnectorOperation.run(new NullProgressMonitor());

        Assert.assertTrue("Test connector operation status is not ok", testConnectorOperation.getStatus().isOK());
        Assert.assertEquals("The result is not right", "Expected Result",
                testConnectorOperation.getResult().values().iterator().next());
    }

    private TestConnectorOperation createOperation() throws ReadFileStoreException {
        TestConnectorOperation testConnectorOperation = new TestConnectorOperation();
        ConnectorConfiguration configuration = createConnectorConfiguration();
        testConnectorOperation.setConnectorConfiguration(configuration);

        Connector connector = createConnectorOutput();
        testConnectorOperation.setConnectorOutput(connector);

        ConnectorImplementation implementation = createConnectorImplementation();
        testConnectorOperation.setImplementation(implementation);
        return testConnectorOperation;
    }

    private ConnectorImplementation createConnectorImplementation() throws ReadFileStoreException {
        ConnectorImplRepositoryStore c = RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
        return c.getImplementations(GROOVY_DEF_ID, GROOVY_DEF_VERSION).stream().findFirst().orElse(null);
    }

    private Connector createConnectorOutput() {
        Connector connector = ProcessFactory.eINSTANCE.createConnector();
        connector.setDefinitionId(GROOVY_DEF_ID);
        connector.setDefinitionVersion(GROOVY_DEF_VERSION);
        connector.setName("Connector for test");
        Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        Operator op = ExpressionFactory.eINSTANCE.createOperator();
        op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        op.setExpression("=");
        operation.setOperator(op);
        Expression rightOperandExpression = ExpressionFactory.eINSTANCE.createExpression();
        rightOperandExpression.setType(ExpressionConstants.CONNECTOR_OUTPUT_TYPE);
        rightOperandExpression.setName("result");
        rightOperandExpression.setContent("result");
        operation.setRightOperand(rightOperandExpression);
        Expression leftOperandExpression = ExpressionFactory.eINSTANCE.createExpression();
        leftOperandExpression.setType(ExpressionConstants.VARIABLE_TYPE);
        leftOperandExpression.setReturnType(String.class.getName());
        leftOperandExpression.setName("var");
        leftOperandExpression.setContent("var");
        operation.setLeftOperand(leftOperandExpression);
        connector.getOutputs().add(operation);
        return connector;
    }

    private ConnectorConfiguration createConnectorConfiguration() {
        ConnectorConfiguration configuration = ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration();
        configuration.setDefinitionId(GROOVY_DEF_ID);
        configuration.setVersion(GROOVY_DEF_VERSION);
        ConnectorParameter parameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        Expression expressionInput = ExpressionFactory.eINSTANCE.createExpression();
        expressionInput.setType(ExpressionConstants.SCRIPT_TYPE);
        expressionInput.setInterpreter(ExpressionConstants.GROOVY);
        expressionInput.setContent("\"Expected Result\"");
        parameter.setExpression(expressionInput);
        parameter.setKey("script");
        configuration.getParameters().add(parameter);

        configuration.getParameters().add(aConnectorParameter().withKey("variables").havingExpression(
                aConstantExpression().withName("").withContent("")).build());
        configuration.getParameters().add(aConnectorParameter().withKey("fakeScriptExpression").havingExpression(
                aGroovyScriptExpression().withName("scriptName").withContent("return \"Expected Result\";")).build());

        return configuration;
    }

}
