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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingOperationBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

/**
 * @author aurelie
 */
public class RootContractInputGeneratorTest {

    @Test
    public void should_create_a_complex_contract_input_with_root_input_name() throws Exception {
        final RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName",
                Collections.<FieldToContractInputMapping> emptyList(), mock(RepositoryAccessor.class), mock(FieldToContractInputMappingOperationBuilder.class));

        rootContractInputGenerator.build(aBusinessData().build());

        ContractInputAssert.assertThat(rootContractInputGenerator.getRootContractInput()).hasName("rootInputName")
                .hasType(ContractInputType.COMPLEX);
    }

    @Test
    public void should_create_a_complex_contract_input_with_generated_child_input_from_mapping() throws Exception {
        final SimpleFieldToContractInputMapping notGeneratedMapping = new SimpleFieldToContractInputMapping(SimpleFieldBuilder.aStringField(
                "input2").build());
        notGeneratedMapping.setGenerated(false);
        final RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName",
                newArrayList(notGeneratedMapping, new SimpleFieldToContractInputMapping(SimpleFieldBuilder
                        .aStringField("input1").build())), mock(RepositoryAccessor.class), mock(FieldToContractInputMappingOperationBuilder.class));

        rootContractInputGenerator.build(aBusinessData().build());

        assertThat(rootContractInputGenerator.getRootContractInput().getInputs()).hasSize(1);
    }

    @Test
    public void should_create_operation_for_given_business_data_and_generated_contract_input() throws Exception {
        final SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping(SimpleFieldBuilder.aStringField(
                "firstName").build());

        final FieldToContractInputMappingOperationBuilder operationBuilder = mock(FieldToContractInputMappingOperationBuilder.class);
        final RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName", newArrayList(mapping),
                mock(RepositoryAccessor.class), operationBuilder);
        final BusinessObjectData businessObjectData = aBusinessData().withName("employee").build();
        rootContractInputGenerator.build(businessObjectData);

        verify(operationBuilder).toOperation(businessObjectData, mapping);
    }

    @Test
    public void should_create_operation_for_given_multiple_business_data_and_generated_contract_input() throws Exception {
        final SimpleField firstNameField = SimpleFieldBuilder.aStringField("firstName").build();
        final SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping(firstNameField);

        final BusinessObjectModelRepositoryStore businessObjectStore = mock(BusinessObjectModelRepositoryStore.class);
        final BusinessObject bo = aBO("org.test.Employee").withField(firstNameField).build();
        when(businessObjectStore.getBusinessObjectByQualifiedName("org.test.Employee")).thenReturn(bo);
        final RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(businessObjectStore);
        final FieldToContractInputMappingOperationBuilder operationBuilder = mock(FieldToContractInputMappingOperationBuilder.class);
        final RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("employeesInput", newArrayList(mapping),
                repositoryAccessor, operationBuilder);
        final BusinessObjectData businessObjectData = aBusinessData().withName("employees").withClassname("org.test.Employee").multiple().build();
        rootContractInputGenerator.build(businessObjectData);

        final ArgumentCaptor<FieldToContractInputMapping> argumentCaptor = ArgumentCaptor.forClass(FieldToContractInputMapping.class);
        verify(operationBuilder).toOperation(eq(businessObjectData), argumentCaptor.capture());
        final Field field = argumentCaptor.getValue().getField();
        assertThat(field).isInstanceOf(RelationField.class);
        assertThat(field.getName()).isEqualTo("employeesInput");
        assertThat(field.isCollection()).isTrue();
        assertThat(((RelationField) field).getReference().getQualifiedName()).isEqualTo("org.test.Employee");
        assertThat(((RelationField) field).getType()).isEqualTo(RelationField.Type.COMPOSITION);
    }
}
