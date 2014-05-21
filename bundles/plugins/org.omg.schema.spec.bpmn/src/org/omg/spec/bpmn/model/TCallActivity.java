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
 * A representation of the model object '<em><b>TCall Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCallActivity#getCalledElement <em>Called Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCallActivity()
 * @model extendedMetaData="name='tCallActivity' kind='elementOnly'"
 * @generated
 */
public interface TCallActivity extends TActivity {
	/**
	 * Returns the value of the '<em><b>Called Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Element</em>' attribute.
	 * @see #setCalledElement(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCallActivity_CalledElement()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='calledElement'"
	 * @generated
	 */
	QName getCalledElement();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TCallActivity#getCalledElement <em>Called Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Called Element</em>' attribute.
	 * @see #getCalledElement()
	 * @generated
	 */
	void setCalledElement(QName value);

} // TCallActivity
