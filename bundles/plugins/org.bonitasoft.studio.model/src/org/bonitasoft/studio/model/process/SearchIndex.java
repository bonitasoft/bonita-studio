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
package org.bonitasoft.studio.model.process;

import org.bonitasoft.studio.model.expression.Expression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Search Index</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.SearchIndex#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.SearchIndex#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getSearchIndex()
 * @model
 * @generated
 */
public interface SearchIndex extends EObject {
	/**
     * Returns the value of the '<em><b>Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' containment reference.
     * @see #setName(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getSearchIndex_Name()
     * @model containment="true"
     * @generated
     */
	Expression getName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.SearchIndex#getName <em>Name</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' containment reference.
     * @see #getName()
     * @generated
     */
	void setName(Expression value);

	/**
     * Returns the value of the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' containment reference.
     * @see #setValue(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getSearchIndex_Value()
     * @model containment="true"
     * @generated
     */
	Expression getValue();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.SearchIndex#getValue <em>Value</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' containment reference.
     * @see #getValue()
     * @generated
     */
	void setValue(Expression value);

} // SearchIndex
