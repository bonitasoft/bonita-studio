/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

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
 * @see org.omg.spec.bpmn.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/spec/BPMN/20100524/MODEL";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = org.omg.spec.bpmn.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.DocumentRootImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 0;

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
	 * The feature id for the '<em><b>Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ACTIVITY = 3;

	/**
	 * The feature id for the '<em><b>Ad Hoc Sub Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__AD_HOC_SUB_PROCESS = 4;

	/**
	 * The feature id for the '<em><b>Flow Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FLOW_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ARTIFACT = 6;

	/**
	 * The feature id for the '<em><b>Assignment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ASSIGNMENT = 7;

	/**
	 * The feature id for the '<em><b>Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ASSOCIATION = 8;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__AUDITING = 9;

	/**
	 * The feature id for the '<em><b>Base Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BASE_ELEMENT = 10;

	/**
	 * The feature id for the '<em><b>Base Element With Mixed Content</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT = 11;

	/**
	 * The feature id for the '<em><b>Boundary Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BOUNDARY_EVENT = 12;

	/**
	 * The feature id for the '<em><b>Business Rule Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BUSINESS_RULE_TASK = 13;

	/**
	 * The feature id for the '<em><b>Callable Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CALLABLE_ELEMENT = 14;

	/**
	 * The feature id for the '<em><b>Call Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CALL_ACTIVITY = 15;

	/**
	 * The feature id for the '<em><b>Call Choreography</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CALL_CHOREOGRAPHY = 16;

	/**
	 * The feature id for the '<em><b>Call Conversation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CALL_CONVERSATION = 17;

	/**
	 * The feature id for the '<em><b>Conversation Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONVERSATION_NODE = 18;

	/**
	 * The feature id for the '<em><b>Cancel Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION = 19;

	/**
	 * The feature id for the '<em><b>Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EVENT_DEFINITION = 20;

	/**
	 * The feature id for the '<em><b>Root Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ROOT_ELEMENT = 21;

	/**
	 * The feature id for the '<em><b>Catch Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CATCH_EVENT = 22;

	/**
	 * The feature id for the '<em><b>Category</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CATEGORY = 23;

	/**
	 * The feature id for the '<em><b>Category Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CATEGORY_VALUE = 24;

	/**
	 * The feature id for the '<em><b>Choreography</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CHOREOGRAPHY = 25;

	/**
	 * The feature id for the '<em><b>Collaboration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COLLABORATION = 26;

	/**
	 * The feature id for the '<em><b>Choreography Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY = 27;

	/**
	 * The feature id for the '<em><b>Choreography Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CHOREOGRAPHY_TASK = 28;

	/**
	 * The feature id for the '<em><b>Compensate Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION = 29;

	/**
	 * The feature id for the '<em><b>Complex Behavior Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION = 30;

	/**
	 * The feature id for the '<em><b>Complex Gateway</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMPLEX_GATEWAY = 31;

	/**
	 * The feature id for the '<em><b>Conditional Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION = 32;

	/**
	 * The feature id for the '<em><b>Conversation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONVERSATION = 33;

	/**
	 * The feature id for the '<em><b>Conversation Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONVERSATION_ASSOCIATION = 34;

	/**
	 * The feature id for the '<em><b>Conversation Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONVERSATION_LINK = 35;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CORRELATION_KEY = 36;

	/**
	 * The feature id for the '<em><b>Correlation Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CORRELATION_PROPERTY = 37;

	/**
	 * The feature id for the '<em><b>Correlation Property Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING = 38;

	/**
	 * The feature id for the '<em><b>Correlation Property Retrieval Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION = 39;

	/**
	 * The feature id for the '<em><b>Correlation Subscription</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION = 40;

	/**
	 * The feature id for the '<em><b>Data Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_ASSOCIATION = 41;

	/**
	 * The feature id for the '<em><b>Data Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_INPUT = 42;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION = 43;

	/**
	 * The feature id for the '<em><b>Data Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_OBJECT = 44;

	/**
	 * The feature id for the '<em><b>Data Object Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_OBJECT_REFERENCE = 45;

	/**
	 * The feature id for the '<em><b>Data Output</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_OUTPUT = 46;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION = 47;

	/**
	 * The feature id for the '<em><b>Data State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_STATE = 48;

	/**
	 * The feature id for the '<em><b>Data Store</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_STORE = 49;

	/**
	 * The feature id for the '<em><b>Data Store Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DATA_STORE_REFERENCE = 50;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DEFINITIONS = 51;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DOCUMENTATION = 52;

	/**
	 * The feature id for the '<em><b>End Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__END_EVENT = 53;

	/**
	 * The feature id for the '<em><b>End Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__END_POINT = 54;

	/**
	 * The feature id for the '<em><b>Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ERROR = 55;

	/**
	 * The feature id for the '<em><b>Error Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ERROR_EVENT_DEFINITION = 56;

	/**
	 * The feature id for the '<em><b>Escalation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ESCALATION = 57;

	/**
	 * The feature id for the '<em><b>Escalation Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION = 58;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EVENT = 59;

	/**
	 * The feature id for the '<em><b>Event Based Gateway</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EVENT_BASED_GATEWAY = 60;

	/**
	 * The feature id for the '<em><b>Exclusive Gateway</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXCLUSIVE_GATEWAY = 61;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXPRESSION = 62;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXTENSION = 63;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXTENSION_ELEMENTS = 64;

	/**
	 * The feature id for the '<em><b>Flow Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FLOW_NODE = 65;

	/**
	 * The feature id for the '<em><b>Formal Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FORMAL_EXPRESSION = 66;

	/**
	 * The feature id for the '<em><b>Gateway</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GATEWAY = 67;

	/**
	 * The feature id for the '<em><b>Global Business Rule Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK = 68;

	/**
	 * The feature id for the '<em><b>Global Choreography Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK = 69;

	/**
	 * The feature id for the '<em><b>Global Conversation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GLOBAL_CONVERSATION = 70;

	/**
	 * The feature id for the '<em><b>Global Manual Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GLOBAL_MANUAL_TASK = 71;

	/**
	 * The feature id for the '<em><b>Global Script Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK = 72;

	/**
	 * The feature id for the '<em><b>Global Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GLOBAL_TASK = 73;

	/**
	 * The feature id for the '<em><b>Global User Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GLOBAL_USER_TASK = 74;

	/**
	 * The feature id for the '<em><b>Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GROUP = 75;

	/**
	 * The feature id for the '<em><b>Human Performer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__HUMAN_PERFORMER = 76;

	/**
	 * The feature id for the '<em><b>Performer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PERFORMER = 77;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESOURCE_ROLE = 78;

	/**
	 * The feature id for the '<em><b>Implicit Throw Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IMPLICIT_THROW_EVENT = 79;

	/**
	 * The feature id for the '<em><b>Import</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IMPORT = 80;

	/**
	 * The feature id for the '<em><b>Inclusive Gateway</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__INCLUSIVE_GATEWAY = 81;

	/**
	 * The feature id for the '<em><b>Input Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__INPUT_SET = 82;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__INTERFACE = 83;

	/**
	 * The feature id for the '<em><b>Intermediate Catch Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT = 84;

	/**
	 * The feature id for the '<em><b>Intermediate Throw Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT = 85;

	/**
	 * The feature id for the '<em><b>Io Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IO_BINDING = 86;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IO_SPECIFICATION = 87;

	/**
	 * The feature id for the '<em><b>Item Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ITEM_DEFINITION = 88;

	/**
	 * The feature id for the '<em><b>Lane</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LANE = 89;

	/**
	 * The feature id for the '<em><b>Lane Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LANE_SET = 90;

	/**
	 * The feature id for the '<em><b>Link Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LINK_EVENT_DEFINITION = 91;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LOOP_CHARACTERISTICS = 92;

	/**
	 * The feature id for the '<em><b>Manual Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MANUAL_TASK = 93;

	/**
	 * The feature id for the '<em><b>Message</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MESSAGE = 94;

	/**
	 * The feature id for the '<em><b>Message Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION = 95;

	/**
	 * The feature id for the '<em><b>Message Flow</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MESSAGE_FLOW = 96;

	/**
	 * The feature id for the '<em><b>Message Flow Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION = 97;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MONITORING = 98;

	/**
	 * The feature id for the '<em><b>Multi Instance Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS = 99;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OPERATION = 100;

	/**
	 * The feature id for the '<em><b>Output Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OUTPUT_SET = 101;

	/**
	 * The feature id for the '<em><b>Parallel Gateway</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARALLEL_GATEWAY = 102;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARTICIPANT = 103;

	/**
	 * The feature id for the '<em><b>Participant Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION = 104;

	/**
	 * The feature id for the '<em><b>Participant Multiplicity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY = 105;

	/**
	 * The feature id for the '<em><b>Partner Entity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARTNER_ENTITY = 106;

	/**
	 * The feature id for the '<em><b>Partner Role</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARTNER_ROLE = 107;

	/**
	 * The feature id for the '<em><b>Potential Owner</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__POTENTIAL_OWNER = 108;

	/**
	 * The feature id for the '<em><b>Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROCESS = 109;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROPERTY = 110;

	/**
	 * The feature id for the '<em><b>Receive Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RECEIVE_TASK = 111;

	/**
	 * The feature id for the '<em><b>Relationship</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RELATIONSHIP = 112;

	/**
	 * The feature id for the '<em><b>Rendering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RENDERING = 113;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESOURCE = 114;

	/**
	 * The feature id for the '<em><b>Resource Assignment Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION = 115;

	/**
	 * The feature id for the '<em><b>Resource Parameter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESOURCE_PARAMETER = 116;

	/**
	 * The feature id for the '<em><b>Resource Parameter Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING = 117;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SCRIPT = 118;

	/**
	 * The feature id for the '<em><b>Script Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SCRIPT_TASK = 119;

	/**
	 * The feature id for the '<em><b>Send Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SEND_TASK = 120;

	/**
	 * The feature id for the '<em><b>Sequence Flow</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SEQUENCE_FLOW = 121;

	/**
	 * The feature id for the '<em><b>Service Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SERVICE_TASK = 122;

	/**
	 * The feature id for the '<em><b>Signal</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SIGNAL = 123;

	/**
	 * The feature id for the '<em><b>Signal Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION = 124;

	/**
	 * The feature id for the '<em><b>Standard Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS = 125;

	/**
	 * The feature id for the '<em><b>Start Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__START_EVENT = 126;

	/**
	 * The feature id for the '<em><b>Sub Choreography</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SUB_CHOREOGRAPHY = 127;

	/**
	 * The feature id for the '<em><b>Sub Conversation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SUB_CONVERSATION = 128;

	/**
	 * The feature id for the '<em><b>Sub Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SUB_PROCESS = 129;

	/**
	 * The feature id for the '<em><b>Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TASK = 130;

	/**
	 * The feature id for the '<em><b>Terminate Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION = 131;

	/**
	 * The feature id for the '<em><b>Text</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TEXT = 132;

	/**
	 * The feature id for the '<em><b>Text Annotation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TEXT_ANNOTATION = 133;

	/**
	 * The feature id for the '<em><b>Throw Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__THROW_EVENT = 134;

	/**
	 * The feature id for the '<em><b>Timer Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TIMER_EVENT_DEFINITION = 135;

	/**
	 * The feature id for the '<em><b>Transaction</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TRANSACTION = 136;

	/**
	 * The feature id for the '<em><b>User Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__USER_TASK = 137;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 138;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TBaseElementImpl <em>TBase Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TBaseElementImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTBaseElement()
	 * @generated
	 */
	int TBASE_ELEMENT = 7;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBASE_ELEMENT__DOCUMENTATION = 0;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBASE_ELEMENT__EXTENSION_ELEMENTS = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBASE_ELEMENT__ID = 2;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBASE_ELEMENT__ANY_ATTRIBUTE = 3;

	/**
	 * The number of structural features of the '<em>TBase Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBASE_ELEMENT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TFlowElementImpl <em>TFlow Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TFlowElementImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTFlowElement()
	 * @generated
	 */
	int TFLOW_ELEMENT = 61;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_ELEMENT__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_ELEMENT__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_ELEMENT__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_ELEMENT__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_ELEMENT__AUDITING = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_ELEMENT__MONITORING = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_ELEMENT__CATEGORY_VALUE_REF = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_ELEMENT__NAME = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TFlow Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_ELEMENT_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TFlowNodeImpl <em>TFlow Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TFlowNodeImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTFlowNode()
	 * @generated
	 */
	int TFLOW_NODE = 62;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_NODE__DOCUMENTATION = TFLOW_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_NODE__EXTENSION_ELEMENTS = TFLOW_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_NODE__ID = TFLOW_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_NODE__ANY_ATTRIBUTE = TFLOW_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_NODE__AUDITING = TFLOW_ELEMENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_NODE__MONITORING = TFLOW_ELEMENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_NODE__CATEGORY_VALUE_REF = TFLOW_ELEMENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_NODE__NAME = TFLOW_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_NODE__INCOMING = TFLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_NODE__OUTGOING = TFLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TFlow Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFLOW_NODE_FEATURE_COUNT = TFLOW_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TActivityImpl <em>TActivity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TActivityImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTActivity()
	 * @generated
	 */
	int TACTIVITY = 1;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__DOCUMENTATION = TFLOW_NODE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__EXTENSION_ELEMENTS = TFLOW_NODE__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__ID = TFLOW_NODE__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__ANY_ATTRIBUTE = TFLOW_NODE__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__AUDITING = TFLOW_NODE__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__MONITORING = TFLOW_NODE__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__CATEGORY_VALUE_REF = TFLOW_NODE__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__NAME = TFLOW_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__INCOMING = TFLOW_NODE__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__OUTGOING = TFLOW_NODE__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__IO_SPECIFICATION = TFLOW_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__PROPERTY = TFLOW_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__DATA_INPUT_ASSOCIATION = TFLOW_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__DATA_OUTPUT_ASSOCIATION = TFLOW_NODE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__RESOURCE_ROLE_GROUP = TFLOW_NODE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__RESOURCE_ROLE = TFLOW_NODE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__LOOP_CHARACTERISTICS_GROUP = TFLOW_NODE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__LOOP_CHARACTERISTICS = TFLOW_NODE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__COMPLETION_QUANTITY = TFLOW_NODE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__DEFAULT = TFLOW_NODE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__IS_FOR_COMPENSATION = TFLOW_NODE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY__START_QUANTITY = TFLOW_NODE_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>TActivity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIVITY_FEATURE_COUNT = TFLOW_NODE_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TSubProcessImpl <em>TSub Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TSubProcessImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSubProcess()
	 * @generated
	 */
	int TSUB_PROCESS = 127;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__DOCUMENTATION = TACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__EXTENSION_ELEMENTS = TACTIVITY__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__ID = TACTIVITY__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__ANY_ATTRIBUTE = TACTIVITY__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__AUDITING = TACTIVITY__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__MONITORING = TACTIVITY__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__CATEGORY_VALUE_REF = TACTIVITY__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__NAME = TACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__INCOMING = TACTIVITY__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__OUTGOING = TACTIVITY__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__IO_SPECIFICATION = TACTIVITY__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__PROPERTY = TACTIVITY__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__DATA_INPUT_ASSOCIATION = TACTIVITY__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__DATA_OUTPUT_ASSOCIATION = TACTIVITY__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__RESOURCE_ROLE_GROUP = TACTIVITY__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__RESOURCE_ROLE = TACTIVITY__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__LOOP_CHARACTERISTICS_GROUP = TACTIVITY__LOOP_CHARACTERISTICS_GROUP;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__LOOP_CHARACTERISTICS = TACTIVITY__LOOP_CHARACTERISTICS;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__COMPLETION_QUANTITY = TACTIVITY__COMPLETION_QUANTITY;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__DEFAULT = TACTIVITY__DEFAULT;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__IS_FOR_COMPENSATION = TACTIVITY__IS_FOR_COMPENSATION;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__START_QUANTITY = TACTIVITY__START_QUANTITY;

	/**
	 * The feature id for the '<em><b>Lane Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__LANE_SET = TACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Flow Element Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__FLOW_ELEMENT_GROUP = TACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Flow Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__FLOW_ELEMENT = TACTIVITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Artifact Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__ARTIFACT_GROUP = TACTIVITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__ARTIFACT = TACTIVITY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Triggered By Event</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS__TRIGGERED_BY_EVENT = TACTIVITY_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>TSub Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_PROCESS_FEATURE_COUNT = TACTIVITY_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TAdHocSubProcessImpl <em>TAd Hoc Sub Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TAdHocSubProcessImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAdHocSubProcess()
	 * @generated
	 */
	int TAD_HOC_SUB_PROCESS = 2;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__DOCUMENTATION = TSUB_PROCESS__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__EXTENSION_ELEMENTS = TSUB_PROCESS__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__ID = TSUB_PROCESS__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__ANY_ATTRIBUTE = TSUB_PROCESS__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__AUDITING = TSUB_PROCESS__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__MONITORING = TSUB_PROCESS__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__CATEGORY_VALUE_REF = TSUB_PROCESS__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__NAME = TSUB_PROCESS__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__INCOMING = TSUB_PROCESS__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__OUTGOING = TSUB_PROCESS__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__IO_SPECIFICATION = TSUB_PROCESS__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__PROPERTY = TSUB_PROCESS__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__DATA_INPUT_ASSOCIATION = TSUB_PROCESS__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__DATA_OUTPUT_ASSOCIATION = TSUB_PROCESS__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__RESOURCE_ROLE_GROUP = TSUB_PROCESS__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__RESOURCE_ROLE = TSUB_PROCESS__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__LOOP_CHARACTERISTICS_GROUP = TSUB_PROCESS__LOOP_CHARACTERISTICS_GROUP;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__LOOP_CHARACTERISTICS = TSUB_PROCESS__LOOP_CHARACTERISTICS;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__COMPLETION_QUANTITY = TSUB_PROCESS__COMPLETION_QUANTITY;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__DEFAULT = TSUB_PROCESS__DEFAULT;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__IS_FOR_COMPENSATION = TSUB_PROCESS__IS_FOR_COMPENSATION;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__START_QUANTITY = TSUB_PROCESS__START_QUANTITY;

	/**
	 * The feature id for the '<em><b>Lane Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__LANE_SET = TSUB_PROCESS__LANE_SET;

	/**
	 * The feature id for the '<em><b>Flow Element Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__FLOW_ELEMENT_GROUP = TSUB_PROCESS__FLOW_ELEMENT_GROUP;

	/**
	 * The feature id for the '<em><b>Flow Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__FLOW_ELEMENT = TSUB_PROCESS__FLOW_ELEMENT;

	/**
	 * The feature id for the '<em><b>Artifact Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__ARTIFACT_GROUP = TSUB_PROCESS__ARTIFACT_GROUP;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__ARTIFACT = TSUB_PROCESS__ARTIFACT;

	/**
	 * The feature id for the '<em><b>Triggered By Event</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__TRIGGERED_BY_EVENT = TSUB_PROCESS__TRIGGERED_BY_EVENT;

	/**
	 * The feature id for the '<em><b>Completion Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__COMPLETION_CONDITION = TSUB_PROCESS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cancel Remaining Instances</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__CANCEL_REMAINING_INSTANCES = TSUB_PROCESS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS__ORDERING = TSUB_PROCESS_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TAd Hoc Sub Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAD_HOC_SUB_PROCESS_FEATURE_COUNT = TSUB_PROCESS_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TArtifactImpl <em>TArtifact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TArtifactImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTArtifact()
	 * @generated
	 */
	int TARTIFACT = 3;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARTIFACT__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARTIFACT__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARTIFACT__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARTIFACT__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>TArtifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARTIFACT_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TAssignmentImpl <em>TAssignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TAssignmentImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAssignment()
	 * @generated
	 */
	int TASSIGNMENT = 4;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSIGNMENT__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSIGNMENT__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSIGNMENT__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSIGNMENT__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>From</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSIGNMENT__FROM = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSIGNMENT__TO = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TAssignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSIGNMENT_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TAssociationImpl <em>TAssociation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TAssociationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAssociation()
	 * @generated
	 */
	int TASSOCIATION = 5;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSOCIATION__DOCUMENTATION = TARTIFACT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSOCIATION__EXTENSION_ELEMENTS = TARTIFACT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSOCIATION__ID = TARTIFACT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSOCIATION__ANY_ATTRIBUTE = TARTIFACT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Association Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSOCIATION__ASSOCIATION_DIRECTION = TARTIFACT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSOCIATION__SOURCE_REF = TARTIFACT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSOCIATION__TARGET_REF = TARTIFACT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TAssociation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASSOCIATION_FEATURE_COUNT = TARTIFACT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TAuditingImpl <em>TAuditing</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TAuditingImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAuditing()
	 * @generated
	 */
	int TAUDITING = 6;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAUDITING__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAUDITING__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAUDITING__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAUDITING__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>TAuditing</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAUDITING_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TBaseElementWithMixedContentImpl <em>TBase Element With Mixed Content</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TBaseElementWithMixedContentImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTBaseElementWithMixedContent()
	 * @generated
	 */
	int TBASE_ELEMENT_WITH_MIXED_CONTENT = 8;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBASE_ELEMENT_WITH_MIXED_CONTENT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBASE_ELEMENT_WITH_MIXED_CONTENT__DOCUMENTATION = 1;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBASE_ELEMENT_WITH_MIXED_CONTENT__EXTENSION_ELEMENTS = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBASE_ELEMENT_WITH_MIXED_CONTENT__ID = 3;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBASE_ELEMENT_WITH_MIXED_CONTENT__ANY_ATTRIBUTE = 4;

	/**
	 * The number of structural features of the '<em>TBase Element With Mixed Content</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBASE_ELEMENT_WITH_MIXED_CONTENT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TEventImpl <em>TEvent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TEventImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEvent()
	 * @generated
	 */
	int TEVENT = 54;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT__DOCUMENTATION = TFLOW_NODE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT__EXTENSION_ELEMENTS = TFLOW_NODE__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT__ID = TFLOW_NODE__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT__ANY_ATTRIBUTE = TFLOW_NODE__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT__AUDITING = TFLOW_NODE__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT__MONITORING = TFLOW_NODE__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT__CATEGORY_VALUE_REF = TFLOW_NODE__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT__NAME = TFLOW_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT__INCOMING = TFLOW_NODE__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT__OUTGOING = TFLOW_NODE__OUTGOING;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT__PROPERTY = TFLOW_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TEvent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_FEATURE_COUNT = TFLOW_NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCatchEventImpl <em>TCatch Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCatchEventImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCatchEvent()
	 * @generated
	 */
	int TCATCH_EVENT = 16;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__DOCUMENTATION = TEVENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__EXTENSION_ELEMENTS = TEVENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__ID = TEVENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__ANY_ATTRIBUTE = TEVENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__AUDITING = TEVENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__MONITORING = TEVENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__CATEGORY_VALUE_REF = TEVENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__NAME = TEVENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__INCOMING = TEVENT__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__OUTGOING = TEVENT__OUTGOING;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__PROPERTY = TEVENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Output</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__DATA_OUTPUT = TEVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__DATA_OUTPUT_ASSOCIATION = TEVENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Output Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__OUTPUT_SET = TEVENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Event Definition Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__EVENT_DEFINITION_GROUP = TEVENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Event Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__EVENT_DEFINITION = TEVENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Event Definition Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__EVENT_DEFINITION_REF = TEVENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Parallel Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT__PARALLEL_MULTIPLE = TEVENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>TCatch Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATCH_EVENT_FEATURE_COUNT = TEVENT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TBoundaryEventImpl <em>TBoundary Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TBoundaryEventImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTBoundaryEvent()
	 * @generated
	 */
	int TBOUNDARY_EVENT = 9;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__DOCUMENTATION = TCATCH_EVENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__EXTENSION_ELEMENTS = TCATCH_EVENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__ID = TCATCH_EVENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__ANY_ATTRIBUTE = TCATCH_EVENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__AUDITING = TCATCH_EVENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__MONITORING = TCATCH_EVENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__CATEGORY_VALUE_REF = TCATCH_EVENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__NAME = TCATCH_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__INCOMING = TCATCH_EVENT__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__OUTGOING = TCATCH_EVENT__OUTGOING;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__PROPERTY = TCATCH_EVENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Output</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__DATA_OUTPUT = TCATCH_EVENT__DATA_OUTPUT;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__DATA_OUTPUT_ASSOCIATION = TCATCH_EVENT__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Output Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__OUTPUT_SET = TCATCH_EVENT__OUTPUT_SET;

	/**
	 * The feature id for the '<em><b>Event Definition Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__EVENT_DEFINITION_GROUP = TCATCH_EVENT__EVENT_DEFINITION_GROUP;

	/**
	 * The feature id for the '<em><b>Event Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__EVENT_DEFINITION = TCATCH_EVENT__EVENT_DEFINITION;

	/**
	 * The feature id for the '<em><b>Event Definition Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__EVENT_DEFINITION_REF = TCATCH_EVENT__EVENT_DEFINITION_REF;

	/**
	 * The feature id for the '<em><b>Parallel Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__PARALLEL_MULTIPLE = TCATCH_EVENT__PARALLEL_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Attached To Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__ATTACHED_TO_REF = TCATCH_EVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cancel Activity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT__CANCEL_ACTIVITY = TCATCH_EVENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TBoundary Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBOUNDARY_EVENT_FEATURE_COUNT = TCATCH_EVENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TTaskImpl <em>TTask</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTask()
	 * @generated
	 */
	int TTASK = 128;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__DOCUMENTATION = TACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__EXTENSION_ELEMENTS = TACTIVITY__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__ID = TACTIVITY__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__ANY_ATTRIBUTE = TACTIVITY__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__AUDITING = TACTIVITY__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__MONITORING = TACTIVITY__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__CATEGORY_VALUE_REF = TACTIVITY__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__NAME = TACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__INCOMING = TACTIVITY__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__OUTGOING = TACTIVITY__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__IO_SPECIFICATION = TACTIVITY__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__PROPERTY = TACTIVITY__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__DATA_INPUT_ASSOCIATION = TACTIVITY__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__DATA_OUTPUT_ASSOCIATION = TACTIVITY__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__RESOURCE_ROLE_GROUP = TACTIVITY__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__RESOURCE_ROLE = TACTIVITY__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__LOOP_CHARACTERISTICS_GROUP = TACTIVITY__LOOP_CHARACTERISTICS_GROUP;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__LOOP_CHARACTERISTICS = TACTIVITY__LOOP_CHARACTERISTICS;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__COMPLETION_QUANTITY = TACTIVITY__COMPLETION_QUANTITY;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__DEFAULT = TACTIVITY__DEFAULT;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__IS_FOR_COMPENSATION = TACTIVITY__IS_FOR_COMPENSATION;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK__START_QUANTITY = TACTIVITY__START_QUANTITY;

	/**
	 * The number of structural features of the '<em>TTask</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTASK_FEATURE_COUNT = TACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TBusinessRuleTaskImpl <em>TBusiness Rule Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TBusinessRuleTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTBusinessRuleTask()
	 * @generated
	 */
	int TBUSINESS_RULE_TASK = 10;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__DOCUMENTATION = TTASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__EXTENSION_ELEMENTS = TTASK__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__ID = TTASK__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__ANY_ATTRIBUTE = TTASK__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__AUDITING = TTASK__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__MONITORING = TTASK__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__CATEGORY_VALUE_REF = TTASK__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__NAME = TTASK__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__INCOMING = TTASK__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__OUTGOING = TTASK__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__IO_SPECIFICATION = TTASK__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__PROPERTY = TTASK__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__DATA_INPUT_ASSOCIATION = TTASK__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__DATA_OUTPUT_ASSOCIATION = TTASK__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__RESOURCE_ROLE_GROUP = TTASK__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__RESOURCE_ROLE = TTASK__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__LOOP_CHARACTERISTICS_GROUP = TTASK__LOOP_CHARACTERISTICS_GROUP;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__LOOP_CHARACTERISTICS = TTASK__LOOP_CHARACTERISTICS;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__COMPLETION_QUANTITY = TTASK__COMPLETION_QUANTITY;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__DEFAULT = TTASK__DEFAULT;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__IS_FOR_COMPENSATION = TTASK__IS_FOR_COMPENSATION;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__START_QUANTITY = TTASK__START_QUANTITY;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK__IMPLEMENTATION = TTASK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TBusiness Rule Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TBUSINESS_RULE_TASK_FEATURE_COUNT = TTASK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TRootElementImpl <em>TRoot Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TRootElementImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTRootElement()
	 * @generated
	 */
	int TROOT_ELEMENT = 115;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TROOT_ELEMENT__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TROOT_ELEMENT__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TROOT_ELEMENT__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TROOT_ELEMENT__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>TRoot Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TROOT_ELEMENT_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCallableElementImpl <em>TCallable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCallableElementImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCallableElement()
	 * @generated
	 */
	int TCALLABLE_ELEMENT = 11;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALLABLE_ELEMENT__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALLABLE_ELEMENT__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALLABLE_ELEMENT__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALLABLE_ELEMENT__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Supported Interface Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALLABLE_ELEMENT__SUPPORTED_INTERFACE_REF = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALLABLE_ELEMENT__IO_SPECIFICATION = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Io Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALLABLE_ELEMENT__IO_BINDING = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALLABLE_ELEMENT__NAME = TROOT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TCallable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALLABLE_ELEMENT_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCallActivityImpl <em>TCall Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCallActivityImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCallActivity()
	 * @generated
	 */
	int TCALL_ACTIVITY = 12;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__DOCUMENTATION = TACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__EXTENSION_ELEMENTS = TACTIVITY__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__ID = TACTIVITY__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__ANY_ATTRIBUTE = TACTIVITY__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__AUDITING = TACTIVITY__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__MONITORING = TACTIVITY__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__CATEGORY_VALUE_REF = TACTIVITY__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__NAME = TACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__INCOMING = TACTIVITY__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__OUTGOING = TACTIVITY__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__IO_SPECIFICATION = TACTIVITY__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__PROPERTY = TACTIVITY__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__DATA_INPUT_ASSOCIATION = TACTIVITY__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__DATA_OUTPUT_ASSOCIATION = TACTIVITY__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__RESOURCE_ROLE_GROUP = TACTIVITY__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__RESOURCE_ROLE = TACTIVITY__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__LOOP_CHARACTERISTICS_GROUP = TACTIVITY__LOOP_CHARACTERISTICS_GROUP;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__LOOP_CHARACTERISTICS = TACTIVITY__LOOP_CHARACTERISTICS;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__COMPLETION_QUANTITY = TACTIVITY__COMPLETION_QUANTITY;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__DEFAULT = TACTIVITY__DEFAULT;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__IS_FOR_COMPENSATION = TACTIVITY__IS_FOR_COMPENSATION;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__START_QUANTITY = TACTIVITY__START_QUANTITY;

	/**
	 * The feature id for the '<em><b>Called Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY__CALLED_ELEMENT = TACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TCall Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_ACTIVITY_FEATURE_COUNT = TACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TChoreographyActivityImpl <em>TChoreography Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TChoreographyActivityImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTChoreographyActivity()
	 * @generated
	 */
	int TCHOREOGRAPHY_ACTIVITY = 20;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__DOCUMENTATION = TFLOW_NODE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__EXTENSION_ELEMENTS = TFLOW_NODE__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__ID = TFLOW_NODE__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__ANY_ATTRIBUTE = TFLOW_NODE__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__AUDITING = TFLOW_NODE__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__MONITORING = TFLOW_NODE__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__CATEGORY_VALUE_REF = TFLOW_NODE__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__NAME = TFLOW_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__INCOMING = TFLOW_NODE__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__OUTGOING = TFLOW_NODE__OUTGOING;

	/**
	 * The feature id for the '<em><b>Participant Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__PARTICIPANT_REF = TFLOW_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__CORRELATION_KEY = TFLOW_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Initiating Participant Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__INITIATING_PARTICIPANT_REF = TFLOW_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Loop Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY__LOOP_TYPE = TFLOW_NODE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TChoreography Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_ACTIVITY_FEATURE_COUNT = TFLOW_NODE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCallChoreographyImpl <em>TCall Choreography</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCallChoreographyImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCallChoreography()
	 * @generated
	 */
	int TCALL_CHOREOGRAPHY = 13;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__DOCUMENTATION = TCHOREOGRAPHY_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__EXTENSION_ELEMENTS = TCHOREOGRAPHY_ACTIVITY__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__ID = TCHOREOGRAPHY_ACTIVITY__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__ANY_ATTRIBUTE = TCHOREOGRAPHY_ACTIVITY__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__AUDITING = TCHOREOGRAPHY_ACTIVITY__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__MONITORING = TCHOREOGRAPHY_ACTIVITY__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__CATEGORY_VALUE_REF = TCHOREOGRAPHY_ACTIVITY__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__NAME = TCHOREOGRAPHY_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__INCOMING = TCHOREOGRAPHY_ACTIVITY__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__OUTGOING = TCHOREOGRAPHY_ACTIVITY__OUTGOING;

	/**
	 * The feature id for the '<em><b>Participant Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__PARTICIPANT_REF = TCHOREOGRAPHY_ACTIVITY__PARTICIPANT_REF;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__CORRELATION_KEY = TCHOREOGRAPHY_ACTIVITY__CORRELATION_KEY;

	/**
	 * The feature id for the '<em><b>Initiating Participant Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__INITIATING_PARTICIPANT_REF = TCHOREOGRAPHY_ACTIVITY__INITIATING_PARTICIPANT_REF;

	/**
	 * The feature id for the '<em><b>Loop Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__LOOP_TYPE = TCHOREOGRAPHY_ACTIVITY__LOOP_TYPE;

	/**
	 * The feature id for the '<em><b>Participant Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__PARTICIPANT_ASSOCIATION = TCHOREOGRAPHY_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Called Choreography Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY__CALLED_CHOREOGRAPHY_REF = TCHOREOGRAPHY_ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TCall Choreography</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CHOREOGRAPHY_FEATURE_COUNT = TCHOREOGRAPHY_ACTIVITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TConversationNodeImpl <em>TConversation Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TConversationNodeImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTConversationNode()
	 * @generated
	 */
	int TCONVERSATION_NODE = 30;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_NODE__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_NODE__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_NODE__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_NODE__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Participant Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_NODE__PARTICIPANT_REF = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Message Flow Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_NODE__MESSAGE_FLOW_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_NODE__CORRELATION_KEY = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_NODE__NAME = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TConversation Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_NODE_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCallConversationImpl <em>TCall Conversation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCallConversationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCallConversation()
	 * @generated
	 */
	int TCALL_CONVERSATION = 14;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CONVERSATION__DOCUMENTATION = TCONVERSATION_NODE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CONVERSATION__EXTENSION_ELEMENTS = TCONVERSATION_NODE__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CONVERSATION__ID = TCONVERSATION_NODE__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CONVERSATION__ANY_ATTRIBUTE = TCONVERSATION_NODE__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Participant Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CONVERSATION__PARTICIPANT_REF = TCONVERSATION_NODE__PARTICIPANT_REF;

	/**
	 * The feature id for the '<em><b>Message Flow Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CONVERSATION__MESSAGE_FLOW_REF = TCONVERSATION_NODE__MESSAGE_FLOW_REF;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CONVERSATION__CORRELATION_KEY = TCONVERSATION_NODE__CORRELATION_KEY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CONVERSATION__NAME = TCONVERSATION_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Participant Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CONVERSATION__PARTICIPANT_ASSOCIATION = TCONVERSATION_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Called Collaboration Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CONVERSATION__CALLED_COLLABORATION_REF = TCONVERSATION_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TCall Conversation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCALL_CONVERSATION_FEATURE_COUNT = TCONVERSATION_NODE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TEventDefinitionImpl <em>TEvent Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TEventDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEventDefinition()
	 * @generated
	 */
	int TEVENT_DEFINITION = 56;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_DEFINITION__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_DEFINITION__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_DEFINITION__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_DEFINITION__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>TEvent Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_DEFINITION_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCancelEventDefinitionImpl <em>TCancel Event Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCancelEventDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCancelEventDefinition()
	 * @generated
	 */
	int TCANCEL_EVENT_DEFINITION = 15;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCANCEL_EVENT_DEFINITION__DOCUMENTATION = TEVENT_DEFINITION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCANCEL_EVENT_DEFINITION__EXTENSION_ELEMENTS = TEVENT_DEFINITION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCANCEL_EVENT_DEFINITION__ID = TEVENT_DEFINITION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCANCEL_EVENT_DEFINITION__ANY_ATTRIBUTE = TEVENT_DEFINITION__ANY_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>TCancel Event Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCANCEL_EVENT_DEFINITION_FEATURE_COUNT = TEVENT_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCategoryImpl <em>TCategory</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCategoryImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCategory()
	 * @generated
	 */
	int TCATEGORY = 17;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Category Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY__CATEGORY_VALUE = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY__NAME = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TCategory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCategoryValueImpl <em>TCategory Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCategoryValueImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCategoryValue()
	 * @generated
	 */
	int TCATEGORY_VALUE = 18;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY_VALUE__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY_VALUE__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY_VALUE__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY_VALUE__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY_VALUE__VALUE = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TCategory Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCATEGORY_VALUE_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl <em>TCollaboration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCollaborationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCollaboration()
	 * @generated
	 */
	int TCOLLABORATION = 22;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__PARTICIPANT = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Message Flow</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__MESSAGE_FLOW = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Artifact Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__ARTIFACT_GROUP = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__ARTIFACT = TROOT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Conversation Node Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__CONVERSATION_NODE_GROUP = TROOT_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Conversation Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__CONVERSATION_NODE = TROOT_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Conversation Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__CONVERSATION_ASSOCIATION = TROOT_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Participant Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__PARTICIPANT_ASSOCIATION = TROOT_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Message Flow Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__MESSAGE_FLOW_ASSOCIATION = TROOT_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__CORRELATION_KEY = TROOT_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Choreography Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__CHOREOGRAPHY_REF = TROOT_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Conversation Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__CONVERSATION_LINK = TROOT_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Is Closed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__IS_CLOSED = TROOT_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION__NAME = TROOT_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>TCollaboration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOLLABORATION_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TChoreographyImpl <em>TChoreography</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TChoreographyImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTChoreography()
	 * @generated
	 */
	int TCHOREOGRAPHY = 19;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__DOCUMENTATION = TCOLLABORATION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__EXTENSION_ELEMENTS = TCOLLABORATION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__ID = TCOLLABORATION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__ANY_ATTRIBUTE = TCOLLABORATION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__PARTICIPANT = TCOLLABORATION__PARTICIPANT;

	/**
	 * The feature id for the '<em><b>Message Flow</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__MESSAGE_FLOW = TCOLLABORATION__MESSAGE_FLOW;

	/**
	 * The feature id for the '<em><b>Artifact Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__ARTIFACT_GROUP = TCOLLABORATION__ARTIFACT_GROUP;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__ARTIFACT = TCOLLABORATION__ARTIFACT;

	/**
	 * The feature id for the '<em><b>Conversation Node Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__CONVERSATION_NODE_GROUP = TCOLLABORATION__CONVERSATION_NODE_GROUP;

	/**
	 * The feature id for the '<em><b>Conversation Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__CONVERSATION_NODE = TCOLLABORATION__CONVERSATION_NODE;

	/**
	 * The feature id for the '<em><b>Conversation Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__CONVERSATION_ASSOCIATION = TCOLLABORATION__CONVERSATION_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Participant Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__PARTICIPANT_ASSOCIATION = TCOLLABORATION__PARTICIPANT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Message Flow Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__MESSAGE_FLOW_ASSOCIATION = TCOLLABORATION__MESSAGE_FLOW_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__CORRELATION_KEY = TCOLLABORATION__CORRELATION_KEY;

	/**
	 * The feature id for the '<em><b>Choreography Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__CHOREOGRAPHY_REF = TCOLLABORATION__CHOREOGRAPHY_REF;

	/**
	 * The feature id for the '<em><b>Conversation Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__CONVERSATION_LINK = TCOLLABORATION__CONVERSATION_LINK;

	/**
	 * The feature id for the '<em><b>Is Closed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__IS_CLOSED = TCOLLABORATION__IS_CLOSED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__NAME = TCOLLABORATION__NAME;

	/**
	 * The feature id for the '<em><b>Flow Element Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__FLOW_ELEMENT_GROUP = TCOLLABORATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Flow Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY__FLOW_ELEMENT = TCOLLABORATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TChoreography</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_FEATURE_COUNT = TCOLLABORATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TChoreographyTaskImpl <em>TChoreography Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TChoreographyTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTChoreographyTask()
	 * @generated
	 */
	int TCHOREOGRAPHY_TASK = 21;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__DOCUMENTATION = TCHOREOGRAPHY_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__EXTENSION_ELEMENTS = TCHOREOGRAPHY_ACTIVITY__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__ID = TCHOREOGRAPHY_ACTIVITY__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__ANY_ATTRIBUTE = TCHOREOGRAPHY_ACTIVITY__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__AUDITING = TCHOREOGRAPHY_ACTIVITY__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__MONITORING = TCHOREOGRAPHY_ACTIVITY__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__CATEGORY_VALUE_REF = TCHOREOGRAPHY_ACTIVITY__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__NAME = TCHOREOGRAPHY_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__INCOMING = TCHOREOGRAPHY_ACTIVITY__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__OUTGOING = TCHOREOGRAPHY_ACTIVITY__OUTGOING;

	/**
	 * The feature id for the '<em><b>Participant Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__PARTICIPANT_REF = TCHOREOGRAPHY_ACTIVITY__PARTICIPANT_REF;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__CORRELATION_KEY = TCHOREOGRAPHY_ACTIVITY__CORRELATION_KEY;

	/**
	 * The feature id for the '<em><b>Initiating Participant Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__INITIATING_PARTICIPANT_REF = TCHOREOGRAPHY_ACTIVITY__INITIATING_PARTICIPANT_REF;

	/**
	 * The feature id for the '<em><b>Loop Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__LOOP_TYPE = TCHOREOGRAPHY_ACTIVITY__LOOP_TYPE;

	/**
	 * The feature id for the '<em><b>Message Flow Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK__MESSAGE_FLOW_REF = TCHOREOGRAPHY_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TChoreography Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCHOREOGRAPHY_TASK_FEATURE_COUNT = TCHOREOGRAPHY_ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCompensateEventDefinitionImpl <em>TCompensate Event Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCompensateEventDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCompensateEventDefinition()
	 * @generated
	 */
	int TCOMPENSATE_EVENT_DEFINITION = 23;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPENSATE_EVENT_DEFINITION__DOCUMENTATION = TEVENT_DEFINITION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPENSATE_EVENT_DEFINITION__EXTENSION_ELEMENTS = TEVENT_DEFINITION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPENSATE_EVENT_DEFINITION__ID = TEVENT_DEFINITION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPENSATE_EVENT_DEFINITION__ANY_ATTRIBUTE = TEVENT_DEFINITION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Activity Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPENSATE_EVENT_DEFINITION__ACTIVITY_REF = TEVENT_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Wait For Completion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION = TEVENT_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TCompensate Event Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPENSATE_EVENT_DEFINITION_FEATURE_COUNT = TEVENT_DEFINITION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TComplexBehaviorDefinitionImpl <em>TComplex Behavior Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TComplexBehaviorDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTComplexBehaviorDefinition()
	 * @generated
	 */
	int TCOMPLEX_BEHAVIOR_DEFINITION = 24;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_BEHAVIOR_DEFINITION__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_BEHAVIOR_DEFINITION__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_BEHAVIOR_DEFINITION__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_BEHAVIOR_DEFINITION__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_BEHAVIOR_DEFINITION__CONDITION = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_BEHAVIOR_DEFINITION__EVENT = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TComplex Behavior Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_BEHAVIOR_DEFINITION_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TGatewayImpl <em>TGateway</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TGatewayImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGateway()
	 * @generated
	 */
	int TGATEWAY = 64;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGATEWAY__DOCUMENTATION = TFLOW_NODE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGATEWAY__EXTENSION_ELEMENTS = TFLOW_NODE__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGATEWAY__ID = TFLOW_NODE__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGATEWAY__ANY_ATTRIBUTE = TFLOW_NODE__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGATEWAY__AUDITING = TFLOW_NODE__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGATEWAY__MONITORING = TFLOW_NODE__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGATEWAY__CATEGORY_VALUE_REF = TFLOW_NODE__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGATEWAY__NAME = TFLOW_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGATEWAY__INCOMING = TFLOW_NODE__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGATEWAY__OUTGOING = TFLOW_NODE__OUTGOING;

	/**
	 * The feature id for the '<em><b>Gateway Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGATEWAY__GATEWAY_DIRECTION = TFLOW_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TGateway</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGATEWAY_FEATURE_COUNT = TFLOW_NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TComplexGatewayImpl <em>TComplex Gateway</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TComplexGatewayImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTComplexGateway()
	 * @generated
	 */
	int TCOMPLEX_GATEWAY = 25;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__DOCUMENTATION = TGATEWAY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__EXTENSION_ELEMENTS = TGATEWAY__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__ID = TGATEWAY__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__ANY_ATTRIBUTE = TGATEWAY__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__AUDITING = TGATEWAY__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__MONITORING = TGATEWAY__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__CATEGORY_VALUE_REF = TGATEWAY__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__NAME = TGATEWAY__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__INCOMING = TGATEWAY__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__OUTGOING = TGATEWAY__OUTGOING;

	/**
	 * The feature id for the '<em><b>Gateway Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__GATEWAY_DIRECTION = TGATEWAY__GATEWAY_DIRECTION;

	/**
	 * The feature id for the '<em><b>Activation Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__ACTIVATION_CONDITION = TGATEWAY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY__DEFAULT = TGATEWAY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TComplex Gateway</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCOMPLEX_GATEWAY_FEATURE_COUNT = TGATEWAY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TConditionalEventDefinitionImpl <em>TConditional Event Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TConditionalEventDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTConditionalEventDefinition()
	 * @generated
	 */
	int TCONDITIONAL_EVENT_DEFINITION = 26;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONDITIONAL_EVENT_DEFINITION__DOCUMENTATION = TEVENT_DEFINITION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONDITIONAL_EVENT_DEFINITION__EXTENSION_ELEMENTS = TEVENT_DEFINITION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONDITIONAL_EVENT_DEFINITION__ID = TEVENT_DEFINITION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONDITIONAL_EVENT_DEFINITION__ANY_ATTRIBUTE = TEVENT_DEFINITION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONDITIONAL_EVENT_DEFINITION__CONDITION = TEVENT_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TConditional Event Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONDITIONAL_EVENT_DEFINITION_FEATURE_COUNT = TEVENT_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TConversationImpl <em>TConversation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TConversationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTConversation()
	 * @generated
	 */
	int TCONVERSATION = 27;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION__DOCUMENTATION = TCONVERSATION_NODE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION__EXTENSION_ELEMENTS = TCONVERSATION_NODE__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION__ID = TCONVERSATION_NODE__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION__ANY_ATTRIBUTE = TCONVERSATION_NODE__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Participant Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION__PARTICIPANT_REF = TCONVERSATION_NODE__PARTICIPANT_REF;

	/**
	 * The feature id for the '<em><b>Message Flow Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION__MESSAGE_FLOW_REF = TCONVERSATION_NODE__MESSAGE_FLOW_REF;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION__CORRELATION_KEY = TCONVERSATION_NODE__CORRELATION_KEY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION__NAME = TCONVERSATION_NODE__NAME;

	/**
	 * The number of structural features of the '<em>TConversation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_FEATURE_COUNT = TCONVERSATION_NODE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TConversationAssociationImpl <em>TConversation Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TConversationAssociationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTConversationAssociation()
	 * @generated
	 */
	int TCONVERSATION_ASSOCIATION = 28;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_ASSOCIATION__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_ASSOCIATION__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_ASSOCIATION__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_ASSOCIATION__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Inner Conversation Node Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_ASSOCIATION__INNER_CONVERSATION_NODE_REF = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outer Conversation Node Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_ASSOCIATION__OUTER_CONVERSATION_NODE_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TConversation Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_ASSOCIATION_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TConversationLinkImpl <em>TConversation Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TConversationLinkImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTConversationLink()
	 * @generated
	 */
	int TCONVERSATION_LINK = 29;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_LINK__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_LINK__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_LINK__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_LINK__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_LINK__NAME = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_LINK__SOURCE_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_LINK__TARGET_REF = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TConversation Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCONVERSATION_LINK_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCorrelationKeyImpl <em>TCorrelation Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCorrelationKeyImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCorrelationKey()
	 * @generated
	 */
	int TCORRELATION_KEY = 31;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_KEY__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_KEY__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_KEY__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_KEY__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Correlation Property Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_KEY__CORRELATION_PROPERTY_REF = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_KEY__NAME = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TCorrelation Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_KEY_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCorrelationPropertyImpl <em>TCorrelation Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCorrelationPropertyImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCorrelationProperty()
	 * @generated
	 */
	int TCORRELATION_PROPERTY = 32;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Correlation Property Retrieval Expression</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY__NAME = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY__TYPE = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TCorrelation Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCorrelationPropertyBindingImpl <em>TCorrelation Property Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCorrelationPropertyBindingImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCorrelationPropertyBinding()
	 * @generated
	 */
	int TCORRELATION_PROPERTY_BINDING = 33;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_BINDING__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_BINDING__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_BINDING__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_BINDING__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Data Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_BINDING__DATA_PATH = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Correlation Property Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TCorrelation Property Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_BINDING_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCorrelationPropertyRetrievalExpressionImpl <em>TCorrelation Property Retrieval Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCorrelationPropertyRetrievalExpressionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCorrelationPropertyRetrievalExpression()
	 * @generated
	 */
	int TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION = 34;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Message Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION__MESSAGE_PATH = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Message Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION__MESSAGE_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TCorrelation Property Retrieval Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TCorrelationSubscriptionImpl <em>TCorrelation Subscription</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TCorrelationSubscriptionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCorrelationSubscription()
	 * @generated
	 */
	int TCORRELATION_SUBSCRIPTION = 35;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_SUBSCRIPTION__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_SUBSCRIPTION__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_SUBSCRIPTION__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_SUBSCRIPTION__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Correlation Property Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_SUBSCRIPTION__CORRELATION_PROPERTY_BINDING = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Correlation Key Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_SUBSCRIPTION__CORRELATION_KEY_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TCorrelation Subscription</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TCORRELATION_SUBSCRIPTION_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TDataAssociationImpl <em>TData Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TDataAssociationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataAssociation()
	 * @generated
	 */
	int TDATA_ASSOCIATION = 36;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_ASSOCIATION__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_ASSOCIATION__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_ASSOCIATION__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_ASSOCIATION__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Source Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_ASSOCIATION__SOURCE_REF = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_ASSOCIATION__TARGET_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Transformation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_ASSOCIATION__TRANSFORMATION = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Assignment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_ASSOCIATION__ASSIGNMENT = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TData Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_ASSOCIATION_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TDataInputImpl <em>TData Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TDataInputImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataInput()
	 * @generated
	 */
	int TDATA_INPUT = 37;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Data State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT__DATA_STATE = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT__IS_COLLECTION = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Item Subject Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT__ITEM_SUBJECT_REF = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT__NAME = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TData Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TDataInputAssociationImpl <em>TData Input Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TDataInputAssociationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataInputAssociation()
	 * @generated
	 */
	int TDATA_INPUT_ASSOCIATION = 38;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT_ASSOCIATION__DOCUMENTATION = TDATA_ASSOCIATION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT_ASSOCIATION__EXTENSION_ELEMENTS = TDATA_ASSOCIATION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT_ASSOCIATION__ID = TDATA_ASSOCIATION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT_ASSOCIATION__ANY_ATTRIBUTE = TDATA_ASSOCIATION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Source Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT_ASSOCIATION__SOURCE_REF = TDATA_ASSOCIATION__SOURCE_REF;

	/**
	 * The feature id for the '<em><b>Target Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT_ASSOCIATION__TARGET_REF = TDATA_ASSOCIATION__TARGET_REF;

	/**
	 * The feature id for the '<em><b>Transformation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT_ASSOCIATION__TRANSFORMATION = TDATA_ASSOCIATION__TRANSFORMATION;

	/**
	 * The feature id for the '<em><b>Assignment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT_ASSOCIATION__ASSIGNMENT = TDATA_ASSOCIATION__ASSIGNMENT;

	/**
	 * The number of structural features of the '<em>TData Input Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_INPUT_ASSOCIATION_FEATURE_COUNT = TDATA_ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TDataObjectImpl <em>TData Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TDataObjectImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataObject()
	 * @generated
	 */
	int TDATA_OBJECT = 39;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT__DOCUMENTATION = TFLOW_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT__EXTENSION_ELEMENTS = TFLOW_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT__ID = TFLOW_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT__ANY_ATTRIBUTE = TFLOW_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT__AUDITING = TFLOW_ELEMENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT__MONITORING = TFLOW_ELEMENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT__CATEGORY_VALUE_REF = TFLOW_ELEMENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT__NAME = TFLOW_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Data State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT__DATA_STATE = TFLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT__IS_COLLECTION = TFLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Item Subject Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT__ITEM_SUBJECT_REF = TFLOW_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TData Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_FEATURE_COUNT = TFLOW_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TDataObjectReferenceImpl <em>TData Object Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TDataObjectReferenceImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataObjectReference()
	 * @generated
	 */
	int TDATA_OBJECT_REFERENCE = 40;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_REFERENCE__DOCUMENTATION = TFLOW_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_REFERENCE__EXTENSION_ELEMENTS = TFLOW_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_REFERENCE__ID = TFLOW_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_REFERENCE__ANY_ATTRIBUTE = TFLOW_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_REFERENCE__AUDITING = TFLOW_ELEMENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_REFERENCE__MONITORING = TFLOW_ELEMENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_REFERENCE__CATEGORY_VALUE_REF = TFLOW_ELEMENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_REFERENCE__NAME = TFLOW_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Data State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_REFERENCE__DATA_STATE = TFLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Object Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_REFERENCE__DATA_OBJECT_REF = TFLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Item Subject Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_REFERENCE__ITEM_SUBJECT_REF = TFLOW_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TData Object Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OBJECT_REFERENCE_FEATURE_COUNT = TFLOW_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TDataOutputImpl <em>TData Output</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TDataOutputImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataOutput()
	 * @generated
	 */
	int TDATA_OUTPUT = 41;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Data State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT__DATA_STATE = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT__IS_COLLECTION = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Item Subject Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT__ITEM_SUBJECT_REF = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT__NAME = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TData Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TDataOutputAssociationImpl <em>TData Output Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TDataOutputAssociationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataOutputAssociation()
	 * @generated
	 */
	int TDATA_OUTPUT_ASSOCIATION = 42;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT_ASSOCIATION__DOCUMENTATION = TDATA_ASSOCIATION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT_ASSOCIATION__EXTENSION_ELEMENTS = TDATA_ASSOCIATION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT_ASSOCIATION__ID = TDATA_ASSOCIATION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT_ASSOCIATION__ANY_ATTRIBUTE = TDATA_ASSOCIATION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Source Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT_ASSOCIATION__SOURCE_REF = TDATA_ASSOCIATION__SOURCE_REF;

	/**
	 * The feature id for the '<em><b>Target Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT_ASSOCIATION__TARGET_REF = TDATA_ASSOCIATION__TARGET_REF;

	/**
	 * The feature id for the '<em><b>Transformation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT_ASSOCIATION__TRANSFORMATION = TDATA_ASSOCIATION__TRANSFORMATION;

	/**
	 * The feature id for the '<em><b>Assignment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT_ASSOCIATION__ASSIGNMENT = TDATA_ASSOCIATION__ASSIGNMENT;

	/**
	 * The number of structural features of the '<em>TData Output Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_OUTPUT_ASSOCIATION_FEATURE_COUNT = TDATA_ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TDataStateImpl <em>TData State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TDataStateImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataState()
	 * @generated
	 */
	int TDATA_STATE = 43;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STATE__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STATE__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STATE__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STATE__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STATE__NAME = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TData State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STATE_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TDataStoreImpl <em>TData Store</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TDataStoreImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataStore()
	 * @generated
	 */
	int TDATA_STORE = 44;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Data State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE__DATA_STATE = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE__CAPACITY = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Unlimited</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE__IS_UNLIMITED = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Item Subject Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE__ITEM_SUBJECT_REF = TROOT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE__NAME = TROOT_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>TData Store</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TDataStoreReferenceImpl <em>TData Store Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TDataStoreReferenceImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataStoreReference()
	 * @generated
	 */
	int TDATA_STORE_REFERENCE = 45;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_REFERENCE__DOCUMENTATION = TFLOW_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_REFERENCE__EXTENSION_ELEMENTS = TFLOW_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_REFERENCE__ID = TFLOW_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_REFERENCE__ANY_ATTRIBUTE = TFLOW_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_REFERENCE__AUDITING = TFLOW_ELEMENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_REFERENCE__MONITORING = TFLOW_ELEMENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_REFERENCE__CATEGORY_VALUE_REF = TFLOW_ELEMENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_REFERENCE__NAME = TFLOW_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Data State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_REFERENCE__DATA_STATE = TFLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Store Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_REFERENCE__DATA_STORE_REF = TFLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Item Subject Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_REFERENCE__ITEM_SUBJECT_REF = TFLOW_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TData Store Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDATA_STORE_REFERENCE_FEATURE_COUNT = TFLOW_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl <em>TDefinitions</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TDefinitionsImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDefinitions()
	 * @generated
	 */
	int TDEFINITIONS = 46;

	/**
	 * The feature id for the '<em><b>Import</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__IMPORT = 0;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__EXTENSION = 1;

	/**
	 * The feature id for the '<em><b>Root Element Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__ROOT_ELEMENT_GROUP = 2;

	/**
	 * The feature id for the '<em><b>Root Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__ROOT_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>BPMN Diagram</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__BPMN_DIAGRAM = 4;

	/**
	 * The feature id for the '<em><b>Relationship</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__RELATIONSHIP = 5;

	/**
	 * The feature id for the '<em><b>Exporter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__EXPORTER = 6;

	/**
	 * The feature id for the '<em><b>Exporter Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__EXPORTER_VERSION = 7;

	/**
	 * The feature id for the '<em><b>Expression Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__EXPRESSION_LANGUAGE = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__ID = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__NAME = 10;

	/**
	 * The feature id for the '<em><b>Target Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__TARGET_NAMESPACE = 11;

	/**
	 * The feature id for the '<em><b>Type Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__TYPE_LANGUAGE = 12;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS__ANY_ATTRIBUTE = 13;

	/**
	 * The number of structural features of the '<em>TDefinitions</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDEFINITIONS_FEATURE_COUNT = 14;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TDocumentationImpl <em>TDocumentation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TDocumentationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDocumentation()
	 * @generated
	 */
	int TDOCUMENTATION = 47;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDOCUMENTATION__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDOCUMENTATION__ANY = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDOCUMENTATION__ID = 2;

	/**
	 * The feature id for the '<em><b>Text Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDOCUMENTATION__TEXT_FORMAT = 3;

	/**
	 * The number of structural features of the '<em>TDocumentation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TDOCUMENTATION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TThrowEventImpl <em>TThrow Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TThrowEventImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTThrowEvent()
	 * @generated
	 */
	int TTHROW_EVENT = 132;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__DOCUMENTATION = TEVENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__EXTENSION_ELEMENTS = TEVENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__ID = TEVENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__ANY_ATTRIBUTE = TEVENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__AUDITING = TEVENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__MONITORING = TEVENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__CATEGORY_VALUE_REF = TEVENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__NAME = TEVENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__INCOMING = TEVENT__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__OUTGOING = TEVENT__OUTGOING;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__PROPERTY = TEVENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__DATA_INPUT = TEVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__DATA_INPUT_ASSOCIATION = TEVENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Input Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__INPUT_SET = TEVENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Event Definition Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__EVENT_DEFINITION_GROUP = TEVENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Event Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__EVENT_DEFINITION = TEVENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Event Definition Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT__EVENT_DEFINITION_REF = TEVENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>TThrow Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTHROW_EVENT_FEATURE_COUNT = TEVENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TEndEventImpl <em>TEnd Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TEndEventImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEndEvent()
	 * @generated
	 */
	int TEND_EVENT = 48;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__DOCUMENTATION = TTHROW_EVENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__EXTENSION_ELEMENTS = TTHROW_EVENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__ID = TTHROW_EVENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__ANY_ATTRIBUTE = TTHROW_EVENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__AUDITING = TTHROW_EVENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__MONITORING = TTHROW_EVENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__CATEGORY_VALUE_REF = TTHROW_EVENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__NAME = TTHROW_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__INCOMING = TTHROW_EVENT__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__OUTGOING = TTHROW_EVENT__OUTGOING;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__PROPERTY = TTHROW_EVENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__DATA_INPUT = TTHROW_EVENT__DATA_INPUT;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__DATA_INPUT_ASSOCIATION = TTHROW_EVENT__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Input Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__INPUT_SET = TTHROW_EVENT__INPUT_SET;

	/**
	 * The feature id for the '<em><b>Event Definition Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__EVENT_DEFINITION_GROUP = TTHROW_EVENT__EVENT_DEFINITION_GROUP;

	/**
	 * The feature id for the '<em><b>Event Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__EVENT_DEFINITION = TTHROW_EVENT__EVENT_DEFINITION;

	/**
	 * The feature id for the '<em><b>Event Definition Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT__EVENT_DEFINITION_REF = TTHROW_EVENT__EVENT_DEFINITION_REF;

	/**
	 * The number of structural features of the '<em>TEnd Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_EVENT_FEATURE_COUNT = TTHROW_EVENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TEndPointImpl <em>TEnd Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TEndPointImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEndPoint()
	 * @generated
	 */
	int TEND_POINT = 49;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_POINT__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_POINT__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_POINT__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_POINT__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>TEnd Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEND_POINT_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TErrorImpl <em>TError</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TErrorImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTError()
	 * @generated
	 */
	int TERROR = 50;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Error Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR__ERROR_CODE = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR__NAME = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Structure Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR__STRUCTURE_REF = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TError</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TErrorEventDefinitionImpl <em>TError Event Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TErrorEventDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTErrorEventDefinition()
	 * @generated
	 */
	int TERROR_EVENT_DEFINITION = 51;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR_EVENT_DEFINITION__DOCUMENTATION = TEVENT_DEFINITION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR_EVENT_DEFINITION__EXTENSION_ELEMENTS = TEVENT_DEFINITION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR_EVENT_DEFINITION__ID = TEVENT_DEFINITION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR_EVENT_DEFINITION__ANY_ATTRIBUTE = TEVENT_DEFINITION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Error Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR_EVENT_DEFINITION__ERROR_REF = TEVENT_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TError Event Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERROR_EVENT_DEFINITION_FEATURE_COUNT = TEVENT_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TEscalationImpl <em>TEscalation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TEscalationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEscalation()
	 * @generated
	 */
	int TESCALATION = 52;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Escalation Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION__ESCALATION_CODE = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION__NAME = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Structure Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION__STRUCTURE_REF = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TEscalation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TEscalationEventDefinitionImpl <em>TEscalation Event Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TEscalationEventDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEscalationEventDefinition()
	 * @generated
	 */
	int TESCALATION_EVENT_DEFINITION = 53;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION_EVENT_DEFINITION__DOCUMENTATION = TEVENT_DEFINITION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION_EVENT_DEFINITION__EXTENSION_ELEMENTS = TEVENT_DEFINITION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION_EVENT_DEFINITION__ID = TEVENT_DEFINITION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION_EVENT_DEFINITION__ANY_ATTRIBUTE = TEVENT_DEFINITION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Escalation Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION_EVENT_DEFINITION__ESCALATION_REF = TEVENT_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TEscalation Event Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TESCALATION_EVENT_DEFINITION_FEATURE_COUNT = TEVENT_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TEventBasedGatewayImpl <em>TEvent Based Gateway</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TEventBasedGatewayImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEventBasedGateway()
	 * @generated
	 */
	int TEVENT_BASED_GATEWAY = 55;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__DOCUMENTATION = TGATEWAY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__EXTENSION_ELEMENTS = TGATEWAY__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__ID = TGATEWAY__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__ANY_ATTRIBUTE = TGATEWAY__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__AUDITING = TGATEWAY__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__MONITORING = TGATEWAY__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__CATEGORY_VALUE_REF = TGATEWAY__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__NAME = TGATEWAY__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__INCOMING = TGATEWAY__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__OUTGOING = TGATEWAY__OUTGOING;

	/**
	 * The feature id for the '<em><b>Gateway Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__GATEWAY_DIRECTION = TGATEWAY__GATEWAY_DIRECTION;

	/**
	 * The feature id for the '<em><b>Event Gateway Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__EVENT_GATEWAY_TYPE = TGATEWAY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instantiate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY__INSTANTIATE = TGATEWAY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TEvent Based Gateway</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEVENT_BASED_GATEWAY_FEATURE_COUNT = TGATEWAY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TExclusiveGatewayImpl <em>TExclusive Gateway</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TExclusiveGatewayImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTExclusiveGateway()
	 * @generated
	 */
	int TEXCLUSIVE_GATEWAY = 57;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY__DOCUMENTATION = TGATEWAY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY__EXTENSION_ELEMENTS = TGATEWAY__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY__ID = TGATEWAY__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY__ANY_ATTRIBUTE = TGATEWAY__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY__AUDITING = TGATEWAY__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY__MONITORING = TGATEWAY__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY__CATEGORY_VALUE_REF = TGATEWAY__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY__NAME = TGATEWAY__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY__INCOMING = TGATEWAY__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY__OUTGOING = TGATEWAY__OUTGOING;

	/**
	 * The feature id for the '<em><b>Gateway Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY__GATEWAY_DIRECTION = TGATEWAY__GATEWAY_DIRECTION;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY__DEFAULT = TGATEWAY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TExclusive Gateway</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXCLUSIVE_GATEWAY_FEATURE_COUNT = TGATEWAY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TExpressionImpl <em>TExpression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TExpressionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTExpression()
	 * @generated
	 */
	int TEXPRESSION = 58;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXPRESSION__MIXED = TBASE_ELEMENT_WITH_MIXED_CONTENT__MIXED;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXPRESSION__DOCUMENTATION = TBASE_ELEMENT_WITH_MIXED_CONTENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXPRESSION__EXTENSION_ELEMENTS = TBASE_ELEMENT_WITH_MIXED_CONTENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXPRESSION__ID = TBASE_ELEMENT_WITH_MIXED_CONTENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXPRESSION__ANY_ATTRIBUTE = TBASE_ELEMENT_WITH_MIXED_CONTENT__ANY_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>TExpression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXPRESSION_FEATURE_COUNT = TBASE_ELEMENT_WITH_MIXED_CONTENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TExtensionImpl <em>TExtension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TExtensionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTExtension()
	 * @generated
	 */
	int TEXTENSION = 59;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTENSION__DOCUMENTATION = 0;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTENSION__DEFINITION = 1;

	/**
	 * The feature id for the '<em><b>Must Understand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTENSION__MUST_UNDERSTAND = 2;

	/**
	 * The number of structural features of the '<em>TExtension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTENSION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TExtensionElementsImpl <em>TExtension Elements</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TExtensionElementsImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTExtensionElements()
	 * @generated
	 */
	int TEXTENSION_ELEMENTS = 60;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTENSION_ELEMENTS__ANY = 0;

	/**
	 * The number of structural features of the '<em>TExtension Elements</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXTENSION_ELEMENTS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TFormalExpressionImpl <em>TFormal Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TFormalExpressionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTFormalExpression()
	 * @generated
	 */
	int TFORMAL_EXPRESSION = 63;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFORMAL_EXPRESSION__MIXED = TEXPRESSION__MIXED;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFORMAL_EXPRESSION__DOCUMENTATION = TEXPRESSION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFORMAL_EXPRESSION__EXTENSION_ELEMENTS = TEXPRESSION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFORMAL_EXPRESSION__ID = TEXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFORMAL_EXPRESSION__ANY_ATTRIBUTE = TEXPRESSION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Evaluates To Type Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF = TEXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFORMAL_EXPRESSION__LANGUAGE = TEXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TFormal Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TFORMAL_EXPRESSION_FEATURE_COUNT = TEXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TGlobalTaskImpl <em>TGlobal Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TGlobalTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalTask()
	 * @generated
	 */
	int TGLOBAL_TASK = 70;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_TASK__DOCUMENTATION = TCALLABLE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_TASK__EXTENSION_ELEMENTS = TCALLABLE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_TASK__ID = TCALLABLE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_TASK__ANY_ATTRIBUTE = TCALLABLE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Supported Interface Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_TASK__SUPPORTED_INTERFACE_REF = TCALLABLE_ELEMENT__SUPPORTED_INTERFACE_REF;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_TASK__IO_SPECIFICATION = TCALLABLE_ELEMENT__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Io Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_TASK__IO_BINDING = TCALLABLE_ELEMENT__IO_BINDING;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_TASK__NAME = TCALLABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_TASK__RESOURCE_ROLE_GROUP = TCALLABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_TASK__RESOURCE_ROLE = TCALLABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TGlobal Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_TASK_FEATURE_COUNT = TCALLABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TGlobalBusinessRuleTaskImpl <em>TGlobal Business Rule Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TGlobalBusinessRuleTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalBusinessRuleTask()
	 * @generated
	 */
	int TGLOBAL_BUSINESS_RULE_TASK = 65;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_BUSINESS_RULE_TASK__DOCUMENTATION = TGLOBAL_TASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_BUSINESS_RULE_TASK__EXTENSION_ELEMENTS = TGLOBAL_TASK__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_BUSINESS_RULE_TASK__ID = TGLOBAL_TASK__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_BUSINESS_RULE_TASK__ANY_ATTRIBUTE = TGLOBAL_TASK__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Supported Interface Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_BUSINESS_RULE_TASK__SUPPORTED_INTERFACE_REF = TGLOBAL_TASK__SUPPORTED_INTERFACE_REF;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_BUSINESS_RULE_TASK__IO_SPECIFICATION = TGLOBAL_TASK__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Io Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_BUSINESS_RULE_TASK__IO_BINDING = TGLOBAL_TASK__IO_BINDING;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_BUSINESS_RULE_TASK__NAME = TGLOBAL_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_BUSINESS_RULE_TASK__RESOURCE_ROLE_GROUP = TGLOBAL_TASK__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_BUSINESS_RULE_TASK__RESOURCE_ROLE = TGLOBAL_TASK__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_BUSINESS_RULE_TASK__IMPLEMENTATION = TGLOBAL_TASK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TGlobal Business Rule Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_BUSINESS_RULE_TASK_FEATURE_COUNT = TGLOBAL_TASK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TGlobalChoreographyTaskImpl <em>TGlobal Choreography Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TGlobalChoreographyTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalChoreographyTask()
	 * @generated
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK = 66;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__DOCUMENTATION = TCHOREOGRAPHY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__EXTENSION_ELEMENTS = TCHOREOGRAPHY__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__ID = TCHOREOGRAPHY__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__ANY_ATTRIBUTE = TCHOREOGRAPHY__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__PARTICIPANT = TCHOREOGRAPHY__PARTICIPANT;

	/**
	 * The feature id for the '<em><b>Message Flow</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__MESSAGE_FLOW = TCHOREOGRAPHY__MESSAGE_FLOW;

	/**
	 * The feature id for the '<em><b>Artifact Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__ARTIFACT_GROUP = TCHOREOGRAPHY__ARTIFACT_GROUP;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__ARTIFACT = TCHOREOGRAPHY__ARTIFACT;

	/**
	 * The feature id for the '<em><b>Conversation Node Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__CONVERSATION_NODE_GROUP = TCHOREOGRAPHY__CONVERSATION_NODE_GROUP;

	/**
	 * The feature id for the '<em><b>Conversation Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__CONVERSATION_NODE = TCHOREOGRAPHY__CONVERSATION_NODE;

	/**
	 * The feature id for the '<em><b>Conversation Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__CONVERSATION_ASSOCIATION = TCHOREOGRAPHY__CONVERSATION_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Participant Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__PARTICIPANT_ASSOCIATION = TCHOREOGRAPHY__PARTICIPANT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Message Flow Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__MESSAGE_FLOW_ASSOCIATION = TCHOREOGRAPHY__MESSAGE_FLOW_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__CORRELATION_KEY = TCHOREOGRAPHY__CORRELATION_KEY;

	/**
	 * The feature id for the '<em><b>Choreography Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__CHOREOGRAPHY_REF = TCHOREOGRAPHY__CHOREOGRAPHY_REF;

	/**
	 * The feature id for the '<em><b>Conversation Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__CONVERSATION_LINK = TCHOREOGRAPHY__CONVERSATION_LINK;

	/**
	 * The feature id for the '<em><b>Is Closed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__IS_CLOSED = TCHOREOGRAPHY__IS_CLOSED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__NAME = TCHOREOGRAPHY__NAME;

	/**
	 * The feature id for the '<em><b>Flow Element Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__FLOW_ELEMENT_GROUP = TCHOREOGRAPHY__FLOW_ELEMENT_GROUP;

	/**
	 * The feature id for the '<em><b>Flow Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__FLOW_ELEMENT = TCHOREOGRAPHY__FLOW_ELEMENT;

	/**
	 * The feature id for the '<em><b>Initiating Participant Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK__INITIATING_PARTICIPANT_REF = TCHOREOGRAPHY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TGlobal Choreography Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CHOREOGRAPHY_TASK_FEATURE_COUNT = TCHOREOGRAPHY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TGlobalConversationImpl <em>TGlobal Conversation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TGlobalConversationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalConversation()
	 * @generated
	 */
	int TGLOBAL_CONVERSATION = 67;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__DOCUMENTATION = TCOLLABORATION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__EXTENSION_ELEMENTS = TCOLLABORATION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__ID = TCOLLABORATION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__ANY_ATTRIBUTE = TCOLLABORATION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__PARTICIPANT = TCOLLABORATION__PARTICIPANT;

	/**
	 * The feature id for the '<em><b>Message Flow</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__MESSAGE_FLOW = TCOLLABORATION__MESSAGE_FLOW;

	/**
	 * The feature id for the '<em><b>Artifact Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__ARTIFACT_GROUP = TCOLLABORATION__ARTIFACT_GROUP;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__ARTIFACT = TCOLLABORATION__ARTIFACT;

	/**
	 * The feature id for the '<em><b>Conversation Node Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__CONVERSATION_NODE_GROUP = TCOLLABORATION__CONVERSATION_NODE_GROUP;

	/**
	 * The feature id for the '<em><b>Conversation Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__CONVERSATION_NODE = TCOLLABORATION__CONVERSATION_NODE;

	/**
	 * The feature id for the '<em><b>Conversation Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__CONVERSATION_ASSOCIATION = TCOLLABORATION__CONVERSATION_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Participant Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__PARTICIPANT_ASSOCIATION = TCOLLABORATION__PARTICIPANT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Message Flow Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__MESSAGE_FLOW_ASSOCIATION = TCOLLABORATION__MESSAGE_FLOW_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__CORRELATION_KEY = TCOLLABORATION__CORRELATION_KEY;

	/**
	 * The feature id for the '<em><b>Choreography Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__CHOREOGRAPHY_REF = TCOLLABORATION__CHOREOGRAPHY_REF;

	/**
	 * The feature id for the '<em><b>Conversation Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__CONVERSATION_LINK = TCOLLABORATION__CONVERSATION_LINK;

	/**
	 * The feature id for the '<em><b>Is Closed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__IS_CLOSED = TCOLLABORATION__IS_CLOSED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION__NAME = TCOLLABORATION__NAME;

	/**
	 * The number of structural features of the '<em>TGlobal Conversation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_CONVERSATION_FEATURE_COUNT = TCOLLABORATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TGlobalManualTaskImpl <em>TGlobal Manual Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TGlobalManualTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalManualTask()
	 * @generated
	 */
	int TGLOBAL_MANUAL_TASK = 68;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_MANUAL_TASK__DOCUMENTATION = TGLOBAL_TASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_MANUAL_TASK__EXTENSION_ELEMENTS = TGLOBAL_TASK__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_MANUAL_TASK__ID = TGLOBAL_TASK__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_MANUAL_TASK__ANY_ATTRIBUTE = TGLOBAL_TASK__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Supported Interface Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_MANUAL_TASK__SUPPORTED_INTERFACE_REF = TGLOBAL_TASK__SUPPORTED_INTERFACE_REF;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_MANUAL_TASK__IO_SPECIFICATION = TGLOBAL_TASK__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Io Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_MANUAL_TASK__IO_BINDING = TGLOBAL_TASK__IO_BINDING;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_MANUAL_TASK__NAME = TGLOBAL_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_MANUAL_TASK__RESOURCE_ROLE_GROUP = TGLOBAL_TASK__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_MANUAL_TASK__RESOURCE_ROLE = TGLOBAL_TASK__RESOURCE_ROLE;

	/**
	 * The number of structural features of the '<em>TGlobal Manual Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_MANUAL_TASK_FEATURE_COUNT = TGLOBAL_TASK_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TGlobalScriptTaskImpl <em>TGlobal Script Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TGlobalScriptTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalScriptTask()
	 * @generated
	 */
	int TGLOBAL_SCRIPT_TASK = 69;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK__DOCUMENTATION = TGLOBAL_TASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK__EXTENSION_ELEMENTS = TGLOBAL_TASK__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK__ID = TGLOBAL_TASK__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK__ANY_ATTRIBUTE = TGLOBAL_TASK__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Supported Interface Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK__SUPPORTED_INTERFACE_REF = TGLOBAL_TASK__SUPPORTED_INTERFACE_REF;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK__IO_SPECIFICATION = TGLOBAL_TASK__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Io Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK__IO_BINDING = TGLOBAL_TASK__IO_BINDING;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK__NAME = TGLOBAL_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK__RESOURCE_ROLE_GROUP = TGLOBAL_TASK__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK__RESOURCE_ROLE = TGLOBAL_TASK__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK__SCRIPT = TGLOBAL_TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Script Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK__SCRIPT_LANGUAGE = TGLOBAL_TASK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TGlobal Script Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_SCRIPT_TASK_FEATURE_COUNT = TGLOBAL_TASK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TGlobalUserTaskImpl <em>TGlobal User Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TGlobalUserTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalUserTask()
	 * @generated
	 */
	int TGLOBAL_USER_TASK = 71;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK__DOCUMENTATION = TGLOBAL_TASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK__EXTENSION_ELEMENTS = TGLOBAL_TASK__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK__ID = TGLOBAL_TASK__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK__ANY_ATTRIBUTE = TGLOBAL_TASK__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Supported Interface Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK__SUPPORTED_INTERFACE_REF = TGLOBAL_TASK__SUPPORTED_INTERFACE_REF;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK__IO_SPECIFICATION = TGLOBAL_TASK__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Io Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK__IO_BINDING = TGLOBAL_TASK__IO_BINDING;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK__NAME = TGLOBAL_TASK__NAME;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK__RESOURCE_ROLE_GROUP = TGLOBAL_TASK__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK__RESOURCE_ROLE = TGLOBAL_TASK__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Rendering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK__RENDERING = TGLOBAL_TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK__IMPLEMENTATION = TGLOBAL_TASK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TGlobal User Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGLOBAL_USER_TASK_FEATURE_COUNT = TGLOBAL_TASK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TGroupImpl <em>TGroup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TGroupImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGroup()
	 * @generated
	 */
	int TGROUP = 72;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGROUP__DOCUMENTATION = TARTIFACT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGROUP__EXTENSION_ELEMENTS = TARTIFACT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGROUP__ID = TARTIFACT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGROUP__ANY_ATTRIBUTE = TARTIFACT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGROUP__CATEGORY_VALUE_REF = TARTIFACT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TGroup</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TGROUP_FEATURE_COUNT = TARTIFACT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TResourceRoleImpl <em>TResource Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TResourceRoleImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTResourceRole()
	 * @generated
	 */
	int TRESOURCE_ROLE = 114;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ROLE__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ROLE__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ROLE__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ROLE__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Resource Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ROLE__RESOURCE_REF = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Resource Parameter Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ROLE__RESOURCE_PARAMETER_BINDING = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Resource Assignment Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ROLE__NAME = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TResource Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ROLE_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TPerformerImpl <em>TPerformer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TPerformerImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTPerformer()
	 * @generated
	 */
	int TPERFORMER = 103;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPERFORMER__DOCUMENTATION = TRESOURCE_ROLE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPERFORMER__EXTENSION_ELEMENTS = TRESOURCE_ROLE__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPERFORMER__ID = TRESOURCE_ROLE__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPERFORMER__ANY_ATTRIBUTE = TRESOURCE_ROLE__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Resource Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPERFORMER__RESOURCE_REF = TRESOURCE_ROLE__RESOURCE_REF;

	/**
	 * The feature id for the '<em><b>Resource Parameter Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPERFORMER__RESOURCE_PARAMETER_BINDING = TRESOURCE_ROLE__RESOURCE_PARAMETER_BINDING;

	/**
	 * The feature id for the '<em><b>Resource Assignment Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPERFORMER__RESOURCE_ASSIGNMENT_EXPRESSION = TRESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPERFORMER__NAME = TRESOURCE_ROLE__NAME;

	/**
	 * The number of structural features of the '<em>TPerformer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPERFORMER_FEATURE_COUNT = TRESOURCE_ROLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.THumanPerformerImpl <em>THuman Performer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.THumanPerformerImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTHumanPerformer()
	 * @generated
	 */
	int THUMAN_PERFORMER = 73;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THUMAN_PERFORMER__DOCUMENTATION = TPERFORMER__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THUMAN_PERFORMER__EXTENSION_ELEMENTS = TPERFORMER__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THUMAN_PERFORMER__ID = TPERFORMER__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THUMAN_PERFORMER__ANY_ATTRIBUTE = TPERFORMER__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Resource Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THUMAN_PERFORMER__RESOURCE_REF = TPERFORMER__RESOURCE_REF;

	/**
	 * The feature id for the '<em><b>Resource Parameter Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THUMAN_PERFORMER__RESOURCE_PARAMETER_BINDING = TPERFORMER__RESOURCE_PARAMETER_BINDING;

	/**
	 * The feature id for the '<em><b>Resource Assignment Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THUMAN_PERFORMER__RESOURCE_ASSIGNMENT_EXPRESSION = TPERFORMER__RESOURCE_ASSIGNMENT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THUMAN_PERFORMER__NAME = TPERFORMER__NAME;

	/**
	 * The number of structural features of the '<em>THuman Performer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THUMAN_PERFORMER_FEATURE_COUNT = TPERFORMER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TImplicitThrowEventImpl <em>TImplicit Throw Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TImplicitThrowEventImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTImplicitThrowEvent()
	 * @generated
	 */
	int TIMPLICIT_THROW_EVENT = 74;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__DOCUMENTATION = TTHROW_EVENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__EXTENSION_ELEMENTS = TTHROW_EVENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__ID = TTHROW_EVENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__ANY_ATTRIBUTE = TTHROW_EVENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__AUDITING = TTHROW_EVENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__MONITORING = TTHROW_EVENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__CATEGORY_VALUE_REF = TTHROW_EVENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__NAME = TTHROW_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__INCOMING = TTHROW_EVENT__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__OUTGOING = TTHROW_EVENT__OUTGOING;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__PROPERTY = TTHROW_EVENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__DATA_INPUT = TTHROW_EVENT__DATA_INPUT;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__DATA_INPUT_ASSOCIATION = TTHROW_EVENT__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Input Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__INPUT_SET = TTHROW_EVENT__INPUT_SET;

	/**
	 * The feature id for the '<em><b>Event Definition Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__EVENT_DEFINITION_GROUP = TTHROW_EVENT__EVENT_DEFINITION_GROUP;

	/**
	 * The feature id for the '<em><b>Event Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__EVENT_DEFINITION = TTHROW_EVENT__EVENT_DEFINITION;

	/**
	 * The feature id for the '<em><b>Event Definition Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT__EVENT_DEFINITION_REF = TTHROW_EVENT__EVENT_DEFINITION_REF;

	/**
	 * The number of structural features of the '<em>TImplicit Throw Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPLICIT_THROW_EVENT_FEATURE_COUNT = TTHROW_EVENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TImportImpl <em>TImport</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TImportImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTImport()
	 * @generated
	 */
	int TIMPORT = 75;

	/**
	 * The feature id for the '<em><b>Import Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPORT__IMPORT_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPORT__LOCATION = 1;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPORT__NAMESPACE = 2;

	/**
	 * The number of structural features of the '<em>TImport</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMPORT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TInclusiveGatewayImpl <em>TInclusive Gateway</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TInclusiveGatewayImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTInclusiveGateway()
	 * @generated
	 */
	int TINCLUSIVE_GATEWAY = 76;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY__DOCUMENTATION = TGATEWAY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY__EXTENSION_ELEMENTS = TGATEWAY__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY__ID = TGATEWAY__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY__ANY_ATTRIBUTE = TGATEWAY__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY__AUDITING = TGATEWAY__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY__MONITORING = TGATEWAY__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY__CATEGORY_VALUE_REF = TGATEWAY__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY__NAME = TGATEWAY__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY__INCOMING = TGATEWAY__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY__OUTGOING = TGATEWAY__OUTGOING;

	/**
	 * The feature id for the '<em><b>Gateway Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY__GATEWAY_DIRECTION = TGATEWAY__GATEWAY_DIRECTION;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY__DEFAULT = TGATEWAY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TInclusive Gateway</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINCLUSIVE_GATEWAY_FEATURE_COUNT = TGATEWAY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TInputOutputBindingImpl <em>TInput Output Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TInputOutputBindingImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTInputOutputBinding()
	 * @generated
	 */
	int TINPUT_OUTPUT_BINDING = 77;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_BINDING__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_BINDING__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_BINDING__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_BINDING__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Input Data Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_BINDING__INPUT_DATA_REF = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operation Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_BINDING__OPERATION_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Output Data Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_BINDING__OUTPUT_DATA_REF = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TInput Output Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_BINDING_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TInputOutputSpecificationImpl <em>TInput Output Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TInputOutputSpecificationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTInputOutputSpecification()
	 * @generated
	 */
	int TINPUT_OUTPUT_SPECIFICATION = 78;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_SPECIFICATION__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_SPECIFICATION__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_SPECIFICATION__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_SPECIFICATION__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Data Input</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_SPECIFICATION__DATA_INPUT = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Output</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_SPECIFICATION__DATA_OUTPUT = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Input Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_SPECIFICATION__INPUT_SET = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Output Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_SPECIFICATION__OUTPUT_SET = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TInput Output Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_OUTPUT_SPECIFICATION_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TInputSetImpl <em>TInput Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TInputSetImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTInputSet()
	 * @generated
	 */
	int TINPUT_SET = 79;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_SET__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_SET__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_SET__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_SET__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Data Input Refs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_SET__DATA_INPUT_REFS = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Optional Input Refs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_SET__OPTIONAL_INPUT_REFS = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>While Executing Input Refs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_SET__WHILE_EXECUTING_INPUT_REFS = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Output Set Refs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_SET__OUTPUT_SET_REFS = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_SET__NAME = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>TInput Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINPUT_SET_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TInterfaceImpl <em>TInterface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TInterfaceImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTInterface()
	 * @generated
	 */
	int TINTERFACE = 80;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERFACE__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERFACE__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERFACE__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERFACE__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERFACE__OPERATION = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Implementation Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERFACE__IMPLEMENTATION_REF = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERFACE__NAME = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TInterface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERFACE_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TIntermediateCatchEventImpl <em>TIntermediate Catch Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TIntermediateCatchEventImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTIntermediateCatchEvent()
	 * @generated
	 */
	int TINTERMEDIATE_CATCH_EVENT = 81;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__DOCUMENTATION = TCATCH_EVENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__EXTENSION_ELEMENTS = TCATCH_EVENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__ID = TCATCH_EVENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__ANY_ATTRIBUTE = TCATCH_EVENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__AUDITING = TCATCH_EVENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__MONITORING = TCATCH_EVENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__CATEGORY_VALUE_REF = TCATCH_EVENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__NAME = TCATCH_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__INCOMING = TCATCH_EVENT__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__OUTGOING = TCATCH_EVENT__OUTGOING;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__PROPERTY = TCATCH_EVENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Output</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__DATA_OUTPUT = TCATCH_EVENT__DATA_OUTPUT;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__DATA_OUTPUT_ASSOCIATION = TCATCH_EVENT__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Output Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__OUTPUT_SET = TCATCH_EVENT__OUTPUT_SET;

	/**
	 * The feature id for the '<em><b>Event Definition Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__EVENT_DEFINITION_GROUP = TCATCH_EVENT__EVENT_DEFINITION_GROUP;

	/**
	 * The feature id for the '<em><b>Event Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__EVENT_DEFINITION = TCATCH_EVENT__EVENT_DEFINITION;

	/**
	 * The feature id for the '<em><b>Event Definition Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__EVENT_DEFINITION_REF = TCATCH_EVENT__EVENT_DEFINITION_REF;

	/**
	 * The feature id for the '<em><b>Parallel Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT__PARALLEL_MULTIPLE = TCATCH_EVENT__PARALLEL_MULTIPLE;

	/**
	 * The number of structural features of the '<em>TIntermediate Catch Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_CATCH_EVENT_FEATURE_COUNT = TCATCH_EVENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TIntermediateThrowEventImpl <em>TIntermediate Throw Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TIntermediateThrowEventImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTIntermediateThrowEvent()
	 * @generated
	 */
	int TINTERMEDIATE_THROW_EVENT = 82;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__DOCUMENTATION = TTHROW_EVENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__EXTENSION_ELEMENTS = TTHROW_EVENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__ID = TTHROW_EVENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__ANY_ATTRIBUTE = TTHROW_EVENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__AUDITING = TTHROW_EVENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__MONITORING = TTHROW_EVENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__CATEGORY_VALUE_REF = TTHROW_EVENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__NAME = TTHROW_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__INCOMING = TTHROW_EVENT__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__OUTGOING = TTHROW_EVENT__OUTGOING;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__PROPERTY = TTHROW_EVENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__DATA_INPUT = TTHROW_EVENT__DATA_INPUT;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__DATA_INPUT_ASSOCIATION = TTHROW_EVENT__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Input Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__INPUT_SET = TTHROW_EVENT__INPUT_SET;

	/**
	 * The feature id for the '<em><b>Event Definition Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__EVENT_DEFINITION_GROUP = TTHROW_EVENT__EVENT_DEFINITION_GROUP;

	/**
	 * The feature id for the '<em><b>Event Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__EVENT_DEFINITION = TTHROW_EVENT__EVENT_DEFINITION;

	/**
	 * The feature id for the '<em><b>Event Definition Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT__EVENT_DEFINITION_REF = TTHROW_EVENT__EVENT_DEFINITION_REF;

	/**
	 * The number of structural features of the '<em>TIntermediate Throw Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TINTERMEDIATE_THROW_EVENT_FEATURE_COUNT = TTHROW_EVENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TItemDefinitionImpl <em>TItem Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TItemDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTItemDefinition()
	 * @generated
	 */
	int TITEM_DEFINITION = 83;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITEM_DEFINITION__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITEM_DEFINITION__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITEM_DEFINITION__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITEM_DEFINITION__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Is Collection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITEM_DEFINITION__IS_COLLECTION = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Item Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITEM_DEFINITION__ITEM_KIND = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Structure Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITEM_DEFINITION__STRUCTURE_REF = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TItem Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TITEM_DEFINITION_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TLaneImpl <em>TLane</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TLaneImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTLane()
	 * @generated
	 */
	int TLANE = 84;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Partition Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE__PARTITION_ELEMENT = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Flow Node Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE__FLOW_NODE_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Child Lane Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE__CHILD_LANE_SET = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE__NAME = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Partition Element Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE__PARTITION_ELEMENT_REF = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>TLane</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TLaneSetImpl <em>TLane Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TLaneSetImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTLaneSet()
	 * @generated
	 */
	int TLANE_SET = 85;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE_SET__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE_SET__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE_SET__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE_SET__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Lane</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE_SET__LANE = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE_SET__NAME = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TLane Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLANE_SET_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TLinkEventDefinitionImpl <em>TLink Event Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TLinkEventDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTLinkEventDefinition()
	 * @generated
	 */
	int TLINK_EVENT_DEFINITION = 86;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLINK_EVENT_DEFINITION__DOCUMENTATION = TEVENT_DEFINITION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLINK_EVENT_DEFINITION__EXTENSION_ELEMENTS = TEVENT_DEFINITION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLINK_EVENT_DEFINITION__ID = TEVENT_DEFINITION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLINK_EVENT_DEFINITION__ANY_ATTRIBUTE = TEVENT_DEFINITION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLINK_EVENT_DEFINITION__SOURCE = TEVENT_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLINK_EVENT_DEFINITION__TARGET = TEVENT_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLINK_EVENT_DEFINITION__NAME = TEVENT_DEFINITION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TLink Event Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLINK_EVENT_DEFINITION_FEATURE_COUNT = TEVENT_DEFINITION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TLoopCharacteristicsImpl <em>TLoop Characteristics</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TLoopCharacteristicsImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTLoopCharacteristics()
	 * @generated
	 */
	int TLOOP_CHARACTERISTICS = 87;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLOOP_CHARACTERISTICS__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLOOP_CHARACTERISTICS__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLOOP_CHARACTERISTICS__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLOOP_CHARACTERISTICS__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>TLoop Characteristics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLOOP_CHARACTERISTICS_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TManualTaskImpl <em>TManual Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TManualTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTManualTask()
	 * @generated
	 */
	int TMANUAL_TASK = 88;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__DOCUMENTATION = TTASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__EXTENSION_ELEMENTS = TTASK__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__ID = TTASK__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__ANY_ATTRIBUTE = TTASK__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__AUDITING = TTASK__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__MONITORING = TTASK__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__CATEGORY_VALUE_REF = TTASK__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__NAME = TTASK__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__INCOMING = TTASK__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__OUTGOING = TTASK__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__IO_SPECIFICATION = TTASK__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__PROPERTY = TTASK__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__DATA_INPUT_ASSOCIATION = TTASK__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__DATA_OUTPUT_ASSOCIATION = TTASK__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__RESOURCE_ROLE_GROUP = TTASK__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__RESOURCE_ROLE = TTASK__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__LOOP_CHARACTERISTICS_GROUP = TTASK__LOOP_CHARACTERISTICS_GROUP;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__LOOP_CHARACTERISTICS = TTASK__LOOP_CHARACTERISTICS;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__COMPLETION_QUANTITY = TTASK__COMPLETION_QUANTITY;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__DEFAULT = TTASK__DEFAULT;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__IS_FOR_COMPENSATION = TTASK__IS_FOR_COMPENSATION;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK__START_QUANTITY = TTASK__START_QUANTITY;

	/**
	 * The number of structural features of the '<em>TManual Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMANUAL_TASK_FEATURE_COUNT = TTASK_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TMessageImpl <em>TMessage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TMessageImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMessage()
	 * @generated
	 */
	int TMESSAGE = 89;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Item Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE__ITEM_REF = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE__NAME = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TMessage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TMessageEventDefinitionImpl <em>TMessage Event Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TMessageEventDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMessageEventDefinition()
	 * @generated
	 */
	int TMESSAGE_EVENT_DEFINITION = 90;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_EVENT_DEFINITION__DOCUMENTATION = TEVENT_DEFINITION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_EVENT_DEFINITION__EXTENSION_ELEMENTS = TEVENT_DEFINITION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_EVENT_DEFINITION__ID = TEVENT_DEFINITION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_EVENT_DEFINITION__ANY_ATTRIBUTE = TEVENT_DEFINITION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Operation Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_EVENT_DEFINITION__OPERATION_REF = TEVENT_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Message Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_EVENT_DEFINITION__MESSAGE_REF = TEVENT_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TMessage Event Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_EVENT_DEFINITION_FEATURE_COUNT = TEVENT_DEFINITION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TMessageFlowImpl <em>TMessage Flow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TMessageFlowImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMessageFlow()
	 * @generated
	 */
	int TMESSAGE_FLOW = 91;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Message Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW__MESSAGE_REF = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW__NAME = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW__SOURCE_REF = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Target Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW__TARGET_REF = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TMessage Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TMessageFlowAssociationImpl <em>TMessage Flow Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TMessageFlowAssociationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMessageFlowAssociation()
	 * @generated
	 */
	int TMESSAGE_FLOW_ASSOCIATION = 92;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW_ASSOCIATION__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW_ASSOCIATION__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW_ASSOCIATION__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW_ASSOCIATION__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Inner Message Flow Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outer Message Flow Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TMessage Flow Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMESSAGE_FLOW_ASSOCIATION_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TMonitoringImpl <em>TMonitoring</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TMonitoringImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMonitoring()
	 * @generated
	 */
	int TMONITORING = 93;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMONITORING__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMONITORING__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMONITORING__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMONITORING__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>TMonitoring</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMONITORING_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TMultiInstanceLoopCharacteristicsImpl <em>TMulti Instance Loop Characteristics</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TMultiInstanceLoopCharacteristicsImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS = 94;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__DOCUMENTATION = TLOOP_CHARACTERISTICS__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__EXTENSION_ELEMENTS = TLOOP_CHARACTERISTICS__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__ID = TLOOP_CHARACTERISTICS__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__ANY_ATTRIBUTE = TLOOP_CHARACTERISTICS__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Loop Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__LOOP_CARDINALITY = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Loop Data Input Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__LOOP_DATA_INPUT_REF = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Loop Data Output Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__LOOP_DATA_OUTPUT_REF = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Input Data Item</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__INPUT_DATA_ITEM = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Output Data Item</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__OUTPUT_DATA_ITEM = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Complex Behavior Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__COMPLEX_BEHAVIOR_DEFINITION = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Completion Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__COMPLETION_CONDITION = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Behavior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__BEHAVIOR = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Is Sequential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__IS_SEQUENTIAL = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>None Behavior Event Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__NONE_BEHAVIOR_EVENT_REF = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>One Behavior Event Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS__ONE_BEHAVIOR_EVENT_REF = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>TMulti Instance Loop Characteristics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMULTI_INSTANCE_LOOP_CHARACTERISTICS_FEATURE_COUNT = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TOperationImpl <em>TOperation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TOperationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTOperation()
	 * @generated
	 */
	int TOPERATION = 95;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPERATION__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPERATION__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPERATION__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPERATION__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>In Message Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPERATION__IN_MESSAGE_REF = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Out Message Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPERATION__OUT_MESSAGE_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Error Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPERATION__ERROR_REF = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Implementation Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPERATION__IMPLEMENTATION_REF = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPERATION__NAME = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>TOperation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPERATION_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TOutputSetImpl <em>TOutput Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TOutputSetImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTOutputSet()
	 * @generated
	 */
	int TOUTPUT_SET = 96;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOUTPUT_SET__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOUTPUT_SET__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOUTPUT_SET__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOUTPUT_SET__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Data Output Refs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOUTPUT_SET__DATA_OUTPUT_REFS = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Optional Output Refs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOUTPUT_SET__OPTIONAL_OUTPUT_REFS = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>While Executing Output Refs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOUTPUT_SET__WHILE_EXECUTING_OUTPUT_REFS = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Input Set Refs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOUTPUT_SET__INPUT_SET_REFS = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOUTPUT_SET__NAME = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>TOutput Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOUTPUT_SET_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TParallelGatewayImpl <em>TParallel Gateway</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TParallelGatewayImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTParallelGateway()
	 * @generated
	 */
	int TPARALLEL_GATEWAY = 97;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARALLEL_GATEWAY__DOCUMENTATION = TGATEWAY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARALLEL_GATEWAY__EXTENSION_ELEMENTS = TGATEWAY__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARALLEL_GATEWAY__ID = TGATEWAY__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARALLEL_GATEWAY__ANY_ATTRIBUTE = TGATEWAY__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARALLEL_GATEWAY__AUDITING = TGATEWAY__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARALLEL_GATEWAY__MONITORING = TGATEWAY__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARALLEL_GATEWAY__CATEGORY_VALUE_REF = TGATEWAY__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARALLEL_GATEWAY__NAME = TGATEWAY__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARALLEL_GATEWAY__INCOMING = TGATEWAY__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARALLEL_GATEWAY__OUTGOING = TGATEWAY__OUTGOING;

	/**
	 * The feature id for the '<em><b>Gateway Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARALLEL_GATEWAY__GATEWAY_DIRECTION = TGATEWAY__GATEWAY_DIRECTION;

	/**
	 * The number of structural features of the '<em>TParallel Gateway</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARALLEL_GATEWAY_FEATURE_COUNT = TGATEWAY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TParticipantImpl <em>TParticipant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TParticipantImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTParticipant()
	 * @generated
	 */
	int TPARTICIPANT = 98;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Interface Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT__INTERFACE_REF = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>End Point Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT__END_POINT_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Participant Multiplicity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT__PARTICIPANT_MULTIPLICITY = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT__NAME = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Process Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT__PROCESS_REF = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>TParticipant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TParticipantAssociationImpl <em>TParticipant Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TParticipantAssociationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTParticipantAssociation()
	 * @generated
	 */
	int TPARTICIPANT_ASSOCIATION = 99;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_ASSOCIATION__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_ASSOCIATION__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_ASSOCIATION__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_ASSOCIATION__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Inner Participant Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outer Participant Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TParticipant Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_ASSOCIATION_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TParticipantMultiplicityImpl <em>TParticipant Multiplicity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TParticipantMultiplicityImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTParticipantMultiplicity()
	 * @generated
	 */
	int TPARTICIPANT_MULTIPLICITY = 100;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_MULTIPLICITY__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_MULTIPLICITY__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_MULTIPLICITY__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_MULTIPLICITY__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Maximum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_MULTIPLICITY__MAXIMUM = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Minimum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_MULTIPLICITY__MINIMUM = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TParticipant Multiplicity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTICIPANT_MULTIPLICITY_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TPartnerEntityImpl <em>TPartner Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TPartnerEntityImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTPartnerEntity()
	 * @generated
	 */
	int TPARTNER_ENTITY = 101;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ENTITY__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ENTITY__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ENTITY__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ENTITY__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Participant Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ENTITY__PARTICIPANT_REF = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ENTITY__NAME = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TPartner Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ENTITY_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TPartnerRoleImpl <em>TPartner Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TPartnerRoleImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTPartnerRole()
	 * @generated
	 */
	int TPARTNER_ROLE = 102;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ROLE__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ROLE__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ROLE__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ROLE__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Participant Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ROLE__PARTICIPANT_REF = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ROLE__NAME = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TPartner Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPARTNER_ROLE_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TPotentialOwnerImpl <em>TPotential Owner</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TPotentialOwnerImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTPotentialOwner()
	 * @generated
	 */
	int TPOTENTIAL_OWNER = 104;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPOTENTIAL_OWNER__DOCUMENTATION = THUMAN_PERFORMER__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPOTENTIAL_OWNER__EXTENSION_ELEMENTS = THUMAN_PERFORMER__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPOTENTIAL_OWNER__ID = THUMAN_PERFORMER__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPOTENTIAL_OWNER__ANY_ATTRIBUTE = THUMAN_PERFORMER__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Resource Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPOTENTIAL_OWNER__RESOURCE_REF = THUMAN_PERFORMER__RESOURCE_REF;

	/**
	 * The feature id for the '<em><b>Resource Parameter Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPOTENTIAL_OWNER__RESOURCE_PARAMETER_BINDING = THUMAN_PERFORMER__RESOURCE_PARAMETER_BINDING;

	/**
	 * The feature id for the '<em><b>Resource Assignment Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPOTENTIAL_OWNER__RESOURCE_ASSIGNMENT_EXPRESSION = THUMAN_PERFORMER__RESOURCE_ASSIGNMENT_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPOTENTIAL_OWNER__NAME = THUMAN_PERFORMER__NAME;

	/**
	 * The number of structural features of the '<em>TPotential Owner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPOTENTIAL_OWNER_FEATURE_COUNT = THUMAN_PERFORMER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TProcessImpl <em>TProcess</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TProcessImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTProcess()
	 * @generated
	 */
	int TPROCESS = 105;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__DOCUMENTATION = TCALLABLE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__EXTENSION_ELEMENTS = TCALLABLE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__ID = TCALLABLE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__ANY_ATTRIBUTE = TCALLABLE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Supported Interface Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__SUPPORTED_INTERFACE_REF = TCALLABLE_ELEMENT__SUPPORTED_INTERFACE_REF;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__IO_SPECIFICATION = TCALLABLE_ELEMENT__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Io Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__IO_BINDING = TCALLABLE_ELEMENT__IO_BINDING;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__NAME = TCALLABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__AUDITING = TCALLABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__MONITORING = TCALLABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__PROPERTY = TCALLABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Lane Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__LANE_SET = TCALLABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Flow Element Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__FLOW_ELEMENT_GROUP = TCALLABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Flow Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__FLOW_ELEMENT = TCALLABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Artifact Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__ARTIFACT_GROUP = TCALLABLE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__ARTIFACT = TCALLABLE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__RESOURCE_ROLE_GROUP = TCALLABLE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__RESOURCE_ROLE = TCALLABLE_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Correlation Subscription</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__CORRELATION_SUBSCRIPTION = TCALLABLE_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Supports</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__SUPPORTS = TCALLABLE_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Definitional Collaboration Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__DEFINITIONAL_COLLABORATION_REF = TCALLABLE_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Is Closed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__IS_CLOSED = TCALLABLE_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Is Executable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__IS_EXECUTABLE = TCALLABLE_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Process Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS__PROCESS_TYPE = TCALLABLE_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>TProcess</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROCESS_FEATURE_COUNT = TCALLABLE_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TPropertyImpl <em>TProperty</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TPropertyImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTProperty()
	 * @generated
	 */
	int TPROPERTY = 106;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROPERTY__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROPERTY__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROPERTY__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROPERTY__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Data State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROPERTY__DATA_STATE = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Item Subject Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROPERTY__ITEM_SUBJECT_REF = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROPERTY__NAME = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TProperty</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TPROPERTY_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TReceiveTaskImpl <em>TReceive Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TReceiveTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTReceiveTask()
	 * @generated
	 */
	int TRECEIVE_TASK = 107;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__DOCUMENTATION = TTASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__EXTENSION_ELEMENTS = TTASK__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__ID = TTASK__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__ANY_ATTRIBUTE = TTASK__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__AUDITING = TTASK__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__MONITORING = TTASK__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__CATEGORY_VALUE_REF = TTASK__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__NAME = TTASK__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__INCOMING = TTASK__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__OUTGOING = TTASK__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__IO_SPECIFICATION = TTASK__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__PROPERTY = TTASK__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__DATA_INPUT_ASSOCIATION = TTASK__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__DATA_OUTPUT_ASSOCIATION = TTASK__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__RESOURCE_ROLE_GROUP = TTASK__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__RESOURCE_ROLE = TTASK__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__LOOP_CHARACTERISTICS_GROUP = TTASK__LOOP_CHARACTERISTICS_GROUP;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__LOOP_CHARACTERISTICS = TTASK__LOOP_CHARACTERISTICS;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__COMPLETION_QUANTITY = TTASK__COMPLETION_QUANTITY;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__DEFAULT = TTASK__DEFAULT;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__IS_FOR_COMPENSATION = TTASK__IS_FOR_COMPENSATION;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__START_QUANTITY = TTASK__START_QUANTITY;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__IMPLEMENTATION = TTASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instantiate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__INSTANTIATE = TTASK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Message Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__MESSAGE_REF = TTASK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Operation Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK__OPERATION_REF = TTASK_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TReceive Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRECEIVE_TASK_FEATURE_COUNT = TTASK_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TRelationshipImpl <em>TRelationship</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TRelationshipImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTRelationship()
	 * @generated
	 */
	int TRELATIONSHIP = 108;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRELATIONSHIP__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRELATIONSHIP__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRELATIONSHIP__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRELATIONSHIP__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRELATIONSHIP__SOURCE = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRELATIONSHIP__TARGET = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRELATIONSHIP__DIRECTION = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRELATIONSHIP__TYPE = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TRelationship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRELATIONSHIP_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TRenderingImpl <em>TRendering</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TRenderingImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTRendering()
	 * @generated
	 */
	int TRENDERING = 109;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRENDERING__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRENDERING__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRENDERING__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRENDERING__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>TRendering</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRENDERING_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TResourceImpl <em>TResource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TResourceImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTResource()
	 * @generated
	 */
	int TRESOURCE = 110;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Resource Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE__RESOURCE_PARAMETER = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE__NAME = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TResource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TResourceAssignmentExpressionImpl <em>TResource Assignment Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TResourceAssignmentExpressionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTResourceAssignmentExpression()
	 * @generated
	 */
	int TRESOURCE_ASSIGNMENT_EXPRESSION = 111;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ASSIGNMENT_EXPRESSION__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ASSIGNMENT_EXPRESSION__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ASSIGNMENT_EXPRESSION__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ASSIGNMENT_EXPRESSION__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Expression Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION_GROUP = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TResource Assignment Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_ASSIGNMENT_EXPRESSION_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TResourceParameterImpl <em>TResource Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TResourceParameterImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTResourceParameter()
	 * @generated
	 */
	int TRESOURCE_PARAMETER = 112;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Is Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER__IS_REQUIRED = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER__NAME = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER__TYPE = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TResource Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TResourceParameterBindingImpl <em>TResource Parameter Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TResourceParameterBindingImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTResourceParameterBinding()
	 * @generated
	 */
	int TRESOURCE_PARAMETER_BINDING = 113;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER_BINDING__DOCUMENTATION = TBASE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER_BINDING__EXTENSION_ELEMENTS = TBASE_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER_BINDING__ID = TBASE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER_BINDING__ANY_ATTRIBUTE = TBASE_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Expression Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER_BINDING__EXPRESSION_GROUP = TBASE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER_BINDING__EXPRESSION = TBASE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameter Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER_BINDING__PARAMETER_REF = TBASE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TResource Parameter Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRESOURCE_PARAMETER_BINDING_FEATURE_COUNT = TBASE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TScriptImpl <em>TScript</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TScriptImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTScript()
	 * @generated
	 */
	int TSCRIPT = 116;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT__ANY = 1;

	/**
	 * The number of structural features of the '<em>TScript</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TScriptTaskImpl <em>TScript Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TScriptTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTScriptTask()
	 * @generated
	 */
	int TSCRIPT_TASK = 117;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__DOCUMENTATION = TTASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__EXTENSION_ELEMENTS = TTASK__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__ID = TTASK__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__ANY_ATTRIBUTE = TTASK__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__AUDITING = TTASK__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__MONITORING = TTASK__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__CATEGORY_VALUE_REF = TTASK__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__NAME = TTASK__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__INCOMING = TTASK__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__OUTGOING = TTASK__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__IO_SPECIFICATION = TTASK__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__PROPERTY = TTASK__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__DATA_INPUT_ASSOCIATION = TTASK__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__DATA_OUTPUT_ASSOCIATION = TTASK__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__RESOURCE_ROLE_GROUP = TTASK__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__RESOURCE_ROLE = TTASK__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__LOOP_CHARACTERISTICS_GROUP = TTASK__LOOP_CHARACTERISTICS_GROUP;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__LOOP_CHARACTERISTICS = TTASK__LOOP_CHARACTERISTICS;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__COMPLETION_QUANTITY = TTASK__COMPLETION_QUANTITY;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__DEFAULT = TTASK__DEFAULT;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__IS_FOR_COMPENSATION = TTASK__IS_FOR_COMPENSATION;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__START_QUANTITY = TTASK__START_QUANTITY;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__SCRIPT = TTASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Script Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK__SCRIPT_FORMAT = TTASK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TScript Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSCRIPT_TASK_FEATURE_COUNT = TTASK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TSendTaskImpl <em>TSend Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TSendTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSendTask()
	 * @generated
	 */
	int TSEND_TASK = 118;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__DOCUMENTATION = TTASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__EXTENSION_ELEMENTS = TTASK__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__ID = TTASK__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__ANY_ATTRIBUTE = TTASK__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__AUDITING = TTASK__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__MONITORING = TTASK__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__CATEGORY_VALUE_REF = TTASK__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__NAME = TTASK__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__INCOMING = TTASK__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__OUTGOING = TTASK__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__IO_SPECIFICATION = TTASK__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__PROPERTY = TTASK__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__DATA_INPUT_ASSOCIATION = TTASK__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__DATA_OUTPUT_ASSOCIATION = TTASK__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__RESOURCE_ROLE_GROUP = TTASK__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__RESOURCE_ROLE = TTASK__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__LOOP_CHARACTERISTICS_GROUP = TTASK__LOOP_CHARACTERISTICS_GROUP;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__LOOP_CHARACTERISTICS = TTASK__LOOP_CHARACTERISTICS;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__COMPLETION_QUANTITY = TTASK__COMPLETION_QUANTITY;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__DEFAULT = TTASK__DEFAULT;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__IS_FOR_COMPENSATION = TTASK__IS_FOR_COMPENSATION;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__START_QUANTITY = TTASK__START_QUANTITY;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__IMPLEMENTATION = TTASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Message Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__MESSAGE_REF = TTASK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operation Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK__OPERATION_REF = TTASK_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TSend Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEND_TASK_FEATURE_COUNT = TTASK_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TSequenceFlowImpl <em>TSequence Flow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TSequenceFlowImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSequenceFlow()
	 * @generated
	 */
	int TSEQUENCE_FLOW = 119;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW__DOCUMENTATION = TFLOW_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW__EXTENSION_ELEMENTS = TFLOW_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW__ID = TFLOW_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW__ANY_ATTRIBUTE = TFLOW_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW__AUDITING = TFLOW_ELEMENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW__MONITORING = TFLOW_ELEMENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW__CATEGORY_VALUE_REF = TFLOW_ELEMENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW__NAME = TFLOW_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Condition Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW__CONDITION_EXPRESSION = TFLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Immediate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW__IS_IMMEDIATE = TFLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW__SOURCE_REF = TFLOW_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Target Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW__TARGET_REF = TFLOW_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TSequence Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSEQUENCE_FLOW_FEATURE_COUNT = TFLOW_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TServiceTaskImpl <em>TService Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TServiceTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTServiceTask()
	 * @generated
	 */
	int TSERVICE_TASK = 120;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__DOCUMENTATION = TTASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__EXTENSION_ELEMENTS = TTASK__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__ID = TTASK__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__ANY_ATTRIBUTE = TTASK__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__AUDITING = TTASK__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__MONITORING = TTASK__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__CATEGORY_VALUE_REF = TTASK__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__NAME = TTASK__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__INCOMING = TTASK__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__OUTGOING = TTASK__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__IO_SPECIFICATION = TTASK__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__PROPERTY = TTASK__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__DATA_INPUT_ASSOCIATION = TTASK__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__DATA_OUTPUT_ASSOCIATION = TTASK__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__RESOURCE_ROLE_GROUP = TTASK__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__RESOURCE_ROLE = TTASK__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__LOOP_CHARACTERISTICS_GROUP = TTASK__LOOP_CHARACTERISTICS_GROUP;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__LOOP_CHARACTERISTICS = TTASK__LOOP_CHARACTERISTICS;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__COMPLETION_QUANTITY = TTASK__COMPLETION_QUANTITY;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__DEFAULT = TTASK__DEFAULT;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__IS_FOR_COMPENSATION = TTASK__IS_FOR_COMPENSATION;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__START_QUANTITY = TTASK__START_QUANTITY;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__IMPLEMENTATION = TTASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operation Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK__OPERATION_REF = TTASK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TService Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSERVICE_TASK_FEATURE_COUNT = TTASK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TSignalImpl <em>TSignal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TSignalImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSignal()
	 * @generated
	 */
	int TSIGNAL = 121;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL__DOCUMENTATION = TROOT_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL__EXTENSION_ELEMENTS = TROOT_ELEMENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL__ID = TROOT_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL__ANY_ATTRIBUTE = TROOT_ELEMENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL__NAME = TROOT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Structure Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL__STRUCTURE_REF = TROOT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TSignal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL_FEATURE_COUNT = TROOT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TSignalEventDefinitionImpl <em>TSignal Event Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TSignalEventDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSignalEventDefinition()
	 * @generated
	 */
	int TSIGNAL_EVENT_DEFINITION = 122;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL_EVENT_DEFINITION__DOCUMENTATION = TEVENT_DEFINITION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL_EVENT_DEFINITION__EXTENSION_ELEMENTS = TEVENT_DEFINITION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL_EVENT_DEFINITION__ID = TEVENT_DEFINITION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL_EVENT_DEFINITION__ANY_ATTRIBUTE = TEVENT_DEFINITION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Signal Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL_EVENT_DEFINITION__SIGNAL_REF = TEVENT_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TSignal Event Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSIGNAL_EVENT_DEFINITION_FEATURE_COUNT = TEVENT_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TStandardLoopCharacteristicsImpl <em>TStandard Loop Characteristics</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TStandardLoopCharacteristicsImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTStandardLoopCharacteristics()
	 * @generated
	 */
	int TSTANDARD_LOOP_CHARACTERISTICS = 123;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTANDARD_LOOP_CHARACTERISTICS__DOCUMENTATION = TLOOP_CHARACTERISTICS__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTANDARD_LOOP_CHARACTERISTICS__EXTENSION_ELEMENTS = TLOOP_CHARACTERISTICS__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTANDARD_LOOP_CHARACTERISTICS__ID = TLOOP_CHARACTERISTICS__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTANDARD_LOOP_CHARACTERISTICS__ANY_ATTRIBUTE = TLOOP_CHARACTERISTICS__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Loop Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Loop Maximum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTANDARD_LOOP_CHARACTERISTICS__LOOP_MAXIMUM = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Test Before</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TStandard Loop Characteristics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTANDARD_LOOP_CHARACTERISTICS_FEATURE_COUNT = TLOOP_CHARACTERISTICS_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TStartEventImpl <em>TStart Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TStartEventImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTStartEvent()
	 * @generated
	 */
	int TSTART_EVENT = 124;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__DOCUMENTATION = TCATCH_EVENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__EXTENSION_ELEMENTS = TCATCH_EVENT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__ID = TCATCH_EVENT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__ANY_ATTRIBUTE = TCATCH_EVENT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__AUDITING = TCATCH_EVENT__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__MONITORING = TCATCH_EVENT__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__CATEGORY_VALUE_REF = TCATCH_EVENT__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__NAME = TCATCH_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__INCOMING = TCATCH_EVENT__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__OUTGOING = TCATCH_EVENT__OUTGOING;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__PROPERTY = TCATCH_EVENT__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Output</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__DATA_OUTPUT = TCATCH_EVENT__DATA_OUTPUT;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__DATA_OUTPUT_ASSOCIATION = TCATCH_EVENT__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Output Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__OUTPUT_SET = TCATCH_EVENT__OUTPUT_SET;

	/**
	 * The feature id for the '<em><b>Event Definition Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__EVENT_DEFINITION_GROUP = TCATCH_EVENT__EVENT_DEFINITION_GROUP;

	/**
	 * The feature id for the '<em><b>Event Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__EVENT_DEFINITION = TCATCH_EVENT__EVENT_DEFINITION;

	/**
	 * The feature id for the '<em><b>Event Definition Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__EVENT_DEFINITION_REF = TCATCH_EVENT__EVENT_DEFINITION_REF;

	/**
	 * The feature id for the '<em><b>Parallel Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__PARALLEL_MULTIPLE = TCATCH_EVENT__PARALLEL_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Is Interrupting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT__IS_INTERRUPTING = TCATCH_EVENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TStart Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSTART_EVENT_FEATURE_COUNT = TCATCH_EVENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TSubChoreographyImpl <em>TSub Choreography</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TSubChoreographyImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSubChoreography()
	 * @generated
	 */
	int TSUB_CHOREOGRAPHY = 125;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__DOCUMENTATION = TCHOREOGRAPHY_ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__EXTENSION_ELEMENTS = TCHOREOGRAPHY_ACTIVITY__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__ID = TCHOREOGRAPHY_ACTIVITY__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__ANY_ATTRIBUTE = TCHOREOGRAPHY_ACTIVITY__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__AUDITING = TCHOREOGRAPHY_ACTIVITY__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__MONITORING = TCHOREOGRAPHY_ACTIVITY__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__CATEGORY_VALUE_REF = TCHOREOGRAPHY_ACTIVITY__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__NAME = TCHOREOGRAPHY_ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__INCOMING = TCHOREOGRAPHY_ACTIVITY__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__OUTGOING = TCHOREOGRAPHY_ACTIVITY__OUTGOING;

	/**
	 * The feature id for the '<em><b>Participant Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__PARTICIPANT_REF = TCHOREOGRAPHY_ACTIVITY__PARTICIPANT_REF;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__CORRELATION_KEY = TCHOREOGRAPHY_ACTIVITY__CORRELATION_KEY;

	/**
	 * The feature id for the '<em><b>Initiating Participant Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__INITIATING_PARTICIPANT_REF = TCHOREOGRAPHY_ACTIVITY__INITIATING_PARTICIPANT_REF;

	/**
	 * The feature id for the '<em><b>Loop Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__LOOP_TYPE = TCHOREOGRAPHY_ACTIVITY__LOOP_TYPE;

	/**
	 * The feature id for the '<em><b>Flow Element Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__FLOW_ELEMENT_GROUP = TCHOREOGRAPHY_ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Flow Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__FLOW_ELEMENT = TCHOREOGRAPHY_ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Artifact Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__ARTIFACT_GROUP = TCHOREOGRAPHY_ACTIVITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY__ARTIFACT = TCHOREOGRAPHY_ACTIVITY_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>TSub Choreography</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CHOREOGRAPHY_FEATURE_COUNT = TCHOREOGRAPHY_ACTIVITY_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TSubConversationImpl <em>TSub Conversation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TSubConversationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSubConversation()
	 * @generated
	 */
	int TSUB_CONVERSATION = 126;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CONVERSATION__DOCUMENTATION = TCONVERSATION_NODE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CONVERSATION__EXTENSION_ELEMENTS = TCONVERSATION_NODE__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CONVERSATION__ID = TCONVERSATION_NODE__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CONVERSATION__ANY_ATTRIBUTE = TCONVERSATION_NODE__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Participant Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CONVERSATION__PARTICIPANT_REF = TCONVERSATION_NODE__PARTICIPANT_REF;

	/**
	 * The feature id for the '<em><b>Message Flow Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CONVERSATION__MESSAGE_FLOW_REF = TCONVERSATION_NODE__MESSAGE_FLOW_REF;

	/**
	 * The feature id for the '<em><b>Correlation Key</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CONVERSATION__CORRELATION_KEY = TCONVERSATION_NODE__CORRELATION_KEY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CONVERSATION__NAME = TCONVERSATION_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Conversation Node Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CONVERSATION__CONVERSATION_NODE_GROUP = TCONVERSATION_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Conversation Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CONVERSATION__CONVERSATION_NODE = TCONVERSATION_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TSub Conversation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TSUB_CONVERSATION_FEATURE_COUNT = TCONVERSATION_NODE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TTerminateEventDefinitionImpl <em>TTerminate Event Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TTerminateEventDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTerminateEventDefinition()
	 * @generated
	 */
	int TTERMINATE_EVENT_DEFINITION = 129;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTERMINATE_EVENT_DEFINITION__DOCUMENTATION = TEVENT_DEFINITION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTERMINATE_EVENT_DEFINITION__EXTENSION_ELEMENTS = TEVENT_DEFINITION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTERMINATE_EVENT_DEFINITION__ID = TEVENT_DEFINITION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTERMINATE_EVENT_DEFINITION__ANY_ATTRIBUTE = TEVENT_DEFINITION__ANY_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>TTerminate Event Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTERMINATE_EVENT_DEFINITION_FEATURE_COUNT = TEVENT_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TTextImpl <em>TText</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TTextImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTText()
	 * @generated
	 */
	int TTEXT = 130;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTEXT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Any</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTEXT__ANY = 1;

	/**
	 * The number of structural features of the '<em>TText</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTEXT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TTextAnnotationImpl <em>TText Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TTextAnnotationImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTextAnnotation()
	 * @generated
	 */
	int TTEXT_ANNOTATION = 131;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTEXT_ANNOTATION__DOCUMENTATION = TARTIFACT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTEXT_ANNOTATION__EXTENSION_ELEMENTS = TARTIFACT__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTEXT_ANNOTATION__ID = TARTIFACT__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTEXT_ANNOTATION__ANY_ATTRIBUTE = TARTIFACT__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Text</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTEXT_ANNOTATION__TEXT = TARTIFACT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Text Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTEXT_ANNOTATION__TEXT_FORMAT = TARTIFACT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TText Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTEXT_ANNOTATION_FEATURE_COUNT = TARTIFACT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TTimerEventDefinitionImpl <em>TTimer Event Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TTimerEventDefinitionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTimerEventDefinition()
	 * @generated
	 */
	int TTIMER_EVENT_DEFINITION = 133;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTIMER_EVENT_DEFINITION__DOCUMENTATION = TEVENT_DEFINITION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTIMER_EVENT_DEFINITION__EXTENSION_ELEMENTS = TEVENT_DEFINITION__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTIMER_EVENT_DEFINITION__ID = TEVENT_DEFINITION__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTIMER_EVENT_DEFINITION__ANY_ATTRIBUTE = TEVENT_DEFINITION__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Time Date</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTIMER_EVENT_DEFINITION__TIME_DATE = TEVENT_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Time Duration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTIMER_EVENT_DEFINITION__TIME_DURATION = TEVENT_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Time Cycle</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTIMER_EVENT_DEFINITION__TIME_CYCLE = TEVENT_DEFINITION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>TTimer Event Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTIMER_EVENT_DEFINITION_FEATURE_COUNT = TEVENT_DEFINITION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TTransactionImpl <em>TTransaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TTransactionImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTransaction()
	 * @generated
	 */
	int TTRANSACTION = 134;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__DOCUMENTATION = TSUB_PROCESS__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__EXTENSION_ELEMENTS = TSUB_PROCESS__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__ID = TSUB_PROCESS__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__ANY_ATTRIBUTE = TSUB_PROCESS__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__AUDITING = TSUB_PROCESS__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__MONITORING = TSUB_PROCESS__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__CATEGORY_VALUE_REF = TSUB_PROCESS__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__NAME = TSUB_PROCESS__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__INCOMING = TSUB_PROCESS__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__OUTGOING = TSUB_PROCESS__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__IO_SPECIFICATION = TSUB_PROCESS__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__PROPERTY = TSUB_PROCESS__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__DATA_INPUT_ASSOCIATION = TSUB_PROCESS__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__DATA_OUTPUT_ASSOCIATION = TSUB_PROCESS__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__RESOURCE_ROLE_GROUP = TSUB_PROCESS__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__RESOURCE_ROLE = TSUB_PROCESS__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__LOOP_CHARACTERISTICS_GROUP = TSUB_PROCESS__LOOP_CHARACTERISTICS_GROUP;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__LOOP_CHARACTERISTICS = TSUB_PROCESS__LOOP_CHARACTERISTICS;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__COMPLETION_QUANTITY = TSUB_PROCESS__COMPLETION_QUANTITY;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__DEFAULT = TSUB_PROCESS__DEFAULT;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__IS_FOR_COMPENSATION = TSUB_PROCESS__IS_FOR_COMPENSATION;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__START_QUANTITY = TSUB_PROCESS__START_QUANTITY;

	/**
	 * The feature id for the '<em><b>Lane Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__LANE_SET = TSUB_PROCESS__LANE_SET;

	/**
	 * The feature id for the '<em><b>Flow Element Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__FLOW_ELEMENT_GROUP = TSUB_PROCESS__FLOW_ELEMENT_GROUP;

	/**
	 * The feature id for the '<em><b>Flow Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__FLOW_ELEMENT = TSUB_PROCESS__FLOW_ELEMENT;

	/**
	 * The feature id for the '<em><b>Artifact Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__ARTIFACT_GROUP = TSUB_PROCESS__ARTIFACT_GROUP;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__ARTIFACT = TSUB_PROCESS__ARTIFACT;

	/**
	 * The feature id for the '<em><b>Triggered By Event</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__TRIGGERED_BY_EVENT = TSUB_PROCESS__TRIGGERED_BY_EVENT;

	/**
	 * The feature id for the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION__METHOD = TSUB_PROCESS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>TTransaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TTRANSACTION_FEATURE_COUNT = TSUB_PROCESS_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.impl.TUserTaskImpl <em>TUser Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.impl.TUserTaskImpl
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTUserTask()
	 * @generated
	 */
	int TUSER_TASK = 135;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__DOCUMENTATION = TTASK__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__EXTENSION_ELEMENTS = TTASK__EXTENSION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__ID = TTASK__ID;

	/**
	 * The feature id for the '<em><b>Any Attribute</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__ANY_ATTRIBUTE = TTASK__ANY_ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__AUDITING = TTASK__AUDITING;

	/**
	 * The feature id for the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__MONITORING = TTASK__MONITORING;

	/**
	 * The feature id for the '<em><b>Category Value Ref</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__CATEGORY_VALUE_REF = TTASK__CATEGORY_VALUE_REF;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__NAME = TTASK__NAME;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__INCOMING = TTASK__INCOMING;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__OUTGOING = TTASK__OUTGOING;

	/**
	 * The feature id for the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__IO_SPECIFICATION = TTASK__IO_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__PROPERTY = TTASK__PROPERTY;

	/**
	 * The feature id for the '<em><b>Data Input Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__DATA_INPUT_ASSOCIATION = TTASK__DATA_INPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Data Output Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__DATA_OUTPUT_ASSOCIATION = TTASK__DATA_OUTPUT_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Resource Role Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__RESOURCE_ROLE_GROUP = TTASK__RESOURCE_ROLE_GROUP;

	/**
	 * The feature id for the '<em><b>Resource Role</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__RESOURCE_ROLE = TTASK__RESOURCE_ROLE;

	/**
	 * The feature id for the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__LOOP_CHARACTERISTICS_GROUP = TTASK__LOOP_CHARACTERISTICS_GROUP;

	/**
	 * The feature id for the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__LOOP_CHARACTERISTICS = TTASK__LOOP_CHARACTERISTICS;

	/**
	 * The feature id for the '<em><b>Completion Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__COMPLETION_QUANTITY = TTASK__COMPLETION_QUANTITY;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__DEFAULT = TTASK__DEFAULT;

	/**
	 * The feature id for the '<em><b>Is For Compensation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__IS_FOR_COMPENSATION = TTASK__IS_FOR_COMPENSATION;

	/**
	 * The feature id for the '<em><b>Start Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__START_QUANTITY = TTASK__START_QUANTITY;

	/**
	 * The feature id for the '<em><b>Rendering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__RENDERING = TTASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK__IMPLEMENTATION = TTASK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>TUser Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUSER_TASK_FEATURE_COUNT = TTASK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.TAdHocOrdering <em>TAd Hoc Ordering</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TAdHocOrdering
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAdHocOrdering()
	 * @generated
	 */
	int TAD_HOC_ORDERING = 136;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.TAssociationDirection <em>TAssociation Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TAssociationDirection
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAssociationDirection()
	 * @generated
	 */
	int TASSOCIATION_DIRECTION = 137;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.TChoreographyLoopType <em>TChoreography Loop Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TChoreographyLoopType
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTChoreographyLoopType()
	 * @generated
	 */
	int TCHOREOGRAPHY_LOOP_TYPE = 138;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.TEventBasedGatewayType <em>TEvent Based Gateway Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TEventBasedGatewayType
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEventBasedGatewayType()
	 * @generated
	 */
	int TEVENT_BASED_GATEWAY_TYPE = 139;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.TGatewayDirection <em>TGateway Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TGatewayDirection
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGatewayDirection()
	 * @generated
	 */
	int TGATEWAY_DIRECTION = 140;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.TImplementationMember1 <em>TImplementation Member1</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TImplementationMember1
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTImplementationMember1()
	 * @generated
	 */
	int TIMPLEMENTATION_MEMBER1 = 141;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.TItemKind <em>TItem Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TItemKind
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTItemKind()
	 * @generated
	 */
	int TITEM_KIND = 142;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.TMultiInstanceFlowCondition <em>TMulti Instance Flow Condition</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TMultiInstanceFlowCondition
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMultiInstanceFlowCondition()
	 * @generated
	 */
	int TMULTI_INSTANCE_FLOW_CONDITION = 143;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.TProcessType <em>TProcess Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TProcessType
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTProcessType()
	 * @generated
	 */
	int TPROCESS_TYPE = 144;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.TRelationshipDirection <em>TRelationship Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TRelationshipDirection
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTRelationshipDirection()
	 * @generated
	 */
	int TRELATIONSHIP_DIRECTION = 145;

	/**
	 * The meta object id for the '{@link org.omg.spec.bpmn.model.TTransactionMethodMember1 <em>TTransaction Method Member1</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TTransactionMethodMember1
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTransactionMethodMember1()
	 * @generated
	 */
	int TTRANSACTION_METHOD_MEMBER1 = 146;

	/**
	 * The meta object id for the '<em>TAd Hoc Ordering Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TAdHocOrdering
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAdHocOrderingObject()
	 * @generated
	 */
	int TAD_HOC_ORDERING_OBJECT = 147;

	/**
	 * The meta object id for the '<em>TAssociation Direction Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TAssociationDirection
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAssociationDirectionObject()
	 * @generated
	 */
	int TASSOCIATION_DIRECTION_OBJECT = 148;

	/**
	 * The meta object id for the '<em>TChoreography Loop Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TChoreographyLoopType
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTChoreographyLoopTypeObject()
	 * @generated
	 */
	int TCHOREOGRAPHY_LOOP_TYPE_OBJECT = 149;

	/**
	 * The meta object id for the '<em>TEvent Based Gateway Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TEventBasedGatewayType
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEventBasedGatewayTypeObject()
	 * @generated
	 */
	int TEVENT_BASED_GATEWAY_TYPE_OBJECT = 150;

	/**
	 * The meta object id for the '<em>TGateway Direction Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TGatewayDirection
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGatewayDirectionObject()
	 * @generated
	 */
	int TGATEWAY_DIRECTION_OBJECT = 151;

	/**
	 * The meta object id for the '<em>TImplementation</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Object
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTImplementation()
	 * @generated
	 */
	int TIMPLEMENTATION = 152;

	/**
	 * The meta object id for the '<em>TImplementation Member1 Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TImplementationMember1
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTImplementationMember1Object()
	 * @generated
	 */
	int TIMPLEMENTATION_MEMBER1_OBJECT = 153;

	/**
	 * The meta object id for the '<em>TItem Kind Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TItemKind
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTItemKindObject()
	 * @generated
	 */
	int TITEM_KIND_OBJECT = 154;

	/**
	 * The meta object id for the '<em>TMulti Instance Flow Condition Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TMultiInstanceFlowCondition
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMultiInstanceFlowConditionObject()
	 * @generated
	 */
	int TMULTI_INSTANCE_FLOW_CONDITION_OBJECT = 155;

	/**
	 * The meta object id for the '<em>TProcess Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TProcessType
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTProcessTypeObject()
	 * @generated
	 */
	int TPROCESS_TYPE_OBJECT = 156;

	/**
	 * The meta object id for the '<em>TRelationship Direction Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TRelationshipDirection
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTRelationshipDirectionObject()
	 * @generated
	 */
	int TRELATIONSHIP_DIRECTION_OBJECT = 157;

	/**
	 * The meta object id for the '<em>TTransaction Method</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Object
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTransactionMethod()
	 * @generated
	 */
	int TTRANSACTION_METHOD = 158;

	/**
	 * The meta object id for the '<em>TTransaction Method Member1 Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.omg.spec.bpmn.model.TTransactionMethodMember1
	 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTransactionMethodMember1Object()
	 * @generated
	 */
	int TTRANSACTION_METHOD_MEMBER1_OBJECT = 159;


	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.omg.spec.bpmn.model.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.omg.spec.bpmn.model.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activity</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getActivity()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Activity();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getAdHocSubProcess <em>Ad Hoc Sub Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ad Hoc Sub Process</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getAdHocSubProcess()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_AdHocSubProcess();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getFlowElement <em>Flow Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Flow Element</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getFlowElement()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FlowElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Artifact</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getArtifact()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Artifact();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getAssignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Assignment</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getAssignment()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Assignment();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getAssociation <em>Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Association</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getAssociation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Association();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getAuditing <em>Auditing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Auditing</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getAuditing()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Auditing();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getBaseElement <em>Base Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Base Element</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getBaseElement()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BaseElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getBaseElementWithMixedContent <em>Base Element With Mixed Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Base Element With Mixed Content</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getBaseElementWithMixedContent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BaseElementWithMixedContent();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getBoundaryEvent <em>Boundary Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Boundary Event</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getBoundaryEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BoundaryEvent();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getBusinessRuleTask <em>Business Rule Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Business Rule Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getBusinessRuleTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BusinessRuleTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCallableElement <em>Callable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Callable Element</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCallableElement()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CallableElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCallActivity <em>Call Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Call Activity</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCallActivity()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CallActivity();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCallChoreography <em>Call Choreography</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Call Choreography</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCallChoreography()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CallChoreography();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCallConversation <em>Call Conversation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Call Conversation</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCallConversation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CallConversation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getConversationNode <em>Conversation Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Conversation Node</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getConversationNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConversationNode();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCancelEventDefinition <em>Cancel Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cancel Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCancelEventDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CancelEventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getEventDefinition <em>Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getEventDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_EventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getRootElement <em>Root Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root Element</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getRootElement()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RootElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCatchEvent <em>Catch Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Catch Event</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCatchEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CatchEvent();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCategory <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Category</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCategory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Category();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCategoryValue <em>Category Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Category Value</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCategoryValue()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CategoryValue();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getChoreography <em>Choreography</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Choreography</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getChoreography()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Choreography();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCollaboration <em>Collaboration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Collaboration</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCollaboration()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Collaboration();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getChoreographyActivity <em>Choreography Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Choreography Activity</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getChoreographyActivity()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ChoreographyActivity();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getChoreographyTask <em>Choreography Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Choreography Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getChoreographyTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ChoreographyTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCompensateEventDefinition <em>Compensate Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Compensate Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCompensateEventDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CompensateEventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getComplexBehaviorDefinition <em>Complex Behavior Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Complex Behavior Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getComplexBehaviorDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ComplexBehaviorDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getComplexGateway <em>Complex Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Complex Gateway</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getComplexGateway()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ComplexGateway();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getConditionalEventDefinition <em>Conditional Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Conditional Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getConditionalEventDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConditionalEventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getConversation <em>Conversation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Conversation</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getConversation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Conversation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getConversationAssociation <em>Conversation Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Conversation Association</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getConversationAssociation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConversationAssociation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getConversationLink <em>Conversation Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Conversation Link</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getConversationLink()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConversationLink();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationKey <em>Correlation Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Correlation Key</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCorrelationKey()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CorrelationKey();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationProperty <em>Correlation Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Correlation Property</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCorrelationProperty()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CorrelationProperty();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationPropertyBinding <em>Correlation Property Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Correlation Property Binding</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCorrelationPropertyBinding()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CorrelationPropertyBinding();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationPropertyRetrievalExpression <em>Correlation Property Retrieval Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Correlation Property Retrieval Expression</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCorrelationPropertyRetrievalExpression()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CorrelationPropertyRetrievalExpression();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationSubscription <em>Correlation Subscription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Correlation Subscription</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getCorrelationSubscription()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CorrelationSubscription();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataAssociation <em>Data Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Association</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getDataAssociation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataAssociation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataInput <em>Data Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Input</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getDataInput()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataInput();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataInputAssociation <em>Data Input Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Input Association</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getDataInputAssociation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataInputAssociation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataObject <em>Data Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Object</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getDataObject()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataObject();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataObjectReference <em>Data Object Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Object Reference</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getDataObjectReference()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataObjectReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataOutput <em>Data Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Output</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getDataOutput()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataOutput();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataOutputAssociation <em>Data Output Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Output Association</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getDataOutputAssociation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataOutputAssociation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataState <em>Data State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data State</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getDataState()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataState();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataStore <em>Data Store</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Store</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getDataStore()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataStore();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataStoreReference <em>Data Store Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Store Reference</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getDataStoreReference()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DataStoreReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getDefinitions <em>Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Definitions</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getDefinitions()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Definitions();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Documentation</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getDocumentation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Documentation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getEndEvent <em>End Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>End Event</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getEndEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_EndEvent();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getEndPoint <em>End Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>End Point</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getEndPoint()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_EndPoint();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getError <em>Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Error</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getError()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Error();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getErrorEventDefinition <em>Error Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Error Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getErrorEventDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ErrorEventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getEscalation <em>Escalation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Escalation</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getEscalation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Escalation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getEscalationEventDefinition <em>Escalation Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Escalation Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getEscalationEventDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_EscalationEventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Event();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getEventBasedGateway <em>Event Based Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event Based Gateway</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getEventBasedGateway()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_EventBasedGateway();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getExclusiveGateway <em>Exclusive Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exclusive Gateway</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getExclusiveGateway()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExclusiveGateway();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getExpression()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Expression();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extension</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getExtension()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Extension();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getExtensionElements <em>Extension Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extension Elements</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getExtensionElements()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExtensionElements();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getFlowNode <em>Flow Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Flow Node</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getFlowNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FlowNode();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getFormalExpression <em>Formal Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Formal Expression</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getFormalExpression()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FormalExpression();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getGateway <em>Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Gateway</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getGateway()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Gateway();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalBusinessRuleTask <em>Global Business Rule Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Global Business Rule Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getGlobalBusinessRuleTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_GlobalBusinessRuleTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalChoreographyTask <em>Global Choreography Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Global Choreography Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getGlobalChoreographyTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_GlobalChoreographyTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalConversation <em>Global Conversation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Global Conversation</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getGlobalConversation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_GlobalConversation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalManualTask <em>Global Manual Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Global Manual Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getGlobalManualTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_GlobalManualTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalScriptTask <em>Global Script Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Global Script Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getGlobalScriptTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_GlobalScriptTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalTask <em>Global Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Global Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getGlobalTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_GlobalTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalUserTask <em>Global User Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Global User Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getGlobalUserTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_GlobalUserTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Group</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getGroup()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Group();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getHumanPerformer <em>Human Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Human Performer</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getHumanPerformer()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_HumanPerformer();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getPerformer <em>Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Performer</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getPerformer()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Performer();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getResourceRole <em>Resource Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource Role</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getResourceRole()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ResourceRole();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getImplicitThrowEvent <em>Implicit Throw Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Implicit Throw Event</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getImplicitThrowEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ImplicitThrowEvent();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getImport <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Import</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getImport()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Import();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getInclusiveGateway <em>Inclusive Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Inclusive Gateway</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getInclusiveGateway()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_InclusiveGateway();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getInputSet <em>Input Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Input Set</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getInputSet()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_InputSet();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Interface</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getInterface()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Interface();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getIntermediateCatchEvent <em>Intermediate Catch Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Intermediate Catch Event</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getIntermediateCatchEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_IntermediateCatchEvent();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getIntermediateThrowEvent <em>Intermediate Throw Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Intermediate Throw Event</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getIntermediateThrowEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_IntermediateThrowEvent();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getIoBinding <em>Io Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Io Binding</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getIoBinding()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_IoBinding();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getIoSpecification <em>Io Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Io Specification</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getIoSpecification()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_IoSpecification();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getItemDefinition <em>Item Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Item Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getItemDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ItemDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getLane <em>Lane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Lane</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getLane()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Lane();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getLaneSet <em>Lane Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Lane Set</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getLaneSet()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_LaneSet();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getLinkEventDefinition <em>Link Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Link Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getLinkEventDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_LinkEventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getLoopCharacteristics <em>Loop Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Loop Characteristics</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getLoopCharacteristics()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_LoopCharacteristics();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getManualTask <em>Manual Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Manual Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getManualTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ManualTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Message</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getMessage()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Message();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getMessageEventDefinition <em>Message Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Message Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getMessageEventDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MessageEventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getMessageFlow <em>Message Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Message Flow</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getMessageFlow()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MessageFlow();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getMessageFlowAssociation <em>Message Flow Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Message Flow Association</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getMessageFlowAssociation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MessageFlowAssociation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getMonitoring <em>Monitoring</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Monitoring</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getMonitoring()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Monitoring();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getMultiInstanceLoopCharacteristics <em>Multi Instance Loop Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Multi Instance Loop Characteristics</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getMultiInstanceLoopCharacteristics()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MultiInstanceLoopCharacteristics();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operation</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getOperation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Operation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getOutputSet <em>Output Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Output Set</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getOutputSet()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_OutputSet();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getParallelGateway <em>Parallel Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Parallel Gateway</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getParallelGateway()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ParallelGateway();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Participant</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getParticipant()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Participant();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getParticipantAssociation <em>Participant Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Participant Association</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getParticipantAssociation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ParticipantAssociation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getParticipantMultiplicity <em>Participant Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Participant Multiplicity</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getParticipantMultiplicity()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ParticipantMultiplicity();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getPartnerEntity <em>Partner Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Partner Entity</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getPartnerEntity()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PartnerEntity();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getPartnerRole <em>Partner Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Partner Role</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getPartnerRole()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PartnerRole();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getPotentialOwner <em>Potential Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Potential Owner</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getPotentialOwner()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PotentialOwner();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getProcess <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getProcess()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Process();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getProperty()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Property();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getReceiveTask <em>Receive Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Receive Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getReceiveTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ReceiveTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getRelationship <em>Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Relationship</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getRelationship()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Relationship();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getRendering <em>Rendering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Rendering</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getRendering()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Rendering();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getResource()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Resource();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource Assignment Expression</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getResourceAssignmentExpression()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ResourceAssignmentExpression();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getResourceParameter <em>Resource Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource Parameter</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getResourceParameter()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ResourceParameter();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getResourceParameterBinding <em>Resource Parameter Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource Parameter Binding</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getResourceParameterBinding()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ResourceParameterBinding();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getScript()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Script();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getScriptTask <em>Script Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getScriptTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ScriptTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getSendTask <em>Send Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Send Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getSendTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SendTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getSequenceFlow <em>Sequence Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sequence Flow</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getSequenceFlow()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SequenceFlow();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getServiceTask <em>Service Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Service Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getServiceTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ServiceTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getSignal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Signal</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getSignal()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Signal();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getSignalEventDefinition <em>Signal Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Signal Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getSignalEventDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SignalEventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getStandardLoopCharacteristics <em>Standard Loop Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Standard Loop Characteristics</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getStandardLoopCharacteristics()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StandardLoopCharacteristics();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getStartEvent <em>Start Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Start Event</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getStartEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StartEvent();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getSubChoreography <em>Sub Choreography</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sub Choreography</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getSubChoreography()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SubChoreography();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getSubConversation <em>Sub Conversation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sub Conversation</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getSubConversation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SubConversation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getSubProcess <em>Sub Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sub Process</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getSubProcess()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SubProcess();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Task();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getTerminateEventDefinition <em>Terminate Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Terminate Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getTerminateEventDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TerminateEventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Text</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getText()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Text();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getTextAnnotation <em>Text Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Text Annotation</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getTextAnnotation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TextAnnotation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getThrowEvent <em>Throw Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Throw Event</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getThrowEvent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ThrowEvent();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getTimerEventDefinition <em>Timer Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Timer Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getTimerEventDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TimerEventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getTransaction <em>Transaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transaction</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getTransaction()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Transaction();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.DocumentRoot#getUserTask <em>User Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>User Task</em>'.
	 * @see org.omg.spec.bpmn.model.DocumentRoot#getUserTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_UserTask();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TActivity <em>TActivity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TActivity</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity
	 * @generated
	 */
	EClass getTActivity();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TActivity#getIoSpecification <em>Io Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Io Specification</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity#getIoSpecification()
	 * @see #getTActivity()
	 * @generated
	 */
	EReference getTActivity_IoSpecification();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TActivity#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity#getProperty()
	 * @see #getTActivity()
	 * @generated
	 */
	EReference getTActivity_Property();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TActivity#getDataInputAssociation <em>Data Input Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Input Association</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity#getDataInputAssociation()
	 * @see #getTActivity()
	 * @generated
	 */
	EReference getTActivity_DataInputAssociation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TActivity#getDataOutputAssociation <em>Data Output Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Output Association</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity#getDataOutputAssociation()
	 * @see #getTActivity()
	 * @generated
	 */
	EReference getTActivity_DataOutputAssociation();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TActivity#getResourceRoleGroup <em>Resource Role Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Resource Role Group</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity#getResourceRoleGroup()
	 * @see #getTActivity()
	 * @generated
	 */
	EAttribute getTActivity_ResourceRoleGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TActivity#getResourceRole <em>Resource Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Role</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity#getResourceRole()
	 * @see #getTActivity()
	 * @generated
	 */
	EReference getTActivity_ResourceRole();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TActivity#getLoopCharacteristicsGroup <em>Loop Characteristics Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Loop Characteristics Group</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity#getLoopCharacteristicsGroup()
	 * @see #getTActivity()
	 * @generated
	 */
	EAttribute getTActivity_LoopCharacteristicsGroup();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TActivity#getLoopCharacteristics <em>Loop Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Loop Characteristics</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity#getLoopCharacteristics()
	 * @see #getTActivity()
	 * @generated
	 */
	EReference getTActivity_LoopCharacteristics();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TActivity#getCompletionQuantity <em>Completion Quantity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Completion Quantity</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity#getCompletionQuantity()
	 * @see #getTActivity()
	 * @generated
	 */
	EAttribute getTActivity_CompletionQuantity();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TActivity#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity#getDefault()
	 * @see #getTActivity()
	 * @generated
	 */
	EAttribute getTActivity_Default();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TActivity#isIsForCompensation <em>Is For Compensation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is For Compensation</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity#isIsForCompensation()
	 * @see #getTActivity()
	 * @generated
	 */
	EAttribute getTActivity_IsForCompensation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TActivity#getStartQuantity <em>Start Quantity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Quantity</em>'.
	 * @see org.omg.spec.bpmn.model.TActivity#getStartQuantity()
	 * @see #getTActivity()
	 * @generated
	 */
	EAttribute getTActivity_StartQuantity();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TAdHocSubProcess <em>TAd Hoc Sub Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TAd Hoc Sub Process</em>'.
	 * @see org.omg.spec.bpmn.model.TAdHocSubProcess
	 * @generated
	 */
	EClass getTAdHocSubProcess();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TAdHocSubProcess#getCompletionCondition <em>Completion Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Completion Condition</em>'.
	 * @see org.omg.spec.bpmn.model.TAdHocSubProcess#getCompletionCondition()
	 * @see #getTAdHocSubProcess()
	 * @generated
	 */
	EReference getTAdHocSubProcess_CompletionCondition();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TAdHocSubProcess#isCancelRemainingInstances <em>Cancel Remaining Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cancel Remaining Instances</em>'.
	 * @see org.omg.spec.bpmn.model.TAdHocSubProcess#isCancelRemainingInstances()
	 * @see #getTAdHocSubProcess()
	 * @generated
	 */
	EAttribute getTAdHocSubProcess_CancelRemainingInstances();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TAdHocSubProcess#getOrdering <em>Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ordering</em>'.
	 * @see org.omg.spec.bpmn.model.TAdHocSubProcess#getOrdering()
	 * @see #getTAdHocSubProcess()
	 * @generated
	 */
	EAttribute getTAdHocSubProcess_Ordering();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TArtifact <em>TArtifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TArtifact</em>'.
	 * @see org.omg.spec.bpmn.model.TArtifact
	 * @generated
	 */
	EClass getTArtifact();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TAssignment <em>TAssignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TAssignment</em>'.
	 * @see org.omg.spec.bpmn.model.TAssignment
	 * @generated
	 */
	EClass getTAssignment();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TAssignment#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>From</em>'.
	 * @see org.omg.spec.bpmn.model.TAssignment#getFrom()
	 * @see #getTAssignment()
	 * @generated
	 */
	EReference getTAssignment_From();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TAssignment#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>To</em>'.
	 * @see org.omg.spec.bpmn.model.TAssignment#getTo()
	 * @see #getTAssignment()
	 * @generated
	 */
	EReference getTAssignment_To();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TAssociation <em>TAssociation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TAssociation</em>'.
	 * @see org.omg.spec.bpmn.model.TAssociation
	 * @generated
	 */
	EClass getTAssociation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TAssociation#getAssociationDirection <em>Association Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Association Direction</em>'.
	 * @see org.omg.spec.bpmn.model.TAssociation#getAssociationDirection()
	 * @see #getTAssociation()
	 * @generated
	 */
	EAttribute getTAssociation_AssociationDirection();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TAssociation#getSourceRef <em>Source Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TAssociation#getSourceRef()
	 * @see #getTAssociation()
	 * @generated
	 */
	EAttribute getTAssociation_SourceRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TAssociation#getTargetRef <em>Target Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TAssociation#getTargetRef()
	 * @see #getTAssociation()
	 * @generated
	 */
	EAttribute getTAssociation_TargetRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TAuditing <em>TAuditing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TAuditing</em>'.
	 * @see org.omg.spec.bpmn.model.TAuditing
	 * @generated
	 */
	EClass getTAuditing();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TBaseElement <em>TBase Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TBase Element</em>'.
	 * @see org.omg.spec.bpmn.model.TBaseElement
	 * @generated
	 */
	EClass getTBaseElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TBaseElement#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Documentation</em>'.
	 * @see org.omg.spec.bpmn.model.TBaseElement#getDocumentation()
	 * @see #getTBaseElement()
	 * @generated
	 */
	EReference getTBaseElement_Documentation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TBaseElement#getExtensionElements <em>Extension Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extension Elements</em>'.
	 * @see org.omg.spec.bpmn.model.TBaseElement#getExtensionElements()
	 * @see #getTBaseElement()
	 * @generated
	 */
	EReference getTBaseElement_ExtensionElements();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TBaseElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.omg.spec.bpmn.model.TBaseElement#getId()
	 * @see #getTBaseElement()
	 * @generated
	 */
	EAttribute getTBaseElement_Id();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TBaseElement#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.omg.spec.bpmn.model.TBaseElement#getAnyAttribute()
	 * @see #getTBaseElement()
	 * @generated
	 */
	EAttribute getTBaseElement_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TBaseElementWithMixedContent <em>TBase Element With Mixed Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TBase Element With Mixed Content</em>'.
	 * @see org.omg.spec.bpmn.model.TBaseElementWithMixedContent
	 * @generated
	 */
	EClass getTBaseElementWithMixedContent();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TBaseElementWithMixedContent#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.omg.spec.bpmn.model.TBaseElementWithMixedContent#getMixed()
	 * @see #getTBaseElementWithMixedContent()
	 * @generated
	 */
	EAttribute getTBaseElementWithMixedContent_Mixed();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TBaseElementWithMixedContent#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Documentation</em>'.
	 * @see org.omg.spec.bpmn.model.TBaseElementWithMixedContent#getDocumentation()
	 * @see #getTBaseElementWithMixedContent()
	 * @generated
	 */
	EReference getTBaseElementWithMixedContent_Documentation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TBaseElementWithMixedContent#getExtensionElements <em>Extension Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extension Elements</em>'.
	 * @see org.omg.spec.bpmn.model.TBaseElementWithMixedContent#getExtensionElements()
	 * @see #getTBaseElementWithMixedContent()
	 * @generated
	 */
	EReference getTBaseElementWithMixedContent_ExtensionElements();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TBaseElementWithMixedContent#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.omg.spec.bpmn.model.TBaseElementWithMixedContent#getId()
	 * @see #getTBaseElementWithMixedContent()
	 * @generated
	 */
	EAttribute getTBaseElementWithMixedContent_Id();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TBaseElementWithMixedContent#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.omg.spec.bpmn.model.TBaseElementWithMixedContent#getAnyAttribute()
	 * @see #getTBaseElementWithMixedContent()
	 * @generated
	 */
	EAttribute getTBaseElementWithMixedContent_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TBoundaryEvent <em>TBoundary Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TBoundary Event</em>'.
	 * @see org.omg.spec.bpmn.model.TBoundaryEvent
	 * @generated
	 */
	EClass getTBoundaryEvent();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TBoundaryEvent#getAttachedToRef <em>Attached To Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attached To Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TBoundaryEvent#getAttachedToRef()
	 * @see #getTBoundaryEvent()
	 * @generated
	 */
	EAttribute getTBoundaryEvent_AttachedToRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TBoundaryEvent#isCancelActivity <em>Cancel Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cancel Activity</em>'.
	 * @see org.omg.spec.bpmn.model.TBoundaryEvent#isCancelActivity()
	 * @see #getTBoundaryEvent()
	 * @generated
	 */
	EAttribute getTBoundaryEvent_CancelActivity();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TBusinessRuleTask <em>TBusiness Rule Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TBusiness Rule Task</em>'.
	 * @see org.omg.spec.bpmn.model.TBusinessRuleTask
	 * @generated
	 */
	EClass getTBusinessRuleTask();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TBusinessRuleTask#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see org.omg.spec.bpmn.model.TBusinessRuleTask#getImplementation()
	 * @see #getTBusinessRuleTask()
	 * @generated
	 */
	EAttribute getTBusinessRuleTask_Implementation();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCallableElement <em>TCallable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCallable Element</em>'.
	 * @see org.omg.spec.bpmn.model.TCallableElement
	 * @generated
	 */
	EClass getTCallableElement();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TCallableElement#getSupportedInterfaceRef <em>Supported Interface Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Supported Interface Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TCallableElement#getSupportedInterfaceRef()
	 * @see #getTCallableElement()
	 * @generated
	 */
	EAttribute getTCallableElement_SupportedInterfaceRef();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TCallableElement#getIoSpecification <em>Io Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Io Specification</em>'.
	 * @see org.omg.spec.bpmn.model.TCallableElement#getIoSpecification()
	 * @see #getTCallableElement()
	 * @generated
	 */
	EReference getTCallableElement_IoSpecification();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCallableElement#getIoBinding <em>Io Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Io Binding</em>'.
	 * @see org.omg.spec.bpmn.model.TCallableElement#getIoBinding()
	 * @see #getTCallableElement()
	 * @generated
	 */
	EReference getTCallableElement_IoBinding();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCallableElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TCallableElement#getName()
	 * @see #getTCallableElement()
	 * @generated
	 */
	EAttribute getTCallableElement_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCallActivity <em>TCall Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCall Activity</em>'.
	 * @see org.omg.spec.bpmn.model.TCallActivity
	 * @generated
	 */
	EClass getTCallActivity();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCallActivity#getCalledElement <em>Called Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Called Element</em>'.
	 * @see org.omg.spec.bpmn.model.TCallActivity#getCalledElement()
	 * @see #getTCallActivity()
	 * @generated
	 */
	EAttribute getTCallActivity_CalledElement();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCallChoreography <em>TCall Choreography</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCall Choreography</em>'.
	 * @see org.omg.spec.bpmn.model.TCallChoreography
	 * @generated
	 */
	EClass getTCallChoreography();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCallChoreography#getParticipantAssociation <em>Participant Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participant Association</em>'.
	 * @see org.omg.spec.bpmn.model.TCallChoreography#getParticipantAssociation()
	 * @see #getTCallChoreography()
	 * @generated
	 */
	EReference getTCallChoreography_ParticipantAssociation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCallChoreography#getCalledChoreographyRef <em>Called Choreography Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Called Choreography Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TCallChoreography#getCalledChoreographyRef()
	 * @see #getTCallChoreography()
	 * @generated
	 */
	EAttribute getTCallChoreography_CalledChoreographyRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCallConversation <em>TCall Conversation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCall Conversation</em>'.
	 * @see org.omg.spec.bpmn.model.TCallConversation
	 * @generated
	 */
	EClass getTCallConversation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCallConversation#getParticipantAssociation <em>Participant Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participant Association</em>'.
	 * @see org.omg.spec.bpmn.model.TCallConversation#getParticipantAssociation()
	 * @see #getTCallConversation()
	 * @generated
	 */
	EReference getTCallConversation_ParticipantAssociation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCallConversation#getCalledCollaborationRef <em>Called Collaboration Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Called Collaboration Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TCallConversation#getCalledCollaborationRef()
	 * @see #getTCallConversation()
	 * @generated
	 */
	EAttribute getTCallConversation_CalledCollaborationRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCancelEventDefinition <em>TCancel Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCancel Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TCancelEventDefinition
	 * @generated
	 */
	EClass getTCancelEventDefinition();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCatchEvent <em>TCatch Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCatch Event</em>'.
	 * @see org.omg.spec.bpmn.model.TCatchEvent
	 * @generated
	 */
	EClass getTCatchEvent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCatchEvent#getDataOutput <em>Data Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Output</em>'.
	 * @see org.omg.spec.bpmn.model.TCatchEvent#getDataOutput()
	 * @see #getTCatchEvent()
	 * @generated
	 */
	EReference getTCatchEvent_DataOutput();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCatchEvent#getDataOutputAssociation <em>Data Output Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Output Association</em>'.
	 * @see org.omg.spec.bpmn.model.TCatchEvent#getDataOutputAssociation()
	 * @see #getTCatchEvent()
	 * @generated
	 */
	EReference getTCatchEvent_DataOutputAssociation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TCatchEvent#getOutputSet <em>Output Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Output Set</em>'.
	 * @see org.omg.spec.bpmn.model.TCatchEvent#getOutputSet()
	 * @see #getTCatchEvent()
	 * @generated
	 */
	EReference getTCatchEvent_OutputSet();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TCatchEvent#getEventDefinitionGroup <em>Event Definition Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Event Definition Group</em>'.
	 * @see org.omg.spec.bpmn.model.TCatchEvent#getEventDefinitionGroup()
	 * @see #getTCatchEvent()
	 * @generated
	 */
	EAttribute getTCatchEvent_EventDefinitionGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCatchEvent#getEventDefinition <em>Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TCatchEvent#getEventDefinition()
	 * @see #getTCatchEvent()
	 * @generated
	 */
	EReference getTCatchEvent_EventDefinition();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TCatchEvent#getEventDefinitionRef <em>Event Definition Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Event Definition Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TCatchEvent#getEventDefinitionRef()
	 * @see #getTCatchEvent()
	 * @generated
	 */
	EAttribute getTCatchEvent_EventDefinitionRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCatchEvent#isParallelMultiple <em>Parallel Multiple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parallel Multiple</em>'.
	 * @see org.omg.spec.bpmn.model.TCatchEvent#isParallelMultiple()
	 * @see #getTCatchEvent()
	 * @generated
	 */
	EAttribute getTCatchEvent_ParallelMultiple();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCategory <em>TCategory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCategory</em>'.
	 * @see org.omg.spec.bpmn.model.TCategory
	 * @generated
	 */
	EClass getTCategory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCategory#getCategoryValue <em>Category Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Category Value</em>'.
	 * @see org.omg.spec.bpmn.model.TCategory#getCategoryValue()
	 * @see #getTCategory()
	 * @generated
	 */
	EReference getTCategory_CategoryValue();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCategory#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TCategory#getName()
	 * @see #getTCategory()
	 * @generated
	 */
	EAttribute getTCategory_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCategoryValue <em>TCategory Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCategory Value</em>'.
	 * @see org.omg.spec.bpmn.model.TCategoryValue
	 * @generated
	 */
	EClass getTCategoryValue();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCategoryValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.omg.spec.bpmn.model.TCategoryValue#getValue()
	 * @see #getTCategoryValue()
	 * @generated
	 */
	EAttribute getTCategoryValue_Value();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TChoreography <em>TChoreography</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TChoreography</em>'.
	 * @see org.omg.spec.bpmn.model.TChoreography
	 * @generated
	 */
	EClass getTChoreography();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TChoreography#getFlowElementGroup <em>Flow Element Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Flow Element Group</em>'.
	 * @see org.omg.spec.bpmn.model.TChoreography#getFlowElementGroup()
	 * @see #getTChoreography()
	 * @generated
	 */
	EAttribute getTChoreography_FlowElementGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TChoreography#getFlowElement <em>Flow Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Flow Element</em>'.
	 * @see org.omg.spec.bpmn.model.TChoreography#getFlowElement()
	 * @see #getTChoreography()
	 * @generated
	 */
	EReference getTChoreography_FlowElement();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TChoreographyActivity <em>TChoreography Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TChoreography Activity</em>'.
	 * @see org.omg.spec.bpmn.model.TChoreographyActivity
	 * @generated
	 */
	EClass getTChoreographyActivity();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TChoreographyActivity#getParticipantRef <em>Participant Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Participant Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TChoreographyActivity#getParticipantRef()
	 * @see #getTChoreographyActivity()
	 * @generated
	 */
	EAttribute getTChoreographyActivity_ParticipantRef();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TChoreographyActivity#getCorrelationKey <em>Correlation Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Correlation Key</em>'.
	 * @see org.omg.spec.bpmn.model.TChoreographyActivity#getCorrelationKey()
	 * @see #getTChoreographyActivity()
	 * @generated
	 */
	EReference getTChoreographyActivity_CorrelationKey();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TChoreographyActivity#getInitiatingParticipantRef <em>Initiating Participant Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initiating Participant Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TChoreographyActivity#getInitiatingParticipantRef()
	 * @see #getTChoreographyActivity()
	 * @generated
	 */
	EAttribute getTChoreographyActivity_InitiatingParticipantRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TChoreographyActivity#getLoopType <em>Loop Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loop Type</em>'.
	 * @see org.omg.spec.bpmn.model.TChoreographyActivity#getLoopType()
	 * @see #getTChoreographyActivity()
	 * @generated
	 */
	EAttribute getTChoreographyActivity_LoopType();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TChoreographyTask <em>TChoreography Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TChoreography Task</em>'.
	 * @see org.omg.spec.bpmn.model.TChoreographyTask
	 * @generated
	 */
	EClass getTChoreographyTask();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TChoreographyTask#getMessageFlowRef <em>Message Flow Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Message Flow Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TChoreographyTask#getMessageFlowRef()
	 * @see #getTChoreographyTask()
	 * @generated
	 */
	EAttribute getTChoreographyTask_MessageFlowRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCollaboration <em>TCollaboration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCollaboration</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration
	 * @generated
	 */
	EClass getTCollaboration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCollaboration#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participant</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getParticipant()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EReference getTCollaboration_Participant();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCollaboration#getMessageFlow <em>Message Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Flow</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getMessageFlow()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EReference getTCollaboration_MessageFlow();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TCollaboration#getArtifactGroup <em>Artifact Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Artifact Group</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getArtifactGroup()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EAttribute getTCollaboration_ArtifactGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCollaboration#getArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Artifact</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getArtifact()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EReference getTCollaboration_Artifact();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TCollaboration#getConversationNodeGroup <em>Conversation Node Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Conversation Node Group</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getConversationNodeGroup()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EAttribute getTCollaboration_ConversationNodeGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCollaboration#getConversationNode <em>Conversation Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conversation Node</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getConversationNode()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EReference getTCollaboration_ConversationNode();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCollaboration#getConversationAssociation <em>Conversation Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conversation Association</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getConversationAssociation()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EReference getTCollaboration_ConversationAssociation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCollaboration#getParticipantAssociation <em>Participant Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participant Association</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getParticipantAssociation()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EReference getTCollaboration_ParticipantAssociation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCollaboration#getMessageFlowAssociation <em>Message Flow Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Flow Association</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getMessageFlowAssociation()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EReference getTCollaboration_MessageFlowAssociation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCollaboration#getCorrelationKey <em>Correlation Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Correlation Key</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getCorrelationKey()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EReference getTCollaboration_CorrelationKey();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TCollaboration#getChoreographyRef <em>Choreography Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Choreography Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getChoreographyRef()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EAttribute getTCollaboration_ChoreographyRef();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCollaboration#getConversationLink <em>Conversation Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conversation Link</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getConversationLink()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EReference getTCollaboration_ConversationLink();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCollaboration#isIsClosed <em>Is Closed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Closed</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#isIsClosed()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EAttribute getTCollaboration_IsClosed();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCollaboration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TCollaboration#getName()
	 * @see #getTCollaboration()
	 * @generated
	 */
	EAttribute getTCollaboration_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCompensateEventDefinition <em>TCompensate Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCompensate Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TCompensateEventDefinition
	 * @generated
	 */
	EClass getTCompensateEventDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#getActivityRef <em>Activity Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TCompensateEventDefinition#getActivityRef()
	 * @see #getTCompensateEventDefinition()
	 * @generated
	 */
	EAttribute getTCompensateEventDefinition_ActivityRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#isWaitForCompletion <em>Wait For Completion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wait For Completion</em>'.
	 * @see org.omg.spec.bpmn.model.TCompensateEventDefinition#isWaitForCompletion()
	 * @see #getTCompensateEventDefinition()
	 * @generated
	 */
	EAttribute getTCompensateEventDefinition_WaitForCompletion();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition <em>TComplex Behavior Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TComplex Behavior Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TComplexBehaviorDefinition
	 * @generated
	 */
	EClass getTComplexBehaviorDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see org.omg.spec.bpmn.model.TComplexBehaviorDefinition#getCondition()
	 * @see #getTComplexBehaviorDefinition()
	 * @generated
	 */
	EReference getTComplexBehaviorDefinition_Condition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event</em>'.
	 * @see org.omg.spec.bpmn.model.TComplexBehaviorDefinition#getEvent()
	 * @see #getTComplexBehaviorDefinition()
	 * @generated
	 */
	EReference getTComplexBehaviorDefinition_Event();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TComplexGateway <em>TComplex Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TComplex Gateway</em>'.
	 * @see org.omg.spec.bpmn.model.TComplexGateway
	 * @generated
	 */
	EClass getTComplexGateway();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TComplexGateway#getActivationCondition <em>Activation Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activation Condition</em>'.
	 * @see org.omg.spec.bpmn.model.TComplexGateway#getActivationCondition()
	 * @see #getTComplexGateway()
	 * @generated
	 */
	EReference getTComplexGateway_ActivationCondition();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TComplexGateway#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see org.omg.spec.bpmn.model.TComplexGateway#getDefault()
	 * @see #getTComplexGateway()
	 * @generated
	 */
	EAttribute getTComplexGateway_Default();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TConditionalEventDefinition <em>TConditional Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TConditional Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TConditionalEventDefinition
	 * @generated
	 */
	EClass getTConditionalEventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TConditionalEventDefinition#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see org.omg.spec.bpmn.model.TConditionalEventDefinition#getCondition()
	 * @see #getTConditionalEventDefinition()
	 * @generated
	 */
	EReference getTConditionalEventDefinition_Condition();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TConversation <em>TConversation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TConversation</em>'.
	 * @see org.omg.spec.bpmn.model.TConversation
	 * @generated
	 */
	EClass getTConversation();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TConversationAssociation <em>TConversation Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TConversation Association</em>'.
	 * @see org.omg.spec.bpmn.model.TConversationAssociation
	 * @generated
	 */
	EClass getTConversationAssociation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TConversationAssociation#getInnerConversationNodeRef <em>Inner Conversation Node Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inner Conversation Node Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TConversationAssociation#getInnerConversationNodeRef()
	 * @see #getTConversationAssociation()
	 * @generated
	 */
	EAttribute getTConversationAssociation_InnerConversationNodeRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TConversationAssociation#getOuterConversationNodeRef <em>Outer Conversation Node Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outer Conversation Node Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TConversationAssociation#getOuterConversationNodeRef()
	 * @see #getTConversationAssociation()
	 * @generated
	 */
	EAttribute getTConversationAssociation_OuterConversationNodeRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TConversationLink <em>TConversation Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TConversation Link</em>'.
	 * @see org.omg.spec.bpmn.model.TConversationLink
	 * @generated
	 */
	EClass getTConversationLink();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TConversationLink#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TConversationLink#getName()
	 * @see #getTConversationLink()
	 * @generated
	 */
	EAttribute getTConversationLink_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TConversationLink#getSourceRef <em>Source Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TConversationLink#getSourceRef()
	 * @see #getTConversationLink()
	 * @generated
	 */
	EAttribute getTConversationLink_SourceRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TConversationLink#getTargetRef <em>Target Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TConversationLink#getTargetRef()
	 * @see #getTConversationLink()
	 * @generated
	 */
	EAttribute getTConversationLink_TargetRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TConversationNode <em>TConversation Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TConversation Node</em>'.
	 * @see org.omg.spec.bpmn.model.TConversationNode
	 * @generated
	 */
	EClass getTConversationNode();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TConversationNode#getParticipantRef <em>Participant Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Participant Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TConversationNode#getParticipantRef()
	 * @see #getTConversationNode()
	 * @generated
	 */
	EAttribute getTConversationNode_ParticipantRef();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TConversationNode#getMessageFlowRef <em>Message Flow Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Message Flow Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TConversationNode#getMessageFlowRef()
	 * @see #getTConversationNode()
	 * @generated
	 */
	EAttribute getTConversationNode_MessageFlowRef();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TConversationNode#getCorrelationKey <em>Correlation Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Correlation Key</em>'.
	 * @see org.omg.spec.bpmn.model.TConversationNode#getCorrelationKey()
	 * @see #getTConversationNode()
	 * @generated
	 */
	EReference getTConversationNode_CorrelationKey();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TConversationNode#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TConversationNode#getName()
	 * @see #getTConversationNode()
	 * @generated
	 */
	EAttribute getTConversationNode_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCorrelationKey <em>TCorrelation Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCorrelation Key</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationKey
	 * @generated
	 */
	EClass getTCorrelationKey();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TCorrelationKey#getCorrelationPropertyRef <em>Correlation Property Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Correlation Property Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationKey#getCorrelationPropertyRef()
	 * @see #getTCorrelationKey()
	 * @generated
	 */
	EAttribute getTCorrelationKey_CorrelationPropertyRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCorrelationKey#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationKey#getName()
	 * @see #getTCorrelationKey()
	 * @generated
	 */
	EAttribute getTCorrelationKey_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCorrelationProperty <em>TCorrelation Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCorrelation Property</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationProperty
	 * @generated
	 */
	EClass getTCorrelationProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCorrelationProperty#getCorrelationPropertyRetrievalExpression <em>Correlation Property Retrieval Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Correlation Property Retrieval Expression</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationProperty#getCorrelationPropertyRetrievalExpression()
	 * @see #getTCorrelationProperty()
	 * @generated
	 */
	EReference getTCorrelationProperty_CorrelationPropertyRetrievalExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCorrelationProperty#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationProperty#getName()
	 * @see #getTCorrelationProperty()
	 * @generated
	 */
	EAttribute getTCorrelationProperty_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCorrelationProperty#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationProperty#getType()
	 * @see #getTCorrelationProperty()
	 * @generated
	 */
	EAttribute getTCorrelationProperty_Type();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding <em>TCorrelation Property Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCorrelation Property Binding</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationPropertyBinding
	 * @generated
	 */
	EClass getTCorrelationPropertyBinding();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding#getDataPath <em>Data Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Path</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationPropertyBinding#getDataPath()
	 * @see #getTCorrelationPropertyBinding()
	 * @generated
	 */
	EReference getTCorrelationPropertyBinding_DataPath();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding#getCorrelationPropertyRef <em>Correlation Property Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Correlation Property Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationPropertyBinding#getCorrelationPropertyRef()
	 * @see #getTCorrelationPropertyBinding()
	 * @generated
	 */
	EAttribute getTCorrelationPropertyBinding_CorrelationPropertyRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression <em>TCorrelation Property Retrieval Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCorrelation Property Retrieval Expression</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression
	 * @generated
	 */
	EClass getTCorrelationPropertyRetrievalExpression();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression#getMessagePath <em>Message Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Message Path</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression#getMessagePath()
	 * @see #getTCorrelationPropertyRetrievalExpression()
	 * @generated
	 */
	EReference getTCorrelationPropertyRetrievalExpression_MessagePath();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression#getMessageRef <em>Message Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression#getMessageRef()
	 * @see #getTCorrelationPropertyRetrievalExpression()
	 * @generated
	 */
	EAttribute getTCorrelationPropertyRetrievalExpression_MessageRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TCorrelationSubscription <em>TCorrelation Subscription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TCorrelation Subscription</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationSubscription
	 * @generated
	 */
	EClass getTCorrelationSubscription();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TCorrelationSubscription#getCorrelationPropertyBinding <em>Correlation Property Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Correlation Property Binding</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationSubscription#getCorrelationPropertyBinding()
	 * @see #getTCorrelationSubscription()
	 * @generated
	 */
	EReference getTCorrelationSubscription_CorrelationPropertyBinding();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TCorrelationSubscription#getCorrelationKeyRef <em>Correlation Key Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Correlation Key Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TCorrelationSubscription#getCorrelationKeyRef()
	 * @see #getTCorrelationSubscription()
	 * @generated
	 */
	EAttribute getTCorrelationSubscription_CorrelationKeyRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TDataAssociation <em>TData Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TData Association</em>'.
	 * @see org.omg.spec.bpmn.model.TDataAssociation
	 * @generated
	 */
	EClass getTDataAssociation();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TDataAssociation#getSourceRef <em>Source Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Source Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TDataAssociation#getSourceRef()
	 * @see #getTDataAssociation()
	 * @generated
	 */
	EAttribute getTDataAssociation_SourceRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataAssociation#getTargetRef <em>Target Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TDataAssociation#getTargetRef()
	 * @see #getTDataAssociation()
	 * @generated
	 */
	EAttribute getTDataAssociation_TargetRef();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TDataAssociation#getTransformation <em>Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transformation</em>'.
	 * @see org.omg.spec.bpmn.model.TDataAssociation#getTransformation()
	 * @see #getTDataAssociation()
	 * @generated
	 */
	EReference getTDataAssociation_Transformation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TDataAssociation#getAssignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Assignment</em>'.
	 * @see org.omg.spec.bpmn.model.TDataAssociation#getAssignment()
	 * @see #getTDataAssociation()
	 * @generated
	 */
	EReference getTDataAssociation_Assignment();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TDataInput <em>TData Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TData Input</em>'.
	 * @see org.omg.spec.bpmn.model.TDataInput
	 * @generated
	 */
	EClass getTDataInput();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TDataInput#getDataState <em>Data State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data State</em>'.
	 * @see org.omg.spec.bpmn.model.TDataInput#getDataState()
	 * @see #getTDataInput()
	 * @generated
	 */
	EReference getTDataInput_DataState();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataInput#isIsCollection <em>Is Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Collection</em>'.
	 * @see org.omg.spec.bpmn.model.TDataInput#isIsCollection()
	 * @see #getTDataInput()
	 * @generated
	 */
	EAttribute getTDataInput_IsCollection();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataInput#getItemSubjectRef <em>Item Subject Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Item Subject Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TDataInput#getItemSubjectRef()
	 * @see #getTDataInput()
	 * @generated
	 */
	EAttribute getTDataInput_ItemSubjectRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataInput#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TDataInput#getName()
	 * @see #getTDataInput()
	 * @generated
	 */
	EAttribute getTDataInput_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TDataInputAssociation <em>TData Input Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TData Input Association</em>'.
	 * @see org.omg.spec.bpmn.model.TDataInputAssociation
	 * @generated
	 */
	EClass getTDataInputAssociation();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TDataObject <em>TData Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TData Object</em>'.
	 * @see org.omg.spec.bpmn.model.TDataObject
	 * @generated
	 */
	EClass getTDataObject();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TDataObject#getDataState <em>Data State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data State</em>'.
	 * @see org.omg.spec.bpmn.model.TDataObject#getDataState()
	 * @see #getTDataObject()
	 * @generated
	 */
	EReference getTDataObject_DataState();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataObject#isIsCollection <em>Is Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Collection</em>'.
	 * @see org.omg.spec.bpmn.model.TDataObject#isIsCollection()
	 * @see #getTDataObject()
	 * @generated
	 */
	EAttribute getTDataObject_IsCollection();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataObject#getItemSubjectRef <em>Item Subject Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Item Subject Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TDataObject#getItemSubjectRef()
	 * @see #getTDataObject()
	 * @generated
	 */
	EAttribute getTDataObject_ItemSubjectRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TDataObjectReference <em>TData Object Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TData Object Reference</em>'.
	 * @see org.omg.spec.bpmn.model.TDataObjectReference
	 * @generated
	 */
	EClass getTDataObjectReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TDataObjectReference#getDataState <em>Data State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data State</em>'.
	 * @see org.omg.spec.bpmn.model.TDataObjectReference#getDataState()
	 * @see #getTDataObjectReference()
	 * @generated
	 */
	EReference getTDataObjectReference_DataState();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataObjectReference#getDataObjectRef <em>Data Object Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Object Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TDataObjectReference#getDataObjectRef()
	 * @see #getTDataObjectReference()
	 * @generated
	 */
	EAttribute getTDataObjectReference_DataObjectRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataObjectReference#getItemSubjectRef <em>Item Subject Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Item Subject Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TDataObjectReference#getItemSubjectRef()
	 * @see #getTDataObjectReference()
	 * @generated
	 */
	EAttribute getTDataObjectReference_ItemSubjectRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TDataOutput <em>TData Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TData Output</em>'.
	 * @see org.omg.spec.bpmn.model.TDataOutput
	 * @generated
	 */
	EClass getTDataOutput();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TDataOutput#getDataState <em>Data State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data State</em>'.
	 * @see org.omg.spec.bpmn.model.TDataOutput#getDataState()
	 * @see #getTDataOutput()
	 * @generated
	 */
	EReference getTDataOutput_DataState();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataOutput#isIsCollection <em>Is Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Collection</em>'.
	 * @see org.omg.spec.bpmn.model.TDataOutput#isIsCollection()
	 * @see #getTDataOutput()
	 * @generated
	 */
	EAttribute getTDataOutput_IsCollection();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataOutput#getItemSubjectRef <em>Item Subject Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Item Subject Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TDataOutput#getItemSubjectRef()
	 * @see #getTDataOutput()
	 * @generated
	 */
	EAttribute getTDataOutput_ItemSubjectRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataOutput#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TDataOutput#getName()
	 * @see #getTDataOutput()
	 * @generated
	 */
	EAttribute getTDataOutput_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TDataOutputAssociation <em>TData Output Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TData Output Association</em>'.
	 * @see org.omg.spec.bpmn.model.TDataOutputAssociation
	 * @generated
	 */
	EClass getTDataOutputAssociation();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TDataState <em>TData State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TData State</em>'.
	 * @see org.omg.spec.bpmn.model.TDataState
	 * @generated
	 */
	EClass getTDataState();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataState#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TDataState#getName()
	 * @see #getTDataState()
	 * @generated
	 */
	EAttribute getTDataState_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TDataStore <em>TData Store</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TData Store</em>'.
	 * @see org.omg.spec.bpmn.model.TDataStore
	 * @generated
	 */
	EClass getTDataStore();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TDataStore#getDataState <em>Data State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data State</em>'.
	 * @see org.omg.spec.bpmn.model.TDataStore#getDataState()
	 * @see #getTDataStore()
	 * @generated
	 */
	EReference getTDataStore_DataState();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataStore#getCapacity <em>Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Capacity</em>'.
	 * @see org.omg.spec.bpmn.model.TDataStore#getCapacity()
	 * @see #getTDataStore()
	 * @generated
	 */
	EAttribute getTDataStore_Capacity();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataStore#isIsUnlimited <em>Is Unlimited</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Unlimited</em>'.
	 * @see org.omg.spec.bpmn.model.TDataStore#isIsUnlimited()
	 * @see #getTDataStore()
	 * @generated
	 */
	EAttribute getTDataStore_IsUnlimited();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataStore#getItemSubjectRef <em>Item Subject Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Item Subject Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TDataStore#getItemSubjectRef()
	 * @see #getTDataStore()
	 * @generated
	 */
	EAttribute getTDataStore_ItemSubjectRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataStore#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TDataStore#getName()
	 * @see #getTDataStore()
	 * @generated
	 */
	EAttribute getTDataStore_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TDataStoreReference <em>TData Store Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TData Store Reference</em>'.
	 * @see org.omg.spec.bpmn.model.TDataStoreReference
	 * @generated
	 */
	EClass getTDataStoreReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TDataStoreReference#getDataState <em>Data State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data State</em>'.
	 * @see org.omg.spec.bpmn.model.TDataStoreReference#getDataState()
	 * @see #getTDataStoreReference()
	 * @generated
	 */
	EReference getTDataStoreReference_DataState();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataStoreReference#getDataStoreRef <em>Data Store Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Store Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TDataStoreReference#getDataStoreRef()
	 * @see #getTDataStoreReference()
	 * @generated
	 */
	EAttribute getTDataStoreReference_DataStoreRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDataStoreReference#getItemSubjectRef <em>Item Subject Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Item Subject Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TDataStoreReference#getItemSubjectRef()
	 * @see #getTDataStoreReference()
	 * @generated
	 */
	EAttribute getTDataStoreReference_ItemSubjectRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TDefinitions <em>TDefinitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TDefinitions</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions
	 * @generated
	 */
	EClass getTDefinitions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TDefinitions#getImport <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Import</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getImport()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EReference getTDefinitions_Import();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TDefinitions#getExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extension</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getExtension()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EReference getTDefinitions_Extension();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TDefinitions#getRootElementGroup <em>Root Element Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Root Element Group</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getRootElementGroup()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EAttribute getTDefinitions_RootElementGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TDefinitions#getRootElement <em>Root Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Root Element</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getRootElement()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EReference getTDefinitions_RootElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TDefinitions#getBPMNDiagram <em>BPMN Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>BPMN Diagram</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getBPMNDiagram()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EReference getTDefinitions_BPMNDiagram();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TDefinitions#getRelationship <em>Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relationship</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getRelationship()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EReference getTDefinitions_Relationship();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDefinitions#getExporter <em>Exporter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exporter</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getExporter()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EAttribute getTDefinitions_Exporter();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDefinitions#getExporterVersion <em>Exporter Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exporter Version</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getExporterVersion()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EAttribute getTDefinitions_ExporterVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDefinitions#getExpressionLanguage <em>Expression Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression Language</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getExpressionLanguage()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EAttribute getTDefinitions_ExpressionLanguage();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDefinitions#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getId()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EAttribute getTDefinitions_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDefinitions#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getName()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EAttribute getTDefinitions_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDefinitions#getTargetNamespace <em>Target Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Namespace</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getTargetNamespace()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EAttribute getTDefinitions_TargetNamespace();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDefinitions#getTypeLanguage <em>Type Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Language</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getTypeLanguage()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EAttribute getTDefinitions_TypeLanguage();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TDefinitions#getAnyAttribute <em>Any Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any Attribute</em>'.
	 * @see org.omg.spec.bpmn.model.TDefinitions#getAnyAttribute()
	 * @see #getTDefinitions()
	 * @generated
	 */
	EAttribute getTDefinitions_AnyAttribute();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TDocumentation <em>TDocumentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TDocumentation</em>'.
	 * @see org.omg.spec.bpmn.model.TDocumentation
	 * @generated
	 */
	EClass getTDocumentation();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TDocumentation#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.omg.spec.bpmn.model.TDocumentation#getMixed()
	 * @see #getTDocumentation()
	 * @generated
	 */
	EAttribute getTDocumentation_Mixed();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TDocumentation#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.omg.spec.bpmn.model.TDocumentation#getAny()
	 * @see #getTDocumentation()
	 * @generated
	 */
	EAttribute getTDocumentation_Any();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDocumentation#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.omg.spec.bpmn.model.TDocumentation#getId()
	 * @see #getTDocumentation()
	 * @generated
	 */
	EAttribute getTDocumentation_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TDocumentation#getTextFormat <em>Text Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Format</em>'.
	 * @see org.omg.spec.bpmn.model.TDocumentation#getTextFormat()
	 * @see #getTDocumentation()
	 * @generated
	 */
	EAttribute getTDocumentation_TextFormat();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TEndEvent <em>TEnd Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TEnd Event</em>'.
	 * @see org.omg.spec.bpmn.model.TEndEvent
	 * @generated
	 */
	EClass getTEndEvent();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TEndPoint <em>TEnd Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TEnd Point</em>'.
	 * @see org.omg.spec.bpmn.model.TEndPoint
	 * @generated
	 */
	EClass getTEndPoint();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TError <em>TError</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TError</em>'.
	 * @see org.omg.spec.bpmn.model.TError
	 * @generated
	 */
	EClass getTError();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TError#getErrorCode <em>Error Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error Code</em>'.
	 * @see org.omg.spec.bpmn.model.TError#getErrorCode()
	 * @see #getTError()
	 * @generated
	 */
	EAttribute getTError_ErrorCode();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TError#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TError#getName()
	 * @see #getTError()
	 * @generated
	 */
	EAttribute getTError_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TError#getStructureRef <em>Structure Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Structure Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TError#getStructureRef()
	 * @see #getTError()
	 * @generated
	 */
	EAttribute getTError_StructureRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TErrorEventDefinition <em>TError Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TError Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TErrorEventDefinition
	 * @generated
	 */
	EClass getTErrorEventDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TErrorEventDefinition#getErrorRef <em>Error Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TErrorEventDefinition#getErrorRef()
	 * @see #getTErrorEventDefinition()
	 * @generated
	 */
	EAttribute getTErrorEventDefinition_ErrorRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TEscalation <em>TEscalation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TEscalation</em>'.
	 * @see org.omg.spec.bpmn.model.TEscalation
	 * @generated
	 */
	EClass getTEscalation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TEscalation#getEscalationCode <em>Escalation Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Escalation Code</em>'.
	 * @see org.omg.spec.bpmn.model.TEscalation#getEscalationCode()
	 * @see #getTEscalation()
	 * @generated
	 */
	EAttribute getTEscalation_EscalationCode();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TEscalation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TEscalation#getName()
	 * @see #getTEscalation()
	 * @generated
	 */
	EAttribute getTEscalation_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TEscalation#getStructureRef <em>Structure Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Structure Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TEscalation#getStructureRef()
	 * @see #getTEscalation()
	 * @generated
	 */
	EAttribute getTEscalation_StructureRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TEscalationEventDefinition <em>TEscalation Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TEscalation Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TEscalationEventDefinition
	 * @generated
	 */
	EClass getTEscalationEventDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TEscalationEventDefinition#getEscalationRef <em>Escalation Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Escalation Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TEscalationEventDefinition#getEscalationRef()
	 * @see #getTEscalationEventDefinition()
	 * @generated
	 */
	EAttribute getTEscalationEventDefinition_EscalationRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TEvent <em>TEvent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TEvent</em>'.
	 * @see org.omg.spec.bpmn.model.TEvent
	 * @generated
	 */
	EClass getTEvent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TEvent#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see org.omg.spec.bpmn.model.TEvent#getProperty()
	 * @see #getTEvent()
	 * @generated
	 */
	EReference getTEvent_Property();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TEventBasedGateway <em>TEvent Based Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TEvent Based Gateway</em>'.
	 * @see org.omg.spec.bpmn.model.TEventBasedGateway
	 * @generated
	 */
	EClass getTEventBasedGateway();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TEventBasedGateway#getEventGatewayType <em>Event Gateway Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Event Gateway Type</em>'.
	 * @see org.omg.spec.bpmn.model.TEventBasedGateway#getEventGatewayType()
	 * @see #getTEventBasedGateway()
	 * @generated
	 */
	EAttribute getTEventBasedGateway_EventGatewayType();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TEventBasedGateway#isInstantiate <em>Instantiate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instantiate</em>'.
	 * @see org.omg.spec.bpmn.model.TEventBasedGateway#isInstantiate()
	 * @see #getTEventBasedGateway()
	 * @generated
	 */
	EAttribute getTEventBasedGateway_Instantiate();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TEventDefinition <em>TEvent Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TEvent Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TEventDefinition
	 * @generated
	 */
	EClass getTEventDefinition();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TExclusiveGateway <em>TExclusive Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TExclusive Gateway</em>'.
	 * @see org.omg.spec.bpmn.model.TExclusiveGateway
	 * @generated
	 */
	EClass getTExclusiveGateway();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TExclusiveGateway#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see org.omg.spec.bpmn.model.TExclusiveGateway#getDefault()
	 * @see #getTExclusiveGateway()
	 * @generated
	 */
	EAttribute getTExclusiveGateway_Default();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TExpression <em>TExpression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TExpression</em>'.
	 * @see org.omg.spec.bpmn.model.TExpression
	 * @generated
	 */
	EClass getTExpression();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TExtension <em>TExtension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TExtension</em>'.
	 * @see org.omg.spec.bpmn.model.TExtension
	 * @generated
	 */
	EClass getTExtension();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TExtension#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Documentation</em>'.
	 * @see org.omg.spec.bpmn.model.TExtension#getDocumentation()
	 * @see #getTExtension()
	 * @generated
	 */
	EReference getTExtension_Documentation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TExtension#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TExtension#getDefinition()
	 * @see #getTExtension()
	 * @generated
	 */
	EAttribute getTExtension_Definition();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TExtension#isMustUnderstand <em>Must Understand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Must Understand</em>'.
	 * @see org.omg.spec.bpmn.model.TExtension#isMustUnderstand()
	 * @see #getTExtension()
	 * @generated
	 */
	EAttribute getTExtension_MustUnderstand();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TExtensionElements <em>TExtension Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TExtension Elements</em>'.
	 * @see org.omg.spec.bpmn.model.TExtensionElements
	 * @generated
	 */
	EClass getTExtensionElements();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TExtensionElements#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.omg.spec.bpmn.model.TExtensionElements#getAny()
	 * @see #getTExtensionElements()
	 * @generated
	 */
	EAttribute getTExtensionElements_Any();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TFlowElement <em>TFlow Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TFlow Element</em>'.
	 * @see org.omg.spec.bpmn.model.TFlowElement
	 * @generated
	 */
	EClass getTFlowElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TFlowElement#getAuditing <em>Auditing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Auditing</em>'.
	 * @see org.omg.spec.bpmn.model.TFlowElement#getAuditing()
	 * @see #getTFlowElement()
	 * @generated
	 */
	EReference getTFlowElement_Auditing();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TFlowElement#getMonitoring <em>Monitoring</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Monitoring</em>'.
	 * @see org.omg.spec.bpmn.model.TFlowElement#getMonitoring()
	 * @see #getTFlowElement()
	 * @generated
	 */
	EReference getTFlowElement_Monitoring();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TFlowElement#getCategoryValueRef <em>Category Value Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Category Value Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TFlowElement#getCategoryValueRef()
	 * @see #getTFlowElement()
	 * @generated
	 */
	EAttribute getTFlowElement_CategoryValueRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TFlowElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TFlowElement#getName()
	 * @see #getTFlowElement()
	 * @generated
	 */
	EAttribute getTFlowElement_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TFlowNode <em>TFlow Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TFlow Node</em>'.
	 * @see org.omg.spec.bpmn.model.TFlowNode
	 * @generated
	 */
	EClass getTFlowNode();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TFlowNode#getIncoming <em>Incoming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Incoming</em>'.
	 * @see org.omg.spec.bpmn.model.TFlowNode#getIncoming()
	 * @see #getTFlowNode()
	 * @generated
	 */
	EAttribute getTFlowNode_Incoming();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TFlowNode#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Outgoing</em>'.
	 * @see org.omg.spec.bpmn.model.TFlowNode#getOutgoing()
	 * @see #getTFlowNode()
	 * @generated
	 */
	EAttribute getTFlowNode_Outgoing();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TFormalExpression <em>TFormal Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TFormal Expression</em>'.
	 * @see org.omg.spec.bpmn.model.TFormalExpression
	 * @generated
	 */
	EClass getTFormalExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TFormalExpression#getEvaluatesToTypeRef <em>Evaluates To Type Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Evaluates To Type Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TFormalExpression#getEvaluatesToTypeRef()
	 * @see #getTFormalExpression()
	 * @generated
	 */
	EAttribute getTFormalExpression_EvaluatesToTypeRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TFormalExpression#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see org.omg.spec.bpmn.model.TFormalExpression#getLanguage()
	 * @see #getTFormalExpression()
	 * @generated
	 */
	EAttribute getTFormalExpression_Language();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TGateway <em>TGateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TGateway</em>'.
	 * @see org.omg.spec.bpmn.model.TGateway
	 * @generated
	 */
	EClass getTGateway();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TGateway#getGatewayDirection <em>Gateway Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gateway Direction</em>'.
	 * @see org.omg.spec.bpmn.model.TGateway#getGatewayDirection()
	 * @see #getTGateway()
	 * @generated
	 */
	EAttribute getTGateway_GatewayDirection();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TGlobalBusinessRuleTask <em>TGlobal Business Rule Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TGlobal Business Rule Task</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalBusinessRuleTask
	 * @generated
	 */
	EClass getTGlobalBusinessRuleTask();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TGlobalBusinessRuleTask#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalBusinessRuleTask#getImplementation()
	 * @see #getTGlobalBusinessRuleTask()
	 * @generated
	 */
	EAttribute getTGlobalBusinessRuleTask_Implementation();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TGlobalChoreographyTask <em>TGlobal Choreography Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TGlobal Choreography Task</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalChoreographyTask
	 * @generated
	 */
	EClass getTGlobalChoreographyTask();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TGlobalChoreographyTask#getInitiatingParticipantRef <em>Initiating Participant Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initiating Participant Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalChoreographyTask#getInitiatingParticipantRef()
	 * @see #getTGlobalChoreographyTask()
	 * @generated
	 */
	EAttribute getTGlobalChoreographyTask_InitiatingParticipantRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TGlobalConversation <em>TGlobal Conversation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TGlobal Conversation</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalConversation
	 * @generated
	 */
	EClass getTGlobalConversation();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TGlobalManualTask <em>TGlobal Manual Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TGlobal Manual Task</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalManualTask
	 * @generated
	 */
	EClass getTGlobalManualTask();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TGlobalScriptTask <em>TGlobal Script Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TGlobal Script Task</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalScriptTask
	 * @generated
	 */
	EClass getTGlobalScriptTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TGlobalScriptTask#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalScriptTask#getScript()
	 * @see #getTGlobalScriptTask()
	 * @generated
	 */
	EReference getTGlobalScriptTask_Script();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TGlobalScriptTask#getScriptLanguage <em>Script Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Script Language</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalScriptTask#getScriptLanguage()
	 * @see #getTGlobalScriptTask()
	 * @generated
	 */
	EAttribute getTGlobalScriptTask_ScriptLanguage();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TGlobalTask <em>TGlobal Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TGlobal Task</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalTask
	 * @generated
	 */
	EClass getTGlobalTask();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TGlobalTask#getResourceRoleGroup <em>Resource Role Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Resource Role Group</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalTask#getResourceRoleGroup()
	 * @see #getTGlobalTask()
	 * @generated
	 */
	EAttribute getTGlobalTask_ResourceRoleGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TGlobalTask#getResourceRole <em>Resource Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Role</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalTask#getResourceRole()
	 * @see #getTGlobalTask()
	 * @generated
	 */
	EReference getTGlobalTask_ResourceRole();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TGlobalUserTask <em>TGlobal User Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TGlobal User Task</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalUserTask
	 * @generated
	 */
	EClass getTGlobalUserTask();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TGlobalUserTask#getRendering <em>Rendering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rendering</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalUserTask#getRendering()
	 * @see #getTGlobalUserTask()
	 * @generated
	 */
	EReference getTGlobalUserTask_Rendering();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TGlobalUserTask#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see org.omg.spec.bpmn.model.TGlobalUserTask#getImplementation()
	 * @see #getTGlobalUserTask()
	 * @generated
	 */
	EAttribute getTGlobalUserTask_Implementation();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TGroup <em>TGroup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TGroup</em>'.
	 * @see org.omg.spec.bpmn.model.TGroup
	 * @generated
	 */
	EClass getTGroup();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TGroup#getCategoryValueRef <em>Category Value Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Category Value Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TGroup#getCategoryValueRef()
	 * @see #getTGroup()
	 * @generated
	 */
	EAttribute getTGroup_CategoryValueRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.THumanPerformer <em>THuman Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>THuman Performer</em>'.
	 * @see org.omg.spec.bpmn.model.THumanPerformer
	 * @generated
	 */
	EClass getTHumanPerformer();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TImplicitThrowEvent <em>TImplicit Throw Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TImplicit Throw Event</em>'.
	 * @see org.omg.spec.bpmn.model.TImplicitThrowEvent
	 * @generated
	 */
	EClass getTImplicitThrowEvent();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TImport <em>TImport</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TImport</em>'.
	 * @see org.omg.spec.bpmn.model.TImport
	 * @generated
	 */
	EClass getTImport();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TImport#getImportType <em>Import Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Import Type</em>'.
	 * @see org.omg.spec.bpmn.model.TImport#getImportType()
	 * @see #getTImport()
	 * @generated
	 */
	EAttribute getTImport_ImportType();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TImport#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.omg.spec.bpmn.model.TImport#getLocation()
	 * @see #getTImport()
	 * @generated
	 */
	EAttribute getTImport_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TImport#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Namespace</em>'.
	 * @see org.omg.spec.bpmn.model.TImport#getNamespace()
	 * @see #getTImport()
	 * @generated
	 */
	EAttribute getTImport_Namespace();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TInclusiveGateway <em>TInclusive Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TInclusive Gateway</em>'.
	 * @see org.omg.spec.bpmn.model.TInclusiveGateway
	 * @generated
	 */
	EClass getTInclusiveGateway();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TInclusiveGateway#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see org.omg.spec.bpmn.model.TInclusiveGateway#getDefault()
	 * @see #getTInclusiveGateway()
	 * @generated
	 */
	EAttribute getTInclusiveGateway_Default();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TInputOutputBinding <em>TInput Output Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TInput Output Binding</em>'.
	 * @see org.omg.spec.bpmn.model.TInputOutputBinding
	 * @generated
	 */
	EClass getTInputOutputBinding();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TInputOutputBinding#getInputDataRef <em>Input Data Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Input Data Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TInputOutputBinding#getInputDataRef()
	 * @see #getTInputOutputBinding()
	 * @generated
	 */
	EAttribute getTInputOutputBinding_InputDataRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TInputOutputBinding#getOperationRef <em>Operation Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TInputOutputBinding#getOperationRef()
	 * @see #getTInputOutputBinding()
	 * @generated
	 */
	EAttribute getTInputOutputBinding_OperationRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TInputOutputBinding#getOutputDataRef <em>Output Data Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Output Data Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TInputOutputBinding#getOutputDataRef()
	 * @see #getTInputOutputBinding()
	 * @generated
	 */
	EAttribute getTInputOutputBinding_OutputDataRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TInputOutputSpecification <em>TInput Output Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TInput Output Specification</em>'.
	 * @see org.omg.spec.bpmn.model.TInputOutputSpecification
	 * @generated
	 */
	EClass getTInputOutputSpecification();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TInputOutputSpecification#getDataInput <em>Data Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Input</em>'.
	 * @see org.omg.spec.bpmn.model.TInputOutputSpecification#getDataInput()
	 * @see #getTInputOutputSpecification()
	 * @generated
	 */
	EReference getTInputOutputSpecification_DataInput();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TInputOutputSpecification#getDataOutput <em>Data Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Output</em>'.
	 * @see org.omg.spec.bpmn.model.TInputOutputSpecification#getDataOutput()
	 * @see #getTInputOutputSpecification()
	 * @generated
	 */
	EReference getTInputOutputSpecification_DataOutput();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TInputOutputSpecification#getInputSet <em>Input Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Input Set</em>'.
	 * @see org.omg.spec.bpmn.model.TInputOutputSpecification#getInputSet()
	 * @see #getTInputOutputSpecification()
	 * @generated
	 */
	EReference getTInputOutputSpecification_InputSet();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TInputOutputSpecification#getOutputSet <em>Output Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Output Set</em>'.
	 * @see org.omg.spec.bpmn.model.TInputOutputSpecification#getOutputSet()
	 * @see #getTInputOutputSpecification()
	 * @generated
	 */
	EReference getTInputOutputSpecification_OutputSet();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TInputSet <em>TInput Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TInput Set</em>'.
	 * @see org.omg.spec.bpmn.model.TInputSet
	 * @generated
	 */
	EClass getTInputSet();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TInputSet#getDataInputRefs <em>Data Input Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Data Input Refs</em>'.
	 * @see org.omg.spec.bpmn.model.TInputSet#getDataInputRefs()
	 * @see #getTInputSet()
	 * @generated
	 */
	EAttribute getTInputSet_DataInputRefs();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TInputSet#getOptionalInputRefs <em>Optional Input Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Optional Input Refs</em>'.
	 * @see org.omg.spec.bpmn.model.TInputSet#getOptionalInputRefs()
	 * @see #getTInputSet()
	 * @generated
	 */
	EAttribute getTInputSet_OptionalInputRefs();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TInputSet#getWhileExecutingInputRefs <em>While Executing Input Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>While Executing Input Refs</em>'.
	 * @see org.omg.spec.bpmn.model.TInputSet#getWhileExecutingInputRefs()
	 * @see #getTInputSet()
	 * @generated
	 */
	EAttribute getTInputSet_WhileExecutingInputRefs();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TInputSet#getOutputSetRefs <em>Output Set Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Output Set Refs</em>'.
	 * @see org.omg.spec.bpmn.model.TInputSet#getOutputSetRefs()
	 * @see #getTInputSet()
	 * @generated
	 */
	EAttribute getTInputSet_OutputSetRefs();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TInputSet#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TInputSet#getName()
	 * @see #getTInputSet()
	 * @generated
	 */
	EAttribute getTInputSet_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TInterface <em>TInterface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TInterface</em>'.
	 * @see org.omg.spec.bpmn.model.TInterface
	 * @generated
	 */
	EClass getTInterface();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TInterface#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operation</em>'.
	 * @see org.omg.spec.bpmn.model.TInterface#getOperation()
	 * @see #getTInterface()
	 * @generated
	 */
	EReference getTInterface_Operation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TInterface#getImplementationRef <em>Implementation Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TInterface#getImplementationRef()
	 * @see #getTInterface()
	 * @generated
	 */
	EAttribute getTInterface_ImplementationRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TInterface#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TInterface#getName()
	 * @see #getTInterface()
	 * @generated
	 */
	EAttribute getTInterface_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TIntermediateCatchEvent <em>TIntermediate Catch Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TIntermediate Catch Event</em>'.
	 * @see org.omg.spec.bpmn.model.TIntermediateCatchEvent
	 * @generated
	 */
	EClass getTIntermediateCatchEvent();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TIntermediateThrowEvent <em>TIntermediate Throw Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TIntermediate Throw Event</em>'.
	 * @see org.omg.spec.bpmn.model.TIntermediateThrowEvent
	 * @generated
	 */
	EClass getTIntermediateThrowEvent();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TItemDefinition <em>TItem Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TItem Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TItemDefinition
	 * @generated
	 */
	EClass getTItemDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TItemDefinition#isIsCollection <em>Is Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Collection</em>'.
	 * @see org.omg.spec.bpmn.model.TItemDefinition#isIsCollection()
	 * @see #getTItemDefinition()
	 * @generated
	 */
	EAttribute getTItemDefinition_IsCollection();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TItemDefinition#getItemKind <em>Item Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Item Kind</em>'.
	 * @see org.omg.spec.bpmn.model.TItemDefinition#getItemKind()
	 * @see #getTItemDefinition()
	 * @generated
	 */
	EAttribute getTItemDefinition_ItemKind();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TItemDefinition#getStructureRef <em>Structure Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Structure Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TItemDefinition#getStructureRef()
	 * @see #getTItemDefinition()
	 * @generated
	 */
	EAttribute getTItemDefinition_StructureRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TLane <em>TLane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TLane</em>'.
	 * @see org.omg.spec.bpmn.model.TLane
	 * @generated
	 */
	EClass getTLane();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TLane#getPartitionElement <em>Partition Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Partition Element</em>'.
	 * @see org.omg.spec.bpmn.model.TLane#getPartitionElement()
	 * @see #getTLane()
	 * @generated
	 */
	EReference getTLane_PartitionElement();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TLane#getFlowNodeRef <em>Flow Node Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Flow Node Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TLane#getFlowNodeRef()
	 * @see #getTLane()
	 * @generated
	 */
	EAttribute getTLane_FlowNodeRef();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TLane#getChildLaneSet <em>Child Lane Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Child Lane Set</em>'.
	 * @see org.omg.spec.bpmn.model.TLane#getChildLaneSet()
	 * @see #getTLane()
	 * @generated
	 */
	EReference getTLane_ChildLaneSet();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TLane#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TLane#getName()
	 * @see #getTLane()
	 * @generated
	 */
	EAttribute getTLane_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TLane#getPartitionElementRef <em>Partition Element Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partition Element Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TLane#getPartitionElementRef()
	 * @see #getTLane()
	 * @generated
	 */
	EAttribute getTLane_PartitionElementRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TLaneSet <em>TLane Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TLane Set</em>'.
	 * @see org.omg.spec.bpmn.model.TLaneSet
	 * @generated
	 */
	EClass getTLaneSet();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TLaneSet#getLane <em>Lane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Lane</em>'.
	 * @see org.omg.spec.bpmn.model.TLaneSet#getLane()
	 * @see #getTLaneSet()
	 * @generated
	 */
	EReference getTLaneSet_Lane();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TLaneSet#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TLaneSet#getName()
	 * @see #getTLaneSet()
	 * @generated
	 */
	EAttribute getTLaneSet_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TLinkEventDefinition <em>TLink Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TLink Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TLinkEventDefinition
	 * @generated
	 */
	EClass getTLinkEventDefinition();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TLinkEventDefinition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Source</em>'.
	 * @see org.omg.spec.bpmn.model.TLinkEventDefinition#getSource()
	 * @see #getTLinkEventDefinition()
	 * @generated
	 */
	EAttribute getTLinkEventDefinition_Source();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TLinkEventDefinition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target</em>'.
	 * @see org.omg.spec.bpmn.model.TLinkEventDefinition#getTarget()
	 * @see #getTLinkEventDefinition()
	 * @generated
	 */
	EAttribute getTLinkEventDefinition_Target();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TLinkEventDefinition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TLinkEventDefinition#getName()
	 * @see #getTLinkEventDefinition()
	 * @generated
	 */
	EAttribute getTLinkEventDefinition_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TLoopCharacteristics <em>TLoop Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TLoop Characteristics</em>'.
	 * @see org.omg.spec.bpmn.model.TLoopCharacteristics
	 * @generated
	 */
	EClass getTLoopCharacteristics();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TManualTask <em>TManual Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TManual Task</em>'.
	 * @see org.omg.spec.bpmn.model.TManualTask
	 * @generated
	 */
	EClass getTManualTask();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TMessage <em>TMessage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TMessage</em>'.
	 * @see org.omg.spec.bpmn.model.TMessage
	 * @generated
	 */
	EClass getTMessage();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMessage#getItemRef <em>Item Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Item Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TMessage#getItemRef()
	 * @see #getTMessage()
	 * @generated
	 */
	EAttribute getTMessage_ItemRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMessage#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TMessage#getName()
	 * @see #getTMessage()
	 * @generated
	 */
	EAttribute getTMessage_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TMessageEventDefinition <em>TMessage Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TMessage Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TMessageEventDefinition
	 * @generated
	 */
	EClass getTMessageEventDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMessageEventDefinition#getOperationRef <em>Operation Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TMessageEventDefinition#getOperationRef()
	 * @see #getTMessageEventDefinition()
	 * @generated
	 */
	EAttribute getTMessageEventDefinition_OperationRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMessageEventDefinition#getMessageRef <em>Message Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TMessageEventDefinition#getMessageRef()
	 * @see #getTMessageEventDefinition()
	 * @generated
	 */
	EAttribute getTMessageEventDefinition_MessageRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TMessageFlow <em>TMessage Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TMessage Flow</em>'.
	 * @see org.omg.spec.bpmn.model.TMessageFlow
	 * @generated
	 */
	EClass getTMessageFlow();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMessageFlow#getMessageRef <em>Message Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TMessageFlow#getMessageRef()
	 * @see #getTMessageFlow()
	 * @generated
	 */
	EAttribute getTMessageFlow_MessageRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMessageFlow#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TMessageFlow#getName()
	 * @see #getTMessageFlow()
	 * @generated
	 */
	EAttribute getTMessageFlow_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMessageFlow#getSourceRef <em>Source Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TMessageFlow#getSourceRef()
	 * @see #getTMessageFlow()
	 * @generated
	 */
	EAttribute getTMessageFlow_SourceRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMessageFlow#getTargetRef <em>Target Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TMessageFlow#getTargetRef()
	 * @see #getTMessageFlow()
	 * @generated
	 */
	EAttribute getTMessageFlow_TargetRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TMessageFlowAssociation <em>TMessage Flow Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TMessage Flow Association</em>'.
	 * @see org.omg.spec.bpmn.model.TMessageFlowAssociation
	 * @generated
	 */
	EClass getTMessageFlowAssociation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMessageFlowAssociation#getInnerMessageFlowRef <em>Inner Message Flow Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inner Message Flow Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TMessageFlowAssociation#getInnerMessageFlowRef()
	 * @see #getTMessageFlowAssociation()
	 * @generated
	 */
	EAttribute getTMessageFlowAssociation_InnerMessageFlowRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMessageFlowAssociation#getOuterMessageFlowRef <em>Outer Message Flow Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outer Message Flow Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TMessageFlowAssociation#getOuterMessageFlowRef()
	 * @see #getTMessageFlowAssociation()
	 * @generated
	 */
	EAttribute getTMessageFlowAssociation_OuterMessageFlowRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TMonitoring <em>TMonitoring</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TMonitoring</em>'.
	 * @see org.omg.spec.bpmn.model.TMonitoring
	 * @generated
	 */
	EClass getTMonitoring();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics <em>TMulti Instance Loop Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TMulti Instance Loop Characteristics</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics
	 * @generated
	 */
	EClass getTMultiInstanceLoopCharacteristics();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getLoopCardinality <em>Loop Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Loop Cardinality</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getLoopCardinality()
	 * @see #getTMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	EReference getTMultiInstanceLoopCharacteristics_LoopCardinality();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getLoopDataInputRef <em>Loop Data Input Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loop Data Input Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getLoopDataInputRef()
	 * @see #getTMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	EAttribute getTMultiInstanceLoopCharacteristics_LoopDataInputRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getLoopDataOutputRef <em>Loop Data Output Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loop Data Output Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getLoopDataOutputRef()
	 * @see #getTMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	EAttribute getTMultiInstanceLoopCharacteristics_LoopDataOutputRef();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getInputDataItem <em>Input Data Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Input Data Item</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getInputDataItem()
	 * @see #getTMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	EReference getTMultiInstanceLoopCharacteristics_InputDataItem();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getOutputDataItem <em>Output Data Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Output Data Item</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getOutputDataItem()
	 * @see #getTMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	EReference getTMultiInstanceLoopCharacteristics_OutputDataItem();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getComplexBehaviorDefinition <em>Complex Behavior Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Complex Behavior Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getComplexBehaviorDefinition()
	 * @see #getTMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	EReference getTMultiInstanceLoopCharacteristics_ComplexBehaviorDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getCompletionCondition <em>Completion Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Completion Condition</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getCompletionCondition()
	 * @see #getTMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	EReference getTMultiInstanceLoopCharacteristics_CompletionCondition();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getBehavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Behavior</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getBehavior()
	 * @see #getTMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	EAttribute getTMultiInstanceLoopCharacteristics_Behavior();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#isIsSequential <em>Is Sequential</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Sequential</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#isIsSequential()
	 * @see #getTMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	EAttribute getTMultiInstanceLoopCharacteristics_IsSequential();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getNoneBehaviorEventRef <em>None Behavior Event Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>None Behavior Event Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getNoneBehaviorEventRef()
	 * @see #getTMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	EAttribute getTMultiInstanceLoopCharacteristics_NoneBehaviorEventRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getOneBehaviorEventRef <em>One Behavior Event Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>One Behavior Event Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getOneBehaviorEventRef()
	 * @see #getTMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	EAttribute getTMultiInstanceLoopCharacteristics_OneBehaviorEventRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TOperation <em>TOperation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TOperation</em>'.
	 * @see org.omg.spec.bpmn.model.TOperation
	 * @generated
	 */
	EClass getTOperation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TOperation#getInMessageRef <em>In Message Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>In Message Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TOperation#getInMessageRef()
	 * @see #getTOperation()
	 * @generated
	 */
	EAttribute getTOperation_InMessageRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TOperation#getOutMessageRef <em>Out Message Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Out Message Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TOperation#getOutMessageRef()
	 * @see #getTOperation()
	 * @generated
	 */
	EAttribute getTOperation_OutMessageRef();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TOperation#getErrorRef <em>Error Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Error Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TOperation#getErrorRef()
	 * @see #getTOperation()
	 * @generated
	 */
	EAttribute getTOperation_ErrorRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TOperation#getImplementationRef <em>Implementation Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TOperation#getImplementationRef()
	 * @see #getTOperation()
	 * @generated
	 */
	EAttribute getTOperation_ImplementationRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TOperation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TOperation#getName()
	 * @see #getTOperation()
	 * @generated
	 */
	EAttribute getTOperation_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TOutputSet <em>TOutput Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TOutput Set</em>'.
	 * @see org.omg.spec.bpmn.model.TOutputSet
	 * @generated
	 */
	EClass getTOutputSet();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TOutputSet#getDataOutputRefs <em>Data Output Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Data Output Refs</em>'.
	 * @see org.omg.spec.bpmn.model.TOutputSet#getDataOutputRefs()
	 * @see #getTOutputSet()
	 * @generated
	 */
	EAttribute getTOutputSet_DataOutputRefs();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TOutputSet#getOptionalOutputRefs <em>Optional Output Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Optional Output Refs</em>'.
	 * @see org.omg.spec.bpmn.model.TOutputSet#getOptionalOutputRefs()
	 * @see #getTOutputSet()
	 * @generated
	 */
	EAttribute getTOutputSet_OptionalOutputRefs();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TOutputSet#getWhileExecutingOutputRefs <em>While Executing Output Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>While Executing Output Refs</em>'.
	 * @see org.omg.spec.bpmn.model.TOutputSet#getWhileExecutingOutputRefs()
	 * @see #getTOutputSet()
	 * @generated
	 */
	EAttribute getTOutputSet_WhileExecutingOutputRefs();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TOutputSet#getInputSetRefs <em>Input Set Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Input Set Refs</em>'.
	 * @see org.omg.spec.bpmn.model.TOutputSet#getInputSetRefs()
	 * @see #getTOutputSet()
	 * @generated
	 */
	EAttribute getTOutputSet_InputSetRefs();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TOutputSet#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TOutputSet#getName()
	 * @see #getTOutputSet()
	 * @generated
	 */
	EAttribute getTOutputSet_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TParallelGateway <em>TParallel Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TParallel Gateway</em>'.
	 * @see org.omg.spec.bpmn.model.TParallelGateway
	 * @generated
	 */
	EClass getTParallelGateway();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TParticipant <em>TParticipant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TParticipant</em>'.
	 * @see org.omg.spec.bpmn.model.TParticipant
	 * @generated
	 */
	EClass getTParticipant();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TParticipant#getInterfaceRef <em>Interface Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Interface Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TParticipant#getInterfaceRef()
	 * @see #getTParticipant()
	 * @generated
	 */
	EAttribute getTParticipant_InterfaceRef();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TParticipant#getEndPointRef <em>End Point Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>End Point Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TParticipant#getEndPointRef()
	 * @see #getTParticipant()
	 * @generated
	 */
	EAttribute getTParticipant_EndPointRef();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TParticipant#getParticipantMultiplicity <em>Participant Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Participant Multiplicity</em>'.
	 * @see org.omg.spec.bpmn.model.TParticipant#getParticipantMultiplicity()
	 * @see #getTParticipant()
	 * @generated
	 */
	EReference getTParticipant_ParticipantMultiplicity();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TParticipant#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TParticipant#getName()
	 * @see #getTParticipant()
	 * @generated
	 */
	EAttribute getTParticipant_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TParticipant#getProcessRef <em>Process Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Process Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TParticipant#getProcessRef()
	 * @see #getTParticipant()
	 * @generated
	 */
	EAttribute getTParticipant_ProcessRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TParticipantAssociation <em>TParticipant Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TParticipant Association</em>'.
	 * @see org.omg.spec.bpmn.model.TParticipantAssociation
	 * @generated
	 */
	EClass getTParticipantAssociation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TParticipantAssociation#getInnerParticipantRef <em>Inner Participant Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inner Participant Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TParticipantAssociation#getInnerParticipantRef()
	 * @see #getTParticipantAssociation()
	 * @generated
	 */
	EAttribute getTParticipantAssociation_InnerParticipantRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TParticipantAssociation#getOuterParticipantRef <em>Outer Participant Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outer Participant Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TParticipantAssociation#getOuterParticipantRef()
	 * @see #getTParticipantAssociation()
	 * @generated
	 */
	EAttribute getTParticipantAssociation_OuterParticipantRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TParticipantMultiplicity <em>TParticipant Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TParticipant Multiplicity</em>'.
	 * @see org.omg.spec.bpmn.model.TParticipantMultiplicity
	 * @generated
	 */
	EClass getTParticipantMultiplicity();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TParticipantMultiplicity#getMaximum <em>Maximum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum</em>'.
	 * @see org.omg.spec.bpmn.model.TParticipantMultiplicity#getMaximum()
	 * @see #getTParticipantMultiplicity()
	 * @generated
	 */
	EAttribute getTParticipantMultiplicity_Maximum();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TParticipantMultiplicity#getMinimum <em>Minimum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minimum</em>'.
	 * @see org.omg.spec.bpmn.model.TParticipantMultiplicity#getMinimum()
	 * @see #getTParticipantMultiplicity()
	 * @generated
	 */
	EAttribute getTParticipantMultiplicity_Minimum();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TPartnerEntity <em>TPartner Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TPartner Entity</em>'.
	 * @see org.omg.spec.bpmn.model.TPartnerEntity
	 * @generated
	 */
	EClass getTPartnerEntity();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TPartnerEntity#getParticipantRef <em>Participant Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Participant Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TPartnerEntity#getParticipantRef()
	 * @see #getTPartnerEntity()
	 * @generated
	 */
	EAttribute getTPartnerEntity_ParticipantRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TPartnerEntity#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TPartnerEntity#getName()
	 * @see #getTPartnerEntity()
	 * @generated
	 */
	EAttribute getTPartnerEntity_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TPartnerRole <em>TPartner Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TPartner Role</em>'.
	 * @see org.omg.spec.bpmn.model.TPartnerRole
	 * @generated
	 */
	EClass getTPartnerRole();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TPartnerRole#getParticipantRef <em>Participant Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Participant Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TPartnerRole#getParticipantRef()
	 * @see #getTPartnerRole()
	 * @generated
	 */
	EAttribute getTPartnerRole_ParticipantRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TPartnerRole#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TPartnerRole#getName()
	 * @see #getTPartnerRole()
	 * @generated
	 */
	EAttribute getTPartnerRole_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TPerformer <em>TPerformer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TPerformer</em>'.
	 * @see org.omg.spec.bpmn.model.TPerformer
	 * @generated
	 */
	EClass getTPerformer();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TPotentialOwner <em>TPotential Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TPotential Owner</em>'.
	 * @see org.omg.spec.bpmn.model.TPotentialOwner
	 * @generated
	 */
	EClass getTPotentialOwner();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TProcess <em>TProcess</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TProcess</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess
	 * @generated
	 */
	EClass getTProcess();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TProcess#getAuditing <em>Auditing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Auditing</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getAuditing()
	 * @see #getTProcess()
	 * @generated
	 */
	EReference getTProcess_Auditing();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TProcess#getMonitoring <em>Monitoring</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Monitoring</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getMonitoring()
	 * @see #getTProcess()
	 * @generated
	 */
	EReference getTProcess_Monitoring();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TProcess#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getProperty()
	 * @see #getTProcess()
	 * @generated
	 */
	EReference getTProcess_Property();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TProcess#getLaneSet <em>Lane Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Lane Set</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getLaneSet()
	 * @see #getTProcess()
	 * @generated
	 */
	EReference getTProcess_LaneSet();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TProcess#getFlowElementGroup <em>Flow Element Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Flow Element Group</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getFlowElementGroup()
	 * @see #getTProcess()
	 * @generated
	 */
	EAttribute getTProcess_FlowElementGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TProcess#getFlowElement <em>Flow Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Flow Element</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getFlowElement()
	 * @see #getTProcess()
	 * @generated
	 */
	EReference getTProcess_FlowElement();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TProcess#getArtifactGroup <em>Artifact Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Artifact Group</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getArtifactGroup()
	 * @see #getTProcess()
	 * @generated
	 */
	EAttribute getTProcess_ArtifactGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TProcess#getArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Artifact</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getArtifact()
	 * @see #getTProcess()
	 * @generated
	 */
	EReference getTProcess_Artifact();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TProcess#getResourceRoleGroup <em>Resource Role Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Resource Role Group</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getResourceRoleGroup()
	 * @see #getTProcess()
	 * @generated
	 */
	EAttribute getTProcess_ResourceRoleGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TProcess#getResourceRole <em>Resource Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Role</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getResourceRole()
	 * @see #getTProcess()
	 * @generated
	 */
	EReference getTProcess_ResourceRole();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TProcess#getCorrelationSubscription <em>Correlation Subscription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Correlation Subscription</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getCorrelationSubscription()
	 * @see #getTProcess()
	 * @generated
	 */
	EReference getTProcess_CorrelationSubscription();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TProcess#getSupports <em>Supports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Supports</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getSupports()
	 * @see #getTProcess()
	 * @generated
	 */
	EAttribute getTProcess_Supports();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TProcess#getDefinitionalCollaborationRef <em>Definitional Collaboration Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definitional Collaboration Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getDefinitionalCollaborationRef()
	 * @see #getTProcess()
	 * @generated
	 */
	EAttribute getTProcess_DefinitionalCollaborationRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TProcess#isIsClosed <em>Is Closed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Closed</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#isIsClosed()
	 * @see #getTProcess()
	 * @generated
	 */
	EAttribute getTProcess_IsClosed();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TProcess#isIsExecutable <em>Is Executable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Executable</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#isIsExecutable()
	 * @see #getTProcess()
	 * @generated
	 */
	EAttribute getTProcess_IsExecutable();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TProcess#getProcessType <em>Process Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Process Type</em>'.
	 * @see org.omg.spec.bpmn.model.TProcess#getProcessType()
	 * @see #getTProcess()
	 * @generated
	 */
	EAttribute getTProcess_ProcessType();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TProperty <em>TProperty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TProperty</em>'.
	 * @see org.omg.spec.bpmn.model.TProperty
	 * @generated
	 */
	EClass getTProperty();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TProperty#getDataState <em>Data State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data State</em>'.
	 * @see org.omg.spec.bpmn.model.TProperty#getDataState()
	 * @see #getTProperty()
	 * @generated
	 */
	EReference getTProperty_DataState();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TProperty#getItemSubjectRef <em>Item Subject Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Item Subject Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TProperty#getItemSubjectRef()
	 * @see #getTProperty()
	 * @generated
	 */
	EAttribute getTProperty_ItemSubjectRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TProperty#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TProperty#getName()
	 * @see #getTProperty()
	 * @generated
	 */
	EAttribute getTProperty_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TReceiveTask <em>TReceive Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TReceive Task</em>'.
	 * @see org.omg.spec.bpmn.model.TReceiveTask
	 * @generated
	 */
	EClass getTReceiveTask();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TReceiveTask#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see org.omg.spec.bpmn.model.TReceiveTask#getImplementation()
	 * @see #getTReceiveTask()
	 * @generated
	 */
	EAttribute getTReceiveTask_Implementation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TReceiveTask#isInstantiate <em>Instantiate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instantiate</em>'.
	 * @see org.omg.spec.bpmn.model.TReceiveTask#isInstantiate()
	 * @see #getTReceiveTask()
	 * @generated
	 */
	EAttribute getTReceiveTask_Instantiate();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TReceiveTask#getMessageRef <em>Message Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TReceiveTask#getMessageRef()
	 * @see #getTReceiveTask()
	 * @generated
	 */
	EAttribute getTReceiveTask_MessageRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TReceiveTask#getOperationRef <em>Operation Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TReceiveTask#getOperationRef()
	 * @see #getTReceiveTask()
	 * @generated
	 */
	EAttribute getTReceiveTask_OperationRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TRelationship <em>TRelationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TRelationship</em>'.
	 * @see org.omg.spec.bpmn.model.TRelationship
	 * @generated
	 */
	EClass getTRelationship();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TRelationship#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Source</em>'.
	 * @see org.omg.spec.bpmn.model.TRelationship#getSource()
	 * @see #getTRelationship()
	 * @generated
	 */
	EAttribute getTRelationship_Source();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TRelationship#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Target</em>'.
	 * @see org.omg.spec.bpmn.model.TRelationship#getTarget()
	 * @see #getTRelationship()
	 * @generated
	 */
	EAttribute getTRelationship_Target();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TRelationship#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see org.omg.spec.bpmn.model.TRelationship#getDirection()
	 * @see #getTRelationship()
	 * @generated
	 */
	EAttribute getTRelationship_Direction();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TRelationship#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.omg.spec.bpmn.model.TRelationship#getType()
	 * @see #getTRelationship()
	 * @generated
	 */
	EAttribute getTRelationship_Type();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TRendering <em>TRendering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TRendering</em>'.
	 * @see org.omg.spec.bpmn.model.TRendering
	 * @generated
	 */
	EClass getTRendering();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TResource <em>TResource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TResource</em>'.
	 * @see org.omg.spec.bpmn.model.TResource
	 * @generated
	 */
	EClass getTResource();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TResource#getResourceParameter <em>Resource Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Parameter</em>'.
	 * @see org.omg.spec.bpmn.model.TResource#getResourceParameter()
	 * @see #getTResource()
	 * @generated
	 */
	EReference getTResource_ResourceParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TResource#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TResource#getName()
	 * @see #getTResource()
	 * @generated
	 */
	EAttribute getTResource_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TResourceAssignmentExpression <em>TResource Assignment Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TResource Assignment Expression</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceAssignmentExpression
	 * @generated
	 */
	EClass getTResourceAssignmentExpression();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TResourceAssignmentExpression#getExpressionGroup <em>Expression Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Expression Group</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceAssignmentExpression#getExpressionGroup()
	 * @see #getTResourceAssignmentExpression()
	 * @generated
	 */
	EAttribute getTResourceAssignmentExpression_ExpressionGroup();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TResourceAssignmentExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceAssignmentExpression#getExpression()
	 * @see #getTResourceAssignmentExpression()
	 * @generated
	 */
	EReference getTResourceAssignmentExpression_Expression();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TResourceParameter <em>TResource Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TResource Parameter</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceParameter
	 * @generated
	 */
	EClass getTResourceParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TResourceParameter#isIsRequired <em>Is Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Required</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceParameter#isIsRequired()
	 * @see #getTResourceParameter()
	 * @generated
	 */
	EAttribute getTResourceParameter_IsRequired();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TResourceParameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceParameter#getName()
	 * @see #getTResourceParameter()
	 * @generated
	 */
	EAttribute getTResourceParameter_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TResourceParameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceParameter#getType()
	 * @see #getTResourceParameter()
	 * @generated
	 */
	EAttribute getTResourceParameter_Type();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TResourceParameterBinding <em>TResource Parameter Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TResource Parameter Binding</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceParameterBinding
	 * @generated
	 */
	EClass getTResourceParameterBinding();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TResourceParameterBinding#getExpressionGroup <em>Expression Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Expression Group</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceParameterBinding#getExpressionGroup()
	 * @see #getTResourceParameterBinding()
	 * @generated
	 */
	EAttribute getTResourceParameterBinding_ExpressionGroup();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TResourceParameterBinding#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceParameterBinding#getExpression()
	 * @see #getTResourceParameterBinding()
	 * @generated
	 */
	EReference getTResourceParameterBinding_Expression();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TResourceParameterBinding#getParameterRef <em>Parameter Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceParameterBinding#getParameterRef()
	 * @see #getTResourceParameterBinding()
	 * @generated
	 */
	EAttribute getTResourceParameterBinding_ParameterRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TResourceRole <em>TResource Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TResource Role</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceRole
	 * @generated
	 */
	EClass getTResourceRole();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TResourceRole#getResourceRef <em>Resource Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceRole#getResourceRef()
	 * @see #getTResourceRole()
	 * @generated
	 */
	EAttribute getTResourceRole_ResourceRef();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TResourceRole#getResourceParameterBinding <em>Resource Parameter Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Parameter Binding</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceRole#getResourceParameterBinding()
	 * @see #getTResourceRole()
	 * @generated
	 */
	EReference getTResourceRole_ResourceParameterBinding();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TResourceRole#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource Assignment Expression</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceRole#getResourceAssignmentExpression()
	 * @see #getTResourceRole()
	 * @generated
	 */
	EReference getTResourceRole_ResourceAssignmentExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TResourceRole#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TResourceRole#getName()
	 * @see #getTResourceRole()
	 * @generated
	 */
	EAttribute getTResourceRole_Name();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TRootElement <em>TRoot Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TRoot Element</em>'.
	 * @see org.omg.spec.bpmn.model.TRootElement
	 * @generated
	 */
	EClass getTRootElement();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TScript <em>TScript</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TScript</em>'.
	 * @see org.omg.spec.bpmn.model.TScript
	 * @generated
	 */
	EClass getTScript();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TScript#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.omg.spec.bpmn.model.TScript#getMixed()
	 * @see #getTScript()
	 * @generated
	 */
	EAttribute getTScript_Mixed();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TScript#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.omg.spec.bpmn.model.TScript#getAny()
	 * @see #getTScript()
	 * @generated
	 */
	EAttribute getTScript_Any();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TScriptTask <em>TScript Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TScript Task</em>'.
	 * @see org.omg.spec.bpmn.model.TScriptTask
	 * @generated
	 */
	EClass getTScriptTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TScriptTask#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script</em>'.
	 * @see org.omg.spec.bpmn.model.TScriptTask#getScript()
	 * @see #getTScriptTask()
	 * @generated
	 */
	EReference getTScriptTask_Script();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TScriptTask#getScriptFormat <em>Script Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Script Format</em>'.
	 * @see org.omg.spec.bpmn.model.TScriptTask#getScriptFormat()
	 * @see #getTScriptTask()
	 * @generated
	 */
	EAttribute getTScriptTask_ScriptFormat();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TSendTask <em>TSend Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSend Task</em>'.
	 * @see org.omg.spec.bpmn.model.TSendTask
	 * @generated
	 */
	EClass getTSendTask();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TSendTask#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see org.omg.spec.bpmn.model.TSendTask#getImplementation()
	 * @see #getTSendTask()
	 * @generated
	 */
	EAttribute getTSendTask_Implementation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TSendTask#getMessageRef <em>Message Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TSendTask#getMessageRef()
	 * @see #getTSendTask()
	 * @generated
	 */
	EAttribute getTSendTask_MessageRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TSendTask#getOperationRef <em>Operation Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TSendTask#getOperationRef()
	 * @see #getTSendTask()
	 * @generated
	 */
	EAttribute getTSendTask_OperationRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TSequenceFlow <em>TSequence Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSequence Flow</em>'.
	 * @see org.omg.spec.bpmn.model.TSequenceFlow
	 * @generated
	 */
	EClass getTSequenceFlow();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TSequenceFlow#getConditionExpression <em>Condition Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition Expression</em>'.
	 * @see org.omg.spec.bpmn.model.TSequenceFlow#getConditionExpression()
	 * @see #getTSequenceFlow()
	 * @generated
	 */
	EReference getTSequenceFlow_ConditionExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TSequenceFlow#isIsImmediate <em>Is Immediate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Immediate</em>'.
	 * @see org.omg.spec.bpmn.model.TSequenceFlow#isIsImmediate()
	 * @see #getTSequenceFlow()
	 * @generated
	 */
	EAttribute getTSequenceFlow_IsImmediate();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TSequenceFlow#getSourceRef <em>Source Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TSequenceFlow#getSourceRef()
	 * @see #getTSequenceFlow()
	 * @generated
	 */
	EAttribute getTSequenceFlow_SourceRef();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TSequenceFlow#getTargetRef <em>Target Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TSequenceFlow#getTargetRef()
	 * @see #getTSequenceFlow()
	 * @generated
	 */
	EAttribute getTSequenceFlow_TargetRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TServiceTask <em>TService Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TService Task</em>'.
	 * @see org.omg.spec.bpmn.model.TServiceTask
	 * @generated
	 */
	EClass getTServiceTask();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TServiceTask#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see org.omg.spec.bpmn.model.TServiceTask#getImplementation()
	 * @see #getTServiceTask()
	 * @generated
	 */
	EAttribute getTServiceTask_Implementation();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TServiceTask#getOperationRef <em>Operation Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TServiceTask#getOperationRef()
	 * @see #getTServiceTask()
	 * @generated
	 */
	EAttribute getTServiceTask_OperationRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TSignal <em>TSignal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSignal</em>'.
	 * @see org.omg.spec.bpmn.model.TSignal
	 * @generated
	 */
	EClass getTSignal();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TSignal#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.omg.spec.bpmn.model.TSignal#getName()
	 * @see #getTSignal()
	 * @generated
	 */
	EAttribute getTSignal_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TSignal#getStructureRef <em>Structure Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Structure Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TSignal#getStructureRef()
	 * @see #getTSignal()
	 * @generated
	 */
	EAttribute getTSignal_StructureRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TSignalEventDefinition <em>TSignal Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSignal Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TSignalEventDefinition
	 * @generated
	 */
	EClass getTSignalEventDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TSignalEventDefinition#getSignalRef <em>Signal Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signal Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TSignalEventDefinition#getSignalRef()
	 * @see #getTSignalEventDefinition()
	 * @generated
	 */
	EAttribute getTSignalEventDefinition_SignalRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics <em>TStandard Loop Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TStandard Loop Characteristics</em>'.
	 * @see org.omg.spec.bpmn.model.TStandardLoopCharacteristics
	 * @generated
	 */
	EClass getTStandardLoopCharacteristics();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics#getLoopCondition <em>Loop Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Loop Condition</em>'.
	 * @see org.omg.spec.bpmn.model.TStandardLoopCharacteristics#getLoopCondition()
	 * @see #getTStandardLoopCharacteristics()
	 * @generated
	 */
	EReference getTStandardLoopCharacteristics_LoopCondition();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics#getLoopMaximum <em>Loop Maximum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loop Maximum</em>'.
	 * @see org.omg.spec.bpmn.model.TStandardLoopCharacteristics#getLoopMaximum()
	 * @see #getTStandardLoopCharacteristics()
	 * @generated
	 */
	EAttribute getTStandardLoopCharacteristics_LoopMaximum();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics#isTestBefore <em>Test Before</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Before</em>'.
	 * @see org.omg.spec.bpmn.model.TStandardLoopCharacteristics#isTestBefore()
	 * @see #getTStandardLoopCharacteristics()
	 * @generated
	 */
	EAttribute getTStandardLoopCharacteristics_TestBefore();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TStartEvent <em>TStart Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TStart Event</em>'.
	 * @see org.omg.spec.bpmn.model.TStartEvent
	 * @generated
	 */
	EClass getTStartEvent();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TStartEvent#isIsInterrupting <em>Is Interrupting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Interrupting</em>'.
	 * @see org.omg.spec.bpmn.model.TStartEvent#isIsInterrupting()
	 * @see #getTStartEvent()
	 * @generated
	 */
	EAttribute getTStartEvent_IsInterrupting();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TSubChoreography <em>TSub Choreography</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSub Choreography</em>'.
	 * @see org.omg.spec.bpmn.model.TSubChoreography
	 * @generated
	 */
	EClass getTSubChoreography();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TSubChoreography#getFlowElementGroup <em>Flow Element Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Flow Element Group</em>'.
	 * @see org.omg.spec.bpmn.model.TSubChoreography#getFlowElementGroup()
	 * @see #getTSubChoreography()
	 * @generated
	 */
	EAttribute getTSubChoreography_FlowElementGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TSubChoreography#getFlowElement <em>Flow Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Flow Element</em>'.
	 * @see org.omg.spec.bpmn.model.TSubChoreography#getFlowElement()
	 * @see #getTSubChoreography()
	 * @generated
	 */
	EReference getTSubChoreography_FlowElement();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TSubChoreography#getArtifactGroup <em>Artifact Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Artifact Group</em>'.
	 * @see org.omg.spec.bpmn.model.TSubChoreography#getArtifactGroup()
	 * @see #getTSubChoreography()
	 * @generated
	 */
	EAttribute getTSubChoreography_ArtifactGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TSubChoreography#getArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Artifact</em>'.
	 * @see org.omg.spec.bpmn.model.TSubChoreography#getArtifact()
	 * @see #getTSubChoreography()
	 * @generated
	 */
	EReference getTSubChoreography_Artifact();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TSubConversation <em>TSub Conversation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSub Conversation</em>'.
	 * @see org.omg.spec.bpmn.model.TSubConversation
	 * @generated
	 */
	EClass getTSubConversation();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TSubConversation#getConversationNodeGroup <em>Conversation Node Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Conversation Node Group</em>'.
	 * @see org.omg.spec.bpmn.model.TSubConversation#getConversationNodeGroup()
	 * @see #getTSubConversation()
	 * @generated
	 */
	EAttribute getTSubConversation_ConversationNodeGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TSubConversation#getConversationNode <em>Conversation Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conversation Node</em>'.
	 * @see org.omg.spec.bpmn.model.TSubConversation#getConversationNode()
	 * @see #getTSubConversation()
	 * @generated
	 */
	EReference getTSubConversation_ConversationNode();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TSubProcess <em>TSub Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TSub Process</em>'.
	 * @see org.omg.spec.bpmn.model.TSubProcess
	 * @generated
	 */
	EClass getTSubProcess();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TSubProcess#getLaneSet <em>Lane Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Lane Set</em>'.
	 * @see org.omg.spec.bpmn.model.TSubProcess#getLaneSet()
	 * @see #getTSubProcess()
	 * @generated
	 */
	EReference getTSubProcess_LaneSet();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TSubProcess#getFlowElementGroup <em>Flow Element Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Flow Element Group</em>'.
	 * @see org.omg.spec.bpmn.model.TSubProcess#getFlowElementGroup()
	 * @see #getTSubProcess()
	 * @generated
	 */
	EAttribute getTSubProcess_FlowElementGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TSubProcess#getFlowElement <em>Flow Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Flow Element</em>'.
	 * @see org.omg.spec.bpmn.model.TSubProcess#getFlowElement()
	 * @see #getTSubProcess()
	 * @generated
	 */
	EReference getTSubProcess_FlowElement();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TSubProcess#getArtifactGroup <em>Artifact Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Artifact Group</em>'.
	 * @see org.omg.spec.bpmn.model.TSubProcess#getArtifactGroup()
	 * @see #getTSubProcess()
	 * @generated
	 */
	EAttribute getTSubProcess_ArtifactGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TSubProcess#getArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Artifact</em>'.
	 * @see org.omg.spec.bpmn.model.TSubProcess#getArtifact()
	 * @see #getTSubProcess()
	 * @generated
	 */
	EReference getTSubProcess_Artifact();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TSubProcess#isTriggeredByEvent <em>Triggered By Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Triggered By Event</em>'.
	 * @see org.omg.spec.bpmn.model.TSubProcess#isTriggeredByEvent()
	 * @see #getTSubProcess()
	 * @generated
	 */
	EAttribute getTSubProcess_TriggeredByEvent();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TTask <em>TTask</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TTask</em>'.
	 * @see org.omg.spec.bpmn.model.TTask
	 * @generated
	 */
	EClass getTTask();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TTerminateEventDefinition <em>TTerminate Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TTerminate Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TTerminateEventDefinition
	 * @generated
	 */
	EClass getTTerminateEventDefinition();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TText <em>TText</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TText</em>'.
	 * @see org.omg.spec.bpmn.model.TText
	 * @generated
	 */
	EClass getTText();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TText#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.omg.spec.bpmn.model.TText#getMixed()
	 * @see #getTText()
	 * @generated
	 */
	EAttribute getTText_Mixed();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TText#getAny <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Any</em>'.
	 * @see org.omg.spec.bpmn.model.TText#getAny()
	 * @see #getTText()
	 * @generated
	 */
	EAttribute getTText_Any();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TTextAnnotation <em>TText Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TText Annotation</em>'.
	 * @see org.omg.spec.bpmn.model.TTextAnnotation
	 * @generated
	 */
	EClass getTTextAnnotation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TTextAnnotation#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Text</em>'.
	 * @see org.omg.spec.bpmn.model.TTextAnnotation#getText()
	 * @see #getTTextAnnotation()
	 * @generated
	 */
	EReference getTTextAnnotation_Text();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TTextAnnotation#getTextFormat <em>Text Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Format</em>'.
	 * @see org.omg.spec.bpmn.model.TTextAnnotation#getTextFormat()
	 * @see #getTTextAnnotation()
	 * @generated
	 */
	EAttribute getTTextAnnotation_TextFormat();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TThrowEvent <em>TThrow Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TThrow Event</em>'.
	 * @see org.omg.spec.bpmn.model.TThrowEvent
	 * @generated
	 */
	EClass getTThrowEvent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TThrowEvent#getDataInput <em>Data Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Input</em>'.
	 * @see org.omg.spec.bpmn.model.TThrowEvent#getDataInput()
	 * @see #getTThrowEvent()
	 * @generated
	 */
	EReference getTThrowEvent_DataInput();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TThrowEvent#getDataInputAssociation <em>Data Input Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Input Association</em>'.
	 * @see org.omg.spec.bpmn.model.TThrowEvent#getDataInputAssociation()
	 * @see #getTThrowEvent()
	 * @generated
	 */
	EReference getTThrowEvent_DataInputAssociation();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TThrowEvent#getInputSet <em>Input Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Input Set</em>'.
	 * @see org.omg.spec.bpmn.model.TThrowEvent#getInputSet()
	 * @see #getTThrowEvent()
	 * @generated
	 */
	EReference getTThrowEvent_InputSet();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TThrowEvent#getEventDefinitionGroup <em>Event Definition Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Event Definition Group</em>'.
	 * @see org.omg.spec.bpmn.model.TThrowEvent#getEventDefinitionGroup()
	 * @see #getTThrowEvent()
	 * @generated
	 */
	EAttribute getTThrowEvent_EventDefinitionGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TThrowEvent#getEventDefinition <em>Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TThrowEvent#getEventDefinition()
	 * @see #getTThrowEvent()
	 * @generated
	 */
	EReference getTThrowEvent_EventDefinition();

	/**
	 * Returns the meta object for the attribute list '{@link org.omg.spec.bpmn.model.TThrowEvent#getEventDefinitionRef <em>Event Definition Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Event Definition Ref</em>'.
	 * @see org.omg.spec.bpmn.model.TThrowEvent#getEventDefinitionRef()
	 * @see #getTThrowEvent()
	 * @generated
	 */
	EAttribute getTThrowEvent_EventDefinitionRef();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TTimerEventDefinition <em>TTimer Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TTimer Event Definition</em>'.
	 * @see org.omg.spec.bpmn.model.TTimerEventDefinition
	 * @generated
	 */
	EClass getTTimerEventDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeDate <em>Time Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Time Date</em>'.
	 * @see org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeDate()
	 * @see #getTTimerEventDefinition()
	 * @generated
	 */
	EReference getTTimerEventDefinition_TimeDate();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeDuration <em>Time Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Time Duration</em>'.
	 * @see org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeDuration()
	 * @see #getTTimerEventDefinition()
	 * @generated
	 */
	EReference getTTimerEventDefinition_TimeDuration();

	/**
	 * Returns the meta object for the containment reference '{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeCycle <em>Time Cycle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Time Cycle</em>'.
	 * @see org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeCycle()
	 * @see #getTTimerEventDefinition()
	 * @generated
	 */
	EReference getTTimerEventDefinition_TimeCycle();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TTransaction <em>TTransaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TTransaction</em>'.
	 * @see org.omg.spec.bpmn.model.TTransaction
	 * @generated
	 */
	EClass getTTransaction();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TTransaction#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method</em>'.
	 * @see org.omg.spec.bpmn.model.TTransaction#getMethod()
	 * @see #getTTransaction()
	 * @generated
	 */
	EAttribute getTTransaction_Method();

	/**
	 * Returns the meta object for class '{@link org.omg.spec.bpmn.model.TUserTask <em>TUser Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TUser Task</em>'.
	 * @see org.omg.spec.bpmn.model.TUserTask
	 * @generated
	 */
	EClass getTUserTask();

	/**
	 * Returns the meta object for the containment reference list '{@link org.omg.spec.bpmn.model.TUserTask#getRendering <em>Rendering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rendering</em>'.
	 * @see org.omg.spec.bpmn.model.TUserTask#getRendering()
	 * @see #getTUserTask()
	 * @generated
	 */
	EReference getTUserTask_Rendering();

	/**
	 * Returns the meta object for the attribute '{@link org.omg.spec.bpmn.model.TUserTask#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see org.omg.spec.bpmn.model.TUserTask#getImplementation()
	 * @see #getTUserTask()
	 * @generated
	 */
	EAttribute getTUserTask_Implementation();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.model.TAdHocOrdering <em>TAd Hoc Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TAd Hoc Ordering</em>'.
	 * @see org.omg.spec.bpmn.model.TAdHocOrdering
	 * @generated
	 */
	EEnum getTAdHocOrdering();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.model.TAssociationDirection <em>TAssociation Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TAssociation Direction</em>'.
	 * @see org.omg.spec.bpmn.model.TAssociationDirection
	 * @generated
	 */
	EEnum getTAssociationDirection();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.model.TChoreographyLoopType <em>TChoreography Loop Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TChoreography Loop Type</em>'.
	 * @see org.omg.spec.bpmn.model.TChoreographyLoopType
	 * @generated
	 */
	EEnum getTChoreographyLoopType();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.model.TEventBasedGatewayType <em>TEvent Based Gateway Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TEvent Based Gateway Type</em>'.
	 * @see org.omg.spec.bpmn.model.TEventBasedGatewayType
	 * @generated
	 */
	EEnum getTEventBasedGatewayType();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.model.TGatewayDirection <em>TGateway Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TGateway Direction</em>'.
	 * @see org.omg.spec.bpmn.model.TGatewayDirection
	 * @generated
	 */
	EEnum getTGatewayDirection();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.model.TImplementationMember1 <em>TImplementation Member1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TImplementation Member1</em>'.
	 * @see org.omg.spec.bpmn.model.TImplementationMember1
	 * @generated
	 */
	EEnum getTImplementationMember1();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.model.TItemKind <em>TItem Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TItem Kind</em>'.
	 * @see org.omg.spec.bpmn.model.TItemKind
	 * @generated
	 */
	EEnum getTItemKind();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.model.TMultiInstanceFlowCondition <em>TMulti Instance Flow Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TMulti Instance Flow Condition</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceFlowCondition
	 * @generated
	 */
	EEnum getTMultiInstanceFlowCondition();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.model.TProcessType <em>TProcess Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TProcess Type</em>'.
	 * @see org.omg.spec.bpmn.model.TProcessType
	 * @generated
	 */
	EEnum getTProcessType();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.model.TRelationshipDirection <em>TRelationship Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TRelationship Direction</em>'.
	 * @see org.omg.spec.bpmn.model.TRelationshipDirection
	 * @generated
	 */
	EEnum getTRelationshipDirection();

	/**
	 * Returns the meta object for enum '{@link org.omg.spec.bpmn.model.TTransactionMethodMember1 <em>TTransaction Method Member1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TTransaction Method Member1</em>'.
	 * @see org.omg.spec.bpmn.model.TTransactionMethodMember1
	 * @generated
	 */
	EEnum getTTransactionMethodMember1();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.model.TAdHocOrdering <em>TAd Hoc Ordering Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TAd Hoc Ordering Object</em>'.
	 * @see org.omg.spec.bpmn.model.TAdHocOrdering
	 * @model instanceClass="org.omg.spec.bpmn.model.TAdHocOrdering"
	 *        extendedMetaData="name='tAdHocOrdering:Object' baseType='tAdHocOrdering'"
	 * @generated
	 */
	EDataType getTAdHocOrderingObject();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.model.TAssociationDirection <em>TAssociation Direction Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TAssociation Direction Object</em>'.
	 * @see org.omg.spec.bpmn.model.TAssociationDirection
	 * @model instanceClass="org.omg.spec.bpmn.model.TAssociationDirection"
	 *        extendedMetaData="name='tAssociationDirection:Object' baseType='tAssociationDirection'"
	 * @generated
	 */
	EDataType getTAssociationDirectionObject();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.model.TChoreographyLoopType <em>TChoreography Loop Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TChoreography Loop Type Object</em>'.
	 * @see org.omg.spec.bpmn.model.TChoreographyLoopType
	 * @model instanceClass="org.omg.spec.bpmn.model.TChoreographyLoopType"
	 *        extendedMetaData="name='tChoreographyLoopType:Object' baseType='tChoreographyLoopType'"
	 * @generated
	 */
	EDataType getTChoreographyLoopTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.model.TEventBasedGatewayType <em>TEvent Based Gateway Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TEvent Based Gateway Type Object</em>'.
	 * @see org.omg.spec.bpmn.model.TEventBasedGatewayType
	 * @model instanceClass="org.omg.spec.bpmn.model.TEventBasedGatewayType"
	 *        extendedMetaData="name='tEventBasedGatewayType:Object' baseType='tEventBasedGatewayType'"
	 * @generated
	 */
	EDataType getTEventBasedGatewayTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.model.TGatewayDirection <em>TGateway Direction Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TGateway Direction Object</em>'.
	 * @see org.omg.spec.bpmn.model.TGatewayDirection
	 * @model instanceClass="org.omg.spec.bpmn.model.TGatewayDirection"
	 *        extendedMetaData="name='tGatewayDirection:Object' baseType='tGatewayDirection'"
	 * @generated
	 */
	EDataType getTGatewayDirectionObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object <em>TImplementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TImplementation</em>'.
	 * @see java.lang.Object
	 * @model instanceClass="java.lang.Object"
	 *        extendedMetaData="name='tImplementation' memberTypes='http://www.eclipse.org/emf/2003/XMLType#anyURI tImplementation_._member_._1'"
	 * @generated
	 */
	EDataType getTImplementation();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.model.TImplementationMember1 <em>TImplementation Member1 Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TImplementation Member1 Object</em>'.
	 * @see org.omg.spec.bpmn.model.TImplementationMember1
	 * @model instanceClass="org.omg.spec.bpmn.model.TImplementationMember1"
	 *        extendedMetaData="name='tImplementation_._member_._1:Object' baseType='tImplementation_._member_._1'"
	 * @generated
	 */
	EDataType getTImplementationMember1Object();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.model.TItemKind <em>TItem Kind Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TItem Kind Object</em>'.
	 * @see org.omg.spec.bpmn.model.TItemKind
	 * @model instanceClass="org.omg.spec.bpmn.model.TItemKind"
	 *        extendedMetaData="name='tItemKind:Object' baseType='tItemKind'"
	 * @generated
	 */
	EDataType getTItemKindObject();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.model.TMultiInstanceFlowCondition <em>TMulti Instance Flow Condition Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TMulti Instance Flow Condition Object</em>'.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceFlowCondition
	 * @model instanceClass="org.omg.spec.bpmn.model.TMultiInstanceFlowCondition"
	 *        extendedMetaData="name='tMultiInstanceFlowCondition:Object' baseType='tMultiInstanceFlowCondition'"
	 * @generated
	 */
	EDataType getTMultiInstanceFlowConditionObject();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.model.TProcessType <em>TProcess Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TProcess Type Object</em>'.
	 * @see org.omg.spec.bpmn.model.TProcessType
	 * @model instanceClass="org.omg.spec.bpmn.model.TProcessType"
	 *        extendedMetaData="name='tProcessType:Object' baseType='tProcessType'"
	 * @generated
	 */
	EDataType getTProcessTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.model.TRelationshipDirection <em>TRelationship Direction Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TRelationship Direction Object</em>'.
	 * @see org.omg.spec.bpmn.model.TRelationshipDirection
	 * @model instanceClass="org.omg.spec.bpmn.model.TRelationshipDirection"
	 *        extendedMetaData="name='tRelationshipDirection:Object' baseType='tRelationshipDirection'"
	 * @generated
	 */
	EDataType getTRelationshipDirectionObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object <em>TTransaction Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TTransaction Method</em>'.
	 * @see java.lang.Object
	 * @model instanceClass="java.lang.Object"
	 *        extendedMetaData="name='tTransactionMethod' memberTypes='http://www.eclipse.org/emf/2003/XMLType#anyURI tTransactionMethod_._member_._1'"
	 * @generated
	 */
	EDataType getTTransactionMethod();

	/**
	 * Returns the meta object for data type '{@link org.omg.spec.bpmn.model.TTransactionMethodMember1 <em>TTransaction Method Member1 Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>TTransaction Method Member1 Object</em>'.
	 * @see org.omg.spec.bpmn.model.TTransactionMethodMember1
	 * @model instanceClass="org.omg.spec.bpmn.model.TTransactionMethodMember1"
	 *        extendedMetaData="name='tTransactionMethod_._member_._1:Object' baseType='tTransactionMethod_._member_._1'"
	 * @generated
	 */
	EDataType getTTransactionMethodMember1Object();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

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
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.DocumentRootImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getDocumentRoot()
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
		 * The meta object literal for the '<em><b>Activity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ACTIVITY = eINSTANCE.getDocumentRoot_Activity();

		/**
		 * The meta object literal for the '<em><b>Ad Hoc Sub Process</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__AD_HOC_SUB_PROCESS = eINSTANCE.getDocumentRoot_AdHocSubProcess();

		/**
		 * The meta object literal for the '<em><b>Flow Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FLOW_ELEMENT = eINSTANCE.getDocumentRoot_FlowElement();

		/**
		 * The meta object literal for the '<em><b>Artifact</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ARTIFACT = eINSTANCE.getDocumentRoot_Artifact();

		/**
		 * The meta object literal for the '<em><b>Assignment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ASSIGNMENT = eINSTANCE.getDocumentRoot_Assignment();

		/**
		 * The meta object literal for the '<em><b>Association</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ASSOCIATION = eINSTANCE.getDocumentRoot_Association();

		/**
		 * The meta object literal for the '<em><b>Auditing</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__AUDITING = eINSTANCE.getDocumentRoot_Auditing();

		/**
		 * The meta object literal for the '<em><b>Base Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BASE_ELEMENT = eINSTANCE.getDocumentRoot_BaseElement();

		/**
		 * The meta object literal for the '<em><b>Base Element With Mixed Content</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BASE_ELEMENT_WITH_MIXED_CONTENT = eINSTANCE.getDocumentRoot_BaseElementWithMixedContent();

		/**
		 * The meta object literal for the '<em><b>Boundary Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BOUNDARY_EVENT = eINSTANCE.getDocumentRoot_BoundaryEvent();

		/**
		 * The meta object literal for the '<em><b>Business Rule Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BUSINESS_RULE_TASK = eINSTANCE.getDocumentRoot_BusinessRuleTask();

		/**
		 * The meta object literal for the '<em><b>Callable Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CALLABLE_ELEMENT = eINSTANCE.getDocumentRoot_CallableElement();

		/**
		 * The meta object literal for the '<em><b>Call Activity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CALL_ACTIVITY = eINSTANCE.getDocumentRoot_CallActivity();

		/**
		 * The meta object literal for the '<em><b>Call Choreography</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CALL_CHOREOGRAPHY = eINSTANCE.getDocumentRoot_CallChoreography();

		/**
		 * The meta object literal for the '<em><b>Call Conversation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CALL_CONVERSATION = eINSTANCE.getDocumentRoot_CallConversation();

		/**
		 * The meta object literal for the '<em><b>Conversation Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONVERSATION_NODE = eINSTANCE.getDocumentRoot_ConversationNode();

		/**
		 * The meta object literal for the '<em><b>Cancel Event Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CANCEL_EVENT_DEFINITION = eINSTANCE.getDocumentRoot_CancelEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Event Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EVENT_DEFINITION = eINSTANCE.getDocumentRoot_EventDefinition();

		/**
		 * The meta object literal for the '<em><b>Root Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ROOT_ELEMENT = eINSTANCE.getDocumentRoot_RootElement();

		/**
		 * The meta object literal for the '<em><b>Catch Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CATCH_EVENT = eINSTANCE.getDocumentRoot_CatchEvent();

		/**
		 * The meta object literal for the '<em><b>Category</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CATEGORY = eINSTANCE.getDocumentRoot_Category();

		/**
		 * The meta object literal for the '<em><b>Category Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CATEGORY_VALUE = eINSTANCE.getDocumentRoot_CategoryValue();

		/**
		 * The meta object literal for the '<em><b>Choreography</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CHOREOGRAPHY = eINSTANCE.getDocumentRoot_Choreography();

		/**
		 * The meta object literal for the '<em><b>Collaboration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__COLLABORATION = eINSTANCE.getDocumentRoot_Collaboration();

		/**
		 * The meta object literal for the '<em><b>Choreography Activity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CHOREOGRAPHY_ACTIVITY = eINSTANCE.getDocumentRoot_ChoreographyActivity();

		/**
		 * The meta object literal for the '<em><b>Choreography Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CHOREOGRAPHY_TASK = eINSTANCE.getDocumentRoot_ChoreographyTask();

		/**
		 * The meta object literal for the '<em><b>Compensate Event Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__COMPENSATE_EVENT_DEFINITION = eINSTANCE.getDocumentRoot_CompensateEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Complex Behavior Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__COMPLEX_BEHAVIOR_DEFINITION = eINSTANCE.getDocumentRoot_ComplexBehaviorDefinition();

		/**
		 * The meta object literal for the '<em><b>Complex Gateway</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__COMPLEX_GATEWAY = eINSTANCE.getDocumentRoot_ComplexGateway();

		/**
		 * The meta object literal for the '<em><b>Conditional Event Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONDITIONAL_EVENT_DEFINITION = eINSTANCE.getDocumentRoot_ConditionalEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Conversation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONVERSATION = eINSTANCE.getDocumentRoot_Conversation();

		/**
		 * The meta object literal for the '<em><b>Conversation Association</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONVERSATION_ASSOCIATION = eINSTANCE.getDocumentRoot_ConversationAssociation();

		/**
		 * The meta object literal for the '<em><b>Conversation Link</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONVERSATION_LINK = eINSTANCE.getDocumentRoot_ConversationLink();

		/**
		 * The meta object literal for the '<em><b>Correlation Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CORRELATION_KEY = eINSTANCE.getDocumentRoot_CorrelationKey();

		/**
		 * The meta object literal for the '<em><b>Correlation Property</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CORRELATION_PROPERTY = eINSTANCE.getDocumentRoot_CorrelationProperty();

		/**
		 * The meta object literal for the '<em><b>Correlation Property Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CORRELATION_PROPERTY_BINDING = eINSTANCE.getDocumentRoot_CorrelationPropertyBinding();

		/**
		 * The meta object literal for the '<em><b>Correlation Property Retrieval Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION = eINSTANCE.getDocumentRoot_CorrelationPropertyRetrievalExpression();

		/**
		 * The meta object literal for the '<em><b>Correlation Subscription</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CORRELATION_SUBSCRIPTION = eINSTANCE.getDocumentRoot_CorrelationSubscription();

		/**
		 * The meta object literal for the '<em><b>Data Association</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_ASSOCIATION = eINSTANCE.getDocumentRoot_DataAssociation();

		/**
		 * The meta object literal for the '<em><b>Data Input</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_INPUT = eINSTANCE.getDocumentRoot_DataInput();

		/**
		 * The meta object literal for the '<em><b>Data Input Association</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_INPUT_ASSOCIATION = eINSTANCE.getDocumentRoot_DataInputAssociation();

		/**
		 * The meta object literal for the '<em><b>Data Object</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_OBJECT = eINSTANCE.getDocumentRoot_DataObject();

		/**
		 * The meta object literal for the '<em><b>Data Object Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_OBJECT_REFERENCE = eINSTANCE.getDocumentRoot_DataObjectReference();

		/**
		 * The meta object literal for the '<em><b>Data Output</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_OUTPUT = eINSTANCE.getDocumentRoot_DataOutput();

		/**
		 * The meta object literal for the '<em><b>Data Output Association</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_OUTPUT_ASSOCIATION = eINSTANCE.getDocumentRoot_DataOutputAssociation();

		/**
		 * The meta object literal for the '<em><b>Data State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_STATE = eINSTANCE.getDocumentRoot_DataState();

		/**
		 * The meta object literal for the '<em><b>Data Store</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_STORE = eINSTANCE.getDocumentRoot_DataStore();

		/**
		 * The meta object literal for the '<em><b>Data Store Reference</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DATA_STORE_REFERENCE = eINSTANCE.getDocumentRoot_DataStoreReference();

		/**
		 * The meta object literal for the '<em><b>Definitions</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DEFINITIONS = eINSTANCE.getDocumentRoot_Definitions();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DOCUMENTATION = eINSTANCE.getDocumentRoot_Documentation();

		/**
		 * The meta object literal for the '<em><b>End Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__END_EVENT = eINSTANCE.getDocumentRoot_EndEvent();

		/**
		 * The meta object literal for the '<em><b>End Point</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__END_POINT = eINSTANCE.getDocumentRoot_EndPoint();

		/**
		 * The meta object literal for the '<em><b>Error</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ERROR = eINSTANCE.getDocumentRoot_Error();

		/**
		 * The meta object literal for the '<em><b>Error Event Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ERROR_EVENT_DEFINITION = eINSTANCE.getDocumentRoot_ErrorEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Escalation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ESCALATION = eINSTANCE.getDocumentRoot_Escalation();

		/**
		 * The meta object literal for the '<em><b>Escalation Event Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ESCALATION_EVENT_DEFINITION = eINSTANCE.getDocumentRoot_EscalationEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EVENT = eINSTANCE.getDocumentRoot_Event();

		/**
		 * The meta object literal for the '<em><b>Event Based Gateway</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EVENT_BASED_GATEWAY = eINSTANCE.getDocumentRoot_EventBasedGateway();

		/**
		 * The meta object literal for the '<em><b>Exclusive Gateway</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXCLUSIVE_GATEWAY = eINSTANCE.getDocumentRoot_ExclusiveGateway();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXPRESSION = eINSTANCE.getDocumentRoot_Expression();

		/**
		 * The meta object literal for the '<em><b>Extension</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXTENSION = eINSTANCE.getDocumentRoot_Extension();

		/**
		 * The meta object literal for the '<em><b>Extension Elements</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXTENSION_ELEMENTS = eINSTANCE.getDocumentRoot_ExtensionElements();

		/**
		 * The meta object literal for the '<em><b>Flow Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FLOW_NODE = eINSTANCE.getDocumentRoot_FlowNode();

		/**
		 * The meta object literal for the '<em><b>Formal Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FORMAL_EXPRESSION = eINSTANCE.getDocumentRoot_FormalExpression();

		/**
		 * The meta object literal for the '<em><b>Gateway</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GATEWAY = eINSTANCE.getDocumentRoot_Gateway();

		/**
		 * The meta object literal for the '<em><b>Global Business Rule Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GLOBAL_BUSINESS_RULE_TASK = eINSTANCE.getDocumentRoot_GlobalBusinessRuleTask();

		/**
		 * The meta object literal for the '<em><b>Global Choreography Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GLOBAL_CHOREOGRAPHY_TASK = eINSTANCE.getDocumentRoot_GlobalChoreographyTask();

		/**
		 * The meta object literal for the '<em><b>Global Conversation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GLOBAL_CONVERSATION = eINSTANCE.getDocumentRoot_GlobalConversation();

		/**
		 * The meta object literal for the '<em><b>Global Manual Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GLOBAL_MANUAL_TASK = eINSTANCE.getDocumentRoot_GlobalManualTask();

		/**
		 * The meta object literal for the '<em><b>Global Script Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GLOBAL_SCRIPT_TASK = eINSTANCE.getDocumentRoot_GlobalScriptTask();

		/**
		 * The meta object literal for the '<em><b>Global Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GLOBAL_TASK = eINSTANCE.getDocumentRoot_GlobalTask();

		/**
		 * The meta object literal for the '<em><b>Global User Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GLOBAL_USER_TASK = eINSTANCE.getDocumentRoot_GlobalUserTask();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GROUP = eINSTANCE.getDocumentRoot_Group();

		/**
		 * The meta object literal for the '<em><b>Human Performer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__HUMAN_PERFORMER = eINSTANCE.getDocumentRoot_HumanPerformer();

		/**
		 * The meta object literal for the '<em><b>Performer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PERFORMER = eINSTANCE.getDocumentRoot_Performer();

		/**
		 * The meta object literal for the '<em><b>Resource Role</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RESOURCE_ROLE = eINSTANCE.getDocumentRoot_ResourceRole();

		/**
		 * The meta object literal for the '<em><b>Implicit Throw Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__IMPLICIT_THROW_EVENT = eINSTANCE.getDocumentRoot_ImplicitThrowEvent();

		/**
		 * The meta object literal for the '<em><b>Import</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__IMPORT = eINSTANCE.getDocumentRoot_Import();

		/**
		 * The meta object literal for the '<em><b>Inclusive Gateway</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__INCLUSIVE_GATEWAY = eINSTANCE.getDocumentRoot_InclusiveGateway();

		/**
		 * The meta object literal for the '<em><b>Input Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__INPUT_SET = eINSTANCE.getDocumentRoot_InputSet();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__INTERFACE = eINSTANCE.getDocumentRoot_Interface();

		/**
		 * The meta object literal for the '<em><b>Intermediate Catch Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__INTERMEDIATE_CATCH_EVENT = eINSTANCE.getDocumentRoot_IntermediateCatchEvent();

		/**
		 * The meta object literal for the '<em><b>Intermediate Throw Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__INTERMEDIATE_THROW_EVENT = eINSTANCE.getDocumentRoot_IntermediateThrowEvent();

		/**
		 * The meta object literal for the '<em><b>Io Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__IO_BINDING = eINSTANCE.getDocumentRoot_IoBinding();

		/**
		 * The meta object literal for the '<em><b>Io Specification</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__IO_SPECIFICATION = eINSTANCE.getDocumentRoot_IoSpecification();

		/**
		 * The meta object literal for the '<em><b>Item Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ITEM_DEFINITION = eINSTANCE.getDocumentRoot_ItemDefinition();

		/**
		 * The meta object literal for the '<em><b>Lane</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__LANE = eINSTANCE.getDocumentRoot_Lane();

		/**
		 * The meta object literal for the '<em><b>Lane Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__LANE_SET = eINSTANCE.getDocumentRoot_LaneSet();

		/**
		 * The meta object literal for the '<em><b>Link Event Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__LINK_EVENT_DEFINITION = eINSTANCE.getDocumentRoot_LinkEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Loop Characteristics</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__LOOP_CHARACTERISTICS = eINSTANCE.getDocumentRoot_LoopCharacteristics();

		/**
		 * The meta object literal for the '<em><b>Manual Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MANUAL_TASK = eINSTANCE.getDocumentRoot_ManualTask();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MESSAGE = eINSTANCE.getDocumentRoot_Message();

		/**
		 * The meta object literal for the '<em><b>Message Event Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MESSAGE_EVENT_DEFINITION = eINSTANCE.getDocumentRoot_MessageEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Message Flow</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MESSAGE_FLOW = eINSTANCE.getDocumentRoot_MessageFlow();

		/**
		 * The meta object literal for the '<em><b>Message Flow Association</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MESSAGE_FLOW_ASSOCIATION = eINSTANCE.getDocumentRoot_MessageFlowAssociation();

		/**
		 * The meta object literal for the '<em><b>Monitoring</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MONITORING = eINSTANCE.getDocumentRoot_Monitoring();

		/**
		 * The meta object literal for the '<em><b>Multi Instance Loop Characteristics</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MULTI_INSTANCE_LOOP_CHARACTERISTICS = eINSTANCE.getDocumentRoot_MultiInstanceLoopCharacteristics();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__OPERATION = eINSTANCE.getDocumentRoot_Operation();

		/**
		 * The meta object literal for the '<em><b>Output Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__OUTPUT_SET = eINSTANCE.getDocumentRoot_OutputSet();

		/**
		 * The meta object literal for the '<em><b>Parallel Gateway</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARALLEL_GATEWAY = eINSTANCE.getDocumentRoot_ParallelGateway();

		/**
		 * The meta object literal for the '<em><b>Participant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARTICIPANT = eINSTANCE.getDocumentRoot_Participant();

		/**
		 * The meta object literal for the '<em><b>Participant Association</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARTICIPANT_ASSOCIATION = eINSTANCE.getDocumentRoot_ParticipantAssociation();

		/**
		 * The meta object literal for the '<em><b>Participant Multiplicity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARTICIPANT_MULTIPLICITY = eINSTANCE.getDocumentRoot_ParticipantMultiplicity();

		/**
		 * The meta object literal for the '<em><b>Partner Entity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARTNER_ENTITY = eINSTANCE.getDocumentRoot_PartnerEntity();

		/**
		 * The meta object literal for the '<em><b>Partner Role</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARTNER_ROLE = eINSTANCE.getDocumentRoot_PartnerRole();

		/**
		 * The meta object literal for the '<em><b>Potential Owner</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__POTENTIAL_OWNER = eINSTANCE.getDocumentRoot_PotentialOwner();

		/**
		 * The meta object literal for the '<em><b>Process</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PROCESS = eINSTANCE.getDocumentRoot_Process();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PROPERTY = eINSTANCE.getDocumentRoot_Property();

		/**
		 * The meta object literal for the '<em><b>Receive Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RECEIVE_TASK = eINSTANCE.getDocumentRoot_ReceiveTask();

		/**
		 * The meta object literal for the '<em><b>Relationship</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RELATIONSHIP = eINSTANCE.getDocumentRoot_Relationship();

		/**
		 * The meta object literal for the '<em><b>Rendering</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RENDERING = eINSTANCE.getDocumentRoot_Rendering();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RESOURCE = eINSTANCE.getDocumentRoot_Resource();

		/**
		 * The meta object literal for the '<em><b>Resource Assignment Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RESOURCE_ASSIGNMENT_EXPRESSION = eINSTANCE.getDocumentRoot_ResourceAssignmentExpression();

		/**
		 * The meta object literal for the '<em><b>Resource Parameter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RESOURCE_PARAMETER = eINSTANCE.getDocumentRoot_ResourceParameter();

		/**
		 * The meta object literal for the '<em><b>Resource Parameter Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RESOURCE_PARAMETER_BINDING = eINSTANCE.getDocumentRoot_ResourceParameterBinding();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SCRIPT = eINSTANCE.getDocumentRoot_Script();

		/**
		 * The meta object literal for the '<em><b>Script Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SCRIPT_TASK = eINSTANCE.getDocumentRoot_ScriptTask();

		/**
		 * The meta object literal for the '<em><b>Send Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SEND_TASK = eINSTANCE.getDocumentRoot_SendTask();

		/**
		 * The meta object literal for the '<em><b>Sequence Flow</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SEQUENCE_FLOW = eINSTANCE.getDocumentRoot_SequenceFlow();

		/**
		 * The meta object literal for the '<em><b>Service Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SERVICE_TASK = eINSTANCE.getDocumentRoot_ServiceTask();

		/**
		 * The meta object literal for the '<em><b>Signal</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SIGNAL = eINSTANCE.getDocumentRoot_Signal();

		/**
		 * The meta object literal for the '<em><b>Signal Event Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SIGNAL_EVENT_DEFINITION = eINSTANCE.getDocumentRoot_SignalEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Standard Loop Characteristics</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STANDARD_LOOP_CHARACTERISTICS = eINSTANCE.getDocumentRoot_StandardLoopCharacteristics();

		/**
		 * The meta object literal for the '<em><b>Start Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__START_EVENT = eINSTANCE.getDocumentRoot_StartEvent();

		/**
		 * The meta object literal for the '<em><b>Sub Choreography</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SUB_CHOREOGRAPHY = eINSTANCE.getDocumentRoot_SubChoreography();

		/**
		 * The meta object literal for the '<em><b>Sub Conversation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SUB_CONVERSATION = eINSTANCE.getDocumentRoot_SubConversation();

		/**
		 * The meta object literal for the '<em><b>Sub Process</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SUB_PROCESS = eINSTANCE.getDocumentRoot_SubProcess();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TASK = eINSTANCE.getDocumentRoot_Task();

		/**
		 * The meta object literal for the '<em><b>Terminate Event Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TERMINATE_EVENT_DEFINITION = eINSTANCE.getDocumentRoot_TerminateEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TEXT = eINSTANCE.getDocumentRoot_Text();

		/**
		 * The meta object literal for the '<em><b>Text Annotation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TEXT_ANNOTATION = eINSTANCE.getDocumentRoot_TextAnnotation();

		/**
		 * The meta object literal for the '<em><b>Throw Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__THROW_EVENT = eINSTANCE.getDocumentRoot_ThrowEvent();

		/**
		 * The meta object literal for the '<em><b>Timer Event Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TIMER_EVENT_DEFINITION = eINSTANCE.getDocumentRoot_TimerEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Transaction</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TRANSACTION = eINSTANCE.getDocumentRoot_Transaction();

		/**
		 * The meta object literal for the '<em><b>User Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__USER_TASK = eINSTANCE.getDocumentRoot_UserTask();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TActivityImpl <em>TActivity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TActivityImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTActivity()
		 * @generated
		 */
		EClass TACTIVITY = eINSTANCE.getTActivity();

		/**
		 * The meta object literal for the '<em><b>Io Specification</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TACTIVITY__IO_SPECIFICATION = eINSTANCE.getTActivity_IoSpecification();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TACTIVITY__PROPERTY = eINSTANCE.getTActivity_Property();

		/**
		 * The meta object literal for the '<em><b>Data Input Association</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TACTIVITY__DATA_INPUT_ASSOCIATION = eINSTANCE.getTActivity_DataInputAssociation();

		/**
		 * The meta object literal for the '<em><b>Data Output Association</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TACTIVITY__DATA_OUTPUT_ASSOCIATION = eINSTANCE.getTActivity_DataOutputAssociation();

		/**
		 * The meta object literal for the '<em><b>Resource Role Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TACTIVITY__RESOURCE_ROLE_GROUP = eINSTANCE.getTActivity_ResourceRoleGroup();

		/**
		 * The meta object literal for the '<em><b>Resource Role</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TACTIVITY__RESOURCE_ROLE = eINSTANCE.getTActivity_ResourceRole();

		/**
		 * The meta object literal for the '<em><b>Loop Characteristics Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TACTIVITY__LOOP_CHARACTERISTICS_GROUP = eINSTANCE.getTActivity_LoopCharacteristicsGroup();

		/**
		 * The meta object literal for the '<em><b>Loop Characteristics</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TACTIVITY__LOOP_CHARACTERISTICS = eINSTANCE.getTActivity_LoopCharacteristics();

		/**
		 * The meta object literal for the '<em><b>Completion Quantity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TACTIVITY__COMPLETION_QUANTITY = eINSTANCE.getTActivity_CompletionQuantity();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TACTIVITY__DEFAULT = eINSTANCE.getTActivity_Default();

		/**
		 * The meta object literal for the '<em><b>Is For Compensation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TACTIVITY__IS_FOR_COMPENSATION = eINSTANCE.getTActivity_IsForCompensation();

		/**
		 * The meta object literal for the '<em><b>Start Quantity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TACTIVITY__START_QUANTITY = eINSTANCE.getTActivity_StartQuantity();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TAdHocSubProcessImpl <em>TAd Hoc Sub Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TAdHocSubProcessImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAdHocSubProcess()
		 * @generated
		 */
		EClass TAD_HOC_SUB_PROCESS = eINSTANCE.getTAdHocSubProcess();

		/**
		 * The meta object literal for the '<em><b>Completion Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAD_HOC_SUB_PROCESS__COMPLETION_CONDITION = eINSTANCE.getTAdHocSubProcess_CompletionCondition();

		/**
		 * The meta object literal for the '<em><b>Cancel Remaining Instances</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAD_HOC_SUB_PROCESS__CANCEL_REMAINING_INSTANCES = eINSTANCE.getTAdHocSubProcess_CancelRemainingInstances();

		/**
		 * The meta object literal for the '<em><b>Ordering</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAD_HOC_SUB_PROCESS__ORDERING = eINSTANCE.getTAdHocSubProcess_Ordering();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TArtifactImpl <em>TArtifact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TArtifactImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTArtifact()
		 * @generated
		 */
		EClass TARTIFACT = eINSTANCE.getTArtifact();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TAssignmentImpl <em>TAssignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TAssignmentImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAssignment()
		 * @generated
		 */
		EClass TASSIGNMENT = eINSTANCE.getTAssignment();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASSIGNMENT__FROM = eINSTANCE.getTAssignment_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASSIGNMENT__TO = eINSTANCE.getTAssignment_To();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TAssociationImpl <em>TAssociation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TAssociationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAssociation()
		 * @generated
		 */
		EClass TASSOCIATION = eINSTANCE.getTAssociation();

		/**
		 * The meta object literal for the '<em><b>Association Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASSOCIATION__ASSOCIATION_DIRECTION = eINSTANCE.getTAssociation_AssociationDirection();

		/**
		 * The meta object literal for the '<em><b>Source Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASSOCIATION__SOURCE_REF = eINSTANCE.getTAssociation_SourceRef();

		/**
		 * The meta object literal for the '<em><b>Target Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASSOCIATION__TARGET_REF = eINSTANCE.getTAssociation_TargetRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TAuditingImpl <em>TAuditing</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TAuditingImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAuditing()
		 * @generated
		 */
		EClass TAUDITING = eINSTANCE.getTAuditing();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TBaseElementImpl <em>TBase Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TBaseElementImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTBaseElement()
		 * @generated
		 */
		EClass TBASE_ELEMENT = eINSTANCE.getTBaseElement();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TBASE_ELEMENT__DOCUMENTATION = eINSTANCE.getTBaseElement_Documentation();

		/**
		 * The meta object literal for the '<em><b>Extension Elements</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TBASE_ELEMENT__EXTENSION_ELEMENTS = eINSTANCE.getTBaseElement_ExtensionElements();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TBASE_ELEMENT__ID = eINSTANCE.getTBaseElement_Id();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TBASE_ELEMENT__ANY_ATTRIBUTE = eINSTANCE.getTBaseElement_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TBaseElementWithMixedContentImpl <em>TBase Element With Mixed Content</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TBaseElementWithMixedContentImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTBaseElementWithMixedContent()
		 * @generated
		 */
		EClass TBASE_ELEMENT_WITH_MIXED_CONTENT = eINSTANCE.getTBaseElementWithMixedContent();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TBASE_ELEMENT_WITH_MIXED_CONTENT__MIXED = eINSTANCE.getTBaseElementWithMixedContent_Mixed();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TBASE_ELEMENT_WITH_MIXED_CONTENT__DOCUMENTATION = eINSTANCE.getTBaseElementWithMixedContent_Documentation();

		/**
		 * The meta object literal for the '<em><b>Extension Elements</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TBASE_ELEMENT_WITH_MIXED_CONTENT__EXTENSION_ELEMENTS = eINSTANCE.getTBaseElementWithMixedContent_ExtensionElements();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TBASE_ELEMENT_WITH_MIXED_CONTENT__ID = eINSTANCE.getTBaseElementWithMixedContent_Id();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TBASE_ELEMENT_WITH_MIXED_CONTENT__ANY_ATTRIBUTE = eINSTANCE.getTBaseElementWithMixedContent_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TBoundaryEventImpl <em>TBoundary Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TBoundaryEventImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTBoundaryEvent()
		 * @generated
		 */
		EClass TBOUNDARY_EVENT = eINSTANCE.getTBoundaryEvent();

		/**
		 * The meta object literal for the '<em><b>Attached To Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TBOUNDARY_EVENT__ATTACHED_TO_REF = eINSTANCE.getTBoundaryEvent_AttachedToRef();

		/**
		 * The meta object literal for the '<em><b>Cancel Activity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TBOUNDARY_EVENT__CANCEL_ACTIVITY = eINSTANCE.getTBoundaryEvent_CancelActivity();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TBusinessRuleTaskImpl <em>TBusiness Rule Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TBusinessRuleTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTBusinessRuleTask()
		 * @generated
		 */
		EClass TBUSINESS_RULE_TASK = eINSTANCE.getTBusinessRuleTask();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TBUSINESS_RULE_TASK__IMPLEMENTATION = eINSTANCE.getTBusinessRuleTask_Implementation();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCallableElementImpl <em>TCallable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCallableElementImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCallableElement()
		 * @generated
		 */
		EClass TCALLABLE_ELEMENT = eINSTANCE.getTCallableElement();

		/**
		 * The meta object literal for the '<em><b>Supported Interface Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCALLABLE_ELEMENT__SUPPORTED_INTERFACE_REF = eINSTANCE.getTCallableElement_SupportedInterfaceRef();

		/**
		 * The meta object literal for the '<em><b>Io Specification</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCALLABLE_ELEMENT__IO_SPECIFICATION = eINSTANCE.getTCallableElement_IoSpecification();

		/**
		 * The meta object literal for the '<em><b>Io Binding</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCALLABLE_ELEMENT__IO_BINDING = eINSTANCE.getTCallableElement_IoBinding();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCALLABLE_ELEMENT__NAME = eINSTANCE.getTCallableElement_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCallActivityImpl <em>TCall Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCallActivityImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCallActivity()
		 * @generated
		 */
		EClass TCALL_ACTIVITY = eINSTANCE.getTCallActivity();

		/**
		 * The meta object literal for the '<em><b>Called Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCALL_ACTIVITY__CALLED_ELEMENT = eINSTANCE.getTCallActivity_CalledElement();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCallChoreographyImpl <em>TCall Choreography</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCallChoreographyImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCallChoreography()
		 * @generated
		 */
		EClass TCALL_CHOREOGRAPHY = eINSTANCE.getTCallChoreography();

		/**
		 * The meta object literal for the '<em><b>Participant Association</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCALL_CHOREOGRAPHY__PARTICIPANT_ASSOCIATION = eINSTANCE.getTCallChoreography_ParticipantAssociation();

		/**
		 * The meta object literal for the '<em><b>Called Choreography Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCALL_CHOREOGRAPHY__CALLED_CHOREOGRAPHY_REF = eINSTANCE.getTCallChoreography_CalledChoreographyRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCallConversationImpl <em>TCall Conversation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCallConversationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCallConversation()
		 * @generated
		 */
		EClass TCALL_CONVERSATION = eINSTANCE.getTCallConversation();

		/**
		 * The meta object literal for the '<em><b>Participant Association</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCALL_CONVERSATION__PARTICIPANT_ASSOCIATION = eINSTANCE.getTCallConversation_ParticipantAssociation();

		/**
		 * The meta object literal for the '<em><b>Called Collaboration Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCALL_CONVERSATION__CALLED_COLLABORATION_REF = eINSTANCE.getTCallConversation_CalledCollaborationRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCancelEventDefinitionImpl <em>TCancel Event Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCancelEventDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCancelEventDefinition()
		 * @generated
		 */
		EClass TCANCEL_EVENT_DEFINITION = eINSTANCE.getTCancelEventDefinition();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCatchEventImpl <em>TCatch Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCatchEventImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCatchEvent()
		 * @generated
		 */
		EClass TCATCH_EVENT = eINSTANCE.getTCatchEvent();

		/**
		 * The meta object literal for the '<em><b>Data Output</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCATCH_EVENT__DATA_OUTPUT = eINSTANCE.getTCatchEvent_DataOutput();

		/**
		 * The meta object literal for the '<em><b>Data Output Association</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCATCH_EVENT__DATA_OUTPUT_ASSOCIATION = eINSTANCE.getTCatchEvent_DataOutputAssociation();

		/**
		 * The meta object literal for the '<em><b>Output Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCATCH_EVENT__OUTPUT_SET = eINSTANCE.getTCatchEvent_OutputSet();

		/**
		 * The meta object literal for the '<em><b>Event Definition Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCATCH_EVENT__EVENT_DEFINITION_GROUP = eINSTANCE.getTCatchEvent_EventDefinitionGroup();

		/**
		 * The meta object literal for the '<em><b>Event Definition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCATCH_EVENT__EVENT_DEFINITION = eINSTANCE.getTCatchEvent_EventDefinition();

		/**
		 * The meta object literal for the '<em><b>Event Definition Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCATCH_EVENT__EVENT_DEFINITION_REF = eINSTANCE.getTCatchEvent_EventDefinitionRef();

		/**
		 * The meta object literal for the '<em><b>Parallel Multiple</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCATCH_EVENT__PARALLEL_MULTIPLE = eINSTANCE.getTCatchEvent_ParallelMultiple();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCategoryImpl <em>TCategory</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCategoryImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCategory()
		 * @generated
		 */
		EClass TCATEGORY = eINSTANCE.getTCategory();

		/**
		 * The meta object literal for the '<em><b>Category Value</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCATEGORY__CATEGORY_VALUE = eINSTANCE.getTCategory_CategoryValue();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCATEGORY__NAME = eINSTANCE.getTCategory_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCategoryValueImpl <em>TCategory Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCategoryValueImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCategoryValue()
		 * @generated
		 */
		EClass TCATEGORY_VALUE = eINSTANCE.getTCategoryValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCATEGORY_VALUE__VALUE = eINSTANCE.getTCategoryValue_Value();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TChoreographyImpl <em>TChoreography</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TChoreographyImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTChoreography()
		 * @generated
		 */
		EClass TCHOREOGRAPHY = eINSTANCE.getTChoreography();

		/**
		 * The meta object literal for the '<em><b>Flow Element Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCHOREOGRAPHY__FLOW_ELEMENT_GROUP = eINSTANCE.getTChoreography_FlowElementGroup();

		/**
		 * The meta object literal for the '<em><b>Flow Element</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCHOREOGRAPHY__FLOW_ELEMENT = eINSTANCE.getTChoreography_FlowElement();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TChoreographyActivityImpl <em>TChoreography Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TChoreographyActivityImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTChoreographyActivity()
		 * @generated
		 */
		EClass TCHOREOGRAPHY_ACTIVITY = eINSTANCE.getTChoreographyActivity();

		/**
		 * The meta object literal for the '<em><b>Participant Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCHOREOGRAPHY_ACTIVITY__PARTICIPANT_REF = eINSTANCE.getTChoreographyActivity_ParticipantRef();

		/**
		 * The meta object literal for the '<em><b>Correlation Key</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCHOREOGRAPHY_ACTIVITY__CORRELATION_KEY = eINSTANCE.getTChoreographyActivity_CorrelationKey();

		/**
		 * The meta object literal for the '<em><b>Initiating Participant Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCHOREOGRAPHY_ACTIVITY__INITIATING_PARTICIPANT_REF = eINSTANCE.getTChoreographyActivity_InitiatingParticipantRef();

		/**
		 * The meta object literal for the '<em><b>Loop Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCHOREOGRAPHY_ACTIVITY__LOOP_TYPE = eINSTANCE.getTChoreographyActivity_LoopType();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TChoreographyTaskImpl <em>TChoreography Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TChoreographyTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTChoreographyTask()
		 * @generated
		 */
		EClass TCHOREOGRAPHY_TASK = eINSTANCE.getTChoreographyTask();

		/**
		 * The meta object literal for the '<em><b>Message Flow Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCHOREOGRAPHY_TASK__MESSAGE_FLOW_REF = eINSTANCE.getTChoreographyTask_MessageFlowRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCollaborationImpl <em>TCollaboration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCollaborationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCollaboration()
		 * @generated
		 */
		EClass TCOLLABORATION = eINSTANCE.getTCollaboration();

		/**
		 * The meta object literal for the '<em><b>Participant</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOLLABORATION__PARTICIPANT = eINSTANCE.getTCollaboration_Participant();

		/**
		 * The meta object literal for the '<em><b>Message Flow</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOLLABORATION__MESSAGE_FLOW = eINSTANCE.getTCollaboration_MessageFlow();

		/**
		 * The meta object literal for the '<em><b>Artifact Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCOLLABORATION__ARTIFACT_GROUP = eINSTANCE.getTCollaboration_ArtifactGroup();

		/**
		 * The meta object literal for the '<em><b>Artifact</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOLLABORATION__ARTIFACT = eINSTANCE.getTCollaboration_Artifact();

		/**
		 * The meta object literal for the '<em><b>Conversation Node Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCOLLABORATION__CONVERSATION_NODE_GROUP = eINSTANCE.getTCollaboration_ConversationNodeGroup();

		/**
		 * The meta object literal for the '<em><b>Conversation Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOLLABORATION__CONVERSATION_NODE = eINSTANCE.getTCollaboration_ConversationNode();

		/**
		 * The meta object literal for the '<em><b>Conversation Association</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOLLABORATION__CONVERSATION_ASSOCIATION = eINSTANCE.getTCollaboration_ConversationAssociation();

		/**
		 * The meta object literal for the '<em><b>Participant Association</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOLLABORATION__PARTICIPANT_ASSOCIATION = eINSTANCE.getTCollaboration_ParticipantAssociation();

		/**
		 * The meta object literal for the '<em><b>Message Flow Association</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOLLABORATION__MESSAGE_FLOW_ASSOCIATION = eINSTANCE.getTCollaboration_MessageFlowAssociation();

		/**
		 * The meta object literal for the '<em><b>Correlation Key</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOLLABORATION__CORRELATION_KEY = eINSTANCE.getTCollaboration_CorrelationKey();

		/**
		 * The meta object literal for the '<em><b>Choreography Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCOLLABORATION__CHOREOGRAPHY_REF = eINSTANCE.getTCollaboration_ChoreographyRef();

		/**
		 * The meta object literal for the '<em><b>Conversation Link</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOLLABORATION__CONVERSATION_LINK = eINSTANCE.getTCollaboration_ConversationLink();

		/**
		 * The meta object literal for the '<em><b>Is Closed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCOLLABORATION__IS_CLOSED = eINSTANCE.getTCollaboration_IsClosed();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCOLLABORATION__NAME = eINSTANCE.getTCollaboration_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCompensateEventDefinitionImpl <em>TCompensate Event Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCompensateEventDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCompensateEventDefinition()
		 * @generated
		 */
		EClass TCOMPENSATE_EVENT_DEFINITION = eINSTANCE.getTCompensateEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Activity Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCOMPENSATE_EVENT_DEFINITION__ACTIVITY_REF = eINSTANCE.getTCompensateEventDefinition_ActivityRef();

		/**
		 * The meta object literal for the '<em><b>Wait For Completion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCOMPENSATE_EVENT_DEFINITION__WAIT_FOR_COMPLETION = eINSTANCE.getTCompensateEventDefinition_WaitForCompletion();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TComplexBehaviorDefinitionImpl <em>TComplex Behavior Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TComplexBehaviorDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTComplexBehaviorDefinition()
		 * @generated
		 */
		EClass TCOMPLEX_BEHAVIOR_DEFINITION = eINSTANCE.getTComplexBehaviorDefinition();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOMPLEX_BEHAVIOR_DEFINITION__CONDITION = eINSTANCE.getTComplexBehaviorDefinition_Condition();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOMPLEX_BEHAVIOR_DEFINITION__EVENT = eINSTANCE.getTComplexBehaviorDefinition_Event();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TComplexGatewayImpl <em>TComplex Gateway</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TComplexGatewayImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTComplexGateway()
		 * @generated
		 */
		EClass TCOMPLEX_GATEWAY = eINSTANCE.getTComplexGateway();

		/**
		 * The meta object literal for the '<em><b>Activation Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCOMPLEX_GATEWAY__ACTIVATION_CONDITION = eINSTANCE.getTComplexGateway_ActivationCondition();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCOMPLEX_GATEWAY__DEFAULT = eINSTANCE.getTComplexGateway_Default();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TConditionalEventDefinitionImpl <em>TConditional Event Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TConditionalEventDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTConditionalEventDefinition()
		 * @generated
		 */
		EClass TCONDITIONAL_EVENT_DEFINITION = eINSTANCE.getTConditionalEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCONDITIONAL_EVENT_DEFINITION__CONDITION = eINSTANCE.getTConditionalEventDefinition_Condition();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TConversationImpl <em>TConversation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TConversationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTConversation()
		 * @generated
		 */
		EClass TCONVERSATION = eINSTANCE.getTConversation();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TConversationAssociationImpl <em>TConversation Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TConversationAssociationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTConversationAssociation()
		 * @generated
		 */
		EClass TCONVERSATION_ASSOCIATION = eINSTANCE.getTConversationAssociation();

		/**
		 * The meta object literal for the '<em><b>Inner Conversation Node Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCONVERSATION_ASSOCIATION__INNER_CONVERSATION_NODE_REF = eINSTANCE.getTConversationAssociation_InnerConversationNodeRef();

		/**
		 * The meta object literal for the '<em><b>Outer Conversation Node Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCONVERSATION_ASSOCIATION__OUTER_CONVERSATION_NODE_REF = eINSTANCE.getTConversationAssociation_OuterConversationNodeRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TConversationLinkImpl <em>TConversation Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TConversationLinkImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTConversationLink()
		 * @generated
		 */
		EClass TCONVERSATION_LINK = eINSTANCE.getTConversationLink();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCONVERSATION_LINK__NAME = eINSTANCE.getTConversationLink_Name();

		/**
		 * The meta object literal for the '<em><b>Source Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCONVERSATION_LINK__SOURCE_REF = eINSTANCE.getTConversationLink_SourceRef();

		/**
		 * The meta object literal for the '<em><b>Target Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCONVERSATION_LINK__TARGET_REF = eINSTANCE.getTConversationLink_TargetRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TConversationNodeImpl <em>TConversation Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TConversationNodeImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTConversationNode()
		 * @generated
		 */
		EClass TCONVERSATION_NODE = eINSTANCE.getTConversationNode();

		/**
		 * The meta object literal for the '<em><b>Participant Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCONVERSATION_NODE__PARTICIPANT_REF = eINSTANCE.getTConversationNode_ParticipantRef();

		/**
		 * The meta object literal for the '<em><b>Message Flow Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCONVERSATION_NODE__MESSAGE_FLOW_REF = eINSTANCE.getTConversationNode_MessageFlowRef();

		/**
		 * The meta object literal for the '<em><b>Correlation Key</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCONVERSATION_NODE__CORRELATION_KEY = eINSTANCE.getTConversationNode_CorrelationKey();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCONVERSATION_NODE__NAME = eINSTANCE.getTConversationNode_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCorrelationKeyImpl <em>TCorrelation Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCorrelationKeyImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCorrelationKey()
		 * @generated
		 */
		EClass TCORRELATION_KEY = eINSTANCE.getTCorrelationKey();

		/**
		 * The meta object literal for the '<em><b>Correlation Property Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCORRELATION_KEY__CORRELATION_PROPERTY_REF = eINSTANCE.getTCorrelationKey_CorrelationPropertyRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCORRELATION_KEY__NAME = eINSTANCE.getTCorrelationKey_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCorrelationPropertyImpl <em>TCorrelation Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCorrelationPropertyImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCorrelationProperty()
		 * @generated
		 */
		EClass TCORRELATION_PROPERTY = eINSTANCE.getTCorrelationProperty();

		/**
		 * The meta object literal for the '<em><b>Correlation Property Retrieval Expression</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCORRELATION_PROPERTY__CORRELATION_PROPERTY_RETRIEVAL_EXPRESSION = eINSTANCE.getTCorrelationProperty_CorrelationPropertyRetrievalExpression();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCORRELATION_PROPERTY__NAME = eINSTANCE.getTCorrelationProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCORRELATION_PROPERTY__TYPE = eINSTANCE.getTCorrelationProperty_Type();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCorrelationPropertyBindingImpl <em>TCorrelation Property Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCorrelationPropertyBindingImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCorrelationPropertyBinding()
		 * @generated
		 */
		EClass TCORRELATION_PROPERTY_BINDING = eINSTANCE.getTCorrelationPropertyBinding();

		/**
		 * The meta object literal for the '<em><b>Data Path</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCORRELATION_PROPERTY_BINDING__DATA_PATH = eINSTANCE.getTCorrelationPropertyBinding_DataPath();

		/**
		 * The meta object literal for the '<em><b>Correlation Property Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF = eINSTANCE.getTCorrelationPropertyBinding_CorrelationPropertyRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCorrelationPropertyRetrievalExpressionImpl <em>TCorrelation Property Retrieval Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCorrelationPropertyRetrievalExpressionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCorrelationPropertyRetrievalExpression()
		 * @generated
		 */
		EClass TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION = eINSTANCE.getTCorrelationPropertyRetrievalExpression();

		/**
		 * The meta object literal for the '<em><b>Message Path</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION__MESSAGE_PATH = eINSTANCE.getTCorrelationPropertyRetrievalExpression_MessagePath();

		/**
		 * The meta object literal for the '<em><b>Message Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION__MESSAGE_REF = eINSTANCE.getTCorrelationPropertyRetrievalExpression_MessageRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TCorrelationSubscriptionImpl <em>TCorrelation Subscription</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TCorrelationSubscriptionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTCorrelationSubscription()
		 * @generated
		 */
		EClass TCORRELATION_SUBSCRIPTION = eINSTANCE.getTCorrelationSubscription();

		/**
		 * The meta object literal for the '<em><b>Correlation Property Binding</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TCORRELATION_SUBSCRIPTION__CORRELATION_PROPERTY_BINDING = eINSTANCE.getTCorrelationSubscription_CorrelationPropertyBinding();

		/**
		 * The meta object literal for the '<em><b>Correlation Key Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TCORRELATION_SUBSCRIPTION__CORRELATION_KEY_REF = eINSTANCE.getTCorrelationSubscription_CorrelationKeyRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TDataAssociationImpl <em>TData Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TDataAssociationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataAssociation()
		 * @generated
		 */
		EClass TDATA_ASSOCIATION = eINSTANCE.getTDataAssociation();

		/**
		 * The meta object literal for the '<em><b>Source Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_ASSOCIATION__SOURCE_REF = eINSTANCE.getTDataAssociation_SourceRef();

		/**
		 * The meta object literal for the '<em><b>Target Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_ASSOCIATION__TARGET_REF = eINSTANCE.getTDataAssociation_TargetRef();

		/**
		 * The meta object literal for the '<em><b>Transformation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDATA_ASSOCIATION__TRANSFORMATION = eINSTANCE.getTDataAssociation_Transformation();

		/**
		 * The meta object literal for the '<em><b>Assignment</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDATA_ASSOCIATION__ASSIGNMENT = eINSTANCE.getTDataAssociation_Assignment();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TDataInputImpl <em>TData Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TDataInputImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataInput()
		 * @generated
		 */
		EClass TDATA_INPUT = eINSTANCE.getTDataInput();

		/**
		 * The meta object literal for the '<em><b>Data State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDATA_INPUT__DATA_STATE = eINSTANCE.getTDataInput_DataState();

		/**
		 * The meta object literal for the '<em><b>Is Collection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_INPUT__IS_COLLECTION = eINSTANCE.getTDataInput_IsCollection();

		/**
		 * The meta object literal for the '<em><b>Item Subject Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_INPUT__ITEM_SUBJECT_REF = eINSTANCE.getTDataInput_ItemSubjectRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_INPUT__NAME = eINSTANCE.getTDataInput_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TDataInputAssociationImpl <em>TData Input Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TDataInputAssociationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataInputAssociation()
		 * @generated
		 */
		EClass TDATA_INPUT_ASSOCIATION = eINSTANCE.getTDataInputAssociation();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TDataObjectImpl <em>TData Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TDataObjectImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataObject()
		 * @generated
		 */
		EClass TDATA_OBJECT = eINSTANCE.getTDataObject();

		/**
		 * The meta object literal for the '<em><b>Data State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDATA_OBJECT__DATA_STATE = eINSTANCE.getTDataObject_DataState();

		/**
		 * The meta object literal for the '<em><b>Is Collection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_OBJECT__IS_COLLECTION = eINSTANCE.getTDataObject_IsCollection();

		/**
		 * The meta object literal for the '<em><b>Item Subject Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_OBJECT__ITEM_SUBJECT_REF = eINSTANCE.getTDataObject_ItemSubjectRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TDataObjectReferenceImpl <em>TData Object Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TDataObjectReferenceImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataObjectReference()
		 * @generated
		 */
		EClass TDATA_OBJECT_REFERENCE = eINSTANCE.getTDataObjectReference();

		/**
		 * The meta object literal for the '<em><b>Data State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDATA_OBJECT_REFERENCE__DATA_STATE = eINSTANCE.getTDataObjectReference_DataState();

		/**
		 * The meta object literal for the '<em><b>Data Object Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_OBJECT_REFERENCE__DATA_OBJECT_REF = eINSTANCE.getTDataObjectReference_DataObjectRef();

		/**
		 * The meta object literal for the '<em><b>Item Subject Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_OBJECT_REFERENCE__ITEM_SUBJECT_REF = eINSTANCE.getTDataObjectReference_ItemSubjectRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TDataOutputImpl <em>TData Output</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TDataOutputImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataOutput()
		 * @generated
		 */
		EClass TDATA_OUTPUT = eINSTANCE.getTDataOutput();

		/**
		 * The meta object literal for the '<em><b>Data State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDATA_OUTPUT__DATA_STATE = eINSTANCE.getTDataOutput_DataState();

		/**
		 * The meta object literal for the '<em><b>Is Collection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_OUTPUT__IS_COLLECTION = eINSTANCE.getTDataOutput_IsCollection();

		/**
		 * The meta object literal for the '<em><b>Item Subject Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_OUTPUT__ITEM_SUBJECT_REF = eINSTANCE.getTDataOutput_ItemSubjectRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_OUTPUT__NAME = eINSTANCE.getTDataOutput_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TDataOutputAssociationImpl <em>TData Output Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TDataOutputAssociationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataOutputAssociation()
		 * @generated
		 */
		EClass TDATA_OUTPUT_ASSOCIATION = eINSTANCE.getTDataOutputAssociation();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TDataStateImpl <em>TData State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TDataStateImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataState()
		 * @generated
		 */
		EClass TDATA_STATE = eINSTANCE.getTDataState();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_STATE__NAME = eINSTANCE.getTDataState_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TDataStoreImpl <em>TData Store</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TDataStoreImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataStore()
		 * @generated
		 */
		EClass TDATA_STORE = eINSTANCE.getTDataStore();

		/**
		 * The meta object literal for the '<em><b>Data State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDATA_STORE__DATA_STATE = eINSTANCE.getTDataStore_DataState();

		/**
		 * The meta object literal for the '<em><b>Capacity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_STORE__CAPACITY = eINSTANCE.getTDataStore_Capacity();

		/**
		 * The meta object literal for the '<em><b>Is Unlimited</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_STORE__IS_UNLIMITED = eINSTANCE.getTDataStore_IsUnlimited();

		/**
		 * The meta object literal for the '<em><b>Item Subject Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_STORE__ITEM_SUBJECT_REF = eINSTANCE.getTDataStore_ItemSubjectRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_STORE__NAME = eINSTANCE.getTDataStore_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TDataStoreReferenceImpl <em>TData Store Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TDataStoreReferenceImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDataStoreReference()
		 * @generated
		 */
		EClass TDATA_STORE_REFERENCE = eINSTANCE.getTDataStoreReference();

		/**
		 * The meta object literal for the '<em><b>Data State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDATA_STORE_REFERENCE__DATA_STATE = eINSTANCE.getTDataStoreReference_DataState();

		/**
		 * The meta object literal for the '<em><b>Data Store Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_STORE_REFERENCE__DATA_STORE_REF = eINSTANCE.getTDataStoreReference_DataStoreRef();

		/**
		 * The meta object literal for the '<em><b>Item Subject Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDATA_STORE_REFERENCE__ITEM_SUBJECT_REF = eINSTANCE.getTDataStoreReference_ItemSubjectRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TDefinitionsImpl <em>TDefinitions</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TDefinitionsImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDefinitions()
		 * @generated
		 */
		EClass TDEFINITIONS = eINSTANCE.getTDefinitions();

		/**
		 * The meta object literal for the '<em><b>Import</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDEFINITIONS__IMPORT = eINSTANCE.getTDefinitions_Import();

		/**
		 * The meta object literal for the '<em><b>Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDEFINITIONS__EXTENSION = eINSTANCE.getTDefinitions_Extension();

		/**
		 * The meta object literal for the '<em><b>Root Element Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDEFINITIONS__ROOT_ELEMENT_GROUP = eINSTANCE.getTDefinitions_RootElementGroup();

		/**
		 * The meta object literal for the '<em><b>Root Element</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDEFINITIONS__ROOT_ELEMENT = eINSTANCE.getTDefinitions_RootElement();

		/**
		 * The meta object literal for the '<em><b>BPMN Diagram</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDEFINITIONS__BPMN_DIAGRAM = eINSTANCE.getTDefinitions_BPMNDiagram();

		/**
		 * The meta object literal for the '<em><b>Relationship</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TDEFINITIONS__RELATIONSHIP = eINSTANCE.getTDefinitions_Relationship();

		/**
		 * The meta object literal for the '<em><b>Exporter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDEFINITIONS__EXPORTER = eINSTANCE.getTDefinitions_Exporter();

		/**
		 * The meta object literal for the '<em><b>Exporter Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDEFINITIONS__EXPORTER_VERSION = eINSTANCE.getTDefinitions_ExporterVersion();

		/**
		 * The meta object literal for the '<em><b>Expression Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDEFINITIONS__EXPRESSION_LANGUAGE = eINSTANCE.getTDefinitions_ExpressionLanguage();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDEFINITIONS__ID = eINSTANCE.getTDefinitions_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDEFINITIONS__NAME = eINSTANCE.getTDefinitions_Name();

		/**
		 * The meta object literal for the '<em><b>Target Namespace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDEFINITIONS__TARGET_NAMESPACE = eINSTANCE.getTDefinitions_TargetNamespace();

		/**
		 * The meta object literal for the '<em><b>Type Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDEFINITIONS__TYPE_LANGUAGE = eINSTANCE.getTDefinitions_TypeLanguage();

		/**
		 * The meta object literal for the '<em><b>Any Attribute</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDEFINITIONS__ANY_ATTRIBUTE = eINSTANCE.getTDefinitions_AnyAttribute();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TDocumentationImpl <em>TDocumentation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TDocumentationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTDocumentation()
		 * @generated
		 */
		EClass TDOCUMENTATION = eINSTANCE.getTDocumentation();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDOCUMENTATION__MIXED = eINSTANCE.getTDocumentation_Mixed();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDOCUMENTATION__ANY = eINSTANCE.getTDocumentation_Any();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDOCUMENTATION__ID = eINSTANCE.getTDocumentation_Id();

		/**
		 * The meta object literal for the '<em><b>Text Format</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TDOCUMENTATION__TEXT_FORMAT = eINSTANCE.getTDocumentation_TextFormat();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TEndEventImpl <em>TEnd Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TEndEventImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEndEvent()
		 * @generated
		 */
		EClass TEND_EVENT = eINSTANCE.getTEndEvent();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TEndPointImpl <em>TEnd Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TEndPointImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEndPoint()
		 * @generated
		 */
		EClass TEND_POINT = eINSTANCE.getTEndPoint();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TErrorImpl <em>TError</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TErrorImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTError()
		 * @generated
		 */
		EClass TERROR = eINSTANCE.getTError();

		/**
		 * The meta object literal for the '<em><b>Error Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERROR__ERROR_CODE = eINSTANCE.getTError_ErrorCode();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERROR__NAME = eINSTANCE.getTError_Name();

		/**
		 * The meta object literal for the '<em><b>Structure Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERROR__STRUCTURE_REF = eINSTANCE.getTError_StructureRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TErrorEventDefinitionImpl <em>TError Event Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TErrorEventDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTErrorEventDefinition()
		 * @generated
		 */
		EClass TERROR_EVENT_DEFINITION = eINSTANCE.getTErrorEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Error Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERROR_EVENT_DEFINITION__ERROR_REF = eINSTANCE.getTErrorEventDefinition_ErrorRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TEscalationImpl <em>TEscalation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TEscalationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEscalation()
		 * @generated
		 */
		EClass TESCALATION = eINSTANCE.getTEscalation();

		/**
		 * The meta object literal for the '<em><b>Escalation Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TESCALATION__ESCALATION_CODE = eINSTANCE.getTEscalation_EscalationCode();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TESCALATION__NAME = eINSTANCE.getTEscalation_Name();

		/**
		 * The meta object literal for the '<em><b>Structure Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TESCALATION__STRUCTURE_REF = eINSTANCE.getTEscalation_StructureRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TEscalationEventDefinitionImpl <em>TEscalation Event Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TEscalationEventDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEscalationEventDefinition()
		 * @generated
		 */
		EClass TESCALATION_EVENT_DEFINITION = eINSTANCE.getTEscalationEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Escalation Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TESCALATION_EVENT_DEFINITION__ESCALATION_REF = eINSTANCE.getTEscalationEventDefinition_EscalationRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TEventImpl <em>TEvent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TEventImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEvent()
		 * @generated
		 */
		EClass TEVENT = eINSTANCE.getTEvent();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEVENT__PROPERTY = eINSTANCE.getTEvent_Property();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TEventBasedGatewayImpl <em>TEvent Based Gateway</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TEventBasedGatewayImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEventBasedGateway()
		 * @generated
		 */
		EClass TEVENT_BASED_GATEWAY = eINSTANCE.getTEventBasedGateway();

		/**
		 * The meta object literal for the '<em><b>Event Gateway Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEVENT_BASED_GATEWAY__EVENT_GATEWAY_TYPE = eINSTANCE.getTEventBasedGateway_EventGatewayType();

		/**
		 * The meta object literal for the '<em><b>Instantiate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEVENT_BASED_GATEWAY__INSTANTIATE = eINSTANCE.getTEventBasedGateway_Instantiate();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TEventDefinitionImpl <em>TEvent Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TEventDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEventDefinition()
		 * @generated
		 */
		EClass TEVENT_DEFINITION = eINSTANCE.getTEventDefinition();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TExclusiveGatewayImpl <em>TExclusive Gateway</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TExclusiveGatewayImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTExclusiveGateway()
		 * @generated
		 */
		EClass TEXCLUSIVE_GATEWAY = eINSTANCE.getTExclusiveGateway();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXCLUSIVE_GATEWAY__DEFAULT = eINSTANCE.getTExclusiveGateway_Default();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TExpressionImpl <em>TExpression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TExpressionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTExpression()
		 * @generated
		 */
		EClass TEXPRESSION = eINSTANCE.getTExpression();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TExtensionImpl <em>TExtension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TExtensionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTExtension()
		 * @generated
		 */
		EClass TEXTENSION = eINSTANCE.getTExtension();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEXTENSION__DOCUMENTATION = eINSTANCE.getTExtension_Documentation();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXTENSION__DEFINITION = eINSTANCE.getTExtension_Definition();

		/**
		 * The meta object literal for the '<em><b>Must Understand</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXTENSION__MUST_UNDERSTAND = eINSTANCE.getTExtension_MustUnderstand();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TExtensionElementsImpl <em>TExtension Elements</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TExtensionElementsImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTExtensionElements()
		 * @generated
		 */
		EClass TEXTENSION_ELEMENTS = eINSTANCE.getTExtensionElements();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXTENSION_ELEMENTS__ANY = eINSTANCE.getTExtensionElements_Any();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TFlowElementImpl <em>TFlow Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TFlowElementImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTFlowElement()
		 * @generated
		 */
		EClass TFLOW_ELEMENT = eINSTANCE.getTFlowElement();

		/**
		 * The meta object literal for the '<em><b>Auditing</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TFLOW_ELEMENT__AUDITING = eINSTANCE.getTFlowElement_Auditing();

		/**
		 * The meta object literal for the '<em><b>Monitoring</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TFLOW_ELEMENT__MONITORING = eINSTANCE.getTFlowElement_Monitoring();

		/**
		 * The meta object literal for the '<em><b>Category Value Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TFLOW_ELEMENT__CATEGORY_VALUE_REF = eINSTANCE.getTFlowElement_CategoryValueRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TFLOW_ELEMENT__NAME = eINSTANCE.getTFlowElement_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TFlowNodeImpl <em>TFlow Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TFlowNodeImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTFlowNode()
		 * @generated
		 */
		EClass TFLOW_NODE = eINSTANCE.getTFlowNode();

		/**
		 * The meta object literal for the '<em><b>Incoming</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TFLOW_NODE__INCOMING = eINSTANCE.getTFlowNode_Incoming();

		/**
		 * The meta object literal for the '<em><b>Outgoing</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TFLOW_NODE__OUTGOING = eINSTANCE.getTFlowNode_Outgoing();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TFormalExpressionImpl <em>TFormal Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TFormalExpressionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTFormalExpression()
		 * @generated
		 */
		EClass TFORMAL_EXPRESSION = eINSTANCE.getTFormalExpression();

		/**
		 * The meta object literal for the '<em><b>Evaluates To Type Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TFORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF = eINSTANCE.getTFormalExpression_EvaluatesToTypeRef();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TFORMAL_EXPRESSION__LANGUAGE = eINSTANCE.getTFormalExpression_Language();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TGatewayImpl <em>TGateway</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TGatewayImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGateway()
		 * @generated
		 */
		EClass TGATEWAY = eINSTANCE.getTGateway();

		/**
		 * The meta object literal for the '<em><b>Gateway Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TGATEWAY__GATEWAY_DIRECTION = eINSTANCE.getTGateway_GatewayDirection();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TGlobalBusinessRuleTaskImpl <em>TGlobal Business Rule Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TGlobalBusinessRuleTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalBusinessRuleTask()
		 * @generated
		 */
		EClass TGLOBAL_BUSINESS_RULE_TASK = eINSTANCE.getTGlobalBusinessRuleTask();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TGLOBAL_BUSINESS_RULE_TASK__IMPLEMENTATION = eINSTANCE.getTGlobalBusinessRuleTask_Implementation();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TGlobalChoreographyTaskImpl <em>TGlobal Choreography Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TGlobalChoreographyTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalChoreographyTask()
		 * @generated
		 */
		EClass TGLOBAL_CHOREOGRAPHY_TASK = eINSTANCE.getTGlobalChoreographyTask();

		/**
		 * The meta object literal for the '<em><b>Initiating Participant Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TGLOBAL_CHOREOGRAPHY_TASK__INITIATING_PARTICIPANT_REF = eINSTANCE.getTGlobalChoreographyTask_InitiatingParticipantRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TGlobalConversationImpl <em>TGlobal Conversation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TGlobalConversationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalConversation()
		 * @generated
		 */
		EClass TGLOBAL_CONVERSATION = eINSTANCE.getTGlobalConversation();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TGlobalManualTaskImpl <em>TGlobal Manual Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TGlobalManualTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalManualTask()
		 * @generated
		 */
		EClass TGLOBAL_MANUAL_TASK = eINSTANCE.getTGlobalManualTask();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TGlobalScriptTaskImpl <em>TGlobal Script Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TGlobalScriptTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalScriptTask()
		 * @generated
		 */
		EClass TGLOBAL_SCRIPT_TASK = eINSTANCE.getTGlobalScriptTask();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TGLOBAL_SCRIPT_TASK__SCRIPT = eINSTANCE.getTGlobalScriptTask_Script();

		/**
		 * The meta object literal for the '<em><b>Script Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TGLOBAL_SCRIPT_TASK__SCRIPT_LANGUAGE = eINSTANCE.getTGlobalScriptTask_ScriptLanguage();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TGlobalTaskImpl <em>TGlobal Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TGlobalTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalTask()
		 * @generated
		 */
		EClass TGLOBAL_TASK = eINSTANCE.getTGlobalTask();

		/**
		 * The meta object literal for the '<em><b>Resource Role Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TGLOBAL_TASK__RESOURCE_ROLE_GROUP = eINSTANCE.getTGlobalTask_ResourceRoleGroup();

		/**
		 * The meta object literal for the '<em><b>Resource Role</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TGLOBAL_TASK__RESOURCE_ROLE = eINSTANCE.getTGlobalTask_ResourceRole();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TGlobalUserTaskImpl <em>TGlobal User Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TGlobalUserTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGlobalUserTask()
		 * @generated
		 */
		EClass TGLOBAL_USER_TASK = eINSTANCE.getTGlobalUserTask();

		/**
		 * The meta object literal for the '<em><b>Rendering</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TGLOBAL_USER_TASK__RENDERING = eINSTANCE.getTGlobalUserTask_Rendering();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TGLOBAL_USER_TASK__IMPLEMENTATION = eINSTANCE.getTGlobalUserTask_Implementation();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TGroupImpl <em>TGroup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TGroupImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGroup()
		 * @generated
		 */
		EClass TGROUP = eINSTANCE.getTGroup();

		/**
		 * The meta object literal for the '<em><b>Category Value Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TGROUP__CATEGORY_VALUE_REF = eINSTANCE.getTGroup_CategoryValueRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.THumanPerformerImpl <em>THuman Performer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.THumanPerformerImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTHumanPerformer()
		 * @generated
		 */
		EClass THUMAN_PERFORMER = eINSTANCE.getTHumanPerformer();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TImplicitThrowEventImpl <em>TImplicit Throw Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TImplicitThrowEventImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTImplicitThrowEvent()
		 * @generated
		 */
		EClass TIMPLICIT_THROW_EVENT = eINSTANCE.getTImplicitThrowEvent();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TImportImpl <em>TImport</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TImportImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTImport()
		 * @generated
		 */
		EClass TIMPORT = eINSTANCE.getTImport();

		/**
		 * The meta object literal for the '<em><b>Import Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMPORT__IMPORT_TYPE = eINSTANCE.getTImport_ImportType();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMPORT__LOCATION = eINSTANCE.getTImport_Location();

		/**
		 * The meta object literal for the '<em><b>Namespace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMPORT__NAMESPACE = eINSTANCE.getTImport_Namespace();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TInclusiveGatewayImpl <em>TInclusive Gateway</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TInclusiveGatewayImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTInclusiveGateway()
		 * @generated
		 */
		EClass TINCLUSIVE_GATEWAY = eINSTANCE.getTInclusiveGateway();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINCLUSIVE_GATEWAY__DEFAULT = eINSTANCE.getTInclusiveGateway_Default();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TInputOutputBindingImpl <em>TInput Output Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TInputOutputBindingImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTInputOutputBinding()
		 * @generated
		 */
		EClass TINPUT_OUTPUT_BINDING = eINSTANCE.getTInputOutputBinding();

		/**
		 * The meta object literal for the '<em><b>Input Data Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINPUT_OUTPUT_BINDING__INPUT_DATA_REF = eINSTANCE.getTInputOutputBinding_InputDataRef();

		/**
		 * The meta object literal for the '<em><b>Operation Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINPUT_OUTPUT_BINDING__OPERATION_REF = eINSTANCE.getTInputOutputBinding_OperationRef();

		/**
		 * The meta object literal for the '<em><b>Output Data Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINPUT_OUTPUT_BINDING__OUTPUT_DATA_REF = eINSTANCE.getTInputOutputBinding_OutputDataRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TInputOutputSpecificationImpl <em>TInput Output Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TInputOutputSpecificationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTInputOutputSpecification()
		 * @generated
		 */
		EClass TINPUT_OUTPUT_SPECIFICATION = eINSTANCE.getTInputOutputSpecification();

		/**
		 * The meta object literal for the '<em><b>Data Input</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TINPUT_OUTPUT_SPECIFICATION__DATA_INPUT = eINSTANCE.getTInputOutputSpecification_DataInput();

		/**
		 * The meta object literal for the '<em><b>Data Output</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TINPUT_OUTPUT_SPECIFICATION__DATA_OUTPUT = eINSTANCE.getTInputOutputSpecification_DataOutput();

		/**
		 * The meta object literal for the '<em><b>Input Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TINPUT_OUTPUT_SPECIFICATION__INPUT_SET = eINSTANCE.getTInputOutputSpecification_InputSet();

		/**
		 * The meta object literal for the '<em><b>Output Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TINPUT_OUTPUT_SPECIFICATION__OUTPUT_SET = eINSTANCE.getTInputOutputSpecification_OutputSet();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TInputSetImpl <em>TInput Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TInputSetImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTInputSet()
		 * @generated
		 */
		EClass TINPUT_SET = eINSTANCE.getTInputSet();

		/**
		 * The meta object literal for the '<em><b>Data Input Refs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINPUT_SET__DATA_INPUT_REFS = eINSTANCE.getTInputSet_DataInputRefs();

		/**
		 * The meta object literal for the '<em><b>Optional Input Refs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINPUT_SET__OPTIONAL_INPUT_REFS = eINSTANCE.getTInputSet_OptionalInputRefs();

		/**
		 * The meta object literal for the '<em><b>While Executing Input Refs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINPUT_SET__WHILE_EXECUTING_INPUT_REFS = eINSTANCE.getTInputSet_WhileExecutingInputRefs();

		/**
		 * The meta object literal for the '<em><b>Output Set Refs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINPUT_SET__OUTPUT_SET_REFS = eINSTANCE.getTInputSet_OutputSetRefs();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINPUT_SET__NAME = eINSTANCE.getTInputSet_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TInterfaceImpl <em>TInterface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TInterfaceImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTInterface()
		 * @generated
		 */
		EClass TINTERFACE = eINSTANCE.getTInterface();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TINTERFACE__OPERATION = eINSTANCE.getTInterface_Operation();

		/**
		 * The meta object literal for the '<em><b>Implementation Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINTERFACE__IMPLEMENTATION_REF = eINSTANCE.getTInterface_ImplementationRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TINTERFACE__NAME = eINSTANCE.getTInterface_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TIntermediateCatchEventImpl <em>TIntermediate Catch Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TIntermediateCatchEventImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTIntermediateCatchEvent()
		 * @generated
		 */
		EClass TINTERMEDIATE_CATCH_EVENT = eINSTANCE.getTIntermediateCatchEvent();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TIntermediateThrowEventImpl <em>TIntermediate Throw Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TIntermediateThrowEventImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTIntermediateThrowEvent()
		 * @generated
		 */
		EClass TINTERMEDIATE_THROW_EVENT = eINSTANCE.getTIntermediateThrowEvent();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TItemDefinitionImpl <em>TItem Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TItemDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTItemDefinition()
		 * @generated
		 */
		EClass TITEM_DEFINITION = eINSTANCE.getTItemDefinition();

		/**
		 * The meta object literal for the '<em><b>Is Collection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TITEM_DEFINITION__IS_COLLECTION = eINSTANCE.getTItemDefinition_IsCollection();

		/**
		 * The meta object literal for the '<em><b>Item Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TITEM_DEFINITION__ITEM_KIND = eINSTANCE.getTItemDefinition_ItemKind();

		/**
		 * The meta object literal for the '<em><b>Structure Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TITEM_DEFINITION__STRUCTURE_REF = eINSTANCE.getTItemDefinition_StructureRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TLaneImpl <em>TLane</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TLaneImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTLane()
		 * @generated
		 */
		EClass TLANE = eINSTANCE.getTLane();

		/**
		 * The meta object literal for the '<em><b>Partition Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TLANE__PARTITION_ELEMENT = eINSTANCE.getTLane_PartitionElement();

		/**
		 * The meta object literal for the '<em><b>Flow Node Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLANE__FLOW_NODE_REF = eINSTANCE.getTLane_FlowNodeRef();

		/**
		 * The meta object literal for the '<em><b>Child Lane Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TLANE__CHILD_LANE_SET = eINSTANCE.getTLane_ChildLaneSet();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLANE__NAME = eINSTANCE.getTLane_Name();

		/**
		 * The meta object literal for the '<em><b>Partition Element Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLANE__PARTITION_ELEMENT_REF = eINSTANCE.getTLane_PartitionElementRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TLaneSetImpl <em>TLane Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TLaneSetImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTLaneSet()
		 * @generated
		 */
		EClass TLANE_SET = eINSTANCE.getTLaneSet();

		/**
		 * The meta object literal for the '<em><b>Lane</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TLANE_SET__LANE = eINSTANCE.getTLaneSet_Lane();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLANE_SET__NAME = eINSTANCE.getTLaneSet_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TLinkEventDefinitionImpl <em>TLink Event Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TLinkEventDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTLinkEventDefinition()
		 * @generated
		 */
		EClass TLINK_EVENT_DEFINITION = eINSTANCE.getTLinkEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLINK_EVENT_DEFINITION__SOURCE = eINSTANCE.getTLinkEventDefinition_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLINK_EVENT_DEFINITION__TARGET = eINSTANCE.getTLinkEventDefinition_Target();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLINK_EVENT_DEFINITION__NAME = eINSTANCE.getTLinkEventDefinition_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TLoopCharacteristicsImpl <em>TLoop Characteristics</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TLoopCharacteristicsImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTLoopCharacteristics()
		 * @generated
		 */
		EClass TLOOP_CHARACTERISTICS = eINSTANCE.getTLoopCharacteristics();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TManualTaskImpl <em>TManual Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TManualTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTManualTask()
		 * @generated
		 */
		EClass TMANUAL_TASK = eINSTANCE.getTManualTask();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TMessageImpl <em>TMessage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TMessageImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMessage()
		 * @generated
		 */
		EClass TMESSAGE = eINSTANCE.getTMessage();

		/**
		 * The meta object literal for the '<em><b>Item Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMESSAGE__ITEM_REF = eINSTANCE.getTMessage_ItemRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMESSAGE__NAME = eINSTANCE.getTMessage_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TMessageEventDefinitionImpl <em>TMessage Event Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TMessageEventDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMessageEventDefinition()
		 * @generated
		 */
		EClass TMESSAGE_EVENT_DEFINITION = eINSTANCE.getTMessageEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Operation Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMESSAGE_EVENT_DEFINITION__OPERATION_REF = eINSTANCE.getTMessageEventDefinition_OperationRef();

		/**
		 * The meta object literal for the '<em><b>Message Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMESSAGE_EVENT_DEFINITION__MESSAGE_REF = eINSTANCE.getTMessageEventDefinition_MessageRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TMessageFlowImpl <em>TMessage Flow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TMessageFlowImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMessageFlow()
		 * @generated
		 */
		EClass TMESSAGE_FLOW = eINSTANCE.getTMessageFlow();

		/**
		 * The meta object literal for the '<em><b>Message Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMESSAGE_FLOW__MESSAGE_REF = eINSTANCE.getTMessageFlow_MessageRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMESSAGE_FLOW__NAME = eINSTANCE.getTMessageFlow_Name();

		/**
		 * The meta object literal for the '<em><b>Source Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMESSAGE_FLOW__SOURCE_REF = eINSTANCE.getTMessageFlow_SourceRef();

		/**
		 * The meta object literal for the '<em><b>Target Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMESSAGE_FLOW__TARGET_REF = eINSTANCE.getTMessageFlow_TargetRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TMessageFlowAssociationImpl <em>TMessage Flow Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TMessageFlowAssociationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMessageFlowAssociation()
		 * @generated
		 */
		EClass TMESSAGE_FLOW_ASSOCIATION = eINSTANCE.getTMessageFlowAssociation();

		/**
		 * The meta object literal for the '<em><b>Inner Message Flow Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMESSAGE_FLOW_ASSOCIATION__INNER_MESSAGE_FLOW_REF = eINSTANCE.getTMessageFlowAssociation_InnerMessageFlowRef();

		/**
		 * The meta object literal for the '<em><b>Outer Message Flow Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMESSAGE_FLOW_ASSOCIATION__OUTER_MESSAGE_FLOW_REF = eINSTANCE.getTMessageFlowAssociation_OuterMessageFlowRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TMonitoringImpl <em>TMonitoring</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TMonitoringImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMonitoring()
		 * @generated
		 */
		EClass TMONITORING = eINSTANCE.getTMonitoring();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TMultiInstanceLoopCharacteristicsImpl <em>TMulti Instance Loop Characteristics</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TMultiInstanceLoopCharacteristicsImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMultiInstanceLoopCharacteristics()
		 * @generated
		 */
		EClass TMULTI_INSTANCE_LOOP_CHARACTERISTICS = eINSTANCE.getTMultiInstanceLoopCharacteristics();

		/**
		 * The meta object literal for the '<em><b>Loop Cardinality</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMULTI_INSTANCE_LOOP_CHARACTERISTICS__LOOP_CARDINALITY = eINSTANCE.getTMultiInstanceLoopCharacteristics_LoopCardinality();

		/**
		 * The meta object literal for the '<em><b>Loop Data Input Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMULTI_INSTANCE_LOOP_CHARACTERISTICS__LOOP_DATA_INPUT_REF = eINSTANCE.getTMultiInstanceLoopCharacteristics_LoopDataInputRef();

		/**
		 * The meta object literal for the '<em><b>Loop Data Output Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMULTI_INSTANCE_LOOP_CHARACTERISTICS__LOOP_DATA_OUTPUT_REF = eINSTANCE.getTMultiInstanceLoopCharacteristics_LoopDataOutputRef();

		/**
		 * The meta object literal for the '<em><b>Input Data Item</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMULTI_INSTANCE_LOOP_CHARACTERISTICS__INPUT_DATA_ITEM = eINSTANCE.getTMultiInstanceLoopCharacteristics_InputDataItem();

		/**
		 * The meta object literal for the '<em><b>Output Data Item</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMULTI_INSTANCE_LOOP_CHARACTERISTICS__OUTPUT_DATA_ITEM = eINSTANCE.getTMultiInstanceLoopCharacteristics_OutputDataItem();

		/**
		 * The meta object literal for the '<em><b>Complex Behavior Definition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMULTI_INSTANCE_LOOP_CHARACTERISTICS__COMPLEX_BEHAVIOR_DEFINITION = eINSTANCE.getTMultiInstanceLoopCharacteristics_ComplexBehaviorDefinition();

		/**
		 * The meta object literal for the '<em><b>Completion Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMULTI_INSTANCE_LOOP_CHARACTERISTICS__COMPLETION_CONDITION = eINSTANCE.getTMultiInstanceLoopCharacteristics_CompletionCondition();

		/**
		 * The meta object literal for the '<em><b>Behavior</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMULTI_INSTANCE_LOOP_CHARACTERISTICS__BEHAVIOR = eINSTANCE.getTMultiInstanceLoopCharacteristics_Behavior();

		/**
		 * The meta object literal for the '<em><b>Is Sequential</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMULTI_INSTANCE_LOOP_CHARACTERISTICS__IS_SEQUENTIAL = eINSTANCE.getTMultiInstanceLoopCharacteristics_IsSequential();

		/**
		 * The meta object literal for the '<em><b>None Behavior Event Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMULTI_INSTANCE_LOOP_CHARACTERISTICS__NONE_BEHAVIOR_EVENT_REF = eINSTANCE.getTMultiInstanceLoopCharacteristics_NoneBehaviorEventRef();

		/**
		 * The meta object literal for the '<em><b>One Behavior Event Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMULTI_INSTANCE_LOOP_CHARACTERISTICS__ONE_BEHAVIOR_EVENT_REF = eINSTANCE.getTMultiInstanceLoopCharacteristics_OneBehaviorEventRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TOperationImpl <em>TOperation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TOperationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTOperation()
		 * @generated
		 */
		EClass TOPERATION = eINSTANCE.getTOperation();

		/**
		 * The meta object literal for the '<em><b>In Message Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPERATION__IN_MESSAGE_REF = eINSTANCE.getTOperation_InMessageRef();

		/**
		 * The meta object literal for the '<em><b>Out Message Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPERATION__OUT_MESSAGE_REF = eINSTANCE.getTOperation_OutMessageRef();

		/**
		 * The meta object literal for the '<em><b>Error Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPERATION__ERROR_REF = eINSTANCE.getTOperation_ErrorRef();

		/**
		 * The meta object literal for the '<em><b>Implementation Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPERATION__IMPLEMENTATION_REF = eINSTANCE.getTOperation_ImplementationRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPERATION__NAME = eINSTANCE.getTOperation_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TOutputSetImpl <em>TOutput Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TOutputSetImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTOutputSet()
		 * @generated
		 */
		EClass TOUTPUT_SET = eINSTANCE.getTOutputSet();

		/**
		 * The meta object literal for the '<em><b>Data Output Refs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOUTPUT_SET__DATA_OUTPUT_REFS = eINSTANCE.getTOutputSet_DataOutputRefs();

		/**
		 * The meta object literal for the '<em><b>Optional Output Refs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOUTPUT_SET__OPTIONAL_OUTPUT_REFS = eINSTANCE.getTOutputSet_OptionalOutputRefs();

		/**
		 * The meta object literal for the '<em><b>While Executing Output Refs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOUTPUT_SET__WHILE_EXECUTING_OUTPUT_REFS = eINSTANCE.getTOutputSet_WhileExecutingOutputRefs();

		/**
		 * The meta object literal for the '<em><b>Input Set Refs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOUTPUT_SET__INPUT_SET_REFS = eINSTANCE.getTOutputSet_InputSetRefs();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOUTPUT_SET__NAME = eINSTANCE.getTOutputSet_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TParallelGatewayImpl <em>TParallel Gateway</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TParallelGatewayImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTParallelGateway()
		 * @generated
		 */
		EClass TPARALLEL_GATEWAY = eINSTANCE.getTParallelGateway();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TParticipantImpl <em>TParticipant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TParticipantImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTParticipant()
		 * @generated
		 */
		EClass TPARTICIPANT = eINSTANCE.getTParticipant();

		/**
		 * The meta object literal for the '<em><b>Interface Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPARTICIPANT__INTERFACE_REF = eINSTANCE.getTParticipant_InterfaceRef();

		/**
		 * The meta object literal for the '<em><b>End Point Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPARTICIPANT__END_POINT_REF = eINSTANCE.getTParticipant_EndPointRef();

		/**
		 * The meta object literal for the '<em><b>Participant Multiplicity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TPARTICIPANT__PARTICIPANT_MULTIPLICITY = eINSTANCE.getTParticipant_ParticipantMultiplicity();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPARTICIPANT__NAME = eINSTANCE.getTParticipant_Name();

		/**
		 * The meta object literal for the '<em><b>Process Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPARTICIPANT__PROCESS_REF = eINSTANCE.getTParticipant_ProcessRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TParticipantAssociationImpl <em>TParticipant Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TParticipantAssociationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTParticipantAssociation()
		 * @generated
		 */
		EClass TPARTICIPANT_ASSOCIATION = eINSTANCE.getTParticipantAssociation();

		/**
		 * The meta object literal for the '<em><b>Inner Participant Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF = eINSTANCE.getTParticipantAssociation_InnerParticipantRef();

		/**
		 * The meta object literal for the '<em><b>Outer Participant Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF = eINSTANCE.getTParticipantAssociation_OuterParticipantRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TParticipantMultiplicityImpl <em>TParticipant Multiplicity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TParticipantMultiplicityImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTParticipantMultiplicity()
		 * @generated
		 */
		EClass TPARTICIPANT_MULTIPLICITY = eINSTANCE.getTParticipantMultiplicity();

		/**
		 * The meta object literal for the '<em><b>Maximum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPARTICIPANT_MULTIPLICITY__MAXIMUM = eINSTANCE.getTParticipantMultiplicity_Maximum();

		/**
		 * The meta object literal for the '<em><b>Minimum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPARTICIPANT_MULTIPLICITY__MINIMUM = eINSTANCE.getTParticipantMultiplicity_Minimum();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TPartnerEntityImpl <em>TPartner Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TPartnerEntityImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTPartnerEntity()
		 * @generated
		 */
		EClass TPARTNER_ENTITY = eINSTANCE.getTPartnerEntity();

		/**
		 * The meta object literal for the '<em><b>Participant Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPARTNER_ENTITY__PARTICIPANT_REF = eINSTANCE.getTPartnerEntity_ParticipantRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPARTNER_ENTITY__NAME = eINSTANCE.getTPartnerEntity_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TPartnerRoleImpl <em>TPartner Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TPartnerRoleImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTPartnerRole()
		 * @generated
		 */
		EClass TPARTNER_ROLE = eINSTANCE.getTPartnerRole();

		/**
		 * The meta object literal for the '<em><b>Participant Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPARTNER_ROLE__PARTICIPANT_REF = eINSTANCE.getTPartnerRole_ParticipantRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPARTNER_ROLE__NAME = eINSTANCE.getTPartnerRole_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TPerformerImpl <em>TPerformer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TPerformerImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTPerformer()
		 * @generated
		 */
		EClass TPERFORMER = eINSTANCE.getTPerformer();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TPotentialOwnerImpl <em>TPotential Owner</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TPotentialOwnerImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTPotentialOwner()
		 * @generated
		 */
		EClass TPOTENTIAL_OWNER = eINSTANCE.getTPotentialOwner();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TProcessImpl <em>TProcess</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TProcessImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTProcess()
		 * @generated
		 */
		EClass TPROCESS = eINSTANCE.getTProcess();

		/**
		 * The meta object literal for the '<em><b>Auditing</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TPROCESS__AUDITING = eINSTANCE.getTProcess_Auditing();

		/**
		 * The meta object literal for the '<em><b>Monitoring</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TPROCESS__MONITORING = eINSTANCE.getTProcess_Monitoring();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TPROCESS__PROPERTY = eINSTANCE.getTProcess_Property();

		/**
		 * The meta object literal for the '<em><b>Lane Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TPROCESS__LANE_SET = eINSTANCE.getTProcess_LaneSet();

		/**
		 * The meta object literal for the '<em><b>Flow Element Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPROCESS__FLOW_ELEMENT_GROUP = eINSTANCE.getTProcess_FlowElementGroup();

		/**
		 * The meta object literal for the '<em><b>Flow Element</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TPROCESS__FLOW_ELEMENT = eINSTANCE.getTProcess_FlowElement();

		/**
		 * The meta object literal for the '<em><b>Artifact Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPROCESS__ARTIFACT_GROUP = eINSTANCE.getTProcess_ArtifactGroup();

		/**
		 * The meta object literal for the '<em><b>Artifact</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TPROCESS__ARTIFACT = eINSTANCE.getTProcess_Artifact();

		/**
		 * The meta object literal for the '<em><b>Resource Role Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPROCESS__RESOURCE_ROLE_GROUP = eINSTANCE.getTProcess_ResourceRoleGroup();

		/**
		 * The meta object literal for the '<em><b>Resource Role</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TPROCESS__RESOURCE_ROLE = eINSTANCE.getTProcess_ResourceRole();

		/**
		 * The meta object literal for the '<em><b>Correlation Subscription</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TPROCESS__CORRELATION_SUBSCRIPTION = eINSTANCE.getTProcess_CorrelationSubscription();

		/**
		 * The meta object literal for the '<em><b>Supports</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPROCESS__SUPPORTS = eINSTANCE.getTProcess_Supports();

		/**
		 * The meta object literal for the '<em><b>Definitional Collaboration Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPROCESS__DEFINITIONAL_COLLABORATION_REF = eINSTANCE.getTProcess_DefinitionalCollaborationRef();

		/**
		 * The meta object literal for the '<em><b>Is Closed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPROCESS__IS_CLOSED = eINSTANCE.getTProcess_IsClosed();

		/**
		 * The meta object literal for the '<em><b>Is Executable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPROCESS__IS_EXECUTABLE = eINSTANCE.getTProcess_IsExecutable();

		/**
		 * The meta object literal for the '<em><b>Process Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPROCESS__PROCESS_TYPE = eINSTANCE.getTProcess_ProcessType();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TPropertyImpl <em>TProperty</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TPropertyImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTProperty()
		 * @generated
		 */
		EClass TPROPERTY = eINSTANCE.getTProperty();

		/**
		 * The meta object literal for the '<em><b>Data State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TPROPERTY__DATA_STATE = eINSTANCE.getTProperty_DataState();

		/**
		 * The meta object literal for the '<em><b>Item Subject Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPROPERTY__ITEM_SUBJECT_REF = eINSTANCE.getTProperty_ItemSubjectRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TPROPERTY__NAME = eINSTANCE.getTProperty_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TReceiveTaskImpl <em>TReceive Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TReceiveTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTReceiveTask()
		 * @generated
		 */
		EClass TRECEIVE_TASK = eINSTANCE.getTReceiveTask();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRECEIVE_TASK__IMPLEMENTATION = eINSTANCE.getTReceiveTask_Implementation();

		/**
		 * The meta object literal for the '<em><b>Instantiate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRECEIVE_TASK__INSTANTIATE = eINSTANCE.getTReceiveTask_Instantiate();

		/**
		 * The meta object literal for the '<em><b>Message Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRECEIVE_TASK__MESSAGE_REF = eINSTANCE.getTReceiveTask_MessageRef();

		/**
		 * The meta object literal for the '<em><b>Operation Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRECEIVE_TASK__OPERATION_REF = eINSTANCE.getTReceiveTask_OperationRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TRelationshipImpl <em>TRelationship</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TRelationshipImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTRelationship()
		 * @generated
		 */
		EClass TRELATIONSHIP = eINSTANCE.getTRelationship();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRELATIONSHIP__SOURCE = eINSTANCE.getTRelationship_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRELATIONSHIP__TARGET = eINSTANCE.getTRelationship_Target();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRELATIONSHIP__DIRECTION = eINSTANCE.getTRelationship_Direction();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRELATIONSHIP__TYPE = eINSTANCE.getTRelationship_Type();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TRenderingImpl <em>TRendering</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TRenderingImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTRendering()
		 * @generated
		 */
		EClass TRENDERING = eINSTANCE.getTRendering();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TResourceImpl <em>TResource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TResourceImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTResource()
		 * @generated
		 */
		EClass TRESOURCE = eINSTANCE.getTResource();

		/**
		 * The meta object literal for the '<em><b>Resource Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRESOURCE__RESOURCE_PARAMETER = eINSTANCE.getTResource_ResourceParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRESOURCE__NAME = eINSTANCE.getTResource_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TResourceAssignmentExpressionImpl <em>TResource Assignment Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TResourceAssignmentExpressionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTResourceAssignmentExpression()
		 * @generated
		 */
		EClass TRESOURCE_ASSIGNMENT_EXPRESSION = eINSTANCE.getTResourceAssignmentExpression();

		/**
		 * The meta object literal for the '<em><b>Expression Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION_GROUP = eINSTANCE.getTResourceAssignmentExpression_ExpressionGroup();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION = eINSTANCE.getTResourceAssignmentExpression_Expression();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TResourceParameterImpl <em>TResource Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TResourceParameterImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTResourceParameter()
		 * @generated
		 */
		EClass TRESOURCE_PARAMETER = eINSTANCE.getTResourceParameter();

		/**
		 * The meta object literal for the '<em><b>Is Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRESOURCE_PARAMETER__IS_REQUIRED = eINSTANCE.getTResourceParameter_IsRequired();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRESOURCE_PARAMETER__NAME = eINSTANCE.getTResourceParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRESOURCE_PARAMETER__TYPE = eINSTANCE.getTResourceParameter_Type();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TResourceParameterBindingImpl <em>TResource Parameter Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TResourceParameterBindingImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTResourceParameterBinding()
		 * @generated
		 */
		EClass TRESOURCE_PARAMETER_BINDING = eINSTANCE.getTResourceParameterBinding();

		/**
		 * The meta object literal for the '<em><b>Expression Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRESOURCE_PARAMETER_BINDING__EXPRESSION_GROUP = eINSTANCE.getTResourceParameterBinding_ExpressionGroup();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRESOURCE_PARAMETER_BINDING__EXPRESSION = eINSTANCE.getTResourceParameterBinding_Expression();

		/**
		 * The meta object literal for the '<em><b>Parameter Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRESOURCE_PARAMETER_BINDING__PARAMETER_REF = eINSTANCE.getTResourceParameterBinding_ParameterRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TResourceRoleImpl <em>TResource Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TResourceRoleImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTResourceRole()
		 * @generated
		 */
		EClass TRESOURCE_ROLE = eINSTANCE.getTResourceRole();

		/**
		 * The meta object literal for the '<em><b>Resource Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRESOURCE_ROLE__RESOURCE_REF = eINSTANCE.getTResourceRole_ResourceRef();

		/**
		 * The meta object literal for the '<em><b>Resource Parameter Binding</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRESOURCE_ROLE__RESOURCE_PARAMETER_BINDING = eINSTANCE.getTResourceRole_ResourceParameterBinding();

		/**
		 * The meta object literal for the '<em><b>Resource Assignment Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRESOURCE_ROLE__RESOURCE_ASSIGNMENT_EXPRESSION = eINSTANCE.getTResourceRole_ResourceAssignmentExpression();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRESOURCE_ROLE__NAME = eINSTANCE.getTResourceRole_Name();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TRootElementImpl <em>TRoot Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TRootElementImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTRootElement()
		 * @generated
		 */
		EClass TROOT_ELEMENT = eINSTANCE.getTRootElement();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TScriptImpl <em>TScript</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TScriptImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTScript()
		 * @generated
		 */
		EClass TSCRIPT = eINSTANCE.getTScript();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSCRIPT__MIXED = eINSTANCE.getTScript_Mixed();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSCRIPT__ANY = eINSTANCE.getTScript_Any();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TScriptTaskImpl <em>TScript Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TScriptTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTScriptTask()
		 * @generated
		 */
		EClass TSCRIPT_TASK = eINSTANCE.getTScriptTask();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSCRIPT_TASK__SCRIPT = eINSTANCE.getTScriptTask_Script();

		/**
		 * The meta object literal for the '<em><b>Script Format</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSCRIPT_TASK__SCRIPT_FORMAT = eINSTANCE.getTScriptTask_ScriptFormat();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TSendTaskImpl <em>TSend Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TSendTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSendTask()
		 * @generated
		 */
		EClass TSEND_TASK = eINSTANCE.getTSendTask();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSEND_TASK__IMPLEMENTATION = eINSTANCE.getTSendTask_Implementation();

		/**
		 * The meta object literal for the '<em><b>Message Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSEND_TASK__MESSAGE_REF = eINSTANCE.getTSendTask_MessageRef();

		/**
		 * The meta object literal for the '<em><b>Operation Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSEND_TASK__OPERATION_REF = eINSTANCE.getTSendTask_OperationRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TSequenceFlowImpl <em>TSequence Flow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TSequenceFlowImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSequenceFlow()
		 * @generated
		 */
		EClass TSEQUENCE_FLOW = eINSTANCE.getTSequenceFlow();

		/**
		 * The meta object literal for the '<em><b>Condition Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSEQUENCE_FLOW__CONDITION_EXPRESSION = eINSTANCE.getTSequenceFlow_ConditionExpression();

		/**
		 * The meta object literal for the '<em><b>Is Immediate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSEQUENCE_FLOW__IS_IMMEDIATE = eINSTANCE.getTSequenceFlow_IsImmediate();

		/**
		 * The meta object literal for the '<em><b>Source Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSEQUENCE_FLOW__SOURCE_REF = eINSTANCE.getTSequenceFlow_SourceRef();

		/**
		 * The meta object literal for the '<em><b>Target Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSEQUENCE_FLOW__TARGET_REF = eINSTANCE.getTSequenceFlow_TargetRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TServiceTaskImpl <em>TService Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TServiceTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTServiceTask()
		 * @generated
		 */
		EClass TSERVICE_TASK = eINSTANCE.getTServiceTask();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSERVICE_TASK__IMPLEMENTATION = eINSTANCE.getTServiceTask_Implementation();

		/**
		 * The meta object literal for the '<em><b>Operation Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSERVICE_TASK__OPERATION_REF = eINSTANCE.getTServiceTask_OperationRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TSignalImpl <em>TSignal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TSignalImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSignal()
		 * @generated
		 */
		EClass TSIGNAL = eINSTANCE.getTSignal();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSIGNAL__NAME = eINSTANCE.getTSignal_Name();

		/**
		 * The meta object literal for the '<em><b>Structure Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSIGNAL__STRUCTURE_REF = eINSTANCE.getTSignal_StructureRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TSignalEventDefinitionImpl <em>TSignal Event Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TSignalEventDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSignalEventDefinition()
		 * @generated
		 */
		EClass TSIGNAL_EVENT_DEFINITION = eINSTANCE.getTSignalEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Signal Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSIGNAL_EVENT_DEFINITION__SIGNAL_REF = eINSTANCE.getTSignalEventDefinition_SignalRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TStandardLoopCharacteristicsImpl <em>TStandard Loop Characteristics</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TStandardLoopCharacteristicsImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTStandardLoopCharacteristics()
		 * @generated
		 */
		EClass TSTANDARD_LOOP_CHARACTERISTICS = eINSTANCE.getTStandardLoopCharacteristics();

		/**
		 * The meta object literal for the '<em><b>Loop Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSTANDARD_LOOP_CHARACTERISTICS__LOOP_CONDITION = eINSTANCE.getTStandardLoopCharacteristics_LoopCondition();

		/**
		 * The meta object literal for the '<em><b>Loop Maximum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSTANDARD_LOOP_CHARACTERISTICS__LOOP_MAXIMUM = eINSTANCE.getTStandardLoopCharacteristics_LoopMaximum();

		/**
		 * The meta object literal for the '<em><b>Test Before</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSTANDARD_LOOP_CHARACTERISTICS__TEST_BEFORE = eINSTANCE.getTStandardLoopCharacteristics_TestBefore();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TStartEventImpl <em>TStart Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TStartEventImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTStartEvent()
		 * @generated
		 */
		EClass TSTART_EVENT = eINSTANCE.getTStartEvent();

		/**
		 * The meta object literal for the '<em><b>Is Interrupting</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSTART_EVENT__IS_INTERRUPTING = eINSTANCE.getTStartEvent_IsInterrupting();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TSubChoreographyImpl <em>TSub Choreography</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TSubChoreographyImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSubChoreography()
		 * @generated
		 */
		EClass TSUB_CHOREOGRAPHY = eINSTANCE.getTSubChoreography();

		/**
		 * The meta object literal for the '<em><b>Flow Element Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSUB_CHOREOGRAPHY__FLOW_ELEMENT_GROUP = eINSTANCE.getTSubChoreography_FlowElementGroup();

		/**
		 * The meta object literal for the '<em><b>Flow Element</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSUB_CHOREOGRAPHY__FLOW_ELEMENT = eINSTANCE.getTSubChoreography_FlowElement();

		/**
		 * The meta object literal for the '<em><b>Artifact Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSUB_CHOREOGRAPHY__ARTIFACT_GROUP = eINSTANCE.getTSubChoreography_ArtifactGroup();

		/**
		 * The meta object literal for the '<em><b>Artifact</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSUB_CHOREOGRAPHY__ARTIFACT = eINSTANCE.getTSubChoreography_Artifact();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TSubConversationImpl <em>TSub Conversation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TSubConversationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSubConversation()
		 * @generated
		 */
		EClass TSUB_CONVERSATION = eINSTANCE.getTSubConversation();

		/**
		 * The meta object literal for the '<em><b>Conversation Node Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSUB_CONVERSATION__CONVERSATION_NODE_GROUP = eINSTANCE.getTSubConversation_ConversationNodeGroup();

		/**
		 * The meta object literal for the '<em><b>Conversation Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSUB_CONVERSATION__CONVERSATION_NODE = eINSTANCE.getTSubConversation_ConversationNode();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TSubProcessImpl <em>TSub Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TSubProcessImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTSubProcess()
		 * @generated
		 */
		EClass TSUB_PROCESS = eINSTANCE.getTSubProcess();

		/**
		 * The meta object literal for the '<em><b>Lane Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSUB_PROCESS__LANE_SET = eINSTANCE.getTSubProcess_LaneSet();

		/**
		 * The meta object literal for the '<em><b>Flow Element Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSUB_PROCESS__FLOW_ELEMENT_GROUP = eINSTANCE.getTSubProcess_FlowElementGroup();

		/**
		 * The meta object literal for the '<em><b>Flow Element</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSUB_PROCESS__FLOW_ELEMENT = eINSTANCE.getTSubProcess_FlowElement();

		/**
		 * The meta object literal for the '<em><b>Artifact Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSUB_PROCESS__ARTIFACT_GROUP = eINSTANCE.getTSubProcess_ArtifactGroup();

		/**
		 * The meta object literal for the '<em><b>Artifact</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TSUB_PROCESS__ARTIFACT = eINSTANCE.getTSubProcess_Artifact();

		/**
		 * The meta object literal for the '<em><b>Triggered By Event</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TSUB_PROCESS__TRIGGERED_BY_EVENT = eINSTANCE.getTSubProcess_TriggeredByEvent();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TTaskImpl <em>TTask</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTask()
		 * @generated
		 */
		EClass TTASK = eINSTANCE.getTTask();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TTerminateEventDefinitionImpl <em>TTerminate Event Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TTerminateEventDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTerminateEventDefinition()
		 * @generated
		 */
		EClass TTERMINATE_EVENT_DEFINITION = eINSTANCE.getTTerminateEventDefinition();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TTextImpl <em>TText</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TTextImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTText()
		 * @generated
		 */
		EClass TTEXT = eINSTANCE.getTText();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TTEXT__MIXED = eINSTANCE.getTText_Mixed();

		/**
		 * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TTEXT__ANY = eINSTANCE.getTText_Any();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TTextAnnotationImpl <em>TText Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TTextAnnotationImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTextAnnotation()
		 * @generated
		 */
		EClass TTEXT_ANNOTATION = eINSTANCE.getTTextAnnotation();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TTEXT_ANNOTATION__TEXT = eINSTANCE.getTTextAnnotation_Text();

		/**
		 * The meta object literal for the '<em><b>Text Format</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TTEXT_ANNOTATION__TEXT_FORMAT = eINSTANCE.getTTextAnnotation_TextFormat();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TThrowEventImpl <em>TThrow Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TThrowEventImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTThrowEvent()
		 * @generated
		 */
		EClass TTHROW_EVENT = eINSTANCE.getTThrowEvent();

		/**
		 * The meta object literal for the '<em><b>Data Input</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TTHROW_EVENT__DATA_INPUT = eINSTANCE.getTThrowEvent_DataInput();

		/**
		 * The meta object literal for the '<em><b>Data Input Association</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TTHROW_EVENT__DATA_INPUT_ASSOCIATION = eINSTANCE.getTThrowEvent_DataInputAssociation();

		/**
		 * The meta object literal for the '<em><b>Input Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TTHROW_EVENT__INPUT_SET = eINSTANCE.getTThrowEvent_InputSet();

		/**
		 * The meta object literal for the '<em><b>Event Definition Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TTHROW_EVENT__EVENT_DEFINITION_GROUP = eINSTANCE.getTThrowEvent_EventDefinitionGroup();

		/**
		 * The meta object literal for the '<em><b>Event Definition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TTHROW_EVENT__EVENT_DEFINITION = eINSTANCE.getTThrowEvent_EventDefinition();

		/**
		 * The meta object literal for the '<em><b>Event Definition Ref</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TTHROW_EVENT__EVENT_DEFINITION_REF = eINSTANCE.getTThrowEvent_EventDefinitionRef();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TTimerEventDefinitionImpl <em>TTimer Event Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TTimerEventDefinitionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTimerEventDefinition()
		 * @generated
		 */
		EClass TTIMER_EVENT_DEFINITION = eINSTANCE.getTTimerEventDefinition();

		/**
		 * The meta object literal for the '<em><b>Time Date</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TTIMER_EVENT_DEFINITION__TIME_DATE = eINSTANCE.getTTimerEventDefinition_TimeDate();

		/**
		 * The meta object literal for the '<em><b>Time Duration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TTIMER_EVENT_DEFINITION__TIME_DURATION = eINSTANCE.getTTimerEventDefinition_TimeDuration();

		/**
		 * The meta object literal for the '<em><b>Time Cycle</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TTIMER_EVENT_DEFINITION__TIME_CYCLE = eINSTANCE.getTTimerEventDefinition_TimeCycle();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TTransactionImpl <em>TTransaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TTransactionImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTransaction()
		 * @generated
		 */
		EClass TTRANSACTION = eINSTANCE.getTTransaction();

		/**
		 * The meta object literal for the '<em><b>Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TTRANSACTION__METHOD = eINSTANCE.getTTransaction_Method();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.impl.TUserTaskImpl <em>TUser Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.impl.TUserTaskImpl
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTUserTask()
		 * @generated
		 */
		EClass TUSER_TASK = eINSTANCE.getTUserTask();

		/**
		 * The meta object literal for the '<em><b>Rendering</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUSER_TASK__RENDERING = eINSTANCE.getTUserTask_Rendering();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TUSER_TASK__IMPLEMENTATION = eINSTANCE.getTUserTask_Implementation();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.TAdHocOrdering <em>TAd Hoc Ordering</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TAdHocOrdering
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAdHocOrdering()
		 * @generated
		 */
		EEnum TAD_HOC_ORDERING = eINSTANCE.getTAdHocOrdering();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.TAssociationDirection <em>TAssociation Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TAssociationDirection
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAssociationDirection()
		 * @generated
		 */
		EEnum TASSOCIATION_DIRECTION = eINSTANCE.getTAssociationDirection();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.TChoreographyLoopType <em>TChoreography Loop Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TChoreographyLoopType
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTChoreographyLoopType()
		 * @generated
		 */
		EEnum TCHOREOGRAPHY_LOOP_TYPE = eINSTANCE.getTChoreographyLoopType();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.TEventBasedGatewayType <em>TEvent Based Gateway Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TEventBasedGatewayType
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEventBasedGatewayType()
		 * @generated
		 */
		EEnum TEVENT_BASED_GATEWAY_TYPE = eINSTANCE.getTEventBasedGatewayType();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.TGatewayDirection <em>TGateway Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TGatewayDirection
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGatewayDirection()
		 * @generated
		 */
		EEnum TGATEWAY_DIRECTION = eINSTANCE.getTGatewayDirection();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.TImplementationMember1 <em>TImplementation Member1</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TImplementationMember1
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTImplementationMember1()
		 * @generated
		 */
		EEnum TIMPLEMENTATION_MEMBER1 = eINSTANCE.getTImplementationMember1();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.TItemKind <em>TItem Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TItemKind
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTItemKind()
		 * @generated
		 */
		EEnum TITEM_KIND = eINSTANCE.getTItemKind();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.TMultiInstanceFlowCondition <em>TMulti Instance Flow Condition</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TMultiInstanceFlowCondition
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMultiInstanceFlowCondition()
		 * @generated
		 */
		EEnum TMULTI_INSTANCE_FLOW_CONDITION = eINSTANCE.getTMultiInstanceFlowCondition();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.TProcessType <em>TProcess Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TProcessType
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTProcessType()
		 * @generated
		 */
		EEnum TPROCESS_TYPE = eINSTANCE.getTProcessType();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.TRelationshipDirection <em>TRelationship Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TRelationshipDirection
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTRelationshipDirection()
		 * @generated
		 */
		EEnum TRELATIONSHIP_DIRECTION = eINSTANCE.getTRelationshipDirection();

		/**
		 * The meta object literal for the '{@link org.omg.spec.bpmn.model.TTransactionMethodMember1 <em>TTransaction Method Member1</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TTransactionMethodMember1
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTransactionMethodMember1()
		 * @generated
		 */
		EEnum TTRANSACTION_METHOD_MEMBER1 = eINSTANCE.getTTransactionMethodMember1();

		/**
		 * The meta object literal for the '<em>TAd Hoc Ordering Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TAdHocOrdering
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAdHocOrderingObject()
		 * @generated
		 */
		EDataType TAD_HOC_ORDERING_OBJECT = eINSTANCE.getTAdHocOrderingObject();

		/**
		 * The meta object literal for the '<em>TAssociation Direction Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TAssociationDirection
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTAssociationDirectionObject()
		 * @generated
		 */
		EDataType TASSOCIATION_DIRECTION_OBJECT = eINSTANCE.getTAssociationDirectionObject();

		/**
		 * The meta object literal for the '<em>TChoreography Loop Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TChoreographyLoopType
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTChoreographyLoopTypeObject()
		 * @generated
		 */
		EDataType TCHOREOGRAPHY_LOOP_TYPE_OBJECT = eINSTANCE.getTChoreographyLoopTypeObject();

		/**
		 * The meta object literal for the '<em>TEvent Based Gateway Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TEventBasedGatewayType
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTEventBasedGatewayTypeObject()
		 * @generated
		 */
		EDataType TEVENT_BASED_GATEWAY_TYPE_OBJECT = eINSTANCE.getTEventBasedGatewayTypeObject();

		/**
		 * The meta object literal for the '<em>TGateway Direction Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TGatewayDirection
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTGatewayDirectionObject()
		 * @generated
		 */
		EDataType TGATEWAY_DIRECTION_OBJECT = eINSTANCE.getTGatewayDirectionObject();

		/**
		 * The meta object literal for the '<em>TImplementation</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTImplementation()
		 * @generated
		 */
		EDataType TIMPLEMENTATION = eINSTANCE.getTImplementation();

		/**
		 * The meta object literal for the '<em>TImplementation Member1 Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TImplementationMember1
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTImplementationMember1Object()
		 * @generated
		 */
		EDataType TIMPLEMENTATION_MEMBER1_OBJECT = eINSTANCE.getTImplementationMember1Object();

		/**
		 * The meta object literal for the '<em>TItem Kind Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TItemKind
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTItemKindObject()
		 * @generated
		 */
		EDataType TITEM_KIND_OBJECT = eINSTANCE.getTItemKindObject();

		/**
		 * The meta object literal for the '<em>TMulti Instance Flow Condition Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TMultiInstanceFlowCondition
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTMultiInstanceFlowConditionObject()
		 * @generated
		 */
		EDataType TMULTI_INSTANCE_FLOW_CONDITION_OBJECT = eINSTANCE.getTMultiInstanceFlowConditionObject();

		/**
		 * The meta object literal for the '<em>TProcess Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TProcessType
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTProcessTypeObject()
		 * @generated
		 */
		EDataType TPROCESS_TYPE_OBJECT = eINSTANCE.getTProcessTypeObject();

		/**
		 * The meta object literal for the '<em>TRelationship Direction Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TRelationshipDirection
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTRelationshipDirectionObject()
		 * @generated
		 */
		EDataType TRELATIONSHIP_DIRECTION_OBJECT = eINSTANCE.getTRelationshipDirectionObject();

		/**
		 * The meta object literal for the '<em>TTransaction Method</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTransactionMethod()
		 * @generated
		 */
		EDataType TTRANSACTION_METHOD = eINSTANCE.getTTransactionMethod();

		/**
		 * The meta object literal for the '<em>TTransaction Method Member1 Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.omg.spec.bpmn.model.TTransactionMethodMember1
		 * @see org.omg.spec.bpmn.model.impl.ModelPackageImpl#getTTransactionMethodMember1Object()
		 * @generated
		 */
		EDataType TTRANSACTION_METHOD_MEMBER1_OBJECT = eINSTANCE.getTTransactionMethodMember1Object();

	}

} //ModelPackage
