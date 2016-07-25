/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TRendering;
import org.omg.spec.bpmn.model.TUserTask;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TUser Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TUserTaskImpl#getRendering <em>Rendering</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TUserTaskImpl#getImplementation <em>Implementation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TUserTaskImpl extends TTaskImpl implements TUserTask {
	/**
	 * The cached value of the '{@link #getRendering() <em>Rendering</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRendering()
	 * @generated
	 * @ordered
	 */
	protected EList<TRendering> rendering;

	/**
	 * The default value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected static final Object IMPLEMENTATION_EDEFAULT = ModelFactory.eINSTANCE.createFromString(ModelPackage.eINSTANCE.getTImplementation(), "##unspecified");

	/**
	 * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected Object implementation = IMPLEMENTATION_EDEFAULT;

	/**
	 * This is true if the Implementation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean implementationESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TUserTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TUSER_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TRendering> getRendering() {
		if (rendering == null) {
			rendering = new EObjectContainmentEList<TRendering>(TRendering.class, this, ModelPackage.TUSER_TASK__RENDERING);
		}
		return rendering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImplementation() {
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementation(Object newImplementation) {
		Object oldImplementation = implementation;
		implementation = newImplementation;
		boolean oldImplementationESet = implementationESet;
		implementationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TUSER_TASK__IMPLEMENTATION, oldImplementation, implementation, !oldImplementationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetImplementation() {
		Object oldImplementation = implementation;
		boolean oldImplementationESet = implementationESet;
		implementation = IMPLEMENTATION_EDEFAULT;
		implementationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TUSER_TASK__IMPLEMENTATION, oldImplementation, IMPLEMENTATION_EDEFAULT, oldImplementationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetImplementation() {
		return implementationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TUSER_TASK__RENDERING:
				return ((InternalEList<?>)getRendering()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.TUSER_TASK__RENDERING:
				return getRendering();
			case ModelPackage.TUSER_TASK__IMPLEMENTATION:
				return getImplementation();
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
			case ModelPackage.TUSER_TASK__RENDERING:
				getRendering().clear();
				getRendering().addAll((Collection<? extends TRendering>)newValue);
				return;
			case ModelPackage.TUSER_TASK__IMPLEMENTATION:
				setImplementation(newValue);
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
			case ModelPackage.TUSER_TASK__RENDERING:
				getRendering().clear();
				return;
			case ModelPackage.TUSER_TASK__IMPLEMENTATION:
				unsetImplementation();
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
			case ModelPackage.TUSER_TASK__RENDERING:
				return rendering != null && !rendering.isEmpty();
			case ModelPackage.TUSER_TASK__IMPLEMENTATION:
				return isSetImplementation();
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
		result.append(" (implementation: ");
		if (implementationESet) result.append(implementation); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TUserTaskImpl
