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
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aSimpleField;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.junit.Test;

public class NewBusinessObjectListInitializerTest {

    @Test
    public void should_initialize_new_object_property_for_multiple_composed_reference() throws Exception {
        final SimpleField streetField = aSimpleField().withName("street").ofType(FieldType.STRING).notNullable().build();
        final BusinessObject businessObject = aBO("org.test.Address").withField(streetField).build();
        final RelationField addressField = aCompositionField("address",
                businessObject);
        addressField.setCollection(true);
        final AbstractBusinessObjectInitializer propertyInitializer = new NewBusinessObjectListInitializer(addressField, aContractInput().withName("address")
                .multiple().in(aContractInput().withName("employee").withType(ContractInputType.COMPLEX)).build(), "myAddresses", false);
        propertyInitializer.addPropertyInitializer(new SimpleFieldPropertyInitializer(businessObject,
                streetField, aContractInput().withName("street")
                        .in(aContractInput().withName("address").withType(ContractInputType.COMPLEX).multiple()
                                .in(aContractInput().withName("employee").withType(ContractInputType.COMPLEX))).build()));
        assertThat(propertyInitializer.getInitialValue()).isEqualTo("def addressList = []" + System.lineSeparator()
                + "//Append existing myAddresses" + System.lineSeparator()
                + "addressList.addAll(myAddresses)" + System.lineSeparator()
                + "//For each item collected in multiple input" + System.lineSeparator()
                + "employee.address.each{" + System.lineSeparator()
                + "//Add a new composed Address instance" + System.lineSeparator()
                + "addressList.add({ currentAddressInput ->" + System.lineSeparator()
                + "def addressVar = new org.test.Address()" + System.lineSeparator()
                + "addressVar.street = currentAddressInput.street" + System.lineSeparator()
                + "return addressVar" + System.lineSeparator()
                + "}(it))" + System.lineSeparator()
                + "}" + System.lineSeparator()
                + "return addressList");
    }

    @Test
    public void should_initialize_new_object_property_for_multiple_composed_reference_withoutExistingOnPool() throws Exception {
        final SimpleField streetField = aSimpleField().withName("street").ofType(FieldType.STRING).notNullable().build();
        final BusinessObject businessObject = aBO("org.test.Address").withField(streetField).build();
        final RelationField addressField = aCompositionField("address",
                businessObject);
        addressField.setCollection(true);
        final AbstractBusinessObjectInitializer propertyInitializer = new NewBusinessObjectListInitializer(addressField, aContractInput().withName("address")
                .multiple().in(aContractInput().withName("employee").withType(ContractInputType.COMPLEX)).build(), "myAddresses", true);
        propertyInitializer.addPropertyInitializer(new SimpleFieldPropertyInitializer(businessObject,
                streetField, aContractInput().withName("street")
                        .in(aContractInput().withName("address").withType(ContractInputType.COMPLEX).multiple()
                                .in(aContractInput().withName("employee").withType(ContractInputType.COMPLEX))).build()));
        assertThat(propertyInitializer.getInitialValue()).isEqualTo("def addressList = []" + System.lineSeparator()
                + "//For each item collected in multiple input" + System.lineSeparator()
                + "employee.address.each{" + System.lineSeparator()
                + "//Add a new composed Address instance" + System.lineSeparator()
                + "addressList.add({ currentAddressInput ->" + System.lineSeparator()
                + "def addressVar = new org.test.Address()" + System.lineSeparator()
                + "addressVar.street = currentAddressInput.street" + System.lineSeparator()
                + "return addressVar" + System.lineSeparator()
                + "}(it))" + System.lineSeparator()
                + "}" + System.lineSeparator()
                + "return addressList");
    }

    @Test
    public void should_not_add_existing_object_to_list_input_is_a_root_input() throws Exception {
        final SimpleField streetField = aSimpleField().withName("street").ofType(FieldType.STRING).notNullable().build();
        final BusinessObject businessObject = aBO("org.test.Address").withField(streetField).build();
        final RelationField addressField = aCompositionField("address",
                businessObject);
        addressField.setCollection(true);
        final AbstractBusinessObjectInitializer propertyInitializer = new NewBusinessObjectListInitializer(addressField, aContractInput().withName("addresses")
                .multiple().build(), "myAddress", false);
        propertyInitializer.addPropertyInitializer(new SimpleFieldPropertyInitializer(businessObject,
                streetField, aContractInput().withName("street")
                        .in(aContractInput().withName("address").withType(ContractInputType.COMPLEX).multiple()
                                .in(aContractInput().withName("employee").withType(ContractInputType.COMPLEX))).build()));
        assertThat(propertyInitializer.getInitialValue()).isEqualTo("def addressList = []" + System.lineSeparator()
                + "//For each item collected in multiple input" + System.lineSeparator()
                + "addresses.each{" + System.lineSeparator()
                + "//Add a new composed Address instance" + System.lineSeparator()
                + "addressList.add({ currentAddressInput ->" + System.lineSeparator()
                + "def addressVar = new org.test.Address()" + System.lineSeparator()
                + "addressVar.street = currentAddressInput.street" + System.lineSeparator()
                + "return addressVar" + System.lineSeparator()
                + "}(it))" + System.lineSeparator()
                + "}" + System.lineSeparator()
                + "return addressList");
    }

}
