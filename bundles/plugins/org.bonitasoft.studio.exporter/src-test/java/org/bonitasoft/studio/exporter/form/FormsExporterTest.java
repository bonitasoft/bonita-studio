/**
 * 
 */
package org.bonitasoft.studio.exporter.form;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.bonitasoft.forms.server.builder.IFormBuilder;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class FormsExporterTest {


	@Mock
	private IFormBuilder formBuilder;
	
	@Spy
	private FormsExporter formExporter;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void shouldAddSubmitButtonActions_NotAddActionIfLeffOperand_IsEmpty() throws Exception {
		SubmitFormButton formButton = FormFactory.eINSTANCE.createSubmitFormButton();
		formButton.getActions().add(createOperation("","value",ExpressionConstants.ASSIGNMENT_OPERATOR));
		formExporter.addSubmitButtonActions(formBuilder, formButton);
		verifyZeroInteractions(formBuilder);
	}
	
	@Test
	public void shouldAddSubmitButtonActions_NotAddActionIfRightOperand_IsEmpty() throws Exception {
		SubmitFormButton formButton = FormFactory.eINSTANCE.createSubmitFormButton();
		formButton.getActions().add(createOperation("data","",ExpressionConstants.ASSIGNMENT_OPERATOR));
		formExporter.addSubmitButtonActions(formBuilder, formButton);
		verifyZeroInteractions(formBuilder);
	}
	
	@Test
	public void shouldAddSubmitButtonActions_AddActionIfRightAndLeftOperand_NotEmpty() throws Exception {
		SubmitFormButton formButton = FormFactory.eINSTANCE.createSubmitFormButton();
		Operation operation = createOperation("data","value", ExpressionConstants.ASSIGNMENT_OPERATOR);
		formButton.getActions().add(operation);
		formExporter.addSubmitButtonActions(formBuilder, formButton);
		verify(formExporter).addAction(formBuilder,operation);
	}


	private Operation createOperation(String leftOpreand, String rightOperand,String operatorType) {
		Operation operation = ExpressionFactory.eINSTANCE.createOperation();
		Expression leftExpression= ExpressionFactory.eINSTANCE.createExpression();
		leftExpression.setName(leftOpreand);
		leftExpression.setContent(leftOpreand);
		operation.setLeftOperand(leftExpression);
		
		Expression rightExpression= ExpressionFactory.eINSTANCE.createExpression();
		rightExpression.setName(leftOpreand);
		rightExpression.setContent(rightOperand);
		operation.setRightOperand(rightExpression);
		
		Operator op =  ExpressionFactory.eINSTANCE.createOperator();
		op.setType(operatorType);
		operation.setOperator(op);
		
		return operation;
	}
	

}
