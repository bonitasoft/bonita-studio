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
 * A representation of the model object '<em><b>TInput Output Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TInputOutputSpecification#getDataInput <em>Data Input</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TInputOutputSpecification#getDataOutput <em>Data Output</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TInputOutputSpecification#getInputSet <em>Input Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TInputOutputSpecification#getOutputSet <em>Output Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputSpecification()
 * @model extendedMetaData="name='tInputOutputSpecification' kind='elementOnly'"
 * @generated
 */
public interface TInputOutputSpecification extends TBaseElement {
	/**
	 * Returns the value of the '<em><b>Data Input</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TDataInput}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Input</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Input</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputSpecification_DataInput()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dataInput' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TDataInput> getDataInput();

	/**
	 * Returns the value of the '<em><b>Data Output</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TDataOutput}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Output</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Output</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputSpecification_DataOutput()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dataOutput' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TDataOutput> getDataOutput();

	/**
	 * Returns the value of the '<em><b>Input Set</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TInputSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Set</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputSpecification_InputSet()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='inputSet' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TInputSet> getInputSet();

	/**
	 * Returns the value of the '<em><b>Output Set</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TOutputSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Set</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputSpecification_OutputSet()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='outputSet' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TOutputSet> getOutputSet();

} // TInputOutputSpecification
