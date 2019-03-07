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
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.operation.VariableNameResolver;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.AggregationReferencePropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.CompositionReferencePropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.IPropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.MultipleAggregationReferencePropertyInitializer;
import org.bonitasoft.studio.contract.core.mapping.operation.initializer.MultipleCompositionReferencePropertyInitializer;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder;
import org.junit.Test;

public class RelationPropertyInitializerFactoryTest {

    @Test
    public void should_create_a_CompositionReferencePropertyInitializer() throws Exception {
        final RelationPropertyInitializerFactory factory = newFactory();

        final RelationField employeeField = aCompositionField("employee", aBO("Employee").build());
        final RelationField addressField = aCompositionField("address", aBO("Address").build());
        final FieldToContractInputMapping mapping = aRelationMapping(employeeField)
                .addChild(aRelationMapping(addressField))
                .addChild(aSimpleMapping(aStringField("street").build())).build();
        final IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mapping.getChildren().get(0),
                aBusinessData()
                        .withName("employee").build(),
                false);

        assertThat(propertyInitializer).isInstanceOf(CompositionReferencePropertyInitializer.class);
    }

    @Test
    public void should_create_a_MultipleCompositionReferencePropertyInitializer() throws Exception {
        final RelationPropertyInitializerFactory factory = newFactory();

        final RelationField employeeField = aCompositionField("employee", aBO("Employee").build());
        final RelationField addressField = aCompositionField("addresses", aBO("Address").build());
        addressField.setCollection(true);
        final FieldToContractInputMapping mapping = aRelationMapping(employeeField)
                .addChild(aRelationMapping(addressField))
                .addChild(aSimpleMapping(aStringField("street").build())).build();
        final IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mapping.getChildren().get(0),
                aBusinessData()
                        .withName("employee").build(),
                false);

        assertThat(propertyInitializer).isInstanceOf(MultipleCompositionReferencePropertyInitializer.class);
    }

    @Test
    public void should_create_a_AggregationReferencePropertyInitializer() throws Exception {
        RelationPropertyInitializerFactory factory = newFactory();

        BusinessObject businessObject = aBO("Employee").withField(anAggregationField("country", aBO("Country").build()))
                .build();
        BusinessObjectData businessObjectData = new BusinessObjectDataBuilder()
                .withClassname(businessObject.getQualifiedName()).build();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName()))
                .thenReturn(Optional.of(businessObject));
        List<FieldToContractInputMapping> mappings = new FieldToContractInputMappingFactory(repositoryStore)
                .createMappingForBusinessObjectType(aPool().build(), businessObjectData);
        IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mappings.get(0), aBusinessData()
                .withName("employee").build(), false);

        assertThat(propertyInitializer).isInstanceOf(AggregationReferencePropertyInitializer.class);
    }

    @Test
    public void should_create_a_MultipleAggregationReferencePropertyInitializer() throws Exception {
        RelationPropertyInitializerFactory factory = newFactory();

        RelationField anAggregationField = anAggregationField("countries", aBO("Country").build());
        anAggregationField.setCollection(true);
        BusinessObject businessObject = aBO("Employee").withField(anAggregationField).build();
        BusinessObjectData businessObjectData = new BusinessObjectDataBuilder()
                .withClassname(businessObject.getQualifiedName()).build();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName()))
                .thenReturn(Optional.of(businessObject));
        List<FieldToContractInputMapping> mappings = new FieldToContractInputMappingFactory(repositoryStore)
                .createMappingForBusinessObjectType(aPool().build(), businessObjectData);
        FieldToContractInputMapping mapping = mappings.get(0);
        IPropertyInitializer propertyInitializer = factory.newPropertyInitializer(mapping, businessObjectData,
                false);

        assertThat(propertyInitializer).isInstanceOf(MultipleAggregationReferencePropertyInitializer.class);
    }

    @Test
    public void should_check_existence_for_composition_field_with_multiple_parent_on_a_task() {
        RelationPropertyInitializerFactory factory = newFactory();

        BusinessObject businessObject = aBO("Employee").build();
        RelationField aCompositionField = aCompositionField("address", businessObject);
        businessObject.addField(aCompositionField);
        BusinessObjectData businessObjectData = new BusinessObjectDataBuilder()
                .withClassname(businessObject.getQualifiedName())
                .multiple()
                .build();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName()))
                .thenReturn(Optional.of(businessObject));
        List<FieldToContractInputMapping> mappings = new FieldToContractInputMappingFactory(repositoryStore)
                .createMappingForBusinessObjectType(aPool().build(), businessObjectData);
        FieldToContractInputMapping addressMapping = mappings.get(0);

        RelationField parentRelationField = new RelationField();
        parentRelationField.setCollection(true);
        parentRelationField.setType(Type.COMPOSITION);
        new RelationFieldToContractInputMapping(parentRelationField).addChild(addressMapping);

        assertThat(factory.checkExistence(addressMapping, false)).isTrue();
        assertThat(factory.checkExistence(addressMapping, true)).isFalse();
    }

    private RelationPropertyInitializerFactory newFactory() {
        return new RelationPropertyInitializerFactory(new VariableNameResolver());
    }

}
