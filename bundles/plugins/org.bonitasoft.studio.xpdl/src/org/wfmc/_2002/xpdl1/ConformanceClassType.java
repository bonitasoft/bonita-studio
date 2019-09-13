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
 * A representation of the model object '<em><b>Conformance Class Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ConformanceClassType#getGraphConformance <em>Graph Conformance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getConformanceClassType()
 * @model extendedMetaData="name='ConformanceClass_._type' kind='empty'"
 * @generated
 */
public interface ConformanceClassType extends EObject {
	/**
	 * Returns the value of the '<em><b>Graph Conformance</b></em>' attribute.
	 * The literals are from the enumeration {@link org.wfmc._2002.xpdl1.GraphConformanceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Graph Conformance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Graph Conformance</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.GraphConformanceType
	 * @see #isSetGraphConformance()
	 * @see #unsetGraphConformance()
	 * @see #setGraphConformance(GraphConformanceType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getConformanceClassType_GraphConformance()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='GraphConformance'"
	 * @generated
	 */
	GraphConformanceType getGraphConformance();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ConformanceClassType#getGraphConformance <em>Graph Conformance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Graph Conformance</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.GraphConformanceType
	 * @see #isSetGraphConformance()
	 * @see #unsetGraphConformance()
	 * @see #getGraphConformance()
	 * @generated
	 */
	void setGraphConformance(GraphConformanceType value);

	/**
	 * Unsets the value of the '{@link org.wfmc._2002.xpdl1.ConformanceClassType#getGraphConformance <em>Graph Conformance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetGraphConformance()
	 * @see #getGraphConformance()
	 * @see #setGraphConformance(GraphConformanceType)
	 * @generated
	 */
	void unsetGraphConformance();

	/**
	 * Returns whether the value of the '{@link org.wfmc._2002.xpdl1.ConformanceClassType#getGraphConformance <em>Graph Conformance</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Graph Conformance</em>' attribute is set.
	 * @see #unsetGraphConformance()
	 * @see #getGraphConformance()
	 * @see #setGraphConformance(GraphConformanceType)
	 * @generated
	 */
	boolean isSetGraphConformance();

} // ConformanceClassType
