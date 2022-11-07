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

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TAssignment;
import org.omg.spec.bpmn.model.TDataAssociation;
import org.omg.spec.bpmn.model.TFormalExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TData Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDataAssociationImpl#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDataAssociationImpl#getTargetRef <em>Target Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDataAssociationImpl#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDataAssociationImpl#getAssignment <em>Assignment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TDataAssociationImpl extends TBaseElementImpl implements TDataAssociation {
	/**
	 * The cached value of the '{@link #getSourceRef() <em>Source Ref</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceRef()
	 * @generated
	 * @ordered
	 */
	protected EList<String> sourceRef;

	/**
	 * The default value of the '{@link #getTargetRef() <em>Target Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetRef()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetRef() <em>Target Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetRef()
	 * @generated
	 * @ordered
	 */
	protected String targetRef = TARGET_REF_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTransformation() <em>Transformation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformation()
	 * @generated
	 * @ordered
	 */
	protected TFormalExpression transformation;

	/**
	 * The cached value of the '{@link #getAssignment() <em>Assignment</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignment()
	 * @generated
	 * @ordered
	 */
	protected EList<TAssignment> assignment;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TDataAssociationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TDATA_ASSOCIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getSourceRef() {
		if (sourceRef == null) {
			sourceRef = new EDataTypeEList<String>(String.class, this, ModelPackage.TDATA_ASSOCIATION__SOURCE_REF);
		}
		return sourceRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetRef() {
		return targetRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetRef(String newTargetRef) {
		String oldTargetRef = targetRef;
		targetRef = newTargetRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDATA_ASSOCIATION__TARGET_REF, oldTargetRef, targetRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFormalExpression getTransformation() {
		return transformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransformation(TFormalExpression newTransformation, NotificationChain msgs) {
		TFormalExpression oldTransformation = transformation;
		transformation = newTransformation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TDATA_ASSOCIATION__TRANSFORMATION, oldTransformation, newTransformation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransformation(TFormalExpression newTransformation) {
		if (newTransformation != transformation) {
			NotificationChain msgs = null;
			if (transformation != null)
				msgs = ((InternalEObject)transformation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TDATA_ASSOCIATION__TRANSFORMATION, null, msgs);
			if (newTransformation != null)
				msgs = ((InternalEObject)newTransformation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TDATA_ASSOCIATION__TRANSFORMATION, null, msgs);
			msgs = basicSetTransformation(newTransformation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDATA_ASSOCIATION__TRANSFORMATION, newTransformation, newTransformation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TAssignment> getAssignment() {
		if (assignment == null) {
			assignment = new EObjectContainmentEList<TAssignment>(TAssignment.class, this, ModelPackage.TDATA_ASSOCIATION__ASSIGNMENT);
		}
		return assignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TDATA_ASSOCIATION__TRANSFORMATION:
				return basicSetTransformation(null, msgs);
			case ModelPackage.TDATA_ASSOCIATION__ASSIGNMENT:
				return ((InternalEList<?>)getAssignment()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.TDATA_ASSOCIATION__SOURCE_REF:
				return getSourceRef();
			case ModelPackage.TDATA_ASSOCIATION__TARGET_REF:
				return getTargetRef();
			case ModelPackage.TDATA_ASSOCIATION__TRANSFORMATION:
				return getTransformation();
			case ModelPackage.TDATA_ASSOCIATION__ASSIGNMENT:
				return getAssignment();
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
			case ModelPackage.TDATA_ASSOCIATION__SOURCE_REF:
				getSourceRef().clear();
				getSourceRef().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.TDATA_ASSOCIATION__TARGET_REF:
				setTargetRef((String)newValue);
				return;
			case ModelPackage.TDATA_ASSOCIATION__TRANSFORMATION:
				setTransformation((TFormalExpression)newValue);
				return;
			case ModelPackage.TDATA_ASSOCIATION__ASSIGNMENT:
				getAssignment().clear();
				getAssignment().addAll((Collection<? extends TAssignment>)newValue);
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
			case ModelPackage.TDATA_ASSOCIATION__SOURCE_REF:
				getSourceRef().clear();
				return;
			case ModelPackage.TDATA_ASSOCIATION__TARGET_REF:
				setTargetRef(TARGET_REF_EDEFAULT);
				return;
			case ModelPackage.TDATA_ASSOCIATION__TRANSFORMATION:
				setTransformation((TFormalExpression)null);
				return;
			case ModelPackage.TDATA_ASSOCIATION__ASSIGNMENT:
				getAssignment().clear();
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
			case ModelPackage.TDATA_ASSOCIATION__SOURCE_REF:
				return sourceRef != null && !sourceRef.isEmpty();
			case ModelPackage.TDATA_ASSOCIATION__TARGET_REF:
				return TARGET_REF_EDEFAULT == null ? targetRef != null : !TARGET_REF_EDEFAULT.equals(targetRef);
			case ModelPackage.TDATA_ASSOCIATION__TRANSFORMATION:
				return transformation != null;
			case ModelPackage.TDATA_ASSOCIATION__ASSIGNMENT:
				return assignment != null && !assignment.isEmpty();
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
		result.append(" (sourceRef: ");
		result.append(sourceRef);
		result.append(", targetRef: ");
		result.append(targetRef);
		result.append(')');
		return result.toString();
	}

} //TDataAssociationImpl
