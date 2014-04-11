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
 * A representation of the model object '<em><b>TOperation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TOperation#getInMessageRef <em>In Message Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TOperation#getOutMessageRef <em>Out Message Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TOperation#getErrorRef <em>Error Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TOperation#getImplementationRef <em>Implementation Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TOperation#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTOperation()
 * @model extendedMetaData="name='tOperation' kind='elementOnly'"
 * @generated
 */
public interface TOperation extends TBaseElement {
	/**
	 * Returns the value of the '<em><b>In Message Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Message Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Message Ref</em>' attribute.
	 * @see #setInMessageRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTOperation_InMessageRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
	 *        extendedMetaData="kind='element' name='inMessageRef' namespace='##targetNamespace'"
	 * @generated
	 */
	QName getInMessageRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TOperation#getInMessageRef <em>In Message Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Message Ref</em>' attribute.
	 * @see #getInMessageRef()
	 * @generated
	 */
	void setInMessageRef(QName value);

	/**
	 * Returns the value of the '<em><b>Out Message Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Message Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Message Ref</em>' attribute.
	 * @see #setOutMessageRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTOperation_OutMessageRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='element' name='outMessageRef' namespace='##targetNamespace'"
	 * @generated
	 */
	QName getOutMessageRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TOperation#getOutMessageRef <em>Out Message Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Out Message Ref</em>' attribute.
	 * @see #getOutMessageRef()
	 * @generated
	 */
	void setOutMessageRef(QName value);

	/**
	 * Returns the value of the '<em><b>Error Ref</b></em>' attribute list.
	 * The list contents are of type {@link javax.xml.namespace.QName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error Ref</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Ref</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTOperation_ErrorRef()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='element' name='errorRef' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<QName> getErrorRef();

	/**
	 * Returns the value of the '<em><b>Implementation Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation Ref</em>' attribute.
	 * @see #setImplementationRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTOperation_ImplementationRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='implementationRef'"
	 * @generated
	 */
	QName getImplementationRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TOperation#getImplementationRef <em>Implementation Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation Ref</em>' attribute.
	 * @see #getImplementationRef()
	 * @generated
	 */
	void setImplementationRef(QName value);

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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTOperation_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TOperation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // TOperation
