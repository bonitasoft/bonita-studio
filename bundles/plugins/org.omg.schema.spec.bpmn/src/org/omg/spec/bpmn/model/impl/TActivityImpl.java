/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import java.math.BigInteger;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TDataInputAssociation;
import org.omg.spec.bpmn.model.TDataOutputAssociation;
import org.omg.spec.bpmn.model.TInputOutputSpecification;
import org.omg.spec.bpmn.model.TLoopCharacteristics;
import org.omg.spec.bpmn.model.TProperty;
import org.omg.spec.bpmn.model.TResourceRole;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TActivity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TActivityImpl#getIoSpecification <em>Io Specification</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TActivityImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TActivityImpl#getDataInputAssociation <em>Data Input Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TActivityImpl#getDataOutputAssociation <em>Data Output Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TActivityImpl#getResourceRoleGroup <em>Resource Role Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TActivityImpl#getResourceRole <em>Resource Role</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TActivityImpl#getLoopCharacteristicsGroup <em>Loop Characteristics Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TActivityImpl#getLoopCharacteristics <em>Loop Characteristics</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TActivityImpl#getCompletionQuantity <em>Completion Quantity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TActivityImpl#getDefault <em>Default</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TActivityImpl#isIsForCompensation <em>Is For Compensation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TActivityImpl#getStartQuantity <em>Start Quantity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TActivityImpl extends TFlowNodeImpl implements TActivity {
	/**
	 * The cached value of the '{@link #getIoSpecification() <em>Io Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIoSpecification()
	 * @generated
	 * @ordered
	 */
	protected TInputOutputSpecification ioSpecification;

	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<TProperty> property;

	/**
	 * The cached value of the '{@link #getDataInputAssociation() <em>Data Input Association</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataInputAssociation()
	 * @generated
	 * @ordered
	 */
	protected EList<TDataInputAssociation> dataInputAssociation;

	/**
	 * The cached value of the '{@link #getDataOutputAssociation() <em>Data Output Association</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataOutputAssociation()
	 * @generated
	 * @ordered
	 */
	protected EList<TDataOutputAssociation> dataOutputAssociation;

	/**
	 * The cached value of the '{@link #getResourceRoleGroup() <em>Resource Role Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceRoleGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap resourceRoleGroup;

	/**
	 * The cached value of the '{@link #getLoopCharacteristicsGroup() <em>Loop Characteristics Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopCharacteristicsGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap loopCharacteristicsGroup;

	/**
	 * The default value of the '{@link #getCompletionQuantity() <em>Completion Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompletionQuantity()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger COMPLETION_QUANTITY_EDEFAULT = new BigInteger("1");

	/**
	 * The cached value of the '{@link #getCompletionQuantity() <em>Completion Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompletionQuantity()
	 * @generated
	 * @ordered
	 */
	protected BigInteger completionQuantity = COMPLETION_QUANTITY_EDEFAULT;

	/**
	 * This is true if the Completion Quantity attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean completionQuantityESet;

	/**
	 * The default value of the '{@link #getDefault() <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefault()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefault() <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefault()
	 * @generated
	 * @ordered
	 */
	protected String default_ = DEFAULT_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsForCompensation() <em>Is For Compensation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsForCompensation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_FOR_COMPENSATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsForCompensation() <em>Is For Compensation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsForCompensation()
	 * @generated
	 * @ordered
	 */
	protected boolean isForCompensation = IS_FOR_COMPENSATION_EDEFAULT;

	/**
	 * This is true if the Is For Compensation attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isForCompensationESet;

	/**
	 * The default value of the '{@link #getStartQuantity() <em>Start Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartQuantity()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger START_QUANTITY_EDEFAULT = new BigInteger("1");

	/**
	 * The cached value of the '{@link #getStartQuantity() <em>Start Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartQuantity()
	 * @generated
	 * @ordered
	 */
	protected BigInteger startQuantity = START_QUANTITY_EDEFAULT;

	/**
	 * This is true if the Start Quantity attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean startQuantityESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TInputOutputSpecification getIoSpecification() {
		return ioSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIoSpecification(TInputOutputSpecification newIoSpecification, NotificationChain msgs) {
		TInputOutputSpecification oldIoSpecification = ioSpecification;
		ioSpecification = newIoSpecification;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TACTIVITY__IO_SPECIFICATION, oldIoSpecification, newIoSpecification);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIoSpecification(TInputOutputSpecification newIoSpecification) {
		if (newIoSpecification != ioSpecification) {
			NotificationChain msgs = null;
			if (ioSpecification != null)
				msgs = ((InternalEObject)ioSpecification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TACTIVITY__IO_SPECIFICATION, null, msgs);
			if (newIoSpecification != null)
				msgs = ((InternalEObject)newIoSpecification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TACTIVITY__IO_SPECIFICATION, null, msgs);
			msgs = basicSetIoSpecification(newIoSpecification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TACTIVITY__IO_SPECIFICATION, newIoSpecification, newIoSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TProperty> getProperty() {
		if (property == null) {
			property = new EObjectContainmentEList<TProperty>(TProperty.class, this, ModelPackage.TACTIVITY__PROPERTY);
		}
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TDataInputAssociation> getDataInputAssociation() {
		if (dataInputAssociation == null) {
			dataInputAssociation = new EObjectContainmentEList<TDataInputAssociation>(TDataInputAssociation.class, this, ModelPackage.TACTIVITY__DATA_INPUT_ASSOCIATION);
		}
		return dataInputAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TDataOutputAssociation> getDataOutputAssociation() {
		if (dataOutputAssociation == null) {
			dataOutputAssociation = new EObjectContainmentEList<TDataOutputAssociation>(TDataOutputAssociation.class, this, ModelPackage.TACTIVITY__DATA_OUTPUT_ASSOCIATION);
		}
		return dataOutputAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getResourceRoleGroup() {
		if (resourceRoleGroup == null) {
			resourceRoleGroup = new BasicFeatureMap(this, ModelPackage.TACTIVITY__RESOURCE_ROLE_GROUP);
		}
		return resourceRoleGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TResourceRole> getResourceRole() {
		return getResourceRoleGroup().list(ModelPackage.Literals.TACTIVITY__RESOURCE_ROLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getLoopCharacteristicsGroup() {
		if (loopCharacteristicsGroup == null) {
			loopCharacteristicsGroup = new BasicFeatureMap(this, ModelPackage.TACTIVITY__LOOP_CHARACTERISTICS_GROUP);
		}
		return loopCharacteristicsGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TLoopCharacteristics getLoopCharacteristics() {
		return (TLoopCharacteristics)getLoopCharacteristicsGroup().get(ModelPackage.Literals.TACTIVITY__LOOP_CHARACTERISTICS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoopCharacteristics(TLoopCharacteristics newLoopCharacteristics, NotificationChain msgs) {
		return ((FeatureMap.Internal)getLoopCharacteristicsGroup()).basicAdd(ModelPackage.Literals.TACTIVITY__LOOP_CHARACTERISTICS, newLoopCharacteristics, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoopCharacteristics(TLoopCharacteristics newLoopCharacteristics) {
		((FeatureMap.Internal)getLoopCharacteristicsGroup()).set(ModelPackage.Literals.TACTIVITY__LOOP_CHARACTERISTICS, newLoopCharacteristics);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getCompletionQuantity() {
		return completionQuantity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompletionQuantity(BigInteger newCompletionQuantity) {
		BigInteger oldCompletionQuantity = completionQuantity;
		completionQuantity = newCompletionQuantity;
		boolean oldCompletionQuantityESet = completionQuantityESet;
		completionQuantityESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TACTIVITY__COMPLETION_QUANTITY, oldCompletionQuantity, completionQuantity, !oldCompletionQuantityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetCompletionQuantity() {
		BigInteger oldCompletionQuantity = completionQuantity;
		boolean oldCompletionQuantityESet = completionQuantityESet;
		completionQuantity = COMPLETION_QUANTITY_EDEFAULT;
		completionQuantityESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TACTIVITY__COMPLETION_QUANTITY, oldCompletionQuantity, COMPLETION_QUANTITY_EDEFAULT, oldCompletionQuantityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetCompletionQuantity() {
		return completionQuantityESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefault() {
		return default_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefault(String newDefault) {
		String oldDefault = default_;
		default_ = newDefault;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TACTIVITY__DEFAULT, oldDefault, default_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsForCompensation() {
		return isForCompensation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsForCompensation(boolean newIsForCompensation) {
		boolean oldIsForCompensation = isForCompensation;
		isForCompensation = newIsForCompensation;
		boolean oldIsForCompensationESet = isForCompensationESet;
		isForCompensationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TACTIVITY__IS_FOR_COMPENSATION, oldIsForCompensation, isForCompensation, !oldIsForCompensationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsForCompensation() {
		boolean oldIsForCompensation = isForCompensation;
		boolean oldIsForCompensationESet = isForCompensationESet;
		isForCompensation = IS_FOR_COMPENSATION_EDEFAULT;
		isForCompensationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TACTIVITY__IS_FOR_COMPENSATION, oldIsForCompensation, IS_FOR_COMPENSATION_EDEFAULT, oldIsForCompensationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsForCompensation() {
		return isForCompensationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getStartQuantity() {
		return startQuantity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartQuantity(BigInteger newStartQuantity) {
		BigInteger oldStartQuantity = startQuantity;
		startQuantity = newStartQuantity;
		boolean oldStartQuantityESet = startQuantityESet;
		startQuantityESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TACTIVITY__START_QUANTITY, oldStartQuantity, startQuantity, !oldStartQuantityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetStartQuantity() {
		BigInteger oldStartQuantity = startQuantity;
		boolean oldStartQuantityESet = startQuantityESet;
		startQuantity = START_QUANTITY_EDEFAULT;
		startQuantityESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TACTIVITY__START_QUANTITY, oldStartQuantity, START_QUANTITY_EDEFAULT, oldStartQuantityESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetStartQuantity() {
		return startQuantityESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TACTIVITY__IO_SPECIFICATION:
				return basicSetIoSpecification(null, msgs);
			case ModelPackage.TACTIVITY__PROPERTY:
				return ((InternalEList<?>)getProperty()).basicRemove(otherEnd, msgs);
			case ModelPackage.TACTIVITY__DATA_INPUT_ASSOCIATION:
				return ((InternalEList<?>)getDataInputAssociation()).basicRemove(otherEnd, msgs);
			case ModelPackage.TACTIVITY__DATA_OUTPUT_ASSOCIATION:
				return ((InternalEList<?>)getDataOutputAssociation()).basicRemove(otherEnd, msgs);
			case ModelPackage.TACTIVITY__RESOURCE_ROLE_GROUP:
				return ((InternalEList<?>)getResourceRoleGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TACTIVITY__RESOURCE_ROLE:
				return ((InternalEList<?>)getResourceRole()).basicRemove(otherEnd, msgs);
			case ModelPackage.TACTIVITY__LOOP_CHARACTERISTICS_GROUP:
				return ((InternalEList<?>)getLoopCharacteristicsGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TACTIVITY__LOOP_CHARACTERISTICS:
				return basicSetLoopCharacteristics(null, msgs);
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
			case ModelPackage.TACTIVITY__IO_SPECIFICATION:
				return getIoSpecification();
			case ModelPackage.TACTIVITY__PROPERTY:
				return getProperty();
			case ModelPackage.TACTIVITY__DATA_INPUT_ASSOCIATION:
				return getDataInputAssociation();
			case ModelPackage.TACTIVITY__DATA_OUTPUT_ASSOCIATION:
				return getDataOutputAssociation();
			case ModelPackage.TACTIVITY__RESOURCE_ROLE_GROUP:
				if (coreType) return getResourceRoleGroup();
				return ((FeatureMap.Internal)getResourceRoleGroup()).getWrapper();
			case ModelPackage.TACTIVITY__RESOURCE_ROLE:
				return getResourceRole();
			case ModelPackage.TACTIVITY__LOOP_CHARACTERISTICS_GROUP:
				if (coreType) return getLoopCharacteristicsGroup();
				return ((FeatureMap.Internal)getLoopCharacteristicsGroup()).getWrapper();
			case ModelPackage.TACTIVITY__LOOP_CHARACTERISTICS:
				return getLoopCharacteristics();
			case ModelPackage.TACTIVITY__COMPLETION_QUANTITY:
				return getCompletionQuantity();
			case ModelPackage.TACTIVITY__DEFAULT:
				return getDefault();
			case ModelPackage.TACTIVITY__IS_FOR_COMPENSATION:
				return isIsForCompensation();
			case ModelPackage.TACTIVITY__START_QUANTITY:
				return getStartQuantity();
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
			case ModelPackage.TACTIVITY__IO_SPECIFICATION:
				setIoSpecification((TInputOutputSpecification)newValue);
				return;
			case ModelPackage.TACTIVITY__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection<? extends TProperty>)newValue);
				return;
			case ModelPackage.TACTIVITY__DATA_INPUT_ASSOCIATION:
				getDataInputAssociation().clear();
				getDataInputAssociation().addAll((Collection<? extends TDataInputAssociation>)newValue);
				return;
			case ModelPackage.TACTIVITY__DATA_OUTPUT_ASSOCIATION:
				getDataOutputAssociation().clear();
				getDataOutputAssociation().addAll((Collection<? extends TDataOutputAssociation>)newValue);
				return;
			case ModelPackage.TACTIVITY__RESOURCE_ROLE_GROUP:
				((FeatureMap.Internal)getResourceRoleGroup()).set(newValue);
				return;
			case ModelPackage.TACTIVITY__RESOURCE_ROLE:
				getResourceRole().clear();
				getResourceRole().addAll((Collection<? extends TResourceRole>)newValue);
				return;
			case ModelPackage.TACTIVITY__LOOP_CHARACTERISTICS_GROUP:
				((FeatureMap.Internal)getLoopCharacteristicsGroup()).set(newValue);
				return;
			case ModelPackage.TACTIVITY__LOOP_CHARACTERISTICS:
				setLoopCharacteristics((TLoopCharacteristics)newValue);
				return;
			case ModelPackage.TACTIVITY__COMPLETION_QUANTITY:
				setCompletionQuantity((BigInteger)newValue);
				return;
			case ModelPackage.TACTIVITY__DEFAULT:
				setDefault((String)newValue);
				return;
			case ModelPackage.TACTIVITY__IS_FOR_COMPENSATION:
				setIsForCompensation((Boolean)newValue);
				return;
			case ModelPackage.TACTIVITY__START_QUANTITY:
				setStartQuantity((BigInteger)newValue);
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
			case ModelPackage.TACTIVITY__IO_SPECIFICATION:
				setIoSpecification((TInputOutputSpecification)null);
				return;
			case ModelPackage.TACTIVITY__PROPERTY:
				getProperty().clear();
				return;
			case ModelPackage.TACTIVITY__DATA_INPUT_ASSOCIATION:
				getDataInputAssociation().clear();
				return;
			case ModelPackage.TACTIVITY__DATA_OUTPUT_ASSOCIATION:
				getDataOutputAssociation().clear();
				return;
			case ModelPackage.TACTIVITY__RESOURCE_ROLE_GROUP:
				getResourceRoleGroup().clear();
				return;
			case ModelPackage.TACTIVITY__RESOURCE_ROLE:
				getResourceRole().clear();
				return;
			case ModelPackage.TACTIVITY__LOOP_CHARACTERISTICS_GROUP:
				getLoopCharacteristicsGroup().clear();
				return;
			case ModelPackage.TACTIVITY__LOOP_CHARACTERISTICS:
				setLoopCharacteristics((TLoopCharacteristics)null);
				return;
			case ModelPackage.TACTIVITY__COMPLETION_QUANTITY:
				unsetCompletionQuantity();
				return;
			case ModelPackage.TACTIVITY__DEFAULT:
				setDefault(DEFAULT_EDEFAULT);
				return;
			case ModelPackage.TACTIVITY__IS_FOR_COMPENSATION:
				unsetIsForCompensation();
				return;
			case ModelPackage.TACTIVITY__START_QUANTITY:
				unsetStartQuantity();
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
			case ModelPackage.TACTIVITY__IO_SPECIFICATION:
				return ioSpecification != null;
			case ModelPackage.TACTIVITY__PROPERTY:
				return property != null && !property.isEmpty();
			case ModelPackage.TACTIVITY__DATA_INPUT_ASSOCIATION:
				return dataInputAssociation != null && !dataInputAssociation.isEmpty();
			case ModelPackage.TACTIVITY__DATA_OUTPUT_ASSOCIATION:
				return dataOutputAssociation != null && !dataOutputAssociation.isEmpty();
			case ModelPackage.TACTIVITY__RESOURCE_ROLE_GROUP:
				return resourceRoleGroup != null && !resourceRoleGroup.isEmpty();
			case ModelPackage.TACTIVITY__RESOURCE_ROLE:
				return !getResourceRole().isEmpty();
			case ModelPackage.TACTIVITY__LOOP_CHARACTERISTICS_GROUP:
				return loopCharacteristicsGroup != null && !loopCharacteristicsGroup.isEmpty();
			case ModelPackage.TACTIVITY__LOOP_CHARACTERISTICS:
				return getLoopCharacteristics() != null;
			case ModelPackage.TACTIVITY__COMPLETION_QUANTITY:
				return isSetCompletionQuantity();
			case ModelPackage.TACTIVITY__DEFAULT:
				return DEFAULT_EDEFAULT == null ? default_ != null : !DEFAULT_EDEFAULT.equals(default_);
			case ModelPackage.TACTIVITY__IS_FOR_COMPENSATION:
				return isSetIsForCompensation();
			case ModelPackage.TACTIVITY__START_QUANTITY:
				return isSetStartQuantity();
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
		result.append(" (resourceRoleGroup: ");
		result.append(resourceRoleGroup);
		result.append(", loopCharacteristicsGroup: ");
		result.append(loopCharacteristicsGroup);
		result.append(", completionQuantity: ");
		if (completionQuantityESet) result.append(completionQuantity); else result.append("<unset>");
		result.append(", default: ");
		result.append(default_);
		result.append(", isForCompensation: ");
		if (isForCompensationESet) result.append(isForCompensation); else result.append("<unset>");
		result.append(", startQuantity: ");
		if (startQuantityESet) result.append(startQuantity); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TActivityImpl
