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
package org.bonitasoft.studio.model.parameter.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterContext;
import org.bonitasoft.studio.model.parameter.ParameterPackage;

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
 * An implementation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.parameter.impl.ParameterContextImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.parameter.impl.ParameterContextImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.parameter.impl.ParameterContextImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.parameter.impl.ParameterContextImpl#isDefaultContext <em>Default Context</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParameterContextImpl extends EObjectImpl implements ParameterContext {
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
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getParameters()
     * @generated
     * @ordered
     */
	protected EList<Parameter> parameters;

	/**
     * The default value of the '{@link #isDefaultContext() <em>Default Context</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isDefaultContext()
     * @generated
     * @ordered
     */
	protected static final boolean DEFAULT_CONTEXT_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isDefaultContext() <em>Default Context</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isDefaultContext()
     * @generated
     * @ordered
     */
	protected boolean defaultContext = DEFAULT_CONTEXT_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ParameterContextImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ParameterPackage.Literals.PARAMETER_CONTEXT;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ParameterPackage.PARAMETER_CONTEXT__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ParameterPackage.PARAMETER_CONTEXT__DESCRIPTION, oldDescription, description));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Parameter> getParameters() {
        if (parameters == null) {
            parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, ParameterPackage.PARAMETER_CONTEXT__PARAMETERS);
        }
        return parameters;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isDefaultContext() {
        return defaultContext;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDefaultContext(boolean newDefaultContext) {
        boolean oldDefaultContext = defaultContext;
        defaultContext = newDefaultContext;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ParameterPackage.PARAMETER_CONTEXT__DEFAULT_CONTEXT, oldDefaultContext, defaultContext));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ParameterPackage.PARAMETER_CONTEXT__PARAMETERS:
                return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
            case ParameterPackage.PARAMETER_CONTEXT__NAME:
                return getName();
            case ParameterPackage.PARAMETER_CONTEXT__DESCRIPTION:
                return getDescription();
            case ParameterPackage.PARAMETER_CONTEXT__PARAMETERS:
                return getParameters();
            case ParameterPackage.PARAMETER_CONTEXT__DEFAULT_CONTEXT:
                return isDefaultContext();
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
            case ParameterPackage.PARAMETER_CONTEXT__NAME:
                setName((String)newValue);
                return;
            case ParameterPackage.PARAMETER_CONTEXT__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case ParameterPackage.PARAMETER_CONTEXT__PARAMETERS:
                getParameters().clear();
                getParameters().addAll((Collection<? extends Parameter>)newValue);
                return;
            case ParameterPackage.PARAMETER_CONTEXT__DEFAULT_CONTEXT:
                setDefaultContext((Boolean)newValue);
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
            case ParameterPackage.PARAMETER_CONTEXT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ParameterPackage.PARAMETER_CONTEXT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case ParameterPackage.PARAMETER_CONTEXT__PARAMETERS:
                getParameters().clear();
                return;
            case ParameterPackage.PARAMETER_CONTEXT__DEFAULT_CONTEXT:
                setDefaultContext(DEFAULT_CONTEXT_EDEFAULT);
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
            case ParameterPackage.PARAMETER_CONTEXT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ParameterPackage.PARAMETER_CONTEXT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case ParameterPackage.PARAMETER_CONTEXT__PARAMETERS:
                return parameters != null && !parameters.isEmpty();
            case ParameterPackage.PARAMETER_CONTEXT__DEFAULT_CONTEXT:
                return defaultContext != DEFAULT_CONTEXT_EDEFAULT;
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
        result.append(", description: "); //$NON-NLS-1$
        result.append(description);
        result.append(", defaultContext: "); //$NON-NLS-1$
        result.append(defaultContext);
        result.append(')');
        return result.toString();
    }

} //ParameterContextImpl
