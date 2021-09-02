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

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TParticipant;
import org.omg.spec.bpmn.model.TParticipantMultiplicity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TParticipant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TParticipantImpl#getInterfaceRef <em>Interface Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TParticipantImpl#getEndPointRef <em>End Point Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TParticipantImpl#getParticipantMultiplicity <em>Participant Multiplicity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TParticipantImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TParticipantImpl#getProcessRef <em>Process Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TParticipantImpl extends TBaseElementImpl implements TParticipant {
	/**
	 * The cached value of the '{@link #getInterfaceRef() <em>Interface Ref</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaceRef()
	 * @generated
	 * @ordered
	 */
	protected EList<QName> interfaceRef;

	/**
	 * The cached value of the '{@link #getEndPointRef() <em>End Point Ref</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndPointRef()
	 * @generated
	 * @ordered
	 */
	protected EList<QName> endPointRef;

	/**
	 * The cached value of the '{@link #getParticipantMultiplicity() <em>Participant Multiplicity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipantMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected TParticipantMultiplicity participantMultiplicity;

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
	 * The default value of the '{@link #getProcessRef() <em>Process Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName PROCESS_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProcessRef() <em>Process Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessRef()
	 * @generated
	 * @ordered
	 */
	protected QName processRef = PROCESS_REF_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TParticipantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TPARTICIPANT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<QName> getInterfaceRef() {
		if (interfaceRef == null) {
			interfaceRef = new EDataTypeEList<QName>(QName.class, this, ModelPackage.TPARTICIPANT__INTERFACE_REF);
		}
		return interfaceRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<QName> getEndPointRef() {
		if (endPointRef == null) {
			endPointRef = new EDataTypeEList<QName>(QName.class, this, ModelPackage.TPARTICIPANT__END_POINT_REF);
		}
		return endPointRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TParticipantMultiplicity getParticipantMultiplicity() {
		return participantMultiplicity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParticipantMultiplicity(TParticipantMultiplicity newParticipantMultiplicity, NotificationChain msgs) {
		TParticipantMultiplicity oldParticipantMultiplicity = participantMultiplicity;
		participantMultiplicity = newParticipantMultiplicity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TPARTICIPANT__PARTICIPANT_MULTIPLICITY, oldParticipantMultiplicity, newParticipantMultiplicity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipantMultiplicity(TParticipantMultiplicity newParticipantMultiplicity) {
		if (newParticipantMultiplicity != participantMultiplicity) {
			NotificationChain msgs = null;
			if (participantMultiplicity != null)
				msgs = ((InternalEObject)participantMultiplicity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TPARTICIPANT__PARTICIPANT_MULTIPLICITY, null, msgs);
			if (newParticipantMultiplicity != null)
				msgs = ((InternalEObject)newParticipantMultiplicity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TPARTICIPANT__PARTICIPANT_MULTIPLICITY, null, msgs);
			msgs = basicSetParticipantMultiplicity(newParticipantMultiplicity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TPARTICIPANT__PARTICIPANT_MULTIPLICITY, newParticipantMultiplicity, newParticipantMultiplicity));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TPARTICIPANT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getProcessRef() {
		return processRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessRef(QName newProcessRef) {
		QName oldProcessRef = processRef;
		processRef = newProcessRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TPARTICIPANT__PROCESS_REF, oldProcessRef, processRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TPARTICIPANT__PARTICIPANT_MULTIPLICITY:
				return basicSetParticipantMultiplicity(null, msgs);
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
			case ModelPackage.TPARTICIPANT__INTERFACE_REF:
				return getInterfaceRef();
			case ModelPackage.TPARTICIPANT__END_POINT_REF:
				return getEndPointRef();
			case ModelPackage.TPARTICIPANT__PARTICIPANT_MULTIPLICITY:
				return getParticipantMultiplicity();
			case ModelPackage.TPARTICIPANT__NAME:
				return getName();
			case ModelPackage.TPARTICIPANT__PROCESS_REF:
				return getProcessRef();
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
			case ModelPackage.TPARTICIPANT__INTERFACE_REF:
				getInterfaceRef().clear();
				getInterfaceRef().addAll((Collection<? extends QName>)newValue);
				return;
			case ModelPackage.TPARTICIPANT__END_POINT_REF:
				getEndPointRef().clear();
				getEndPointRef().addAll((Collection<? extends QName>)newValue);
				return;
			case ModelPackage.TPARTICIPANT__PARTICIPANT_MULTIPLICITY:
				setParticipantMultiplicity((TParticipantMultiplicity)newValue);
				return;
			case ModelPackage.TPARTICIPANT__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.TPARTICIPANT__PROCESS_REF:
				setProcessRef((QName)newValue);
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
			case ModelPackage.TPARTICIPANT__INTERFACE_REF:
				getInterfaceRef().clear();
				return;
			case ModelPackage.TPARTICIPANT__END_POINT_REF:
				getEndPointRef().clear();
				return;
			case ModelPackage.TPARTICIPANT__PARTICIPANT_MULTIPLICITY:
				setParticipantMultiplicity((TParticipantMultiplicity)null);
				return;
			case ModelPackage.TPARTICIPANT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.TPARTICIPANT__PROCESS_REF:
				setProcessRef(PROCESS_REF_EDEFAULT);
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
			case ModelPackage.TPARTICIPANT__INTERFACE_REF:
				return interfaceRef != null && !interfaceRef.isEmpty();
			case ModelPackage.TPARTICIPANT__END_POINT_REF:
				return endPointRef != null && !endPointRef.isEmpty();
			case ModelPackage.TPARTICIPANT__PARTICIPANT_MULTIPLICITY:
				return participantMultiplicity != null;
			case ModelPackage.TPARTICIPANT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.TPARTICIPANT__PROCESS_REF:
				return PROCESS_REF_EDEFAULT == null ? processRef != null : !PROCESS_REF_EDEFAULT.equals(processRef);
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
		result.append(" (interfaceRef: ");
		result.append(interfaceRef);
		result.append(", endPointRef: ");
		result.append(endPointRef);
		result.append(", name: ");
		result.append(name);
		result.append(", processRef: ");
		result.append(processRef);
		result.append(')');
		return result.toString();
	}

} //TParticipantImpl
