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

import org.bonitasoft.studio.model.simulation.InjectionPeriod;
import org.bonitasoft.studio.model.simulation.RepartitionType;
import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Injection Period</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.InjectionPeriodImpl#getBegin <em>Begin</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.InjectionPeriodImpl#getEnd <em>End</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.InjectionPeriodImpl#getNbInstances <em>Nb Instances</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.InjectionPeriodImpl#getRepartition <em>Repartition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InjectionPeriodImpl extends EObjectImpl implements InjectionPeriod {
	/**
     * The default value of the '{@link #getBegin() <em>Begin</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getBegin()
     * @generated
     * @ordered
     */
	protected static final long BEGIN_EDEFAULT = 0L;

	/**
     * The cached value of the '{@link #getBegin() <em>Begin</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getBegin()
     * @generated
     * @ordered
     */
	protected long begin = BEGIN_EDEFAULT;

	/**
     * The default value of the '{@link #getEnd() <em>End</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getEnd()
     * @generated
     * @ordered
     */
	protected static final long END_EDEFAULT = 0L;

	/**
     * The cached value of the '{@link #getEnd() <em>End</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getEnd()
     * @generated
     * @ordered
     */
	protected long end = END_EDEFAULT;

	/**
     * The default value of the '{@link #getNbInstances() <em>Nb Instances</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getNbInstances()
     * @generated
     * @ordered
     */
	protected static final int NB_INSTANCES_EDEFAULT = 1;

	/**
     * The cached value of the '{@link #getNbInstances() <em>Nb Instances</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getNbInstances()
     * @generated
     * @ordered
     */
	protected int nbInstances = NB_INSTANCES_EDEFAULT;

	/**
     * The default value of the '{@link #getRepartition() <em>Repartition</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRepartition()
     * @generated
     * @ordered
     */
	protected static final RepartitionType REPARTITION_EDEFAULT = RepartitionType.CONSTANT;

	/**
     * The cached value of the '{@link #getRepartition() <em>Repartition</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRepartition()
     * @generated
     * @ordered
     */
	protected RepartitionType repartition = REPARTITION_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected InjectionPeriodImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return SimulationPackage.Literals.INJECTION_PERIOD;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public long getBegin() {
        return begin;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setBegin(long newBegin) {
        long oldBegin = begin;
        begin = newBegin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.INJECTION_PERIOD__BEGIN, oldBegin, begin));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public long getEnd() {
        return end;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setEnd(long newEnd) {
        long oldEnd = end;
        end = newEnd;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.INJECTION_PERIOD__END, oldEnd, end));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int getNbInstances() {
        return nbInstances;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setNbInstances(int newNbInstances) {
        int oldNbInstances = nbInstances;
        nbInstances = newNbInstances;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.INJECTION_PERIOD__NB_INSTANCES, oldNbInstances, nbInstances));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public RepartitionType getRepartition() {
        return repartition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setRepartition(RepartitionType newRepartition) {
        RepartitionType oldRepartition = repartition;
        repartition = newRepartition == null ? REPARTITION_EDEFAULT : newRepartition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.INJECTION_PERIOD__REPARTITION, oldRepartition, repartition));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SimulationPackage.INJECTION_PERIOD__BEGIN:
                return getBegin();
            case SimulationPackage.INJECTION_PERIOD__END:
                return getEnd();
            case SimulationPackage.INJECTION_PERIOD__NB_INSTANCES:
                return getNbInstances();
            case SimulationPackage.INJECTION_PERIOD__REPARTITION:
                return getRepartition();
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
            case SimulationPackage.INJECTION_PERIOD__BEGIN:
                setBegin((Long)newValue);
                return;
            case SimulationPackage.INJECTION_PERIOD__END:
                setEnd((Long)newValue);
                return;
            case SimulationPackage.INJECTION_PERIOD__NB_INSTANCES:
                setNbInstances((Integer)newValue);
                return;
            case SimulationPackage.INJECTION_PERIOD__REPARTITION:
                setRepartition((RepartitionType)newValue);
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
            case SimulationPackage.INJECTION_PERIOD__BEGIN:
                setBegin(BEGIN_EDEFAULT);
                return;
            case SimulationPackage.INJECTION_PERIOD__END:
                setEnd(END_EDEFAULT);
                return;
            case SimulationPackage.INJECTION_PERIOD__NB_INSTANCES:
                setNbInstances(NB_INSTANCES_EDEFAULT);
                return;
            case SimulationPackage.INJECTION_PERIOD__REPARTITION:
                setRepartition(REPARTITION_EDEFAULT);
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
            case SimulationPackage.INJECTION_PERIOD__BEGIN:
                return begin != BEGIN_EDEFAULT;
            case SimulationPackage.INJECTION_PERIOD__END:
                return end != END_EDEFAULT;
            case SimulationPackage.INJECTION_PERIOD__NB_INSTANCES:
                return nbInstances != NB_INSTANCES_EDEFAULT;
            case SimulationPackage.INJECTION_PERIOD__REPARTITION:
                return repartition != REPARTITION_EDEFAULT;
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
        result.append(" (begin: "); //$NON-NLS-1$
        result.append(begin);
        result.append(", end: "); //$NON-NLS-1$
        result.append(end);
        result.append(", nbInstances: "); //$NON-NLS-1$
        result.append(nbInstances);
        result.append(", repartition: "); //$NON-NLS-1$
        result.append(repartition);
        result.append(')');
        return result.toString();
    }

} //InjectionPeriodImpl
