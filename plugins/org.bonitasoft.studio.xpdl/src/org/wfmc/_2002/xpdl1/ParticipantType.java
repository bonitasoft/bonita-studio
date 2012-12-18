/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Participant Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ParticipantType#getParticipantType <em>Participant Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ParticipantType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ParticipantType#getExternalReference <em>External Reference</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ParticipantType#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ParticipantType#getId <em>Id</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ParticipantType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getParticipantType()
 * @model extendedMetaData="name='Participant_._type' kind='elementOnly'"
 * @generated
 */
public interface ParticipantType extends EObject {
	/**
	 * Returns the value of the '<em><b>Participant Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant Type</em>' containment reference.
	 * @see #setParticipantType(ParticipantTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getParticipantType_ParticipantType()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='ParticipantType' namespace='##targetNamespace'"
	 * @generated
	 */
	ParticipantTypeType getParticipantType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ParticipantType#getParticipantType <em>Participant Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participant Type</em>' containment reference.
	 * @see #getParticipantType()
	 * @generated
	 */
	void setParticipantType(ParticipantTypeType value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getParticipantType_Description()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Description' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ParticipantType#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Reference</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Reference</em>' containment reference.
	 * @see #setExternalReference(ExternalReferenceType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getParticipantType_ExternalReference()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExternalReference' namespace='##targetNamespace'"
	 * @generated
	 */
	ExternalReferenceType getExternalReference();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ParticipantType#getExternalReference <em>External Reference</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Reference</em>' containment reference.
	 * @see #getExternalReference()
	 * @generated
	 */
	void setExternalReference(ExternalReferenceType value);

	/**
	 * Returns the value of the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Attributes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Attributes</em>' containment reference.
	 * @see #setExtendedAttributes(ExtendedAttributesType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getParticipantType_ExtendedAttributes()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExtendedAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	ExtendedAttributesType getExtendedAttributes();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ParticipantType#getExtendedAttributes <em>Extended Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Attributes</em>' containment reference.
	 * @see #getExtendedAttributes()
	 * @generated
	 */
	void setExtendedAttributes(ExtendedAttributesType value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getParticipantType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='Id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ParticipantType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getParticipantType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='Name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ParticipantType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ParticipantType
