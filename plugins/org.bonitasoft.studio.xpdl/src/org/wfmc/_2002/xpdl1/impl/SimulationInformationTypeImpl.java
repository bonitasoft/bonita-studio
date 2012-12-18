/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.wfmc._2002.xpdl1.InstantiationType;
import org.wfmc._2002.xpdl1.SimulationInformationType;
import org.wfmc._2002.xpdl1.TimeEstimationType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simulation Information Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.SimulationInformationTypeImpl#getCost <em>Cost</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.SimulationInformationTypeImpl#getTimeEstimation <em>Time Estimation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.SimulationInformationTypeImpl#getInstantiation <em>Instantiation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SimulationInformationTypeImpl extends EObjectImpl implements SimulationInformationType {
	/**
	 * The default value of the '{@link #getCost() <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCost()
	 * @generated
	 * @ordered
	 */
	protected static final String COST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCost() <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCost()
	 * @generated
	 * @ordered
	 */
	protected String cost = COST_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTimeEstimation() <em>Time Estimation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeEstimation()
	 * @generated
	 * @ordered
	 */
	protected TimeEstimationType timeEstimation;

	/**
	 * The default value of the '{@link #getInstantiation() <em>Instantiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstantiation()
	 * @generated
	 * @ordered
	 */
	protected static final InstantiationType INSTANTIATION_EDEFAULT = InstantiationType.ONCE;

	/**
	 * The cached value of the '{@link #getInstantiation() <em>Instantiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstantiation()
	 * @generated
	 * @ordered
	 */
	protected InstantiationType instantiation = INSTANTIATION_EDEFAULT;

	/**
	 * This is true if the Instantiation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean instantiationESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimulationInformationTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.SIMULATION_INFORMATION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCost() {
		return cost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCost(String newCost) {
		String oldCost = cost;
		cost = newCost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.SIMULATION_INFORMATION_TYPE__COST, oldCost, cost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeEstimationType getTimeEstimation() {
		return timeEstimation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimeEstimation(TimeEstimationType newTimeEstimation, NotificationChain msgs) {
		TimeEstimationType oldTimeEstimation = timeEstimation;
		timeEstimation = newTimeEstimation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.SIMULATION_INFORMATION_TYPE__TIME_ESTIMATION, oldTimeEstimation, newTimeEstimation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeEstimation(TimeEstimationType newTimeEstimation) {
		if (newTimeEstimation != timeEstimation) {
			NotificationChain msgs = null;
			if (timeEstimation != null)
				msgs = ((InternalEObject)timeEstimation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.SIMULATION_INFORMATION_TYPE__TIME_ESTIMATION, null, msgs);
			if (newTimeEstimation != null)
				msgs = ((InternalEObject)newTimeEstimation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.SIMULATION_INFORMATION_TYPE__TIME_ESTIMATION, null, msgs);
			msgs = basicSetTimeEstimation(newTimeEstimation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.SIMULATION_INFORMATION_TYPE__TIME_ESTIMATION, newTimeEstimation, newTimeEstimation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstantiationType getInstantiation() {
		return instantiation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstantiation(InstantiationType newInstantiation) {
		InstantiationType oldInstantiation = instantiation;
		instantiation = newInstantiation == null ? INSTANTIATION_EDEFAULT : newInstantiation;
		boolean oldInstantiationESet = instantiationESet;
		instantiationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.SIMULATION_INFORMATION_TYPE__INSTANTIATION, oldInstantiation, instantiation, !oldInstantiationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetInstantiation() {
		InstantiationType oldInstantiation = instantiation;
		boolean oldInstantiationESet = instantiationESet;
		instantiation = INSTANTIATION_EDEFAULT;
		instantiationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Xpdl1Package.SIMULATION_INFORMATION_TYPE__INSTANTIATION, oldInstantiation, INSTANTIATION_EDEFAULT, oldInstantiationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetInstantiation() {
		return instantiationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__TIME_ESTIMATION:
				return basicSetTimeEstimation(null, msgs);
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
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__COST:
				return getCost();
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__TIME_ESTIMATION:
				return getTimeEstimation();
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__INSTANTIATION:
				return getInstantiation();
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
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__COST:
				setCost((String)newValue);
				return;
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__TIME_ESTIMATION:
				setTimeEstimation((TimeEstimationType)newValue);
				return;
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__INSTANTIATION:
				setInstantiation((InstantiationType)newValue);
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
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__COST:
				setCost(COST_EDEFAULT);
				return;
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__TIME_ESTIMATION:
				setTimeEstimation((TimeEstimationType)null);
				return;
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__INSTANTIATION:
				unsetInstantiation();
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
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__COST:
				return COST_EDEFAULT == null ? cost != null : !COST_EDEFAULT.equals(cost);
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__TIME_ESTIMATION:
				return timeEstimation != null;
			case Xpdl1Package.SIMULATION_INFORMATION_TYPE__INSTANTIATION:
				return isSetInstantiation();
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (cost: ");
		result.append(cost);
		result.append(", instantiation: ");
		if (instantiationESet) result.append(instantiation); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //SimulationInformationTypeImpl
