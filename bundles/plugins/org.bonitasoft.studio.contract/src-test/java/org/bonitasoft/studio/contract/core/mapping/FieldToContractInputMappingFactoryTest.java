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
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.RelationFieldBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
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
        doReturn(aBO("Employee")
                .withField(SimpleFieldBuilder.aStringField("firstName").build())
                .withField(SimpleFieldBuilder.aBooleanField("isValid")).build()).when(businessObjectStore)
                .getBusinessObjectByQualifiedName("Employee");
        final FieldToContractInputMappingFactory factory = new FieldToContractInputMappingFactory(businessObjectStore);

        final List<FieldToContractInputMapping> mappings = factory.createMappingForBusinessObjectType("Employee");

        assertThat(mappings).extracting("field.name").containsOnly("firstName", "isValid");
    }

    @Test
    public void should_create_fieldMappingToContractInputMapping_from_complex_business_object_with_aggregation_field() {
        doReturn(aBO("Employee")
                .withField(RelationFieldBuilder.anAggregationField("manager", aBO("Manager").build())).build()).when(businessObjectStore)
                .getBusinessObjectByQualifiedName("Employee");

        final FieldToContractInputMappingFactory factory = new FieldToContractInputMappingFactory(businessObjectStore);

        final List<FieldToContractInputMapping> mappings = factory.createMappingForBusinessObjectType("Employee");
        assertThat(mappings).extracting("field.name").containsOnly("manager");
    }

    @Test
    public void should_create_fieldMappingToContractInputMapping_from_complex_business_object_with_composition_field() {
        doReturn(
                aBO("Employee")
                        .withField(
                                RelationFieldBuilder.aCompositionField(
                                        "manager",
                                        aBO("Manager").withField(SimpleFieldBuilder.aStringField("firstName").build())
                                                .withField(SimpleFieldBuilder.aBooleanField("isValid")).build())).build()).when(businessObjectStore)
                .getBusinessObjectByQualifiedName("Employee");

        final FieldToContractInputMappingFactory factory = new FieldToContractInputMappingFactory(businessObjectStore);

        final List<FieldToContractInputMapping> mappings = factory.createMappingForBusinessObjectType("Employee");
        assertThat(mappings).extracting("field.name").containsOnly("manager");
        assertThat(mappings.get(0).getChildren()).extracting("field.name").containsOnly("firstName", "isValid");
        mappings.get(0).getChildren().get(0).setGenerated(false);

    }

}
