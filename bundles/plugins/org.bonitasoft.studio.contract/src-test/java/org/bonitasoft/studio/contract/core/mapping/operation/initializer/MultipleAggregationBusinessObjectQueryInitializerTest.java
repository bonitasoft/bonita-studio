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
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aStringField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.anAggregationField;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MultipleAggregationBusinessObjectQueryInitializerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_initialize_a_list_of_employees_retireved_with_a_query_by_persistenceId() throws Exception {
        final BusinessObject employeeBo = aBO("Employee").build();
        final SimpleField pIdField = new SimpleField();
        pIdField.setName(Field.PERSISTENCE_ID);
        pIdField.setType(FieldType.LONG);
        employeeBo.addField(pIdField);
        final SimpleField nameField = aStringField("name").build();
        employeeBo.addField(nameField);
        final RelationField employeesField = anAggregationField("employees", employeeBo);
        employeesField.setCollection(true);

        final InitializerContext context = new InitializerContext();
        final RelationFieldToContractInputMapping mapping = new RelationFieldToContractInputMapping(employeesField);
        context.setMapping(mapping);
        context.setData(aBusinessData().withName("myData").build());
        context.setContractInput(aContractInput().withName("employeeInput").withType(ContractInputType.COMPLEX).multiple()
                .havingInput(aContractInput()
                        .withName("persistenceId_string"))
                .build());
        context.setLocalVariableName("employeeVar");
        context.setLocalListVariableName("employeeList");

        final MultipleAggregationBusinessObjectQueryInitializer initializer = new MultipleAggregationBusinessObjectQueryInitializer(
                employeeBo,
                context);
        initializer.addPropertyInitializer(new SimpleFieldPropertyInitializer(employeeBo, nameField,
                aContractInput().withName("name")
                        .in(aContractInput().withName("employeeInput").withType(ContractInputType.COMPLEX).multiple())
                        .build()));

        final String initialValue = initializer.getInitialValue();

        assertThat(initialValue).isEqualTo("def employeeList = []" + System.lineSeparator()
                + "//For each item collected in multiple input" + System.lineSeparator()
                + "employeeInput.each{" + System.lineSeparator()
                + "//Add Employee instance" + System.lineSeparator()
                + "employeeList.add({ currentEmployeeInput ->" + System.lineSeparator()
                + "def employeeVar = employeeDAO.findByPersistenceId(currentEmployeeInput.persistenceId_string?.toLong())"
                + System.lineSeparator()
                + "if(!employeeVar) {"
                + System.lineSeparator()
                + "throw new IllegalArgumentException(\"The aggregated reference of type `Employee`  with the persistence id \" + currentEmployeeInput.persistenceId_string?.toLong() + \" has not been found.\")"
                + System.lineSeparator()
                + "}"
                + System.lineSeparator()
                + "employeeVar.name = currentEmployeeInput.name" + System.lineSeparator()
                + "return employeeVar" + System.lineSeparator()
                + "}(it))" + System.lineSeparator()
                + "}" + System.lineSeparator()
                + "return employeeList");
    }

    @Test
    public void should_throw_an_IllegalStateException_if_persistenceId_is_not_found() throws Exception {
        expectedException.expect(IllegalStateException.class);

        final InitializerContext context = new InitializerContext();
        context.setData(aBusinessData().withName("myData").build());
        context.setContractInput(aContractInput()
                .withName("notPersistenceId")
                .in(aContractInput().withName("employeeInput").withType(ContractInputType.COMPLEX).multiple()).build());
        context.setLocalVariableName("employeeVar");
        context.setLocalListVariableName("employeeList");

        new MultipleAggregationBusinessObjectQueryInitializer(null, context);
    }
}
