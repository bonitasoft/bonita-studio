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
package org.bonitasoft.studio.model.simulation.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.simulation.DayPeriod;
import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Day Period</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.DayPeriodImpl#getDay <em>Day</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.DayPeriodImpl#getStartHour <em>Start Hour</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.DayPeriodImpl#getEndHour <em>End Hour</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.DayPeriodImpl#getStartMinute <em>Start Minute</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.DayPeriodImpl#getEndMinute <em>End Minute</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DayPeriodImpl extends EObjectImpl implements DayPeriod {
	/**
	 * The cached value of the '{@link #getDay() <em>Day</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDay()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> day;

	/**
	 * The default value of the '{@link #getStartHour() <em>Start Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartHour()
	 * @generated
	 * @ordered
	 */
	protected static final int START_HOUR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStartHour() <em>Start Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartHour()
	 * @generated
	 * @ordered
	 */
	protected int startHour = START_HOUR_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndHour() <em>End Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndHour()
	 * @generated
	 * @ordered
	 */
	protected static final int END_HOUR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getEndHour() <em>End Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndHour()
	 * @generated
	 * @ordered
	 */
	protected int endHour = END_HOUR_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartMinute() <em>Start Minute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartMinute()
	 * @generated
	 * @ordered
	 */
	protected static final int START_MINUTE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStartMinute() <em>Start Minute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartMinute()
	 * @generated
	 * @ordered
	 */
	protected int startMinute = START_MINUTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndMinute() <em>End Minute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndMinute()
	 * @generated
	 * @ordered
	 */
	protected static final int END_MINUTE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getEndMinute() <em>End Minute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndMinute()
	 * @generated
	 * @ordered
	 */
	protected int endMinute = END_MINUTE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DayPeriodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimulationPackage.Literals.DAY_PERIOD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Integer> getDay() {
		if (day == null) {
			day = new EDataTypeUniqueEList<Integer>(Integer.class, this, SimulationPackage.DAY_PERIOD__DAY);
		}
		return day;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getStartHour() {
		return startHour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStartHour(int newStartHour) {
		int oldStartHour = startHour;
		startHour = newStartHour;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.DAY_PERIOD__START_HOUR, oldStartHour, startHour));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getEndHour() {
		return endHour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEndHour(int newEndHour) {
		int oldEndHour = endHour;
		endHour = newEndHour;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.DAY_PERIOD__END_HOUR, oldEndHour, endHour));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getStartMinute() {
		return startMinute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStartMinute(int newStartMinute) {
		int oldStartMinute = startMinute;
		startMinute = newStartMinute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.DAY_PERIOD__START_MINUTE, oldStartMinute, startMinute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getEndMinute() {
		return endMinute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEndMinute(int newEndMinute) {
		int oldEndMinute = endMinute;
		endMinute = newEndMinute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.DAY_PERIOD__END_MINUTE, oldEndMinute, endMinute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SimulationPackage.DAY_PERIOD__DAY:
				return getDay();
			case SimulationPackage.DAY_PERIOD__START_HOUR:
				return getStartHour();
			case SimulationPackage.DAY_PERIOD__END_HOUR:
				return getEndHour();
			case SimulationPackage.DAY_PERIOD__START_MINUTE:
				return getStartMinute();
			case SimulationPackage.DAY_PERIOD__END_MINUTE:
				return getEndMinute();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SimulationPackage.DAY_PERIOD__DAY:
				getDay().clear();
				getDay().addAll((Collection<? extends Integer>)newValue);
				return;
			case SimulationPackage.DAY_PERIOD__START_HOUR:
				setStartHour((Integer)newValue);
				return;
			case SimulationPackage.DAY_PERIOD__END_HOUR:
				setEndHour((Integer)newValue);
				return;
			case SimulationPackage.DAY_PERIOD__START_MINUTE:
				setStartMinute((Integer)newValue);
				return;
			case SimulationPackage.DAY_PERIOD__END_MINUTE:
				setEndMinute((Integer)newValue);
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
			case SimulationPackage.DAY_PERIOD__DAY:
				getDay().clear();
				return;
			case SimulationPackage.DAY_PERIOD__START_HOUR:
				setStartHour(START_HOUR_EDEFAULT);
				return;
			case SimulationPackage.DAY_PERIOD__END_HOUR:
				setEndHour(END_HOUR_EDEFAULT);
				return;
			case SimulationPackage.DAY_PERIOD__START_MINUTE:
				setStartMinute(START_MINUTE_EDEFAULT);
				return;
			case SimulationPackage.DAY_PERIOD__END_MINUTE:
				setEndMinute(END_MINUTE_EDEFAULT);
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
			case SimulationPackage.DAY_PERIOD__DAY:
				return day != null && !day.isEmpty();
			case SimulationPackage.DAY_PERIOD__START_HOUR:
				return startHour != START_HOUR_EDEFAULT;
			case SimulationPackage.DAY_PERIOD__END_HOUR:
				return endHour != END_HOUR_EDEFAULT;
			case SimulationPackage.DAY_PERIOD__START_MINUTE:
				return startMinute != START_MINUTE_EDEFAULT;
			case SimulationPackage.DAY_PERIOD__END_MINUTE:
				return endMinute != END_MINUTE_EDEFAULT;
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
		result.append(" (day: "); //$NON-NLS-1$
		result.append(day);
		result.append(", startHour: "); //$NON-NLS-1$
		result.append(startHour);
		result.append(", endHour: "); //$NON-NLS-1$
		result.append(endHour);
		result.append(", startMinute: "); //$NON-NLS-1$
		result.append(startMinute);
		result.append(", endMinute: "); //$NON-NLS-1$
		result.append(endMinute);
		result.append(')');
		return result.toString();
	}

} //DayPeriodImpl
