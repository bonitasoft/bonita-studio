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

import org.bonitasoft.studio.model.kpi.KpiFactory;

import org.bonitasoft.studio.model.process.Container;
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
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.process.Container} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ContainerItemProvider 
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
	public ContainerItemProvider(AdapterFactory adapterFactory) {
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
            childrenFeatures.add(ProcessPackage.Literals.CONTAINER__ELEMENTS);
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
     * This returns Container.gif.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Container")); //$NON-NLS-1$
    }

	/**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getText(Object object) {
        String label = ((Container)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_Container_type") : //$NON-NLS-1$
            getString("_UI_Container_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(Container.class)) {
            case ProcessPackage.CONTAINER__DOCUMENTATION:
            case ProcessPackage.CONTAINER__NAME:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case ProcessPackage.CONTAINER__TEXT_ANNOTATION_ATTACHMENT:
            case ProcessPackage.CONTAINER__ELEMENTS:
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
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createContainer()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createFlowElement()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createActivity()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createActor()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createConnector()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createActorFilter()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createGateway()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createANDGateway()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createConnection()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createAssociation()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createBooleanType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createBoundaryEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createBoundaryMessageEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createBoundarySignalEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createBoundaryTimerEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createData()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createJavaObjectData()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createBusinessObjectData()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createBusinessObjectType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createCallActivity()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createLinkEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createCatchLinkEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createMessageEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createConnectableElement()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createStringType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createDateType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createDocument()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createDoubleType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createEndErrorEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createEndEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createThrowMessageEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createEndMessageEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createEndSignalEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createEndTerminatedEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createEnumType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createFloatType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createInclusiveGateway()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createIntegerType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createIntermediateErrorCatchEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createIntermediateCatchSignalEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createIntermediateThrowSignalEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createIntermediateCatchMessageEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createIntermediateThrowMessageEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createTimerEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createIntermediateCatchTimerEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createJavaType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createLane()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createLongType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createMainProcess()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createMessage()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createMessageFlow()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createNonInterruptingBoundaryTimerEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createPageFlow()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createPool()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createReceiveTask()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createSequenceFlow()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createScriptTask()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createSendTask()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createServiceTask()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createStartErrorEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createStartEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createStartMessageEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createStartSignalEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createStartTimerEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createSubProcessEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createTask()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createTextAnnotation()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createThrowLinkEvent()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createXMLData()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createXMLType()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 ProcessFactory.eINSTANCE.createXORGateway()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 KpiFactory.eINSTANCE.createDatabaseKPIBinding()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 KpiFactory.eINSTANCE.createDatabaseKPIDefinition()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createForm()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createViewForm()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createGroup()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createCheckBoxMultipleFormField()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createComboFormField()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createDateFormField()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createListFormField()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createPasswordFormField()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createRadioFormField()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createSelectFormField()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createTextFormField()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createTextAreaFormField()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createRichTextAreaFormField()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createFormButton()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createSubmitFormButton()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createPreviousFormButton()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createNextFormButton()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createInfo()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createTextInfo()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createMessageInfo()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createCheckBoxSingleFormField()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createFileWidget()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createImageWidget()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createHiddenWidget()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createDurationFormField()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createTable()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createDynamicTable()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createIFrameWidget()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createHtmlWidget()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createSuggestBox()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CONTAINER__ELEMENTS,
                 FormFactory.eINSTANCE.createGroupIterator()));
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
