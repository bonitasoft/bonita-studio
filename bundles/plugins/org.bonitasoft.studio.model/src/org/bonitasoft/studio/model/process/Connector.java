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

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;

import org.bonitasoft.studio.model.expression.Operation;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.Connector#getDefinitionId <em>Definition Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Connector#getEvent <em>Event</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Connector#isIgnoreErrors <em>Ignore Errors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Connector#isThrowErrorEvent <em>Throw Error Event</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Connector#getNamedError <em>Named Error</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Connector#getDefinitionVersion <em>Definition Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Connector#getConfiguration <em>Configuration</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Connector#getOutputs <em>Outputs</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getConnector()
 * @model
 * @generated
 */
public interface Connector extends Element {
	/**
     * Returns the value of the '<em><b>Definition Id</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Definition Id</em>' attribute.
     * @see #setDefinitionId(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getConnector_DefinitionId()
     * @model default="" required="true"
     * @generated
     */
	String getDefinitionId();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Connector#getDefinitionId <em>Definition Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definition Id</em>' attribute.
     * @see #getDefinitionId()
     * @generated
     */
	void setDefinitionId(String value);

	/**
     * Returns the value of the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Event</em>' attribute.
     * @see #setEvent(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getConnector_Event()
     * @model
     * @generated
     */
	String getEvent();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Connector#getEvent <em>Event</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Event</em>' attribute.
     * @see #getEvent()
     * @generated
     */
	void setEvent(String value);

	/**
     * Returns the value of the '<em><b>Ignore Errors</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * ignoreErrors -> ignore error
     * !ignoreError && throwErrorEvent -> throwErrorEvent
     * !ignoreError && !throwErrorEvent -> raise exception
     * <!-- end-model-doc -->
     * @return the value of the '<em>Ignore Errors</em>' attribute.
     * @see #setIgnoreErrors(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getConnector_IgnoreErrors()
     * @model default="false"
     * @generated
     */
	boolean isIgnoreErrors();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Connector#isIgnoreErrors <em>Ignore Errors</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ignore Errors</em>' attribute.
     * @see #isIgnoreErrors()
     * @generated
     */
	void setIgnoreErrors(boolean value);

	/**
     * Returns the value of the '<em><b>Throw Error Event</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * It will be used only if !ignoreErrors
     * <!-- end-model-doc -->
     * @return the value of the '<em>Throw Error Event</em>' attribute.
     * @see #setThrowErrorEvent(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getConnector_ThrowErrorEvent()
     * @model default="false" required="true"
     * @generated
     */
	boolean isThrowErrorEvent();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Connector#isThrowErrorEvent <em>Throw Error Event</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Throw Error Event</em>' attribute.
     * @see #isThrowErrorEvent()
     * @generated
     */
	void setThrowErrorEvent(boolean value);

	/**
     * Returns the value of the '<em><b>Named Error</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * It will be used only if !ignoreErrors && throwErrorEvent
     * <!-- end-model-doc -->
     * @return the value of the '<em>Named Error</em>' attribute.
     * @see #setNamedError(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getConnector_NamedError()
     * @model default=""
     * @generated
     */
	String getNamedError();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Connector#getNamedError <em>Named Error</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Named Error</em>' attribute.
     * @see #getNamedError()
     * @generated
     */
	void setNamedError(String value);

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
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getConnector_DefinitionVersion()
     * @model required="true"
     * @generated
     */
	String getDefinitionVersion();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Connector#getDefinitionVersion <em>Definition Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definition Version</em>' attribute.
     * @see #getDefinitionVersion()
     * @generated
     */
	void setDefinitionVersion(String value);

	/**
     * Returns the value of the '<em><b>Configuration</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configuration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Configuration</em>' containment reference.
     * @see #setConfiguration(ConnectorConfiguration)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getConnector_Configuration()
     * @model containment="true"
     * @generated
     */
	ConnectorConfiguration getConfiguration();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Connector#getConfiguration <em>Configuration</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Configuration</em>' containment reference.
     * @see #getConfiguration()
     * @generated
     */
	void setConfiguration(ConnectorConfiguration value);

	/**
     * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.expression.Operation}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Outputs</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getConnector_Outputs()
     * @model containment="true"
     * @generated
     */
	EList<Operation> getOutputs();

} // Connector
