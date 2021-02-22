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
 * A representation of the model object '<em><b>TLane Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TLaneSet#getLane <em>Lane</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TLaneSet#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTLaneSet()
 * @model extendedMetaData="name='tLaneSet' kind='elementOnly'"
 * @generated
 */
public interface TLaneSet extends TBaseElement {
	/**
	 * Returns the value of the '<em><b>Lane</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TLane}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lane</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lane</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTLaneSet_Lane()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='lane' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TLane> getLane();

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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTLaneSet_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TLaneSet#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // TLaneSet
