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
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinition;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinitions;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoValue;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoValuesType;
import org.bonitasoft.studio.identity.organization.model.organization.DocumentRoot;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.Groups;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Memberships;
import org.bonitasoft.studio.identity.organization.model.organization.MetaDatasType;
import org.bonitasoft.studio.identity.organization.model.organization.Metadata;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.PasswordType;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.identity.organization.model.organization.Roles;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.model.organization.Users;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OrganizationPackageImpl extends EPackageImpl implements OrganizationPackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass contactDataEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass customUserInfoDefinitionEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass customUserInfoDefinitionsEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass customUserInfoValueEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass customUserInfoValuesTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass documentRootEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass groupEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass groupsEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass membershipEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass membershipsEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass metadataEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass metaDatasTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass organizationEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass roleEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass rolesEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass userEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass usersEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass passwordTypeEClass = null;

	/**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#eNS_URI
     * @see #init()
     * @generated
     */
	private OrganizationPackageImpl() {
        super(eNS_URI, OrganizationFactory.eINSTANCE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static boolean isInited = false;

	/**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>This method is used to initialize {@link OrganizationPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static OrganizationPackage init() {
        if (isInited) return (OrganizationPackage)EPackage.Registry.INSTANCE.getEPackage(OrganizationPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredOrganizationPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        OrganizationPackageImpl theOrganizationPackage = registeredOrganizationPackage instanceof OrganizationPackageImpl ? (OrganizationPackageImpl)registeredOrganizationPackage : new OrganizationPackageImpl();

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theOrganizationPackage.createPackageContents();

        // Initialize created meta-data
        theOrganizationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theOrganizationPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(OrganizationPackage.eNS_URI, theOrganizationPackage);
        return theOrganizationPackage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getContactData() {
        return contactDataEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getContactData_Email() {
        return (EAttribute)contactDataEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getContactData_PhoneNumber() {
        return (EAttribute)contactDataEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getContactData_MobileNumber() {
        return (EAttribute)contactDataEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getContactData_FaxNumber() {
        return (EAttribute)contactDataEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getContactData_Building() {
        return (EAttribute)contactDataEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getContactData_Room() {
        return (EAttribute)contactDataEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getContactData_Address() {
        return (EAttribute)contactDataEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getContactData_ZipCode() {
        return (EAttribute)contactDataEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getContactData_City() {
        return (EAttribute)contactDataEClass.getEStructuralFeatures().get(8);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getContactData_State() {
        return (EAttribute)contactDataEClass.getEStructuralFeatures().get(9);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getContactData_Country() {
        return (EAttribute)contactDataEClass.getEStructuralFeatures().get(10);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getContactData_Website() {
        return (EAttribute)contactDataEClass.getEStructuralFeatures().get(11);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getCustomUserInfoDefinition() {
        return customUserInfoDefinitionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getCustomUserInfoDefinition_Name() {
        return (EAttribute)customUserInfoDefinitionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getCustomUserInfoDefinition_Description() {
        return (EAttribute)customUserInfoDefinitionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getCustomUserInfoDefinitions() {
        return customUserInfoDefinitionsEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getCustomUserInfoDefinitions_CustomUserInfoDefinition() {
        return (EReference)customUserInfoDefinitionsEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getCustomUserInfoValue() {
        return customUserInfoValueEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getCustomUserInfoValue_Name() {
        return (EAttribute)customUserInfoValueEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getCustomUserInfoValue_Value() {
        return (EAttribute)customUserInfoValueEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getCustomUserInfoValuesType() {
        return customUserInfoValuesTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getCustomUserInfoValuesType_CustomUserInfoValue() {
        return (EReference)customUserInfoValuesTypeEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getDocumentRoot() {
        return documentRootEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getDocumentRoot_Organization() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getGroup() {
        return groupEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getGroup_DisplayName() {
        return (EAttribute)groupEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getGroup_Description() {
        return (EAttribute)groupEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getGroup_IconName() {
        return (EAttribute)groupEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getGroup_IconPath() {
        return (EAttribute)groupEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getGroup_Name() {
        return (EAttribute)groupEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getGroup_ParentPath() {
        return (EAttribute)groupEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getGroups() {
        return groupsEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getGroups_Group() {
        return (EReference)groupsEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getMembership() {
        return membershipEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getMembership_UserName() {
        return (EAttribute)membershipEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getMembership_RoleName() {
        return (EAttribute)membershipEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getMembership_GroupName() {
        return (EAttribute)membershipEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getMembership_GroupParentPath() {
        return (EAttribute)membershipEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getMembership_AssignedBy() {
        return (EAttribute)membershipEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getMembership_AssignedDate() {
        return (EAttribute)membershipEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getMemberships() {
        return membershipsEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getMemberships_Membership() {
        return (EReference)membershipsEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getMetadata() {
        return metadataEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getMetadata_Name() {
        return (EAttribute)metadataEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getMetadata_Value() {
        return (EAttribute)metadataEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getMetaDatasType() {
        return metaDatasTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getMetaDatasType_MetaData() {
        return (EReference)metaDatasTypeEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getOrganization() {
        return organizationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getOrganization_CustomUserInfoDefinitions() {
        return (EReference)organizationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getOrganization_Users() {
        return (EReference)organizationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getOrganization_Roles() {
        return (EReference)organizationEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getOrganization_Groups() {
        return (EReference)organizationEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getOrganization_Memberships() {
        return (EReference)organizationEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getOrganization_Name() {
        return (EAttribute)organizationEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getOrganization_Description() {
        return (EAttribute)organizationEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getOrganization_ModelVersion() {
        return (EAttribute)organizationEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getRole() {
        return roleEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getRole_DisplayName() {
        return (EAttribute)roleEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getRole_Description() {
        return (EAttribute)roleEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getRole_IconName() {
        return (EAttribute)roleEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getRole_IconPath() {
        return (EAttribute)roleEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getRole_Name() {
        return (EAttribute)roleEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getRoles() {
        return rolesEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getRoles_Role() {
        return (EReference)rolesEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getUser() {
        return userEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getUser_FirstName() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getUser_LastName() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getUser_IconName() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getUser_IconPath() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getUser_Title() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getUser_JobTitle() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(5);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getUser_Manager() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(6);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getUser_PersonalData() {
        return (EReference)userEClass.getEStructuralFeatures().get(7);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getUser_ProfessionalData() {
        return (EReference)userEClass.getEStructuralFeatures().get(8);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getUser_MetaDatas() {
        return (EReference)userEClass.getEStructuralFeatures().get(9);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getUser_UserName() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(10);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getUser_Enabled() {
        return (EAttribute)userEClass.getEStructuralFeatures().get(11);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getUser_CustomUserInfoValues() {
        return (EReference)userEClass.getEStructuralFeatures().get(13);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getUser_Password() {
        return (EReference)userEClass.getEStructuralFeatures().get(12);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getUsers() {
        return usersEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EReference getUsers_User() {
        return (EReference)usersEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EClass getPasswordType() {
        return passwordTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getPasswordType_Value() {
        return (EAttribute)passwordTypeEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public EAttribute getPasswordType_Encrypted() {
        return (EAttribute)passwordTypeEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
    public OrganizationFactory getOrganizationFactory() {
        return (OrganizationFactory)getEFactoryInstance();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isCreated = false;

	/**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        contactDataEClass = createEClass(CONTACT_DATA);
        createEAttribute(contactDataEClass, CONTACT_DATA__EMAIL);
        createEAttribute(contactDataEClass, CONTACT_DATA__PHONE_NUMBER);
        createEAttribute(contactDataEClass, CONTACT_DATA__MOBILE_NUMBER);
        createEAttribute(contactDataEClass, CONTACT_DATA__FAX_NUMBER);
        createEAttribute(contactDataEClass, CONTACT_DATA__BUILDING);
        createEAttribute(contactDataEClass, CONTACT_DATA__ROOM);
        createEAttribute(contactDataEClass, CONTACT_DATA__ADDRESS);
        createEAttribute(contactDataEClass, CONTACT_DATA__ZIP_CODE);
        createEAttribute(contactDataEClass, CONTACT_DATA__CITY);
        createEAttribute(contactDataEClass, CONTACT_DATA__STATE);
        createEAttribute(contactDataEClass, CONTACT_DATA__COUNTRY);
        createEAttribute(contactDataEClass, CONTACT_DATA__WEBSITE);

        customUserInfoDefinitionEClass = createEClass(CUSTOM_USER_INFO_DEFINITION);
        createEAttribute(customUserInfoDefinitionEClass, CUSTOM_USER_INFO_DEFINITION__NAME);
        createEAttribute(customUserInfoDefinitionEClass, CUSTOM_USER_INFO_DEFINITION__DESCRIPTION);

        customUserInfoDefinitionsEClass = createEClass(CUSTOM_USER_INFO_DEFINITIONS);
        createEReference(customUserInfoDefinitionsEClass, CUSTOM_USER_INFO_DEFINITIONS__CUSTOM_USER_INFO_DEFINITION);

        customUserInfoValueEClass = createEClass(CUSTOM_USER_INFO_VALUE);
        createEAttribute(customUserInfoValueEClass, CUSTOM_USER_INFO_VALUE__NAME);
        createEAttribute(customUserInfoValueEClass, CUSTOM_USER_INFO_VALUE__VALUE);

        customUserInfoValuesTypeEClass = createEClass(CUSTOM_USER_INFO_VALUES_TYPE);
        createEReference(customUserInfoValuesTypeEClass, CUSTOM_USER_INFO_VALUES_TYPE__CUSTOM_USER_INFO_VALUE);

        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ORGANIZATION);

        groupEClass = createEClass(GROUP);
        createEAttribute(groupEClass, GROUP__DISPLAY_NAME);
        createEAttribute(groupEClass, GROUP__DESCRIPTION);
        createEAttribute(groupEClass, GROUP__ICON_NAME);
        createEAttribute(groupEClass, GROUP__ICON_PATH);
        createEAttribute(groupEClass, GROUP__NAME);
        createEAttribute(groupEClass, GROUP__PARENT_PATH);

        groupsEClass = createEClass(GROUPS);
        createEReference(groupsEClass, GROUPS__GROUP);

        membershipEClass = createEClass(MEMBERSHIP);
        createEAttribute(membershipEClass, MEMBERSHIP__USER_NAME);
        createEAttribute(membershipEClass, MEMBERSHIP__ROLE_NAME);
        createEAttribute(membershipEClass, MEMBERSHIP__GROUP_NAME);
        createEAttribute(membershipEClass, MEMBERSHIP__GROUP_PARENT_PATH);
        createEAttribute(membershipEClass, MEMBERSHIP__ASSIGNED_BY);
        createEAttribute(membershipEClass, MEMBERSHIP__ASSIGNED_DATE);

        membershipsEClass = createEClass(MEMBERSHIPS);
        createEReference(membershipsEClass, MEMBERSHIPS__MEMBERSHIP);

        metadataEClass = createEClass(METADATA);
        createEAttribute(metadataEClass, METADATA__NAME);
        createEAttribute(metadataEClass, METADATA__VALUE);

        metaDatasTypeEClass = createEClass(META_DATAS_TYPE);
        createEReference(metaDatasTypeEClass, META_DATAS_TYPE__META_DATA);

        organizationEClass = createEClass(ORGANIZATION);
        createEReference(organizationEClass, ORGANIZATION__CUSTOM_USER_INFO_DEFINITIONS);
        createEReference(organizationEClass, ORGANIZATION__USERS);
        createEReference(organizationEClass, ORGANIZATION__ROLES);
        createEReference(organizationEClass, ORGANIZATION__GROUPS);
        createEReference(organizationEClass, ORGANIZATION__MEMBERSHIPS);
        createEAttribute(organizationEClass, ORGANIZATION__NAME);
        createEAttribute(organizationEClass, ORGANIZATION__DESCRIPTION);
        createEAttribute(organizationEClass, ORGANIZATION__MODEL_VERSION);

        roleEClass = createEClass(ROLE);
        createEAttribute(roleEClass, ROLE__DISPLAY_NAME);
        createEAttribute(roleEClass, ROLE__DESCRIPTION);
        createEAttribute(roleEClass, ROLE__ICON_NAME);
        createEAttribute(roleEClass, ROLE__ICON_PATH);
        createEAttribute(roleEClass, ROLE__NAME);

        rolesEClass = createEClass(ROLES);
        createEReference(rolesEClass, ROLES__ROLE);

        userEClass = createEClass(USER);
        createEAttribute(userEClass, USER__FIRST_NAME);
        createEAttribute(userEClass, USER__LAST_NAME);
        createEAttribute(userEClass, USER__ICON_NAME);
        createEAttribute(userEClass, USER__ICON_PATH);
        createEAttribute(userEClass, USER__TITLE);
        createEAttribute(userEClass, USER__JOB_TITLE);
        createEAttribute(userEClass, USER__MANAGER);
        createEReference(userEClass, USER__PERSONAL_DATA);
        createEReference(userEClass, USER__PROFESSIONAL_DATA);
        createEReference(userEClass, USER__META_DATAS);
        createEAttribute(userEClass, USER__USER_NAME);
        createEAttribute(userEClass, USER__ENABLED);
        createEReference(userEClass, USER__PASSWORD);
        createEReference(userEClass, USER__CUSTOM_USER_INFO_VALUES);

        usersEClass = createEClass(USERS);
        createEReference(usersEClass, USERS__USER);

        passwordTypeEClass = createEClass(PASSWORD_TYPE);
        createEAttribute(passwordTypeEClass, PASSWORD_TYPE__VALUE);
        createEAttribute(passwordTypeEClass, PASSWORD_TYPE__ENCRYPTED);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isInitialized = false;

	/**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(contactDataEClass, ContactData.class, "ContactData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getContactData_Email(), theXMLTypePackage.getString(), "email", null, 0, 1, ContactData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContactData_PhoneNumber(), theXMLTypePackage.getString(), "phoneNumber", null, 0, 1, ContactData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContactData_MobileNumber(), theXMLTypePackage.getString(), "mobileNumber", null, 0, 1, ContactData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContactData_FaxNumber(), theXMLTypePackage.getString(), "faxNumber", null, 0, 1, ContactData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContactData_Building(), theXMLTypePackage.getString(), "building", null, 0, 1, ContactData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContactData_Room(), theXMLTypePackage.getString(), "room", null, 0, 1, ContactData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContactData_Address(), theXMLTypePackage.getString(), "address", null, 0, 1, ContactData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContactData_ZipCode(), theXMLTypePackage.getString(), "zipCode", null, 0, 1, ContactData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContactData_City(), theXMLTypePackage.getString(), "city", null, 0, 1, ContactData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContactData_State(), theXMLTypePackage.getString(), "state", null, 0, 1, ContactData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContactData_Country(), theXMLTypePackage.getString(), "country", null, 0, 1, ContactData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContactData_Website(), theXMLTypePackage.getString(), "website", null, 0, 1, ContactData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(customUserInfoDefinitionEClass, CustomUserInfoDefinition.class, "CustomUserInfoDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCustomUserInfoDefinition_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, CustomUserInfoDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCustomUserInfoDefinition_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, CustomUserInfoDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(customUserInfoDefinitionsEClass, CustomUserInfoDefinitions.class, "CustomUserInfoDefinitions", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCustomUserInfoDefinitions_CustomUserInfoDefinition(), this.getCustomUserInfoDefinition(), null, "customUserInfoDefinition", null, 0, -1, CustomUserInfoDefinitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(customUserInfoValueEClass, CustomUserInfoValue.class, "CustomUserInfoValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCustomUserInfoValue_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, CustomUserInfoValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCustomUserInfoValue_Value(), theXMLTypePackage.getString(), "value", null, 1, 1, CustomUserInfoValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(customUserInfoValuesTypeEClass, CustomUserInfoValuesType.class, "CustomUserInfoValuesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCustomUserInfoValuesType_CustomUserInfoValue(), this.getCustomUserInfoValue(), null, "customUserInfoValue", null, 0, -1, CustomUserInfoValuesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Organization(), this.getOrganization(), null, "organization", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(groupEClass, Group.class, "Group", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGroup_DisplayName(), theXMLTypePackage.getString(), "displayName", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGroup_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGroup_IconName(), theXMLTypePackage.getString(), "iconName", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGroup_IconPath(), theXMLTypePackage.getString(), "iconPath", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGroup_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGroup_ParentPath(), theXMLTypePackage.getString(), "parentPath", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(groupsEClass, Groups.class, "Groups", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGroups_Group(), this.getGroup(), null, "group", null, 0, -1, Groups.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(membershipEClass, Membership.class, "Membership", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMembership_UserName(), theXMLTypePackage.getString(), "userName", null, 0, 1, Membership.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMembership_RoleName(), theXMLTypePackage.getString(), "roleName", null, 1, 1, Membership.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMembership_GroupName(), theXMLTypePackage.getString(), "groupName", null, 1, 1, Membership.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMembership_GroupParentPath(), theXMLTypePackage.getString(), "groupParentPath", null, 0, 1, Membership.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMembership_AssignedBy(), theXMLTypePackage.getString(), "assignedBy", null, 0, 1, Membership.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMembership_AssignedDate(), theXMLTypePackage.getLong(), "assignedDate", null, 0, 1, Membership.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(membershipsEClass, Memberships.class, "Memberships", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMemberships_Membership(), this.getMembership(), null, "membership", null, 0, -1, Memberships.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(metadataEClass, Metadata.class, "Metadata", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMetadata_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Metadata.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMetadata_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, Metadata.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(metaDatasTypeEClass, MetaDatasType.class, "MetaDatasType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMetaDatasType_MetaData(), this.getMetadata(), null, "metaData", null, 0, -1, MetaDatasType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(organizationEClass, Organization.class, "Organization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getOrganization_CustomUserInfoDefinitions(), this.getCustomUserInfoDefinitions(), null, "customUserInfoDefinitions", null, 0, 1, Organization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getOrganization_Users(), this.getUsers(), null, "users", null, 0, 1, Organization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getOrganization_Roles(), this.getRoles(), null, "roles", null, 0, 1, Organization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getOrganization_Groups(), this.getGroups(), null, "groups", null, 0, 1, Organization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getOrganization_Memberships(), this.getMemberships(), null, "memberships", null, 0, 1, Organization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getOrganization_Name(), ecorePackage.getEString(), "name", null, 0, 1, Organization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getOrganization_Description(), ecorePackage.getEString(), "description", null, 0, 1, Organization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getOrganization_ModelVersion(), ecorePackage.getEString(), "modelVersion", null, 0, 1, Organization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(roleEClass, Role.class, "Role", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRole_DisplayName(), theXMLTypePackage.getString(), "displayName", null, 0, 1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRole_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRole_IconName(), theXMLTypePackage.getString(), "iconName", null, 0, 1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRole_IconPath(), theXMLTypePackage.getString(), "iconPath", null, 0, 1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRole_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(rolesEClass, Roles.class, "Roles", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRoles_Role(), this.getRole(), null, "role", null, 0, -1, Roles.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(userEClass, User.class, "User", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUser_FirstName(), theXMLTypePackage.getString(), "firstName", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_LastName(), theXMLTypePackage.getString(), "lastName", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_IconName(), theXMLTypePackage.getString(), "iconName", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_IconPath(), theXMLTypePackage.getString(), "iconPath", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_Title(), theXMLTypePackage.getString(), "title", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_JobTitle(), theXMLTypePackage.getString(), "jobTitle", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_Manager(), theXMLTypePackage.getString(), "manager", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_PersonalData(), this.getContactData(), null, "personalData", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_ProfessionalData(), this.getContactData(), null, "professionalData", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_MetaDatas(), this.getMetaDatasType(), null, "metaDatas", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_UserName(), theXMLTypePackage.getString(), "userName", null, 1, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUser_Enabled(), theXMLTypePackage.getBoolean(), "enabled", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_Password(), this.getPasswordType(), null, "password", null, 1, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUser_CustomUserInfoValues(), this.getCustomUserInfoValuesType(), null, "customUserInfoValues", null, 0, 1, User.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(usersEClass, Users.class, "Users", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getUsers_User(), this.getUser(), null, "user", null, 1, -1, Users.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(passwordTypeEClass, PasswordType.class, "PasswordType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPasswordType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, PasswordType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPasswordType_Encrypted(), theXMLTypePackage.getBoolean(), "encrypted", "true", 1, 1, PasswordType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

	/**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
        addAnnotation
          (contactDataEClass,
           source,
           new String[] {
               "name", "ContactData",
               "kind", "elementOnly"
           });
        addAnnotation
          (getContactData_Email(),
           source,
           new String[] {
               "kind", "element",
               "name", "email"
           });
        addAnnotation
          (getContactData_PhoneNumber(),
           source,
           new String[] {
               "kind", "element",
               "name", "phoneNumber"
           });
        addAnnotation
          (getContactData_MobileNumber(),
           source,
           new String[] {
               "kind", "element",
               "name", "mobileNumber"
           });
        addAnnotation
          (getContactData_FaxNumber(),
           source,
           new String[] {
               "kind", "element",
               "name", "faxNumber"
           });
        addAnnotation
          (getContactData_Building(),
           source,
           new String[] {
               "kind", "element",
               "name", "building"
           });
        addAnnotation
          (getContactData_Room(),
           source,
           new String[] {
               "kind", "element",
               "name", "room"
           });
        addAnnotation
          (getContactData_Address(),
           source,
           new String[] {
               "kind", "element",
               "name", "address"
           });
        addAnnotation
          (getContactData_ZipCode(),
           source,
           new String[] {
               "kind", "element",
               "name", "zipCode"
           });
        addAnnotation
          (getContactData_City(),
           source,
           new String[] {
               "kind", "element",
               "name", "city"
           });
        addAnnotation
          (getContactData_State(),
           source,
           new String[] {
               "kind", "element",
               "name", "state"
           });
        addAnnotation
          (getContactData_Country(),
           source,
           new String[] {
               "kind", "element",
               "name", "country"
           });
        addAnnotation
          (getContactData_Website(),
           source,
           new String[] {
               "kind", "element",
               "name", "website"
           });
        addAnnotation
          (customUserInfoDefinitionEClass,
           source,
           new String[] {
               "name", "CustomUserInfoDefinition",
               "kind", "elementOnly"
           });
        addAnnotation
          (getCustomUserInfoDefinition_Name(),
           source,
           new String[] {
               "kind", "element",
               "name", "name"
           });
        addAnnotation
          (getCustomUserInfoDefinition_Description(),
           source,
           new String[] {
               "kind", "element",
               "name", "description"
           });
        addAnnotation
          (customUserInfoDefinitionsEClass,
           source,
           new String[] {
               "name", "CustomUserInfoDefinitions",
               "kind", "elementOnly"
           });
        addAnnotation
          (getCustomUserInfoDefinitions_CustomUserInfoDefinition(),
           source,
           new String[] {
               "kind", "element",
               "name", "customUserInfoDefinition"
           });
        addAnnotation
          (customUserInfoValueEClass,
           source,
           new String[] {
               "name", "CustomUserInfoValue",
               "kind", "elementOnly"
           });
        addAnnotation
          (getCustomUserInfoValue_Name(),
           source,
           new String[] {
               "kind", "element",
               "name", "name"
           });
        addAnnotation
          (getCustomUserInfoValue_Value(),
           source,
           new String[] {
               "kind", "element",
               "name", "value"
           });
        addAnnotation
          (customUserInfoValuesTypeEClass,
           source,
           new String[] {
               "name", "customUserInfoValues_._type",
               "kind", "elementOnly"
           });
        addAnnotation
          (getCustomUserInfoValuesType_CustomUserInfoValue(),
           source,
           new String[] {
               "kind", "element",
               "name", "customUserInfoValue"
           });
        addAnnotation
          (documentRootEClass,
           source,
           new String[] {
               "name", "",
               "kind", "mixed"
           });
        addAnnotation
          (getDocumentRoot_Mixed(),
           source,
           new String[] {
               "kind", "elementWildcard",
               "name", ":mixed"
           });
        addAnnotation
          (getDocumentRoot_XMLNSPrefixMap(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "xmlns:prefix"
           });
        addAnnotation
          (getDocumentRoot_XSISchemaLocation(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "xsi:schemaLocation"
           });
        addAnnotation
          (getDocumentRoot_Organization(),
           source,
           new String[] {
               "kind", "element",
               "name", "Organization",
               "namespace", "##targetNamespace"
           });
        addAnnotation
          (groupEClass,
           source,
           new String[] {
               "name", "Group",
               "kind", "elementOnly"
           });
        addAnnotation
          (getGroup_DisplayName(),
           source,
           new String[] {
               "kind", "element",
               "name", "displayName"
           });
        addAnnotation
          (getGroup_Description(),
           source,
           new String[] {
               "kind", "element",
               "name", "description"
           });
        addAnnotation
          (getGroup_IconName(),
           source,
           new String[] {
               "kind", "element",
               "name", "iconName"
           });
        addAnnotation
          (getGroup_IconPath(),
           source,
           new String[] {
               "kind", "element",
               "name", "iconPath"
           });
        addAnnotation
          (getGroup_Name(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "name"
           });
        addAnnotation
          (getGroup_ParentPath(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "parentPath"
           });
        addAnnotation
          (groupsEClass,
           source,
           new String[] {
               "name", "Groups",
               "kind", "elementOnly"
           });
        addAnnotation
          (getGroups_Group(),
           source,
           new String[] {
               "kind", "element",
               "name", "group"
           });
        addAnnotation
          (membershipEClass,
           source,
           new String[] {
               "name", "Membership",
               "kind", "elementOnly"
           });
        addAnnotation
          (getMembership_UserName(),
           source,
           new String[] {
               "kind", "element",
               "name", "userName"
           });
        addAnnotation
          (getMembership_RoleName(),
           source,
           new String[] {
               "kind", "element",
               "name", "roleName"
           });
        addAnnotation
          (getMembership_GroupName(),
           source,
           new String[] {
               "kind", "element",
               "name", "groupName"
           });
        addAnnotation
          (getMembership_GroupParentPath(),
           source,
           new String[] {
               "kind", "element",
               "name", "groupParentPath"
           });
        addAnnotation
          (getMembership_AssignedBy(),
           source,
           new String[] {
               "kind", "element",
               "name", "assignedBy"
           });
        addAnnotation
          (getMembership_AssignedDate(),
           source,
           new String[] {
               "kind", "element",
               "name", "assignedDate"
           });
        addAnnotation
          (membershipsEClass,
           source,
           new String[] {
               "name", "Memberships",
               "kind", "elementOnly"
           });
        addAnnotation
          (getMemberships_Membership(),
           source,
           new String[] {
               "kind", "element",
               "name", "membership"
           });
        addAnnotation
          (metadataEClass,
           source,
           new String[] {
               "name", "Metadata",
               "kind", "empty"
           });
        addAnnotation
          (getMetadata_Name(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "name"
           });
        addAnnotation
          (getMetadata_Value(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "value"
           });
        addAnnotation
          (metaDatasTypeEClass,
           source,
           new String[] {
               "name", "metaDatas_._type",
               "kind", "elementOnly"
           });
        addAnnotation
          (getMetaDatasType_MetaData(),
           source,
           new String[] {
               "kind", "element",
               "name", "metaData"
           });
        addAnnotation
          (organizationEClass,
           source,
           new String[] {
               "name", "Organization",
               "kind", "elementOnly"
           });
        addAnnotation
          (getOrganization_CustomUserInfoDefinitions(),
           source,
           new String[] {
               "kind", "element",
               "name", "customUserInfoDefinitions"
           });
        addAnnotation
          (getOrganization_Users(),
           source,
           new String[] {
               "kind", "element",
               "name", "users"
           });
        addAnnotation
          (getOrganization_Roles(),
           source,
           new String[] {
               "kind", "element",
               "name", "roles"
           });
        addAnnotation
          (getOrganization_Groups(),
           source,
           new String[] {
               "kind", "element",
               "name", "groups"
           });
        addAnnotation
          (getOrganization_Memberships(),
           source,
           new String[] {
               "kind", "element",
               "name", "memberships"
           });
        addAnnotation
          (roleEClass,
           source,
           new String[] {
               "name", "Role",
               "kind", "elementOnly"
           });
        addAnnotation
          (getRole_DisplayName(),
           source,
           new String[] {
               "kind", "element",
               "name", "displayName"
           });
        addAnnotation
          (getRole_Description(),
           source,
           new String[] {
               "kind", "element",
               "name", "description"
           });
        addAnnotation
          (getRole_IconName(),
           source,
           new String[] {
               "kind", "element",
               "name", "iconName"
           });
        addAnnotation
          (getRole_IconPath(),
           source,
           new String[] {
               "kind", "element",
               "name", "iconPath"
           });
        addAnnotation
          (getRole_Name(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "name"
           });
        addAnnotation
          (rolesEClass,
           source,
           new String[] {
               "name", "Roles",
               "kind", "elementOnly"
           });
        addAnnotation
          (getRoles_Role(),
           source,
           new String[] {
               "kind", "element",
               "name", "role"
           });
        addAnnotation
          (userEClass,
           source,
           new String[] {
               "name", "User",
               "kind", "elementOnly"
           });
        addAnnotation
          (getUser_FirstName(),
           source,
           new String[] {
               "kind", "element",
               "name", "firstName"
           });
        addAnnotation
          (getUser_LastName(),
           source,
           new String[] {
               "kind", "element",
               "name", "lastName"
           });
        addAnnotation
          (getUser_IconName(),
           source,
           new String[] {
               "kind", "element",
               "name", "iconName"
           });
        addAnnotation
          (getUser_IconPath(),
           source,
           new String[] {
               "kind", "element",
               "name", "iconPath"
           });
        addAnnotation
          (getUser_Title(),
           source,
           new String[] {
               "kind", "element",
               "name", "title"
           });
        addAnnotation
          (getUser_JobTitle(),
           source,
           new String[] {
               "kind", "element",
               "name", "jobTitle"
           });
        addAnnotation
          (getUser_Manager(),
           source,
           new String[] {
               "kind", "element",
               "name", "manager"
           });
        addAnnotation
          (getUser_PersonalData(),
           source,
           new String[] {
               "kind", "element",
               "name", "personalData"
           });
        addAnnotation
          (getUser_ProfessionalData(),
           source,
           new String[] {
               "kind", "element",
               "name", "professionalData"
           });
        addAnnotation
          (getUser_MetaDatas(),
           source,
           new String[] {
               "kind", "element",
               "name", "metaDatas"
           });
        addAnnotation
          (getUser_UserName(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "userName"
           });
        addAnnotation
          (getUser_Enabled(),
           source,
           new String[] {
               "kind", "element",
               "name", "enabled"
           });
        addAnnotation
          (getUser_Password(),
           source,
           new String[] {
               "kind", "element",
               "name", "password"
           });
        addAnnotation
          (getUser_CustomUserInfoValues(),
           source,
           new String[] {
               "kind", "element",
               "name", "customUserInfoValues"
           });
        addAnnotation
          (usersEClass,
           source,
           new String[] {
               "name", "Users",
               "kind", "elementOnly"
           });
        addAnnotation
          (getUsers_User(),
           source,
           new String[] {
               "kind", "element",
               "name", "user"
           });
        addAnnotation
          (passwordTypeEClass,
           source,
           new String[] {
               "name", "password_._type",
               "kind", "simple"
           });
        addAnnotation
          (getPasswordType_Value(),
           source,
           new String[] {
               "kind", "simple",
               "name", ":0"
           });
        addAnnotation
          (getPasswordType_Encrypted(),
           source,
           new String[] {
               "kind", "attribute",
               "name", "encrypted"
           });
    }

} //OrganizationPackageImpl
