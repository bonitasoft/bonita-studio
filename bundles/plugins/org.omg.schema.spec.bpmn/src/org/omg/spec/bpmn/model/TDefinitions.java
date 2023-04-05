/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.omg.spec.bpmn.di.BPMNDiagram;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TDefinitions</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getImport <em>Import</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getExtension <em>Extension</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getRootElementGroup <em>Root Element Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getRootElement <em>Root Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getBPMNDiagram <em>BPMN Diagram</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getRelationship <em>Relationship</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getExporter <em>Exporter</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getExporterVersion <em>Exporter Version</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getExpressionLanguage <em>Expression Language</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getId <em>Id</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getTypeLanguage <em>Type Language</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDefinitions#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions()
 * @model extendedMetaData="name='tDefinitions' kind='elementOnly'"
 * @generated
 */
public interface TDefinitions extends EObject {
	/**
	 * Returns the value of the '<em><b>Import</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TImport}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Import</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_Import()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='import' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TImport> getImport();

	/**
	 * Returns the value of the '<em><b>Extension</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TExtension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extension</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_Extension()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='extension' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TExtension> getExtension();

	/**
	 * Returns the value of the '<em><b>Root Element Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Element Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Element Group</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_RootElementGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='rootElement:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getRootElementGroup();

	/**
	 * Returns the value of the '<em><b>Root Element</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TRootElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Element</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Element</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_RootElement()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='rootElement' namespace='##targetNamespace' group='rootElement:group'"
	 * @generated
	 */
	EList<TRootElement> getRootElement();

	/**
	 * Returns the value of the '<em><b>BPMN Diagram</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.di.BPMNDiagram}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>BPMN Diagram</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>BPMN Diagram</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_BPMNDiagram()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='BPMNDiagram' namespace='http://www.omg.org/spec/BPMN/20100524/DI'"
	 * @generated
	 */
	EList<BPMNDiagram> getBPMNDiagram();

	/**
	 * Returns the value of the '<em><b>Relationship</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TRelationship}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relationship</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relationship</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_Relationship()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='relationship' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TRelationship> getRelationship();

	/**
	 * Returns the value of the '<em><b>Exporter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exporter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exporter</em>' attribute.
	 * @see #setExporter(String)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_Exporter()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='exporter'"
	 * @generated
	 */
	String getExporter();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TDefinitions#getExporter <em>Exporter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exporter</em>' attribute.
	 * @see #getExporter()
	 * @generated
	 */
	void setExporter(String value);

	/**
	 * Returns the value of the '<em><b>Exporter Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exporter Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exporter Version</em>' attribute.
	 * @see #setExporterVersion(String)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_ExporterVersion()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='exporterVersion'"
	 * @generated
	 */
	String getExporterVersion();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TDefinitions#getExporterVersion <em>Exporter Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exporter Version</em>' attribute.
	 * @see #getExporterVersion()
	 * @generated
	 */
	void setExporterVersion(String value);

	/**
	 * Returns the value of the '<em><b>Expression Language</b></em>' attribute.
	 * The default value is <code>"http://www.w3.org/1999/XPath"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression Language</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression Language</em>' attribute.
	 * @see #isSetExpressionLanguage()
	 * @see #unsetExpressionLanguage()
	 * @see #setExpressionLanguage(String)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_ExpressionLanguage()
	 * @model default="http://www.w3.org/1999/XPath" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="kind='attribute' name='expressionLanguage'"
	 * @generated
	 */
	String getExpressionLanguage();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TDefinitions#getExpressionLanguage <em>Expression Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression Language</em>' attribute.
	 * @see #isSetExpressionLanguage()
	 * @see #unsetExpressionLanguage()
	 * @see #getExpressionLanguage()
	 * @generated
	 */
	void setExpressionLanguage(String value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TDefinitions#getExpressionLanguage <em>Expression Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetExpressionLanguage()
	 * @see #getExpressionLanguage()
	 * @see #setExpressionLanguage(String)
	 * @generated
	 */
	void unsetExpressionLanguage();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TDefinitions#getExpressionLanguage <em>Expression Language</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Expression Language</em>' attribute is set.
	 * @see #unsetExpressionLanguage()
	 * @see #getExpressionLanguage()
	 * @see #setExpressionLanguage(String)
	 * @generated
	 */
	boolean isSetExpressionLanguage();

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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TDefinitions#getId <em>Id</em>}' attribute.
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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TDefinitions#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Target Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Namespace</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Namespace</em>' attribute.
	 * @see #setTargetNamespace(String)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_TargetNamespace()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI" required="true"
	 *        extendedMetaData="kind='attribute' name='targetNamespace'"
	 * @generated
	 */
	String getTargetNamespace();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TDefinitions#getTargetNamespace <em>Target Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Namespace</em>' attribute.
	 * @see #getTargetNamespace()
	 * @generated
	 */
	void setTargetNamespace(String value);

	/**
	 * Returns the value of the '<em><b>Type Language</b></em>' attribute.
	 * The default value is <code>"http://www.w3.org/2001/XMLSchema"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Language</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Language</em>' attribute.
	 * @see #isSetTypeLanguage()
	 * @see #unsetTypeLanguage()
	 * @see #setTypeLanguage(String)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_TypeLanguage()
	 * @model default="http://www.w3.org/2001/XMLSchema" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="kind='attribute' name='typeLanguage'"
	 * @generated
	 */
	String getTypeLanguage();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TDefinitions#getTypeLanguage <em>Type Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Language</em>' attribute.
	 * @see #isSetTypeLanguage()
	 * @see #unsetTypeLanguage()
	 * @see #getTypeLanguage()
	 * @generated
	 */
	void setTypeLanguage(String value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TDefinitions#getTypeLanguage <em>Type Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTypeLanguage()
	 * @see #getTypeLanguage()
	 * @see #setTypeLanguage(String)
	 * @generated
	 */
	void unsetTypeLanguage();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TDefinitions#getTypeLanguage <em>Type Language</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type Language</em>' attribute is set.
	 * @see #unsetTypeLanguage()
	 * @see #getTypeLanguage()
	 * @see #setTypeLanguage(String)
	 * @generated
	 */
	boolean isSetTypeLanguage();

	/**
	 * Returns the value of the '<em><b>Any Attribute</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Any Attribute</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Any Attribute</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDefinitions_AnyAttribute()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='attributeWildcard' wildcards='##other' name=':13' processing='lax'"
	 * @generated
	 */
	FeatureMap getAnyAttribute();

} // TDefinitions
