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

import org.bonitasoft.studio.model.kpi.KpiFactory;

import org.bonitasoft.studio.model.parameter.ParameterFactory;

import org.bonitasoft.studio.model.process.AbstractProcess;
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
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.process.AbstractProcess} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractProcessItemProvider extends ContainerItemProvider {
	/**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public AbstractProcessItemProvider(AdapterFactory adapterFactory) {
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

            addVersionPropertyDescriptor(object);
            addAuthorPropertyDescriptor(object);
            addCreationDatePropertyDescriptor(object);
            addModificationDatePropertyDescriptor(object);
            addCategoriesPropertyDescriptor(object);
            addConfigurationsPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
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
                 getString("_UI_AbstractProcess_version_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractProcess_version_feature", "_UI_AbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Author feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addAuthorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractProcess_author_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractProcess_author_feature", "_UI_AbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.ABSTRACT_PROCESS__AUTHOR,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Creation Date feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addCreationDatePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractProcess_creationDate_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractProcess_creationDate_feature", "_UI_AbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.ABSTRACT_PROCESS__CREATION_DATE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Modification Date feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addModificationDatePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractProcess_modificationDate_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractProcess_modificationDate_feature", "_UI_AbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.ABSTRACT_PROCESS__MODIFICATION_DATE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Categories feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addCategoriesPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractProcess_categories_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractProcess_categories_feature", "_UI_AbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.ABSTRACT_PROCESS__CATEGORIES,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Configurations feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addConfigurationsPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractProcess_configurations_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractProcess_configurations_feature", "_UI_AbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.ABSTRACT_PROCESS__CONFIGURATIONS,
                 true,
                 false,
                 true,
                 null,
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
            childrenFeatures.add(ProcessPackage.Literals.DATA_AWARE__DATA);
            childrenFeatures.add(ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS);
            childrenFeatures.add(ProcessPackage.Literals.CONNECTABLE_ELEMENT__KPIS);
            childrenFeatures.add(ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING);
            childrenFeatures.add(ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING);
            childrenFeatures.add(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES);
            childrenFeatures.add(ProcessPackage.Literals.ABSTRACT_PROCESS__CONNECTIONS);
            childrenFeatures.add(ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS);
            childrenFeatures.add(ProcessPackage.Literals.ABSTRACT_PROCESS__PARAMETERS);
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
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getText(Object object) {
        String label = ((AbstractProcess)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_AbstractProcess_type") : //$NON-NLS-1$
            getString("_UI_AbstractProcess_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(AbstractProcess.class)) {
            case ProcessPackage.ABSTRACT_PROCESS__VERSION:
            case ProcessPackage.ABSTRACT_PROCESS__AUTHOR:
            case ProcessPackage.ABSTRACT_PROCESS__CREATION_DATE:
            case ProcessPackage.ABSTRACT_PROCESS__MODIFICATION_DATE:
            case ProcessPackage.ABSTRACT_PROCESS__CATEGORIES:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case ProcessPackage.ABSTRACT_PROCESS__DATA:
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
            case ProcessPackage.ABSTRACT_PROCESS__KPIS:
            case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
            case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
            case ProcessPackage.ABSTRACT_PROCESS__DATATYPES:
            case ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS:
            case ProcessPackage.ABSTRACT_PROCESS__ACTORS:
            case ProcessPackage.ABSTRACT_PROCESS__PARAMETERS:
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
                (ProcessPackage.Literals.DATA_AWARE__DATA,
                 ProcessFactory.eINSTANCE.createData()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.DATA_AWARE__DATA,
                 ProcessFactory.eINSTANCE.createJavaObjectData()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.DATA_AWARE__DATA,
                 ProcessFactory.eINSTANCE.createBusinessObjectData()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.DATA_AWARE__DATA,
                 ProcessFactory.eINSTANCE.createXMLData()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS,
                 ProcessFactory.eINSTANCE.createConnector()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS,
                 ProcessFactory.eINSTANCE.createActorFilter()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONNECTABLE_ELEMENT__KPIS,
                 KpiFactory.eINSTANCE.createDatabaseKPIBinding()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING,
                 ProcessFactory.eINSTANCE.createFormMapping()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING,
                 ProcessFactory.eINSTANCE.createFormMapping()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
                 ProcessFactory.eINSTANCE.createBooleanType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
                 ProcessFactory.eINSTANCE.createBusinessObjectType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
                 ProcessFactory.eINSTANCE.createStringType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
                 ProcessFactory.eINSTANCE.createDateType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
                 ProcessFactory.eINSTANCE.createDoubleType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
                 ProcessFactory.eINSTANCE.createEnumType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
                 ProcessFactory.eINSTANCE.createFloatType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
                 ProcessFactory.eINSTANCE.createIntegerType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
                 ProcessFactory.eINSTANCE.createJavaType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
                 ProcessFactory.eINSTANCE.createLongType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
                 ProcessFactory.eINSTANCE.createXMLType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__CONNECTIONS,
                 ProcessFactory.eINSTANCE.createConnection()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__CONNECTIONS,
                 ProcessFactory.eINSTANCE.createAssociation()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__CONNECTIONS,
                 ProcessFactory.eINSTANCE.createSequenceFlow()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS,
                 ProcessFactory.eINSTANCE.createActor()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.ABSTRACT_PROCESS__PARAMETERS,
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
            childFeature == ProcessPackage.Literals.CONTAINER__ELEMENTS ||
            childFeature == ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS ||
            childFeature == ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS ||
            childFeature == ProcessPackage.Literals.ABSTRACT_PROCESS__CONNECTIONS ||
            childFeature == ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES ||
            childFeature == ProcessPackage.Literals.DATA_AWARE__DATA ||
            childFeature == ProcessPackage.Literals.CONNECTABLE_ELEMENT__KPIS ||
            childFeature == ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING ||
            childFeature == ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING;

        if (qualify) {
            return getString
                ("_UI_CreateChild_text2", //$NON-NLS-1$
                 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

}
