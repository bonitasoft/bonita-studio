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
package org.bonitasoft.studio.model.connectorconfiguration;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connector Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getDefinitionId <em>Definition Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getModelVersion <em>Model Version</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage#getConnectorConfiguration()
 * @model
 * @generated
 */
public interface ConnectorConfiguration extends EObject {
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
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage#getConnectorConfiguration_DefinitionId()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * @generated
     */
	String getDefinitionId();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getDefinitionId <em>Definition Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definition Id</em>' attribute.
     * @see #getDefinitionId()
     * @generated
     */
	void setDefinitionId(String value);

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
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage#getConnectorConfiguration_Version()
     * @model required="true"
     * @generated
     */
	String getVersion();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
	void setVersion(String value);

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
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage#getConnectorConfiguration_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parameters</em>' containment reference list.
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage#getConnectorConfiguration_Parameters()
     * @model containment="true"
     * @generated
     */
	EList<ConnectorParameter> getParameters();

	/**
     * Returns the value of the '<em><b>Model Version</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Model Version</em>' attribute.
     * @see #setModelVersion(String)
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage#getConnectorConfiguration_ModelVersion()
     * @model default=""
     * @generated
     */
	String getModelVersion();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getModelVersion <em>Model Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Model Version</em>' attribute.
     * @see #getModelVersion()
     * @generated
     */
	void setModelVersion(String value);

} // ConnectorConfiguration
