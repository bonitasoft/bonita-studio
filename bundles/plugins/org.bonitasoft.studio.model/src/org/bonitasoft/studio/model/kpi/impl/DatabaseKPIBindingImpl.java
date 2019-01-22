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

import org.bonitasoft.studio.model.kpi.DatabaseKPIBinding;
import org.bonitasoft.studio.model.kpi.KpiPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database KPI Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIBindingImpl#getDriverclassName <em>Driverclass Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIBindingImpl#getJdbcUrl <em>Jdbc Url</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIBindingImpl#getUser <em>User</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIBindingImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIBindingImpl#getJndiUrl <em>Jndi Url</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIBindingImpl#getTableName <em>Table Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DatabaseKPIBindingImpl extends AbstractKPIBindingImpl implements DatabaseKPIBinding {
	/**
	 * The cached value of the '{@link #getDriverclassName() <em>Driverclass Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDriverclassName()
	 * @generated
	 * @ordered
	 */
	protected Expression driverclassName;

	/**
	 * The cached value of the '{@link #getJdbcUrl() <em>Jdbc Url</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJdbcUrl()
	 * @generated
	 * @ordered
	 */
	protected Expression jdbcUrl;

	/**
	 * The cached value of the '{@link #getUser() <em>User</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected Expression user;

	/**
	 * The cached value of the '{@link #getPassword() <em>Password</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected Expression password;

	/**
	 * The cached value of the '{@link #getJndiUrl() <em>Jndi Url</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJndiUrl()
	 * @generated
	 * @ordered
	 */
	protected Expression jndiUrl;

	/**
	 * The default value of the '{@link #getTableName() <em>Table Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTableName()
	 * @generated
	 * @ordered
	 */
	protected static final String TABLE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTableName() <em>Table Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTableName()
	 * @generated
	 * @ordered
	 */
	protected String tableName = TABLE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DatabaseKPIBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KpiPackage.Literals.DATABASE_KPI_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getDriverclassName() {
		return driverclassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDriverclassName(Expression newDriverclassName, NotificationChain msgs) {
		Expression oldDriverclassName = driverclassName;
		driverclassName = newDriverclassName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_BINDING__DRIVERCLASS_NAME, oldDriverclassName, newDriverclassName);
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
	public void setDriverclassName(Expression newDriverclassName) {
		if (newDriverclassName != driverclassName) {
			NotificationChain msgs = null;
			if (driverclassName != null)
				msgs = ((InternalEObject)driverclassName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_BINDING__DRIVERCLASS_NAME, null, msgs);
			if (newDriverclassName != null)
				msgs = ((InternalEObject)newDriverclassName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_BINDING__DRIVERCLASS_NAME, null, msgs);
			msgs = basicSetDriverclassName(newDriverclassName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_BINDING__DRIVERCLASS_NAME, newDriverclassName, newDriverclassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getJdbcUrl() {
		return jdbcUrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJdbcUrl(Expression newJdbcUrl, NotificationChain msgs) {
		Expression oldJdbcUrl = jdbcUrl;
		jdbcUrl = newJdbcUrl;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_BINDING__JDBC_URL, oldJdbcUrl, newJdbcUrl);
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
	public void setJdbcUrl(Expression newJdbcUrl) {
		if (newJdbcUrl != jdbcUrl) {
			NotificationChain msgs = null;
			if (jdbcUrl != null)
				msgs = ((InternalEObject)jdbcUrl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_BINDING__JDBC_URL, null, msgs);
			if (newJdbcUrl != null)
				msgs = ((InternalEObject)newJdbcUrl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_BINDING__JDBC_URL, null, msgs);
			msgs = basicSetJdbcUrl(newJdbcUrl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_BINDING__JDBC_URL, newJdbcUrl, newJdbcUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getUser() {
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUser(Expression newUser, NotificationChain msgs) {
		Expression oldUser = user;
		user = newUser;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_BINDING__USER, oldUser, newUser);
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
	public void setUser(Expression newUser) {
		if (newUser != user) {
			NotificationChain msgs = null;
			if (user != null)
				msgs = ((InternalEObject)user).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_BINDING__USER, null, msgs);
			if (newUser != null)
				msgs = ((InternalEObject)newUser).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_BINDING__USER, null, msgs);
			msgs = basicSetUser(newUser, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_BINDING__USER, newUser, newUser));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPassword(Expression newPassword, NotificationChain msgs) {
		Expression oldPassword = password;
		password = newPassword;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_BINDING__PASSWORD, oldPassword, newPassword);
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
	public void setPassword(Expression newPassword) {
		if (newPassword != password) {
			NotificationChain msgs = null;
			if (password != null)
				msgs = ((InternalEObject)password).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_BINDING__PASSWORD, null, msgs);
			if (newPassword != null)
				msgs = ((InternalEObject)newPassword).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_BINDING__PASSWORD, null, msgs);
			msgs = basicSetPassword(newPassword, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_BINDING__PASSWORD, newPassword, newPassword));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getJndiUrl() {
		return jndiUrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJndiUrl(Expression newJndiUrl, NotificationChain msgs) {
		Expression oldJndiUrl = jndiUrl;
		jndiUrl = newJndiUrl;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_BINDING__JNDI_URL, oldJndiUrl, newJndiUrl);
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
	public void setJndiUrl(Expression newJndiUrl) {
		if (newJndiUrl != jndiUrl) {
			NotificationChain msgs = null;
			if (jndiUrl != null)
				msgs = ((InternalEObject)jndiUrl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_BINDING__JNDI_URL, null, msgs);
			if (newJndiUrl != null)
				msgs = ((InternalEObject)newJndiUrl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - KpiPackage.DATABASE_KPI_BINDING__JNDI_URL, null, msgs);
			msgs = basicSetJndiUrl(newJndiUrl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_BINDING__JNDI_URL, newJndiUrl, newJndiUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTableName() {
		return tableName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTableName(String newTableName) {
		String oldTableName = tableName;
		tableName = newTableName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpiPackage.DATABASE_KPI_BINDING__TABLE_NAME, oldTableName, tableName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KpiPackage.DATABASE_KPI_BINDING__DRIVERCLASS_NAME:
				return basicSetDriverclassName(null, msgs);
			case KpiPackage.DATABASE_KPI_BINDING__JDBC_URL:
				return basicSetJdbcUrl(null, msgs);
			case KpiPackage.DATABASE_KPI_BINDING__USER:
				return basicSetUser(null, msgs);
			case KpiPackage.DATABASE_KPI_BINDING__PASSWORD:
				return basicSetPassword(null, msgs);
			case KpiPackage.DATABASE_KPI_BINDING__JNDI_URL:
				return basicSetJndiUrl(null, msgs);
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
			case KpiPackage.DATABASE_KPI_BINDING__DRIVERCLASS_NAME:
				return getDriverclassName();
			case KpiPackage.DATABASE_KPI_BINDING__JDBC_URL:
				return getJdbcUrl();
			case KpiPackage.DATABASE_KPI_BINDING__USER:
				return getUser();
			case KpiPackage.DATABASE_KPI_BINDING__PASSWORD:
				return getPassword();
			case KpiPackage.DATABASE_KPI_BINDING__JNDI_URL:
				return getJndiUrl();
			case KpiPackage.DATABASE_KPI_BINDING__TABLE_NAME:
				return getTableName();
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
			case KpiPackage.DATABASE_KPI_BINDING__DRIVERCLASS_NAME:
				setDriverclassName((Expression)newValue);
				return;
			case KpiPackage.DATABASE_KPI_BINDING__JDBC_URL:
				setJdbcUrl((Expression)newValue);
				return;
			case KpiPackage.DATABASE_KPI_BINDING__USER:
				setUser((Expression)newValue);
				return;
			case KpiPackage.DATABASE_KPI_BINDING__PASSWORD:
				setPassword((Expression)newValue);
				return;
			case KpiPackage.DATABASE_KPI_BINDING__JNDI_URL:
				setJndiUrl((Expression)newValue);
				return;
			case KpiPackage.DATABASE_KPI_BINDING__TABLE_NAME:
				setTableName((String)newValue);
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
			case KpiPackage.DATABASE_KPI_BINDING__DRIVERCLASS_NAME:
				setDriverclassName((Expression)null);
				return;
			case KpiPackage.DATABASE_KPI_BINDING__JDBC_URL:
				setJdbcUrl((Expression)null);
				return;
			case KpiPackage.DATABASE_KPI_BINDING__USER:
				setUser((Expression)null);
				return;
			case KpiPackage.DATABASE_KPI_BINDING__PASSWORD:
				setPassword((Expression)null);
				return;
			case KpiPackage.DATABASE_KPI_BINDING__JNDI_URL:
				setJndiUrl((Expression)null);
				return;
			case KpiPackage.DATABASE_KPI_BINDING__TABLE_NAME:
				setTableName(TABLE_NAME_EDEFAULT);
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
			case KpiPackage.DATABASE_KPI_BINDING__DRIVERCLASS_NAME:
				return driverclassName != null;
			case KpiPackage.DATABASE_KPI_BINDING__JDBC_URL:
				return jdbcUrl != null;
			case KpiPackage.DATABASE_KPI_BINDING__USER:
				return user != null;
			case KpiPackage.DATABASE_KPI_BINDING__PASSWORD:
				return password != null;
			case KpiPackage.DATABASE_KPI_BINDING__JNDI_URL:
				return jndiUrl != null;
			case KpiPackage.DATABASE_KPI_BINDING__TABLE_NAME:
				return TABLE_NAME_EDEFAULT == null ? tableName != null : !TABLE_NAME_EDEFAULT.equals(tableName);
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
		result.append(" (tableName: "); //$NON-NLS-1$
		result.append(tableName);
		result.append(')');
		return result.toString();
	}

} //DatabaseKPIBindingImpl
