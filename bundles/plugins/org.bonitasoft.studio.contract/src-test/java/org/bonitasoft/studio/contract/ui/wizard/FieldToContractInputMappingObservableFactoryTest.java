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
