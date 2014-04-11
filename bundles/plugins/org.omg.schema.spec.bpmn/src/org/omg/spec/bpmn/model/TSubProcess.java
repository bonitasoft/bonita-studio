/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TSub Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TSubProcess#getLaneSet <em>Lane Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubProcess#getFlowElementGroup <em>Flow Element Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubProcess#getFlowElement <em>Flow Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubProcess#getArtifactGroup <em>Artifact Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubProcess#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubProcess#isTriggeredByEvent <em>Triggered By Event</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess()
 * @model extendedMetaData="name='tSubProcess' kind='elementOnly'"
 * @generated
 */
public interface TSubProcess extends TActivity {
	/**
	 * Returns the value of the '<em><b>Lane Set</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TLaneSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lane Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lane Set</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess_LaneSet()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='laneSet' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TLaneSet> getLaneSet();

	/**
	 * Returns the value of the '<em><b>Flow Element Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flow Element Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flow Element Group</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess_FlowElementGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='flowElement:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getFlowElementGroup();

	/**
	 * Returns the value of the '<em><b>Flow Element</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TFlowElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flow Element</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flow Element</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess_FlowElement()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='flowElement' namespace='##targetNamespace' group='flowElement:group'"
	 * @generated
	 */
	EList<TFlowElement> getFlowElement();

	/**
	 * Returns the value of the '<em><b>Artifact Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifact Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact Group</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess_ArtifactGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='artifact:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getArtifactGroup();

	/**
	 * Returns the value of the '<em><b>Artifact</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TArtifact}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifact</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess_Artifact()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='artifact' namespace='##targetNamespace' group='artifact:group'"
	 * @generated
	 */
	EList<TArtifact> getArtifact();

	/**
	 * Returns the value of the '<em><b>Triggered By Event</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Triggered By Event</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Triggered By Event</em>' attribute.
	 * @see #isSetTriggeredByEvent()
	 * @see #unsetTriggeredByEvent()
	 * @see #setTriggeredByEvent(boolean)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess_TriggeredByEvent()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='triggeredByEvent'"
	 * @generated
	 */
	boolean isTriggeredByEvent();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TSubProcess#isTriggeredByEvent <em>Triggered By Event</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Triggered By Event</em>' attribute.
	 * @see #isSetTriggeredByEvent()
	 * @see #unsetTriggeredByEvent()
	 * @see #isTriggeredByEvent()
	 * @generated
	 */
	void setTriggeredByEvent(boolean value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TSubProcess#isTriggeredByEvent <em>Triggered By Event</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTriggeredByEvent()
	 * @see #isTriggeredByEvent()
	 * @see #setTriggeredByEvent(boolean)
	 * @generated
	 */
	void unsetTriggeredByEvent();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TSubProcess#isTriggeredByEvent <em>Triggered By Event</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Triggered By Event</em>' attribute is set.
	 * @see #unsetTriggeredByEvent()
	 * @see #isTriggeredByEvent()
	 * @see #setTriggeredByEvent(boolean)
	 * @generated
	 */
	boolean isSetTriggeredByEvent();

} // TSubProcess
