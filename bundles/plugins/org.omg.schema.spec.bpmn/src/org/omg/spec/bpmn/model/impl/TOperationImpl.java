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
import org.omg.spec.bpmn.model.TOperation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TOperation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TOperationImpl#getInMessageRef <em>In Message Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TOperationImpl#getOutMessageRef <em>Out Message Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TOperationImpl#getErrorRef <em>Error Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TOperationImpl#getImplementationRef <em>Implementation Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TOperationImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TOperationImpl extends TBaseElementImpl implements TOperation {
	/**
	 * The default value of the '{@link #getInMessageRef() <em>In Message Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInMessageRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName IN_MESSAGE_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInMessageRef() <em>In Message Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInMessageRef()
	 * @generated
	 * @ordered
	 */
	protected QName inMessageRef = IN_MESSAGE_REF_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutMessageRef() <em>Out Message Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutMessageRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName OUT_MESSAGE_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutMessageRef() <em>Out Message Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutMessageRef()
	 * @generated
	 * @ordered
	 */
	protected QName outMessageRef = OUT_MESSAGE_REF_EDEFAULT;

	/**
	 * The cached value of the '{@link #getErrorRef() <em>Error Ref</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorRef()
	 * @generated
	 * @ordered
	 */
	protected EList<QName> errorRef;

	/**
	 * The default value of the '{@link #getImplementationRef() <em>Implementation Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName IMPLEMENTATION_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImplementationRef() <em>Implementation Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationRef()
	 * @generated
	 * @ordered
	 */
	protected QName implementationRef = IMPLEMENTATION_REF_EDEFAULT;

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
	protected TOperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TOPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getInMessageRef() {
		return inMessageRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInMessageRef(QName newInMessageRef) {
		QName oldInMessageRef = inMessageRef;
		inMessageRef = newInMessageRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPERATION__IN_MESSAGE_REF, oldInMessageRef, inMessageRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getOutMessageRef() {
		return outMessageRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutMessageRef(QName newOutMessageRef) {
		QName oldOutMessageRef = outMessageRef;
		outMessageRef = newOutMessageRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPERATION__OUT_MESSAGE_REF, oldOutMessageRef, outMessageRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<QName> getErrorRef() {
		if (errorRef == null) {
			errorRef = new EDataTypeEList<QName>(QName.class, this, ModelPackage.TOPERATION__ERROR_REF);
		}
		return errorRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getImplementationRef() {
		return implementationRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementationRef(QName newImplementationRef) {
		QName oldImplementationRef = implementationRef;
		implementationRef = newImplementationRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPERATION__IMPLEMENTATION_REF, oldImplementationRef, implementationRef));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPERATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TOPERATION__IN_MESSAGE_REF:
				return getInMessageRef();
			case ModelPackage.TOPERATION__OUT_MESSAGE_REF:
				return getOutMessageRef();
			case ModelPackage.TOPERATION__ERROR_REF:
				return getErrorRef();
			case ModelPackage.TOPERATION__IMPLEMENTATION_REF:
				return getImplementationRef();
			case ModelPackage.TOPERATION__NAME:
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
			case ModelPackage.TOPERATION__IN_MESSAGE_REF:
				setInMessageRef((QName)newValue);
				return;
			case ModelPackage.TOPERATION__OUT_MESSAGE_REF:
				setOutMessageRef((QName)newValue);
				return;
			case ModelPackage.TOPERATION__ERROR_REF:
				getErrorRef().clear();
				getErrorRef().addAll((Collection<? extends QName>)newValue);
				return;
			case ModelPackage.TOPERATION__IMPLEMENTATION_REF:
				setImplementationRef((QName)newValue);
				return;
			case ModelPackage.TOPERATION__NAME:
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
			case ModelPackage.TOPERATION__IN_MESSAGE_REF:
				setInMessageRef(IN_MESSAGE_REF_EDEFAULT);
				return;
			case ModelPackage.TOPERATION__OUT_MESSAGE_REF:
				setOutMessageRef(OUT_MESSAGE_REF_EDEFAULT);
				return;
			case ModelPackage.TOPERATION__ERROR_REF:
				getErrorRef().clear();
				return;
			case ModelPackage.TOPERATION__IMPLEMENTATION_REF:
				setImplementationRef(IMPLEMENTATION_REF_EDEFAULT);
				return;
			case ModelPackage.TOPERATION__NAME:
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
			case ModelPackage.TOPERATION__IN_MESSAGE_REF:
				return IN_MESSAGE_REF_EDEFAULT == null ? inMessageRef != null : !IN_MESSAGE_REF_EDEFAULT.equals(inMessageRef);
			case ModelPackage.TOPERATION__OUT_MESSAGE_REF:
				return OUT_MESSAGE_REF_EDEFAULT == null ? outMessageRef != null : !OUT_MESSAGE_REF_EDEFAULT.equals(outMessageRef);
			case ModelPackage.TOPERATION__ERROR_REF:
				return errorRef != null && !errorRef.isEmpty();
			case ModelPackage.TOPERATION__IMPLEMENTATION_REF:
				return IMPLEMENTATION_REF_EDEFAULT == null ? implementationRef != null : !IMPLEMENTATION_REF_EDEFAULT.equals(implementationRef);
			case ModelPackage.TOPERATION__NAME:
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
		result.append(" (inMessageRef: ");
		result.append(inMessageRef);
		result.append(", outMessageRef: ");
		result.append(outMessageRef);
		result.append(", errorRef: ");
		result.append(errorRef);
		result.append(", implementationRef: ");
		result.append(implementationRef);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TOperationImpl
