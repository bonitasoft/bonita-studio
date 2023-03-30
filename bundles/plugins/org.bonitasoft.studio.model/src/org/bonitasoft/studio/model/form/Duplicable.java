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
 * A representation of the model object '<em><b>Duplicable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Add option to choose if a widget can be dynamically duplicated
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.Duplicable#isDuplicate <em>Duplicate</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Duplicable#isLimitNumberOfDuplication <em>Limit Number Of Duplication</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Duplicable#isLimitMinNumberOfDuplication <em>Limit Min Number Of Duplication</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Duplicable#getMaxNumberOfDuplication <em>Max Number Of Duplication</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Duplicable#getMinNumberOfDuplication <em>Min Number Of Duplication</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Duplicable#getDisplayLabelForAdd <em>Display Label For Add</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Duplicable#getTooltipForAdd <em>Tooltip For Add</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Duplicable#getDisplayLabelForRemove <em>Display Label For Remove</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Duplicable#getTooltipForRemove <em>Tooltip For Remove</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getDuplicable()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Duplicable extends EObject {
	/**
     * Returns the value of the '<em><b>Duplicate</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duplicate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Duplicate</em>' attribute.
     * @see #setDuplicate(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDuplicable_Duplicate()
     * @model default="false" required="true"
     * @generated
     */
	boolean isDuplicate();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Duplicable#isDuplicate <em>Duplicate</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Duplicate</em>' attribute.
     * @see #isDuplicate()
     * @generated
     */
	void setDuplicate(boolean value);

	/**
     * Returns the value of the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Limit Number Of Duplication</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Limit Number Of Duplication</em>' attribute.
     * @see #setLimitNumberOfDuplication(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDuplicable_LimitNumberOfDuplication()
     * @model default="false"
     * @generated
     */
	boolean isLimitNumberOfDuplication();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Duplicable#isLimitNumberOfDuplication <em>Limit Number Of Duplication</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Limit Number Of Duplication</em>' attribute.
     * @see #isLimitNumberOfDuplication()
     * @generated
     */
	void setLimitNumberOfDuplication(boolean value);

	/**
     * Returns the value of the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Limit Min Number Of Duplication</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Limit Min Number Of Duplication</em>' attribute.
     * @see #setLimitMinNumberOfDuplication(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDuplicable_LimitMinNumberOfDuplication()
     * @model default="false"
     * @generated
     */
	boolean isLimitMinNumberOfDuplication();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Duplicable#isLimitMinNumberOfDuplication <em>Limit Min Number Of Duplication</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Limit Min Number Of Duplication</em>' attribute.
     * @see #isLimitMinNumberOfDuplication()
     * @generated
     */
	void setLimitMinNumberOfDuplication(boolean value);

	/**
     * Returns the value of the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Number Of Duplication</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Max Number Of Duplication</em>' containment reference.
     * @see #setMaxNumberOfDuplication(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDuplicable_MaxNumberOfDuplication()
     * @model containment="true"
     * @generated
     */
	Expression getMaxNumberOfDuplication();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Duplicable#getMaxNumberOfDuplication <em>Max Number Of Duplication</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max Number Of Duplication</em>' containment reference.
     * @see #getMaxNumberOfDuplication()
     * @generated
     */
	void setMaxNumberOfDuplication(Expression value);

	/**
     * Returns the value of the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Number Of Duplication</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Min Number Of Duplication</em>' containment reference.
     * @see #setMinNumberOfDuplication(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDuplicable_MinNumberOfDuplication()
     * @model containment="true"
     * @generated
     */
	Expression getMinNumberOfDuplication();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Duplicable#getMinNumberOfDuplication <em>Min Number Of Duplication</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min Number Of Duplication</em>' containment reference.
     * @see #getMinNumberOfDuplication()
     * @generated
     */
	void setMinNumberOfDuplication(Expression value);

	/**
     * Returns the value of the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Label For Add</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Display Label For Add</em>' containment reference.
     * @see #setDisplayLabelForAdd(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDuplicable_DisplayLabelForAdd()
     * @model containment="true"
     * @generated
     */
	Expression getDisplayLabelForAdd();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Duplicable#getDisplayLabelForAdd <em>Display Label For Add</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Label For Add</em>' containment reference.
     * @see #getDisplayLabelForAdd()
     * @generated
     */
	void setDisplayLabelForAdd(Expression value);

	/**
     * Returns the value of the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tooltip For Add</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Tooltip For Add</em>' containment reference.
     * @see #setTooltipForAdd(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDuplicable_TooltipForAdd()
     * @model containment="true"
     * @generated
     */
	Expression getTooltipForAdd();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Duplicable#getTooltipForAdd <em>Tooltip For Add</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Tooltip For Add</em>' containment reference.
     * @see #getTooltipForAdd()
     * @generated
     */
	void setTooltipForAdd(Expression value);

	/**
     * Returns the value of the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Label For Remove</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Display Label For Remove</em>' containment reference.
     * @see #setDisplayLabelForRemove(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDuplicable_DisplayLabelForRemove()
     * @model containment="true"
     * @generated
     */
	Expression getDisplayLabelForRemove();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Duplicable#getDisplayLabelForRemove <em>Display Label For Remove</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Label For Remove</em>' containment reference.
     * @see #getDisplayLabelForRemove()
     * @generated
     */
	void setDisplayLabelForRemove(Expression value);

	/**
     * Returns the value of the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tooltip For Remove</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Tooltip For Remove</em>' containment reference.
     * @see #setTooltipForRemove(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDuplicable_TooltipForRemove()
     * @model containment="true"
     * @generated
     */
	Expression getTooltipForRemove();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Duplicable#getTooltipForRemove <em>Tooltip For Remove</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Tooltip For Remove</em>' containment reference.
     * @see #getTooltipForRemove()
     * @generated
     */
	void setTooltipForRemove(Expression value);

} // Duplicable
