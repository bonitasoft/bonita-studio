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

import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;

import org.bonitasoft.studio.model.kpi.KpiFactory;

import org.bonitasoft.studio.model.parameter.ParameterFactory;

import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;

import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.bonitasoft.studio.model.process.AbstractProcess} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractProcessItemProvider extends ContainerItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractProcessItemProvider(AdapterFactory adapterFactory) {
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

			addResourceJarsPropertyDescriptor(object);
			addResourceValidatorsPropertyDescriptor(object);
			addResourceFilesPropertyDescriptor(object);
			addMandatorySymbolPropertyDescriptor(object);
			addMandatoryLabelPropertyDescriptor(object);
			addWelcomePageInternalPropertyDescriptor(object);
			addAutoLoginPropertyDescriptor(object);
			addAutoLoginIdPropertyDescriptor(object);
			addBasedOnLookAndFeelPropertyDescriptor(object);
			addRegExpToHideDefaultFieldPropertyDescriptor(object);
			addUseRegExpToHideDefaultFieldPropertyDescriptor(object);
			addByPassFormsGenerationPropertyDescriptor(object);
			addConfirmationTemplatePropertyDescriptor(object);
			addEntryPageFlowTypePropertyDescriptor(object);
			addTransmitURLAsParameterPropertyDescriptor(object);
			addEntryRedirectionURLPropertyDescriptor(object);
			addConfirmationMessagePropertyDescriptor(object);
			addLoadProfileIDPropertyDescriptor(object);
			addMaximumTimePropertyDescriptor(object);
			addRecapPageFlowTypePropertyDescriptor(object);
			addRecapPageFlowRedirectionURLPropertyDescriptor(object);
			addViewPageFlowTypePropertyDescriptor(object);
			addViewPageFlowRedirectionURLPropertyDescriptor(object);
			addVersionPropertyDescriptor(object);
			addAuthorPropertyDescriptor(object);
			addCreationDatePropertyDescriptor(object);
			addModificationDatePropertyDescriptor(object);
			addCategoriesPropertyDescriptor(object);
			addConfigurationsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Resource Jars feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResourceJarsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ResourceContainer_resourceJars_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ResourceContainer_resourceJars_feature", "_UI_ResourceContainer_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_JARS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Resource Validators feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResourceValidatorsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ResourceContainer_resourceValidators_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ResourceContainer_resourceValidators_feature", "_UI_ResourceContainer_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_VALIDATORS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Resource Files feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResourceFilesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ResourceContainer_resourceFiles_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ResourceContainer_resourceFiles_feature", "_UI_ResourceContainer_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FILES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Mandatory Symbol feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMandatorySymbolPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MandatoryFieldsCustomization_mandatorySymbol_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_MandatoryFieldsCustomization_mandatorySymbol_feature", "_UI_MandatoryFieldsCustomization_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Mandatory Label feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMandatoryLabelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MandatoryFieldsCustomization_mandatoryLabel_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_MandatoryFieldsCustomization_mandatoryLabel_feature", "_UI_MandatoryFieldsCustomization_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 FormPackage.Literals.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Welcome Page Internal feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWelcomePageInternalPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ProcessApplication_welcomePageInternal_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ProcessApplication_welcomePageInternal_feature", "_UI_ProcessApplication_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Auto Login feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAutoLoginPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ProcessApplication_autoLogin_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ProcessApplication_autoLogin_feature", "_UI_ProcessApplication_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PROCESS_APPLICATION__AUTO_LOGIN,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Auto Login Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAutoLoginIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ProcessApplication_autoLoginId_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ProcessApplication_autoLoginId_feature", "_UI_ProcessApplication_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PROCESS_APPLICATION__AUTO_LOGIN_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Based On Look And Feel feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBasedOnLookAndFeelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ProcessApplication_basedOnLookAndFeel_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ProcessApplication_basedOnLookAndFeel_feature", "_UI_ProcessApplication_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PROCESS_APPLICATION__BASED_ON_LOOK_AND_FEEL,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
	 * This adds a property descriptor for the By Pass Forms Generation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addByPassFormsGenerationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PageFlow_byPassFormsGeneration_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PageFlow_byPassFormsGeneration_feature", "_UI_PageFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PAGE_FLOW__BY_PASS_FORMS_GENERATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Confirmation Template feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConfirmationTemplatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PageFlow_confirmationTemplate_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PageFlow_confirmationTemplate_feature", "_UI_PageFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PAGE_FLOW__CONFIRMATION_TEMPLATE,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Entry Page Flow Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEntryPageFlowTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PageFlow_entryPageFlowType_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PageFlow_entryPageFlowType_feature", "_UI_PageFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Transmit URL As Parameter feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTransmitURLAsParameterPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PageFlow_transmitURLAsParameter_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PageFlow_transmitURLAsParameter_feature", "_UI_PageFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PAGE_FLOW__TRANSMIT_URL_AS_PARAMETER,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Entry Redirection URL feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEntryRedirectionURLPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PageFlow_entryRedirectionURL_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PageFlow_entryRedirectionURL_feature", "_UI_PageFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PAGE_FLOW__ENTRY_REDIRECTION_URL,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Confirmation Message feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConfirmationMessagePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PageFlow_confirmationMessage_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PageFlow_confirmationMessage_feature", "_UI_PageFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.PAGE_FLOW__CONFIRMATION_MESSAGE,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Load Profile ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLoadProfileIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimulationAbstractProcess_loadProfileID_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_SimulationAbstractProcess_loadProfileID_feature", "_UI_SimulationAbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SimulationPackage.Literals.SIMULATION_ABSTRACT_PROCESS__LOAD_PROFILE_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
				 getString("_UI_SimulationAbstractProcess_maximumTime_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_SimulationAbstractProcess_maximumTime_feature", "_UI_SimulationAbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 SimulationPackage.Literals.SIMULATION_ABSTRACT_PROCESS__MAXIMUM_TIME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Recap Page Flow Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRecapPageFlowTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RecapFlow_recapPageFlowType_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_RecapFlow_recapPageFlowType_feature", "_UI_RecapFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.RECAP_FLOW__RECAP_PAGE_FLOW_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Recap Page Flow Redirection URL feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRecapPageFlowRedirectionURLPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RecapFlow_recapPageFlowRedirectionURL_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_RecapFlow_recapPageFlowRedirectionURL_feature", "_UI_RecapFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.RECAP_FLOW__RECAP_PAGE_FLOW_REDIRECTION_URL,
				 true,
				 false,
				 false,
				 null,
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
				 getString("_UI_AbstractProcess_version_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractProcess_version_feature", "_UI_AbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Author feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAuthorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractProcess_author_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractProcess_author_feature", "_UI_AbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.ABSTRACT_PROCESS__AUTHOR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Creation Date feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCreationDatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractProcess_creationDate_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractProcess_creationDate_feature", "_UI_AbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.ABSTRACT_PROCESS__CREATION_DATE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Modification Date feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addModificationDatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractProcess_modificationDate_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractProcess_modificationDate_feature", "_UI_AbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.ABSTRACT_PROCESS__MODIFICATION_DATE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Categories feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCategoriesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractProcess_categories_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractProcess_categories_feature", "_UI_AbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.ABSTRACT_PROCESS__CATEGORIES,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Configurations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConfigurationsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractProcess_configurations_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractProcess_configurations_feature", "_UI_AbstractProcess_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 ProcessPackage.Literals.ABSTRACT_PROCESS__CONFIGURATIONS,
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
			childrenFeatures.add(ProcessPackage.Literals.RESOURCE_CONTAINER__HTML_TEMPLATE);
			childrenFeatures.add(ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS);
			childrenFeatures.add(FormPackage.Literals.CSS_CUSTOMIZABLE__HTML_ATTRIBUTES);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE);
			childrenFeatures.add(ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE);
			childrenFeatures.add(ProcessPackage.Literals.DATA_AWARE__DATA);
			childrenFeatures.add(ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS);
			childrenFeatures.add(ProcessPackage.Literals.CONNECTABLE_ELEMENT__KPIS);
			childrenFeatures.add(ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA);
			childrenFeatures.add(ProcessPackage.Literals.PAGE_FLOW__PAGE_FLOW_CONNECTORS);
			childrenFeatures.add(ProcessPackage.Literals.PAGE_FLOW__PAGE_FLOW_TRANSITIONS);
			childrenFeatures.add(ProcessPackage.Literals.PAGE_FLOW__FORM);
			childrenFeatures.add(ProcessPackage.Literals.PAGE_FLOW__ENTRY_REDIRECTION_ACTIONS);
			childrenFeatures.add(ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING);
			childrenFeatures.add(SimulationPackage.Literals.SIMULATION_DATA_CONTAINER__SIMULATION_DATA);
			childrenFeatures.add(ProcessPackage.Literals.RECAP_FLOW__RECAP_PAGE_FLOW_TRANSITIONS);
			childrenFeatures.add(ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA);
			childrenFeatures.add(ProcessPackage.Literals.RECAP_FLOW__RECAP_FLOW_CONNECTORS);
			childrenFeatures.add(ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS);
			childrenFeatures.add(ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING);
			childrenFeatures.add(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TRANSITIONS);
			childrenFeatures.add(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA);
			childrenFeatures.add(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS);
			childrenFeatures.add(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM);
			childrenFeatures.add(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES);
			childrenFeatures.add(ProcessPackage.Literals.ABSTRACT_PROCESS__CONNECTIONS);
			childrenFeatures.add(ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS);
			childrenFeatures.add(ProcessPackage.Literals.ABSTRACT_PROCESS__PARAMETERS);
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
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((AbstractProcess)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_AbstractProcess_type") : //$NON-NLS-1$
			getString("_UI_AbstractProcess_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(AbstractProcess.class)) {
			case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_JARS:
			case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_VALIDATORS:
			case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_SYMBOL:
			case ProcessPackage.ABSTRACT_PROCESS__MANDATORY_LABEL:
			case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE_INTERNAL:
			case ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN:
			case ProcessPackage.ABSTRACT_PROCESS__AUTO_LOGIN_ID:
			case ProcessPackage.ABSTRACT_PROCESS__BASED_ON_LOOK_AND_FEEL:
			case ProcessPackage.ABSTRACT_PROCESS__REG_EXP_TO_HIDE_DEFAULT_FIELD:
			case ProcessPackage.ABSTRACT_PROCESS__USE_REG_EXP_TO_HIDE_DEFAULT_FIELD:
			case ProcessPackage.ABSTRACT_PROCESS__BY_PASS_FORMS_GENERATION:
			case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_TEMPLATE:
			case ProcessPackage.ABSTRACT_PROCESS__ENTRY_PAGE_FLOW_TYPE:
			case ProcessPackage.ABSTRACT_PROCESS__TRANSMIT_URL_AS_PARAMETER:
			case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_URL:
			case ProcessPackage.ABSTRACT_PROCESS__CONFIRMATION_MESSAGE:
			case ProcessPackage.ABSTRACT_PROCESS__LOAD_PROFILE_ID:
			case ProcessPackage.ABSTRACT_PROCESS__MAXIMUM_TIME:
			case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TYPE:
			case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_REDIRECTION_URL:
			case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TYPE:
			case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_REDIRECTION_URL:
			case ProcessPackage.ABSTRACT_PROCESS__VERSION:
			case ProcessPackage.ABSTRACT_PROCESS__AUTHOR:
			case ProcessPackage.ABSTRACT_PROCESS__CREATION_DATE:
			case ProcessPackage.ABSTRACT_PROCESS__MODIFICATION_DATE:
			case ProcessPackage.ABSTRACT_PROCESS__CATEGORIES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ProcessPackage.ABSTRACT_PROCESS__HTML_TEMPLATE:
			case ProcessPackage.ABSTRACT_PROCESS__RESOURCE_FOLDERS:
			case ProcessPackage.ABSTRACT_PROCESS__HTML_ATTRIBUTES:
			case ProcessPackage.ABSTRACT_PROCESS__ERROR_TEMPLATE:
			case ProcessPackage.ABSTRACT_PROCESS__PROCESS_TEMPLATE:
			case ProcessPackage.ABSTRACT_PROCESS__PAGE_TEMPLATE:
			case ProcessPackage.ABSTRACT_PROCESS__CONSULTATION_TEMPLATE:
			case ProcessPackage.ABSTRACT_PROCESS__LOG_IN_PAGE:
			case ProcessPackage.ABSTRACT_PROCESS__WELCOME_PAGE:
			case ProcessPackage.ABSTRACT_PROCESS__HOST_PAGE:
			case ProcessPackage.ABSTRACT_PROCESS__DATA:
			case ProcessPackage.ABSTRACT_PROCESS__CONNECTORS:
			case ProcessPackage.ABSTRACT_PROCESS__KPIS:
			case ProcessPackage.ABSTRACT_PROCESS__TRANSIENT_DATA:
			case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_CONNECTORS:
			case ProcessPackage.ABSTRACT_PROCESS__PAGE_FLOW_TRANSITIONS:
			case ProcessPackage.ABSTRACT_PROCESS__FORM:
			case ProcessPackage.ABSTRACT_PROCESS__ENTRY_REDIRECTION_ACTIONS:
			case ProcessPackage.ABSTRACT_PROCESS__FORM_MAPPING:
			case ProcessPackage.ABSTRACT_PROCESS__SIMULATION_DATA:
			case ProcessPackage.ABSTRACT_PROCESS__RECAP_PAGE_FLOW_TRANSITIONS:
			case ProcessPackage.ABSTRACT_PROCESS__RECAP_TRANSIENT_DATA:
			case ProcessPackage.ABSTRACT_PROCESS__RECAP_FLOW_CONNECTORS:
			case ProcessPackage.ABSTRACT_PROCESS__RECAP_FORMS:
			case ProcessPackage.ABSTRACT_PROCESS__OVERVIEW_FORM_MAPPING:
			case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_TRANSITIONS:
			case ProcessPackage.ABSTRACT_PROCESS__VIEW_TRANSIENT_DATA:
			case ProcessPackage.ABSTRACT_PROCESS__VIEW_PAGE_FLOW_CONNECTORS:
			case ProcessPackage.ABSTRACT_PROCESS__VIEW_FORM:
			case ProcessPackage.ABSTRACT_PROCESS__DATATYPES:
			case ProcessPackage.ABSTRACT_PROCESS__CONNECTIONS:
			case ProcessPackage.ABSTRACT_PROCESS__ACTORS:
			case ProcessPackage.ABSTRACT_PROCESS__PARAMETERS:
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
				(ProcessPackage.Literals.RESOURCE_CONTAINER__HTML_TEMPLATE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.RESOURCE_CONTAINER__HTML_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.RESOURCE_CONTAINER__HTML_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE,
				 ProcessFactory.eINSTANCE.createAssociatedFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE,
				 ProcessFactory.eINSTANCE.createResourceFile()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE,
				 ProcessFactory.eINSTANCE.createResourceFolder()));

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
				(ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA,
				 ProcessFactory.eINSTANCE.createData()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA,
				 ProcessFactory.eINSTANCE.createJavaObjectData()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA,
				 ProcessFactory.eINSTANCE.createBusinessObjectData()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA,
				 ProcessFactory.eINSTANCE.createXMLData()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PAGE_FLOW__PAGE_FLOW_CONNECTORS,
				 ProcessFactory.eINSTANCE.createConnector()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PAGE_FLOW__PAGE_FLOW_CONNECTORS,
				 ProcessFactory.eINSTANCE.createActorFilter()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PAGE_FLOW__PAGE_FLOW_TRANSITIONS,
				 ProcessFactory.eINSTANCE.createPageFlowTransition()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PAGE_FLOW__FORM,
				 FormFactory.eINSTANCE.createForm()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PAGE_FLOW__FORM,
				 FormFactory.eINSTANCE.createViewForm()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PAGE_FLOW__ENTRY_REDIRECTION_ACTIONS,
				 ExpressionFactory.eINSTANCE.createOperation()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING,
				 ProcessFactory.eINSTANCE.createFormMapping()));

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
				(ProcessPackage.Literals.RECAP_FLOW__RECAP_PAGE_FLOW_TRANSITIONS,
				 ProcessFactory.eINSTANCE.createPageFlowTransition()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA,
				 ProcessFactory.eINSTANCE.createData()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA,
				 ProcessFactory.eINSTANCE.createJavaObjectData()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA,
				 ProcessFactory.eINSTANCE.createBusinessObjectData()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA,
				 ProcessFactory.eINSTANCE.createXMLData()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.RECAP_FLOW__RECAP_FLOW_CONNECTORS,
				 ProcessFactory.eINSTANCE.createConnector()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.RECAP_FLOW__RECAP_FLOW_CONNECTORS,
				 ProcessFactory.eINSTANCE.createActorFilter()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS,
				 FormFactory.eINSTANCE.createViewForm()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING,
				 ProcessFactory.eINSTANCE.createFormMapping()));

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
				(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
				 ProcessFactory.eINSTANCE.createBooleanType()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
				 ProcessFactory.eINSTANCE.createBusinessObjectType()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
				 ProcessFactory.eINSTANCE.createStringType()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
				 ProcessFactory.eINSTANCE.createDateType()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
				 ProcessFactory.eINSTANCE.createDoubleType()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
				 ProcessFactory.eINSTANCE.createEnumType()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
				 ProcessFactory.eINSTANCE.createFloatType()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
				 ProcessFactory.eINSTANCE.createIntegerType()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
				 ProcessFactory.eINSTANCE.createJavaType()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
				 ProcessFactory.eINSTANCE.createLongType()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES,
				 ProcessFactory.eINSTANCE.createXMLType()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__CONNECTIONS,
				 ProcessFactory.eINSTANCE.createConnection()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__CONNECTIONS,
				 ProcessFactory.eINSTANCE.createAssociation()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__CONNECTIONS,
				 ProcessFactory.eINSTANCE.createSequenceFlow()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS,
				 ProcessFactory.eINSTANCE.createActor()));

		newChildDescriptors.add
			(createChildParameter
				(ProcessPackage.Literals.ABSTRACT_PROCESS__PARAMETERS,
				 ParameterFactory.eINSTANCE.createParameter()));
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
			childFeature == ProcessPackage.Literals.CONTAINER__ELEMENTS ||
			childFeature == ProcessPackage.Literals.ABSTRACT_PROCESS__ACTORS ||
			childFeature == ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS ||
			childFeature == ProcessPackage.Literals.PAGE_FLOW__PAGE_FLOW_CONNECTORS ||
			childFeature == ProcessPackage.Literals.RECAP_FLOW__RECAP_FLOW_CONNECTORS ||
			childFeature == ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS ||
			childFeature == ProcessPackage.Literals.ABSTRACT_PROCESS__CONNECTIONS ||
			childFeature == ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES ||
			childFeature == ProcessPackage.Literals.DATA_AWARE__DATA ||
			childFeature == ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA ||
			childFeature == ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA ||
			childFeature == ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA ||
			childFeature == ProcessPackage.Literals.PAGE_FLOW__FORM ||
			childFeature == ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS ||
			childFeature == ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM ||
			childFeature == ProcessPackage.Literals.CONNECTABLE_ELEMENT__KPIS ||
			childFeature == ProcessPackage.Literals.RESOURCE_CONTAINER__HTML_TEMPLATE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE ||
			childFeature == ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE ||
			childFeature == ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS ||
			childFeature == ProcessPackage.Literals.PAGE_FLOW__PAGE_FLOW_TRANSITIONS ||
			childFeature == ProcessPackage.Literals.RECAP_FLOW__RECAP_PAGE_FLOW_TRANSITIONS ||
			childFeature == ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_TRANSITIONS ||
			childFeature == ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING ||
			childFeature == ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2", //$NON-NLS-1$
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
