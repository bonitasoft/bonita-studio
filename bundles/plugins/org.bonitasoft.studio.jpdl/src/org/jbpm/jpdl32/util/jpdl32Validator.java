/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32.util;

import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;
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
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.jbpm.jpdl32.jpdl32Package
 * @generated
 */
public class jpdl32Validator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final jpdl32Validator INSTANCE = new jpdl32Validator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.jbpm.jpdl32";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XMLTypeValidator xmlTypeValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public jpdl32Validator() {
		super();
		xmlTypeValidator = XMLTypeValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return jpdl32Package.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case jpdl32Package.ACTION_TYPE:
				return validateActionType((ActionType)value, diagnostics, context);
			case jpdl32Package.ASSIGNMENT_TYPE:
				return validateAssignmentType((AssignmentType)value, diagnostics, context);
			case jpdl32Package.CANCEL_TIMER_TYPE:
				return validateCancelTimerType((CancelTimerType)value, diagnostics, context);
			case jpdl32Package.CONDITION_TYPE:
				return validateConditionType((ConditionType)value, diagnostics, context);
			case jpdl32Package.CREATE_TIMER_TYPE:
				return validateCreateTimerType((CreateTimerType)value, diagnostics, context);
			case jpdl32Package.DECISION_TYPE:
				return validateDecisionType((DecisionType)value, diagnostics, context);
			case jpdl32Package.DELEGATION:
				return validateDelegation((Delegation)value, diagnostics, context);
			case jpdl32Package.DOCUMENT_ROOT:
				return validateDocumentRoot((DocumentRoot)value, diagnostics, context);
			case jpdl32Package.END_STATE_TYPE:
				return validateEndStateType((EndStateType)value, diagnostics, context);
			case jpdl32Package.EVENT_TYPE:
				return validateEventType((EventType)value, diagnostics, context);
			case jpdl32Package.EXCEPTION_HANDLER_TYPE:
				return validateExceptionHandlerType((ExceptionHandlerType)value, diagnostics, context);
			case jpdl32Package.FORK_TYPE:
				return validateForkType((ForkType)value, diagnostics, context);
			case jpdl32Package.JOIN_TYPE:
				return validateJoinType((JoinType)value, diagnostics, context);
			case jpdl32Package.MAIL_NODE_TYPE:
				return validateMailNodeType((MailNodeType)value, diagnostics, context);
			case jpdl32Package.MAIL_TYPE:
				return validateMailType((MailType)value, diagnostics, context);
			case jpdl32Package.NODE_TYPE:
				return validateNodeType((NodeType)value, diagnostics, context);
			case jpdl32Package.PROCESS_DEFINITION_TYPE:
				return validateProcessDefinitionType((ProcessDefinitionType)value, diagnostics, context);
			case jpdl32Package.PROCESS_STATE_TYPE:
				return validateProcessStateType((ProcessStateType)value, diagnostics, context);
			case jpdl32Package.REMINDER_TYPE:
				return validateReminderType((ReminderType)value, diagnostics, context);
			case jpdl32Package.SCRIPT_TYPE:
				return validateScriptType((ScriptType)value, diagnostics, context);
			case jpdl32Package.START_STATE_TYPE:
				return validateStartStateType((StartStateType)value, diagnostics, context);
			case jpdl32Package.STATE_TYPE:
				return validateStateType((StateType)value, diagnostics, context);
			case jpdl32Package.SUB_PROCESS_TYPE:
				return validateSubProcessType((SubProcessType)value, diagnostics, context);
			case jpdl32Package.SUPER_STATE_TYPE:
				return validateSuperStateType((SuperStateType)value, diagnostics, context);
			case jpdl32Package.SWIMLANE_TYPE:
				return validateSwimlaneType((SwimlaneType)value, diagnostics, context);
			case jpdl32Package.TASK_NODE_TYPE:
				return validateTaskNodeType((TaskNodeType)value, diagnostics, context);
			case jpdl32Package.TASK_TYPE:
				return validateTaskType((TaskType)value, diagnostics, context);
			case jpdl32Package.TIMER_TYPE:
				return validateTimerType((TimerType)value, diagnostics, context);
			case jpdl32Package.TRANSITION_TYPE:
				return validateTransitionType((TransitionType)value, diagnostics, context);
			case jpdl32Package.VARIABLE_TYPE:
				return validateVariableType((VariableType)value, diagnostics, context);
			case jpdl32Package.BINDING_TYPE:
				return validateBindingType((BindingType)value, diagnostics, context);
			case jpdl32Package.BOOLEAN_TYPE:
				return validateBooleanType((BooleanType)value, diagnostics, context);
			case jpdl32Package.CONFIG_TYPE:
				return validateConfigType((ConfigType)value, diagnostics, context);
			case jpdl32Package.PRIORITY_TYPE_MEMBER0:
				return validatePriorityTypeMember0((PriorityTypeMember0)value, diagnostics, context);
			case jpdl32Package.SIGNAL_TYPE:
				return validateSignalType((SignalType)value, diagnostics, context);
			case jpdl32Package.TYPE_TYPE_MEMBER1:
				return validateTypeTypeMember1((TypeTypeMember1)value, diagnostics, context);
			case jpdl32Package.BINDING_TYPE_OBJECT:
				return validateBindingTypeObject((BindingType)value, diagnostics, context);
			case jpdl32Package.BOOLEAN_TYPE_OBJECT:
				return validateBooleanTypeObject((BooleanType)value, diagnostics, context);
			case jpdl32Package.CONFIG_TYPE_OBJECT:
				return validateConfigTypeObject((ConfigType)value, diagnostics, context);
			case jpdl32Package.PRIORITY_TYPE:
				return validatePriorityType(value, diagnostics, context);
			case jpdl32Package.PRIORITY_TYPE_MEMBER0_OBJECT:
				return validatePriorityTypeMember0Object((PriorityTypeMember0)value, diagnostics, context);
			case jpdl32Package.PRIORITY_TYPE_MEMBER1:
				return validatePriorityTypeMember1((Integer)value, diagnostics, context);
			case jpdl32Package.PRIORITY_TYPE_MEMBER1_OBJECT:
				return validatePriorityTypeMember1Object((Integer)value, diagnostics, context);
			case jpdl32Package.SIGNAL_TYPE_OBJECT:
				return validateSignalTypeObject((SignalType)value, diagnostics, context);
			case jpdl32Package.TYPE_TYPE:
				return validateTypeType(value, diagnostics, context);
			case jpdl32Package.TYPE_TYPE_MEMBER0:
				return validateTypeTypeMember0((String)value, diagnostics, context);
			case jpdl32Package.TYPE_TYPE_MEMBER1_OBJECT:
				return validateTypeTypeMember1Object((TypeTypeMember1)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateActionType(ActionType actionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(actionType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssignmentType(AssignmentType assignmentType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(assignmentType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCancelTimerType(CancelTimerType cancelTimerType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(cancelTimerType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConditionType(ConditionType conditionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(conditionType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCreateTimerType(CreateTimerType createTimerType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(createTimerType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDecisionType(DecisionType decisionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(decisionType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDelegation(Delegation delegation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(delegation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(documentRoot, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEndStateType(EndStateType endStateType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(endStateType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEventType(EventType eventType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(eventType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExceptionHandlerType(ExceptionHandlerType exceptionHandlerType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(exceptionHandlerType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateForkType(ForkType forkType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(forkType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJoinType(JoinType joinType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(joinType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMailNodeType(MailNodeType mailNodeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(mailNodeType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMailType(MailType mailType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(mailType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNodeType(NodeType nodeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(nodeType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcessDefinitionType(ProcessDefinitionType processDefinitionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(processDefinitionType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcessStateType(ProcessStateType processStateType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(processStateType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateReminderType(ReminderType reminderType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(reminderType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScriptType(ScriptType scriptType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scriptType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStartStateType(StartStateType startStateType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(startStateType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStateType(StateType stateType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stateType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSubProcessType(SubProcessType subProcessType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(subProcessType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSuperStateType(SuperStateType superStateType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(superStateType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSwimlaneType(SwimlaneType swimlaneType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(swimlaneType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTaskNodeType(TaskNodeType taskNodeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(taskNodeType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTaskType(TaskType taskType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(taskType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimerType(TimerType timerType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(timerType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransitionType(TransitionType transitionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(transitionType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVariableType(VariableType variableType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(variableType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBindingType(BindingType bindingType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBooleanType(BooleanType booleanType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConfigType(ConfigType configType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePriorityTypeMember0(PriorityTypeMember0 priorityTypeMember0, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSignalType(SignalType signalType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeTypeMember1(TypeTypeMember1 typeTypeMember1, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBindingTypeObject(BindingType bindingTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBooleanTypeObject(BooleanType booleanTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConfigTypeObject(ConfigType configTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePriorityType(Object priorityType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validatePriorityType_MemberTypes(priorityType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MemberTypes constraint of '<em>Priority Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePriorityType_MemberTypes(Object priorityType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null) {
			BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
			if (jpdl32Package.Literals.PRIORITY_TYPE_MEMBER0.isInstance(priorityType)) {
				if (validatePriorityTypeMember0((PriorityTypeMember0)priorityType, tempDiagnostics, context)) return true;
			}
			if (jpdl32Package.Literals.PRIORITY_TYPE_MEMBER1.isInstance(priorityType)) {
				if (validatePriorityTypeMember1((Integer)priorityType, tempDiagnostics, context)) return true;
			}
			for (Diagnostic diagnostic : tempDiagnostics.getChildren()) {
				diagnostics.add(diagnostic);
			}
		}
		else {
			if (jpdl32Package.Literals.PRIORITY_TYPE_MEMBER0.isInstance(priorityType)) {
				if (validatePriorityTypeMember0((PriorityTypeMember0)priorityType, null, context)) return true;
			}
			if (jpdl32Package.Literals.PRIORITY_TYPE_MEMBER1.isInstance(priorityType)) {
				if (validatePriorityTypeMember1((Integer)priorityType, null, context)) return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePriorityTypeMember0Object(PriorityTypeMember0 priorityTypeMember0Object, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePriorityTypeMember1(int priorityTypeMember1, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePriorityTypeMember1Object(Integer priorityTypeMember1Object, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSignalTypeObject(SignalType signalTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType(Object typeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateTypeType_MemberTypes(typeType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MemberTypes constraint of '<em>Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeType_MemberTypes(Object typeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null) {
			BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
			if (jpdl32Package.Literals.TYPE_TYPE_MEMBER0.isInstance(typeType)) {
				if (validateTypeTypeMember0((String)typeType, tempDiagnostics, context)) return true;
			}
			if (jpdl32Package.Literals.TYPE_TYPE_MEMBER1.isInstance(typeType)) {
				if (validateTypeTypeMember1((TypeTypeMember1)typeType, tempDiagnostics, context)) return true;
			}
			for (Diagnostic diagnostic : tempDiagnostics.getChildren()) {
				diagnostics.add(diagnostic);
			}
		}
		else {
			if (jpdl32Package.Literals.TYPE_TYPE_MEMBER0.isInstance(typeType)) {
				if (validateTypeTypeMember0((String)typeType, null, context)) return true;
			}
			if (jpdl32Package.Literals.TYPE_TYPE_MEMBER1.isInstance(typeType)) {
				if (validateTypeTypeMember1((TypeTypeMember1)typeType, null, context)) return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeTypeMember0(String typeTypeMember0, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeTypeMember1Object(TypeTypeMember1 typeTypeMember1Object, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //jpdl32Validator
