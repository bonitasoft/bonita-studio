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
 * A representation of the model object '<em><b>TCorrelation Subscription</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationSubscription#getCorrelationPropertyBinding <em>Correlation Property Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationSubscription#getCorrelationKeyRef <em>Correlation Key Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationSubscription()
 * @model extendedMetaData="name='tCorrelationSubscription' kind='elementOnly'"
 * @generated
 */
public interface TCorrelationSubscription extends TBaseElement {
	/**
	 * Returns the value of the '<em><b>Correlation Property Binding</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Property Binding</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlation Property Binding</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationSubscription_CorrelationPropertyBinding()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='correlationPropertyBinding' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TCorrelationPropertyBinding> getCorrelationPropertyBinding();

	/**
	 * Returns the value of the '<em><b>Correlation Key Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Key Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlation Key Ref</em>' attribute.
	 * @see #setCorrelationKeyRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationSubscription_CorrelationKeyRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
	 *        extendedMetaData="kind='attribute' name='correlationKeyRef'"
	 * @generated
	 */
	QName getCorrelationKeyRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TCorrelationSubscription#getCorrelationKeyRef <em>Correlation Key Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Correlation Key Ref</em>' attribute.
	 * @see #getCorrelationKeyRef()
	 * @generated
	 */
	void setCorrelationKeyRef(QName value);

} // TCorrelationSubscription
