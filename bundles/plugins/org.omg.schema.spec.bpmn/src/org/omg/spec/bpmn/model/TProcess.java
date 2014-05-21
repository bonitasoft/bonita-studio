/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TProcess</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getAuditing <em>Auditing</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getMonitoring <em>Monitoring</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getProperty <em>Property</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getLaneSet <em>Lane Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getFlowElementGroup <em>Flow Element Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getFlowElement <em>Flow Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getArtifactGroup <em>Artifact Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getResourceRoleGroup <em>Resource Role Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getResourceRole <em>Resource Role</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getCorrelationSubscription <em>Correlation Subscription</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getSupports <em>Supports</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getDefinitionalCollaborationRef <em>Definitional Collaboration Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#isIsClosed <em>Is Closed</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#isIsExecutable <em>Is Executable</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TProcess#getProcessType <em>Process Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess()
 * @model extendedMetaData="name='tProcess' kind='elementOnly'"
 * @generated
 */
public interface TProcess extends TCallableElement {
	/**
	 * Returns the value of the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auditing</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auditing</em>' containment reference.
	 * @see #setAuditing(TAuditing)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_Auditing()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='auditing' namespace='##targetNamespace'"
	 * @generated
	 */
	TAuditing getAuditing();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TProcess#getAuditing <em>Auditing</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auditing</em>' containment reference.
	 * @see #getAuditing()
	 * @generated
	 */
	void setAuditing(TAuditing value);

	/**
	 * Returns the value of the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Monitoring</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Monitoring</em>' containment reference.
	 * @see #setMonitoring(TMonitoring)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_Monitoring()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='monitoring' namespace='##targetNamespace'"
	 * @generated
	 */
	TMonitoring getMonitoring();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TProcess#getMonitoring <em>Monitoring</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Monitoring</em>' containment reference.
	 * @see #getMonitoring()
	 * @generated
	 */
	void setMonitoring(TMonitoring value);

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_Property()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='property' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TProperty> getProperty();

	/**
	 * Returns the value of the '<em><b>Lane Set</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TLaneSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lane Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lane Set</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_LaneSet()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='laneSet' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TLaneSet> getLaneSet();

	/**
	 * Returns the value of the '<em><b>Flow Element Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flow Element Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flow Element Group</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_FlowElementGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='flowElement:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getFlowElementGroup();

	/**
	 * Returns the value of the '<em><b>Flow Element</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TFlowElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flow Element</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flow Element</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_FlowElement()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='flowElement' namespace='##targetNamespace' group='flowElement:group'"
	 * @generated
	 */
	EList<TFlowElement> getFlowElement();

	/**
	 * Returns the value of the '<em><b>Artifact Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifact Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact Group</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_ArtifactGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='artifact:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getArtifactGroup();

	/**
	 * Returns the value of the '<em><b>Artifact</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TArtifact}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifact</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_Artifact()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='artifact' namespace='##targetNamespace' group='artifact:group'"
	 * @generated
	 */
	EList<TArtifact> getArtifact();

	/**
	 * Returns the value of the '<em><b>Resource Role Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Role Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Role Group</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_ResourceRoleGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='resourceRole:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getResourceRoleGroup();

	/**
	 * Returns the value of the '<em><b>Resource Role</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TResourceRole}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Role</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Role</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_ResourceRole()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='resourceRole' namespace='##targetNamespace' group='resourceRole:group'"
	 * @generated
	 */
	EList<TResourceRole> getResourceRole();

