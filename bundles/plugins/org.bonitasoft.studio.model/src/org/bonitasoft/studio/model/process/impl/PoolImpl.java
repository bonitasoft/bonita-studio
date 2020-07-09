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
package org.bonitasoft.studio.model.process.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.process.AdditionalResource;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SearchIndex;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pool</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.PoolImpl#getContract <em>Contract</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.PoolImpl#getDocuments <em>Documents</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.PoolImpl#getSearchIndexes <em>Search Indexes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.PoolImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.PoolImpl#getAdditionalResources <em>Additional Resources</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PoolImpl extends AbstractProcessImpl implements Pool {
	/**
     * The cached value of the '{@link #getContract() <em>Contract</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getContract()
     * @generated
     * @ordered
     */
	protected Contract contract;

	/**
     * The cached value of the '{@link #getDocuments() <em>Documents</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocuments()
     * @generated
     * @ordered
     */
	protected EList<Document> documents;

	/**
     * The cached value of the '{@link #getSearchIndexes() <em>Search Indexes</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSearchIndexes()
     * @generated
     * @ordered
     */
	protected EList<SearchIndex> searchIndexes;

	/**
     * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayName()
     * @generated
     * @ordered
     */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayName()
     * @generated
     * @ordered
     */
	protected String displayName = DISPLAY_NAME_EDEFAULT;

	/**
     * The cached value of the '{@link #getAdditionalResources() <em>Additional Resources</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAdditionalResources()
     * @generated
     * @ordered
     */
    protected EList<AdditionalResource> additionalResources;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected PoolImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ProcessPackage.Literals.POOL;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Contract getContract() {
        return contract;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetContract(Contract newContract, NotificationChain msgs) {
        Contract oldContract = contract;
        contract = newContract;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.POOL__CONTRACT, oldContract, newContract);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setContract(Contract newContract) {
        if (newContract != contract) {
            NotificationChain msgs = null;
            if (contract != null)
                msgs = ((InternalEObject)contract).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.POOL__CONTRACT, null, msgs);
            if (newContract != null)
                msgs = ((InternalEObject)newContract).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.POOL__CONTRACT, null, msgs);
            msgs = basicSetContract(newContract, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.POOL__CONTRACT, newContract, newContract));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Document> getDocuments() {
        if (documents == null) {
            documents = new EObjectContainmentEList<Document>(Document.class, this, ProcessPackage.POOL__DOCUMENTS);
        }
        return documents;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<SearchIndex> getSearchIndexes() {
        if (searchIndexes == null) {
            searchIndexes = new EObjectContainmentEList<SearchIndex>(SearchIndex.class, this, ProcessPackage.POOL__SEARCH_INDEXES);
        }
        return searchIndexes;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getDisplayName() {
        return displayName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDisplayName(String newDisplayName) {
        String oldDisplayName = displayName;
        displayName = newDisplayName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.POOL__DISPLAY_NAME, oldDisplayName, displayName));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<AdditionalResource> getAdditionalResources() {
        if (additionalResources == null) {
            additionalResources = new EObjectContainmentEList<AdditionalResource>(AdditionalResource.class, this, ProcessPackage.POOL__ADDITIONAL_RESOURCES);
        }
        return additionalResources;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ProcessPackage.POOL__CONTRACT:
                return basicSetContract(null, msgs);
            case ProcessPackage.POOL__DOCUMENTS:
                return ((InternalEList<?>)getDocuments()).basicRemove(otherEnd, msgs);
            case ProcessPackage.POOL__SEARCH_INDEXES:
                return ((InternalEList<?>)getSearchIndexes()).basicRemove(otherEnd, msgs);
            case ProcessPackage.POOL__ADDITIONAL_RESOURCES:
                return ((InternalEList<?>)getAdditionalResources()).basicRemove(otherEnd, msgs);
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
            case ProcessPackage.POOL__CONTRACT:
                return getContract();
            case ProcessPackage.POOL__DOCUMENTS:
                return getDocuments();
            case ProcessPackage.POOL__SEARCH_INDEXES:
                return getSearchIndexes();
            case ProcessPackage.POOL__DISPLAY_NAME:
                return getDisplayName();
            case ProcessPackage.POOL__ADDITIONAL_RESOURCES:
                return getAdditionalResources();
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
            case ProcessPackage.POOL__CONTRACT:
                setContract((Contract)newValue);
                return;
            case ProcessPackage.POOL__DOCUMENTS:
                getDocuments().clear();
                getDocuments().addAll((Collection<? extends Document>)newValue);
                return;
            case ProcessPackage.POOL__SEARCH_INDEXES:
                getSearchIndexes().clear();
                getSearchIndexes().addAll((Collection<? extends SearchIndex>)newValue);
                return;
            case ProcessPackage.POOL__DISPLAY_NAME:
                setDisplayName((String)newValue);
                return;
            case ProcessPackage.POOL__ADDITIONAL_RESOURCES:
                getAdditionalResources().clear();
                getAdditionalResources().addAll((Collection<? extends AdditionalResource>)newValue);
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
            case ProcessPackage.POOL__CONTRACT:
                setContract((Contract)null);
                return;
            case ProcessPackage.POOL__DOCUMENTS:
                getDocuments().clear();
                return;
            case ProcessPackage.POOL__SEARCH_INDEXES:
                getSearchIndexes().clear();
                return;
            case ProcessPackage.POOL__DISPLAY_NAME:
                setDisplayName(DISPLAY_NAME_EDEFAULT);
                return;
            case ProcessPackage.POOL__ADDITIONAL_RESOURCES:
                getAdditionalResources().clear();
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
            case ProcessPackage.POOL__CONTRACT:
                return contract != null;
            case ProcessPackage.POOL__DOCUMENTS:
                return documents != null && !documents.isEmpty();
            case ProcessPackage.POOL__SEARCH_INDEXES:
                return searchIndexes != null && !searchIndexes.isEmpty();
            case ProcessPackage.POOL__DISPLAY_NAME:
                return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
            case ProcessPackage.POOL__ADDITIONAL_RESOURCES:
                return additionalResources != null && !additionalResources.isEmpty();
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == ContractContainer.class) {
            switch (derivedFeatureID) {
                case ProcessPackage.POOL__CONTRACT: return ProcessPackage.CONTRACT_CONTAINER__CONTRACT;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == ContractContainer.class) {
            switch (baseFeatureID) {
                case ProcessPackage.CONTRACT_CONTAINER__CONTRACT: return ProcessPackage.POOL__CONTRACT;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (displayName: "); //$NON-NLS-1$
        result.append(displayName);
        result.append(')');
        return result.toString();
    }

} //PoolImpl
