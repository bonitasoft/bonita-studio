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
package org.bonitasoft.studio.model.connectorconfiguration.impl;

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;

import org.bonitasoft.studio.model.expression.AbstractExpression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connector Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorParameterImpl#getKey <em>Key</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorParameterImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectorParameterImpl extends EObjectImpl implements ConnectorParameter {
	/**
     * The default value of the '{@link #getKey() <em>Key</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getKey()
     * @generated
     * @ordered
     */
	protected static final String KEY_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getKey() <em>Key</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getKey()
     * @generated
     * @ordered
     */
	protected String key = KEY_EDEFAULT;

	/**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
	protected AbstractExpression expression;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ConnectorParameterImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ConnectorConfigurationPackage.Literals.CONNECTOR_PARAMETER;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getKey() {
        return key;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setKey(String newKey) {
        String oldKey = key;
        key = newKey;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectorConfigurationPackage.CONNECTOR_PARAMETER__KEY, oldKey, key));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public AbstractExpression getExpression() {
        return expression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetExpression(AbstractExpression newExpression, NotificationChain msgs) {
        AbstractExpression oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConnectorConfigurationPackage.CONNECTOR_PARAMETER__EXPRESSION, oldExpression, newExpression);
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
	public void setExpression(AbstractExpression newExpression) {
        if (newExpression != expression) {
            NotificationChain msgs = null;
            if (expression != null)
                msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConnectorConfigurationPackage.CONNECTOR_PARAMETER__EXPRESSION, null, msgs);
            if (newExpression != null)
                msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConnectorConfigurationPackage.CONNECTOR_PARAMETER__EXPRESSION, null, msgs);
            msgs = basicSetExpression(newExpression, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectorConfigurationPackage.CONNECTOR_PARAMETER__EXPRESSION, newExpression, newExpression));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConnectorConfigurationPackage.CONNECTOR_PARAMETER__EXPRESSION:
                return basicSetExpression(null, msgs);
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
            case ConnectorConfigurationPackage.CONNECTOR_PARAMETER__KEY:
                return getKey();
            case ConnectorConfigurationPackage.CONNECTOR_PARAMETER__EXPRESSION:
                return getExpression();
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
            case ConnectorConfigurationPackage.CONNECTOR_PARAMETER__KEY:
                setKey((String)newValue);
                return;
            case ConnectorConfigurationPackage.CONNECTOR_PARAMETER__EXPRESSION:
                setExpression((AbstractExpression)newValue);
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
            case ConnectorConfigurationPackage.CONNECTOR_PARAMETER__KEY:
                setKey(KEY_EDEFAULT);
                return;
            case ConnectorConfigurationPackage.CONNECTOR_PARAMETER__EXPRESSION:
                setExpression((AbstractExpression)null);
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
            case ConnectorConfigurationPackage.CONNECTOR_PARAMETER__KEY:
                return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
            case ConnectorConfigurationPackage.CONNECTOR_PARAMETER__EXPRESSION:
                return expression != null;
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
        result.append(" (key: "); //$NON-NLS-1$
        result.append(key);
        result.append(')');
        return result.toString();
    }

} //ConnectorParameterImpl
