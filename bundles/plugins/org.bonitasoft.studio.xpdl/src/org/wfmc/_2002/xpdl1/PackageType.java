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
 * A representation of the model object '<em><b>Package Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getPackageHeader <em>Package Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getRedefinableHeader <em>Redefinable Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getConformanceClass <em>Conformance Class</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getScript <em>Script</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getExternalPackages <em>External Packages</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getTypeDeclarations <em>Type Declarations</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getApplications <em>Applications</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getDataFields <em>Data Fields</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getWorkflowProcesses <em>Workflow Processes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getId <em>Id</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType()
 * @model extendedMetaData="name='Package_._type' kind='elementOnly'"
 * @generated
 */
public interface PackageType extends EObject {
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_PackageHeader()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='PackageHeader' namespace='##targetNamespace'"
	 * @generated
	 */
	PackageHeaderType getPackageHeader();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getPackageHeader <em>Package Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Header</em>' containment reference.
	 * @see #getPackageHeader()
	 * @generated
	 */
	void setPackageHeader(PackageHeaderType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_RedefinableHeader()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='RedefinableHeader' namespace='##targetNamespace'"
	 * @generated
	 */
	RedefinableHeaderType getRedefinableHeader();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getRedefinableHeader <em>Redefinable Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Redefinable Header</em>' containment reference.
	 * @see #getRedefinableHeader()
	 * @generated
	 */
	void setRedefinableHeader(RedefinableHeaderType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_ConformanceClass()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ConformanceClass' namespace='##targetNamespace'"
	 * @generated
	 */
	ConformanceClassType getConformanceClass();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getConformanceClass <em>Conformance Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conformance Class</em>' containment reference.
	 * @see #getConformanceClass()
	 * @generated
	 */
	void setConformanceClass(ConformanceClassType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_Script()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Script' namespace='##targetNamespace'"
	 * @generated
	 */
	ScriptType getScript();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getScript <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' containment reference.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(ScriptType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_ExternalPackages()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExternalPackages' namespace='##targetNamespace'"
	 * @generated
	 */
	ExternalPackagesType getExternalPackages();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getExternalPackages <em>External Packages</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Packages</em>' containment reference.
	 * @see #getExternalPackages()
	 * @generated
	 */
	void setExternalPackages(ExternalPackagesType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_TypeDeclarations()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='TypeDeclarations' namespace='##targetNamespace'"
	 * @generated
	 */
	TypeDeclarationsType getTypeDeclarations();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getTypeDeclarations <em>Type Declarations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Declarations</em>' containment reference.
	 * @see #getTypeDeclarations()
	 * @generated
	 */
	void setTypeDeclarations(TypeDeclarationsType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_Participants()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Participants' namespace='##targetNamespace'"
	 * @generated
	 */
	ParticipantsType getParticipants();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getParticipants <em>Participants</em>}' containment reference.
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_Applications()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Applications' namespace='##targetNamespace'"
	 * @generated
	 */
	ApplicationsType getApplications();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getApplications <em>Applications</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applications</em>' containment reference.
	 * @see #getApplications()
	 * @generated
	 */
	void setApplications(ApplicationsType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_DataFields()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='DataFields' namespace='##targetNamespace'"
	 * @generated
	 */
	DataFieldsType getDataFields();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getDataFields <em>Data Fields</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Fields</em>' containment reference.
	 * @see #getDataFields()
	 * @generated
	 */
	void setDataFields(DataFieldsType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_WorkflowProcesses()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='WorkflowProcesses' namespace='##targetNamespace'"
	 * @generated
	 */
	WorkflowProcessesType getWorkflowProcesses();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getWorkflowProcesses <em>Workflow Processes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workflow Processes</em>' containment reference.
	 * @see #getWorkflowProcesses()
	 * @generated
	 */
	void setWorkflowProcesses(WorkflowProcessesType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_ExtendedAttributes()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExtendedAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	ExtendedAttributesType getExtendedAttributes();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getExtendedAttributes <em>Extended Attributes</em>}' containment reference.
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='Id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getId <em>Id</em>}' attribute.
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='Name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // PackageType
