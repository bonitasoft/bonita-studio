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
 * A representation of the model object '<em><b>Redefinable Header Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getVersion <em>Version</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getCodepage <em>Codepage</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getCountrykey <em>Countrykey</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getResponsibles <em>Responsibles</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getPublicationStatus <em>Publication Status</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getRedefinableHeaderType()
 * @model extendedMetaData="name='RedefinableHeader_._type' kind='elementOnly'"
 * @generated
 */
public interface RedefinableHeaderType extends EObject {
	/**
	 * Returns the value of the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Author</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Author</em>' attribute.
	 * @see #setAuthor(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getRedefinableHeaderType_Author()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Author' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAuthor();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getAuthor <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Author</em>' attribute.
	 * @see #getAuthor()
	 * @generated
	 */
	void setAuthor(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getRedefinableHeaderType_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Version' namespace='##targetNamespace'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Codepage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Codepage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Codepage</em>' attribute.
	 * @see #setCodepage(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getRedefinableHeaderType_Codepage()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Codepage' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCodepage();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getCodepage <em>Codepage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Codepage</em>' attribute.
	 * @see #getCodepage()
	 * @generated
	 */
	void setCodepage(String value);

	/**
	 * Returns the value of the '<em><b>Countrykey</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Countrykey</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Countrykey</em>' attribute.
	 * @see #setCountrykey(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getRedefinableHeaderType_Countrykey()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Countrykey' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCountrykey();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getCountrykey <em>Countrykey</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Countrykey</em>' attribute.
	 * @see #getCountrykey()
	 * @generated
	 */
	void setCountrykey(String value);

	/**
	 * Returns the value of the '<em><b>Responsibles</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responsibles</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsibles</em>' containment reference.
	 * @see #setResponsibles(ResponsiblesType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getRedefinableHeaderType_Responsibles()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Responsibles' namespace='##targetNamespace'"
	 * @generated
	 */
	ResponsiblesType getResponsibles();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getResponsibles <em>Responsibles</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Responsibles</em>' containment reference.
	 * @see #getResponsibles()
	 * @generated
	 */
	void setResponsibles(ResponsiblesType value);

	/**
	 * Returns the value of the '<em><b>Publication Status</b></em>' attribute.
	 * The literals are from the enumeration {@link org.wfmc._2002.xpdl1.PublicationStatusType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Publication Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Publication Status</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.PublicationStatusType
	 * @see #isSetPublicationStatus()
	 * @see #unsetPublicationStatus()
	 * @see #setPublicationStatus(PublicationStatusType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getRedefinableHeaderType_PublicationStatus()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='PublicationStatus'"
	 * @generated
	 */
	PublicationStatusType getPublicationStatus();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getPublicationStatus <em>Publication Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Publication Status</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.PublicationStatusType
	 * @see #isSetPublicationStatus()
	 * @see #unsetPublicationStatus()
	 * @see #getPublicationStatus()
	 * @generated
	 */
	void setPublicationStatus(PublicationStatusType value);

	/**
	 * Unsets the value of the '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getPublicationStatus <em>Publication Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPublicationStatus()
	 * @see #getPublicationStatus()
	 * @see #setPublicationStatus(PublicationStatusType)
	 * @generated
	 */
	void unsetPublicationStatus();

	/**
	 * Returns whether the value of the '{@link org.wfmc._2002.xpdl1.RedefinableHeaderType#getPublicationStatus <em>Publication Status</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Publication Status</em>' attribute is set.
	 * @see #unsetPublicationStatus()
	 * @see #getPublicationStatus()
	 * @see #setPublicationStatus(PublicationStatusType)
	 * @generated
	 */
	boolean isSetPublicationStatus();

} // RedefinableHeaderType
