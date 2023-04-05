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
package org.bonitasoft.studio.identity.organization.model.organization.impl;

import org.bonitasoft.studio.identity.organization.model.organization.ContactData;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contact Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.ContactDataImpl#getEmail <em>Email</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.ContactDataImpl#getPhoneNumber <em>Phone Number</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.ContactDataImpl#getMobileNumber <em>Mobile Number</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.ContactDataImpl#getFaxNumber <em>Fax Number</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.ContactDataImpl#getBuilding <em>Building</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.ContactDataImpl#getRoom <em>Room</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.ContactDataImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.ContactDataImpl#getZipCode <em>Zip Code</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.ContactDataImpl#getCity <em>City</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.ContactDataImpl#getState <em>State</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.ContactDataImpl#getCountry <em>Country</em>}</li>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.impl.ContactDataImpl#getWebsite <em>Website</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContactDataImpl extends EObjectImpl implements ContactData {
	/**
     * The default value of the '{@link #getEmail() <em>Email</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getEmail()
     * @generated
     * @ordered
     */
	protected static final String EMAIL_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getEmail() <em>Email</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getEmail()
     * @generated
     * @ordered
     */
	protected String email = EMAIL_EDEFAULT;

	/**
     * The default value of the '{@link #getPhoneNumber() <em>Phone Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPhoneNumber()
     * @generated
     * @ordered
     */
	protected static final String PHONE_NUMBER_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getPhoneNumber() <em>Phone Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPhoneNumber()
     * @generated
     * @ordered
     */
	protected String phoneNumber = PHONE_NUMBER_EDEFAULT;

	/**
     * The default value of the '{@link #getMobileNumber() <em>Mobile Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMobileNumber()
     * @generated
     * @ordered
     */
	protected static final String MOBILE_NUMBER_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getMobileNumber() <em>Mobile Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMobileNumber()
     * @generated
     * @ordered
     */
	protected String mobileNumber = MOBILE_NUMBER_EDEFAULT;

	/**
     * The default value of the '{@link #getFaxNumber() <em>Fax Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFaxNumber()
     * @generated
     * @ordered
     */
	protected static final String FAX_NUMBER_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getFaxNumber() <em>Fax Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFaxNumber()
     * @generated
     * @ordered
     */
	protected String faxNumber = FAX_NUMBER_EDEFAULT;

	/**
     * The default value of the '{@link #getBuilding() <em>Building</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getBuilding()
     * @generated
     * @ordered
     */
	protected static final String BUILDING_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getBuilding() <em>Building</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getBuilding()
     * @generated
     * @ordered
     */
	protected String building = BUILDING_EDEFAULT;

	/**
     * The default value of the '{@link #getRoom() <em>Room</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRoom()
     * @generated
     * @ordered
     */
	protected static final String ROOM_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getRoom() <em>Room</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRoom()
     * @generated
     * @ordered
     */
	protected String room = ROOM_EDEFAULT;

	/**
     * The default value of the '{@link #getAddress() <em>Address</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAddress()
     * @generated
     * @ordered
     */
	protected static final String ADDRESS_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getAddress() <em>Address</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAddress()
     * @generated
     * @ordered
     */
	protected String address = ADDRESS_EDEFAULT;

	/**
     * The default value of the '{@link #getZipCode() <em>Zip Code</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getZipCode()
     * @generated
     * @ordered
     */
	protected static final String ZIP_CODE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getZipCode() <em>Zip Code</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getZipCode()
     * @generated
     * @ordered
     */
	protected String zipCode = ZIP_CODE_EDEFAULT;

	/**
     * The default value of the '{@link #getCity() <em>City</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCity()
     * @generated
     * @ordered
     */
	protected static final String CITY_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getCity() <em>City</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCity()
     * @generated
     * @ordered
     */
	protected String city = CITY_EDEFAULT;

	/**
     * The default value of the '{@link #getState() <em>State</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getState()
     * @generated
     * @ordered
     */
	protected static final String STATE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getState() <em>State</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getState()
     * @generated
     * @ordered
     */
	protected String state = STATE_EDEFAULT;

	/**
     * The default value of the '{@link #getCountry() <em>Country</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCountry()
     * @generated
     * @ordered
     */
	protected static final String COUNTRY_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getCountry() <em>Country</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCountry()
     * @generated
     * @ordered
     */
	protected String country = COUNTRY_EDEFAULT;

	/**
     * The default value of the '{@link #getWebsite() <em>Website</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getWebsite()
     * @generated
     * @ordered
     */
	protected static final String WEBSITE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getWebsite() <em>Website</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getWebsite()
     * @generated
     * @ordered
     */
	protected String website = WEBSITE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ContactDataImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return OrganizationPackage.Literals.CONTACT_DATA;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getEmail() {
        return email;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setEmail(String newEmail) {
        String oldEmail = email;
        email = newEmail;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.CONTACT_DATA__EMAIL, oldEmail, email));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setPhoneNumber(String newPhoneNumber) {
        String oldPhoneNumber = phoneNumber;
        phoneNumber = newPhoneNumber;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.CONTACT_DATA__PHONE_NUMBER, oldPhoneNumber, phoneNumber));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getMobileNumber() {
        return mobileNumber;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setMobileNumber(String newMobileNumber) {
        String oldMobileNumber = mobileNumber;
        mobileNumber = newMobileNumber;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.CONTACT_DATA__MOBILE_NUMBER, oldMobileNumber, mobileNumber));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getFaxNumber() {
        return faxNumber;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setFaxNumber(String newFaxNumber) {
        String oldFaxNumber = faxNumber;
        faxNumber = newFaxNumber;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.CONTACT_DATA__FAX_NUMBER, oldFaxNumber, faxNumber));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getBuilding() {
        return building;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setBuilding(String newBuilding) {
        String oldBuilding = building;
        building = newBuilding;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.CONTACT_DATA__BUILDING, oldBuilding, building));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getRoom() {
        return room;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setRoom(String newRoom) {
        String oldRoom = room;
        room = newRoom;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.CONTACT_DATA__ROOM, oldRoom, room));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getAddress() {
        return address;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setAddress(String newAddress) {
        String oldAddress = address;
        address = newAddress;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.CONTACT_DATA__ADDRESS, oldAddress, address));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getZipCode() {
        return zipCode;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setZipCode(String newZipCode) {
        String oldZipCode = zipCode;
        zipCode = newZipCode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.CONTACT_DATA__ZIP_CODE, oldZipCode, zipCode));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getCity() {
        return city;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setCity(String newCity) {
        String oldCity = city;
        city = newCity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.CONTACT_DATA__CITY, oldCity, city));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getState() {
        return state;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setState(String newState) {
        String oldState = state;
        state = newState;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.CONTACT_DATA__STATE, oldState, state));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getCountry() {
        return country;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setCountry(String newCountry) {
        String oldCountry = country;
        country = newCountry;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.CONTACT_DATA__COUNTRY, oldCountry, country));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public String getWebsite() {
        return website;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public void setWebsite(String newWebsite) {
        String oldWebsite = website;
        website = newWebsite;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.CONTACT_DATA__WEBSITE, oldWebsite, website));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case OrganizationPackage.CONTACT_DATA__EMAIL:
                return getEmail();
            case OrganizationPackage.CONTACT_DATA__PHONE_NUMBER:
                return getPhoneNumber();
            case OrganizationPackage.CONTACT_DATA__MOBILE_NUMBER:
                return getMobileNumber();
            case OrganizationPackage.CONTACT_DATA__FAX_NUMBER:
                return getFaxNumber();
            case OrganizationPackage.CONTACT_DATA__BUILDING:
                return getBuilding();
            case OrganizationPackage.CONTACT_DATA__ROOM:
                return getRoom();
            case OrganizationPackage.CONTACT_DATA__ADDRESS:
                return getAddress();
            case OrganizationPackage.CONTACT_DATA__ZIP_CODE:
                return getZipCode();
            case OrganizationPackage.CONTACT_DATA__CITY:
                return getCity();
            case OrganizationPackage.CONTACT_DATA__STATE:
                return getState();
            case OrganizationPackage.CONTACT_DATA__COUNTRY:
                return getCountry();
            case OrganizationPackage.CONTACT_DATA__WEBSITE:
                return getWebsite();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case OrganizationPackage.CONTACT_DATA__EMAIL:
                setEmail((String)newValue);
                return;
            case OrganizationPackage.CONTACT_DATA__PHONE_NUMBER:
                setPhoneNumber((String)newValue);
                return;
            case OrganizationPackage.CONTACT_DATA__MOBILE_NUMBER:
                setMobileNumber((String)newValue);
                return;
            case OrganizationPackage.CONTACT_DATA__FAX_NUMBER:
                setFaxNumber((String)newValue);
                return;
            case OrganizationPackage.CONTACT_DATA__BUILDING:
                setBuilding((String)newValue);
                return;
            case OrganizationPackage.CONTACT_DATA__ROOM:
                setRoom((String)newValue);
                return;
            case OrganizationPackage.CONTACT_DATA__ADDRESS:
                setAddress((String)newValue);
                return;
            case OrganizationPackage.CONTACT_DATA__ZIP_CODE:
                setZipCode((String)newValue);
                return;
            case OrganizationPackage.CONTACT_DATA__CITY:
                setCity((String)newValue);
                return;
            case OrganizationPackage.CONTACT_DATA__STATE:
                setState((String)newValue);
                return;
            case OrganizationPackage.CONTACT_DATA__COUNTRY:
                setCountry((String)newValue);
                return;
            case OrganizationPackage.CONTACT_DATA__WEBSITE:
                setWebsite((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eUnset(int featureID) {
        switch (featureID) {
            case OrganizationPackage.CONTACT_DATA__EMAIL:
                setEmail(EMAIL_EDEFAULT);
                return;
            case OrganizationPackage.CONTACT_DATA__PHONE_NUMBER:
                setPhoneNumber(PHONE_NUMBER_EDEFAULT);
                return;
            case OrganizationPackage.CONTACT_DATA__MOBILE_NUMBER:
                setMobileNumber(MOBILE_NUMBER_EDEFAULT);
                return;
            case OrganizationPackage.CONTACT_DATA__FAX_NUMBER:
                setFaxNumber(FAX_NUMBER_EDEFAULT);
                return;
            case OrganizationPackage.CONTACT_DATA__BUILDING:
                setBuilding(BUILDING_EDEFAULT);
                return;
            case OrganizationPackage.CONTACT_DATA__ROOM:
                setRoom(ROOM_EDEFAULT);
                return;
            case OrganizationPackage.CONTACT_DATA__ADDRESS:
                setAddress(ADDRESS_EDEFAULT);
                return;
            case OrganizationPackage.CONTACT_DATA__ZIP_CODE:
                setZipCode(ZIP_CODE_EDEFAULT);
                return;
            case OrganizationPackage.CONTACT_DATA__CITY:
                setCity(CITY_EDEFAULT);
                return;
            case OrganizationPackage.CONTACT_DATA__STATE:
                setState(STATE_EDEFAULT);
                return;
            case OrganizationPackage.CONTACT_DATA__COUNTRY:
                setCountry(COUNTRY_EDEFAULT);
                return;
            case OrganizationPackage.CONTACT_DATA__WEBSITE:
                setWebsite(WEBSITE_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case OrganizationPackage.CONTACT_DATA__EMAIL:
                return EMAIL_EDEFAULT == null ? email != null : !EMAIL_EDEFAULT.equals(email);
            case OrganizationPackage.CONTACT_DATA__PHONE_NUMBER:
                return PHONE_NUMBER_EDEFAULT == null ? phoneNumber != null : !PHONE_NUMBER_EDEFAULT.equals(phoneNumber);
            case OrganizationPackage.CONTACT_DATA__MOBILE_NUMBER:
                return MOBILE_NUMBER_EDEFAULT == null ? mobileNumber != null : !MOBILE_NUMBER_EDEFAULT.equals(mobileNumber);
            case OrganizationPackage.CONTACT_DATA__FAX_NUMBER:
                return FAX_NUMBER_EDEFAULT == null ? faxNumber != null : !FAX_NUMBER_EDEFAULT.equals(faxNumber);
            case OrganizationPackage.CONTACT_DATA__BUILDING:
                return BUILDING_EDEFAULT == null ? building != null : !BUILDING_EDEFAULT.equals(building);
            case OrganizationPackage.CONTACT_DATA__ROOM:
                return ROOM_EDEFAULT == null ? room != null : !ROOM_EDEFAULT.equals(room);
            case OrganizationPackage.CONTACT_DATA__ADDRESS:
                return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
            case OrganizationPackage.CONTACT_DATA__ZIP_CODE:
                return ZIP_CODE_EDEFAULT == null ? zipCode != null : !ZIP_CODE_EDEFAULT.equals(zipCode);
            case OrganizationPackage.CONTACT_DATA__CITY:
                return CITY_EDEFAULT == null ? city != null : !CITY_EDEFAULT.equals(city);
            case OrganizationPackage.CONTACT_DATA__STATE:
                return STATE_EDEFAULT == null ? state != null : !STATE_EDEFAULT.equals(state);
            case OrganizationPackage.CONTACT_DATA__COUNTRY:
                return COUNTRY_EDEFAULT == null ? country != null : !COUNTRY_EDEFAULT.equals(country);
            case OrganizationPackage.CONTACT_DATA__WEBSITE:
                return WEBSITE_EDEFAULT == null ? website != null : !WEBSITE_EDEFAULT.equals(website);
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (email: ");
        result.append(email);
        result.append(", phoneNumber: ");
        result.append(phoneNumber);
        result.append(", mobileNumber: ");
        result.append(mobileNumber);
        result.append(", faxNumber: ");
        result.append(faxNumber);
        result.append(", building: ");
        result.append(building);
        result.append(", room: ");
        result.append(room);
        result.append(", address: ");
        result.append(address);
        result.append(", zipCode: ");
        result.append(zipCode);
        result.append(", city: ");
        result.append(city);
        result.append(", state: ");
        result.append(state);
        result.append(", country: ");
        result.append(country);
        result.append(", website: ");
        result.append(website);
        result.append(')');
        return result.toString();
    }

} //ContactDataImpl
