/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TComplex Gateway</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TComplexGateway#getActivationCondition <em>Activation Condition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TComplexGateway#getDefault <em>Default</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTComplexGateway()
 * @model extendedMetaData="name='tComplexGateway' kind='elementOnly'"
 * @generated
 */
public interface TComplexGateway extends TGateway {
	/**
	 * Returns the value of the '<em><b>Activation Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activation Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activation Condition</em>' containment reference.
	 * @see #setActivationCondition(TExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTComplexGateway_ActivationCondition()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='activationCondition' namespace='##targetNamespace'"
	 * @generated
	 */
	TExpression getActivationCondition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TComplexGateway#getActivationCondition <em>Activation Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activation Condition</em>' containment reference.
	 * @see #getActivationCondition()
	 * @generated
	 */
	void setActivationCondition(TExpression value);

	/**
	 * Returns the value of the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default</em>' attribute.
	 * @see #setDefault(String)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTComplexGateway_Default()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
	 *        extendedMetaData="kind='attribute' name='default'"
	 * @generated
	 */
	String getDefault();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TComplexGateway#getDefault <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default</em>' attribute.
	 * @see #getDefault()
	 * @generated
	 */
	void setDefault(String value);

} // TComplexGateway
