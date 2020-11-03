/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.model.actormapping.impl;

import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;
import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.actormapping.DocumentRoot;
import org.bonitasoft.studio.model.actormapping.Groups;
import org.bonitasoft.studio.model.actormapping.Membership;
import org.bonitasoft.studio.model.actormapping.MembershipType;
import org.bonitasoft.studio.model.actormapping.Roles;
import org.bonitasoft.studio.model.actormapping.Users;

import org.bonitasoft.studio.model.configuration.ConfigurationPackage;

import org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl;

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;

import org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationPackageImpl;

import org.bonitasoft.studio.model.expression.ExpressionPackage;

import org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl;

import org.bonitasoft.studio.model.form.FormPackage;

import org.bonitasoft.studio.model.form.impl.FormPackageImpl;

import org.bonitasoft.studio.model.kpi.KpiPackage;

import org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl;

import org.bonitasoft.studio.model.parameter.ParameterPackage;

import org.bonitasoft.studio.model.parameter.impl.ParameterPackageImpl;

import org.bonitasoft.studio.model.process.ProcessPackage;

import org.bonitasoft.studio.model.process.decision.DecisionPackage;

import org.bonitasoft.studio.model.process.decision.impl.DecisionPackageImpl;

import org.bonitasoft.studio.model.process.decision.transitions.TransitionsPackage;

import org.bonitasoft.studio.model.process.decision.transitions.impl.TransitionsPackageImpl;

import org.bonitasoft.studio.model.process.impl.ProcessPackageImpl;

