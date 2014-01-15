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

import java.util.HashMap;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 *
 */
public class CreateFormCommandTest {

	private CreateFormCommand commandUnderTest;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		commandUnderTest = new CreateFormCommand(ProcessFactory.eINSTANCE.createTask(),
				ProcessPackage.Literals.PAGE_FLOW__FORM, 
				"Test form",
				"Test form description", 
				new HashMap<EObject, Widget>(), 
				null);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldCreateLabelExpression_ReturnExpression() throws Exception {
		Expression exp = commandUnderTest.createLabelExpression("Test form");
		assertThat(exp).isNotNull();
		assertThat(exp.getContent()).isEqualTo("Test form");
		assertThat(exp.getName()).isEqualTo("Test form");
		assertThat(exp.getReturnType()).isEqualTo(String.class.getName());
		assertThat(exp.isReturnTypeFixed()).isTrue();
		assertThat(exp.getType()).isEqualTo(ExpressionConstants.CONSTANT_TYPE);
		assertThat(exp.getReferencedElements()).isEmpty();
	}
	
	@Test
	public void shouldCreateLabelExpression_ReturnFormattedNameExpression() throws Exception {
		Expression exp = commandUnderTest.createLabelExpression("firstName");
		assertThat(exp).isNotNull();
		assertThat(exp.getContent()).isEqualTo("First Name");
		assertThat(exp.getName()).isEqualTo("First Name");
		assertThat(exp.getReturnType()).isEqualTo(String.class.getName());
		assertThat(exp.isReturnTypeFixed()).isTrue();
		assertThat(exp.getType()).isEqualTo(ExpressionConstants.CONSTANT_TYPE);
		assertThat(exp.getReferencedElements()).isEmpty();
	}
	
	@Test
	public void shoudCalculateAndSetNumColumn_AlwaysReturnOneForViewPageFlow() throws Exception {
		commandUnderTest = new CreateFormCommand(ProcessFactory.eINSTANCE.createTask(),
				ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM, 
				"Test form",
				"Test form description", 
				new HashMap<EObject, Widget>(), 
				null);
		Form viewForm = commandUnderTest.createForm();
		assertThat(viewForm).isNotNull().isInstanceOf(ViewForm.class);
		assertThat(commandUnderTest.calculateAndSetNumColumn(viewForm)).isEqualTo(1);
	}
	
	@Test
	public void shoudCalculateAndSetNumColumn_ReturnOneForEmptyPageFlow() throws Exception {
		Form form = commandUnderTest.createForm();
		assertThat(form).isNotNull().isInstanceOf(Form.class);
		assertThat(commandUnderTest.calculateAndSetNumColumn(form)).isEqualTo(1);
	}
	
	@Test
	public void shoudCalculateAndSetNumColumn_ReturnTwoForNonEmptyPageFlow() throws Exception {
		Task task = ProcessFactory.eINSTANCE.createTask();
		task.getForm().add(FormFactory.eINSTANCE.createForm());
		commandUnderTest = new CreateFormCommand(task,
				ProcessPackage.Literals.PAGE_FLOW__FORM, 
				"Test form",
				"Test form description", 
				new HashMap<EObject, Widget>(), 
				null);
		Form form = commandUnderTest.createForm();
		assertThat(form).isNotNull().isInstanceOf(Form.class);
		assertThat(commandUnderTest.calculateAndSetNumColumn(form)).isEqualTo(2);
	}
	
	@Test
	public void shouldCreateInsertWidgetIfScript_ReturnExpression() throws Exception {
		Expression exp = commandUnderTest.createInsertWidgetIfScript();
		assertThat(exp).isNotNull();
		assertThat(exp.getReturnType()).isEqualTo(Boolean.class.getName());
		assertThat(exp.isReturnTypeFixed()).isTrue();
		assertThat(exp.getType()).isEqualTo(ExpressionConstants.CONSTANT_TYPE);
		assertThat(exp.getReferencedElements()).isEmpty();
	}
	
}
