/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implementation Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ImplementationType#getNo <em>No</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ImplementationType#getTool <em>Tool</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ImplementationType#getSubFlow <em>Sub Flow</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getImplementationType()
 * @model extendedMetaData="name='Implementation_._type' kind='elementOnly'"
 * @generated
 */
public interface ImplementationType extends EObject {
	/**
	 * Returns the value of the '<em><b>No</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>No</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>No</em>' containment reference.
	 * @see #setNo(NoType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getImplementationType_No()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='No' namespace='##targetNamespace'"
	 * @generated
	 */
	NoType getNo();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ImplementationType#getNo <em>No</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>No</em>' containment reference.
	 * @see #getNo()
	 * @generated
	 */
	void setNo(NoType value);

	/**
	 * Returns the value of the '<em><b>Tool</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.ToolType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tool</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tool</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getImplementationType_Tool()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Tool' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ToolType> getTool();

	/**
	 * Returns the value of the '<em><b>Sub Flow</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Flow</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Flow</em>' containment reference.
	 * @see #setSubFlow(SubFlowType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getImplementationType_SubFlow()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='SubFlow' namespace='##targetNamespace'"
	 * @generated
	 */
	SubFlowType getSubFlow();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ImplementationType#getSubFlow <em>Sub Flow</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Flow</em>' containment reference.
	 * @see #getSubFlow()
	 * @generated
	 */
	void setSubFlow(SubFlowType value);

} // ImplementationType
