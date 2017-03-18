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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.junit.Test;

/**
 * @author aurelie
 */
public class SimpleFieldToContractInputMappingTest {

    @Test
    public void should_have_a_child() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                new SimpleField());

        final SimpleFieldToContractInputMapping childField = new SimpleFieldToContractInputMapping(new SimpleField());
        fieldToContractInputMapping.addChild(childField);

        assertThat(fieldToContractInputMapping.getChildren()).contains(childField);
    }

    @Test
    public void should_childField_have_a_parent() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                new SimpleField());

        final SimpleFieldToContractInputMapping childField = new SimpleFieldToContractInputMapping(new SimpleField());
        fieldToContractInputMapping.addChild(childField);

        assertThat(childField.getParent()).isEqualTo(fieldToContractInputMapping);
    }

    @Test
    public void should_create_contract_input_from_a_string_simple_field() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                aSimpleField("firstName", FieldType.STRING));

        final ContractInput input = fieldToContractInputMapping.toContractInput(null);

        ContractInputAssert.assertThat(input).hasName("firstName").hasType(ContractInputType.TEXT).hasNoInputs();
    }

    @Test
    public void should_create_contract_input_from_a_long_simple_field() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                aSimpleField("timeStamp", FieldType.LONG));

        final ContractInput input = fieldToContractInputMapping.toContractInput(null);

        ContractInputAssert.assertThat(input).hasName("timeStamp").hasType(ContractInputType.TEXT).hasNoInputs();
    }

    @Test
    public void should_create_contract_input_from_a_boolean_simple_field() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                aSimpleField("isValid", FieldType.BOOLEAN));

        final ContractInput input = fieldToContractInputMapping.toContractInput(null);

        ContractInputAssert.assertThat(input).hasName("isValid").hasType(ContractInputType.BOOLEAN).hasNoInputs();
    }

    @Test
    public void should_create_contract_input_from_a_double_simple_field() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                aSimpleField("rate", FieldType.DOUBLE));

        final ContractInput input = fieldToContractInputMapping.toContractInput(null);

        ContractInputAssert.assertThat(input).hasName("rate").hasType(ContractInputType.DECIMAL).hasNoInputs();
    }

    @Test
    public void should_create_contract_input_from_a_date_simple_field() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                aSimpleField("date", FieldType.DATE));

        final ContractInput input = fieldToContractInputMapping.toContractInput(null);

        ContractInputAssert.assertThat(input).hasName("date").hasType(ContractInputType.DATE).hasNoInputs();
    }

    @Test
    public void should_create_contract_input_from_a_float_simple_field() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                aSimpleField("amount", FieldType.FLOAT));

        final ContractInput input = fieldToContractInputMapping.toContractInput(null);

        ContractInputAssert.assertThat(input).hasName("amount").hasType(ContractInputType.DECIMAL).hasNoInputs();
    }

    @Test
    public void should_create_contract_input_from_a_text_simple_field() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                aSimpleField("description", FieldType.TEXT));

        final ContractInput input = fieldToContractInputMapping.toContractInput(null);

        ContractInputAssert.assertThat(input).hasName("description").hasType(ContractInputType.TEXT).hasNoInputs();
    }

    @Test
    public void should_create_contract_input_from_an_integer_simple_field() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                aSimpleField("age", FieldType.INTEGER));

        final ContractInput input = fieldToContractInputMapping.toContractInput(null);

        ContractInputAssert.assertThat(input).hasName("age").hasType(ContractInputType.INTEGER).hasNoInputs();
    }

    @Test
    public void should_create_a_multiple_contract_input() throws Exception {
        final SimpleField multipleField = aSimpleField("messages", FieldType.STRING);
        multipleField.setCollection(true);

        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                multipleField);

        final ContractInput input = fieldToContractInputMapping.toContractInput(null);

        ContractInputAssert.assertThat(input).hasName("messages").hasType(ContractInputType.TEXT).hasNoInputs().isMultiple();
    }

    @Test
    public void should_return_field_type() throws Exception {
        final SimpleField messageField = aSimpleField("message", FieldType.STRING);

        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                messageField);

        assertThat(fieldToContractInputMapping.getFieldType()).isEqualTo(String.class.getName());
    }

    @Test
    public void should_return_field_type_for_multiple_fields() throws Exception {
        final SimpleField messageField = aSimpleField("message", FieldType.STRING);
        messageField.setCollection(true);
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                messageField);

        assertThat(fieldToContractInputMapping.getFieldType()).isEqualTo(List.class.getName());
    }

    @Test
    public void should_create_contract_input_from_a_localdate_simple_field() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                aSimpleField("date", FieldType.LOCALDATE));

        final ContractInput input = fieldToContractInputMapping.toContractInput(null);

        ContractInputAssert.assertThat(input).hasName("date").hasType(ContractInputType.LOCALDATE).hasNoInputs();
    }

    @Test
    public void should_create_contract_input_from_a_localdatetime_simple_field() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                aSimpleField("date", FieldType.LOCALDATETIME));

        final ContractInput input = fieldToContractInputMapping.toContractInput(null);

        ContractInputAssert.assertThat(input).hasName("date").hasType(ContractInputType.LOCALDATETIME).hasNoInputs();
    }

    @Test
    public void should_create_contract_input_from_a_offsetdatetime_simple_field() throws Exception {
        final SimpleFieldToContractInputMapping fieldToContractInputMapping = new SimpleFieldToContractInputMapping(
                aSimpleField("date", FieldType.OFFSETDATETIME));

        final ContractInput input = fieldToContractInputMapping.toContractInput(null);

        ContractInputAssert.assertThat(input).hasName("date").hasType(ContractInputType.OFFSETDATETIME).hasNoInputs();
    }

    private SimpleField aSimpleField(final String name, final FieldType type) {
        final SimpleField simpleField = new SimpleField();
        simpleField.setName(name);
        simpleField.setType(type);
        return simpleField;
    }
}
