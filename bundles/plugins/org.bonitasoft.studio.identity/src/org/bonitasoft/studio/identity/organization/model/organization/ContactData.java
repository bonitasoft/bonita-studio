/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.organization.model.organization;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contact Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.ContactData#getEmail <em>Email</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.ContactData#getPhoneNumber <em>Phone Number</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.ContactData#getMobileNumber <em>Mobile Number</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.ContactData#getFaxNumber <em>Fax Number</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.ContactData#getBuilding <em>Building</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.ContactData#getRoom <em>Room</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.ContactData#getAddress <em>Address</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.ContactData#getZipCode <em>Zip Code</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.ContactData#getCity <em>City</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.ContactData#getState <em>State</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.ContactData#getCountry <em>Country</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.ContactData#getWebsite <em>Website</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData()
 * @model extendedMetaData="name='ContactData' kind='elementOnly'"
 * @generated
 */
public interface ContactData extends EObject {
	/**
     * Returns the value of the '<em><b>Email</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Email</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Email</em>' attribute.
     * @see #setEmail(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData_Email()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='email'"
     * @generated
     */
	String getEmail();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.ContactData#getEmail <em>Email</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Email</em>' attribute.
     * @see #getEmail()
     * @generated
     */
	void setEmail(String value);

	/**
     * Returns the value of the '<em><b>Phone Number</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Phone Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Phone Number</em>' attribute.
     * @see #setPhoneNumber(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData_PhoneNumber()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='phoneNumber'"
     * @generated
     */
	String getPhoneNumber();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.ContactData#getPhoneNumber <em>Phone Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Phone Number</em>' attribute.
     * @see #getPhoneNumber()
     * @generated
     */
	void setPhoneNumber(String value);

	/**
     * Returns the value of the '<em><b>Mobile Number</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mobile Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Mobile Number</em>' attribute.
     * @see #setMobileNumber(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData_MobileNumber()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='mobileNumber'"
     * @generated
     */
	String getMobileNumber();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.ContactData#getMobileNumber <em>Mobile Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mobile Number</em>' attribute.
     * @see #getMobileNumber()
     * @generated
     */
	void setMobileNumber(String value);

	/**
     * Returns the value of the '<em><b>Fax Number</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fax Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Fax Number</em>' attribute.
     * @see #setFaxNumber(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData_FaxNumber()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='faxNumber'"
     * @generated
     */
	String getFaxNumber();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.ContactData#getFaxNumber <em>Fax Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fax Number</em>' attribute.
     * @see #getFaxNumber()
     * @generated
     */
	void setFaxNumber(String value);

	/**
     * Returns the value of the '<em><b>Building</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Building</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Building</em>' attribute.
     * @see #setBuilding(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData_Building()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='building'"
     * @generated
     */
	String getBuilding();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.ContactData#getBuilding <em>Building</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Building</em>' attribute.
     * @see #getBuilding()
     * @generated
     */
	void setBuilding(String value);

	/**
     * Returns the value of the '<em><b>Room</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Room</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Room</em>' attribute.
     * @see #setRoom(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData_Room()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='room'"
     * @generated
     */
	String getRoom();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.ContactData#getRoom <em>Room</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Room</em>' attribute.
     * @see #getRoom()
     * @generated
     */
	void setRoom(String value);

	/**
     * Returns the value of the '<em><b>Address</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Address</em>' attribute.
     * @see #setAddress(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData_Address()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='address'"
     * @generated
     */
	String getAddress();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.ContactData#getAddress <em>Address</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Address</em>' attribute.
     * @see #getAddress()
     * @generated
     */
	void setAddress(String value);

	/**
     * Returns the value of the '<em><b>Zip Code</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Zip Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Zip Code</em>' attribute.
     * @see #setZipCode(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData_ZipCode()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='zipCode'"
     * @generated
     */
	String getZipCode();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.ContactData#getZipCode <em>Zip Code</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Zip Code</em>' attribute.
     * @see #getZipCode()
     * @generated
     */
	void setZipCode(String value);

	/**
     * Returns the value of the '<em><b>City</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>City</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>City</em>' attribute.
     * @see #setCity(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData_City()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='city'"
     * @generated
     */
	String getCity();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.ContactData#getCity <em>City</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>City</em>' attribute.
     * @see #getCity()
     * @generated
     */
	void setCity(String value);

	/**
     * Returns the value of the '<em><b>State</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>State</em>' attribute.
     * @see #setState(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData_State()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='state'"
     * @generated
     */
	String getState();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.ContactData#getState <em>State</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>State</em>' attribute.
     * @see #getState()
     * @generated
     */
	void setState(String value);

	/**
     * Returns the value of the '<em><b>Country</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Country</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Country</em>' attribute.
     * @see #setCountry(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData_Country()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='country'"
     * @generated
     */
	String getCountry();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.ContactData#getCountry <em>Country</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Country</em>' attribute.
     * @see #getCountry()
     * @generated
     */
	void setCountry(String value);

	/**
     * Returns the value of the '<em><b>Website</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Website</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Website</em>' attribute.
     * @see #setWebsite(String)
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getContactData_Website()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='website'"
     * @generated
     */
	String getWebsite();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.organization.model.organization.ContactData#getWebsite <em>Website</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Website</em>' attribute.
     * @see #getWebsite()
     * @generated
     */
	void setWebsite(String value);

} // ContactData
