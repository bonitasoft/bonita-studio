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
 * A representation of the model object '<em><b>Database KPI Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultDriverclassName <em>Default Driverclass Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultJdbcUrl <em>Default Jdbc Url</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultUser <em>Default User</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultPassword <em>Default Password</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultJNDIUrl <em>Default JNDI Url</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultDBName <em>Default DB Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultTableName <em>Default Table Name</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIDefinition()
 * @model
 * @generated
 */
public interface DatabaseKPIDefinition extends AbstractKPIDefinition {
	/**
     * Returns the value of the '<em><b>Default Driverclass Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Driverclass Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default Driverclass Name</em>' containment reference.
     * @see #setDefaultDriverclassName(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIDefinition_DefaultDriverclassName()
     * @model containment="true"
     * @generated
     */
	Expression getDefaultDriverclassName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultDriverclassName <em>Default Driverclass Name</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Driverclass Name</em>' containment reference.
     * @see #getDefaultDriverclassName()
     * @generated
     */
	void setDefaultDriverclassName(Expression value);

	/**
     * Returns the value of the '<em><b>Default Jdbc Url</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Jdbc Url</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default Jdbc Url</em>' containment reference.
     * @see #setDefaultJdbcUrl(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIDefinition_DefaultJdbcUrl()
     * @model containment="true"
     * @generated
     */
	Expression getDefaultJdbcUrl();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultJdbcUrl <em>Default Jdbc Url</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Jdbc Url</em>' containment reference.
     * @see #getDefaultJdbcUrl()
     * @generated
     */
	void setDefaultJdbcUrl(Expression value);

	/**
     * Returns the value of the '<em><b>Default User</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default User</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default User</em>' containment reference.
     * @see #setDefaultUser(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIDefinition_DefaultUser()
     * @model containment="true"
     * @generated
     */
	Expression getDefaultUser();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultUser <em>Default User</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default User</em>' containment reference.
     * @see #getDefaultUser()
     * @generated
     */
	void setDefaultUser(Expression value);

	/**
     * Returns the value of the '<em><b>Default Password</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Password</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default Password</em>' containment reference.
     * @see #setDefaultPassword(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIDefinition_DefaultPassword()
     * @model containment="true"
     * @generated
     */
	Expression getDefaultPassword();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultPassword <em>Default Password</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Password</em>' containment reference.
     * @see #getDefaultPassword()
     * @generated
     */
	void setDefaultPassword(Expression value);

	/**
     * Returns the value of the '<em><b>Default JNDI Url</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default JNDI Url</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default JNDI Url</em>' containment reference.
     * @see #setDefaultJNDIUrl(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIDefinition_DefaultJNDIUrl()
     * @model containment="true"
     * @generated
     */
	Expression getDefaultJNDIUrl();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultJNDIUrl <em>Default JNDI Url</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default JNDI Url</em>' containment reference.
     * @see #getDefaultJNDIUrl()
     * @generated
     */
	void setDefaultJNDIUrl(Expression value);

	/**
     * Returns the value of the '<em><b>Default DB Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default DB Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default DB Name</em>' containment reference.
     * @see #setDefaultDBName(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIDefinition_DefaultDBName()
     * @model containment="true"
     * @generated
     */
	Expression getDefaultDBName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultDBName <em>Default DB Name</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default DB Name</em>' containment reference.
     * @see #getDefaultDBName()
     * @generated
     */
	void setDefaultDBName(Expression value);

	/**
     * Returns the value of the '<em><b>Default Table Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Table Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default Table Name</em>' attribute.
     * @see #setDefaultTableName(String)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getDatabaseKPIDefinition_DefaultTableName()
     * @model
     * @generated
     */
	String getDefaultTableName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultTableName <em>Default Table Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Table Name</em>' attribute.
     * @see #getDefaultTableName()
     * @generated
     */
	void setDefaultTableName(String value);

} // DatabaseKPIDefinition
