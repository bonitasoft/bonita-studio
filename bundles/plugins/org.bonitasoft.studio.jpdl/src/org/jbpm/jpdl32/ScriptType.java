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
 * A representation of the model object '<em><b>Script Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.ScriptType#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ScriptType#getAny <em>Any</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ScriptType#getAcceptPropagatedEvents <em>Accept Propagated Events</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.ScriptType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getScriptType()
 * @model extendedMetaData="name='script_._type' kind='mixed'"
 * @generated
 */
public interface ScriptType extends EObject {
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getScriptType_Mixed()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getScriptType_Any()
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getScriptType_AcceptPropagatedEvents()
	 * @model default="true" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='accept-propagated-events'"
	 * @generated
	 */
	BooleanType getAcceptPropagatedEvents();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ScriptType#getAcceptPropagatedEvents <em>Accept Propagated Events</em>}' attribute.
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
	 * Unsets the value of the '{@link org.jbpm.jpdl32.ScriptType#getAcceptPropagatedEvents <em>Accept Propagated Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAcceptPropagatedEvents()
	 * @see #getAcceptPropagatedEvents()
	 * @see #setAcceptPropagatedEvents(BooleanType)
	 * @generated
	 */
	void unsetAcceptPropagatedEvents();

	/**
	 * Returns whether the value of the '{@link org.jbpm.jpdl32.ScriptType#getAcceptPropagatedEvents <em>Accept Propagated Events</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.jbpm.jpdl32.jpdl32Package#getScriptType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.ScriptType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ScriptType
