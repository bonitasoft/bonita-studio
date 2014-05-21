/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import java.util.Collection;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TResourceAssignmentExpression;
import org.omg.spec.bpmn.model.TResourceParameterBinding;
import org.omg.spec.bpmn.model.TResourceRole;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TResource Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TResourceRoleImpl#getResourceRef <em>Resource Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TResourceRoleImpl#getResourceParameterBinding <em>Resource Parameter Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TResourceRoleImpl#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TResourceRoleImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TResourceRoleImpl extends TBaseElementImpl implements TResourceRole {
	/**
	 * The default value of the '{@link #getResourceRef() <em>Resource Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName RESOURCE_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceRef() <em>Resource Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceRef()
	 * @generated
	 * @ordered
	 */
	protected QName resourceRef = RESOURCE_REF_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResourceParameterBinding() <em>Resource Parameter Binding</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceParameterBinding()
	 * @generated
	 * @ordered
	 */
	protected EList<TResourceParameterBinding> resourceParameterBinding;

	/**
	 * The cached value of the '{@link #getResourceAssignmentExpression() <em>Resource Assignment Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceAssignmentExpression()
	 * @generated
	 * @ordered
	 */
	protected TResourceAssignmentExpression resourceAssignmentExpression;

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
	protected TResourceRoleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TRESOURCE_ROLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getResourceRef() {
		return resourceRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceRef(QName newResourceRef) {
		QName oldResourceRef = resourceRef;
		resourceRef = newResourceRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRESOURCE_ROLE__RESOURCE_REF, oldResourceRef, resourceRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TResourceParameterBinding> getResourceParameterBinding() {
		if (resourceParameterBinding == null) {
			resourceParameterBinding = new EObjectContainmentEList<TResourceParameterBinding>(TResourceParameterBinding.class, this, ModelPackage.TRESOURCE_ROLE__RESOURCE_PARAMETER_BINDING);
		}
		return resourceParameterBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TResourceAssignmentExpression getResourceAssignmentExpression() {
		return resourceAssignmentExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResourceAssignmentExpression(TResourceAssignmentExpression newResourceAssignmentExpression, NotificationChain msgs) {
		TResourceAssignmentExpression oldResourceAssignmentExpression = resourceAssignmentExpression;
		resourceAssignmentExpression = newResourceAssignmentExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TRESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION, oldResourceAssignmentExpression, newResourceAssignmentExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceAssignmentExpression(TResourceAssignmentExpression newResourceAssignmentExpression) {
		if (newResourceAssignmentExpression != resourceAssignmentExpression) {
			NotificationChain msgs = null;
			if (resourceAssignmentExpression != null)
				msgs = ((InternalEObject)resourceAssignmentExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TRESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION, null, msgs);
			if (newResourceAssignmentExpression != null)
				msgs = ((InternalEObject)newResourceAssignmentExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TRESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION, null, msgs);
			msgs = basicSetResourceAssignmentExpression(newResourceAssignmentExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION, newResourceAssignmentExpression, newResourceAssignmentExpression));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRESOURCE_ROLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_PARAMETER_BINDING:
				return ((InternalEList<?>)getResourceParameterBinding()).basicRemove(otherEnd, msgs);
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION:
				return basicSetResourceAssignmentExpression(null, msgs);
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
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_REF:
				return getResourceRef();
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_PARAMETER_BINDING:
				return getResourceParameterBinding();
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION:
				return getResourceAssignmentExpression();
			case ModelPackage.TRESOURCE_ROLE__NAME:
				return getName();
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
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_REF:
				setResourceRef((QName)newValue);
				return;
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_PARAMETER_BINDING:
				getResourceParameterBinding().clear();
				getResourceParameterBinding().addAll((Collection<? extends TResourceParameterBinding>)newValue);
				return;
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION:
				setResourceAssignmentExpression((TResourceAssignmentExpression)newValue);
				return;
			case ModelPackage.TRESOURCE_ROLE__NAME:
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
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_REF:
				setResourceRef(RESOURCE_REF_EDEFAULT);
				return;
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_PARAMETER_BINDING:
				getResourceParameterBinding().clear();
				return;
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION:
				setResourceAssignmentExpression((TResourceAssignmentExpression)null);
				return;
			case ModelPackage.TRESOURCE_ROLE__NAME:
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
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_REF:
				return RESOURCE_REF_EDEFAULT == null ? resourceRef != null : !RESOURCE_REF_EDEFAULT.equals(resourceRef);
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_PARAMETER_BINDING:
				return resourceParameterBinding != null && !resourceParameterBinding.isEmpty();
			case ModelPackage.TRESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION:
				return resourceAssignmentExpression != null;
			case ModelPackage.TRESOURCE_ROLE__NAME:
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
		result.append(" (resourceRef: ");
		result.append(resourceRef);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TResourceRoleImpl
