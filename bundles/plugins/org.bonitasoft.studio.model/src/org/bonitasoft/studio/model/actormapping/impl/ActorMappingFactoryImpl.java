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

import org.bonitasoft.studio.model.actormapping.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ActorMappingFactoryImpl extends EFactoryImpl implements ActorMappingFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static ActorMappingFactory init() {
        try {
            ActorMappingFactory theActorMappingFactory = (ActorMappingFactory)EPackage.Registry.INSTANCE.getEFactory(ActorMappingPackage.eNS_URI);
            if (theActorMappingFactory != null) {
                return theActorMappingFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ActorMappingFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ActorMappingFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case ActorMappingPackage.ACTOR_MAPPING: return createActorMapping();
            case ActorMappingPackage.ACTOR_MAPPINGS_TYPE: return createActorMappingsType();
            case ActorMappingPackage.DOCUMENT_ROOT: return createDocumentRoot();
            case ActorMappingPackage.GROUPS: return createGroups();
            case ActorMappingPackage.MEMBERSHIP: return createMembership();
            case ActorMappingPackage.MEMBERSHIP_TYPE: return createMembershipType();
            case ActorMappingPackage.ROLES: return createRoles();
            case ActorMappingPackage.USERS: return createUsers();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ActorMapping createActorMapping() {
        ActorMappingImpl actorMapping = new ActorMappingImpl();
        return actorMapping;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ActorMappingsType createActorMappingsType() {
        ActorMappingsTypeImpl actorMappingsType = new ActorMappingsTypeImpl();
        return actorMappingsType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public DocumentRoot createDocumentRoot() {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Groups createGroups() {
        GroupsImpl groups = new GroupsImpl();
        return groups;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Membership createMembership() {
        MembershipImpl membership = new MembershipImpl();
        return membership;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public MembershipType createMembershipType() {
        MembershipTypeImpl membershipType = new MembershipTypeImpl();
        return membershipType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Roles createRoles() {
        RolesImpl roles = new RolesImpl();
        return roles;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Users createUsers() {
        UsersImpl users = new UsersImpl();
        return users;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ActorMappingPackage getActorMappingPackage() {
        return (ActorMappingPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static ActorMappingPackage getPackage() {
        return ActorMappingPackage.eINSTANCE;
    }

} //ActorMappingFactoryImpl
