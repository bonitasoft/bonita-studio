/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import java.math.BigInteger;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TActivity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TActivity#getIoSpecification <em>Io Specification</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TActivity#getProperty <em>Property</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TActivity#getDataInputAssociation <em>Data Input Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TActivity#getDataOutputAssociation <em>Data Output Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TActivity#getResourceRoleGroup <em>Resource Role Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TActivity#getResourceRole <em>Resource Role</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TActivity#getLoopCharacteristicsGroup <em>Loop Characteristics Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TActivity#getLoopCharacteristics <em>Loop Characteristics</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TActivity#getCompletionQuantity <em>Completion Quantity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TActivity#getDefault <em>Default</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TActivity#isIsForCompensation <em>Is For Compensation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TActivity#getStartQuantity <em>Start Quantity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity()
 * @model abstract="true"
 *        extendedMetaData="name='tActivity' kind='elementOnly'"
 * @generated
 */
public interface TActivity extends TFlowNode {
	/**
	 * Returns the value of the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Io Specification</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Io Specification</em>' containment reference.
	 * @see #setIoSpecification(TInputOutputSpecification)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity_IoSpecification()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ioSpecification' namespace='##targetNamespace'"
	 * @generated
	 */
	TInputOutputSpecification getIoSpecification();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TActivity#getIoSpecification <em>Io Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Io Specification</em>' containment reference.
	 * @see #getIoSpecification()
	 * @generated
	 */
	void setIoSpecification(TInputOutputSpecification value);

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity_Property()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='property' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TProperty> getProperty();

	/**
	 * Returns the value of the '<em><b>Data Input Association</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TDataInputAssociation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Input Association</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Input Association</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity_DataInputAssociation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dataInputAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TDataInputAssociation> getDataInputAssociation();

	/**
	 * Returns the value of the '<em><b>Data Output Association</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TDataOutputAssociation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Output Association</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Output Association</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity_DataOutputAssociation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dataOutputAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TDataOutputAssociation> getDataOutputAssociation();

	/**
	 * Returns the value of the '<em><b>Resource Role Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Role Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Role Group</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity_ResourceRoleGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='resourceRole:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getResourceRoleGroup();

	/**
	 * Returns the value of the '<em><b>Resource Role</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TResourceRole}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Role</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Role</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity_ResourceRole()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='resourceRole' namespace='##targetNamespace' group='resourceRole:group'"
	 * @generated
	 */
	EList<TResourceRole> getResourceRole();

	/**
	 * Returns the value of the '<em><b>Loop Characteristics Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Characteristics Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Characteristics Group</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity_LoopCharacteristicsGroup()
	 * @model dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="false"
	 *        extendedMetaData="kind='group' name='loopCharacteristics:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getLoopCharacteristicsGroup();

	/**
	 * Returns the value of the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Characteristics</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Characteristics</em>' containment reference.
	 * @see #setLoopCharacteristics(TLoopCharacteristics)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity_LoopCharacteristics()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='loopCharacteristics' namespace='##targetNamespace' group='loopCharacteristics:group'"
	 * @generated
	 */
	TLoopCharacteristics getLoopCharacteristics();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TActivity#getLoopCharacteristics <em>Loop Characteristics</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Characteristics</em>' containment reference.
	 * @see #getLoopCharacteristics()
	 * @generated
	 */
	void setLoopCharacteristics(TLoopCharacteristics value);

	/**
	 * Returns the value of the '<em><b>Completion Quantity</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Completion Quantity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Completion Quantity</em>' attribute.
	 * @see #isSetCompletionQuantity()
	 * @see #unsetCompletionQuantity()
	 * @see #setCompletionQuantity(BigInteger)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity_CompletionQuantity()
	 * @model default="1" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='attribute' name='completionQuantity'"
	 * @generated
	 */
	BigInteger getCompletionQuantity();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TActivity#getCompletionQuantity <em>Completion Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completion Quantity</em>' attribute.
	 * @see #isSetCompletionQuantity()
	 * @see #unsetCompletionQuantity()
	 * @see #getCompletionQuantity()
	 * @generated
	 */
	void setCompletionQuantity(BigInteger value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TActivity#getCompletionQuantity <em>Completion Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCompletionQuantity()
	 * @see #getCompletionQuantity()
	 * @see #setCompletionQuantity(BigInteger)
	 * @generated
	 */
	void unsetCompletionQuantity();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TActivity#getCompletionQuantity <em>Completion Quantity</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Completion Quantity</em>' attribute is set.
	 * @see #unsetCompletionQuantity()
	 * @see #getCompletionQuantity()
	 * @see #setCompletionQuantity(BigInteger)
	 * @generated
	 */
	boolean isSetCompletionQuantity();

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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity_Default()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
	 *        extendedMetaData="kind='attribute' name='default'"
	 * @generated
	 */
	String getDefault();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TActivity#getDefault <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default</em>' attribute.
	 * @see #getDefault()
	 * @generated
	 */
	void setDefault(String value);

	/**
	 * Returns the value of the '<em><b>Is For Compensation</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is For Compensation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is For Compensation</em>' attribute.
	 * @see #isSetIsForCompensation()
	 * @see #unsetIsForCompensation()
	 * @see #setIsForCompensation(boolean)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity_IsForCompensation()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isForCompensation'"
	 * @generated
	 */
	boolean isIsForCompensation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TActivity#isIsForCompensation <em>Is For Compensation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is For Compensation</em>' attribute.
	 * @see #isSetIsForCompensation()
	 * @see #unsetIsForCompensation()
	 * @see #isIsForCompensation()
	 * @generated
	 */
	void setIsForCompensation(boolean value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TActivity#isIsForCompensation <em>Is For Compensation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsForCompensation()
	 * @see #isIsForCompensation()
	 * @see #setIsForCompensation(boolean)
	 * @generated
	 */
	void unsetIsForCompensation();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TActivity#isIsForCompensation <em>Is For Compensation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is For Compensation</em>' attribute is set.
	 * @see #unsetIsForCompensation()
	 * @see #isIsForCompensation()
	 * @see #setIsForCompensation(boolean)
	 * @generated
	 */
	boolean isSetIsForCompensation();

	/**
	 * Returns the value of the '<em><b>Start Quantity</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Quantity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Quantity</em>' attribute.
	 * @see #isSetStartQuantity()
	 * @see #unsetStartQuantity()
	 * @see #setStartQuantity(BigInteger)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTActivity_StartQuantity()
	 * @model default="1" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Integer"
	 *        extendedMetaData="kind='attribute' name='startQuantity'"
	 * @generated
	 */
	BigInteger getStartQuantity();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TActivity#getStartQuantity <em>Start Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Quantity</em>' attribute.
	 * @see #isSetStartQuantity()
	 * @see #unsetStartQuantity()
	 * @see #getStartQuantity()
	 * @generated
	 */
	void setStartQuantity(BigInteger value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TActivity#getStartQuantity <em>Start Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetStartQuantity()
	 * @see #getStartQuantity()
	 * @see #setStartQuantity(BigInteger)
	 * @generated
	 */
	void unsetStartQuantity();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TActivity#getStartQuantity <em>Start Quantity</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Start Quantity</em>' attribute is set.
	 * @see #unsetStartQuantity()
	 * @see #getStartQuantity()
	 * @see #setStartQuantity(BigInteger)
	 * @generated
	 */
	boolean isSetStartQuantity();

} // TActivity
