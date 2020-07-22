/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getLimit <em>Limit</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getRoute <em>Route</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getBlockActivity <em>Block Activity</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getPerformer <em>Performer</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getStartMode <em>Start Mode</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getFinishMode <em>Finish Mode</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getDeadline <em>Deadline</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getSimulationInformation <em>Simulation Information</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getTransitionRestrictions <em>Transition Restrictions</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getId <em>Id</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivityType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType()
 * @model extendedMetaData="name='Activity_._type' kind='elementOnly'"
 * @generated
 */
public interface ActivityType extends EObject {
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_Description()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Description' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_Limit()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Limit' namespace='##targetNamespace'"
	 * @generated
	 */
	String getLimit();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getLimit <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Limit</em>' attribute.
	 * @see #getLimit()
	 * @generated
	 */
	void setLimit(String value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_Route()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Route' namespace='##targetNamespace'"
	 * @generated
	 */
	RouteType getRoute();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getRoute <em>Route</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Route</em>' containment reference.
	 * @see #getRoute()
	 * @generated
	 */
	void setRoute(RouteType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_Implementation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Implementation' namespace='##targetNamespace'"
	 * @generated
	 */
	ImplementationType getImplementation();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getImplementation <em>Implementation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' containment reference.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(ImplementationType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_BlockActivity()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='BlockActivity' namespace='##targetNamespace'"
	 * @generated
	 */
	BlockActivityType getBlockActivity();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getBlockActivity <em>Block Activity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Block Activity</em>' containment reference.
	 * @see #getBlockActivity()
	 * @generated
	 */
	void setBlockActivity(BlockActivityType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_Performer()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Performer' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPerformer();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getPerformer <em>Performer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Performer</em>' attribute.
	 * @see #getPerformer()
	 * @generated
	 */
	void setPerformer(String value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_StartMode()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='StartMode' namespace='##targetNamespace'"
	 * @generated
	 */
	StartModeType getStartMode();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getStartMode <em>Start Mode</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Mode</em>' containment reference.
	 * @see #getStartMode()
	 * @generated
	 */
	void setStartMode(StartModeType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_FinishMode()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='FinishMode' namespace='##targetNamespace'"
	 * @generated
	 */
	FinishModeType getFinishMode();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getFinishMode <em>Finish Mode</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Finish Mode</em>' containment reference.
	 * @see #getFinishMode()
	 * @generated
	 */
	void setFinishMode(FinishModeType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_Priority()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Priority' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPriority();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(String value);

	/**
	 * Returns the value of the '<em><b>Deadline</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.DeadlineType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deadline</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deadline</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_Deadline()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Deadline' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<DeadlineType> getDeadline();

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_SimulationInformation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='SimulationInformation' namespace='##targetNamespace'"
	 * @generated
	 */
	SimulationInformationType getSimulationInformation();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getSimulationInformation <em>Simulation Information</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simulation Information</em>' containment reference.
	 * @see #getSimulationInformation()
	 * @generated
	 */
	void setSimulationInformation(SimulationInformationType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_Icon()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Icon' namespace='##targetNamespace'"
	 * @generated
	 */
	String getIcon();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getIcon <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icon</em>' attribute.
	 * @see #getIcon()
	 * @generated
	 */
	void setIcon(String value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_Documentation()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Documentation' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDocumentation();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getDocumentation <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' attribute.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(String value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_TransitionRestrictions()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='TransitionRestrictions' namespace='##targetNamespace'"
	 * @generated
	 */
	TransitionRestrictionsType getTransitionRestrictions();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getTransitionRestrictions <em>Transition Restrictions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transition Restrictions</em>' containment reference.
	 * @see #getTransitionRestrictions()
	 * @generated
	 */
	void setTransitionRestrictions(TransitionRestrictionsType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_ExtendedAttributes()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExtendedAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	ExtendedAttributesType getExtendedAttributes();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getExtendedAttributes <em>Extended Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Attributes</em>' containment reference.
	 * @see #getExtendedAttributes()
	 * @generated
	 */
	void setExtendedAttributes(ExtendedAttributesType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='Id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getId <em>Id</em>}' attribute.
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivityType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='Name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ActivityType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ActivityType
