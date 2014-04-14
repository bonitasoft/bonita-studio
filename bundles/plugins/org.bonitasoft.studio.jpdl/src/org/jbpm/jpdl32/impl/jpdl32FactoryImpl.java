/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.jbpm.jpdl32.ActionType;
import org.jbpm.jpdl32.AssignmentType;
import org.jbpm.jpdl32.BindingType;
import org.jbpm.jpdl32.BooleanType;
import org.jbpm.jpdl32.CancelTimerType;
import org.jbpm.jpdl32.ConditionType;
import org.jbpm.jpdl32.ConfigType;
import org.jbpm.jpdl32.CreateTimerType;
import org.jbpm.jpdl32.DecisionType;
import org.jbpm.jpdl32.Delegation;
import org.jbpm.jpdl32.DocumentRoot;
import org.jbpm.jpdl32.EndStateType;
import org.jbpm.jpdl32.EventType;
import org.jbpm.jpdl32.ExceptionHandlerType;
import org.jbpm.jpdl32.ForkType;
import org.jbpm.jpdl32.JoinType;
import org.jbpm.jpdl32.MailNodeType;
import org.jbpm.jpdl32.MailType;
import org.jbpm.jpdl32.NodeType;
import org.jbpm.jpdl32.PriorityTypeMember0;
import org.jbpm.jpdl32.ProcessDefinitionType;
import org.jbpm.jpdl32.ProcessStateType;
import org.jbpm.jpdl32.ReminderType;
import org.jbpm.jpdl32.ScriptType;
import org.jbpm.jpdl32.SignalType;
import org.jbpm.jpdl32.StartStateType;
import org.jbpm.jpdl32.StateType;
import org.jbpm.jpdl32.SubProcessType;
import org.jbpm.jpdl32.SuperStateType;
import org.jbpm.jpdl32.SwimlaneType;
import org.jbpm.jpdl32.TaskNodeType;
import org.jbpm.jpdl32.TaskType;
import org.jbpm.jpdl32.TimerType;
import org.jbpm.jpdl32.TransitionType;
import org.jbpm.jpdl32.TypeTypeMember1;
import org.jbpm.jpdl32.VariableType;
import org.jbpm.jpdl32.jpdl32Factory;
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class jpdl32FactoryImpl extends EFactoryImpl implements jpdl32Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static jpdl32Factory init() {
		try {
			jpdl32Factory thejpdl32Factory = (jpdl32Factory)EPackage.Registry.INSTANCE.getEFactory("urn:jbpm.org:jpdl-3.2"); 
			if (thejpdl32Factory != null) {
				return thejpdl32Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new jpdl32FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public jpdl32FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case jpdl32Package.ACTION_TYPE: return createActionType();
			case jpdl32Package.ASSIGNMENT_TYPE: return createAssignmentType();
			case jpdl32Package.CANCEL_TIMER_TYPE: return createCancelTimerType();
			case jpdl32Package.CONDITION_TYPE: return createConditionType();
			case jpdl32Package.CREATE_TIMER_TYPE: return createCreateTimerType();
			case jpdl32Package.DECISION_TYPE: return createDecisionType();
			case jpdl32Package.DELEGATION: return createDelegation();
			case jpdl32Package.DOCUMENT_ROOT: return createDocumentRoot();
			case jpdl32Package.END_STATE_TYPE: return createEndStateType();
			case jpdl32Package.EVENT_TYPE: return createEventType();
			case jpdl32Package.EXCEPTION_HANDLER_TYPE: return createExceptionHandlerType();
			case jpdl32Package.FORK_TYPE: return createForkType();
			case jpdl32Package.JOIN_TYPE: return createJoinType();
			case jpdl32Package.MAIL_NODE_TYPE: return createMailNodeType();
			case jpdl32Package.MAIL_TYPE: return createMailType();
			case jpdl32Package.NODE_TYPE: return createNodeType();
			case jpdl32Package.PROCESS_DEFINITION_TYPE: return createProcessDefinitionType();
			case jpdl32Package.PROCESS_STATE_TYPE: return createProcessStateType();
			case jpdl32Package.REMINDER_TYPE: return createReminderType();
			case jpdl32Package.SCRIPT_TYPE: return createScriptType();
			case jpdl32Package.START_STATE_TYPE: return createStartStateType();
			case jpdl32Package.STATE_TYPE: return createStateType();
			case jpdl32Package.SUB_PROCESS_TYPE: return createSubProcessType();
			case jpdl32Package.SUPER_STATE_TYPE: return createSuperStateType();
			case jpdl32Package.SWIMLANE_TYPE: return createSwimlaneType();
			case jpdl32Package.TASK_NODE_TYPE: return createTaskNodeType();
			case jpdl32Package.TASK_TYPE: return createTaskType();
			case jpdl32Package.TIMER_TYPE: return createTimerType();
			case jpdl32Package.TRANSITION_TYPE: return createTransitionType();
			case jpdl32Package.VARIABLE_TYPE: return createVariableType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case jpdl32Package.BINDING_TYPE:
				return createBindingTypeFromString(eDataType, initialValue);
			case jpdl32Package.BOOLEAN_TYPE:
				return createBooleanTypeFromString(eDataType, initialValue);
			case jpdl32Package.CONFIG_TYPE:
				return createConfigTypeFromString(eDataType, initialValue);
			case jpdl32Package.PRIORITY_TYPE_MEMBER0:
				return createPriorityTypeMember0FromString(eDataType, initialValue);
			case jpdl32Package.SIGNAL_TYPE:
				return createSignalTypeFromString(eDataType, initialValue);
			case jpdl32Package.TYPE_TYPE_MEMBER1:
				return createTypeTypeMember1FromString(eDataType, initialValue);
			case jpdl32Package.BINDING_TYPE_OBJECT:
				return createBindingTypeObjectFromString(eDataType, initialValue);
			case jpdl32Package.BOOLEAN_TYPE_OBJECT:
				return createBooleanTypeObjectFromString(eDataType, initialValue);
			case jpdl32Package.CONFIG_TYPE_OBJECT:
				return createConfigTypeObjectFromString(eDataType, initialValue);
			case jpdl32Package.PRIORITY_TYPE:
				return createPriorityTypeFromString(eDataType, initialValue);
			case jpdl32Package.PRIORITY_TYPE_MEMBER0_OBJECT:
				return createPriorityTypeMember0ObjectFromString(eDataType, initialValue);
			case jpdl32Package.PRIORITY_TYPE_MEMBER1:
				return createPriorityTypeMember1FromString(eDataType, initialValue);
			case jpdl32Package.PRIORITY_TYPE_MEMBER1_OBJECT:
				return createPriorityTypeMember1ObjectFromString(eDataType, initialValue);
			case jpdl32Package.SIGNAL_TYPE_OBJECT:
				return createSignalTypeObjectFromString(eDataType, initialValue);
			case jpdl32Package.TYPE_TYPE:
				return createTypeTypeFromString(eDataType, initialValue);
			case jpdl32Package.TYPE_TYPE_MEMBER0:
				return createTypeTypeMember0FromString(eDataType, initialValue);
			case jpdl32Package.TYPE_TYPE_MEMBER1_OBJECT:
				return createTypeTypeMember1ObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case jpdl32Package.BINDING_TYPE:
				return convertBindingTypeToString(eDataType, instanceValue);
			case jpdl32Package.BOOLEAN_TYPE:
				return convertBooleanTypeToString(eDataType, instanceValue);
			case jpdl32Package.CONFIG_TYPE:
				return convertConfigTypeToString(eDataType, instanceValue);
			case jpdl32Package.PRIORITY_TYPE_MEMBER0:
				return convertPriorityTypeMember0ToString(eDataType, instanceValue);
			case jpdl32Package.SIGNAL_TYPE:
				return convertSignalTypeToString(eDataType, instanceValue);
			case jpdl32Package.TYPE_TYPE_MEMBER1:
				return convertTypeTypeMember1ToString(eDataType, instanceValue);
			case jpdl32Package.BINDING_TYPE_OBJECT:
				return convertBindingTypeObjectToString(eDataType, instanceValue);
			case jpdl32Package.BOOLEAN_TYPE_OBJECT:
				return convertBooleanTypeObjectToString(eDataType, instanceValue);
			case jpdl32Package.CONFIG_TYPE_OBJECT:
				return convertConfigTypeObjectToString(eDataType, instanceValue);
			case jpdl32Package.PRIORITY_TYPE:
				return convertPriorityTypeToString(eDataType, instanceValue);
			case jpdl32Package.PRIORITY_TYPE_MEMBER0_OBJECT:
				return convertPriorityTypeMember0ObjectToString(eDataType, instanceValue);
			case jpdl32Package.PRIORITY_TYPE_MEMBER1:
				return convertPriorityTypeMember1ToString(eDataType, instanceValue);
			case jpdl32Package.PRIORITY_TYPE_MEMBER1_OBJECT:
				return convertPriorityTypeMember1ObjectToString(eDataType, instanceValue);
			case jpdl32Package.SIGNAL_TYPE_OBJECT:
				return convertSignalTypeObjectToString(eDataType, instanceValue);
			case jpdl32Package.TYPE_TYPE:
				return convertTypeTypeToString(eDataType, instanceValue);
			case jpdl32Package.TYPE_TYPE_MEMBER0:
				return convertTypeTypeMember0ToString(eDataType, instanceValue);
			case jpdl32Package.TYPE_TYPE_MEMBER1_OBJECT:
				return convertTypeTypeMember1ObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionType createActionType() {
		ActionTypeImpl actionType = new ActionTypeImpl();
		return actionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignmentType createAssignmentType() {
		AssignmentTypeImpl assignmentType = new AssignmentTypeImpl();
		return assignmentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CancelTimerType createCancelTimerType() {
		CancelTimerTypeImpl cancelTimerType = new CancelTimerTypeImpl();
		return cancelTimerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionType createConditionType() {
		ConditionTypeImpl conditionType = new ConditionTypeImpl();
		return conditionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreateTimerType createCreateTimerType() {
		CreateTimerTypeImpl createTimerType = new CreateTimerTypeImpl();
		return createTimerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecisionType createDecisionType() {
		DecisionTypeImpl decisionType = new DecisionTypeImpl();
		return decisionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Delegation createDelegation() {
		DelegationImpl delegation = new DelegationImpl();
		return delegation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EndStateType createEndStateType() {
		EndStateTypeImpl endStateType = new EndStateTypeImpl();
		return endStateType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventType createEventType() {
		EventTypeImpl eventType = new EventTypeImpl();
		return eventType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExceptionHandlerType createExceptionHandlerType() {
		ExceptionHandlerTypeImpl exceptionHandlerType = new ExceptionHandlerTypeImpl();
		return exceptionHandlerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForkType createForkType() {
		ForkTypeImpl forkType = new ForkTypeImpl();
		return forkType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinType createJoinType() {
		JoinTypeImpl joinType = new JoinTypeImpl();
		return joinType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MailNodeType createMailNodeType() {
		MailNodeTypeImpl mailNodeType = new MailNodeTypeImpl();
		return mailNodeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MailType createMailType() {
		MailTypeImpl mailType = new MailTypeImpl();
		return mailType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeType createNodeType() {
		NodeTypeImpl nodeType = new NodeTypeImpl();
		return nodeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessDefinitionType createProcessDefinitionType() {
		ProcessDefinitionTypeImpl processDefinitionType = new ProcessDefinitionTypeImpl();
		return processDefinitionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessStateType createProcessStateType() {
		ProcessStateTypeImpl processStateType = new ProcessStateTypeImpl();
		return processStateType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReminderType createReminderType() {
		ReminderTypeImpl reminderType = new ReminderTypeImpl();
		return reminderType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptType createScriptType() {
		ScriptTypeImpl scriptType = new ScriptTypeImpl();
		return scriptType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartStateType createStartStateType() {
		StartStateTypeImpl startStateType = new StartStateTypeImpl();
		return startStateType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateType createStateType() {
		StateTypeImpl stateType = new StateTypeImpl();
		return stateType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubProcessType createSubProcessType() {
		SubProcessTypeImpl subProcessType = new SubProcessTypeImpl();
		return subProcessType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SuperStateType createSuperStateType() {
		SuperStateTypeImpl superStateType = new SuperStateTypeImpl();
		return superStateType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwimlaneType createSwimlaneType() {
		SwimlaneTypeImpl swimlaneType = new SwimlaneTypeImpl();
		return swimlaneType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskNodeType createTaskNodeType() {
		TaskNodeTypeImpl taskNodeType = new TaskNodeTypeImpl();
		return taskNodeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaskType createTaskType() {
		TaskTypeImpl taskType = new TaskTypeImpl();
		return taskType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimerType createTimerType() {
		TimerTypeImpl timerType = new TimerTypeImpl();
		return timerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionType createTransitionType() {
		TransitionTypeImpl transitionType = new TransitionTypeImpl();
		return transitionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableType createVariableType() {
		VariableTypeImpl variableType = new VariableTypeImpl();
		return variableType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingType createBindingTypeFromString(EDataType eDataType, String initialValue) {
		BindingType result = BindingType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBindingTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanType createBooleanTypeFromString(EDataType eDataType, String initialValue) {
		BooleanType result = BooleanType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBooleanTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigType createConfigTypeFromString(EDataType eDataType, String initialValue) {
		ConfigType result = ConfigType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConfigTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PriorityTypeMember0 createPriorityTypeMember0FromString(EDataType eDataType, String initialValue) {
		PriorityTypeMember0 result = PriorityTypeMember0.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPriorityTypeMember0ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SignalType createSignalTypeFromString(EDataType eDataType, String initialValue) {
		SignalType result = SignalType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSignalTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeTypeMember1 createTypeTypeMember1FromString(EDataType eDataType, String initialValue) {
		TypeTypeMember1 result = TypeTypeMember1.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeMember1ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingType createBindingTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createBindingTypeFromString(jpdl32Package.Literals.BINDING_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBindingTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertBindingTypeToString(jpdl32Package.Literals.BINDING_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanType createBooleanTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createBooleanTypeFromString(jpdl32Package.Literals.BOOLEAN_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBooleanTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertBooleanTypeToString(jpdl32Package.Literals.BOOLEAN_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigType createConfigTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createConfigTypeFromString(jpdl32Package.Literals.CONFIG_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConfigTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertConfigTypeToString(jpdl32Package.Literals.CONFIG_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createPriorityTypeFromString(EDataType eDataType, String initialValue) {
		if (initialValue == null) return null;
		Object result = null;
		RuntimeException exception = null;
		try {
			result = createPriorityTypeMember0FromString(jpdl32Package.Literals.PRIORITY_TYPE_MEMBER0, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		try {
			result = createPriorityTypeMember1FromString(jpdl32Package.Literals.PRIORITY_TYPE_MEMBER1, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		if (result != null || exception == null) return result;
    
		throw exception;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPriorityTypeToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == null) return null;
		if (jpdl32Package.Literals.PRIORITY_TYPE_MEMBER0.isInstance(instanceValue)) {
			try {
				String value = convertPriorityTypeMember0ToString(jpdl32Package.Literals.PRIORITY_TYPE_MEMBER0, instanceValue);
				if (value != null) return value;
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		if (jpdl32Package.Literals.PRIORITY_TYPE_MEMBER1.isInstance(instanceValue)) {
			try {
				String value = convertPriorityTypeMember1ToString(jpdl32Package.Literals.PRIORITY_TYPE_MEMBER1, instanceValue);
				if (value != null) return value;
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		throw new IllegalArgumentException("Invalid value: '"+instanceValue+"' for datatype :"+eDataType.getName());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PriorityTypeMember0 createPriorityTypeMember0ObjectFromString(EDataType eDataType, String initialValue) {
		return createPriorityTypeMember0FromString(jpdl32Package.Literals.PRIORITY_TYPE_MEMBER0, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPriorityTypeMember0ObjectToString(EDataType eDataType, Object instanceValue) {
		return convertPriorityTypeMember0ToString(jpdl32Package.Literals.PRIORITY_TYPE_MEMBER0, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer createPriorityTypeMember1FromString(EDataType eDataType, String initialValue) {
		return (Integer)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.INT, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPriorityTypeMember1ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.INT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer createPriorityTypeMember1ObjectFromString(EDataType eDataType, String initialValue) {
		return createPriorityTypeMember1FromString(jpdl32Package.Literals.PRIORITY_TYPE_MEMBER1, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPriorityTypeMember1ObjectToString(EDataType eDataType, Object instanceValue) {
		return convertPriorityTypeMember1ToString(jpdl32Package.Literals.PRIORITY_TYPE_MEMBER1, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SignalType createSignalTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createSignalTypeFromString(jpdl32Package.Literals.SIGNAL_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSignalTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertSignalTypeToString(jpdl32Package.Literals.SIGNAL_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createTypeTypeFromString(EDataType eDataType, String initialValue) {
		if (initialValue == null) return null;
		Object result = null;
		RuntimeException exception = null;
		try {
			result = createTypeTypeMember0FromString(jpdl32Package.Literals.TYPE_TYPE_MEMBER0, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		try {
			result = createTypeTypeMember1FromString(jpdl32Package.Literals.TYPE_TYPE_MEMBER1, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		if (result != null || exception == null) return result;
    
		throw exception;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == null) return null;
		if (jpdl32Package.Literals.TYPE_TYPE_MEMBER0.isInstance(instanceValue)) {
			try {
				String value = convertTypeTypeMember0ToString(jpdl32Package.Literals.TYPE_TYPE_MEMBER0, instanceValue);
				if (value != null) return value;
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		if (jpdl32Package.Literals.TYPE_TYPE_MEMBER1.isInstance(instanceValue)) {
			try {
				String value = convertTypeTypeMember1ToString(jpdl32Package.Literals.TYPE_TYPE_MEMBER1, instanceValue);
				if (value != null) return value;
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		throw new IllegalArgumentException("Invalid value: '"+instanceValue+"' for datatype :"+eDataType.getName());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createTypeTypeMember0FromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeMember0ToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeTypeMember1 createTypeTypeMember1ObjectFromString(EDataType eDataType, String initialValue) {
		return createTypeTypeMember1FromString(jpdl32Package.Literals.TYPE_TYPE_MEMBER1, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeMember1ObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTypeTypeMember1ToString(jpdl32Package.Literals.TYPE_TYPE_MEMBER1, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public jpdl32Package getjpdl32Package() {
		return (jpdl32Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static jpdl32Package getPackage() {
		return jpdl32Package.eINSTANCE;
	}

} //jpdl32FactoryImpl
