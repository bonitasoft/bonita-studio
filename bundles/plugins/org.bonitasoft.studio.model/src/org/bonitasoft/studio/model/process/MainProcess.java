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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Main Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.MainProcess#getBonitaVersion <em>Bonita Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MainProcess#getBonitaModelVersion <em>Bonita Model Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MainProcess#getIncludedEntries <em>Included Entries</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MainProcess#getMessageConnections <em>Message Connections</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MainProcess#getGeneratedLibs <em>Generated Libs</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MainProcess#isEnableValidation <em>Enable Validation</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MainProcess#getConfigId <em>Config Id</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getMainProcess()
 * @model
 * @generated
 */
public interface MainProcess extends AbstractProcess {
	/**
     * Returns the value of the '<em><b>Bonita Version</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bonita Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Bonita Version</em>' attribute.
     * @see #setBonitaVersion(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMainProcess_BonitaVersion()
     * @model default="" required="true"
     * @generated
     */
	String getBonitaVersion();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MainProcess#getBonitaVersion <em>Bonita Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bonita Version</em>' attribute.
     * @see #getBonitaVersion()
     * @generated
     */
	void setBonitaVersion(String value);

	/**
     * Returns the value of the '<em><b>Bonita Model Version</b></em>' attribute.
     * The default value is <code>"5.0"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bonita Model Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Bonita Model Version</em>' attribute.
     * @see #setBonitaModelVersion(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMainProcess_BonitaModelVersion()
     * @model default="5.0"
     * @generated
     */
	String getBonitaModelVersion();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MainProcess#getBonitaModelVersion <em>Bonita Model Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bonita Model Version</em>' attribute.
     * @see #getBonitaModelVersion()
     * @generated
     */
	void setBonitaModelVersion(String value);

	/**
     * Returns the value of the '<em><b>Included Entries</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included Entries</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Included Entries</em>' attribute list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMainProcess_IncludedEntries()
     * @model
     * @generated
     */
	EList<String> getIncludedEntries();

	/**
     * Returns the value of the '<em><b>Message Connections</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.MessageFlow}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Connections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Message Connections</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMainProcess_MessageConnections()
     * @model containment="true"
     * @generated
     */
	EList<MessageFlow> getMessageConnections();

	/**
     * Returns the value of the '<em><b>Generated Libs</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generated Libs</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Generated Libs</em>' attribute list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMainProcess_GeneratedLibs()
     * @model
     * @generated
     */
	EList<String> getGeneratedLibs();

	/**
     * Returns the value of the '<em><b>Enable Validation</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enable Validation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Enable Validation</em>' attribute.
     * @see #setEnableValidation(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMainProcess_EnableValidation()
     * @model default="true"
     * @generated
     */
	boolean isEnableValidation();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MainProcess#isEnableValidation <em>Enable Validation</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Enable Validation</em>' attribute.
     * @see #isEnableValidation()
     * @generated
     */
	void setEnableValidation(boolean value);

	/**
     * Returns the value of the '<em><b>Config Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Config Id</em>' attribute.
     * @see #setConfigId(Object)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMainProcess_ConfigId()
     * @model
     * @generated
     */
	Object getConfigId();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MainProcess#getConfigId <em>Config Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Config Id</em>' attribute.
     * @see #getConfigId()
     * @generated
     */
	void setConfigId(Object value);

} // MainProcess
