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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mandatory Fields Customization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.MandatoryFieldsCustomization#getMandatorySymbol <em>Mandatory Symbol</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.MandatoryFieldsCustomization#getMandatoryLabel <em>Mandatory Label</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getMandatoryFieldsCustomization()
 * @model abstract="true"
 * @generated
 */
public interface MandatoryFieldsCustomization extends CSSCustomizable {
	/**
     * Returns the value of the '<em><b>Mandatory Symbol</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory Symbol</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Mandatory Symbol</em>' containment reference.
     * @see #setMandatorySymbol(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getMandatoryFieldsCustomization_MandatorySymbol()
     * @model containment="true"
     * @generated
     */
	Expression getMandatorySymbol();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.MandatoryFieldsCustomization#getMandatorySymbol <em>Mandatory Symbol</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mandatory Symbol</em>' containment reference.
     * @see #getMandatorySymbol()
     * @generated
     */
	void setMandatorySymbol(Expression value);

	/**
     * Returns the value of the '<em><b>Mandatory Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory Label</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Mandatory Label</em>' containment reference.
     * @see #setMandatoryLabel(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getMandatoryFieldsCustomization_MandatoryLabel()
     * @model containment="true"
     * @generated
     */
	Expression getMandatoryLabel();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.MandatoryFieldsCustomization#getMandatoryLabel <em>Mandatory Label</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mandatory Label</em>' containment reference.
     * @see #getMandatoryLabel()
     * @generated
     */
	void setMandatoryLabel(Expression value);

} // MandatoryFieldsCustomization
