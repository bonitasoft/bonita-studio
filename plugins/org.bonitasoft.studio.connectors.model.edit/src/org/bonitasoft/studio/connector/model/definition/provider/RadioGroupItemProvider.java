/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.provider;


import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.RadioGroup;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.bonitasoft.studio.connector.model.definition.RadioGroup} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RadioGroupItemProvider
    extends WidgetItemProvider
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
    public RadioGroupItemProvider(AdapterFactory adapterFactory) {
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

			addChoicesPropertyDescriptor(object);
			addOrientationPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

    /**
	 * This adds a property descriptor for the Choices feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addChoicesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RadioGroup_choices_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_RadioGroup_choices_feature", "_UI_RadioGroup_type"),
				 ConnectorDefinitionPackage.Literals.RADIO_GROUP__CHOICES,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This adds a property descriptor for the Orientation feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addOrientationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RadioGroup_orientation_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_RadioGroup_orientation_feature", "_UI_RadioGroup_type"),
				 ConnectorDefinitionPackage.Literals.RADIO_GROUP__ORIENTATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

    /**
	 * This returns RadioGroup.gif.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/RadioGroup"));
	}

    /**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String getText(Object object) {
		String label = ((RadioGroup)object).getId();
		return label == null || label.length() == 0 ?
			getString("_UI_RadioGroup_type") :
			getString("_UI_RadioGroup_type") + " " + label;
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

		switch (notification.getFeatureID(RadioGroup.class)) {
			case ConnectorDefinitionPackage.RADIO_GROUP__CHOICES:
			case ConnectorDefinitionPackage.RADIO_GROUP__ORIENTATION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
	}

}
