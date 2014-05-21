/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TItemDefinition;
import org.omg.spec.bpmn.model.TItemKind;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TItem Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TItemDefinitionImpl#isIsCollection <em>Is Collection</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TItemDefinitionImpl#getItemKind <em>Item Kind</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TItemDefinitionImpl#getStructureRef <em>Structure Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TItemDefinitionImpl extends TRootElementImpl implements TItemDefinition {
	/**
	 * The default value of the '{@link #isIsCollection() <em>Is Collection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCollection()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_COLLECTION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsCollection() <em>Is Collection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCollection()
	 * @generated
	 * @ordered
	 */
	protected boolean isCollection = IS_COLLECTION_EDEFAULT;

	/**
	 * This is true if the Is Collection attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isCollectionESet;

	/**
	 * The default value of the '{@link #getItemKind() <em>Item Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItemKind()
	 * @generated
	 * @ordered
	 */
	protected static final TItemKind ITEM_KIND_EDEFAULT = TItemKind.INFORMATION;

	/**
	 * The cached value of the '{@link #getItemKind() <em>Item Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItemKind()
	 * @generated
	 * @ordered
	 */
	protected TItemKind itemKind = ITEM_KIND_EDEFAULT;

	/**
	 * This is true if the Item Kind attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean itemKindESet;

	/**
	 * The default value of the '{@link #getStructureRef() <em>Structure Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStructureRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName STRUCTURE_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStructureRef() <em>Structure Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStructureRef()
	 * @generated
	 * @ordered
	 */
	protected QName structureRef = STRUCTURE_REF_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TItemDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TITEM_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsCollection() {
		return isCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsCollection(boolean newIsCollection) {
		boolean oldIsCollection = isCollection;
		isCollection = newIsCollection;
		boolean oldIsCollectionESet = isCollectionESet;
		isCollectionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TITEM_DEFINITION__IS_COLLECTION, oldIsCollection, isCollection, !oldIsCollectionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsCollection() {
		boolean oldIsCollection = isCollection;
		boolean oldIsCollectionESet = isCollectionESet;
		isCollection = IS_COLLECTION_EDEFAULT;
		isCollectionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TITEM_DEFINITION__IS_COLLECTION, oldIsCollection, IS_COLLECTION_EDEFAULT, oldIsCollectionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsCollection() {
		return isCollectionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TItemKind getItemKind() {
		return itemKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setItemKind(TItemKind newItemKind) {
		TItemKind oldItemKind = itemKind;
		itemKind = newItemKind == null ? ITEM_KIND_EDEFAULT : newItemKind;
		boolean oldItemKindESet = itemKindESet;
		itemKindESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TITEM_DEFINITION__ITEM_KIND, oldItemKind, itemKind, !oldItemKindESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetItemKind() {
		TItemKind oldItemKind = itemKind;
		boolean oldItemKindESet = itemKindESet;
		itemKind = ITEM_KIND_EDEFAULT;
		itemKindESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TITEM_DEFINITION__ITEM_KIND, oldItemKind, ITEM_KIND_EDEFAULT, oldItemKindESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetItemKind() {
		return itemKindESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getStructureRef() {
		return structureRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStructureRef(QName newStructureRef) {
		QName oldStructureRef = structureRef;
		structureRef = newStructureRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TITEM_DEFINITION__STRUCTURE_REF, oldStructureRef, structureRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TITEM_DEFINITION__IS_COLLECTION:
				return isIsCollection();
			case ModelPackage.TITEM_DEFINITION__ITEM_KIND:
				return getItemKind();
			case ModelPackage.TITEM_DEFINITION__STRUCTURE_REF:
				return getStructureRef();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.TITEM_DEFINITION__IS_COLLECTION:
				setIsCollection((Boolean)newValue);
				return;
			case ModelPackage.TITEM_DEFINITION__ITEM_KIND:
				setItemKind((TItemKind)newValue);
				return;
			case ModelPackage.TITEM_DEFINITION__STRUCTURE_REF:
				setStructureRef((QName)newValue);
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
			case ModelPackage.TITEM_DEFINITION__IS_COLLECTION:
				unsetIsCollection();
				return;
			case ModelPackage.TITEM_DEFINITION__ITEM_KIND:
				unsetItemKind();
				return;
			case ModelPackage.TITEM_DEFINITION__STRUCTURE_REF:
				setStructureRef(STRUCTURE_REF_EDEFAULT);
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
			case ModelPackage.TITEM_DEFINITION__IS_COLLECTION:
				return isSetIsCollection();
			case ModelPackage.TITEM_DEFINITION__ITEM_KIND:
				return isSetItemKind();
			case ModelPackage.TITEM_DEFINITION__STRUCTURE_REF:
				return STRUCTURE_REF_EDEFAULT == null ? structureRef != null : !STRUCTURE_REF_EDEFAULT.equals(structureRef);
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
		result.append(" (isCollection: ");
		if (isCollectionESet) result.append(isCollection); else result.append("<unset>");
		result.append(", itemKind: ");
		if (itemKindESet) result.append(itemKind); else result.append("<unset>");
		result.append(", structureRef: ");
		result.append(structureRef);
		result.append(')');
		return result.toString();
	}

} //TItemDefinitionImpl
