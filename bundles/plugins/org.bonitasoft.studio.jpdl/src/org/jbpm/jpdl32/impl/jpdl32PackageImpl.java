/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;
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
import org.jbpm.jpdl32.util.jpdl32Validator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class jpdl32PackageImpl extends EPackageImpl implements jpdl32Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assignmentTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cancelTimerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass createTimerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decisionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass delegationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass endStateTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exceptionHandlerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass forkTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass joinTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mailNodeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mailTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processDefinitionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processStateTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reminderTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scriptTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startStateTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subProcessTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass superStateTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass swimlaneTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskNodeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum bindingTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum booleanTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum configTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum priorityTypeMember0EEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum signalTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum typeTypeMember1EEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType bindingTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType booleanTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType configTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType priorityTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType priorityTypeMember0ObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType priorityTypeMember1EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType priorityTypeMember1ObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType signalTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeTypeMember0EDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeTypeMember1ObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.jbpm.jpdl32.jpdl32Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private jpdl32PackageImpl() {
		super(eNS_URI, jpdl32Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link jpdl32Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static jpdl32Package init() {
		if (isInited) return (jpdl32Package)EPackage.Registry.INSTANCE.getEPackage(jpdl32Package.eNS_URI);

		// Obtain or create and register package
		jpdl32PackageImpl thejpdl32Package = (jpdl32PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof jpdl32PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new jpdl32PackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thejpdl32Package.createPackageContents();

		// Initialize created meta-data
		thejpdl32Package.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(thejpdl32Package, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return jpdl32Validator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		thejpdl32Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(jpdl32Package.eNS_URI, thejpdl32Package);
		return thejpdl32Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionType() {
		return actionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_Mixed() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_Any() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_AcceptPropagatedEvents() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_Async() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_Class() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_ConfigType() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_Expression() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_Name() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActionType_RefName() {
		return (EAttribute)actionTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssignmentType() {
		return assignmentTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignmentType_ActorId() {
		return (EAttribute)assignmentTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignmentType_Expression() {
		return (EAttribute)assignmentTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignmentType_PooledActors() {
		return (EAttribute)assignmentTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCancelTimerType() {
		return cancelTimerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCancelTimerType_Name() {
		return (EAttribute)cancelTimerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionType() {
		return conditionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionType_Mixed() {
		return (EAttribute)conditionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionType_Group() {
		return (EAttribute)conditionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionType_Any() {
		return (EAttribute)conditionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionType_Expression() {
		return (EAttribute)conditionTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCreateTimerType() {
		return createTimerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCreateTimerType_Action() {
		return (EReference)createTimerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCreateTimerType_Script() {
		return (EReference)createTimerTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCreateTimerType_Duedate() {
		return (EAttribute)createTimerTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCreateTimerType_Name() {
		return (EAttribute)createTimerTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCreateTimerType_Repeat() {
		return (EAttribute)createTimerTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCreateTimerType_Transition() {
		return (EAttribute)createTimerTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecisionType() {
		return decisionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDecisionType_Group() {
		return (EAttribute)decisionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDecisionType_Description() {
		return (EAttribute)decisionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDecisionType_Handler() {
		return (EReference)decisionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDecisionType_Event() {
		return (EReference)decisionTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDecisionType_ExceptionHandler() {
		return (EReference)decisionTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDecisionType_Transition() {
		return (EReference)decisionTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDecisionType_Async() {
		return (EAttribute)decisionTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDecisionType_Expression() {
		return (EAttribute)decisionTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDecisionType_Name() {
		return (EAttribute)decisionTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDelegation() {
		return delegationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_Mixed() {
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_Any() {
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_Class() {
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_ConfigType() {
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Action() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Assignment() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CancelTimer() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Controller() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CreateTimer() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Decision() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Description() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_EndState() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Event() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ExceptionHandler() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Fork() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Join() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Mail() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MailNode() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Node() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ProcessDefinition() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ProcessState() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Recipients() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Script() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StartState() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_State() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Subject() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SuperState() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Swimlane() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Task() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TaskNode() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Template() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Text() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Timer() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_To() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Transition() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Variable() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEndStateType() {
		return endStateTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndStateType_Group() {
		return (EAttribute)endStateTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndStateType_Description() {
		return (EAttribute)endStateTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEndStateType_Event() {
		return (EReference)endStateTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEndStateType_ExceptionHandler() {
		return (EReference)endStateTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndStateType_EndCompleteProcess() {
		return (EAttribute)endStateTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndStateType_Name() {
		return (EAttribute)endStateTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventType() {
		return eventTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventType_ActionElements() {
		return (EAttribute)eventTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventType_Action() {
		return (EReference)eventTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventType_Script() {
		return (EReference)eventTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventType_CreateTimer() {
		return (EReference)eventTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventType_CancelTimer() {
		return (EReference)eventTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEventType_Mail() {
		return (EReference)eventTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEventType_Type() {
		return (EAttribute)eventTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExceptionHandlerType() {
		return exceptionHandlerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExceptionHandlerType_Group() {
		return (EAttribute)exceptionHandlerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExceptionHandlerType_Action() {
		return (EReference)exceptionHandlerTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExceptionHandlerType_Script() {
		return (EReference)exceptionHandlerTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExceptionHandlerType_ExceptionClass() {
		return (EAttribute)exceptionHandlerTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getForkType() {
		return forkTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForkType_Group() {
		return (EAttribute)forkTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForkType_Script() {
		return (EReference)forkTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForkType_Description() {
		return (EAttribute)forkTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForkType_Event() {
		return (EReference)forkTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForkType_ExceptionHandler() {
		return (EReference)forkTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForkType_Timer() {
		return (EReference)forkTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForkType_Transition() {
		return (EReference)forkTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForkType_Async() {
		return (EAttribute)forkTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForkType_Name() {
		return (EAttribute)forkTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJoinType() {
		return joinTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_NodeContentElements() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_Description() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJoinType_Event() {
		return (EReference)joinTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJoinType_ExceptionHandler() {
		return (EReference)joinTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJoinType_Timer() {
		return (EReference)joinTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJoinType_Transition() {
		return (EReference)joinTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_Async() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJoinType_Name() {
		return (EAttribute)joinTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMailNodeType() {
		return mailNodeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailNodeType_Group() {
		return (EAttribute)mailNodeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailNodeType_Subject() {
		return (EAttribute)mailNodeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailNodeType_Text() {
		return (EAttribute)mailNodeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailNodeType_Description() {
		return (EAttribute)mailNodeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMailNodeType_Event() {
		return (EReference)mailNodeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMailNodeType_ExceptionHandler() {
		return (EReference)mailNodeTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMailNodeType_Timer() {
		return (EReference)mailNodeTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMailNodeType_Transition() {
		return (EReference)mailNodeTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailNodeType_Actors() {
		return (EAttribute)mailNodeTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailNodeType_Async() {
		return (EAttribute)mailNodeTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailNodeType_Name() {
		return (EAttribute)mailNodeTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailNodeType_Subject1() {
		return (EAttribute)mailNodeTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailNodeType_Template() {
		return (EAttribute)mailNodeTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailNodeType_Text1() {
		return (EAttribute)mailNodeTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailNodeType_To() {
		return (EAttribute)mailNodeTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMailType() {
		return mailTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailType_Group() {
		return (EAttribute)mailTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailType_Subject() {
		return (EAttribute)mailTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailType_Text() {
		return (EAttribute)mailTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailType_Actors() {
		return (EAttribute)mailTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailType_Async() {
		return (EAttribute)mailTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailType_Name() {
		return (EAttribute)mailTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailType_Subject1() {
		return (EAttribute)mailTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailType_Template() {
		return (EAttribute)mailTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailType_Text1() {
		return (EAttribute)mailTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMailType_To() {
		return (EAttribute)mailTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNodeType() {
		return nodeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeType_Action() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeType_Script() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeType_CreateTimer() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeType_CancelTimer() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeType_Mail() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodeType_NodeContentElements() {
		return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodeType_Description() {
		return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeType_Event() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeType_ExceptionHandler() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeType_Timer() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeType_Transition() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodeType_Async() {
		return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodeType_Name() {
		return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessDefinitionType() {
		return processDefinitionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessDefinitionType_Group() {
		return (EAttribute)processDefinitionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessDefinitionType_Description() {
		return (EAttribute)processDefinitionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_Swimlane() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_StartState() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_Node() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_State() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_TaskNode() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_SuperState() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_ProcessState() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_Fork() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_Join() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_Decision() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_EndState() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_MailNode() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_Action() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_Script() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_CreateTimer() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_CancelTimer() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_Mail() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_Event() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_ExceptionHandler() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessDefinitionType_Task() {
		return (EReference)processDefinitionTypeEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessDefinitionType_Name() {
		return (EAttribute)processDefinitionTypeEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessStateType() {
		return processStateTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessStateType_Group() {
		return (EAttribute)processStateTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessStateType_SubProcess() {
		return (EReference)processStateTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessStateType_Variable() {
		return (EReference)processStateTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessStateType_Description() {
		return (EAttribute)processStateTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessStateType_Event() {
		return (EReference)processStateTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessStateType_ExceptionHandler() {
		return (EReference)processStateTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessStateType_Timer() {
		return (EReference)processStateTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessStateType_Transition() {
		return (EReference)processStateTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessStateType_Async() {
		return (EAttribute)processStateTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessStateType_Binding() {
		return (EAttribute)processStateTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessStateType_Name() {
		return (EAttribute)processStateTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReminderType() {
		return reminderTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReminderType_Duedate() {
		return (EAttribute)reminderTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReminderType_Repeat() {
		return (EAttribute)reminderTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScriptType() {
		return scriptTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScriptType_Mixed() {
		return (EAttribute)scriptTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScriptType_Any() {
		return (EAttribute)scriptTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScriptType_AcceptPropagatedEvents() {
		return (EAttribute)scriptTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScriptType_Name() {
		return (EAttribute)scriptTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStartStateType() {
		return startStateTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartStateType_Group() {
		return (EAttribute)startStateTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartStateType_Description() {
		return (EAttribute)startStateTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStartStateType_Task() {
		return (EReference)startStateTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStartStateType_Transition() {
		return (EReference)startStateTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStartStateType_Event() {
		return (EReference)startStateTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStartStateType_ExceptionHandler() {
		return (EReference)startStateTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStartStateType_Name() {
		return (EAttribute)startStateTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStateType() {
		return stateTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateType_NodeContentElements() {
		return (EAttribute)stateTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateType_Description() {
		return (EAttribute)stateTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateType_Event() {
		return (EReference)stateTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateType_ExceptionHandler() {
		return (EReference)stateTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateType_Timer() {
		return (EReference)stateTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateType_Transition() {
		return (EReference)stateTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateType_Async() {
		return (EAttribute)stateTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStateType_Name() {
		return (EAttribute)stateTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubProcessType() {
		return subProcessTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_Binding() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_Name() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubProcessType_Version() {
		return (EAttribute)subProcessTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSuperStateType() {
		return superStateTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSuperStateType_Group() {
		return (EAttribute)superStateTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_Node() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_State() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_TaskNode() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_SuperState() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_ProcessState() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_Fork() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_Join() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_Decision() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_EndState() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_MailNode() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSuperStateType_Description() {
		return (EAttribute)superStateTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_Event() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_ExceptionHandler() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_Timer() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperStateType_Transition() {
		return (EReference)superStateTypeEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSuperStateType_Async() {
		return (EAttribute)superStateTypeEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSuperStateType_Name() {
		return (EAttribute)superStateTypeEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwimlaneType() {
		return swimlaneTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwimlaneType_Assignment() {
		return (EReference)swimlaneTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwimlaneType_Name() {
		return (EAttribute)swimlaneTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaskNodeType() {
		return taskNodeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskNodeType_Group() {
		return (EAttribute)taskNodeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskNodeType_Task() {
		return (EReference)taskNodeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskNodeType_Description() {
		return (EAttribute)taskNodeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskNodeType_Event() {
		return (EReference)taskNodeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskNodeType_ExceptionHandler() {
		return (EReference)taskNodeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskNodeType_Timer() {
		return (EReference)taskNodeTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskNodeType_Transition() {
		return (EReference)taskNodeTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskNodeType_Async() {
		return (EAttribute)taskNodeTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskNodeType_CreateTasks() {
		return (EAttribute)taskNodeTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskNodeType_EndTasks() {
		return (EAttribute)taskNodeTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskNodeType_Name() {
		return (EAttribute)taskNodeTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskNodeType_Signal() {
		return (EAttribute)taskNodeTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTaskType() {
		return taskTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskType_Group() {
		return (EAttribute)taskTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskType_Description() {
		return (EAttribute)taskTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskType_Assignment() {
		return (EReference)taskTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskType_Controller() {
		return (EReference)taskTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskType_Event() {
		return (EReference)taskTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskType_Timer() {
		return (EReference)taskTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTaskType_Reminder() {
		return (EReference)taskTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskType_Blocking() {
		return (EAttribute)taskTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskType_Description1() {
		return (EAttribute)taskTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskType_Duedate() {
		return (EAttribute)taskTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskType_Name() {
		return (EAttribute)taskTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskType_Notify() {
		return (EAttribute)taskTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskType_Priority() {
		return (EAttribute)taskTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskType_Signalling() {
		return (EAttribute)taskTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTaskType_Swimlane() {
		return (EAttribute)taskTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimerType() {
		return timerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimerType_Action() {
		return (EReference)timerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimerType_Script() {
		return (EReference)timerTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimerType_CreateTimer() {
		return (EReference)timerTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimerType_CancelTimer() {
		return (EReference)timerTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimerType_Mail() {
		return (EReference)timerTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_Duedate() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_Name() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_Repeat() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_Transition() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransitionType() {
		return transitionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransitionType_Group() {
		return (EAttribute)transitionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransitionType_Description() {
		return (EAttribute)transitionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionType_Condition() {
		return (EReference)transitionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionType_Action() {
		return (EReference)transitionTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionType_Script() {
		return (EReference)transitionTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionType_CreateTimer() {
		return (EReference)transitionTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionType_CancelTimer() {
		return (EReference)transitionTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionType_Mail() {
		return (EReference)transitionTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitionType_ExceptionHandler() {
		return (EReference)transitionTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransitionType_Name() {
		return (EAttribute)transitionTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransitionType_To() {
		return (EAttribute)transitionTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableType() {
		return variableTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableType_Any() {
		return (EAttribute)variableTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableType_Access() {
		return (EAttribute)variableTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableType_MappedName() {
		return (EAttribute)variableTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableType_Name() {
		return (EAttribute)variableTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBindingType() {
		return bindingTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBooleanType() {
		return booleanTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getConfigType() {
		return configTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPriorityTypeMember0() {
		return priorityTypeMember0EEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSignalType() {
		return signalTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTypeTypeMember1() {
		return typeTypeMember1EEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBindingTypeObject() {
		return bindingTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBooleanTypeObject() {
		return booleanTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getConfigTypeObject() {
		return configTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPriorityType() {
		return priorityTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPriorityTypeMember0Object() {
		return priorityTypeMember0ObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPriorityTypeMember1() {
		return priorityTypeMember1EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPriorityTypeMember1Object() {
		return priorityTypeMember1ObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSignalTypeObject() {
		return signalTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeType() {
		return typeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeTypeMember0() {
		return typeTypeMember0EDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTypeTypeMember1Object() {
		return typeTypeMember1ObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public jpdl32Factory getjpdl32Factory() {
		return (jpdl32Factory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		actionTypeEClass = createEClass(ACTION_TYPE);
		createEAttribute(actionTypeEClass, ACTION_TYPE__MIXED);
		createEAttribute(actionTypeEClass, ACTION_TYPE__ANY);
		createEAttribute(actionTypeEClass, ACTION_TYPE__ACCEPT_PROPAGATED_EVENTS);
		createEAttribute(actionTypeEClass, ACTION_TYPE__ASYNC);
		createEAttribute(actionTypeEClass, ACTION_TYPE__CLASS);
		createEAttribute(actionTypeEClass, ACTION_TYPE__CONFIG_TYPE);
		createEAttribute(actionTypeEClass, ACTION_TYPE__EXPRESSION);
		createEAttribute(actionTypeEClass, ACTION_TYPE__NAME);
		createEAttribute(actionTypeEClass, ACTION_TYPE__REF_NAME);

		assignmentTypeEClass = createEClass(ASSIGNMENT_TYPE);
		createEAttribute(assignmentTypeEClass, ASSIGNMENT_TYPE__ACTOR_ID);
		createEAttribute(assignmentTypeEClass, ASSIGNMENT_TYPE__EXPRESSION);
		createEAttribute(assignmentTypeEClass, ASSIGNMENT_TYPE__POOLED_ACTORS);

		cancelTimerTypeEClass = createEClass(CANCEL_TIMER_TYPE);
		createEAttribute(cancelTimerTypeEClass, CANCEL_TIMER_TYPE__NAME);

		conditionTypeEClass = createEClass(CONDITION_TYPE);
		createEAttribute(conditionTypeEClass, CONDITION_TYPE__MIXED);
		createEAttribute(conditionTypeEClass, CONDITION_TYPE__GROUP);
		createEAttribute(conditionTypeEClass, CONDITION_TYPE__ANY);
		createEAttribute(conditionTypeEClass, CONDITION_TYPE__EXPRESSION);

		createTimerTypeEClass = createEClass(CREATE_TIMER_TYPE);
		createEReference(createTimerTypeEClass, CREATE_TIMER_TYPE__ACTION);
		createEReference(createTimerTypeEClass, CREATE_TIMER_TYPE__SCRIPT);
		createEAttribute(createTimerTypeEClass, CREATE_TIMER_TYPE__DUEDATE);
		createEAttribute(createTimerTypeEClass, CREATE_TIMER_TYPE__NAME);
		createEAttribute(createTimerTypeEClass, CREATE_TIMER_TYPE__REPEAT);
		createEAttribute(createTimerTypeEClass, CREATE_TIMER_TYPE__TRANSITION);

		decisionTypeEClass = createEClass(DECISION_TYPE);
		createEAttribute(decisionTypeEClass, DECISION_TYPE__GROUP);
		createEAttribute(decisionTypeEClass, DECISION_TYPE__DESCRIPTION);
		createEReference(decisionTypeEClass, DECISION_TYPE__HANDLER);
		createEReference(decisionTypeEClass, DECISION_TYPE__EVENT);
		createEReference(decisionTypeEClass, DECISION_TYPE__EXCEPTION_HANDLER);
		createEReference(decisionTypeEClass, DECISION_TYPE__TRANSITION);
		createEAttribute(decisionTypeEClass, DECISION_TYPE__ASYNC);
		createEAttribute(decisionTypeEClass, DECISION_TYPE__EXPRESSION);
		createEAttribute(decisionTypeEClass, DECISION_TYPE__NAME);

		delegationEClass = createEClass(DELEGATION);
		createEAttribute(delegationEClass, DELEGATION__MIXED);
		createEAttribute(delegationEClass, DELEGATION__ANY);
		createEAttribute(delegationEClass, DELEGATION__CLASS);
		createEAttribute(delegationEClass, DELEGATION__CONFIG_TYPE);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ACTION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ASSIGNMENT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CANCEL_TIMER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONTROLLER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CREATE_TIMER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DECISION);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__DESCRIPTION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__END_STATE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EVENT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EXCEPTION_HANDLER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__FORK);
		createEReference(documentRootEClass, DOCUMENT_ROOT__JOIN);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MAIL);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MAIL_NODE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__NODE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROCESS_DEFINITION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROCESS_STATE);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__RECIPIENTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SCRIPT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__START_STATE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STATE);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__SUBJECT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SUPER_STATE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SWIMLANE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TASK);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TASK_NODE);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__TEMPLATE);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__TEXT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TIMER);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__TO);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TRANSITION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__VARIABLE);

		endStateTypeEClass = createEClass(END_STATE_TYPE);
		createEAttribute(endStateTypeEClass, END_STATE_TYPE__GROUP);
		createEAttribute(endStateTypeEClass, END_STATE_TYPE__DESCRIPTION);
		createEReference(endStateTypeEClass, END_STATE_TYPE__EVENT);
		createEReference(endStateTypeEClass, END_STATE_TYPE__EXCEPTION_HANDLER);
		createEAttribute(endStateTypeEClass, END_STATE_TYPE__END_COMPLETE_PROCESS);
		createEAttribute(endStateTypeEClass, END_STATE_TYPE__NAME);

		eventTypeEClass = createEClass(EVENT_TYPE);
		createEAttribute(eventTypeEClass, EVENT_TYPE__ACTION_ELEMENTS);
		createEReference(eventTypeEClass, EVENT_TYPE__ACTION);
		createEReference(eventTypeEClass, EVENT_TYPE__SCRIPT);
		createEReference(eventTypeEClass, EVENT_TYPE__CREATE_TIMER);
		createEReference(eventTypeEClass, EVENT_TYPE__CANCEL_TIMER);
		createEReference(eventTypeEClass, EVENT_TYPE__MAIL);
		createEAttribute(eventTypeEClass, EVENT_TYPE__TYPE);

		exceptionHandlerTypeEClass = createEClass(EXCEPTION_HANDLER_TYPE);
		createEAttribute(exceptionHandlerTypeEClass, EXCEPTION_HANDLER_TYPE__GROUP);
		createEReference(exceptionHandlerTypeEClass, EXCEPTION_HANDLER_TYPE__ACTION);
		createEReference(exceptionHandlerTypeEClass, EXCEPTION_HANDLER_TYPE__SCRIPT);
		createEAttribute(exceptionHandlerTypeEClass, EXCEPTION_HANDLER_TYPE__EXCEPTION_CLASS);

		forkTypeEClass = createEClass(FORK_TYPE);
		createEAttribute(forkTypeEClass, FORK_TYPE__GROUP);
		createEReference(forkTypeEClass, FORK_TYPE__SCRIPT);
		createEAttribute(forkTypeEClass, FORK_TYPE__DESCRIPTION);
		createEReference(forkTypeEClass, FORK_TYPE__EVENT);
		createEReference(forkTypeEClass, FORK_TYPE__EXCEPTION_HANDLER);
		createEReference(forkTypeEClass, FORK_TYPE__TIMER);
		createEReference(forkTypeEClass, FORK_TYPE__TRANSITION);
		createEAttribute(forkTypeEClass, FORK_TYPE__ASYNC);
		createEAttribute(forkTypeEClass, FORK_TYPE__NAME);

		joinTypeEClass = createEClass(JOIN_TYPE);
		createEAttribute(joinTypeEClass, JOIN_TYPE__NODE_CONTENT_ELEMENTS);
		createEAttribute(joinTypeEClass, JOIN_TYPE__DESCRIPTION);
		createEReference(joinTypeEClass, JOIN_TYPE__EVENT);
		createEReference(joinTypeEClass, JOIN_TYPE__EXCEPTION_HANDLER);
		createEReference(joinTypeEClass, JOIN_TYPE__TIMER);
		createEReference(joinTypeEClass, JOIN_TYPE__TRANSITION);
		createEAttribute(joinTypeEClass, JOIN_TYPE__ASYNC);
		createEAttribute(joinTypeEClass, JOIN_TYPE__NAME);

		mailNodeTypeEClass = createEClass(MAIL_NODE_TYPE);
		createEAttribute(mailNodeTypeEClass, MAIL_NODE_TYPE__GROUP);
		createEAttribute(mailNodeTypeEClass, MAIL_NODE_TYPE__SUBJECT);
		createEAttribute(mailNodeTypeEClass, MAIL_NODE_TYPE__TEXT);
		createEAttribute(mailNodeTypeEClass, MAIL_NODE_TYPE__DESCRIPTION);
		createEReference(mailNodeTypeEClass, MAIL_NODE_TYPE__EVENT);
		createEReference(mailNodeTypeEClass, MAIL_NODE_TYPE__EXCEPTION_HANDLER);
		createEReference(mailNodeTypeEClass, MAIL_NODE_TYPE__TIMER);
		createEReference(mailNodeTypeEClass, MAIL_NODE_TYPE__TRANSITION);
		createEAttribute(mailNodeTypeEClass, MAIL_NODE_TYPE__ACTORS);
		createEAttribute(mailNodeTypeEClass, MAIL_NODE_TYPE__ASYNC);
		createEAttribute(mailNodeTypeEClass, MAIL_NODE_TYPE__NAME);
		createEAttribute(mailNodeTypeEClass, MAIL_NODE_TYPE__SUBJECT1);
		createEAttribute(mailNodeTypeEClass, MAIL_NODE_TYPE__TEMPLATE);
		createEAttribute(mailNodeTypeEClass, MAIL_NODE_TYPE__TEXT1);
		createEAttribute(mailNodeTypeEClass, MAIL_NODE_TYPE__TO);

		mailTypeEClass = createEClass(MAIL_TYPE);
		createEAttribute(mailTypeEClass, MAIL_TYPE__GROUP);
		createEAttribute(mailTypeEClass, MAIL_TYPE__SUBJECT);
		createEAttribute(mailTypeEClass, MAIL_TYPE__TEXT);
		createEAttribute(mailTypeEClass, MAIL_TYPE__ACTORS);
		createEAttribute(mailTypeEClass, MAIL_TYPE__ASYNC);
		createEAttribute(mailTypeEClass, MAIL_TYPE__NAME);
		createEAttribute(mailTypeEClass, MAIL_TYPE__SUBJECT1);
		createEAttribute(mailTypeEClass, MAIL_TYPE__TEMPLATE);
		createEAttribute(mailTypeEClass, MAIL_TYPE__TEXT1);
		createEAttribute(mailTypeEClass, MAIL_TYPE__TO);

		nodeTypeEClass = createEClass(NODE_TYPE);
		createEReference(nodeTypeEClass, NODE_TYPE__ACTION);
		createEReference(nodeTypeEClass, NODE_TYPE__SCRIPT);
		createEReference(nodeTypeEClass, NODE_TYPE__CREATE_TIMER);
		createEReference(nodeTypeEClass, NODE_TYPE__CANCEL_TIMER);
		createEReference(nodeTypeEClass, NODE_TYPE__MAIL);
		createEAttribute(nodeTypeEClass, NODE_TYPE__NODE_CONTENT_ELEMENTS);
		createEAttribute(nodeTypeEClass, NODE_TYPE__DESCRIPTION);
		createEReference(nodeTypeEClass, NODE_TYPE__EVENT);
		createEReference(nodeTypeEClass, NODE_TYPE__EXCEPTION_HANDLER);
		createEReference(nodeTypeEClass, NODE_TYPE__TIMER);
		createEReference(nodeTypeEClass, NODE_TYPE__TRANSITION);
		createEAttribute(nodeTypeEClass, NODE_TYPE__ASYNC);
		createEAttribute(nodeTypeEClass, NODE_TYPE__NAME);

		processDefinitionTypeEClass = createEClass(PROCESS_DEFINITION_TYPE);
		createEAttribute(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__GROUP);
		createEAttribute(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__DESCRIPTION);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__SWIMLANE);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__START_STATE);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__NODE);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__STATE);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__TASK_NODE);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__SUPER_STATE);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__PROCESS_STATE);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__FORK);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__JOIN);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__DECISION);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__END_STATE);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__MAIL_NODE);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__ACTION);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__SCRIPT);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__CREATE_TIMER);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__CANCEL_TIMER);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__MAIL);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__EVENT);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__EXCEPTION_HANDLER);
		createEReference(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__TASK);
		createEAttribute(processDefinitionTypeEClass, PROCESS_DEFINITION_TYPE__NAME);

		processStateTypeEClass = createEClass(PROCESS_STATE_TYPE);
		createEAttribute(processStateTypeEClass, PROCESS_STATE_TYPE__GROUP);
		createEReference(processStateTypeEClass, PROCESS_STATE_TYPE__SUB_PROCESS);
		createEReference(processStateTypeEClass, PROCESS_STATE_TYPE__VARIABLE);
		createEAttribute(processStateTypeEClass, PROCESS_STATE_TYPE__DESCRIPTION);
		createEReference(processStateTypeEClass, PROCESS_STATE_TYPE__EVENT);
		createEReference(processStateTypeEClass, PROCESS_STATE_TYPE__EXCEPTION_HANDLER);
		createEReference(processStateTypeEClass, PROCESS_STATE_TYPE__TIMER);
		createEReference(processStateTypeEClass, PROCESS_STATE_TYPE__TRANSITION);
		createEAttribute(processStateTypeEClass, PROCESS_STATE_TYPE__ASYNC);
		createEAttribute(processStateTypeEClass, PROCESS_STATE_TYPE__BINDING);
		createEAttribute(processStateTypeEClass, PROCESS_STATE_TYPE__NAME);

		reminderTypeEClass = createEClass(REMINDER_TYPE);
		createEAttribute(reminderTypeEClass, REMINDER_TYPE__DUEDATE);
		createEAttribute(reminderTypeEClass, REMINDER_TYPE__REPEAT);

		scriptTypeEClass = createEClass(SCRIPT_TYPE);
		createEAttribute(scriptTypeEClass, SCRIPT_TYPE__MIXED);
		createEAttribute(scriptTypeEClass, SCRIPT_TYPE__ANY);
		createEAttribute(scriptTypeEClass, SCRIPT_TYPE__ACCEPT_PROPAGATED_EVENTS);
		createEAttribute(scriptTypeEClass, SCRIPT_TYPE__NAME);

		startStateTypeEClass = createEClass(START_STATE_TYPE);
		createEAttribute(startStateTypeEClass, START_STATE_TYPE__GROUP);
		createEAttribute(startStateTypeEClass, START_STATE_TYPE__DESCRIPTION);
		createEReference(startStateTypeEClass, START_STATE_TYPE__TASK);
		createEReference(startStateTypeEClass, START_STATE_TYPE__TRANSITION);
		createEReference(startStateTypeEClass, START_STATE_TYPE__EVENT);
		createEReference(startStateTypeEClass, START_STATE_TYPE__EXCEPTION_HANDLER);
		createEAttribute(startStateTypeEClass, START_STATE_TYPE__NAME);

		stateTypeEClass = createEClass(STATE_TYPE);
		createEAttribute(stateTypeEClass, STATE_TYPE__NODE_CONTENT_ELEMENTS);
		createEAttribute(stateTypeEClass, STATE_TYPE__DESCRIPTION);
		createEReference(stateTypeEClass, STATE_TYPE__EVENT);
		createEReference(stateTypeEClass, STATE_TYPE__EXCEPTION_HANDLER);
		createEReference(stateTypeEClass, STATE_TYPE__TIMER);
		createEReference(stateTypeEClass, STATE_TYPE__TRANSITION);
		createEAttribute(stateTypeEClass, STATE_TYPE__ASYNC);
		createEAttribute(stateTypeEClass, STATE_TYPE__NAME);

		subProcessTypeEClass = createEClass(SUB_PROCESS_TYPE);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__BINDING);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__NAME);
		createEAttribute(subProcessTypeEClass, SUB_PROCESS_TYPE__VERSION);

		superStateTypeEClass = createEClass(SUPER_STATE_TYPE);
		createEAttribute(superStateTypeEClass, SUPER_STATE_TYPE__GROUP);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__NODE);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__STATE);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__TASK_NODE);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__SUPER_STATE);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__PROCESS_STATE);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__FORK);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__JOIN);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__DECISION);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__END_STATE);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__MAIL_NODE);
		createEAttribute(superStateTypeEClass, SUPER_STATE_TYPE__DESCRIPTION);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__EVENT);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__EXCEPTION_HANDLER);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__TIMER);
		createEReference(superStateTypeEClass, SUPER_STATE_TYPE__TRANSITION);
		createEAttribute(superStateTypeEClass, SUPER_STATE_TYPE__ASYNC);
		createEAttribute(superStateTypeEClass, SUPER_STATE_TYPE__NAME);

		swimlaneTypeEClass = createEClass(SWIMLANE_TYPE);
		createEReference(swimlaneTypeEClass, SWIMLANE_TYPE__ASSIGNMENT);
		createEAttribute(swimlaneTypeEClass, SWIMLANE_TYPE__NAME);

		taskNodeTypeEClass = createEClass(TASK_NODE_TYPE);
		createEAttribute(taskNodeTypeEClass, TASK_NODE_TYPE__GROUP);
		createEReference(taskNodeTypeEClass, TASK_NODE_TYPE__TASK);
		createEAttribute(taskNodeTypeEClass, TASK_NODE_TYPE__DESCRIPTION);
		createEReference(taskNodeTypeEClass, TASK_NODE_TYPE__EVENT);
		createEReference(taskNodeTypeEClass, TASK_NODE_TYPE__EXCEPTION_HANDLER);
		createEReference(taskNodeTypeEClass, TASK_NODE_TYPE__TIMER);
		createEReference(taskNodeTypeEClass, TASK_NODE_TYPE__TRANSITION);
		createEAttribute(taskNodeTypeEClass, TASK_NODE_TYPE__ASYNC);
		createEAttribute(taskNodeTypeEClass, TASK_NODE_TYPE__CREATE_TASKS);
		createEAttribute(taskNodeTypeEClass, TASK_NODE_TYPE__END_TASKS);
		createEAttribute(taskNodeTypeEClass, TASK_NODE_TYPE__NAME);
		createEAttribute(taskNodeTypeEClass, TASK_NODE_TYPE__SIGNAL);

		taskTypeEClass = createEClass(TASK_TYPE);
		createEAttribute(taskTypeEClass, TASK_TYPE__GROUP);
		createEAttribute(taskTypeEClass, TASK_TYPE__DESCRIPTION);
		createEReference(taskTypeEClass, TASK_TYPE__ASSIGNMENT);
		createEReference(taskTypeEClass, TASK_TYPE__CONTROLLER);
		createEReference(taskTypeEClass, TASK_TYPE__EVENT);
		createEReference(taskTypeEClass, TASK_TYPE__TIMER);
		createEReference(taskTypeEClass, TASK_TYPE__REMINDER);
		createEAttribute(taskTypeEClass, TASK_TYPE__BLOCKING);
		createEAttribute(taskTypeEClass, TASK_TYPE__DESCRIPTION1);
		createEAttribute(taskTypeEClass, TASK_TYPE__DUEDATE);
		createEAttribute(taskTypeEClass, TASK_TYPE__NAME);
		createEAttribute(taskTypeEClass, TASK_TYPE__NOTIFY);
		createEAttribute(taskTypeEClass, TASK_TYPE__PRIORITY);
		createEAttribute(taskTypeEClass, TASK_TYPE__SIGNALLING);
		createEAttribute(taskTypeEClass, TASK_TYPE__SWIMLANE);

		timerTypeEClass = createEClass(TIMER_TYPE);
		createEReference(timerTypeEClass, TIMER_TYPE__ACTION);
		createEReference(timerTypeEClass, TIMER_TYPE__SCRIPT);
		createEReference(timerTypeEClass, TIMER_TYPE__CREATE_TIMER);
		createEReference(timerTypeEClass, TIMER_TYPE__CANCEL_TIMER);
		createEReference(timerTypeEClass, TIMER_TYPE__MAIL);
		createEAttribute(timerTypeEClass, TIMER_TYPE__DUEDATE);
		createEAttribute(timerTypeEClass, TIMER_TYPE__NAME);
		createEAttribute(timerTypeEClass, TIMER_TYPE__REPEAT);
		createEAttribute(timerTypeEClass, TIMER_TYPE__TRANSITION);

		transitionTypeEClass = createEClass(TRANSITION_TYPE);
		createEAttribute(transitionTypeEClass, TRANSITION_TYPE__GROUP);
		createEAttribute(transitionTypeEClass, TRANSITION_TYPE__DESCRIPTION);
		createEReference(transitionTypeEClass, TRANSITION_TYPE__CONDITION);
		createEReference(transitionTypeEClass, TRANSITION_TYPE__ACTION);
		createEReference(transitionTypeEClass, TRANSITION_TYPE__SCRIPT);
		createEReference(transitionTypeEClass, TRANSITION_TYPE__CREATE_TIMER);
		createEReference(transitionTypeEClass, TRANSITION_TYPE__CANCEL_TIMER);
		createEReference(transitionTypeEClass, TRANSITION_TYPE__MAIL);
		createEReference(transitionTypeEClass, TRANSITION_TYPE__EXCEPTION_HANDLER);
		createEAttribute(transitionTypeEClass, TRANSITION_TYPE__NAME);
		createEAttribute(transitionTypeEClass, TRANSITION_TYPE__TO);

		variableTypeEClass = createEClass(VARIABLE_TYPE);
		createEAttribute(variableTypeEClass, VARIABLE_TYPE__ANY);
		createEAttribute(variableTypeEClass, VARIABLE_TYPE__ACCESS);
		createEAttribute(variableTypeEClass, VARIABLE_TYPE__MAPPED_NAME);
		createEAttribute(variableTypeEClass, VARIABLE_TYPE__NAME);

		// Create enums
		bindingTypeEEnum = createEEnum(BINDING_TYPE);
		booleanTypeEEnum = createEEnum(BOOLEAN_TYPE);
		configTypeEEnum = createEEnum(CONFIG_TYPE);
		priorityTypeMember0EEnum = createEEnum(PRIORITY_TYPE_MEMBER0);
		signalTypeEEnum = createEEnum(SIGNAL_TYPE);
		typeTypeMember1EEnum = createEEnum(TYPE_TYPE_MEMBER1);

		// Create data types
		bindingTypeObjectEDataType = createEDataType(BINDING_TYPE_OBJECT);
		booleanTypeObjectEDataType = createEDataType(BOOLEAN_TYPE_OBJECT);
		configTypeObjectEDataType = createEDataType(CONFIG_TYPE_OBJECT);
		priorityTypeEDataType = createEDataType(PRIORITY_TYPE);
		priorityTypeMember0ObjectEDataType = createEDataType(PRIORITY_TYPE_MEMBER0_OBJECT);
		priorityTypeMember1EDataType = createEDataType(PRIORITY_TYPE_MEMBER1);
		priorityTypeMember1ObjectEDataType = createEDataType(PRIORITY_TYPE_MEMBER1_OBJECT);
		signalTypeObjectEDataType = createEDataType(SIGNAL_TYPE_OBJECT);
		typeTypeEDataType = createEDataType(TYPE_TYPE);
		typeTypeMember0EDataType = createEDataType(TYPE_TYPE_MEMBER0);
		typeTypeMember1ObjectEDataType = createEDataType(TYPE_TYPE_MEMBER1_OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		assignmentTypeEClass.getESuperTypes().add(this.getDelegation());

		// Initialize classes and features; add operations and parameters
		initEClass(actionTypeEClass, ActionType.class, "ActionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActionType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ActionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionType_AcceptPropagatedEvents(), this.getBooleanType(), "acceptPropagatedEvents", "true", 0, 1, ActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionType_Async(), theXMLTypePackage.getString(), "async", null, 0, 1, ActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionType_Class(), theXMLTypePackage.getString(), "class", null, 0, 1, ActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionType_ConfigType(), theXMLTypePackage.getString(), "configType", "field", 0, 1, ActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionType_Expression(), theXMLTypePackage.getString(), "expression", null, 0, 1, ActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActionType_RefName(), theXMLTypePackage.getString(), "refName", null, 0, 1, ActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(assignmentTypeEClass, AssignmentType.class, "AssignmentType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAssignmentType_ActorId(), theXMLTypePackage.getString(), "actorId", null, 0, 1, AssignmentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssignmentType_Expression(), theXMLTypePackage.getString(), "expression", null, 0, 1, AssignmentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAssignmentType_PooledActors(), theXMLTypePackage.getString(), "pooledActors", null, 0, 1, AssignmentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cancelTimerTypeEClass, CancelTimerType.class, "CancelTimerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCancelTimerType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, CancelTimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionTypeEClass, ConditionType.class, "ConditionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConditionType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ConditionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConditionType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ConditionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getConditionType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ConditionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getConditionType_Expression(), theXMLTypePackage.getString(), "expression", null, 0, 1, ConditionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(createTimerTypeEClass, CreateTimerType.class, "CreateTimerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCreateTimerType_Action(), this.getActionType(), null, "action", null, 0, 1, CreateTimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCreateTimerType_Script(), this.getScriptType(), null, "script", null, 0, 1, CreateTimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCreateTimerType_Duedate(), theXMLTypePackage.getString(), "duedate", null, 1, 1, CreateTimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCreateTimerType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, CreateTimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCreateTimerType_Repeat(), theXMLTypePackage.getString(), "repeat", null, 0, 1, CreateTimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCreateTimerType_Transition(), theXMLTypePackage.getString(), "transition", null, 0, 1, CreateTimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(decisionTypeEClass, DecisionType.class, "DecisionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDecisionType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, DecisionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDecisionType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, DecisionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDecisionType_Handler(), this.getDelegation(), null, "handler", null, 0, -1, DecisionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDecisionType_Event(), this.getEventType(), null, "event", null, 0, -1, DecisionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDecisionType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, DecisionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDecisionType_Transition(), this.getTransitionType(), null, "transition", null, 0, -1, DecisionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDecisionType_Async(), theXMLTypePackage.getString(), "async", "false", 0, 1, DecisionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDecisionType_Expression(), theXMLTypePackage.getString(), "expression", null, 0, 1, DecisionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDecisionType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, DecisionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(delegationEClass, Delegation.class, "Delegation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDelegation_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, Delegation.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_Class(), theXMLTypePackage.getString(), "class", null, 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_ConfigType(), theXMLTypePackage.getString(), "configType", "field", 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Action(), this.getActionType(), null, "action", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Assignment(), this.getAssignmentType(), null, "assignment", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_CancelTimer(), this.getCancelTimerType(), null, "cancelTimer", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Controller(), this.getDelegation(), null, "controller", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_CreateTimer(), this.getCreateTimerType(), null, "createTimer", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Decision(), this.getDecisionType(), null, "decision", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Description(), theXMLTypePackage.getString(), "description", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_EndState(), this.getEndStateType(), null, "endState", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Event(), this.getEventType(), null, "event", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Fork(), this.getForkType(), null, "fork", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Join(), this.getJoinType(), null, "join", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Mail(), this.getMailType(), null, "mail", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MailNode(), this.getMailNodeType(), null, "mailNode", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Node(), this.getNodeType(), null, "node", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ProcessDefinition(), this.getProcessDefinitionType(), null, "processDefinition", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ProcessState(), this.getProcessStateType(), null, "processState", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Recipients(), theXMLTypePackage.getString(), "recipients", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Script(), this.getScriptType(), null, "script", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_StartState(), this.getStartStateType(), null, "startState", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_State(), this.getStateType(), null, "state", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Subject(), theXMLTypePackage.getString(), "subject", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SuperState(), this.getSuperStateType(), null, "superState", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Swimlane(), this.getSwimlaneType(), null, "swimlane", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Task(), this.getTaskType(), null, "task", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_TaskNode(), this.getTaskNodeType(), null, "taskNode", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Template(), theXMLTypePackage.getString(), "template", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Text(), theXMLTypePackage.getString(), "text", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Timer(), this.getTimerType(), null, "timer", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_To(), theXMLTypePackage.getString(), "to", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Transition(), this.getTransitionType(), null, "transition", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Variable(), this.getVariableType(), null, "variable", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(endStateTypeEClass, EndStateType.class, "EndStateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEndStateType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, EndStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndStateType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, EndStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEndStateType_Event(), this.getEventType(), null, "event", null, 0, -1, EndStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEndStateType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, EndStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndStateType_EndCompleteProcess(), this.getBooleanType(), "endCompleteProcess", "false", 0, 1, EndStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndStateType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, EndStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventTypeEClass, EventType.class, "EventType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEventType_ActionElements(), ecorePackage.getEFeatureMapEntry(), "actionElements", null, 0, -1, EventType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventType_Action(), this.getActionType(), null, "action", null, 0, -1, EventType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEventType_Script(), this.getScriptType(), null, "script", null, 0, -1, EventType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEventType_CreateTimer(), this.getCreateTimerType(), null, "createTimer", null, 0, -1, EventType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEventType_CancelTimer(), this.getCancelTimerType(), null, "cancelTimer", null, 0, -1, EventType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEventType_Mail(), this.getMailType(), null, "mail", null, 0, -1, EventType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventType_Type(), this.getTypeType(), "type", null, 1, 1, EventType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exceptionHandlerTypeEClass, ExceptionHandlerType.class, "ExceptionHandlerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExceptionHandlerType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ExceptionHandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExceptionHandlerType_Action(), this.getActionType(), null, "action", null, 0, -1, ExceptionHandlerType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getExceptionHandlerType_Script(), this.getScriptType(), null, "script", null, 0, -1, ExceptionHandlerType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getExceptionHandlerType_ExceptionClass(), theXMLTypePackage.getString(), "exceptionClass", null, 0, 1, ExceptionHandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(forkTypeEClass, ForkType.class, "ForkType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getForkType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ForkType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getForkType_Script(), this.getScriptType(), null, "script", null, 0, -1, ForkType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getForkType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, ForkType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getForkType_Event(), this.getEventType(), null, "event", null, 0, -1, ForkType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getForkType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, ForkType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getForkType_Timer(), this.getTimerType(), null, "timer", null, 0, -1, ForkType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getForkType_Transition(), this.getTransitionType(), null, "transition", null, 0, -1, ForkType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getForkType_Async(), theXMLTypePackage.getString(), "async", "false", 0, 1, ForkType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getForkType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, ForkType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(joinTypeEClass, JoinType.class, "JoinType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJoinType_NodeContentElements(), ecorePackage.getEFeatureMapEntry(), "nodeContentElements", null, 0, -1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJoinType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, JoinType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getJoinType_Event(), this.getEventType(), null, "event", null, 0, -1, JoinType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getJoinType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, JoinType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getJoinType_Timer(), this.getTimerType(), null, "timer", null, 0, -1, JoinType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getJoinType_Transition(), this.getTransitionType(), null, "transition", null, 0, -1, JoinType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getJoinType_Async(), theXMLTypePackage.getString(), "async", "false", 0, 1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJoinType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, JoinType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mailNodeTypeEClass, MailNodeType.class, "MailNodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMailNodeType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, MailNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailNodeType_Subject(), theXMLTypePackage.getString(), "subject", null, 0, -1, MailNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailNodeType_Text(), theXMLTypePackage.getString(), "text", null, 0, -1, MailNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailNodeType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, MailNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMailNodeType_Event(), this.getEventType(), null, "event", null, 0, -1, MailNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMailNodeType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, MailNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMailNodeType_Timer(), this.getTimerType(), null, "timer", null, 0, -1, MailNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMailNodeType_Transition(), this.getTransitionType(), null, "transition", null, 0, -1, MailNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailNodeType_Actors(), theXMLTypePackage.getString(), "actors", null, 0, 1, MailNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailNodeType_Async(), theXMLTypePackage.getString(), "async", "false", 0, 1, MailNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailNodeType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, MailNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailNodeType_Subject1(), theXMLTypePackage.getString(), "subject1", null, 0, 1, MailNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailNodeType_Template(), theXMLTypePackage.getString(), "template", null, 0, 1, MailNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailNodeType_Text1(), theXMLTypePackage.getString(), "text1", null, 0, 1, MailNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailNodeType_To(), theXMLTypePackage.getString(), "to", null, 0, 1, MailNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mailTypeEClass, MailType.class, "MailType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMailType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, MailType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailType_Subject(), theXMLTypePackage.getString(), "subject", null, 0, -1, MailType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailType_Text(), theXMLTypePackage.getString(), "text", null, 0, -1, MailType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailType_Actors(), theXMLTypePackage.getString(), "actors", null, 0, 1, MailType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailType_Async(), theXMLTypePackage.getString(), "async", "false", 0, 1, MailType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, MailType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailType_Subject1(), theXMLTypePackage.getString(), "subject1", null, 0, 1, MailType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailType_Template(), theXMLTypePackage.getString(), "template", null, 0, 1, MailType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailType_Text1(), theXMLTypePackage.getString(), "text1", null, 0, 1, MailType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailType_To(), theXMLTypePackage.getString(), "to", null, 0, 1, MailType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodeTypeEClass, NodeType.class, "NodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNodeType_Action(), this.getActionType(), null, "action", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodeType_Script(), this.getScriptType(), null, "script", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodeType_CreateTimer(), this.getCreateTimerType(), null, "createTimer", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodeType_CancelTimer(), this.getCancelTimerType(), null, "cancelTimer", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodeType_Mail(), this.getMailType(), null, "mail", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeType_NodeContentElements(), ecorePackage.getEFeatureMapEntry(), "nodeContentElements", null, 0, -1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, NodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodeType_Event(), this.getEventType(), null, "event", null, 0, -1, NodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodeType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, NodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodeType_Timer(), this.getTimerType(), null, "timer", null, 0, -1, NodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodeType_Transition(), this.getTransitionType(), null, "transition", null, 0, -1, NodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeType_Async(), theXMLTypePackage.getString(), "async", "false", 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processDefinitionTypeEClass, ProcessDefinitionType.class, "ProcessDefinitionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessDefinitionType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ProcessDefinitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessDefinitionType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_Swimlane(), this.getSwimlaneType(), null, "swimlane", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_StartState(), this.getStartStateType(), null, "startState", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_Node(), this.getNodeType(), null, "node", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_State(), this.getStateType(), null, "state", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_TaskNode(), this.getTaskNodeType(), null, "taskNode", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_SuperState(), this.getSuperStateType(), null, "superState", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_ProcessState(), this.getProcessStateType(), null, "processState", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_Fork(), this.getForkType(), null, "fork", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_Join(), this.getJoinType(), null, "join", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_Decision(), this.getDecisionType(), null, "decision", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_EndState(), this.getEndStateType(), null, "endState", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_MailNode(), this.getMailNodeType(), null, "mailNode", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_Action(), this.getActionType(), null, "action", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_Script(), this.getScriptType(), null, "script", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_CreateTimer(), this.getCreateTimerType(), null, "createTimer", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_CancelTimer(), this.getCancelTimerType(), null, "cancelTimer", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_Mail(), this.getMailType(), null, "mail", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_Event(), this.getEventType(), null, "event", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessDefinitionType_Task(), this.getTaskType(), null, "task", null, 0, -1, ProcessDefinitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessDefinitionType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, ProcessDefinitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processStateTypeEClass, ProcessStateType.class, "ProcessStateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessStateType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ProcessStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessStateType_SubProcess(), this.getSubProcessType(), null, "subProcess", null, 0, -1, ProcessStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessStateType_Variable(), this.getVariableType(), null, "variable", null, 0, -1, ProcessStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessStateType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, ProcessStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessStateType_Event(), this.getEventType(), null, "event", null, 0, -1, ProcessStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessStateType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, ProcessStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessStateType_Timer(), this.getTimerType(), null, "timer", null, 0, -1, ProcessStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProcessStateType_Transition(), this.getTransitionType(), null, "transition", null, 0, -1, ProcessStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessStateType_Async(), theXMLTypePackage.getString(), "async", "false", 0, 1, ProcessStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessStateType_Binding(), this.getBindingType(), "binding", null, 0, 1, ProcessStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessStateType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, ProcessStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reminderTypeEClass, ReminderType.class, "ReminderType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReminderType_Duedate(), theXMLTypePackage.getString(), "duedate", null, 1, 1, ReminderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReminderType_Repeat(), theXMLTypePackage.getString(), "repeat", null, 0, 1, ReminderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scriptTypeEClass, ScriptType.class, "ScriptType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScriptType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ScriptType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ScriptType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptType_AcceptPropagatedEvents(), this.getBooleanType(), "acceptPropagatedEvents", "true", 0, 1, ScriptType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ScriptType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(startStateTypeEClass, StartStateType.class, "StartStateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStartStateType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, StartStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartStateType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, StartStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStartStateType_Task(), this.getTaskType(), null, "task", null, 0, -1, StartStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStartStateType_Transition(), this.getTransitionType(), null, "transition", null, 0, -1, StartStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStartStateType_Event(), this.getEventType(), null, "event", null, 0, -1, StartStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStartStateType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, StartStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartStateType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, StartStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateTypeEClass, StateType.class, "StateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStateType_NodeContentElements(), ecorePackage.getEFeatureMapEntry(), "nodeContentElements", null, 0, -1, StateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, StateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStateType_Event(), this.getEventType(), null, "event", null, 0, -1, StateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStateType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, StateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStateType_Timer(), this.getTimerType(), null, "timer", null, 0, -1, StateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getStateType_Transition(), this.getTransitionType(), null, "transition", null, 0, -1, StateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateType_Async(), theXMLTypePackage.getString(), "async", "false", 0, 1, StateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStateType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, StateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(subProcessTypeEClass, SubProcessType.class, "SubProcessType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSubProcessType_Binding(), theXMLTypePackage.getString(), "binding", null, 0, 1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubProcessType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubProcessType_Version(), theXMLTypePackage.getInteger(), "version", null, 0, 1, SubProcessType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(superStateTypeEClass, SuperStateType.class, "SuperStateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSuperStateType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, SuperStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_Node(), this.getNodeType(), null, "node", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_State(), this.getStateType(), null, "state", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_TaskNode(), this.getTaskNodeType(), null, "taskNode", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_SuperState(), this.getSuperStateType(), null, "superState", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_ProcessState(), this.getProcessStateType(), null, "processState", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_Fork(), this.getForkType(), null, "fork", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_Join(), this.getJoinType(), null, "join", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_Decision(), this.getDecisionType(), null, "decision", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_EndState(), this.getEndStateType(), null, "endState", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_MailNode(), this.getMailNodeType(), null, "mailNode", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSuperStateType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_Event(), this.getEventType(), null, "event", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_Timer(), this.getTimerType(), null, "timer", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSuperStateType_Transition(), this.getTransitionType(), null, "transition", null, 0, -1, SuperStateType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSuperStateType_Async(), theXMLTypePackage.getString(), "async", "false", 0, 1, SuperStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSuperStateType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, SuperStateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(swimlaneTypeEClass, SwimlaneType.class, "SwimlaneType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSwimlaneType_Assignment(), this.getAssignmentType(), null, "assignment", null, 0, 1, SwimlaneType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwimlaneType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, SwimlaneType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taskNodeTypeEClass, TaskNodeType.class, "TaskNodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTaskNodeType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TaskNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTaskNodeType_Task(), this.getTaskType(), null, "task", null, 0, -1, TaskNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskNodeType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, TaskNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTaskNodeType_Event(), this.getEventType(), null, "event", null, 0, -1, TaskNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTaskNodeType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, TaskNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTaskNodeType_Timer(), this.getTimerType(), null, "timer", null, 0, -1, TaskNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTaskNodeType_Transition(), this.getTransitionType(), null, "transition", null, 0, -1, TaskNodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskNodeType_Async(), theXMLTypePackage.getString(), "async", "false", 0, 1, TaskNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskNodeType_CreateTasks(), this.getBooleanType(), "createTasks", "true", 0, 1, TaskNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskNodeType_EndTasks(), this.getBooleanType(), "endTasks", "false", 0, 1, TaskNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskNodeType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, TaskNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskNodeType_Signal(), this.getSignalType(), "signal", "last", 0, 1, TaskNodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taskTypeEClass, TaskType.class, "TaskType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTaskType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, TaskType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTaskType_Assignment(), this.getAssignmentType(), null, "assignment", null, 0, -1, TaskType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTaskType_Controller(), this.getDelegation(), null, "controller", null, 0, -1, TaskType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTaskType_Event(), this.getEventType(), null, "event", null, 0, -1, TaskType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTaskType_Timer(), this.getTimerType(), null, "timer", null, 0, -1, TaskType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTaskType_Reminder(), this.getReminderType(), null, "reminder", null, 0, -1, TaskType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskType_Blocking(), this.getBooleanType(), "blocking", "false", 0, 1, TaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskType_Description1(), theXMLTypePackage.getString(), "description1", null, 0, 1, TaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskType_Duedate(), theXMLTypePackage.getString(), "duedate", null, 0, 1, TaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, TaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskType_Notify(), this.getBooleanType(), "notify", "false", 0, 1, TaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskType_Priority(), this.getPriorityType(), "priority", "normal", 0, 1, TaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskType_Signalling(), this.getBooleanType(), "signalling", "true", 0, 1, TaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskType_Swimlane(), theXMLTypePackage.getString(), "swimlane", null, 0, 1, TaskType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(timerTypeEClass, TimerType.class, "TimerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTimerType_Action(), this.getActionType(), null, "action", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTimerType_Script(), this.getScriptType(), null, "script", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTimerType_CreateTimer(), this.getCreateTimerType(), null, "createTimer", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTimerType_CancelTimer(), this.getCancelTimerType(), null, "cancelTimer", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTimerType_Mail(), this.getMailType(), null, "mail", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerType_Duedate(), theXMLTypePackage.getString(), "duedate", null, 1, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerType_Repeat(), theXMLTypePackage.getString(), "repeat", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimerType_Transition(), theXMLTypePackage.getString(), "transition", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionTypeEClass, TransitionType.class, "TransitionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransitionType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, TransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransitionType_Description(), theXMLTypePackage.getString(), "description", null, 0, -1, TransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTransitionType_Condition(), this.getConditionType(), null, "condition", null, 0, -1, TransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTransitionType_Action(), this.getActionType(), null, "action", null, 0, -1, TransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTransitionType_Script(), this.getScriptType(), null, "script", null, 0, -1, TransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTransitionType_CreateTimer(), this.getCreateTimerType(), null, "createTimer", null, 0, -1, TransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTransitionType_CancelTimer(), this.getCancelTimerType(), null, "cancelTimer", null, 0, -1, TransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTransitionType_Mail(), this.getMailType(), null, "mail", null, 0, -1, TransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTransitionType_ExceptionHandler(), this.getExceptionHandlerType(), null, "exceptionHandler", null, 0, -1, TransitionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransitionType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, TransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransitionType_To(), theXMLTypePackage.getString(), "to", null, 1, 1, TransitionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variableTypeEClass, VariableType.class, "VariableType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariableType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, VariableType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableType_Access(), theXMLTypePackage.getString(), "access", "read,write", 0, 1, VariableType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableType_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, VariableType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, VariableType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(bindingTypeEEnum, BindingType.class, "BindingType");
		addEEnumLiteral(bindingTypeEEnum, BindingType.LATE);
		addEEnumLiteral(bindingTypeEEnum, BindingType.EARLY);

		initEEnum(booleanTypeEEnum, BooleanType.class, "BooleanType");
		addEEnumLiteral(booleanTypeEEnum, BooleanType.YES);
		addEEnumLiteral(booleanTypeEEnum, BooleanType.NO);
		addEEnumLiteral(booleanTypeEEnum, BooleanType.TRUE);
		addEEnumLiteral(booleanTypeEEnum, BooleanType.FALSE);
		addEEnumLiteral(booleanTypeEEnum, BooleanType.ON);
		addEEnumLiteral(booleanTypeEEnum, BooleanType.OFF);

		initEEnum(configTypeEEnum, ConfigType.class, "ConfigType");
		addEEnumLiteral(configTypeEEnum, ConfigType.FIELD);
		addEEnumLiteral(configTypeEEnum, ConfigType.BEAN);
		addEEnumLiteral(configTypeEEnum, ConfigType.CONSTRUCTOR);
		addEEnumLiteral(configTypeEEnum, ConfigType.CONFIGURATION_PROPERTY);

		initEEnum(priorityTypeMember0EEnum, PriorityTypeMember0.class, "PriorityTypeMember0");
		addEEnumLiteral(priorityTypeMember0EEnum, PriorityTypeMember0.HIGHEST);
		addEEnumLiteral(priorityTypeMember0EEnum, PriorityTypeMember0.HIGH);
		addEEnumLiteral(priorityTypeMember0EEnum, PriorityTypeMember0.NORMAL);
		addEEnumLiteral(priorityTypeMember0EEnum, PriorityTypeMember0.LOW);
		addEEnumLiteral(priorityTypeMember0EEnum, PriorityTypeMember0.LOWEST);

		initEEnum(signalTypeEEnum, SignalType.class, "SignalType");
		addEEnumLiteral(signalTypeEEnum, SignalType.UNSYNCHRONIZED);
		addEEnumLiteral(signalTypeEEnum, SignalType.NEVER);
		addEEnumLiteral(signalTypeEEnum, SignalType.FIRST);
		addEEnumLiteral(signalTypeEEnum, SignalType.FIRST_WAIT);
		addEEnumLiteral(signalTypeEEnum, SignalType.LAST);
		addEEnumLiteral(signalTypeEEnum, SignalType.LAST_WAIT);

		initEEnum(typeTypeMember1EEnum, TypeTypeMember1.class, "TypeTypeMember1");
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.NODE_ENTER);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.NODE_LEAVE);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.PROCESS_START);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.PROCESS_END);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.TASK_CREATE);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.TASK_ASSIGN);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.TASK_START);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.TASK_END);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.BEFORE_SIGNAL);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.AFTER_SIGNAL);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.SUPERSTATE_ENTER);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.SUPERSTATE_LEAVE);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.TIMER_CREATE);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.SUBPROCESS_CREATED);
		addEEnumLiteral(typeTypeMember1EEnum, TypeTypeMember1.SUBPROCESS_END);

		// Initialize data types
		initEDataType(bindingTypeObjectEDataType, BindingType.class, "BindingTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(booleanTypeObjectEDataType, BooleanType.class, "BooleanTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(configTypeObjectEDataType, ConfigType.class, "ConfigTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(priorityTypeEDataType, Object.class, "PriorityType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(priorityTypeMember0ObjectEDataType, PriorityTypeMember0.class, "PriorityTypeMember0Object", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(priorityTypeMember1EDataType, int.class, "PriorityTypeMember1", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(priorityTypeMember1ObjectEDataType, Integer.class, "PriorityTypeMember1Object", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(signalTypeObjectEDataType, SignalType.class, "SignalTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(typeTypeEDataType, Object.class, "TypeType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(typeTypeMember0EDataType, String.class, "TypeTypeMember0", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(typeTypeMember1ObjectEDataType, TypeTypeMember1.class, "TypeTypeMember1Object", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (this, 
		   source, 
		   new String[] {
			 "qualified", "false"
		   });		
		addAnnotation
		  (actionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "action_._type",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getActionType_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getActionType_Any(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "##any",
			 "name", ":1",
			 "processing", "lax"
		   });		
		addAnnotation
		  (getActionType_AcceptPropagatedEvents(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "accept-propagated-events"
		   });		
		addAnnotation
		  (getActionType_Async(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "async"
		   });		
		addAnnotation
		  (getActionType_Class(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class"
		   });		
		addAnnotation
		  (getActionType_ConfigType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "config-type"
		   });		
		addAnnotation
		  (getActionType_Expression(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "expression"
		   });		
		addAnnotation
		  (getActionType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getActionType_RefName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "ref-name"
		   });		
		addAnnotation
		  (assignmentTypeEClass, 
		   source, 
		   new String[] {
			 "name", "assignment_._type",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getAssignmentType_ActorId(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "actor-id"
		   });		
		addAnnotation
		  (getAssignmentType_Expression(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "expression"
		   });		
		addAnnotation
		  (getAssignmentType_PooledActors(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "pooled-actors"
		   });		
		addAnnotation
		  (bindingTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "bindingType"
		   });		
		addAnnotation
		  (bindingTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "bindingType:Object",
			 "baseType", "bindingType"
		   });		
		addAnnotation
		  (booleanTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "booleanType"
		   });		
		addAnnotation
		  (booleanTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "booleanType:Object",
			 "baseType", "booleanType"
		   });		
		addAnnotation
		  (cancelTimerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "cancel-timer_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getCancelTimerType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (conditionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "condition_._type",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getConditionType_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getConditionType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:1"
		   });		
		addAnnotation
		  (getConditionType_Any(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "##any",
			 "name", ":2",
			 "processing", "lax",
			 "group", "#group:1"
		   });		
		addAnnotation
		  (getConditionType_Expression(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "expression"
		   });		
		addAnnotation
		  (configTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "configType"
		   });		
		addAnnotation
		  (configTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "configType:Object",
			 "baseType", "configType"
		   });		
		addAnnotation
		  (createTimerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "create-timer_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getCreateTimerType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getCreateTimerType_Script(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "script",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getCreateTimerType_Duedate(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "duedate"
		   });		
		addAnnotation
		  (getCreateTimerType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getCreateTimerType_Repeat(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "repeat"
		   });		
		addAnnotation
		  (getCreateTimerType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "transition"
		   });		
		addAnnotation
		  (decisionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "decision_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getDecisionType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getDecisionType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getDecisionType_Handler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "handler",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getDecisionType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getDecisionType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getDecisionType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transition",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getDecisionType_Async(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "async"
		   });		
		addAnnotation
		  (getDecisionType_Expression(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "expression"
		   });		
		addAnnotation
		  (getDecisionType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (delegationEClass, 
		   source, 
		   new String[] {
			 "name", "delegation",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDelegation_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDelegation_Any(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "##any",
			 "name", ":1",
			 "processing", "lax"
		   });		
		addAnnotation
		  (getDelegation_Class(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class"
		   });		
		addAnnotation
		  (getDelegation_ConfigType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "config-type"
		   });		
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Assignment(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "assignment",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_CancelTimer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "cancel-timer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Controller(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "controller",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_CreateTimer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "create-timer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Decision(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "decision",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_EndState(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "end-state",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Fork(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "fork",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Join(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "join",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Mail(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mail",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_MailNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mail-node",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Node(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "node",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ProcessDefinition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "process-definition",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ProcessState(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "process-state",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Recipients(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "recipients",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Script(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "script",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_StartState(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "start-state",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_State(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "state",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Subject(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "subject",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_SuperState(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "super-state",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Swimlane(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "swimlane",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Task(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "task",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_TaskNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "task-node",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Template(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "template",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Text(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "text",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_To(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "to",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transition",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Variable(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "variable",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (endStateTypeEClass, 
		   source, 
		   new String[] {
			 "name", "end-state_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getEndStateType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getEndStateType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEndStateType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEndStateType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getEndStateType_EndCompleteProcess(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "end-complete-process"
		   });		
		addAnnotation
		  (getEndStateType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (eventTypeEClass, 
		   source, 
		   new String[] {
			 "name", "event_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getEventType_ActionElements(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "ActionElements:0"
		   });		
		addAnnotation
		  (getEventType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace",
			 "group", "#ActionElements:0"
		   });		
		addAnnotation
		  (getEventType_Script(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "script",
			 "namespace", "##targetNamespace",
			 "group", "#ActionElements:0"
		   });		
		addAnnotation
		  (getEventType_CreateTimer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "create-timer",
			 "namespace", "##targetNamespace",
			 "group", "#ActionElements:0"
		   });		
		addAnnotation
		  (getEventType_CancelTimer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "cancel-timer",
			 "namespace", "##targetNamespace",
			 "group", "#ActionElements:0"
		   });		
		addAnnotation
		  (getEventType_Mail(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mail",
			 "namespace", "##targetNamespace",
			 "group", "#ActionElements:0"
		   });		
		addAnnotation
		  (getEventType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (exceptionHandlerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "exception-handler_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getExceptionHandlerType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getExceptionHandlerType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getExceptionHandlerType_Script(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "script",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getExceptionHandlerType_ExceptionClass(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "exception-class"
		   });		
		addAnnotation
		  (forkTypeEClass, 
		   source, 
		   new String[] {
			 "name", "fork_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getForkType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getForkType_Script(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "script",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getForkType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getForkType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getForkType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getForkType_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timer",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getForkType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transition",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getForkType_Async(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "async"
		   });		
		addAnnotation
		  (getForkType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (joinTypeEClass, 
		   source, 
		   new String[] {
			 "name", "join_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getJoinType_NodeContentElements(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "NodeContentElements:0"
		   });		
		addAnnotation
		  (getJoinType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:0"
		   });		
		addAnnotation
		  (getJoinType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:0"
		   });		
		addAnnotation
		  (getJoinType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:0"
		   });		
		addAnnotation
		  (getJoinType_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timer",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:0"
		   });		
		addAnnotation
		  (getJoinType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transition",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:0"
		   });		
		addAnnotation
		  (getJoinType_Async(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "async"
		   });		
		addAnnotation
		  (getJoinType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (mailNodeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "mail-node_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getMailNodeType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getMailNodeType_Subject(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "subject",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailNodeType_Text(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "text",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailNodeType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailNodeType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailNodeType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailNodeType_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timer",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailNodeType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transition",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailNodeType_Actors(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "actors"
		   });		
		addAnnotation
		  (getMailNodeType_Async(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "async"
		   });		
		addAnnotation
		  (getMailNodeType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getMailNodeType_Subject1(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "subject"
		   });		
		addAnnotation
		  (getMailNodeType_Template(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "template"
		   });		
		addAnnotation
		  (getMailNodeType_Text1(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "text"
		   });		
		addAnnotation
		  (getMailNodeType_To(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "to"
		   });		
		addAnnotation
		  (mailTypeEClass, 
		   source, 
		   new String[] {
			 "name", "mail_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getMailType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getMailType_Subject(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "subject",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailType_Text(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "text",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getMailType_Actors(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "actors"
		   });		
		addAnnotation
		  (getMailType_Async(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "async"
		   });		
		addAnnotation
		  (getMailType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getMailType_Subject1(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "subject"
		   });		
		addAnnotation
		  (getMailType_Template(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "template"
		   });		
		addAnnotation
		  (getMailType_Text1(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "text"
		   });		
		addAnnotation
		  (getMailType_To(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "to"
		   });		
		addAnnotation
		  (nodeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "node_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getNodeType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getNodeType_Script(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "script",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getNodeType_CreateTimer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "create-timer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getNodeType_CancelTimer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "cancel-timer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getNodeType_Mail(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mail",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getNodeType_NodeContentElements(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "NodeContentElements:5"
		   });		
		addAnnotation
		  (getNodeType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:5"
		   });		
		addAnnotation
		  (getNodeType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:5"
		   });		
		addAnnotation
		  (getNodeType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:5"
		   });		
		addAnnotation
		  (getNodeType_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timer",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:5"
		   });		
		addAnnotation
		  (getNodeType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transition",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:5"
		   });		
		addAnnotation
		  (getNodeType_Async(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "async"
		   });		
		addAnnotation
		  (getNodeType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (priorityTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "priorityType",
			 "memberTypes", "priorityType_._member_._0 priorityType_._member_._1"
		   });		
		addAnnotation
		  (priorityTypeMember0EEnum, 
		   source, 
		   new String[] {
			 "name", "priorityType_._member_._0"
		   });		
		addAnnotation
		  (priorityTypeMember0ObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "priorityType_._member_._0:Object",
			 "baseType", "priorityType_._member_._0"
		   });		
		addAnnotation
		  (priorityTypeMember1EDataType, 
		   source, 
		   new String[] {
			 "name", "priorityType_._member_._1",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#int"
		   });		
		addAnnotation
		  (priorityTypeMember1ObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "priorityType_._member_._1:Object",
			 "baseType", "priorityType_._member_._1"
		   });		
		addAnnotation
		  (processDefinitionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "process-definition_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Swimlane(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "swimlane",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_StartState(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "start-state",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Node(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "node",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_State(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "state",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_TaskNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "task-node",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_SuperState(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "super-state",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_ProcessState(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "process-state",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Fork(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "fork",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Join(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "join",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Decision(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "decision",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_EndState(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "end-state",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_MailNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mail-node",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Script(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "script",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_CreateTimer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "create-timer",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_CancelTimer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "cancel-timer",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Mail(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mail",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Task(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "task",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessDefinitionType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (processStateTypeEClass, 
		   source, 
		   new String[] {
			 "name", "process-state_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getProcessStateType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getProcessStateType_SubProcess(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "sub-process",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessStateType_Variable(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "variable",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessStateType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessStateType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessStateType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessStateType_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timer",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessStateType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transition",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getProcessStateType_Async(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "async"
		   });		
		addAnnotation
		  (getProcessStateType_Binding(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "binding"
		   });		
		addAnnotation
		  (getProcessStateType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (reminderTypeEClass, 
		   source, 
		   new String[] {
			 "name", "reminder_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getReminderType_Duedate(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "duedate"
		   });		
		addAnnotation
		  (getReminderType_Repeat(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "repeat"
		   });		
		addAnnotation
		  (scriptTypeEClass, 
		   source, 
		   new String[] {
			 "name", "script_._type",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getScriptType_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getScriptType_Any(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "##any",
			 "name", ":1",
			 "processing", "lax"
		   });		
		addAnnotation
		  (getScriptType_AcceptPropagatedEvents(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "accept-propagated-events"
		   });		
		addAnnotation
		  (getScriptType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (signalTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "signal_._type"
		   });		
		addAnnotation
		  (signalTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "signal_._type:Object",
			 "baseType", "signal_._type"
		   });		
		addAnnotation
		  (startStateTypeEClass, 
		   source, 
		   new String[] {
			 "name", "start-state_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStartStateType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getStartStateType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getStartStateType_Task(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "task",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getStartStateType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transition",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getStartStateType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getStartStateType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getStartStateType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (stateTypeEClass, 
		   source, 
		   new String[] {
			 "name", "state_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getStateType_NodeContentElements(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "NodeContentElements:0"
		   });		
		addAnnotation
		  (getStateType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:0"
		   });		
		addAnnotation
		  (getStateType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:0"
		   });		
		addAnnotation
		  (getStateType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:0"
		   });		
		addAnnotation
		  (getStateType_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timer",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:0"
		   });		
		addAnnotation
		  (getStateType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transition",
			 "namespace", "##targetNamespace",
			 "group", "#NodeContentElements:0"
		   });		
		addAnnotation
		  (getStateType_Async(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "async"
		   });		
		addAnnotation
		  (getStateType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (subProcessTypeEClass, 
		   source, 
		   new String[] {
			 "name", "sub-process_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getSubProcessType_Binding(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "binding"
		   });		
		addAnnotation
		  (getSubProcessType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getSubProcessType_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "version"
		   });		
		addAnnotation
		  (superStateTypeEClass, 
		   source, 
		   new String[] {
			 "name", "super-state_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSuperStateType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getSuperStateType_Node(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "node",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_State(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "state",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_TaskNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "task-node",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_SuperState(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "super-state",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_ProcessState(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "process-state",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_Fork(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "fork",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_Join(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "join",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_Decision(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "decision",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_EndState(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "end-state",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_MailNode(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mail-node",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timer",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transition",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getSuperStateType_Async(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "async"
		   });		
		addAnnotation
		  (getSuperStateType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (swimlaneTypeEClass, 
		   source, 
		   new String[] {
			 "name", "swimlane_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSwimlaneType_Assignment(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "assignment",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSwimlaneType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (taskNodeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "task-node_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTaskNodeType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTaskNodeType_Task(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "task",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTaskNodeType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTaskNodeType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTaskNodeType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTaskNodeType_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timer",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTaskNodeType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "transition",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTaskNodeType_Async(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "async"
		   });		
		addAnnotation
		  (getTaskNodeType_CreateTasks(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "create-tasks"
		   });		
		addAnnotation
		  (getTaskNodeType_EndTasks(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "end-tasks"
		   });		
		addAnnotation
		  (getTaskNodeType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getTaskNodeType_Signal(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "signal"
		   });		
		addAnnotation
		  (taskTypeEClass, 
		   source, 
		   new String[] {
			 "name", "task_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTaskType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTaskType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTaskType_Assignment(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "assignment",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTaskType_Controller(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "controller",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTaskType_Event(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "event",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTaskType_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "timer",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTaskType_Reminder(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "reminder",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTaskType_Blocking(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "blocking"
		   });		
		addAnnotation
		  (getTaskType_Description1(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "description"
		   });		
		addAnnotation
		  (getTaskType_Duedate(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "duedate"
		   });		
		addAnnotation
		  (getTaskType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getTaskType_Notify(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "notify"
		   });		
		addAnnotation
		  (getTaskType_Priority(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "priority"
		   });		
		addAnnotation
		  (getTaskType_Signalling(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "signalling"
		   });		
		addAnnotation
		  (getTaskType_Swimlane(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "swimlane"
		   });		
		addAnnotation
		  (timerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "timer_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTimerType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTimerType_Script(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "script",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTimerType_CreateTimer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "create-timer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTimerType_CancelTimer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "cancel-timer",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTimerType_Mail(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mail",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getTimerType_Duedate(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "duedate"
		   });		
		addAnnotation
		  (getTimerType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getTimerType_Repeat(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "repeat"
		   });		
		addAnnotation
		  (getTimerType_Transition(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "transition"
		   });		
		addAnnotation
		  (transitionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "transition_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getTransitionType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });		
		addAnnotation
		  (getTransitionType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTransitionType_Condition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "condition",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTransitionType_Action(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "action",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTransitionType_Script(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "script",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTransitionType_CreateTimer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "create-timer",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTransitionType_CancelTimer(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "cancel-timer",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTransitionType_Mail(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mail",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTransitionType_ExceptionHandler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "exception-handler",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });		
		addAnnotation
		  (getTransitionType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getTransitionType_To(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "to"
		   });		
		addAnnotation
		  (typeTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "type_._type",
			 "memberTypes", "type_._type_._member_._0 type_._type_._member_._1"
		   });		
		addAnnotation
		  (typeTypeMember0EDataType, 
		   source, 
		   new String[] {
			 "name", "type_._type_._member_._0",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#string"
		   });		
		addAnnotation
		  (typeTypeMember1EEnum, 
		   source, 
		   new String[] {
			 "name", "type_._type_._member_._1"
		   });		
		addAnnotation
		  (typeTypeMember1ObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "type_._type_._member_._1:Object",
			 "baseType", "type_._type_._member_._1"
		   });		
		addAnnotation
		  (variableTypeEClass, 
		   source, 
		   new String[] {
			 "name", "variable_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getVariableType_Any(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "wildcards", "##any",
			 "name", ":0",
			 "processing", "lax"
		   });		
		addAnnotation
		  (getVariableType_Access(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "access"
		   });		
		addAnnotation
		  (getVariableType_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "mapped-name"
		   });		
		addAnnotation
		  (getVariableType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });
	}

} //jpdl32PackageImpl
