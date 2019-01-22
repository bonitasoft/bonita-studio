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
package org.bonitasoft.studio.model.process.decision.transitions.impl;

import org.bonitasoft.studio.model.process.decision.impl.DecisionTableActionImpl;

import org.bonitasoft.studio.model.process.decision.transitions.TakeTransitionAction;
import org.bonitasoft.studio.model.process.decision.transitions.TransitionsPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Take Transition Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.decision.transitions.impl.TakeTransitionActionImpl#isTakeTransition <em>Take Transition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TakeTransitionActionImpl extends DecisionTableActionImpl implements TakeTransitionAction {
	/**
	 * The default value of the '{@link #isTakeTransition() <em>Take Transition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTakeTransition()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TAKE_TRANSITION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTakeTransition() <em>Take Transition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTakeTransition()
	 * @generated
	 * @ordered
	 */
	protected boolean takeTransition = TAKE_TRANSITION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TakeTransitionActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TransitionsPackage.Literals.TAKE_TRANSITION_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isTakeTransition() {
		return takeTransition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTakeTransition(boolean newTakeTransition) {
		boolean oldTakeTransition = takeTransition;
		takeTransition = newTakeTransition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TransitionsPackage.TAKE_TRANSITION_ACTION__TAKE_TRANSITION, oldTakeTransition, takeTransition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TransitionsPackage.TAKE_TRANSITION_ACTION__TAKE_TRANSITION:
				return isTakeTransition();
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
			case TransitionsPackage.TAKE_TRANSITION_ACTION__TAKE_TRANSITION:
				setTakeTransition((Boolean)newValue);
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
			case TransitionsPackage.TAKE_TRANSITION_ACTION__TAKE_TRANSITION:
				setTakeTransition(TAKE_TRANSITION_EDEFAULT);
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
			case TransitionsPackage.TAKE_TRANSITION_ACTION__TAKE_TRANSITION:
				return takeTransition != TAKE_TRANSITION_EDEFAULT;
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
		result.append(" (takeTransition: "); //$NON-NLS-1$
		result.append(takeTransition);
		result.append(')');
		return result.toString();
	}

} //TakeTransitionActionImpl
