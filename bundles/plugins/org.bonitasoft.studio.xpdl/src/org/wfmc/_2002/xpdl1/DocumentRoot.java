/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getActivities <em>Activities</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getActivitySet <em>Activity Set</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getActivitySets <em>Activity Sets</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getActualParameter <em>Actual Parameter</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getActualParameters <em>Actual Parameters</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getApplication <em>Application</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getApplications <em>Applications</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getArrayType <em>Array Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getAutomatic <em>Automatic</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getBasicType <em>Basic Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getBlockActivity <em>Block Activity</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getCodepage <em>Codepage</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getConformanceClass <em>Conformance Class</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getCost <em>Cost</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getCostUnit <em>Cost Unit</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getCountrykey <em>Countrykey</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getCreated <em>Created</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getDataField <em>Data Field</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getDataFields <em>Data Fields</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getDeadline <em>Deadline</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getDeclaredType <em>Declared Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getDescription <em>Description</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getEnumerationType <em>Enumeration Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getEnumerationValue <em>Enumeration Value</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getExtendedAttribute <em>Extended Attribute</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getExternalPackage <em>External Package</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getExternalPackages <em>External Packages</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getExternalReference <em>External Reference</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getFinishMode <em>Finish Mode</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getFormalParameter <em>Formal Parameter</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getFormalParameters <em>Formal Parameters</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getInitialValue <em>Initial Value</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getJoin <em>Join</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getLength <em>Length</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getLimit <em>Limit</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getListType <em>List Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getManual <em>Manual</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getMember <em>Member</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getNo <em>No</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getPackage <em>Package</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getPackageHeader <em>Package Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getParticipantType <em>Participant Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getPerformer <em>Performer</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getPriorityUnit <em>Priority Unit</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getProcessHeader <em>Process Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getRecordType <em>Record Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getRedefinableHeader <em>Redefinable Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getResponsible <em>Responsible</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getResponsibles <em>Responsibles</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getRoute <em>Route</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getSchemaType <em>Schema Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getScript <em>Script</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getSimulationInformation <em>Simulation Information</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getSplit <em>Split</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getStartMode <em>Start Mode</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getSubFlow <em>Sub Flow</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getTimeEstimation <em>Time Estimation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getTool <em>Tool</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRef <em>Transition Ref</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRefs <em>Transition Refs</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRestriction <em>Transition Restriction</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRestrictions <em>Transition Restrictions</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getTypeDeclaration <em>Type Declaration</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getTypeDeclarations <em>Type Declarations</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getUnionType <em>Union Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getValidFrom <em>Valid From</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getValidTo <em>Valid To</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getVendor <em>Vendor</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getVersion <em>Version</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getWaitingTime <em>Waiting Time</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getWorkflowProcess <em>Workflow Process</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getWorkflowProcesses <em>Workflow Processes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getWorkingTime <em>Working Time</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getXPDLVersion <em>XPDL Version</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DocumentRoot#getXpression <em>Xpression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Activities</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activities</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activities</em>' containment reference.
	 * @see #setActivities(ActivitiesType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Activities()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Activities' namespace='##targetNamespace'"
	 * @generated
	 */
	ActivitiesType getActivities();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getActivities <em>Activities</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activities</em>' containment reference.
	 * @see #getActivities()
	 * @generated
	 */
	void setActivities(ActivitiesType value);

	/**
	 * Returns the value of the '<em><b>Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' containment reference.
	 * @see #setActivity(ActivityType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Activity()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Activity' namespace='##targetNamespace'"
	 * @generated
	 */
	ActivityType getActivity();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getActivity <em>Activity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' containment reference.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(ActivityType value);

	/**
	 * Returns the value of the '<em><b>Activity Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Set</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Set</em>' containment reference.
	 * @see #setActivitySet(ActivitySetType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ActivitySet()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ActivitySet' namespace='##targetNamespace'"
	 * @generated
	 */
	ActivitySetType getActivitySet();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getActivitySet <em>Activity Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Set</em>' containment reference.
	 * @see #getActivitySet()
	 * @generated
	 */
	void setActivitySet(ActivitySetType value);

	/**
	 * Returns the value of the '<em><b>Activity Sets</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Sets</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Sets</em>' containment reference.
	 * @see #setActivitySets(ActivitySetsType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ActivitySets()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ActivitySets' namespace='##targetNamespace'"
	 * @generated
	 */
	ActivitySetsType getActivitySets();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getActivitySets <em>Activity Sets</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Sets</em>' containment reference.
	 * @see #getActivitySets()
	 * @generated
	 */
	void setActivitySets(ActivitySetsType value);

	/**
	 * Returns the value of the '<em><b>Actual Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actual Parameter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actual Parameter</em>' attribute.
	 * @see #setActualParameter(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ActualParameter()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ActualParameter' namespace='##targetNamespace'"
	 * @generated
	 */
	String getActualParameter();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getActualParameter <em>Actual Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Parameter</em>' attribute.
	 * @see #getActualParameter()
	 * @generated
	 */
	void setActualParameter(String value);

	/**
	 * Returns the value of the '<em><b>Actual Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actual Parameters</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actual Parameters</em>' containment reference.
	 * @see #setActualParameters(ActualParametersType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ActualParameters()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ActualParameters' namespace='##targetNamespace'"
	 * @generated
	 */
	ActualParametersType getActualParameters();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getActualParameters <em>Actual Parameters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Parameters</em>' containment reference.
	 * @see #getActualParameters()
	 * @generated
	 */
	void setActualParameters(ActualParametersType value);

	/**
	 * Returns the value of the '<em><b>Application</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Application</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Application</em>' containment reference.
	 * @see #setApplication(ApplicationType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Application()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Application' namespace='##targetNamespace'"
	 * @generated
	 */
	ApplicationType getApplication();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getApplication <em>Application</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Application</em>' containment reference.
	 * @see #getApplication()
	 * @generated
	 */
	void setApplication(ApplicationType value);

	/**
	 * Returns the value of the '<em><b>Applications</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applications</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applications</em>' containment reference.
	 * @see #setApplications(ApplicationsType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Applications()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Applications' namespace='##targetNamespace'"
	 * @generated
	 */
	ApplicationsType getApplications();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getApplications <em>Applications</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applications</em>' containment reference.
	 * @see #getApplications()
	 * @generated
	 */
	void setApplications(ApplicationsType value);

	/**
	 * Returns the value of the '<em><b>Array Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Type</em>' containment reference.
	 * @see #setArrayType(ArrayTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ArrayType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ArrayType' namespace='##targetNamespace'"
	 * @generated
	 */
	ArrayTypeType getArrayType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getArrayType <em>Array Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Type</em>' containment reference.
	 * @see #getArrayType()
	 * @generated
	 */
	void setArrayType(ArrayTypeType value);

	/**
	 * Returns the value of the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Author</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Author</em>' attribute.
	 * @see #setAuthor(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Author()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Author' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAuthor();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getAuthor <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Author</em>' attribute.
	 * @see #getAuthor()
	 * @generated
	 */
	void setAuthor(String value);

	/**
	 * Returns the value of the '<em><b>Automatic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Automatic</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Automatic</em>' containment reference.
	 * @see #setAutomatic(AutomaticType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Automatic()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Automatic' namespace='##targetNamespace'"
	 * @generated
	 */
	AutomaticType getAutomatic();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getAutomatic <em>Automatic</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Automatic</em>' containment reference.
	 * @see #getAutomatic()
	 * @generated
	 */
	void setAutomatic(AutomaticType value);

	/**
	 * Returns the value of the '<em><b>Basic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Basic Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Basic Type</em>' containment reference.
	 * @see #setBasicType(BasicTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_BasicType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='BasicType' namespace='##targetNamespace'"
	 * @generated
	 */
	BasicTypeType getBasicType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getBasicType <em>Basic Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Basic Type</em>' containment reference.
	 * @see #getBasicType()
	 * @generated
	 */
	void setBasicType(BasicTypeType value);

	/**
	 * Returns the value of the '<em><b>Block Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Block Activity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Block Activity</em>' containment reference.
	 * @see #setBlockActivity(BlockActivityType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_BlockActivity()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='BlockActivity' namespace='##targetNamespace'"
	 * @generated
	 */
	BlockActivityType getBlockActivity();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getBlockActivity <em>Block Activity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Block Activity</em>' containment reference.
	 * @see #getBlockActivity()
	 * @generated
	 */
	void setBlockActivity(BlockActivityType value);

	/**
	 * Returns the value of the '<em><b>Codepage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Codepage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Codepage</em>' attribute.
	 * @see #setCodepage(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Codepage()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Codepage' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCodepage();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getCodepage <em>Codepage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Codepage</em>' attribute.
	 * @see #getCodepage()
	 * @generated
	 */
	void setCodepage(String value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(ConditionType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Condition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Condition' namespace='##targetNamespace'"
	 * @generated
	 */
	ConditionType getCondition();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(ConditionType value);

	/**
	 * Returns the value of the '<em><b>Conformance Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conformance Class</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conformance Class</em>' containment reference.
	 * @see #setConformanceClass(ConformanceClassType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ConformanceClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ConformanceClass' namespace='##targetNamespace'"
	 * @generated
	 */
	ConformanceClassType getConformanceClass();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getConformanceClass <em>Conformance Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conformance Class</em>' containment reference.
	 * @see #getConformanceClass()
	 * @generated
	 */
	void setConformanceClass(ConformanceClassType value);

	/**
	 * Returns the value of the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cost</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cost</em>' attribute.
	 * @see #setCost(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Cost()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Cost' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCost();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getCost <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost</em>' attribute.
	 * @see #getCost()
	 * @generated
	 */
	void setCost(String value);

	/**
	 * Returns the value of the '<em><b>Cost Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cost Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cost Unit</em>' attribute.
	 * @see #setCostUnit(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_CostUnit()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='CostUnit' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCostUnit();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getCostUnit <em>Cost Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost Unit</em>' attribute.
	 * @see #getCostUnit()
	 * @generated
	 */
	void setCostUnit(String value);

	/**
	 * Returns the value of the '<em><b>Countrykey</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Countrykey</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Countrykey</em>' attribute.
	 * @see #setCountrykey(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Countrykey()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Countrykey' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCountrykey();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getCountrykey <em>Countrykey</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Countrykey</em>' attribute.
	 * @see #getCountrykey()
	 * @generated
	 */
	void setCountrykey(String value);

	/**
	 * Returns the value of the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Created</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Created</em>' attribute.
	 * @see #setCreated(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Created()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Created' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCreated();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getCreated <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Created</em>' attribute.
	 * @see #getCreated()
	 * @generated
	 */
	void setCreated(String value);

	/**
	 * Returns the value of the '<em><b>Data Field</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Field</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Field</em>' containment reference.
	 * @see #setDataField(DataFieldType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_DataField()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='DataField' namespace='##targetNamespace'"
	 * @generated
	 */
	DataFieldType getDataField();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDataField <em>Data Field</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Field</em>' containment reference.
	 * @see #getDataField()
	 * @generated
	 */
	void setDataField(DataFieldType value);

	/**
	 * Returns the value of the '<em><b>Data Fields</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Fields</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Fields</em>' containment reference.
	 * @see #setDataFields(DataFieldsType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_DataFields()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='DataFields' namespace='##targetNamespace'"
	 * @generated
	 */
	DataFieldsType getDataFields();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDataFields <em>Data Fields</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Fields</em>' containment reference.
	 * @see #getDataFields()
	 * @generated
	 */
	void setDataFields(DataFieldsType value);

	/**
	 * Returns the value of the '<em><b>Data Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Type</em>' containment reference.
	 * @see #setDataType(DataTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_DataType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='DataType' namespace='##targetNamespace'"
	 * @generated
	 */
	DataTypeType getDataType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDataType <em>Data Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' containment reference.
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(DataTypeType value);

	/**
	 * Returns the value of the '<em><b>Deadline</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deadline</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deadline</em>' containment reference.
	 * @see #setDeadline(DeadlineType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Deadline()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Deadline' namespace='##targetNamespace'"
	 * @generated
	 */
	DeadlineType getDeadline();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDeadline <em>Deadline</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deadline</em>' containment reference.
	 * @see #getDeadline()
	 * @generated
	 */
	void setDeadline(DeadlineType value);

	/**
	 * Returns the value of the '<em><b>Declared Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Type</em>' containment reference.
	 * @see #setDeclaredType(DeclaredTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_DeclaredType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='DeclaredType' namespace='##targetNamespace'"
	 * @generated
	 */
	DeclaredTypeType getDeclaredType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDeclaredType <em>Declared Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Type</em>' containment reference.
	 * @see #getDeclaredType()
	 * @generated
	 */
	void setDeclaredType(DeclaredTypeType value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Description()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Description' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' attribute.
	 * @see #setDocumentation(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Documentation()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Documentation' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDocumentation();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDocumentation <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' attribute.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(String value);

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Duration()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Duration' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDuration();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(String value);

	/**
	 * Returns the value of the '<em><b>Enumeration Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enumeration Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enumeration Type</em>' containment reference.
	 * @see #setEnumerationType(EnumerationTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_EnumerationType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='EnumerationType' namespace='##targetNamespace'"
	 * @generated
	 */
	EnumerationTypeType getEnumerationType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getEnumerationType <em>Enumeration Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enumeration Type</em>' containment reference.
	 * @see #getEnumerationType()
	 * @generated
	 */
	void setEnumerationType(EnumerationTypeType value);

	/**
	 * Returns the value of the '<em><b>Enumeration Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enumeration Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enumeration Value</em>' containment reference.
	 * @see #setEnumerationValue(EnumerationValueType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_EnumerationValue()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='EnumerationValue' namespace='##targetNamespace'"
	 * @generated
	 */
	EnumerationValueType getEnumerationValue();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getEnumerationValue <em>Enumeration Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enumeration Value</em>' containment reference.
	 * @see #getEnumerationValue()
	 * @generated
	 */
	void setEnumerationValue(EnumerationValueType value);

	/**
	 * Returns the value of the '<em><b>Extended Attribute</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Attribute</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Attribute</em>' containment reference.
	 * @see #setExtendedAttribute(ExtendedAttributeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ExtendedAttribute()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ExtendedAttribute' namespace='##targetNamespace'"
	 * @generated
	 */
	ExtendedAttributeType getExtendedAttribute();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getExtendedAttribute <em>Extended Attribute</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Attribute</em>' containment reference.
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	void setExtendedAttribute(ExtendedAttributeType value);

	/**
	 * Returns the value of the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Attributes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Attributes</em>' containment reference.
	 * @see #setExtendedAttributes(ExtendedAttributesType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ExtendedAttributes()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ExtendedAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	ExtendedAttributesType getExtendedAttributes();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getExtendedAttributes <em>Extended Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Attributes</em>' containment reference.
	 * @see #getExtendedAttributes()
	 * @generated
	 */
	void setExtendedAttributes(ExtendedAttributesType value);

	/**
	 * Returns the value of the '<em><b>External Package</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Package</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Package</em>' containment reference.
	 * @see #setExternalPackage(ExternalPackageType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ExternalPackage()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ExternalPackage' namespace='##targetNamespace'"
	 * @generated
	 */
	ExternalPackageType getExternalPackage();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getExternalPackage <em>External Package</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Package</em>' containment reference.
	 * @see #getExternalPackage()
	 * @generated
	 */
	void setExternalPackage(ExternalPackageType value);

	/**
	 * Returns the value of the '<em><b>External Packages</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Packages</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Packages</em>' containment reference.
	 * @see #setExternalPackages(ExternalPackagesType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ExternalPackages()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ExternalPackages' namespace='##targetNamespace'"
	 * @generated
	 */
	ExternalPackagesType getExternalPackages();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getExternalPackages <em>External Packages</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Packages</em>' containment reference.
	 * @see #getExternalPackages()
	 * @generated
	 */
	void setExternalPackages(ExternalPackagesType value);

	/**
	 * Returns the value of the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Reference</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Reference</em>' containment reference.
	 * @see #setExternalReference(ExternalReferenceType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ExternalReference()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ExternalReference' namespace='##targetNamespace'"
	 * @generated
	 */
	ExternalReferenceType getExternalReference();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getExternalReference <em>External Reference</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Reference</em>' containment reference.
	 * @see #getExternalReference()
	 * @generated
	 */
	void setExternalReference(ExternalReferenceType value);

	/**
	 * Returns the value of the '<em><b>Finish Mode</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Finish Mode</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Finish Mode</em>' containment reference.
	 * @see #setFinishMode(FinishModeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_FinishMode()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='FinishMode' namespace='##targetNamespace'"
	 * @generated
	 */
	FinishModeType getFinishMode();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getFinishMode <em>Finish Mode</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish Mode</em>' containment reference.
	 * @see #getFinishMode()
	 * @generated
	 */
	void setFinishMode(FinishModeType value);

	/**
	 * Returns the value of the '<em><b>Formal Parameter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formal Parameter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formal Parameter</em>' containment reference.
	 * @see #setFormalParameter(FormalParameterType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_FormalParameter()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='FormalParameter' namespace='##targetNamespace'"
	 * @generated
	 */
	FormalParameterType getFormalParameter();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getFormalParameter <em>Formal Parameter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Formal Parameter</em>' containment reference.
	 * @see #getFormalParameter()
	 * @generated
	 */
	void setFormalParameter(FormalParameterType value);

	/**
	 * Returns the value of the '<em><b>Formal Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formal Parameters</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formal Parameters</em>' containment reference.
	 * @see #setFormalParameters(FormalParametersType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_FormalParameters()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='FormalParameters' namespace='##targetNamespace'"
	 * @generated
	 */
	FormalParametersType getFormalParameters();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getFormalParameters <em>Formal Parameters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Formal Parameters</em>' containment reference.
	 * @see #getFormalParameters()
	 * @generated
	 */
	void setFormalParameters(FormalParametersType value);

	/**
	 * Returns the value of the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icon</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icon</em>' attribute.
	 * @see #setIcon(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Icon()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Icon' namespace='##targetNamespace'"
	 * @generated
	 */
	String getIcon();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getIcon <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icon</em>' attribute.
	 * @see #getIcon()
	 * @generated
	 */
	void setIcon(String value);

	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation</em>' containment reference.
	 * @see #setImplementation(ImplementationType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Implementation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Implementation' namespace='##targetNamespace'"
	 * @generated
	 */
	ImplementationType getImplementation();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getImplementation <em>Implementation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' containment reference.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(ImplementationType value);

	/**
	 * Returns the value of the '<em><b>Initial Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Value</em>' attribute.
	 * @see #setInitialValue(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_InitialValue()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='InitialValue' namespace='##targetNamespace'"
	 * @generated
	 */
	String getInitialValue();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getInitialValue <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Value</em>' attribute.
	 * @see #getInitialValue()
	 * @generated
	 */
	void setInitialValue(String value);

	/**
	 * Returns the value of the '<em><b>Join</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Join</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Join</em>' containment reference.
	 * @see #setJoin(JoinType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Join()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Join' namespace='##targetNamespace'"
	 * @generated
	 */
	JoinType getJoin();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getJoin <em>Join</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Join</em>' containment reference.
	 * @see #getJoin()
	 * @generated
	 */
	void setJoin(JoinType value);

	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Length()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Length' namespace='##targetNamespace'"
	 * @generated
	 */
	String getLength();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(String value);

	/**
	 * Returns the value of the '<em><b>Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Limit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Limit</em>' attribute.
	 * @see #setLimit(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Limit()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Limit' namespace='##targetNamespace'"
	 * @generated
	 */
	String getLimit();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getLimit <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Limit</em>' attribute.
	 * @see #getLimit()
	 * @generated
	 */
	void setLimit(String value);

	/**
	 * Returns the value of the '<em><b>List Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>List Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>List Type</em>' containment reference.
	 * @see #setListType(ListTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ListType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ListType' namespace='##targetNamespace'"
	 * @generated
	 */
	ListTypeType getListType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getListType <em>List Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>List Type</em>' containment reference.
	 * @see #getListType()
	 * @generated
	 */
	void setListType(ListTypeType value);

	/**
	 * Returns the value of the '<em><b>Manual</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manual</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manual</em>' containment reference.
	 * @see #setManual(ManualType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Manual()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Manual' namespace='##targetNamespace'"
	 * @generated
	 */
	ManualType getManual();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getManual <em>Manual</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manual</em>' containment reference.
	 * @see #getManual()
	 * @generated
	 */
	void setManual(ManualType value);

	/**
	 * Returns the value of the '<em><b>Member</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Member</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Member</em>' containment reference.
	 * @see #setMember(MemberType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Member()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Member' namespace='##targetNamespace'"
	 * @generated
	 */
	MemberType getMember();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getMember <em>Member</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Member</em>' containment reference.
	 * @see #getMember()
	 * @generated
	 */
	void setMember(MemberType value);

	/**
	 * Returns the value of the '<em><b>No</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>No</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>No</em>' containment reference.
	 * @see #setNo(NoType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_No()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='No' namespace='##targetNamespace'"
	 * @generated
	 */
	NoType getNo();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getNo <em>No</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>No</em>' containment reference.
	 * @see #getNo()
	 * @generated
	 */
	void setNo(NoType value);

	/**
	 * Returns the value of the '<em><b>Package</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package</em>' containment reference.
	 * @see #setPackage(PackageType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Package()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Package' namespace='##targetNamespace'"
	 * @generated
	 */
	PackageType getPackage();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getPackage <em>Package</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' containment reference.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(PackageType value);

	/**
	 * Returns the value of the '<em><b>Package Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Header</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Header</em>' containment reference.
	 * @see #setPackageHeader(PackageHeaderType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_PackageHeader()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='PackageHeader' namespace='##targetNamespace'"
	 * @generated
	 */
	PackageHeaderType getPackageHeader();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getPackageHeader <em>Package Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Header</em>' containment reference.
	 * @see #getPackageHeader()
	 * @generated
	 */
	void setPackageHeader(PackageHeaderType value);

	/**
	 * Returns the value of the '<em><b>Participant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant</em>' containment reference.
	 * @see #setParticipant(ParticipantType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Participant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Participant' namespace='##targetNamespace'"
	 * @generated
	 */
	ParticipantType getParticipant();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getParticipant <em>Participant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participant</em>' containment reference.
	 * @see #getParticipant()
	 * @generated
	 */
	void setParticipant(ParticipantType value);

	/**
	 * Returns the value of the '<em><b>Participants</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participants</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participants</em>' containment reference.
	 * @see #setParticipants(ParticipantsType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Participants()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Participants' namespace='##targetNamespace'"
	 * @generated
	 */
	ParticipantsType getParticipants();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getParticipants <em>Participants</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participants</em>' containment reference.
	 * @see #getParticipants()
	 * @generated
	 */
	void setParticipants(ParticipantsType value);

	/**
	 * Returns the value of the '<em><b>Participant Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant Type</em>' containment reference.
	 * @see #setParticipantType(ParticipantTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ParticipantType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ParticipantType' namespace='##targetNamespace'"
	 * @generated
	 */
	ParticipantTypeType getParticipantType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getParticipantType <em>Participant Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participant Type</em>' containment reference.
	 * @see #getParticipantType()
	 * @generated
	 */
	void setParticipantType(ParticipantTypeType value);

	/**
	 * Returns the value of the '<em><b>Performer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performer</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performer</em>' attribute.
	 * @see #setPerformer(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Performer()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Performer' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPerformer();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getPerformer <em>Performer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Performer</em>' attribute.
	 * @see #getPerformer()
	 * @generated
	 */
	void setPerformer(String value);

	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see #setPriority(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Priority()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Priority' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPriority();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(String value);

	/**
	 * Returns the value of the '<em><b>Priority Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority Unit</em>' attribute.
	 * @see #setPriorityUnit(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_PriorityUnit()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='PriorityUnit' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPriorityUnit();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getPriorityUnit <em>Priority Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority Unit</em>' attribute.
	 * @see #getPriorityUnit()
	 * @generated
	 */
	void setPriorityUnit(String value);

	/**
	 * Returns the value of the '<em><b>Process Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Header</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Header</em>' containment reference.
	 * @see #setProcessHeader(ProcessHeaderType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ProcessHeader()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ProcessHeader' namespace='##targetNamespace'"
	 * @generated
	 */
	ProcessHeaderType getProcessHeader();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getProcessHeader <em>Process Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process Header</em>' containment reference.
	 * @see #getProcessHeader()
	 * @generated
	 */
	void setProcessHeader(ProcessHeaderType value);

	/**
	 * Returns the value of the '<em><b>Record Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Record Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Record Type</em>' containment reference.
	 * @see #setRecordType(RecordTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_RecordType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='RecordType' namespace='##targetNamespace'"
	 * @generated
	 */
	RecordTypeType getRecordType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getRecordType <em>Record Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Record Type</em>' containment reference.
	 * @see #getRecordType()
	 * @generated
	 */
	void setRecordType(RecordTypeType value);

	/**
	 * Returns the value of the '<em><b>Redefinable Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Redefinable Header</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Redefinable Header</em>' containment reference.
	 * @see #setRedefinableHeader(RedefinableHeaderType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_RedefinableHeader()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='RedefinableHeader' namespace='##targetNamespace'"
	 * @generated
	 */
	RedefinableHeaderType getRedefinableHeader();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getRedefinableHeader <em>Redefinable Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Redefinable Header</em>' containment reference.
	 * @see #getRedefinableHeader()
	 * @generated
	 */
	void setRedefinableHeader(RedefinableHeaderType value);

	/**
	 * Returns the value of the '<em><b>Responsible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responsible</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsible</em>' attribute.
	 * @see #setResponsible(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Responsible()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Responsible' namespace='##targetNamespace'"
	 * @generated
	 */
	String getResponsible();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getResponsible <em>Responsible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Responsible</em>' attribute.
	 * @see #getResponsible()
	 * @generated
	 */
	void setResponsible(String value);

	/**
	 * Returns the value of the '<em><b>Responsibles</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responsibles</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsibles</em>' containment reference.
	 * @see #setResponsibles(ResponsiblesType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Responsibles()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Responsibles' namespace='##targetNamespace'"
	 * @generated
	 */
	ResponsiblesType getResponsibles();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getResponsibles <em>Responsibles</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Responsibles</em>' containment reference.
	 * @see #getResponsibles()
	 * @generated
	 */
	void setResponsibles(ResponsiblesType value);

	/**
	 * Returns the value of the '<em><b>Route</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Route</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Route</em>' containment reference.
	 * @see #setRoute(RouteType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Route()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Route' namespace='##targetNamespace'"
	 * @generated
	 */
	RouteType getRoute();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getRoute <em>Route</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Route</em>' containment reference.
	 * @see #getRoute()
	 * @generated
	 */
	void setRoute(RouteType value);

	/**
	 * Returns the value of the '<em><b>Schema Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema Type</em>' containment reference.
	 * @see #setSchemaType(SchemaTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_SchemaType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='SchemaType' namespace='##targetNamespace'"
	 * @generated
	 */
	SchemaTypeType getSchemaType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getSchemaType <em>Schema Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema Type</em>' containment reference.
	 * @see #getSchemaType()
	 * @generated
	 */
	void setSchemaType(SchemaTypeType value);

	/**
	 * Returns the value of the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' containment reference.
	 * @see #setScript(ScriptType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Script()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Script' namespace='##targetNamespace'"
	 * @generated
	 */
	ScriptType getScript();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getScript <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' containment reference.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(ScriptType value);

	/**
	 * Returns the value of the '<em><b>Simulation Information</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Simulation Information</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simulation Information</em>' containment reference.
	 * @see #setSimulationInformation(SimulationInformationType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_SimulationInformation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='SimulationInformation' namespace='##targetNamespace'"
	 * @generated
	 */
	SimulationInformationType getSimulationInformation();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getSimulationInformation <em>Simulation Information</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simulation Information</em>' containment reference.
	 * @see #getSimulationInformation()
	 * @generated
	 */
	void setSimulationInformation(SimulationInformationType value);

	/**
	 * Returns the value of the '<em><b>Split</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Split</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Split</em>' containment reference.
	 * @see #setSplit(SplitType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Split()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Split' namespace='##targetNamespace'"
	 * @generated
	 */
	SplitType getSplit();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getSplit <em>Split</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Split</em>' containment reference.
	 * @see #getSplit()
	 * @generated
	 */
	void setSplit(SplitType value);

	/**
	 * Returns the value of the '<em><b>Start Mode</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Mode</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Mode</em>' containment reference.
	 * @see #setStartMode(StartModeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_StartMode()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='StartMode' namespace='##targetNamespace'"
	 * @generated
	 */
	StartModeType getStartMode();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getStartMode <em>Start Mode</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Mode</em>' containment reference.
	 * @see #getStartMode()
	 * @generated
	 */
	void setStartMode(StartModeType value);

	/**
	 * Returns the value of the '<em><b>Sub Flow</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Flow</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Flow</em>' containment reference.
	 * @see #setSubFlow(SubFlowType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_SubFlow()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='SubFlow' namespace='##targetNamespace'"
	 * @generated
	 */
	SubFlowType getSubFlow();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getSubFlow <em>Sub Flow</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Flow</em>' containment reference.
	 * @see #getSubFlow()
	 * @generated
	 */
	void setSubFlow(SubFlowType value);

	/**
	 * Returns the value of the '<em><b>Time Estimation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Estimation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Estimation</em>' containment reference.
	 * @see #setTimeEstimation(TimeEstimationType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_TimeEstimation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='TimeEstimation' namespace='##targetNamespace'"
	 * @generated
	 */
	TimeEstimationType getTimeEstimation();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTimeEstimation <em>Time Estimation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Estimation</em>' containment reference.
	 * @see #getTimeEstimation()
	 * @generated
	 */
	void setTimeEstimation(TimeEstimationType value);

	/**
	 * Returns the value of the '<em><b>Tool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tool</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tool</em>' containment reference.
	 * @see #setTool(ToolType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Tool()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Tool' namespace='##targetNamespace'"
	 * @generated
	 */
	ToolType getTool();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTool <em>Tool</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tool</em>' containment reference.
	 * @see #getTool()
	 * @generated
	 */
	void setTool(ToolType value);

	/**
	 * Returns the value of the '<em><b>Transition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition</em>' containment reference.
	 * @see #setTransition(TransitionType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Transition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Transition' namespace='##targetNamespace'"
	 * @generated
	 */
	TransitionType getTransition();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransition <em>Transition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transition</em>' containment reference.
	 * @see #getTransition()
	 * @generated
	 */
	void setTransition(TransitionType value);

	/**
	 * Returns the value of the '<em><b>Transition Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition Ref</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition Ref</em>' containment reference.
	 * @see #setTransitionRef(TransitionRefType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_TransitionRef()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='TransitionRef' namespace='##targetNamespace'"
	 * @generated
	 */
	TransitionRefType getTransitionRef();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRef <em>Transition Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transition Ref</em>' containment reference.
	 * @see #getTransitionRef()
	 * @generated
	 */
	void setTransitionRef(TransitionRefType value);

	/**
	 * Returns the value of the '<em><b>Transition Refs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition Refs</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition Refs</em>' containment reference.
	 * @see #setTransitionRefs(TransitionRefsType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_TransitionRefs()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='TransitionRefs' namespace='##targetNamespace'"
	 * @generated
	 */
	TransitionRefsType getTransitionRefs();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRefs <em>Transition Refs</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transition Refs</em>' containment reference.
	 * @see #getTransitionRefs()
	 * @generated
	 */
	void setTransitionRefs(TransitionRefsType value);

	/**
	 * Returns the value of the '<em><b>Transition Restriction</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition Restriction</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition Restriction</em>' containment reference.
	 * @see #setTransitionRestriction(TransitionRestrictionType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_TransitionRestriction()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='TransitionRestriction' namespace='##targetNamespace'"
	 * @generated
	 */
	TransitionRestrictionType getTransitionRestriction();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRestriction <em>Transition Restriction</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transition Restriction</em>' containment reference.
	 * @see #getTransitionRestriction()
	 * @generated
	 */
	void setTransitionRestriction(TransitionRestrictionType value);

	/**
	 * Returns the value of the '<em><b>Transition Restrictions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition Restrictions</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition Restrictions</em>' containment reference.
	 * @see #setTransitionRestrictions(TransitionRestrictionsType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_TransitionRestrictions()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='TransitionRestrictions' namespace='##targetNamespace'"
	 * @generated
	 */
	TransitionRestrictionsType getTransitionRestrictions();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitionRestrictions <em>Transition Restrictions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transition Restrictions</em>' containment reference.
	 * @see #getTransitionRestrictions()
	 * @generated
	 */
	void setTransitionRestrictions(TransitionRestrictionsType value);

	/**
	 * Returns the value of the '<em><b>Transitions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transitions</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transitions</em>' containment reference.
	 * @see #setTransitions(TransitionsType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Transitions()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Transitions' namespace='##targetNamespace'"
	 * @generated
	 */
	TransitionsType getTransitions();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTransitions <em>Transitions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transitions</em>' containment reference.
	 * @see #getTransitions()
	 * @generated
	 */
	void setTransitions(TransitionsType value);

	/**
	 * Returns the value of the '<em><b>Type Declaration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Declaration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Declaration</em>' containment reference.
	 * @see #setTypeDeclaration(TypeDeclarationType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_TypeDeclaration()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='TypeDeclaration' namespace='##targetNamespace'"
	 * @generated
	 */
	TypeDeclarationType getTypeDeclaration();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTypeDeclaration <em>Type Declaration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Declaration</em>' containment reference.
	 * @see #getTypeDeclaration()
	 * @generated
	 */
	void setTypeDeclaration(TypeDeclarationType value);

	/**
	 * Returns the value of the '<em><b>Type Declarations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Declarations</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Declarations</em>' containment reference.
	 * @see #setTypeDeclarations(TypeDeclarationsType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_TypeDeclarations()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='TypeDeclarations' namespace='##targetNamespace'"
	 * @generated
	 */
	TypeDeclarationsType getTypeDeclarations();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getTypeDeclarations <em>Type Declarations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Declarations</em>' containment reference.
	 * @see #getTypeDeclarations()
	 * @generated
	 */
	void setTypeDeclarations(TypeDeclarationsType value);

	/**
	 * Returns the value of the '<em><b>Union Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Union Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Union Type</em>' containment reference.
	 * @see #setUnionType(UnionTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_UnionType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='UnionType' namespace='##targetNamespace'"
	 * @generated
	 */
	UnionTypeType getUnionType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getUnionType <em>Union Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Union Type</em>' containment reference.
	 * @see #getUnionType()
	 * @generated
	 */
	void setUnionType(UnionTypeType value);

	/**
	 * Returns the value of the '<em><b>Valid From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Valid From</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid From</em>' attribute.
	 * @see #setValidFrom(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ValidFrom()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ValidFrom' namespace='##targetNamespace'"
	 * @generated
	 */
	String getValidFrom();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getValidFrom <em>Valid From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid From</em>' attribute.
	 * @see #getValidFrom()
	 * @generated
	 */
	void setValidFrom(String value);

	/**
	 * Returns the value of the '<em><b>Valid To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Valid To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid To</em>' attribute.
	 * @see #setValidTo(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_ValidTo()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ValidTo' namespace='##targetNamespace'"
	 * @generated
	 */
	String getValidTo();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getValidTo <em>Valid To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid To</em>' attribute.
	 * @see #getValidTo()
	 * @generated
	 */
	void setValidTo(String value);

	/**
	 * Returns the value of the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vendor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vendor</em>' attribute.
	 * @see #setVendor(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Vendor()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Vendor' namespace='##targetNamespace'"
	 * @generated
	 */
	String getVendor();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getVendor <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vendor</em>' attribute.
	 * @see #getVendor()
	 * @generated
	 */
	void setVendor(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Version()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Version' namespace='##targetNamespace'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Waiting Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Waiting Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Waiting Time</em>' attribute.
	 * @see #setWaitingTime(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_WaitingTime()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='WaitingTime' namespace='##targetNamespace'"
	 * @generated
	 */
	String getWaitingTime();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getWaitingTime <em>Waiting Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Waiting Time</em>' attribute.
	 * @see #getWaitingTime()
	 * @generated
	 */
	void setWaitingTime(String value);

	/**
	 * Returns the value of the '<em><b>Workflow Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workflow Process</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workflow Process</em>' containment reference.
	 * @see #setWorkflowProcess(WorkflowProcessType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_WorkflowProcess()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='WorkflowProcess' namespace='##targetNamespace'"
	 * @generated
	 */
	WorkflowProcessType getWorkflowProcess();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getWorkflowProcess <em>Workflow Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workflow Process</em>' containment reference.
	 * @see #getWorkflowProcess()
	 * @generated
	 */
	void setWorkflowProcess(WorkflowProcessType value);

	/**
	 * Returns the value of the '<em><b>Workflow Processes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workflow Processes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workflow Processes</em>' containment reference.
	 * @see #setWorkflowProcesses(WorkflowProcessesType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_WorkflowProcesses()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='WorkflowProcesses' namespace='##targetNamespace'"
	 * @generated
	 */
	WorkflowProcessesType getWorkflowProcesses();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getWorkflowProcesses <em>Workflow Processes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workflow Processes</em>' containment reference.
	 * @see #getWorkflowProcesses()
	 * @generated
	 */
	void setWorkflowProcesses(WorkflowProcessesType value);

	/**
	 * Returns the value of the '<em><b>Working Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Working Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Time</em>' attribute.
	 * @see #setWorkingTime(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_WorkingTime()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='WorkingTime' namespace='##targetNamespace'"
	 * @generated
	 */
	String getWorkingTime();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getWorkingTime <em>Working Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Time</em>' attribute.
	 * @see #getWorkingTime()
	 * @generated
	 */
	void setWorkingTime(String value);

	/**
	 * Returns the value of the '<em><b>XPDL Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XPDL Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XPDL Version</em>' attribute.
	 * @see #setXPDLVersion(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_XPDLVersion()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='XPDLVersion' namespace='##targetNamespace'"
	 * @generated
	 */
	String getXPDLVersion();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getXPDLVersion <em>XPDL Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XPDL Version</em>' attribute.
	 * @see #getXPDLVersion()
	 * @generated
	 */
	void setXPDLVersion(String value);

	/**
	 * Returns the value of the '<em><b>Xpression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xpression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xpression</em>' containment reference.
	 * @see #setXpression(XpressionType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDocumentRoot_Xpression()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Xpression' namespace='##targetNamespace'"
	 * @generated
	 */
	XpressionType getXpression();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DocumentRoot#getXpression <em>Xpression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xpression</em>' containment reference.
	 * @see #getXpression()
	 * @generated
	 */
	void setXpression(XpressionType value);

} // DocumentRoot
