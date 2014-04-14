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

import java.util.Collections;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetContainer;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
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
				Collections.<WidgetMapping>emptyList(), 
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
	public void shoudGetHorizontalSpan_AlwaysReturnOneForViewPageFlow() throws Exception {
		commandUnderTest = new CreateFormCommand(ProcessFactory.eINSTANCE.createTask(),
				ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM, 
				"Test form",
				"Test form description", 
				Collections.<WidgetMapping>emptyList(), 
				null);
		Form viewForm = commandUnderTest.createForm();
		assertThat(viewForm).isNotNull().isInstanceOf(ViewForm.class);
		assertThat(commandUnderTest.getHorizontalSpan(viewForm)).isEqualTo(1);
	}
	
	@Test
	public void shoudGetHorizontalSpan_ReturnOneForEmptyPageFlow() throws Exception {
		Form form = commandUnderTest.createForm();
		assertThat(form).isNotNull().isInstanceOf(Form.class);
		assertThat(commandUnderTest.getHorizontalSpan(form)).isEqualTo(1);
	}
	
	@Test
	public void shoudGetHorizontalSpan_ReturnTwoForNonEmptyPageFlow() throws Exception {
		Task task = ProcessFactory.eINSTANCE.createTask();
		task.getForm().add(FormFactory.eINSTANCE.createForm());
		commandUnderTest = new CreateFormCommand(task,
				ProcessPackage.Literals.PAGE_FLOW__FORM, 
				"Test form",
				"Test form description", 
				Collections.<WidgetMapping>emptyList(), 
				null);
		Form form = commandUnderTest.createForm();
		assertThat(form).isNotNull().isInstanceOf(Form.class);
		assertThat(commandUnderTest.getHorizontalSpan(form)).isEqualTo(2);
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

	
	@Test
	public void shouldGetVerticalSpan_ReturnOneIfWidgetIsNotAGroup() throws Exception {
		for(EClassifier eClass : FormFactory.eINSTANCE.getEPackage().getEClassifiers()){
			if(eClass instanceof EClass && !((EClass) eClass).isAbstract() && FormPackage.Literals.WIDGET.isSuperTypeOf((EClass) eClass)){
				Widget w = (Widget) FormFactory.eINSTANCE.create((EClass) eClass);
				if(!(w instanceof Group)){
					assertThat(commandUnderTest.getVerticalSpan(w)).isEqualTo(1);
				}
			}
		}
	}
	
	@Test
	public void shouldGetVerticalSpan_ReturnVerticalSpanForGroup() throws Exception {
		Group group = FormFactory.eINSTANCE.createGroup();
		Widget w1 = FormFactory.eINSTANCE.createTextFormField();
		Widget w2 = FormFactory.eINSTANCE.createTextFormField();
		Group childGroup = FormFactory.eINSTANCE.createGroup();
		Widget w3 = FormFactory.eINSTANCE.createTextFormField();
		childGroup.getWidgets().add(w3);
		group.getWidgets().add(w1);
		group.getWidgets().add(w2);
		group.getWidgets().add(childGroup);
		assertThat(commandUnderTest.getVerticalSpan(group)).isEqualTo(3);
	}
	
	@Test
	public void shouldGetLineIndex_ReturnCurrentLineIndex() throws Exception {
		Form form = FormFactory.eINSTANCE.createForm();
		Group group = FormFactory.eINSTANCE.createGroup();
		TextFormField w1 = FormFactory.eINSTANCE.createTextFormField();
		CheckBoxSingleFormField w2 = FormFactory.eINSTANCE.createCheckBoxSingleFormField();
		TextFormField w3 = FormFactory.eINSTANCE.createTextFormField();
		CheckBoxSingleFormField w4 = FormFactory.eINSTANCE.createCheckBoxSingleFormField();
		
		WidgetContainer widgetContainer = new WidgetContainer(form);
		assertThat(commandUnderTest.getLineIndex(widgetContainer)).isEqualTo(0);
		form.getWidgets().add(w1);
		assertThat(commandUnderTest.getLineIndex(widgetContainer)).isEqualTo(1);
		widgetContainer.getWidgets().add(w2);
		assertThat(commandUnderTest.getLineIndex(widgetContainer)).isEqualTo(2);
	
		WidgetContainer groupContainer = new WidgetContainer(group);
		assertThat(commandUnderTest.getLineIndex(groupContainer)).isEqualTo(0);
		groupContainer.getWidgets().add(w3);
		assertThat(commandUnderTest.getLineIndex(groupContainer)).isEqualTo(1);
		groupContainer.getWidgets().add(w4);
		assertThat(commandUnderTest.getLineIndex(groupContainer)).isEqualTo(2);
		widgetContainer.getWidgets().add(group);
		assertThat(commandUnderTest.getLineIndex(widgetContainer)).isEqualTo(4);
	}
}
