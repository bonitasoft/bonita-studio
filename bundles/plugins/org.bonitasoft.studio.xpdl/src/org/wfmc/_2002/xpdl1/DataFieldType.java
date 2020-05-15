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
 * A representation of the model object '<em><b>Data Field Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.DataFieldType#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DataFieldType#getInitialValue <em>Initial Value</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DataFieldType#getLength <em>Length</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DataFieldType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DataFieldType#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DataFieldType#getId <em>Id</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DataFieldType#getIsArray <em>Is Array</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.DataFieldType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDataFieldType()
 * @model extendedMetaData="name='DataField_._type' kind='elementOnly'"
 * @generated
 */
public interface DataFieldType extends EObject {
	/**
	 * Returns the value of the '<em><b>Data Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Type</em>' containment reference.
	 * @see #setDataType(DataTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDataFieldType_DataType()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='DataType' namespace='##targetNamespace'"
	 * @generated
	 */
	DataTypeType getDataType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DataFieldType#getDataType <em>Data Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' containment reference.
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(DataTypeType value);

	/**
	 * Returns the value of the '<em><b>Initial Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Value</em>' attribute.
	 * @see #setInitialValue(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDataFieldType_InitialValue()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='InitialValue' namespace='##targetNamespace'"
	 * @generated
	 */
	String getInitialValue();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DataFieldType#getInitialValue <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Value</em>' attribute.
	 * @see #getInitialValue()
	 * @generated
	 */
	void setInitialValue(String value);

	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDataFieldType_Length()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Length' namespace='##targetNamespace'"
	 * @generated
	 */
	String getLength();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DataFieldType#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(String value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDataFieldType_Description()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Description' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DataFieldType#getDescription <em>Description</em>}' attribute.
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDataFieldType_ExtendedAttributes()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExtendedAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	ExtendedAttributesType getExtendedAttributes();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DataFieldType#getExtendedAttributes <em>Extended Attributes</em>}' containment reference.
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDataFieldType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='Id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DataFieldType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Is Array</b></em>' attribute.
	 * The default value is <code>"FALSE"</code>.
	 * The literals are from the enumeration {@link org.wfmc._2002.xpdl1.IsArrayType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Array</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Array</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.IsArrayType
	 * @see #isSetIsArray()
	 * @see #unsetIsArray()
	 * @see #setIsArray(IsArrayType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDataFieldType_IsArray()
	 * @model default="FALSE" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='IsArray'"
	 * @generated
	 */
	IsArrayType getIsArray();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DataFieldType#getIsArray <em>Is Array</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Array</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.IsArrayType
	 * @see #isSetIsArray()
	 * @see #unsetIsArray()
	 * @see #getIsArray()
	 * @generated
	 */
	void setIsArray(IsArrayType value);

	/**
	 * Unsets the value of the '{@link org.wfmc._2002.xpdl1.DataFieldType#getIsArray <em>Is Array</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsArray()
	 * @see #getIsArray()
	 * @see #setIsArray(IsArrayType)
	 * @generated
	 */
	void unsetIsArray();

	/**
	 * Returns whether the value of the '{@link org.wfmc._2002.xpdl1.DataFieldType#getIsArray <em>Is Array</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Array</em>' attribute is set.
	 * @see #unsetIsArray()
	 * @see #getIsArray()
	 * @see #setIsArray(IsArrayType)
	 * @generated
	 */
	boolean isSetIsArray();

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDataFieldType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='Name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.DataFieldType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // DataFieldType
