/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.wizard.office.validation;

import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.aConstantExpression;
import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.aVariableExpression;
import static org.bonitasoft.bpm.model.expression.builders.ListExpressionBuilder.aListExpression;
import static org.bonitasoft.bpm.model.expression.builders.TableExpressionBuilder.aTableExpression;
import static org.bonitasoft.bpm.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.bpm.model.process.builders.ConnectorBuilder.aConnector;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.bonitasoft.bpm.model.connectorconfiguration.builders.ConnectorConfigurationBuilder;
import org.bonitasoft.bpm.model.connectorconfiguration.builders.ConnectorParameterBuilder;
import org.bonitasoft.bpm.model.expression.builders.TableExpressionBuilder;
import org.bonitasoft.bpm.model.process.Connector;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LazyLoadedBusinessObjectReferenceConstraintTest {

    @Mock
    IValidationContext context;
    
    private LazyLoadedBusinessObjectReferenceConstraint constraint;

    @Before
    public void setUp() throws Exception {
        constraint = new LazyLoadedBusinessObjectReferenceConstraint();
    }

    @Test
    public void should_return_okStatus_when_connectorIsNot_Office() {
        final Connector connector = ProcessFactory.eINSTANCE.createConnector();
        connector.setDefinitionId("notOfficeConnector");
        doReturn(connector).when(context).getTarget();
        constraint.performBatchValidation(context);
        verify(context).createSuccessStatus();
    }

    @Test
    public void should_return_okStatus_when_connectorIsOffice_and_thereIsNoBusinessVariable() {
        doReturn(anOfficeConnectorWithNoBusinessData()).when(context).getTarget();
        constraint.performBatchValidation(context);
        verify(context).createSuccessStatus();
    }

    @Test
    public void should_return_warningStatus_when_connectorIsOffice_and_thereAreLazyLoadedBusinessVariable() {
        doReturn(anOfficeConnectorWithLazyBusinessData()).when(context).getTarget();
        constraint.performBatchValidation(context);
        verify(context).createFailureStatus(any());
    }

    private Connector anOfficeConnectorWithNoBusinessData() {
        return aConnector()
                .withDefinitionId("document-templating")
                .havingConfiguration(
                        ConnectorConfigurationBuilder.aConnectorConfiguration().havingParameters(
                                ConnectorParameterBuilder.aConnectorParameter().withKey("replacements")
                                        .havingExpression(TableExpressionBuilder.aTableExpression().build())))
                .build();
    }

    private Connector anOfficeConnectorWithLazyBusinessData() {
        final TableExpressionBuilder tableExpressionBuilder = aTableExpression()
                .havingRows(aListExpression().havingExpressions(
                        aConstantExpression().withName("key"),
                        aVariableExpression().havingReferencedElements(aBusinessData().withClassname("Employee"))));
        return aConnector()
                .withDefinitionId("document-templating")
                .havingConfiguration(
                        ConnectorConfigurationBuilder.aConnectorConfiguration().havingParameters(
                                ConnectorParameterBuilder.aConnectorParameter().withKey("replacements")
                                        .havingExpression(tableExpressionBuilder.build())))
                .build();
    }

}
