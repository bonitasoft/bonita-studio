/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.jbpm.jpdl32.jpdl32Factory
 * @model kind="package"
 *        extendedMetaData="qualified='false'"
 * @generated
 */
public interface jpdl32Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "jpdl32";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:jbpm.org:jpdl-3.2";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "jpdl32";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	jpdl32Package eINSTANCE = org.jbpm.jpdl32.impl.jpdl32PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.ActionTypeImpl <em>Action Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.ActionTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getActionType()
	 * @generated
	 */
	int ACTION_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__ANY = 1;

	/**
	 * The feature id for the '<em><b>Accept Propagated Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__ACCEPT_PROPAGATED_EVENTS = 2;

	/**
	 * The feature id for the '<em><b>Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__ASYNC = 3;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__CLASS = 4;

	/**
	 * The feature id for the '<em><b>Config Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__CONFIG_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__EXPRESSION = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__NAME = 7;

	/**
	 * The feature id for the '<em><b>Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__REF_NAME = 8;

	/**
	 * The number of structural features of the '<em>Action Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.DelegationImpl <em>Delegation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.DelegationImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getDelegation()
	 * @generated
	 */
	int DELEGATION = 6;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELEGATION__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELEGATION__ANY = 1;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELEGATION__CLASS = 2;

	/**
	 * The feature id for the '<em><b>Config Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELEGATION__CONFIG_TYPE = 3;

	/**
	 * The number of structural features of the '<em>Delegation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELEGATION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.AssignmentTypeImpl <em>Assignment Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.AssignmentTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getAssignmentType()
	 * @generated
	 */
	int ASSIGNMENT_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TYPE__MIXED = DELEGATION__MIXED;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TYPE__ANY = DELEGATION__ANY;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TYPE__CLASS = DELEGATION__CLASS;

	/**
	 * The feature id for the '<em><b>Config Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TYPE__CONFIG_TYPE = DELEGATION__CONFIG_TYPE;

	/**
	 * The feature id for the '<em><b>Actor Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TYPE__ACTOR_ID = DELEGATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TYPE__EXPRESSION = DELEGATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Pooled Actors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TYPE__POOLED_ACTORS = DELEGATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Assignment Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_TYPE_FEATURE_COUNT = DELEGATION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.CancelTimerTypeImpl <em>Cancel Timer Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.CancelTimerTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getCancelTimerType()
	 * @generated
	 */
	int CANCEL_TIMER_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANCEL_TIMER_TYPE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Cancel Timer Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANCEL_TIMER_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.ConditionTypeImpl <em>Condition Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.ConditionTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getConditionType()
	 * @generated
	 */
	int CONDITION_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_TYPE__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_TYPE__GROUP = 1;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_TYPE__ANY = 2;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_TYPE__EXPRESSION = 3;

	/**
	 * The number of structural features of the '<em>Condition Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.CreateTimerTypeImpl <em>Create Timer Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.CreateTimerTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getCreateTimerType()
	 * @generated
	 */
	int CREATE_TIMER_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_TIMER_TYPE__ACTION = 0;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_TIMER_TYPE__SCRIPT = 1;

	/**
	 * The feature id for the '<em><b>Duedate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_TIMER_TYPE__DUEDATE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_TIMER_TYPE__NAME = 3;

	/**
	 * The feature id for the '<em><b>Repeat</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_TIMER_TYPE__REPEAT = 4;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_TIMER_TYPE__TRANSITION = 5;

	/**
	 * The number of structural features of the '<em>Create Timer Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CREATE_TIMER_TYPE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.DecisionTypeImpl <em>Decision Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.DecisionTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getDecisionType()
	 * @generated
	 */
	int DECISION_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TYPE__HANDLER = 2;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TYPE__EVENT = 3;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TYPE__EXCEPTION_HANDLER = 4;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TYPE__TRANSITION = 5;

	/**
	 * The feature id for the '<em><b>Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TYPE__ASYNC = 6;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TYPE__EXPRESSION = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TYPE__NAME = 8;

	/**
	 * The number of structural features of the '<em>Decision Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_TYPE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.DocumentRootImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 7;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ACTION = 3;

	/**
	 * The feature id for the '<em><b>Assignment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ASSIGNMENT = 4;

	/**
	 * The feature id for the '<em><b>Cancel Timer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CANCEL_TIMER = 5;

	/**
	 * The feature id for the '<em><b>Controller</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONTROLLER = 6;

	/**
	 * The feature id for the '<em><b>Create Timer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CREATE_TIMER = 7;

	/**
	 * The feature id for the '<em><b>Decision</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DECISION = 8;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DESCRIPTION = 9;

	/**
	 * The feature id for the '<em><b>End State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__END_STATE = 10;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EVENT = 11;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXCEPTION_HANDLER = 12;

	/**
	 * The feature id for the '<em><b>Fork</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FORK = 13;

	/**
	 * The feature id for the '<em><b>Join</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__JOIN = 14;

	/**
	 * The feature id for the '<em><b>Mail</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MAIL = 15;

	/**
	 * The feature id for the '<em><b>Mail Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MAIL_NODE = 16;

	/**
	 * The feature id for the '<em><b>Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NODE = 17;

	/**
	 * The feature id for the '<em><b>Process Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROCESS_DEFINITION = 18;

	/**
	 * The feature id for the '<em><b>Process State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROCESS_STATE = 19;

	/**
	 * The feature id for the '<em><b>Recipients</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RECIPIENTS = 20;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SCRIPT = 21;

	/**
	 * The feature id for the '<em><b>Start State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__START_STATE = 22;

	/**
	 * The feature id for the '<em><b>State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STATE = 23;

	/**
	 * The feature id for the '<em><b>Subject</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SUBJECT = 24;

	/**
	 * The feature id for the '<em><b>Super State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SUPER_STATE = 25;

	/**
	 * The feature id for the '<em><b>Swimlane</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SWIMLANE = 26;

	/**
	 * The feature id for the '<em><b>Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TASK = 27;

	/**
	 * The feature id for the '<em><b>Task Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TASK_NODE = 28;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TEMPLATE = 29;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TEXT = 30;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TIMER = 31;

	/**
	 * The feature id for the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TO = 32;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TRANSITION = 33;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VARIABLE = 34;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 35;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.EndStateTypeImpl <em>End State Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.EndStateTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getEndStateType()
	 * @generated
	 */
	int END_STATE_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE_TYPE__EVENT = 2;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE_TYPE__EXCEPTION_HANDLER = 3;

	/**
	 * The feature id for the '<em><b>End Complete Process</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE_TYPE__END_COMPLETE_PROCESS = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE_TYPE__NAME = 5;

	/**
	 * The number of structural features of the '<em>End State Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_STATE_TYPE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.EventTypeImpl <em>Event Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.EventTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getEventType()
	 * @generated
	 */
	int EVENT_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Action Elements</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE__ACTION_ELEMENTS = 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE__ACTION = 1;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE__SCRIPT = 2;

	/**
	 * The feature id for the '<em><b>Create Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE__CREATE_TIMER = 3;

	/**
	 * The feature id for the '<em><b>Cancel Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE__CANCEL_TIMER = 4;

	/**
	 * The feature id for the '<em><b>Mail</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE__MAIL = 5;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE__TYPE = 6;

	/**
	 * The number of structural features of the '<em>Event Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.ExceptionHandlerTypeImpl <em>Exception Handler Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.ExceptionHandlerTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getExceptionHandlerType()
	 * @generated
	 */
	int EXCEPTION_HANDLER_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_TYPE__ACTION = 1;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_TYPE__SCRIPT = 2;

	/**
	 * The feature id for the '<em><b>Exception Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_TYPE__EXCEPTION_CLASS = 3;

	/**
	 * The number of structural features of the '<em>Exception Handler Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.ForkTypeImpl <em>Fork Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.ForkTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getForkType()
	 * @generated
	 */
	int FORK_TYPE = 11;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_TYPE__SCRIPT = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_TYPE__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_TYPE__EVENT = 3;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_TYPE__EXCEPTION_HANDLER = 4;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_TYPE__TIMER = 5;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_TYPE__TRANSITION = 6;

	/**
	 * The feature id for the '<em><b>Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_TYPE__ASYNC = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_TYPE__NAME = 8;

	/**
	 * The number of structural features of the '<em>Fork Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_TYPE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.JoinTypeImpl <em>Join Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.JoinTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getJoinType()
	 * @generated
	 */
	int JOIN_TYPE = 12;

	/**
	 * The feature id for the '<em><b>Node Content Elements</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__NODE_CONTENT_ELEMENTS = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__EVENT = 2;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__EXCEPTION_HANDLER = 3;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__TIMER = 4;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__TRANSITION = 5;

	/**
	 * The feature id for the '<em><b>Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__ASYNC = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__NAME = 7;

	/**
	 * The number of structural features of the '<em>Join Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.MailNodeTypeImpl <em>Mail Node Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.MailNodeTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getMailNodeType()
	 * @generated
	 */
	int MAIL_NODE_TYPE = 13;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Subject</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__SUBJECT = 1;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__TEXT = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__DESCRIPTION = 3;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__EVENT = 4;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__EXCEPTION_HANDLER = 5;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__TIMER = 6;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__TRANSITION = 7;

	/**
	 * The feature id for the '<em><b>Actors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__ACTORS = 8;

	/**
	 * The feature id for the '<em><b>Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__ASYNC = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__NAME = 10;

	/**
	 * The feature id for the '<em><b>Subject1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__SUBJECT1 = 11;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__TEMPLATE = 12;

	/**
	 * The feature id for the '<em><b>Text1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__TEXT1 = 13;

	/**
	 * The feature id for the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE__TO = 14;

	/**
	 * The number of structural features of the '<em>Mail Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_NODE_TYPE_FEATURE_COUNT = 15;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.MailTypeImpl <em>Mail Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.MailTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getMailType()
	 * @generated
	 */
	int MAIL_TYPE = 14;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Subject</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_TYPE__SUBJECT = 1;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_TYPE__TEXT = 2;

	/**
	 * The feature id for the '<em><b>Actors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_TYPE__ACTORS = 3;

	/**
	 * The feature id for the '<em><b>Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_TYPE__ASYNC = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_TYPE__NAME = 5;

	/**
	 * The feature id for the '<em><b>Subject1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_TYPE__SUBJECT1 = 6;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_TYPE__TEMPLATE = 7;

	/**
	 * The feature id for the '<em><b>Text1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_TYPE__TEXT1 = 8;

	/**
	 * The feature id for the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_TYPE__TO = 9;

	/**
	 * The number of structural features of the '<em>Mail Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAIL_TYPE_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.NodeTypeImpl <em>Node Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.NodeTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getNodeType()
	 * @generated
	 */
	int NODE_TYPE = 15;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__ACTION = 0;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__SCRIPT = 1;

	/**
	 * The feature id for the '<em><b>Create Timer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__CREATE_TIMER = 2;

	/**
	 * The feature id for the '<em><b>Cancel Timer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__CANCEL_TIMER = 3;

	/**
	 * The feature id for the '<em><b>Mail</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__MAIL = 4;

	/**
	 * The feature id for the '<em><b>Node Content Elements</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__NODE_CONTENT_ELEMENTS = 5;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__DESCRIPTION = 6;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__EVENT = 7;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__EXCEPTION_HANDLER = 8;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__TIMER = 9;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__TRANSITION = 10;

	/**
	 * The feature id for the '<em><b>Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__ASYNC = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE__NAME = 12;

	/**
	 * The number of structural features of the '<em>Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TYPE_FEATURE_COUNT = 13;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl <em>Process Definition Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getProcessDefinitionType()
	 * @generated
	 */
	int PROCESS_DEFINITION_TYPE = 16;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Swimlane</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__SWIMLANE = 2;

	/**
	 * The feature id for the '<em><b>Start State</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__START_STATE = 3;

	/**
	 * The feature id for the '<em><b>Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__NODE = 4;

	/**
	 * The feature id for the '<em><b>State</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__STATE = 5;

	/**
	 * The feature id for the '<em><b>Task Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__TASK_NODE = 6;

	/**
	 * The feature id for the '<em><b>Super State</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__SUPER_STATE = 7;

	/**
	 * The feature id for the '<em><b>Process State</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__PROCESS_STATE = 8;

	/**
	 * The feature id for the '<em><b>Fork</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__FORK = 9;

	/**
	 * The feature id for the '<em><b>Join</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__JOIN = 10;

	/**
	 * The feature id for the '<em><b>Decision</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__DECISION = 11;

	/**
	 * The feature id for the '<em><b>End State</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__END_STATE = 12;

	/**
	 * The feature id for the '<em><b>Mail Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__MAIL_NODE = 13;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__ACTION = 14;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__SCRIPT = 15;

	/**
	 * The feature id for the '<em><b>Create Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__CREATE_TIMER = 16;

	/**
	 * The feature id for the '<em><b>Cancel Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__CANCEL_TIMER = 17;

	/**
	 * The feature id for the '<em><b>Mail</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__MAIL = 18;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__EVENT = 19;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__EXCEPTION_HANDLER = 20;

	/**
	 * The feature id for the '<em><b>Task</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__TASK = 21;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE__NAME = 22;

	/**
	 * The number of structural features of the '<em>Process Definition Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_DEFINITION_TYPE_FEATURE_COUNT = 23;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl <em>Process State Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.ProcessStateTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getProcessStateType()
	 * @generated
	 */
	int PROCESS_STATE_TYPE = 17;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Sub Process</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_TYPE__SUB_PROCESS = 1;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_TYPE__VARIABLE = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_TYPE__DESCRIPTION = 3;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_TYPE__EVENT = 4;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_TYPE__EXCEPTION_HANDLER = 5;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_TYPE__TIMER = 6;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_TYPE__TRANSITION = 7;

	/**
	 * The feature id for the '<em><b>Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_TYPE__ASYNC = 8;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_TYPE__BINDING = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_TYPE__NAME = 10;

	/**
	 * The number of structural features of the '<em>Process State Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STATE_TYPE_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.ReminderTypeImpl <em>Reminder Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.ReminderTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getReminderType()
	 * @generated
	 */
	int REMINDER_TYPE = 18;

	/**
	 * The feature id for the '<em><b>Duedate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMINDER_TYPE__DUEDATE = 0;

	/**
	 * The feature id for the '<em><b>Repeat</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMINDER_TYPE__REPEAT = 1;

	/**
	 * The number of structural features of the '<em>Reminder Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMINDER_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.ScriptTypeImpl <em>Script Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.ScriptTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getScriptType()
	 * @generated
	 */
	int SCRIPT_TYPE = 19;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TYPE__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TYPE__ANY = 1;

	/**
	 * The feature id for the '<em><b>Accept Propagated Events</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TYPE__ACCEPT_PROPAGATED_EVENTS = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TYPE__NAME = 3;

	/**
	 * The number of structural features of the '<em>Script Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.StartStateTypeImpl <em>Start State Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.StartStateTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getStartStateType()
	 * @generated
	 */
	int START_STATE_TYPE = 20;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Task</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE_TYPE__TASK = 2;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE_TYPE__TRANSITION = 3;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE_TYPE__EVENT = 4;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE_TYPE__EXCEPTION_HANDLER = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE_TYPE__NAME = 6;

	/**
	 * The number of structural features of the '<em>Start State Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_STATE_TYPE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.StateTypeImpl <em>State Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.StateTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getStateType()
	 * @generated
	 */
	int STATE_TYPE = 21;

	/**
	 * The feature id for the '<em><b>Node Content Elements</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__NODE_CONTENT_ELEMENTS = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__EVENT = 2;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__EXCEPTION_HANDLER = 3;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__TIMER = 4;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__TRANSITION = 5;

	/**
	 * The feature id for the '<em><b>Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__ASYNC = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__NAME = 7;

	/**
	 * The number of structural features of the '<em>State Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.SubProcessTypeImpl <em>Sub Process Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.SubProcessTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getSubProcessType()
	 * @generated
	 */
	int SUB_PROCESS_TYPE = 22;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__BINDING = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__VERSION = 2;

	/**
	 * The number of structural features of the '<em>Sub Process Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl <em>Super State Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.SuperStateTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getSuperStateType()
	 * @generated
	 */
	int SUPER_STATE_TYPE = 23;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__NODE = 1;

	/**
	 * The feature id for the '<em><b>State</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__STATE = 2;

	/**
	 * The feature id for the '<em><b>Task Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__TASK_NODE = 3;

	/**
	 * The feature id for the '<em><b>Super State</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__SUPER_STATE = 4;

	/**
	 * The feature id for the '<em><b>Process State</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__PROCESS_STATE = 5;

	/**
	 * The feature id for the '<em><b>Fork</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__FORK = 6;

	/**
	 * The feature id for the '<em><b>Join</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__JOIN = 7;

	/**
	 * The feature id for the '<em><b>Decision</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__DECISION = 8;

	/**
	 * The feature id for the '<em><b>End State</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__END_STATE = 9;

	/**
	 * The feature id for the '<em><b>Mail Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__MAIL_NODE = 10;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__DESCRIPTION = 11;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__EVENT = 12;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__EXCEPTION_HANDLER = 13;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__TIMER = 14;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__TRANSITION = 15;

	/**
	 * The feature id for the '<em><b>Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__ASYNC = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE__NAME = 17;

	/**
	 * The number of structural features of the '<em>Super State Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_STATE_TYPE_FEATURE_COUNT = 18;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.SwimlaneTypeImpl <em>Swimlane Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.SwimlaneTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getSwimlaneType()
	 * @generated
	 */
	int SWIMLANE_TYPE = 24;

	/**
	 * The feature id for the '<em><b>Assignment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE_TYPE__ASSIGNMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE_TYPE__NAME = 1;

	/**
	 * The number of structural features of the '<em>Swimlane Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl <em>Task Node Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.TaskNodeTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTaskNodeType()
	 * @generated
	 */
	int TASK_NODE_TYPE = 25;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Task</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE__TASK = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE__EVENT = 3;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE__EXCEPTION_HANDLER = 4;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE__TIMER = 5;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE__TRANSITION = 6;

	/**
	 * The feature id for the '<em><b>Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE__ASYNC = 7;

	/**
	 * The feature id for the '<em><b>Create Tasks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE__CREATE_TASKS = 8;

	/**
	 * The feature id for the '<em><b>End Tasks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE__END_TASKS = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE__NAME = 10;

	/**
	 * The feature id for the '<em><b>Signal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE__SIGNAL = 11;

	/**
	 * The number of structural features of the '<em>Task Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_NODE_TYPE_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.TaskTypeImpl <em>Task Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.TaskTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTaskType()
	 * @generated
	 */
	int TASK_TYPE = 26;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Assignment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__ASSIGNMENT = 2;

	/**
	 * The feature id for the '<em><b>Controller</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__CONTROLLER = 3;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__EVENT = 4;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__TIMER = 5;

	/**
	 * The feature id for the '<em><b>Reminder</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__REMINDER = 6;

	/**
	 * The feature id for the '<em><b>Blocking</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__BLOCKING = 7;

	/**
	 * The feature id for the '<em><b>Description1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__DESCRIPTION1 = 8;

	/**
	 * The feature id for the '<em><b>Duedate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__DUEDATE = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__NAME = 10;

	/**
	 * The feature id for the '<em><b>Notify</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__NOTIFY = 11;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__PRIORITY = 12;

	/**
	 * The feature id for the '<em><b>Signalling</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__SIGNALLING = 13;

	/**
	 * The feature id for the '<em><b>Swimlane</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE__SWIMLANE = 14;

	/**
	 * The number of structural features of the '<em>Task Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_TYPE_FEATURE_COUNT = 15;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.TimerTypeImpl <em>Timer Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.TimerTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTimerType()
	 * @generated
	 */
	int TIMER_TYPE = 27;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__ACTION = 0;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__SCRIPT = 1;

	/**
	 * The feature id for the '<em><b>Create Timer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__CREATE_TIMER = 2;

	/**
	 * The feature id for the '<em><b>Cancel Timer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__CANCEL_TIMER = 3;

	/**
	 * The feature id for the '<em><b>Mail</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__MAIL = 4;

	/**
	 * The feature id for the '<em><b>Duedate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__DUEDATE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__NAME = 6;

	/**
	 * The feature id for the '<em><b>Repeat</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__REPEAT = 7;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__TRANSITION = 8;

	/**
	 * The number of structural features of the '<em>Timer Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.TransitionTypeImpl <em>Transition Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.TransitionTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTransitionType()
	 * @generated
	 */
	int TRANSITION_TYPE = 28;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__CONDITION = 2;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__ACTION = 3;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__SCRIPT = 4;

	/**
	 * The feature id for the '<em><b>Create Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__CREATE_TIMER = 5;

	/**
	 * The feature id for the '<em><b>Cancel Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__CANCEL_TIMER = 6;

	/**
	 * The feature id for the '<em><b>Mail</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__MAIL = 7;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__EXCEPTION_HANDLER = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__NAME = 9;

	/**
	 * The feature id for the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE__TO = 10;

	/**
	 * The number of structural features of the '<em>Transition Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_TYPE_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.impl.VariableTypeImpl <em>Variable Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.VariableTypeImpl
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getVariableType()
	 * @generated
	 */
	int VARIABLE_TYPE = 29;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TYPE__ANY = 0;

	/**
	 * The feature id for the '<em><b>Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TYPE__ACCESS = 1;

	/**
	 * The feature id for the '<em><b>Mapped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TYPE__MAPPED_NAME = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TYPE__NAME = 3;

	/**
	 * The number of structural features of the '<em>Variable Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.BindingType <em>Binding Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.BindingType
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getBindingType()
	 * @generated
	 */
	int BINDING_TYPE = 30;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.BooleanType <em>Boolean Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.BooleanType
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getBooleanType()
	 * @generated
	 */
	int BOOLEAN_TYPE = 31;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.ConfigType <em>Config Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.ConfigType
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getConfigType()
	 * @generated
	 */
	int CONFIG_TYPE = 32;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.PriorityTypeMember0 <em>Priority Type Member0</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.PriorityTypeMember0
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getPriorityTypeMember0()
	 * @generated
	 */
	int PRIORITY_TYPE_MEMBER0 = 33;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.SignalType <em>Signal Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.SignalType
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getSignalType()
	 * @generated
	 */
	int SIGNAL_TYPE = 34;

	/**
	 * The meta object id for the '{@link org.jbpm.jpdl32.TypeTypeMember1 <em>Type Type Member1</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.TypeTypeMember1
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTypeTypeMember1()
	 * @generated
	 */
	int TYPE_TYPE_MEMBER1 = 35;

	/**
	 * The meta object id for the '<em>Binding Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.BindingType
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getBindingTypeObject()
	 * @generated
	 */
	int BINDING_TYPE_OBJECT = 36;

	/**
	 * The meta object id for the '<em>Boolean Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.BooleanType
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getBooleanTypeObject()
	 * @generated
	 */
	int BOOLEAN_TYPE_OBJECT = 37;

	/**
	 * The meta object id for the '<em>Config Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.ConfigType
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getConfigTypeObject()
	 * @generated
	 */
	int CONFIG_TYPE_OBJECT = 38;

	/**
	 * The meta object id for the '<em>Priority Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Object
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getPriorityType()
	 * @generated
	 */
	int PRIORITY_TYPE = 39;

	/**
	 * The meta object id for the '<em>Priority Type Member0 Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.PriorityTypeMember0
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getPriorityTypeMember0Object()
	 * @generated
	 */
	int PRIORITY_TYPE_MEMBER0_OBJECT = 40;

	/**
	 * The meta object id for the '<em>Priority Type Member1</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getPriorityTypeMember1()
	 * @generated
	 */
	int PRIORITY_TYPE_MEMBER1 = 41;

	/**
	 * The meta object id for the '<em>Priority Type Member1 Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Integer
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getPriorityTypeMember1Object()
	 * @generated
	 */
	int PRIORITY_TYPE_MEMBER1_OBJECT = 42;

	/**
	 * The meta object id for the '<em>Signal Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.SignalType
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getSignalTypeObject()
	 * @generated
	 */
	int SIGNAL_TYPE_OBJECT = 43;

	/**
	 * The meta object id for the '<em>Type Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Object
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTypeType()
	 * @generated
	 */
	int TYPE_TYPE = 44;

	/**
	 * The meta object id for the '<em>Type Type Member0</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTypeTypeMember0()
	 * @generated
	 */
	int TYPE_TYPE_MEMBER0 = 45;

	/**
	 * The meta object id for the '<em>Type Type Member1 Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.jbpm.jpdl32.TypeTypeMember1
	 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTypeTypeMember1Object()
	 * @generated
	 */
	int TYPE_TYPE_MEMBER1_OBJECT = 46;


	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.ActionType <em>Action Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Type</em>'.
	 * @see org.jbpm.jpdl32.ActionType
	 * @generated
	 */
	EClass getActionType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ActionType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.jbpm.jpdl32.ActionType#getMixed()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_Mixed();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ActionType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.jbpm.jpdl32.ActionType#getAny()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ActionType#getAcceptPropagatedEvents <em>Accept Propagated Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Accept Propagated Events</em>'.
	 * @see org.jbpm.jpdl32.ActionType#getAcceptPropagatedEvents()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_AcceptPropagatedEvents();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ActionType#getAsync <em>Async</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async</em>'.
	 * @see org.jbpm.jpdl32.ActionType#getAsync()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_Async();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ActionType#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see org.jbpm.jpdl32.ActionType#getClass_()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_Class();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ActionType#getConfigType <em>Config Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Config Type</em>'.
	 * @see org.jbpm.jpdl32.ActionType#getConfigType()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_ConfigType();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ActionType#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see org.jbpm.jpdl32.ActionType#getExpression()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_Expression();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ActionType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.ActionType#getName()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ActionType#getRefName <em>Ref Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ref Name</em>'.
	 * @see org.jbpm.jpdl32.ActionType#getRefName()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_RefName();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.AssignmentType <em>Assignment Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assignment Type</em>'.
	 * @see org.jbpm.jpdl32.AssignmentType
	 * @generated
	 */
	EClass getAssignmentType();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.AssignmentType#getActorId <em>Actor Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actor Id</em>'.
	 * @see org.jbpm.jpdl32.AssignmentType#getActorId()
	 * @see #getAssignmentType()
	 * @generated
	 */
	EAttribute getAssignmentType_ActorId();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.AssignmentType#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see org.jbpm.jpdl32.AssignmentType#getExpression()
	 * @see #getAssignmentType()
	 * @generated
	 */
	EAttribute getAssignmentType_Expression();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.AssignmentType#getPooledActors <em>Pooled Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pooled Actors</em>'.
	 * @see org.jbpm.jpdl32.AssignmentType#getPooledActors()
	 * @see #getAssignmentType()
	 * @generated
	 */
	EAttribute getAssignmentType_PooledActors();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.CancelTimerType <em>Cancel Timer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cancel Timer Type</em>'.
	 * @see org.jbpm.jpdl32.CancelTimerType
	 * @generated
	 */
	EClass getCancelTimerType();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.CancelTimerType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.CancelTimerType#getName()
	 * @see #getCancelTimerType()
	 * @generated
	 */
	EAttribute getCancelTimerType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.ConditionType <em>Condition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition Type</em>'.
	 * @see org.jbpm.jpdl32.ConditionType
	 * @generated
	 */
	EClass getConditionType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ConditionType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.jbpm.jpdl32.ConditionType#getMixed()
	 * @see #getConditionType()
	 * @generated
	 */
	EAttribute getConditionType_Mixed();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ConditionType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.ConditionType#getGroup()
	 * @see #getConditionType()
	 * @generated
	 */
	EAttribute getConditionType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ConditionType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.jbpm.jpdl32.ConditionType#getAny()
	 * @see #getConditionType()
	 * @generated
	 */
	EAttribute getConditionType_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ConditionType#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see org.jbpm.jpdl32.ConditionType#getExpression()
	 * @see #getConditionType()
	 * @generated
	 */
	EAttribute getConditionType_Expression();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.CreateTimerType <em>Create Timer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Create Timer Type</em>'.
	 * @see org.jbpm.jpdl32.CreateTimerType
	 * @generated
	 */
	EClass getCreateTimerType();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.CreateTimerType#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see org.jbpm.jpdl32.CreateTimerType#getAction()
	 * @see #getCreateTimerType()
	 * @generated
	 */
	EReference getCreateTimerType_Action();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.CreateTimerType#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script</em>'.
	 * @see org.jbpm.jpdl32.CreateTimerType#getScript()
	 * @see #getCreateTimerType()
	 * @generated
	 */
	EReference getCreateTimerType_Script();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.CreateTimerType#getDuedate <em>Duedate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duedate</em>'.
	 * @see org.jbpm.jpdl32.CreateTimerType#getDuedate()
	 * @see #getCreateTimerType()
	 * @generated
	 */
	EAttribute getCreateTimerType_Duedate();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.CreateTimerType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.CreateTimerType#getName()
	 * @see #getCreateTimerType()
	 * @generated
	 */
	EAttribute getCreateTimerType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.CreateTimerType#getRepeat <em>Repeat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repeat</em>'.
	 * @see org.jbpm.jpdl32.CreateTimerType#getRepeat()
	 * @see #getCreateTimerType()
	 * @generated
	 */
	EAttribute getCreateTimerType_Repeat();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.CreateTimerType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.CreateTimerType#getTransition()
	 * @see #getCreateTimerType()
	 * @generated
	 */
	EAttribute getCreateTimerType_Transition();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.DecisionType <em>Decision Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decision Type</em>'.
	 * @see org.jbpm.jpdl32.DecisionType
	 * @generated
	 */
	EClass getDecisionType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.DecisionType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.DecisionType#getGroup()
	 * @see #getDecisionType()
	 * @generated
	 */
	EAttribute getDecisionType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.DecisionType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.DecisionType#getDescription()
	 * @see #getDecisionType()
	 * @generated
	 */
	EAttribute getDecisionType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.DecisionType#getHandler <em>Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Handler</em>'.
	 * @see org.jbpm.jpdl32.DecisionType#getHandler()
	 * @see #getDecisionType()
	 * @generated
	 */
	EReference getDecisionType_Handler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.DecisionType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.DecisionType#getEvent()
	 * @see #getDecisionType()
	 * @generated
	 */
	EReference getDecisionType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.DecisionType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.DecisionType#getExceptionHandler()
	 * @see #getDecisionType()
	 * @generated
	 */
	EReference getDecisionType_ExceptionHandler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.DecisionType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.DecisionType#getTransition()
	 * @see #getDecisionType()
	 * @generated
	 */
	EReference getDecisionType_Transition();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.DecisionType#getAsync <em>Async</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async</em>'.
	 * @see org.jbpm.jpdl32.DecisionType#getAsync()
	 * @see #getDecisionType()
	 * @generated
	 */
	EAttribute getDecisionType_Async();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.DecisionType#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see org.jbpm.jpdl32.DecisionType#getExpression()
	 * @see #getDecisionType()
	 * @generated
	 */
	EAttribute getDecisionType_Expression();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.DecisionType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.DecisionType#getName()
	 * @see #getDecisionType()
	 * @generated
	 */
	EAttribute getDecisionType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.Delegation <em>Delegation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delegation</em>'.
	 * @see org.jbpm.jpdl32.Delegation
	 * @generated
	 */
	EClass getDelegation();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.Delegation#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.jbpm.jpdl32.Delegation#getMixed()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_Mixed();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.Delegation#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.jbpm.jpdl32.Delegation#getAny()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.Delegation#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see org.jbpm.jpdl32.Delegation#getClass_()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_Class();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.Delegation#getConfigType <em>Config Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Config Type</em>'.
	 * @see org.jbpm.jpdl32.Delegation#getConfigType()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_ConfigType();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.jbpm.jpdl32.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.jbpm.jpdl32.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getAction()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Action();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getAssignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Assignment</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getAssignment()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Assignment();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getCancelTimer <em>Cancel Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cancel Timer</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getCancelTimer()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CancelTimer();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getController <em>Controller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Controller</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getController()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Controller();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getCreateTimer <em>Create Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Create Timer</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getCreateTimer()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CreateTimer();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getDecision <em>Decision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Decision</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getDecision()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Decision();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.DocumentRoot#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getDescription()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Description();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getEndState <em>End State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>End State</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getEndState()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_EndState();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Event();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getExceptionHandler()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExceptionHandler();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getFork <em>Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Fork</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getFork()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Fork();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getJoin <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Join</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getJoin()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Join();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getMail <em>Mail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Mail</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getMail()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Mail();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getMailNode <em>Mail Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Mail Node</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getMailNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MailNode();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Node</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Node();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getProcessDefinition <em>Process Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process Definition</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getProcessDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ProcessDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getProcessState <em>Process State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process State</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getProcessState()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ProcessState();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.DocumentRoot#getRecipients <em>Recipients</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recipients</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getRecipients()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Recipients();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getScript()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Script();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getStartState <em>Start State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Start State</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getStartState()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StartState();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>State</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getState()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_State();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.DocumentRoot#getSubject <em>Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subject</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getSubject()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Subject();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getSuperState <em>Super State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Super State</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getSuperState()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SuperState();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getSwimlane <em>Swimlane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Swimlane</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getSwimlane()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Swimlane();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Task</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Task();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getTaskNode <em>Task Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Task Node</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getTaskNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TaskNode();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.DocumentRoot#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Template</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getTemplate()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Template();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.DocumentRoot#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getText()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Text();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Timer</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getTimer()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Timer();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.DocumentRoot#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getTo()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_To();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getTransition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Transition();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.DocumentRoot#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variable</em>'.
	 * @see org.jbpm.jpdl32.DocumentRoot#getVariable()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Variable();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.EndStateType <em>End State Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>End State Type</em>'.
	 * @see org.jbpm.jpdl32.EndStateType
	 * @generated
	 */
	EClass getEndStateType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.EndStateType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.EndStateType#getGroup()
	 * @see #getEndStateType()
	 * @generated
	 */
	EAttribute getEndStateType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.EndStateType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.EndStateType#getDescription()
	 * @see #getEndStateType()
	 * @generated
	 */
	EAttribute getEndStateType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.EndStateType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.EndStateType#getEvent()
	 * @see #getEndStateType()
	 * @generated
	 */
	EReference getEndStateType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.EndStateType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.EndStateType#getExceptionHandler()
	 * @see #getEndStateType()
	 * @generated
	 */
	EReference getEndStateType_ExceptionHandler();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.EndStateType#getEndCompleteProcess <em>End Complete Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Complete Process</em>'.
	 * @see org.jbpm.jpdl32.EndStateType#getEndCompleteProcess()
	 * @see #getEndStateType()
	 * @generated
	 */
	EAttribute getEndStateType_EndCompleteProcess();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.EndStateType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.EndStateType#getName()
	 * @see #getEndStateType()
	 * @generated
	 */
	EAttribute getEndStateType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.EventType <em>Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Type</em>'.
	 * @see org.jbpm.jpdl32.EventType
	 * @generated
	 */
	EClass getEventType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.EventType#getActionElements <em>Action Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Action Elements</em>'.
	 * @see org.jbpm.jpdl32.EventType#getActionElements()
	 * @see #getEventType()
	 * @generated
	 */
	EAttribute getEventType_ActionElements();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.EventType#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action</em>'.
	 * @see org.jbpm.jpdl32.EventType#getAction()
	 * @see #getEventType()
	 * @generated
	 */
	EReference getEventType_Action();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.EventType#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Script</em>'.
	 * @see org.jbpm.jpdl32.EventType#getScript()
	 * @see #getEventType()
	 * @generated
	 */
	EReference getEventType_Script();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.EventType#getCreateTimer <em>Create Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Create Timer</em>'.
	 * @see org.jbpm.jpdl32.EventType#getCreateTimer()
	 * @see #getEventType()
	 * @generated
	 */
	EReference getEventType_CreateTimer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.EventType#getCancelTimer <em>Cancel Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cancel Timer</em>'.
	 * @see org.jbpm.jpdl32.EventType#getCancelTimer()
	 * @see #getEventType()
	 * @generated
	 */
	EReference getEventType_CancelTimer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.EventType#getMail <em>Mail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mail</em>'.
	 * @see org.jbpm.jpdl32.EventType#getMail()
	 * @see #getEventType()
	 * @generated
	 */
	EReference getEventType_Mail();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.EventType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.jbpm.jpdl32.EventType#getType()
	 * @see #getEventType()
	 * @generated
	 */
	EAttribute getEventType_Type();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.ExceptionHandlerType <em>Exception Handler Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exception Handler Type</em>'.
	 * @see org.jbpm.jpdl32.ExceptionHandlerType
	 * @generated
	 */
	EClass getExceptionHandlerType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ExceptionHandlerType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.ExceptionHandlerType#getGroup()
	 * @see #getExceptionHandlerType()
	 * @generated
	 */
	EAttribute getExceptionHandlerType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ExceptionHandlerType#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action</em>'.
	 * @see org.jbpm.jpdl32.ExceptionHandlerType#getAction()
	 * @see #getExceptionHandlerType()
	 * @generated
	 */
	EReference getExceptionHandlerType_Action();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ExceptionHandlerType#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Script</em>'.
	 * @see org.jbpm.jpdl32.ExceptionHandlerType#getScript()
	 * @see #getExceptionHandlerType()
	 * @generated
	 */
	EReference getExceptionHandlerType_Script();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ExceptionHandlerType#getExceptionClass <em>Exception Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exception Class</em>'.
	 * @see org.jbpm.jpdl32.ExceptionHandlerType#getExceptionClass()
	 * @see #getExceptionHandlerType()
	 * @generated
	 */
	EAttribute getExceptionHandlerType_ExceptionClass();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.ForkType <em>Fork Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fork Type</em>'.
	 * @see org.jbpm.jpdl32.ForkType
	 * @generated
	 */
	EClass getForkType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ForkType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.ForkType#getGroup()
	 * @see #getForkType()
	 * @generated
	 */
	EAttribute getForkType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ForkType#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Script</em>'.
	 * @see org.jbpm.jpdl32.ForkType#getScript()
	 * @see #getForkType()
	 * @generated
	 */
	EReference getForkType_Script();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ForkType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.ForkType#getDescription()
	 * @see #getForkType()
	 * @generated
	 */
	EAttribute getForkType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ForkType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.ForkType#getEvent()
	 * @see #getForkType()
	 * @generated
	 */
	EReference getForkType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ForkType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.ForkType#getExceptionHandler()
	 * @see #getForkType()
	 * @generated
	 */
	EReference getForkType_ExceptionHandler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ForkType#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see org.jbpm.jpdl32.ForkType#getTimer()
	 * @see #getForkType()
	 * @generated
	 */
	EReference getForkType_Timer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ForkType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.ForkType#getTransition()
	 * @see #getForkType()
	 * @generated
	 */
	EReference getForkType_Transition();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ForkType#getAsync <em>Async</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async</em>'.
	 * @see org.jbpm.jpdl32.ForkType#getAsync()
	 * @see #getForkType()
	 * @generated
	 */
	EAttribute getForkType_Async();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ForkType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.ForkType#getName()
	 * @see #getForkType()
	 * @generated
	 */
	EAttribute getForkType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.JoinType <em>Join Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join Type</em>'.
	 * @see org.jbpm.jpdl32.JoinType
	 * @generated
	 */
	EClass getJoinType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.JoinType#getNodeContentElements <em>Node Content Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Node Content Elements</em>'.
	 * @see org.jbpm.jpdl32.JoinType#getNodeContentElements()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_NodeContentElements();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.JoinType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.JoinType#getDescription()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.JoinType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.JoinType#getEvent()
	 * @see #getJoinType()
	 * @generated
	 */
	EReference getJoinType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.JoinType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.JoinType#getExceptionHandler()
	 * @see #getJoinType()
	 * @generated
	 */
	EReference getJoinType_ExceptionHandler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.JoinType#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see org.jbpm.jpdl32.JoinType#getTimer()
	 * @see #getJoinType()
	 * @generated
	 */
	EReference getJoinType_Timer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.JoinType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.JoinType#getTransition()
	 * @see #getJoinType()
	 * @generated
	 */
	EReference getJoinType_Transition();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.JoinType#getAsync <em>Async</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async</em>'.
	 * @see org.jbpm.jpdl32.JoinType#getAsync()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_Async();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.JoinType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.JoinType#getName()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.MailNodeType <em>Mail Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mail Node Type</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType
	 * @generated
	 */
	EClass getMailNodeType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.MailNodeType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getGroup()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EAttribute getMailNodeType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.MailNodeType#getSubject <em>Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Subject</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getSubject()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EAttribute getMailNodeType_Subject();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.MailNodeType#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Text</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getText()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EAttribute getMailNodeType_Text();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.MailNodeType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getDescription()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EAttribute getMailNodeType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.MailNodeType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getEvent()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EReference getMailNodeType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.MailNodeType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getExceptionHandler()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EReference getMailNodeType_ExceptionHandler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.MailNodeType#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getTimer()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EReference getMailNodeType_Timer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.MailNodeType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getTransition()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EReference getMailNodeType_Transition();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailNodeType#getActors <em>Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actors</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getActors()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EAttribute getMailNodeType_Actors();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailNodeType#getAsync <em>Async</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getAsync()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EAttribute getMailNodeType_Async();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailNodeType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getName()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EAttribute getMailNodeType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailNodeType#getSubject1 <em>Subject1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subject1</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getSubject1()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EAttribute getMailNodeType_Subject1();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailNodeType#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Template</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getTemplate()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EAttribute getMailNodeType_Template();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailNodeType#getText1 <em>Text1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text1</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getText1()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EAttribute getMailNodeType_Text1();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailNodeType#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To</em>'.
	 * @see org.jbpm.jpdl32.MailNodeType#getTo()
	 * @see #getMailNodeType()
	 * @generated
	 */
	EAttribute getMailNodeType_To();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.MailType <em>Mail Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mail Type</em>'.
	 * @see org.jbpm.jpdl32.MailType
	 * @generated
	 */
	EClass getMailType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.MailType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.MailType#getGroup()
	 * @see #getMailType()
	 * @generated
	 */
	EAttribute getMailType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.MailType#getSubject <em>Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Subject</em>'.
	 * @see org.jbpm.jpdl32.MailType#getSubject()
	 * @see #getMailType()
	 * @generated
	 */
	EAttribute getMailType_Subject();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.MailType#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Text</em>'.
	 * @see org.jbpm.jpdl32.MailType#getText()
	 * @see #getMailType()
	 * @generated
	 */
	EAttribute getMailType_Text();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailType#getActors <em>Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actors</em>'.
	 * @see org.jbpm.jpdl32.MailType#getActors()
	 * @see #getMailType()
	 * @generated
	 */
	EAttribute getMailType_Actors();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailType#getAsync <em>Async</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async</em>'.
	 * @see org.jbpm.jpdl32.MailType#getAsync()
	 * @see #getMailType()
	 * @generated
	 */
	EAttribute getMailType_Async();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.MailType#getName()
	 * @see #getMailType()
	 * @generated
	 */
	EAttribute getMailType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailType#getSubject1 <em>Subject1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subject1</em>'.
	 * @see org.jbpm.jpdl32.MailType#getSubject1()
	 * @see #getMailType()
	 * @generated
	 */
	EAttribute getMailType_Subject1();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailType#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Template</em>'.
	 * @see org.jbpm.jpdl32.MailType#getTemplate()
	 * @see #getMailType()
	 * @generated
	 */
	EAttribute getMailType_Template();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailType#getText1 <em>Text1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text1</em>'.
	 * @see org.jbpm.jpdl32.MailType#getText1()
	 * @see #getMailType()
	 * @generated
	 */
	EAttribute getMailType_Text1();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.MailType#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To</em>'.
	 * @see org.jbpm.jpdl32.MailType#getTo()
	 * @see #getMailType()
	 * @generated
	 */
	EAttribute getMailType_To();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.NodeType <em>Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Type</em>'.
	 * @see org.jbpm.jpdl32.NodeType
	 * @generated
	 */
	EClass getNodeType();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.NodeType#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getAction()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_Action();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.NodeType#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getScript()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_Script();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.NodeType#getCreateTimer <em>Create Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Create Timer</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getCreateTimer()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_CreateTimer();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.NodeType#getCancelTimer <em>Cancel Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cancel Timer</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getCancelTimer()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_CancelTimer();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.NodeType#getMail <em>Mail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Mail</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getMail()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_Mail();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.NodeType#getNodeContentElements <em>Node Content Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Node Content Elements</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getNodeContentElements()
	 * @see #getNodeType()
	 * @generated
	 */
	EAttribute getNodeType_NodeContentElements();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.NodeType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getDescription()
	 * @see #getNodeType()
	 * @generated
	 */
	EAttribute getNodeType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.NodeType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getEvent()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.NodeType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getExceptionHandler()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_ExceptionHandler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.NodeType#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getTimer()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_Timer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.NodeType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getTransition()
	 * @see #getNodeType()
	 * @generated
	 */
	EReference getNodeType_Transition();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.NodeType#getAsync <em>Async</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getAsync()
	 * @see #getNodeType()
	 * @generated
	 */
	EAttribute getNodeType_Async();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.NodeType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.NodeType#getName()
	 * @see #getNodeType()
	 * @generated
	 */
	EAttribute getNodeType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.ProcessDefinitionType <em>Process Definition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Definition Type</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType
	 * @generated
	 */
	EClass getProcessDefinitionType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getGroup()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EAttribute getProcessDefinitionType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getDescription()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EAttribute getProcessDefinitionType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getSwimlane <em>Swimlane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Swimlane</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getSwimlane()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Swimlane();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getStartState <em>Start State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Start State</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getStartState()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_StartState();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Node</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getNode()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Node();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>State</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getState()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_State();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getTaskNode <em>Task Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Task Node</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getTaskNode()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_TaskNode();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getSuperState <em>Super State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Super State</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getSuperState()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_SuperState();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getProcessState <em>Process State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Process State</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getProcessState()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_ProcessState();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getFork <em>Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fork</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getFork()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Fork();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getJoin <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Join</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getJoin()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Join();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getDecision <em>Decision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Decision</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getDecision()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Decision();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getEndState <em>End State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>End State</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getEndState()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_EndState();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getMailNode <em>Mail Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mail Node</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getMailNode()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_MailNode();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getAction()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Action();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Script</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getScript()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Script();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getCreateTimer <em>Create Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Create Timer</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getCreateTimer()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_CreateTimer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getCancelTimer <em>Cancel Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cancel Timer</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getCancelTimer()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_CancelTimer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getMail <em>Mail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mail</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getMail()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Mail();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getEvent()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getExceptionHandler()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_ExceptionHandler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessDefinitionType#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Task</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getTask()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EReference getProcessDefinitionType_Task();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ProcessDefinitionType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.ProcessDefinitionType#getName()
	 * @see #getProcessDefinitionType()
	 * @generated
	 */
	EAttribute getProcessDefinitionType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.ProcessStateType <em>Process State Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process State Type</em>'.
	 * @see org.jbpm.jpdl32.ProcessStateType
	 * @generated
	 */
	EClass getProcessStateType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ProcessStateType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.ProcessStateType#getGroup()
	 * @see #getProcessStateType()
	 * @generated
	 */
	EAttribute getProcessStateType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessStateType#getSubProcess <em>Sub Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Process</em>'.
	 * @see org.jbpm.jpdl32.ProcessStateType#getSubProcess()
	 * @see #getProcessStateType()
	 * @generated
	 */
	EReference getProcessStateType_SubProcess();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessStateType#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variable</em>'.
	 * @see org.jbpm.jpdl32.ProcessStateType#getVariable()
	 * @see #getProcessStateType()
	 * @generated
	 */
	EReference getProcessStateType_Variable();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ProcessStateType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.ProcessStateType#getDescription()
	 * @see #getProcessStateType()
	 * @generated
	 */
	EAttribute getProcessStateType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessStateType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.ProcessStateType#getEvent()
	 * @see #getProcessStateType()
	 * @generated
	 */
	EReference getProcessStateType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessStateType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.ProcessStateType#getExceptionHandler()
	 * @see #getProcessStateType()
	 * @generated
	 */
	EReference getProcessStateType_ExceptionHandler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessStateType#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see org.jbpm.jpdl32.ProcessStateType#getTimer()
	 * @see #getProcessStateType()
	 * @generated
	 */
	EReference getProcessStateType_Timer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.ProcessStateType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.ProcessStateType#getTransition()
	 * @see #getProcessStateType()
	 * @generated
	 */
	EReference getProcessStateType_Transition();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ProcessStateType#getAsync <em>Async</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async</em>'.
	 * @see org.jbpm.jpdl32.ProcessStateType#getAsync()
	 * @see #getProcessStateType()
	 * @generated
	 */
	EAttribute getProcessStateType_Async();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ProcessStateType#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Binding</em>'.
	 * @see org.jbpm.jpdl32.ProcessStateType#getBinding()
	 * @see #getProcessStateType()
	 * @generated
	 */
	EAttribute getProcessStateType_Binding();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ProcessStateType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.ProcessStateType#getName()
	 * @see #getProcessStateType()
	 * @generated
	 */
	EAttribute getProcessStateType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.ReminderType <em>Reminder Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reminder Type</em>'.
	 * @see org.jbpm.jpdl32.ReminderType
	 * @generated
	 */
	EClass getReminderType();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ReminderType#getDuedate <em>Duedate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duedate</em>'.
	 * @see org.jbpm.jpdl32.ReminderType#getDuedate()
	 * @see #getReminderType()
	 * @generated
	 */
	EAttribute getReminderType_Duedate();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ReminderType#getRepeat <em>Repeat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repeat</em>'.
	 * @see org.jbpm.jpdl32.ReminderType#getRepeat()
	 * @see #getReminderType()
	 * @generated
	 */
	EAttribute getReminderType_Repeat();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.ScriptType <em>Script Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Type</em>'.
	 * @see org.jbpm.jpdl32.ScriptType
	 * @generated
	 */
	EClass getScriptType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ScriptType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.jbpm.jpdl32.ScriptType#getMixed()
	 * @see #getScriptType()
	 * @generated
	 */
	EAttribute getScriptType_Mixed();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.ScriptType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.jbpm.jpdl32.ScriptType#getAny()
	 * @see #getScriptType()
	 * @generated
	 */
	EAttribute getScriptType_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ScriptType#getAcceptPropagatedEvents <em>Accept Propagated Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Accept Propagated Events</em>'.
	 * @see org.jbpm.jpdl32.ScriptType#getAcceptPropagatedEvents()
	 * @see #getScriptType()
	 * @generated
	 */
	EAttribute getScriptType_AcceptPropagatedEvents();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.ScriptType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.ScriptType#getName()
	 * @see #getScriptType()
	 * @generated
	 */
	EAttribute getScriptType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.StartStateType <em>Start State Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start State Type</em>'.
	 * @see org.jbpm.jpdl32.StartStateType
	 * @generated
	 */
	EClass getStartStateType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.StartStateType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.StartStateType#getGroup()
	 * @see #getStartStateType()
	 * @generated
	 */
	EAttribute getStartStateType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.StartStateType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.StartStateType#getDescription()
	 * @see #getStartStateType()
	 * @generated
	 */
	EAttribute getStartStateType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.StartStateType#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Task</em>'.
	 * @see org.jbpm.jpdl32.StartStateType#getTask()
	 * @see #getStartStateType()
	 * @generated
	 */
	EReference getStartStateType_Task();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.StartStateType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.StartStateType#getTransition()
	 * @see #getStartStateType()
	 * @generated
	 */
	EReference getStartStateType_Transition();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.StartStateType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.StartStateType#getEvent()
	 * @see #getStartStateType()
	 * @generated
	 */
	EReference getStartStateType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.StartStateType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.StartStateType#getExceptionHandler()
	 * @see #getStartStateType()
	 * @generated
	 */
	EReference getStartStateType_ExceptionHandler();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.StartStateType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.StartStateType#getName()
	 * @see #getStartStateType()
	 * @generated
	 */
	EAttribute getStartStateType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.StateType <em>State Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Type</em>'.
	 * @see org.jbpm.jpdl32.StateType
	 * @generated
	 */
	EClass getStateType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.StateType#getNodeContentElements <em>Node Content Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Node Content Elements</em>'.
	 * @see org.jbpm.jpdl32.StateType#getNodeContentElements()
	 * @see #getStateType()
	 * @generated
	 */
	EAttribute getStateType_NodeContentElements();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.StateType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.StateType#getDescription()
	 * @see #getStateType()
	 * @generated
	 */
	EAttribute getStateType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.StateType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.StateType#getEvent()
	 * @see #getStateType()
	 * @generated
	 */
	EReference getStateType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.StateType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.StateType#getExceptionHandler()
	 * @see #getStateType()
	 * @generated
	 */
	EReference getStateType_ExceptionHandler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.StateType#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see org.jbpm.jpdl32.StateType#getTimer()
	 * @see #getStateType()
	 * @generated
	 */
	EReference getStateType_Timer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.StateType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.StateType#getTransition()
	 * @see #getStateType()
	 * @generated
	 */
	EReference getStateType_Transition();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.StateType#getAsync <em>Async</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async</em>'.
	 * @see org.jbpm.jpdl32.StateType#getAsync()
	 * @see #getStateType()
	 * @generated
	 */
	EAttribute getStateType_Async();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.StateType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.StateType#getName()
	 * @see #getStateType()
	 * @generated
	 */
	EAttribute getStateType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.SubProcessType <em>Sub Process Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sub Process Type</em>'.
	 * @see org.jbpm.jpdl32.SubProcessType
	 * @generated
	 */
	EClass getSubProcessType();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.SubProcessType#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Binding</em>'.
	 * @see org.jbpm.jpdl32.SubProcessType#getBinding()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_Binding();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.SubProcessType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.SubProcessType#getName()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.SubProcessType#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.jbpm.jpdl32.SubProcessType#getVersion()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_Version();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.SuperStateType <em>Super State Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Super State Type</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType
	 * @generated
	 */
	EClass getSuperStateType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.SuperStateType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getGroup()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EAttribute getSuperStateType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Node</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getNode()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_Node();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>State</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getState()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_State();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getTaskNode <em>Task Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Task Node</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getTaskNode()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_TaskNode();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getSuperState <em>Super State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Super State</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getSuperState()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_SuperState();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getProcessState <em>Process State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Process State</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getProcessState()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_ProcessState();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getFork <em>Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fork</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getFork()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_Fork();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getJoin <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Join</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getJoin()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_Join();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getDecision <em>Decision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Decision</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getDecision()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_Decision();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getEndState <em>End State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>End State</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getEndState()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_EndState();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getMailNode <em>Mail Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mail Node</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getMailNode()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_MailNode();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.SuperStateType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getDescription()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EAttribute getSuperStateType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getEvent()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getExceptionHandler()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_ExceptionHandler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getTimer()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_Timer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.SuperStateType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getTransition()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EReference getSuperStateType_Transition();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.SuperStateType#getAsync <em>Async</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getAsync()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EAttribute getSuperStateType_Async();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.SuperStateType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.SuperStateType#getName()
	 * @see #getSuperStateType()
	 * @generated
	 */
	EAttribute getSuperStateType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.SwimlaneType <em>Swimlane Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Swimlane Type</em>'.
	 * @see org.jbpm.jpdl32.SwimlaneType
	 * @generated
	 */
	EClass getSwimlaneType();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.SwimlaneType#getAssignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Assignment</em>'.
	 * @see org.jbpm.jpdl32.SwimlaneType#getAssignment()
	 * @see #getSwimlaneType()
	 * @generated
	 */
	EReference getSwimlaneType_Assignment();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.SwimlaneType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.SwimlaneType#getName()
	 * @see #getSwimlaneType()
	 * @generated
	 */
	EAttribute getSwimlaneType_Name();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.TaskNodeType <em>Task Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Node Type</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType
	 * @generated
	 */
	EClass getTaskNodeType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.TaskNodeType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType#getGroup()
	 * @see #getTaskNodeType()
	 * @generated
	 */
	EAttribute getTaskNodeType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TaskNodeType#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Task</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType#getTask()
	 * @see #getTaskNodeType()
	 * @generated
	 */
	EReference getTaskNodeType_Task();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.TaskNodeType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType#getDescription()
	 * @see #getTaskNodeType()
	 * @generated
	 */
	EAttribute getTaskNodeType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TaskNodeType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType#getEvent()
	 * @see #getTaskNodeType()
	 * @generated
	 */
	EReference getTaskNodeType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TaskNodeType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType#getExceptionHandler()
	 * @see #getTaskNodeType()
	 * @generated
	 */
	EReference getTaskNodeType_ExceptionHandler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TaskNodeType#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType#getTimer()
	 * @see #getTaskNodeType()
	 * @generated
	 */
	EReference getTaskNodeType_Timer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TaskNodeType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType#getTransition()
	 * @see #getTaskNodeType()
	 * @generated
	 */
	EReference getTaskNodeType_Transition();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskNodeType#getAsync <em>Async</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType#getAsync()
	 * @see #getTaskNodeType()
	 * @generated
	 */
	EAttribute getTaskNodeType_Async();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskNodeType#getCreateTasks <em>Create Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Create Tasks</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType#getCreateTasks()
	 * @see #getTaskNodeType()
	 * @generated
	 */
	EAttribute getTaskNodeType_CreateTasks();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskNodeType#getEndTasks <em>End Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Tasks</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType#getEndTasks()
	 * @see #getTaskNodeType()
	 * @generated
	 */
	EAttribute getTaskNodeType_EndTasks();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskNodeType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType#getName()
	 * @see #getTaskNodeType()
	 * @generated
	 */
	EAttribute getTaskNodeType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskNodeType#getSignal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signal</em>'.
	 * @see org.jbpm.jpdl32.TaskNodeType#getSignal()
	 * @see #getTaskNodeType()
	 * @generated
	 */
	EAttribute getTaskNodeType_Signal();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.TaskType <em>Task Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Type</em>'.
	 * @see org.jbpm.jpdl32.TaskType
	 * @generated
	 */
	EClass getTaskType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.TaskType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getGroup()
	 * @see #getTaskType()
	 * @generated
	 */
	EAttribute getTaskType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.TaskType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getDescription()
	 * @see #getTaskType()
	 * @generated
	 */
	EAttribute getTaskType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TaskType#getAssignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Assignment</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getAssignment()
	 * @see #getTaskType()
	 * @generated
	 */
	EReference getTaskType_Assignment();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TaskType#getController <em>Controller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Controller</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getController()
	 * @see #getTaskType()
	 * @generated
	 */
	EReference getTaskType_Controller();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TaskType#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getEvent()
	 * @see #getTaskType()
	 * @generated
	 */
	EReference getTaskType_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TaskType#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getTimer()
	 * @see #getTaskType()
	 * @generated
	 */
	EReference getTaskType_Timer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TaskType#getReminder <em>Reminder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reminder</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getReminder()
	 * @see #getTaskType()
	 * @generated
	 */
	EReference getTaskType_Reminder();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskType#getBlocking <em>Blocking</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Blocking</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getBlocking()
	 * @see #getTaskType()
	 * @generated
	 */
	EAttribute getTaskType_Blocking();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskType#getDescription1 <em>Description1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description1</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getDescription1()
	 * @see #getTaskType()
	 * @generated
	 */
	EAttribute getTaskType_Description1();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskType#getDuedate <em>Duedate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duedate</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getDuedate()
	 * @see #getTaskType()
	 * @generated
	 */
	EAttribute getTaskType_Duedate();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getName()
	 * @see #getTaskType()
	 * @generated
	 */
	EAttribute getTaskType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskType#getNotify <em>Notify</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Notify</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getNotify()
	 * @see #getTaskType()
	 * @generated
	 */
	EAttribute getTaskType_Notify();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskType#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getPriority()
	 * @see #getTaskType()
	 * @generated
	 */
	EAttribute getTaskType_Priority();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskType#getSignalling <em>Signalling</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signalling</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getSignalling()
	 * @see #getTaskType()
	 * @generated
	 */
	EAttribute getTaskType_Signalling();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TaskType#getSwimlane <em>Swimlane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Swimlane</em>'.
	 * @see org.jbpm.jpdl32.TaskType#getSwimlane()
	 * @see #getTaskType()
	 * @generated
	 */
	EAttribute getTaskType_Swimlane();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.TimerType <em>Timer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timer Type</em>'.
	 * @see org.jbpm.jpdl32.TimerType
	 * @generated
	 */
	EClass getTimerType();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.TimerType#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see org.jbpm.jpdl32.TimerType#getAction()
	 * @see #getTimerType()
	 * @generated
	 */
	EReference getTimerType_Action();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.TimerType#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script</em>'.
	 * @see org.jbpm.jpdl32.TimerType#getScript()
	 * @see #getTimerType()
	 * @generated
	 */
	EReference getTimerType_Script();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.TimerType#getCreateTimer <em>Create Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Create Timer</em>'.
	 * @see org.jbpm.jpdl32.TimerType#getCreateTimer()
	 * @see #getTimerType()
	 * @generated
	 */
	EReference getTimerType_CreateTimer();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.TimerType#getCancelTimer <em>Cancel Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cancel Timer</em>'.
	 * @see org.jbpm.jpdl32.TimerType#getCancelTimer()
	 * @see #getTimerType()
	 * @generated
	 */
	EReference getTimerType_CancelTimer();

	/**
	 * Returns the meta object for the containment reference '{@link org.jbpm.jpdl32.TimerType#getMail <em>Mail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Mail</em>'.
	 * @see org.jbpm.jpdl32.TimerType#getMail()
	 * @see #getTimerType()
	 * @generated
	 */
	EReference getTimerType_Mail();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TimerType#getDuedate <em>Duedate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duedate</em>'.
	 * @see org.jbpm.jpdl32.TimerType#getDuedate()
	 * @see #getTimerType()
	 * @generated
	 */
	EAttribute getTimerType_Duedate();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TimerType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.TimerType#getName()
	 * @see #getTimerType()
	 * @generated
	 */
	EAttribute getTimerType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TimerType#getRepeat <em>Repeat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repeat</em>'.
	 * @see org.jbpm.jpdl32.TimerType#getRepeat()
	 * @see #getTimerType()
	 * @generated
	 */
	EAttribute getTimerType_Repeat();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TimerType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transition</em>'.
	 * @see org.jbpm.jpdl32.TimerType#getTransition()
	 * @see #getTimerType()
	 * @generated
	 */
	EAttribute getTimerType_Transition();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.TransitionType <em>Transition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Type</em>'.
	 * @see org.jbpm.jpdl32.TransitionType
	 * @generated
	 */
	EClass getTransitionType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.TransitionType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.jbpm.jpdl32.TransitionType#getGroup()
	 * @see #getTransitionType()
	 * @generated
	 */
	EAttribute getTransitionType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.TransitionType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see org.jbpm.jpdl32.TransitionType#getDescription()
	 * @see #getTransitionType()
	 * @generated
	 */
	EAttribute getTransitionType_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TransitionType#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Condition</em>'.
	 * @see org.jbpm.jpdl32.TransitionType#getCondition()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_Condition();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TransitionType#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action</em>'.
	 * @see org.jbpm.jpdl32.TransitionType#getAction()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_Action();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TransitionType#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Script</em>'.
	 * @see org.jbpm.jpdl32.TransitionType#getScript()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_Script();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TransitionType#getCreateTimer <em>Create Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Create Timer</em>'.
	 * @see org.jbpm.jpdl32.TransitionType#getCreateTimer()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_CreateTimer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TransitionType#getCancelTimer <em>Cancel Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cancel Timer</em>'.
	 * @see org.jbpm.jpdl32.TransitionType#getCancelTimer()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_CancelTimer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TransitionType#getMail <em>Mail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mail</em>'.
	 * @see org.jbpm.jpdl32.TransitionType#getMail()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_Mail();

	/**
	 * Returns the meta object for the containment reference list '{@link org.jbpm.jpdl32.TransitionType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.jbpm.jpdl32.TransitionType#getExceptionHandler()
	 * @see #getTransitionType()
	 * @generated
	 */
	EReference getTransitionType_ExceptionHandler();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TransitionType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.TransitionType#getName()
	 * @see #getTransitionType()
	 * @generated
	 */
	EAttribute getTransitionType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.TransitionType#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To</em>'.
	 * @see org.jbpm.jpdl32.TransitionType#getTo()
	 * @see #getTransitionType()
	 * @generated
	 */
	EAttribute getTransitionType_To();

	/**
	 * Returns the meta object for class '{@link org.jbpm.jpdl32.VariableType <em>Variable Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Type</em>'.
	 * @see org.jbpm.jpdl32.VariableType
	 * @generated
	 */
	EClass getVariableType();

	/**
	 * Returns the meta object for the attribute list '{@link org.jbpm.jpdl32.VariableType#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.jbpm.jpdl32.VariableType#getAny()
	 * @see #getVariableType()
	 * @generated
	 */
	EAttribute getVariableType_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.VariableType#getAccess <em>Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Access</em>'.
	 * @see org.jbpm.jpdl32.VariableType#getAccess()
	 * @see #getVariableType()
	 * @generated
	 */
	EAttribute getVariableType_Access();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.VariableType#getMappedName <em>Mapped Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mapped Name</em>'.
	 * @see org.jbpm.jpdl32.VariableType#getMappedName()
	 * @see #getVariableType()
	 * @generated
	 */
	EAttribute getVariableType_MappedName();

	/**
	 * Returns the meta object for the attribute '{@link org.jbpm.jpdl32.VariableType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.jbpm.jpdl32.VariableType#getName()
	 * @see #getVariableType()
	 * @generated
	 */
	EAttribute getVariableType_Name();

	/**
	 * Returns the meta object for enum '{@link org.jbpm.jpdl32.BindingType <em>Binding Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Binding Type</em>'.
	 * @see org.jbpm.jpdl32.BindingType
	 * @generated
	 */
	EEnum getBindingType();

	/**
	 * Returns the meta object for enum '{@link org.jbpm.jpdl32.BooleanType <em>Boolean Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Boolean Type</em>'.
	 * @see org.jbpm.jpdl32.BooleanType
	 * @generated
	 */
	EEnum getBooleanType();

	/**
	 * Returns the meta object for enum '{@link org.jbpm.jpdl32.ConfigType <em>Config Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Config Type</em>'.
	 * @see org.jbpm.jpdl32.ConfigType
	 * @generated
	 */
	EEnum getConfigType();

	/**
	 * Returns the meta object for enum '{@link org.jbpm.jpdl32.PriorityTypeMember0 <em>Priority Type Member0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Priority Type Member0</em>'.
	 * @see org.jbpm.jpdl32.PriorityTypeMember0
	 * @generated
	 */
	EEnum getPriorityTypeMember0();

	/**
	 * Returns the meta object for enum '{@link org.jbpm.jpdl32.SignalType <em>Signal Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Signal Type</em>'.
	 * @see org.jbpm.jpdl32.SignalType
	 * @generated
	 */
	EEnum getSignalType();

	/**
	 * Returns the meta object for enum '{@link org.jbpm.jpdl32.TypeTypeMember1 <em>Type Type Member1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type Type Member1</em>'.
	 * @see org.jbpm.jpdl32.TypeTypeMember1
	 * @generated
	 */
	EEnum getTypeTypeMember1();

	/**
	 * Returns the meta object for data type '{@link org.jbpm.jpdl32.BindingType <em>Binding Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Binding Type Object</em>'.
	 * @see org.jbpm.jpdl32.BindingType
	 * @model instanceClass="org.jbpm.jpdl32.BindingType"
	 *        extendedMetaData="name='bindingType:Object' baseType='bindingType'"
	 * @generated
	 */
	EDataType getBindingTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.jbpm.jpdl32.BooleanType <em>Boolean Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Boolean Type Object</em>'.
	 * @see org.jbpm.jpdl32.BooleanType
	 * @model instanceClass="org.jbpm.jpdl32.BooleanType"
	 *        extendedMetaData="name='booleanType:Object' baseType='booleanType'"
	 * @generated
	 */
	EDataType getBooleanTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.jbpm.jpdl32.ConfigType <em>Config Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Config Type Object</em>'.
	 * @see org.jbpm.jpdl32.ConfigType
	 * @model instanceClass="org.jbpm.jpdl32.ConfigType"
	 *        extendedMetaData="name='configType:Object' baseType='configType'"
	 * @generated
	 */
	EDataType getConfigTypeObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object <em>Priority Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Priority Type</em>'.
	 * @see java.lang.Object
	 * @model instanceClass="java.lang.Object"
	 *        extendedMetaData="name='priorityType' memberTypes='priorityType_._member_._0 priorityType_._member_._1'"
	 * @generated
	 */
	EDataType getPriorityType();

	/**
	 * Returns the meta object for data type '{@link org.jbpm.jpdl32.PriorityTypeMember0 <em>Priority Type Member0 Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Priority Type Member0 Object</em>'.
	 * @see org.jbpm.jpdl32.PriorityTypeMember0
	 * @model instanceClass="org.jbpm.jpdl32.PriorityTypeMember0"
	 *        extendedMetaData="name='priorityType_._member_._0:Object' baseType='priorityType_._member_._0'"
	 * @generated
	 */
	EDataType getPriorityTypeMember0Object();

	/**
	 * Returns the meta object for data type '<em>Priority Type Member1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Priority Type Member1</em>'.
	 * @model instanceClass="int"
	 *        extendedMetaData="name='priorityType_._member_._1' baseType='http://www.eclipse.org/emf/2003/XMLType#int'"
	 * @generated
	 */
	EDataType getPriorityTypeMember1();

	/**
	 * Returns the meta object for data type '{@link java.lang.Integer <em>Priority Type Member1 Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Priority Type Member1 Object</em>'.
	 * @see java.lang.Integer
	 * @model instanceClass="java.lang.Integer"
	 *        extendedMetaData="name='priorityType_._member_._1:Object' baseType='priorityType_._member_._1'"
	 * @generated
	 */
	EDataType getPriorityTypeMember1Object();

	/**
	 * Returns the meta object for data type '{@link org.jbpm.jpdl32.SignalType <em>Signal Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Signal Type Object</em>'.
	 * @see org.jbpm.jpdl32.SignalType
	 * @model instanceClass="org.jbpm.jpdl32.SignalType"
	 *        extendedMetaData="name='signal_._type:Object' baseType='signal_._type'"
	 * @generated
	 */
	EDataType getSignalTypeObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object <em>Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type</em>'.
	 * @see java.lang.Object
	 * @model instanceClass="java.lang.Object"
	 *        extendedMetaData="name='type_._type' memberTypes='type_._type_._member_._0 type_._type_._member_._1'"
	 * @generated
	 */
	EDataType getTypeType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Type Type Member0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type Member0</em>'.
	 * @see java.lang.String
	 * @model instanceClass="java.lang.String"
	 *        extendedMetaData="name='type_._type_._member_._0' baseType='http://www.eclipse.org/emf/2003/XMLType#string'"
	 * @generated
	 */
	EDataType getTypeTypeMember0();

	/**
	 * Returns the meta object for data type '{@link org.jbpm.jpdl32.TypeTypeMember1 <em>Type Type Member1 Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Type Member1 Object</em>'.
	 * @see org.jbpm.jpdl32.TypeTypeMember1
	 * @model instanceClass="org.jbpm.jpdl32.TypeTypeMember1"
	 *        extendedMetaData="name='type_._type_._member_._1:Object' baseType='type_._type_._member_._1'"
	 * @generated
	 */
	EDataType getTypeTypeMember1Object();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	jpdl32Factory getjpdl32Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.ActionTypeImpl <em>Action Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.ActionTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getActionType()
		 * @generated
		 */
		EClass ACTION_TYPE = eINSTANCE.getActionType();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__MIXED = eINSTANCE.getActionType_Mixed();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__ANY = eINSTANCE.getActionType_Any();

		/**
		 * The meta object literal for the '<em><b>Accept Propagated Events</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__ACCEPT_PROPAGATED_EVENTS = eINSTANCE.getActionType_AcceptPropagatedEvents();

		/**
		 * The meta object literal for the '<em><b>Async</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__ASYNC = eINSTANCE.getActionType_Async();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__CLASS = eINSTANCE.getActionType_Class();

		/**
		 * The meta object literal for the '<em><b>Config Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__CONFIG_TYPE = eINSTANCE.getActionType_ConfigType();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__EXPRESSION = eINSTANCE.getActionType_Expression();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__NAME = eINSTANCE.getActionType_Name();

		/**
		 * The meta object literal for the '<em><b>Ref Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__REF_NAME = eINSTANCE.getActionType_RefName();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.AssignmentTypeImpl <em>Assignment Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.AssignmentTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getAssignmentType()
		 * @generated
		 */
		EClass ASSIGNMENT_TYPE = eINSTANCE.getAssignmentType();

		/**
		 * The meta object literal for the '<em><b>Actor Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSIGNMENT_TYPE__ACTOR_ID = eINSTANCE.getAssignmentType_ActorId();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSIGNMENT_TYPE__EXPRESSION = eINSTANCE.getAssignmentType_Expression();

		/**
		 * The meta object literal for the '<em><b>Pooled Actors</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSIGNMENT_TYPE__POOLED_ACTORS = eINSTANCE.getAssignmentType_PooledActors();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.CancelTimerTypeImpl <em>Cancel Timer Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.CancelTimerTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getCancelTimerType()
		 * @generated
		 */
		EClass CANCEL_TIMER_TYPE = eINSTANCE.getCancelTimerType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CANCEL_TIMER_TYPE__NAME = eINSTANCE.getCancelTimerType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.ConditionTypeImpl <em>Condition Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.ConditionTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getConditionType()
		 * @generated
		 */
		EClass CONDITION_TYPE = eINSTANCE.getConditionType();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION_TYPE__MIXED = eINSTANCE.getConditionType_Mixed();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION_TYPE__GROUP = eINSTANCE.getConditionType_Group();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION_TYPE__ANY = eINSTANCE.getConditionType_Any();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION_TYPE__EXPRESSION = eINSTANCE.getConditionType_Expression();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.CreateTimerTypeImpl <em>Create Timer Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.CreateTimerTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getCreateTimerType()
		 * @generated
		 */
		EClass CREATE_TIMER_TYPE = eINSTANCE.getCreateTimerType();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CREATE_TIMER_TYPE__ACTION = eINSTANCE.getCreateTimerType_Action();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CREATE_TIMER_TYPE__SCRIPT = eINSTANCE.getCreateTimerType_Script();

		/**
		 * The meta object literal for the '<em><b>Duedate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CREATE_TIMER_TYPE__DUEDATE = eINSTANCE.getCreateTimerType_Duedate();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CREATE_TIMER_TYPE__NAME = eINSTANCE.getCreateTimerType_Name();

		/**
		 * The meta object literal for the '<em><b>Repeat</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CREATE_TIMER_TYPE__REPEAT = eINSTANCE.getCreateTimerType_Repeat();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CREATE_TIMER_TYPE__TRANSITION = eINSTANCE.getCreateTimerType_Transition();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.DecisionTypeImpl <em>Decision Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.DecisionTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getDecisionType()
		 * @generated
		 */
		EClass DECISION_TYPE = eINSTANCE.getDecisionType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECISION_TYPE__GROUP = eINSTANCE.getDecisionType_Group();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECISION_TYPE__DESCRIPTION = eINSTANCE.getDecisionType_Description();

		/**
		 * The meta object literal for the '<em><b>Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECISION_TYPE__HANDLER = eINSTANCE.getDecisionType_Handler();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECISION_TYPE__EVENT = eINSTANCE.getDecisionType_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECISION_TYPE__EXCEPTION_HANDLER = eINSTANCE.getDecisionType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECISION_TYPE__TRANSITION = eINSTANCE.getDecisionType_Transition();

		/**
		 * The meta object literal for the '<em><b>Async</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECISION_TYPE__ASYNC = eINSTANCE.getDecisionType_Async();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECISION_TYPE__EXPRESSION = eINSTANCE.getDecisionType_Expression();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECISION_TYPE__NAME = eINSTANCE.getDecisionType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.DelegationImpl <em>Delegation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.DelegationImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getDelegation()
		 * @generated
		 */
		EClass DELEGATION = eINSTANCE.getDelegation();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELEGATION__MIXED = eINSTANCE.getDelegation_Mixed();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELEGATION__ANY = eINSTANCE.getDelegation_Any();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELEGATION__CLASS = eINSTANCE.getDelegation_Class();

		/**
		 * The meta object literal for the '<em><b>Config Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELEGATION__CONFIG_TYPE = eINSTANCE.getDelegation_ConfigType();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.DocumentRootImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ACTION = eINSTANCE.getDocumentRoot_Action();

		/**
		 * The meta object literal for the '<em><b>Assignment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ASSIGNMENT = eINSTANCE.getDocumentRoot_Assignment();

		/**
		 * The meta object literal for the '<em><b>Cancel Timer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CANCEL_TIMER = eINSTANCE.getDocumentRoot_CancelTimer();

		/**
		 * The meta object literal for the '<em><b>Controller</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONTROLLER = eINSTANCE.getDocumentRoot_Controller();

		/**
		 * The meta object literal for the '<em><b>Create Timer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CREATE_TIMER = eINSTANCE.getDocumentRoot_CreateTimer();

		/**
		 * The meta object literal for the '<em><b>Decision</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DECISION = eINSTANCE.getDocumentRoot_Decision();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__DESCRIPTION = eINSTANCE.getDocumentRoot_Description();

		/**
		 * The meta object literal for the '<em><b>End State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__END_STATE = eINSTANCE.getDocumentRoot_EndState();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EVENT = eINSTANCE.getDocumentRoot_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXCEPTION_HANDLER = eINSTANCE.getDocumentRoot_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Fork</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FORK = eINSTANCE.getDocumentRoot_Fork();

		/**
		 * The meta object literal for the '<em><b>Join</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__JOIN = eINSTANCE.getDocumentRoot_Join();

		/**
		 * The meta object literal for the '<em><b>Mail</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MAIL = eINSTANCE.getDocumentRoot_Mail();

		/**
		 * The meta object literal for the '<em><b>Mail Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MAIL_NODE = eINSTANCE.getDocumentRoot_MailNode();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__NODE = eINSTANCE.getDocumentRoot_Node();

		/**
		 * The meta object literal for the '<em><b>Process Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PROCESS_DEFINITION = eINSTANCE.getDocumentRoot_ProcessDefinition();

		/**
		 * The meta object literal for the '<em><b>Process State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PROCESS_STATE = eINSTANCE.getDocumentRoot_ProcessState();

		/**
		 * The meta object literal for the '<em><b>Recipients</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__RECIPIENTS = eINSTANCE.getDocumentRoot_Recipients();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SCRIPT = eINSTANCE.getDocumentRoot_Script();

		/**
		 * The meta object literal for the '<em><b>Start State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__START_STATE = eINSTANCE.getDocumentRoot_StartState();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STATE = eINSTANCE.getDocumentRoot_State();

		/**
		 * The meta object literal for the '<em><b>Subject</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__SUBJECT = eINSTANCE.getDocumentRoot_Subject();

		/**
		 * The meta object literal for the '<em><b>Super State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SUPER_STATE = eINSTANCE.getDocumentRoot_SuperState();

		/**
		 * The meta object literal for the '<em><b>Swimlane</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SWIMLANE = eINSTANCE.getDocumentRoot_Swimlane();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TASK = eINSTANCE.getDocumentRoot_Task();

		/**
		 * The meta object literal for the '<em><b>Task Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TASK_NODE = eINSTANCE.getDocumentRoot_TaskNode();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__TEMPLATE = eINSTANCE.getDocumentRoot_Template();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__TEXT = eINSTANCE.getDocumentRoot_Text();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TIMER = eINSTANCE.getDocumentRoot_Timer();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__TO = eINSTANCE.getDocumentRoot_To();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TRANSITION = eINSTANCE.getDocumentRoot_Transition();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__VARIABLE = eINSTANCE.getDocumentRoot_Variable();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.EndStateTypeImpl <em>End State Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.EndStateTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getEndStateType()
		 * @generated
		 */
		EClass END_STATE_TYPE = eINSTANCE.getEndStateType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_STATE_TYPE__GROUP = eINSTANCE.getEndStateType_Group();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_STATE_TYPE__DESCRIPTION = eINSTANCE.getEndStateType_Description();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference END_STATE_TYPE__EVENT = eINSTANCE.getEndStateType_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference END_STATE_TYPE__EXCEPTION_HANDLER = eINSTANCE.getEndStateType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>End Complete Process</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_STATE_TYPE__END_COMPLETE_PROCESS = eINSTANCE.getEndStateType_EndCompleteProcess();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_STATE_TYPE__NAME = eINSTANCE.getEndStateType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.EventTypeImpl <em>Event Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.EventTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getEventType()
		 * @generated
		 */
		EClass EVENT_TYPE = eINSTANCE.getEventType();

		/**
		 * The meta object literal for the '<em><b>Action Elements</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_TYPE__ACTION_ELEMENTS = eINSTANCE.getEventType_ActionElements();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_TYPE__ACTION = eINSTANCE.getEventType_Action();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_TYPE__SCRIPT = eINSTANCE.getEventType_Script();

		/**
		 * The meta object literal for the '<em><b>Create Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_TYPE__CREATE_TIMER = eINSTANCE.getEventType_CreateTimer();

		/**
		 * The meta object literal for the '<em><b>Cancel Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_TYPE__CANCEL_TIMER = eINSTANCE.getEventType_CancelTimer();

		/**
		 * The meta object literal for the '<em><b>Mail</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_TYPE__MAIL = eINSTANCE.getEventType_Mail();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_TYPE__TYPE = eINSTANCE.getEventType_Type();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.ExceptionHandlerTypeImpl <em>Exception Handler Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.ExceptionHandlerTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getExceptionHandlerType()
		 * @generated
		 */
		EClass EXCEPTION_HANDLER_TYPE = eINSTANCE.getExceptionHandlerType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCEPTION_HANDLER_TYPE__GROUP = eINSTANCE.getExceptionHandlerType_Group();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXCEPTION_HANDLER_TYPE__ACTION = eINSTANCE.getExceptionHandlerType_Action();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXCEPTION_HANDLER_TYPE__SCRIPT = eINSTANCE.getExceptionHandlerType_Script();

		/**
		 * The meta object literal for the '<em><b>Exception Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCEPTION_HANDLER_TYPE__EXCEPTION_CLASS = eINSTANCE.getExceptionHandlerType_ExceptionClass();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.ForkTypeImpl <em>Fork Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.ForkTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getForkType()
		 * @generated
		 */
		EClass FORK_TYPE = eINSTANCE.getForkType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORK_TYPE__GROUP = eINSTANCE.getForkType_Group();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORK_TYPE__SCRIPT = eINSTANCE.getForkType_Script();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORK_TYPE__DESCRIPTION = eINSTANCE.getForkType_Description();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORK_TYPE__EVENT = eINSTANCE.getForkType_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORK_TYPE__EXCEPTION_HANDLER = eINSTANCE.getForkType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORK_TYPE__TIMER = eINSTANCE.getForkType_Timer();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORK_TYPE__TRANSITION = eINSTANCE.getForkType_Transition();

		/**
		 * The meta object literal for the '<em><b>Async</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORK_TYPE__ASYNC = eINSTANCE.getForkType_Async();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORK_TYPE__NAME = eINSTANCE.getForkType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.JoinTypeImpl <em>Join Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.JoinTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getJoinType()
		 * @generated
		 */
		EClass JOIN_TYPE = eINSTANCE.getJoinType();

		/**
		 * The meta object literal for the '<em><b>Node Content Elements</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__NODE_CONTENT_ELEMENTS = eINSTANCE.getJoinType_NodeContentElements();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__DESCRIPTION = eINSTANCE.getJoinType_Description();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN_TYPE__EVENT = eINSTANCE.getJoinType_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN_TYPE__EXCEPTION_HANDLER = eINSTANCE.getJoinType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN_TYPE__TIMER = eINSTANCE.getJoinType_Timer();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN_TYPE__TRANSITION = eINSTANCE.getJoinType_Transition();

		/**
		 * The meta object literal for the '<em><b>Async</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__ASYNC = eINSTANCE.getJoinType_Async();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__NAME = eINSTANCE.getJoinType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.MailNodeTypeImpl <em>Mail Node Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.MailNodeTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getMailNodeType()
		 * @generated
		 */
		EClass MAIL_NODE_TYPE = eINSTANCE.getMailNodeType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_NODE_TYPE__GROUP = eINSTANCE.getMailNodeType_Group();

		/**
		 * The meta object literal for the '<em><b>Subject</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_NODE_TYPE__SUBJECT = eINSTANCE.getMailNodeType_Subject();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_NODE_TYPE__TEXT = eINSTANCE.getMailNodeType_Text();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_NODE_TYPE__DESCRIPTION = eINSTANCE.getMailNodeType_Description();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAIL_NODE_TYPE__EVENT = eINSTANCE.getMailNodeType_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAIL_NODE_TYPE__EXCEPTION_HANDLER = eINSTANCE.getMailNodeType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAIL_NODE_TYPE__TIMER = eINSTANCE.getMailNodeType_Timer();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAIL_NODE_TYPE__TRANSITION = eINSTANCE.getMailNodeType_Transition();

		/**
		 * The meta object literal for the '<em><b>Actors</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_NODE_TYPE__ACTORS = eINSTANCE.getMailNodeType_Actors();

		/**
		 * The meta object literal for the '<em><b>Async</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_NODE_TYPE__ASYNC = eINSTANCE.getMailNodeType_Async();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_NODE_TYPE__NAME = eINSTANCE.getMailNodeType_Name();

		/**
		 * The meta object literal for the '<em><b>Subject1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_NODE_TYPE__SUBJECT1 = eINSTANCE.getMailNodeType_Subject1();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_NODE_TYPE__TEMPLATE = eINSTANCE.getMailNodeType_Template();

		/**
		 * The meta object literal for the '<em><b>Text1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_NODE_TYPE__TEXT1 = eINSTANCE.getMailNodeType_Text1();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_NODE_TYPE__TO = eINSTANCE.getMailNodeType_To();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.MailTypeImpl <em>Mail Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.MailTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getMailType()
		 * @generated
		 */
		EClass MAIL_TYPE = eINSTANCE.getMailType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_TYPE__GROUP = eINSTANCE.getMailType_Group();

		/**
		 * The meta object literal for the '<em><b>Subject</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_TYPE__SUBJECT = eINSTANCE.getMailType_Subject();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_TYPE__TEXT = eINSTANCE.getMailType_Text();

		/**
		 * The meta object literal for the '<em><b>Actors</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_TYPE__ACTORS = eINSTANCE.getMailType_Actors();

		/**
		 * The meta object literal for the '<em><b>Async</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_TYPE__ASYNC = eINSTANCE.getMailType_Async();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_TYPE__NAME = eINSTANCE.getMailType_Name();

		/**
		 * The meta object literal for the '<em><b>Subject1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_TYPE__SUBJECT1 = eINSTANCE.getMailType_Subject1();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_TYPE__TEMPLATE = eINSTANCE.getMailType_Template();

		/**
		 * The meta object literal for the '<em><b>Text1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_TYPE__TEXT1 = eINSTANCE.getMailType_Text1();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAIL_TYPE__TO = eINSTANCE.getMailType_To();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.NodeTypeImpl <em>Node Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.NodeTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getNodeType()
		 * @generated
		 */
		EClass NODE_TYPE = eINSTANCE.getNodeType();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_TYPE__ACTION = eINSTANCE.getNodeType_Action();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_TYPE__SCRIPT = eINSTANCE.getNodeType_Script();

		/**
		 * The meta object literal for the '<em><b>Create Timer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_TYPE__CREATE_TIMER = eINSTANCE.getNodeType_CreateTimer();

		/**
		 * The meta object literal for the '<em><b>Cancel Timer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_TYPE__CANCEL_TIMER = eINSTANCE.getNodeType_CancelTimer();

		/**
		 * The meta object literal for the '<em><b>Mail</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_TYPE__MAIL = eINSTANCE.getNodeType_Mail();

		/**
		 * The meta object literal for the '<em><b>Node Content Elements</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_TYPE__NODE_CONTENT_ELEMENTS = eINSTANCE.getNodeType_NodeContentElements();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_TYPE__DESCRIPTION = eINSTANCE.getNodeType_Description();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_TYPE__EVENT = eINSTANCE.getNodeType_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_TYPE__EXCEPTION_HANDLER = eINSTANCE.getNodeType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_TYPE__TIMER = eINSTANCE.getNodeType_Timer();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_TYPE__TRANSITION = eINSTANCE.getNodeType_Transition();

		/**
		 * The meta object literal for the '<em><b>Async</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_TYPE__ASYNC = eINSTANCE.getNodeType_Async();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_TYPE__NAME = eINSTANCE.getNodeType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl <em>Process Definition Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.ProcessDefinitionTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getProcessDefinitionType()
		 * @generated
		 */
		EClass PROCESS_DEFINITION_TYPE = eINSTANCE.getProcessDefinitionType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_DEFINITION_TYPE__GROUP = eINSTANCE.getProcessDefinitionType_Group();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_DEFINITION_TYPE__DESCRIPTION = eINSTANCE.getProcessDefinitionType_Description();

		/**
		 * The meta object literal for the '<em><b>Swimlane</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__SWIMLANE = eINSTANCE.getProcessDefinitionType_Swimlane();

		/**
		 * The meta object literal for the '<em><b>Start State</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__START_STATE = eINSTANCE.getProcessDefinitionType_StartState();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__NODE = eINSTANCE.getProcessDefinitionType_Node();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__STATE = eINSTANCE.getProcessDefinitionType_State();

		/**
		 * The meta object literal for the '<em><b>Task Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__TASK_NODE = eINSTANCE.getProcessDefinitionType_TaskNode();

		/**
		 * The meta object literal for the '<em><b>Super State</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__SUPER_STATE = eINSTANCE.getProcessDefinitionType_SuperState();

		/**
		 * The meta object literal for the '<em><b>Process State</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__PROCESS_STATE = eINSTANCE.getProcessDefinitionType_ProcessState();

		/**
		 * The meta object literal for the '<em><b>Fork</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__FORK = eINSTANCE.getProcessDefinitionType_Fork();

		/**
		 * The meta object literal for the '<em><b>Join</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__JOIN = eINSTANCE.getProcessDefinitionType_Join();

		/**
		 * The meta object literal for the '<em><b>Decision</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__DECISION = eINSTANCE.getProcessDefinitionType_Decision();

		/**
		 * The meta object literal for the '<em><b>End State</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__END_STATE = eINSTANCE.getProcessDefinitionType_EndState();

		/**
		 * The meta object literal for the '<em><b>Mail Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__MAIL_NODE = eINSTANCE.getProcessDefinitionType_MailNode();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__ACTION = eINSTANCE.getProcessDefinitionType_Action();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__SCRIPT = eINSTANCE.getProcessDefinitionType_Script();

		/**
		 * The meta object literal for the '<em><b>Create Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__CREATE_TIMER = eINSTANCE.getProcessDefinitionType_CreateTimer();

		/**
		 * The meta object literal for the '<em><b>Cancel Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__CANCEL_TIMER = eINSTANCE.getProcessDefinitionType_CancelTimer();

		/**
		 * The meta object literal for the '<em><b>Mail</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__MAIL = eINSTANCE.getProcessDefinitionType_Mail();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__EVENT = eINSTANCE.getProcessDefinitionType_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__EXCEPTION_HANDLER = eINSTANCE.getProcessDefinitionType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_DEFINITION_TYPE__TASK = eINSTANCE.getProcessDefinitionType_Task();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_DEFINITION_TYPE__NAME = eINSTANCE.getProcessDefinitionType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.ProcessStateTypeImpl <em>Process State Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.ProcessStateTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getProcessStateType()
		 * @generated
		 */
		EClass PROCESS_STATE_TYPE = eINSTANCE.getProcessStateType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_STATE_TYPE__GROUP = eINSTANCE.getProcessStateType_Group();

		/**
		 * The meta object literal for the '<em><b>Sub Process</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_STATE_TYPE__SUB_PROCESS = eINSTANCE.getProcessStateType_SubProcess();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_STATE_TYPE__VARIABLE = eINSTANCE.getProcessStateType_Variable();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_STATE_TYPE__DESCRIPTION = eINSTANCE.getProcessStateType_Description();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_STATE_TYPE__EVENT = eINSTANCE.getProcessStateType_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_STATE_TYPE__EXCEPTION_HANDLER = eINSTANCE.getProcessStateType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_STATE_TYPE__TIMER = eINSTANCE.getProcessStateType_Timer();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_STATE_TYPE__TRANSITION = eINSTANCE.getProcessStateType_Transition();

		/**
		 * The meta object literal for the '<em><b>Async</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_STATE_TYPE__ASYNC = eINSTANCE.getProcessStateType_Async();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_STATE_TYPE__BINDING = eINSTANCE.getProcessStateType_Binding();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_STATE_TYPE__NAME = eINSTANCE.getProcessStateType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.ReminderTypeImpl <em>Reminder Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.ReminderTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getReminderType()
		 * @generated
		 */
		EClass REMINDER_TYPE = eINSTANCE.getReminderType();

		/**
		 * The meta object literal for the '<em><b>Duedate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REMINDER_TYPE__DUEDATE = eINSTANCE.getReminderType_Duedate();

		/**
		 * The meta object literal for the '<em><b>Repeat</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REMINDER_TYPE__REPEAT = eINSTANCE.getReminderType_Repeat();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.ScriptTypeImpl <em>Script Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.ScriptTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getScriptType()
		 * @generated
		 */
		EClass SCRIPT_TYPE = eINSTANCE.getScriptType();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TYPE__MIXED = eINSTANCE.getScriptType_Mixed();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TYPE__ANY = eINSTANCE.getScriptType_Any();

		/**
		 * The meta object literal for the '<em><b>Accept Propagated Events</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TYPE__ACCEPT_PROPAGATED_EVENTS = eINSTANCE.getScriptType_AcceptPropagatedEvents();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TYPE__NAME = eINSTANCE.getScriptType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.StartStateTypeImpl <em>Start State Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.StartStateTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getStartStateType()
		 * @generated
		 */
		EClass START_STATE_TYPE = eINSTANCE.getStartStateType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_STATE_TYPE__GROUP = eINSTANCE.getStartStateType_Group();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_STATE_TYPE__DESCRIPTION = eINSTANCE.getStartStateType_Description();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_STATE_TYPE__TASK = eINSTANCE.getStartStateType_Task();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_STATE_TYPE__TRANSITION = eINSTANCE.getStartStateType_Transition();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_STATE_TYPE__EVENT = eINSTANCE.getStartStateType_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_STATE_TYPE__EXCEPTION_HANDLER = eINSTANCE.getStartStateType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_STATE_TYPE__NAME = eINSTANCE.getStartStateType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.StateTypeImpl <em>State Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.StateTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getStateType()
		 * @generated
		 */
		EClass STATE_TYPE = eINSTANCE.getStateType();

		/**
		 * The meta object literal for the '<em><b>Node Content Elements</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_TYPE__NODE_CONTENT_ELEMENTS = eINSTANCE.getStateType_NodeContentElements();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_TYPE__DESCRIPTION = eINSTANCE.getStateType_Description();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_TYPE__EVENT = eINSTANCE.getStateType_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_TYPE__EXCEPTION_HANDLER = eINSTANCE.getStateType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_TYPE__TIMER = eINSTANCE.getStateType_Timer();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_TYPE__TRANSITION = eINSTANCE.getStateType_Transition();

		/**
		 * The meta object literal for the '<em><b>Async</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_TYPE__ASYNC = eINSTANCE.getStateType_Async();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_TYPE__NAME = eINSTANCE.getStateType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.SubProcessTypeImpl <em>Sub Process Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.SubProcessTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getSubProcessType()
		 * @generated
		 */
		EClass SUB_PROCESS_TYPE = eINSTANCE.getSubProcessType();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__BINDING = eINSTANCE.getSubProcessType_Binding();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__NAME = eINSTANCE.getSubProcessType_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__VERSION = eINSTANCE.getSubProcessType_Version();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.SuperStateTypeImpl <em>Super State Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.SuperStateTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getSuperStateType()
		 * @generated
		 */
		EClass SUPER_STATE_TYPE = eINSTANCE.getSuperStateType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUPER_STATE_TYPE__GROUP = eINSTANCE.getSuperStateType_Group();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__NODE = eINSTANCE.getSuperStateType_Node();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__STATE = eINSTANCE.getSuperStateType_State();

		/**
		 * The meta object literal for the '<em><b>Task Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__TASK_NODE = eINSTANCE.getSuperStateType_TaskNode();

		/**
		 * The meta object literal for the '<em><b>Super State</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__SUPER_STATE = eINSTANCE.getSuperStateType_SuperState();

		/**
		 * The meta object literal for the '<em><b>Process State</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__PROCESS_STATE = eINSTANCE.getSuperStateType_ProcessState();

		/**
		 * The meta object literal for the '<em><b>Fork</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__FORK = eINSTANCE.getSuperStateType_Fork();

		/**
		 * The meta object literal for the '<em><b>Join</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__JOIN = eINSTANCE.getSuperStateType_Join();

		/**
		 * The meta object literal for the '<em><b>Decision</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__DECISION = eINSTANCE.getSuperStateType_Decision();

		/**
		 * The meta object literal for the '<em><b>End State</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__END_STATE = eINSTANCE.getSuperStateType_EndState();

		/**
		 * The meta object literal for the '<em><b>Mail Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__MAIL_NODE = eINSTANCE.getSuperStateType_MailNode();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUPER_STATE_TYPE__DESCRIPTION = eINSTANCE.getSuperStateType_Description();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__EVENT = eINSTANCE.getSuperStateType_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__EXCEPTION_HANDLER = eINSTANCE.getSuperStateType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__TIMER = eINSTANCE.getSuperStateType_Timer();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_STATE_TYPE__TRANSITION = eINSTANCE.getSuperStateType_Transition();

		/**
		 * The meta object literal for the '<em><b>Async</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUPER_STATE_TYPE__ASYNC = eINSTANCE.getSuperStateType_Async();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUPER_STATE_TYPE__NAME = eINSTANCE.getSuperStateType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.SwimlaneTypeImpl <em>Swimlane Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.SwimlaneTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getSwimlaneType()
		 * @generated
		 */
		EClass SWIMLANE_TYPE = eINSTANCE.getSwimlaneType();

		/**
		 * The meta object literal for the '<em><b>Assignment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWIMLANE_TYPE__ASSIGNMENT = eINSTANCE.getSwimlaneType_Assignment();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWIMLANE_TYPE__NAME = eINSTANCE.getSwimlaneType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.TaskNodeTypeImpl <em>Task Node Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.TaskNodeTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTaskNodeType()
		 * @generated
		 */
		EClass TASK_NODE_TYPE = eINSTANCE.getTaskNodeType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE_TYPE__GROUP = eINSTANCE.getTaskNodeType_Group();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_NODE_TYPE__TASK = eINSTANCE.getTaskNodeType_Task();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE_TYPE__DESCRIPTION = eINSTANCE.getTaskNodeType_Description();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_NODE_TYPE__EVENT = eINSTANCE.getTaskNodeType_Event();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_NODE_TYPE__EXCEPTION_HANDLER = eINSTANCE.getTaskNodeType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_NODE_TYPE__TIMER = eINSTANCE.getTaskNodeType_Timer();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_NODE_TYPE__TRANSITION = eINSTANCE.getTaskNodeType_Transition();

		/**
		 * The meta object literal for the '<em><b>Async</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE_TYPE__ASYNC = eINSTANCE.getTaskNodeType_Async();

		/**
		 * The meta object literal for the '<em><b>Create Tasks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE_TYPE__CREATE_TASKS = eINSTANCE.getTaskNodeType_CreateTasks();

		/**
		 * The meta object literal for the '<em><b>End Tasks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE_TYPE__END_TASKS = eINSTANCE.getTaskNodeType_EndTasks();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE_TYPE__NAME = eINSTANCE.getTaskNodeType_Name();

		/**
		 * The meta object literal for the '<em><b>Signal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_NODE_TYPE__SIGNAL = eINSTANCE.getTaskNodeType_Signal();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.TaskTypeImpl <em>Task Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.TaskTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTaskType()
		 * @generated
		 */
		EClass TASK_TYPE = eINSTANCE.getTaskType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_TYPE__GROUP = eINSTANCE.getTaskType_Group();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_TYPE__DESCRIPTION = eINSTANCE.getTaskType_Description();

		/**
		 * The meta object literal for the '<em><b>Assignment</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_TYPE__ASSIGNMENT = eINSTANCE.getTaskType_Assignment();

		/**
		 * The meta object literal for the '<em><b>Controller</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_TYPE__CONTROLLER = eINSTANCE.getTaskType_Controller();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_TYPE__EVENT = eINSTANCE.getTaskType_Event();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_TYPE__TIMER = eINSTANCE.getTaskType_Timer();

		/**
		 * The meta object literal for the '<em><b>Reminder</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_TYPE__REMINDER = eINSTANCE.getTaskType_Reminder();

		/**
		 * The meta object literal for the '<em><b>Blocking</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_TYPE__BLOCKING = eINSTANCE.getTaskType_Blocking();

		/**
		 * The meta object literal for the '<em><b>Description1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_TYPE__DESCRIPTION1 = eINSTANCE.getTaskType_Description1();

		/**
		 * The meta object literal for the '<em><b>Duedate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_TYPE__DUEDATE = eINSTANCE.getTaskType_Duedate();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_TYPE__NAME = eINSTANCE.getTaskType_Name();

		/**
		 * The meta object literal for the '<em><b>Notify</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_TYPE__NOTIFY = eINSTANCE.getTaskType_Notify();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_TYPE__PRIORITY = eINSTANCE.getTaskType_Priority();

		/**
		 * The meta object literal for the '<em><b>Signalling</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_TYPE__SIGNALLING = eINSTANCE.getTaskType_Signalling();

		/**
		 * The meta object literal for the '<em><b>Swimlane</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_TYPE__SWIMLANE = eINSTANCE.getTaskType_Swimlane();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.TimerTypeImpl <em>Timer Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.TimerTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTimerType()
		 * @generated
		 */
		EClass TIMER_TYPE = eINSTANCE.getTimerType();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMER_TYPE__ACTION = eINSTANCE.getTimerType_Action();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMER_TYPE__SCRIPT = eINSTANCE.getTimerType_Script();

		/**
		 * The meta object literal for the '<em><b>Create Timer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMER_TYPE__CREATE_TIMER = eINSTANCE.getTimerType_CreateTimer();

		/**
		 * The meta object literal for the '<em><b>Cancel Timer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMER_TYPE__CANCEL_TIMER = eINSTANCE.getTimerType_CancelTimer();

		/**
		 * The meta object literal for the '<em><b>Mail</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMER_TYPE__MAIL = eINSTANCE.getTimerType_Mail();

		/**
		 * The meta object literal for the '<em><b>Duedate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_TYPE__DUEDATE = eINSTANCE.getTimerType_Duedate();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_TYPE__NAME = eINSTANCE.getTimerType_Name();

		/**
		 * The meta object literal for the '<em><b>Repeat</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_TYPE__REPEAT = eINSTANCE.getTimerType_Repeat();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_TYPE__TRANSITION = eINSTANCE.getTimerType_Transition();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.TransitionTypeImpl <em>Transition Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.TransitionTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTransitionType()
		 * @generated
		 */
		EClass TRANSITION_TYPE = eINSTANCE.getTransitionType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION_TYPE__GROUP = eINSTANCE.getTransitionType_Group();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION_TYPE__DESCRIPTION = eINSTANCE.getTransitionType_Description();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_TYPE__CONDITION = eINSTANCE.getTransitionType_Condition();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_TYPE__ACTION = eINSTANCE.getTransitionType_Action();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_TYPE__SCRIPT = eINSTANCE.getTransitionType_Script();

		/**
		 * The meta object literal for the '<em><b>Create Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_TYPE__CREATE_TIMER = eINSTANCE.getTransitionType_CreateTimer();

		/**
		 * The meta object literal for the '<em><b>Cancel Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_TYPE__CANCEL_TIMER = eINSTANCE.getTransitionType_CancelTimer();

		/**
		 * The meta object literal for the '<em><b>Mail</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_TYPE__MAIL = eINSTANCE.getTransitionType_Mail();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_TYPE__EXCEPTION_HANDLER = eINSTANCE.getTransitionType_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION_TYPE__NAME = eINSTANCE.getTransitionType_Name();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION_TYPE__TO = eINSTANCE.getTransitionType_To();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.impl.VariableTypeImpl <em>Variable Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.VariableTypeImpl
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getVariableType()
		 * @generated
		 */
		EClass VARIABLE_TYPE = eINSTANCE.getVariableType();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_TYPE__ANY = eINSTANCE.getVariableType_Any();

		/**
		 * The meta object literal for the '<em><b>Access</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_TYPE__ACCESS = eINSTANCE.getVariableType_Access();

		/**
		 * The meta object literal for the '<em><b>Mapped Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_TYPE__MAPPED_NAME = eINSTANCE.getVariableType_MappedName();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_TYPE__NAME = eINSTANCE.getVariableType_Name();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.BindingType <em>Binding Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.BindingType
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getBindingType()
		 * @generated
		 */
		EEnum BINDING_TYPE = eINSTANCE.getBindingType();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.BooleanType <em>Boolean Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.BooleanType
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getBooleanType()
		 * @generated
		 */
		EEnum BOOLEAN_TYPE = eINSTANCE.getBooleanType();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.ConfigType <em>Config Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.ConfigType
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getConfigType()
		 * @generated
		 */
		EEnum CONFIG_TYPE = eINSTANCE.getConfigType();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.PriorityTypeMember0 <em>Priority Type Member0</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.PriorityTypeMember0
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getPriorityTypeMember0()
		 * @generated
		 */
		EEnum PRIORITY_TYPE_MEMBER0 = eINSTANCE.getPriorityTypeMember0();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.SignalType <em>Signal Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.SignalType
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getSignalType()
		 * @generated
		 */
		EEnum SIGNAL_TYPE = eINSTANCE.getSignalType();

		/**
		 * The meta object literal for the '{@link org.jbpm.jpdl32.TypeTypeMember1 <em>Type Type Member1</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.TypeTypeMember1
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTypeTypeMember1()
		 * @generated
		 */
		EEnum TYPE_TYPE_MEMBER1 = eINSTANCE.getTypeTypeMember1();

		/**
		 * The meta object literal for the '<em>Binding Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.BindingType
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getBindingTypeObject()
		 * @generated
		 */
		EDataType BINDING_TYPE_OBJECT = eINSTANCE.getBindingTypeObject();

		/**
		 * The meta object literal for the '<em>Boolean Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.BooleanType
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getBooleanTypeObject()
		 * @generated
		 */
		EDataType BOOLEAN_TYPE_OBJECT = eINSTANCE.getBooleanTypeObject();

		/**
		 * The meta object literal for the '<em>Config Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.ConfigType
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getConfigTypeObject()
		 * @generated
		 */
		EDataType CONFIG_TYPE_OBJECT = eINSTANCE.getConfigTypeObject();

		/**
		 * The meta object literal for the '<em>Priority Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getPriorityType()
		 * @generated
		 */
		EDataType PRIORITY_TYPE = eINSTANCE.getPriorityType();

		/**
		 * The meta object literal for the '<em>Priority Type Member0 Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.PriorityTypeMember0
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getPriorityTypeMember0Object()
		 * @generated
		 */
		EDataType PRIORITY_TYPE_MEMBER0_OBJECT = eINSTANCE.getPriorityTypeMember0Object();

		/**
		 * The meta object literal for the '<em>Priority Type Member1</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getPriorityTypeMember1()
		 * @generated
		 */
		EDataType PRIORITY_TYPE_MEMBER1 = eINSTANCE.getPriorityTypeMember1();

		/**
		 * The meta object literal for the '<em>Priority Type Member1 Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Integer
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getPriorityTypeMember1Object()
		 * @generated
		 */
		EDataType PRIORITY_TYPE_MEMBER1_OBJECT = eINSTANCE.getPriorityTypeMember1Object();

		/**
		 * The meta object literal for the '<em>Signal Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.SignalType
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getSignalTypeObject()
		 * @generated
		 */
		EDataType SIGNAL_TYPE_OBJECT = eINSTANCE.getSignalTypeObject();

		/**
		 * The meta object literal for the '<em>Type Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTypeType()
		 * @generated
		 */
		EDataType TYPE_TYPE = eINSTANCE.getTypeType();

		/**
		 * The meta object literal for the '<em>Type Type Member0</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTypeTypeMember0()
		 * @generated
		 */
		EDataType TYPE_TYPE_MEMBER0 = eINSTANCE.getTypeTypeMember0();

		/**
		 * The meta object literal for the '<em>Type Type Member1 Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.jbpm.jpdl32.TypeTypeMember1
		 * @see org.jbpm.jpdl32.impl.jpdl32PackageImpl#getTypeTypeMember1Object()
		 * @generated
		 */
		EDataType TYPE_TYPE_MEMBER1_OBJECT = eINSTANCE.getTypeTypeMember1Object();

	}

} //jpdl32Package
