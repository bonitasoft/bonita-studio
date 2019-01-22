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
import org.bonitasoft.studio.model.form.ListFormField;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.form.ListFormField} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ListFormFieldItemProvider extends MultipleValuatedFormFieldItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListFormFieldItemProvider(AdapterFactory adapterFactory) {
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

			addMaxHeigthPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Max Heigth feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMaxHeigthPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ListFormField_maxHeigth_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ListFormField_maxHeigth_feature", "_UI_ListFormField_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.LIST_FORM_FIELD__MAX_HEIGTH,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns ListFormField.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ListFormField")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ListFormField)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ListFormField_type") : //$NON-NLS-1$
			getString("_UI_ListFormField_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(ListFormField.class)) {
			case FormPackage.LIST_FORM_FIELD__MAX_HEIGTH:
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
			childFeature == FormPackage.Literals.WIDGET__DEPEND_ON ||
			childFeature == FormPackage.Literals.WIDGET__PARENT_OF ||
			childFeature == FormPackage.Literals.WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION ||
			childFeature == FormPackage.Literals.WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT ||
			childFeature == FormPackage.Literals.WIDGET__INPUT_EXPRESSION ||
			childFeature == FormPackage.Literals.WIDGET__AFTER_EVENT_EXPRESSION ||
			childFeature == FormPackage.Literals.WIDGET__TOOLTIP ||
			childFeature == FormPackage.Literals.WIDGET__HELP_MESSAGE ||
			childFeature == FormPackage.Literals.WIDGET__DISPLAY_LABEL ||
			childFeature == FormPackage.Literals.WIDGET__INJECT_WIDGET_SCRIPT ||
			childFeature == FormPackage.Literals.DUPLICABLE__MAX_NUMBER_OF_DUPLICATION ||
			childFeature == FormPackage.Literals.DUPLICABLE__MIN_NUMBER_OF_DUPLICATION ||
			childFeature == FormPackage.Literals.DUPLICABLE__DISPLAY_LABEL_FOR_ADD ||
			childFeature == FormPackage.Literals.DUPLICABLE__TOOLTIP_FOR_ADD ||
			childFeature == FormPackage.Literals.DUPLICABLE__DISPLAY_LABEL_FOR_REMOVE ||
			childFeature == FormPackage.Literals.DUPLICABLE__TOOLTIP_FOR_REMOVE ||
			childFeature == FormPackage.Literals.FORM_FIELD__EXAMPLE_MESSAGE ||
			childFeature == FormPackage.Literals.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION ||
			childFeature == FormPackage.Literals.MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2", //$NON-NLS-1$
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
