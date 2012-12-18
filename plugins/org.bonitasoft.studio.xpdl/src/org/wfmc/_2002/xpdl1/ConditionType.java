/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Condition Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ConditionType#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ConditionType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ConditionType#getXpression <em>Xpression</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ConditionType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getConditionType()
 * @model extendedMetaData="name='Condition_._type' kind='mixed'"
 * @generated
 */
public interface ConditionType extends EObject {
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getConditionType_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getConditionType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='group' name='group:1'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Xpression</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.XpressionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xpression</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xpression</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getConditionType_Xpression()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='Xpression' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<XpressionType> getXpression();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.wfmc._2002.xpdl1.TypeType2}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.TypeType2
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(TypeType2)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getConditionType_Type()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='Type'"
	 * @generated
	 */
	TypeType2 getType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ConditionType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.TypeType2
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(TypeType2 value);

	/**
	 * Unsets the value of the '{@link org.wfmc._2002.xpdl1.ConditionType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(TypeType2)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link org.wfmc._2002.xpdl1.ConditionType#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(TypeType2)
	 * @generated
	 */
	boolean isSetType();

} // ConditionType
