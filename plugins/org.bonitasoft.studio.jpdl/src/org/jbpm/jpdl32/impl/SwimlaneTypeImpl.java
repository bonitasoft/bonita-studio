/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.jbpm.jpdl32.AssignmentType;
import org.jbpm.jpdl32.SwimlaneType;
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Swimlane Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.impl.SwimlaneTypeImpl#getAssignment <em>Assignment</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.SwimlaneTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SwimlaneTypeImpl extends EObjectImpl implements SwimlaneType {
	/**
	 * The cached value of the '{@link #getAssignment() <em>Assignment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignment()
	 * @generated
	 * @ordered
	 */
	protected AssignmentType assignment;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SwimlaneTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return jpdl32Package.Literals.SWIMLANE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignmentType getAssignment() {
		return assignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssignment(AssignmentType newAssignment, NotificationChain msgs) {
		AssignmentType oldAssignment = assignment;
		assignment = newAssignment;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, jpdl32Package.SWIMLANE_TYPE__ASSIGNMENT, oldAssignment, newAssignment);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssignment(AssignmentType newAssignment) {
		if (newAssignment != assignment) {
			NotificationChain msgs = null;
			if (assignment != null)
				msgs = ((InternalEObject)assignment).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.SWIMLANE_TYPE__ASSIGNMENT, null, msgs);
			if (newAssignment != null)
				msgs = ((InternalEObject)newAssignment).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - jpdl32Package.SWIMLANE_TYPE__ASSIGNMENT, null, msgs);
			msgs = basicSetAssignment(newAssignment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.SWIMLANE_TYPE__ASSIGNMENT, newAssignment, newAssignment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.SWIMLANE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case jpdl32Package.SWIMLANE_TYPE__ASSIGNMENT:
				return basicSetAssignment(null, msgs);
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
			case jpdl32Package.SWIMLANE_TYPE__ASSIGNMENT:
				return getAssignment();
			case jpdl32Package.SWIMLANE_TYPE__NAME:
				return getName();
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
			case jpdl32Package.SWIMLANE_TYPE__ASSIGNMENT:
				setAssignment((AssignmentType)newValue);
				return;
			case jpdl32Package.SWIMLANE_TYPE__NAME:
				setName((String)newValue);
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
			case jpdl32Package.SWIMLANE_TYPE__ASSIGNMENT:
				setAssignment((AssignmentType)null);
				return;
			case jpdl32Package.SWIMLANE_TYPE__NAME:
				setName(NAME_EDEFAULT);
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
			case jpdl32Package.SWIMLANE_TYPE__ASSIGNMENT:
				return assignment != null;
			case jpdl32Package.SWIMLANE_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //SwimlaneTypeImpl
