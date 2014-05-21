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
 * A representation of the model object '<em><b>Type Declaration Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getBasicType <em>Basic Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getDeclaredType <em>Declared Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getSchemaType <em>Schema Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getExternalReference <em>External Reference</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getRecordType <em>Record Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getUnionType <em>Union Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getEnumerationType <em>Enumeration Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getArrayType <em>Array Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getListType <em>List Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getId <em>Id</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType()
 * @model extendedMetaData="name='TypeDeclaration_._type' kind='elementOnly'"
 * @generated
 */
public interface TypeDeclarationType extends EObject {
	/**
	 * Returns the value of the '<em><b>Basic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Basic Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Basic Type</em>' containment reference.
	 * @see #setBasicType(BasicTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_BasicType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='BasicType' namespace='##targetNamespace'"
	 * @generated
	 */
	BasicTypeType getBasicType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getBasicType <em>Basic Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Basic Type</em>' containment reference.
	 * @see #getBasicType()
	 * @generated
	 */
	void setBasicType(BasicTypeType value);

	/**
	 * Returns the value of the '<em><b>Declared Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Type</em>' containment reference.
	 * @see #setDeclaredType(DeclaredTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_DeclaredType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='DeclaredType' namespace='##targetNamespace'"
	 * @generated
	 */
	DeclaredTypeType getDeclaredType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getDeclaredType <em>Declared Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Type</em>' containment reference.
	 * @see #getDeclaredType()
	 * @generated
	 */
	void setDeclaredType(DeclaredTypeType value);

	/**
	 * Returns the value of the '<em><b>Schema Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema Type</em>' containment reference.
	 * @see #setSchemaType(SchemaTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_SchemaType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='SchemaType' namespace='##targetNamespace'"
	 * @generated
	 */
	SchemaTypeType getSchemaType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getSchemaType <em>Schema Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema Type</em>' containment reference.
	 * @see #getSchemaType()
	 * @generated
	 */
	void setSchemaType(SchemaTypeType value);

	/**
	 * Returns the value of the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Reference</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Reference</em>' containment reference.
	 * @see #setExternalReference(ExternalReferenceType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_ExternalReference()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExternalReference' namespace='##targetNamespace'"
	 * @generated
	 */
	ExternalReferenceType getExternalReference();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getExternalReference <em>External Reference</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Reference</em>' containment reference.
	 * @see #getExternalReference()
	 * @generated
	 */
	void setExternalReference(ExternalReferenceType value);

	/**
	 * Returns the value of the '<em><b>Record Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Record Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Record Type</em>' containment reference.
	 * @see #setRecordType(RecordTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_RecordType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='RecordType' namespace='##targetNamespace'"
	 * @generated
	 */
	RecordTypeType getRecordType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getRecordType <em>Record Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Record Type</em>' containment reference.
	 * @see #getRecordType()
	 * @generated
	 */
	void setRecordType(RecordTypeType value);

	/**
	 * Returns the value of the '<em><b>Union Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Union Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Union Type</em>' containment reference.
	 * @see #setUnionType(UnionTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_UnionType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='UnionType' namespace='##targetNamespace'"
	 * @generated
	 */
	UnionTypeType getUnionType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getUnionType <em>Union Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Union Type</em>' containment reference.
	 * @see #getUnionType()
	 * @generated
	 */
	void setUnionType(UnionTypeType value);

	/**
	 * Returns the value of the '<em><b>Enumeration Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enumeration Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enumeration Type</em>' containment reference.
	 * @see #setEnumerationType(EnumerationTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_EnumerationType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='EnumerationType' namespace='##targetNamespace'"
	 * @generated
	 */
	EnumerationTypeType getEnumerationType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getEnumerationType <em>Enumeration Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enumeration Type</em>' containment reference.
	 * @see #getEnumerationType()
	 * @generated
	 */
	void setEnumerationType(EnumerationTypeType value);

	/**
	 * Returns the value of the '<em><b>Array Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Type</em>' containment reference.
	 * @see #setArrayType(ArrayTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_ArrayType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ArrayType' namespace='##targetNamespace'"
	 * @generated
	 */
	ArrayTypeType getArrayType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getArrayType <em>Array Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Type</em>' containment reference.
	 * @see #getArrayType()
	 * @generated
	 */
	void setArrayType(ArrayTypeType value);

	/**
	 * Returns the value of the '<em><b>List Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>List Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>List Type</em>' containment reference.
	 * @see #setListType(ListTypeType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_ListType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ListType' namespace='##targetNamespace'"
	 * @generated
	 */
	ListTypeType getListType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getListType <em>List Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>List Type</em>' containment reference.
	 * @see #getListType()
	 * @generated
	 */
	void setListType(ListTypeType value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_Description()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Description' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getDescription <em>Description</em>}' attribute.
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_ExtendedAttributes()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExtendedAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	ExtendedAttributesType getExtendedAttributes();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getExtendedAttributes <em>Extended Attributes</em>}' containment reference.
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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='Id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='Name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TypeDeclarationType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // TypeDeclarationType
