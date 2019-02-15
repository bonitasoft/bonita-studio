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

import org.bonitasoft.studio.model.process.BoundarySignalEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SignalEvent;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boundary Signal Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.BoundarySignalEventImpl#getSignalCode <em>Signal Code</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BoundarySignalEventImpl extends BoundaryEventImpl implements BoundarySignalEvent {
	/**
     * The default value of the '{@link #getSignalCode() <em>Signal Code</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSignalCode()
     * @generated
     * @ordered
     */
	protected static final String SIGNAL_CODE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getSignalCode() <em>Signal Code</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSignalCode()
     * @generated
     * @ordered
     */
	protected String signalCode = SIGNAL_CODE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected BoundarySignalEventImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ProcessPackage.Literals.BOUNDARY_SIGNAL_EVENT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getSignalCode() {
        return signalCode;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setSignalCode(String newSignalCode) {
        String oldSignalCode = signalCode;
        signalCode = newSignalCode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.BOUNDARY_SIGNAL_EVENT__SIGNAL_CODE, oldSignalCode, signalCode));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ProcessPackage.BOUNDARY_SIGNAL_EVENT__SIGNAL_CODE:
                return getSignalCode();
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
            case ProcessPackage.BOUNDARY_SIGNAL_EVENT__SIGNAL_CODE:
                setSignalCode((String)newValue);
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
            case ProcessPackage.BOUNDARY_SIGNAL_EVENT__SIGNAL_CODE:
                setSignalCode(SIGNAL_CODE_EDEFAULT);
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
            case ProcessPackage.BOUNDARY_SIGNAL_EVENT__SIGNAL_CODE:
                return SIGNAL_CODE_EDEFAULT == null ? signalCode != null : !SIGNAL_CODE_EDEFAULT.equals(signalCode);
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == SignalEvent.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.BOUNDARY_SIGNAL_EVENT__SIGNAL_CODE: return ProcessPackage.SIGNAL_EVENT__SIGNAL_CODE;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == SignalEvent.class) {
            switch (baseFeatureID) {
                case ProcessPackage.SIGNAL_EVENT__SIGNAL_CODE: return ProcessPackage.BOUNDARY_SIGNAL_EVENT__SIGNAL_CODE;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (signalCode: "); //$NON-NLS-1$
        result.append(signalCode);
        result.append(')');
        return result.toString();
    }

} //BoundarySignalEventImpl
