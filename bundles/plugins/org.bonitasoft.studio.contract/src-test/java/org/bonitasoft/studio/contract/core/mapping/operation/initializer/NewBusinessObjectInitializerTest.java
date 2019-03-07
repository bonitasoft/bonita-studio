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
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NewBusinessObjectInitializerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_create_groovy_script_as_initial_value() throws Exception {
        final InitializerContext context = new InitializerContext();
        final RelationFieldToContractInputMapping mapping = new RelationFieldToContractInputMapping(aCompositionField(
                "address", aBO("org.test.Address").build()));
        context.setMapping(mapping);
        context.setData(aBusinessData().withName("employee").build());
        context.setContractInput(aContractInput().withName("employee").multiple()
                .in(aContractInput().withName("employeeInput").withType(ContractInputType.COMPLEX).multiple())
                .build());
        context.setLocalVariableName("addressVar");
        context.setOnPool(true);
        context.setCheckExistence(true);

        final NewBusinessObjectInitializer propertyInitializer = new NewBusinessObjectInitializer(context);

        assertThat(propertyInitializer.getInitialValue()).isEqualTo(
                "def addressVar = employee.address ?: new org.test.Address()" + System.lineSeparator()
                        + "return addressVar");
    }

    @Test
    public void should_initialize_new_object_property_for_simple_composed_reference() throws Exception {
        final SimpleField streetField = aSimpleField().withName("street").ofType(FieldType.STRING).notNullable().build();

        final InitializerContext context = new InitializerContext();
        final RelationFieldToContractInputMapping mapping = new RelationFieldToContractInputMapping(
                aCompositionField("address",
                        aBO("org.test.Address").withField(streetField).build()));
        context.setMapping(mapping);
        context.setData(aBusinessData().withName("employee").build());
        context.setContractInput(aContractInput().withName("employee").multiple()
                .in(aContractInput().withName("employeeInput").withType(ContractInputType.COMPLEX).multiple())
                .build());
        context.setLocalVariableName("addressVar");
        context.setOnPool(true);
        context.setCheckExistence(true);

        final NewBusinessObjectInitializer propertyInitializer = new NewBusinessObjectInitializer(context);
        propertyInitializer.addPropertyInitializer(new SimpleFieldPropertyInitializer(null,
                streetField, aContractInput().withName("street")
                        .in(aContractInput().withName("address").withType(ContractInputType.COMPLEX)
                                .in(aContractInput().withName("employee").withType(ContractInputType.COMPLEX)))
                        .build()));
        assertThat(propertyInitializer.getInitialValue())
                .isEqualTo("def addressVar = employee.address ?: new org.test.Address()"
                        + System.lineSeparator()
                        + "addressVar.street = employee.address.street"
                        + System.lineSeparator()
                        + "return addressVar");
    }

}
