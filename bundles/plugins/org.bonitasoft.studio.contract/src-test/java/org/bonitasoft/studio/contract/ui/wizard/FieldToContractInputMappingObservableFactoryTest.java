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
package org.bonitasoft.studio.contract.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.RelationFieldBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.junit.Rule;
import org.junit.Test;

public class FieldToContractInputMappingObservableFactoryTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_create_observable_from_list_of_fieldToInputContractMapping() {
        final List<FieldToContractInputMapping> list = new ArrayList<FieldToContractInputMapping>();
        final SimpleFieldToContractInputMapping simpleMapping = new SimpleFieldToContractInputMapping((SimpleField) SimpleFieldBuilder.aTextField("name")
                .build());
        list.add(simpleMapping);
        final RelationFieldToContractInputMapping relationMapping = new RelationFieldToContractInputMapping(
                (RelationField) RelationFieldBuilder.aCompositionField("manager",
                        BusinessObjectBuilder.aBO("com.compagny.Manager").build()));
        list.add(relationMapping);

        final FieldToContractInputMappingObservableFactory factory = new FieldToContractInputMappingObservableFactory();
        assertThat(factory.createObservable(list)).isInstanceOf(WritableList.class);
        assertThat((WritableList) factory.createObservable(list)).contains(simpleMapping, relationMapping);

    }

    @Test
    public void should_create_observable_from_relationfieldToInputContractMapping() {
        final Field firstName = SimpleFieldBuilder.aTextField("firstName").build();
        final Field lastName = SimpleFieldBuilder.aTextField("lastName").build();
        final FieldToContractInputMapping firstNameMapping = new SimpleFieldToContractInputMapping((SimpleField) firstName);
        final FieldToContractInputMapping lastNameMapping = new SimpleFieldToContractInputMapping((SimpleField) lastName);
        final RelationFieldToContractInputMapping relationMapping = new RelationFieldToContractInputMapping(
                (RelationField) RelationFieldBuilder.aCompositionField(
                        "manager",
                        BusinessObjectBuilder.aBO("com.compagny.Manager").withField(firstName)
                                .withField(lastName).build()));
        relationMapping.addChild(firstNameMapping);
        relationMapping.addChild(lastNameMapping);

        final FieldToContractInputMappingObservableFactory factory = new FieldToContractInputMappingObservableFactory();
        assertThat(factory.createObservable(relationMapping)).isInstanceOf(WritableList.class);
        assertThat((WritableList) factory.createObservable(relationMapping)).contains(firstNameMapping, lastNameMapping);

    }
}
