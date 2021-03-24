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

import org.bonitasoft.studio.model.edit.ProcessEditPlugin;

import org.bonitasoft.studio.model.expression.ExpressionFactory;

import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;

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
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.process.Document} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DocumentItemProvider 
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
	public DocumentItemProvider(AdapterFactory adapterFactory) {
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

            addDocumentationPropertyDescriptor(object);
            addNamePropertyDescriptor(object);
            addDefaultValueIdOfDocumentStorePropertyDescriptor(object);
            addMimeTypePropertyDescriptor(object);
            addDocumentTypePropertyDescriptor(object);
            addMultiplePropertyDescriptor(object);
            addContractInputPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

	/**
     * This adds a property descriptor for the Documentation feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addDocumentationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Element_documentation_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Element_documentation_feature", "_UI_Element_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.ELEMENT__DOCUMENTATION,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
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
                 getString("_UI_Element_name_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Element_name_feature", "_UI_Element_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.ELEMENT__NAME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Default Value Id Of Document Store feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addDefaultValueIdOfDocumentStorePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Document_defaultValueIdOfDocumentStore_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Document_defaultValueIdOfDocumentStore_feature", "_UI_Document_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Mime Type feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addMimeTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Document_mimeType_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Document_mimeType_feature", "_UI_Document_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.DOCUMENT__MIME_TYPE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Document Type feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addDocumentTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Document_documentType_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Document_documentType_feature", "_UI_Document_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.DOCUMENT__DOCUMENT_TYPE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Multiple feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addMultiplePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Document_multiple_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Document_multiple_feature", "_UI_Document_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.DOCUMENT__MULTIPLE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Contract Input feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addContractInputPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Document_contractInput_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Document_contractInput_feature", "_UI_Document_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.DOCUMENT__CONTRACT_INPUT,
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
            childrenFeatures.add(ProcessPackage.Literals.ELEMENT__TEXT_ANNOTATION_ATTACHMENT);
            childrenFeatures.add(ProcessPackage.Literals.DOCUMENT__URL);
            childrenFeatures.add(ProcessPackage.Literals.DOCUMENT__INITIAL_MULTIPLE_CONTENT);
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
     * This returns Document.gif.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Document")); //$NON-NLS-1$
    }

	/**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getText(Object object) {
        String label = ((Document)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_Document_type") : //$NON-NLS-1$
            getString("_UI_Document_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(Document.class)) {
            case ProcessPackage.DOCUMENT__DOCUMENTATION:
            case ProcessPackage.DOCUMENT__NAME:
            case ProcessPackage.DOCUMENT__DEFAULT_VALUE_ID_OF_DOCUMENT_STORE:
            case ProcessPackage.DOCUMENT__DOCUMENT_TYPE:
            case ProcessPackage.DOCUMENT__MULTIPLE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case ProcessPackage.DOCUMENT__TEXT_ANNOTATION_ATTACHMENT:
            case ProcessPackage.DOCUMENT__URL:
            case ProcessPackage.DOCUMENT__INITIAL_MULTIPLE_CONTENT:
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
                (ProcessPackage.Literals.ELEMENT__TEXT_ANNOTATION_ATTACHMENT,
                 ProcessFactory.eINSTANCE.createTextAnnotationAttachment()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.DOCUMENT__URL,
                 ExpressionFactory.eINSTANCE.createExpression()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.DOCUMENT__INITIAL_MULTIPLE_CONTENT,
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
            childFeature == ProcessPackage.Literals.DOCUMENT__URL ||
            childFeature == ProcessPackage.Literals.DOCUMENT__INITIAL_MULTIPLE_CONTENT;

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
