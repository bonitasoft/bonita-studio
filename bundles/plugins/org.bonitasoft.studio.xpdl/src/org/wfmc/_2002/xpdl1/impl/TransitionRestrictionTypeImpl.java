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
import org.wfmc._2002.xpdl1.JoinType;
import org.wfmc._2002.xpdl1.SplitType;
import org.wfmc._2002.xpdl1.TransitionRestrictionType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition Restriction Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.TransitionRestrictionTypeImpl#getJoin <em>Join</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.TransitionRestrictionTypeImpl#getSplit <em>Split</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransitionRestrictionTypeImpl extends EObjectImpl implements TransitionRestrictionType {
	/**
	 * The cached value of the '{@link #getJoin() <em>Join</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJoin()
	 * @generated
	 * @ordered
	 */
	protected JoinType join;

	/**
	 * The cached value of the '{@link #getSplit() <em>Split</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSplit()
	 * @generated
	 * @ordered
	 */
	protected SplitType split;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionRestrictionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.TRANSITION_RESTRICTION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinType getJoin() {
		return join;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJoin(JoinType newJoin, NotificationChain msgs) {
		JoinType oldJoin = join;
		join = newJoin;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.TRANSITION_RESTRICTION_TYPE__JOIN, oldJoin, newJoin);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJoin(JoinType newJoin) {
		if (newJoin != join) {
			NotificationChain msgs = null;
			if (join != null)
				msgs = ((InternalEObject)join).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.TRANSITION_RESTRICTION_TYPE__JOIN, null, msgs);
			if (newJoin != null)
				msgs = ((InternalEObject)newJoin).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.TRANSITION_RESTRICTION_TYPE__JOIN, null, msgs);
			msgs = basicSetJoin(newJoin, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.TRANSITION_RESTRICTION_TYPE__JOIN, newJoin, newJoin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SplitType getSplit() {
		return split;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSplit(SplitType newSplit, NotificationChain msgs) {
		SplitType oldSplit = split;
		split = newSplit;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.TRANSITION_RESTRICTION_TYPE__SPLIT, oldSplit, newSplit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSplit(SplitType newSplit) {
		if (newSplit != split) {
			NotificationChain msgs = null;
			if (split != null)
				msgs = ((InternalEObject)split).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.TRANSITION_RESTRICTION_TYPE__SPLIT, null, msgs);
			if (newSplit != null)
				msgs = ((InternalEObject)newSplit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.TRANSITION_RESTRICTION_TYPE__SPLIT, null, msgs);
			msgs = basicSetSplit(newSplit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.TRANSITION_RESTRICTION_TYPE__SPLIT, newSplit, newSplit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.TRANSITION_RESTRICTION_TYPE__JOIN:
				return basicSetJoin(null, msgs);
			case Xpdl1Package.TRANSITION_RESTRICTION_TYPE__SPLIT:
				return basicSetSplit(null, msgs);
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
			case Xpdl1Package.TRANSITION_RESTRICTION_TYPE__JOIN:
				return getJoin();
			case Xpdl1Package.TRANSITION_RESTRICTION_TYPE__SPLIT:
				return getSplit();
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
			case Xpdl1Package.TRANSITION_RESTRICTION_TYPE__JOIN:
				setJoin((JoinType)newValue);
				return;
			case Xpdl1Package.TRANSITION_RESTRICTION_TYPE__SPLIT:
				setSplit((SplitType)newValue);
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
			case Xpdl1Package.TRANSITION_RESTRICTION_TYPE__JOIN:
				setJoin((JoinType)null);
				return;
			case Xpdl1Package.TRANSITION_RESTRICTION_TYPE__SPLIT:
				setSplit((SplitType)null);
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
			case Xpdl1Package.TRANSITION_RESTRICTION_TYPE__JOIN:
				return join != null;
			case Xpdl1Package.TRANSITION_RESTRICTION_TYPE__SPLIT:
				return split != null;
		}
		return super.eIsSet(featureID);
	}

} //TransitionRestrictionTypeImpl
