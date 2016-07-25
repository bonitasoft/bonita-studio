/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TGateway</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TGateway#getGatewayDirection <em>Gateway Direction</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTGateway()
 * @model extendedMetaData="name='tGateway' kind='elementOnly'"
 * @generated
 */
public interface TGateway extends TFlowNode {
	/**
	 * Returns the value of the '<em><b>Gateway Direction</b></em>' attribute.
	 * The default value is <code>"Unspecified"</code>.
	 * The literals are from the enumeration {@link org.omg.spec.bpmn.model.TGatewayDirection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gateway Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gateway Direction</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TGatewayDirection
	 * @see #isSetGatewayDirection()
	 * @see #unsetGatewayDirection()
	 * @see #setGatewayDirection(TGatewayDirection)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTGateway_GatewayDirection()
	 * @model default="Unspecified" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='gatewayDirection'"
	 * @generated
	 */
	TGatewayDirection getGatewayDirection();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TGateway#getGatewayDirection <em>Gateway Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gateway Direction</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TGatewayDirection
	 * @see #isSetGatewayDirection()
	 * @see #unsetGatewayDirection()
	 * @see #getGatewayDirection()
	 * @generated
	 */
	void setGatewayDirection(TGatewayDirection value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TGateway#getGatewayDirection <em>Gateway Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetGatewayDirection()
	 * @see #getGatewayDirection()
	 * @see #setGatewayDirection(TGatewayDirection)
	 * @generated
	 */
	void unsetGatewayDirection();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TGateway#getGatewayDirection <em>Gateway Direction</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Gateway Direction</em>' attribute is set.
	 * @see #unsetGatewayDirection()
	 * @see #getGatewayDirection()
	 * @see #setGatewayDirection(TGatewayDirection)
	 * @generated
	 */
	boolean isSetGatewayDirection();

} // TGateway
