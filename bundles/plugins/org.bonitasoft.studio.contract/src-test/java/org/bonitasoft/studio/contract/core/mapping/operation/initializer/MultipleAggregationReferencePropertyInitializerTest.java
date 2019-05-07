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
import org.junit.Test;

public class MultipleAggregationReferencePropertyInitializerTest {

    @Test
    public void should_embbed_aggregation_list_in_a_closure() throws Exception {
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
        context.setData(aBusinessData().withName("emp").build());
        context.setContractInput(aContractInput().withName("persistenceId_string")
                .in(aContractInput().withName("employees").withType(ContractInputType.COMPLEX).multiple()).build());
        context.setLocalVariableName("employeeVar");
        context.setLocalListVariableName("employeeList");

        final MultipleAggregationReferencePropertyInitializer initializer = new MultipleAggregationReferencePropertyInitializer(
                null, employeeBo, context);

        final String initialValue = initializer.getInitialValue();

        assertThat(initialValue).isEqualTo("{" + System.lineSeparator()
                + "def employeeList = []" + System.lineSeparator()
                + "//For each item collected in multiple input" + System.lineSeparator()
                + "employees.each{" + System.lineSeparator()
                + "//Add Employee instance" + System.lineSeparator()
                + "employeeList.add({ currentEmployeeInput ->" + System.lineSeparator()
                + "def employeeVar = employeeDAO.findByPersistenceId(currentEmployeeInput.persistenceId_string?.find()?.toLong())"
                + System.lineSeparator()
                + "if(!employeeVar) {"
                + System.lineSeparator()
                + "throw new IllegalArgumentException(\"The aggregated reference of type `Employee`  with the persistence id \" + currentEmployeeInput.persistenceId_string?.find()?.toLong() + \" has not been found.\")"
                + System.lineSeparator()
                + "}"
                + System.lineSeparator()
                + "return employeeVar" + System.lineSeparator()
                + "}(it))" + System.lineSeparator()
                + "}" + System.lineSeparator()
                + "return employeeList}()");
    }

    @Test
    public void should_embbed_aggregation_list_in_a_closure_WithoutExistingOnAPool() throws Exception {
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
        context.setData(aBusinessData().withName("emp").build());
        context.setContractInput(aContractInput().withName("persistenceId_string")
                .in(aContractInput().withName("employees").withType(ContractInputType.COMPLEX).multiple()).build());
        context.setLocalVariableName("employeeVar");
        context.setLocalListVariableName("employeeList");
        context.setCreateMode(true);

        final MultipleAggregationReferencePropertyInitializer initializer = new MultipleAggregationReferencePropertyInitializer(
                null, employeeBo, context);
        final String initialValue = initializer.getInitialValue();

        assertThat(initialValue).isEqualTo("{" + System.lineSeparator()
                + "def employeeList = []" + System.lineSeparator()
                + "//For each item collected in multiple input" + System.lineSeparator()
                + "employees.each{" + System.lineSeparator()
                + "//Add Employee instance" + System.lineSeparator()
                + "employeeList.add({ currentEmployeeInput ->" + System.lineSeparator()
                + "def employeeVar = employeeDAO.findByPersistenceId(currentEmployeeInput.persistenceId_string?.find()?.toLong())"
                + System.lineSeparator()
                + "if(!employeeVar) {"
                + System.lineSeparator()
                + "throw new IllegalArgumentException(\"The aggregated reference of type `Employee`  with the persistence id \" + currentEmployeeInput.persistenceId_string?.find()?.toLong() + \" has not been found.\")"
                + System.lineSeparator()
                + "}"
                + System.lineSeparator()
                + "return employeeVar" + System.lineSeparator()
                + "}(it))" + System.lineSeparator()
                + "}" + System.lineSeparator()
                + "return employeeList}()");
    }

    @Test
    public void should_use_parent_as_iterator() throws Exception {
        final BusinessObject directoryBo = aBO("Directory").build();
        final BusinessObject employeeBo = aBO("Employee").build();
        final SimpleField pIdField = new SimpleField();
        pIdField.setName(Field.PERSISTENCE_ID);
        pIdField.setType(FieldType.LONG);
        employeeBo.addField(pIdField);
        final SimpleField nameField = aStringField("name").build();
        employeeBo.addField(nameField);
        final RelationField employeesField = anAggregationField("employees", employeeBo);
        employeesField.setCollection(true);
        directoryBo.addField(employeesField);

        final InitializerContext context = new InitializerContext();
        final RelationFieldToContractInputMapping mapping = new RelationFieldToContractInputMapping(employeesField);
        context.setMapping(mapping);
        context.setData(aBusinessData().withName("emp").build());
        context.setContractInput(aContractInput().withName("employees").withType(ContractInputType.COMPLEX).multiple()
                .havingInput(aContractInput().withName("persistenceId_string"))
                .in(aContractInput().withName("direcotries").withType(ContractInputType.COMPLEX).multiple()).build());
        context.setLocalVariableName("employeeVar");
        context.setLocalListVariableName("employeeList");

        final MultipleAggregationReferencePropertyInitializer initializer = new MultipleAggregationReferencePropertyInitializer(
                directoryBo, employeeBo,
                context);
        final String initialValue = initializer.getInitialValue();

        assertThat(initialValue).isEqualTo("{" + System.lineSeparator()
                + "def employeeList = []" + System.lineSeparator()
                + "//For each item collected in multiple input" + System.lineSeparator()
                + "currentDirectoryInput.employees.each{" + System.lineSeparator()
                + "//Add Employee instance" + System.lineSeparator()
                + "employeeList.add({ currentEmployeeInput ->" + System.lineSeparator()
                + "def employeeVar = employeeDAO.findByPersistenceId(currentEmployeeInput.persistenceId_string?.find()?.toLong())"
                + System.lineSeparator()
                + "if(!employeeVar) {"
                + System.lineSeparator()
                + "throw new IllegalArgumentException(\"The aggregated reference of type `Employee`  with the persistence id \" + currentEmployeeInput.persistenceId_string?.find()?.toLong() + \" has not been found.\")"
                + System.lineSeparator()
                + "}"
                + System.lineSeparator()
                + "return employeeVar" + System.lineSeparator()
                + "}(it))" + System.lineSeparator()
                + "}" + System.lineSeparator()
                + "return employeeList}()");

    }
}
