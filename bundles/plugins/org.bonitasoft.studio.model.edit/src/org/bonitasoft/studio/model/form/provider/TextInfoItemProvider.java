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

import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.TextInfo;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

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
            childrenFeatures.add(FormPackage.Literals.DUPLICABLE__MAX_NUMBER_OF_DUPLICATION);
            childrenFeatures.add(FormPackage.Literals.DUPLICABLE__MIN_NUMBER_OF_DUPLICATION);
            childrenFeatures.add(FormPackage.Literals.DUPLICABLE__DISPLAY_LABEL_FOR_ADD);
            childrenFeatures.add(FormPackage.Literals.DUPLICABLE__TOOLTIP_FOR_ADD);
            childrenFeatures.add(FormPackage.Literals.DUPLICABLE__DISPLAY_LABEL_FOR_REMOVE);
            childrenFeatures.add(FormPackage.Literals.DUPLICABLE__TOOLTIP_FOR_REMOVE);
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
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case FormPackage.TEXT_INFO__MAX_NUMBER_OF_DUPLICATION:
            case FormPackage.TEXT_INFO__MIN_NUMBER_OF_DUPLICATION:
            case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_ADD:
            case FormPackage.TEXT_INFO__TOOLTIP_FOR_ADD:
            case FormPackage.TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE:
            case FormPackage.TEXT_INFO__TOOLTIP_FOR_REMOVE:
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
                (FormPackage.Literals.DUPLICABLE__MAX_NUMBER_OF_DUPLICATION,
                 ExpressionFactory.eINSTANCE.createExpression()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.DUPLICABLE__MIN_NUMBER_OF_DUPLICATION,
                 ExpressionFactory.eINSTANCE.createExpression()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.DUPLICABLE__DISPLAY_LABEL_FOR_ADD,
                 ExpressionFactory.eINSTANCE.createExpression()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.DUPLICABLE__TOOLTIP_FOR_ADD,
                 ExpressionFactory.eINSTANCE.createExpression()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.DUPLICABLE__DISPLAY_LABEL_FOR_REMOVE,
                 ExpressionFactory.eINSTANCE.createExpression()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.DUPLICABLE__TOOLTIP_FOR_REMOVE,
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
            childFeature == FormPackage.Literals.DUPLICABLE__TOOLTIP_FOR_REMOVE;

        if (qualify) {
            return getString
                ("_UI_CreateChild_text2", //$NON-NLS-1$
                 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

}
