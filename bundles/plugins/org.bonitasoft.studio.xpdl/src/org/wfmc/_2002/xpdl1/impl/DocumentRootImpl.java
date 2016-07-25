/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.wfmc._2002.xpdl1.ActivitiesType;
import org.wfmc._2002.xpdl1.ActivitySetType;
import org.wfmc._2002.xpdl1.ActivitySetsType;
import org.wfmc._2002.xpdl1.ActivityType;
import org.wfmc._2002.xpdl1.ActualParametersType;
import org.wfmc._2002.xpdl1.ApplicationType;
import org.wfmc._2002.xpdl1.ApplicationsType;
import org.wfmc._2002.xpdl1.ArrayTypeType;
import org.wfmc._2002.xpdl1.AutomaticType;
import org.wfmc._2002.xpdl1.BasicTypeType;
import org.wfmc._2002.xpdl1.BlockActivityType;
import org.wfmc._2002.xpdl1.ConditionType;
import org.wfmc._2002.xpdl1.ConformanceClassType;
import org.wfmc._2002.xpdl1.DataFieldType;
import org.wfmc._2002.xpdl1.DataFieldsType;
import org.wfmc._2002.xpdl1.DataTypeType;
import org.wfmc._2002.xpdl1.DeadlineType;
import org.wfmc._2002.xpdl1.DeclaredTypeType;
import org.wfmc._2002.xpdl1.DocumentRoot;
import org.wfmc._2002.xpdl1.EnumerationTypeType;
import org.wfmc._2002.xpdl1.EnumerationValueType;
import org.wfmc._2002.xpdl1.ExtendedAttributeType;
import org.wfmc._2002.xpdl1.ExtendedAttributesType;
import org.wfmc._2002.xpdl1.ExternalPackageType;
import org.wfmc._2002.xpdl1.ExternalPackagesType;
import org.wfmc._2002.xpdl1.ExternalReferenceType;
import org.wfmc._2002.xpdl1.FinishModeType;
import org.wfmc._2002.xpdl1.FormalParameterType;
import org.wfmc._2002.xpdl1.FormalParametersType;
import org.wfmc._2002.xpdl1.ImplementationType;
import org.wfmc._2002.xpdl1.JoinType;
import org.wfmc._2002.xpdl1.ListTypeType;
import org.wfmc._2002.xpdl1.ManualType;
import org.wfmc._2002.xpdl1.MemberType;
import org.wfmc._2002.xpdl1.NoType;
import org.wfmc._2002.xpdl1.PackageHeaderType;
import org.wfmc._2002.xpdl1.PackageType;
import org.wfmc._2002.xpdl1.ParticipantType;
import org.wfmc._2002.xpdl1.ParticipantTypeType;
import org.wfmc._2002.xpdl1.ParticipantsType;
import org.wfmc._2002.xpdl1.ProcessHeaderType;
import org.wfmc._2002.xpdl1.RecordTypeType;
import org.wfmc._2002.xpdl1.RedefinableHeaderType;
import org.wfmc._2002.xpdl1.ResponsiblesType;
import org.wfmc._2002.xpdl1.RouteType;
import org.wfmc._2002.xpdl1.SchemaTypeType;
import org.wfmc._2002.xpdl1.ScriptType;
import org.wfmc._2002.xpdl1.SimulationInformationType;
import org.wfmc._2002.xpdl1.SplitType;
import org.wfmc._2002.xpdl1.StartModeType;
import org.wfmc._2002.xpdl1.SubFlowType;
import org.wfmc._2002.xpdl1.TimeEstimationType;
import org.wfmc._2002.xpdl1.ToolType;
import org.wfmc._2002.xpdl1.TransitionRefType;
import org.wfmc._2002.xpdl1.TransitionRefsType;
import org.wfmc._2002.xpdl1.TransitionRestrictionType;
import org.wfmc._2002.xpdl1.TransitionRestrictionsType;
import org.wfmc._2002.xpdl1.TransitionType;
import org.wfmc._2002.xpdl1.TransitionsType;
import org.wfmc._2002.xpdl1.TypeDeclarationType;
import org.wfmc._2002.xpdl1.TypeDeclarationsType;
import org.wfmc._2002.xpdl1.UnionTypeType;
import org.wfmc._2002.xpdl1.WorkflowProcessType;
import org.wfmc._2002.xpdl1.WorkflowProcessesType;
import org.wfmc._2002.xpdl1.Xpdl1Package;
import org.wfmc._2002.xpdl1.XpressionType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getActivities <em>Activities</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getActivitySet <em>Activity Set</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getActivitySets <em>Activity Sets</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getActualParameter <em>Actual Parameter</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getActualParameters <em>Actual Parameters</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getApplication <em>Application</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getApplications <em>Applications</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getArrayType <em>Array Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getAutomatic <em>Automatic</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getBasicType <em>Basic Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getBlockActivity <em>Block Activity</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getCodepage <em>Codepage</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getConformanceClass <em>Conformance Class</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getCost <em>Cost</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getCostUnit <em>Cost Unit</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getCountrykey <em>Countrykey</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getCreated <em>Created</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getDataField <em>Data Field</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getDataFields <em>Data Fields</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getDeadline <em>Deadline</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getDeclaredType <em>Declared Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getEnumerationType <em>Enumeration Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getEnumerationValue <em>Enumeration Value</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getExtendedAttribute <em>Extended Attribute</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getExternalPackage <em>External Package</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getExternalPackages <em>External Packages</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getExternalReference <em>External Reference</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getFinishMode <em>Finish Mode</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getFormalParameter <em>Formal Parameter</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getFormalParameters <em>Formal Parameters</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getInitialValue <em>Initial Value</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getJoin <em>Join</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getLength <em>Length</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getLimit <em>Limit</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getListType <em>List Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getManual <em>Manual</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getMember <em>Member</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getNo <em>No</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getPackageHeader <em>Package Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getParticipantType <em>Participant Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getPerformer <em>Performer</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getPriorityUnit <em>Priority Unit</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getProcessHeader <em>Process Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getRecordType <em>Record Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getRedefinableHeader <em>Redefinable Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getResponsible <em>Responsible</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getResponsibles <em>Responsibles</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getRoute <em>Route</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getSchemaType <em>Schema Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getSimulationInformation <em>Simulation Information</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getSplit <em>Split</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getStartMode <em>Start Mode</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getSubFlow <em>Sub Flow</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getTimeEstimation <em>Time Estimation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getTool <em>Tool</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getTransitionRef <em>Transition Ref</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getTransitionRefs <em>Transition Refs</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getTransitionRestriction <em>Transition Restriction</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getTransitionRestrictions <em>Transition Restrictions</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getTypeDeclaration <em>Type Declaration</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getTypeDeclarations <em>Type Declarations</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getUnionType <em>Union Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getValidFrom <em>Valid From</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getValidTo <em>Valid To</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getVendor <em>Vendor</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getWaitingTime <em>Waiting Time</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getWorkflowProcess <em>Workflow Process</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getWorkflowProcesses <em>Workflow Processes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getWorkingTime <em>Working Time</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getXPDLVersion <em>XPDL Version</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.DocumentRootImpl#getXpression <em>Xpression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * The default value of the '{@link #getActualParameter() <em>Actual Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualParameter()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTUAL_PARAMETER_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHOR_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getCodepage() <em>Codepage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCodepage()
	 * @generated
	 * @ordered
	 */
	protected static final String CODEPAGE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getCost() <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCost()
	 * @generated
	 * @ordered
	 */
	protected static final String COST_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getCostUnit() <em>Cost Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostUnit()
	 * @generated
	 * @ordered
	 */
	protected static final String COST_UNIT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getCountrykey() <em>Countrykey</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCountrykey()
	 * @generated
	 * @ordered
	 */
	protected static final String COUNTRYKEY_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATED_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected static final String DOCUMENTATION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final String DURATION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getIcon() <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected static final String ICON_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getInitialValue() <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialValue()
	 * @generated
	 * @ordered
	 */
	protected static final String INITIAL_VALUE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected static final String LENGTH_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getLimit() <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLimit()
	 * @generated
	 * @ordered
	 */
	protected static final String LIMIT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getPerformer() <em>Performer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformer()
	 * @generated
	 * @ordered
	 */
	protected static final String PERFORMER_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final String PRIORITY_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getPriorityUnit() <em>Priority Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriorityUnit()
	 * @generated
	 * @ordered
	 */
	protected static final String PRIORITY_UNIT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getResponsible() <em>Responsible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponsible()
	 * @generated
	 * @ordered
	 */
	protected static final String RESPONSIBLE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getValidFrom() <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidFrom()
	 * @generated
	 * @ordered
	 */
	protected static final String VALID_FROM_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getValidTo() <em>Valid To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidTo()
	 * @generated
	 * @ordered
	 */
	protected static final String VALID_TO_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected static final String VENDOR_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getWaitingTime() <em>Waiting Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaitingTime()
	 * @generated
	 * @ordered
	 */
	protected static final String WAITING_TIME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getWorkingTime() <em>Working Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkingTime()
	 * @generated
	 * @ordered
	 */
	protected static final String WORKING_TIME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getXPDLVersion() <em>XPDL Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXPDLVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String XPDL_VERSION_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, Xpdl1Package.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, Xpdl1Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, Xpdl1Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivitiesType getActivities() {
		return (ActivitiesType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTIVITIES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivities(ActivitiesType newActivities, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTIVITIES, newActivities, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivities(ActivitiesType newActivities) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTIVITIES, newActivities);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityType getActivity() {
		return (ActivityType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTIVITY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivity(ActivityType newActivity, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTIVITY, newActivity, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivity(ActivityType newActivity) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTIVITY, newActivity);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivitySetType getActivitySet() {
		return (ActivitySetType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTIVITY_SET, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivitySet(ActivitySetType newActivitySet, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTIVITY_SET, newActivitySet, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivitySet(ActivitySetType newActivitySet) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTIVITY_SET, newActivitySet);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivitySetsType getActivitySets() {
		return (ActivitySetsType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTIVITY_SETS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivitySets(ActivitySetsType newActivitySets, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTIVITY_SETS, newActivitySets, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivitySets(ActivitySetsType newActivitySets) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTIVITY_SETS, newActivitySets);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActualParameter() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTUAL_PARAMETER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualParameter(String newActualParameter) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTUAL_PARAMETER, newActualParameter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActualParametersType getActualParameters() {
		return (ActualParametersType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTUAL_PARAMETERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActualParameters(ActualParametersType newActualParameters, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTUAL_PARAMETERS, newActualParameters, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualParameters(ActualParametersType newActualParameters) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__ACTUAL_PARAMETERS, newActualParameters);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationType getApplication() {
		return (ApplicationType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__APPLICATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetApplication(ApplicationType newApplication, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__APPLICATION, newApplication, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setApplication(ApplicationType newApplication) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__APPLICATION, newApplication);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationsType getApplications() {
		return (ApplicationsType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__APPLICATIONS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetApplications(ApplicationsType newApplications, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__APPLICATIONS, newApplications, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setApplications(ApplicationsType newApplications) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__APPLICATIONS, newApplications);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayTypeType getArrayType() {
		return (ArrayTypeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__ARRAY_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArrayType(ArrayTypeType newArrayType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__ARRAY_TYPE, newArrayType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrayType(ArrayTypeType newArrayType) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__ARRAY_TYPE, newArrayType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthor() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__AUTHOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthor(String newAuthor) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__AUTHOR, newAuthor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AutomaticType getAutomatic() {
		return (AutomaticType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__AUTOMATIC, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAutomatic(AutomaticType newAutomatic, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__AUTOMATIC, newAutomatic, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutomatic(AutomaticType newAutomatic) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__AUTOMATIC, newAutomatic);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicTypeType getBasicType() {
		return (BasicTypeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__BASIC_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBasicType(BasicTypeType newBasicType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__BASIC_TYPE, newBasicType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBasicType(BasicTypeType newBasicType) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__BASIC_TYPE, newBasicType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockActivityType getBlockActivity() {
		return (BlockActivityType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__BLOCK_ACTIVITY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBlockActivity(BlockActivityType newBlockActivity, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__BLOCK_ACTIVITY, newBlockActivity, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlockActivity(BlockActivityType newBlockActivity) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__BLOCK_ACTIVITY, newBlockActivity);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCodepage() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__CODEPAGE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCodepage(String newCodepage) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__CODEPAGE, newCodepage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionType getCondition() {
		return (ConditionType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__CONDITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(ConditionType newCondition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__CONDITION, newCondition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(ConditionType newCondition) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__CONDITION, newCondition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConformanceClassType getConformanceClass() {
		return (ConformanceClassType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__CONFORMANCE_CLASS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConformanceClass(ConformanceClassType newConformanceClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__CONFORMANCE_CLASS, newConformanceClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConformanceClass(ConformanceClassType newConformanceClass) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__CONFORMANCE_CLASS, newConformanceClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCost() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__COST, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCost(String newCost) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__COST, newCost);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCostUnit() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__COST_UNIT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCostUnit(String newCostUnit) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__COST_UNIT, newCostUnit);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCountrykey() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__COUNTRYKEY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCountrykey(String newCountrykey) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__COUNTRYKEY, newCountrykey);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreated() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__CREATED, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreated(String newCreated) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__CREATED, newCreated);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataFieldType getDataField() {
		return (DataFieldType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__DATA_FIELD, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataField(DataFieldType newDataField, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__DATA_FIELD, newDataField, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataField(DataFieldType newDataField) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__DATA_FIELD, newDataField);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataFieldsType getDataFields() {
		return (DataFieldsType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__DATA_FIELDS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataFields(DataFieldsType newDataFields, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__DATA_FIELDS, newDataFields, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataFields(DataFieldsType newDataFields) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__DATA_FIELDS, newDataFields);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataTypeType getDataType() {
		return (DataTypeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__DATA_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataType(DataTypeType newDataType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__DATA_TYPE, newDataType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataType(DataTypeType newDataType) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__DATA_TYPE, newDataType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeadlineType getDeadline() {
		return (DeadlineType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__DEADLINE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeadline(DeadlineType newDeadline, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__DEADLINE, newDeadline, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeadline(DeadlineType newDeadline) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__DEADLINE, newDeadline);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeclaredTypeType getDeclaredType() {
		return (DeclaredTypeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__DECLARED_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeclaredType(DeclaredTypeType newDeclaredType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__DECLARED_TYPE, newDeclaredType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredType(DeclaredTypeType newDeclaredType) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__DECLARED_TYPE, newDeclaredType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__DESCRIPTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDocumentation() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__DOCUMENTATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocumentation(String newDocumentation) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__DOCUMENTATION, newDocumentation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDuration() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__DURATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(String newDuration) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__DURATION, newDuration);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationTypeType getEnumerationType() {
		return (EnumerationTypeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__ENUMERATION_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnumerationType(EnumerationTypeType newEnumerationType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__ENUMERATION_TYPE, newEnumerationType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnumerationType(EnumerationTypeType newEnumerationType) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__ENUMERATION_TYPE, newEnumerationType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationValueType getEnumerationValue() {
		return (EnumerationValueType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__ENUMERATION_VALUE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnumerationValue(EnumerationValueType newEnumerationValue, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__ENUMERATION_VALUE, newEnumerationValue, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnumerationValue(EnumerationValueType newEnumerationValue) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__ENUMERATION_VALUE, newEnumerationValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttributeType getExtendedAttribute() {
		return (ExtendedAttributeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTENDED_ATTRIBUTE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExtendedAttribute(ExtendedAttributeType newExtendedAttribute, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTENDED_ATTRIBUTE, newExtendedAttribute, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtendedAttribute(ExtendedAttributeType newExtendedAttribute) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTENDED_ATTRIBUTE, newExtendedAttribute);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttributesType getExtendedAttributes() {
		return (ExtendedAttributesType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTENDED_ATTRIBUTES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExtendedAttributes(ExtendedAttributesType newExtendedAttributes, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTENDED_ATTRIBUTES, newExtendedAttributes, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtendedAttributes(ExtendedAttributesType newExtendedAttributes) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTENDED_ATTRIBUTES, newExtendedAttributes);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalPackageType getExternalPackage() {
		return (ExternalPackageType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTERNAL_PACKAGE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExternalPackage(ExternalPackageType newExternalPackage, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTERNAL_PACKAGE, newExternalPackage, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalPackage(ExternalPackageType newExternalPackage) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTERNAL_PACKAGE, newExternalPackage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalPackagesType getExternalPackages() {
		return (ExternalPackagesType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTERNAL_PACKAGES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExternalPackages(ExternalPackagesType newExternalPackages, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTERNAL_PACKAGES, newExternalPackages, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalPackages(ExternalPackagesType newExternalPackages) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTERNAL_PACKAGES, newExternalPackages);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalReferenceType getExternalReference() {
		return (ExternalReferenceType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTERNAL_REFERENCE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExternalReference(ExternalReferenceType newExternalReference, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTERNAL_REFERENCE, newExternalReference, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalReference(ExternalReferenceType newExternalReference) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__EXTERNAL_REFERENCE, newExternalReference);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FinishModeType getFinishMode() {
		return (FinishModeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__FINISH_MODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFinishMode(FinishModeType newFinishMode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__FINISH_MODE, newFinishMode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinishMode(FinishModeType newFinishMode) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__FINISH_MODE, newFinishMode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalParameterType getFormalParameter() {
		return (FormalParameterType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__FORMAL_PARAMETER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFormalParameter(FormalParameterType newFormalParameter, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__FORMAL_PARAMETER, newFormalParameter, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormalParameter(FormalParameterType newFormalParameter) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__FORMAL_PARAMETER, newFormalParameter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalParametersType getFormalParameters() {
		return (FormalParametersType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__FORMAL_PARAMETERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFormalParameters(FormalParametersType newFormalParameters, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__FORMAL_PARAMETERS, newFormalParameters, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormalParameters(FormalParametersType newFormalParameters) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__FORMAL_PARAMETERS, newFormalParameters);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIcon() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__ICON, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIcon(String newIcon) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__ICON, newIcon);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImplementationType getImplementation() {
		return (ImplementationType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__IMPLEMENTATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImplementation(ImplementationType newImplementation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__IMPLEMENTATION, newImplementation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementation(ImplementationType newImplementation) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__IMPLEMENTATION, newImplementation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInitialValue() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__INITIAL_VALUE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitialValue(String newInitialValue) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__INITIAL_VALUE, newInitialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinType getJoin() {
		return (JoinType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__JOIN, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJoin(JoinType newJoin, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__JOIN, newJoin, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJoin(JoinType newJoin) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__JOIN, newJoin);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLength() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__LENGTH, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLength(String newLength) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__LENGTH, newLength);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLimit() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__LIMIT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLimit(String newLimit) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__LIMIT, newLimit);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListTypeType getListType() {
		return (ListTypeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__LIST_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetListType(ListTypeType newListType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__LIST_TYPE, newListType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListType(ListTypeType newListType) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__LIST_TYPE, newListType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManualType getManual() {
		return (ManualType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__MANUAL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetManual(ManualType newManual, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__MANUAL, newManual, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManual(ManualType newManual) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__MANUAL, newManual);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemberType getMember() {
		return (MemberType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__MEMBER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMember(MemberType newMember, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__MEMBER, newMember, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMember(MemberType newMember) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__MEMBER, newMember);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoType getNo() {
		return (NoType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__NO, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNo(NoType newNo, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__NO, newNo, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNo(NoType newNo) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__NO, newNo);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageType getPackage() {
		return (PackageType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__PACKAGE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPackage(PackageType newPackage, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__PACKAGE, newPackage, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackage(PackageType newPackage) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__PACKAGE, newPackage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageHeaderType getPackageHeader() {
		return (PackageHeaderType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__PACKAGE_HEADER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPackageHeader(PackageHeaderType newPackageHeader, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__PACKAGE_HEADER, newPackageHeader, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageHeader(PackageHeaderType newPackageHeader) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__PACKAGE_HEADER, newPackageHeader);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParticipantType getParticipant() {
		return (ParticipantType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__PARTICIPANT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParticipant(ParticipantType newParticipant, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__PARTICIPANT, newParticipant, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipant(ParticipantType newParticipant) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__PARTICIPANT, newParticipant);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParticipantsType getParticipants() {
		return (ParticipantsType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__PARTICIPANTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParticipants(ParticipantsType newParticipants, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__PARTICIPANTS, newParticipants, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipants(ParticipantsType newParticipants) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__PARTICIPANTS, newParticipants);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParticipantTypeType getParticipantType() {
		return (ParticipantTypeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__PARTICIPANT_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParticipantType(ParticipantTypeType newParticipantType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__PARTICIPANT_TYPE, newParticipantType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipantType(ParticipantTypeType newParticipantType) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__PARTICIPANT_TYPE, newParticipantType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPerformer() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__PERFORMER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerformer(String newPerformer) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__PERFORMER, newPerformer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPriority() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__PRIORITY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(String newPriority) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__PRIORITY, newPriority);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPriorityUnit() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__PRIORITY_UNIT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriorityUnit(String newPriorityUnit) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__PRIORITY_UNIT, newPriorityUnit);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessHeaderType getProcessHeader() {
		return (ProcessHeaderType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__PROCESS_HEADER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcessHeader(ProcessHeaderType newProcessHeader, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__PROCESS_HEADER, newProcessHeader, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessHeader(ProcessHeaderType newProcessHeader) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__PROCESS_HEADER, newProcessHeader);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecordTypeType getRecordType() {
		return (RecordTypeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__RECORD_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRecordType(RecordTypeType newRecordType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__RECORD_TYPE, newRecordType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecordType(RecordTypeType newRecordType) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__RECORD_TYPE, newRecordType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RedefinableHeaderType getRedefinableHeader() {
		return (RedefinableHeaderType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__REDEFINABLE_HEADER, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRedefinableHeader(RedefinableHeaderType newRedefinableHeader, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__REDEFINABLE_HEADER, newRedefinableHeader, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRedefinableHeader(RedefinableHeaderType newRedefinableHeader) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__REDEFINABLE_HEADER, newRedefinableHeader);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResponsible() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__RESPONSIBLE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResponsible(String newResponsible) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__RESPONSIBLE, newResponsible);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResponsiblesType getResponsibles() {
		return (ResponsiblesType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__RESPONSIBLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResponsibles(ResponsiblesType newResponsibles, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__RESPONSIBLES, newResponsibles, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResponsibles(ResponsiblesType newResponsibles) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__RESPONSIBLES, newResponsibles);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RouteType getRoute() {
		return (RouteType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__ROUTE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoute(RouteType newRoute, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__ROUTE, newRoute, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoute(RouteType newRoute) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__ROUTE, newRoute);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SchemaTypeType getSchemaType() {
		return (SchemaTypeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__SCHEMA_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchemaType(SchemaTypeType newSchemaType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__SCHEMA_TYPE, newSchemaType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchemaType(SchemaTypeType newSchemaType) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__SCHEMA_TYPE, newSchemaType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptType getScript() {
		return (ScriptType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__SCRIPT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScript(ScriptType newScript, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__SCRIPT, newScript, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScript(ScriptType newScript) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__SCRIPT, newScript);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationInformationType getSimulationInformation() {
		return (SimulationInformationType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__SIMULATION_INFORMATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSimulationInformation(SimulationInformationType newSimulationInformation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__SIMULATION_INFORMATION, newSimulationInformation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSimulationInformation(SimulationInformationType newSimulationInformation) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__SIMULATION_INFORMATION, newSimulationInformation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SplitType getSplit() {
		return (SplitType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__SPLIT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSplit(SplitType newSplit, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__SPLIT, newSplit, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSplit(SplitType newSplit) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__SPLIT, newSplit);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartModeType getStartMode() {
		return (StartModeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__START_MODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartMode(StartModeType newStartMode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__START_MODE, newStartMode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartMode(StartModeType newStartMode) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__START_MODE, newStartMode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubFlowType getSubFlow() {
		return (SubFlowType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__SUB_FLOW, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSubFlow(SubFlowType newSubFlow, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__SUB_FLOW, newSubFlow, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubFlow(SubFlowType newSubFlow) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__SUB_FLOW, newSubFlow);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeEstimationType getTimeEstimation() {
		return (TimeEstimationType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__TIME_ESTIMATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimeEstimation(TimeEstimationType newTimeEstimation, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__TIME_ESTIMATION, newTimeEstimation, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeEstimation(TimeEstimationType newTimeEstimation) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__TIME_ESTIMATION, newTimeEstimation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ToolType getTool() {
		return (ToolType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__TOOL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTool(ToolType newTool, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__TOOL, newTool, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTool(ToolType newTool) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__TOOL, newTool);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionType getTransition() {
		return (TransitionType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransition(TransitionType newTransition, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION, newTransition, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransition(TransitionType newTransition) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION, newTransition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionRefType getTransitionRef() {
		return (TransitionRefType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION_REF, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransitionRef(TransitionRefType newTransitionRef, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION_REF, newTransitionRef, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitionRef(TransitionRefType newTransitionRef) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION_REF, newTransitionRef);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionRefsType getTransitionRefs() {
		return (TransitionRefsType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION_REFS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransitionRefs(TransitionRefsType newTransitionRefs, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION_REFS, newTransitionRefs, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitionRefs(TransitionRefsType newTransitionRefs) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION_REFS, newTransitionRefs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionRestrictionType getTransitionRestriction() {
		return (TransitionRestrictionType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION_RESTRICTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransitionRestriction(TransitionRestrictionType newTransitionRestriction, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION_RESTRICTION, newTransitionRestriction, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitionRestriction(TransitionRestrictionType newTransitionRestriction) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION_RESTRICTION, newTransitionRestriction);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionRestrictionsType getTransitionRestrictions() {
		return (TransitionRestrictionsType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION_RESTRICTIONS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransitionRestrictions(TransitionRestrictionsType newTransitionRestrictions, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION_RESTRICTIONS, newTransitionRestrictions, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitionRestrictions(TransitionRestrictionsType newTransitionRestrictions) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITION_RESTRICTIONS, newTransitionRestrictions);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionsType getTransitions() {
		return (TransitionsType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITIONS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransitions(TransitionsType newTransitions, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITIONS, newTransitions, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitions(TransitionsType newTransitions) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__TRANSITIONS, newTransitions);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclarationType getTypeDeclaration() {
		return (TypeDeclarationType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__TYPE_DECLARATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypeDeclaration(TypeDeclarationType newTypeDeclaration, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__TYPE_DECLARATION, newTypeDeclaration, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeDeclaration(TypeDeclarationType newTypeDeclaration) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__TYPE_DECLARATION, newTypeDeclaration);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclarationsType getTypeDeclarations() {
		return (TypeDeclarationsType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__TYPE_DECLARATIONS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypeDeclarations(TypeDeclarationsType newTypeDeclarations, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__TYPE_DECLARATIONS, newTypeDeclarations, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeDeclarations(TypeDeclarationsType newTypeDeclarations) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__TYPE_DECLARATIONS, newTypeDeclarations);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionTypeType getUnionType() {
		return (UnionTypeType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__UNION_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUnionType(UnionTypeType newUnionType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__UNION_TYPE, newUnionType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnionType(UnionTypeType newUnionType) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__UNION_TYPE, newUnionType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValidFrom() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__VALID_FROM, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidFrom(String newValidFrom) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__VALID_FROM, newValidFrom);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValidTo() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__VALID_TO, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidTo(String newValidTo) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__VALID_TO, newValidTo);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVendor() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__VENDOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVendor(String newVendor) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__VENDOR, newVendor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__VERSION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__VERSION, newVersion);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWaitingTime() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__WAITING_TIME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWaitingTime(String newWaitingTime) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__WAITING_TIME, newWaitingTime);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkflowProcessType getWorkflowProcess() {
		return (WorkflowProcessType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__WORKFLOW_PROCESS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorkflowProcess(WorkflowProcessType newWorkflowProcess, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__WORKFLOW_PROCESS, newWorkflowProcess, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkflowProcess(WorkflowProcessType newWorkflowProcess) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__WORKFLOW_PROCESS, newWorkflowProcess);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkflowProcessesType getWorkflowProcesses() {
		return (WorkflowProcessesType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__WORKFLOW_PROCESSES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorkflowProcesses(WorkflowProcessesType newWorkflowProcesses, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__WORKFLOW_PROCESSES, newWorkflowProcesses, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkflowProcesses(WorkflowProcessesType newWorkflowProcesses) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__WORKFLOW_PROCESSES, newWorkflowProcesses);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWorkingTime() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__WORKING_TIME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkingTime(String newWorkingTime) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__WORKING_TIME, newWorkingTime);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getXPDLVersion() {
		return (String)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__XPDL_VERSION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXPDLVersion(String newXPDLVersion) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__XPDL_VERSION, newXPDLVersion);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XpressionType getXpression() {
		return (XpressionType)getMixed().get(Xpdl1Package.Literals.DOCUMENT_ROOT__XPRESSION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetXpression(XpressionType newXpression, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(Xpdl1Package.Literals.DOCUMENT_ROOT__XPRESSION, newXpression, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXpression(XpressionType newXpression) {
		((FeatureMap.Internal)getMixed()).set(Xpdl1Package.Literals.DOCUMENT_ROOT__XPRESSION, newXpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITIES:
				return basicSetActivities(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY:
				return basicSetActivity(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY_SET:
				return basicSetActivitySet(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY_SETS:
				return basicSetActivitySets(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__ACTUAL_PARAMETERS:
				return basicSetActualParameters(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__APPLICATION:
				return basicSetApplication(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__APPLICATIONS:
				return basicSetApplications(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__ARRAY_TYPE:
				return basicSetArrayType(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__AUTOMATIC:
				return basicSetAutomatic(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__BASIC_TYPE:
				return basicSetBasicType(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__BLOCK_ACTIVITY:
				return basicSetBlockActivity(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__CONDITION:
				return basicSetCondition(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__CONFORMANCE_CLASS:
				return basicSetConformanceClass(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__DATA_FIELD:
				return basicSetDataField(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__DATA_FIELDS:
				return basicSetDataFields(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__DATA_TYPE:
				return basicSetDataType(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__DEADLINE:
				return basicSetDeadline(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__DECLARED_TYPE:
				return basicSetDeclaredType(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__ENUMERATION_TYPE:
				return basicSetEnumerationType(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__ENUMERATION_VALUE:
				return basicSetEnumerationValue(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__EXTENDED_ATTRIBUTE:
				return basicSetExtendedAttribute(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__EXTENDED_ATTRIBUTES:
				return basicSetExtendedAttributes(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_PACKAGE:
				return basicSetExternalPackage(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_PACKAGES:
				return basicSetExternalPackages(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_REFERENCE:
				return basicSetExternalReference(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__FINISH_MODE:
				return basicSetFinishMode(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__FORMAL_PARAMETER:
				return basicSetFormalParameter(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__FORMAL_PARAMETERS:
				return basicSetFormalParameters(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__IMPLEMENTATION:
				return basicSetImplementation(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__JOIN:
				return basicSetJoin(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__LIST_TYPE:
				return basicSetListType(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__MANUAL:
				return basicSetManual(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__MEMBER:
				return basicSetMember(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__NO:
				return basicSetNo(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__PACKAGE:
				return basicSetPackage(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__PACKAGE_HEADER:
				return basicSetPackageHeader(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANT:
				return basicSetParticipant(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANTS:
				return basicSetParticipants(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANT_TYPE:
				return basicSetParticipantType(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__PROCESS_HEADER:
				return basicSetProcessHeader(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__RECORD_TYPE:
				return basicSetRecordType(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__REDEFINABLE_HEADER:
				return basicSetRedefinableHeader(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__RESPONSIBLES:
				return basicSetResponsibles(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__ROUTE:
				return basicSetRoute(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__SCHEMA_TYPE:
				return basicSetSchemaType(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__SCRIPT:
				return basicSetScript(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__SIMULATION_INFORMATION:
				return basicSetSimulationInformation(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__SPLIT:
				return basicSetSplit(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__START_MODE:
				return basicSetStartMode(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__SUB_FLOW:
				return basicSetSubFlow(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__TIME_ESTIMATION:
				return basicSetTimeEstimation(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__TOOL:
				return basicSetTool(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION:
				return basicSetTransition(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_REF:
				return basicSetTransitionRef(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_REFS:
				return basicSetTransitionRefs(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_RESTRICTION:
				return basicSetTransitionRestriction(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_RESTRICTIONS:
				return basicSetTransitionRestrictions(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITIONS:
				return basicSetTransitions(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__TYPE_DECLARATION:
				return basicSetTypeDeclaration(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__TYPE_DECLARATIONS:
				return basicSetTypeDeclarations(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__UNION_TYPE:
				return basicSetUnionType(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__WORKFLOW_PROCESS:
				return basicSetWorkflowProcess(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__WORKFLOW_PROCESSES:
				return basicSetWorkflowProcesses(null, msgs);
			case Xpdl1Package.DOCUMENT_ROOT__XPRESSION:
				return basicSetXpression(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Xpdl1Package.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case Xpdl1Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case Xpdl1Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITIES:
				return getActivities();
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY:
				return getActivity();
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY_SET:
				return getActivitySet();
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY_SETS:
				return getActivitySets();
			case Xpdl1Package.DOCUMENT_ROOT__ACTUAL_PARAMETER:
				return getActualParameter();
			case Xpdl1Package.DOCUMENT_ROOT__ACTUAL_PARAMETERS:
				return getActualParameters();
			case Xpdl1Package.DOCUMENT_ROOT__APPLICATION:
				return getApplication();
			case Xpdl1Package.DOCUMENT_ROOT__APPLICATIONS:
				return getApplications();
			case Xpdl1Package.DOCUMENT_ROOT__ARRAY_TYPE:
				return getArrayType();
			case Xpdl1Package.DOCUMENT_ROOT__AUTHOR:
				return getAuthor();
			case Xpdl1Package.DOCUMENT_ROOT__AUTOMATIC:
				return getAutomatic();
			case Xpdl1Package.DOCUMENT_ROOT__BASIC_TYPE:
				return getBasicType();
			case Xpdl1Package.DOCUMENT_ROOT__BLOCK_ACTIVITY:
				return getBlockActivity();
			case Xpdl1Package.DOCUMENT_ROOT__CODEPAGE:
				return getCodepage();
			case Xpdl1Package.DOCUMENT_ROOT__CONDITION:
				return getCondition();
			case Xpdl1Package.DOCUMENT_ROOT__CONFORMANCE_CLASS:
				return getConformanceClass();
			case Xpdl1Package.DOCUMENT_ROOT__COST:
				return getCost();
			case Xpdl1Package.DOCUMENT_ROOT__COST_UNIT:
				return getCostUnit();
			case Xpdl1Package.DOCUMENT_ROOT__COUNTRYKEY:
				return getCountrykey();
			case Xpdl1Package.DOCUMENT_ROOT__CREATED:
				return getCreated();
			case Xpdl1Package.DOCUMENT_ROOT__DATA_FIELD:
				return getDataField();
			case Xpdl1Package.DOCUMENT_ROOT__DATA_FIELDS:
				return getDataFields();
			case Xpdl1Package.DOCUMENT_ROOT__DATA_TYPE:
				return getDataType();
			case Xpdl1Package.DOCUMENT_ROOT__DEADLINE:
				return getDeadline();
			case Xpdl1Package.DOCUMENT_ROOT__DECLARED_TYPE:
				return getDeclaredType();
			case Xpdl1Package.DOCUMENT_ROOT__DESCRIPTION:
				return getDescription();
			case Xpdl1Package.DOCUMENT_ROOT__DOCUMENTATION:
				return getDocumentation();
			case Xpdl1Package.DOCUMENT_ROOT__DURATION:
				return getDuration();
			case Xpdl1Package.DOCUMENT_ROOT__ENUMERATION_TYPE:
				return getEnumerationType();
			case Xpdl1Package.DOCUMENT_ROOT__ENUMERATION_VALUE:
				return getEnumerationValue();
			case Xpdl1Package.DOCUMENT_ROOT__EXTENDED_ATTRIBUTE:
				return getExtendedAttribute();
			case Xpdl1Package.DOCUMENT_ROOT__EXTENDED_ATTRIBUTES:
				return getExtendedAttributes();
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_PACKAGE:
				return getExternalPackage();
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_PACKAGES:
				return getExternalPackages();
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_REFERENCE:
				return getExternalReference();
			case Xpdl1Package.DOCUMENT_ROOT__FINISH_MODE:
				return getFinishMode();
			case Xpdl1Package.DOCUMENT_ROOT__FORMAL_PARAMETER:
				return getFormalParameter();
			case Xpdl1Package.DOCUMENT_ROOT__FORMAL_PARAMETERS:
				return getFormalParameters();
			case Xpdl1Package.DOCUMENT_ROOT__ICON:
				return getIcon();
			case Xpdl1Package.DOCUMENT_ROOT__IMPLEMENTATION:
				return getImplementation();
			case Xpdl1Package.DOCUMENT_ROOT__INITIAL_VALUE:
				return getInitialValue();
			case Xpdl1Package.DOCUMENT_ROOT__JOIN:
				return getJoin();
			case Xpdl1Package.DOCUMENT_ROOT__LENGTH:
				return getLength();
			case Xpdl1Package.DOCUMENT_ROOT__LIMIT:
				return getLimit();
			case Xpdl1Package.DOCUMENT_ROOT__LIST_TYPE:
				return getListType();
			case Xpdl1Package.DOCUMENT_ROOT__MANUAL:
				return getManual();
			case Xpdl1Package.DOCUMENT_ROOT__MEMBER:
				return getMember();
			case Xpdl1Package.DOCUMENT_ROOT__NO:
				return getNo();
			case Xpdl1Package.DOCUMENT_ROOT__PACKAGE:
				return getPackage();
			case Xpdl1Package.DOCUMENT_ROOT__PACKAGE_HEADER:
				return getPackageHeader();
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANT:
				return getParticipant();
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANTS:
				return getParticipants();
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANT_TYPE:
				return getParticipantType();
			case Xpdl1Package.DOCUMENT_ROOT__PERFORMER:
				return getPerformer();
			case Xpdl1Package.DOCUMENT_ROOT__PRIORITY:
				return getPriority();
			case Xpdl1Package.DOCUMENT_ROOT__PRIORITY_UNIT:
				return getPriorityUnit();
			case Xpdl1Package.DOCUMENT_ROOT__PROCESS_HEADER:
				return getProcessHeader();
			case Xpdl1Package.DOCUMENT_ROOT__RECORD_TYPE:
				return getRecordType();
			case Xpdl1Package.DOCUMENT_ROOT__REDEFINABLE_HEADER:
				return getRedefinableHeader();
			case Xpdl1Package.DOCUMENT_ROOT__RESPONSIBLE:
				return getResponsible();
			case Xpdl1Package.DOCUMENT_ROOT__RESPONSIBLES:
				return getResponsibles();
			case Xpdl1Package.DOCUMENT_ROOT__ROUTE:
				return getRoute();
			case Xpdl1Package.DOCUMENT_ROOT__SCHEMA_TYPE:
				return getSchemaType();
			case Xpdl1Package.DOCUMENT_ROOT__SCRIPT:
				return getScript();
			case Xpdl1Package.DOCUMENT_ROOT__SIMULATION_INFORMATION:
				return getSimulationInformation();
			case Xpdl1Package.DOCUMENT_ROOT__SPLIT:
				return getSplit();
			case Xpdl1Package.DOCUMENT_ROOT__START_MODE:
				return getStartMode();
			case Xpdl1Package.DOCUMENT_ROOT__SUB_FLOW:
				return getSubFlow();
			case Xpdl1Package.DOCUMENT_ROOT__TIME_ESTIMATION:
				return getTimeEstimation();
			case Xpdl1Package.DOCUMENT_ROOT__TOOL:
				return getTool();
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION:
				return getTransition();
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_REF:
				return getTransitionRef();
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_REFS:
				return getTransitionRefs();
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_RESTRICTION:
				return getTransitionRestriction();
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_RESTRICTIONS:
				return getTransitionRestrictions();
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITIONS:
				return getTransitions();
			case Xpdl1Package.DOCUMENT_ROOT__TYPE_DECLARATION:
				return getTypeDeclaration();
			case Xpdl1Package.DOCUMENT_ROOT__TYPE_DECLARATIONS:
				return getTypeDeclarations();
			case Xpdl1Package.DOCUMENT_ROOT__UNION_TYPE:
				return getUnionType();
			case Xpdl1Package.DOCUMENT_ROOT__VALID_FROM:
				return getValidFrom();
			case Xpdl1Package.DOCUMENT_ROOT__VALID_TO:
				return getValidTo();
			case Xpdl1Package.DOCUMENT_ROOT__VENDOR:
				return getVendor();
			case Xpdl1Package.DOCUMENT_ROOT__VERSION:
				return getVersion();
			case Xpdl1Package.DOCUMENT_ROOT__WAITING_TIME:
				return getWaitingTime();
			case Xpdl1Package.DOCUMENT_ROOT__WORKFLOW_PROCESS:
				return getWorkflowProcess();
			case Xpdl1Package.DOCUMENT_ROOT__WORKFLOW_PROCESSES:
				return getWorkflowProcesses();
			case Xpdl1Package.DOCUMENT_ROOT__WORKING_TIME:
				return getWorkingTime();
			case Xpdl1Package.DOCUMENT_ROOT__XPDL_VERSION:
				return getXPDLVersion();
			case Xpdl1Package.DOCUMENT_ROOT__XPRESSION:
				return getXpression();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Xpdl1Package.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITIES:
				setActivities((ActivitiesType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY:
				setActivity((ActivityType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY_SET:
				setActivitySet((ActivitySetType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY_SETS:
				setActivitySets((ActivitySetsType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ACTUAL_PARAMETER:
				setActualParameter((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ACTUAL_PARAMETERS:
				setActualParameters((ActualParametersType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__APPLICATION:
				setApplication((ApplicationType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__APPLICATIONS:
				setApplications((ApplicationsType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ARRAY_TYPE:
				setArrayType((ArrayTypeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__AUTHOR:
				setAuthor((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__AUTOMATIC:
				setAutomatic((AutomaticType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__BASIC_TYPE:
				setBasicType((BasicTypeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__BLOCK_ACTIVITY:
				setBlockActivity((BlockActivityType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__CODEPAGE:
				setCodepage((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__CONDITION:
				setCondition((ConditionType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__CONFORMANCE_CLASS:
				setConformanceClass((ConformanceClassType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__COST:
				setCost((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__COST_UNIT:
				setCostUnit((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__COUNTRYKEY:
				setCountrykey((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__CREATED:
				setCreated((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DATA_FIELD:
				setDataField((DataFieldType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DATA_FIELDS:
				setDataFields((DataFieldsType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DATA_TYPE:
				setDataType((DataTypeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DEADLINE:
				setDeadline((DeadlineType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DECLARED_TYPE:
				setDeclaredType((DeclaredTypeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DOCUMENTATION:
				setDocumentation((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DURATION:
				setDuration((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ENUMERATION_TYPE:
				setEnumerationType((EnumerationTypeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ENUMERATION_VALUE:
				setEnumerationValue((EnumerationValueType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__EXTENDED_ATTRIBUTE:
				setExtendedAttribute((ExtendedAttributeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__EXTENDED_ATTRIBUTES:
				setExtendedAttributes((ExtendedAttributesType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_PACKAGE:
				setExternalPackage((ExternalPackageType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_PACKAGES:
				setExternalPackages((ExternalPackagesType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_REFERENCE:
				setExternalReference((ExternalReferenceType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__FINISH_MODE:
				setFinishMode((FinishModeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__FORMAL_PARAMETER:
				setFormalParameter((FormalParameterType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__FORMAL_PARAMETERS:
				setFormalParameters((FormalParametersType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ICON:
				setIcon((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__IMPLEMENTATION:
				setImplementation((ImplementationType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__INITIAL_VALUE:
				setInitialValue((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__JOIN:
				setJoin((JoinType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__LENGTH:
				setLength((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__LIMIT:
				setLimit((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__LIST_TYPE:
				setListType((ListTypeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__MANUAL:
				setManual((ManualType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__MEMBER:
				setMember((MemberType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__NO:
				setNo((NoType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PACKAGE:
				setPackage((PackageType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PACKAGE_HEADER:
				setPackageHeader((PackageHeaderType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANT:
				setParticipant((ParticipantType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANTS:
				setParticipants((ParticipantsType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANT_TYPE:
				setParticipantType((ParticipantTypeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PERFORMER:
				setPerformer((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PRIORITY:
				setPriority((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PRIORITY_UNIT:
				setPriorityUnit((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PROCESS_HEADER:
				setProcessHeader((ProcessHeaderType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__RECORD_TYPE:
				setRecordType((RecordTypeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__REDEFINABLE_HEADER:
				setRedefinableHeader((RedefinableHeaderType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__RESPONSIBLE:
				setResponsible((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__RESPONSIBLES:
				setResponsibles((ResponsiblesType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ROUTE:
				setRoute((RouteType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__SCHEMA_TYPE:
				setSchemaType((SchemaTypeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__SCRIPT:
				setScript((ScriptType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__SIMULATION_INFORMATION:
				setSimulationInformation((SimulationInformationType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__SPLIT:
				setSplit((SplitType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__START_MODE:
				setStartMode((StartModeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__SUB_FLOW:
				setSubFlow((SubFlowType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TIME_ESTIMATION:
				setTimeEstimation((TimeEstimationType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TOOL:
				setTool((ToolType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION:
				setTransition((TransitionType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_REF:
				setTransitionRef((TransitionRefType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_REFS:
				setTransitionRefs((TransitionRefsType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_RESTRICTION:
				setTransitionRestriction((TransitionRestrictionType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_RESTRICTIONS:
				setTransitionRestrictions((TransitionRestrictionsType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITIONS:
				setTransitions((TransitionsType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TYPE_DECLARATION:
				setTypeDeclaration((TypeDeclarationType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TYPE_DECLARATIONS:
				setTypeDeclarations((TypeDeclarationsType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__UNION_TYPE:
				setUnionType((UnionTypeType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__VALID_FROM:
				setValidFrom((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__VALID_TO:
				setValidTo((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__VENDOR:
				setVendor((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__VERSION:
				setVersion((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__WAITING_TIME:
				setWaitingTime((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__WORKFLOW_PROCESS:
				setWorkflowProcess((WorkflowProcessType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__WORKFLOW_PROCESSES:
				setWorkflowProcesses((WorkflowProcessesType)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__WORKING_TIME:
				setWorkingTime((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__XPDL_VERSION:
				setXPDLVersion((String)newValue);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__XPRESSION:
				setXpression((XpressionType)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Xpdl1Package.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case Xpdl1Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case Xpdl1Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITIES:
				setActivities((ActivitiesType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY:
				setActivity((ActivityType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY_SET:
				setActivitySet((ActivitySetType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY_SETS:
				setActivitySets((ActivitySetsType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ACTUAL_PARAMETER:
				setActualParameter(ACTUAL_PARAMETER_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ACTUAL_PARAMETERS:
				setActualParameters((ActualParametersType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__APPLICATION:
				setApplication((ApplicationType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__APPLICATIONS:
				setApplications((ApplicationsType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ARRAY_TYPE:
				setArrayType((ArrayTypeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__AUTHOR:
				setAuthor(AUTHOR_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__AUTOMATIC:
				setAutomatic((AutomaticType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__BASIC_TYPE:
				setBasicType((BasicTypeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__BLOCK_ACTIVITY:
				setBlockActivity((BlockActivityType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__CODEPAGE:
				setCodepage(CODEPAGE_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__CONDITION:
				setCondition((ConditionType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__CONFORMANCE_CLASS:
				setConformanceClass((ConformanceClassType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__COST:
				setCost(COST_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__COST_UNIT:
				setCostUnit(COST_UNIT_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__COUNTRYKEY:
				setCountrykey(COUNTRYKEY_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__CREATED:
				setCreated(CREATED_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DATA_FIELD:
				setDataField((DataFieldType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DATA_FIELDS:
				setDataFields((DataFieldsType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DATA_TYPE:
				setDataType((DataTypeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DEADLINE:
				setDeadline((DeadlineType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DECLARED_TYPE:
				setDeclaredType((DeclaredTypeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DOCUMENTATION:
				setDocumentation(DOCUMENTATION_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__DURATION:
				setDuration(DURATION_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ENUMERATION_TYPE:
				setEnumerationType((EnumerationTypeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ENUMERATION_VALUE:
				setEnumerationValue((EnumerationValueType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__EXTENDED_ATTRIBUTE:
				setExtendedAttribute((ExtendedAttributeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__EXTENDED_ATTRIBUTES:
				setExtendedAttributes((ExtendedAttributesType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_PACKAGE:
				setExternalPackage((ExternalPackageType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_PACKAGES:
				setExternalPackages((ExternalPackagesType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_REFERENCE:
				setExternalReference((ExternalReferenceType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__FINISH_MODE:
				setFinishMode((FinishModeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__FORMAL_PARAMETER:
				setFormalParameter((FormalParameterType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__FORMAL_PARAMETERS:
				setFormalParameters((FormalParametersType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ICON:
				setIcon(ICON_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__IMPLEMENTATION:
				setImplementation((ImplementationType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__INITIAL_VALUE:
				setInitialValue(INITIAL_VALUE_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__JOIN:
				setJoin((JoinType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__LENGTH:
				setLength(LENGTH_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__LIMIT:
				setLimit(LIMIT_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__LIST_TYPE:
				setListType((ListTypeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__MANUAL:
				setManual((ManualType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__MEMBER:
				setMember((MemberType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__NO:
				setNo((NoType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PACKAGE:
				setPackage((PackageType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PACKAGE_HEADER:
				setPackageHeader((PackageHeaderType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANT:
				setParticipant((ParticipantType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANTS:
				setParticipants((ParticipantsType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANT_TYPE:
				setParticipantType((ParticipantTypeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PERFORMER:
				setPerformer(PERFORMER_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PRIORITY_UNIT:
				setPriorityUnit(PRIORITY_UNIT_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__PROCESS_HEADER:
				setProcessHeader((ProcessHeaderType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__RECORD_TYPE:
				setRecordType((RecordTypeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__REDEFINABLE_HEADER:
				setRedefinableHeader((RedefinableHeaderType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__RESPONSIBLE:
				setResponsible(RESPONSIBLE_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__RESPONSIBLES:
				setResponsibles((ResponsiblesType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__ROUTE:
				setRoute((RouteType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__SCHEMA_TYPE:
				setSchemaType((SchemaTypeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__SCRIPT:
				setScript((ScriptType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__SIMULATION_INFORMATION:
				setSimulationInformation((SimulationInformationType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__SPLIT:
				setSplit((SplitType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__START_MODE:
				setStartMode((StartModeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__SUB_FLOW:
				setSubFlow((SubFlowType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TIME_ESTIMATION:
				setTimeEstimation((TimeEstimationType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TOOL:
				setTool((ToolType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION:
				setTransition((TransitionType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_REF:
				setTransitionRef((TransitionRefType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_REFS:
				setTransitionRefs((TransitionRefsType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_RESTRICTION:
				setTransitionRestriction((TransitionRestrictionType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_RESTRICTIONS:
				setTransitionRestrictions((TransitionRestrictionsType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITIONS:
				setTransitions((TransitionsType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TYPE_DECLARATION:
				setTypeDeclaration((TypeDeclarationType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__TYPE_DECLARATIONS:
				setTypeDeclarations((TypeDeclarationsType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__UNION_TYPE:
				setUnionType((UnionTypeType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__VALID_FROM:
				setValidFrom(VALID_FROM_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__VALID_TO:
				setValidTo(VALID_TO_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__VENDOR:
				setVendor(VENDOR_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__WAITING_TIME:
				setWaitingTime(WAITING_TIME_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__WORKFLOW_PROCESS:
				setWorkflowProcess((WorkflowProcessType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__WORKFLOW_PROCESSES:
				setWorkflowProcesses((WorkflowProcessesType)null);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__WORKING_TIME:
				setWorkingTime(WORKING_TIME_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__XPDL_VERSION:
				setXPDLVersion(XPDL_VERSION_EDEFAULT);
				return;
			case Xpdl1Package.DOCUMENT_ROOT__XPRESSION:
				setXpression((XpressionType)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Xpdl1Package.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case Xpdl1Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case Xpdl1Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITIES:
				return getActivities() != null;
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY:
				return getActivity() != null;
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY_SET:
				return getActivitySet() != null;
			case Xpdl1Package.DOCUMENT_ROOT__ACTIVITY_SETS:
				return getActivitySets() != null;
			case Xpdl1Package.DOCUMENT_ROOT__ACTUAL_PARAMETER:
				return ACTUAL_PARAMETER_EDEFAULT == null ? getActualParameter() != null : !ACTUAL_PARAMETER_EDEFAULT.equals(getActualParameter());
			case Xpdl1Package.DOCUMENT_ROOT__ACTUAL_PARAMETERS:
				return getActualParameters() != null;
			case Xpdl1Package.DOCUMENT_ROOT__APPLICATION:
				return getApplication() != null;
			case Xpdl1Package.DOCUMENT_ROOT__APPLICATIONS:
				return getApplications() != null;
			case Xpdl1Package.DOCUMENT_ROOT__ARRAY_TYPE:
				return getArrayType() != null;
			case Xpdl1Package.DOCUMENT_ROOT__AUTHOR:
				return AUTHOR_EDEFAULT == null ? getAuthor() != null : !AUTHOR_EDEFAULT.equals(getAuthor());
			case Xpdl1Package.DOCUMENT_ROOT__AUTOMATIC:
				return getAutomatic() != null;
			case Xpdl1Package.DOCUMENT_ROOT__BASIC_TYPE:
				return getBasicType() != null;
			case Xpdl1Package.DOCUMENT_ROOT__BLOCK_ACTIVITY:
				return getBlockActivity() != null;
			case Xpdl1Package.DOCUMENT_ROOT__CODEPAGE:
				return CODEPAGE_EDEFAULT == null ? getCodepage() != null : !CODEPAGE_EDEFAULT.equals(getCodepage());
			case Xpdl1Package.DOCUMENT_ROOT__CONDITION:
				return getCondition() != null;
			case Xpdl1Package.DOCUMENT_ROOT__CONFORMANCE_CLASS:
				return getConformanceClass() != null;
			case Xpdl1Package.DOCUMENT_ROOT__COST:
				return COST_EDEFAULT == null ? getCost() != null : !COST_EDEFAULT.equals(getCost());
			case Xpdl1Package.DOCUMENT_ROOT__COST_UNIT:
				return COST_UNIT_EDEFAULT == null ? getCostUnit() != null : !COST_UNIT_EDEFAULT.equals(getCostUnit());
			case Xpdl1Package.DOCUMENT_ROOT__COUNTRYKEY:
				return COUNTRYKEY_EDEFAULT == null ? getCountrykey() != null : !COUNTRYKEY_EDEFAULT.equals(getCountrykey());
			case Xpdl1Package.DOCUMENT_ROOT__CREATED:
				return CREATED_EDEFAULT == null ? getCreated() != null : !CREATED_EDEFAULT.equals(getCreated());
			case Xpdl1Package.DOCUMENT_ROOT__DATA_FIELD:
				return getDataField() != null;
			case Xpdl1Package.DOCUMENT_ROOT__DATA_FIELDS:
				return getDataFields() != null;
			case Xpdl1Package.DOCUMENT_ROOT__DATA_TYPE:
				return getDataType() != null;
			case Xpdl1Package.DOCUMENT_ROOT__DEADLINE:
				return getDeadline() != null;
			case Xpdl1Package.DOCUMENT_ROOT__DECLARED_TYPE:
				return getDeclaredType() != null;
			case Xpdl1Package.DOCUMENT_ROOT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
			case Xpdl1Package.DOCUMENT_ROOT__DOCUMENTATION:
				return DOCUMENTATION_EDEFAULT == null ? getDocumentation() != null : !DOCUMENTATION_EDEFAULT.equals(getDocumentation());
			case Xpdl1Package.DOCUMENT_ROOT__DURATION:
				return DURATION_EDEFAULT == null ? getDuration() != null : !DURATION_EDEFAULT.equals(getDuration());
			case Xpdl1Package.DOCUMENT_ROOT__ENUMERATION_TYPE:
				return getEnumerationType() != null;
			case Xpdl1Package.DOCUMENT_ROOT__ENUMERATION_VALUE:
				return getEnumerationValue() != null;
			case Xpdl1Package.DOCUMENT_ROOT__EXTENDED_ATTRIBUTE:
				return getExtendedAttribute() != null;
			case Xpdl1Package.DOCUMENT_ROOT__EXTENDED_ATTRIBUTES:
				return getExtendedAttributes() != null;
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_PACKAGE:
				return getExternalPackage() != null;
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_PACKAGES:
				return getExternalPackages() != null;
			case Xpdl1Package.DOCUMENT_ROOT__EXTERNAL_REFERENCE:
				return getExternalReference() != null;
			case Xpdl1Package.DOCUMENT_ROOT__FINISH_MODE:
				return getFinishMode() != null;
			case Xpdl1Package.DOCUMENT_ROOT__FORMAL_PARAMETER:
				return getFormalParameter() != null;
			case Xpdl1Package.DOCUMENT_ROOT__FORMAL_PARAMETERS:
				return getFormalParameters() != null;
			case Xpdl1Package.DOCUMENT_ROOT__ICON:
				return ICON_EDEFAULT == null ? getIcon() != null : !ICON_EDEFAULT.equals(getIcon());
			case Xpdl1Package.DOCUMENT_ROOT__IMPLEMENTATION:
				return getImplementation() != null;
			case Xpdl1Package.DOCUMENT_ROOT__INITIAL_VALUE:
				return INITIAL_VALUE_EDEFAULT == null ? getInitialValue() != null : !INITIAL_VALUE_EDEFAULT.equals(getInitialValue());
			case Xpdl1Package.DOCUMENT_ROOT__JOIN:
				return getJoin() != null;
			case Xpdl1Package.DOCUMENT_ROOT__LENGTH:
				return LENGTH_EDEFAULT == null ? getLength() != null : !LENGTH_EDEFAULT.equals(getLength());
			case Xpdl1Package.DOCUMENT_ROOT__LIMIT:
				return LIMIT_EDEFAULT == null ? getLimit() != null : !LIMIT_EDEFAULT.equals(getLimit());
			case Xpdl1Package.DOCUMENT_ROOT__LIST_TYPE:
				return getListType() != null;
			case Xpdl1Package.DOCUMENT_ROOT__MANUAL:
				return getManual() != null;
			case Xpdl1Package.DOCUMENT_ROOT__MEMBER:
				return getMember() != null;
			case Xpdl1Package.DOCUMENT_ROOT__NO:
				return getNo() != null;
			case Xpdl1Package.DOCUMENT_ROOT__PACKAGE:
				return getPackage() != null;
			case Xpdl1Package.DOCUMENT_ROOT__PACKAGE_HEADER:
				return getPackageHeader() != null;
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANT:
				return getParticipant() != null;
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANTS:
				return getParticipants() != null;
			case Xpdl1Package.DOCUMENT_ROOT__PARTICIPANT_TYPE:
				return getParticipantType() != null;
			case Xpdl1Package.DOCUMENT_ROOT__PERFORMER:
				return PERFORMER_EDEFAULT == null ? getPerformer() != null : !PERFORMER_EDEFAULT.equals(getPerformer());
			case Xpdl1Package.DOCUMENT_ROOT__PRIORITY:
				return PRIORITY_EDEFAULT == null ? getPriority() != null : !PRIORITY_EDEFAULT.equals(getPriority());
			case Xpdl1Package.DOCUMENT_ROOT__PRIORITY_UNIT:
				return PRIORITY_UNIT_EDEFAULT == null ? getPriorityUnit() != null : !PRIORITY_UNIT_EDEFAULT.equals(getPriorityUnit());
			case Xpdl1Package.DOCUMENT_ROOT__PROCESS_HEADER:
				return getProcessHeader() != null;
			case Xpdl1Package.DOCUMENT_ROOT__RECORD_TYPE:
				return getRecordType() != null;
			case Xpdl1Package.DOCUMENT_ROOT__REDEFINABLE_HEADER:
				return getRedefinableHeader() != null;
			case Xpdl1Package.DOCUMENT_ROOT__RESPONSIBLE:
				return RESPONSIBLE_EDEFAULT == null ? getResponsible() != null : !RESPONSIBLE_EDEFAULT.equals(getResponsible());
			case Xpdl1Package.DOCUMENT_ROOT__RESPONSIBLES:
				return getResponsibles() != null;
			case Xpdl1Package.DOCUMENT_ROOT__ROUTE:
				return getRoute() != null;
			case Xpdl1Package.DOCUMENT_ROOT__SCHEMA_TYPE:
				return getSchemaType() != null;
			case Xpdl1Package.DOCUMENT_ROOT__SCRIPT:
				return getScript() != null;
			case Xpdl1Package.DOCUMENT_ROOT__SIMULATION_INFORMATION:
				return getSimulationInformation() != null;
			case Xpdl1Package.DOCUMENT_ROOT__SPLIT:
				return getSplit() != null;
			case Xpdl1Package.DOCUMENT_ROOT__START_MODE:
				return getStartMode() != null;
			case Xpdl1Package.DOCUMENT_ROOT__SUB_FLOW:
				return getSubFlow() != null;
			case Xpdl1Package.DOCUMENT_ROOT__TIME_ESTIMATION:
				return getTimeEstimation() != null;
			case Xpdl1Package.DOCUMENT_ROOT__TOOL:
				return getTool() != null;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION:
				return getTransition() != null;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_REF:
				return getTransitionRef() != null;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_REFS:
				return getTransitionRefs() != null;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_RESTRICTION:
				return getTransitionRestriction() != null;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITION_RESTRICTIONS:
				return getTransitionRestrictions() != null;
			case Xpdl1Package.DOCUMENT_ROOT__TRANSITIONS:
				return getTransitions() != null;
			case Xpdl1Package.DOCUMENT_ROOT__TYPE_DECLARATION:
				return getTypeDeclaration() != null;
			case Xpdl1Package.DOCUMENT_ROOT__TYPE_DECLARATIONS:
				return getTypeDeclarations() != null;
			case Xpdl1Package.DOCUMENT_ROOT__UNION_TYPE:
				return getUnionType() != null;
			case Xpdl1Package.DOCUMENT_ROOT__VALID_FROM:
				return VALID_FROM_EDEFAULT == null ? getValidFrom() != null : !VALID_FROM_EDEFAULT.equals(getValidFrom());
			case Xpdl1Package.DOCUMENT_ROOT__VALID_TO:
				return VALID_TO_EDEFAULT == null ? getValidTo() != null : !VALID_TO_EDEFAULT.equals(getValidTo());
			case Xpdl1Package.DOCUMENT_ROOT__VENDOR:
				return VENDOR_EDEFAULT == null ? getVendor() != null : !VENDOR_EDEFAULT.equals(getVendor());
			case Xpdl1Package.DOCUMENT_ROOT__VERSION:
				return VERSION_EDEFAULT == null ? getVersion() != null : !VERSION_EDEFAULT.equals(getVersion());
			case Xpdl1Package.DOCUMENT_ROOT__WAITING_TIME:
				return WAITING_TIME_EDEFAULT == null ? getWaitingTime() != null : !WAITING_TIME_EDEFAULT.equals(getWaitingTime());
			case Xpdl1Package.DOCUMENT_ROOT__WORKFLOW_PROCESS:
				return getWorkflowProcess() != null;
			case Xpdl1Package.DOCUMENT_ROOT__WORKFLOW_PROCESSES:
				return getWorkflowProcesses() != null;
			case Xpdl1Package.DOCUMENT_ROOT__WORKING_TIME:
				return WORKING_TIME_EDEFAULT == null ? getWorkingTime() != null : !WORKING_TIME_EDEFAULT.equals(getWorkingTime());
			case Xpdl1Package.DOCUMENT_ROOT__XPDL_VERSION:
				return XPDL_VERSION_EDEFAULT == null ? getXPDLVersion() != null : !XPDL_VERSION_EDEFAULT.equals(getXPDLVersion());
			case Xpdl1Package.DOCUMENT_ROOT__XPRESSION:
				return getXpression() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
