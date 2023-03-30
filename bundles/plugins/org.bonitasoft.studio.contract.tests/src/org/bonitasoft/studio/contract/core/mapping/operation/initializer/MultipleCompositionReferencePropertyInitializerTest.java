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
package org.bonitasoft.studio.contract.core.mapping.operation.initializer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aCompositionField;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.junit.Test;

public class MultipleCompositionReferencePropertyInitializerTest {

    @Test
    public void should_build_a_closure_for_multiple_field_in_a_single_businessObject() throws Exception {
        final RelationField field = aCompositionField("addresses", aBO("Address").build());
        field.setCollection(true);

        final InitializerContext context = new InitializerContext();
        final RelationFieldToContractInputMapping mapping = new RelationFieldToContractInputMapping(field);
        context.setMapping(mapping);
        context.setData(aBusinessData().withName("employee").build());
        context.setContractInput(aContractInput().withName("addresses").multiple()
                .in(aContractInput().withName("employeeInput").withType(ContractInputType.COMPLEX))
                .build());
        context.setLocalVariableName("addressVar");
        context.setLocalListVariableName("addressList");

        final MultipleCompositionReferencePropertyInitializer initializer = new MultipleCompositionReferencePropertyInitializer(
                null, context);

        assertThat(initializer.getInitialValue()).isEqualTo("{" + System.lineSeparator()
                + "def addressList = []" + System.lineSeparator()
                + "//For each item collected in multiple input" + System.lineSeparator()
                + "employeeInput?.addresses.each{" + System.lineSeparator()
                + "//Add a new composed Address instance" + System.lineSeparator()
                + "addressList.add({ currentAddressInput ->" + System.lineSeparator()
                + "def addressVar = new Address()" + System.lineSeparator()
                + "return addressVar" + System.lineSeparator()
                + "}(it))" + System.lineSeparator()
                + "}" + System.lineSeparator()
                + "return addressList}()");
    }

    @Test
    public void should_build_a_closure_for_multiple_field_in_a_single_businessObject_without_existingValueOnPool()
            throws Exception {
        final RelationField field = aCompositionField("addresses", aBO("Address").build());
        field.setCollection(true);

        final InitializerContext context = new InitializerContext();
        final RelationFieldToContractInputMapping mapping = new RelationFieldToContractInputMapping(field);
        context.setMapping(mapping);
        context.setData(aBusinessData().withName("employee").build());
        context.setContractInput(aContractInput().withName("addresses").multiple()
                .in(aContractInput().withName("employeeInput").withType(ContractInputType.COMPLEX))
                .build());
        context.setLocalVariableName("addressVar");
        context.setLocalListVariableName("addressList");
        context.setCreateMode(true);

        final MultipleCompositionReferencePropertyInitializer initializer = new MultipleCompositionReferencePropertyInitializer(
                null, context);

        assertThat(initializer.getInitialValue()).isEqualTo("{" + System.lineSeparator()
                + "def addressList = []" + System.lineSeparator()
                + "//For each item collected in multiple input" + System.lineSeparator()
                + "employeeInput?.addresses.each{" + System.lineSeparator()
                + "//Add a new composed Address instance" + System.lineSeparator()
                + "addressList.add({ currentAddressInput ->" + System.lineSeparator()
                + "def addressVar = new Address()" + System.lineSeparator()
                + "return addressVar" + System.lineSeparator()
                + "}(it))" + System.lineSeparator()
                + "}" + System.lineSeparator()
                + "return addressList}()");
    }

    @Test
    public void should_build_a_closure_using_parent_iterator_for_multiple_field_in_a_multiple_businessObject()
            throws Exception {
        final RelationField field = aCompositionField("addresses", aBO("Address").build());
        field.setCollection(true);

        final InitializerContext context = new InitializerContext();
        final RelationFieldToContractInputMapping mapping = new RelationFieldToContractInputMapping(field);
        context.setMapping(mapping);
        context.setData(aBusinessData().withName("employee").build());
        context.setContractInput(aContractInput().withName("addresses").multiple()
                .in(aContractInput().withName("employeeInput").withType(ContractInputType.COMPLEX).multiple())
                .build());
        context.setLocalVariableName("addressVar");
        context.setLocalListVariableName("addressList");
        context.setCreateMode(true);

        final BusinessObject employee = aBO("Employee").withField(field).build();
        final MultipleCompositionReferencePropertyInitializer initializer = new MultipleCompositionReferencePropertyInitializer(
                employee, context);

        assertThat(initializer.getInitialValue()).isEqualTo("{" + System.lineSeparator()
                + "def addressList = []" + System.lineSeparator()
                + "//For each item collected in multiple input" + System.lineSeparator()
                + "currentEmployeeInput.addresses.each{" + System.lineSeparator()
                + "//Add a new composed Address instance" + System.lineSeparator()
                + "addressList.add({ currentAddressInput ->" + System.lineSeparator()
                + "def addressVar = new Address()" + System.lineSeparator()
                + "return addressVar" + System.lineSeparator()
                + "}(it))" + System.lineSeparator()
                + "}" + System.lineSeparator()
                + "return addressList}()");
    }
}
