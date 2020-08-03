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
package org.bonitasoft.studio.model.form;

import org.bonitasoft.studio.model.expression.Expression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Validator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.Validator#getValidatorClass <em>Validator Class</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Validator#getHtmlClass <em>Html Class</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Validator#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Validator#isBelowField <em>Below Field</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Validator#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Validator#getDisplayName <em>Display Name</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getValidator()
 * @model
 * @generated
 */
public interface Validator extends EObject {
	/**
     * Returns the value of the '<em><b>Validator Class</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The qualified name of the validator class which need to extend IFormFieldValidator or IFormPageValidator.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Validator Class</em>' attribute.
     * @see #setValidatorClass(String)
     * @see org.bonitasoft.studio.model.form.FormPackage#getValidator_ValidatorClass()
     * @model
     * @generated
     */
	String getValidatorClass();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Validator#getValidatorClass <em>Validator Class</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Validator Class</em>' attribute.
     * @see #getValidatorClass()
     * @generated
     */
	void setValidatorClass(String value);

	/**
     * Returns the value of the '<em><b>Html Class</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Html Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Html Class</em>' attribute.
     * @see #setHtmlClass(String)
     * @see org.bonitasoft.studio.model.form.FormPackage#getValidator_HtmlClass()
     * @model
     * @generated
     */
	String getHtmlClass();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Validator#getHtmlClass <em>Html Class</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Html Class</em>' attribute.
     * @see #getHtmlClass()
     * @generated
     */
	void setHtmlClass(String value);

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
     * @see org.bonitasoft.studio.model.form.FormPackage#getValidator_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Validator#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Below Field</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Below Field</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Below Field</em>' attribute.
     * @see #setBelowField(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getValidator_BelowField()
     * @model default="true"
     * @generated
     */
	boolean isBelowField();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Validator#isBelowField <em>Below Field</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Below Field</em>' attribute.
     * @see #isBelowField()
     * @generated
     */
	void setBelowField(boolean value);

	/**
     * Returns the value of the '<em><b>Parameter</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter</em>' containment reference.
     * @see #setParameter(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getValidator_Parameter()
     * @model containment="true"
     * @generated
     */
	Expression getParameter();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Validator#getParameter <em>Parameter</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parameter</em>' containment reference.
     * @see #getParameter()
     * @generated
     */
	void setParameter(Expression value);

	/**
     * Returns the value of the '<em><b>Display Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Display Name</em>' containment reference.
     * @see #setDisplayName(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getValidator_DisplayName()
     * @model containment="true"
     * @generated
     */
	Expression getDisplayName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Validator#getDisplayName <em>Display Name</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Name</em>' containment reference.
     * @see #getDisplayName()
     * @generated
     */
	void setDisplayName(Expression value);

} // Validator
