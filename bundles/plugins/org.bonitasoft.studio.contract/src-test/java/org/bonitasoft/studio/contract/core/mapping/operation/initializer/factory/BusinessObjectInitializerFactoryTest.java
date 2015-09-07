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
package org.bonitasoft.studio.contract.core.mapping.operation.initializer.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingBuilder.aRelationMapping;
import static org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingBuilder.aSimpleMapping;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aCompositionField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aStringField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.anAggregationField;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;

import java.util.List;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.BusinessObjectQueryInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.IPropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.MultipleBusinessObjectQueryInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.NewBusinessObjectInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.NewBusinessObjectListInitializer;
import org.junit.Test;

public class BusinessObjectInitializerFactoryTest {

    @Test
    public void should_create_a_NewBusinessObjectInitializer() throws Exception {
        final BusinessObjectInitializerFactory factory = newFactory();

        final RelationField aCompositionField = aCompositionField("employee", aBO("Employee").build());
        final FieldToContractInputMapping mapping = aRelationMapping(aCompositionField)
                .addChild(aSimpleMapping(aStringField("name").build())).build();
        final IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mapping, aBusinessData().withName("employee").build(), false);

        assertThat(propertyInitializer).isInstanceOf(NewBusinessObjectInitializer.class);
    }

    @Test
    public void should_create_a_BusinessObjectListInitializer() throws Exception {
        final BusinessObjectInitializerFactory factory = newFactory();

        final RelationField aCompositionField = aCompositionField("employee", aBO("Employee").build());
        aCompositionField.setCollection(true);
        final FieldToContractInputMapping mapping = FieldToContractInputMappingBuilder
                .aRelationMapping(aCompositionField)
                .addChild(aSimpleMapping(aStringField("name").build())).build();
        final IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mapping, aBusinessData().withName("employee").build(), false);

        assertThat(propertyInitializer).isInstanceOf(NewBusinessObjectListInitializer.class);
    }

    @Test
    public void should_create_a_BusinessObjectQueryInitializer() throws Exception {
        final BusinessObjectInitializerFactory factory = newFactory();

        final BusinessObject businessObject = aBO("Employee").withField(anAggregationField("country", aBO("Country").build())).build();
        final List<FieldToContractInputMapping> mappings = new FieldToContractInputMappingFactory().createMappingForBusinessObjectType(businessObject);
        final IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mappings.get(0), aBusinessData().withName("employee").build(), false);

        assertThat(propertyInitializer).isInstanceOf(BusinessObjectQueryInitializer.class);
    }

    @Test
    public void should_create_a_MultipleBusinessObjectQueryInitializer() throws Exception {
        final BusinessObjectInitializerFactory factory = newFactory();
        final RelationField anAggregationField = anAggregationField("countries", aBO("Country").build());
        anAggregationField.setCollection(true);
        final BusinessObject businessObject = aBO("Employee").withField(anAggregationField).build();
        final List<FieldToContractInputMapping> mappings = new FieldToContractInputMappingFactory().createMappingForBusinessObjectType(businessObject);
        final IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mappings.get(0), aBusinessData()
                .withName("employee").build(), false);

        assertThat(propertyInitializer).isInstanceOf(MultipleBusinessObjectQueryInitializer.class);
    }

    private BusinessObjectInitializerFactory newFactory() {
        return new BusinessObjectInitializerFactory();
    }

}
