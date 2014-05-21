/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sub Flow Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.SubFlowType#getActualParameters <em>Actual Parameters</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.SubFlowType#getExecution <em>Execution</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.SubFlowType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getSubFlowType()
 * @model extendedMetaData="name='SubFlow_._type' kind='elementOnly'"
 * @generated
 */
public interface SubFlowType extends EObject {
	/**
	 * Returns the value of the '<em><b>Actual Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actual Parameters</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actual Parameters</em>' containment reference.
	 * @see #setActualParameters(ActualParametersType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getSubFlowType_ActualParameters()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ActualParameters' namespace='##targetNamespace'"
	 * @generated
	 */
	ActualParametersType getActualParameters();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.SubFlowType#getActualParameters <em>Actual Parameters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Parameters</em>' containment reference.
	 * @see #getActualParameters()
	 * @generated
	 */
	void setActualParameters(ActualParametersType value);

	/**
	 * Returns the value of the '<em><b>Execution</b></em>' attribute.
	 * The literals are from the enumeration {@link org.wfmc._2002.xpdl1.ExecutionType1}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.ExecutionType1
	 * @see #isSetExecution()
	 * @see #unsetExecution()
	 * @see #setExecution(ExecutionType1)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getSubFlowType_Execution()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='Execution'"
	 * @generated
	 */
	ExecutionType1 getExecution();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.SubFlowType#getExecution <em>Execution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.ExecutionType1
	 * @see #isSetExecution()
	 * @see #unsetExecution()
	 * @see #getExecution()
	 * @generated
	 */
	void setExecution(ExecutionType1 value);

	/**
	 * Unsets the value of the '{@link org.wfmc._2002.xpdl1.SubFlowType#getExecution <em>Execution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetExecution()
	 * @see #getExecution()
	 * @see #setExecution(ExecutionType1)
	 * @generated
	 */
	void unsetExecution();

	/**
	 * Returns whether the value of the '{@link org.wfmc._2002.xpdl1.SubFlowType#getExecution <em>Execution</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Execution</em>' attribute is set.
	 * @see #unsetExecution()
	 * @see #getExecution()
	 * @see #setExecution(ExecutionType1)
	 * @generated
	 */
	boolean isSetExecution();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getSubFlowType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='Id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.SubFlowType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // SubFlowType
