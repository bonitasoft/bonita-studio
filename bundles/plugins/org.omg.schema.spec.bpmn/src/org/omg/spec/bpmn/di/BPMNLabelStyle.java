/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.di;

import org.omg.spec.dd.dc.Font;

import org.omg.spec.dd.di.Style;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Label Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNLabelStyle#getFont <em>Font</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.di.DiPackage#getBPMNLabelStyle()
 * @model extendedMetaData="name='BPMNLabelStyle' kind='elementOnly'"
 * @generated
 */
public interface BPMNLabelStyle extends Style {
	/**
	 * Returns the value of the '<em><b>Font</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Font</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Font</em>' containment reference.
	 * @see #setFont(Font)
	 * @see org.omg.spec.bpmn.di.DiPackage#getBPMNLabelStyle_Font()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='Font' namespace='http://www.omg.org/spec/DD/20100524/DC'"
	 * @generated
	 */
	Font getFont();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.di.BPMNLabelStyle#getFont <em>Font</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Font</em>' containment reference.
	 * @see #getFont()
	 * @generated
	 */
	void setFont(Font value);

} // BPMNLabelStyle
