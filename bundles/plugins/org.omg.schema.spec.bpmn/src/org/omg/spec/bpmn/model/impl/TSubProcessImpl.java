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

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TArtifact;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TLaneSet;
import org.omg.spec.bpmn.model.TSubProcess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TSub Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSubProcessImpl#getLaneSet <em>Lane Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSubProcessImpl#getFlowElementGroup <em>Flow Element Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSubProcessImpl#getFlowElement <em>Flow Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSubProcessImpl#getArtifactGroup <em>Artifact Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSubProcessImpl#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSubProcessImpl#isTriggeredByEvent <em>Triggered By Event</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TSubProcessImpl extends TActivityImpl implements TSubProcess {
	/**
	 * The cached value of the '{@link #getLaneSet() <em>Lane Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLaneSet()
	 * @generated
	 * @ordered
	 */
	protected EList<TLaneSet> laneSet;

	/**
	 * The cached value of the '{@link #getFlowElementGroup() <em>Flow Element Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFlowElementGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap flowElementGroup;

	/**
	 * The cached value of the '{@link #getArtifactGroup() <em>Artifact Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap artifactGroup;

	/**
	 * The default value of the '{@link #isTriggeredByEvent() <em>Triggered By Event</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTriggeredByEvent()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRIGGERED_BY_EVENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTriggeredByEvent() <em>Triggered By Event</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTriggeredByEvent()
	 * @generated
	 * @ordered
	 */
	protected boolean triggeredByEvent = TRIGGERED_BY_EVENT_EDEFAULT;

	/**
	 * This is true if the Triggered By Event attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean triggeredByEventESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TSubProcessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TSUB_PROCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TLaneSet> getLaneSet() {
		if (laneSet == null) {
			laneSet = new EObjectContainmentEList<TLaneSet>(TLaneSet.class, this, ModelPackage.TSUB_PROCESS__LANE_SET);
		}
		return laneSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getFlowElementGroup() {
		if (flowElementGroup == null) {
			flowElementGroup = new BasicFeatureMap(this, ModelPackage.TSUB_PROCESS__FLOW_ELEMENT_GROUP);
		}
		return flowElementGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TFlowElement> getFlowElement() {
		return getFlowElementGroup().list(ModelPackage.Literals.TSUB_PROCESS__FLOW_ELEMENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getArtifactGroup() {
		if (artifactGroup == null) {
			artifactGroup = new BasicFeatureMap(this, ModelPackage.TSUB_PROCESS__ARTIFACT_GROUP);
		}
		return artifactGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TArtifact> getArtifact() {
		return getArtifactGroup().list(ModelPackage.Literals.TSUB_PROCESS__ARTIFACT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTriggeredByEvent() {
		return triggeredByEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTriggeredByEvent(boolean newTriggeredByEvent) {
		boolean oldTriggeredByEvent = triggeredByEvent;
		triggeredByEvent = newTriggeredByEvent;
		boolean oldTriggeredByEventESet = triggeredByEventESet;
		triggeredByEventESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TSUB_PROCESS__TRIGGERED_BY_EVENT, oldTriggeredByEvent, triggeredByEvent, !oldTriggeredByEventESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTriggeredByEvent() {
		boolean oldTriggeredByEvent = triggeredByEvent;
		boolean oldTriggeredByEventESet = triggeredByEventESet;
		triggeredByEvent = TRIGGERED_BY_EVENT_EDEFAULT;
		triggeredByEventESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TSUB_PROCESS__TRIGGERED_BY_EVENT, oldTriggeredByEvent, TRIGGERED_BY_EVENT_EDEFAULT, oldTriggeredByEventESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTriggeredByEvent() {
		return triggeredByEventESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TSUB_PROCESS__LANE_SET:
				return ((InternalEList<?>)getLaneSet()).basicRemove(otherEnd, msgs);
			case ModelPackage.TSUB_PROCESS__FLOW_ELEMENT_GROUP:
				return ((InternalEList<?>)getFlowElementGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TSUB_PROCESS__FLOW_ELEMENT:
				return ((InternalEList<?>)getFlowElement()).basicRemove(otherEnd, msgs);
			case ModelPackage.TSUB_PROCESS__ARTIFACT_GROUP:
				return ((InternalEList<?>)getArtifactGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TSUB_PROCESS__ARTIFACT:
				return ((InternalEList<?>)getArtifact()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.TSUB_PROCESS__LANE_SET:
				return getLaneSet();
			case ModelPackage.TSUB_PROCESS__FLOW_ELEMENT_GROUP:
				if (coreType) return getFlowElementGroup();
				return ((FeatureMap.Internal)getFlowElementGroup()).getWrapper();
			case ModelPackage.TSUB_PROCESS__FLOW_ELEMENT:
				return getFlowElement();
			case ModelPackage.TSUB_PROCESS__ARTIFACT_GROUP:
				if (coreType) return getArtifactGroup();
				return ((FeatureMap.Internal)getArtifactGroup()).getWrapper();
			case ModelPackage.TSUB_PROCESS__ARTIFACT:
				return getArtifact();
			case ModelPackage.TSUB_PROCESS__TRIGGERED_BY_EVENT:
				return isTriggeredByEvent();
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
			case ModelPackage.TSUB_PROCESS__LANE_SET:
				getLaneSet().clear();
				getLaneSet().addAll((Collection<? extends TLaneSet>)newValue);
				return;
			case ModelPackage.TSUB_PROCESS__FLOW_ELEMENT_GROUP:
				((FeatureMap.Internal)getFlowElementGroup()).set(newValue);
				return;
			case ModelPackage.TSUB_PROCESS__FLOW_ELEMENT:
				getFlowElement().clear();
				getFlowElement().addAll((Collection<? extends TFlowElement>)newValue);
				return;
			case ModelPackage.TSUB_PROCESS__ARTIFACT_GROUP:
				((FeatureMap.Internal)getArtifactGroup()).set(newValue);
				return;
			case ModelPackage.TSUB_PROCESS__ARTIFACT:
				getArtifact().clear();
				getArtifact().addAll((Collection<? extends TArtifact>)newValue);
				return;
			case ModelPackage.TSUB_PROCESS__TRIGGERED_BY_EVENT:
				setTriggeredByEvent((Boolean)newValue);
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
			case ModelPackage.TSUB_PROCESS__LANE_SET:
				getLaneSet().clear();
				return;
			case ModelPackage.TSUB_PROCESS__FLOW_ELEMENT_GROUP:
				getFlowElementGroup().clear();
				return;
			case ModelPackage.TSUB_PROCESS__FLOW_ELEMENT:
				getFlowElement().clear();
				return;
			case ModelPackage.TSUB_PROCESS__ARTIFACT_GROUP:
				getArtifactGroup().clear();
				return;
			case ModelPackage.TSUB_PROCESS__ARTIFACT:
				getArtifact().clear();
				return;
			case ModelPackage.TSUB_PROCESS__TRIGGERED_BY_EVENT:
				unsetTriggeredByEvent();
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
			case ModelPackage.TSUB_PROCESS__LANE_SET:
				return laneSet != null && !laneSet.isEmpty();
			case ModelPackage.TSUB_PROCESS__FLOW_ELEMENT_GROUP:
				return flowElementGroup != null && !flowElementGroup.isEmpty();
			case ModelPackage.TSUB_PROCESS__FLOW_ELEMENT:
				return !getFlowElement().isEmpty();
			case ModelPackage.TSUB_PROCESS__ARTIFACT_GROUP:
				return artifactGroup != null && !artifactGroup.isEmpty();
			case ModelPackage.TSUB_PROCESS__ARTIFACT:
				return !getArtifact().isEmpty();
			case ModelPackage.TSUB_PROCESS__TRIGGERED_BY_EVENT:
				return isSetTriggeredByEvent();
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
		result.append(" (flowElementGroup: ");
		result.append(flowElementGroup);
		result.append(", artifactGroup: ");
		result.append(artifactGroup);
		result.append(", triggeredByEvent: ");
		if (triggeredByEventESet) result.append(triggeredByEvent); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TSubProcessImpl
