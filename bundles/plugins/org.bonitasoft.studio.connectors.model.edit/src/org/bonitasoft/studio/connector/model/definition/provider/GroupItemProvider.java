/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.provider;


import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.Group;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

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
 * This is the item provider adapter for a {@link org.bonitasoft.studio.connector.model.definition.Group} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GroupItemProvider
    extends ComponentItemProvider
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
    public GroupItemProvider(AdapterFactory adapterFactory) {
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

			addOptionalPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

    /**
	 * This adds a property descriptor for the Optional feature.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void addOptionalPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Group_optional_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Group_optional_feature", "_UI_Group_type"),
				 ConnectorDefinitionPackage.Literals.GROUP__OPTIONAL,
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
			childrenFeatures.add(ConnectorDefinitionPackage.Literals.GROUP__WIDGET);
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
	 * This returns Group.gif.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Group"));
	}

    /**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String getText(Object object) {
		String label = ((Group)object).getId();
		return label == null || label.length() == 0 ?
			getString("_UI_Group_type") :
			getString("_UI_Group_type") + " " + label;
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

		switch (notification.getFeatureID(Group.class)) {
			case ConnectorDefinitionPackage.GROUP__OPTIONAL:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ConnectorDefinitionPackage.GROUP__WIDGET:
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
				(ConnectorDefinitionPackage.Literals.GROUP__WIDGET,
				 ConnectorDefinitionFactory.eINSTANCE.createWidgetComponent()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.GROUP__WIDGET,
				 ConnectorDefinitionFactory.eINSTANCE.createArray()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.GROUP__WIDGET,
				 ConnectorDefinitionFactory.eINSTANCE.createCheckbox()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.GROUP__WIDGET,
				 ConnectorDefinitionFactory.eINSTANCE.createGroup()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.GROUP__WIDGET,
				 ConnectorDefinitionFactory.eINSTANCE.createList()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.GROUP__WIDGET,
				 ConnectorDefinitionFactory.eINSTANCE.createText()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.GROUP__WIDGET,
				 ConnectorDefinitionFactory.eINSTANCE.createPassword()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.GROUP__WIDGET,
				 ConnectorDefinitionFactory.eINSTANCE.createRadioGroup()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.GROUP__WIDGET,
				 ConnectorDefinitionFactory.eINSTANCE.createSelect()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.GROUP__WIDGET,
				 ConnectorDefinitionFactory.eINSTANCE.createTextArea()));

		newChildDescriptors.add
			(createChildParameter
				(ConnectorDefinitionPackage.Literals.GROUP__WIDGET,
				 ConnectorDefinitionFactory.eINSTANCE.createScriptEditor()));
	}

}
