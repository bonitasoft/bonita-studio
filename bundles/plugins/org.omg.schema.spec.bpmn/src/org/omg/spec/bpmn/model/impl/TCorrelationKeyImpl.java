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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TCorrelationKey;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TCorrelation Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCorrelationKeyImpl#getCorrelationPropertyRef <em>Correlation Property Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCorrelationKeyImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TCorrelationKeyImpl extends TBaseElementImpl implements TCorrelationKey {
	/**
	 * The cached value of the '{@link #getCorrelationPropertyRef() <em>Correlation Property Ref</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationPropertyRef()
	 * @generated
	 * @ordered
	 */
	protected EList<QName> correlationPropertyRef;

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
	protected TCorrelationKeyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TCORRELATION_KEY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<QName> getCorrelationPropertyRef() {
		if (correlationPropertyRef == null) {
			correlationPropertyRef = new EDataTypeEList<QName>(QName.class, this, ModelPackage.TCORRELATION_KEY__CORRELATION_PROPERTY_REF);
		}
		return correlationPropertyRef;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCORRELATION_KEY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TCORRELATION_KEY__CORRELATION_PROPERTY_REF:
				return getCorrelationPropertyRef();
			case ModelPackage.TCORRELATION_KEY__NAME:
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
			case ModelPackage.TCORRELATION_KEY__CORRELATION_PROPERTY_REF:
				getCorrelationPropertyRef().clear();
				getCorrelationPropertyRef().addAll((Collection<? extends QName>)newValue);
				return;
			case ModelPackage.TCORRELATION_KEY__NAME:
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
			case ModelPackage.TCORRELATION_KEY__CORRELATION_PROPERTY_REF:
				getCorrelationPropertyRef().clear();
				return;
			case ModelPackage.TCORRELATION_KEY__NAME:
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
			case ModelPackage.TCORRELATION_KEY__CORRELATION_PROPERTY_REF:
				return correlationPropertyRef != null && !correlationPropertyRef.isEmpty();
			case ModelPackage.TCORRELATION_KEY__NAME:
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
		result.append(" (correlationPropertyRef: ");
		result.append(correlationPropertyRef);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TCorrelationKeyImpl
