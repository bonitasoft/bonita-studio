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

import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.process.CallActivity} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CallActivityItemProvider extends ActivityItemProvider {
	/**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CallActivityItemProvider(AdapterFactory adapterFactory) {
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

        }
        return itemPropertyDescriptors;
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
            childrenFeatures.add(ProcessPackage.Literals.CALL_ACTIVITY__INPUT_MAPPINGS);
            childrenFeatures.add(ProcessPackage.Literals.CALL_ACTIVITY__OUTPUT_MAPPINGS);
            childrenFeatures.add(ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_NAME);
            childrenFeatures.add(ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION);
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
     * This returns CallActivity.gif.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/CallActivity")); //$NON-NLS-1$
    }

	/**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getText(Object object) {
        String label = ((CallActivity)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_CallActivity_type") : //$NON-NLS-1$
            getString("_UI_CallActivity_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(CallActivity.class)) {
            case ProcessPackage.CALL_ACTIVITY__INPUT_MAPPINGS:
            case ProcessPackage.CALL_ACTIVITY__OUTPUT_MAPPINGS:
            case ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_NAME:
            case ProcessPackage.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION:
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
                (ProcessPackage.Literals.CALL_ACTIVITY__INPUT_MAPPINGS,
                 ProcessFactory.eINSTANCE.createInputMapping()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CALL_ACTIVITY__OUTPUT_MAPPINGS,
                 ProcessFactory.eINSTANCE.createOutputMapping()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_NAME,
                 ExpressionFactory.eINSTANCE.createExpression()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION,
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
            childFeature == ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_CONDITION ||
            childFeature == ProcessPackage.Literals.MULTI_INSTANTIABLE__LOOP_MAXIMUM ||
            childFeature == ProcessPackage.Literals.MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION ||
            childFeature == ProcessPackage.Literals.MULTI_INSTANTIABLE__ITERATOR_EXPRESSION ||
            childFeature == ProcessPackage.Literals.MULTI_INSTANTIABLE__COMPLETION_CONDITION ||
            childFeature == ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_NAME ||
            childFeature == ProcessPackage.Literals.CALL_ACTIVITY__CALLED_ACTIVITY_VERSION;

        if (qualify) {
            return getString
                ("_UI_CreateChild_text2", //$NON-NLS-1$
                 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

}
