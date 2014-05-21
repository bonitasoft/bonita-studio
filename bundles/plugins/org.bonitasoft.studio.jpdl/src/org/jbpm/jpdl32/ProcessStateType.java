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
 * A representation of the model object '<em><b>Process State Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.ProcessStateType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessStateType#getSubProcess <em>Sub Process</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessStateType#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessStateType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessStateType#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessStateType#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessStateType#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessStateType#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessStateType#getAsync <em>Async</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessStateType#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ProcessStateType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getProcessStateType()
 * @model extendedMetaData="name='process-state_._type' kind='elementOnly'"
 * @generated
 */
public interface ProcessStateType extends EObject {
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessStateType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Sub Process</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.SubProcessType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Process</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Process</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessStateType_SubProcess()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='sub-process' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<SubProcessType> getSubProcess();

	/**
	 * Returns the value of the '<em><b>Variable</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.VariableType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessStateType_Variable()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='variable' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<VariableType> getVariable();

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessStateType_Description()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessStateType_Event()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessStateType_ExceptionHandler()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='exception-handler' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ExceptionHandlerType> getExceptionHandler();

	/**
	 * Returns the value of the '<em><b>Timer</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.TimerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timer</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessStateType_Timer()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='timer' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<TimerType> getTimer();

	/**
	 * Returns the value of the '<em><b>Transition</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.TransitionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessStateType_Transition()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='transition' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<TransitionType> getTransition();

	/**
	 * Returns the value of the '<em><b>Async</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Async</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Async</em>' attribute.
	 * @see #isSetAsync()
	 * @see #unsetAsync()
	 * @see #setAsync(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessStateType_Async()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='async'"
	 * @generated
	 */
	String getAsync();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ProcessStateType#getAsync <em>Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Async</em>' attribute.
	 * @see #isSetAsync()
	 * @see #unsetAsync()
	 * @see #getAsync()
	 * @generated
	 */
	void setAsync(String value);

	/**
	 * Unsets the value of the '{@link org.jbpm.jpdl32.ProcessStateType#getAsync <em>Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAsync()
	 * @see #getAsync()
	 * @see #setAsync(String)
	 * @generated
	 */
	void unsetAsync();

	/**
	 * Returns whether the value of the '{@link org.jbpm.jpdl32.ProcessStateType#getAsync <em>Async</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Async</em>' attribute is set.
	 * @see #unsetAsync()
	 * @see #getAsync()
	 * @see #setAsync(String)
	 * @generated
	 */
	boolean isSetAsync();

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' attribute.
	 * The literals are from the enumeration {@link org.jbpm.jpdl32.BindingType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' attribute.
	 * @see org.jbpm.jpdl32.BindingType
	 * @see #isSetBinding()
	 * @see #unsetBinding()
	 * @see #setBinding(BindingType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessStateType_Binding()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='binding'"
	 * @generated
	 */
	BindingType getBinding();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ProcessStateType#getBinding <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' attribute.
	 * @see org.jbpm.jpdl32.BindingType
	 * @see #isSetBinding()
	 * @see #unsetBinding()
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(BindingType value);

	/**
	 * Unsets the value of the '{@link org.jbpm.jpdl32.ProcessStateType#getBinding <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBinding()
	 * @see #getBinding()
	 * @see #setBinding(BindingType)
	 * @generated
	 */
	void unsetBinding();

	/**
	 * Returns whether the value of the '{@link org.jbpm.jpdl32.ProcessStateType#getBinding <em>Binding</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Binding</em>' attribute is set.
	 * @see #unsetBinding()
	 * @see #getBinding()
	 * @see #setBinding(BindingType)
	 * @generated
	 */
	boolean isSetBinding();

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getProcessStateType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ProcessStateType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ProcessStateType
