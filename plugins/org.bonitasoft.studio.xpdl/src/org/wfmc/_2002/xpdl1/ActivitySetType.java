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
 * A representation of the model object '<em><b>Activity Set Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivitySetType#getActivities <em>Activities</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivitySetType#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivitySetType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivitySetType()
 * @model extendedMetaData="name='ActivitySet_._type' kind='elementOnly'"
 * @generated
 */
public interface ActivitySetType extends EObject {
	/**
	 * Returns the value of the '<em><b>Activities</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activities</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activities</em>' containment reference.
	 * @see #setActivities(ActivitiesType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivitySetType_Activities()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Activities' namespace='##targetNamespace'"
	 * @generated
	 */
	ActivitiesType getActivities();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivitySetType#getActivities <em>Activities</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activities</em>' containment reference.
	 * @see #getActivities()
	 * @generated
	 */
	void setActivities(ActivitiesType value);

	/**
	 * Returns the value of the '<em><b>Transitions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transitions</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transitions</em>' containment reference.
	 * @see #setTransitions(TransitionsType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivitySetType_Transitions()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Transitions' namespace='##targetNamespace'"
	 * @generated
	 */
	TransitionsType getTransitions();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivitySetType#getTransitions <em>Transitions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transitions</em>' containment reference.
	 * @see #getTransitions()
	 * @generated
	 */
	void setTransitions(TransitionsType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivitySetType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='Id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivitySetType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ActivitySetType
