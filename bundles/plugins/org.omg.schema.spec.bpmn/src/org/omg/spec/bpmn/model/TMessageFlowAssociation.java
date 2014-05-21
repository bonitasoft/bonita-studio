/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TMessage Flow Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TMessageFlowAssociation#getInnerMessageFlowRef <em>Inner Message Flow Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMessageFlowAssociation#getOuterMessageFlowRef <em>Outer Message Flow Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTMessageFlowAssociation()
 * @model extendedMetaData="name='tMessageFlowAssociation' kind='elementOnly'"
 * @generated
 */
public interface TMessageFlowAssociation extends TBaseElement {
	/**
	 * Returns the value of the '<em><b>Inner Message Flow Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inner Message Flow Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inner Message Flow Ref</em>' attribute.
	 * @see #setInnerMessageFlowRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMessageFlowAssociation_InnerMessageFlowRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
	 *        extendedMetaData="kind='attribute' name='innerMessageFlowRef'"
	 * @generated
	 */
	QName getInnerMessageFlowRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMessageFlowAssociation#getInnerMessageFlowRef <em>Inner Message Flow Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inner Message Flow Ref</em>' attribute.
	 * @see #getInnerMessageFlowRef()
	 * @generated
	 */
	void setInnerMessageFlowRef(QName value);

	/**
	 * Returns the value of the '<em><b>Outer Message Flow Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outer Message Flow Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outer Message Flow Ref</em>' attribute.
	 * @see #setOuterMessageFlowRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMessageFlowAssociation_OuterMessageFlowRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
	 *        extendedMetaData="kind='attribute' name='outerMessageFlowRef'"
	 * @generated
	 */
	QName getOuterMessageFlowRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMessageFlowAssociation#getOuterMessageFlowRef <em>Outer Message Flow Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outer Message Flow Ref</em>' attribute.
	 * @see #getOuterMessageFlowRef()
	 * @generated
	 */
	void setOuterMessageFlowRef(QName value);

} // TMessageFlowAssociation
