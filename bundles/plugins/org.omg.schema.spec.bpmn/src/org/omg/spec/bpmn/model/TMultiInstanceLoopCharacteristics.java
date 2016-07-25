/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TMulti Instance Loop Characteristics</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getLoopCardinality <em>Loop Cardinality</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getLoopDataInputRef <em>Loop Data Input Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getLoopDataOutputRef <em>Loop Data Output Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getInputDataItem <em>Input Data Item</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getOutputDataItem <em>Output Data Item</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getComplexBehaviorDefinition <em>Complex Behavior Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getCompletionCondition <em>Completion Condition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getBehavior <em>Behavior</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#isIsSequential <em>Is Sequential</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getNoneBehaviorEventRef <em>None Behavior Event Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getOneBehaviorEventRef <em>One Behavior Event Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTMultiInstanceLoopCharacteristics()
 * @model extendedMetaData="name='tMultiInstanceLoopCharacteristics' kind='elementOnly'"
 * @generated
 */
public interface TMultiInstanceLoopCharacteristics extends TLoopCharacteristics {
	/**
	 * Returns the value of the '<em><b>Loop Cardinality</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Cardinality</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Cardinality</em>' containment reference.
	 * @see #setLoopCardinality(TExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMultiInstanceLoopCharacteristics_LoopCardinality()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='loopCardinality' namespace='##targetNamespace'"
	 * @generated
	 */
	TExpression getLoopCardinality();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getLoopCardinality <em>Loop Cardinality</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Cardinality</em>' containment reference.
	 * @see #getLoopCardinality()
	 * @generated
	 */
	void setLoopCardinality(TExpression value);

	/**
	 * Returns the value of the '<em><b>Loop Data Input Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Data Input Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Data Input Ref</em>' attribute.
	 * @see #setLoopDataInputRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMultiInstanceLoopCharacteristics_LoopDataInputRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='element' name='loopDataInputRef' namespace='##targetNamespace'"
	 * @generated
	 */
	QName getLoopDataInputRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getLoopDataInputRef <em>Loop Data Input Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Data Input Ref</em>' attribute.
	 * @see #getLoopDataInputRef()
	 * @generated
	 */
	void setLoopDataInputRef(QName value);

	/**
	 * Returns the value of the '<em><b>Loop Data Output Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Data Output Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Data Output Ref</em>' attribute.
	 * @see #setLoopDataOutputRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMultiInstanceLoopCharacteristics_LoopDataOutputRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='element' name='loopDataOutputRef' namespace='##targetNamespace'"
	 * @generated
	 */
	QName getLoopDataOutputRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getLoopDataOutputRef <em>Loop Data Output Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Data Output Ref</em>' attribute.
	 * @see #getLoopDataOutputRef()
	 * @generated
	 */
	void setLoopDataOutputRef(QName value);

	/**
	 * Returns the value of the '<em><b>Input Data Item</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Data Item</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Data Item</em>' containment reference.
	 * @see #setInputDataItem(TDataInput)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMultiInstanceLoopCharacteristics_InputDataItem()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='inputDataItem' namespace='##targetNamespace'"
	 * @generated
	 */
	TDataInput getInputDataItem();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getInputDataItem <em>Input Data Item</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input Data Item</em>' containment reference.
	 * @see #getInputDataItem()
	 * @generated
	 */
	void setInputDataItem(TDataInput value);

	/**
	 * Returns the value of the '<em><b>Output Data Item</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Data Item</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Data Item</em>' containment reference.
	 * @see #setOutputDataItem(TDataOutput)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMultiInstanceLoopCharacteristics_OutputDataItem()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='outputDataItem' namespace='##targetNamespace'"
	 * @generated
	 */
	TDataOutput getOutputDataItem();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getOutputDataItem <em>Output Data Item</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output Data Item</em>' containment reference.
	 * @see #getOutputDataItem()
	 * @generated
	 */
	void setOutputDataItem(TDataOutput value);

