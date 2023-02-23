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
 * A representation of the model object '<em><b>Business Object Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.BusinessObjectData#getBusinessDataRepositoryId <em>Business Data Repository Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.BusinessObjectData#isCreateNewInstance <em>Create New Instance</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.BusinessObjectData#getEClassName <em>EClass Name</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getBusinessObjectData()
 * @model
 * @generated
 */
public interface BusinessObjectData extends JavaObjectData {
	/**
     * Returns the value of the '<em><b>Business Data Repository Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Business Data Repository Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Business Data Repository Id</em>' attribute.
     * @see #setBusinessDataRepositoryId(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getBusinessObjectData_BusinessDataRepositoryId()
     * @model
     * @generated
     */
	String getBusinessDataRepositoryId();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.BusinessObjectData#getBusinessDataRepositoryId <em>Business Data Repository Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Business Data Repository Id</em>' attribute.
     * @see #getBusinessDataRepositoryId()
     * @generated
     */
	void setBusinessDataRepositoryId(String value);

	/**
     * Returns the value of the '<em><b>Create New Instance</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Create New Instance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Create New Instance</em>' attribute.
     * @see #setCreateNewInstance(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getBusinessObjectData_CreateNewInstance()
     * @model default="true"
     * @generated
     */
	boolean isCreateNewInstance();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.BusinessObjectData#isCreateNewInstance <em>Create New Instance</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Create New Instance</em>' attribute.
     * @see #isCreateNewInstance()
     * @generated
     */
	void setCreateNewInstance(boolean value);

	/**
     * Returns the value of the '<em><b>EClass Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EClass Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>EClass Name</em>' attribute.
     * @see #setEClassName(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getBusinessObjectData_EClassName()
     * @model
     * @generated
     */
	String getEClassName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.BusinessObjectData#getEClassName <em>EClass Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>EClass Name</em>' attribute.
     * @see #getEClassName()
     * @generated
     */
	void setEClassName(String value);

} // BusinessObjectData
