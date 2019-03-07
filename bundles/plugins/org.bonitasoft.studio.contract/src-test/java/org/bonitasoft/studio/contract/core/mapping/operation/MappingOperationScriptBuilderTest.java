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
package org.bonitasoft.studio.contract.core.mapping.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aCompositionField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aSimpleField;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.eclipse.swt.SWT;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MappingOperationScriptBuilderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_not_need_data_dependency_for_a_simple_mapping() throws Exception {
        final SimpleField firstNameField = aSimpleField().withName("firstName").ofType(FieldType.TEXT).build();
        final MappingOperationScriptBuilder scriptBuilder = new MappingOperationScriptBuilder(
                aBusinessData().withName("employee").build(),
                new SimpleFieldToContractInputMapping(firstNameField));

        final String script = scriptBuilder.toScript();

        assertThat(scriptBuilder.needsDataDependency()).isFalse();
        assertThat(script).isEqualTo("firstName");
    }

    @Test
    public void should_need_data_dependency_for_a_relation_mapping() throws Exception {
        final SimpleField streetField = aSimpleField().withName("street").ofType(FieldType.TEXT).build();
        final RelationField addressField = aCompositionField("address", aBO("Address").withField(streetField).build());
        final RelationFieldToContractInputMapping relationFieldToContractInputMapping = new RelationFieldToContractInputMapping(
                addressField);
        relationFieldToContractInputMapping.addChild(new SimpleFieldToContractInputMapping(streetField));
        final MappingOperationScriptBuilder scriptBuilder = new MappingOperationScriptBuilder(
                aBusinessData().withName("employee").build(),
                relationFieldToContractInputMapping);

        final String script = scriptBuilder.toScript();

        assertThat(scriptBuilder.needsDataDependency()).isTrue();
        assertThat(script).isEqualTo("def addressVar = new Address()" + System.lineSeparator()
                + "addressVar.street = address.street" + System.lineSeparator()
                + "return addressVar");
    }

    @Test
    public void should_not_add_child_initializer_if_mappping_is_not_generated() throws Exception {
        final SimpleField streetField = aSimpleField().withName("street").ofType(FieldType.TEXT).build();
        final RelationField addressField = aCompositionField("address", aBO("Address").withField(streetField).build());
        final RelationFieldToContractInputMapping relationFieldToContractInputMapping = new RelationFieldToContractInputMapping(
                addressField);
        final SimpleFieldToContractInputMapping child = new SimpleFieldToContractInputMapping(streetField);
        child.setGenerated(false);
        relationFieldToContractInputMapping.addChild(child);
        final MappingOperationScriptBuilder scriptBuilder = new MappingOperationScriptBuilder(
                aBusinessData().withName("employee").build(),
                relationFieldToContractInputMapping);

        final String script = scriptBuilder.toScript();

        assertThat(script).isEqualTo("def addressVar = new Address()" + System.lineSeparator()
                + "return addressVar");
    }

    @Test
    public void should_generate_a_closure_for_deep_relation_mapping() throws Exception {
        final SimpleField streetField = aSimpleField().withName("street").ofType(FieldType.TEXT).build();
        final RelationField countryField = aCompositionField("country", aBO("Country").build());
        final RelationField addressField = aCompositionField("address",
                aBO("Address").withField(streetField).withField(countryField).build());
        final RelationFieldToContractInputMapping relationFieldToContractInputMapping = new RelationFieldToContractInputMapping(
                addressField);
        relationFieldToContractInputMapping.addChild(new SimpleFieldToContractInputMapping(streetField));
        relationFieldToContractInputMapping.addChild(new RelationFieldToContractInputMapping(countryField));
        final MappingOperationScriptBuilder scriptBuilder = new MappingOperationScriptBuilder(
                aBusinessData().withName("employee").build(),
                relationFieldToContractInputMapping);

        final String script = scriptBuilder.toScript();

        assertThat(script).isEqualTo("def addressVar = new Address()" + System.lineSeparator()
                + "addressVar.street = address.street" + System.lineSeparator()
                + "addressVar.country = {" + System.lineSeparator()
                + SWT.TAB + "def countryVar = addressVar.country ?: new Country()" + System.lineSeparator()
                + SWT.TAB + "return countryVar}()" + System.lineSeparator()
                + "return addressVar");
    }

    @Test
    public void should_throw_an_UnsupportedOperationException_for_unsupported_field_type() throws Exception {
        final Field field = new Field() {
        };
        final FieldToContractInputMapping mapping = new FieldToContractInputMapping(field) {

            @Override
            protected ContractInputType toContractInputType() {
                return null;
            }

            @Override
            public String getFieldType() {
                return null;
            }
        };

        final MappingOperationScriptBuilder scriptBuilder = new MappingOperationScriptBuilder(
                aBusinessData().withName("employee").build(),
                mapping);

        thrown.expect(UnsupportedOperationException.class);
        scriptBuilder.toScript();
    }
}
