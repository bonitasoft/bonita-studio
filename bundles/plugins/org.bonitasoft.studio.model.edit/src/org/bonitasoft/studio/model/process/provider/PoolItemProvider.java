/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process.provider;

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.model.process.Pool;
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
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.process.Pool} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class PoolItemProvider extends AbstractProcessItemProvider {

    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public PoolItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addContractPropertyDescriptor(object);
            addDisplayNamePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Contract feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addContractPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_ContractContainer_contract_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_ContractContainer_contract_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_ContractContainer_type"), //$NON-NLS-1$
                        ProcessPackage.Literals.CONTRACT_CONTAINER__CONTRACT,
                        true,
                        false,
                        true,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Display Name feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDisplayNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Pool_displayName_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Pool_displayName_feature", "_UI_Pool_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ProcessPackage.Literals.POOL__DISPLAY_NAME,
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
     * 
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(ProcessPackage.Literals.CONTRACT_CONTAINER__CONTRACT);
            childrenFeatures.add(ProcessPackage.Literals.POOL__DOCUMENTS);
            childrenFeatures.add(ProcessPackage.Literals.POOL__SEARCH_INDEXES);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns Pool.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Pool")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((Pool) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_Pool_type") : //$NON-NLS-1$
                getString("_UI_Pool_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Pool.class)) {
            case ProcessPackage.POOL__DISPLAY_NAME:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case ProcessPackage.POOL__DOCUMENTS:
            case ProcessPackage.POOL__SEARCH_INDEXES:
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
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(ProcessPackage.Literals.POOL__DOCUMENTS,
                ProcessFactory.eINSTANCE.createDocument()));

        newChildDescriptors.add(createChildParameter(ProcessPackage.Literals.POOL__SEARCH_INDEXES,
                ProcessFactory.eINSTANCE.createSearchIndex()));
    }

    /**
     * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        Object childFeature = feature;
        Object childObject = child;

        boolean qualify = childFeature == ProcessPackage.Literals.CONTAINER__ELEMENTS ||
                childFeature == ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS ||
                childFeature == ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS ||
                childFeature == ProcessPackage.Literals.PAGE_FLOW__PAGE_FLOW_CONNECTORS ||
                childFeature == ProcessPackage.Literals.RECAP_FLOW__RECAP_FLOW_CONNECTORS ||
                childFeature == ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS ||
                childFeature == ProcessPackage.Literals.ABSTRACT_PROCESS__CONNECTIONS ||
                childFeature == ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES ||
                childFeature == ProcessPackage.Literals.DATA_AWARE__DATA ||
                childFeature == ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA ||
                childFeature == ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA ||
                childFeature == ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA ||
                childFeature == ProcessPackage.Literals.POOL__DOCUMENTS ||
                childFeature == ProcessPackage.Literals.PAGE_FLOW__FORM ||
                childFeature == ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS ||
                childFeature == ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM ||
                childFeature == ProcessPackage.Literals.CONNECTABLE_ELEMENT__KPIS ||
                childFeature == ProcessPackage.Literals.RESOURCE_CONTAINER__HTML_TEMPLATE ||
                childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE ||
                childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE ||
                childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE ||
                childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE ||
                childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE ||
                childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE ||
                childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE ||
                childFeature == ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS ||
                childFeature == ProcessPackage.Literals.PAGE_FLOW__PAGE_FLOW_TRANSITIONS ||
                childFeature == ProcessPackage.Literals.RECAP_FLOW__RECAP_PAGE_FLOW_TRANSITIONS ||
                childFeature == ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TRANSITIONS ||
                childFeature == ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING ||
                childFeature == ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING;

        if (qualify) {
            return getString("_UI_CreateChild_text2", //$NON-NLS-1$
                    new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

}
