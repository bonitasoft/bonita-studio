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
package org.bonitasoft.studio.common.emf.tools;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 *
 */
public class ModelHelperTest {

	private Pool process;
	private Task task1;
	private Task task2;
	private Data processData;
	private Data t1Data;
	private Data t2Data;
	private Form myForm;
	private TextFormField textField;
	private Data pageFlowTransientData;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ProcessFactory processFactory = ProcessFactory.eINSTANCE;
		FormFactory formFactory = FormFactory.eINSTANCE;
		process = processFactory.createPool();
		processData = processFactory.createData();
		process.getData().add(processData);
		task1 = processFactory.createTask();
		t1Data = processFactory.createData();
		task1.getData().add(t1Data);
		
		myForm = formFactory.createForm();
		textField = formFactory.createTextFormField();
		myForm.getWidgets().add(textField);
		task1.getForm().add(myForm);
		
		pageFlowTransientData = processFactory.createData();
		pageFlowTransientData.setTransient(true);
		task1.getTransientData().add(pageFlowTransientData);
		
		task2 = processFactory.createTask();
		t2Data = processFactory.createData();
		task2.getData().add(t2Data);
		process.getElements().add(task1);
		process.getElements().add(task2);
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	
	}

	@Test
	public void shouldGetAccessibleData_ReturnEmptyListWhenNoDataAccessible() throws Exception {
		Element container = ProcessFactory.eINSTANCE.createPool(); 
		assertThat(ModelHelper.getAccessibleData(container, true)).isNotNull().isEmpty();
	}
	
	@Test
	public void shouldGetAccessibleData_ReturnEmptyListWhenContainerIsNull() throws Exception {
		assertThat(ModelHelper.getAccessibleData(null, true)).isNotNull().isEmpty();
	}
	
	@Test
	public void shouldGetAccessibleData_ForProcessReturnProcessData() throws Exception {
		assertThat(ModelHelper.getAccessibleData(process, false)).isNotNull().containsOnly(processData);
	}
	
	@Test
	public void shouldGetAccessibleData_ForTask1ReturnProcessDataAndT1Data() throws Exception {
		assertThat(ModelHelper.getAccessibleData(task1, false)).isNotNull().containsOnly(processData,t1Data);
	}
	
	@Test
	public void shouldGetAccessibleData_ForWidgetReturnAllAccessibleAndPageflowTransientData() throws Exception {
		assertThat(ModelHelper.getAccessibleData(textField, true)).isNotNull().containsOnly(processData,t1Data,pageFlowTransientData);
	}
	
	@Test
	public void shouldGetAccessibleData_ForWidgetReturnAllAccessibleWithoutPageflowTransientData_IfIncludeTransientDataIsFalse() throws Exception {
		assertThat(ModelHelper.getAccessibleData(textField, false)).isNotNull().containsOnly(processData,t1Data);
	}
	
}
