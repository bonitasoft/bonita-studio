/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.wfmc._2002.xpdl1.ImplementationType;
import org.wfmc._2002.xpdl1.NoType;
import org.wfmc._2002.xpdl1.SubFlowType;
import org.wfmc._2002.xpdl1.ToolType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Implementation Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ImplementationTypeImpl#getNo <em>No</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ImplementationTypeImpl#getTool <em>Tool</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ImplementationTypeImpl#getSubFlow <em>Sub Flow</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImplementationTypeImpl extends EObjectImpl implements ImplementationType {
	/**
	 * The cached value of the '{@link #getNo() <em>No</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNo()
	 * @generated
	 * @ordered
	 */
	protected NoType no;

	/**
	 * The cached value of the '{@link #getTool() <em>Tool</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTool()
	 * @generated
	 * @ordered
	 */
	protected EList<ToolType> tool;

	/**
	 * The cached value of the '{@link #getSubFlow() <em>Sub Flow</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubFlow()
	 * @generated
	 * @ordered
	 */
	protected SubFlowType subFlow;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImplementationTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.IMPLEMENTATION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoType getNo() {
		return no;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNo(NoType newNo, NotificationChain msgs) {
		NoType oldNo = no;
		no = newNo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.IMPLEMENTATION_TYPE__NO, oldNo, newNo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNo(NoType newNo) {
		if (newNo != no) {
			NotificationChain msgs = null;
			if (no != null)
				msgs = ((InternalEObject)no).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.IMPLEMENTATION_TYPE__NO, null, msgs);
			if (newNo != null)
				msgs = ((InternalEObject)newNo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.IMPLEMENTATION_TYPE__NO, null, msgs);
			msgs = basicSetNo(newNo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.IMPLEMENTATION_TYPE__NO, newNo, newNo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ToolType> getTool() {
		if (tool == null) {
			tool = new EObjectContainmentEList<ToolType>(ToolType.class, this, Xpdl1Package.IMPLEMENTATION_TYPE__TOOL);
		}
		return tool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubFlowType getSubFlow() {
		return subFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSubFlow(SubFlowType newSubFlow, NotificationChain msgs) {
		SubFlowType oldSubFlow = subFlow;
		subFlow = newSubFlow;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.IMPLEMENTATION_TYPE__SUB_FLOW, oldSubFlow, newSubFlow);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubFlow(SubFlowType newSubFlow) {
		if (newSubFlow != subFlow) {
			NotificationChain msgs = null;
			if (subFlow != null)
				msgs = ((InternalEObject)subFlow).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.IMPLEMENTATION_TYPE__SUB_FLOW, null, msgs);
			if (newSubFlow != null)
				msgs = ((InternalEObject)newSubFlow).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.IMPLEMENTATION_TYPE__SUB_FLOW, null, msgs);
			msgs = basicSetSubFlow(newSubFlow, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.IMPLEMENTATION_TYPE__SUB_FLOW, newSubFlow, newSubFlow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.IMPLEMENTATION_TYPE__NO:
				return basicSetNo(null, msgs);
			case Xpdl1Package.IMPLEMENTATION_TYPE__TOOL:
				return ((InternalEList<?>)getTool()).basicRemove(otherEnd, msgs);
			case Xpdl1Package.IMPLEMENTATION_TYPE__SUB_FLOW:
				return basicSetSubFlow(null, msgs);
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
			case Xpdl1Package.IMPLEMENTATION_TYPE__NO:
				return getNo();
			case Xpdl1Package.IMPLEMENTATION_TYPE__TOOL:
				return getTool();
			case Xpdl1Package.IMPLEMENTATION_TYPE__SUB_FLOW:
				return getSubFlow();
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
			case Xpdl1Package.IMPLEMENTATION_TYPE__NO:
				setNo((NoType)newValue);
				return;
			case Xpdl1Package.IMPLEMENTATION_TYPE__TOOL:
				getTool().clear();
				getTool().addAll((Collection<? extends ToolType>)newValue);
				return;
			case Xpdl1Package.IMPLEMENTATION_TYPE__SUB_FLOW:
				setSubFlow((SubFlowType)newValue);
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
			case Xpdl1Package.IMPLEMENTATION_TYPE__NO:
				setNo((NoType)null);
				return;
			case Xpdl1Package.IMPLEMENTATION_TYPE__TOOL:
				getTool().clear();
				return;
			case Xpdl1Package.IMPLEMENTATION_TYPE__SUB_FLOW:
				setSubFlow((SubFlowType)null);
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
			case Xpdl1Package.IMPLEMENTATION_TYPE__NO:
				return no != null;
			case Xpdl1Package.IMPLEMENTATION_TYPE__TOOL:
				return tool != null && !tool.isEmpty();
			case Xpdl1Package.IMPLEMENTATION_TYPE__SUB_FLOW:
				return subFlow != null;
		}
		return super.eIsSet(featureID);
	}

} //ImplementationTypeImpl
