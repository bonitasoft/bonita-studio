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
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.Lists;

/**
 * @author aurelie
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateContractInputFromBusinessObjectWizardPageTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_setAllFieldToInputContractMappingsGenerated_toTrue_whenSelectingAllFields() {
        BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        Contract contract = aContract().build();
        Pool process = aPool().havingContract(contract).build();
        process.getData().add(data);
        GenerationOptions options = new GenerationOptions();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = mock(
                BusinessObjectModelRepositoryStore.class);
        WritableValue<BusinessObjectData> selectedDataObservable = new WritableValue<>();
        selectedDataObservable.setValue(data);
        WritableValue<String> rootNameObservable = new WritableValue<>();
        rootNameObservable.setValue("employeeInput");
        WritableList<FieldToContractInputMapping> fieldToContractInputMappingsObservable = new WritableList<>();
        CreateContractInputFromBusinessObjectWizardPage page = new CreateContractInputFromBusinessObjectWizardPage(
                process, options,
                selectedDataObservable, new FieldToContractInputMappingFactory(store),
                fieldToContractInputMappingsObservable,
                store);
        FieldToContractInputMapping rootMapping1 = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(
                Mockito.mock(SimpleField.class));
        SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(
                Mockito.mock(SimpleField.class));
        rootMapping1.addChild(childMapping1);
        rootMapping1.addChild(childMapping2);
        FieldToContractInputMapping rootMapping2 = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        page.setMappings(Lists.newArrayList(rootMapping1, rootMapping2));
        WritableSet<FieldToContractInputMapping> checkedElements = new WritableSet<>();
        checkedElements.addAll(Arrays.asList(rootMapping1, childMapping2));
        SelectionAdapter listener = page.createSelectAllListener(checkedElements);
        listener.widgetSelected(mock(SelectionEvent.class));
        assertThat(rootMapping1.isGenerated()).isTrue();
        assertThat(childMapping1.isGenerated()).isTrue();
        assertThat(childMapping2.isGenerated()).isTrue();
        assertThat(rootMapping2.isGenerated()).isTrue();
    }

    @Test
    public void should_setAllFieldToInputContractMappingsGenerated_toFalse_whenDeselectingAllFields() {
        BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        Contract contract = aContract().build();
        Pool process = aPool().havingContract(contract).build();
        process.getData().add(data);
        GenerationOptions options = new GenerationOptions();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = mock(
                BusinessObjectModelRepositoryStore.class);
        WritableValue<BusinessObjectData> selectedDataObservable = new WritableValue<>();
        selectedDataObservable.setValue(data);
        WritableValue<String> rootNameObservable = new WritableValue<>();
        rootNameObservable.setValue("employeeInput");
        WritableList<FieldToContractInputMapping> fieldToContractInputMappingsObservable = new WritableList<>();
        CreateContractInputFromBusinessObjectWizardPage page = new CreateContractInputFromBusinessObjectWizardPage(
                process, options,
                selectedDataObservable, new FieldToContractInputMappingFactory(store),
                fieldToContractInputMappingsObservable,
                store);
        FieldToContractInputMapping rootMapping1 = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(
                Mockito.mock(SimpleField.class));
        SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(
                Mockito.mock(SimpleField.class));
        rootMapping1.addChild(childMapping1);
        rootMapping1.addChild(childMapping2);
        FieldToContractInputMapping rootMapping2 = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        page.setMappings(Lists.newArrayList(rootMapping1, rootMapping2));
        WritableSet<FieldToContractInputMapping> checkedElements = new WritableSet<>();
        checkedElements.addAll(Arrays.asList(rootMapping1, childMapping2));
        SelectionAdapter listener = page.createDeselectAllListener(checkedElements);
        listener.widgetSelected(mock(SelectionEvent.class));
        assertThat(rootMapping1.isGenerated()).isFalse();
        assertThat(childMapping1.isGenerated()).isFalse();
        assertThat(childMapping2.isGenerated()).isFalse();
        assertThat(rootMapping2.isGenerated()).isFalse();
    }

    @Test
    public void should_CheckedElementsList_Contains_AllFieldToInputContractMappingsGenerated_whenSelectingAllFields() {
        BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        Contract contract = aContract().build();
        Pool process = aPool().havingContract(contract).build();
        process.getData().add(data);
        GenerationOptions options = new GenerationOptions();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = mock(
                BusinessObjectModelRepositoryStore.class);
        WritableValue<BusinessObjectData> selectedDataObservable = new WritableValue<>();
        selectedDataObservable.setValue(data);
        WritableValue<String> rootNameObservable = new WritableValue<>();
        rootNameObservable.setValue("employeeInput");
        WritableList<FieldToContractInputMapping> fieldToContractInputMappingsObservable = new WritableList<>();
        CreateContractInputFromBusinessObjectWizardPage page = new CreateContractInputFromBusinessObjectWizardPage(
                process, options,
                selectedDataObservable, new FieldToContractInputMappingFactory(store),
                fieldToContractInputMappingsObservable,
                store);
        FieldToContractInputMapping rootMapping1 = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(
                Mockito.mock(SimpleField.class));
        SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(
                Mockito.mock(SimpleField.class));
        rootMapping1.addChild(childMapping1);
        rootMapping1.addChild(childMapping2);
        FieldToContractInputMapping rootMapping2 = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        WritableSet<FieldToContractInputMapping> checkedElements = new WritableSet<>();
        checkedElements.addAll(Arrays.asList(rootMapping1, childMapping2));
        page.setMappings(Lists.newArrayList(rootMapping1, rootMapping2));
        SelectionAdapter listener = page.createSelectAllListener(checkedElements);
        listener.widgetSelected(mock(SelectionEvent.class));
        assertThat(checkedElements.size()).isEqualTo(4);
        assertThat(checkedElements)
                .containsAll(Lists.newArrayList(rootMapping1, rootMapping2, childMapping1, childMapping2));
    }

    @Test
    public void should_selectAllMandatoryAttributes_whenClickingOnMandatoryAttributes() {
        BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        Contract contract = aContract().build();
        Pool process = aPool().havingContract(contract).build();
        process.getData().add(data);
        GenerationOptions options = new GenerationOptions();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = mock(
                BusinessObjectModelRepositoryStore.class);
        WritableValue<BusinessObjectData> selectedDataObservable = new WritableValue<>();
        selectedDataObservable.setValue(data);
        WritableValue<String> rootNameObservable = new WritableValue<>();
        rootNameObservable.setValue("employeeInput");
        WritableList<FieldToContractInputMapping> fieldToContractInputMappingsObservable = new WritableList<>();
        CreateContractInputFromBusinessObjectWizardPage page = new CreateContractInputFromBusinessObjectWizardPage(
                process, options,
                selectedDataObservable, new FieldToContractInputMappingFactory(store),
                fieldToContractInputMappingsObservable,
                store);
        RelationField rootMandatoryField = Mockito.mock(RelationField.class);
        RelationField rootOptionalField = Mockito.mock(RelationField.class);
        SimpleField childOptionalField = Mockito.mock(SimpleField.class);
        SimpleField childMandatoryField = Mockito.mock(SimpleField.class);
        when(rootMandatoryField.isNullable()).thenReturn(false);
        when(rootOptionalField.isNullable()).thenReturn(true);
        when(childOptionalField.isNullable()).thenReturn(true);
        when(childMandatoryField.isNullable()).thenReturn(false);
        FieldToContractInputMapping rootMappingMandatoryField = new RelationFieldToContractInputMapping(
                rootMandatoryField);
        SimpleFieldToContractInputMapping childMappingOptional = new SimpleFieldToContractInputMapping(
                childOptionalField);
        SimpleFieldToContractInputMapping childMappingMandatory = new SimpleFieldToContractInputMapping(
                childMandatoryField);
        rootMappingMandatoryField.addChild(childMappingOptional);
        rootMappingMandatoryField.addChild(childMappingMandatory);
        rootMappingMandatoryField.setGenerated(false);
        FieldToContractInputMapping rootMappingOptionalField = new RelationFieldToContractInputMapping(
                rootOptionalField);
        rootMappingOptionalField.setGenerated(false);
        childMappingOptional.setGenerated(false);
        childMappingMandatory.setGenerated(false);
        page.setMappings(Lists.newArrayList(rootMappingMandatoryField, rootMappingOptionalField));
        WritableSet<FieldToContractInputMapping> checkedElements = new WritableSet<>();
        SelectionAdapter listener = page.createMandatoryAttributesSelectionListener(checkedElements);
        listener.widgetSelected(mock(SelectionEvent.class));
        assertThat(checkedElements.size()).isEqualTo(2);
        assertThat(checkedElements.contains(rootMappingMandatoryField)).isTrue();
        assertThat(checkedElements.contains(rootMappingOptionalField)).isFalse();
        assertThat(checkedElements.contains(childMappingOptional)).isFalse();
        assertThat(checkedElements.contains(childMappingMandatory)).isTrue();
    }

}
