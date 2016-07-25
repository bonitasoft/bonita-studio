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
 * A representation of the model object '<em><b>Package Header Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageHeaderType#getXPDLVersion <em>XPDL Version</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageHeaderType#getVendor <em>Vendor</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageHeaderType#getCreated <em>Created</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageHeaderType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageHeaderType#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageHeaderType#getPriorityUnit <em>Priority Unit</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.PackageHeaderType#getCostUnit <em>Cost Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageHeaderType()
 * @model extendedMetaData="name='PackageHeader_._type' kind='elementOnly'"
 * @generated
 */
public interface PackageHeaderType extends EObject {
	/**
	 * Returns the value of the '<em><b>XPDL Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XPDL Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XPDL Version</em>' attribute.
	 * @see #setXPDLVersion(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageHeaderType_XPDLVersion()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='XPDLVersion' namespace='##targetNamespace'"
	 * @generated
	 */
	String getXPDLVersion();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getXPDLVersion <em>XPDL Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XPDL Version</em>' attribute.
	 * @see #getXPDLVersion()
	 * @generated
	 */
	void setXPDLVersion(String value);

	/**
	 * Returns the value of the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vendor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vendor</em>' attribute.
	 * @see #setVendor(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageHeaderType_Vendor()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='Vendor' namespace='##targetNamespace'"
	 * @generated
	 */
	String getVendor();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getVendor <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vendor</em>' attribute.
	 * @see #getVendor()
	 * @generated
	 */
	void setVendor(String value);

	/**
	 * Returns the value of the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Created</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Created</em>' attribute.
	 * @see #setCreated(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageHeaderType_Created()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='Created' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCreated();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getCreated <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Created</em>' attribute.
	 * @see #getCreated()
	 * @generated
	 */
	void setCreated(String value);

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
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageHeaderType_Description()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Description' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' attribute.
	 * @see #setDocumentation(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageHeaderType_Documentation()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Documentation' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDocumentation();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getDocumentation <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' attribute.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(String value);

	/**
	 * Returns the value of the '<em><b>Priority Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority Unit</em>' attribute.
	 * @see #setPriorityUnit(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageHeaderType_PriorityUnit()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='PriorityUnit' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPriorityUnit();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getPriorityUnit <em>Priority Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority Unit</em>' attribute.
	 * @see #getPriorityUnit()
	 * @generated
	 */
	void setPriorityUnit(String value);

	/**
	 * Returns the value of the '<em><b>Cost Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cost Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cost Unit</em>' attribute.
	 * @see #setCostUnit(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPackageHeaderType_CostUnit()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='CostUnit' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCostUnit();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.PackageHeaderType#getCostUnit <em>Cost Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost Unit</em>' attribute.
	 * @see #getCostUnit()
	 * @generated
	 */
	void setCostUnit(String value);

} // PackageHeaderType
