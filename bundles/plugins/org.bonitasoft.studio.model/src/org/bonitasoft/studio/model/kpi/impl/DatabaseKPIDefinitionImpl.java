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
package org.bonitasoft.studio.model.kpi.impl;

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition;
import org.bonitasoft.studio.model.kpi.KpiPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database KPI Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIDefinitionImpl#getDefaultDriverclassName <em>Default Driverclass Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIDefinitionImpl#getDefaultJdbcUrl <em>Default Jdbc Url</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIDefinitionImpl#getDefaultUser <em>Default User</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIDefinitionImpl#getDefaultPassword <em>Default Password</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIDefinitionImpl#getDefaultJNDIUrl <em>Default JNDI Url</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIDefinitionImpl#getDefaultDBName <em>Default DB Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIDefinitionImpl#getDefaultTableName <em>Default Table Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DatabaseKPIDefinitionImpl extends AbstractKPIDefinitionImpl implements DatabaseKPIDefinition {
	/**
	 * The cached value of the '{@link #getDefaultDriverclassName() <em>Default Driverclass Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultDriverclassName()
	 * @generated
	 * @ordered
	 */
	protected Expression defaultDriverclassName;

	/**
	 * The cached value of the '{@link #getDefaultJdbcUrl() <em>Default Jdbc Url</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultJdbcUrl()
	 * @generated
	 * @ordered
	 */
	protected Expression defaultJdbcUrl;

	/**
	 * The cached value of the '{@link #getDefaultUser() <em>Default User</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultUser()
	 * @generated
	 * @ordered
	 */
	protected Expression defaultUser;

	/**
	 * The cached value of the '{@link #getDefaultPassword() <em>Default Password</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultPassword()
	 * @generated
	 * @ordered
	 */
	protected Expression defaultPassword;

	/**
	 * The cached value of the '{@link #getDefaultJNDIUrl() <em>Default JNDI Url</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultJNDIUrl()
	 * @generated
	 * @ordered
	 */
	protected Expression defaultJNDIUrl;

	/**
	 * The cached value of the '{@link #getDefaultDBName() <em>Default DB Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultDBName()
	 * @generated
	 * @ordered
	 */
	protected Expression defaultDBName;

	/**
	 * The default value of the '{@link #getDefaultTableName() <em>Default Table Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultTableName()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_TABLE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultTableName() <em>Default Table Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultTableName()
	 * @generated
	 * @ordered
	 */
	protected String defaultTableName = DEFAULT_TABLE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DatabaseKPIDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KpiPackage.Literals.DATABASE_KPI_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getDefaultDriverclassName() {
		return defaultDriverclassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultDriverclassName(Expression newDefaultDriverclassName, NotificationChain msgs) {
		Expression oldDefaultDriverclassName = defaultDriverclassName;
		defaultDriverclassName = newDefaultDriverclassName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DRIVERCLASS_NAME, oldDefaultDriverclassName, newDefaultDriverclassName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefaultDriverclassName(Expression newDefaultDriverclassName) {
		if (newDefaultDriverclassName != defaultDriverclassName) {
			NotificationChain msgs = null;
			if (defaultDriverclassName != null)
				msgs = ((InternalEObject)defaultDriverclassName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DRIVERCLASS_NAME, null, msgs);
			if (newDefaultDriverclassName != null)
				msgs = ((InternalEObject)newDefaultDriverclassName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DRIVERCLASS_NAME, null, msgs);
			msgs = basicSetDefaultDriverclassName(newDefaultDriverclassName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DRIVERCLASS_NAME, newDefaultDriverclassName, newDefaultDriverclassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getDefaultJdbcUrl() {
		return defaultJdbcUrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultJdbcUrl(Expression newDefaultJdbcUrl, NotificationChain msgs) {
		Expression oldDefaultJdbcUrl = defaultJdbcUrl;
		defaultJdbcUrl = newDefaultJdbcUrl;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JDBC_URL, oldDefaultJdbcUrl, newDefaultJdbcUrl);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefaultJdbcUrl(Expression newDefaultJdbcUrl) {
		if (newDefaultJdbcUrl != defaultJdbcUrl) {
			NotificationChain msgs = null;
			if (defaultJdbcUrl != null)
				msgs = ((InternalEObject)defaultJdbcUrl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JDBC_URL, null, msgs);
			if (newDefaultJdbcUrl != null)
				msgs = ((InternalEObject)newDefaultJdbcUrl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JDBC_URL, null, msgs);
			msgs = basicSetDefaultJdbcUrl(newDefaultJdbcUrl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JDBC_URL, newDefaultJdbcUrl, newDefaultJdbcUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getDefaultUser() {
		return defaultUser;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultUser(Expression newDefaultUser, NotificationChain msgs) {
		Expression oldDefaultUser = defaultUser;
		defaultUser = newDefaultUser;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_USER, oldDefaultUser, newDefaultUser);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefaultUser(Expression newDefaultUser) {
		if (newDefaultUser != defaultUser) {
			NotificationChain msgs = null;
			if (defaultUser != null)
				msgs = ((InternalEObject)defaultUser).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_USER, null, msgs);
			if (newDefaultUser != null)
				msgs = ((InternalEObject)newDefaultUser).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_USER, null, msgs);
			msgs = basicSetDefaultUser(newDefaultUser, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_USER, newDefaultUser, newDefaultUser));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getDefaultPassword() {
		return defaultPassword;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultPassword(Expression newDefaultPassword, NotificationChain msgs) {
		Expression oldDefaultPassword = defaultPassword;
		defaultPassword = newDefaultPassword;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_PASSWORD, oldDefaultPassword, newDefaultPassword);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefaultPassword(Expression newDefaultPassword) {
		if (newDefaultPassword != defaultPassword) {
			NotificationChain msgs = null;
			if (defaultPassword != null)
				msgs = ((InternalEObject)defaultPassword).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_PASSWORD, null, msgs);
			if (newDefaultPassword != null)
				msgs = ((InternalEObject)newDefaultPassword).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_PASSWORD, null, msgs);
			msgs = basicSetDefaultPassword(newDefaultPassword, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_PASSWORD, newDefaultPassword, newDefaultPassword));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getDefaultJNDIUrl() {
		return defaultJNDIUrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultJNDIUrl(Expression newDefaultJNDIUrl, NotificationChain msgs) {
		Expression oldDefaultJNDIUrl = defaultJNDIUrl;
		defaultJNDIUrl = newDefaultJNDIUrl;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JNDI_URL, oldDefaultJNDIUrl, newDefaultJNDIUrl);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefaultJNDIUrl(Expression newDefaultJNDIUrl) {
		if (newDefaultJNDIUrl != defaultJNDIUrl) {
			NotificationChain msgs = null;
			if (defaultJNDIUrl != null)
				msgs = ((InternalEObject)defaultJNDIUrl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JNDI_URL, null, msgs);
			if (newDefaultJNDIUrl != null)
				msgs = ((InternalEObject)newDefaultJNDIUrl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JNDI_URL, null, msgs);
			msgs = basicSetDefaultJNDIUrl(newDefaultJNDIUrl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JNDI_URL, newDefaultJNDIUrl, newDefaultJNDIUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getDefaultDBName() {
		return defaultDBName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultDBName(Expression newDefaultDBName, NotificationChain msgs) {
		Expression oldDefaultDBName = defaultDBName;
		defaultDBName = newDefaultDBName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DB_NAME, oldDefaultDBName, newDefaultDBName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefaultDBName(Expression newDefaultDBName) {
		if (newDefaultDBName != defaultDBName) {
			NotificationChain msgs = null;
			if (defaultDBName != null)
				msgs = ((InternalEObject)defaultDBName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DB_NAME, null, msgs);
			if (newDefaultDBName != null)
				msgs = ((InternalEObject)newDefaultDBName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DB_NAME, null, msgs);
			msgs = basicSetDefaultDBName(newDefaultDBName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DB_NAME, newDefaultDBName, newDefaultDBName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDefaultTableName() {
		return defaultTableName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefaultTableName(String newDefaultTableName) {
		String oldDefaultTableName = defaultTableName;
		defaultTableName = newDefaultTableName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_TABLE_NAME, oldDefaultTableName, defaultTableName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DRIVERCLASS_NAME:
				return basicSetDefaultDriverclassName(null, msgs);
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JDBC_URL:
				return basicSetDefaultJdbcUrl(null, msgs);
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_USER:
				return basicSetDefaultUser(null, msgs);
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_PASSWORD:
				return basicSetDefaultPassword(null, msgs);
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JNDI_URL:
				return basicSetDefaultJNDIUrl(null, msgs);
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DB_NAME:
				return basicSetDefaultDBName(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DRIVERCLASS_NAME:
				return getDefaultDriverclassName();
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JDBC_URL:
				return getDefaultJdbcUrl();
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_USER:
				return getDefaultUser();
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_PASSWORD:
				return getDefaultPassword();
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JNDI_URL:
				return getDefaultJNDIUrl();
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DB_NAME:
				return getDefaultDBName();
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_TABLE_NAME:
				return getDefaultTableName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DRIVERCLASS_NAME:
				setDefaultDriverclassName((Expression)newValue);
				return;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JDBC_URL:
				setDefaultJdbcUrl((Expression)newValue);
				return;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_USER:
				setDefaultUser((Expression)newValue);
				return;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_PASSWORD:
				setDefaultPassword((Expression)newValue);
				return;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JNDI_URL:
				setDefaultJNDIUrl((Expression)newValue);
				return;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DB_NAME:
				setDefaultDBName((Expression)newValue);
				return;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_TABLE_NAME:
				setDefaultTableName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DRIVERCLASS_NAME:
				setDefaultDriverclassName((Expression)null);
				return;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JDBC_URL:
				setDefaultJdbcUrl((Expression)null);
				return;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_USER:
				setDefaultUser((Expression)null);
				return;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_PASSWORD:
				setDefaultPassword((Expression)null);
				return;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JNDI_URL:
				setDefaultJNDIUrl((Expression)null);
				return;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DB_NAME:
				setDefaultDBName((Expression)null);
				return;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_TABLE_NAME:
				setDefaultTableName(DEFAULT_TABLE_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DRIVERCLASS_NAME:
				return defaultDriverclassName != null;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JDBC_URL:
				return defaultJdbcUrl != null;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_USER:
				return defaultUser != null;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_PASSWORD:
				return defaultPassword != null;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_JNDI_URL:
				return defaultJNDIUrl != null;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_DB_NAME:
				return defaultDBName != null;
			case KpiPackage.DATABASE_KPI_DEFINITION__DEFAULT_TABLE_NAME:
				return DEFAULT_TABLE_NAME_EDEFAULT == null ? defaultTableName != null : !DEFAULT_TABLE_NAME_EDEFAULT.equals(defaultTableName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (defaultTableName: "); //$NON-NLS-1$
		result.append(defaultTableName);
		result.append(')');
		return result.toString();
	}

} //DatabaseKPIDefinitionImpl
