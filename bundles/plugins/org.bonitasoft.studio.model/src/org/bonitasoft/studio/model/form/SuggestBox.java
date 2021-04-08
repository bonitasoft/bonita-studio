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
 * A representation of the model object '<em><b>Suggest Box</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.SuggestBox#getMaxItems <em>Max Items</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.SuggestBox#isUseMaxItems <em>Use Max Items</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.SuggestBox#isAsynchronous <em>Asynchronous</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.SuggestBox#getDelay <em>Delay</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getSuggestBox()
 * @model
 * @generated
 */
public interface SuggestBox extends MultipleValuatedFormField {
	/**
     * Returns the value of the '<em><b>Max Items</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Items</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Max Items</em>' attribute.
     * @see #setMaxItems(int)
     * @see org.bonitasoft.studio.model.form.FormPackage#getSuggestBox_MaxItems()
     * @model default="-1" required="true"
     * @generated
     */
	int getMaxItems();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.SuggestBox#getMaxItems <em>Max Items</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max Items</em>' attribute.
     * @see #getMaxItems()
     * @generated
     */
	void setMaxItems(int value);

	/**
     * Returns the value of the '<em><b>Use Max Items</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Max Items</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Use Max Items</em>' attribute.
     * @see #setUseMaxItems(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getSuggestBox_UseMaxItems()
     * @model default="false" required="true"
     * @generated
     */
	boolean isUseMaxItems();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.SuggestBox#isUseMaxItems <em>Use Max Items</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Use Max Items</em>' attribute.
     * @see #isUseMaxItems()
     * @generated
     */
	void setUseMaxItems(boolean value);

	/**
     * Returns the value of the '<em><b>Asynchronous</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Asynchronous</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Asynchronous</em>' attribute.
     * @see #setAsynchronous(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getSuggestBox_Asynchronous()
     * @model default="false" required="true"
     * @generated
     */
	boolean isAsynchronous();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.SuggestBox#isAsynchronous <em>Asynchronous</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Asynchronous</em>' attribute.
     * @see #isAsynchronous()
     * @generated
     */
	void setAsynchronous(boolean value);

	/**
     * Returns the value of the '<em><b>Delay</b></em>' attribute.
     * The default value is <code>"1000"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delay</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Delay</em>' attribute.
     * @see #setDelay(int)
     * @see org.bonitasoft.studio.model.form.FormPackage#getSuggestBox_Delay()
     * @model default="1000"
     * @generated
     */
	int getDelay();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.SuggestBox#getDelay <em>Delay</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Delay</em>' attribute.
     * @see #getDelay()
     * @generated
     */
	void setDelay(int value);

} // SuggestBox
