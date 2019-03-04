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
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aStringField;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.RelationFieldBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author aurelie
 */
@RunWith(MockitoJUnitRunner.class)
public class FieldToContractInputMappingFactoryTest {

    @Mock
    private BusinessObjectModelRepositoryStore businessObjectStore;

    @Test
    public void should_create_fieldMappingToContractInputMapping_from_simple_business_object() {
        BusinessObject businessObject = aBO("Employee")
                .withField(SimpleFieldBuilder.aStringField("firstName").build())
                .withField(SimpleFieldBuilder.aBooleanField("isValid")).build();
        BusinessObjectData businessObjectData = new BusinessObjectDataBuilder()
                .withClassname(businessObject.getQualifiedName()).build();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName()))
                .thenReturn(Optional.of(businessObject));
        FieldToContractInputMappingFactory factory = new FieldToContractInputMappingFactory(repositoryStore);

        List<FieldToContractInputMapping> mappings = factory.createMappingForBusinessObjectType(aPool().build(),
                businessObjectData);

        assertThat(mappings).extracting("field.name").containsOnly("firstName", "isValid");
    }

    @Test
    public void should_create_fieldMappingToContractInputMapping_from_complex_business_object_with_aggregation_field() {
        BusinessObject businessObject = aBO("Employee")
                .withField(RelationFieldBuilder.anAggregationField("manager", aBO("Manager").build())).build();
        BusinessObjectData businessObjectData = new BusinessObjectDataBuilder()
                .withClassname(businessObject.getQualifiedName()).build();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName()))
                .thenReturn(Optional.of(businessObject));
        FieldToContractInputMappingFactory factory = new FieldToContractInputMappingFactory(repositoryStore);

        List<FieldToContractInputMapping> mappings = factory.createMappingForBusinessObjectType(aPool().build(),
                businessObjectData);
        assertThat(mappings).extracting("field.name").containsOnly("manager");
    }

    @Test
    public void should_create_fieldMappingToContractInputMapping_from_complex_business_object_recursive_with_aggregation_field() {
        BusinessObject businessObject = aBO("Employee")
                .withField(RelationFieldBuilder.anAggregationField("manager", aBO("Employee").build())).build();
        BusinessObjectData businessObjectData = new BusinessObjectDataBuilder()
                .withClassname(businessObject.getQualifiedName()).build();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName()))
                .thenReturn(Optional.of(businessObject));
        FieldToContractInputMappingFactory factory = new FieldToContractInputMappingFactory(repositoryStore);

        List<FieldToContractInputMapping> mappings = factory.createMappingForBusinessObjectType(aPool().build(),
                businessObjectData);
        assertThat(mappings).extracting("field.name").containsOnly("manager");
    }

    @Test
    public void should_create_fieldMappingToContractInputMapping_from_complex_business_object_with_composition_field() {
        BusinessObject businessObject = aBO("Employee")
                .withField(
                        RelationFieldBuilder.aCompositionField(
                                "manager",
                                aBO("Manager").withField(SimpleFieldBuilder.aStringField("firstName").build())
                                        .withField(SimpleFieldBuilder.aBooleanField("isValid")).build()))
                .build();
        BusinessObjectData businessObjectData = new BusinessObjectDataBuilder()
                .withClassname(businessObject.getQualifiedName()).build();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName()))
                .thenReturn(Optional.of(businessObject));
        FieldToContractInputMappingFactory factory = new FieldToContractInputMappingFactory(repositoryStore);

        List<FieldToContractInputMapping> mappings = factory.createMappingForBusinessObjectType(aPool().build(),
                businessObjectData);
        assertThat(mappings).extracting("field.name").containsOnly("manager");
        assertThat(mappings.get(0).getChildren()).extracting("field.name").containsOnly("firstName", "isValid");
        mappings.get(0).getChildren().get(0).setGenerated(false);

    }

    @Test
    public void should_create_fieldMappingToContractInputMappingTree_withADepth_of_five_when_business_model_is_recursive() {
        BusinessObject businessObject = aBO("Employee").build();
        businessObject.addField(RelationFieldBuilder.aCompositionField("employee", businessObject));
        BusinessObjectData businessObjectData = new BusinessObjectDataBuilder()
                .withClassname(businessObject.getQualifiedName()).build();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName()))
                .thenReturn(Optional.of(businessObject));
        FieldToContractInputMappingFactory factory = new FieldToContractInputMappingFactory(repositoryStore);

        List<FieldToContractInputMapping> mappings = factory.createMappingForBusinessObjectType(aPool().build(),
                businessObjectData);
        assertThat(
                mappings.get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)
                        .getChildren().get(0).getChildren()
                        .isEmpty()).isTrue();
    }

    @Test
    public void should_create_fieldMappingToContractInputMappingTree_withADepth_of_five_when_business_model_is_recursive_with_agregation() {
        BusinessObject businessObject = aBO("Employee").build();
        businessObject.addField(RelationFieldBuilder.anAggregationField("employee", businessObject));
        BusinessObjectData businessObjectData = new BusinessObjectDataBuilder()
                .withClassname(businessObject.getQualifiedName()).build();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName()))
                .thenReturn(Optional.of(businessObject));
        FieldToContractInputMappingFactory factory = new FieldToContractInputMappingFactory(repositoryStore);

        List<FieldToContractInputMapping> mappings = factory.createMappingForBusinessObjectType(aPool().build(),
                businessObjectData);
        FieldToContractInputMapping lastMappingChild = mappings.get(0).getChildren().get(1).getChildren().get(1)
                .getChildren().get(1).getChildren().get(1).getChildren().get(1);
        assertThat(lastMappingChild.getChildren().size()).isEqualTo(1);
    }

    @Test
    public void should_create_a_persistenceId_mapping_for_aggregated_field() {
        BusinessObject businessObject = aBO("Employee").build();
        RelationField aggregatedField = RelationFieldBuilder.anAggregationField("employee", businessObject);
        businessObject.addField(aggregatedField);
        BusinessObjectData businessObjectData = new BusinessObjectDataBuilder()
                .withClassname(businessObject.getQualifiedName()).build();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName()))
                .thenReturn(Optional.of(businessObject));
        FieldToContractInputMappingFactory factory = new FieldToContractInputMappingFactory(repositoryStore);

        // Aggregation field not multiple on a task
        List<FieldToContractInputMapping> mappings = factory.createMappingForBusinessObjectType(aTask().build(),
                businessObjectData);
        assertThat(mappings).hasSize(1);
        assertThat(mappings.get(0)).isInstanceOf(RelationFieldToContractInputMapping.class);
        assertThat(((RelationFieldToContractInputMapping) mappings.get(0)).getChildren()).extracting("field.name")
                .contains("persistenceId_string");

        // Aggregation field not multiple on a pool
        mappings = factory.createMappingForBusinessObjectType(aPool().build(), businessObjectData);
        assertThat(((RelationFieldToContractInputMapping) mappings.get(0)).getChildren()).extracting("field.name")
                .contains("persistenceId_string");

        aggregatedField.setCollection(true);

        // Aggregation field multiple on a task
        mappings = factory.createMappingForBusinessObjectType(aTask().build(), businessObjectData);
        assertThat(((RelationFieldToContractInputMapping) mappings.get(0)).getChildren()).extracting("field.name")
                .contains("persistenceId_string");

        // Aggregation field multiple on a pool
        mappings = factory.createMappingForBusinessObjectType(aPool().build(), businessObjectData);
        assertThat(((RelationFieldToContractInputMapping) mappings.get(0)).getChildren()).extracting("field.name")
                .contains("persistenceId_string");
    }

    @Test
    public void should_create_a_persistenceId_mapping_for_multiple_composition_field_on_task() {
        BusinessObject employeeBusinessObject = aBO("Employee").build();
        BusinessObject addressBusinessObject = aBO("Address").build();
        RelationField addressCompositionField = RelationFieldBuilder.aCompositionField("address", addressBusinessObject);
        employeeBusinessObject.addField(addressCompositionField);
        BusinessObjectData businessObjectData = new BusinessObjectDataBuilder()
                .withClassname(employeeBusinessObject.getQualifiedName()).build();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName()))
                .thenReturn(Optional.of(employeeBusinessObject));
        FieldToContractInputMappingFactory factory = new FieldToContractInputMappingFactory(repositoryStore);

        // Composition field not multiple on a task
        List<FieldToContractInputMapping> mappings = factory.createMappingForBusinessObjectType(aTask().build(),
                businessObjectData);
        assertThat(((RelationFieldToContractInputMapping) mappings.get(0)).getChildren()).extracting("field.name")
                .doesNotContain("persistenceId_string");

        // Composition field not multiple on a pool
        mappings = factory.createMappingForBusinessObjectType(aPool().build(), businessObjectData);
        assertThat(((RelationFieldToContractInputMapping) mappings.get(0)).getChildren()).extracting("field.name")
                .doesNotContain("persistenceId_string");

        addressCompositionField.setCollection(true);

        // Composition field multiple on a task
        mappings = factory.createMappingForBusinessObjectType(aTask().build(), businessObjectData);
        assertThat(((RelationFieldToContractInputMapping) mappings.get(0)).getChildren()).extracting("field.name")
                .contains("persistenceId_string");

        // Composition field multiple on a pool
        mappings = factory.createMappingForBusinessObjectType(aPool().build(), businessObjectData);
        assertThat(((RelationFieldToContractInputMapping) mappings.get(0)).getChildren()).extracting("field.name")
                .doesNotContain("persistenceId_string");
    }

    @Test
    public void should_create_a_persistenceId_mapping_for_multiple_objects_on_tasks() {
        BusinessObject businessObject = aBO("Employee").withField(aStringField("name").build()).build();
        BusinessObjectData businessObjectData = new BusinessObjectDataBuilder()
                .multiple()
                .withClassname(businessObject.getQualifiedName()).build();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName()))
                .thenReturn(Optional.of(businessObject));
        FieldToContractInputMappingFactory factory = new FieldToContractInputMappingFactory(repositoryStore);

        // multiple on a pool
        List<FieldToContractInputMapping> mappings = factory.createMappingForBusinessObjectType(aPool().build(),
                businessObjectData);
        assertThat(mappings).extracting(FieldToContractInputMapping::getField).extracting(Field::getName)
                .containsExactly("name");

        // multiple on a task
        mappings = factory.createMappingForBusinessObjectType(aTask().build(), businessObjectData);
        assertThat(mappings).extracting(FieldToContractInputMapping::getField).extracting(Field::getName)
                .containsExactly("name", FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME);

        businessObjectData.setMultiple(false);

        // not multiple on a pool
        mappings = factory.createMappingForBusinessObjectType(aPool().build(), businessObjectData);
        assertThat(mappings).extracting(FieldToContractInputMapping::getField).extracting(Field::getName)
                .containsExactly("name");

        // not multiple on a task
        mappings = factory.createMappingForBusinessObjectType(aTask().build(), businessObjectData);
        assertThat(mappings).extracting(FieldToContractInputMapping::getField).extracting(Field::getName)
                .containsExactly("name");
    }

}
