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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TBase Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TBaseElement#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TBaseElement#getExtensionElements <em>Extension Elements</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TBaseElement#getId <em>Id</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TBaseElement#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTBaseElement()
 * @model abstract="true"
 *        extendedMetaData="name='tBaseElement' kind='elementOnly'"
 * @generated
 */
public interface TBaseElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TDocumentation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTBaseElement_Documentation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='documentation' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TDocumentation> getDocumentation();

	/**
	 * Returns the value of the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension Elements</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extension Elements</em>' containment reference.
	 * @see #setExtensionElements(TExtensionElements)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTBaseElement_ExtensionElements()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='extensionElements' namespace='##targetNamespace'"
	 * @generated
	 */
	TExtensionElements getExtensionElements();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TBaseElement#getExtensionElements <em>Extension Elements</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extension Elements</em>' containment reference.
	 * @see #getExtensionElements()
	 * @generated
	 */
	void setExtensionElements(TExtensionElements value);

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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTBaseElement_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TBaseElement#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTBaseElement_AnyAttribute()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='attributeWildcard' wildcards='##other' name=':3' processing='lax'"
	 * @generated
	 */
	FeatureMap getAnyAttribute();

} // TBaseElement
