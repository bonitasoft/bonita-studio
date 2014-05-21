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
 * A representation of the model object '<em><b>End State Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.EndStateType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.EndStateType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.EndStateType#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.EndStateType#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.EndStateType#getEndCompleteProcess <em>End Complete Process</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.EndStateType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getEndStateType()
 * @model extendedMetaData="name='end-state_._type' kind='elementOnly'"
 * @generated
 */
public interface EndStateType extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getEndStateType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getEndStateType_Description()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<String> getDescription();

	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.EventType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getEndStateType_Event()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='event' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<EventType> getEvent();

	/**
	 * Returns the value of the '<em><b>Exception Handler</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.ExceptionHandlerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exception Handler</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exception Handler</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getEndStateType_ExceptionHandler()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='exception-handler' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ExceptionHandlerType> getExceptionHandler();

	/**
	 * Returns the value of the '<em><b>End Complete Process</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * The literals are from the enumeration {@link org.jbpm.jpdl32.BooleanType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Complete Process</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Complete Process</em>' attribute.
	 * @see org.jbpm.jpdl32.BooleanType
	 * @see #isSetEndCompleteProcess()
	 * @see #unsetEndCompleteProcess()
	 * @see #setEndCompleteProcess(BooleanType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getEndStateType_EndCompleteProcess()
	 * @model default="false" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='end-complete-process'"
	 * @generated
	 */
	BooleanType getEndCompleteProcess();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.EndStateType#getEndCompleteProcess <em>End Complete Process</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Complete Process</em>' attribute.
	 * @see org.jbpm.jpdl32.BooleanType
	 * @see #isSetEndCompleteProcess()
	 * @see #unsetEndCompleteProcess()
	 * @see #getEndCompleteProcess()
	 * @generated
	 */
	void setEndCompleteProcess(BooleanType value);

	/**
	 * Unsets the value of the '{@link org.jbpm.jpdl32.EndStateType#getEndCompleteProcess <em>End Complete Process</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEndCompleteProcess()
	 * @see #getEndCompleteProcess()
	 * @see #setEndCompleteProcess(BooleanType)
	 * @generated
	 */
	void unsetEndCompleteProcess();

	/**
	 * Returns whether the value of the '{@link org.jbpm.jpdl32.EndStateType#getEndCompleteProcess <em>End Complete Process</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>End Complete Process</em>' attribute is set.
	 * @see #unsetEndCompleteProcess()
	 * @see #getEndCompleteProcess()
	 * @see #setEndCompleteProcess(BooleanType)
	 * @generated
	 */
	boolean isSetEndCompleteProcess();

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getEndStateType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.EndStateType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // EndStateType
