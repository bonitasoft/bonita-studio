/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.jbpm.jpdl32.jpdl32Package
 * @generated
 */
public interface jpdl32Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	jpdl32Factory eINSTANCE = org.jbpm.jpdl32.impl.jpdl32FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Action Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Type</em>'.
	 * @generated
	 */
	ActionType createActionType();

	/**
	 * Returns a new object of class '<em>Assignment Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assignment Type</em>'.
	 * @generated
	 */
	AssignmentType createAssignmentType();

	/**
	 * Returns a new object of class '<em>Cancel Timer Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cancel Timer Type</em>'.
	 * @generated
	 */
	CancelTimerType createCancelTimerType();

	/**
	 * Returns a new object of class '<em>Condition Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Condition Type</em>'.
	 * @generated
	 */
	ConditionType createConditionType();

	/**
	 * Returns a new object of class '<em>Create Timer Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Create Timer Type</em>'.
	 * @generated
	 */
	CreateTimerType createCreateTimerType();

	/**
	 * Returns a new object of class '<em>Decision Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decision Type</em>'.
	 * @generated
	 */
	DecisionType createDecisionType();

	/**
	 * Returns a new object of class '<em>Delegation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Delegation</em>'.
	 * @generated
	 */
	Delegation createDelegation();

	/**
	 * Returns a new object of class '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>End State Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>End State Type</em>'.
	 * @generated
	 */
	EndStateType createEndStateType();

	/**
	 * Returns a new object of class '<em>Event Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event Type</em>'.
	 * @generated
	 */
	EventType createEventType();

	/**
	 * Returns a new object of class '<em>Exception Handler Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exception Handler Type</em>'.
	 * @generated
	 */
	ExceptionHandlerType createExceptionHandlerType();

	/**
	 * Returns a new object of class '<em>Fork Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fork Type</em>'.
	 * @generated
	 */
	ForkType createForkType();

	/**
	 * Returns a new object of class '<em>Join Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Join Type</em>'.
	 * @generated
	 */
	JoinType createJoinType();

	/**
	 * Returns a new object of class '<em>Mail Node Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mail Node Type</em>'.
	 * @generated
	 */
	MailNodeType createMailNodeType();

	/**
	 * Returns a new object of class '<em>Mail Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mail Type</em>'.
	 * @generated
	 */
	MailType createMailType();

	/**
	 * Returns a new object of class '<em>Node Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Node Type</em>'.
	 * @generated
	 */
	NodeType createNodeType();

	/**
	 * Returns a new object of class '<em>Process Definition Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Definition Type</em>'.
	 * @generated
	 */
	ProcessDefinitionType createProcessDefinitionType();

	/**
	 * Returns a new object of class '<em>Process State Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process State Type</em>'.
	 * @generated
	 */
	ProcessStateType createProcessStateType();

	/**
	 * Returns a new object of class '<em>Reminder Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reminder Type</em>'.
	 * @generated
	 */
	ReminderType createReminderType();

	/**
	 * Returns a new object of class '<em>Script Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Script Type</em>'.
	 * @generated
	 */
	ScriptType createScriptType();

	/**
	 * Returns a new object of class '<em>Start State Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Start State Type</em>'.
	 * @generated
	 */
	StartStateType createStartStateType();

	/**
	 * Returns a new object of class '<em>State Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State Type</em>'.
	 * @generated
	 */
	StateType createStateType();

	/**
	 * Returns a new object of class '<em>Sub Process Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sub Process Type</em>'.
	 * @generated
	 */
	SubProcessType createSubProcessType();

	/**
	 * Returns a new object of class '<em>Super State Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Super State Type</em>'.
	 * @generated
	 */
	SuperStateType createSuperStateType();

	/**
	 * Returns a new object of class '<em>Swimlane Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Swimlane Type</em>'.
	 * @generated
	 */
	SwimlaneType createSwimlaneType();

	/**
	 * Returns a new object of class '<em>Task Node Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Task Node Type</em>'.
	 * @generated
	 */
	TaskNodeType createTaskNodeType();

	/**
	 * Returns a new object of class '<em>Task Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Task Type</em>'.
	 * @generated
	 */
	TaskType createTaskType();

	/**
	 * Returns a new object of class '<em>Timer Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Timer Type</em>'.
	 * @generated
	 */
	TimerType createTimerType();

	/**
	 * Returns a new object of class '<em>Transition Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transition Type</em>'.
	 * @generated
	 */
	TransitionType createTransitionType();

	/**
	 * Returns a new object of class '<em>Variable Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable Type</em>'.
	 * @generated
	 */
	VariableType createVariableType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	jpdl32Package getjpdl32Package();

} //jpdl32Factory
