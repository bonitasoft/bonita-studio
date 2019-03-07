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
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aSimpleField;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;

import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.junit.Test;

public class SimpleFieldPropertyInitializerTest {

    @Test
    public void should_return_name_of_the_property_to_initialize() throws Exception {
        final SimpleFieldPropertyInitializer initializer = new SimpleFieldPropertyInitializer(null,
                aSimpleField().withName("lastName").build(),
                aContractInput()
                        .build());

        assertThat(initializer.getPropertyName()).isEqualTo("lastName");
    }

    @Test
    public void should_return_a_groovy_expression_retrieving_contract_input_value() throws Exception {
        final SimpleFieldPropertyInitializer initializer = new SimpleFieldPropertyInitializer(null,
                aSimpleField().withName("lastName").ofType(FieldType.STRING)
                        .build(),
                aContractInput().withName("lastName")
                        .in(aContractInput().withName("employee").withType(ContractInputType.COMPLEX)).build());

        assertThat(initializer.getInitialValue()).isEqualTo("employee.lastName");
    }

    @Test
    public void should_return_a_groovy_expression_retrieving_contract_input_value_cast_to_long() throws Exception {
        final SimpleFieldPropertyInitializer initializer = new SimpleFieldPropertyInitializer(null,
                aSimpleField().withName("id").ofType(FieldType.LONG).build(), aContractInput().withName("id")
                        .in(aContractInput().withName("employee").withType(ContractInputType.COMPLEX)).build());

        assertThat(initializer.getInitialValue()).isEqualTo("employee.id?.toLong()");
    }

    @Test
    public void should_return_a_groovy_expression_retrieving_contract_input_value_cast_to_float() throws Exception {
        final SimpleFieldPropertyInitializer initializer = new SimpleFieldPropertyInitializer(
                null,
                aSimpleField().withName("salary").ofType(FieldType.FLOAT).build(), aContractInput().withName("salary")
                        .in(aContractInput().withName("employee").withType(ContractInputType.COMPLEX)).build());

        assertThat(initializer.getInitialValue()).isEqualTo("employee.salary?.toFloat()");
    }

    @Test
    public void should_return_a_groovy_expression_retrieving_contract_input_value_cast_to_float_list() throws Exception {
        final SimpleFieldPropertyInitializer initializer = new SimpleFieldPropertyInitializer(
                null,
                (SimpleField) aSimpleField().withName("grades").ofType(FieldType.FLOAT).multiple().build(),
                aContractInput().withName("grades").multiple()
                        .in(aContractInput().withName("employee").withType(ContractInputType.COMPLEX)).build());

        assertThat(initializer.getInitialValue()).isEqualTo("employee.grades?.collect{ it.toFloat() }");
    }

    @Test
    public void should_return_a_groovy_expression_retrieving_contract_input_value_cast_to_long_list() throws Exception {
        final SimpleFieldPropertyInitializer initializer = new SimpleFieldPropertyInitializer(
                null,
                aSimpleField().withName("ids").ofType(FieldType.LONG).build(), aContractInput().withName("ids").multiple()
                        .in(aContractInput().withName("employee").withType(ContractInputType.COMPLEX)).build());

        assertThat(initializer.getInitialValue()).isEqualTo("employee.ids?.collect{ it.toLong() }");
    }
}
