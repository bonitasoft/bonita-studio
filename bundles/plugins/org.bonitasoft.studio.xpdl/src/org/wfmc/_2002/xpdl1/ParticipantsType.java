/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Participants Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ParticipantsType#getParticipant <em>Participant</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getParticipantsType()
 * @model extendedMetaData="name='Participants_._type' kind='elementOnly'"
 * @generated
 */
public interface ParticipantsType extends EObject {
	/**
	 * Returns the value of the '<em><b>Participant</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.ParticipantType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getParticipantsType_Participant()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Participant' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ParticipantType> getParticipant();

} // ParticipantsType
