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

import org.bonitasoft.studio.model.simulation.ResourceUsage;
import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Usage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceUsageImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceUsageImpl#getResourceID <em>Resource ID</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceUsageImpl#getQuantity <em>Quantity</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.impl.ResourceUsageImpl#isUseActivityDuration <em>Use Activity Duration</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceUsageImpl extends EObjectImpl implements ResourceUsage {
	/**
     * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDuration()
     * @generated
     * @ordered
     */
	protected static final long DURATION_EDEFAULT = 0L;

	/**
     * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDuration()
     * @generated
     * @ordered
     */
	protected long duration = DURATION_EDEFAULT;

	/**
     * The default value of the '{@link #getResourceID() <em>Resource ID</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getResourceID()
     * @generated
     * @ordered
     */
	protected static final String RESOURCE_ID_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getResourceID() <em>Resource ID</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getResourceID()
     * @generated
     * @ordered
     */
	protected String resourceID = RESOURCE_ID_EDEFAULT;

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
     * The default value of the '{@link #isUseActivityDuration() <em>Use Activity Duration</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUseActivityDuration()
     * @generated
     * @ordered
     */
	protected static final boolean USE_ACTIVITY_DURATION_EDEFAULT = true;

	/**
     * The cached value of the '{@link #isUseActivityDuration() <em>Use Activity Duration</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUseActivityDuration()
     * @generated
     * @ordered
     */
	protected boolean useActivityDuration = USE_ACTIVITY_DURATION_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ResourceUsageImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return SimulationPackage.Literals.RESOURCE_USAGE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public long getDuration() {
        return duration;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDuration(long newDuration) {
        long oldDuration = duration;
        duration = newDuration;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE_USAGE__DURATION, oldDuration, duration));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getResourceID() {
        return resourceID;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setResourceID(String newResourceID) {
        String oldResourceID = resourceID;
        resourceID = newResourceID;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE_USAGE__RESOURCE_ID, oldResourceID, resourceID));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE_USAGE__QUANTITY, oldQuantity, quantity));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isUseActivityDuration() {
        return useActivityDuration;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setUseActivityDuration(boolean newUseActivityDuration) {
        boolean oldUseActivityDuration = useActivityDuration;
        useActivityDuration = newUseActivityDuration;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.RESOURCE_USAGE__USE_ACTIVITY_DURATION, oldUseActivityDuration, useActivityDuration));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SimulationPackage.RESOURCE_USAGE__DURATION:
                return getDuration();
            case SimulationPackage.RESOURCE_USAGE__RESOURCE_ID:
                return getResourceID();
            case SimulationPackage.RESOURCE_USAGE__QUANTITY:
                return getQuantity();
            case SimulationPackage.RESOURCE_USAGE__USE_ACTIVITY_DURATION:
                return isUseActivityDuration();
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
            case SimulationPackage.RESOURCE_USAGE__DURATION:
                setDuration((Long)newValue);
                return;
            case SimulationPackage.RESOURCE_USAGE__RESOURCE_ID:
                setResourceID((String)newValue);
                return;
            case SimulationPackage.RESOURCE_USAGE__QUANTITY:
                setQuantity((Integer)newValue);
                return;
            case SimulationPackage.RESOURCE_USAGE__USE_ACTIVITY_DURATION:
                setUseActivityDuration((Boolean)newValue);
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
            case SimulationPackage.RESOURCE_USAGE__DURATION:
                setDuration(DURATION_EDEFAULT);
                return;
            case SimulationPackage.RESOURCE_USAGE__RESOURCE_ID:
                setResourceID(RESOURCE_ID_EDEFAULT);
                return;
            case SimulationPackage.RESOURCE_USAGE__QUANTITY:
                setQuantity(QUANTITY_EDEFAULT);
                return;
            case SimulationPackage.RESOURCE_USAGE__USE_ACTIVITY_DURATION:
                setUseActivityDuration(USE_ACTIVITY_DURATION_EDEFAULT);
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
            case SimulationPackage.RESOURCE_USAGE__DURATION:
                return duration != DURATION_EDEFAULT;
            case SimulationPackage.RESOURCE_USAGE__RESOURCE_ID:
                return RESOURCE_ID_EDEFAULT == null ? resourceID != null : !RESOURCE_ID_EDEFAULT.equals(resourceID);
            case SimulationPackage.RESOURCE_USAGE__QUANTITY:
                return quantity != QUANTITY_EDEFAULT;
            case SimulationPackage.RESOURCE_USAGE__USE_ACTIVITY_DURATION:
                return useActivityDuration != USE_ACTIVITY_DURATION_EDEFAULT;
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
        result.append(" (duration: "); //$NON-NLS-1$
        result.append(duration);
        result.append(", resourceID: "); //$NON-NLS-1$
        result.append(resourceID);
        result.append(", quantity: "); //$NON-NLS-1$
        result.append(quantity);
        result.append(", useActivityDuration: "); //$NON-NLS-1$
        result.append(useActivityDuration);
        result.append(')');
        return result.toString();
    }

} //ResourceUsageImpl
