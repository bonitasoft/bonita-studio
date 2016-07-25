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
 * A representation of the model object '<em><b>TData Store Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TDataStoreReference#getDataState <em>Data State</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDataStoreReference#getDataStoreRef <em>Data Store Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDataStoreReference#getItemSubjectRef <em>Item Subject Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTDataStoreReference()
 * @model extendedMetaData="name='tDataStoreReference' kind='elementOnly'"
 * @generated
 */
public interface TDataStoreReference extends TFlowElement {
	/**
	 * Returns the value of the '<em><b>Data State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data State</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data State</em>' containment reference.
	 * @see #setDataState(TDataState)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDataStoreReference_DataState()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dataState' namespace='##targetNamespace'"
	 * @generated
	 */
	TDataState getDataState();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataStoreReference#getDataState <em>Data State</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data State</em>' containment reference.
	 * @see #getDataState()
	 * @generated
	 */
	void setDataState(TDataState value);

	/**
	 * Returns the value of the '<em><b>Data Store Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Store Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Store Ref</em>' attribute.
	 * @see #setDataStoreRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDataStoreReference_DataStoreRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='dataStoreRef'"
	 * @generated
	 */
	QName getDataStoreRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataStoreReference#getDataStoreRef <em>Data Store Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Store Ref</em>' attribute.
	 * @see #getDataStoreRef()
	 * @generated
	 */
	void setDataStoreRef(QName value);

	/**
	 * Returns the value of the '<em><b>Item Subject Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Item Subject Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Item Subject Ref</em>' attribute.
	 * @see #setItemSubjectRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTDataStoreReference_ItemSubjectRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='itemSubjectRef'"
	 * @generated
	 */
	QName getItemSubjectRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataStoreReference#getItemSubjectRef <em>Item Subject Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Item Subject Ref</em>' attribute.
	 * @see #getItemSubjectRef()
	 * @generated
	 */
	void setItemSubjectRef(QName value);

} // TDataStoreReference
