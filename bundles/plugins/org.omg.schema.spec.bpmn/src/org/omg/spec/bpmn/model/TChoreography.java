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
 * A representation of the model object '<em><b>TChoreography</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TChoreography#getFlowElementGroup <em>Flow Element Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TChoreography#getFlowElement <em>Flow Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTChoreography()
 * @model extendedMetaData="name='tChoreography' kind='elementOnly'"
 * @generated
 */
public interface TChoreography extends TCollaboration {
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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTChoreography_FlowElementGroup()
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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTChoreography_FlowElement()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='flowElement' namespace='##targetNamespace' group='flowElement:group'"
	 * @generated
	 */
	EList<TFlowElement> getFlowElement();

} // TChoreography
