/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.core.mapping.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingBuilder.aRelationMapping;
import static org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingBuilder.aSimpleMapping;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aCompositionField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aSimpleField;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FieldToContractInputMappingExpressionBuilderTest {

    @Mock
    private RepositoryAccessor repositoryAccessor;
    @Mock
    private ExpressionEditorService expressionEditorService;
    @Mock
    private GroovyCompilationUnit groovyCompilationUnit;

    @Test
    public void should_create_an_operation_for_a_given_complex_contact_input_and_a_composite_reference_business_data_field() throws Exception {
        final FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        final RelationField address = aCompositionField("address", aBO("Address").build());
        final FieldToContractInputMapping mapping = aRelationMapping(address).build();
        mapping.toContractInput(aContractInput().withName("employee").withType(ContractInputType.COMPLEX).build());
        final BusinessObjectData businessObjectData = aBusinessData().withName("myEmployee").build();
        final Expression expression = expressionBuilder.toExpression(businessObjectData,
                mapping, false);

        ExpressionAssert.assertThat(expression)
                .hasName("employee.address")
                .hasContent("def addressVar = myEmployee.address == null ? new Address() : myEmployee.address" + System.lineSeparator() + "return addressVar")
                .hasReturnType("Address")
                .hasType(ExpressionConstants.SCRIPT_TYPE);
        assertThat(expression.getReferencedElements()).hasSize(2);
    }

    private FieldToContractInputMappingExpressionBuilder newExpressionBuilder() throws JavaModelException {
        final FieldToContractInputMappingExpressionBuilder expressionBuilder = spy(new FieldToContractInputMappingExpressionBuilder(repositoryAccessor,
                expressionEditorService));
        doReturn(groovyCompilationUnit).when(expressionBuilder).groovyCompilationUnit(any(Expression.class));
        return expressionBuilder;
    }

    @Test
    public void should_create_an_operation_for_a_given_simple_contact_input_and_a_primitive_business_data_field() throws Exception {
        final FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        final SimpleField lastNameField = aSimpleField().withName("lastName").ofType(FieldType.STRING).build();
        final FieldToContractInputMapping mapping = aSimpleMapping(lastNameField).build();
        final Expression expression = expressionBuilder.toExpression(aBusinessData().withName("myEmployee").build(),
                mapping, false);

        ExpressionAssert.assertThat(expression)
                .hasName("lastName")
                .hasContent("lastName")
                .hasType(ExpressionConstants.SCRIPT_TYPE);
        assertThat(expression.getReferencedElements()).hasSize(1);
    }

    @Test
    public void should_create_an_operation_for_a_given_complex_contact_input_and_a_primitive_business_data_field() throws Exception {
        final FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        final SimpleField lastNameField = aSimpleField().withName("lastName").ofType(FieldType.STRING).build();
        final FieldToContractInputMapping mapping = aSimpleMapping(lastNameField).build();
        mapping.toContractInput(aContractInput().withType(ContractInputType.COMPLEX)
                .withName("employee").build());

        final BusinessObjectData data = aBusinessData().withName("myEmployee").build();
        final Expression expression = expressionBuilder.toExpression(data,
                mapping, false);

        ExpressionAssert.assertThat(expression)
                .hasName("employee.lastName")
                .hasContent("employee.lastName")
                .hasReturnType(String.class.getName())
                .hasType(ExpressionConstants.SCRIPT_TYPE);
        assertThat(expression.getReferencedElements()).hasSize(1);
        ContractInputAssert.assertThat((ContractInput) expression.getReferencedElements().get(0)).hasName("employee");
    }

    @Test
    public void should_not_add_businessVariable_dependency_forInitializationScript() throws JavaModelException, BusinessObjectInstantiationException {
        final FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        final RelationField address = aCompositionField("address", aBO("Address").build());
        final FieldToContractInputMapping mapping = aRelationMapping(address).build();
        mapping.toContractInput(aContractInput().withName("employee").withType(ContractInputType.COMPLEX).build());
        final BusinessObjectData businessObjectData = aBusinessData().withName("myEmployee").build();
        final Expression expression = expressionBuilder.toExpression(businessObjectData,
                mapping, true);

        ExpressionAssert.assertThat(expression)
                .hasName("initMyEmployee()")
                .hasContent("def addressVar = myEmployee.address == null ? new Address() : myEmployee.address" + System.lineSeparator() + "return addressVar")
                .hasReturnType("Address")
                .hasType(ExpressionConstants.SCRIPT_TYPE);
        assertThat(expression.getReferencedElements()).hasSize(1);
    }

    @Test
    public void should_delete_compilation_unit_after_dependencies_resolution() throws Exception {
        final FieldToContractInputMappingExpressionBuilder expressionBuilder = newExpressionBuilder();

        final SimpleField lastNameField = aSimpleField().withName("lastName").ofType(FieldType.STRING).build();
        final FieldToContractInputMapping mapping = aSimpleMapping(lastNameField).build();
        expressionBuilder.toExpression(aBusinessData().withName("myEmployee").build(),
                mapping, false);

        verify(groovyCompilationUnit).delete(true, Repository.NULL_PROGRESS_MONITOR);

    }
}
