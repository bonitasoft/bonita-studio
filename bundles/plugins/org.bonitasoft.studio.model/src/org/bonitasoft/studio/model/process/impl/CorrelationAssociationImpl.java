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
package org.bonitasoft.studio.model.process.impl;

import org.bonitasoft.studio.model.expression.AbstractExpression;

import org.bonitasoft.studio.model.process.CorrelationAssociation;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Correlation Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.CorrelationAssociationImpl#getCorrelationExpression <em>Correlation Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.CorrelationAssociationImpl#getCorrelationKey <em>Correlation Key</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CorrelationAssociationImpl extends EObjectImpl implements CorrelationAssociation {
	/**
	 * The cached value of the '{@link #getCorrelationExpression() <em>Correlation Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationExpression()
	 * @generated
	 * @ordered
	 */
	protected AbstractExpression correlationExpression;

	/**
	 * The cached value of the '{@link #getCorrelationKey() <em>Correlation Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationKey()
	 * @generated
	 * @ordered
	 */
	protected AbstractExpression correlationKey;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CorrelationAssociationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.CORRELATION_ASSOCIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AbstractExpression getCorrelationExpression() {
		return correlationExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCorrelationExpression(AbstractExpression newCorrelationExpression, NotificationChain msgs) {
		AbstractExpression oldCorrelationExpression = correlationExpression;
		correlationExpression = newCorrelationExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_EXPRESSION, oldCorrelationExpression, newCorrelationExpression);
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
	public void setCorrelationExpression(AbstractExpression newCorrelationExpression) {
		if (newCorrelationExpression != correlationExpression) {
			NotificationChain msgs = null;
			if (correlationExpression != null)
				msgs = ((InternalEObject)correlationExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_EXPRESSION, null, msgs);
			if (newCorrelationExpression != null)
				msgs = ((InternalEObject)newCorrelationExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_EXPRESSION, null, msgs);
			msgs = basicSetCorrelationExpression(newCorrelationExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_EXPRESSION, newCorrelationExpression, newCorrelationExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AbstractExpression getCorrelationKey() {
		return correlationKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCorrelationKey(AbstractExpression newCorrelationKey, NotificationChain msgs) {
		AbstractExpression oldCorrelationKey = correlationKey;
		correlationKey = newCorrelationKey;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_KEY, oldCorrelationKey, newCorrelationKey);
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
	public void setCorrelationKey(AbstractExpression newCorrelationKey) {
		if (newCorrelationKey != correlationKey) {
			NotificationChain msgs = null;
			if (correlationKey != null)
				msgs = ((InternalEObject)correlationKey).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_KEY, null, msgs);
			if (newCorrelationKey != null)
				msgs = ((InternalEObject)newCorrelationKey).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_KEY, null, msgs);
			msgs = basicSetCorrelationKey(newCorrelationKey, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_KEY, newCorrelationKey, newCorrelationKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_EXPRESSION:
				return basicSetCorrelationExpression(null, msgs);
			case ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_KEY:
				return basicSetCorrelationKey(null, msgs);
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
			case ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_EXPRESSION:
				return getCorrelationExpression();
			case ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_KEY:
				return getCorrelationKey();
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
			case ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_EXPRESSION:
				setCorrelationExpression((AbstractExpression)newValue);
				return;
			case ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_KEY:
				setCorrelationKey((AbstractExpression)newValue);
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
			case ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_EXPRESSION:
				setCorrelationExpression((AbstractExpression)null);
				return;
			case ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_KEY:
				setCorrelationKey((AbstractExpression)null);
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
			case ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_EXPRESSION:
				return correlationExpression != null;
			case ProcessPackage.CORRELATION_ASSOCIATION__CORRELATION_KEY:
				return correlationKey != null;
		}
		return super.eIsSet(featureID);
	}

} //CorrelationAssociationImpl
