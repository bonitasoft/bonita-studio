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

import org.bonitasoft.studio.model.simulation.ModelVersion;
import org.bonitasoft.studio.model.simulation.Resource;
import org.bonitasoft.studio.model.simulation.SimulationCalendar;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.model.simulation.TimeUnit;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceImpl#getQuantity <em>Quantity</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceImpl#getMaximumQuantity <em>Maximum Quantity</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceImpl#getCostUnit <em>Cost Unit</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceImpl#getTimeUnit <em>Time Unit</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceImpl#getFixedCost <em>Fixed Cost</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceImpl#getTimeCost <em>Time Cost</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceImpl#getCalendar <em>Calendar</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceImpl#isUnlimited <em>Unlimited</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceImpl extends SimulationElementImpl implements Resource {
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
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected static final String TYPE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected String type = TYPE_EDEFAULT;

	/**
     * The default value of the '{@link #getQuantity() <em>Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getQuantity()
     * @generated
     * @ordered
     */
	protected static final int QUANTITY_EDEFAULT = 1;

	/**
     * The cached value of the '{@link #getQuantity() <em>Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getQuantity()
     * @generated
     * @ordered
     */
	protected int quantity = QUANTITY_EDEFAULT;

	/**
     * The default value of the '{@link #getMaximumQuantity() <em>Maximum Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMaximumQuantity()
     * @generated
     * @ordered
     */
	protected static final int MAXIMUM_QUANTITY_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getMaximumQuantity() <em>Maximum Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMaximumQuantity()
     * @generated
     * @ordered
     */
	protected int maximumQuantity = MAXIMUM_QUANTITY_EDEFAULT;

	/**
     * The default value of the '{@link #getCostUnit() <em>Cost Unit</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCostUnit()
     * @generated
     * @ordered
     */
	protected static final String COST_UNIT_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getCostUnit() <em>Cost Unit</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCostUnit()
     * @generated
     * @ordered
     */
	protected String costUnit = COST_UNIT_EDEFAULT;

	/**
     * The default value of the '{@link #getTimeUnit() <em>Time Unit</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTimeUnit()
     * @generated
     * @ordered
     */
	protected static final TimeUnit TIME_UNIT_EDEFAULT = TimeUnit.MINUTE;

	/**
     * The cached value of the '{@link #getTimeUnit() <em>Time Unit</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTimeUnit()
     * @generated
     * @ordered
     */
	protected TimeUnit timeUnit = TIME_UNIT_EDEFAULT;

	/**
     * The default value of the '{@link #getFixedCost() <em>Fixed Cost</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFixedCost()
     * @generated
     * @ordered
     */
	protected static final double FIXED_COST_EDEFAULT = 0.0;

	/**
     * The cached value of the '{@link #getFixedCost() <em>Fixed Cost</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFixedCost()
     * @generated
     * @ordered
     */
	protected double fixedCost = FIXED_COST_EDEFAULT;

	/**
     * The default value of the '{@link #getTimeCost() <em>Time Cost</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTimeCost()
     * @generated
     * @ordered
     */
	protected static final double TIME_COST_EDEFAULT = 0.0;

	/**
     * The cached value of the '{@link #getTimeCost() <em>Time Cost</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTimeCost()
     * @generated
     * @ordered
     */
	protected double timeCost = TIME_COST_EDEFAULT;

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
     * The default value of the '{@link #isUnlimited() <em>Unlimited</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUnlimited()
     * @generated
     * @ordered
     */
	protected static final boolean UNLIMITED_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isUnlimited() <em>Unlimited</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUnlimited()
     * @generated
     * @ordered
     */
	protected boolean unlimited = UNLIMITED_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ResourceImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return SimulationPackage.Literals.RESOURCE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE__VERSION, oldVersion, version));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getType() {
        return type;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setType(String newType) {
        String oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE__TYPE, oldType, type));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int getQuantity() {
        return quantity;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setQuantity(int newQuantity) {
        int oldQuantity = quantity;
        quantity = newQuantity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE__QUANTITY, oldQuantity, quantity));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int getMaximumQuantity() {
        return maximumQuantity;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setMaximumQuantity(int newMaximumQuantity) {
        int oldMaximumQuantity = maximumQuantity;
        maximumQuantity = newMaximumQuantity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE__MAXIMUM_QUANTITY, oldMaximumQuantity, maximumQuantity));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getCostUnit() {
        return costUnit;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setCostUnit(String newCostUnit) {
        String oldCostUnit = costUnit;
        costUnit = newCostUnit;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE__COST_UNIT, oldCostUnit, costUnit));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public TimeUnit getTimeUnit() {
        return timeUnit;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setTimeUnit(TimeUnit newTimeUnit) {
        TimeUnit oldTimeUnit = timeUnit;
        timeUnit = newTimeUnit == null ? TIME_UNIT_EDEFAULT : newTimeUnit;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE__TIME_UNIT, oldTimeUnit, timeUnit));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public double getFixedCost() {
        return fixedCost;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setFixedCost(double newFixedCost) {
        double oldFixedCost = fixedCost;
        fixedCost = newFixedCost;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE__FIXED_COST, oldFixedCost, fixedCost));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public double getTimeCost() {
        return timeCost;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setTimeCost(double newTimeCost) {
        double oldTimeCost = timeCost;
        timeCost = newTimeCost;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE__TIME_COST, oldTimeCost, timeCost));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE__CALENDAR, oldCalendar, newCalendar);
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
                msgs = ((InternalEObject)calendar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.RESOURCE__CALENDAR, null, msgs);
            if (newCalendar != null)
                msgs = ((InternalEObject)newCalendar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.RESOURCE__CALENDAR, null, msgs);
            msgs = basicSetCalendar(newCalendar, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE__CALENDAR, newCalendar, newCalendar));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isUnlimited() {
        return unlimited;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setUnlimited(boolean newUnlimited) {
        boolean oldUnlimited = unlimited;
        unlimited = newUnlimited;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE__UNLIMITED, oldUnlimited, unlimited));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SimulationPackage.RESOURCE__CALENDAR:
                return basicSetCalendar(null, msgs);
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
            case SimulationPackage.RESOURCE__VERSION:
                return getVersion();
            case SimulationPackage.RESOURCE__TYPE:
                return getType();
            case SimulationPackage.RESOURCE__QUANTITY:
                return getQuantity();
            case SimulationPackage.RESOURCE__MAXIMUM_QUANTITY:
                return getMaximumQuantity();
            case SimulationPackage.RESOURCE__COST_UNIT:
                return getCostUnit();
            case SimulationPackage.RESOURCE__TIME_UNIT:
                return getTimeUnit();
            case SimulationPackage.RESOURCE__FIXED_COST:
                return getFixedCost();
            case SimulationPackage.RESOURCE__TIME_COST:
                return getTimeCost();
            case SimulationPackage.RESOURCE__CALENDAR:
                return getCalendar();
            case SimulationPackage.RESOURCE__UNLIMITED:
                return isUnlimited();
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
            case SimulationPackage.RESOURCE__VERSION:
                setVersion((String)newValue);
                return;
            case SimulationPackage.RESOURCE__TYPE:
                setType((String)newValue);
                return;
            case SimulationPackage.RESOURCE__QUANTITY:
                setQuantity((Integer)newValue);
                return;
            case SimulationPackage.RESOURCE__MAXIMUM_QUANTITY:
                setMaximumQuantity((Integer)newValue);
                return;
            case SimulationPackage.RESOURCE__COST_UNIT:
                setCostUnit((String)newValue);
                return;
            case SimulationPackage.RESOURCE__TIME_UNIT:
                setTimeUnit((TimeUnit)newValue);
                return;
            case SimulationPackage.RESOURCE__FIXED_COST:
                setFixedCost((Double)newValue);
                return;
            case SimulationPackage.RESOURCE__TIME_COST:
                setTimeCost((Double)newValue);
                return;
            case SimulationPackage.RESOURCE__CALENDAR:
                setCalendar((SimulationCalendar)newValue);
                return;
            case SimulationPackage.RESOURCE__UNLIMITED:
                setUnlimited((Boolean)newValue);
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
            case SimulationPackage.RESOURCE__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case SimulationPackage.RESOURCE__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case SimulationPackage.RESOURCE__QUANTITY:
                setQuantity(QUANTITY_EDEFAULT);
                return;
            case SimulationPackage.RESOURCE__MAXIMUM_QUANTITY:
                setMaximumQuantity(MAXIMUM_QUANTITY_EDEFAULT);
                return;
            case SimulationPackage.RESOURCE__COST_UNIT:
                setCostUnit(COST_UNIT_EDEFAULT);
                return;
            case SimulationPackage.RESOURCE__TIME_UNIT:
                setTimeUnit(TIME_UNIT_EDEFAULT);
                return;
            case SimulationPackage.RESOURCE__FIXED_COST:
                setFixedCost(FIXED_COST_EDEFAULT);
                return;
            case SimulationPackage.RESOURCE__TIME_COST:
                setTimeCost(TIME_COST_EDEFAULT);
                return;
            case SimulationPackage.RESOURCE__CALENDAR:
                setCalendar((SimulationCalendar)null);
                return;
            case SimulationPackage.RESOURCE__UNLIMITED:
                setUnlimited(UNLIMITED_EDEFAULT);
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
            case SimulationPackage.RESOURCE__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case SimulationPackage.RESOURCE__TYPE:
                return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
            case SimulationPackage.RESOURCE__QUANTITY:
                return quantity != QUANTITY_EDEFAULT;
            case SimulationPackage.RESOURCE__MAXIMUM_QUANTITY:
                return maximumQuantity != MAXIMUM_QUANTITY_EDEFAULT;
            case SimulationPackage.RESOURCE__COST_UNIT:
                return COST_UNIT_EDEFAULT == null ? costUnit != null : !COST_UNIT_EDEFAULT.equals(costUnit);
            case SimulationPackage.RESOURCE__TIME_UNIT:
                return timeUnit != TIME_UNIT_EDEFAULT;
            case SimulationPackage.RESOURCE__FIXED_COST:
                return fixedCost != FIXED_COST_EDEFAULT;
            case SimulationPackage.RESOURCE__TIME_COST:
                return timeCost != TIME_COST_EDEFAULT;
            case SimulationPackage.RESOURCE__CALENDAR:
                return calendar != null;
            case SimulationPackage.RESOURCE__UNLIMITED:
                return unlimited != UNLIMITED_EDEFAULT;
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
                case SimulationPackage.RESOURCE__VERSION: return SimulationPackage.MODEL_VERSION__VERSION;
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
                case SimulationPackage.MODEL_VERSION__VERSION: return SimulationPackage.RESOURCE__VERSION;
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
        result.append(", type: "); //$NON-NLS-1$
        result.append(type);
        result.append(", quantity: "); //$NON-NLS-1$
        result.append(quantity);
        result.append(", maximumQuantity: "); //$NON-NLS-1$
        result.append(maximumQuantity);
        result.append(", costUnit: "); //$NON-NLS-1$
        result.append(costUnit);
        result.append(", timeUnit: "); //$NON-NLS-1$
        result.append(timeUnit);
        result.append(", fixedCost: "); //$NON-NLS-1$
        result.append(fixedCost);
        result.append(", timeCost: "); //$NON-NLS-1$
        result.append(timeCost);
        result.append(", unlimited: "); //$NON-NLS-1$
        result.append(unlimited);
        result.append(')');
        return result.toString();
    }

} //ResourceImpl
