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

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contract Input</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractInputImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractInputImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractInputImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractInputImpl#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractInputImpl#getMapping <em>Mapping</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractInputImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractInputImpl#getDataReference <em>Data Reference</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ContractInputImpl#isCreateMode <em>Create Mode</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContractInputImpl extends EObjectImpl implements ContractInput {
	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected String name = NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected static final ContractInputType TYPE_EDEFAULT = ContractInputType.TEXT;

	/**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected ContractInputType type = TYPE_EDEFAULT;

	/**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
     * The default value of the '{@link #isMultiple() <em>Multiple</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isMultiple()
     * @generated
     * @ordered
     */
	protected static final boolean MULTIPLE_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isMultiple() <em>Multiple</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isMultiple()
     * @generated
     * @ordered
     */
	protected boolean multiple = MULTIPLE_EDEFAULT;

	/**
     * The cached value of the '{@link #getMapping() <em>Mapping</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMapping()
     * @generated
     * @ordered
     */
	protected ContractInputMapping mapping;

	/**
     * The cached value of the '{@link #getInputs() <em>Inputs</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInputs()
     * @generated
     * @ordered
     */
	protected EList<ContractInput> inputs;

	/**
     * The default value of the '{@link #getDataReference() <em>Data Reference</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataReference()
     * @generated
     * @ordered
     */
    protected static final String DATA_REFERENCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDataReference() <em>Data Reference</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataReference()
     * @generated
     * @ordered
     */
    protected String dataReference = DATA_REFERENCE_EDEFAULT;

    /**
     * The default value of the '{@link #isCreateMode() <em>Create Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCreateMode()
     * @generated
     * @ordered
     */
    protected static final boolean CREATE_MODE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isCreateMode() <em>Create Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCreateMode()
     * @generated
     * @ordered
     */
    protected boolean createMode = CREATE_MODE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ContractInputImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ProcessPackage.Literals.CONTRACT_INPUT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_INPUT__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ContractInputType getType() {
        return type;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setType(ContractInputType newType) {
        ContractInputType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_INPUT__TYPE, oldType, type));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getDescription() {
        return description;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_INPUT__DESCRIPTION, oldDescription, description));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isMultiple() {
        return multiple;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setMultiple(boolean newMultiple) {
        boolean oldMultiple = multiple;
        multiple = newMultiple;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_INPUT__MULTIPLE, oldMultiple, multiple));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ContractInputMapping getMapping() {
        return mapping;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMapping(ContractInputMapping newMapping, NotificationChain msgs) {
        ContractInputMapping oldMapping = mapping;
        mapping = newMapping;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_INPUT__MAPPING, oldMapping, newMapping);
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
	public void setMapping(ContractInputMapping newMapping) {
        if (newMapping != mapping) {
            NotificationChain msgs = null;
            if (mapping != null)
                msgs = ((InternalEObject)mapping).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CONTRACT_INPUT__MAPPING, null, msgs);
            if (newMapping != null)
                msgs = ((InternalEObject)newMapping).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.CONTRACT_INPUT__MAPPING, null, msgs);
            msgs = basicSetMapping(newMapping, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_INPUT__MAPPING, newMapping, newMapping));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<ContractInput> getInputs() {
        if (inputs == null) {
            inputs = new EObjectContainmentEList<ContractInput>(ContractInput.class, this, ProcessPackage.CONTRACT_INPUT__INPUTS);
        }
        return inputs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getDataReference() {
        return dataReference;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDataReference(String newDataReference) {
        String oldDataReference = dataReference;
        dataReference = newDataReference;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_INPUT__DATA_REFERENCE, oldDataReference, dataReference));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isCreateMode() {
        return createMode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCreateMode(boolean newCreateMode) {
        boolean oldCreateMode = createMode;
        createMode = newCreateMode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONTRACT_INPUT__CREATE_MODE, oldCreateMode, createMode));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getJavaType() {
        switch(getType()){
        case BOOLEAN:return java.lang.Boolean.class.getName();
        case DATE: return java.util.Date.class.getName();
        case LOCALDATE: return java.time.LocalDate.class.getName();
        case LOCALDATETIME: return java.time.LocalDateTime.class.getName();
        case OFFSETDATETIME: return java.time.OffsetDateTime.class.getName();
        case INTEGER: return java.lang.Integer.class.getName();
        case LONG: return java.lang.Long.class.getName();
        case DECIMAL: return java.lang.Double.class.getName();
        case FILE:return "org.bonitasoft.engine.bpm.contract.FileInputValue";
        case COMPLEX:return java.util.Map.class.getName();
        case TEXT:
        default: return java.lang.String.class.getName();
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ProcessPackage.CONTRACT_INPUT__MAPPING:
                return basicSetMapping(null, msgs);
            case ProcessPackage.CONTRACT_INPUT__INPUTS:
                return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
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
            case ProcessPackage.CONTRACT_INPUT__NAME:
                return getName();
            case ProcessPackage.CONTRACT_INPUT__TYPE:
                return getType();
            case ProcessPackage.CONTRACT_INPUT__DESCRIPTION:
                return getDescription();
            case ProcessPackage.CONTRACT_INPUT__MULTIPLE:
                return isMultiple();
            case ProcessPackage.CONTRACT_INPUT__MAPPING:
                return getMapping();
            case ProcessPackage.CONTRACT_INPUT__INPUTS:
                return getInputs();
            case ProcessPackage.CONTRACT_INPUT__DATA_REFERENCE:
                return getDataReference();
            case ProcessPackage.CONTRACT_INPUT__CREATE_MODE:
                return isCreateMode();
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
            case ProcessPackage.CONTRACT_INPUT__NAME:
                setName((String)newValue);
                return;
            case ProcessPackage.CONTRACT_INPUT__TYPE:
                setType((ContractInputType)newValue);
                return;
            case ProcessPackage.CONTRACT_INPUT__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case ProcessPackage.CONTRACT_INPUT__MULTIPLE:
                setMultiple((Boolean)newValue);
                return;
            case ProcessPackage.CONTRACT_INPUT__MAPPING:
                setMapping((ContractInputMapping)newValue);
                return;
            case ProcessPackage.CONTRACT_INPUT__INPUTS:
                getInputs().clear();
                getInputs().addAll((Collection<? extends ContractInput>)newValue);
                return;
            case ProcessPackage.CONTRACT_INPUT__DATA_REFERENCE:
                setDataReference((String)newValue);
                return;
            case ProcessPackage.CONTRACT_INPUT__CREATE_MODE:
                setCreateMode((Boolean)newValue);
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
            case ProcessPackage.CONTRACT_INPUT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ProcessPackage.CONTRACT_INPUT__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case ProcessPackage.CONTRACT_INPUT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case ProcessPackage.CONTRACT_INPUT__MULTIPLE:
                setMultiple(MULTIPLE_EDEFAULT);
                return;
            case ProcessPackage.CONTRACT_INPUT__MAPPING:
                setMapping((ContractInputMapping)null);
                return;
            case ProcessPackage.CONTRACT_INPUT__INPUTS:
                getInputs().clear();
                return;
            case ProcessPackage.CONTRACT_INPUT__DATA_REFERENCE:
                setDataReference(DATA_REFERENCE_EDEFAULT);
                return;
            case ProcessPackage.CONTRACT_INPUT__CREATE_MODE:
                setCreateMode(CREATE_MODE_EDEFAULT);
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
            case ProcessPackage.CONTRACT_INPUT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ProcessPackage.CONTRACT_INPUT__TYPE:
                return type != TYPE_EDEFAULT;
            case ProcessPackage.CONTRACT_INPUT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case ProcessPackage.CONTRACT_INPUT__MULTIPLE:
                return multiple != MULTIPLE_EDEFAULT;
            case ProcessPackage.CONTRACT_INPUT__MAPPING:
                return mapping != null;
            case ProcessPackage.CONTRACT_INPUT__INPUTS:
                return inputs != null && !inputs.isEmpty();
            case ProcessPackage.CONTRACT_INPUT__DATA_REFERENCE:
                return DATA_REFERENCE_EDEFAULT == null ? dataReference != null : !DATA_REFERENCE_EDEFAULT.equals(dataReference);
            case ProcessPackage.CONTRACT_INPUT__CREATE_MODE:
                return createMode != CREATE_MODE_EDEFAULT;
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", type: "); //$NON-NLS-1$
        result.append(type);
        result.append(", description: "); //$NON-NLS-1$
        result.append(description);
        result.append(", multiple: "); //$NON-NLS-1$
        result.append(multiple);
        result.append(", dataReference: "); //$NON-NLS-1$
        result.append(dataReference);
        result.append(", createMode: "); //$NON-NLS-1$
        result.append(createMode);
        result.append(')');
        return result.toString();
    }

} //ContractInputImpl
