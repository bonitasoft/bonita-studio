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

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TCatchEvent;
import org.omg.spec.bpmn.model.TDataOutput;
import org.omg.spec.bpmn.model.TDataOutputAssociation;
import org.omg.spec.bpmn.model.TEventDefinition;
import org.omg.spec.bpmn.model.TOutputSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TCatch Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCatchEventImpl#getDataOutput <em>Data Output</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCatchEventImpl#getDataOutputAssociation <em>Data Output Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCatchEventImpl#getOutputSet <em>Output Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCatchEventImpl#getEventDefinitionGroup <em>Event Definition Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCatchEventImpl#getEventDefinition <em>Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCatchEventImpl#getEventDefinitionRef <em>Event Definition Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCatchEventImpl#isParallelMultiple <em>Parallel Multiple</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TCatchEventImpl extends TEventImpl implements TCatchEvent {
	/**
	 * The cached value of the '{@link #getDataOutput() <em>Data Output</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataOutput()
	 * @generated
	 * @ordered
	 */
	protected EList<TDataOutput> dataOutput;

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
	 * The cached value of the '{@link #getOutputSet() <em>Output Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputSet()
	 * @generated
	 * @ordered
	 */
	protected TOutputSet outputSet;

	/**
	 * The cached value of the '{@link #getEventDefinitionGroup() <em>Event Definition Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventDefinitionGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap eventDefinitionGroup;

	/**
	 * The cached value of the '{@link #getEventDefinitionRef() <em>Event Definition Ref</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventDefinitionRef()
	 * @generated
	 * @ordered
	 */
	protected EList<QName> eventDefinitionRef;

	/**
	 * The default value of the '{@link #isParallelMultiple() <em>Parallel Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParallelMultiple()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PARALLEL_MULTIPLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isParallelMultiple() <em>Parallel Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParallelMultiple()
	 * @generated
	 * @ordered
	 */
	protected boolean parallelMultiple = PARALLEL_MULTIPLE_EDEFAULT;

	/**
	 * This is true if the Parallel Multiple attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean parallelMultipleESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TCatchEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TCATCH_EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TDataOutput> getDataOutput() {
		if (dataOutput == null) {
			dataOutput = new EObjectContainmentEList<TDataOutput>(TDataOutput.class, this, ModelPackage.TCATCH_EVENT__DATA_OUTPUT);
		}
		return dataOutput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TDataOutputAssociation> getDataOutputAssociation() {
		if (dataOutputAssociation == null) {
			dataOutputAssociation = new EObjectContainmentEList<TDataOutputAssociation>(TDataOutputAssociation.class, this, ModelPackage.TCATCH_EVENT__DATA_OUTPUT_ASSOCIATION);
		}
		return dataOutputAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOutputSet getOutputSet() {
		return outputSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutputSet(TOutputSet newOutputSet, NotificationChain msgs) {
		TOutputSet oldOutputSet = outputSet;
		outputSet = newOutputSet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TCATCH_EVENT__OUTPUT_SET, oldOutputSet, newOutputSet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputSet(TOutputSet newOutputSet) {
		if (newOutputSet != outputSet) {
			NotificationChain msgs = null;
			if (outputSet != null)
				msgs = ((InternalEObject)outputSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TCATCH_EVENT__OUTPUT_SET, null, msgs);
			if (newOutputSet != null)
				msgs = ((InternalEObject)newOutputSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TCATCH_EVENT__OUTPUT_SET, null, msgs);
			msgs = basicSetOutputSet(newOutputSet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCATCH_EVENT__OUTPUT_SET, newOutputSet, newOutputSet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getEventDefinitionGroup() {
		if (eventDefinitionGroup == null) {
			eventDefinitionGroup = new BasicFeatureMap(this, ModelPackage.TCATCH_EVENT__EVENT_DEFINITION_GROUP);
		}
		return eventDefinitionGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TEventDefinition> getEventDefinition() {
		return getEventDefinitionGroup().list(ModelPackage.Literals.TCATCH_EVENT__EVENT_DEFINITION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<QName> getEventDefinitionRef() {
		if (eventDefinitionRef == null) {
			eventDefinitionRef = new EDataTypeEList<QName>(QName.class, this, ModelPackage.TCATCH_EVENT__EVENT_DEFINITION_REF);
		}
		return eventDefinitionRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isParallelMultiple() {
		return parallelMultiple;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParallelMultiple(boolean newParallelMultiple) {
		boolean oldParallelMultiple = parallelMultiple;
		parallelMultiple = newParallelMultiple;
		boolean oldParallelMultipleESet = parallelMultipleESet;
		parallelMultipleESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCATCH_EVENT__PARALLEL_MULTIPLE, oldParallelMultiple, parallelMultiple, !oldParallelMultipleESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetParallelMultiple() {
		boolean oldParallelMultiple = parallelMultiple;
		boolean oldParallelMultipleESet = parallelMultipleESet;
		parallelMultiple = PARALLEL_MULTIPLE_EDEFAULT;
		parallelMultipleESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TCATCH_EVENT__PARALLEL_MULTIPLE, oldParallelMultiple, PARALLEL_MULTIPLE_EDEFAULT, oldParallelMultipleESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetParallelMultiple() {
		return parallelMultipleESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TCATCH_EVENT__DATA_OUTPUT:
				return ((InternalEList<?>)getDataOutput()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCATCH_EVENT__DATA_OUTPUT_ASSOCIATION:
				return ((InternalEList<?>)getDataOutputAssociation()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCATCH_EVENT__OUTPUT_SET:
				return basicSetOutputSet(null, msgs);
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION_GROUP:
				return ((InternalEList<?>)getEventDefinitionGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION:
				return ((InternalEList<?>)getEventDefinition()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.TCATCH_EVENT__DATA_OUTPUT:
				return getDataOutput();
			case ModelPackage.TCATCH_EVENT__DATA_OUTPUT_ASSOCIATION:
				return getDataOutputAssociation();
			case ModelPackage.TCATCH_EVENT__OUTPUT_SET:
				return getOutputSet();
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION_GROUP:
				if (coreType) return getEventDefinitionGroup();
				return ((FeatureMap.Internal)getEventDefinitionGroup()).getWrapper();
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION:
				return getEventDefinition();
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION_REF:
				return getEventDefinitionRef();
			case ModelPackage.TCATCH_EVENT__PARALLEL_MULTIPLE:
				return isParallelMultiple();
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
			case ModelPackage.TCATCH_EVENT__DATA_OUTPUT:
				getDataOutput().clear();
				getDataOutput().addAll((Collection<? extends TDataOutput>)newValue);
				return;
			case ModelPackage.TCATCH_EVENT__DATA_OUTPUT_ASSOCIATION:
				getDataOutputAssociation().clear();
				getDataOutputAssociation().addAll((Collection<? extends TDataOutputAssociation>)newValue);
				return;
			case ModelPackage.TCATCH_EVENT__OUTPUT_SET:
				setOutputSet((TOutputSet)newValue);
				return;
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION_GROUP:
				((FeatureMap.Internal)getEventDefinitionGroup()).set(newValue);
				return;
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION:
				getEventDefinition().clear();
				getEventDefinition().addAll((Collection<? extends TEventDefinition>)newValue);
				return;
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION_REF:
				getEventDefinitionRef().clear();
				getEventDefinitionRef().addAll((Collection<? extends QName>)newValue);
				return;
			case ModelPackage.TCATCH_EVENT__PARALLEL_MULTIPLE:
				setParallelMultiple((Boolean)newValue);
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
			case ModelPackage.TCATCH_EVENT__DATA_OUTPUT:
				getDataOutput().clear();
				return;
			case ModelPackage.TCATCH_EVENT__DATA_OUTPUT_ASSOCIATION:
				getDataOutputAssociation().clear();
				return;
			case ModelPackage.TCATCH_EVENT__OUTPUT_SET:
				setOutputSet((TOutputSet)null);
				return;
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION_GROUP:
				getEventDefinitionGroup().clear();
				return;
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION:
				getEventDefinition().clear();
				return;
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION_REF:
				getEventDefinitionRef().clear();
				return;
			case ModelPackage.TCATCH_EVENT__PARALLEL_MULTIPLE:
				unsetParallelMultiple();
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
			case ModelPackage.TCATCH_EVENT__DATA_OUTPUT:
				return dataOutput != null && !dataOutput.isEmpty();
			case ModelPackage.TCATCH_EVENT__DATA_OUTPUT_ASSOCIATION:
				return dataOutputAssociation != null && !dataOutputAssociation.isEmpty();
			case ModelPackage.TCATCH_EVENT__OUTPUT_SET:
				return outputSet != null;
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION_GROUP:
				return eventDefinitionGroup != null && !eventDefinitionGroup.isEmpty();
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION:
				return !getEventDefinition().isEmpty();
			case ModelPackage.TCATCH_EVENT__EVENT_DEFINITION_REF:
				return eventDefinitionRef != null && !eventDefinitionRef.isEmpty();
			case ModelPackage.TCATCH_EVENT__PARALLEL_MULTIPLE:
				return isSetParallelMultiple();
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
		result.append(" (eventDefinitionGroup: ");
		result.append(eventDefinitionGroup);
		result.append(", eventDefinitionRef: ");
		result.append(eventDefinitionRef);
		result.append(", parallelMultiple: ");
		if (parallelMultipleESet) result.append(parallelMultiple); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TCatchEventImpl
