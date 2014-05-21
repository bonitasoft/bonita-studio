/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.EventType#getActionElements <em>Action Elements</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.EventType#getAction <em>Action</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.EventType#getScript <em>Script</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.EventType#getCreateTimer <em>Create Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.EventType#getCancelTimer <em>Cancel Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.EventType#getMail <em>Mail</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.EventType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getEventType()
 * @model extendedMetaData="name='event_._type' kind='elementOnly'"
 * @generated
 */
public interface EventType extends EObject {
	/**
	 * Returns the value of the '<em><b>Action Elements</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Elements</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Elements</em>' attribute list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getEventType_ActionElements()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='ActionElements:0'"
	 * @generated
	 */
	FeatureMap getActionElements();

	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.ActionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getEventType_Action()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='action' namespace='##targetNamespace' group='#ActionElements:0'"
	 * @generated
	 */
	EList<ActionType> getAction();

	/**
	 * Returns the value of the '<em><b>Script</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.ScriptType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getEventType_Script()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace' group='#ActionElements:0'"
	 * @generated
	 */
	EList<ScriptType> getScript();

	/**
	 * Returns the value of the '<em><b>Create Timer</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.CreateTimerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Create Timer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Create Timer</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getEventType_CreateTimer()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='create-timer' namespace='##targetNamespace' group='#ActionElements:0'"
	 * @generated
	 */
	EList<CreateTimerType> getCreateTimer();

	/**
	 * Returns the value of the '<em><b>Cancel Timer</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.CancelTimerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cancel Timer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cancel Timer</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getEventType_CancelTimer()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='cancel-timer' namespace='##targetNamespace' group='#ActionElements:0'"
	 * @generated
	 */
	EList<CancelTimerType> getCancelTimer();

	/**
	 * Returns the value of the '<em><b>Mail</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.MailType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mail</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mail</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getEventType_Mail()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='mail' namespace='##targetNamespace' group='#ActionElements:0'"
	 * @generated
	 */
	EList<MailType> getMail();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(Object)
	 * @see org.jbpm.jpdl32.jpdl32Package#getEventType_Type()
	 * @model dataType="org.jbpm.jpdl32.TypeType" required="true"
	 *        extendedMetaData="kind='attribute' name='type'"
	 * @generated
	 */
	Object getType();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.EventType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(Object value);

} // EventType
