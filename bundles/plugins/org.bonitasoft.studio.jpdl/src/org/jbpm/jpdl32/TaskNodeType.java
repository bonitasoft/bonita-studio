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
 * A representation of the model object '<em><b>Task Node Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.TaskNodeType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TaskNodeType#getTask <em>Task</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TaskNodeType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TaskNodeType#getEvent <em>Event</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TaskNodeType#getExceptionHandler <em>Exception Handler</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TaskNodeType#getTimer <em>Timer</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TaskNodeType#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TaskNodeType#getAsync <em>Async</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TaskNodeType#getCreateTasks <em>Create Tasks</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TaskNodeType#getEndTasks <em>End Tasks</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TaskNodeType#getName <em>Name</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.TaskNodeType#getSignal <em>Signal</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType()
 * @model extendedMetaData="name='task-node_._type' kind='elementOnly'"
 * @generated
 */
public interface TaskNodeType extends EObject {
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Task</b></em>' containment reference list.
	 * The list contents are of type {@link org.jbpm.jpdl32.TaskType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task</em>' containment reference list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType_Task()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='task' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<TaskType> getTask();

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType_Description()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType_Event()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType_ExceptionHandler()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType_Timer()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType_Transition()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType_Async()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='async'"
	 * @generated
	 */
	String getAsync();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getAsync <em>Async</em>}' attribute.
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
	 * Unsets the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getAsync <em>Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAsync()
	 * @see #getAsync()
	 * @see #setAsync(String)
	 * @generated
	 */
	void unsetAsync();

	/**
	 * Returns whether the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getAsync <em>Async</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Create Tasks</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * The literals are from the enumeration {@link org.jbpm.jpdl32.BooleanType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Create Tasks</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Create Tasks</em>' attribute.
	 * @see org.jbpm.jpdl32.BooleanType
	 * @see #isSetCreateTasks()
	 * @see #unsetCreateTasks()
	 * @see #setCreateTasks(BooleanType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType_CreateTasks()
	 * @model default="true" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='create-tasks'"
	 * @generated
	 */
	BooleanType getCreateTasks();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getCreateTasks <em>Create Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Create Tasks</em>' attribute.
	 * @see org.jbpm.jpdl32.BooleanType
	 * @see #isSetCreateTasks()
	 * @see #unsetCreateTasks()
	 * @see #getCreateTasks()
	 * @generated
	 */
	void setCreateTasks(BooleanType value);

	/**
	 * Unsets the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getCreateTasks <em>Create Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCreateTasks()
	 * @see #getCreateTasks()
	 * @see #setCreateTasks(BooleanType)
	 * @generated
	 */
	void unsetCreateTasks();

	/**
	 * Returns whether the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getCreateTasks <em>Create Tasks</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Create Tasks</em>' attribute is set.
	 * @see #unsetCreateTasks()
	 * @see #getCreateTasks()
	 * @see #setCreateTasks(BooleanType)
	 * @generated
	 */
	boolean isSetCreateTasks();

	/**
	 * Returns the value of the '<em><b>End Tasks</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * The literals are from the enumeration {@link org.jbpm.jpdl32.BooleanType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Tasks</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Tasks</em>' attribute.
	 * @see org.jbpm.jpdl32.BooleanType
	 * @see #isSetEndTasks()
	 * @see #unsetEndTasks()
	 * @see #setEndTasks(BooleanType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType_EndTasks()
	 * @model default="false" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='end-tasks'"
	 * @generated
	 */
	BooleanType getEndTasks();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getEndTasks <em>End Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Tasks</em>' attribute.
	 * @see org.jbpm.jpdl32.BooleanType
	 * @see #isSetEndTasks()
	 * @see #unsetEndTasks()
	 * @see #getEndTasks()
	 * @generated
	 */
	void setEndTasks(BooleanType value);

	/**
	 * Unsets the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getEndTasks <em>End Tasks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEndTasks()
	 * @see #getEndTasks()
	 * @see #setEndTasks(BooleanType)
	 * @generated
	 */
	void unsetEndTasks();

	/**
	 * Returns whether the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getEndTasks <em>End Tasks</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>End Tasks</em>' attribute is set.
	 * @see #unsetEndTasks()
	 * @see #getEndTasks()
	 * @see #setEndTasks(BooleanType)
	 * @generated
	 */
	boolean isSetEndTasks();

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
	 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Signal</b></em>' attribute.
	 * The default value is <code>"last"</code>.
	 * The literals are from the enumeration {@link org.jbpm.jpdl32.SignalType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signal</em>' attribute.
	 * @see org.jbpm.jpdl32.SignalType
	 * @see #isSetSignal()
	 * @see #unsetSignal()
	 * @see #setSignal(SignalType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getTaskNodeType_Signal()
	 * @model default="last" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='signal'"
	 * @generated
	 */
	SignalType getSignal();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getSignal <em>Signal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signal</em>' attribute.
	 * @see org.jbpm.jpdl32.SignalType
	 * @see #isSetSignal()
	 * @see #unsetSignal()
	 * @see #getSignal()
	 * @generated
	 */
	void setSignal(SignalType value);

	/**
	 * Unsets the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getSignal <em>Signal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSignal()
	 * @see #getSignal()
	 * @see #setSignal(SignalType)
	 * @generated
	 */
	void unsetSignal();

	/**
	 * Returns whether the value of the '{@link org.jbpm.jpdl32.TaskNodeType#getSignal <em>Signal</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Signal</em>' attribute is set.
	 * @see #unsetSignal()
	 * @see #getSignal()
	 * @see #setSignal(SignalType)
	 * @generated
	 */
	boolean isSetSignal();

} // TaskNodeType
