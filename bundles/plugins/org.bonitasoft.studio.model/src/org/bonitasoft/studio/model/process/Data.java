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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.Data#isGenerated <em>Generated</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Data#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Data#isTransient <em>Transient</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Data#getDatasourceId <em>Datasource Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Data#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Data#getDefaultValue <em>Default Value</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getData()
 * @model
 * @generated
 */
public interface Data extends Element {
	/**
     * Returns the value of the '<em><b>Generated</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Generated</em>' attribute.
     * @see #setGenerated(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getData_Generated()
     * @model default="true"
     * @generated
     */
	boolean isGenerated();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Data#isGenerated <em>Generated</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Generated</em>' attribute.
     * @see #isGenerated()
     * @generated
     */
	void setGenerated(boolean value);

	/**
     * Returns the value of the '<em><b>Multiple</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiple</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Multiple</em>' attribute.
     * @see #setMultiple(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getData_Multiple()
     * @model
     * @generated
     */
	boolean isMultiple();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Data#isMultiple <em>Multiple</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Multiple</em>' attribute.
     * @see #isMultiple()
     * @generated
     */
	void setMultiple(boolean value);

	/**
     * Returns the value of the '<em><b>Transient</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transient</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Transient</em>' attribute.
     * @see #setTransient(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getData_Transient()
     * @model
     * @generated
     */
	boolean isTransient();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Data#isTransient <em>Transient</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Transient</em>' attribute.
     * @see #isTransient()
     * @generated
     */
	void setTransient(boolean value);

	/**
     * Returns the value of the '<em><b>Datasource Id</b></em>' attribute.
     * The default value is <code>"BOS"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Datasource Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Datasource Id</em>' attribute.
     * @see #setDatasourceId(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getData_DatasourceId()
     * @model default="BOS" required="true"
     * @generated
     */
	String getDatasourceId();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Data#getDatasourceId <em>Datasource Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Datasource Id</em>' attribute.
     * @see #getDatasourceId()
     * @generated
     */
	void setDatasourceId(String value);

	/**
     * Returns the value of the '<em><b>Data Type</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Data Type</em>' reference.
     * @see #setDataType(DataType)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getData_DataType()
     * @model required="true"
     * @generated
     */
	DataType getDataType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Data#getDataType <em>Data Type</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Type</em>' reference.
     * @see #getDataType()
     * @generated
     */
	void setDataType(DataType value);

	/**
     * Returns the value of the '<em><b>Default Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default Value</em>' containment reference.
     * @see #setDefaultValue(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getData_DefaultValue()
     * @model containment="true"
     * @generated
     */
	Expression getDefaultValue();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Data#getDefaultValue <em>Default Value</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Value</em>' containment reference.
     * @see #getDefaultValue()
     * @generated
     */
	void setDefaultValue(Expression value);

} // Data
