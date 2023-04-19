/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.dd.di;

import org.eclipse.emf.common.util.EList;

import org.omg.spec.dd.dc.Point;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.dd.di.Edge#getWaypoint <em>Waypoint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.dd.di.DiPackage#getEdge()
 * @model abstract="true"
 *        extendedMetaData="name='Edge' kind='elementOnly'"
 * @generated
 */
public interface Edge extends DiagramElement {
	/**
	 * Returns the value of the '<em><b>Waypoint</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.dd.dc.Point}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Waypoint</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Waypoint</em>' containment reference list.
	 * @see org.omg.spec.dd.di.DiPackage#getEdge_Waypoint()
	 * @model containment="true" lower="2"
	 *        extendedMetaData="kind='element' name='waypoint' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Point> getWaypoint();

} // Edge
