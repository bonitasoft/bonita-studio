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
 * A representation of the model object '<em><b>Time Estimation Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.TimeEstimationType#getWaitingTime <em>Waiting Time</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TimeEstimationType#getWorkingTime <em>Working Time</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TimeEstimationType#getDuration <em>Duration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTimeEstimationType()
 * @model extendedMetaData="name='TimeEstimation_._type' kind='elementOnly'"
 * @generated
 */
public interface TimeEstimationType extends EObject {
	/**
	 * Returns the value of the '<em><b>Waiting Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Waiting Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Waiting Time</em>' attribute.
	 * @see #setWaitingTime(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTimeEstimationType_WaitingTime()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='WaitingTime' namespace='##targetNamespace'"
	 * @generated
	 */
	String getWaitingTime();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TimeEstimationType#getWaitingTime <em>Waiting Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Waiting Time</em>' attribute.
	 * @see #getWaitingTime()
	 * @generated
	 */
	void setWaitingTime(String value);

	/**
	 * Returns the value of the '<em><b>Working Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Working Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Time</em>' attribute.
	 * @see #setWorkingTime(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTimeEstimationType_WorkingTime()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='WorkingTime' namespace='##targetNamespace'"
	 * @generated
	 */
	String getWorkingTime();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TimeEstimationType#getWorkingTime <em>Working Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Time</em>' attribute.
	 * @see #getWorkingTime()
	 * @generated
	 */
	void setWorkingTime(String value);

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTimeEstimationType_Duration()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Duration' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDuration();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TimeEstimationType#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(String value);

} // TimeEstimationType
