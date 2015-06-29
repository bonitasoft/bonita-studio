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
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.junit.Test;

/**
 * @author aurelie
 */
public class RootContractInputGeneratorTest {

    @Test
    public void should_create_a_complex_contract_input_with_root_input_name() throws Exception {
        final RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName",
                Collections.<FieldToContractInputMapping> emptyList(), mock(BusinessObjectModelRepositoryStore.class));

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
                        .aStringField("input1").build())), mock(BusinessObjectModelRepositoryStore.class));

        rootContractInputGenerator.build(aBusinessData().build());

        assertThat(rootContractInputGenerator.getRootContractInput().getInputs()).hasSize(1);
    }

    @Test
    public void should_create_operation_for_given_business_data_and_generated_contract_input() throws Exception {
        final SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping(SimpleFieldBuilder.aStringField(
                "firstName").build());

        final RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("rootInputName", newArrayList(mapping),
                mock(BusinessObjectModelRepositoryStore.class));
        rootContractInputGenerator.build(aBusinessData().withName("employee").build());

        assertThat(rootContractInputGenerator.getMappingOperations()).extracting("leftOperand.name", "operator.type",
                "operator.expression", "rightOperand.name").containsOnly(
                tuple("employee", ExpressionConstants.JAVA_METHOD_OPERATOR, "setFirstName", "rootInputName.firstName"));
    }

    @Test
    public void should_create_operation_for_given_multiple_business_data_and_generated_contract_input() throws Exception {
        final SimpleField firstNameField = SimpleFieldBuilder.aStringField("firstName").build();
        final SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping(firstNameField);

        final BusinessObjectModelRepositoryStore businessObjectStore = mock(BusinessObjectModelRepositoryStore.class);
        final BusinessObject bo = aBO("org.test.Employee").withField(firstNameField).build();
        when(businessObjectStore.getBusinessObjectByQualifiedName("org.test.Employee")).thenReturn(bo);
        final RootContractInputGenerator rootContractInputGenerator = new RootContractInputGenerator("employeesInput", newArrayList(mapping),
                businessObjectStore);
        rootContractInputGenerator.build(aBusinessData().withName("employees").withClassname("org.test.Employee").multiple().build());

        assertThat(rootContractInputGenerator.getMappingOperations()).extracting("leftOperand.name", "operator.type",
                "operator.expression", "rightOperand.name").containsOnly(
                tuple("employees", ExpressionConstants.JAVA_METHOD_OPERATOR, "addAll", "employeesInput"));
    }
}
