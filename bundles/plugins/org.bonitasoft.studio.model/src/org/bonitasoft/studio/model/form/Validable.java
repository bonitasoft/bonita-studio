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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Validable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.Validable#getValidators <em>Validators</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Validable#getUseDefaultValidator <em>Use Default Validator</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Validable#isBelow <em>Below</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getValidable()
 * @model
 * @generated
 */
public interface Validable extends EObject {
	/**
     * Returns the value of the '<em><b>Validators</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.form.Validator}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Validators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Validators</em>' containment reference list.
     * @see org.bonitasoft.studio.model.form.FormPackage#getValidable_Validators()
     * @model containment="true"
     * @generated
     */
	EList<Validator> getValidators();

	/**
     * Returns the value of the '<em><b>Use Default Validator</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Default Validator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Use Default Validator</em>' attribute.
     * @see #setUseDefaultValidator(Boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getValidable_UseDefaultValidator()
     * @model default="true"
     * @generated
     */
	Boolean getUseDefaultValidator();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Validable#getUseDefaultValidator <em>Use Default Validator</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Use Default Validator</em>' attribute.
     * @see #getUseDefaultValidator()
     * @generated
     */
	void setUseDefaultValidator(Boolean value);

	/**
     * Returns the value of the '<em><b>Below</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Below</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Below</em>' attribute.
     * @see #setBelow(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getValidable_Below()
     * @model default="true" required="true"
     * @generated
     */
	boolean isBelow();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Validable#isBelow <em>Below</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Below</em>' attribute.
     * @see #isBelow()
     * @generated
     */
	void setBelow(boolean value);

} // Validable
