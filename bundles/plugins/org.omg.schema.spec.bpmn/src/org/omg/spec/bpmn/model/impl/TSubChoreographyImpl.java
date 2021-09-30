/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TArtifact;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TSubChoreography;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TSub Choreography</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSubChoreographyImpl#getFlowElementGroup <em>Flow Element Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSubChoreographyImpl#getFlowElement <em>Flow Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSubChoreographyImpl#getArtifactGroup <em>Artifact Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSubChoreographyImpl#getArtifact <em>Artifact</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TSubChoreographyImpl extends TChoreographyActivityImpl implements TSubChoreography {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TSubChoreographyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TSUB_CHOREOGRAPHY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getFlowElementGroup() {
		if (flowElementGroup == null) {
			flowElementGroup = new BasicFeatureMap(this, ModelPackage.TSUB_CHOREOGRAPHY__FLOW_ELEMENT_GROUP);
		}
		return flowElementGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TFlowElement> getFlowElement() {
		return getFlowElementGroup().list(ModelPackage.Literals.TSUB_CHOREOGRAPHY__FLOW_ELEMENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getArtifactGroup() {
		if (artifactGroup == null) {
			artifactGroup = new BasicFeatureMap(this, ModelPackage.TSUB_CHOREOGRAPHY__ARTIFACT_GROUP);
		}
		return artifactGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TArtifact> getArtifact() {
		return getArtifactGroup().list(ModelPackage.Literals.TSUB_CHOREOGRAPHY__ARTIFACT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TSUB_CHOREOGRAPHY__FLOW_ELEMENT_GROUP:
				return ((InternalEList<?>)getFlowElementGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TSUB_CHOREOGRAPHY__FLOW_ELEMENT:
				return ((InternalEList<?>)getFlowElement()).basicRemove(otherEnd, msgs);
			case ModelPackage.TSUB_CHOREOGRAPHY__ARTIFACT_GROUP:
				return ((InternalEList<?>)getArtifactGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TSUB_CHOREOGRAPHY__ARTIFACT:
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
			case ModelPackage.TSUB_CHOREOGRAPHY__FLOW_ELEMENT_GROUP:
				if (coreType) return getFlowElementGroup();
				return ((FeatureMap.Internal)getFlowElementGroup()).getWrapper();
			case ModelPackage.TSUB_CHOREOGRAPHY__FLOW_ELEMENT:
				return getFlowElement();
			case ModelPackage.TSUB_CHOREOGRAPHY__ARTIFACT_GROUP:
				if (coreType) return getArtifactGroup();
				return ((FeatureMap.Internal)getArtifactGroup()).getWrapper();
			case ModelPackage.TSUB_CHOREOGRAPHY__ARTIFACT:
				return getArtifact();
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
			case ModelPackage.TSUB_CHOREOGRAPHY__FLOW_ELEMENT_GROUP:
				((FeatureMap.Internal)getFlowElementGroup()).set(newValue);
				return;
			case ModelPackage.TSUB_CHOREOGRAPHY__FLOW_ELEMENT:
				getFlowElement().clear();
				getFlowElement().addAll((Collection<? extends TFlowElement>)newValue);
				return;
			case ModelPackage.TSUB_CHOREOGRAPHY__ARTIFACT_GROUP:
				((FeatureMap.Internal)getArtifactGroup()).set(newValue);
				return;
			case ModelPackage.TSUB_CHOREOGRAPHY__ARTIFACT:
				getArtifact().clear();
				getArtifact().addAll((Collection<? extends TArtifact>)newValue);
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
			case ModelPackage.TSUB_CHOREOGRAPHY__FLOW_ELEMENT_GROUP:
				getFlowElementGroup().clear();
				return;
			case ModelPackage.TSUB_CHOREOGRAPHY__FLOW_ELEMENT:
				getFlowElement().clear();
				return;
			case ModelPackage.TSUB_CHOREOGRAPHY__ARTIFACT_GROUP:
				getArtifactGroup().clear();
				return;
			case ModelPackage.TSUB_CHOREOGRAPHY__ARTIFACT:
				getArtifact().clear();
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
			case ModelPackage.TSUB_CHOREOGRAPHY__FLOW_ELEMENT_GROUP:
				return flowElementGroup != null && !flowElementGroup.isEmpty();
			case ModelPackage.TSUB_CHOREOGRAPHY__FLOW_ELEMENT:
				return !getFlowElement().isEmpty();
			case ModelPackage.TSUB_CHOREOGRAPHY__ARTIFACT_GROUP:
				return artifactGroup != null && !artifactGroup.isEmpty();
			case ModelPackage.TSUB_CHOREOGRAPHY__ARTIFACT:
				return !getArtifact().isEmpty();
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
		result.append(')');
		return result.toString();
	}

} //TSubChoreographyImpl
