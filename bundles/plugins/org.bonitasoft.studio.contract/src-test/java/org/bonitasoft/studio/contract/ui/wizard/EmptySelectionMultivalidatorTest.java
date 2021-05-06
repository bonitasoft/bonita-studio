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
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.contract.ui.wizard.GenerationOptions.EditMode;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.core.databinding.observable.value.WritableValue;
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
public class EmptySelectionMultivalidatorTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void shouldReturnErrorWhenNoElementsAreChecked() {
        final IObservableSet checkedElements = new WritableSet();
        final WritableValue selectedDataObservable = new WritableValue();
        final WritableValue currentPageObservable = new WritableValue();
        currentPageObservable.setValue(mock(CreateContractInputFromBusinessObjectWizardPage.class));
        final BusinessObjectData businessObjectData = aBusinessData().withClassname("org.test.Employee").build();
        selectedDataObservable.setValue(businessObjectData);
        final EmptySelectionMultivalidator multiValidator = new EmptySelectionMultivalidator(selectedDataObservable,
                checkedElements,
                new ArrayList<FieldToContractInputMapping>(), aPool().build(),
                new WritableValue<GenerationOptions.EditMode>(EditMode.CREATE, EditMode.class), currentPageObservable);
        assertThat(multiValidator.validate().isOK()).isFalse();
    }

    @Test
    public void shouldReturnOkStatusWhenAtLeastOneElementIsChecked() {
        final IObservableSet checkedElements = new WritableSet();
        checkedElements.add(mock(FieldToContractInputMapping.class));
        final WritableValue selectedDataObservable = new WritableValue();
        final WritableValue currentPageObservable = new WritableValue();
        currentPageObservable.setValue(mock(CreateContractInputFromBusinessObjectWizardPage.class));
        final BusinessObjectData businessObjectData = aBusinessData().withClassname("org.test.Employee").build();
        selectedDataObservable.setValue(businessObjectData);
        final EmptySelectionMultivalidator multiValidator = new EmptySelectionMultivalidator(selectedDataObservable,
                checkedElements,
                new ArrayList<FieldToContractInputMapping>(), aPool().build(),
                new WritableValue<GenerationOptions.EditMode>(EditMode.CREATE, EditMode.class), 
                currentPageObservable);
        assertThat(multiValidator.validate().isOK()).isTrue();
    }

    @Test
    public void shouldReturnWarningWhenOneElementIsMandatoryAndNotChecked() {
        final IObservableSet checkedElements = new WritableSet();
        final FieldToContractInputMapping rootMapping1 = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        final SimpleField simpleField1 = new SimpleField();
        simpleField1.setNullable(false);
        final SimpleField simpleField2 = new SimpleField();
        simpleField2.setNullable(true);
        final SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(simpleField1);
        childMapping1.setGenerated(false);
        final SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(simpleField2);
        rootMapping1.addChild(childMapping1);
        rootMapping1.addChild(childMapping2);
        final FieldToContractInputMapping rootMapping2 = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        checkedElements.add(rootMapping1);
        checkedElements.add(rootMapping2);
        checkedElements.add(childMapping2);
        final WritableValue selectedDataObservable = new WritableValue();
        final BusinessObjectData businessObjectData = aBusinessData().withClassname("org.test.Employee").build();
        selectedDataObservable.setValue(businessObjectData);
        final WritableValue currentPageObservable = new WritableValue();
        currentPageObservable.setValue(mock(CreateContractInputFromBusinessObjectWizardPage.class));
        final EmptySelectionMultivalidator multiValidator = new EmptySelectionMultivalidator(selectedDataObservable,
                checkedElements,
                Lists.newArrayList(rootMapping1, rootMapping2), aPool().build(),
                new WritableValue<GenerationOptions.EditMode>(EditMode.CREATE, EditMode.class),currentPageObservable);
        assertThat(multiValidator.validate().isOK()).isFalse();
    }

    @Test
    public void shouldReturnOkWhenElementIsMandatoryAndChecked() {
        final IObservableSet checkedElements = new WritableSet();
        final FieldToContractInputMapping rootMapping1 = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        final SimpleField simpleField1 = new SimpleField();
        simpleField1.setNullable(false);
        final SimpleField simpleField2 = new SimpleField();
        simpleField2.setNullable(true);
        final SimpleFieldToContractInputMapping childMapping1 = new SimpleFieldToContractInputMapping(simpleField1);
        childMapping1.setGenerated(true);
        final SimpleFieldToContractInputMapping childMapping2 = new SimpleFieldToContractInputMapping(simpleField2);
        rootMapping1.addChild(childMapping1);
        rootMapping1.addChild(childMapping2);
        final FieldToContractInputMapping rootMapping2 = new RelationFieldToContractInputMapping(
                Mockito.mock(RelationField.class));
        checkedElements.add(rootMapping1);
        checkedElements.add(rootMapping2);
        checkedElements.add(childMapping2);
        checkedElements.add(childMapping1);
        final WritableValue selectedDataObservable = new WritableValue();
        final BusinessObjectData businessObjectData = aBusinessData().withClassname("org.test.Employee").build();
        selectedDataObservable.setValue(businessObjectData);
        final WritableValue currentPageObservable = new WritableValue();
        currentPageObservable.setValue(mock(CreateContractInputFromBusinessObjectWizardPage.class));
        final EmptySelectionMultivalidator multiValidator = new EmptySelectionMultivalidator(selectedDataObservable,
                checkedElements,
                Lists.newArrayList(rootMapping1, rootMapping2), aPool().build(),
                new WritableValue<GenerationOptions.EditMode>(EditMode.CREATE, EditMode.class),
                currentPageObservable);
        assertThat(multiValidator.validate().isOK()).isTrue();
    }

}
