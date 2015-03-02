/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.SetChangeEvent;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class SelectGeneratedWidgetsWizardPageTest {

    private SelectGeneratedWidgetsWizardPage selectGeneratedWidgetsWizardPage;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        selectGeneratedWidgetsWizardPage = spy(new SelectGeneratedWidgetsWizardPage("", Collections.<EObject> emptyList()));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link org.bonitasoft.studio.properties.sections.forms.wizard.SelectGeneratedWidgetsWizardPage#asWidgetMappingList(java.util.Collection)}
     * .
     */
    @Test
    public void shouldAsWidgetMappingList_ReturnsAListOfWidgetMappingFromAListOfData() throws Exception {
        final List<Data> data = new ArrayList<Data>();
        final Data myData = ProcessFactory.eINSTANCE.createData();
        myData.setDataType(ProcessFactory.eINSTANCE.createStringType());
        final Data myData2 = ProcessFactory.eINSTANCE.createData();
        myData2.setDataType(ProcessFactory.eINSTANCE.createBooleanType());
        data.add(myData);
        data.add(myData2);
        assertThat(selectGeneratedWidgetsWizardPage.asWidgetMappingList(data)).isNotNull().isNotEmpty().hasSize(2).extracting("modelElement")
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
        assertThat(root.isGenerated()).isTrue();
        assertThat(c1.isGenerated()).isTrue();
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
        final List<WidgetMapping> allMappings = new ArrayList<WidgetMapping>();
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
                final Set<WidgetMapping> additions = new HashSet<WidgetMapping>();
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
                final Set<WidgetMapping> additions = new HashSet<WidgetMapping>();
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

}
