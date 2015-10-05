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

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
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
import org.mockito.runners.MockitoJUnitRunner;

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
        final BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        final Contract contract = aContract().build();
        final Pool process = aPool().havingContract(aContract()).build();
        process.getData().add(data);
        final GenerationOptions options = new GenerationOptions();
        final BusinessObjectModelRepositoryStore store = mock(BusinessObjectModelRepositoryStore.class);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(data);
        final WritableValue rootNameObservable = new WritableValue();
        rootNameObservable.setValue("employeeInput");
        final WritableList fieldToContractInputMappingsObservable = new WritableList();
        final CreateContractInputFromBusinessObjectWizardPage page = new CreateContractInputFromBusinessObjectWizardPage(contract, options,
                selectedDataObservable, rootNameObservable, new FieldToContractInputMappingFactory(), fieldToContractInputMappingsObservable, store);
        final FieldToContractInputMapping rootMapping1 = new RelationFieldToContractInputMapping(Mockito.mock(RelationField.class));
        final SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(Mockito.mock(SimpleField.class));
        final SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(Mockito.mock(SimpleField.class));
        rootMapping1.addChild(childMapping1);
        rootMapping1.addChild(childMapping2);
        final FieldToContractInputMapping rootMapping2 = new RelationFieldToContractInputMapping(Mockito.mock(RelationField.class));
        page.setMappings(Lists.newArrayList(rootMapping1, rootMapping2));
        final WritableSet checkedElements = new WritableSet();
        checkedElements.addAll(Lists.newArrayList(rootMapping1, childMapping2));
        final SelectionAdapter listener = page.createSelectAllListener(checkedElements);
        listener.widgetSelected(mock(SelectionEvent.class));
        assertThat(rootMapping1.isGenerated()).isTrue();
        assertThat(childMapping1.isGenerated()).isTrue();
        assertThat(childMapping2.isGenerated()).isTrue();
        assertThat(rootMapping2.isGenerated()).isTrue();
    }

    @Test
    public void should_setAllFieldToInputContractMappingsGenerated_toFalse_whenDeselectingAllFields() {
        final BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        final Contract contract = aContract().build();
        final Pool process = aPool().havingContract(aContract()).build();
        process.getData().add(data);
        final GenerationOptions options = new GenerationOptions();
        final BusinessObjectModelRepositoryStore store = mock(BusinessObjectModelRepositoryStore.class);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(data);
        final WritableValue rootNameObservable = new WritableValue();
        rootNameObservable.setValue("employeeInput");
        final WritableList fieldToContractInputMappingsObservable = new WritableList();
        final CreateContractInputFromBusinessObjectWizardPage page = new CreateContractInputFromBusinessObjectWizardPage(contract, options,
                selectedDataObservable, rootNameObservable, new FieldToContractInputMappingFactory(), fieldToContractInputMappingsObservable, store);
        final FieldToContractInputMapping rootMapping1 = new RelationFieldToContractInputMapping(Mockito.mock(RelationField.class));
        final SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(Mockito.mock(SimpleField.class));
        final SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(Mockito.mock(SimpleField.class));
        rootMapping1.addChild(childMapping1);
        rootMapping1.addChild(childMapping2);
        final FieldToContractInputMapping rootMapping2 = new RelationFieldToContractInputMapping(Mockito.mock(RelationField.class));
        page.setMappings(Lists.newArrayList(rootMapping1, rootMapping2));
        final WritableSet checkedElements = new WritableSet();
        checkedElements.addAll(Lists.newArrayList(rootMapping1, childMapping2));
        final SelectionAdapter listener = page.createDeselectAllListener(checkedElements);
        listener.widgetSelected(mock(SelectionEvent.class));
        assertThat(rootMapping1.isGenerated()).isFalse();
        assertThat(childMapping1.isGenerated()).isFalse();
        assertThat(childMapping2.isGenerated()).isFalse();
        assertThat(rootMapping2.isGenerated()).isFalse();
    }

    @Test
    public void should_CheckedElementsList_Contains_AllFieldToInputContractMappingsGenerated_whenSelectingAllFields() {
        final BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        final Contract contract = aContract().build();
        final Pool process = aPool().havingContract(aContract()).build();
        process.getData().add(data);
        final GenerationOptions options = new GenerationOptions();
        final BusinessObjectModelRepositoryStore store = mock(BusinessObjectModelRepositoryStore.class);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(data);
        final WritableValue rootNameObservable = new WritableValue();
        rootNameObservable.setValue("employeeInput");
        final WritableList fieldToContractInputMappingsObservable = new WritableList();
        final CreateContractInputFromBusinessObjectWizardPage page = new CreateContractInputFromBusinessObjectWizardPage(contract, options,
                selectedDataObservable, rootNameObservable, new FieldToContractInputMappingFactory(), fieldToContractInputMappingsObservable, store);
        final FieldToContractInputMapping rootMapping1 = new RelationFieldToContractInputMapping(Mockito.mock(RelationField.class));
        final SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(Mockito.mock(SimpleField.class));
        final SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(Mockito.mock(SimpleField.class));
        rootMapping1.addChild(childMapping1);
        rootMapping1.addChild(childMapping2);
        final FieldToContractInputMapping rootMapping2 = new RelationFieldToContractInputMapping(Mockito.mock(RelationField.class));
        final WritableSet checkedElements = new WritableSet();
        checkedElements.addAll(Lists.newArrayList(rootMapping1, childMapping2));
        page.setMappings(Lists.newArrayList(rootMapping1, rootMapping2));
        final SelectionAdapter listener = page.createSelectAllListener(checkedElements);
        listener.widgetSelected(mock(SelectionEvent.class));
        assertThat(checkedElements.size()).isEqualTo(4);
        assertThat(checkedElements.containsAll(Lists.newArrayList(rootMapping1, rootMapping2, childMapping1, childMapping2)));
    }

    @Test
    public void should_selectAllMandatoryAttributes_whenClickingOnMandatoryAttributes() {
        final BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        final Contract contract = aContract().build();
        final Pool process = aPool().havingContract(aContract()).build();
        process.getData().add(data);
        final GenerationOptions options = new GenerationOptions();
        final BusinessObjectModelRepositoryStore store = mock(BusinessObjectModelRepositoryStore.class);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(data);
        final WritableValue rootNameObservable = new WritableValue();
        rootNameObservable.setValue("employeeInput");
        final WritableList fieldToContractInputMappingsObservable = new WritableList();
        final CreateContractInputFromBusinessObjectWizardPage page = new CreateContractInputFromBusinessObjectWizardPage(contract, options,
                selectedDataObservable, rootNameObservable, new FieldToContractInputMappingFactory(), fieldToContractInputMappingsObservable, store);
        final RelationField rootField1 = Mockito.mock(RelationField.class);
        final RelationField rootField2 = Mockito.mock(RelationField.class);
        final SimpleField childField1 = Mockito.mock(SimpleField.class);
        final SimpleField childField2 = Mockito.mock(SimpleField.class);
        when(rootField1.isNullable()).thenReturn(true);
        when(rootField2.isNullable()).thenReturn(false);
        when(childField1.isNullable()).thenReturn(true);
        when(childField2.isNullable()).thenReturn(false);
        final FieldToContractInputMapping rootMapping1 = new RelationFieldToContractInputMapping(rootField1);
        final SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(childField1);
        final SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(childField2);
        rootMapping1.addChild(childMapping1);
        rootMapping1.addChild(childMapping2);
        rootMapping1.setGenerated(false);
        final FieldToContractInputMapping rootMapping2 = new RelationFieldToContractInputMapping(rootField2);
        rootMapping2.setGenerated(false);
        childMapping1.setGenerated(false);
        childMapping2.setGenerated(false);
        page.setMappings(Lists.newArrayList(rootMapping1, rootMapping2));
        final WritableSet checkedElements = new WritableSet();
        final SelectionAdapter listener = page.createMandatoryAttributesSelectionListener(checkedElements);
        listener.widgetSelected(mock(SelectionEvent.class));
        assertThat(checkedElements.size()).isEqualTo(3);
        assertThat(checkedElements.contains(rootMapping1)).isTrue();
        assertThat(checkedElements.contains(rootMapping2)).isTrue();
        assertThat(checkedElements.contains(childMapping1)).isFalse();
        assertThat(checkedElements.contains(childMapping2)).isTrue();
    }

}
