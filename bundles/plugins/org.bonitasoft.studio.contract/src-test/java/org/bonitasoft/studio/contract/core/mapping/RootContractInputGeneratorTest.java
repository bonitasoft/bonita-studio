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
package org.bonitasoft.studio.contract.core.mapping;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.expression.FieldToContractInputMappingExpressionBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingOperationBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.OperationCreationException;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class RootContractInputGeneratorTest {

    @Test
    public void should_create_a_complex_contract_input_with_root_input_name() throws Exception {
        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessStore = getBusinessObjectModelRepositoryStore();
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(businessStore);
        RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName",
                Collections.<FieldToContractInputMapping> emptyList(), repositoryAccessor,
                mock(FieldToContractInputMappingOperationBuilder.class),
                mock(FieldToContractInputMappingExpressionBuilder.class));

        rootContractInputGenerator.build(aBusinessData().build(), new NullProgressMonitor());

        ContractInputAssert.assertThat(rootContractInputGenerator.getRootContractInput()).hasName("rootInputName")
                .hasType(ContractInputType.COMPLEX);
    }

    @Test
    public void should_create_a_complex_contract_input_with_generated_child_input_from_mapping() throws Exception {
        SimpleFieldToContractInputMapping generatedMapping = new SimpleFieldToContractInputMapping(
                SimpleFieldBuilder.aStringField("input1").build());
        SimpleFieldToContractInputMapping notGeneratedMapping = new SimpleFieldToContractInputMapping(
                SimpleFieldBuilder.aStringField("input2").build());
        notGeneratedMapping.setGenerated(false);
        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessStore = getBusinessObjectModelRepositoryStore();
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(businessStore);
        List<FieldToContractInputMapping> children = Arrays.asList(notGeneratedMapping, generatedMapping);
        RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName",
                children,
                repositoryAccessor, mock(FieldToContractInputMappingOperationBuilder.class),
                mock(FieldToContractInputMappingExpressionBuilder.class));

        rootContractInputGenerator.build(aBusinessData().build(), new NullProgressMonitor());

        assertThat(rootContractInputGenerator.getRootContractInput().getInputs()).hasSize(1);
    }

    @Test
    public void should_create_operation_for_given_business_data_and_generated_contract_input() throws Exception {
        SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping(
                SimpleFieldBuilder.aStringField(
                        "firstName").build());

        FieldToContractInputMappingOperationBuilder operationBuilder = mock(
                FieldToContractInputMappingOperationBuilder.class);
        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessStore = getBusinessObjectModelRepositoryStore();
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(businessStore);
        RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName",
                newArrayList(mapping),
                repositoryAccessor, operationBuilder, mock(FieldToContractInputMappingExpressionBuilder.class));
        BusinessObjectData businessObjectData = aBusinessData().withName("employee").build();
        IProgressMonitor monitor = new NullProgressMonitor();
        rootContractInputGenerator.build(businessObjectData, monitor);

        verify(operationBuilder).toOperation(businessObjectData, mapping, monitor);
    }

    @Test
    public void should_allAttributesGenerated_setToFalse_whenNotAllMappingAreGenerated() throws OperationCreationException {
        SimpleFieldToContractInputMapping notGeneratedMapping = new SimpleFieldToContractInputMapping(
                SimpleFieldBuilder.aStringField(
                        "input1").build());
        notGeneratedMapping.setGenerated(false);
        FieldToContractInputMappingOperationBuilder operationBuilder = mock(
                FieldToContractInputMappingOperationBuilder.class);
        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessStore = getBusinessObjectModelRepositoryStore();
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(businessStore);
        RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName",
                newArrayList(notGeneratedMapping),
                repositoryAccessor, operationBuilder, mock(FieldToContractInputMappingExpressionBuilder.class));
        BusinessObjectData businessObjectData = aBusinessData().withName("employee").build();
        rootContractInputGenerator.build(businessObjectData, new NullProgressMonitor());
        assertThat(rootContractInputGenerator.isAllAttributesGenerated()).isFalse();
    }

    @Test
    public void should_allAttributesGenerated_setTotrue_whenAllMappingAreGenerated() throws OperationCreationException {
        SimpleFieldToContractInputMapping notGeneratedMapping = new SimpleFieldToContractInputMapping(
                SimpleFieldBuilder.aStringField(
                        "input2").build());
        notGeneratedMapping.setGenerated(true);
        FieldToContractInputMappingOperationBuilder operationBuilder = mock(
                FieldToContractInputMappingOperationBuilder.class);
        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessStore = getBusinessObjectModelRepositoryStore();
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(businessStore);
        RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName",
                newArrayList(notGeneratedMapping,
                        new SimpleFieldToContractInputMapping(SimpleFieldBuilder
                                .aStringField("input1").build())),
                repositoryAccessor, operationBuilder, mock(FieldToContractInputMappingExpressionBuilder.class));
        BusinessObjectData businessObjectData = aBusinessData().withName("employee").build();
        rootContractInputGenerator.build(businessObjectData, new NullProgressMonitor());
        assertThat(rootContractInputGenerator.isAllAttributesGenerated()).isTrue();
    }

    @Test
    public void should_create_operation_for_given_multiple_business_data_and_generated_contract_input() throws Exception {
        SimpleField firstNameField = SimpleFieldBuilder.aStringField("firstName").build();
        SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping(firstNameField);

        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessObjectStore = getBusinessObjectModelRepositoryStore();
        BusinessObject bo = aBO("org.test.Employee").withField(firstNameField).build();
        when(businessObjectStore.getBusinessObjectByQualifiedName("org.test.Employee")).thenReturn(Optional.of(bo));
        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class))
                .thenReturn(businessObjectStore);
        FieldToContractInputMappingOperationBuilder operationBuilder = mock(
                FieldToContractInputMappingOperationBuilder.class);
        RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("employeesInput",
                newArrayList(mapping),
                repositoryAccessor, operationBuilder, mock(FieldToContractInputMappingExpressionBuilder.class));
        BusinessObjectData businessObjectData = aBusinessData().withName("employees")
                .withClassname("org.test.Employee").multiple().build();
        NullProgressMonitor monitor = new NullProgressMonitor();
        rootContractInputGenerator.build(businessObjectData, monitor);

        ArgumentCaptor<FieldToContractInputMapping> argumentCaptor = ArgumentCaptor
                .forClass(FieldToContractInputMapping.class);
        verify(operationBuilder).toOperation(eq(businessObjectData), argumentCaptor.capture(), eq(monitor));
        Field field = argumentCaptor.getValue().getField();
        assertThat(field).isInstanceOf(RelationField.class);
        assertThat(field.getName()).isEqualTo("employeesInput");
        assertThat(field.isCollection()).isTrue();
        assertThat(((RelationField) field).getReference().getQualifiedName()).isEqualTo("org.test.Employee");
        assertThat(((RelationField) field).getType()).isEqualTo(RelationField.Type.COMPOSITION);
    }

    @Test
    public void should_create_initilal_value_expression_for_given_business_data_and_generated_contract_input()
            throws Exception {
        SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping(
                SimpleFieldBuilder.aStringField("firstName").build());

        FieldToContractInputMappingOperationBuilder operationBuilder = mock(
                FieldToContractInputMappingOperationBuilder.class);
        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessStore = getBusinessObjectModelRepositoryStore();
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(businessStore);
        FieldToContractInputMappingExpressionBuilder expressionBuilder = mock(
                FieldToContractInputMappingExpressionBuilder.class);
        RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName",
                newArrayList(mapping),
                repositoryAccessor, operationBuilder, expressionBuilder);
        BusinessObjectData businessObjectData = aBusinessData().withName("employee").build();
        rootContractInputGenerator.buildForInstanciation(businessObjectData, new NullProgressMonitor());

        ArgumentCaptor<FieldToContractInputMapping> argumentCaptor = ArgumentCaptor
                .forClass(FieldToContractInputMapping.class);
        verify(expressionBuilder).toExpression(eq(businessObjectData), argumentCaptor.capture(), eq(true));
        FieldToContractInputMapping fieldToContractInputMapping = argumentCaptor.getValue();
        assertThat(fieldToContractInputMapping.getField().getName()).isEqualTo("rootInputName");
        assertThat(fieldToContractInputMapping.getField().isCollection()).isFalse();
        assertThat(fieldToContractInputMapping.getChildren()).hasSize(1);
    }

    @Test
    public void should_create_initilal_value_expression_for_given_multiple_business_data_and_generated_contract_input()
            throws Exception {
        SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping(
                SimpleFieldBuilder.aStringField("firstName").build());

        FieldToContractInputMappingOperationBuilder operationBuilder = mock(
                FieldToContractInputMappingOperationBuilder.class);
        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessStore = getBusinessObjectModelRepositoryStore();
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(businessStore);
        FieldToContractInputMappingExpressionBuilder expressionBuilder = mock(
                FieldToContractInputMappingExpressionBuilder.class);
        RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName",
                newArrayList(mapping),
                repositoryAccessor, operationBuilder, expressionBuilder);
        BusinessObjectData businessObjectData = aBusinessData().withName("employee").multiple().build();
        rootContractInputGenerator.buildForInstanciation(businessObjectData, new NullProgressMonitor());

        ArgumentCaptor<FieldToContractInputMapping> argumentCaptor = ArgumentCaptor
                .forClass(FieldToContractInputMapping.class);
        verify(expressionBuilder).toExpression(eq(businessObjectData), argumentCaptor.capture(), eq(true));
        FieldToContractInputMapping fieldToContractInputMapping = argumentCaptor.getValue();
        assertThat(fieldToContractInputMapping.getField().getName()).isEqualTo("rootInputName");
        assertThat(fieldToContractInputMapping.getField().isCollection()).isTrue();
        assertThat(fieldToContractInputMapping.getChildren()).hasSize(1);
    }

    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> getBusinessObjectModelRepositoryStore() {
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(businessStore.getBusinessObjectByQualifiedName(anyString())).thenReturn(Optional.empty());
        return businessStore;
    }
}
