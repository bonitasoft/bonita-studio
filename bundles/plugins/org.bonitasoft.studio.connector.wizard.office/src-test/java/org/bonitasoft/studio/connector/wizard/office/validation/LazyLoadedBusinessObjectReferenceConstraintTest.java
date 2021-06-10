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

import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aConstantExpression;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aVariableExpression;
import static org.bonitasoft.studio.model.expression.builders.ListExpressionBuilder.aListExpression;
import static org.bonitasoft.studio.model.expression.builders.TableExpressionBuilder.aTableExpression;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.ConnectorBuilder.aConnector;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.FetchType;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.RelationFieldBuilder;
import org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorConfigurationBuilder;
import org.bonitasoft.studio.model.connectorconfiguration.builders.ConnectorParameterBuilder;
import org.bonitasoft.studio.model.expression.builders.TableExpressionBuilder;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LazyLoadedBusinessObjectReferenceConstraintTest {

    @Spy
    LazyLoadedBusinessObjectReferenceConstraint constraint;

    @Mock
    IValidationContext context;

    public void setUp() throws Exception {
        constraint = spy(new LazyLoadedBusinessObjectReferenceConstraint());

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
        doReturn(mock(BusinessObjectModelRepositoryStore.class)).when(constraint).getBusinessObjectRepositoryStore();
        constraint.performBatchValidation(context);
        verify(context).createSuccessStatus();
    }

    @Test
    public void should_return_warningStatus_when_connectorIsOffice_and_thereAreLazyLoadedBusinessVariable() {
        doReturn(anOfficeConnectorWithLazyBusinessData()).when(context).getTarget();
        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = mock(
                BusinessObjectModelRepositoryStore.class);
        doReturn(store).when(constraint).getBusinessObjectRepositoryStore();
        when(store.getBusinessObjectByQualifiedName("Employee"))
                .thenReturn(Optional.of(aBusinessObjectWithLazyFields("Employee")));
        constraint.performBatchValidation(context);
        verify(context).createFailureStatus(anyObject());
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

    private BusinessObject aBusinessObjectWithLazyFields(final String name) {
        final RelationField lazyField = RelationFieldBuilder.aCompositionField("lazy",
                BusinessObjectBuilder.aBO("Address").build());
        lazyField.setFetchType(FetchType.LAZY);
        return BusinessObjectBuilder.aBO(name).withField(lazyField).build();
    }
}
