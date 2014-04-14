/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TLane</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TLane#getPartitionElement <em>Partition Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TLane#getFlowNodeRef <em>Flow Node Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TLane#getChildLaneSet <em>Child Lane Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TLane#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TLane#getPartitionElementRef <em>Partition Element Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTLane()
 * @model extendedMetaData="name='tLane' kind='elementOnly'"
 * @generated
 */
public interface TLane extends TBaseElement {
	/**
	 * Returns the value of the '<em><b>Partition Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Element</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partition Element</em>' containment reference.
	 * @see #setPartitionElement(TBaseElement)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTLane_PartitionElement()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='partitionElement' namespace='##targetNamespace'"
	 * @generated
	 */
	TBaseElement getPartitionElement();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TLane#getPartitionElement <em>Partition Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partition Element</em>' containment reference.
	 * @see #getPartitionElement()
	 * @generated
	 */
	void setPartitionElement(TBaseElement value);

	/**
	 * Returns the value of the '<em><b>Flow Node Ref</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flow Node Ref</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flow Node Ref</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTLane_FlowNodeRef()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
	 *        extendedMetaData="kind='element' name='flowNodeRef' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getFlowNodeRef();

	/**
	 * Returns the value of the '<em><b>Child Lane Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child Lane Set</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child Lane Set</em>' containment reference.
	 * @see #setChildLaneSet(TLaneSet)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTLane_ChildLaneSet()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='childLaneSet' namespace='##targetNamespace'"
	 * @generated
	 */
	TLaneSet getChildLaneSet();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TLane#getChildLaneSet <em>Child Lane Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Child Lane Set</em>' containment reference.
	 * @see #getChildLaneSet()
	 * @generated
	 */
	void setChildLaneSet(TLaneSet value);

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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTLane_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TLane#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Partition Element Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Element Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partition Element Ref</em>' attribute.
	 * @see #setPartitionElementRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTLane_PartitionElementRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='partitionElementRef'"
	 * @generated
	 */
	QName getPartitionElementRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TLane#getPartitionElementRef <em>Partition Element Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partition Element Ref</em>' attribute.
	 * @see #getPartitionElementRef()
	 * @generated
	 */
	void setPartitionElementRef(QName value);

} // TLane
