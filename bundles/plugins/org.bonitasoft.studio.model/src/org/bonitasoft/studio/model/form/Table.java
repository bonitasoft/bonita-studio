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
 * A representation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.Table#isUsePagination <em>Use Pagination</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Table#isAllowSelection <em>Allow Selection</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Table#isSelectionModeIsMultiple <em>Selection Mode Is Multiple</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Table#getMaxRowForPagination <em>Max Row For Pagination</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Table#getColumnForInitialSelectionIndex <em>Column For Initial Selection Index</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Table#getSelectedValues <em>Selected Values</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getTable()
 * @model
 * @generated
 */
public interface Table extends AbstractTable, MultipleValuatedFormField {
	/**
     * Returns the value of the '<em><b>Use Pagination</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Pagination</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Use Pagination</em>' attribute.
     * @see #setUsePagination(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getTable_UsePagination()
     * @model default="false"
     * @generated
     */
	boolean isUsePagination();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Table#isUsePagination <em>Use Pagination</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Use Pagination</em>' attribute.
     * @see #isUsePagination()
     * @generated
     */
	void setUsePagination(boolean value);

	/**
     * Returns the value of the '<em><b>Allow Selection</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allow Selection</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Allow Selection</em>' attribute.
     * @see #setAllowSelection(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getTable_AllowSelection()
     * @model default="false"
     * @generated
     */
	boolean isAllowSelection();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Table#isAllowSelection <em>Allow Selection</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Allow Selection</em>' attribute.
     * @see #isAllowSelection()
     * @generated
     */
	void setAllowSelection(boolean value);

	/**
     * Returns the value of the '<em><b>Selection Mode Is Multiple</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selection Mode Is Multiple</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Selection Mode Is Multiple</em>' attribute.
     * @see #setSelectionModeIsMultiple(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getTable_SelectionModeIsMultiple()
     * @model default="true"
     * @generated
     */
	boolean isSelectionModeIsMultiple();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Table#isSelectionModeIsMultiple <em>Selection Mode Is Multiple</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Selection Mode Is Multiple</em>' attribute.
     * @see #isSelectionModeIsMultiple()
     * @generated
     */
	void setSelectionModeIsMultiple(boolean value);

	/**
     * Returns the value of the '<em><b>Max Row For Pagination</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Row For Pagination</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Max Row For Pagination</em>' containment reference.
     * @see #setMaxRowForPagination(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getTable_MaxRowForPagination()
     * @model containment="true"
     * @generated
     */
	Expression getMaxRowForPagination();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Table#getMaxRowForPagination <em>Max Row For Pagination</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max Row For Pagination</em>' containment reference.
     * @see #getMaxRowForPagination()
     * @generated
     */
	void setMaxRowForPagination(Expression value);

	/**
     * Returns the value of the '<em><b>Column For Initial Selection Index</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column For Initial Selection Index</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Column For Initial Selection Index</em>' containment reference.
     * @see #setColumnForInitialSelectionIndex(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getTable_ColumnForInitialSelectionIndex()
     * @model containment="true"
     * @generated
     */
	Expression getColumnForInitialSelectionIndex();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Table#getColumnForInitialSelectionIndex <em>Column For Initial Selection Index</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column For Initial Selection Index</em>' containment reference.
     * @see #getColumnForInitialSelectionIndex()
     * @generated
     */
	void setColumnForInitialSelectionIndex(Expression value);

	/**
     * Returns the value of the '<em><b>Selected Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selected Values</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Selected Values</em>' containment reference.
     * @see #setSelectedValues(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getTable_SelectedValues()
     * @model containment="true"
     * @generated
     */
	Expression getSelectedValues();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Table#getSelectedValues <em>Selected Values</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Selected Values</em>' containment reference.
     * @see #getSelectedValues()
     * @generated
     */
	void setSelectedValues(Expression value);

} // Table
