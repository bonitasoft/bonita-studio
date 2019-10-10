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

import org.bonitasoft.studio.model.actormapping.ActorMappingsType;

import org.bonitasoft.studio.model.parameter.Parameter;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Configuration#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Configuration#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Configuration#getActorMappings <em>Actor Mappings</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Configuration#getAnonymousUserName <em>Anonymous User Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Configuration#getAnonymousPassword <em>Anonymous Password</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Configuration#getDefinitionMappings <em>Definition Mappings</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Configuration#getProcessDependencies <em>Process Dependencies</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Configuration#getApplicationDependencies <em>Application Dependencies</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Configuration#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Configuration#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Configuration#getUsername <em>Username</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Configuration#getPassword <em>Password</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration()
 * @model
 * @generated
 */
public interface Configuration extends EObject {
	/**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration_Name()
     * @model required="true"
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Configuration#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration_Description()
     * @model
     * @generated
     */
	String getDescription();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Configuration#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

	/**
     * Returns the value of the '<em><b>Actor Mappings</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actor Mappings</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Actor Mappings</em>' containment reference.
     * @see #setActorMappings(ActorMappingsType)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration_ActorMappings()
     * @model containment="true"
     * @generated
     */
	ActorMappingsType getActorMappings();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Configuration#getActorMappings <em>Actor Mappings</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Actor Mappings</em>' containment reference.
     * @see #getActorMappings()
     * @generated
     */
	void setActorMappings(ActorMappingsType value);

	/**
     * Returns the value of the '<em><b>Anonymous User Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anonymous User Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Anonymous User Name</em>' attribute.
     * @see #setAnonymousUserName(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration_AnonymousUserName()
     * @model
     * @generated
     */
	String getAnonymousUserName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Configuration#getAnonymousUserName <em>Anonymous User Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Anonymous User Name</em>' attribute.
     * @see #getAnonymousUserName()
     * @generated
     */
	void setAnonymousUserName(String value);

	/**
     * Returns the value of the '<em><b>Anonymous Password</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anonymous Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Anonymous Password</em>' attribute.
     * @see #setAnonymousPassword(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration_AnonymousPassword()
     * @model
     * @generated
     */
	String getAnonymousPassword();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Configuration#getAnonymousPassword <em>Anonymous Password</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Anonymous Password</em>' attribute.
     * @see #getAnonymousPassword()
     * @generated
     */
	void setAnonymousPassword(String value);

	/**
     * Returns the value of the '<em><b>Definition Mappings</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.configuration.DefinitionMapping}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Definition Mappings</em>' containment reference list.
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration_DefinitionMappings()
     * @model containment="true"
     * @generated
     */
	EList<DefinitionMapping> getDefinitionMappings();

	/**
     * Returns the value of the '<em><b>Process Dependencies</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.configuration.FragmentContainer}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Dependencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Process Dependencies</em>' containment reference list.
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration_ProcessDependencies()
     * @model containment="true"
     * @generated
     */
	EList<FragmentContainer> getProcessDependencies();

	/**
     * Returns the value of the '<em><b>Application Dependencies</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.configuration.FragmentContainer}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Application Dependencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Application Dependencies</em>' containment reference list.
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration_ApplicationDependencies()
     * @model containment="true"
     * @generated
     */
	EList<FragmentContainer> getApplicationDependencies();

	/**
     * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.parameter.Parameter}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parameters</em>' containment reference list.
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration_Parameters()
     * @model containment="true"
     * @generated
     */
	EList<Parameter> getParameters();

	/**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration_Version()
     * @model
     * @generated
     */
	String getVersion();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Configuration#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
	void setVersion(String value);

	/**
     * Returns the value of the '<em><b>Username</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Username</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Username</em>' attribute.
     * @see #setUsername(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration_Username()
     * @model
     * @generated
     */
	String getUsername();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Configuration#getUsername <em>Username</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Username</em>' attribute.
     * @see #getUsername()
     * @generated
     */
	void setUsername(String value);

	/**
     * Returns the value of the '<em><b>Password</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Password</em>' attribute.
     * @see #setPassword(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getConfiguration_Password()
     * @model
     * @generated
     */
	String getPassword();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Configuration#getPassword <em>Password</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Password</em>' attribute.
     * @see #getPassword()
     * @generated
     */
	void setPassword(String value);

} // Configuration
