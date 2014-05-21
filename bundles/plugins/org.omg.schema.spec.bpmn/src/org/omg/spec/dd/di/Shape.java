/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.dd.di;

import org.omg.spec.dd.dc.Bounds;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Shape</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.dd.di.Shape#getBounds <em>Bounds</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.dd.di.DiPackage#getShape()
 * @model abstract="true"
 *        extendedMetaData="name='Shape' kind='elementOnly'"
 * @generated
 */
public interface Shape extends Node {
	/**
	 * Returns the value of the '<em><b>Bounds</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bounds</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bounds</em>' containment reference.
	 * @see #setBounds(Bounds)
	 * @see org.omg.spec.dd.di.DiPackage#getShape_Bounds()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='Bounds' namespace='http://www.omg.org/spec/DD/20100524/DC'"
	 * @generated
	 */
	Bounds getBounds();

	/**
	 * Sets the value of the '{@link org.omg.spec.dd.di.Shape#getBounds <em>Bounds</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bounds</em>' containment reference.
	 * @see #getBounds()
	 * @generated
	 */
	void setBounds(Bounds value);

} // Shape
