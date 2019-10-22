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

import org.bonitasoft.studio.model.edit.ProcessEditPlugin;

import org.bonitasoft.studio.model.expression.ExpressionFactory;

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;

import org.bonitasoft.studio.model.process.provider.ConnectableElementItemProvider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.form.Form} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FormItemProvider extends ConnectableElementItemProvider {
	/**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FormItemProvider(AdapterFactory adapterFactory) {
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
            addNColumnPropertyDescriptor(object);
            addNLinePropertyDescriptor(object);
            addShowPageLabelPropertyDescriptor(object);
            addAllowHTMLInPageLabelPropertyDescriptor(object);
            addVersionPropertyDescriptor(object);
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
     * This adds a property descriptor for the NColumn feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addNColumnPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Form_nColumn_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Form_nColumn_feature", "_UI_Form_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FormPackage.Literals.FORM__NCOLUMN,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the NLine feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addNLinePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Form_nLine_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Form_nLine_feature", "_UI_Form_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FormPackage.Literals.FORM__NLINE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Show Page Label feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addShowPageLabelPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Form_showPageLabel_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Form_showPageLabel_feature", "_UI_Form_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FormPackage.Literals.FORM__SHOW_PAGE_LABEL,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Allow HTML In Page Label feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addAllowHTMLInPageLabelPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Form_allowHTMLInPageLabel_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Form_allowHTMLInPageLabel_feature", "_UI_Form_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FormPackage.Literals.FORM__ALLOW_HTML_IN_PAGE_LABEL,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Version feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addVersionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Form_version_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Form_version_feature", "_UI_Form_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 FormPackage.Literals.FORM__VERSION,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
            childrenFeatures.add(FormPackage.Literals.FORM__STRING_ATTRIBUTES);
            childrenFeatures.add(FormPackage.Literals.FORM__COLUMNS);
            childrenFeatures.add(FormPackage.Literals.FORM__LINES);
            childrenFeatures.add(FormPackage.Literals.FORM__WIDGETS);
            childrenFeatures.add(FormPackage.Literals.FORM__PAGE_LABEL);
            childrenFeatures.add(FormPackage.Literals.FORM__ACTIONS);
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
     * This returns Form.gif.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Form")); //$NON-NLS-1$
    }

	/**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getText(Object object) {
        String label = ((Form)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_Form_type") : //$NON-NLS-1$
            getString("_UI_Form_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(Form.class)) {
            case FormPackage.FORM__USE_DEFAULT_VALIDATOR:
            case FormPackage.FORM__BELOW:
            case FormPackage.FORM__NCOLUMN:
            case FormPackage.FORM__NLINE:
            case FormPackage.FORM__SHOW_PAGE_LABEL:
            case FormPackage.FORM__ALLOW_HTML_IN_PAGE_LABEL:
            case FormPackage.FORM__VERSION:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case FormPackage.FORM__VALIDATORS:
            case FormPackage.FORM__STRING_ATTRIBUTES:
            case FormPackage.FORM__COLUMNS:
            case FormPackage.FORM__LINES:
            case FormPackage.FORM__WIDGETS:
            case FormPackage.FORM__PAGE_LABEL:
            case FormPackage.FORM__ACTIONS:
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
                (FormPackage.Literals.FORM__COLUMNS,
                 FormFactory.eINSTANCE.createColumn()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__LINES,
                 FormFactory.eINSTANCE.createLine()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createGroup()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createCheckBoxMultipleFormField()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createComboFormField()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createDateFormField()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createListFormField()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createPasswordFormField()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createRadioFormField()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createSelectFormField()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createTextFormField()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createTextAreaFormField()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createRichTextAreaFormField()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createFormButton()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createSubmitFormButton()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createPreviousFormButton()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createNextFormButton()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createInfo()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createTextInfo()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createMessageInfo()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createCheckBoxSingleFormField()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createFileWidget()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createImageWidget()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createHiddenWidget()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createDurationFormField()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createTable()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createDynamicTable()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createIFrameWidget()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createHtmlWidget()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__WIDGETS,
                 FormFactory.eINSTANCE.createSuggestBox()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__PAGE_LABEL,
                 ExpressionFactory.eINSTANCE.createExpression()));

        newChildDescriptors.add
            (createChildParameter
                (FormPackage.Literals.FORM__ACTIONS,
                 ExpressionFactory.eINSTANCE.createOperation()));
    }

	/**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ResourceLocator getResourceLocator() {
        return ProcessEditPlugin.INSTANCE;
    }

}
