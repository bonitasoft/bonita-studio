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
package org.bonitasoft.studio.model.kpi;

import org.bonitasoft.studio.model.expression.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Database KPI Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getDriverclassName <em>Driverclass Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getJdbcUrl <em>Jdbc Url</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getUser <em>User</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getPassword <em>Password</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getJndiUrl <em>Jndi Url</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getTableName <em>Table Name</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIBinding()
 * @model
 * @generated
 */
public interface DatabaseKPIBinding extends AbstractKPIBinding {
	/**
     * Returns the value of the '<em><b>Driverclass Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Driverclass Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Driverclass Name</em>' containment reference.
     * @see #setDriverclassName(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIBinding_DriverclassName()
     * @model containment="true"
     * @generated
     */
	Expression getDriverclassName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getDriverclassName <em>Driverclass Name</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Driverclass Name</em>' containment reference.
     * @see #getDriverclassName()
     * @generated
     */
	void setDriverclassName(Expression value);

	/**
     * Returns the value of the '<em><b>Jdbc Url</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jdbc Url</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Jdbc Url</em>' containment reference.
     * @see #setJdbcUrl(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIBinding_JdbcUrl()
     * @model containment="true"
     * @generated
     */
	Expression getJdbcUrl();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getJdbcUrl <em>Jdbc Url</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Jdbc Url</em>' containment reference.
     * @see #getJdbcUrl()
     * @generated
     */
	void setJdbcUrl(Expression value);

	/**
     * Returns the value of the '<em><b>User</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>User</em>' containment reference.
     * @see #setUser(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIBinding_User()
     * @model containment="true"
     * @generated
     */
	Expression getUser();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getUser <em>User</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>User</em>' containment reference.
     * @see #getUser()
     * @generated
     */
	void setUser(Expression value);

	/**
     * Returns the value of the '<em><b>Password</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Password</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Password</em>' containment reference.
     * @see #setPassword(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIBinding_Password()
     * @model containment="true"
     * @generated
     */
	Expression getPassword();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getPassword <em>Password</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Password</em>' containment reference.
     * @see #getPassword()
     * @generated
     */
	void setPassword(Expression value);

	/**
     * Returns the value of the '<em><b>Jndi Url</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jndi Url</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Jndi Url</em>' containment reference.
     * @see #setJndiUrl(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIBinding_JndiUrl()
     * @model containment="true"
     * @generated
     */
	Expression getJndiUrl();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getJndiUrl <em>Jndi Url</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Jndi Url</em>' containment reference.
     * @see #getJndiUrl()
     * @generated
     */
	void setJndiUrl(Expression value);

	/**
     * Returns the value of the '<em><b>Table Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Table Name</em>' attribute.
     * @see #setTableName(String)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIBinding_TableName()
     * @model
     * @generated
     */
	String getTableName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getTableName <em>Table Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Name</em>' attribute.
     * @see #getTableName()
     * @generated
     */
	void setTableName(String value);

} // DatabaseKPIBinding
