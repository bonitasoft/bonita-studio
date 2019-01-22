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

import org.bonitasoft.studio.model.expression.ExpressionFactory;

import org.bonitasoft.studio.model.form.DynamicTable;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.form.DynamicTable} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DynamicTableItemProvider extends AbstractTableItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicTableItemProvider(AdapterFactory adapterFactory) {
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

			addUseDefaultValidatorPropertyDescriptor(object);
			addBelowPropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addExampleMessagePositionPropertyDescriptor(object);
			addLimitMinNumberOfColumnPropertyDescriptor(object);
			addLimitMinNumberOfRowPropertyDescriptor(object);
			addAllowAddRemoveRowPropertyDescriptor(object);
			addAllowAddRemoveColumnPropertyDescriptor(object);
			addLimitMaxNumberOfColumnPropertyDescriptor(object);
			addLimitMaxNumberOfRowPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Use Default Validator feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUseDefaultValidatorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Validable_useDefaultValidator_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Validable_useDefaultValidator_feature", "_UI_Validable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.VALIDABLE__USE_DEFAULT_VALIDATOR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Below feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBelowPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Validable_below_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_Validable_below_feature", "_UI_Validable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.VALIDABLE__BELOW,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FormField_description_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_FormField_description_feature", "_UI_FormField_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.FORM_FIELD__DESCRIPTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Example Message Position feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addExampleMessagePositionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FormField_exampleMessagePosition_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_FormField_exampleMessagePosition_feature", "_UI_FormField_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.FORM_FIELD__EXAMPLE_MESSAGE_POSITION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Limit Min Number Of Column feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLimitMinNumberOfColumnPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicTable_limitMinNumberOfColumn_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicTable_limitMinNumberOfColumn_feature", "_UI_DynamicTable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_COLUMN,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Limit Min Number Of Row feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLimitMinNumberOfRowPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicTable_limitMinNumberOfRow_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicTable_limitMinNumberOfRow_feature", "_UI_DynamicTable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_ROW,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Allow Add Remove Row feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllowAddRemoveRowPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicTable_allowAddRemoveRow_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicTable_allowAddRemoveRow_feature", "_UI_DynamicTable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Allow Add Remove Column feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllowAddRemoveColumnPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicTable_allowAddRemoveColumn_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicTable_allowAddRemoveColumn_feature", "_UI_DynamicTable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_COLUMN,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Limit Max Number Of Column feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLimitMaxNumberOfColumnPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicTable_limitMaxNumberOfColumn_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicTable_limitMaxNumberOfColumn_feature", "_UI_DynamicTable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_COLUMN,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Limit Max Number Of Row feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLimitMaxNumberOfRowPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DynamicTable_limitMaxNumberOfRow_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_DynamicTable_limitMaxNumberOfRow_feature", "_UI_DynamicTable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_ROW,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(FormPackage.Literals.VALIDABLE__VALIDATORS);
			childrenFeatures.add(FormPackage.Literals.FORM_FIELD__EXAMPLE_MESSAGE);
			childrenFeatures.add(FormPackage.Literals.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN);
			childrenFeatures.add(FormPackage.Literals.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW);
			childrenFeatures.add(FormPackage.Literals.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN);
			childrenFeatures.add(FormPackage.Literals.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns DynamicTable.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/DynamicTable")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((DynamicTable)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_DynamicTable_type") : //$NON-NLS-1$
			getString("_UI_DynamicTable_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(DynamicTable.class)) {
			case FormPackage.DYNAMIC_TABLE__USE_DEFAULT_VALIDATOR:
			case FormPackage.DYNAMIC_TABLE__BELOW:
			case FormPackage.DYNAMIC_TABLE__DESCRIPTION:
			case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE_POSITION:
			case FormPackage.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_COLUMN:
			case FormPackage.DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_ROW:
			case FormPackage.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW:
			case FormPackage.DYNAMIC_TABLE__ALLOW_ADD_REMOVE_COLUMN:
			case FormPackage.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_COLUMN:
			case FormPackage.DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_ROW:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case FormPackage.DYNAMIC_TABLE__VALIDATORS:
			case FormPackage.DYNAMIC_TABLE__EXAMPLE_MESSAGE:
			case FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN:
			case FormPackage.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW:
			case FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN:
			case FormPackage.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

		newChildDescriptors.add
			(createChildParameter
				(FormPackage.Literals.VALIDABLE__VALIDATORS,
				 FormFactory.eINSTANCE.createValidator()));

		newChildDescriptors.add
			(createChildParameter
				(FormPackage.Literals.FORM_FIELD__EXAMPLE_MESSAGE,
				 ExpressionFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(FormPackage.Literals.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN,
				 ExpressionFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(FormPackage.Literals.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW,
				 ExpressionFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(FormPackage.Literals.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN,
				 ExpressionFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(FormPackage.Literals.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW,
				 ExpressionFactory.eINSTANCE.createExpression()));
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
			childFeature == FormPackage.Literals.ABSTRACT_TABLE__HORIZONTAL_HEADER_EXPRESSION ||
			childFeature == FormPackage.Literals.ABSTRACT_TABLE__VERTICAL_HEADER_EXPRESSION ||
			childFeature == FormPackage.Literals.FORM_FIELD__EXAMPLE_MESSAGE ||
			childFeature == FormPackage.Literals.DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN ||
			childFeature == FormPackage.Literals.DYNAMIC_TABLE__MIN_NUMBER_OF_ROW ||
			childFeature == FormPackage.Literals.DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN ||
			childFeature == FormPackage.Literals.DYNAMIC_TABLE__MAX_NUMBER_OF_ROW;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2", //$NON-NLS-1$
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
