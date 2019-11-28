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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.Group#getWidgets <em>Widgets</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Group#isShowBorder <em>Show Border</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Group#getColumns <em>Columns</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Group#getLines <em>Lines</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Group#isUseIterator <em>Use Iterator</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Group#getIterator <em>Iterator</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getGroup()
 * @model
 * @generated
 */
public interface Group extends Widget, Duplicable {
	/**
     * Returns the value of the '<em><b>Widgets</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.form.Widget}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widgets</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Widgets</em>' containment reference list.
     * @see org.bonitasoft.studio.model.form.FormPackage#getGroup_Widgets()
     * @model containment="true"
     * @generated
     */
	EList<Widget> getWidgets();

	/**
     * Returns the value of the '<em><b>Show Border</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Show Border</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Show Border</em>' attribute.
     * @see #setShowBorder(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getGroup_ShowBorder()
     * @model default="false" required="true"
     * @generated
     */
	boolean isShowBorder();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Group#isShowBorder <em>Show Border</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Show Border</em>' attribute.
     * @see #isShowBorder()
     * @generated
     */
	void setShowBorder(boolean value);

	/**
     * Returns the value of the '<em><b>Columns</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.form.Column}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Columns</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Columns</em>' containment reference list.
     * @see org.bonitasoft.studio.model.form.FormPackage#getGroup_Columns()
     * @model containment="true"
     * @generated
     */
	EList<Column> getColumns();

	/**
     * Returns the value of the '<em><b>Lines</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.form.Line}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lines</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Lines</em>' containment reference list.
     * @see org.bonitasoft.studio.model.form.FormPackage#getGroup_Lines()
     * @model containment="true"
     * @generated
     */
	EList<Line> getLines();

	/**
     * Returns the value of the '<em><b>Use Iterator</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Iterator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Use Iterator</em>' attribute.
     * @see #setUseIterator(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getGroup_UseIterator()
     * @model default="false" required="true"
     * @generated
     */
	boolean isUseIterator();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Group#isUseIterator <em>Use Iterator</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Use Iterator</em>' attribute.
     * @see #isUseIterator()
     * @generated
     */
	void setUseIterator(boolean value);

	/**
     * Returns the value of the '<em><b>Iterator</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterator</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Iterator</em>' containment reference.
     * @see #setIterator(GroupIterator)
     * @see org.bonitasoft.studio.model.form.FormPackage#getGroup_Iterator()
     * @model containment="true"
     * @generated
     */
	GroupIterator getIterator();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Group#getIterator <em>Iterator</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Iterator</em>' containment reference.
     * @see #getIterator()
     * @generated
     */
	void setIterator(GroupIterator value);

} // Group
