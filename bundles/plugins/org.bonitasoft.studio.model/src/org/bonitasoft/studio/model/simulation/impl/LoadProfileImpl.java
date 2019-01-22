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

import org.bonitasoft.studio.model.simulation.InjectionPeriod;
import org.bonitasoft.studio.model.simulation.LoadProfile;
import org.bonitasoft.studio.model.simulation.ModelVersion;
import org.bonitasoft.studio.model.simulation.SimulationCalendar;
import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Load Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.LoadProfileImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.LoadProfileImpl#getCalendar <em>Calendar</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.LoadProfileImpl#getInjectionPeriods <em>Injection Periods</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LoadProfileImpl extends SimulationElementImpl implements LoadProfile {
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCalendar() <em>Calendar</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalendar()
	 * @generated
	 * @ordered
	 */
	protected SimulationCalendar calendar;

	/**
	 * The cached value of the '{@link #getInjectionPeriods() <em>Injection Periods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjectionPeriods()
	 * @generated
	 * @ordered
	 */
	protected EList<InjectionPeriod> injectionPeriods;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LoadProfileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimulationPackage.Literals.LOAD_PROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.LOAD_PROFILE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimulationCalendar getCalendar() {
		return calendar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCalendar(SimulationCalendar newCalendar, NotificationChain msgs) {
		SimulationCalendar oldCalendar = calendar;
		calendar = newCalendar;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimulationPackage.LOAD_PROFILE__CALENDAR, oldCalendar, newCalendar);
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
	public void setCalendar(SimulationCalendar newCalendar) {
		if (newCalendar != calendar) {
			NotificationChain msgs = null;
			if (calendar != null)
				msgs = ((InternalEObject)calendar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.LOAD_PROFILE__CALENDAR, null, msgs);
			if (newCalendar != null)
				msgs = ((InternalEObject)newCalendar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.LOAD_PROFILE__CALENDAR, null, msgs);
			msgs = basicSetCalendar(newCalendar, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.LOAD_PROFILE__CALENDAR, newCalendar, newCalendar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<InjectionPeriod> getInjectionPeriods() {
		if (injectionPeriods == null) {
			injectionPeriods = new EObjectContainmentEList<InjectionPeriod>(InjectionPeriod.class, this, SimulationPackage.LOAD_PROFILE__INJECTION_PERIODS);
		}
		return injectionPeriods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimulationPackage.LOAD_PROFILE__CALENDAR:
				return basicSetCalendar(null, msgs);
			case SimulationPackage.LOAD_PROFILE__INJECTION_PERIODS:
				return ((InternalEList<?>)getInjectionPeriods()).basicRemove(otherEnd, msgs);
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
			case SimulationPackage.LOAD_PROFILE__VERSION:
				return getVersion();
			case SimulationPackage.LOAD_PROFILE__CALENDAR:
				return getCalendar();
			case SimulationPackage.LOAD_PROFILE__INJECTION_PERIODS:
				return getInjectionPeriods();
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
			case SimulationPackage.LOAD_PROFILE__VERSION:
				setVersion((String)newValue);
				return;
			case SimulationPackage.LOAD_PROFILE__CALENDAR:
				setCalendar((SimulationCalendar)newValue);
				return;
			case SimulationPackage.LOAD_PROFILE__INJECTION_PERIODS:
				getInjectionPeriods().clear();
				getInjectionPeriods().addAll((Collection<? extends InjectionPeriod>)newValue);
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
			case SimulationPackage.LOAD_PROFILE__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case SimulationPackage.LOAD_PROFILE__CALENDAR:
				setCalendar((SimulationCalendar)null);
				return;
			case SimulationPackage.LOAD_PROFILE__INJECTION_PERIODS:
				getInjectionPeriods().clear();
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
			case SimulationPackage.LOAD_PROFILE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case SimulationPackage.LOAD_PROFILE__CALENDAR:
				return calendar != null;
			case SimulationPackage.LOAD_PROFILE__INJECTION_PERIODS:
				return injectionPeriods != null && !injectionPeriods.isEmpty();
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
		if (baseClass == ModelVersion.class) {
			switch (derivedFeatureID) {
				case SimulationPackage.LOAD_PROFILE__VERSION: return SimulationPackage.MODEL_VERSION__VERSION;
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
		if (baseClass == ModelVersion.class) {
			switch (baseFeatureID) {
				case SimulationPackage.MODEL_VERSION__VERSION: return SimulationPackage.LOAD_PROFILE__VERSION;
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
		result.append(" (version: "); //$NON-NLS-1$
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //LoadProfileImpl
