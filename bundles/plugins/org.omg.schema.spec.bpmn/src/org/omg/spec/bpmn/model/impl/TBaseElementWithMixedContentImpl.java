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
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TBaseElementWithMixedContent;
import org.omg.spec.bpmn.model.TDocumentation;
import org.omg.spec.bpmn.model.TExtensionElements;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TBase Element With Mixed Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TBaseElementWithMixedContentImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TBaseElementWithMixedContentImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TBaseElementWithMixedContentImpl#getExtensionElements <em>Extension Elements</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TBaseElementWithMixedContentImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TBaseElementWithMixedContentImpl#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TBaseElementWithMixedContentImpl extends EObjectImpl implements TBaseElementWithMixedContent {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

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
	protected TBaseElementWithMixedContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TBASE_ELEMENT_WITH_MIXED_CONTENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TDocumentation> getDocumentation() {
		return getMixed().list(ModelPackage.Literals.TBASE_ELEMENT_WITH_MIXED_CONTENT__DOCUMENTATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExtensionElements getExtensionElements() {
		return (TExtensionElements)getMixed().get(ModelPackage.Literals.TBASE_ELEMENT_WITH_MIXED_CONTENT__EXTENSION_ELEMENTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExtensionElements(TExtensionElements newExtensionElements, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(ModelPackage.Literals.TBASE_ELEMENT_WITH_MIXED_CONTENT__EXTENSION_ELEMENTS, newExtensionElements, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtensionElements(TExtensionElements newExtensionElements) {
		((FeatureMap.Internal)getMixed()).set(ModelPackage.Literals.TBASE_ELEMENT_WITH_MIXED_CONTENT__EXTENSION_ELEMENTS, newExtensionElements);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getAnyAttribute() {
		if (anyAttribute == null) {
			anyAttribute = new BasicFeatureMap(this, ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__ANY_ATTRIBUTE);
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
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__DOCUMENTATION:
				return ((InternalEList<?>)getDocumentation()).basicRemove(otherEnd, msgs);
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__EXTENSION_ELEMENTS:
				return basicSetExtensionElements(null, msgs);
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__ANY_ATTRIBUTE:
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
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__DOCUMENTATION:
				return getDocumentation();
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__EXTENSION_ELEMENTS:
				return getExtensionElements();
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__ID:
				return getId();
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__ANY_ATTRIBUTE:
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
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__DOCUMENTATION:
				getDocumentation().clear();
				getDocumentation().addAll((Collection<? extends TDocumentation>)newValue);
				return;
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__EXTENSION_ELEMENTS:
				setExtensionElements((TExtensionElements)newValue);
				return;
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__ID:
				setId((String)newValue);
				return;
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__ANY_ATTRIBUTE:
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
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__MIXED:
				getMixed().clear();
				return;
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__DOCUMENTATION:
				getDocumentation().clear();
				return;
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__EXTENSION_ELEMENTS:
				setExtensionElements((TExtensionElements)null);
				return;
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__ID:
				setId(ID_EDEFAULT);
				return;
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__ANY_ATTRIBUTE:
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
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__DOCUMENTATION:
				return !getDocumentation().isEmpty();
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__EXTENSION_ELEMENTS:
				return getExtensionElements() != null;
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT__ANY_ATTRIBUTE:
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
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(", id: ");
		result.append(id);
		result.append(", anyAttribute: ");
		result.append(anyAttribute);
		result.append(')');
		return result.toString();
	}

} //TBaseElementWithMixedContentImpl
