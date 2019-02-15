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
package org.bonitasoft.studio.model.configuration.provider;


import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;

import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;

import org.bonitasoft.studio.model.edit.ProcessEditPlugin;

import org.bonitasoft.studio.model.parameter.ParameterFactory;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.configuration.Configuration} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ConfigurationItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ConfigurationItemProvider(AdapterFactory adapterFactory) {
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

            addNamePropertyDescriptor(object);
            addDescriptionPropertyDescriptor(object);
            addAnonymousUserNamePropertyDescriptor(object);
            addAnonymousPasswordPropertyDescriptor(object);
            addVersionPropertyDescriptor(object);
            addUsernamePropertyDescriptor(object);
            addPasswordPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

	/**
     * This adds a property descriptor for the Name feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Configuration_name_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Configuration_name_feature", "_UI_Configuration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ConfigurationPackage.Literals.CONFIGURATION__NAME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
                 getString("_UI_Configuration_description_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Configuration_description_feature", "_UI_Configuration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ConfigurationPackage.Literals.CONFIGURATION__DESCRIPTION,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Anonymous User Name feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addAnonymousUserNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Configuration_anonymousUserName_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Configuration_anonymousUserName_feature", "_UI_Configuration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ConfigurationPackage.Literals.CONFIGURATION__ANONYMOUS_USER_NAME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Anonymous Password feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addAnonymousPasswordPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Configuration_anonymousPassword_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Configuration_anonymousPassword_feature", "_UI_Configuration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ConfigurationPackage.Literals.CONFIGURATION__ANONYMOUS_PASSWORD,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
                 getString("_UI_Configuration_version_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Configuration_version_feature", "_UI_Configuration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ConfigurationPackage.Literals.CONFIGURATION__VERSION,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Username feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addUsernamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Configuration_username_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Configuration_username_feature", "_UI_Configuration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ConfigurationPackage.Literals.CONFIGURATION__USERNAME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Password feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addPasswordPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Configuration_password_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Configuration_password_feature", "_UI_Configuration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ConfigurationPackage.Literals.CONFIGURATION__PASSWORD,
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
            childrenFeatures.add(ConfigurationPackage.Literals.CONFIGURATION__ACTOR_MAPPINGS);
            childrenFeatures.add(ConfigurationPackage.Literals.CONFIGURATION__DEFINITION_MAPPINGS);
            childrenFeatures.add(ConfigurationPackage.Literals.CONFIGURATION__PROCESS_DEPENDENCIES);
            childrenFeatures.add(ConfigurationPackage.Literals.CONFIGURATION__APPLICATION_DEPENDENCIES);
            childrenFeatures.add(ConfigurationPackage.Literals.CONFIGURATION__PARAMETERS);
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
     * This returns Configuration.gif.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Configuration")); //$NON-NLS-1$
    }

	/**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getText(Object object) {
        String label = ((Configuration)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_Configuration_type") : //$NON-NLS-1$
            getString("_UI_Configuration_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(Configuration.class)) {
            case ConfigurationPackage.CONFIGURATION__NAME:
            case ConfigurationPackage.CONFIGURATION__DESCRIPTION:
            case ConfigurationPackage.CONFIGURATION__ANONYMOUS_USER_NAME:
            case ConfigurationPackage.CONFIGURATION__ANONYMOUS_PASSWORD:
            case ConfigurationPackage.CONFIGURATION__VERSION:
            case ConfigurationPackage.CONFIGURATION__USERNAME:
            case ConfigurationPackage.CONFIGURATION__PASSWORD:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case ConfigurationPackage.CONFIGURATION__ACTOR_MAPPINGS:
            case ConfigurationPackage.CONFIGURATION__DEFINITION_MAPPINGS:
            case ConfigurationPackage.CONFIGURATION__PROCESS_DEPENDENCIES:
            case ConfigurationPackage.CONFIGURATION__APPLICATION_DEPENDENCIES:
            case ConfigurationPackage.CONFIGURATION__PARAMETERS:
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
                (ConfigurationPackage.Literals.CONFIGURATION__ACTOR_MAPPINGS,
                 ActorMappingFactory.eINSTANCE.createActorMappingsType()));

        newChildDescriptors.add
            (createChildParameter
                (ConfigurationPackage.Literals.CONFIGURATION__DEFINITION_MAPPINGS,
                 ConfigurationFactory.eINSTANCE.createDefinitionMapping()));

        newChildDescriptors.add
            (createChildParameter
                (ConfigurationPackage.Literals.CONFIGURATION__PROCESS_DEPENDENCIES,
                 ConfigurationFactory.eINSTANCE.createFragmentContainer()));

        newChildDescriptors.add
            (createChildParameter
                (ConfigurationPackage.Literals.CONFIGURATION__APPLICATION_DEPENDENCIES,
                 ConfigurationFactory.eINSTANCE.createFragmentContainer()));

        newChildDescriptors.add
            (createChildParameter
                (ConfigurationPackage.Literals.CONFIGURATION__PARAMETERS,
                 ParameterFactory.eINSTANCE.createParameter()));
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
            childFeature == ConfigurationPackage.Literals.CONFIGURATION__PROCESS_DEPENDENCIES ||
            childFeature == ConfigurationPackage.Literals.CONFIGURATION__APPLICATION_DEPENDENCIES;

        if (qualify) {
            return getString
                ("_UI_CreateChild_text2", //$NON-NLS-1$
                 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
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
