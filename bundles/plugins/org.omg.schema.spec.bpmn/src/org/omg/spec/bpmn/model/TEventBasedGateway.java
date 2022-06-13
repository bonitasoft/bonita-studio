/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TEvent Based Gateway</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TEventBasedGateway#getEventGatewayType <em>Event Gateway Type</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TEventBasedGateway#isInstantiate <em>Instantiate</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTEventBasedGateway()
 * @model extendedMetaData="name='tEventBasedGateway' kind='elementOnly'"
 * @generated
 */
public interface TEventBasedGateway extends TGateway {
	/**
	 * Returns the value of the '<em><b>Event Gateway Type</b></em>' attribute.
	 * The default value is <code>"Exclusive"</code>.
	 * The literals are from the enumeration {@link org.omg.spec.bpmn.model.TEventBasedGatewayType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Gateway Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Gateway Type</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TEventBasedGatewayType
	 * @see #isSetEventGatewayType()
	 * @see #unsetEventGatewayType()
	 * @see #setEventGatewayType(TEventBasedGatewayType)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTEventBasedGateway_EventGatewayType()
	 * @model default="Exclusive" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='eventGatewayType'"
	 * @generated
	 */
	TEventBasedGatewayType getEventGatewayType();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TEventBasedGateway#getEventGatewayType <em>Event Gateway Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Gateway Type</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TEventBasedGatewayType
	 * @see #isSetEventGatewayType()
	 * @see #unsetEventGatewayType()
	 * @see #getEventGatewayType()
	 * @generated
	 */
	void setEventGatewayType(TEventBasedGatewayType value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TEventBasedGateway#getEventGatewayType <em>Event Gateway Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEventGatewayType()
	 * @see #getEventGatewayType()
	 * @see #setEventGatewayType(TEventBasedGatewayType)
	 * @generated
	 */
	void unsetEventGatewayType();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TEventBasedGateway#getEventGatewayType <em>Event Gateway Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Event Gateway Type</em>' attribute is set.
	 * @see #unsetEventGatewayType()
	 * @see #getEventGatewayType()
	 * @see #setEventGatewayType(TEventBasedGatewayType)
	 * @generated
	 */
	boolean isSetEventGatewayType();

	/**
	 * Returns the value of the '<em><b>Instantiate</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instantiate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instantiate</em>' attribute.
	 * @see #isSetInstantiate()
	 * @see #unsetInstantiate()
	 * @see #setInstantiate(boolean)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTEventBasedGateway_Instantiate()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='instantiate'"
	 * @generated
	 */
	boolean isInstantiate();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TEventBasedGateway#isInstantiate <em>Instantiate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instantiate</em>' attribute.
	 * @see #isSetInstantiate()
	 * @see #unsetInstantiate()
	 * @see #isInstantiate()
	 * @generated
	 */
	void setInstantiate(boolean value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TEventBasedGateway#isInstantiate <em>Instantiate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetInstantiate()
	 * @see #isInstantiate()
	 * @see #setInstantiate(boolean)
	 * @generated
	 */
	void unsetInstantiate();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TEventBasedGateway#isInstantiate <em>Instantiate</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Instantiate</em>' attribute is set.
	 * @see #unsetInstantiate()
	 * @see #isInstantiate()
	 * @see #setInstantiate(boolean)
	 * @generated
	 */
	boolean isSetInstantiate();

} // TEventBasedGateway
