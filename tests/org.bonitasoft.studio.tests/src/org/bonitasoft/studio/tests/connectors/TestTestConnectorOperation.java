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

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
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
import org.junit.Test;

public class TestTestConnectorOperation {

	@Test
	public void testBasicGroovyScript() throws InvocationTargetException, InterruptedException{
		TestConnectorOperation testConnectorOperation = createOperation();
		
		testConnectorOperation.run(new NullProgressMonitor());
		
		Assert.assertTrue("Test connector operation status is not ok", testConnectorOperation.getStatus().isOK());
		Assert.assertEquals("The result is not right", "Expected Result", testConnectorOperation.getResult().values().iterator().next());
	}

	private TestConnectorOperation createOperation() {
		TestConnectorOperation testConnectorOperation = new TestConnectorOperation();
		ConnectorConfiguration configuration = createConnectorConfiguration();
		testConnectorOperation.setConnectorConfiguration(configuration);
		
		Connector connector = createConnectorOutput();
		testConnectorOperation.setConnectorOutput(connector);
		
		ConnectorImplementation implementation = createConnectorImplementation();
		testConnectorOperation.setImplementation(implementation);
		return testConnectorOperation;
	}

	private ConnectorImplementation createConnectorImplementation() {
		ConnectorImplRepositoryStore c = RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
		return c.getChild("scripting-groovy.impl").getContent();
	}

	private Connector createConnectorOutput() {
		Connector connector = ProcessFactory.eINSTANCE.createConnector();
		connector.setDefinitionId("scripting-groovy");
		connector.setDefinitionVersion("1.0.0");
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
		configuration.setDefinitionId("scripting-groovy");
		configuration.setVersion("1.0.0");
		ConnectorParameter parameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
		Expression expressionInput = ExpressionFactory.eINSTANCE.createExpression();
		expressionInput.setType(ExpressionConstants.SCRIPT_TYPE);
		expressionInput.setInterpreter(ExpressionConstants.GROOVY);
		expressionInput.setContent("\"Expected Result\"");
		parameter.setExpression(expressionInput );
		parameter.setKey("script");
		configuration.getParameters().add(parameter);
		return configuration;
	}
	
}
