package org.bonitasoft.studio.diagram.form.custom.commands;

/**
 * Copyright (C) 2013 BonitaSoft S.A.
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.model.form.CheckBoxMultipleFormField;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.DurationFormField;
import org.bonitasoft.studio.model.form.DynamicTable;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.MessageInfo;
import org.bonitasoft.studio.model.form.PasswordFormField;
import org.bonitasoft.studio.model.form.RadioFormField;
import org.bonitasoft.studio.model.form.RichTextAreaFormField;
import org.bonitasoft.studio.model.form.SelectFormField;
import org.bonitasoft.studio.model.form.Table;
import org.bonitasoft.studio.model.form.TextAreaFormField;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.TextInfo;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.builders.PoolBuilder;
import org.bonitasoft.studio.model.process.builders.TaskBuilder;
import org.bonitasoft.studio.model.process.diagram.form.providers.ElementInitializers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateWidgetSwitchTest {

    private CreateWidgetSwitch createWidgetSwitch;

    @Mock
    private ElementInitializers initializer;

    private FormFactory formFactory;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        formFactory = FormFactory.eINSTANCE;
        createWidgetSwitch = new CreateWidgetSwitch(TaskBuilder.aTask().build(), initializer);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldDoSwitchTextField_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createTextFormField());
        assertThat(widget).isNotNull().isInstanceOf(TextFormField.class);
        verify(initializer).init_TextFormField_3112((TextFormField) widget);
    }

    @Test
    public void shouldDoSwitchTextArea_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createTextAreaFormField());
        assertThat(widget).isNotNull().isInstanceOf(TextAreaFormField.class);
        verify(initializer).init_TextAreaFormField_3113((TextAreaFormField) widget);
    }

    @Test
    public void shouldDoSwitchDate_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createDateFormField());
        assertThat(widget).isNotNull().isInstanceOf(DateFormField.class);
        verify(initializer).init_DateFormField_3105((DateFormField) widget);
    }

    @Test
    public void shouldDoSwitchSelect_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createSelectFormField());
        assertThat(widget).isNotNull().isInstanceOf(SelectFormField.class);
        verify(initializer).init_SelectFormField_3111((SelectFormField) widget);
    }

    @Test
    public void shouldDoSwitchPassword_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createPasswordFormField());
        assertThat(widget).isNotNull().isInstanceOf(PasswordFormField.class);
        verify(initializer).init_PasswordFormField_3109((PasswordFormField) widget);
    }

    @Test
    public void shouldDoSwitchList_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createListFormField());
        assertThat(widget).isNotNull().isInstanceOf(ListFormField.class);
        verify(initializer).init_ListFormField_3107((ListFormField) widget);
    }

    @Test
    public void shouldDoSwitchCheckbox_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createCheckBoxSingleFormField());
        assertThat(widget).isNotNull().isInstanceOf(CheckBoxSingleFormField.class);
        verify(initializer).init_CheckBoxSingleFormField_3118((CheckBoxSingleFormField) widget);
    }

    @Test
    public void shouldDoSwitchCheckboxList_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createCheckBoxMultipleFormField());
        assertThat(widget).isNotNull().isInstanceOf(CheckBoxMultipleFormField.class);
        verify(initializer).init_CheckBoxMultipleFormField_3120((CheckBoxMultipleFormField) widget);
    }

    @Test
    public void shouldDoSwitchFileWidget_InitializeWidget() throws Exception {
        Widget widget = createWidgetSwitch.doSwitch(formFactory.createFileWidget());
        assertThat(widget).isNotNull().isInstanceOf(FileWidget.class);
        assertThat(((FileWidget) widget).getInputType()).isEqualTo(FileWidgetInputType.DOCUMENT);
        verify(initializer).init_FileWidget_3119((FileWidget) widget);

        createWidgetSwitch = new CreateWidgetSwitch(PoolBuilder.aPool().build(), initializer);
        widget = createWidgetSwitch.doSwitch(formFactory.createFileWidget());
        assertThat(((FileWidget) widget).getInputType()).isEqualTo(FileWidgetInputType.RESOURCE);
    }

    @Test
    public void shouldDoSwitchDurationFormField_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createDurationFormField());
        assertThat(widget).isNotNull().isInstanceOf(DurationFormField.class);
        verify(initializer).init_DurationFormField_3121((DurationFormField) widget);
    }

    @Test
    public void shouldDoSwitchRadioFormField_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createRadioFormField());
        assertThat(widget).isNotNull().isInstanceOf(RadioFormField.class);
        verify(initializer).init_RadioFormField_3110((RadioFormField) widget);
    }

    @Test
    public void shouldDoSwitchMessageInfo_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createMessageInfo());
        assertThat(widget).isNotNull().isInstanceOf(MessageInfo.class);
        verify(initializer).init_MessageInfo_3124((MessageInfo) widget);
    }

    @Test
    public void shouldDoSwitchTextInfo_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createTextInfo());
        assertThat(widget).isNotNull().isInstanceOf(TextInfo.class);
        verify(initializer).init_TextInfo_3125((TextInfo) widget);
    }

    @Test
    public void shouldDoSwitchRichTextArea_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createRichTextAreaFormField());
        assertThat(widget).isNotNull().isInstanceOf(RichTextAreaFormField.class);
        verify(initializer).init_RichTextAreaFormField_3128((RichTextAreaFormField) widget);
    }

    @Test
    public void shouldDoSwitchGroup_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createGroup());
        assertThat(widget).isNotNull().isInstanceOf(Group.class);
        verify(initializer).init_Group_3106((Group) widget);
    }

    @Test
    public void shouldDoSwitchTable_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createTable());
        assertThat(widget).isNotNull().isInstanceOf(Table.class);
        verify(initializer).init_Table_3127((Table) widget);
    }

    @Test
    public void shouldDoSwitchDynamicTable_InitializeWidget() throws Exception {
        final Widget widget = createWidgetSwitch.doSwitch(formFactory.createDynamicTable());
        assertThat(widget).isNotNull().isInstanceOf(DynamicTable.class);
        verify(initializer).init_DynamicTable_3129((DynamicTable) widget);
    }
}
