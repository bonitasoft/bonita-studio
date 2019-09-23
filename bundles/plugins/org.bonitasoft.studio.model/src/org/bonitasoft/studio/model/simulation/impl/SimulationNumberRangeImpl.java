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

import org.bonitasoft.studio.model.simulation.RepartitionType;
import org.bonitasoft.studio.model.simulation.SimulationNumberRange;
import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Number Range</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.SimulationNumberRangeImpl#getMin <em>Min</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.SimulationNumberRangeImpl#getMax <em>Max</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.SimulationNumberRangeImpl#getProbability <em>Probability</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.SimulationNumberRangeImpl#getRepartitionType <em>Repartition Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SimulationNumberRangeImpl extends EObjectImpl implements SimulationNumberRange {
	/**
     * The default value of the '{@link #getMin() <em>Min</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMin()
     * @generated
     * @ordered
     */
	protected static final long MIN_EDEFAULT = 0L;

	/**
     * The cached value of the '{@link #getMin() <em>Min</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMin()
     * @generated
     * @ordered
     */
	protected long min = MIN_EDEFAULT;

	/**
     * The default value of the '{@link #getMax() <em>Max</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
	protected static final long MAX_EDEFAULT = 0L;

	/**
     * The cached value of the '{@link #getMax() <em>Max</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
	protected long max = MAX_EDEFAULT;

	/**
     * The default value of the '{@link #getProbability() <em>Probability</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getProbability()
     * @generated
     * @ordered
     */
	protected static final double PROBABILITY_EDEFAULT = 1.0;

	/**
     * The cached value of the '{@link #getProbability() <em>Probability</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getProbability()
     * @generated
     * @ordered
     */
	protected double probability = PROBABILITY_EDEFAULT;

	/**
     * The default value of the '{@link #getRepartitionType() <em>Repartition Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRepartitionType()
     * @generated
     * @ordered
     */
	protected static final RepartitionType REPARTITION_TYPE_EDEFAULT = RepartitionType.CONSTANT;

	/**
     * The cached value of the '{@link #getRepartitionType() <em>Repartition Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRepartitionType()
     * @generated
     * @ordered
     */
	protected RepartitionType repartitionType = REPARTITION_TYPE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SimulationNumberRangeImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return SimulationPackage.Literals.SIMULATION_NUMBER_RANGE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public long getMin() {
        return min;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setMin(long newMin) {
        long oldMin = min;
        min = newMin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATION_NUMBER_RANGE__MIN, oldMin, min));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public long getMax() {
        return max;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setMax(long newMax) {
        long oldMax = max;
        max = newMax;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATION_NUMBER_RANGE__MAX, oldMax, max));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public double getProbability() {
        return probability;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setProbability(double newProbability) {
        double oldProbability = probability;
        probability = newProbability;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATION_NUMBER_RANGE__PROBABILITY, oldProbability, probability));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public RepartitionType getRepartitionType() {
        return repartitionType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setRepartitionType(RepartitionType newRepartitionType) {
        RepartitionType oldRepartitionType = repartitionType;
        repartitionType = newRepartitionType == null ? REPARTITION_TYPE_EDEFAULT : newRepartitionType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATION_NUMBER_RANGE__REPARTITION_TYPE, oldRepartitionType, repartitionType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SimulationPackage.SIMULATION_NUMBER_RANGE__MIN:
                return getMin();
            case SimulationPackage.SIMULATION_NUMBER_RANGE__MAX:
                return getMax();
            case SimulationPackage.SIMULATION_NUMBER_RANGE__PROBABILITY:
                return getProbability();
            case SimulationPackage.SIMULATION_NUMBER_RANGE__REPARTITION_TYPE:
                return getRepartitionType();
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
            case SimulationPackage.SIMULATION_NUMBER_RANGE__MIN:
                setMin((Long)newValue);
                return;
            case SimulationPackage.SIMULATION_NUMBER_RANGE__MAX:
                setMax((Long)newValue);
                return;
            case SimulationPackage.SIMULATION_NUMBER_RANGE__PROBABILITY:
                setProbability((Double)newValue);
                return;
            case SimulationPackage.SIMULATION_NUMBER_RANGE__REPARTITION_TYPE:
                setRepartitionType((RepartitionType)newValue);
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
            case SimulationPackage.SIMULATION_NUMBER_RANGE__MIN:
                setMin(MIN_EDEFAULT);
                return;
            case SimulationPackage.SIMULATION_NUMBER_RANGE__MAX:
                setMax(MAX_EDEFAULT);
                return;
            case SimulationPackage.SIMULATION_NUMBER_RANGE__PROBABILITY:
                setProbability(PROBABILITY_EDEFAULT);
                return;
            case SimulationPackage.SIMULATION_NUMBER_RANGE__REPARTITION_TYPE:
                setRepartitionType(REPARTITION_TYPE_EDEFAULT);
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
            case SimulationPackage.SIMULATION_NUMBER_RANGE__MIN:
                return min != MIN_EDEFAULT;
            case SimulationPackage.SIMULATION_NUMBER_RANGE__MAX:
                return max != MAX_EDEFAULT;
            case SimulationPackage.SIMULATION_NUMBER_RANGE__PROBABILITY:
                return probability != PROBABILITY_EDEFAULT;
            case SimulationPackage.SIMULATION_NUMBER_RANGE__REPARTITION_TYPE:
                return repartitionType != REPARTITION_TYPE_EDEFAULT;
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
        result.append(" (min: "); //$NON-NLS-1$
        result.append(min);
        result.append(", max: "); //$NON-NLS-1$
        result.append(max);
        result.append(", probability: "); //$NON-NLS-1$
        result.append(probability);
        result.append(", repartitionType: "); //$NON-NLS-1$
        result.append(repartitionType);
        result.append(')');
        return result.toString();
    }

} //SimulationNumberRangeImpl
