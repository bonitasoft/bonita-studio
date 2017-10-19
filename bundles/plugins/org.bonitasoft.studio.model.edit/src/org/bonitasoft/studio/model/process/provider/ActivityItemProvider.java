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

import org.bonitasoft.studio.model.expression.ExpressionFactory;

import org.bonitasoft.studio.model.kpi.KpiFactory;

import org.bonitasoft.studio.model.process.Activity;
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
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.process.Activity} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ActivityItemProvider extends FlowElementItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityItemProvider(AdapterFactory adapterFactory) {
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

			addTypePropertyDescriptor(object);
			addTestBeforePropertyDescriptor(object);
			addUseCardinalityPropertyDescriptor(object);
			addCollectionDataToMultiInstantiatePropertyDescriptor(object);
			addOutputDataPropertyDescriptor(object);
			addListDataContainingOutputResultsPropertyDescriptor(object);
			addStoreOutputPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MultiInstantiable_type_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_MultiInstantiable_type_feature", "_UI_MultiInstantiable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.MULTI_INSTANTIABLE__TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Test Before feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTestBeforePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MultiInstantiable_testBefore_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_MultiInstantiable_testBefore_feature", "_UI_MultiInstantiable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.MULTI_INSTANTIABLE__TEST_BEFORE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Use Cardinality feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUseCardinalityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MultiInstantiable_useCardinality_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_MultiInstantiable_useCardinality_feature", "_UI_MultiInstantiable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.MULTI_INSTANTIABLE__USE_CARDINALITY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Collection Data To Multi Instantiate feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCollectionDataToMultiInstantiatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MultiInstantiable_collectionDataToMultiInstantiate_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_MultiInstantiable_collectionDataToMultiInstantiate_feature", "_UI_MultiInstantiable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.MULTI_INSTANTIABLE__COLLECTION_DATA_TO_MULTI_INSTANTIATE,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Output Data feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOutputDataPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MultiInstantiable_outputData_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_MultiInstantiable_outputData_feature", "_UI_MultiInstantiable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.MULTI_INSTANTIABLE__OUTPUT_DATA,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the List Data Containing Output Results feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addListDataContainingOutputResultsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MultiInstantiable_listDataContainingOutputResults_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_MultiInstantiable_listDataContainingOutputResults_feature", "_UI_MultiInstantiable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.MULTI_INSTANTIABLE__LIST_DATA_CONTAINING_OUTPUT_RESULTS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Store Output feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStoreOutputPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MultiInstantiable_storeOutput_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_MultiInstantiable_storeOutput_feature", "_UI_MultiInstantiable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.MULTI_INSTANTIABLE__STORE_OUTPUT,
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
			childrenFeatures.add(ProcessPackage.Literals.DATA_AWARE__DATA);
			childrenFeatures.add(ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS);
			childrenFeatures.add(ProcessPackage.Literals.CONNECTABLE_ELEMENT__KPIS);
			childrenFeatures.add(ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS);
			childrenFeatures.add(ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_CONDITION);
			childrenFeatures.add(ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_MAXIMUM);
			childrenFeatures.add(ProcessPackage.Literals.MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION);
			childrenFeatures.add(ProcessPackage.Literals.MULTI_INSTANTIABLE__ITERATOR_EXPRESSION);
			childrenFeatures.add(ProcessPackage.Literals.MULTI_INSTANTIABLE__COMPLETION_CONDITION);
			childrenFeatures.add(ProcessPackage.Literals.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS);
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
	 * This returns Activity.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Activity")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Activity)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Activity_type") : //$NON-NLS-1$
			getString("_UI_Activity_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(Activity.class)) {
			case ProcessPackage.ACTIVITY__TYPE:
			case ProcessPackage.ACTIVITY__TEST_BEFORE:
			case ProcessPackage.ACTIVITY__USE_CARDINALITY:
			case ProcessPackage.ACTIVITY__STORE_OUTPUT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ProcessPackage.ACTIVITY__DATA:
			case ProcessPackage.ACTIVITY__CONNECTORS:
			case ProcessPackage.ACTIVITY__KPIS:
			case ProcessPackage.ACTIVITY__OPERATIONS:
			case ProcessPackage.ACTIVITY__LOOP_CONDITION:
			case ProcessPackage.ACTIVITY__LOOP_MAXIMUM:
			case ProcessPackage.ACTIVITY__CARDINALITY_EXPRESSION:
			case ProcessPackage.ACTIVITY__ITERATOR_EXPRESSION:
			case ProcessPackage.ACTIVITY__COMPLETION_CONDITION:
			case ProcessPackage.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS:
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
				(ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS,
				 ExpressionFactory.eINSTANCE.createOperation()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_CONDITION,
				 ExpressionFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_MAXIMUM,
				 ExpressionFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION,
				 ExpressionFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.MULTI_INSTANTIABLE__ITERATOR_EXPRESSION,
				 ExpressionFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.MULTI_INSTANTIABLE__COMPLETION_CONDITION,
				 ExpressionFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS,
				 ProcessFactory.eINSTANCE.createBoundaryEvent()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS,
				 ProcessFactory.eINSTANCE.createBoundaryMessageEvent()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS,
				 ProcessFactory.eINSTANCE.createBoundarySignalEvent()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS,
				 ProcessFactory.eINSTANCE.createBoundaryTimerEvent()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS,
				 ProcessFactory.eINSTANCE.createIntermediateErrorCatchEvent()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ACTIVITY__BOUNDARY_INTERMEDIATE_EVENTS,
				 ProcessFactory.eINSTANCE.createNonInterruptingBoundaryTimerEvent()));
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
			childFeature == ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA ||
			childFeature == ProcessPackage.Literals.DATA_AWARE__DATA ||
			childFeature == ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS ||
			childFeature == ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS ||
			childFeature == ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_CONDITION ||
			childFeature == ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_MAXIMUM ||
			childFeature == ProcessPackage.Literals.MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION ||
			childFeature == ProcessPackage.Literals.MULTI_INSTANTIABLE__ITERATOR_EXPRESSION ||
			childFeature == ProcessPackage.Literals.MULTI_INSTANTIABLE__COMPLETION_CONDITION;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2", //$NON-NLS-1$
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