	/**
	 * Returns the value of the '<em><b>Correlation Subscription</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TCorrelationSubscription}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Subscription</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlation Subscription</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_CorrelationSubscription()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='correlationSubscription' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TCorrelationSubscription> getCorrelationSubscription();

	/**
	 * Returns the value of the '<em><b>Supports</b></em>' attribute list.
	 * The list contents are of type {@link javax.xml.namespace.QName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supports</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supports</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_Supports()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='element' name='supports' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<QName> getSupports();

	/**
	 * Returns the value of the '<em><b>Definitional Collaboration Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definitional Collaboration Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definitional Collaboration Ref</em>' attribute.
	 * @see #setDefinitionalCollaborationRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_DefinitionalCollaborationRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='definitionalCollaborationRef'"
	 * @generated
	 */
	QName getDefinitionalCollaborationRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TProcess#getDefinitionalCollaborationRef <em>Definitional Collaboration Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definitional Collaboration Ref</em>' attribute.
	 * @see #getDefinitionalCollaborationRef()
	 * @generated
	 */
	void setDefinitionalCollaborationRef(QName value);

	/**
	 * Returns the value of the '<em><b>Is Closed</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Closed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Closed</em>' attribute.
	 * @see #isSetIsClosed()
	 * @see #unsetIsClosed()
	 * @see #setIsClosed(boolean)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_IsClosed()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isClosed'"
	 * @generated
	 */
	boolean isIsClosed();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TProcess#isIsClosed <em>Is Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Closed</em>' attribute.
	 * @see #isSetIsClosed()
	 * @see #unsetIsClosed()
	 * @see #isIsClosed()
	 * @generated
	 */
	void setIsClosed(boolean value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TProcess#isIsClosed <em>Is Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsClosed()
	 * @see #isIsClosed()
	 * @see #setIsClosed(boolean)
	 * @generated
	 */
	void unsetIsClosed();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TProcess#isIsClosed <em>Is Closed</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Closed</em>' attribute is set.
	 * @see #unsetIsClosed()
	 * @see #isIsClosed()
	 * @see #setIsClosed(boolean)
	 * @generated
	 */
	boolean isSetIsClosed();

	/**
	 * Returns the value of the '<em><b>Is Executable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Executable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Executable</em>' attribute.
	 * @see #isSetIsExecutable()
	 * @see #unsetIsExecutable()
	 * @see #setIsExecutable(boolean)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_IsExecutable()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isExecutable'"
	 * @generated
	 */
	boolean isIsExecutable();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TProcess#isIsExecutable <em>Is Executable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Executable</em>' attribute.
	 * @see #isSetIsExecutable()
	 * @see #unsetIsExecutable()
	 * @see #isIsExecutable()
	 * @generated
	 */
	void setIsExecutable(boolean value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TProcess#isIsExecutable <em>Is Executable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsExecutable()
	 * @see #isIsExecutable()
	 * @see #setIsExecutable(boolean)
	 * @generated
	 */
	void unsetIsExecutable();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TProcess#isIsExecutable <em>Is Executable</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Executable</em>' attribute is set.
	 * @see #unsetIsExecutable()
	 * @see #isIsExecutable()
	 * @see #setIsExecutable(boolean)
	 * @generated
	 */
	boolean isSetIsExecutable();

	/**
	 * Returns the value of the '<em><b>Process Type</b></em>' attribute.
	 * The default value is <code>"None"</code>.
	 * The literals are from the enumeration {@link org.omg.spec.bpmn.model.TProcessType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Type</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TProcessType
	 * @see #isSetProcessType()
	 * @see #unsetProcessType()
	 * @see #setProcessType(TProcessType)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTProcess_ProcessType()
	 * @model default="None" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='processType'"
	 * @generated
	 */
	TProcessType getProcessType();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TProcess#getProcessType <em>Process Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process Type</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TProcessType
	 * @see #isSetProcessType()
	 * @see #unsetProcessType()
	 * @see #getProcessType()
	 * @generated
	 */
	void setProcessType(TProcessType value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TProcess#getProcessType <em>Process Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetProcessType()
	 * @see #getProcessType()
	 * @see #setProcessType(TProcessType)
	 * @generated
	 */
	void unsetProcessType();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TProcess#getProcessType <em>Process Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Process Type</em>' attribute is set.
	 * @see #unsetProcessType()
	 * @see #getProcessType()
	 * @see #setProcessType(TProcessType)
	 * @generated
	 */
	boolean isSetProcessType();

} // TProcess
