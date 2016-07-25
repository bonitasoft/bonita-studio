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
 * A representation of the model object '<em><b>Finish Mode Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.FinishModeType#getAutomatic <em>Automatic</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.FinishModeType#getManual <em>Manual</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getFinishModeType()
 * @model extendedMetaData="name='FinishMode_._type' kind='elementOnly'"
 * @generated
 */
public interface FinishModeType extends EObject {
	/**
	 * Returns the value of the '<em><b>Automatic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Automatic</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Automatic</em>' containment reference.
	 * @see #setAutomatic(AutomaticType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getFinishModeType_Automatic()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Automatic' namespace='##targetNamespace'"
	 * @generated
	 */
	AutomaticType getAutomatic();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.FinishModeType#getAutomatic <em>Automatic</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Automatic</em>' containment reference.
	 * @see #getAutomatic()
	 * @generated
	 */
	void setAutomatic(AutomaticType value);

	/**
	 * Returns the value of the '<em><b>Manual</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manual</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manual</em>' containment reference.
	 * @see #setManual(ManualType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getFinishModeType_Manual()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Manual' namespace='##targetNamespace'"
	 * @generated
	 */
	ManualType getManual();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.FinishModeType#getManual <em>Manual</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manual</em>' containment reference.
	 * @see #getManual()
	 * @generated
	 */
	void setManual(ManualType value);

} // FinishModeType
