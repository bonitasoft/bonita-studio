/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.ActionType#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ActionType#getAny <em>Any</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ActionType#getAcceptPropagatedEvents <em>Accept Propagated Events</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ActionType#getAsync <em>Async</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ActionType#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ActionType#getConfigType <em>Config Type</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ActionType#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ActionType#getName <em>Name</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ActionType#getRefName <em>Ref Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getActionType()
 * @model extendedMetaData="name='action_._type' kind='mixed'"
 * @generated
 */
public interface ActionType extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getActionType_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>Any</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Any</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Any</em>' attribute list.
	 * @see org.jbpm.jpdl32.jpdl32Package#getActionType_Any()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='elementWildcard' wildcards='##any' name=':1' processing='lax'"
	 * @generated
	 */
	FeatureMap getAny();

	/**
	 * Returns the value of the '<em><b>Accept Propagated Events</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * The literals are from the enumeration {@link org.jbpm.jpdl32.BooleanType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accept Propagated Events</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accept Propagated Events</em>' attribute.
	 * @see org.jbpm.jpdl32.BooleanType
	 * @see #isSetAcceptPropagatedEvents()
	 * @see #unsetAcceptPropagatedEvents()
	 * @see #setAcceptPropagatedEvents(BooleanType)
	 * @see org.jbpm.jpdl32.jpdl32Package#getActionType_AcceptPropagatedEvents()
	 * @model default="true" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='accept-propagated-events'"
	 * @generated
	 */
	BooleanType getAcceptPropagatedEvents();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ActionType#getAcceptPropagatedEvents <em>Accept Propagated Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accept Propagated Events</em>' attribute.
	 * @see org.jbpm.jpdl32.BooleanType
	 * @see #isSetAcceptPropagatedEvents()
	 * @see #unsetAcceptPropagatedEvents()
	 * @see #getAcceptPropagatedEvents()
	 * @generated
	 */
	void setAcceptPropagatedEvents(BooleanType value);

	/**
	 * Unsets the value of the '{@link org.jbpm.jpdl32.ActionType#getAcceptPropagatedEvents <em>Accept Propagated Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAcceptPropagatedEvents()
	 * @see #getAcceptPropagatedEvents()
	 * @see #setAcceptPropagatedEvents(BooleanType)
	 * @generated
	 */
	void unsetAcceptPropagatedEvents();

	/**
	 * Returns whether the value of the '{@link org.jbpm.jpdl32.ActionType#getAcceptPropagatedEvents <em>Accept Propagated Events</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Accept Propagated Events</em>' attribute is set.
	 * @see #unsetAcceptPropagatedEvents()
	 * @see #getAcceptPropagatedEvents()
	 * @see #setAcceptPropagatedEvents(BooleanType)
	 * @generated
	 */
	boolean isSetAcceptPropagatedEvents();

	/**
	 * Returns the value of the '<em><b>Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Async</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Async</em>' attribute.
	 * @see #setAsync(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getActionType_Async()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='async'"
	 * @generated
	 */
	String getAsync();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ActionType#getAsync <em>Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Async</em>' attribute.
	 * @see #getAsync()
	 * @generated
	 */
	void setAsync(String value);

	/**
	 * Returns the value of the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' attribute.
	 * @see #setClass(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getActionType_Class()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='class'"
	 * @generated
	 */
	String getClass_();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ActionType#getClass_ <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' attribute.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(String value);

	/**
	 * Returns the value of the '<em><b>Config Type</b></em>' attribute.
	 * The default value is <code>"field"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config Type</em>' attribute.
	 * @see #isSetConfigType()
	 * @see #unsetConfigType()
	 * @see #setConfigType(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getActionType_ConfigType()
	 * @model default="field" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='config-type'"
	 * @generated
	 */
	String getConfigType();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ActionType#getConfigType <em>Config Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config Type</em>' attribute.
	 * @see #isSetConfigType()
	 * @see #unsetConfigType()
	 * @see #getConfigType()
	 * @generated
	 */
	void setConfigType(String value);

	/**
	 * Unsets the value of the '{@link org.jbpm.jpdl32.ActionType#getConfigType <em>Config Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetConfigType()
	 * @see #getConfigType()
	 * @see #setConfigType(String)
	 * @generated
	 */
	void unsetConfigType();

	/**
	 * Returns whether the value of the '{@link org.jbpm.jpdl32.ActionType#getConfigType <em>Config Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Config Type</em>' attribute is set.
	 * @see #unsetConfigType()
	 * @see #getConfigType()
	 * @see #setConfigType(String)
	 * @generated
	 */
	boolean isSetConfigType();

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getActionType_Expression()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='expression'"
	 * @generated
	 */
	String getExpression();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ActionType#getExpression <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getActionType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ActionType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ref Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref Name</em>' attribute.
	 * @see #setRefName(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getActionType_RefName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='ref-name'"
	 * @generated
	 */
	String getRefName();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ActionType#getRefName <em>Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref Name</em>' attribute.
	 * @see #getRefName()
	 * @generated
	 */
	void setRefName(String value);

} // ActionType
