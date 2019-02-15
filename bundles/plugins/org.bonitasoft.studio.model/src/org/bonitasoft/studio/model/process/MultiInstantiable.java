/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process;

import org.bonitasoft.studio.model.expression.Expression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Multi Instantiable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.MultiInstantiable#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MultiInstantiable#getTestBefore <em>Test Before</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MultiInstantiable#getLoopCondition <em>Loop Condition</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MultiInstantiable#getLoopMaximum <em>Loop Maximum</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MultiInstantiable#isUseCardinality <em>Use Cardinality</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MultiInstantiable#getCardinalityExpression <em>Cardinality Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MultiInstantiable#getCollectionDataToMultiInstantiate <em>Collection Data To Multi Instantiate</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MultiInstantiable#getIteratorExpression <em>Iterator Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MultiInstantiable#getOutputData <em>Output Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MultiInstantiable#getListDataContainingOutputResults <em>List Data Containing Output Results</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MultiInstantiable#getCompletionCondition <em>Completion Condition</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.MultiInstantiable#isStoreOutput <em>Store Output</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable()
 * @model abstract="true"
 * @generated
 */
public interface MultiInstantiable extends EObject {
	/**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The default value is <code>"NONE"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.process.MultiInstanceType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.MultiInstanceType
     * @see #setType(MultiInstanceType)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable_Type()
     * @model default="NONE" required="true"
     * @generated
     */
	MultiInstanceType getType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.MultiInstanceType
     * @see #getType()
     * @generated
     */
	void setType(MultiInstanceType value);

	/**
     * Returns the value of the '<em><b>Test Before</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Before</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Test Before</em>' attribute.
     * @see #setTestBefore(Boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable_TestBefore()
     * @model default="false"
     * @generated
     */
	Boolean getTestBefore();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getTestBefore <em>Test Before</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Test Before</em>' attribute.
     * @see #getTestBefore()
     * @generated
     */
	void setTestBefore(Boolean value);

	/**
     * Returns the value of the '<em><b>Loop Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Loop Condition</em>' containment reference.
     * @see #setLoopCondition(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable_LoopCondition()
     * @model containment="true"
     * @generated
     */
	Expression getLoopCondition();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getLoopCondition <em>Loop Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Loop Condition</em>' containment reference.
     * @see #getLoopCondition()
     * @generated
     */
	void setLoopCondition(Expression value);

	/**
     * Returns the value of the '<em><b>Loop Maximum</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Maximum</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Loop Maximum</em>' containment reference.
     * @see #setLoopMaximum(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable_LoopMaximum()
     * @model containment="true"
     * @generated
     */
	Expression getLoopMaximum();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getLoopMaximum <em>Loop Maximum</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Loop Maximum</em>' containment reference.
     * @see #getLoopMaximum()
     * @generated
     */
	void setLoopMaximum(Expression value);

	/**
     * Returns the value of the '<em><b>Use Cardinality</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Cardinality</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Use Cardinality</em>' attribute.
     * @see #setUseCardinality(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable_UseCardinality()
     * @model default="false"
     * @generated
     */
	boolean isUseCardinality();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MultiInstantiable#isUseCardinality <em>Use Cardinality</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Use Cardinality</em>' attribute.
     * @see #isUseCardinality()
     * @generated
     */
	void setUseCardinality(boolean value);

	/**
     * Returns the value of the '<em><b>Cardinality Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cardinality Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Cardinality Expression</em>' containment reference.
     * @see #setCardinalityExpression(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable_CardinalityExpression()
     * @model containment="true"
     * @generated
     */
	Expression getCardinalityExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getCardinalityExpression <em>Cardinality Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Cardinality Expression</em>' containment reference.
     * @see #getCardinalityExpression()
     * @generated
     */
	void setCardinalityExpression(Expression value);

	/**
     * Returns the value of the '<em><b>Collection Data To Multi Instantiate</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection Data To Multi Instantiate</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Collection Data To Multi Instantiate</em>' reference.
     * @see #setCollectionDataToMultiInstantiate(Data)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable_CollectionDataToMultiInstantiate()
     * @model
     * @generated
     */
	Data getCollectionDataToMultiInstantiate();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getCollectionDataToMultiInstantiate <em>Collection Data To Multi Instantiate</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Collection Data To Multi Instantiate</em>' reference.
     * @see #getCollectionDataToMultiInstantiate()
     * @generated
     */
	void setCollectionDataToMultiInstantiate(Data value);

	/**
     * Returns the value of the '<em><b>Iterator Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterator Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Iterator Expression</em>' containment reference.
     * @see #setIteratorExpression(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable_IteratorExpression()
     * @model containment="true"
     * @generated
     */
	Expression getIteratorExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getIteratorExpression <em>Iterator Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Iterator Expression</em>' containment reference.
     * @see #getIteratorExpression()
     * @generated
     */
	void setIteratorExpression(Expression value);

	/**
     * Returns the value of the '<em><b>Output Data</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Data</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Output Data</em>' reference.
     * @see #setOutputData(Data)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable_OutputData()
     * @model
     * @generated
     */
	Data getOutputData();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getOutputData <em>Output Data</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Data</em>' reference.
     * @see #getOutputData()
     * @generated
     */
	void setOutputData(Data value);

	/**
     * Returns the value of the '<em><b>List Data Containing Output Results</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>List Data Containing Output Results</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>List Data Containing Output Results</em>' reference.
     * @see #setListDataContainingOutputResults(Data)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable_ListDataContainingOutputResults()
     * @model
     * @generated
     */
	Data getListDataContainingOutputResults();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getListDataContainingOutputResults <em>List Data Containing Output Results</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>List Data Containing Output Results</em>' reference.
     * @see #getListDataContainingOutputResults()
     * @generated
     */
	void setListDataContainingOutputResults(Data value);

	/**
     * Returns the value of the '<em><b>Completion Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Completion Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Completion Condition</em>' containment reference.
     * @see #setCompletionCondition(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable_CompletionCondition()
     * @model containment="true"
     * @generated
     */
	Expression getCompletionCondition();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MultiInstantiable#getCompletionCondition <em>Completion Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Completion Condition</em>' containment reference.
     * @see #getCompletionCondition()
     * @generated
     */
	void setCompletionCondition(Expression value);

	/**
     * Returns the value of the '<em><b>Store Output</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Store Output</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Store Output</em>' attribute.
     * @see #setStoreOutput(boolean)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMultiInstantiable_StoreOutput()
     * @model default="false"
     * @generated
     */
	boolean isStoreOutput();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.MultiInstantiable#isStoreOutput <em>Store Output</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Store Output</em>' attribute.
     * @see #isStoreOutput()
     * @generated
     */
	void setStoreOutput(boolean value);

} // MultiInstantiable
