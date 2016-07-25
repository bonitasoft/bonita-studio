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
 * A representation of the model object '<em><b>TResource Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TResourceRole#getResourceRef <em>Resource Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TResourceRole#getResourceParameterBinding <em>Resource Parameter Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TResourceRole#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TResourceRole#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceRole()
 * @model extendedMetaData="name='tResourceRole' kind='elementOnly'"
 * @generated
 */
public interface TResourceRole extends TBaseElement {
	/**
	 * Returns the value of the '<em><b>Resource Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Ref</em>' attribute.
	 * @see #setResourceRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceRole_ResourceRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='element' name='resourceRef' namespace='##targetNamespace'"
	 * @generated
	 */
	QName getResourceRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TResourceRole#getResourceRef <em>Resource Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Ref</em>' attribute.
	 * @see #getResourceRef()
	 * @generated
	 */
	void setResourceRef(QName value);

	/**
	 * Returns the value of the '<em><b>Resource Parameter Binding</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TResourceParameterBinding}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Parameter Binding</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Parameter Binding</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceRole_ResourceParameterBinding()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='resourceParameterBinding' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TResourceParameterBinding> getResourceParameterBinding();

	/**
	 * Returns the value of the '<em><b>Resource Assignment Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Assignment Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Assignment Expression</em>' containment reference.
	 * @see #setResourceAssignmentExpression(TResourceAssignmentExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceRole_ResourceAssignmentExpression()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='resourceAssignmentExpression' namespace='##targetNamespace'"
	 * @generated
	 */
	TResourceAssignmentExpression getResourceAssignmentExpression();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TResourceRole#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Assignment Expression</em>' containment reference.
	 * @see #getResourceAssignmentExpression()
	 * @generated
	 */
	void setResourceAssignmentExpression(TResourceAssignmentExpression value);

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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceRole_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TResourceRole#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // TResourceRole
