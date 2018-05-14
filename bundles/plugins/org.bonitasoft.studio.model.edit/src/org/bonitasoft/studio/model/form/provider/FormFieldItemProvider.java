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
package org.bonitasoft.studio.model.form.provider;

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.model.edit.ProcessEditPlugin;

import org.bonitasoft.studio.model.expression.ExpressionFactory;

import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.FormPackage;

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
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.form.FormField} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class FormFieldItemProvider
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
     * 
     * @generated
     */
    public FormFieldItemProvider(AdapterFactory adapterFactory) {
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

            addDocumentationPropertyDescriptor(object);
            addNamePropertyDescriptor(object);
            addWidgetLayoutInfoPropertyDescriptor(object);
            addShowDisplayLabelPropertyDescriptor(object);
            addAllowHTMLForDisplayLabelPropertyDescriptor(object);
            addDependOnPropertyDescriptor(object);
            addDisplayDependentWidgetOnlyOnEventTriggeredPropertyDescriptor(object);
            addParentOfPropertyDescriptor(object);
            addMandatoryPropertyDescriptor(object);
            addReadOnlyPropertyDescriptor(object);
            addLabelPositionPropertyDescriptor(object);
            addRealHtmlAttributesPropertyDescriptor(object);
            addInjectWidgetConditionPropertyDescriptor(object);
            addVersionPropertyDescriptor(object);
            addReturnTypeModifierPropertyDescriptor(object);
            addDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndConditionPropertyDescriptor(object);
            addDisplayAfterEventDependsOnConditionScriptPropertyDescriptor(object);
            addTooltipPropertyDescriptor(object);
            addHelpMessagePropertyDescriptor(object);
            addDisplayLabelPropertyDescriptor(object);
            addInjectWidgetScriptPropertyDescriptor(object);
            addUseDefaultValidatorPropertyDescriptor(object);
            addBelowPropertyDescriptor(object);
            addDuplicatePropertyDescriptor(object);
            addLimitNumberOfDuplicationPropertyDescriptor(object);
            addLimitMinNumberOfDuplicationPropertyDescriptor(object);
            addMaxNumberOfDuplicationPropertyDescriptor(object);
            addMinNumberOfDuplicationPropertyDescriptor(object);
            addDisplayLabelForAddPropertyDescriptor(object);
            addTooltipForAddPropertyDescriptor(object);
            addDisplayLabelForRemovePropertyDescriptor(object);
            addTooltipForRemovePropertyDescriptor(object);
            addDescriptionPropertyDescriptor(object);
            addExampleMessagePositionPropertyDescriptor(object);
            addExampleMessagePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Documentation feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDocumentationPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Element_documentation_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Element_documentation_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Element_type"), //$NON-NLS-1$
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
     * 
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
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
     * This adds a property descriptor for the Widget Layout Info feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addWidgetLayoutInfoPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_widgetLayoutInfo_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_widgetLayoutInfo_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Widget_type"), //$NON-NLS-1$
                        FormPackage.Literals.WIDGET__WIDGET_LAYOUT_INFO,
                        true,
                        false,
                        true,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Show Display Label feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addShowDisplayLabelPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_showDisplayLabel_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_showDisplayLabel_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Widget_type"), //$NON-NLS-1$
                        FormPackage.Literals.WIDGET__SHOW_DISPLAY_LABEL,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Allow HTML For Display Label feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAllowHTMLForDisplayLabelPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_allowHTMLForDisplayLabel_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_allowHTMLForDisplayLabel_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Widget_type"), //$NON-NLS-1$
                        FormPackage.Literals.WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Depend On feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDependOnPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_dependOn_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_dependOn_feature", "_UI_Widget_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        FormPackage.Literals.WIDGET__DEPEND_ON,
                        true,
                        false,
                        true,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Display Dependent Widget Only On Event Triggered feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDisplayDependentWidgetOnlyOnEventTriggeredPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_displayDependentWidgetOnlyOnEventTriggered_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", //$NON-NLS-1$
                                "_UI_Widget_displayDependentWidgetOnlyOnEventTriggered_feature", "_UI_Widget_type"), //$NON-NLS-1$ //$NON-NLS-2$
                        FormPackage.Literals.WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Parent Of feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addParentOfPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_parentOf_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_parentOf_feature", "_UI_Widget_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        FormPackage.Literals.WIDGET__PARENT_OF,
                        true,
                        false,
                        true,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Mandatory feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addMandatoryPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_mandatory_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_mandatory_feature", "_UI_Widget_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        FormPackage.Literals.WIDGET__MANDATORY,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Read Only feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addReadOnlyPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_readOnly_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_readOnly_feature", "_UI_Widget_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        FormPackage.Literals.WIDGET__READ_ONLY,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Label Position feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLabelPositionPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_labelPosition_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_labelPosition_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Widget_type"), //$NON-NLS-1$
                        FormPackage.Literals.WIDGET__LABEL_POSITION,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Real Html Attributes feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addRealHtmlAttributesPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_realHtmlAttributes_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_realHtmlAttributes_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Widget_type"), //$NON-NLS-1$
                        FormPackage.Literals.WIDGET__REAL_HTML_ATTRIBUTES,
                        true,
                        false,
                        true,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Inject Widget Condition feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addInjectWidgetConditionPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_injectWidgetCondition_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_injectWidgetCondition_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Widget_type"), //$NON-NLS-1$
                        FormPackage.Literals.WIDGET__INJECT_WIDGET_CONDITION,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Version feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addVersionPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_version_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_version_feature", "_UI_Widget_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        FormPackage.Literals.WIDGET__VERSION,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Return Type Modifier feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addReturnTypeModifierPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_returnTypeModifier_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_returnTypeModifier_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Widget_type"), //$NON-NLS-1$
                        FormPackage.Literals.WIDGET__RETURN_TYPE_MODIFIER,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Display Dependent Widget Only After First Event Triggered And Condition
     * feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndConditionPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", //$NON-NLS-1$
                                "_UI_Widget_displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_feature", //$NON-NLS-1$
                                "_UI_Widget_type"), //$NON-NLS-1$
                        FormPackage.Literals.WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION,
                        true,
                        false,
                        false,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Display After Event Depends On Condition Script feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDisplayAfterEventDependsOnConditionScriptPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_displayAfterEventDependsOnConditionScript_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", //$NON-NLS-1$
                                "_UI_Widget_displayAfterEventDependsOnConditionScript_feature", "_UI_Widget_type"), //$NON-NLS-1$ //$NON-NLS-2$
                        FormPackage.Literals.WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT,
                        true,
                        false,
                        false,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Tooltip feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTooltipPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_tooltip_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_tooltip_feature", "_UI_Widget_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        FormPackage.Literals.WIDGET__TOOLTIP,
                        true,
                        false,
                        false,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Help Message feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addHelpMessagePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_helpMessage_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_helpMessage_feature", "_UI_Widget_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        FormPackage.Literals.WIDGET__HELP_MESSAGE,
                        true,
                        false,
                        false,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Display Label feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDisplayLabelPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_displayLabel_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_displayLabel_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Widget_type"), //$NON-NLS-1$
                        FormPackage.Literals.WIDGET__DISPLAY_LABEL,
                        true,
                        false,
                        false,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Inject Widget Script feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addInjectWidgetScriptPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Widget_injectWidgetScript_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Widget_injectWidgetScript_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Widget_type"), //$NON-NLS-1$
                        FormPackage.Literals.WIDGET__INJECT_WIDGET_SCRIPT,
                        true,
                        false,
                        false,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Use Default Validator feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addUseDefaultValidatorPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Validable_useDefaultValidator_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Validable_useDefaultValidator_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Validable_type"), //$NON-NLS-1$
                        FormPackage.Literals.VALIDABLE__USE_DEFAULT_VALIDATOR,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Below feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addBelowPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Validable_below_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Validable_below_feature", "_UI_Validable_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        FormPackage.Literals.VALIDABLE__BELOW,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Duplicate feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDuplicatePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Duplicable_duplicate_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_duplicate_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Duplicable_type"), //$NON-NLS-1$
                        FormPackage.Literals.DUPLICABLE__DUPLICATE,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Limit Number Of Duplication feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLimitNumberOfDuplicationPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Duplicable_limitNumberOfDuplication_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_limitNumberOfDuplication_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Duplicable_type"), //$NON-NLS-1$
                        FormPackage.Literals.DUPLICABLE__LIMIT_NUMBER_OF_DUPLICATION,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Limit Min Number Of Duplication feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addLimitMinNumberOfDuplicationPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Duplicable_limitMinNumberOfDuplication_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_limitMinNumberOfDuplication_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Duplicable_type"), //$NON-NLS-1$
                        FormPackage.Literals.DUPLICABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Max Number Of Duplication feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addMaxNumberOfDuplicationPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Duplicable_maxNumberOfDuplication_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_maxNumberOfDuplication_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Duplicable_type"), //$NON-NLS-1$
                        FormPackage.Literals.DUPLICABLE__MAX_NUMBER_OF_DUPLICATION,
                        true,
                        false,
                        false,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Min Number Of Duplication feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addMinNumberOfDuplicationPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Duplicable_minNumberOfDuplication_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_minNumberOfDuplication_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Duplicable_type"), //$NON-NLS-1$
                        FormPackage.Literals.DUPLICABLE__MIN_NUMBER_OF_DUPLICATION,
                        true,
                        false,
                        false,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Display Label For Add feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDisplayLabelForAddPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Duplicable_displayLabelForAdd_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_displayLabelForAdd_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Duplicable_type"), //$NON-NLS-1$
                        FormPackage.Literals.DUPLICABLE__DISPLAY_LABEL_FOR_ADD,
                        true,
                        false,
                        false,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Tooltip For Add feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTooltipForAddPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Duplicable_tooltipForAdd_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_tooltipForAdd_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Duplicable_type"), //$NON-NLS-1$
                        FormPackage.Literals.DUPLICABLE__TOOLTIP_FOR_ADD,
                        true,
                        false,
                        false,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Display Label For Remove feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDisplayLabelForRemovePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Duplicable_displayLabelForRemove_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_displayLabelForRemove_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Duplicable_type"), //$NON-NLS-1$
                        FormPackage.Literals.DUPLICABLE__DISPLAY_LABEL_FOR_REMOVE,
                        true,
                        false,
                        false,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Tooltip For Remove feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTooltipForRemovePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Duplicable_tooltipForRemove_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_Duplicable_tooltipForRemove_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_Duplicable_type"), //$NON-NLS-1$
                        FormPackage.Literals.DUPLICABLE__TOOLTIP_FOR_REMOVE,
                        true,
                        false,
                        false,
                        null,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Description feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addDescriptionPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_FormField_description_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_FormField_description_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_FormField_type"), //$NON-NLS-1$
                        FormPackage.Literals.FORM_FIELD__DESCRIPTION,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Example Message Position feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addExampleMessagePositionPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_FormField_exampleMessagePosition_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_FormField_exampleMessagePosition_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_FormField_type"), //$NON-NLS-1$
                        FormPackage.Literals.FORM_FIELD__EXAMPLE_MESSAGE_POSITION,
                        true,
                        false,
                        false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                        null,
                        null));
    }

    /**
     * This adds a property descriptor for the Example Message feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addExampleMessagePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_FormField_exampleMessage_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_FormField_exampleMessage_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_FormField_type"), //$NON-NLS-1$
                        FormPackage.Literals.FORM_FIELD__EXAMPLE_MESSAGE,
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
     * 
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(ProcessPackage.Literals.ELEMENT__TEXT_ANNOTATION_ATTACHMENT);
            childrenFeatures.add(FormPackage.Literals.CSS_CUSTOMIZABLE__HTML_ATTRIBUTES);
            childrenFeatures.add(FormPackage.Literals.WIDGET__INPUT_EXPRESSION);
            childrenFeatures.add(FormPackage.Literals.WIDGET__AFTER_EVENT_EXPRESSION);
            childrenFeatures.add(FormPackage.Literals.WIDGET__ACTION);
            childrenFeatures.add(FormPackage.Literals.VALIDABLE__VALIDATORS);
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
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((FormField) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_FormField_type") : //$NON-NLS-1$
                getString("_UI_FormField_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(FormField.class)) {
            case FormPackage.FORM_FIELD__DOCUMENTATION:
            case FormPackage.FORM_FIELD__NAME:
            case FormPackage.FORM_FIELD__SHOW_DISPLAY_LABEL:
            case FormPackage.FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL:
            case FormPackage.FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED:
            case FormPackage.FORM_FIELD__MANDATORY:
            case FormPackage.FORM_FIELD__READ_ONLY:
            case FormPackage.FORM_FIELD__LABEL_POSITION:
            case FormPackage.FORM_FIELD__INJECT_WIDGET_CONDITION:
            case FormPackage.FORM_FIELD__VERSION:
            case FormPackage.FORM_FIELD__RETURN_TYPE_MODIFIER:
            case FormPackage.FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION:
            case FormPackage.FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT:
            case FormPackage.FORM_FIELD__TOOLTIP:
            case FormPackage.FORM_FIELD__HELP_MESSAGE:
            case FormPackage.FORM_FIELD__DISPLAY_LABEL:
            case FormPackage.FORM_FIELD__INJECT_WIDGET_SCRIPT:
            case FormPackage.FORM_FIELD__USE_DEFAULT_VALIDATOR:
            case FormPackage.FORM_FIELD__BELOW:
            case FormPackage.FORM_FIELD__DUPLICATE:
            case FormPackage.FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION:
            case FormPackage.FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION:
            case FormPackage.FORM_FIELD__MAX_NUMBER_OF_DUPLICATION:
            case FormPackage.FORM_FIELD__MIN_NUMBER_OF_DUPLICATION:
            case FormPackage.FORM_FIELD__DISPLAY_LABEL_FOR_ADD:
            case FormPackage.FORM_FIELD__TOOLTIP_FOR_ADD:
            case FormPackage.FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE:
            case FormPackage.FORM_FIELD__TOOLTIP_FOR_REMOVE:
            case FormPackage.FORM_FIELD__DESCRIPTION:
            case FormPackage.FORM_FIELD__EXAMPLE_MESSAGE_POSITION:
            case FormPackage.FORM_FIELD__EXAMPLE_MESSAGE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case FormPackage.FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT:
            case FormPackage.FORM_FIELD__HTML_ATTRIBUTES:
            case FormPackage.FORM_FIELD__INPUT_EXPRESSION:
            case FormPackage.FORM_FIELD__AFTER_EVENT_EXPRESSION:
            case FormPackage.FORM_FIELD__ACTION:
            case FormPackage.FORM_FIELD__VALIDATORS:
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

        newChildDescriptors.add(createChildParameter(ProcessPackage.Literals.ELEMENT__TEXT_ANNOTATION_ATTACHMENT,
                ProcessFactory.eINSTANCE.createTextAnnotationAttachment()));

        newChildDescriptors.add(createChildParameter(FormPackage.Literals.WIDGET__INPUT_EXPRESSION,
                ExpressionFactory.eINSTANCE.createExpression()));

        newChildDescriptors.add(createChildParameter(FormPackage.Literals.WIDGET__AFTER_EVENT_EXPRESSION,
                ExpressionFactory.eINSTANCE.createExpression()));

        newChildDescriptors.add(createChildParameter(FormPackage.Literals.WIDGET__ACTION,
                ExpressionFactory.eINSTANCE.createOperation()));

        newChildDescriptors.add(createChildParameter(FormPackage.Literals.VALIDABLE__VALIDATORS,
                FormFactory.eINSTANCE.createValidator()));
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

        boolean qualify = childFeature == FormPackage.Literals.WIDGET__INPUT_EXPRESSION ||
                childFeature == FormPackage.Literals.WIDGET__AFTER_EVENT_EXPRESSION;

        if (qualify) {
            return getString("_UI_CreateChild_text2", //$NON-NLS-1$
                    new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
    }

    /**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return ProcessEditPlugin.INSTANCE;
    }

}
