/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TChoreography Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TChoreographyTask#getMessageFlowRef <em>Message Flow Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTChoreographyTask()
 * @model extendedMetaData="name='tChoreographyTask' kind='elementOnly'"
 * @generated
 */
public interface TChoreographyTask extends TChoreographyActivity {
	/**
	 * Returns the value of the '<em><b>Message Flow Ref</b></em>' attribute list.
	 * The list contents are of type {@link javax.xml.namespace.QName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Flow Ref</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Flow Ref</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTChoreographyTask_MessageFlowRef()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName" required="true" upper="2"
	 *        extendedMetaData="kind='element' name='messageFlowRef' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<QName> getMessageFlowRef();

} // TChoreographyTask
