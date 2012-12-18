/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.wfmc._2002.xpdl1.BlockActivityType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Block Activity Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.BlockActivityTypeImpl#getBlockId <em>Block Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BlockActivityTypeImpl extends EObjectImpl implements BlockActivityType {
	/**
	 * The default value of the '{@link #getBlockId() <em>Block Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlockId()
	 * @generated
	 * @ordered
	 */
	protected static final String BLOCK_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBlockId() <em>Block Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlockId()
	 * @generated
	 * @ordered
	 */
	protected String blockId = BLOCK_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BlockActivityTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.BLOCK_ACTIVITY_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBlockId() {
		return blockId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlockId(String newBlockId) {
		String oldBlockId = blockId;
		blockId = newBlockId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.BLOCK_ACTIVITY_TYPE__BLOCK_ID, oldBlockId, blockId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Xpdl1Package.BLOCK_ACTIVITY_TYPE__BLOCK_ID:
				return getBlockId();
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
			case Xpdl1Package.BLOCK_ACTIVITY_TYPE__BLOCK_ID:
				setBlockId((String)newValue);
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
			case Xpdl1Package.BLOCK_ACTIVITY_TYPE__BLOCK_ID:
				setBlockId(BLOCK_ID_EDEFAULT);
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
			case Xpdl1Package.BLOCK_ACTIVITY_TYPE__BLOCK_ID:
				return BLOCK_ID_EDEFAULT == null ? blockId != null : !BLOCK_ID_EDEFAULT.equals(blockId);
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
		result.append(" (blockId: ");
		result.append(blockId);
		result.append(')');
		return result.toString();
	}

} //BlockActivityTypeImpl
