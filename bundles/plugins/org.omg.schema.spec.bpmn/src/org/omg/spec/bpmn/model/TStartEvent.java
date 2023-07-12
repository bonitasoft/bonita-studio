/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TStart Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TStartEvent#isIsInterrupting <em>Is Interrupting</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTStartEvent()
 * @model extendedMetaData="name='tStartEvent' kind='elementOnly'"
 * @generated
 */
public interface TStartEvent extends TCatchEvent {
	/**
	 * Returns the value of the '<em><b>Is Interrupting</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Interrupting</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Interrupting</em>' attribute.
	 * @see #isSetIsInterrupting()
	 * @see #unsetIsInterrupting()
	 * @see #setIsInterrupting(boolean)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTStartEvent_IsInterrupting()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isInterrupting'"
	 * @generated
	 */
	boolean isIsInterrupting();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TStartEvent#isIsInterrupting <em>Is Interrupting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Interrupting</em>' attribute.
	 * @see #isSetIsInterrupting()
	 * @see #unsetIsInterrupting()
	 * @see #isIsInterrupting()
	 * @generated
	 */
	void setIsInterrupting(boolean value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TStartEvent#isIsInterrupting <em>Is Interrupting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsInterrupting()
	 * @see #isIsInterrupting()
	 * @see #setIsInterrupting(boolean)
	 * @generated
	 */
	void unsetIsInterrupting();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TStartEvent#isIsInterrupting <em>Is Interrupting</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Interrupting</em>' attribute is set.
	 * @see #unsetIsInterrupting()
	 * @see #isIsInterrupting()
	 * @see #setIsInterrupting(boolean)
	 * @generated
	 */
	boolean isSetIsInterrupting();

} // TStartEvent
