/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.properties.sections.forms.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder;
import org.bonitasoft.studio.model.process.builders.BusinessObjectDataTypeBuilder;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.SetChangeEvent;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SelectGeneratedWidgetsWizardPageTest {

    private SelectGeneratedWidgetsWizardPage selectGeneratedWidgetsWizardPage;
    @Mock
    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store;
    private BusinessObjectData businessData;
    @Mock
    private BusinessObjectModelFileStore fStore;

    @Mock
    private CheckboxTreeViewer checkboxTreeViewer;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final BusinessObjectModel bom = new BusinessObjectModel();
        final BusinessObject car = new BusinessObject();
        car.setQualifiedName("org.bonita.Car");
        final SimpleField modelField = new SimpleField();
        modelField.setName("model");
        modelField.setType(FieldType.STRING);
        car.getFields().add(modelField);
        bom.getBusinessObjects().add(car);
        when(fStore.getContent()).thenReturn(bom);
        when(store.getChildByQualifiedName("org.bonita.Car")).thenReturn(Optional.of(fStore));
        when(fStore.getBusinessObjects()).thenCallRealMethod();
        when(fStore.getBusinessObject(anyString())).thenCallRealMethod();

        final PageFlow pageFlow = ProcessFactory.eINSTANCE.createPool();
        businessData = ProcessFactory.eINSTANCE.createBusinessObjectData();
        businessData.setDataType(ProcessFactory.eINSTANCE.createBusinessObjectType());
        businessData.setName("testData");
        businessData.setClassName("org.bonita.Car");
        pageFlow.getData().add(businessData);
        final List<EObject> input = new ArrayList<>();
        input.add(businessData);
        selectGeneratedWidgetsWizardPage = spy(new SelectGeneratedWidgetsWizardPage(pageFlow, "",
                input, store));
    }

    /**
     * Test method for {@link org.bonitasoft.studio.properties.sections.forms.wizard.SelectGeneratedWidgetsWizardPage#asWidgetMappingList(java.util.Collection)}
     * .
     */
    @Test
    public void shouldAsWidgetMappingList_ReturnsAListOfWidgetMappingFromAListOfData() throws Exception {
        final List<Data> data = new ArrayList<>();
        final Data myData = ProcessFactory.eINSTANCE.createData();
        myData.setDataType(ProcessFactory.eINSTANCE.createStringType());
        final Data myData2 = ProcessFactory.eINSTANCE.createData();
        myData2.setDataType(ProcessFactory.eINSTANCE.createBooleanType());
        data.add(myData);
        data.add(myData2);
        assertThat(selectGeneratedWidgetsWizardPage.asWidgetMappingList(data)).isNotNull().isNotEmpty().hasSize(2)
                .extracting("modelElement")
                .contains(myData, myData2);
    }

    @Test
    public void shouldSetMappingEnabledRecursivly_EnableGenerationFor_AllChildrenMappings() throws Exception {
        final Data myData = ProcessFactory.eINSTANCE.createData();
        myData.setDataType(ProcessFactory.eINSTANCE.createStringType());
        final WidgetMapping root = new WidgetMapping(myData);
        final WidgetMapping c1 = new WidgetMapping(myData);
        final WidgetMapping c2 = new WidgetMapping(myData);
        final WidgetMapping c1_c1 = new WidgetMapping(myData);
        root.addChild(c1);
        root.addChild(c2);
        c1.addChild(c1_c1);
        assertThat(root.isGenerated()).isFalse();
        assertThat(c1.isGenerated()).isFalse();
        assertThat(c2.isGenerated()).isFalse();
        assertThat(c1_c1.isGenerated()).isFalse();
        selectGeneratedWidgetsWizardPage.setMappingEnabledRecursivly(root, true);

        assertThat(root.isGenerated()).isFalse();
        assertThat(c1.isGenerated()).isFalse();

        assertThat(c2.isGenerated()).isTrue();
        assertThat(c1_c1.isGenerated()).isTrue();
    }

    @Test
    public void shouldSetMappingEnabledRecursivly_DisableGenerationFor_AllChildrenMappings() throws Exception {
        final Data myData = ProcessFactory.eINSTANCE.createData();
        myData.setDataType(ProcessFactory.eINSTANCE.createStringType());
        final WidgetMapping root = new WidgetMapping(myData);
        root.setGenerated(true);
        final WidgetMapping c1 = new WidgetMapping(myData);
        c1.setGenerated(true);
        final WidgetMapping c2 = new WidgetMapping(myData);
        c2.setGenerated(true);
        final WidgetMapping c1_c1 = new WidgetMapping(myData);
        c1_c1.setGenerated(true);
        root.addChild(c1);
        root.addChild(c2);
        c1.addChild(c1_c1);
        assertThat(root.isGenerated()).isTrue();
        assertThat(c1.isGenerated()).isTrue();
        assertThat(c2.isGenerated()).isTrue();
        assertThat(c1_c1.isGenerated()).isTrue();
        selectGeneratedWidgetsWizardPage.setMappingEnabledRecursivly(root, false);
        assertThat(root.isGenerated()).isFalse();
        assertThat(c1.isGenerated()).isFalse();
        assertThat(c2.isGenerated()).isFalse();
        assertThat(c1_c1.isGenerated()).isFalse();
    }

    @Test
    public void shouldFillAllMappings_AddAllChildren() throws Exception {
        final Data myData = ProcessFactory.eINSTANCE.createData();
        myData.setDataType(ProcessFactory.eINSTANCE.createStringType());
        final WidgetMapping root = new WidgetMapping(myData);
        final WidgetMapping c1 = new WidgetMapping(myData);
        final WidgetMapping c2 = new WidgetMapping(myData);
        final WidgetMapping c1_c1 = new WidgetMapping(myData);
        root.addChild(c1);
        root.addChild(c2);
        c1.addChild(c1_c1);
        final List<WidgetMapping> allMappings = new ArrayList<>();
        assertThat(allMappings).isEmpty();
        selectGeneratedWidgetsWizardPage.fillAllMappings(allMappings, Collections.singletonList(root));
        assertThat(allMappings).isNotEmpty().containsExactly(root, c1, c2, c1_c1);
    }

    @Test
    public void shouldCheckStateChanged_CallSetSubtreeCheckedOfTree() throws Exception {
        final CheckboxTreeViewer viewer = mock(CheckboxTreeViewer.class);
        final Data myData = ProcessFactory.eINSTANCE.createData();
        myData.setDataType(ProcessFactory.eINSTANCE.createStringType());
        final WidgetMapping root = new WidgetMapping(myData);
        final CheckStateChangedEvent event = new CheckStateChangedEvent(viewer, root, true);
        selectGeneratedWidgetsWizardPage.checkStateChanged(event);
        verify(viewer).setSubtreeChecked(root, true);
    }

    @Test
    public void shouldUpdateGeneratedMappings_EnableGeneration_ForNewCheckedElement() throws Exception {
        final Data myData = ProcessFactory.eINSTANCE.createData();
        myData.setDataType(ProcessFactory.eINSTANCE.createStringType());
        final WidgetMapping root = new WidgetMapping(myData);
        final WidgetMapping c1 = new WidgetMapping(myData);
        final WidgetMapping c2 = new WidgetMapping(myData);
        root.addChild(c1);
        root.addChild(c2);

        final SetDiff diff = new SetDiff() {

            @Override
            public Set<?> getRemovals() {
                return Collections.emptySet();
            }

            @Override
            public Set<?> getAdditions() {
                final Set<WidgetMapping> additions = new HashSet<>();
                additions.add(root);
                return additions;
            }
        };
        final IObservableSet source = mock(IObservableSet.class);
        final SetChangeEvent event = new SetChangeEvent(source, diff);
        selectGeneratedWidgetsWizardPage.updateGeneratedMappings(event);
        verify(selectGeneratedWidgetsWizardPage).setMappingEnabledRecursivly(root, true);
        verify(selectGeneratedWidgetsWizardPage).setMappingEnabledRecursivly(c1, true);
        verify(selectGeneratedWidgetsWizardPage).setMappingEnabledRecursivly(c2, true);
    }

    @Test
    public void shouldUpdateGeneratedMappings_DisableGeneration_ForUncheckCheckedElement() throws Exception {
        final Data myData = ProcessFactory.eINSTANCE.createData();
        myData.setDataType(ProcessFactory.eINSTANCE.createStringType());
        final WidgetMapping root = new WidgetMapping(myData);
        final WidgetMapping c1 = new WidgetMapping(myData);
        final WidgetMapping c2 = new WidgetMapping(myData);
        root.addChild(c1);
        root.addChild(c2);

        final SetDiff diff = new SetDiff() {

            @Override
            public Set<?> getRemovals() {
                final Set<WidgetMapping> additions = new HashSet<>();
                additions.add(root);
                return additions;

            }

            @Override
            public Set<?> getAdditions() {
                return Collections.emptySet();
            }
        };

        final IObservableSet source = mock(IObservableSet.class);
        final SetChangeEvent event = new SetChangeEvent(source, diff);
        selectGeneratedWidgetsWizardPage.updateGeneratedMappings(event);
        verify(selectGeneratedWidgetsWizardPage).setMappingEnabledRecursivly(root, false);
        verify(selectGeneratedWidgetsWizardPage).setMappingEnabledRecursivly(c1, false);
        verify(selectGeneratedWidgetsWizardPage).setMappingEnabledRecursivly(c2, false);
    }

    @Test
    public void shouldGetEClassFromBusinessObjectData_ReturnValidEClass() throws Exception {
        final BusinessObject bo = selectGeneratedWidgetsWizardPage.getBusinessObjectFromData(businessData);
        assertThat(bo).isNotNull();
        assertThat(bo.getQualifiedName()).isEqualTo("org.bonita.Car");
        assertThat(bo.getFields()).isNotEmpty();
    }

    @Test
    public void should_a_widget_container_be_grayed_if_not_all_of_its_children_is_selected() throws Exception {
        final WidgetMapping parent = new WidgetMapping(BusinessObjectDataBuilder.aBusinessData()
                .havingDataType(BusinessObjectDataTypeBuilder.aBusinessObjectDataType()).build());
        final SimpleField modelElement = new SimpleField();
        modelElement.setType(FieldType.BOOLEAN);
        final WidgetMapping mapping1 = new WidgetMapping(modelElement);
        when(checkboxTreeViewer.getChecked(mapping1)).thenReturn(true);
        final WidgetMapping mapping2 = new WidgetMapping(modelElement);
        when(checkboxTreeViewer.getChecked(mapping2)).thenReturn(false);
        parent.addChild(mapping1);
        parent.addChild(mapping2);

        final ICheckStateProvider checkStateProvider = selectGeneratedWidgetsWizardPage
                .getCheckStateProvider(checkboxTreeViewer);

        assertThat(checkStateProvider.isGrayed(parent)).isTrue();
        assertThat(checkStateProvider.isChecked(parent)).isFalse();
    }

    @Test
    public void should_a_widget_not_grayed_if_a_leaf() throws Exception {
        final SimpleField modelElement = new SimpleField();
        modelElement.setType(FieldType.BOOLEAN);
        final WidgetMapping mapping1 = new WidgetMapping(modelElement);
        when(checkboxTreeViewer.getChecked(mapping1)).thenReturn(true);
        final ICheckStateProvider checkStateProvider = selectGeneratedWidgetsWizardPage
                .getCheckStateProvider(checkboxTreeViewer);

        assertThat(checkStateProvider.isGrayed(mapping1)).isFalse();
    }

    @Test
    public void should_element_in_tree_be_checked_if_generated() throws Exception {
        final ICheckStateProvider checkStateProvider = selectGeneratedWidgetsWizardPage
                .getCheckStateProvider(checkboxTreeViewer);

        final SimpleField modelElement = new SimpleField();
        modelElement.setType(FieldType.BOOLEAN);
        final WidgetMapping mapping = new WidgetMapping(modelElement);
        mapping.setGenerated(true);

        assertThat(checkStateProvider.isChecked(mapping)).isTrue();
    }

    @Test
    public void should_element_in_tree_be_unchecked_if_not_generated() throws Exception {
        final ICheckStateProvider checkStateProvider = selectGeneratedWidgetsWizardPage
                .getCheckStateProvider(checkboxTreeViewer);

        final SimpleField modelElement = new SimpleField();
        modelElement.setType(FieldType.BOOLEAN);
        final WidgetMapping mapping = new WidgetMapping(modelElement);
        mapping.setGenerated(false);

        assertThat(checkStateProvider.isChecked(mapping)).isFalse();
    }
}
