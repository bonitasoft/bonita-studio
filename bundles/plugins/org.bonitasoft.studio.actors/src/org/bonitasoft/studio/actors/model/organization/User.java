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
package org.bonitasoft.studio.actors.model.organization;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getFirstName <em>First Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getLastName <em>Last Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getIconName <em>Icon Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getIconPath <em>Icon Path</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getTitle <em>Title</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getJobTitle <em>Job Title</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getManager <em>Manager</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getPersonalData <em>Personal Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getProfessionalData <em>Professional Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getMetaDatas <em>Meta Datas</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getUserName <em>User Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getPassword <em>Password</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.User#getCustomUserInfoValues <em>Custom User Info Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser()
 * @model extendedMetaData="name='User' kind='elementOnly'"
 * @generated
 */
public interface User extends EObject {
	/**
     * Returns the value of the '<em><b>First Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>First Name</em>' attribute.
     * @see #setFirstName(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_FirstName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='firstName'"
     * @generated
     */
	String getFirstName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getFirstName <em>First Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>First Name</em>' attribute.
     * @see #getFirstName()
     * @generated
     */
	void setFirstName(String value);

	/**
     * Returns the value of the '<em><b>Last Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Last Name</em>' attribute.
     * @see #setLastName(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_LastName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='lastName'"
     * @generated
     */
	String getLastName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getLastName <em>Last Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Last Name</em>' attribute.
     * @see #getLastName()
     * @generated
     */
	void setLastName(String value);

	/**
     * Returns the value of the '<em><b>Icon Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icon Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Icon Name</em>' attribute.
     * @see #setIconName(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_IconName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='iconName'"
     * @generated
     */
	String getIconName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getIconName <em>Icon Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Icon Name</em>' attribute.
     * @see #getIconName()
     * @generated
     */
	void setIconName(String value);

	/**
     * Returns the value of the '<em><b>Icon Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icon Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Icon Path</em>' attribute.
     * @see #setIconPath(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_IconPath()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='iconPath'"
     * @generated
     */
	String getIconPath();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getIconPath <em>Icon Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Icon Path</em>' attribute.
     * @see #getIconPath()
     * @generated
     */
	void setIconPath(String value);

	/**
     * Returns the value of the '<em><b>Title</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Title</em>' attribute.
     * @see #setTitle(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_Title()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='title'"
     * @generated
     */
	String getTitle();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getTitle <em>Title</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Title</em>' attribute.
     * @see #getTitle()
     * @generated
     */
	void setTitle(String value);

	/**
     * Returns the value of the '<em><b>Job Title</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Job Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Job Title</em>' attribute.
     * @see #setJobTitle(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_JobTitle()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='jobTitle'"
     * @generated
     */
	String getJobTitle();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getJobTitle <em>Job Title</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Job Title</em>' attribute.
     * @see #getJobTitle()
     * @generated
     */
	void setJobTitle(String value);

	/**
     * Returns the value of the '<em><b>Manager</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manager</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Manager</em>' attribute.
     * @see #setManager(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_Manager()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='manager'"
     * @generated
     */
	String getManager();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getManager <em>Manager</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Manager</em>' attribute.
     * @see #getManager()
     * @generated
     */
	void setManager(String value);

	/**
     * Returns the value of the '<em><b>Personal Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Personal Data</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Personal Data</em>' containment reference.
     * @see #setPersonalData(ContactData)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_PersonalData()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='personalData'"
     * @generated
     */
	ContactData getPersonalData();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getPersonalData <em>Personal Data</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Personal Data</em>' containment reference.
     * @see #getPersonalData()
     * @generated
     */
	void setPersonalData(ContactData value);

	/**
     * Returns the value of the '<em><b>Professional Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Professional Data</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Professional Data</em>' containment reference.
     * @see #setProfessionalData(ContactData)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_ProfessionalData()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='professionalData'"
     * @generated
     */
	ContactData getProfessionalData();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getProfessionalData <em>Professional Data</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Professional Data</em>' containment reference.
     * @see #getProfessionalData()
     * @generated
     */
	void setProfessionalData(ContactData value);

	/**
     * Returns the value of the '<em><b>Meta Datas</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta Datas</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Meta Datas</em>' containment reference.
     * @see #setMetaDatas(MetaDatasType)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_MetaDatas()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='metaDatas'"
     * @generated
     */
	MetaDatasType getMetaDatas();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getMetaDatas <em>Meta Datas</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Meta Datas</em>' containment reference.
     * @see #getMetaDatas()
     * @generated
     */
	void setMetaDatas(MetaDatasType value);

	/**
     * Returns the value of the '<em><b>User Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>User Name</em>' attribute.
     * @see #setUserName(String)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_UserName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='userName'"
     * @generated
     */
	String getUserName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getUserName <em>User Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>User Name</em>' attribute.
     * @see #getUserName()
     * @generated
     */
	void setUserName(String value);

	/**
     * Returns the value of the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Enabled</em>' attribute.
     * @see #isSetEnabled()
     * @see #unsetEnabled()
     * @see #setEnabled(boolean)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_Enabled()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='enabled'"
     * @generated
     */
	boolean isEnabled();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#isEnabled <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Enabled</em>' attribute.
     * @see #isSetEnabled()
     * @see #unsetEnabled()
     * @see #isEnabled()
     * @generated
     */
	void setEnabled(boolean value);

	/**
     * Unsets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#isEnabled <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSetEnabled()
     * @see #isEnabled()
     * @see #setEnabled(boolean)
     * @generated
     */
	void unsetEnabled();

	/**
     * Returns whether the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#isEnabled <em>Enabled</em>}' attribute is set.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return whether the value of the '<em>Enabled</em>' attribute is set.
     * @see #unsetEnabled()
     * @see #isEnabled()
     * @see #setEnabled(boolean)
     * @generated
     */
	boolean isSetEnabled();

	/**
     * Returns the value of the '<em><b>Custom User Info Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Custom User Info Values</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Custom User Info Values</em>' containment reference.
     * @see #setCustomUserInfoValues(CustomUserInfoValuesType)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_CustomUserInfoValues()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='customUserInfoValues'"
     * @generated
     */
	CustomUserInfoValuesType getCustomUserInfoValues();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getCustomUserInfoValues <em>Custom User Info Values</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Custom User Info Values</em>' containment reference.
     * @see #getCustomUserInfoValues()
     * @generated
     */
	void setCustomUserInfoValues(CustomUserInfoValuesType value);

	/**
     * Returns the value of the '<em><b>Password</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Password</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Password</em>' containment reference.
     * @see #setPassword(PasswordType)
     * @see org.bonitasoft.studio.actors.model.organization.OrganizationPackage#getUser_Password()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='password'"
     * @generated
     */
	PasswordType getPassword();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.actors.model.organization.User#getPassword <em>Password</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Password</em>' containment reference.
     * @see #getPassword()
     * @generated
     */
	void setPassword(PasswordType value);

} // User
