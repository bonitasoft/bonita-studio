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
package org.bonitasoft.studio.model.form.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Operation;

import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.SubmitFormButton;

import org.bonitasoft.studio.model.kpi.AbstractKPIBinding;

import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Submit Form Button</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.SubmitFormButtonImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.SubmitFormButtonImpl#getConnectors <em>Connectors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.SubmitFormButtonImpl#getKpis <em>Kpis</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.SubmitFormButtonImpl#getActions <em>Actions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SubmitFormButtonImpl extends FormButtonImpl implements SubmitFormButton {
	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected EList<Data> data;

	/**
	 * The cached value of the '{@link #getConnectors() <em>Connectors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<Connector> connectors;

	/**
	 * The cached value of the '{@link #getKpis() <em>Kpis</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKpis()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractKPIBinding> kpis;

	/**
	 * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActions()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> actions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubmitFormButtonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.SUBMIT_FORM_BUTTON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Data> getData() {
		if (data == null) {
			data = new EObjectContainmentEList<Data>(Data.class, this, FormPackage.SUBMIT_FORM_BUTTON__DATA);
		}
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Connector> getConnectors() {
		if (connectors == null) {
			connectors = new EObjectContainmentEList<Connector>(Connector.class, this, FormPackage.SUBMIT_FORM_BUTTON__CONNECTORS);
		}
		return connectors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<AbstractKPIBinding> getKpis() {
		if (kpis == null) {
			kpis = new EObjectContainmentEList<AbstractKPIBinding>(AbstractKPIBinding.class, this, FormPackage.SUBMIT_FORM_BUTTON__KPIS);
		}
		return kpis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Operation> getActions() {
		if (actions == null) {
			actions = new EObjectContainmentEList<Operation>(Operation.class, this, FormPackage.SUBMIT_FORM_BUTTON__ACTIONS);
		}
		return actions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FormPackage.SUBMIT_FORM_BUTTON__DATA:
				return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
			case FormPackage.SUBMIT_FORM_BUTTON__CONNECTORS:
				return ((InternalEList<?>)getConnectors()).basicRemove(otherEnd, msgs);
			case FormPackage.SUBMIT_FORM_BUTTON__KPIS:
				return ((InternalEList<?>)getKpis()).basicRemove(otherEnd, msgs);
			case FormPackage.SUBMIT_FORM_BUTTON__ACTIONS:
				return ((InternalEList<?>)getActions()).basicRemove(otherEnd, msgs);
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
			case FormPackage.SUBMIT_FORM_BUTTON__DATA:
				return getData();
			case FormPackage.SUBMIT_FORM_BUTTON__CONNECTORS:
				return getConnectors();
			case FormPackage.SUBMIT_FORM_BUTTON__KPIS:
				return getKpis();
			case FormPackage.SUBMIT_FORM_BUTTON__ACTIONS:
				return getActions();
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
			case FormPackage.SUBMIT_FORM_BUTTON__DATA:
				getData().clear();
				getData().addAll((Collection<? extends Data>)newValue);
				return;
			case FormPackage.SUBMIT_FORM_BUTTON__CONNECTORS:
				getConnectors().clear();
				getConnectors().addAll((Collection<? extends Connector>)newValue);
				return;
			case FormPackage.SUBMIT_FORM_BUTTON__KPIS:
				getKpis().clear();
				getKpis().addAll((Collection<? extends AbstractKPIBinding>)newValue);
				return;
			case FormPackage.SUBMIT_FORM_BUTTON__ACTIONS:
				getActions().clear();
				getActions().addAll((Collection<? extends Operation>)newValue);
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
			case FormPackage.SUBMIT_FORM_BUTTON__DATA:
				getData().clear();
				return;
			case FormPackage.SUBMIT_FORM_BUTTON__CONNECTORS:
				getConnectors().clear();
				return;
			case FormPackage.SUBMIT_FORM_BUTTON__KPIS:
				getKpis().clear();
				return;
			case FormPackage.SUBMIT_FORM_BUTTON__ACTIONS:
				getActions().clear();
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
			case FormPackage.SUBMIT_FORM_BUTTON__DATA:
				return data != null && !data.isEmpty();
			case FormPackage.SUBMIT_FORM_BUTTON__CONNECTORS:
				return connectors != null && !connectors.isEmpty();
			case FormPackage.SUBMIT_FORM_BUTTON__KPIS:
				return kpis != null && !kpis.isEmpty();
			case FormPackage.SUBMIT_FORM_BUTTON__ACTIONS:
				return actions != null && !actions.isEmpty();
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
		if (baseClass == DataAware.class) {
			switch (derivedFeatureID) {
				case FormPackage.SUBMIT_FORM_BUTTON__DATA: return ProcessPackage.DATA_AWARE__DATA;
				default: return -1;
			}
		}
		if (baseClass == ConnectableElement.class) {
			switch (derivedFeatureID) {
				case FormPackage.SUBMIT_FORM_BUTTON__CONNECTORS: return ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS;
				case FormPackage.SUBMIT_FORM_BUTTON__KPIS: return ProcessPackage.CONNECTABLE_ELEMENT__KPIS;
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
		if (baseClass == DataAware.class) {
			switch (baseFeatureID) {
				case ProcessPackage.DATA_AWARE__DATA: return FormPackage.SUBMIT_FORM_BUTTON__DATA;
				default: return -1;
			}
		}
		if (baseClass == ConnectableElement.class) {
			switch (baseFeatureID) {
				case ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS: return FormPackage.SUBMIT_FORM_BUTTON__CONNECTORS;
				case ProcessPackage.CONNECTABLE_ELEMENT__KPIS: return FormPackage.SUBMIT_FORM_BUTTON__KPIS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //SubmitFormButtonImpl
