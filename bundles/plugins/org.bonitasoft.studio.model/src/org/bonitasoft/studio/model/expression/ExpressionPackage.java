/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
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
package org.bonitasoft.studio.model.expression;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.expression.ExpressionFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/edapt historyURI='process.history'"
 * @generated
 */
public interface ExpressionPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "expression"; //$NON-NLS-1$

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://www.bonitasoft.org/ns/studio/expression"; //$NON-NLS-1$

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "expression"; //$NON-NLS-1$

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ExpressionPackage eINSTANCE = org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl.init();

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.expression.impl.AbstractExpressionImpl <em>Abstract Expression</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.expression.impl.AbstractExpressionImpl
     * @see org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl#getAbstractExpression()
     * @generated
     */
	int ABSTRACT_EXPRESSION = 0;

	/**
     * The number of structural features of the '<em>Abstract Expression</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_EXPRESSION_FEATURE_COUNT = 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.expression.impl.ExpressionImpl
     * @see org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl#getExpression()
     * @generated
     */
	int EXPRESSION = 1;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXPRESSION__NAME = ABSTRACT_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Content</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXPRESSION__CONTENT = ABSTRACT_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Interpreter</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXPRESSION__INTERPRETER = ABSTRACT_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXPRESSION__TYPE = ABSTRACT_EXPRESSION_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Return Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXPRESSION__RETURN_TYPE = ABSTRACT_EXPRESSION_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Referenced Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXPRESSION__REFERENCED_ELEMENTS = ABSTRACT_EXPRESSION_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXPRESSION__CONNECTORS = ABSTRACT_EXPRESSION_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Propagate Variable Change</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXPRESSION__PROPAGATE_VARIABLE_CHANGE = ABSTRACT_EXPRESSION_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Return Type Fixed</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXPRESSION__RETURN_TYPE_FIXED = ABSTRACT_EXPRESSION_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Automatic Dependencies</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXPRESSION__AUTOMATIC_DEPENDENCIES = ABSTRACT_EXPRESSION_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>Html Allowed</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXPRESSION__HTML_ALLOWED = ABSTRACT_EXPRESSION_FEATURE_COUNT + 10;

	/**
     * The number of structural features of the '<em>Expression</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXPRESSION_FEATURE_COUNT = ABSTRACT_EXPRESSION_FEATURE_COUNT + 11;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.expression.impl.ListExpressionImpl <em>List Expression</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.expression.impl.ListExpressionImpl
     * @see org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl#getListExpression()
     * @generated
     */
	int LIST_EXPRESSION = 2;

	/**
     * The feature id for the '<em><b>Expressions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_EXPRESSION__EXPRESSIONS = ABSTRACT_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>List Expression</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_EXPRESSION_FEATURE_COUNT = ABSTRACT_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.expression.impl.TableExpressionImpl <em>Table Expression</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.expression.impl.TableExpressionImpl
     * @see org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl#getTableExpression()
     * @generated
     */
	int TABLE_EXPRESSION = 3;

	/**
     * The feature id for the '<em><b>Expressions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__EXPRESSIONS = ABSTRACT_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Table Expression</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION_FEATURE_COUNT = ABSTRACT_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.expression.impl.OperationImpl <em>Operation</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.expression.impl.OperationImpl
     * @see org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl#getOperation()
     * @generated
     */
	int OPERATION = 4;

	/**
     * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OPERATION__LEFT_OPERAND = 0;

	/**
     * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OPERATION__RIGHT_OPERAND = 1;

	/**
     * The feature id for the '<em><b>Operator</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OPERATION__OPERATOR = 2;

	/**
     * The number of structural features of the '<em>Operation</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OPERATION_FEATURE_COUNT = 3;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.expression.impl.OperatorImpl <em>Operator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.expression.impl.OperatorImpl
     * @see org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl#getOperator()
     * @generated
     */
	int OPERATOR = 5;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OPERATOR__TYPE = 0;

	/**
     * The feature id for the '<em><b>Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OPERATOR__EXPRESSION = 1;

	/**
     * The feature id for the '<em><b>Input Types</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OPERATOR__INPUT_TYPES = 2;

	/**
     * The number of structural features of the '<em>Operator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int OPERATOR_FEATURE_COUNT = 3;


	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.expression.AbstractExpression <em>Abstract Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Expression</em>'.
     * @see org.bonitasoft.studio.model.expression.AbstractExpression
     * @generated
     */
	EClass getAbstractExpression();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.expression.Expression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Expression</em>'.
     * @see org.bonitasoft.studio.model.expression.Expression
     * @generated
     */
	EClass getExpression();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.expression.Expression#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.expression.Expression#getName()
     * @see #getExpression()
     * @generated
     */
	EAttribute getExpression_Name();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.expression.Expression#getContent <em>Content</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Content</em>'.
     * @see org.bonitasoft.studio.model.expression.Expression#getContent()
     * @see #getExpression()
     * @generated
     */
	EAttribute getExpression_Content();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.expression.Expression#getInterpreter <em>Interpreter</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Interpreter</em>'.
     * @see org.bonitasoft.studio.model.expression.Expression#getInterpreter()
     * @see #getExpression()
     * @generated
     */
	EAttribute getExpression_Interpreter();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.expression.Expression#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.bonitasoft.studio.model.expression.Expression#getType()
     * @see #getExpression()
     * @generated
     */
	EAttribute getExpression_Type();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.expression.Expression#getReturnType <em>Return Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Return Type</em>'.
     * @see org.bonitasoft.studio.model.expression.Expression#getReturnType()
     * @see #getExpression()
     * @generated
     */
	EAttribute getExpression_ReturnType();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.expression.Expression#getReferencedElements <em>Referenced Elements</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Referenced Elements</em>'.
     * @see org.bonitasoft.studio.model.expression.Expression#getReferencedElements()
     * @see #getExpression()
     * @generated
     */
	EReference getExpression_ReferencedElements();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.expression.Expression#getConnectors <em>Connectors</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Connectors</em>'.
     * @see org.bonitasoft.studio.model.expression.Expression#getConnectors()
     * @see #getExpression()
     * @generated
     */
	EReference getExpression_Connectors();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.expression.Expression#getPropagateVariableChange <em>Propagate Variable Change</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Propagate Variable Change</em>'.
     * @see org.bonitasoft.studio.model.expression.Expression#getPropagateVariableChange()
     * @see #getExpression()
     * @generated
     */
	EAttribute getExpression_PropagateVariableChange();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.expression.Expression#isReturnTypeFixed <em>Return Type Fixed</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Return Type Fixed</em>'.
     * @see org.bonitasoft.studio.model.expression.Expression#isReturnTypeFixed()
     * @see #getExpression()
     * @generated
     */
	EAttribute getExpression_ReturnTypeFixed();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.expression.Expression#isAutomaticDependencies <em>Automatic Dependencies</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Automatic Dependencies</em>'.
     * @see org.bonitasoft.studio.model.expression.Expression#isAutomaticDependencies()
     * @see #getExpression()
     * @generated
     */
	EAttribute getExpression_AutomaticDependencies();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.expression.Expression#isHtmlAllowed <em>Html Allowed</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Html Allowed</em>'.
     * @see org.bonitasoft.studio.model.expression.Expression#isHtmlAllowed()
     * @see #getExpression()
     * @generated
     */
	EAttribute getExpression_HtmlAllowed();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.expression.ListExpression <em>List Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>List Expression</em>'.
     * @see org.bonitasoft.studio.model.expression.ListExpression
     * @generated
     */
	EClass getListExpression();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.expression.ListExpression#getExpressions <em>Expressions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Expressions</em>'.
     * @see org.bonitasoft.studio.model.expression.ListExpression#getExpressions()
     * @see #getListExpression()
     * @generated
     */
	EReference getListExpression_Expressions();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.expression.TableExpression <em>Table Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table Expression</em>'.
     * @see org.bonitasoft.studio.model.expression.TableExpression
     * @generated
     */
	EClass getTableExpression();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.expression.TableExpression#getExpressions <em>Expressions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Expressions</em>'.
     * @see org.bonitasoft.studio.model.expression.TableExpression#getExpressions()
     * @see #getTableExpression()
     * @generated
     */
	EReference getTableExpression_Expressions();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.expression.Operation <em>Operation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Operation</em>'.
     * @see org.bonitasoft.studio.model.expression.Operation
     * @generated
     */
	EClass getOperation();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.expression.Operation#getLeftOperand <em>Left Operand</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Left Operand</em>'.
     * @see org.bonitasoft.studio.model.expression.Operation#getLeftOperand()
     * @see #getOperation()
     * @generated
     */
	EReference getOperation_LeftOperand();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.expression.Operation#getRightOperand <em>Right Operand</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Right Operand</em>'.
     * @see org.bonitasoft.studio.model.expression.Operation#getRightOperand()
     * @see #getOperation()
     * @generated
     */
	EReference getOperation_RightOperand();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.expression.Operation#getOperator <em>Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Operator</em>'.
     * @see org.bonitasoft.studio.model.expression.Operation#getOperator()
     * @see #getOperation()
     * @generated
     */
	EReference getOperation_Operator();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.expression.Operator <em>Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Operator</em>'.
     * @see org.bonitasoft.studio.model.expression.Operator
     * @generated
     */
	EClass getOperator();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.expression.Operator#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.bonitasoft.studio.model.expression.Operator#getType()
     * @see #getOperator()
     * @generated
     */
	EAttribute getOperator_Type();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.expression.Operator#getExpression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Expression</em>'.
     * @see org.bonitasoft.studio.model.expression.Operator#getExpression()
     * @see #getOperator()
     * @generated
     */
	EAttribute getOperator_Expression();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.expression.Operator#getInputTypes <em>Input Types</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Input Types</em>'.
     * @see org.bonitasoft.studio.model.expression.Operator#getInputTypes()
     * @see #getOperator()
     * @generated
     */
	EAttribute getOperator_InputTypes();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	ExpressionFactory getExpressionFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.expression.impl.AbstractExpressionImpl <em>Abstract Expression</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.expression.impl.AbstractExpressionImpl
         * @see org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl#getAbstractExpression()
         * @generated
         */
		EClass ABSTRACT_EXPRESSION = eINSTANCE.getAbstractExpression();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.expression.impl.ExpressionImpl <em>Expression</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.expression.impl.ExpressionImpl
         * @see org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl#getExpression()
         * @generated
         */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute EXPRESSION__NAME = eINSTANCE.getExpression_Name();

		/**
         * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute EXPRESSION__CONTENT = eINSTANCE.getExpression_Content();

		/**
         * The meta object literal for the '<em><b>Interpreter</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute EXPRESSION__INTERPRETER = eINSTANCE.getExpression_Interpreter();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute EXPRESSION__TYPE = eINSTANCE.getExpression_Type();

		/**
         * The meta object literal for the '<em><b>Return Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute EXPRESSION__RETURN_TYPE = eINSTANCE.getExpression_ReturnType();

		/**
         * The meta object literal for the '<em><b>Referenced Elements</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference EXPRESSION__REFERENCED_ELEMENTS = eINSTANCE.getExpression_ReferencedElements();

		/**
         * The meta object literal for the '<em><b>Connectors</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference EXPRESSION__CONNECTORS = eINSTANCE.getExpression_Connectors();

		/**
         * The meta object literal for the '<em><b>Propagate Variable Change</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute EXPRESSION__PROPAGATE_VARIABLE_CHANGE = eINSTANCE.getExpression_PropagateVariableChange();

		/**
         * The meta object literal for the '<em><b>Return Type Fixed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute EXPRESSION__RETURN_TYPE_FIXED = eINSTANCE.getExpression_ReturnTypeFixed();

		/**
         * The meta object literal for the '<em><b>Automatic Dependencies</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute EXPRESSION__AUTOMATIC_DEPENDENCIES = eINSTANCE.getExpression_AutomaticDependencies();

		/**
         * The meta object literal for the '<em><b>Html Allowed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute EXPRESSION__HTML_ALLOWED = eINSTANCE.getExpression_HtmlAllowed();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.expression.impl.ListExpressionImpl <em>List Expression</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.expression.impl.ListExpressionImpl
         * @see org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl#getListExpression()
         * @generated
         */
		EClass LIST_EXPRESSION = eINSTANCE.getListExpression();

		/**
         * The meta object literal for the '<em><b>Expressions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference LIST_EXPRESSION__EXPRESSIONS = eINSTANCE.getListExpression_Expressions();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.expression.impl.TableExpressionImpl <em>Table Expression</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.expression.impl.TableExpressionImpl
         * @see org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl#getTableExpression()
         * @generated
         */
		EClass TABLE_EXPRESSION = eINSTANCE.getTableExpression();

		/**
         * The meta object literal for the '<em><b>Expressions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_EXPRESSION__EXPRESSIONS = eINSTANCE.getTableExpression_Expressions();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.expression.impl.OperationImpl <em>Operation</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.expression.impl.OperationImpl
         * @see org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl#getOperation()
         * @generated
         */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
         * The meta object literal for the '<em><b>Left Operand</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference OPERATION__LEFT_OPERAND = eINSTANCE.getOperation_LeftOperand();

		/**
         * The meta object literal for the '<em><b>Right Operand</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference OPERATION__RIGHT_OPERAND = eINSTANCE.getOperation_RightOperand();

		/**
         * The meta object literal for the '<em><b>Operator</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference OPERATION__OPERATOR = eINSTANCE.getOperation_Operator();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.expression.impl.OperatorImpl <em>Operator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.expression.impl.OperatorImpl
         * @see org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl#getOperator()
         * @generated
         */
		EClass OPERATOR = eINSTANCE.getOperator();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute OPERATOR__TYPE = eINSTANCE.getOperator_Type();

		/**
         * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute OPERATOR__EXPRESSION = eINSTANCE.getOperator_Expression();

		/**
         * The meta object literal for the '<em><b>Input Types</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute OPERATOR__INPUT_TYPES = eINSTANCE.getOperator_InputTypes();

	}

} //ExpressionPackage
