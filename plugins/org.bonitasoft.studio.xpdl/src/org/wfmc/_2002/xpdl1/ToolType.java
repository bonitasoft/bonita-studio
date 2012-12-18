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
 * A representation of the model object '<em><b>Tool Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ToolType#getActualParameters <em>Actual Parameters</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ToolType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ToolType#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ToolType#getId <em>Id</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.ToolType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getToolType()
 * @model extendedMetaData="name='Tool_._type' kind='elementOnly'"
 * @generated
 */
public interface ToolType extends EObject {
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getToolType_ActualParameters()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ActualParameters' namespace='##targetNamespace'"
	 * @generated
	 */
	ActualParametersType getActualParameters();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ToolType#getActualParameters <em>Actual Parameters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Parameters</em>' containment reference.
	 * @see #getActualParameters()
	 * @generated
	 */
	void setActualParameters(ActualParametersType value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getToolType_Description()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Description' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ToolType#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Extended Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Attributes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Attributes</em>' containment reference.
	 * @see #setExtendedAttributes(ExtendedAttributesType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getToolType_ExtendedAttributes()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExtendedAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	ExtendedAttributesType getExtendedAttributes();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ToolType#getExtendedAttributes <em>Extended Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Attributes</em>' containment reference.
	 * @see #getExtendedAttributes()
	 * @generated
	 */
	void setExtendedAttributes(ExtendedAttributesType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getToolType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='Id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ToolType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.wfmc._2002.xpdl1.TypeType5}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.TypeType5
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(TypeType5)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getToolType_Type()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='Type'"
	 * @generated
	 */
	TypeType5 getType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.ToolType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.TypeType5
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(TypeType5 value);

	/**
	 * Unsets the value of the '{@link org.wfmc._2002.xpdl1.ToolType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(TypeType5)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link org.wfmc._2002.xpdl1.ToolType#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(TypeType5)
	 * @generated
	 */
	boolean isSetType();

} // ToolType
