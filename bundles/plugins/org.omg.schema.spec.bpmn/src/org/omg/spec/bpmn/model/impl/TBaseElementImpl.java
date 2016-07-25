/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TBaseElement;
import org.omg.spec.bpmn.model.TDocumentation;
import org.omg.spec.bpmn.model.TExtensionElements;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TBase Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TBaseElementImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TBaseElementImpl#getExtensionElements <em>Extension Elements</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TBaseElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TBaseElementImpl#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TBaseElementImpl extends EObjectImpl implements TBaseElement {
	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected EList<TDocumentation> documentation;

	/**
	 * The cached value of the '{@link #getExtensionElements() <em>Extension Elements</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtensionElements()
	 * @generated
	 * @ordered
	 */
	protected TExtensionElements extensionElements;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAnyAttribute() <em>Any Attribute</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnyAttribute()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap anyAttribute;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TBaseElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TBASE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TDocumentation> getDocumentation() {
		if (documentation == null) {
			documentation = new EObjectContainmentEList<TDocumentation>(TDocumentation.class, this, ModelPackage.TBASE_ELEMENT__DOCUMENTATION);
		}
		return documentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExtensionElements getExtensionElements() {
		return extensionElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExtensionElements(TExtensionElements newExtensionElements, NotificationChain msgs) {
		TExtensionElements oldExtensionElements = extensionElements;
		extensionElements = newExtensionElements;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TBASE_ELEMENT__EXTENSION_ELEMENTS, oldExtensionElements, newExtensionElements);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtensionElements(TExtensionElements newExtensionElements) {
		if (newExtensionElements != extensionElements) {
			NotificationChain msgs = null;
			if (extensionElements != null)
				msgs = ((InternalEObject)extensionElements).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TBASE_ELEMENT__EXTENSION_ELEMENTS, null, msgs);
			if (newExtensionElements != null)
				msgs = ((InternalEObject)newExtensionElements).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TBASE_ELEMENT__EXTENSION_ELEMENTS, null, msgs);
			msgs = basicSetExtensionElements(newExtensionElements, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TBASE_ELEMENT__EXTENSION_ELEMENTS, newExtensionElements, newExtensionElements));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TBASE_ELEMENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getAnyAttribute() {
		if (anyAttribute == null) {
			anyAttribute = new BasicFeatureMap(this, ModelPackage.TBASE_ELEMENT__ANY_ATTRIBUTE);
		}
		return anyAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TBASE_ELEMENT__DOCUMENTATION:
				return ((InternalEList<?>)getDocumentation()).basicRemove(otherEnd, msgs);
			case ModelPackage.TBASE_ELEMENT__EXTENSION_ELEMENTS:
				return basicSetExtensionElements(null, msgs);
			case ModelPackage.TBASE_ELEMENT__ANY_ATTRIBUTE:
				return ((InternalEList<?>)getAnyAttribute()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.TBASE_ELEMENT__DOCUMENTATION:
				return getDocumentation();
			case ModelPackage.TBASE_ELEMENT__EXTENSION_ELEMENTS:
				return getExtensionElements();
			case ModelPackage.TBASE_ELEMENT__ID:
				return getId();
			case ModelPackage.TBASE_ELEMENT__ANY_ATTRIBUTE:
				if (coreType) return getAnyAttribute();
				return ((FeatureMap.Internal)getAnyAttribute()).getWrapper();
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
			case ModelPackage.TBASE_ELEMENT__DOCUMENTATION:
				getDocumentation().clear();
				getDocumentation().addAll((Collection<? extends TDocumentation>)newValue);
				return;
			case ModelPackage.TBASE_ELEMENT__EXTENSION_ELEMENTS:
				setExtensionElements((TExtensionElements)newValue);
				return;
			case ModelPackage.TBASE_ELEMENT__ID:
				setId((String)newValue);
				return;
			case ModelPackage.TBASE_ELEMENT__ANY_ATTRIBUTE:
				((FeatureMap.Internal)getAnyAttribute()).set(newValue);
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
			case ModelPackage.TBASE_ELEMENT__DOCUMENTATION:
				getDocumentation().clear();
				return;
			case ModelPackage.TBASE_ELEMENT__EXTENSION_ELEMENTS:
				setExtensionElements((TExtensionElements)null);
				return;
			case ModelPackage.TBASE_ELEMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case ModelPackage.TBASE_ELEMENT__ANY_ATTRIBUTE:
				getAnyAttribute().clear();
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
			case ModelPackage.TBASE_ELEMENT__DOCUMENTATION:
				return documentation != null && !documentation.isEmpty();
			case ModelPackage.TBASE_ELEMENT__EXTENSION_ELEMENTS:
				return extensionElements != null;
			case ModelPackage.TBASE_ELEMENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ModelPackage.TBASE_ELEMENT__ANY_ATTRIBUTE:
				return anyAttribute != null && !anyAttribute.isEmpty();
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
		result.append(" (id: ");
		result.append(id);
		result.append(", anyAttribute: ");
		result.append(anyAttribute);
		result.append(')');
		return result.toString();
	}

} //TBaseElementImpl
