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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Form Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * mapped with LISTBOX_MULTIPLE
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.ListFormField#getMaxHeigth <em>Max Heigth</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getListFormField()
 * @model
 * @generated
 */
public interface ListFormField extends MultipleValuatedFormField {
	/**
     * Returns the value of the '<em><b>Max Heigth</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Heigth</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Max Heigth</em>' attribute.
     * @see #setMaxHeigth(int)
     * @see org.bonitasoft.studio.model.form.FormPackage#getListFormField_MaxHeigth()
     * @model
     * @generated
     */
	int getMaxHeigth();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.ListFormField#getMaxHeigth <em>Max Heigth</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max Heigth</em>' attribute.
     * @see #getMaxHeigth()
     * @generated
     */
	void setMaxHeigth(int value);

} // ListFormField
