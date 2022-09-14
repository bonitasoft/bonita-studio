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
 * A representation of the model object '<em><b>Duration Form Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.DurationFormField#getDay <em>Day</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DurationFormField#getHour <em>Hour</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DurationFormField#getMin <em>Min</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DurationFormField#getSec <em>Sec</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getDurationFormField()
 * @model
 * @generated
 */
public interface DurationFormField extends SingleValuatedFormField, ItemContainer {
	/**
     * Returns the value of the '<em><b>Day</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Day</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Day</em>' attribute.
     * @see #setDay(Boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDurationFormField_Day()
     * @model default="true" required="true"
     * @generated
     */
	Boolean getDay();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DurationFormField#getDay <em>Day</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Day</em>' attribute.
     * @see #getDay()
     * @generated
     */
	void setDay(Boolean value);

	/**
     * Returns the value of the '<em><b>Hour</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hour</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Hour</em>' attribute.
     * @see #setHour(Boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDurationFormField_Hour()
     * @model default="true" required="true"
     * @generated
     */
	Boolean getHour();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DurationFormField#getHour <em>Hour</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Hour</em>' attribute.
     * @see #getHour()
     * @generated
     */
	void setHour(Boolean value);

	/**
     * Returns the value of the '<em><b>Min</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Min</em>' attribute.
     * @see #setMin(Boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDurationFormField_Min()
     * @model default="true" required="true"
     * @generated
     */
	Boolean getMin();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DurationFormField#getMin <em>Min</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min</em>' attribute.
     * @see #getMin()
     * @generated
     */
	void setMin(Boolean value);

	/**
     * Returns the value of the '<em><b>Sec</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sec</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Sec</em>' attribute.
     * @see #setSec(Boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDurationFormField_Sec()
     * @model default="true" required="true"
     * @generated
     */
	Boolean getSec();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DurationFormField#getSec <em>Sec</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Sec</em>' attribute.
     * @see #getSec()
     * @generated
     */
	void setSec(Boolean value);

} // DurationFormField
