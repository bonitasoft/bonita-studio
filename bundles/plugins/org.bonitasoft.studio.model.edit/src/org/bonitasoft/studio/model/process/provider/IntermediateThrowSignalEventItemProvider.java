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

import org.bonitasoft.studio.model.form.FormFactory;

import org.bonitasoft.studio.model.process.IntermediateThrowSignalEvent;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.model.simulation.SimulationPackage;

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
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.process.IntermediateThrowSignalEvent} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IntermediateThrowSignalEventItemProvider 
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
	public IntermediateThrowSignalEventItemProvider(AdapterFactory adapterFactory) {
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
			addSignalCodePropertyDescriptor(object);
			addOutgoingPropertyDescriptor(object);
			addIncomingPropertyDescriptor(object);
			addRegExpToHideDefaultFieldPropertyDescriptor(object);
			addUseRegExpToHideDefaultFieldPropertyDescriptor(object);
			addViewPageFlowTypePropertyDescriptor(object);
			addViewPageFlowRedirectionURLPropertyDescriptor(object);
			addExecutionTimePropertyDescriptor(object);
			addEstimatedTimePropertyDescriptor(object);
			addMaximumTimePropertyDescriptor(object);
			addContigousPropertyDescriptor(object);
			addExclusiveOutgoingTransitionPropertyDescriptor(object);
			addDynamicLabelPropertyDescriptor(object);
			addDynamicDescriptionPropertyDescriptor(object);
			addStepSummaryPropertyDescriptor(object);
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
	 * This adds a property descriptor for the Signal Code feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSignalCodePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SignalEvent_signalCode_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_SignalEvent_signalCode_feature", "_UI_SignalEvent_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.SIGNAL_EVENT__SIGNAL_CODE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Outgoing feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOutgoingPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SourceElement_outgoing_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_SourceElement_outgoing_feature", "_UI_SourceElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.SOURCE_ELEMENT__OUTGOING,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Incoming feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIncomingPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TargetElement_incoming_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_TargetElement_incoming_feature", "_UI_TargetElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.TARGET_ELEMENT__INCOMING,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Reg Exp To Hide Default Field feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRegExpToHideDefaultFieldPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractPageFlow_regExpToHideDefaultField_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractPageFlow_regExpToHideDefaultField_feature", "_UI_AbstractPageFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.ABSTRACT_PAGE_FLOW__REG_EXP_TO_HIDE_DEFAULT_FIELD,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Use Reg Exp To Hide Default Field feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUseRegExpToHideDefaultFieldPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractPageFlow_useRegExpToHideDefaultField_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractPageFlow_useRegExpToHideDefaultField_feature", "_UI_AbstractPageFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.ABSTRACT_PAGE_FLOW__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the View Page Flow Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addViewPageFlowTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ViewPageFlow_viewPageFlowType_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ViewPageFlow_viewPageFlowType_feature", "_UI_ViewPageFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the View Page Flow Redirection URL feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addViewPageFlowRedirectionURLPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ViewPageFlow_viewPageFlowRedirectionURL_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ViewPageFlow_viewPageFlowRedirectionURL_feature", "_UI_ViewPageFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_REDIRECTION_URL,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Execution Time feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addExecutionTimePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimulationActivity_executionTime_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_SimulationActivity_executionTime_feature", "_UI_SimulationActivity_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SimulationPackage.Literals.SIMULATION_ACTIVITY__EXECUTION_TIME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Estimated Time feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEstimatedTimePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimulationActivity_estimatedTime_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_SimulationActivity_estimatedTime_feature", "_UI_SimulationActivity_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SimulationPackage.Literals.SIMULATION_ACTIVITY__ESTIMATED_TIME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Maximum Time feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMaximumTimePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimulationActivity_maximumTime_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_SimulationActivity_maximumTime_feature", "_UI_SimulationActivity_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SimulationPackage.Literals.SIMULATION_ACTIVITY__MAXIMUM_TIME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Contigous feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContigousPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimulationActivity_contigous_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_SimulationActivity_contigous_feature", "_UI_SimulationActivity_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SimulationPackage.Literals.SIMULATION_ACTIVITY__CONTIGOUS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Exclusive Outgoing Transition feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addExclusiveOutgoingTransitionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimulationActivity_exclusiveOutgoingTransition_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_SimulationActivity_exclusiveOutgoingTransition_feature", "_UI_SimulationActivity_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SimulationPackage.Literals.SIMULATION_ACTIVITY__EXCLUSIVE_OUTGOING_TRANSITION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Dynamic Label feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDynamicLabelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FlowElement_dynamicLabel_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_FlowElement_dynamicLabel_feature", "_UI_FlowElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.FLOW_ELEMENT__DYNAMIC_LABEL,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Dynamic Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDynamicDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FlowElement_dynamicDescription_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_FlowElement_dynamicDescription_feature", "_UI_FlowElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.FLOW_ELEMENT__DYNAMIC_DESCRIPTION,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Step Summary feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStepSummaryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FlowElement_stepSummary_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_FlowElement_stepSummary_feature", "_UI_FlowElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.FLOW_ELEMENT__STEP_SUMMARY,
				 true,
				 false,
				 false,
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
			childrenFeatures.add(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TRANSITIONS);
			childrenFeatures.add(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA);
			childrenFeatures.add(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS);
			childrenFeatures.add(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM);
			childrenFeatures.add(SimulationPackage.Literals.SIMULATION_DATA_CONTAINER__SIMULATION_DATA);
			childrenFeatures.add(SimulationPackage.Literals.SIMULATION_ACTIVITY__RESOURCES_USAGES);
			childrenFeatures.add(SimulationPackage.Literals.SIMULATION_ACTIVITY__LOOP_TRANSITION);
			childrenFeatures.add(SimulationPackage.Literals.SIMULATION_ACTIVITY__DATA_CHANGE);
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
	 * This returns IntermediateThrowSignalEvent.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/IntermediateThrowSignalEvent")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((IntermediateThrowSignalEvent)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_IntermediateThrowSignalEvent_type") : //$NON-NLS-1$
			getString("_UI_IntermediateThrowSignalEvent_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(IntermediateThrowSignalEvent.class)) {
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__DOCUMENTATION:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__NAME:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__SIGNAL_CODE:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__REG_EXP_TO_HIDE_DEFAULT_FIELD:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__VIEW_PAGE_FLOW_TYPE:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__VIEW_PAGE_FLOW_REDIRECTION_URL:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__EXECUTION_TIME:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__ESTIMATED_TIME:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__MAXIMUM_TIME:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__CONTIGOUS:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__EXCLUSIVE_OUTGOING_TRANSITION:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__DYNAMIC_LABEL:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__DYNAMIC_DESCRIPTION:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__STEP_SUMMARY:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__TEXT_ANNOTATION_ATTACHMENT:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__VIEW_PAGE_FLOW_TRANSITIONS:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__VIEW_TRANSIENT_DATA:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__VIEW_PAGE_FLOW_CONNECTORS:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__VIEW_FORM:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__SIMULATION_DATA:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__RESOURCES_USAGES:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__LOOP_TRANSITION:
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT__DATA_CHANGE:
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
				(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TRANSITIONS,
				 ProcessFactory.eINSTANCE.createPageFlowTransition()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA,
				 ProcessFactory.eINSTANCE.createData()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA,
				 ProcessFactory.eINSTANCE.createJavaObjectData()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA,
				 ProcessFactory.eINSTANCE.createBusinessObjectData()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA,
				 ProcessFactory.eINSTANCE.createXMLData()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS,
				 ProcessFactory.eINSTANCE.createConnector()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS,
				 ProcessFactory.eINSTANCE.createActorFilter()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM,
				 FormFactory.eINSTANCE.createViewForm()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATION_DATA_CONTAINER__SIMULATION_DATA,
				 SimulationFactory.eINSTANCE.createSimulationBoolean()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATION_DATA_CONTAINER__SIMULATION_DATA,
				 SimulationFactory.eINSTANCE.createSimulationNumberData()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATION_DATA_CONTAINER__SIMULATION_DATA,
				 SimulationFactory.eINSTANCE.createSimulationLiteralData()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATION_ACTIVITY__RESOURCES_USAGES,
				 SimulationFactory.eINSTANCE.createResourceUsage()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATION_ACTIVITY__LOOP_TRANSITION,
				 ProcessFactory.eINSTANCE.createConnection()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATION_ACTIVITY__LOOP_TRANSITION,
				 ProcessFactory.eINSTANCE.createAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATION_ACTIVITY__LOOP_TRANSITION,
				 ProcessFactory.eINSTANCE.createSequenceFlow()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATION_ACTIVITY__LOOP_TRANSITION,
				 SimulationFactory.eINSTANCE.createSimulationTransition()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATION_ACTIVITY__DATA_CHANGE,
				 SimulationFactory.eINSTANCE.createDataChange()));
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
