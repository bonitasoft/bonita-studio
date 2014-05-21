/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.di;

import javax.xml.namespace.QName;

import org.omg.spec.dd.di.Label;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNLabel#getLabelStyle <em>Label Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.di.DiPackage#getBPMNLabel()
 * @model extendedMetaData="name='BPMNLabel' kind='elementOnly'"
 * @generated
 */
public interface BPMNLabel extends Label {
	/**
	 * Returns the value of the '<em><b>Label Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Style</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Style</em>' attribute.
	 * @see #setLabelStyle(QName)
	 * @see org.omg.spec.bpmn.di.DiPackage#getBPMNLabel_LabelStyle()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='labelStyle'"
	 * @generated
	 */
	QName getLabelStyle();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.di.BPMNLabel#getLabelStyle <em>Label Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Style</em>' attribute.
	 * @see #getLabelStyle()
	 * @generated
	 */
	void setLabelStyle(QName value);

} // BPMNLabel