	/**
	 * Returns the value of the '<em><b>Complex Behavior Definition</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Complex Behavior Definition</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Complex Behavior Definition</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMultiInstanceLoopCharacteristics_ComplexBehaviorDefinition()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='complexBehaviorDefinition' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TComplexBehaviorDefinition> getComplexBehaviorDefinition();

	/**
	 * Returns the value of the '<em><b>Completion Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Completion Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Completion Condition</em>' containment reference.
	 * @see #setCompletionCondition(TExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMultiInstanceLoopCharacteristics_CompletionCondition()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='completionCondition' namespace='##targetNamespace'"
	 * @generated
	 */
	TExpression getCompletionCondition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getCompletionCondition <em>Completion Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completion Condition</em>' containment reference.
	 * @see #getCompletionCondition()
	 * @generated
	 */
	void setCompletionCondition(TExpression value);

	/**
	 * Returns the value of the '<em><b>Behavior</b></em>' attribute.
	 * The default value is <code>"All"</code>.
	 * The literals are from the enumeration {@link org.omg.spec.bpmn.model.TMultiInstanceFlowCondition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behavior</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behavior</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceFlowCondition
	 * @see #isSetBehavior()
	 * @see #unsetBehavior()
	 * @see #setBehavior(TMultiInstanceFlowCondition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMultiInstanceLoopCharacteristics_Behavior()
	 * @model default="All" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='behavior'"
	 * @generated
	 */
	TMultiInstanceFlowCondition getBehavior();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getBehavior <em>Behavior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavior</em>' attribute.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceFlowCondition
	 * @see #isSetBehavior()
	 * @see #unsetBehavior()
	 * @see #getBehavior()
	 * @generated
	 */
	void setBehavior(TMultiInstanceFlowCondition value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getBehavior <em>Behavior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBehavior()
	 * @see #getBehavior()
	 * @see #setBehavior(TMultiInstanceFlowCondition)
	 * @generated
	 */
	void unsetBehavior();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getBehavior <em>Behavior</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Behavior</em>' attribute is set.
	 * @see #unsetBehavior()
	 * @see #getBehavior()
	 * @see #setBehavior(TMultiInstanceFlowCondition)
	 * @generated
	 */
	boolean isSetBehavior();

	/**
	 * Returns the value of the '<em><b>Is Sequential</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Sequential</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Sequential</em>' attribute.
	 * @see #isSetIsSequential()
	 * @see #unsetIsSequential()
	 * @see #setIsSequential(boolean)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMultiInstanceLoopCharacteristics_IsSequential()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isSequential'"
	 * @generated
	 */
	boolean isIsSequential();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#isIsSequential <em>Is Sequential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Sequential</em>' attribute.
	 * @see #isSetIsSequential()
	 * @see #unsetIsSequential()
	 * @see #isIsSequential()
	 * @generated
	 */
	void setIsSequential(boolean value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#isIsSequential <em>Is Sequential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsSequential()
	 * @see #isIsSequential()
	 * @see #setIsSequential(boolean)
	 * @generated
	 */
	void unsetIsSequential();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#isIsSequential <em>Is Sequential</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Sequential</em>' attribute is set.
	 * @see #unsetIsSequential()
	 * @see #isIsSequential()
	 * @see #setIsSequential(boolean)
	 * @generated
	 */
	boolean isSetIsSequential();

	/**
	 * Returns the value of the '<em><b>None Behavior Event Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>None Behavior Event Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>None Behavior Event Ref</em>' attribute.
	 * @see #setNoneBehaviorEventRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMultiInstanceLoopCharacteristics_NoneBehaviorEventRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='noneBehaviorEventRef'"
	 * @generated
	 */
	QName getNoneBehaviorEventRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getNoneBehaviorEventRef <em>None Behavior Event Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>None Behavior Event Ref</em>' attribute.
	 * @see #getNoneBehaviorEventRef()
	 * @generated
	 */
	void setNoneBehaviorEventRef(QName value);

	/**
	 * Returns the value of the '<em><b>One Behavior Event Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>One Behavior Event Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>One Behavior Event Ref</em>' attribute.
	 * @see #setOneBehaviorEventRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMultiInstanceLoopCharacteristics_OneBehaviorEventRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='oneBehaviorEventRef'"
	 * @generated
	 */
	QName getOneBehaviorEventRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics#getOneBehaviorEventRef <em>One Behavior Event Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>One Behavior Event Ref</em>' attribute.
	 * @see #getOneBehaviorEventRef()
	 * @generated
	 */
	void setOneBehaviorEventRef(QName value);

} // TMultiInstanceLoopCharacteristics
