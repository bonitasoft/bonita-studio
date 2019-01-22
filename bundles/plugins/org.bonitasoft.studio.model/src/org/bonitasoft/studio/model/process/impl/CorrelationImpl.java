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

import org.bonitasoft.studio.model.expression.TableExpression;

import org.bonitasoft.studio.model.process.Correlation;
import org.bonitasoft.studio.model.process.CorrelationTypeActive;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Correlation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.CorrelationImpl#getCorrelationType <em>Correlation Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.CorrelationImpl#getCorrelationAssociation <em>Correlation Association</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CorrelationImpl extends EObjectImpl implements Correlation {
	/**
	 * The default value of the '{@link #getCorrelationType() <em>Correlation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationType()
	 * @generated
	 * @ordered
	 */
	protected static final CorrelationTypeActive CORRELATION_TYPE_EDEFAULT = CorrelationTypeActive.INACTIVE;

	/**
	 * The cached value of the '{@link #getCorrelationType() <em>Correlation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationType()
	 * @generated
	 * @ordered
	 */
	protected CorrelationTypeActive correlationType = CORRELATION_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCorrelationAssociation() <em>Correlation Association</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationAssociation()
	 * @generated
	 * @ordered
	 */
	protected TableExpression correlationAssociation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CorrelationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.CORRELATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CorrelationTypeActive getCorrelationType() {
		return correlationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCorrelationType(CorrelationTypeActive newCorrelationType) {
		CorrelationTypeActive oldCorrelationType = correlationType;
		correlationType = newCorrelationType == null ? CORRELATION_TYPE_EDEFAULT : newCorrelationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CORRELATION__CORRELATION_TYPE, oldCorrelationType, correlationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TableExpression getCorrelationAssociation() {
		return correlationAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCorrelationAssociation(TableExpression newCorrelationAssociation, NotificationChain msgs) {
		TableExpression oldCorrelationAssociation = correlationAssociation;
		correlationAssociation = newCorrelationAssociation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.CORRELATION__CORRELATION_ASSOCIATION, oldCorrelationAssociation, newCorrelationAssociation);
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
	public void setCorrelationAssociation(TableExpression newCorrelationAssociation) {
		if (newCorrelationAssociation != correlationAssociation) {
			NotificationChain msgs = null;
			if (correlationAssociation != null)
				msgs = ((InternalEObject)correlationAssociation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CORRELATION__CORRELATION_ASSOCIATION, null, msgs);
			if (newCorrelationAssociation != null)
				msgs = ((InternalEObject)newCorrelationAssociation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CORRELATION__CORRELATION_ASSOCIATION, null, msgs);
			msgs = basicSetCorrelationAssociation(newCorrelationAssociation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CORRELATION__CORRELATION_ASSOCIATION, newCorrelationAssociation, newCorrelationAssociation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.CORRELATION__CORRELATION_ASSOCIATION:
				return basicSetCorrelationAssociation(null, msgs);
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
			case ProcessPackage.CORRELATION__CORRELATION_TYPE:
				return getCorrelationType();
			case ProcessPackage.CORRELATION__CORRELATION_ASSOCIATION:
				return getCorrelationAssociation();
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
			case ProcessPackage.CORRELATION__CORRELATION_TYPE:
				setCorrelationType((CorrelationTypeActive)newValue);
				return;
			case ProcessPackage.CORRELATION__CORRELATION_ASSOCIATION:
				setCorrelationAssociation((TableExpression)newValue);
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
			case ProcessPackage.CORRELATION__CORRELATION_TYPE:
				setCorrelationType(CORRELATION_TYPE_EDEFAULT);
				return;
			case ProcessPackage.CORRELATION__CORRELATION_ASSOCIATION:
				setCorrelationAssociation((TableExpression)null);
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
			case ProcessPackage.CORRELATION__CORRELATION_TYPE:
				return correlationType != CORRELATION_TYPE_EDEFAULT;
			case ProcessPackage.CORRELATION__CORRELATION_ASSOCIATION:
				return correlationAssociation != null;
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
		result.append(" (correlationType: "); //$NON-NLS-1$
		result.append(correlationType);
		result.append(')');
		return result.toString();
	}

} //CorrelationImpl
