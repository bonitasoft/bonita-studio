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
import static org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingBuilder.aRelationMapping;
import static org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingBuilder.aSimpleMapping;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aCompositionField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aStringField;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingBuilder;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PropertyInitializerFactoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_create_a_SimpleFieldPropertyInitializer() throws Exception {
        final PropertyInitializerFactory factory = newFactory();

        final IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(aSimpleMapping(aStringField("name").build()).build(), aBusinessData()
                .withName("employee").build(), null);

        assertThat(propertyInitializer).isInstanceOf(SimpleFieldPropertyInitializer.class);
    }

    @Test
    public void should_create_a_SimpleFieldPropertyInitializer_with_a_parent_business_object() throws Exception {
        final PropertyInitializerFactory factory = newFactory();

        final RelationField employee = aCompositionField("employee", aBO("Employee").build());
        employee.setCollection(true);
        final FieldToContractInputMapping mapping = FieldToContractInputMappingBuilder
                .aRelationMapping(employee)
                .addChild(aSimpleMapping(aStringField("name").build())).build();
        final IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mapping.getChildren().get(0), aBusinessData()
                .withName("employee").build(), null);

        assertThat(propertyInitializer).isInstanceOf(SimpleFieldPropertyInitializer.class);
        assertThat(((SimpleFieldPropertyInitializer) propertyInitializer).getParentBusinessObject()).isNotNull();
    }

    @Test
    public void should_create_a_BusinessObjectInitializer() throws Exception {
        final PropertyInitializerFactory factory = newFactory();

        final RelationField aCompositionField = aCompositionField("employee", aBO("Employee").build());
        final FieldToContractInputMapping mapping = aRelationMapping(aCompositionField)
                .addChild(aSimpleMapping(aStringField("name").build())).build();
        final IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mapping, aBusinessData()
                .withName("employee").build(), aCompositionField);

        assertThat(propertyInitializer).isInstanceOf(BusinessObjectInitializer.class);
    }

    @Test
    public void should_create_a_CompositionReferencePropertyInitializer() throws Exception {
        final PropertyInitializerFactory factory = newFactory();

        final RelationField employeeField = aCompositionField("employee", aBO("Employee").build());
        final RelationField addressField = aCompositionField("address", aBO("Address").build());
        final FieldToContractInputMapping mapping =
                aRelationMapping(employeeField)
                        .addChild(aRelationMapping(addressField))
                        .addChild(aSimpleMapping(aStringField("street").build())).build();
        final IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mapping.getChildren().get(0), aBusinessData()
                .withName("employee").build(), employeeField);

        assertThat(propertyInitializer).isInstanceOf(CompositionReferencePropertyInitializer.class);
    }

    @Test
    public void should_create_a_BusinessObjectListInitializer() throws Exception {
        final PropertyInitializerFactory factory = newFactory();

        final RelationField aCompositionField = aCompositionField("employee", aBO("Employee").build());
        aCompositionField.setCollection(true);
        final FieldToContractInputMapping mapping = FieldToContractInputMappingBuilder
                .aRelationMapping(aCompositionField)
                .addChild(aSimpleMapping(aStringField("name").build())).build();
        final IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mapping, aBusinessData()
                .withName("employee").build(), aCompositionField);

        assertThat(propertyInitializer).isInstanceOf(BusinessObjectListInitializer.class);
    }

    @Test
    public void should_create_a_MultipleCompositionReferencePropertyInitializer() throws Exception {
        final PropertyInitializerFactory factory = newFactory();

        final RelationField employeeField = aCompositionField("employee", aBO("Employee").build());
        final RelationField addressField = aCompositionField("addresses", aBO("Address").build());
        addressField.setCollection(true);
        final FieldToContractInputMapping mapping =
                aRelationMapping(employeeField)
                        .addChild(aRelationMapping(addressField))
                        .addChild(aSimpleMapping(aStringField("street").build())).build();
        final IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mapping.getChildren().get(0), aBusinessData()
                .withName("employee").build(), employeeField);

        assertThat(propertyInitializer).isInstanceOf(MultipleCompositionReferencePropertyInitializer.class);
    }

    @Test
    public void should_throw_an_UnsupportedOperationException_for_unkown_field_type() throws Exception {
        final PropertyInitializerFactory factory = newFactory();

        thrown.expect(UnsupportedOperationException.class);
        factory.newPropertyInitializer(new FakeMapping(new Field() {
        }), aBusinessData().build(), null);
    }

    private PropertyInitializerFactory newFactory() {
        return new PropertyInitializerFactory();
    }

    class FakeMapping extends FieldToContractInputMapping {

        public FakeMapping(final Field field) {
            super(field);
        }

        @Override
        public String getFieldType() {
            return null;
        }

        @Override
        protected ContractInputType toContractInputType() {
            return null;
        }

    }
}
