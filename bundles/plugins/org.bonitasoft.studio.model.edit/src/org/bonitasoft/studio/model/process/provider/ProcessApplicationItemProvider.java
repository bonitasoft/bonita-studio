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
package org.bonitasoft.studio.model.process.provider;


import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.model.form.FormPackage;

import org.bonitasoft.studio.model.process.ProcessApplication;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.process.ProcessApplication} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcessApplicationItemProvider extends ResourceContainerItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessApplicationItemProvider(AdapterFactory adapterFactory) {
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

			addMandatorySymbolPropertyDescriptor(object);
			addMandatoryLabelPropertyDescriptor(object);
			addWelcomePageInternalPropertyDescriptor(object);
			addAutoLoginPropertyDescriptor(object);
			addAutoLoginIdPropertyDescriptor(object);
			addBasedOnLookAndFeelPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Mandatory Symbol feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMandatorySymbolPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MandatoryFieldsCustomization_mandatorySymbol_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_MandatoryFieldsCustomization_mandatorySymbol_feature", "_UI_MandatoryFieldsCustomization_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Mandatory Label feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMandatoryLabelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MandatoryFieldsCustomization_mandatoryLabel_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_MandatoryFieldsCustomization_mandatoryLabel_feature", "_UI_MandatoryFieldsCustomization_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Welcome Page Internal feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWelcomePageInternalPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ProcessApplication_welcomePageInternal_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ProcessApplication_welcomePageInternal_feature", "_UI_ProcessApplication_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Auto Login feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAutoLoginPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ProcessApplication_autoLogin_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ProcessApplication_autoLogin_feature", "_UI_ProcessApplication_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PROCESS_APPLICATION__AUTO_LOGIN,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Auto Login Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAutoLoginIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ProcessApplication_autoLoginId_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ProcessApplication_autoLoginId_feature", "_UI_ProcessApplication_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PROCESS_APPLICATION__AUTO_LOGIN_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Based On Look And Feel feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBasedOnLookAndFeelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ProcessApplication_basedOnLookAndFeel_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ProcessApplication_basedOnLookAndFeel_feature", "_UI_ProcessApplication_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PROCESS_APPLICATION__BASED_ON_LOOK_AND_FEEL,
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
			childrenFeatures.add(FormPackage.Literals.CSS_CUSTOMIZABLE__HTML_ATTRIBUTES);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE);
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
	 * This returns ProcessApplication.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ProcessApplication")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		Boolean labelValue = ((ProcessApplication)object).getWelcomePageInternal();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ?
			getString("_UI_ProcessApplication_type") : //$NON-NLS-1$
			getString("_UI_ProcessApplication_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(ProcessApplication.class)) {
			case ProcessPackage.PROCESS_APPLICATION__MANDATORY_SYMBOL:
			case ProcessPackage.PROCESS_APPLICATION__MANDATORY_LABEL:
			case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL:
			case ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN:
			case ProcessPackage.PROCESS_APPLICATION__AUTO_LOGIN_ID:
			case ProcessPackage.PROCESS_APPLICATION__BASED_ON_LOOK_AND_FEEL:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ProcessPackage.PROCESS_APPLICATION__HTML_ATTRIBUTES:
			case ProcessPackage.PROCESS_APPLICATION__ERROR_TEMPLATE:
			case ProcessPackage.PROCESS_APPLICATION__PROCESS_TEMPLATE:
			case ProcessPackage.PROCESS_APPLICATION__PAGE_TEMPLATE:
			case ProcessPackage.PROCESS_APPLICATION__CONSULTATION_TEMPLATE:
			case ProcessPackage.PROCESS_APPLICATION__LOG_IN_PAGE:
			case ProcessPackage.PROCESS_APPLICATION__WELCOME_PAGE:
			case ProcessPackage.PROCESS_APPLICATION__HOST_PAGE:
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
				(ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));
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
			childFeature == ProcessPackage.Literals.RESOURCE_CONTAINER__HTML_TEMPLATE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE ||
			childFeature == ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2", //$NON-NLS-1$
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
