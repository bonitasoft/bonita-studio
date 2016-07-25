/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workflow Process Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getProcessHeader <em>Process Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getRedefinableHeader <em>Redefinable Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getFormalParameters <em>Formal Parameters</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getDataFields <em>Data Fields</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getApplications <em>Applications</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getActivitySets <em>Activity Sets</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getActivities <em>Activities</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getAccessLevel <em>Access Level</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getId <em>Id</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType()
 * @model extendedMetaData="name='WorkflowProcess_._type' kind='elementOnly'"
 * @generated
 */
public interface WorkflowProcessType extends EObject {
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_ProcessHeader()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='ProcessHeader' namespace='##targetNamespace'"
	 * @generated
	 */
	ProcessHeaderType getProcessHeader();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getProcessHeader <em>Process Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process Header</em>' containment reference.
	 * @see #getProcessHeader()
	 * @generated
	 */
	void setProcessHeader(ProcessHeaderType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_RedefinableHeader()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='RedefinableHeader' namespace='##targetNamespace'"
	 * @generated
	 */
	RedefinableHeaderType getRedefinableHeader();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getRedefinableHeader <em>Redefinable Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Redefinable Header</em>' containment reference.
	 * @see #getRedefinableHeader()
	 * @generated
	 */
	void setRedefinableHeader(RedefinableHeaderType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_FormalParameters()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='FormalParameters' namespace='##targetNamespace'"
	 * @generated
	 */
	FormalParametersType getFormalParameters();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getFormalParameters <em>Formal Parameters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Formal Parameters</em>' containment reference.
	 * @see #getFormalParameters()
	 * @generated
	 */
	void setFormalParameters(FormalParametersType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_DataFields()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='DataFields' namespace='##targetNamespace'"
	 * @generated
	 */
	DataFieldsType getDataFields();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getDataFields <em>Data Fields</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Fields</em>' containment reference.
	 * @see #getDataFields()
	 * @generated
	 */
	void setDataFields(DataFieldsType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_Participants()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Participants' namespace='##targetNamespace'"
	 * @generated
	 */
	ParticipantsType getParticipants();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getParticipants <em>Participants</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participants</em>' containment reference.
	 * @see #getParticipants()
	 * @generated
	 */
	void setParticipants(ParticipantsType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_Applications()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Applications' namespace='##targetNamespace'"
	 * @generated
	 */
	ApplicationsType getApplications();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getApplications <em>Applications</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applications</em>' containment reference.
	 * @see #getApplications()
	 * @generated
	 */
	void setApplications(ApplicationsType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_ActivitySets()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ActivitySets' namespace='##targetNamespace'"
	 * @generated
	 */
	ActivitySetsType getActivitySets();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getActivitySets <em>Activity Sets</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Sets</em>' containment reference.
	 * @see #getActivitySets()
	 * @generated
	 */
	void setActivitySets(ActivitySetsType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_Activities()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Activities' namespace='##targetNamespace'"
	 * @generated
	 */
	ActivitiesType getActivities();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getActivities <em>Activities</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activities</em>' containment reference.
	 * @see #getActivities()
	 * @generated
	 */
	void setActivities(ActivitiesType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_Transitions()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Transitions' namespace='##targetNamespace'"
	 * @generated
	 */
	TransitionsType getTransitions();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getTransitions <em>Transitions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transitions</em>' containment reference.
	 * @see #getTransitions()
	 * @generated
	 */
	void setTransitions(TransitionsType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_ExtendedAttributes()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExtendedAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	ExtendedAttributesType getExtendedAttributes();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getExtendedAttributes <em>Extended Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Attributes</em>' containment reference.
	 * @see #getExtendedAttributes()
	 * @generated
	 */
	void setExtendedAttributes(ExtendedAttributesType value);

	/**
	 * Returns the value of the '<em><b>Access Level</b></em>' attribute.
	 * The literals are from the enumeration {@link org.wfmc._2002.xpdl1.AccessLevelType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access Level</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Access Level</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.AccessLevelType
	 * @see #isSetAccessLevel()
	 * @see #unsetAccessLevel()
	 * @see #setAccessLevel(AccessLevelType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_AccessLevel()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='AccessLevel'"
	 * @generated
	 */
	AccessLevelType getAccessLevel();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getAccessLevel <em>Access Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Access Level</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.AccessLevelType
	 * @see #isSetAccessLevel()
	 * @see #unsetAccessLevel()
	 * @see #getAccessLevel()
	 * @generated
	 */
	void setAccessLevel(AccessLevelType value);

	/**
	 * Unsets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getAccessLevel <em>Access Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAccessLevel()
	 * @see #getAccessLevel()
	 * @see #setAccessLevel(AccessLevelType)
	 * @generated
	 */
	void unsetAccessLevel();

	/**
	 * Returns whether the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getAccessLevel <em>Access Level</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Access Level</em>' attribute is set.
	 * @see #unsetAccessLevel()
	 * @see #getAccessLevel()
	 * @see #setAccessLevel(AccessLevelType)
	 * @generated
	 */
	boolean isSetAccessLevel();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='Id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getWorkflowProcessType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='Name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.WorkflowProcessType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // WorkflowProcessType
