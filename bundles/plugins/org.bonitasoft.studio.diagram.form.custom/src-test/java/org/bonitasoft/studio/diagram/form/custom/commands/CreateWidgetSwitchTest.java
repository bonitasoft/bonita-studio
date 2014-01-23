package org.bonitasoft.studio.diagram.form.custom.commands;
/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.model.form.CheckBoxMultipleFormField;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.DurationFormField;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.PasswordFormField;
import org.bonitasoft.studio.model.form.RadioFormField;
import org.bonitasoft.studio.model.form.SelectFormField;
import org.bonitasoft.studio.model.form.TextAreaFormField;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.diagram.form.providers.ElementInitializers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 *
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
		createWidgetSwitch = new CreateWidgetSwitch(initializer);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void shouldDoSwitchTextField_InitializeWidget() throws Exception {
		Widget widget = createWidgetSwitch.doSwitch(formFactory.createTextFormField());
		assertThat(widget).isNotNull().isInstanceOf(TextFormField.class);
		verify(initializer).init_TextFormField_3112((TextFormField) widget);
	}
	
	@Test
	public void shouldDoSwitchTextArea_InitializeWidget() throws Exception {
		Widget widget = createWidgetSwitch.doSwitch(formFactory.createTextAreaFormField());
		assertThat(widget).isNotNull().isInstanceOf(TextAreaFormField.class);
		verify(initializer).init_TextAreaFormField_3113((TextAreaFormField) widget);
	}
	
	@Test
	public void shouldDoSwitchDate_InitializeWidget() throws Exception {
		Widget widget = createWidgetSwitch.doSwitch(formFactory.createDateFormField());
		assertThat(widget).isNotNull().isInstanceOf(DateFormField.class);
		verify(initializer).init_DateFormField_3105((DateFormField) widget);
	}
	
	@Test
	public void shouldDoSwitchSelect_InitializeWidget() throws Exception {
		Widget widget = createWidgetSwitch.doSwitch(formFactory.createSelectFormField());
		assertThat(widget).isNotNull().isInstanceOf(SelectFormField.class);
		verify(initializer).init_SelectFormField_3111((SelectFormField) widget);
	}
	
	@Test
	public void shouldDoSwitchPassword_InitializeWidget() throws Exception {
		Widget widget = createWidgetSwitch.doSwitch(formFactory.createPasswordFormField());
		assertThat(widget).isNotNull().isInstanceOf(PasswordFormField.class);
		verify(initializer).init_PasswordFormField_3109((PasswordFormField) widget);
	}
	
	@Test
	public void shouldDoSwitchList_InitializeWidget() throws Exception {
		Widget widget = createWidgetSwitch.doSwitch(formFactory.createListFormField());
		assertThat(widget).isNotNull().isInstanceOf(ListFormField.class);
		verify(initializer).init_ListFormField_3107((ListFormField) widget);
	}
	
	@Test
	public void shouldDoSwitchCheckbox_InitializeWidget() throws Exception {
		Widget widget = createWidgetSwitch.doSwitch(formFactory.createCheckBoxSingleFormField());
		assertThat(widget).isNotNull().isInstanceOf(CheckBoxSingleFormField.class);
		verify(initializer).init_CheckBoxSingleFormField_3118((CheckBoxSingleFormField) widget);
	}
	
	@Test
	public void shouldDoSwitchCheckboxList_InitializeWidget() throws Exception {
		Widget widget = createWidgetSwitch.doSwitch(formFactory.createCheckBoxMultipleFormField());
		assertThat(widget).isNotNull().isInstanceOf(CheckBoxMultipleFormField.class);
		verify(initializer).init_CheckBoxMultipleFormField_3120((CheckBoxMultipleFormField) widget);
	}
	
	@Test
	public void shouldDoSwitchFileWidget_InitializeWidget() throws Exception {
		Widget widget = createWidgetSwitch.doSwitch(formFactory.createFileWidget());
		assertThat(widget).isNotNull().isInstanceOf(FileWidget.class);
		verify(initializer).init_FileWidget_3119((FileWidget) widget);
	}
	
	@Test
	public void shouldDoSwitchDurationFormField_InitializeWidget() throws Exception {
		Widget widget = createWidgetSwitch.doSwitch(formFactory.createDurationFormField());
		assertThat(widget).isNotNull().isInstanceOf(DurationFormField.class);
		verify(initializer).init_DurationFormField_3121((DurationFormField) widget);
	}

	@Test
	public void shouldDoSwitchRadioFormField_InitializeWidget() throws Exception {
		Widget widget = createWidgetSwitch.doSwitch(formFactory.createRadioFormField());
		assertThat(widget).isNotNull().isInstanceOf(RadioFormField.class);
		verify(initializer).init_RadioFormField_3110((RadioFormField) widget);
	}
	

}
