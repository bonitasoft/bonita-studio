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
import org.bonitasoft.studio.model.expression.TableExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.AbstractTable#isLeftColumnIsHeader <em>Left Column Is Header</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.AbstractTable#isRightColumnIsHeader <em>Right Column Is Header</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.AbstractTable#isFirstRowIsHeader <em>First Row Is Header</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.AbstractTable#isLastRowIsHeader <em>Last Row Is Header</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.AbstractTable#isInitializedUsingCells <em>Initialized Using Cells</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.AbstractTable#isUseHorizontalHeader <em>Use Horizontal Header</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.AbstractTable#isUseVerticalHeader <em>Use Vertical Header</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.AbstractTable#getHorizontalHeaderExpression <em>Horizontal Header Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.AbstractTable#getVerticalHeaderExpression <em>Vertical Header Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.AbstractTable#getTableExpression <em>Table Expression</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getAbstractTable()
 * @model abstract="true"
 * @generated
 */
public interface AbstractTable extends Widget, Duplicable {
	/**
     * Returns the value of the '<em><b>Left Column Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Column Is Header</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Left Column Is Header</em>' attribute.
     * @see #setLeftColumnIsHeader(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getAbstractTable_LeftColumnIsHeader()
     * @model
     * @generated
     */
	boolean isLeftColumnIsHeader();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.AbstractTable#isLeftColumnIsHeader <em>Left Column Is Header</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Left Column Is Header</em>' attribute.
     * @see #isLeftColumnIsHeader()
     * @generated
     */
	void setLeftColumnIsHeader(boolean value);

	/**
     * Returns the value of the '<em><b>Right Column Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Column Is Header</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Right Column Is Header</em>' attribute.
     * @see #setRightColumnIsHeader(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getAbstractTable_RightColumnIsHeader()
     * @model
     * @generated
     */
	boolean isRightColumnIsHeader();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.AbstractTable#isRightColumnIsHeader <em>Right Column Is Header</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Right Column Is Header</em>' attribute.
     * @see #isRightColumnIsHeader()
     * @generated
     */
	void setRightColumnIsHeader(boolean value);

	/**
     * Returns the value of the '<em><b>First Row Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Row Is Header</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>First Row Is Header</em>' attribute.
     * @see #setFirstRowIsHeader(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getAbstractTable_FirstRowIsHeader()
     * @model
     * @generated
     */
	boolean isFirstRowIsHeader();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.AbstractTable#isFirstRowIsHeader <em>First Row Is Header</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>First Row Is Header</em>' attribute.
     * @see #isFirstRowIsHeader()
     * @generated
     */
	void setFirstRowIsHeader(boolean value);

	/**
     * Returns the value of the '<em><b>Last Row Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Row Is Header</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Last Row Is Header</em>' attribute.
     * @see #setLastRowIsHeader(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getAbstractTable_LastRowIsHeader()
     * @model
     * @generated
     */
	boolean isLastRowIsHeader();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.AbstractTable#isLastRowIsHeader <em>Last Row Is Header</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Last Row Is Header</em>' attribute.
     * @see #isLastRowIsHeader()
     * @generated
     */
	void setLastRowIsHeader(boolean value);

	/**
     * Returns the value of the '<em><b>Initialized Using Cells</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initialized Using Cells</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Initialized Using Cells</em>' attribute.
     * @see #setInitializedUsingCells(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getAbstractTable_InitializedUsingCells()
     * @model
     * @generated
     */
	boolean isInitializedUsingCells();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.AbstractTable#isInitializedUsingCells <em>Initialized Using Cells</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Initialized Using Cells</em>' attribute.
     * @see #isInitializedUsingCells()
     * @generated
     */
	void setInitializedUsingCells(boolean value);

	/**
     * Returns the value of the '<em><b>Use Horizontal Header</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Horizontal Header</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Use Horizontal Header</em>' attribute.
     * @see #setUseHorizontalHeader(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getAbstractTable_UseHorizontalHeader()
     * @model default="false"
     * @generated
     */
	boolean isUseHorizontalHeader();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.AbstractTable#isUseHorizontalHeader <em>Use Horizontal Header</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Use Horizontal Header</em>' attribute.
     * @see #isUseHorizontalHeader()
     * @generated
     */
	void setUseHorizontalHeader(boolean value);

	/**
     * Returns the value of the '<em><b>Use Vertical Header</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Vertical Header</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Use Vertical Header</em>' attribute.
     * @see #setUseVerticalHeader(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getAbstractTable_UseVerticalHeader()
     * @model default="false"
     * @generated
     */
	boolean isUseVerticalHeader();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.AbstractTable#isUseVerticalHeader <em>Use Vertical Header</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Use Vertical Header</em>' attribute.
     * @see #isUseVerticalHeader()
     * @generated
     */
	void setUseVerticalHeader(boolean value);

	/**
     * Returns the value of the '<em><b>Horizontal Header Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Horizontal Header Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Horizontal Header Expression</em>' containment reference.
     * @see #setHorizontalHeaderExpression(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getAbstractTable_HorizontalHeaderExpression()
     * @model containment="true"
     * @generated
     */
	Expression getHorizontalHeaderExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.AbstractTable#getHorizontalHeaderExpression <em>Horizontal Header Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Horizontal Header Expression</em>' containment reference.
     * @see #getHorizontalHeaderExpression()
     * @generated
     */
	void setHorizontalHeaderExpression(Expression value);

	/**
     * Returns the value of the '<em><b>Vertical Header Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vertical Header Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Vertical Header Expression</em>' containment reference.
     * @see #setVerticalHeaderExpression(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getAbstractTable_VerticalHeaderExpression()
     * @model containment="true"
     * @generated
     */
	Expression getVerticalHeaderExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.AbstractTable#getVerticalHeaderExpression <em>Vertical Header Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vertical Header Expression</em>' containment reference.
     * @see #getVerticalHeaderExpression()
     * @generated
     */
	void setVerticalHeaderExpression(Expression value);

	/**
     * Returns the value of the '<em><b>Table Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Table Expression</em>' containment reference.
     * @see #setTableExpression(TableExpression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getAbstractTable_TableExpression()
     * @model containment="true"
     * @generated
     */
	TableExpression getTableExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.AbstractTable#getTableExpression <em>Table Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Expression</em>' containment reference.
     * @see #getTableExpression()
     * @generated
     */
	void setTableExpression(TableExpression value);

} // AbstractTable
