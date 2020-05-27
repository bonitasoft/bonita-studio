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
import org.wfmc._2002.xpdl1.AutomaticType;
import org.wfmc._2002.xpdl1.FinishModeType;
import org.wfmc._2002.xpdl1.ManualType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Finish Mode Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.FinishModeTypeImpl#getAutomatic <em>Automatic</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.FinishModeTypeImpl#getManual <em>Manual</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FinishModeTypeImpl extends EObjectImpl implements FinishModeType {
	/**
	 * The cached value of the '{@link #getAutomatic() <em>Automatic</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutomatic()
	 * @generated
	 * @ordered
	 */
	protected AutomaticType automatic;

	/**
	 * The cached value of the '{@link #getManual() <em>Manual</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManual()
	 * @generated
	 * @ordered
	 */
	protected ManualType manual;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FinishModeTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.FINISH_MODE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AutomaticType getAutomatic() {
		return automatic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAutomatic(AutomaticType newAutomatic, NotificationChain msgs) {
		AutomaticType oldAutomatic = automatic;
		automatic = newAutomatic;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.FINISH_MODE_TYPE__AUTOMATIC, oldAutomatic, newAutomatic);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutomatic(AutomaticType newAutomatic) {
		if (newAutomatic != automatic) {
			NotificationChain msgs = null;
			if (automatic != null)
				msgs = ((InternalEObject)automatic).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.FINISH_MODE_TYPE__AUTOMATIC, null, msgs);
			if (newAutomatic != null)
				msgs = ((InternalEObject)newAutomatic).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.FINISH_MODE_TYPE__AUTOMATIC, null, msgs);
			msgs = basicSetAutomatic(newAutomatic, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.FINISH_MODE_TYPE__AUTOMATIC, newAutomatic, newAutomatic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManualType getManual() {
		return manual;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetManual(ManualType newManual, NotificationChain msgs) {
		ManualType oldManual = manual;
		manual = newManual;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.FINISH_MODE_TYPE__MANUAL, oldManual, newManual);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManual(ManualType newManual) {
		if (newManual != manual) {
			NotificationChain msgs = null;
			if (manual != null)
				msgs = ((InternalEObject)manual).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.FINISH_MODE_TYPE__MANUAL, null, msgs);
			if (newManual != null)
				msgs = ((InternalEObject)newManual).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.FINISH_MODE_TYPE__MANUAL, null, msgs);
			msgs = basicSetManual(newManual, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.FINISH_MODE_TYPE__MANUAL, newManual, newManual));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.FINISH_MODE_TYPE__AUTOMATIC:
				return basicSetAutomatic(null, msgs);
			case Xpdl1Package.FINISH_MODE_TYPE__MANUAL:
				return basicSetManual(null, msgs);
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
			case Xpdl1Package.FINISH_MODE_TYPE__AUTOMATIC:
				return getAutomatic();
			case Xpdl1Package.FINISH_MODE_TYPE__MANUAL:
				return getManual();
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
			case Xpdl1Package.FINISH_MODE_TYPE__AUTOMATIC:
				setAutomatic((AutomaticType)newValue);
				return;
			case Xpdl1Package.FINISH_MODE_TYPE__MANUAL:
				setManual((ManualType)newValue);
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
			case Xpdl1Package.FINISH_MODE_TYPE__AUTOMATIC:
				setAutomatic((AutomaticType)null);
				return;
			case Xpdl1Package.FINISH_MODE_TYPE__MANUAL:
				setManual((ManualType)null);
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
			case Xpdl1Package.FINISH_MODE_TYPE__AUTOMATIC:
				return automatic != null;
			case Xpdl1Package.FINISH_MODE_TYPE__MANUAL:
				return manual != null;
		}
		return super.eIsSet(featureID);
	}

} //FinishModeTypeImpl
