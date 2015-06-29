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
package org.bonitasoft.studio.contract.core.mapping.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingBuilder.aRelationMapping;
import static org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingBuilder.aSimpleMapping;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aCompositionField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aSimpleField;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.expression.editor.filter.ExpressionReturnTypeFilter;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.expression.assertions.OperatorAssert;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FieldToContractInputMappingOperationBuilderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Mock
    private ExpressionReturnTypeFilter expressionReturnTypeFilter;

    @Before
    public void setUp() throws Exception {
        when(expressionReturnTypeFilter.compatibleReturnTypes(anyString(), anyString())).thenReturn(true);
    }

    @Test
    public void should_create_an_operation_for_a_given_simple_contact_input_and_a_primitive_business_data_field() throws Exception {
        final FieldToContractInputMappingOperationBuilder inputToOperation = createFixture();

        final SimpleField lastNameField = aSimpleField().withName("lastName").ofType(FieldType.STRING).build();
        final FieldToContractInputMapping mapping = aSimpleMapping(lastNameField).build();
        final Operation operation = inputToOperation.toOperation(aBusinessData().withName("myEmployee").build(),
                mapping);

        OperatorAssert.assertThat(operation.getOperator())
                .hasType(ExpressionConstants.JAVA_METHOD_OPERATOR)
                .hasInputTypes(String.class.getName())
                .hasExpression("setLastName");

        ExpressionAssert.assertThat(operation.getLeftOperand())
                .hasName("myEmployee").hasContent("myEmployee")
                .hasType(ExpressionConstants.VARIABLE_TYPE);
        assertThat(operation.getLeftOperand().getReferencedElements()).hasSize(1);

        ExpressionAssert.assertThat(operation.getRightOperand())
                .hasName("lastName")
                .hasContent("lastName")
                .hasType(ExpressionConstants.CONTRACT_INPUT_TYPE);
        assertThat(operation.getRightOperand().getReferencedElements()).hasSize(1);
    }

    @Test
    public void should_create_an_operation_for_a_given_complex_contact_input_and_a_primitive_business_data_field() throws Exception {
        final FieldToContractInputMappingOperationBuilder inputToOperation = createFixture();

        final SimpleField lastNameField = aSimpleField().withName("lastName").ofType(FieldType.STRING).build();
        final FieldToContractInputMapping fieldToContractInputMapping = aSimpleMapping(lastNameField).build();
        fieldToContractInputMapping.toContractInput(aContractInput().withType(ContractInputType.COMPLEX)
                .withName("employee").build());
        final Operation operation = inputToOperation.toOperation(aBusinessData().withName("myEmployee").build(),
                fieldToContractInputMapping);

        OperatorAssert.assertThat(operation.getOperator())
                .hasType(ExpressionConstants.JAVA_METHOD_OPERATOR)
                .hasInputTypes(String.class.getName())
                .hasExpression("setLastName");

        ExpressionAssert.assertThat(operation.getLeftOperand())
                .hasName("myEmployee").hasContent("myEmployee")
                .hasType(ExpressionConstants.VARIABLE_TYPE);
        assertThat(operation.getLeftOperand().getReferencedElements()).hasSize(1);

        ExpressionAssert.assertThat(operation.getRightOperand())
                .hasName("employee.lastName")
                .hasContent("employee.lastName")
                .hasReturnType(String.class.getName())
                .hasType(ExpressionConstants.SCRIPT_TYPE);
        assertThat(operation.getRightOperand().getReferencedElements()).hasSize(1);
        ContractInputAssert.assertThat((ContractInput) operation.getRightOperand().getReferencedElements().get(0)).hasName("employee");
    }

    @Test
    public void should_create_an_operation_for_a_given_complex_contact_input_and_a_composite_reference_business_data_field() throws Exception {
        final FieldToContractInputMappingOperationBuilder inputToOperation = createFixture();

        final RelationField address = aCompositionField("address", aBO("Address").build());
        final FieldToContractInputMapping mapping = aRelationMapping(address).build();
        mapping.toContractInput(aContractInput().withName("employee").withType(ContractInputType.COMPLEX).build());
        final Operation operation = inputToOperation.toOperation(aBusinessData().withName("myEmployee").build(),
                mapping);

        OperatorAssert.assertThat(operation.getOperator())
                .hasType(ExpressionConstants.JAVA_METHOD_OPERATOR)
                .hasInputTypes("Address")
                .hasExpression("setAddress");

        ExpressionAssert.assertThat(operation.getLeftOperand())
                .hasName("myEmployee").hasContent("myEmployee")
                .hasType(ExpressionConstants.VARIABLE_TYPE);
        assertThat(operation.getLeftOperand().getReferencedElements()).hasSize(1);

        ExpressionAssert.assertThat(operation.getRightOperand())
                .hasName("employee.address")
                .hasContent("def addressVar = myEmployee.address == null ? new Address() : myEmployee.address" + System.lineSeparator() + "return addressVar")
                .hasReturnType("Address")
                .hasType(ExpressionConstants.SCRIPT_TYPE);
        assertThat(operation.getRightOperand().getReferencedElements()).hasSize(2);
    }

    @Test
    public void should_throw_an_OperationCreationException_if_types_does_not_match() throws Exception {
        final FieldToContractInputMappingOperationBuilder inputToOperation = createFixture();
        when(expressionReturnTypeFilter.compatibleReturnTypes(anyString(), anyString())).thenReturn(false);
        final SimpleField lastNameField = aSimpleField().withName("lastName").ofType(FieldType.STRING).build();

        thrown.expect(OperationCreationException.class);
        inputToOperation.toOperation(aBusinessData().withName("myEmployee").build(),
                aSimpleMapping(lastNameField).build());
    }

    @Test
    public void should_throw_an_OperationCreationException_if_script_generation_fails() throws Exception {
        final FieldToContractInputMappingOperationBuilder inputToOperation = createFixture();
        when(expressionReturnTypeFilter.compatibleReturnTypes(anyString(), anyString())).thenReturn(false);
        final RelationField address = aCompositionField("address", aBO("Address").build());

        final FieldToContractInputMapping mapping = spy(aRelationMapping(address).build());
        mapping.toContractInput(aContractInput().withName("employee").withType(ContractInputType.COMPLEX).build());
        final MappingOperationScriptBuilder fakeScriptBuilder = mock(MappingOperationScriptBuilder.class);
        when(fakeScriptBuilder.toScript()).thenThrow(BusinessObjectInstantiationException.class);
        doReturn(fakeScriptBuilder).when(mapping).getScriptBuilder(any(BusinessObjectData.class));

        thrown.expect(OperationCreationException.class);
        inputToOperation.toOperation(aBusinessData().withName("myEmployee").build(),
                mapping);
    }

    @Test
    public void should_create_an_operation_for_a_multiple_business_data_with_addAll_method() throws Exception {
        final FieldToContractInputMappingOperationBuilder inputToOperation = createFixture();

        final SimpleField lastNameField = aSimpleField().withName("lastName").ofType(FieldType.STRING).build();
        final FieldToContractInputMapping mapping = aSimpleMapping(lastNameField).build();
        final Operation operation = inputToOperation.toOperation(aBusinessData().multiple().withName("employees").build(),
                mapping);

        OperatorAssert.assertThat(operation.getOperator())
                .hasType(ExpressionConstants.JAVA_METHOD_OPERATOR)
                .hasInputTypes(Collection.class.getName())
                .hasExpression("addAll");
    }

    private FieldToContractInputMappingOperationBuilder createFixture() {
        return new FieldToContractInputMappingOperationBuilder(expressionReturnTypeFilter);
    }

}
