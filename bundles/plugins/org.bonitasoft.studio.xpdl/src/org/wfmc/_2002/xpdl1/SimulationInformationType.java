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
 * A representation of the model object '<em><b>Simulation Information Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.SimulationInformationType#getCost <em>Cost</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.SimulationInformationType#getTimeEstimation <em>Time Estimation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.SimulationInformationType#getInstantiation <em>Instantiation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getSimulationInformationType()
 * @model extendedMetaData="name='SimulationInformation_._type' kind='elementOnly'"
 * @generated
 */
public interface SimulationInformationType extends EObject {
	/**
	 * Returns the value of the '<em><b>Cost</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cost</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cost</em>' attribute.
	 * @see #setCost(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getSimulationInformationType_Cost()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='Cost' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCost();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.SimulationInformationType#getCost <em>Cost</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost</em>' attribute.
	 * @see #getCost()
	 * @generated
	 */
	void setCost(String value);

	/**
	 * Returns the value of the '<em><b>Time Estimation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Estimation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Estimation</em>' containment reference.
	 * @see #setTimeEstimation(TimeEstimationType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getSimulationInformationType_TimeEstimation()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='TimeEstimation' namespace='##targetNamespace'"
	 * @generated
	 */
	TimeEstimationType getTimeEstimation();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.SimulationInformationType#getTimeEstimation <em>Time Estimation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Estimation</em>' containment reference.
	 * @see #getTimeEstimation()
	 * @generated
	 */
	void setTimeEstimation(TimeEstimationType value);

	/**
	 * Returns the value of the '<em><b>Instantiation</b></em>' attribute.
	 * The literals are from the enumeration {@link org.wfmc._2002.xpdl1.InstantiationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instantiation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instantiation</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.InstantiationType
	 * @see #isSetInstantiation()
	 * @see #unsetInstantiation()
	 * @see #setInstantiation(InstantiationType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getSimulationInformationType_Instantiation()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='Instantiation'"
	 * @generated
	 */
	InstantiationType getInstantiation();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.SimulationInformationType#getInstantiation <em>Instantiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instantiation</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.InstantiationType
	 * @see #isSetInstantiation()
	 * @see #unsetInstantiation()
	 * @see #getInstantiation()
	 * @generated
	 */
	void setInstantiation(InstantiationType value);

	/**
	 * Unsets the value of the '{@link org.wfmc._2002.xpdl1.SimulationInformationType#getInstantiation <em>Instantiation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetInstantiation()
	 * @see #getInstantiation()
	 * @see #setInstantiation(InstantiationType)
	 * @generated
	 */
	void unsetInstantiation();

	/**
	 * Returns whether the value of the '{@link org.wfmc._2002.xpdl1.SimulationInformationType#getInstantiation <em>Instantiation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Instantiation</em>' attribute is set.
	 * @see #unsetInstantiation()
	 * @see #getInstantiation()
	 * @see #setInstantiation(InstantiationType)
	 * @generated
	 */
	boolean isSetInstantiation();

} // SimulationInformationType
