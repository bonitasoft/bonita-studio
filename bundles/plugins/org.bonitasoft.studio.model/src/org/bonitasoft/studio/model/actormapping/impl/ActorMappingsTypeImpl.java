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

import java.util.Collection;

import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actor Mappings Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.actormapping.impl.ActorMappingsTypeImpl#getActorMapping <em>Actor Mapping</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActorMappingsTypeImpl extends EObjectImpl implements ActorMappingsType {
	/**
     * The cached value of the '{@link #getActorMapping() <em>Actor Mapping</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getActorMapping()
     * @generated
     * @ordered
     */
	protected EList<ActorMapping> actorMapping;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ActorMappingsTypeImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ActorMappingPackage.Literals.ACTOR_MAPPINGS_TYPE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<ActorMapping> getActorMapping() {
        if (actorMapping == null) {
            actorMapping = new EObjectContainmentEList<ActorMapping>(ActorMapping.class, this, ActorMappingPackage.ACTOR_MAPPINGS_TYPE__ACTOR_MAPPING);
        }
        return actorMapping;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ActorMappingPackage.ACTOR_MAPPINGS_TYPE__ACTOR_MAPPING:
                return ((InternalEList<?>)getActorMapping()).basicRemove(otherEnd, msgs);
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
            case ActorMappingPackage.ACTOR_MAPPINGS_TYPE__ACTOR_MAPPING:
                return getActorMapping();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ActorMappingPackage.ACTOR_MAPPINGS_TYPE__ACTOR_MAPPING:
                getActorMapping().clear();
                getActorMapping().addAll((Collection<? extends ActorMapping>)newValue);
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
            case ActorMappingPackage.ACTOR_MAPPINGS_TYPE__ACTOR_MAPPING:
                getActorMapping().clear();
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
            case ActorMappingPackage.ACTOR_MAPPINGS_TYPE__ACTOR_MAPPING:
                return actorMapping != null && !actorMapping.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //ActorMappingsTypeImpl
