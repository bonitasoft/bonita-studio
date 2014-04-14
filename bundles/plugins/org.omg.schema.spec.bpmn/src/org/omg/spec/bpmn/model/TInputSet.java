/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TInput Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TInputSet#getDataInputRefs <em>Data Input Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TInputSet#getOptionalInputRefs <em>Optional Input Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TInputSet#getWhileExecutingInputRefs <em>While Executing Input Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TInputSet#getOutputSetRefs <em>Output Set Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TInputSet#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputSet()
 * @model extendedMetaData="name='tInputSet' kind='elementOnly'"
 * @generated
 */
public interface TInputSet extends TBaseElement {
	/**
	 * Returns the value of the '<em><b>Data Input Refs</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Input Refs</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Input Refs</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputSet_DataInputRefs()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
	 *        extendedMetaData="kind='element' name='dataInputRefs' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getDataInputRefs();

	/**
	 * Returns the value of the '<em><b>Optional Input Refs</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optional Input Refs</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional Input Refs</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputSet_OptionalInputRefs()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
	 *        extendedMetaData="kind='element' name='optionalInputRefs' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getOptionalInputRefs();

	/**
	 * Returns the value of the '<em><b>While Executing Input Refs</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>While Executing Input Refs</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>While Executing Input Refs</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputSet_WhileExecutingInputRefs()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
	 *        extendedMetaData="kind='element' name='whileExecutingInputRefs' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getWhileExecutingInputRefs();

	/**
	 * Returns the value of the '<em><b>Output Set Refs</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Set Refs</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Set Refs</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputSet_OutputSetRefs()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
	 *        extendedMetaData="kind='element' name='outputSetRefs' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getOutputSetRefs();

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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputSet_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TInputSet#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // TInputSet
