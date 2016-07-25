/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.implementation.provider;


import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.connector.model.definition.provider.ConnectorEditPlugin;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationFactory;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage;
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
 * This is the item provider adapter for a {@link org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorImplementationItemProvider
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
    public ConnectorImplementationItemProvider(AdapterFactory adapterFactory) {
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

			addImplementationIdPropertyDescriptor(object);
			addImplementationVersionPropertyDescriptor(object);
			addDefinitionIdPropertyDescriptor(object);
			addDefinitionVersionPropertyDescriptor(object);
			addImplementationClassnamePropertyDescriptor(object);
			addHasSourcesPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

    /**
	 * This adds a property descriptor for the Definition Id feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addDefinitionIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ConnectorImplementation_definitionId_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ConnectorImplementation_definitionId_feature", "_UI_ConnectorImplementation_type"),
				 ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__DEFINITION_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Definition Version feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addDefinitionVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ConnectorImplementation_definitionVersion_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ConnectorImplementation_definitionVersion_feature", "_UI_ConnectorImplementation_type"),
				 ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Has Sources feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addHasSourcesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ConnectorImplementation_hasSources_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ConnectorImplementation_hasSources_feature", "_UI_ConnectorImplementation_type"),
				 ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__HAS_SOURCES,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Implementation Classname feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addImplementationClassnamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ConnectorImplementation_implementationClassname_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ConnectorImplementation_implementationClassname_feature", "_UI_ConnectorImplementation_type"),
				 ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Implementation Id feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addImplementationIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ConnectorImplementation_implementationId_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ConnectorImplementation_implementationId_feature", "_UI_ConnectorImplementation_type"),
				 ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Implementation Version feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addImplementationVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ConnectorImplementation_implementationVersion_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ConnectorImplementation_implementationVersion_feature", "_UI_ConnectorImplementation_type"),
				 ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION,
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
			childrenFeatures.add(ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__DESCRIPTION);
			childrenFeatures.add(ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES);
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
	 * This returns ConnectorImplementation.gif.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ConnectorImplementation"));
	}

    /**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String getText(Object object) {
		String label = ((ConnectorImplementation)object).getImplementationClassname();
		return label == null || label.length() == 0 ?
			getString("_UI_ConnectorImplementation_type") :
			getString("_UI_ConnectorImplementation_type") + " " + label;
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

		switch (notification.getFeatureID(ConnectorImplementation.class)) {
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID:
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION:
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DEFINITION_ID:
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION:
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME:
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__HAS_SOURCES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__DESCRIPTION:
			case ConnectorImplementationPackage.CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES:
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
				(ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__DESCRIPTION,
				 ""));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorImplementationPackage.Literals.CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES,
				 ConnectorImplementationFactory.eINSTANCE.createJarDependencies()));
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
