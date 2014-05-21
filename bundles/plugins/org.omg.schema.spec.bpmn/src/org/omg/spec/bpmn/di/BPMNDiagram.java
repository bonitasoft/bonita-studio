/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.di;

import org.eclipse.emf.common.util.EList;

import org.omg.spec.dd.di.Diagram;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNDiagram#getBPMNPlane <em>BPMN Plane</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNDiagram#getBPMNLabelStyle <em>BPMN Label Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.di.DiPackage#getBPMNDiagram()
 * @model extendedMetaData="name='BPMNDiagram' kind='elementOnly'"
 * @generated
 */
public interface BPMNDiagram extends Diagram {
	/**
	 * Returns the value of the '<em><b>BPMN Plane</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>BPMN Plane</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>BPMN Plane</em>' containment reference.
	 * @see #setBPMNPlane(BPMNPlane)
	 * @see org.omg.spec.bpmn.di.DiPackage#getBPMNDiagram_BPMNPlane()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='BPMNPlane' namespace='##targetNamespace'"
	 * @generated
	 */
	BPMNPlane getBPMNPlane();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.di.BPMNDiagram#getBPMNPlane <em>BPMN Plane</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>BPMN Plane</em>' containment reference.
	 * @see #getBPMNPlane()
	 * @generated
	 */
	void setBPMNPlane(BPMNPlane value);

	/**
	 * Returns the value of the '<em><b>BPMN Label Style</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.di.BPMNLabelStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>BPMN Label Style</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>BPMN Label Style</em>' containment reference list.
	 * @see org.omg.spec.bpmn.di.DiPackage#getBPMNDiagram_BPMNLabelStyle()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='BPMNLabelStyle' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<BPMNLabelStyle> getBPMNLabelStyle();

} // BPMNDiagram
