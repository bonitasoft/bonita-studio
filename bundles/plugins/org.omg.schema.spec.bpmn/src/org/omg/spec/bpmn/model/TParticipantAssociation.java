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
 * A representation of the model object '<em><b>TParticipant Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TParticipantAssociation#getInnerParticipantRef <em>Inner Participant Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TParticipantAssociation#getOuterParticipantRef <em>Outer Participant Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipantAssociation()
 * @model extendedMetaData="name='tParticipantAssociation' kind='elementOnly'"
 * @generated
 */
public interface TParticipantAssociation extends TBaseElement {
	/**
	 * Returns the value of the '<em><b>Inner Participant Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inner Participant Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inner Participant Ref</em>' attribute.
	 * @see #setInnerParticipantRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipantAssociation_InnerParticipantRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
	 *        extendedMetaData="kind='element' name='innerParticipantRef' namespace='##targetNamespace'"
	 * @generated
	 */
	QName getInnerParticipantRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TParticipantAssociation#getInnerParticipantRef <em>Inner Participant Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inner Participant Ref</em>' attribute.
	 * @see #getInnerParticipantRef()
	 * @generated
	 */
	void setInnerParticipantRef(QName value);

	/**
	 * Returns the value of the '<em><b>Outer Participant Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outer Participant Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outer Participant Ref</em>' attribute.
	 * @see #setOuterParticipantRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipantAssociation_OuterParticipantRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
	 *        extendedMetaData="kind='element' name='outerParticipantRef' namespace='##targetNamespace'"
	 * @generated
	 */
	QName getOuterParticipantRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TParticipantAssociation#getOuterParticipantRef <em>Outer Participant Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outer Participant Ref</em>' attribute.
	 * @see #getOuterParticipantRef()
	 * @generated
	 */
	void setOuterParticipantRef(QName value);

} // TParticipantAssociation
