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
 * A representation of the model object '<em><b>TGroup</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TGroup#getCategoryValueRef <em>Category Value Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTGroup()
 * @model extendedMetaData="name='tGroup' kind='elementOnly'"
 * @generated
 */
public interface TGroup extends TArtifact {
	/**
	 * Returns the value of the '<em><b>Category Value Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category Value Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category Value Ref</em>' attribute.
	 * @see #setCategoryValueRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTGroup_CategoryValueRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='categoryValueRef'"
	 * @generated
	 */
	QName getCategoryValueRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TGroup#getCategoryValueRef <em>Category Value Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category Value Ref</em>' attribute.
	 * @see #getCategoryValueRef()
	 * @generated
	 */
	void setCategoryValueRef(QName value);

} // TGroup
