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
package org.bonitasoft.studio.model.configuration;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Definition Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getDefinitionId <em>Definition Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getDefinitionVersion <em>Definition Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getImplementationId <em>Implementation Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getImplementationVersion <em>Implementation Version</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getDefinitionMapping()
 * @model
 * @generated
 */
public interface DefinitionMapping extends EObject {
	/**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getDefinitionMapping_Type()
     * @model default="" required="true"
     * @generated
     */
	String getType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
	void setType(String value);

	/**
     * Returns the value of the '<em><b>Definition Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Definition Id</em>' attribute.
     * @see #setDefinitionId(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getDefinitionMapping_DefinitionId()
     * @model required="true"
     * @generated
     */
	String getDefinitionId();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getDefinitionId <em>Definition Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definition Id</em>' attribute.
     * @see #getDefinitionId()
     * @generated
     */
	void setDefinitionId(String value);

	/**
     * Returns the value of the '<em><b>Definition Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Definition Version</em>' attribute.
     * @see #setDefinitionVersion(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getDefinitionMapping_DefinitionVersion()
     * @model required="true"
     * @generated
     */
	String getDefinitionVersion();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getDefinitionVersion <em>Definition Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definition Version</em>' attribute.
     * @see #getDefinitionVersion()
     * @generated
     */
	void setDefinitionVersion(String value);

	/**
     * Returns the value of the '<em><b>Implementation Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Implementation Id</em>' attribute.
     * @see #setImplementationId(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getDefinitionMapping_ImplementationId()
     * @model
     * @generated
     */
	String getImplementationId();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getImplementationId <em>Implementation Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implementation Id</em>' attribute.
     * @see #getImplementationId()
     * @generated
     */
	void setImplementationId(String value);

	/**
     * Returns the value of the '<em><b>Implementation Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Implementation Version</em>' attribute.
     * @see #setImplementationVersion(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getDefinitionMapping_ImplementationVersion()
     * @model
     * @generated
     */
	String getImplementationVersion();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getImplementationVersion <em>Implementation Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implementation Version</em>' attribute.
     * @see #getImplementationVersion()
     * @generated
     */
	void setImplementationVersion(String value);

} // DefinitionMapping
