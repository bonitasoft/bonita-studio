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
 * A representation of the model object '<em><b>Dynamic Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.DynamicTable#isLimitMinNumberOfColumn <em>Limit Min Number Of Column</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DynamicTable#isLimitMinNumberOfRow <em>Limit Min Number Of Row</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DynamicTable#isAllowAddRemoveRow <em>Allow Add Remove Row</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DynamicTable#isAllowAddRemoveColumn <em>Allow Add Remove Column</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DynamicTable#isLimitMaxNumberOfColumn <em>Limit Max Number Of Column</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DynamicTable#isLimitMaxNumberOfRow <em>Limit Max Number Of Row</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DynamicTable#getMinNumberOfColumn <em>Min Number Of Column</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DynamicTable#getMinNumberOfRow <em>Min Number Of Row</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DynamicTable#getMaxNumberOfColumn <em>Max Number Of Column</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.DynamicTable#getMaxNumberOfRow <em>Max Number Of Row</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getDynamicTable()
 * @model
 * @generated
 */
public interface DynamicTable extends AbstractTable, SingleValuatedFormField {
	/**
     * Returns the value of the '<em><b>Limit Min Number Of Column</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Limit Min Number Of Column</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Limit Min Number Of Column</em>' attribute.
     * @see #setLimitMinNumberOfColumn(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDynamicTable_LimitMinNumberOfColumn()
     * @model default="false"
     * @generated
     */
	boolean isLimitMinNumberOfColumn();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DynamicTable#isLimitMinNumberOfColumn <em>Limit Min Number Of Column</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Limit Min Number Of Column</em>' attribute.
     * @see #isLimitMinNumberOfColumn()
     * @generated
     */
	void setLimitMinNumberOfColumn(boolean value);

	/**
     * Returns the value of the '<em><b>Limit Min Number Of Row</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Limit Min Number Of Row</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Limit Min Number Of Row</em>' attribute.
     * @see #setLimitMinNumberOfRow(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDynamicTable_LimitMinNumberOfRow()
     * @model default="false"
     * @generated
     */
	boolean isLimitMinNumberOfRow();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DynamicTable#isLimitMinNumberOfRow <em>Limit Min Number Of Row</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Limit Min Number Of Row</em>' attribute.
     * @see #isLimitMinNumberOfRow()
     * @generated
     */
	void setLimitMinNumberOfRow(boolean value);

	/**
     * Returns the value of the '<em><b>Allow Add Remove Row</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allow Add Remove Row</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Allow Add Remove Row</em>' attribute.
     * @see #setAllowAddRemoveRow(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDynamicTable_AllowAddRemoveRow()
     * @model default="true"
     * @generated
     */
	boolean isAllowAddRemoveRow();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DynamicTable#isAllowAddRemoveRow <em>Allow Add Remove Row</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Allow Add Remove Row</em>' attribute.
     * @see #isAllowAddRemoveRow()
     * @generated
     */
	void setAllowAddRemoveRow(boolean value);

	/**
     * Returns the value of the '<em><b>Allow Add Remove Column</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allow Add Remove Column</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Allow Add Remove Column</em>' attribute.
     * @see #setAllowAddRemoveColumn(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDynamicTable_AllowAddRemoveColumn()
     * @model default="true"
     * @generated
     */
	boolean isAllowAddRemoveColumn();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DynamicTable#isAllowAddRemoveColumn <em>Allow Add Remove Column</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Allow Add Remove Column</em>' attribute.
     * @see #isAllowAddRemoveColumn()
     * @generated
     */
	void setAllowAddRemoveColumn(boolean value);

	/**
     * Returns the value of the '<em><b>Limit Max Number Of Column</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Limit Max Number Of Column</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Limit Max Number Of Column</em>' attribute.
     * @see #setLimitMaxNumberOfColumn(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDynamicTable_LimitMaxNumberOfColumn()
     * @model default="false"
     * @generated
     */
	boolean isLimitMaxNumberOfColumn();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DynamicTable#isLimitMaxNumberOfColumn <em>Limit Max Number Of Column</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Limit Max Number Of Column</em>' attribute.
     * @see #isLimitMaxNumberOfColumn()
     * @generated
     */
	void setLimitMaxNumberOfColumn(boolean value);

	/**
     * Returns the value of the '<em><b>Limit Max Number Of Row</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Limit Max Number Of Row</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Limit Max Number Of Row</em>' attribute.
     * @see #setLimitMaxNumberOfRow(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDynamicTable_LimitMaxNumberOfRow()
     * @model default="false"
     * @generated
     */
	boolean isLimitMaxNumberOfRow();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DynamicTable#isLimitMaxNumberOfRow <em>Limit Max Number Of Row</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Limit Max Number Of Row</em>' attribute.
     * @see #isLimitMaxNumberOfRow()
     * @generated
     */
	void setLimitMaxNumberOfRow(boolean value);

	/**
     * Returns the value of the '<em><b>Min Number Of Column</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Number Of Column</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Min Number Of Column</em>' containment reference.
     * @see #setMinNumberOfColumn(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDynamicTable_MinNumberOfColumn()
     * @model containment="true"
     * @generated
     */
	Expression getMinNumberOfColumn();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DynamicTable#getMinNumberOfColumn <em>Min Number Of Column</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min Number Of Column</em>' containment reference.
     * @see #getMinNumberOfColumn()
     * @generated
     */
	void setMinNumberOfColumn(Expression value);

	/**
     * Returns the value of the '<em><b>Min Number Of Row</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Number Of Row</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Min Number Of Row</em>' containment reference.
     * @see #setMinNumberOfRow(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDynamicTable_MinNumberOfRow()
     * @model containment="true"
     * @generated
     */
	Expression getMinNumberOfRow();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DynamicTable#getMinNumberOfRow <em>Min Number Of Row</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min Number Of Row</em>' containment reference.
     * @see #getMinNumberOfRow()
     * @generated
     */
	void setMinNumberOfRow(Expression value);

	/**
     * Returns the value of the '<em><b>Max Number Of Column</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Number Of Column</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Max Number Of Column</em>' containment reference.
     * @see #setMaxNumberOfColumn(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDynamicTable_MaxNumberOfColumn()
     * @model containment="true"
     * @generated
     */
	Expression getMaxNumberOfColumn();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DynamicTable#getMaxNumberOfColumn <em>Max Number Of Column</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max Number Of Column</em>' containment reference.
     * @see #getMaxNumberOfColumn()
     * @generated
     */
	void setMaxNumberOfColumn(Expression value);

	/**
     * Returns the value of the '<em><b>Max Number Of Row</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Number Of Row</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Max Number Of Row</em>' containment reference.
     * @see #setMaxNumberOfRow(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getDynamicTable_MaxNumberOfRow()
     * @model containment="true"
     * @generated
     */
	Expression getMaxNumberOfRow();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.DynamicTable#getMaxNumberOfRow <em>Max Number Of Row</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max Number Of Row</em>' containment reference.
     * @see #getMaxNumberOfRow()
     * @generated
     */
	void setMaxNumberOfRow(Expression value);

} // DynamicTable
