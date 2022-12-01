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

import java.util.Date;

import org.bonitasoft.studio.model.configuration.Configuration;

import org.bonitasoft.studio.model.parameter.Parameter;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractProcess#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractProcess#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractProcess#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractProcess#getModificationDate <em>Modification Date</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractProcess#getDatatypes <em>Datatypes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractProcess#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractProcess#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractProcess#getActors <em>Actors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractProcess#getConfigurations <em>Configurations</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractProcess#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractProcess()
 * @model abstract="true"
 * @generated
 */
public interface AbstractProcess extends Container, PageFlow, RecapFlow {
	/**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * The default value is <code>"1.0"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractProcess_Version()
     * @model default="1.0"
     * @generated
     */
	String getVersion();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.AbstractProcess#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
	void setVersion(String value);

	/**
     * Returns the value of the '<em><b>Author</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Author</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Author</em>' attribute.
     * @see #setAuthor(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractProcess_Author()
     * @model
     * @generated
     */
	String getAuthor();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.AbstractProcess#getAuthor <em>Author</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Author</em>' attribute.
     * @see #getAuthor()
     * @generated
     */
	void setAuthor(String value);

	/**
     * Returns the value of the '<em><b>Creation Date</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creation Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Creation Date</em>' attribute.
     * @see #setCreationDate(Date)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractProcess_CreationDate()
     * @model
     * @generated
     */
	Date getCreationDate();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.AbstractProcess#getCreationDate <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Creation Date</em>' attribute.
     * @see #getCreationDate()
     * @generated
     */
	void setCreationDate(Date value);

	/**
     * Returns the value of the '<em><b>Modification Date</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modification Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Modification Date</em>' attribute.
     * @see #setModificationDate(Date)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractProcess_ModificationDate()
     * @model
     * @generated
     */
	Date getModificationDate();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.AbstractProcess#getModificationDate <em>Modification Date</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Modification Date</em>' attribute.
     * @see #getModificationDate()
     * @generated
     */
	void setModificationDate(Date value);

	/**
     * Returns the value of the '<em><b>Datatypes</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.DataType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Datatypes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Datatypes</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractProcess_Datatypes()
     * @model containment="true"
     * @generated
     */
	EList<DataType> getDatatypes();

	/**
     * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.Connection}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Connections</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractProcess_Connections()
     * @model containment="true"
     * @generated
     */
	EList<Connection> getConnections();

	/**
     * Returns the value of the '<em><b>Categories</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categories</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Categories</em>' attribute list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractProcess_Categories()
     * @model
     * @generated
     */
	EList<String> getCategories();

	/**
     * Returns the value of the '<em><b>Actors</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.Actor}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Actors</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractProcess_Actors()
     * @model containment="true"
     * @generated
     */
	EList<Actor> getActors();

	/**
     * Returns the value of the '<em><b>Configurations</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.configuration.Configuration}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configurations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Configurations</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractProcess_Configurations()
     * @model containment="true"
     * @generated
     */
	EList<Configuration> getConfigurations();

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
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractProcess_Parameters()
     * @model containment="true"
     * @generated
     */
	EList<Parameter> getParameters();

} // AbstractProcess
