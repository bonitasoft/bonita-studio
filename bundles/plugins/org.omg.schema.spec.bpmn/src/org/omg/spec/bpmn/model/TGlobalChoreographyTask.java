/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TGlobal Choreography Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TGlobalChoreographyTask#getInitiatingParticipantRef <em>Initiating Participant Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTGlobalChoreographyTask()
 * @model extendedMetaData="name='tGlobalChoreographyTask' kind='elementOnly'"
 * @generated
 */
public interface TGlobalChoreographyTask extends TChoreography {
	/**
	 * Returns the value of the '<em><b>Initiating Participant Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initiating Participant Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initiating Participant Ref</em>' attribute.
	 * @see #setInitiatingParticipantRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTGlobalChoreographyTask_InitiatingParticipantRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='initiatingParticipantRef'"
	 * @generated
	 */
	QName getInitiatingParticipantRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TGlobalChoreographyTask#getInitiatingParticipantRef <em>Initiating Participant Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initiating Participant Ref</em>' attribute.
	 * @see #getInitiatingParticipantRef()
	 * @generated
	 */
	void setInitiatingParticipantRef(QName value);

} // TGlobalChoreographyTask
