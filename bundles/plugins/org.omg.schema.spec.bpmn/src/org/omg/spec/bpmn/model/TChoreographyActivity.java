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
 * A representation of the model object '<em><b>TChoreography Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TChoreographyActivity#getParticipantRef <em>Participant Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TChoreographyActivity#getCorrelationKey <em>Correlation Key</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TChoreographyActivity#getInitiatingParticipantRef <em>Initiating Participant Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TChoreographyActivity#getLoopType <em>Loop Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTChoreographyActivity()
 * @model abstract="true"
 *        extendedMetaData="name='tChoreographyActivity' kind='elementOnly'"
 * @generated
 */
public interface TChoreographyActivity extends TFlowNode {
	/**
	 * Returns the value of the '<em><b>Participant Ref</b></em>' attribute list.
	 * The list contents are of type {@link javax.xml.namespace.QName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant Ref</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant Ref</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTChoreographyActivity_ParticipantRef()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName" lower="2"
	 *        extendedMetaData="kind='element' name='participantRef' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<QName> getParticipantRef();

	/**
	 * Returns the value of the '<em><b>Correlation Key</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TCorrelationKey}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Key</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlation Key</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTChoreographyActivity_CorrelationKey()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='correlationKey' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TCorrelationKey> getCorrelationKey();

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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTChoreographyActivity_InitiatingParticipantRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
	 *        extendedMetaData="kind='attribute' name='initiatingParticipantRef'"
	 * @generated
	 */
	QName getInitiatingParticipantRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TChoreographyActivity#getInitiatingParticipantRef <em>Initiating Participant Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initiating Participant Ref</em>' attribute.
	 * @see #getInitiatingParticipantRef()
	 * @generated
	 */
	void setInitiatingParticipantRef(QName value);

	/**
	 * Returns the value of the '<em><b>Loop Type</b></em>' attribute.
	 * The default value is <code>"None"</code>.
	 * The literals are from the enumeration {@link org.omg.spec.bpmn.model.TChoreographyLoopType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Type</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TChoreographyLoopType
	 * @see #isSetLoopType()
	 * @see #unsetLoopType()
	 * @see #setLoopType(TChoreographyLoopType)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTChoreographyActivity_LoopType()
	 * @model default="None" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='loopType'"
	 * @generated
	 */
	TChoreographyLoopType getLoopType();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TChoreographyActivity#getLoopType <em>Loop Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Type</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TChoreographyLoopType
	 * @see #isSetLoopType()
	 * @see #unsetLoopType()
	 * @see #getLoopType()
	 * @generated
	 */
	void setLoopType(TChoreographyLoopType value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TChoreographyActivity#getLoopType <em>Loop Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLoopType()
	 * @see #getLoopType()
	 * @see #setLoopType(TChoreographyLoopType)
	 * @generated
	 */
	void unsetLoopType();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TChoreographyActivity#getLoopType <em>Loop Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Loop Type</em>' attribute is set.
	 * @see #unsetLoopType()
	 * @see #getLoopType()
	 * @see #setLoopType(TChoreographyLoopType)
	 * @generated
	 */
	boolean isSetLoopType();

} // TChoreographyActivity
