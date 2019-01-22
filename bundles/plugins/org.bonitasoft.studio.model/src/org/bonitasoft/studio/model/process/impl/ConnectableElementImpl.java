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

import org.bonitasoft.studio.model.kpi.AbstractKPIBinding;

import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connectable Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectableElementImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectableElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectableElementImpl#getTextAnnotationAttachment <em>Text Annotation Attachment</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectableElementImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectableElementImpl#getConnectors <em>Connectors</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.ConnectableElementImpl#getKpis <em>Kpis</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectableElementImpl extends EObjectImpl implements ConnectableElement {
	/**
	 * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected String documentation = DOCUMENTATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

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
	 * The cached value of the '{@link #getTextAnnotationAttachment() <em>Text Annotation Attachment</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextAnnotationAttachment()
	 * @generated
	 * @ordered
	 */
	protected EList<TextAnnotationAttachment> textAnnotationAttachment;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectableElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.CONNECTABLE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDocumentation(String newDocumentation) {
		String oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTABLE_ELEMENT__DOCUMENTATION, oldDocumentation, documentation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTABLE_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TextAnnotationAttachment> getTextAnnotationAttachment() {
		if (textAnnotationAttachment == null) {
			textAnnotationAttachment = new EObjectContainmentWithInverseEList<TextAnnotationAttachment>(TextAnnotationAttachment.class, this, ProcessPackage.CONNECTABLE_ELEMENT__TEXT_ANNOTATION_ATTACHMENT, ProcessPackage.TEXT_ANNOTATION_ATTACHMENT__TARGET);
		}
		return textAnnotationAttachment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Data> getData() {
		if (data == null) {
			data = new EObjectContainmentEList<Data>(Data.class, this, ProcessPackage.CONNECTABLE_ELEMENT__DATA);
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
			connectors = new EObjectContainmentEList<Connector>(Connector.class, this, ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS);
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
			kpis = new EObjectContainmentEList<AbstractKPIBinding>(AbstractKPIBinding.class, this, ProcessPackage.CONNECTABLE_ELEMENT__KPIS);
		}
		return kpis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.CONNECTABLE_ELEMENT__TEXT_ANNOTATION_ATTACHMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTextAnnotationAttachment()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.CONNECTABLE_ELEMENT__TEXT_ANNOTATION_ATTACHMENT:
				return ((InternalEList<?>)getTextAnnotationAttachment()).basicRemove(otherEnd, msgs);
			case ProcessPackage.CONNECTABLE_ELEMENT__DATA:
				return ((InternalEList<?>)getData()).basicRemove(otherEnd, msgs);
			case ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS:
				return ((InternalEList<?>)getConnectors()).basicRemove(otherEnd, msgs);
			case ProcessPackage.CONNECTABLE_ELEMENT__KPIS:
				return ((InternalEList<?>)getKpis()).basicRemove(otherEnd, msgs);
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
			case ProcessPackage.CONNECTABLE_ELEMENT__DOCUMENTATION:
				return getDocumentation();
			case ProcessPackage.CONNECTABLE_ELEMENT__NAME:
				return getName();
			case ProcessPackage.CONNECTABLE_ELEMENT__TEXT_ANNOTATION_ATTACHMENT:
				return getTextAnnotationAttachment();
			case ProcessPackage.CONNECTABLE_ELEMENT__DATA:
				return getData();
			case ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS:
				return getConnectors();
			case ProcessPackage.CONNECTABLE_ELEMENT__KPIS:
				return getKpis();
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
			case ProcessPackage.CONNECTABLE_ELEMENT__DOCUMENTATION:
				setDocumentation((String)newValue);
				return;
			case ProcessPackage.CONNECTABLE_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case ProcessPackage.CONNECTABLE_ELEMENT__TEXT_ANNOTATION_ATTACHMENT:
				getTextAnnotationAttachment().clear();
				getTextAnnotationAttachment().addAll((Collection<? extends TextAnnotationAttachment>)newValue);
				return;
			case ProcessPackage.CONNECTABLE_ELEMENT__DATA:
				getData().clear();
				getData().addAll((Collection<? extends Data>)newValue);
				return;
			case ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS:
				getConnectors().clear();
				getConnectors().addAll((Collection<? extends Connector>)newValue);
				return;
			case ProcessPackage.CONNECTABLE_ELEMENT__KPIS:
				getKpis().clear();
				getKpis().addAll((Collection<? extends AbstractKPIBinding>)newValue);
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
			case ProcessPackage.CONNECTABLE_ELEMENT__DOCUMENTATION:
				setDocumentation(DOCUMENTATION_EDEFAULT);
				return;
			case ProcessPackage.CONNECTABLE_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ProcessPackage.CONNECTABLE_ELEMENT__TEXT_ANNOTATION_ATTACHMENT:
				getTextAnnotationAttachment().clear();
				return;
			case ProcessPackage.CONNECTABLE_ELEMENT__DATA:
				getData().clear();
				return;
			case ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS:
				getConnectors().clear();
				return;
			case ProcessPackage.CONNECTABLE_ELEMENT__KPIS:
				getKpis().clear();
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
			case ProcessPackage.CONNECTABLE_ELEMENT__DOCUMENTATION:
				return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
			case ProcessPackage.CONNECTABLE_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ProcessPackage.CONNECTABLE_ELEMENT__TEXT_ANNOTATION_ATTACHMENT:
				return textAnnotationAttachment != null && !textAnnotationAttachment.isEmpty();
			case ProcessPackage.CONNECTABLE_ELEMENT__DATA:
				return data != null && !data.isEmpty();
			case ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS:
				return connectors != null && !connectors.isEmpty();
			case ProcessPackage.CONNECTABLE_ELEMENT__KPIS:
				return kpis != null && !kpis.isEmpty();
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
				case ProcessPackage.CONNECTABLE_ELEMENT__DATA: return ProcessPackage.DATA_AWARE__DATA;
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
				case ProcessPackage.DATA_AWARE__DATA: return ProcessPackage.CONNECTABLE_ELEMENT__DATA;
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
		result.append(" (documentation: "); //$NON-NLS-1$
		result.append(documentation);
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ConnectableElementImpl
