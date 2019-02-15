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

import org.bonitasoft.studio.model.process.Connector;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.expression.Expression#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Expression#getContent <em>Content</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Expression#getInterpreter <em>Interpreter</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Expression#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Expression#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Expression#getReferencedElements <em>Referenced Elements</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Expression#getConnectors <em>Connectors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Expression#getPropagateVariableChange <em>Propagate Variable Change</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Expression#isReturnTypeFixed <em>Return Type Fixed</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Expression#isAutomaticDependencies <em>Automatic Dependencies</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.expression.Expression#isHtmlAllowed <em>Html Allowed</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getExpression()
 * @model
 * @generated
 */
public interface Expression extends AbstractExpression {
	/**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getExpression_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Expression#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Content</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Content</em>' attribute.
     * @see #setContent(String)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getExpression_Content()
     * @model
     * @generated
     */
	String getContent();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Expression#getContent <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Content</em>' attribute.
     * @see #getContent()
     * @generated
     */
	void setContent(String value);

	/**
     * Returns the value of the '<em><b>Interpreter</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interpreter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Interpreter</em>' attribute.
     * @see #setInterpreter(String)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getExpression_Interpreter()
     * @model default=""
     * @generated
     */
	String getInterpreter();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Expression#getInterpreter <em>Interpreter</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Interpreter</em>' attribute.
     * @see #getInterpreter()
     * @generated
     */
	void setInterpreter(String value);

	/**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The default value is <code>"TYPE_CONSTANT"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getExpression_Type()
     * @model default="TYPE_CONSTANT"
     * @generated
     */
	String getType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Expression#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
	void setType(String value);

	/**
     * Returns the value of the '<em><b>Return Type</b></em>' attribute.
     * The default value is <code>"java.lang.String"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Return Type</em>' attribute.
     * @see #setReturnType(String)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getExpression_ReturnType()
     * @model default="java.lang.String"
     * @generated
     */
	String getReturnType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Expression#getReturnType <em>Return Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Return Type</em>' attribute.
     * @see #getReturnType()
     * @generated
     */
	void setReturnType(String value);

	/**
     * Returns the value of the '<em><b>Referenced Elements</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Referenced Elements</em>' containment reference list.
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getExpression_ReferencedElements()
     * @model containment="true"
     * @generated
     */
	EList<EObject> getReferencedElements();

	/**
     * Returns the value of the '<em><b>Connectors</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.Connector}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connectors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Connectors</em>' containment reference list.
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getExpression_Connectors()
     * @model containment="true"
     * @generated
     */
	EList<Connector> getConnectors();

	/**
     * Returns the value of the '<em><b>Propagate Variable Change</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Propagate Variable Change</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Propagate Variable Change</em>' attribute.
     * @see #setPropagateVariableChange(Boolean)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getExpression_PropagateVariableChange()
     * @model default="false"
     * @generated
     */
	Boolean getPropagateVariableChange();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Expression#getPropagateVariableChange <em>Propagate Variable Change</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Propagate Variable Change</em>' attribute.
     * @see #getPropagateVariableChange()
     * @generated
     */
	void setPropagateVariableChange(Boolean value);

	/**
     * Returns the value of the '<em><b>Return Type Fixed</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type Fixed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Return Type Fixed</em>' attribute.
     * @see #setReturnTypeFixed(boolean)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getExpression_ReturnTypeFixed()
     * @model default="false"
     * @generated
     */
	boolean isReturnTypeFixed();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Expression#isReturnTypeFixed <em>Return Type Fixed</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Return Type Fixed</em>' attribute.
     * @see #isReturnTypeFixed()
     * @generated
     */
	void setReturnTypeFixed(boolean value);

	/**
     * Returns the value of the '<em><b>Automatic Dependencies</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Automatic Dependencies</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Automatic Dependencies</em>' attribute.
     * @see #setAutomaticDependencies(boolean)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getExpression_AutomaticDependencies()
     * @model default="true"
     * @generated
     */
	boolean isAutomaticDependencies();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Expression#isAutomaticDependencies <em>Automatic Dependencies</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Automatic Dependencies</em>' attribute.
     * @see #isAutomaticDependencies()
     * @generated
     */
	void setAutomaticDependencies(boolean value);

	/**
     * Returns the value of the '<em><b>Html Allowed</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Html Allowed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Html Allowed</em>' attribute.
     * @see #setHtmlAllowed(boolean)
     * @see org.bonitasoft.studio.model.expression.ExpressionPackage#getExpression_HtmlAllowed()
     * @model default="false"
     * @generated
     */
	boolean isHtmlAllowed();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.expression.Expression#isHtmlAllowed <em>Html Allowed</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Html Allowed</em>' attribute.
     * @see #isHtmlAllowed()
     * @generated
     */
	void setHtmlAllowed(boolean value);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return content != null &amp;&amp; !content.isEmpty();'"
     * @generated
     */
	boolean hasContent();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return name != null &amp;&amp; !name.isEmpty();'"
     * @generated
     */
	boolean hasName();

} // Expression
