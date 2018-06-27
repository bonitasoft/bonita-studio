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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Page Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractPageFlow#getRegExpToHideDefaultField <em>Reg Exp To Hide Default Field</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractPageFlow#isUseRegExpToHideDefaultField <em>Use Reg Exp To Hide Default Field</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractPageFlow()
 * @model abstract="true"
 * @generated
 */
public interface AbstractPageFlow extends Element {
	/**
	 * Returns the value of the '<em><b>Reg Exp To Hide Default Field</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reg Exp To Hide Default Field</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reg Exp To Hide Default Field</em>' attribute.
	 * @see #setRegExpToHideDefaultField(String)
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractPageFlow_RegExpToHideDefaultField()
	 * @model
	 * @generated
	 */
	String getRegExpToHideDefaultField();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.model.process.AbstractPageFlow#getRegExpToHideDefaultField <em>Reg Exp To Hide Default Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reg Exp To Hide Default Field</em>' attribute.
	 * @see #getRegExpToHideDefaultField()
	 * @generated
	 */
	void setRegExpToHideDefaultField(String value);

	/**
	 * Returns the value of the '<em><b>Use Reg Exp To Hide Default Field</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Reg Exp To Hide Default Field</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Reg Exp To Hide Default Field</em>' attribute.
	 * @see #setUseRegExpToHideDefaultField(boolean)
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractPageFlow_UseRegExpToHideDefaultField()
	 * @model default="false"
	 * @generated
	 */
	boolean isUseRegExpToHideDefaultField();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.model.process.AbstractPageFlow#isUseRegExpToHideDefaultField <em>Use Reg Exp To Hide Default Field</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Reg Exp To Hide Default Field</em>' attribute.
	 * @see #isUseRegExpToHideDefaultField()
	 * @generated
	 */
	void setUseRegExpToHideDefaultField(boolean value);

} // AbstractPageFlow
