/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.forms.wizard.editingsupport;

import static org.fest.assertions.Assertions.assertThat;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.jface.viewers.ColumnViewer;
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
public class WidgetTypeEditingSupportTest {

	@Mock
	private ColumnViewer columnViewer;
	
	private WidgetTypeEditingSupport widgettTypeEditingSupport;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		widgettTypeEditingSupport = new WidgetTypeEditingSupport(columnViewer);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.bonitasoft.studio.properties.sections.forms.wizard.editingsupport.WidgetTypeEditingSupport#getItemsFor(java.lang.Object)}.
	 */
	@Test
	public void shouldGetItemsFor_ReturnCompatibleWidgetsNameForTextData() throws Exception {
		Data data = ProcessFactory.eINSTANCE.createData();
		data.setDataType(ProcessFactory.eINSTANCE.createStringType());
		assertThat(widgettTypeEditingSupport.getItemsFor(new WidgetMapping(data))).containsOnly(NamingUtils.getFormPaletteText(false, FormPackage.Literals.TEXT_FORM_FIELD),
				NamingUtils.getFormPaletteText(false, FormPackage.Literals.TEXT_AREA_FORM_FIELD),
				NamingUtils.getFormPaletteText(false, FormPackage.Literals.PASSWORD_FORM_FIELD),
				NamingUtils.getFormPaletteText(false, FormPackage.Literals.MESSAGE_INFO),
				NamingUtils.getFormPaletteText(false, FormPackage.Literals.TEXT_INFO),
				NamingUtils.getFormPaletteText(false, FormPackage.Literals.RICH_TEXT_AREA_FORM_FIELD));
	}

	@Test
	public void shouldGetItemsFor_ReturnCompatibleWidgetsNameForBooleanData() throws Exception {
		Data data = ProcessFactory.eINSTANCE.createData();
		data.setDataType(ProcessFactory.eINSTANCE.createBooleanType());
		assertThat(widgettTypeEditingSupport.getItemsFor(new WidgetMapping(data))).containsOnly(NamingUtils.getFormPaletteText(false, FormPackage.Literals.CHECK_BOX_SINGLE_FORM_FIELD));
	}
	
	@Test
	public void shouldGetItemsFor_ReturnCompatibleWidgetsNameForEnumData() throws Exception {
		Data data = ProcessFactory.eINSTANCE.createData();
		data.setDataType(ProcessFactory.eINSTANCE.createEnumType());
		assertThat(widgettTypeEditingSupport.getItemsFor(new WidgetMapping(data))).containsOnly(NamingUtils.getFormPaletteText(false, FormPackage.Literals.RADIO_FORM_FIELD),
				NamingUtils.getFormPaletteText(false, FormPackage.Literals.SELECT_FORM_FIELD));
	}
	
	@Test
	public void shouldGetItemsFor_ReturnCompatibleWidgetsNameForNumericData() throws Exception {
		Data data = ProcessFactory.eINSTANCE.createData();
		data.setDataType(ProcessFactory.eINSTANCE.createIntegerType());
		assertThat(widgettTypeEditingSupport.getItemsFor(new WidgetMapping(data))).containsOnly(NamingUtils.getFormPaletteText(false, FormPackage.Literals.TEXT_FORM_FIELD));
	}
	
	@Test
	public void shouldGetItemsFor_ReturnCompatibleWidgetsNameForMultipleBooleanData() throws Exception {
		Data data = ProcessFactory.eINSTANCE.createData();
		data.setDataType(ProcessFactory.eINSTANCE.createBooleanType());
		data.setMultiple(true);
		assertThat(widgettTypeEditingSupport.getItemsFor(new WidgetMapping(data))).containsOnly(NamingUtils.getFormPaletteText(false, FormPackage.Literals.CHECK_BOX_MULTIPLE_FORM_FIELD));
	}
	
	@Test
	public void shouldCanEdit_ReturnTrue() throws Exception {
		Data data = ProcessFactory.eINSTANCE.createData();
		data.setDataType(ProcessFactory.eINSTANCE.createStringType());
		data.setMultiple(false);
		assertThat(widgettTypeEditingSupport.canEdit(new WidgetMapping(data))).isTrue();
	}
	
	@Test
	public void shouldCanEdit_ReturnFalse() throws Exception {
		Data data = ProcessFactory.eINSTANCE.createData();
		data.setDataType(ProcessFactory.eINSTANCE.createStringType());
		data.setMultiple(true);
		assertThat(widgettTypeEditingSupport.canEdit(new WidgetMapping(data))).isFalse();
		
		data.setDataType(ProcessFactory.eINSTANCE.createBooleanType());
		assertThat(widgettTypeEditingSupport.canEdit(new WidgetMapping(data))).isFalse();
		data.setMultiple(true);
		assertThat(widgettTypeEditingSupport.canEdit(new WidgetMapping(data))).isFalse();
		data.setMultiple(false);
		
		data.setDataType(ProcessFactory.eINSTANCE.createIntegerType());
		assertThat(widgettTypeEditingSupport.canEdit(new WidgetMapping(data))).isFalse();
		assertThat(widgettTypeEditingSupport.canEdit(data)).isFalse();
	}
	
	

}
