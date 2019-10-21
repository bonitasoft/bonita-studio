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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contract Input Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.ContractInputMapping#getData <em>Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.ContractInputMapping#getSetterName <em>Setter Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.ContractInputMapping#getSetterParamType <em>Setter Param Type</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getContractInputMapping()
 * @model
 * @generated
 */
public interface ContractInputMapping extends EObject {
	/**
     * Returns the value of the '<em><b>Data</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Data</em>' reference.
     * @see #setData(Data)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getContractInputMapping_Data()
     * @model
     * @generated
     */
	Data getData();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ContractInputMapping#getData <em>Data</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data</em>' reference.
     * @see #getData()
     * @generated
     */
	void setData(Data value);

	/**
     * Returns the value of the '<em><b>Setter Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Setter Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Setter Name</em>' attribute.
     * @see #setSetterName(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getContractInputMapping_SetterName()
     * @model
     * @generated
     */
	String getSetterName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ContractInputMapping#getSetterName <em>Setter Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Setter Name</em>' attribute.
     * @see #getSetterName()
     * @generated
     */
	void setSetterName(String value);

	/**
     * Returns the value of the '<em><b>Setter Param Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Setter Param Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Setter Param Type</em>' attribute.
     * @see #setSetterParamType(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getContractInputMapping_SetterParamType()
     * @model
     * @generated
     */
	String getSetterParamType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ContractInputMapping#getSetterParamType <em>Setter Param Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Setter Param Type</em>' attribute.
     * @see #getSetterParamType()
     * @generated
     */
	void setSetterParamType(String value);

} // ContractInputMapping
