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
package org.bonitasoft.studio.properties.sections.general;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProcessElementNameContributionTest {

    @Mock
    private ProcessElementNameContribution nameGridPropertySectionContribution;
    @Mock
    private TabbedPropertySheetPage sheetPage;

    private ExtensibleGridPropertySection gridPropertySection;
    @Mock
    private Display displayMock;
    @Mock
    private IGraphicalEditPart editPart;
    @Mock
    private ITextAwareEditPart textAwareEP;

    private EMFDataBindingContext dbc;

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Before
    public void setUp() throws Exception {
        dbc = new EMFDataBindingContext();
        nameGridPropertySectionContribution = new ProcessElementNameContribution(sheetPage);
    }

    @After
    public void tearDown() throws Exception {
        if (dbc != null) {
            dbc.dispose();
        }
    }

    @Test
    public void should_updatePartName_call_refresh_on_pool_edit_part_asynchronously() throws Exception {
        final Task task = aTask().build();
        nameGridPropertySectionContribution.setEObject(task);
        when(editPart.resolveSemanticElement()).thenReturn(task);
        when(editPart.getChildren()).thenReturn(Arrays.asList(textAwareEP));

        nameGridPropertySectionContribution.setSelection(new StructuredSelection(editPart));

        nameGridPropertySectionContribution.updatePartName("new name", displayMock);

        verify(displayMock).asyncExec(any(Runnable.class));
    }

    @Test
    public void should_validationStatusProvider_accept_valid_name() throws Exception {
        final WritableValue targetValue = new WritableValue();
        final WritableValue modelValue = new WritableValue();
        dbc.bindValue(targetValue, modelValue);
        final MultiValidator validationStatusProvider = nameGridPropertySectionContribution.nameValidationStatusProvider(targetValue);
        dbc.addValidationStatusProvider(validationStatusProvider);

        targetValue.setValue("a valid name");

        assertThat(((IStatus) validationStatusProvider.getValidationStatus().getValue()).isOK()).isTrue();
        assertThat(modelValue.getValue()).isEqualTo(targetValue.getValue()).isEqualTo("a valid name");
    }

    @Test
    public void should_validationStatusProvider_fail_name_with_special_chars() throws Exception {
        final WritableValue targetValue = new WritableValue();
        final WritableValue modelValue = new WritableValue();
        dbc.bindValue(targetValue, modelValue);
        final MultiValidator validationStatusProvider = nameGridPropertySectionContribution.nameValidationStatusProvider(targetValue);
        dbc.addValidationStatusProvider(validationStatusProvider);

        targetValue.setValue("a #invalid name");

        assertThat(((IStatus) validationStatusProvider.getValidationStatus().getValue()).isOK()).isFalse();
        assertThat(modelValue.getValue()).isEqualTo(targetValue.getValue()).isEqualTo("a #invalid name");
    }

    @Test
    public void should_validationStatusProvider_fail_name_with_reservedKeywords() throws Exception {
        final WritableValue targetValue = new WritableValue();
        final WritableValue modelValue = new WritableValue();
        dbc.bindValue(targetValue, modelValue);
        final MultiValidator validationStatusProvider = nameGridPropertySectionContribution.nameValidationStatusProvider(targetValue);
        dbc.addValidationStatusProvider(validationStatusProvider);

        targetValue.setValue("content");

        assertThat(((IStatus) validationStatusProvider.getValidationStatus().getValue()).isOK()).isFalse();
        assertThat(modelValue.getValue()).isEqualTo(targetValue.getValue()).isEqualTo("content");
    }
}
