/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.provider;


import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;

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
 * This is the item provider adapter for a {@link org.bonitasoft.studio.connector.model.definition.ConnectorDefinition} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorDefinitionItemProvider
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
    public ConnectorDefinitionItemProvider(AdapterFactory adapterFactory) {
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

			addIdPropertyDescriptor(object);
			addVersionPropertyDescriptor(object);
			addIconPropertyDescriptor(object);
			addJarDependencyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

    /**
	 * This adds a property descriptor for the Jar Dependency feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addJarDependencyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ConnectorDefinition_jarDependency_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ConnectorDefinition_jarDependency_feature", "_UI_ConnectorDefinition_type"),
				 ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__JAR_DEPENDENCY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Icon feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addIconPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ConnectorDefinition_icon_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ConnectorDefinition_icon_feature", "_UI_ConnectorDefinition_type"),
				 ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__ICON,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ConnectorDefinition_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ConnectorDefinition_id_feature", "_UI_ConnectorDefinition_type"),
				 ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__ID,
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
				 getString("_UI_ConnectorDefinition_version_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ConnectorDefinition_version_feature", "_UI_ConnectorDefinition_type"),
				 ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__VERSION,
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
			childrenFeatures.add(ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__CATEGORY);
			childrenFeatures.add(ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__INPUT);
			childrenFeatures.add(ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__OUTPUT);
			childrenFeatures.add(ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__PAGE);
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
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean hasChildren(Object object) {
		return hasChildren(object, true);
	}

    /**
	 * This returns ConnectorDefinition.gif.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ConnectorDefinition"));
	}

    /**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String getText(Object object) {
		String label = ((ConnectorDefinition)object).getId();
		return label == null || label.length() == 0 ?
			getString("_UI_ConnectorDefinition_type") :
			getString("_UI_ConnectorDefinition_type") + " " + label;
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

		switch (notification.getFeatureID(ConnectorDefinition.class)) {
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__ID:
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__VERSION:
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__ICON:
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__JAR_DEPENDENCY:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__CATEGORY:
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__INPUT:
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__OUTPUT:
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION__PAGE:
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
				(ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__CATEGORY,
				 ConnectorDefinitionFactory.eINSTANCE.createCategory()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__INPUT,
				 ConnectorDefinitionFactory.eINSTANCE.createInput()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__OUTPUT,
				 ConnectorDefinitionFactory.eINSTANCE.createOutput()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.CONNECTOR_DEFINITION__PAGE,
				 ConnectorDefinitionFactory.eINSTANCE.createPage()));
	}

    /**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public ResourceLocator getResourceLocator() {
		return ConnectorEditPlugin.INSTANCE;
	}

}