import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl;

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
public class ActorMappingPackageImpl extends EPackageImpl implements ActorMappingPackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass actorMappingEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass actorMappingsTypeEClass = null;

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
	private EClass membershipTypeEClass = null;

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
	private EClass usersEClass = null;

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
     * @see org.bonitasoft.studio.model.actormapping.ActorMappingPackage#eNS_URI
     * @see #init()
     * @generated
     */
	private ActorMappingPackageImpl() {
        super(eNS_URI, ActorMappingFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link ActorMappingPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static ActorMappingPackage init() {
        if (isInited) return (ActorMappingPackage)EPackage.Registry.INSTANCE.getEPackage(ActorMappingPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredActorMappingPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        ActorMappingPackageImpl theActorMappingPackage = registeredActorMappingPackage instanceof ActorMappingPackageImpl ? (ActorMappingPackageImpl)registeredActorMappingPackage : new ActorMappingPackageImpl();

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConfigurationPackage.eNS_URI);
        ConfigurationPackageImpl theConfigurationPackage = (ConfigurationPackageImpl)(registeredPackage instanceof ConfigurationPackageImpl ? registeredPackage : ConfigurationPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConnectorConfigurationPackage.eNS_URI);
        ConnectorConfigurationPackageImpl theConnectorConfigurationPackage = (ConnectorConfigurationPackageImpl)(registeredPackage instanceof ConnectorConfigurationPackageImpl ? registeredPackage : ConnectorConfigurationPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);
        ExpressionPackageImpl theExpressionPackage = (ExpressionPackageImpl)(registeredPackage instanceof ExpressionPackageImpl ? registeredPackage : ExpressionPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(KpiPackage.eNS_URI);
        KpiPackageImpl theKpiPackage = (KpiPackageImpl)(registeredPackage instanceof KpiPackageImpl ? registeredPackage : KpiPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ParameterPackage.eNS_URI);
        ParameterPackageImpl theParameterPackage = (ParameterPackageImpl)(registeredPackage instanceof ParameterPackageImpl ? registeredPackage : ParameterPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ProcessPackage.eNS_URI);
        ProcessPackageImpl theProcessPackage = (ProcessPackageImpl)(registeredPackage instanceof ProcessPackageImpl ? registeredPackage : ProcessPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DecisionPackage.eNS_URI);
        DecisionPackageImpl theDecisionPackage = (DecisionPackageImpl)(registeredPackage instanceof DecisionPackageImpl ? registeredPackage : DecisionPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TransitionsPackage.eNS_URI);
        TransitionsPackageImpl theTransitionsPackage = (TransitionsPackageImpl)(registeredPackage instanceof TransitionsPackageImpl ? registeredPackage : TransitionsPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI);
        FormPackageImpl theFormPackage = (FormPackageImpl)(registeredPackage instanceof FormPackageImpl ? registeredPackage : FormPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SimulationPackage.eNS_URI);
        SimulationPackageImpl theSimulationPackage = (SimulationPackageImpl)(registeredPackage instanceof SimulationPackageImpl ? registeredPackage : SimulationPackage.eINSTANCE);

        // Create package meta-data objects
        theActorMappingPackage.createPackageContents();
        theConfigurationPackage.createPackageContents();
        theConnectorConfigurationPackage.createPackageContents();
        theExpressionPackage.createPackageContents();
        theKpiPackage.createPackageContents();
        theParameterPackage.createPackageContents();
        theProcessPackage.createPackageContents();
        theDecisionPackage.createPackageContents();
        theTransitionsPackage.createPackageContents();
        theFormPackage.createPackageContents();
        theSimulationPackage.createPackageContents();

        // Initialize created meta-data
        theActorMappingPackage.initializePackageContents();
        theConfigurationPackage.initializePackageContents();
        theConnectorConfigurationPackage.initializePackageContents();
        theExpressionPackage.initializePackageContents();
        theKpiPackage.initializePackageContents();
        theParameterPackage.initializePackageContents();
        theProcessPackage.initializePackageContents();
        theDecisionPackage.initializePackageContents();
        theTransitionsPackage.initializePackageContents();
        theFormPackage.initializePackageContents();
        theSimulationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theActorMappingPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ActorMappingPackage.eNS_URI, theActorMappingPackage);
        return theActorMappingPackage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getActorMapping() {
        return actorMappingEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getActorMapping_Name() {
        return (EAttribute)actorMappingEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getActorMapping_Groups() {
        return (EReference)actorMappingEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getActorMapping_Memberships() {
        return (EReference)actorMappingEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getActorMapping_Roles() {
        return (EReference)actorMappingEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getActorMapping_Users() {
        return (EReference)actorMappingEClass.getEStructuralFeatures().get(4);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getActorMappingsType() {
        return actorMappingsTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EReference getActorMappingsType_ActorMapping() {
        return (EReference)actorMappingsTypeEClass.getEStructuralFeatures().get(0);
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
	public EReference getDocumentRoot_ActorMappings() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getGroups_Group() {
        return (EAttribute)groupsEClass.getEStructuralFeatures().get(0);
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
	public EReference getMembership_Membership() {
        return (EReference)membershipEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EClass getMembershipType() {
        return membershipTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMembershipType_Group() {
        return (EAttribute)membershipTypeEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EAttribute getMembershipType_Role() {
        return (EAttribute)membershipTypeEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getRoles_Role() {
        return (EAttribute)rolesEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getUsers_User() {
        return (EAttribute)usersEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ActorMappingFactory getActorMappingFactory() {
        return (ActorMappingFactory)getEFactoryInstance();
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
        actorMappingEClass = createEClass(ACTOR_MAPPING);
        createEAttribute(actorMappingEClass, ACTOR_MAPPING__NAME);
        createEReference(actorMappingEClass, ACTOR_MAPPING__GROUPS);
        createEReference(actorMappingEClass, ACTOR_MAPPING__MEMBERSHIPS);
        createEReference(actorMappingEClass, ACTOR_MAPPING__ROLES);
        createEReference(actorMappingEClass, ACTOR_MAPPING__USERS);

        actorMappingsTypeEClass = createEClass(ACTOR_MAPPINGS_TYPE);
        createEReference(actorMappingsTypeEClass, ACTOR_MAPPINGS_TYPE__ACTOR_MAPPING);

        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ACTOR_MAPPINGS);

        groupsEClass = createEClass(GROUPS);
        createEAttribute(groupsEClass, GROUPS__GROUP);

        membershipEClass = createEClass(MEMBERSHIP);
        createEReference(membershipEClass, MEMBERSHIP__MEMBERSHIP);

        membershipTypeEClass = createEClass(MEMBERSHIP_TYPE);
        createEAttribute(membershipTypeEClass, MEMBERSHIP_TYPE__GROUP);
        createEAttribute(membershipTypeEClass, MEMBERSHIP_TYPE__ROLE);

        rolesEClass = createEClass(ROLES);
        createEAttribute(rolesEClass, ROLES__ROLE);

        usersEClass = createEClass(USERS);
        createEAttribute(usersEClass, USERS__USER);
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
        initEClass(actorMappingEClass, ActorMapping.class, "ActorMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getActorMapping_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, ActorMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getActorMapping_Groups(), this.getGroups(), null, "groups", null, 0, 1, ActorMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getActorMapping_Memberships(), this.getMembership(), null, "memberships", null, 0, 1, ActorMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getActorMapping_Roles(), this.getRoles(), null, "roles", null, 0, 1, ActorMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getActorMapping_Users(), this.getUsers(), null, "users", null, 0, 1, ActorMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(actorMappingsTypeEClass, ActorMappingsType.class, "ActorMappingsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getActorMappingsType_ActorMapping(), this.getActorMapping(), null, "actorMapping", null, 1, -1, ActorMappingsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ActorMappings(), this.getActorMappingsType(), null, "actorMappings", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(groupsEClass, Groups.class, "Groups", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getGroups_Group(), theXMLTypePackage.getString(), "group", null, 0, -1, Groups.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(membershipEClass, Membership.class, "Membership", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getMembership_Membership(), this.getMembershipType(), null, "membership", null, 0, -1, Membership.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(membershipTypeEClass, MembershipType.class, "MembershipType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getMembershipType_Group(), theXMLTypePackage.getString(), "group", null, 1, 1, MembershipType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getMembershipType_Role(), theXMLTypePackage.getString(), "role", null, 1, 1, MembershipType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(rolesEClass, Roles.class, "Roles", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getRoles_Role(), theXMLTypePackage.getString(), "role", null, 0, -1, Roles.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(usersEClass, Users.class, "Users", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getUsers_User(), theXMLTypePackage.getString(), "user", null, 0, -1, Users.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http://www.eclipse.org/edapt
        createEdaptAnnotations();
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

	/**
     * Initializes the annotations for <b>http://www.eclipse.org/edapt</b>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void createEdaptAnnotations() {
        String source = "http://www.eclipse.org/edapt"; //$NON-NLS-1$
        addAnnotation
          (this,
           source,
           new String[] {
               "historyURI", "process.history" //$NON-NLS-1$ //$NON-NLS-2$
           });
    }

	/**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$
        addAnnotation
          (actorMappingEClass,
           source,
           new String[] {
               "name", "actorMapping", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getActorMapping_Name(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "name" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getActorMapping_Groups(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "groups" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getActorMapping_Memberships(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "memberships" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getActorMapping_Roles(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "roles" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getActorMapping_Users(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "users" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (actorMappingsTypeEClass,
           source,
           new String[] {
               "name", "actorMappings_._type", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getActorMappingsType_ActorMapping(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "actorMapping" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (documentRootEClass,
           source,
           new String[] {
               "name", "", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getDocumentRoot_Mixed(),
           source,
           new String[] {
               "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
               "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getDocumentRoot_XMLNSPrefixMap(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "xmlns:prefix" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getDocumentRoot_XSISchemaLocation(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "xsi:schemaLocation" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getDocumentRoot_ActorMappings(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "actorMappings", //$NON-NLS-1$ //$NON-NLS-2$
               "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (groupsEClass,
           source,
           new String[] {
               "name", "Groups", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getGroups_Group(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "group" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (membershipEClass,
           source,
           new String[] {
               "name", "Membership", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getMembership_Membership(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "membership" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (membershipTypeEClass,
           source,
           new String[] {
               "name", "membership_._type", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getMembershipType_Group(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "group" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getMembershipType_Role(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "role" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (rolesEClass,
           source,
           new String[] {
               "name", "Roles", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getRoles_Role(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "role" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (usersEClass,
           source,
           new String[] {
               "name", "Users", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getUsers_User(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "user" //$NON-NLS-1$ //$NON-NLS-2$
           });
    }

} //ActorMappingPackageImpl
