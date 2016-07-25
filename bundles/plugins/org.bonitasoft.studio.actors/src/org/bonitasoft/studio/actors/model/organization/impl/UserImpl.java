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
package org.bonitasoft.studio.actors.model.organization.impl;

import org.bonitasoft.studio.actors.model.organization.ContactData;
import org.bonitasoft.studio.actors.model.organization.CustomUserInfoValuesType;
import org.bonitasoft.studio.actors.model.organization.MetaDatasType;
import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.bonitasoft.studio.actors.model.organization.PasswordType;
import org.bonitasoft.studio.actors.model.organization.User;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getFirstName <em>First Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getLastName <em>Last Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getIconName <em>Icon Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getIconPath <em>Icon Path</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getJobTitle <em>Job Title</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getManager <em>Manager</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getPersonalData <em>Personal Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getProfessionalData <em>Professional Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getMetaDatas <em>Meta Datas</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getUserName <em>User Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link org.bonitasoft.studio.actors.model.organization.impl.UserImpl#getCustomUserInfoValues <em>Custom User Info Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserImpl extends EObjectImpl implements User {
	/**
     * The default value of the '{@link #getFirstName() <em>First Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFirstName()
     * @generated
     * @ordered
     */
	protected static final String FIRST_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getFirstName() <em>First Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFirstName()
     * @generated
     * @ordered
     */
	protected String firstName = FIRST_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getLastName() <em>Last Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLastName()
     * @generated
     * @ordered
     */
	protected static final String LAST_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getLastName() <em>Last Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLastName()
     * @generated
     * @ordered
     */
	protected String lastName = LAST_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getIconName() <em>Icon Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIconName()
     * @generated
     * @ordered
     */
	protected static final String ICON_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getIconName() <em>Icon Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIconName()
     * @generated
     * @ordered
     */
	protected String iconName = ICON_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getIconPath() <em>Icon Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIconPath()
     * @generated
     * @ordered
     */
	protected static final String ICON_PATH_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getIconPath() <em>Icon Path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIconPath()
     * @generated
     * @ordered
     */
	protected String iconPath = ICON_PATH_EDEFAULT;

	/**
     * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTitle()
     * @generated
     * @ordered
     */
	protected static final String TITLE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTitle()
     * @generated
     * @ordered
     */
	protected String title = TITLE_EDEFAULT;

	/**
     * The default value of the '{@link #getJobTitle() <em>Job Title</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getJobTitle()
     * @generated
     * @ordered
     */
	protected static final String JOB_TITLE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getJobTitle() <em>Job Title</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getJobTitle()
     * @generated
     * @ordered
     */
	protected String jobTitle = JOB_TITLE_EDEFAULT;

	/**
     * The default value of the '{@link #getManager() <em>Manager</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getManager()
     * @generated
     * @ordered
     */
	protected static final String MANAGER_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getManager() <em>Manager</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getManager()
     * @generated
     * @ordered
     */
	protected String manager = MANAGER_EDEFAULT;

	/**
     * The cached value of the '{@link #getPersonalData() <em>Personal Data</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPersonalData()
     * @generated
     * @ordered
     */
	protected ContactData personalData;

	/**
     * The cached value of the '{@link #getProfessionalData() <em>Professional Data</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getProfessionalData()
     * @generated
     * @ordered
     */
	protected ContactData professionalData;

	/**
     * The cached value of the '{@link #getMetaDatas() <em>Meta Datas</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMetaDatas()
     * @generated
     * @ordered
     */
	protected MetaDatasType metaDatas;

	/**
     * The default value of the '{@link #getUserName() <em>User Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUserName()
     * @generated
     * @ordered
     */
	protected static final String USER_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getUserName() <em>User Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUserName()
     * @generated
     * @ordered
     */
	protected String userName = USER_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isEnabled()
     * @generated
     * @ordered
     */
	protected static final boolean ENABLED_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isEnabled()
     * @generated
     * @ordered
     */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
     * This is true if the Enabled attribute has been set.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	protected boolean enabledESet;

	/**
     * The cached value of the '{@link #getPassword() <em>Password</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPassword()
     * @generated
     * @ordered
     */
	protected PasswordType password;

    /**
     * The cached value of the '{@link #getCustomUserInfoValues() <em>Custom User Info Values</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCustomUserInfoValues()
     * @generated
     * @ordered
     */
	protected CustomUserInfoValuesType customUserInfoValues;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected UserImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return OrganizationPackage.Literals.USER;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getFirstName() {
        return firstName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setFirstName(String newFirstName) {
        String oldFirstName = firstName;
        firstName = newFirstName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__FIRST_NAME, oldFirstName, firstName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getLastName() {
        return lastName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setLastName(String newLastName) {
        String oldLastName = lastName;
        lastName = newLastName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__LAST_NAME, oldLastName, lastName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getIconName() {
        return iconName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setIconName(String newIconName) {
        String oldIconName = iconName;
        iconName = newIconName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__ICON_NAME, oldIconName, iconName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getIconPath() {
        return iconPath;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setIconPath(String newIconPath) {
        String oldIconPath = iconPath;
        iconPath = newIconPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__ICON_PATH, oldIconPath, iconPath));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getTitle() {
        return title;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTitle(String newTitle) {
        String oldTitle = title;
        title = newTitle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__TITLE, oldTitle, title));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getJobTitle() {
        return jobTitle;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setJobTitle(String newJobTitle) {
        String oldJobTitle = jobTitle;
        jobTitle = newJobTitle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__JOB_TITLE, oldJobTitle, jobTitle));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getManager() {
        return manager;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setManager(String newManager) {
        String oldManager = manager;
        manager = newManager;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__MANAGER, oldManager, manager));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ContactData getPersonalData() {
        return personalData;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetPersonalData(ContactData newPersonalData, NotificationChain msgs) {
        ContactData oldPersonalData = personalData;
        personalData = newPersonalData;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__PERSONAL_DATA, oldPersonalData, newPersonalData);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setPersonalData(ContactData newPersonalData) {
        if (newPersonalData != personalData) {
            NotificationChain msgs = null;
            if (personalData != null)
                msgs = ((InternalEObject)personalData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.USER__PERSONAL_DATA, null, msgs);
            if (newPersonalData != null)
                msgs = ((InternalEObject)newPersonalData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.USER__PERSONAL_DATA, null, msgs);
            msgs = basicSetPersonalData(newPersonalData, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__PERSONAL_DATA, newPersonalData, newPersonalData));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ContactData getProfessionalData() {
        return professionalData;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetProfessionalData(ContactData newProfessionalData, NotificationChain msgs) {
        ContactData oldProfessionalData = professionalData;
        professionalData = newProfessionalData;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__PROFESSIONAL_DATA, oldProfessionalData, newProfessionalData);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setProfessionalData(ContactData newProfessionalData) {
        if (newProfessionalData != professionalData) {
            NotificationChain msgs = null;
            if (professionalData != null)
                msgs = ((InternalEObject)professionalData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.USER__PROFESSIONAL_DATA, null, msgs);
            if (newProfessionalData != null)
                msgs = ((InternalEObject)newProfessionalData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.USER__PROFESSIONAL_DATA, null, msgs);
            msgs = basicSetProfessionalData(newProfessionalData, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__PROFESSIONAL_DATA, newProfessionalData, newProfessionalData));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public MetaDatasType getMetaDatas() {
        return metaDatas;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMetaDatas(MetaDatasType newMetaDatas, NotificationChain msgs) {
        MetaDatasType oldMetaDatas = metaDatas;
        metaDatas = newMetaDatas;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__META_DATAS, oldMetaDatas, newMetaDatas);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setMetaDatas(MetaDatasType newMetaDatas) {
        if (newMetaDatas != metaDatas) {
            NotificationChain msgs = null;
            if (metaDatas != null)
                msgs = ((InternalEObject)metaDatas).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.USER__META_DATAS, null, msgs);
            if (newMetaDatas != null)
                msgs = ((InternalEObject)newMetaDatas).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.USER__META_DATAS, null, msgs);
            msgs = basicSetMetaDatas(newMetaDatas, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__META_DATAS, newMetaDatas, newMetaDatas));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getUserName() {
        return userName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setUserName(String newUserName) {
        String oldUserName = userName;
        userName = newUserName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__USER_NAME, oldUserName, userName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isEnabled() {
        return enabled;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setEnabled(boolean newEnabled) {
        boolean oldEnabled = enabled;
        enabled = newEnabled;
        boolean oldEnabledESet = enabledESet;
        enabledESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__ENABLED, oldEnabled, enabled, !oldEnabledESet));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void unsetEnabled() {
        boolean oldEnabled = enabled;
        boolean oldEnabledESet = enabledESet;
        enabled = ENABLED_EDEFAULT;
        enabledESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, OrganizationPackage.USER__ENABLED, oldEnabled, ENABLED_EDEFAULT, oldEnabledESet));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isSetEnabled() {
        return enabledESet;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CustomUserInfoValuesType getCustomUserInfoValues() {
        return customUserInfoValues;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetCustomUserInfoValues(CustomUserInfoValuesType newCustomUserInfoValues, NotificationChain msgs) {
        CustomUserInfoValuesType oldCustomUserInfoValues = customUserInfoValues;
        customUserInfoValues = newCustomUserInfoValues;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__CUSTOM_USER_INFO_VALUES, oldCustomUserInfoValues, newCustomUserInfoValues);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setCustomUserInfoValues(CustomUserInfoValuesType newCustomUserInfoValues) {
        if (newCustomUserInfoValues != customUserInfoValues) {
            NotificationChain msgs = null;
            if (customUserInfoValues != null)
                msgs = ((InternalEObject)customUserInfoValues).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.USER__CUSTOM_USER_INFO_VALUES, null, msgs);
            if (newCustomUserInfoValues != null)
                msgs = ((InternalEObject)newCustomUserInfoValues).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.USER__CUSTOM_USER_INFO_VALUES, null, msgs);
            msgs = basicSetCustomUserInfoValues(newCustomUserInfoValues, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__CUSTOM_USER_INFO_VALUES, newCustomUserInfoValues, newCustomUserInfoValues));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PasswordType getPassword() {
        return password;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetPassword(PasswordType newPassword, NotificationChain msgs) {
        PasswordType oldPassword = password;
        password = newPassword;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__PASSWORD, oldPassword, newPassword);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setPassword(PasswordType newPassword) {
        if (newPassword != password) {
            NotificationChain msgs = null;
            if (password != null)
                msgs = ((InternalEObject)password).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.USER__PASSWORD, null, msgs);
            if (newPassword != null)
                msgs = ((InternalEObject)newPassword).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OrganizationPackage.USER__PASSWORD, null, msgs);
            msgs = basicSetPassword(newPassword, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OrganizationPackage.USER__PASSWORD, newPassword, newPassword));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OrganizationPackage.USER__PERSONAL_DATA:
                return basicSetPersonalData(null, msgs);
            case OrganizationPackage.USER__PROFESSIONAL_DATA:
                return basicSetProfessionalData(null, msgs);
            case OrganizationPackage.USER__META_DATAS:
                return basicSetMetaDatas(null, msgs);
            case OrganizationPackage.USER__PASSWORD:
                return basicSetPassword(null, msgs);
            case OrganizationPackage.USER__CUSTOM_USER_INFO_VALUES:
                return basicSetCustomUserInfoValues(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case OrganizationPackage.USER__FIRST_NAME:
                return getFirstName();
            case OrganizationPackage.USER__LAST_NAME:
                return getLastName();
            case OrganizationPackage.USER__ICON_NAME:
                return getIconName();
            case OrganizationPackage.USER__ICON_PATH:
                return getIconPath();
            case OrganizationPackage.USER__TITLE:
                return getTitle();
            case OrganizationPackage.USER__JOB_TITLE:
                return getJobTitle();
            case OrganizationPackage.USER__MANAGER:
                return getManager();
            case OrganizationPackage.USER__PERSONAL_DATA:
                return getPersonalData();
            case OrganizationPackage.USER__PROFESSIONAL_DATA:
                return getProfessionalData();
            case OrganizationPackage.USER__META_DATAS:
                return getMetaDatas();
            case OrganizationPackage.USER__USER_NAME:
                return getUserName();
            case OrganizationPackage.USER__ENABLED:
                return isEnabled();
            case OrganizationPackage.USER__PASSWORD:
                return getPassword();
            case OrganizationPackage.USER__CUSTOM_USER_INFO_VALUES:
                return getCustomUserInfoValues();
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
            case OrganizationPackage.USER__FIRST_NAME:
                setFirstName((String)newValue);
                return;
            case OrganizationPackage.USER__LAST_NAME:
                setLastName((String)newValue);
                return;
            case OrganizationPackage.USER__ICON_NAME:
                setIconName((String)newValue);
                return;
            case OrganizationPackage.USER__ICON_PATH:
                setIconPath((String)newValue);
                return;
            case OrganizationPackage.USER__TITLE:
                setTitle((String)newValue);
                return;
            case OrganizationPackage.USER__JOB_TITLE:
                setJobTitle((String)newValue);
                return;
            case OrganizationPackage.USER__MANAGER:
                setManager((String)newValue);
                return;
            case OrganizationPackage.USER__PERSONAL_DATA:
                setPersonalData((ContactData)newValue);
                return;
            case OrganizationPackage.USER__PROFESSIONAL_DATA:
                setProfessionalData((ContactData)newValue);
                return;
            case OrganizationPackage.USER__META_DATAS:
                setMetaDatas((MetaDatasType)newValue);
                return;
            case OrganizationPackage.USER__USER_NAME:
                setUserName((String)newValue);
                return;
            case OrganizationPackage.USER__ENABLED:
                setEnabled((Boolean)newValue);
                return;
            case OrganizationPackage.USER__PASSWORD:
                setPassword((PasswordType)newValue);
                return;
            case OrganizationPackage.USER__CUSTOM_USER_INFO_VALUES:
                setCustomUserInfoValues((CustomUserInfoValuesType)newValue);
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
            case OrganizationPackage.USER__FIRST_NAME:
                setFirstName(FIRST_NAME_EDEFAULT);
                return;
            case OrganizationPackage.USER__LAST_NAME:
                setLastName(LAST_NAME_EDEFAULT);
                return;
            case OrganizationPackage.USER__ICON_NAME:
                setIconName(ICON_NAME_EDEFAULT);
                return;
            case OrganizationPackage.USER__ICON_PATH:
                setIconPath(ICON_PATH_EDEFAULT);
                return;
            case OrganizationPackage.USER__TITLE:
                setTitle(TITLE_EDEFAULT);
                return;
            case OrganizationPackage.USER__JOB_TITLE:
                setJobTitle(JOB_TITLE_EDEFAULT);
                return;
            case OrganizationPackage.USER__MANAGER:
                setManager(MANAGER_EDEFAULT);
                return;
            case OrganizationPackage.USER__PERSONAL_DATA:
                setPersonalData((ContactData)null);
                return;
            case OrganizationPackage.USER__PROFESSIONAL_DATA:
                setProfessionalData((ContactData)null);
                return;
            case OrganizationPackage.USER__META_DATAS:
                setMetaDatas((MetaDatasType)null);
                return;
            case OrganizationPackage.USER__USER_NAME:
                setUserName(USER_NAME_EDEFAULT);
                return;
            case OrganizationPackage.USER__ENABLED:
                unsetEnabled();
                return;
            case OrganizationPackage.USER__PASSWORD:
                setPassword((PasswordType)null);
                return;
            case OrganizationPackage.USER__CUSTOM_USER_INFO_VALUES:
                setCustomUserInfoValues((CustomUserInfoValuesType)null);
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
            case OrganizationPackage.USER__FIRST_NAME:
                return FIRST_NAME_EDEFAULT == null ? firstName != null : !FIRST_NAME_EDEFAULT.equals(firstName);
            case OrganizationPackage.USER__LAST_NAME:
                return LAST_NAME_EDEFAULT == null ? lastName != null : !LAST_NAME_EDEFAULT.equals(lastName);
            case OrganizationPackage.USER__ICON_NAME:
                return ICON_NAME_EDEFAULT == null ? iconName != null : !ICON_NAME_EDEFAULT.equals(iconName);
            case OrganizationPackage.USER__ICON_PATH:
                return ICON_PATH_EDEFAULT == null ? iconPath != null : !ICON_PATH_EDEFAULT.equals(iconPath);
            case OrganizationPackage.USER__TITLE:
                return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
            case OrganizationPackage.USER__JOB_TITLE:
                return JOB_TITLE_EDEFAULT == null ? jobTitle != null : !JOB_TITLE_EDEFAULT.equals(jobTitle);
            case OrganizationPackage.USER__MANAGER:
                return MANAGER_EDEFAULT == null ? manager != null : !MANAGER_EDEFAULT.equals(manager);
            case OrganizationPackage.USER__PERSONAL_DATA:
                return personalData != null;
            case OrganizationPackage.USER__PROFESSIONAL_DATA:
                return professionalData != null;
            case OrganizationPackage.USER__META_DATAS:
                return metaDatas != null;
            case OrganizationPackage.USER__USER_NAME:
                return USER_NAME_EDEFAULT == null ? userName != null : !USER_NAME_EDEFAULT.equals(userName);
            case OrganizationPackage.USER__ENABLED:
                return isSetEnabled();
            case OrganizationPackage.USER__PASSWORD:
                return password != null;
            case OrganizationPackage.USER__CUSTOM_USER_INFO_VALUES:
                return customUserInfoValues != null;
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

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (firstName: ");
        result.append(firstName);
        result.append(", lastName: ");
        result.append(lastName);
        result.append(", iconName: ");
        result.append(iconName);
        result.append(", iconPath: ");
        result.append(iconPath);
        result.append(", title: ");
        result.append(title);
        result.append(", jobTitle: ");
        result.append(jobTitle);
        result.append(", manager: ");
        result.append(manager);
        result.append(", userName: ");
        result.append(userName);
        result.append(", enabled: ");
        if (enabledESet) result.append(enabled); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //UserImpl
