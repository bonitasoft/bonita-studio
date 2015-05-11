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
import static org.assertj.core.api.Assertions.tuple;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.assertions.ContractInputAssert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class RelationFieldToContractInputMappingTest {

    @Test
    public void should_create_contract_input_from_a_composition_relation_field() throws Exception {
        final RelationFieldToContractInputMapping fieldToContractInputMapping = new RelationFieldToContractInputMapping(aRelationField("address",
                Type.COMPOSITION,
                aBusinessObject("Address", aSimpleField("number", FieldType.INTEGER), aSimpleField("street", FieldType.STRING))));

        final ContractInput input = fieldToContractInputMapping.toContractInput();

        ContractInputAssert.assertThat(input).hasName("address").hasType(ContractInputType.COMPLEX);
    }

    @Test
    public void should_create_contract_input_with_children_from_a_RelationFieldToContractInputMapping_with_children() throws Exception {
        final RelationField compositionField = aRelationField("address", Type.COMPOSITION,
                aBusinessObject("Address", aSimpleField("number", FieldType.INTEGER), aSimpleField("street", FieldType.STRING)));
        final RelationFieldToContractInputMapping fieldToContractInputMapping = new RelationFieldToContractInputMapping(compositionField);
        fieldToContractInputMapping.addChild(new SimpleFieldToContractInputMapping((SimpleField) compositionField.getReference().getFields().get(0)));
        fieldToContractInputMapping.addChild(new SimpleFieldToContractInputMapping((SimpleField) compositionField.getReference().getFields().get(1)));

        final ContractInput input = fieldToContractInputMapping.toContractInput();

        assertThat(input.getInputs()).extracting("name", "type", "inputs").contains(
                tuple("number", ContractInputType.INTEGER, Lists.newArrayList()),
                tuple("street", ContractInputType.TEXT, Lists.newArrayList()));
    }

    @Test
    public void should_create_contract_input_from_a_aggregation_relation_field() throws Exception {
        final RelationFieldToContractInputMapping fieldToContractInputMapping = new RelationFieldToContractInputMapping(aRelationField("country",
                Type.AGGREGATION,
                aBusinessObject("Country", aSimpleField("code", FieldType.INTEGER), aSimpleField("name", FieldType.STRING))));

        final ContractInput input = fieldToContractInputMapping.toContractInput();

        ContractInputAssert.assertThat(input).hasName("country").hasType(ContractInputType.TEXT).hasNoInputs();
    }

    private SimpleField aSimpleField(final String name, final FieldType type) {
        final SimpleField simpleField = new SimpleField();
        simpleField.setName(name);
        simpleField.setType(type);
        return simpleField;
    }

    private RelationField aRelationField(final String name, final Type relationType, final BusinessObject ref) {
        final RelationField field = new RelationField();
        field.setName(name);
        field.setReference(ref);
        field.setType(relationType);
        return field;
    }

    private BusinessObject aBusinessObject(final String name, final Field... fields) {
        final BusinessObject businessObject = new BusinessObject();
        businessObject.setQualifiedName(name);
        for (final Field field : fields) {
            businessObject.addField(field);
        }
        return businessObject;
    }
}
