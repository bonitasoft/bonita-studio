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
package org.bonitasoft.studio.model.form.provider;


import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.TextInfo;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.form.TextInfo} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TextInfoItemProvider extends InfoItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextInfoItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addDuplicatePropertyDescriptor(object);
			addLimitNumberOfDuplicationPropertyDescriptor(object);
			addLimitMinNumberOfDuplicationPropertyDescriptor(object);
			addMaxNumberOfDuplicationPropertyDescriptor(object);
			addMinNumberOfDuplicationPropertyDescriptor(object);
			addDisplayLabelForAddPropertyDescriptor(object);
			addTooltipForAddPropertyDescriptor(object);
			addDisplayLabelForRemovePropertyDescriptor(object);
			addTooltipForRemovePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Duplicate feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDuplicatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Duplicable_duplicate_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_duplicate_feature", "_UI_Duplicable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DUPLICABLE__DUPLICATE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Limit Number Of Duplication feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLimitNumberOfDuplicationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Duplicable_limitNumberOfDuplication_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_limitNumberOfDuplication_feature", "_UI_Duplicable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DUPLICABLE__LIMIT_NUMBER_OF_DUPLICATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Limit Min Number Of Duplication feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLimitMinNumberOfDuplicationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Duplicable_limitMinNumberOfDuplication_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_limitMinNumberOfDuplication_feature", "_UI_Duplicable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DUPLICABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Max Number Of Duplication feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMaxNumberOfDuplicationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Duplicable_maxNumberOfDuplication_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_maxNumberOfDuplication_feature", "_UI_Duplicable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DUPLICABLE__MAX_NUMBER_OF_DUPLICATION,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Min Number Of Duplication feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMinNumberOfDuplicationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Duplicable_minNumberOfDuplication_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_minNumberOfDuplication_feature", "_UI_Duplicable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DUPLICABLE__MIN_NUMBER_OF_DUPLICATION,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Display Label For Add feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDisplayLabelForAddPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Duplicable_displayLabelForAdd_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_displayLabelForAdd_feature", "_UI_Duplicable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DUPLICABLE__DISPLAY_LABEL_FOR_ADD,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Tooltip For Add feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTooltipForAddPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Duplicable_tooltipForAdd_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_tooltipForAdd_feature", "_UI_Duplicable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DUPLICABLE__TOOLTIP_FOR_ADD,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Display Label For Remove feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDisplayLabelForRemovePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Duplicable_displayLabelForRemove_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_displayLabelForRemove_feature", "_UI_Duplicable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DUPLICABLE__DISPLAY_LABEL_FOR_REMOVE,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Tooltip For Remove feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTooltipForRemovePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Duplicable_tooltipForRemove_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_tooltipForRemove_feature", "_UI_Duplicable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DUPLICABLE__TOOLTIP_FOR_REMOVE,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This returns TextInfo.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TextInfo")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((TextInfo)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_TextInfo_type") : //$NON-NLS-1$
			getString("_UI_TextInfo_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(TextInfo.class)) {
			case FormPackage.TEXT_INFO__DUPLICATE:
			case FormPackage.TEXT_INFO__LIMIT_NUMBER_OF_DUPLICATION:
			case FormPackage.TEXT_INFO__LIMIT_MIN_NUMBER_OF_DUPLICATION:
			case FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION:
			case FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION:
			case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD:
			case FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD:
			case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE:
			case FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == FormPackage.Literals.WIDGET__INPUT_EXPRESSION ||
			childFeature == FormPackage.Literals.WIDGET__AFTER_EVENT_EXPRESSION;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2", //$NON-NLS-1$
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
