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
package org.bonitasoft.studio.model.form.impl;

import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;

import org.bonitasoft.studio.model.actormapping.impl.ActorMappingPackageImpl;

import org.bonitasoft.studio.model.configuration.ConfigurationPackage;

import org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl;

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;

import org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationPackageImpl;

import org.bonitasoft.studio.model.expression.ExpressionPackage;

import org.bonitasoft.studio.model.expression.impl.ExpressionPackageImpl;

import org.bonitasoft.studio.model.form.AbstractTable;
import org.bonitasoft.studio.model.form.CSSCustomizable;
import org.bonitasoft.studio.model.form.CheckBoxMultipleFormField;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.Column;
import org.bonitasoft.studio.model.form.ComboFormField;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.DurationFormField;
import org.bonitasoft.studio.model.form.DynamicTable;
import org.bonitasoft.studio.model.form.EventDependencyType;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetDownloadType;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormButton;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.GroupIterator;
import org.bonitasoft.studio.model.form.HiddenWidget;
import org.bonitasoft.studio.model.form.HtmlWidget;
import org.bonitasoft.studio.model.form.IFrameWidget;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.form.Info;
import org.bonitasoft.studio.model.form.ItemContainer;
import org.bonitasoft.studio.model.form.LabelPosition;
import org.bonitasoft.studio.model.form.Line;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.MandatoryFieldsCustomization;
import org.bonitasoft.studio.model.form.MessageInfo;
import org.bonitasoft.studio.model.form.MultipleValuatedFormField;
import org.bonitasoft.studio.model.form.NextFormButton;
import org.bonitasoft.studio.model.form.PasswordFormField;
import org.bonitasoft.studio.model.form.PreviousFormButton;
import org.bonitasoft.studio.model.form.RadioFormField;
import org.bonitasoft.studio.model.form.RichTextAreaFormField;
import org.bonitasoft.studio.model.form.SelectFormField;
import org.bonitasoft.studio.model.form.SingleValuatedFormField;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.form.SuggestBox;
import org.bonitasoft.studio.model.form.Table;
import org.bonitasoft.studio.model.form.TextAreaFormField;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.TextInfo;
import org.bonitasoft.studio.model.form.Validable;
import org.bonitasoft.studio.model.form.Validator;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetDependency;
import org.bonitasoft.studio.model.form.WidgetLayoutInfo;

import org.bonitasoft.studio.model.kpi.KpiPackage;

import org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl;

import org.bonitasoft.studio.model.parameter.ParameterPackage;

import org.bonitasoft.studio.model.parameter.impl.ParameterPackageImpl;

import org.bonitasoft.studio.model.process.ProcessPackage;

import org.bonitasoft.studio.model.process.decision.DecisionPackage;

import org.bonitasoft.studio.model.process.decision.impl.DecisionPackageImpl;

import org.bonitasoft.studio.model.process.decision.transitions.TransitionsPackage;

import org.bonitasoft.studio.model.process.decision.transitions.impl.TransitionsPackageImpl;

import org.bonitasoft.studio.model.process.impl.ProcessPackageImpl;

import org.bonitasoft.studio.model.simulation.SimulationPackage;

import org.bonitasoft.studio.model.simulation.impl.SimulationPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FormPackageImpl extends EPackageImpl implements FormPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass widgetDependencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass widgetLayoutInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass columnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass itemContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass duplicableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass viewFormEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cssCustomizableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass widgetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass groupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multipleValuatedFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleValuatedFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass checkBoxMultipleFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comboFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dateFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass passwordFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass radioFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass selectFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textAreaFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass richTextAreaFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formButtonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass submitFormButtonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass previousFormButtonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nextFormButtonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass checkBoxSingleFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileWidgetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass imageWidgetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hiddenWidgetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass durationFormFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iFrameWidgetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mandatoryFieldsCustomizationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass htmlWidgetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass suggestBoxEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass groupIteratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eventDependencyTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum labelPositionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum fileWidgetInputTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum fileWidgetDownloadTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.bonitasoft.studio.model.form.FormPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private FormPackageImpl() {
		super(eNS_URI, FormFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link FormPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static FormPackage init() {
		if (isInited) return (FormPackage)EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredFormPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		FormPackageImpl theFormPackage = registeredFormPackage instanceof FormPackageImpl ? (FormPackageImpl)registeredFormPackage : new FormPackageImpl();

		isInited = true;

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ActorMappingPackage.eNS_URI);
		ActorMappingPackageImpl theActorMappingPackage = (ActorMappingPackageImpl)(registeredPackage instanceof ActorMappingPackageImpl ? registeredPackage : ActorMappingPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConfigurationPackage.eNS_URI);
		ConfigurationPackageImpl theConfigurationPackage = (ConfigurationPackageImpl)(registeredPackage instanceof ConfigurationPackageImpl ? registeredPackage : ConfigurationPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConnectorConfigurationPackage.eNS_URI);
		ConnectorConfigurationPackageImpl theConnectorConfigurationPackage = (ConnectorConfigurationPackageImpl)(registeredPackage instanceof ConnectorConfigurationPackageImpl ? registeredPackage : ConnectorConfigurationPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);
		ExpressionPackageImpl theExpressionPackage = (ExpressionPackageImpl)(registeredPackage instanceof ExpressionPackageImpl ? registeredPackage : ExpressionPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(KpiPackage.eNS_URI);
		KpiPackageImpl theKpiPackage = (KpiPackageImpl)(registeredPackage instanceof KpiPackageImpl ? registeredPackage : KpiPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ParameterPackage.eNS_URI);
		ParameterPackageImpl theParameterPackage = (ParameterPackageImpl)(registeredPackage instanceof ParameterPackageImpl ? registeredPackage : ParameterPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ProcessPackage.eNS_URI);
		ProcessPackageImpl theProcessPackage = (ProcessPackageImpl)(registeredPackage instanceof ProcessPackageImpl ? registeredPackage : ProcessPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DecisionPackage.eNS_URI);
		DecisionPackageImpl theDecisionPackage = (DecisionPackageImpl)(registeredPackage instanceof DecisionPackageImpl ? registeredPackage : DecisionPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TransitionsPackage.eNS_URI);
		TransitionsPackageImpl theTransitionsPackage = (TransitionsPackageImpl)(registeredPackage instanceof TransitionsPackageImpl ? registeredPackage : TransitionsPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SimulationPackage.eNS_URI);
		SimulationPackageImpl theSimulationPackage = (SimulationPackageImpl)(registeredPackage instanceof SimulationPackageImpl ? registeredPackage : SimulationPackage.eINSTANCE);

		// Create package meta-data objects
		theFormPackage.createPackageContents();
		theActorMappingPackage.createPackageContents();
		theConfigurationPackage.createPackageContents();
		theConnectorConfigurationPackage.createPackageContents();
		theExpressionPackage.createPackageContents();
		theKpiPackage.createPackageContents();
		theParameterPackage.createPackageContents();
		theProcessPackage.createPackageContents();
		theDecisionPackage.createPackageContents();
		theTransitionsPackage.createPackageContents();
		theSimulationPackage.createPackageContents();

		// Initialize created meta-data
		theFormPackage.initializePackageContents();
		theActorMappingPackage.initializePackageContents();
		theConfigurationPackage.initializePackageContents();
		theConnectorConfigurationPackage.initializePackageContents();
		theExpressionPackage.initializePackageContents();
		theKpiPackage.initializePackageContents();
		theParameterPackage.initializePackageContents();
		theProcessPackage.initializePackageContents();
		theDecisionPackage.initializePackageContents();
		theTransitionsPackage.initializePackageContents();
		theSimulationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theFormPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FormPackage.eNS_URI, theFormPackage);
		return theFormPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWidgetDependency() {
		return widgetDependencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidgetDependency_TriggerRefreshOnModification() {
		return (EAttribute)widgetDependencyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidgetDependency_EventTypes() {
		return (EAttribute)widgetDependencyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidgetDependency_Widget() {
		return (EReference)widgetDependencyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValidator() {
		return validatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidator_ValidatorClass() {
		return (EAttribute)validatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidator_HtmlClass() {
		return (EAttribute)validatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidator_Name() {
		return (EAttribute)validatorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidator_BelowField() {
		return (EAttribute)validatorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getValidator_Parameter() {
		return (EReference)validatorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getValidator_DisplayName() {
		return (EReference)validatorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValidable() {
		return validableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getValidable_Validators() {
		return (EReference)validableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidable_UseDefaultValidator() {
		return (EAttribute)validableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValidable_Below() {
		return (EAttribute)validableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getForm() {
		return formEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getForm_NColumn() {
		return (EAttribute)formEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getForm_StringAttributes() {
		return (EReference)formEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getForm_NLine() {
		return (EAttribute)formEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getForm_ShowPageLabel() {
		return (EAttribute)formEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getForm_AllowHTMLInPageLabel() {
		return (EAttribute)formEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getForm_Version() {
		return (EAttribute)formEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getForm_Columns() {
		return (EReference)formEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getForm_Lines() {
		return (EReference)formEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getForm_Widgets() {
		return (EReference)formEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getForm_PageLabel() {
		return (EReference)formEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getForm_Actions() {
		return (EReference)formEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWidgetLayoutInfo() {
		return widgetLayoutInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidgetLayoutInfo_Line() {
		return (EAttribute)widgetLayoutInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidgetLayoutInfo_Column() {
		return (EAttribute)widgetLayoutInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidgetLayoutInfo_VerticalSpan() {
		return (EAttribute)widgetLayoutInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidgetLayoutInfo_HorizontalSpan() {
		return (EAttribute)widgetLayoutInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getColumn() {
		return columnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getColumn_Width() {
		return (EAttribute)columnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getColumn_Number() {
		return (EAttribute)columnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLine() {
		return lineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLine_Height() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLine_Number() {
		return (EAttribute)lineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getItemContainer() {
		return itemContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getItemContainer_ItemClass() {
		return (EAttribute)itemContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDuplicable() {
		return duplicableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDuplicable_Duplicate() {
		return (EAttribute)duplicableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDuplicable_LimitNumberOfDuplication() {
		return (EAttribute)duplicableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDuplicable_LimitMinNumberOfDuplication() {
		return (EAttribute)duplicableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDuplicable_MaxNumberOfDuplication() {
		return (EReference)duplicableEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDuplicable_MinNumberOfDuplication() {
		return (EReference)duplicableEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDuplicable_DisplayLabelForAdd() {
		return (EReference)duplicableEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDuplicable_TooltipForAdd() {
		return (EReference)duplicableEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDuplicable_DisplayLabelForRemove() {
		return (EReference)duplicableEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDuplicable_TooltipForRemove() {
		return (EReference)duplicableEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getViewForm() {
		return viewFormEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCSSCustomizable() {
		return cssCustomizableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCSSCustomizable_HtmlAttributes() {
		return (EReference)cssCustomizableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWidget() {
		return widgetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidget_WidgetLayoutInfo() {
		return (EReference)widgetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidget_ShowDisplayLabel() {
		return (EAttribute)widgetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidget_AllowHTMLForDisplayLabel() {
		return (EAttribute)widgetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidget_DependOn() {
		return (EReference)widgetEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidget_DisplayDependentWidgetOnlyOnEventTriggered() {
		return (EAttribute)widgetEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidget_ParentOf() {
		return (EReference)widgetEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidget_Mandatory() {
		return (EAttribute)widgetEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidget_ReadOnly() {
		return (EAttribute)widgetEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidget_LabelPosition() {
		return (EAttribute)widgetEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidget_RealHtmlAttributes() {
		return (EAttribute)widgetEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidget_InjectWidgetCondition() {
		return (EAttribute)widgetEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidget_Version() {
		return (EAttribute)widgetEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWidget_ReturnTypeModifier() {
		return (EAttribute)widgetEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidget_DisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition() {
		return (EReference)widgetEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidget_DisplayAfterEventDependsOnConditionScript() {
		return (EReference)widgetEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidget_InputExpression() {
		return (EReference)widgetEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidget_AfterEventExpression() {
		return (EReference)widgetEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidget_Tooltip() {
		return (EReference)widgetEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidget_HelpMessage() {
		return (EReference)widgetEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidget_DisplayLabel() {
		return (EReference)widgetEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidget_InjectWidgetScript() {
		return (EReference)widgetEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWidget_Action() {
		return (EReference)widgetEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGroup() {
		return groupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGroup_Widgets() {
		return (EReference)groupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGroup_ShowBorder() {
		return (EAttribute)groupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGroup_Columns() {
		return (EReference)groupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGroup_Lines() {
		return (EReference)groupEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGroup_UseIterator() {
		return (EAttribute)groupEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGroup_Iterator() {
		return (EReference)groupEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFormField() {
		return formFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFormField_Description() {
		return (EAttribute)formFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFormField_ExampleMessagePosition() {
		return (EAttribute)formFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFormField_ExampleMessage() {
		return (EReference)formFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMultipleValuatedFormField() {
		return multipleValuatedFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMultipleValuatedFormField_DefaultExpression() {
		return (EReference)multipleValuatedFormFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMultipleValuatedFormField_DefaultExpressionAfterEvent() {
		return (EReference)multipleValuatedFormFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSingleValuatedFormField() {
		return singleValuatedFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCheckBoxMultipleFormField() {
		return checkBoxMultipleFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComboFormField() {
		return comboFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDateFormField() {
		return dateFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDateFormField_InitialFormat() {
		return (EAttribute)dateFormFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDateFormField_DisplayFormat() {
		return (EAttribute)dateFormFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getListFormField() {
		return listFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getListFormField_MaxHeigth() {
		return (EAttribute)listFormFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPasswordFormField() {
		return passwordFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPasswordFormField_MaxLength() {
		return (EAttribute)passwordFormFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRadioFormField() {
		return radioFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSelectFormField() {
		return selectFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTextFormField() {
		return textFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTextFormField_MaxLength() {
		return (EAttribute)textFormFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTextAreaFormField() {
		return textAreaFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTextAreaFormField_MaxLength() {
		return (EAttribute)textAreaFormFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTextAreaFormField_MaxHeigth() {
		return (EAttribute)textAreaFormFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRichTextAreaFormField() {
		return richTextAreaFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFormButton() {
		return formButtonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFormButton_LabelBehavior() {
		return (EAttribute)formButtonEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSubmitFormButton() {
		return submitFormButtonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSubmitFormButton_Actions() {
		return (EReference)submitFormButtonEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPreviousFormButton() {
		return previousFormButtonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNextFormButton() {
		return nextFormButtonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInfo() {
		return infoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTextInfo() {
		return textInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMessageInfo() {
		return messageInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCheckBoxSingleFormField() {
		return checkBoxSingleFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFileWidget() {
		return fileWidgetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileWidget_DownloadOnly() {
		return (EAttribute)fileWidgetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileWidget_UsePreview() {
		return (EAttribute)fileWidgetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFileWidget_Document() {
		return (EReference)fileWidgetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileWidget_InitialResourcePath() {
		return (EAttribute)fileWidgetEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileWidget_OutputDocumentName() {
		return (EAttribute)fileWidgetEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileWidget_UpdateDocument() {
		return (EAttribute)fileWidgetEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileWidget_IntialResourceList() {
		return (EAttribute)fileWidgetEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileWidget_InputType() {
		return (EAttribute)fileWidgetEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFileWidget_OutputDocumentListExpression() {
		return (EReference)fileWidgetEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFileWidget_DownloadType() {
		return (EAttribute)fileWidgetEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getImageWidget() {
		return imageWidgetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getImageWidget_IsADocument() {
		return (EAttribute)imageWidgetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getImageWidget_Document() {
		return (EReference)imageWidgetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getImageWidget_ImgPath() {
		return (EReference)imageWidgetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHiddenWidget() {
		return hiddenWidgetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDurationFormField() {
		return durationFormFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDurationFormField_Day() {
		return (EAttribute)durationFormFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDurationFormField_Hour() {
		return (EAttribute)durationFormFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDurationFormField_Min() {
		return (EAttribute)durationFormFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDurationFormField_Sec() {
		return (EAttribute)durationFormFieldEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractTable() {
		return abstractTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractTable_LeftColumnIsHeader() {
		return (EAttribute)abstractTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractTable_RightColumnIsHeader() {
		return (EAttribute)abstractTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractTable_FirstRowIsHeader() {
		return (EAttribute)abstractTableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractTable_LastRowIsHeader() {
		return (EAttribute)abstractTableEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractTable_InitializedUsingCells() {
		return (EAttribute)abstractTableEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractTable_UseHorizontalHeader() {
		return (EAttribute)abstractTableEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAbstractTable_UseVerticalHeader() {
		return (EAttribute)abstractTableEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractTable_HorizontalHeaderExpression() {
		return (EReference)abstractTableEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractTable_VerticalHeaderExpression() {
		return (EReference)abstractTableEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractTable_TableExpression() {
		return (EReference)abstractTableEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTable() {
		return tableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTable_UsePagination() {
		return (EAttribute)tableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTable_AllowSelection() {
		return (EAttribute)tableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTable_SelectionModeIsMultiple() {
		return (EAttribute)tableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTable_MaxRowForPagination() {
		return (EReference)tableEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTable_ColumnForInitialSelectionIndex() {
		return (EReference)tableEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTable_SelectedValues() {
		return (EReference)tableEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDynamicTable() {
		return dynamicTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDynamicTable_LimitMinNumberOfColumn() {
		return (EAttribute)dynamicTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDynamicTable_LimitMinNumberOfRow() {
		return (EAttribute)dynamicTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDynamicTable_AllowAddRemoveRow() {
		return (EAttribute)dynamicTableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDynamicTable_AllowAddRemoveColumn() {
		return (EAttribute)dynamicTableEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDynamicTable_LimitMaxNumberOfColumn() {
		return (EAttribute)dynamicTableEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDynamicTable_LimitMaxNumberOfRow() {
		return (EAttribute)dynamicTableEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDynamicTable_MinNumberOfColumn() {
		return (EReference)dynamicTableEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDynamicTable_MinNumberOfRow() {
		return (EReference)dynamicTableEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDynamicTable_MaxNumberOfColumn() {
		return (EReference)dynamicTableEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDynamicTable_MaxNumberOfRow() {
		return (EReference)dynamicTableEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIFrameWidget() {
		return iFrameWidgetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMandatoryFieldsCustomization() {
		return mandatoryFieldsCustomizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMandatoryFieldsCustomization_MandatorySymbol() {
		return (EReference)mandatoryFieldsCustomizationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMandatoryFieldsCustomization_MandatoryLabel() {
		return (EReference)mandatoryFieldsCustomizationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHtmlWidget() {
		return htmlWidgetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSuggestBox() {
		return suggestBoxEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSuggestBox_MaxItems() {
		return (EAttribute)suggestBoxEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSuggestBox_UseMaxItems() {
		return (EAttribute)suggestBoxEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSuggestBox_Asynchronous() {
		return (EAttribute)suggestBoxEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSuggestBox_Delay() {
		return (EAttribute)suggestBoxEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGroupIterator() {
		return groupIteratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGroupIterator_ClassName() {
		return (EAttribute)groupIteratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getEventDependencyType() {
		return eventDependencyTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getLabelPosition() {
		return labelPositionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getFileWidgetInputType() {
		return fileWidgetInputTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getFileWidgetDownloadType() {
		return fileWidgetDownloadTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FormFactory getFormFactory() {
		return (FormFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		widgetDependencyEClass = createEClass(WIDGET_DEPENDENCY);
		createEAttribute(widgetDependencyEClass, WIDGET_DEPENDENCY__TRIGGER_REFRESH_ON_MODIFICATION);
		createEAttribute(widgetDependencyEClass, WIDGET_DEPENDENCY__EVENT_TYPES);
		createEReference(widgetDependencyEClass, WIDGET_DEPENDENCY__WIDGET);

		validatorEClass = createEClass(VALIDATOR);
		createEAttribute(validatorEClass, VALIDATOR__VALIDATOR_CLASS);
		createEAttribute(validatorEClass, VALIDATOR__HTML_CLASS);
		createEAttribute(validatorEClass, VALIDATOR__NAME);
		createEAttribute(validatorEClass, VALIDATOR__BELOW_FIELD);
		createEReference(validatorEClass, VALIDATOR__PARAMETER);
		createEReference(validatorEClass, VALIDATOR__DISPLAY_NAME);

		validableEClass = createEClass(VALIDABLE);
		createEReference(validableEClass, VALIDABLE__VALIDATORS);
		createEAttribute(validableEClass, VALIDABLE__USE_DEFAULT_VALIDATOR);
		createEAttribute(validableEClass, VALIDABLE__BELOW);

		formEClass = createEClass(FORM);
		createEAttribute(formEClass, FORM__NCOLUMN);
		createEReference(formEClass, FORM__STRING_ATTRIBUTES);
		createEAttribute(formEClass, FORM__NLINE);
		createEAttribute(formEClass, FORM__SHOW_PAGE_LABEL);
		createEAttribute(formEClass, FORM__ALLOW_HTML_IN_PAGE_LABEL);
		createEAttribute(formEClass, FORM__VERSION);
		createEReference(formEClass, FORM__COLUMNS);
		createEReference(formEClass, FORM__LINES);
		createEReference(formEClass, FORM__WIDGETS);
		createEReference(formEClass, FORM__PAGE_LABEL);
		createEReference(formEClass, FORM__ACTIONS);

		widgetLayoutInfoEClass = createEClass(WIDGET_LAYOUT_INFO);
		createEAttribute(widgetLayoutInfoEClass, WIDGET_LAYOUT_INFO__LINE);
		createEAttribute(widgetLayoutInfoEClass, WIDGET_LAYOUT_INFO__COLUMN);
		createEAttribute(widgetLayoutInfoEClass, WIDGET_LAYOUT_INFO__VERTICAL_SPAN);
		createEAttribute(widgetLayoutInfoEClass, WIDGET_LAYOUT_INFO__HORIZONTAL_SPAN);

		columnEClass = createEClass(COLUMN);
		createEAttribute(columnEClass, COLUMN__WIDTH);
		createEAttribute(columnEClass, COLUMN__NUMBER);

		lineEClass = createEClass(LINE);
		createEAttribute(lineEClass, LINE__HEIGHT);
		createEAttribute(lineEClass, LINE__NUMBER);

		itemContainerEClass = createEClass(ITEM_CONTAINER);
		createEAttribute(itemContainerEClass, ITEM_CONTAINER__ITEM_CLASS);

		duplicableEClass = createEClass(DUPLICABLE);
		createEAttribute(duplicableEClass, DUPLICABLE__DUPLICATE);
		createEAttribute(duplicableEClass, DUPLICABLE__LIMIT_NUMBER_OF_DUPLICATION);
		createEAttribute(duplicableEClass, DUPLICABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION);
		createEReference(duplicableEClass, DUPLICABLE__MAX_NUMBER_OF_DUPLICATION);
		createEReference(duplicableEClass, DUPLICABLE__MIN_NUMBER_OF_DUPLICATION);
		createEReference(duplicableEClass, DUPLICABLE__DISPLAY_LABEL_FOR_ADD);
		createEReference(duplicableEClass, DUPLICABLE__TOOLTIP_FOR_ADD);
		createEReference(duplicableEClass, DUPLICABLE__DISPLAY_LABEL_FOR_REMOVE);
		createEReference(duplicableEClass, DUPLICABLE__TOOLTIP_FOR_REMOVE);

		viewFormEClass = createEClass(VIEW_FORM);

		cssCustomizableEClass = createEClass(CSS_CUSTOMIZABLE);
		createEReference(cssCustomizableEClass, CSS_CUSTOMIZABLE__HTML_ATTRIBUTES);

		widgetEClass = createEClass(WIDGET);
		createEReference(widgetEClass, WIDGET__WIDGET_LAYOUT_INFO);
		createEAttribute(widgetEClass, WIDGET__SHOW_DISPLAY_LABEL);
		createEAttribute(widgetEClass, WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL);
		createEReference(widgetEClass, WIDGET__DEPEND_ON);
		createEAttribute(widgetEClass, WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED);
		createEReference(widgetEClass, WIDGET__PARENT_OF);
		createEAttribute(widgetEClass, WIDGET__MANDATORY);
		createEAttribute(widgetEClass, WIDGET__READ_ONLY);
		createEAttribute(widgetEClass, WIDGET__LABEL_POSITION);
		createEAttribute(widgetEClass, WIDGET__REAL_HTML_ATTRIBUTES);
		createEAttribute(widgetEClass, WIDGET__INJECT_WIDGET_CONDITION);
		createEAttribute(widgetEClass, WIDGET__VERSION);
		createEAttribute(widgetEClass, WIDGET__RETURN_TYPE_MODIFIER);
		createEReference(widgetEClass, WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION);
		createEReference(widgetEClass, WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT);
		createEReference(widgetEClass, WIDGET__INPUT_EXPRESSION);
		createEReference(widgetEClass, WIDGET__AFTER_EVENT_EXPRESSION);
		createEReference(widgetEClass, WIDGET__TOOLTIP);
		createEReference(widgetEClass, WIDGET__HELP_MESSAGE);
		createEReference(widgetEClass, WIDGET__DISPLAY_LABEL);
		createEReference(widgetEClass, WIDGET__INJECT_WIDGET_SCRIPT);
		createEReference(widgetEClass, WIDGET__ACTION);

		groupEClass = createEClass(GROUP);
		createEReference(groupEClass, GROUP__WIDGETS);
		createEAttribute(groupEClass, GROUP__SHOW_BORDER);
		createEReference(groupEClass, GROUP__COLUMNS);
		createEReference(groupEClass, GROUP__LINES);
		createEAttribute(groupEClass, GROUP__USE_ITERATOR);
		createEReference(groupEClass, GROUP__ITERATOR);

		formFieldEClass = createEClass(FORM_FIELD);
		createEAttribute(formFieldEClass, FORM_FIELD__DESCRIPTION);
		createEAttribute(formFieldEClass, FORM_FIELD__EXAMPLE_MESSAGE_POSITION);
		createEReference(formFieldEClass, FORM_FIELD__EXAMPLE_MESSAGE);

		multipleValuatedFormFieldEClass = createEClass(MULTIPLE_VALUATED_FORM_FIELD);
		createEReference(multipleValuatedFormFieldEClass, MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION);
		createEReference(multipleValuatedFormFieldEClass, MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT);

		singleValuatedFormFieldEClass = createEClass(SINGLE_VALUATED_FORM_FIELD);

		checkBoxMultipleFormFieldEClass = createEClass(CHECK_BOX_MULTIPLE_FORM_FIELD);

		comboFormFieldEClass = createEClass(COMBO_FORM_FIELD);

		dateFormFieldEClass = createEClass(DATE_FORM_FIELD);
		createEAttribute(dateFormFieldEClass, DATE_FORM_FIELD__INITIAL_FORMAT);
		createEAttribute(dateFormFieldEClass, DATE_FORM_FIELD__DISPLAY_FORMAT);

		listFormFieldEClass = createEClass(LIST_FORM_FIELD);
		createEAttribute(listFormFieldEClass, LIST_FORM_FIELD__MAX_HEIGTH);

		passwordFormFieldEClass = createEClass(PASSWORD_FORM_FIELD);
		createEAttribute(passwordFormFieldEClass, PASSWORD_FORM_FIELD__MAX_LENGTH);

		radioFormFieldEClass = createEClass(RADIO_FORM_FIELD);

		selectFormFieldEClass = createEClass(SELECT_FORM_FIELD);

		textFormFieldEClass = createEClass(TEXT_FORM_FIELD);
		createEAttribute(textFormFieldEClass, TEXT_FORM_FIELD__MAX_LENGTH);

		textAreaFormFieldEClass = createEClass(TEXT_AREA_FORM_FIELD);
		createEAttribute(textAreaFormFieldEClass, TEXT_AREA_FORM_FIELD__MAX_LENGTH);
		createEAttribute(textAreaFormFieldEClass, TEXT_AREA_FORM_FIELD__MAX_HEIGTH);

		richTextAreaFormFieldEClass = createEClass(RICH_TEXT_AREA_FORM_FIELD);

		formButtonEClass = createEClass(FORM_BUTTON);
		createEAttribute(formButtonEClass, FORM_BUTTON__LABEL_BEHAVIOR);

		submitFormButtonEClass = createEClass(SUBMIT_FORM_BUTTON);
		createEReference(submitFormButtonEClass, SUBMIT_FORM_BUTTON__ACTIONS);

		previousFormButtonEClass = createEClass(PREVIOUS_FORM_BUTTON);

		nextFormButtonEClass = createEClass(NEXT_FORM_BUTTON);

		infoEClass = createEClass(INFO);

		textInfoEClass = createEClass(TEXT_INFO);

		messageInfoEClass = createEClass(MESSAGE_INFO);

		checkBoxSingleFormFieldEClass = createEClass(CHECK_BOX_SINGLE_FORM_FIELD);

		fileWidgetEClass = createEClass(FILE_WIDGET);
		createEAttribute(fileWidgetEClass, FILE_WIDGET__DOWNLOAD_ONLY);
		createEAttribute(fileWidgetEClass, FILE_WIDGET__USE_PREVIEW);
		createEReference(fileWidgetEClass, FILE_WIDGET__DOCUMENT);
		createEAttribute(fileWidgetEClass, FILE_WIDGET__INITIAL_RESOURCE_PATH);
		createEAttribute(fileWidgetEClass, FILE_WIDGET__OUTPUT_DOCUMENT_NAME);
		createEAttribute(fileWidgetEClass, FILE_WIDGET__UPDATE_DOCUMENT);
		createEAttribute(fileWidgetEClass, FILE_WIDGET__INTIAL_RESOURCE_LIST);
		createEAttribute(fileWidgetEClass, FILE_WIDGET__INPUT_TYPE);
		createEReference(fileWidgetEClass, FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION);
		createEAttribute(fileWidgetEClass, FILE_WIDGET__DOWNLOAD_TYPE);

		imageWidgetEClass = createEClass(IMAGE_WIDGET);
		createEAttribute(imageWidgetEClass, IMAGE_WIDGET__IS_ADOCUMENT);
		createEReference(imageWidgetEClass, IMAGE_WIDGET__DOCUMENT);
		createEReference(imageWidgetEClass, IMAGE_WIDGET__IMG_PATH);

		hiddenWidgetEClass = createEClass(HIDDEN_WIDGET);

		durationFormFieldEClass = createEClass(DURATION_FORM_FIELD);
		createEAttribute(durationFormFieldEClass, DURATION_FORM_FIELD__DAY);
		createEAttribute(durationFormFieldEClass, DURATION_FORM_FIELD__HOUR);
		createEAttribute(durationFormFieldEClass, DURATION_FORM_FIELD__MIN);
		createEAttribute(durationFormFieldEClass, DURATION_FORM_FIELD__SEC);

		abstractTableEClass = createEClass(ABSTRACT_TABLE);
		createEAttribute(abstractTableEClass, ABSTRACT_TABLE__LEFT_COLUMN_IS_HEADER);
		createEAttribute(abstractTableEClass, ABSTRACT_TABLE__RIGHT_COLUMN_IS_HEADER);
		createEAttribute(abstractTableEClass, ABSTRACT_TABLE__FIRST_ROW_IS_HEADER);
		createEAttribute(abstractTableEClass, ABSTRACT_TABLE__LAST_ROW_IS_HEADER);
		createEAttribute(abstractTableEClass, ABSTRACT_TABLE__INITIALIZED_USING_CELLS);
		createEAttribute(abstractTableEClass, ABSTRACT_TABLE__USE_HORIZONTAL_HEADER);
		createEAttribute(abstractTableEClass, ABSTRACT_TABLE__USE_VERTICAL_HEADER);
		createEReference(abstractTableEClass, ABSTRACT_TABLE__HORIZONTAL_HEADER_EXPRESSION);
		createEReference(abstractTableEClass, ABSTRACT_TABLE__VERTICAL_HEADER_EXPRESSION);
		createEReference(abstractTableEClass, ABSTRACT_TABLE__TABLE_EXPRESSION);

		tableEClass = createEClass(TABLE);
		createEAttribute(tableEClass, TABLE__USE_PAGINATION);
		createEAttribute(tableEClass, TABLE__ALLOW_SELECTION);
		createEAttribute(tableEClass, TABLE__SELECTION_MODE_IS_MULTIPLE);
		createEReference(tableEClass, TABLE__MAX_ROW_FOR_PAGINATION);
		createEReference(tableEClass, TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX);
		createEReference(tableEClass, TABLE__SELECTED_VALUES);

		dynamicTableEClass = createEClass(DYNAMIC_TABLE);
		createEAttribute(dynamicTableEClass, DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_COLUMN);
		createEAttribute(dynamicTableEClass, DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_ROW);
		createEAttribute(dynamicTableEClass, DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW);
		createEAttribute(dynamicTableEClass, DYNAMIC_TABLE__ALLOW_ADD_REMOVE_COLUMN);
		createEAttribute(dynamicTableEClass, DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_COLUMN);
		createEAttribute(dynamicTableEClass, DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_ROW);
		createEReference(dynamicTableEClass, DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN);
		createEReference(dynamicTableEClass, DYNAMIC_TABLE__MIN_NUMBER_OF_ROW);
		createEReference(dynamicTableEClass, DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN);
		createEReference(dynamicTableEClass, DYNAMIC_TABLE__MAX_NUMBER_OF_ROW);

		iFrameWidgetEClass = createEClass(IFRAME_WIDGET);

		mandatoryFieldsCustomizationEClass = createEClass(MANDATORY_FIELDS_CUSTOMIZATION);
		createEReference(mandatoryFieldsCustomizationEClass, MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL);
		createEReference(mandatoryFieldsCustomizationEClass, MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL);

		htmlWidgetEClass = createEClass(HTML_WIDGET);

		suggestBoxEClass = createEClass(SUGGEST_BOX);
		createEAttribute(suggestBoxEClass, SUGGEST_BOX__MAX_ITEMS);
		createEAttribute(suggestBoxEClass, SUGGEST_BOX__USE_MAX_ITEMS);
		createEAttribute(suggestBoxEClass, SUGGEST_BOX__ASYNCHRONOUS);
		createEAttribute(suggestBoxEClass, SUGGEST_BOX__DELAY);

		groupIteratorEClass = createEClass(GROUP_ITERATOR);
		createEAttribute(groupIteratorEClass, GROUP_ITERATOR__CLASS_NAME);

		// Create enums
		eventDependencyTypeEEnum = createEEnum(EVENT_DEPENDENCY_TYPE);
		labelPositionEEnum = createEEnum(LABEL_POSITION);
		fileWidgetInputTypeEEnum = createEEnum(FILE_WIDGET_INPUT_TYPE);
		fileWidgetDownloadTypeEEnum = createEEnum(FILE_WIDGET_DOWNLOAD_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ExpressionPackage theExpressionPackage = (ExpressionPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);
		ProcessPackage theProcessPackage = (ProcessPackage)EPackage.Registry.INSTANCE.getEPackage(ProcessPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		formEClass.getESuperTypes().add(theProcessPackage.getConnectableElement());
		formEClass.getESuperTypes().add(this.getValidable());
		viewFormEClass.getESuperTypes().add(this.getForm());
		widgetEClass.getESuperTypes().add(theProcessPackage.getElement());
		widgetEClass.getESuperTypes().add(this.getCSSCustomizable());
		groupEClass.getESuperTypes().add(this.getWidget());
		groupEClass.getESuperTypes().add(this.getDuplicable());
		formFieldEClass.getESuperTypes().add(this.getWidget());
		formFieldEClass.getESuperTypes().add(this.getValidable());
		formFieldEClass.getESuperTypes().add(this.getDuplicable());
		multipleValuatedFormFieldEClass.getESuperTypes().add(this.getFormField());
		singleValuatedFormFieldEClass.getESuperTypes().add(this.getFormField());
		checkBoxMultipleFormFieldEClass.getESuperTypes().add(this.getMultipleValuatedFormField());
		checkBoxMultipleFormFieldEClass.getESuperTypes().add(this.getItemContainer());
		comboFormFieldEClass.getESuperTypes().add(this.getMultipleValuatedFormField());
		dateFormFieldEClass.getESuperTypes().add(this.getSingleValuatedFormField());
		listFormFieldEClass.getESuperTypes().add(this.getMultipleValuatedFormField());
		passwordFormFieldEClass.getESuperTypes().add(this.getSingleValuatedFormField());
		radioFormFieldEClass.getESuperTypes().add(this.getMultipleValuatedFormField());
		radioFormFieldEClass.getESuperTypes().add(this.getItemContainer());
		selectFormFieldEClass.getESuperTypes().add(this.getMultipleValuatedFormField());
		textFormFieldEClass.getESuperTypes().add(this.getSingleValuatedFormField());
		textAreaFormFieldEClass.getESuperTypes().add(this.getSingleValuatedFormField());
		richTextAreaFormFieldEClass.getESuperTypes().add(this.getSingleValuatedFormField());
		formButtonEClass.getESuperTypes().add(this.getWidget());
		submitFormButtonEClass.getESuperTypes().add(this.getFormButton());
		submitFormButtonEClass.getESuperTypes().add(theProcessPackage.getConnectableElement());
		previousFormButtonEClass.getESuperTypes().add(this.getFormButton());
		nextFormButtonEClass.getESuperTypes().add(this.getFormButton());
		infoEClass.getESuperTypes().add(this.getWidget());
		textInfoEClass.getESuperTypes().add(this.getInfo());
		textInfoEClass.getESuperTypes().add(this.getDuplicable());
		messageInfoEClass.getESuperTypes().add(this.getInfo());
		checkBoxSingleFormFieldEClass.getESuperTypes().add(this.getSingleValuatedFormField());
		fileWidgetEClass.getESuperTypes().add(this.getSingleValuatedFormField());
		fileWidgetEClass.getESuperTypes().add(this.getDuplicable());
		imageWidgetEClass.getESuperTypes().add(this.getWidget());
		imageWidgetEClass.getESuperTypes().add(this.getDuplicable());
		hiddenWidgetEClass.getESuperTypes().add(this.getSingleValuatedFormField());
		hiddenWidgetEClass.getESuperTypes().add(this.getDuplicable());
		durationFormFieldEClass.getESuperTypes().add(this.getSingleValuatedFormField());
		durationFormFieldEClass.getESuperTypes().add(this.getItemContainer());
		abstractTableEClass.getESuperTypes().add(this.getWidget());
		abstractTableEClass.getESuperTypes().add(this.getDuplicable());
		tableEClass.getESuperTypes().add(this.getAbstractTable());
		tableEClass.getESuperTypes().add(this.getMultipleValuatedFormField());
		dynamicTableEClass.getESuperTypes().add(this.getAbstractTable());
		dynamicTableEClass.getESuperTypes().add(this.getSingleValuatedFormField());
		iFrameWidgetEClass.getESuperTypes().add(this.getInfo());
		mandatoryFieldsCustomizationEClass.getESuperTypes().add(this.getCSSCustomizable());
		htmlWidgetEClass.getESuperTypes().add(this.getInfo());
		suggestBoxEClass.getESuperTypes().add(this.getMultipleValuatedFormField());
		groupIteratorEClass.getESuperTypes().add(theProcessPackage.getElement());

		// Initialize classes and features; add operations and parameters
		initEClass(widgetDependencyEClass, WidgetDependency.class, "WidgetDependency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getWidgetDependency_TriggerRefreshOnModification(), ecorePackage.getEBoolean(), "triggerRefreshOnModification", "true", 0, 1, WidgetDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getWidgetDependency_EventTypes(), this.getEventDependencyType(), "eventTypes", "onChange", 1, -1, WidgetDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getWidgetDependency_Widget(), this.getWidget(), null, "widget", null, 1, 1, WidgetDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(validatorEClass, Validator.class, "Validator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getValidator_ValidatorClass(), ecorePackage.getEString(), "validatorClass", null, 0, 1, Validator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getValidator_HtmlClass(), ecorePackage.getEString(), "htmlClass", null, 0, 1, Validator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getValidator_Name(), ecorePackage.getEString(), "name", null, 0, 1, Validator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getValidator_BelowField(), ecorePackage.getEBoolean(), "belowField", "true", 0, 1, Validator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getValidator_Parameter(), theExpressionPackage.getExpression(), null, "parameter", null, 0, 1, Validator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getValidator_DisplayName(), theExpressionPackage.getExpression(), null, "displayName", null, 0, 1, Validator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(validableEClass, Validable.class, "Validable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getValidable_Validators(), this.getValidator(), null, "validators", null, 0, -1, Validable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getValidable_UseDefaultValidator(), ecorePackage.getEBooleanObject(), "useDefaultValidator", "true", 0, 1, Validable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getValidable_Below(), ecorePackage.getEBoolean(), "below", "true", 1, 1, Validable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(formEClass, Form.class, "Form", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getForm_NColumn(), ecorePackage.getEInt(), "nColumn", "1", 0, 1, Form.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getForm_StringAttributes(), ecorePackage.getEStringToStringMapEntry(), null, "stringAttributes", null, 0, -1, Form.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getForm_NLine(), ecorePackage.getEInt(), "nLine", "4", 0, 1, Form.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getForm_ShowPageLabel(), ecorePackage.getEBooleanObject(), "showPageLabel", "true", 0, 1, Form.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getForm_AllowHTMLInPageLabel(), ecorePackage.getEBoolean(), "allowHTMLInPageLabel", "false", 0, 1, Form.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getForm_Version(), ecorePackage.getEString(), "version", null, 0, 1, Form.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getForm_Columns(), this.getColumn(), null, "columns", null, 0, -1, Form.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getForm_Lines(), this.getLine(), null, "lines", null, 0, -1, Form.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getForm_Widgets(), this.getWidget(), null, "widgets", null, 0, -1, Form.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getForm_PageLabel(), theExpressionPackage.getExpression(), null, "pageLabel", null, 0, 1, Form.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getForm_Actions(), theExpressionPackage.getOperation(), null, "actions", null, 0, -1, Form.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(widgetLayoutInfoEClass, WidgetLayoutInfo.class, "WidgetLayoutInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getWidgetLayoutInfo_Line(), ecorePackage.getEInt(), "line", null, 0, 1, WidgetLayoutInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWidgetLayoutInfo_Column(), ecorePackage.getEInt(), "column", null, 0, 1, WidgetLayoutInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWidgetLayoutInfo_VerticalSpan(), ecorePackage.getEInt(), "verticalSpan", "1", 0, 1, WidgetLayoutInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getWidgetLayoutInfo_HorizontalSpan(), ecorePackage.getEInt(), "horizontalSpan", "1", 0, 1, WidgetLayoutInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(columnEClass, Column.class, "Column", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getColumn_Width(), ecorePackage.getEString(), "width", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getColumn_Number(), ecorePackage.getEInt(), "number", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(lineEClass, Line.class, "Line", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLine_Height(), ecorePackage.getEString(), "height", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLine_Number(), ecorePackage.getEInt(), "number", null, 0, 1, Line.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(itemContainerEClass, ItemContainer.class, "ItemContainer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getItemContainer_ItemClass(), ecorePackage.getEString(), "itemClass", null, 0, 1, ItemContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(duplicableEClass, Duplicable.class, "Duplicable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDuplicable_Duplicate(), ecorePackage.getEBoolean(), "duplicate", "false", 1, 1, Duplicable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDuplicable_LimitNumberOfDuplication(), ecorePackage.getEBoolean(), "limitNumberOfDuplication", "false", 0, 1, Duplicable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDuplicable_LimitMinNumberOfDuplication(), ecorePackage.getEBoolean(), "limitMinNumberOfDuplication", "false", 0, 1, Duplicable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getDuplicable_MaxNumberOfDuplication(), theExpressionPackage.getExpression(), null, "maxNumberOfDuplication", null, 0, 1, Duplicable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDuplicable_MinNumberOfDuplication(), theExpressionPackage.getExpression(), null, "minNumberOfDuplication", null, 0, 1, Duplicable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDuplicable_DisplayLabelForAdd(), theExpressionPackage.getExpression(), null, "displayLabelForAdd", null, 0, 1, Duplicable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDuplicable_TooltipForAdd(), theExpressionPackage.getExpression(), null, "tooltipForAdd", null, 0, 1, Duplicable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDuplicable_DisplayLabelForRemove(), theExpressionPackage.getExpression(), null, "displayLabelForRemove", null, 0, 1, Duplicable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDuplicable_TooltipForRemove(), theExpressionPackage.getExpression(), null, "tooltipForRemove", null, 0, 1, Duplicable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(viewFormEClass, ViewForm.class, "ViewForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(cssCustomizableEClass, CSSCustomizable.class, "CSSCustomizable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCSSCustomizable_HtmlAttributes(), ecorePackage.getEStringToStringMapEntry(), null, "htmlAttributes", null, 0, -1, CSSCustomizable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(widgetEClass, Widget.class, "Widget", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getWidget_WidgetLayoutInfo(), this.getWidgetLayoutInfo(), null, "widgetLayoutInfo", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWidget_ShowDisplayLabel(), ecorePackage.getEBooleanObject(), "showDisplayLabel", "true", 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getWidget_AllowHTMLForDisplayLabel(), ecorePackage.getEBoolean(), "allowHTMLForDisplayLabel", "false", 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getWidget_DependOn(), this.getWidgetDependency(), null, "dependOn", null, 0, -1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWidget_DisplayDependentWidgetOnlyOnEventTriggered(), ecorePackage.getEBoolean(), "displayDependentWidgetOnlyOnEventTriggered", "false", 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getWidget_ParentOf(), this.getWidgetDependency(), null, "parentOf", null, 0, -1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWidget_Mandatory(), ecorePackage.getEBoolean(), "mandatory", "false", 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getWidget_ReadOnly(), ecorePackage.getEBoolean(), "readOnly", "false", 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getWidget_LabelPosition(), this.getLabelPosition(), "labelPosition", "Up", 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getWidget_RealHtmlAttributes(), ecorePackage.getEString(), "realHtmlAttributes", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWidget_InjectWidgetCondition(), ecorePackage.getEBoolean(), "injectWidgetCondition", "false", 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getWidget_Version(), ecorePackage.getEString(), "version", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getWidget_ReturnTypeModifier(), ecorePackage.getEString(), "returnTypeModifier", "java.lang.String", 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getWidget_DisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition(), theExpressionPackage.getExpression(), null, "displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWidget_DisplayAfterEventDependsOnConditionScript(), theExpressionPackage.getExpression(), null, "displayAfterEventDependsOnConditionScript", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWidget_InputExpression(), theExpressionPackage.getExpression(), null, "inputExpression", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWidget_AfterEventExpression(), theExpressionPackage.getExpression(), null, "afterEventExpression", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWidget_Tooltip(), theExpressionPackage.getExpression(), null, "tooltip", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWidget_HelpMessage(), theExpressionPackage.getExpression(), null, "helpMessage", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWidget_DisplayLabel(), theExpressionPackage.getExpression(), null, "displayLabel", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWidget_InjectWidgetScript(), theExpressionPackage.getExpression(), null, "injectWidgetScript", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWidget_Action(), theExpressionPackage.getOperation(), null, "action", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(groupEClass, Group.class, "Group", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getGroup_Widgets(), this.getWidget(), null, "widgets", null, 0, -1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getGroup_ShowBorder(), ecorePackage.getEBoolean(), "showBorder", "false", 1, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getGroup_Columns(), this.getColumn(), null, "columns", null, 0, -1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getGroup_Lines(), this.getLine(), null, "lines", null, 0, -1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getGroup_UseIterator(), ecorePackage.getEBoolean(), "useIterator", "false", 1, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getGroup_Iterator(), this.getGroupIterator(), null, "iterator", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(formFieldEClass, FormField.class, "FormField", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFormField_Description(), ecorePackage.getEString(), "description", null, 0, 1, FormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFormField_ExampleMessagePosition(), this.getLabelPosition(), "exampleMessagePosition", "Down", 0, 1, FormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getFormField_ExampleMessage(), theExpressionPackage.getExpression(), null, "exampleMessage", null, 0, 1, FormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(multipleValuatedFormFieldEClass, MultipleValuatedFormField.class, "MultipleValuatedFormField", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMultipleValuatedFormField_DefaultExpression(), theExpressionPackage.getExpression(), null, "defaultExpression", null, 0, 1, MultipleValuatedFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMultipleValuatedFormField_DefaultExpressionAfterEvent(), theExpressionPackage.getExpression(), null, "defaultExpressionAfterEvent", null, 0, 1, MultipleValuatedFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(singleValuatedFormFieldEClass, SingleValuatedFormField.class, "SingleValuatedFormField", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(checkBoxMultipleFormFieldEClass, CheckBoxMultipleFormField.class, "CheckBoxMultipleFormField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(comboFormFieldEClass, ComboFormField.class, "ComboFormField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(dateFormFieldEClass, DateFormField.class, "DateFormField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDateFormField_InitialFormat(), ecorePackage.getEString(), "initialFormat", null, 0, 1, DateFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDateFormField_DisplayFormat(), ecorePackage.getEString(), "displayFormat", null, 0, 1, DateFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(listFormFieldEClass, ListFormField.class, "ListFormField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getListFormField_MaxHeigth(), ecorePackage.getEInt(), "maxHeigth", null, 0, 1, ListFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(passwordFormFieldEClass, PasswordFormField.class, "PasswordFormField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPasswordFormField_MaxLength(), ecorePackage.getEInt(), "maxLength", null, 0, 1, PasswordFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(radioFormFieldEClass, RadioFormField.class, "RadioFormField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(selectFormFieldEClass, SelectFormField.class, "SelectFormField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(textFormFieldEClass, TextFormField.class, "TextFormField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTextFormField_MaxLength(), ecorePackage.getEInt(), "maxLength", null, 0, 1, TextFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(textAreaFormFieldEClass, TextAreaFormField.class, "TextAreaFormField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTextAreaFormField_MaxLength(), ecorePackage.getEInt(), "maxLength", null, 0, 1, TextAreaFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTextAreaFormField_MaxHeigth(), ecorePackage.getEInt(), "maxHeigth", null, 0, 1, TextAreaFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(richTextAreaFormFieldEClass, RichTextAreaFormField.class, "RichTextAreaFormField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(formButtonEClass, FormButton.class, "FormButton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFormButton_LabelBehavior(), ecorePackage.getEBooleanObject(), "labelBehavior", "false", 0, 1, FormButton.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(submitFormButtonEClass, SubmitFormButton.class, "SubmitFormButton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSubmitFormButton_Actions(), theExpressionPackage.getOperation(), null, "actions", null, 0, -1, SubmitFormButton.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(previousFormButtonEClass, PreviousFormButton.class, "PreviousFormButton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(nextFormButtonEClass, NextFormButton.class, "NextFormButton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(infoEClass, Info.class, "Info", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(textInfoEClass, TextInfo.class, "TextInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(messageInfoEClass, MessageInfo.class, "MessageInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(checkBoxSingleFormFieldEClass, CheckBoxSingleFormField.class, "CheckBoxSingleFormField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(fileWidgetEClass, FileWidget.class, "FileWidget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFileWidget_DownloadOnly(), ecorePackage.getEBoolean(), "downloadOnly", "false", 0, 1, FileWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getFileWidget_UsePreview(), ecorePackage.getEBoolean(), "usePreview", "false", 0, 1, FileWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getFileWidget_Document(), theProcessPackage.getDocument(), null, "document", null, 0, 1, FileWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFileWidget_InitialResourcePath(), ecorePackage.getEString(), "initialResourcePath", null, 0, 1, FileWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFileWidget_OutputDocumentName(), ecorePackage.getEString(), "outputDocumentName", null, 0, 1, FileWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFileWidget_UpdateDocument(), ecorePackage.getEBoolean(), "updateDocument", "true", 0, 1, FileWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getFileWidget_IntialResourceList(), ecorePackage.getEString(), "intialResourceList", null, 0, -1, FileWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFileWidget_InputType(), this.getFileWidgetInputType(), "inputType", "URL", 0, 1, FileWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getFileWidget_OutputDocumentListExpression(), theExpressionPackage.getExpression(), null, "outputDocumentListExpression", null, 0, 1, FileWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getFileWidget_DownloadType(), this.getFileWidgetDownloadType(), "downloadType", null, 0, 1, FileWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(imageWidgetEClass, ImageWidget.class, "ImageWidget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getImageWidget_IsADocument(), ecorePackage.getEBoolean(), "isADocument", "false", 0, 1, ImageWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getImageWidget_Document(), theProcessPackage.getDocument(), null, "document", null, 0, 1, ImageWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getImageWidget_ImgPath(), theExpressionPackage.getExpression(), null, "imgPath", null, 0, 1, ImageWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(hiddenWidgetEClass, HiddenWidget.class, "HiddenWidget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(durationFormFieldEClass, DurationFormField.class, "DurationFormField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDurationFormField_Day(), ecorePackage.getEBooleanObject(), "day", "true", 1, 1, DurationFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDurationFormField_Hour(), ecorePackage.getEBooleanObject(), "hour", "true", 1, 1, DurationFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDurationFormField_Min(), ecorePackage.getEBooleanObject(), "min", "true", 1, 1, DurationFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDurationFormField_Sec(), ecorePackage.getEBooleanObject(), "sec", "true", 1, 1, DurationFormField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(abstractTableEClass, AbstractTable.class, "AbstractTable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getAbstractTable_LeftColumnIsHeader(), ecorePackage.getEBoolean(), "leftColumnIsHeader", null, 0, 1, AbstractTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAbstractTable_RightColumnIsHeader(), ecorePackage.getEBoolean(), "rightColumnIsHeader", null, 0, 1, AbstractTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAbstractTable_FirstRowIsHeader(), ecorePackage.getEBoolean(), "firstRowIsHeader", null, 0, 1, AbstractTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAbstractTable_LastRowIsHeader(), ecorePackage.getEBoolean(), "LastRowIsHeader", null, 0, 1, AbstractTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAbstractTable_InitializedUsingCells(), ecorePackage.getEBoolean(), "initializedUsingCells", null, 0, 1, AbstractTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAbstractTable_UseHorizontalHeader(), ecorePackage.getEBoolean(), "useHorizontalHeader", "false", 0, 1, AbstractTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getAbstractTable_UseVerticalHeader(), ecorePackage.getEBoolean(), "useVerticalHeader", "false", 0, 1, AbstractTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getAbstractTable_HorizontalHeaderExpression(), theExpressionPackage.getExpression(), null, "horizontalHeaderExpression", null, 0, 1, AbstractTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAbstractTable_VerticalHeaderExpression(), theExpressionPackage.getExpression(), null, "verticalHeaderExpression", null, 0, 1, AbstractTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAbstractTable_TableExpression(), theExpressionPackage.getTableExpression(), null, "tableExpression", null, 0, 1, AbstractTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(tableEClass, Table.class, "Table", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTable_UsePagination(), ecorePackage.getEBoolean(), "usePagination", "false", 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getTable_AllowSelection(), ecorePackage.getEBoolean(), "allowSelection", "false", 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getTable_SelectionModeIsMultiple(), ecorePackage.getEBoolean(), "selectionModeIsMultiple", "true", 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getTable_MaxRowForPagination(), theExpressionPackage.getExpression(), null, "maxRowForPagination", null, 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTable_ColumnForInitialSelectionIndex(), theExpressionPackage.getExpression(), null, "columnForInitialSelectionIndex", null, 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTable_SelectedValues(), theExpressionPackage.getExpression(), null, "selectedValues", null, 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(dynamicTableEClass, DynamicTable.class, "DynamicTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDynamicTable_LimitMinNumberOfColumn(), ecorePackage.getEBoolean(), "limitMinNumberOfColumn", "false", 0, 1, DynamicTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDynamicTable_LimitMinNumberOfRow(), ecorePackage.getEBoolean(), "limitMinNumberOfRow", "false", 0, 1, DynamicTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDynamicTable_AllowAddRemoveRow(), ecorePackage.getEBoolean(), "allowAddRemoveRow", "true", 0, 1, DynamicTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDynamicTable_AllowAddRemoveColumn(), ecorePackage.getEBoolean(), "allowAddRemoveColumn", "true", 0, 1, DynamicTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDynamicTable_LimitMaxNumberOfColumn(), ecorePackage.getEBoolean(), "limitMaxNumberOfColumn", "false", 0, 1, DynamicTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDynamicTable_LimitMaxNumberOfRow(), ecorePackage.getEBoolean(), "limitMaxNumberOfRow", "false", 0, 1, DynamicTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getDynamicTable_MinNumberOfColumn(), theExpressionPackage.getExpression(), null, "minNumberOfColumn", null, 0, 1, DynamicTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDynamicTable_MinNumberOfRow(), theExpressionPackage.getExpression(), null, "minNumberOfRow", null, 0, 1, DynamicTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDynamicTable_MaxNumberOfColumn(), theExpressionPackage.getExpression(), null, "maxNumberOfColumn", null, 0, 1, DynamicTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDynamicTable_MaxNumberOfRow(), theExpressionPackage.getExpression(), null, "maxNumberOfRow", null, 0, 1, DynamicTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(iFrameWidgetEClass, IFrameWidget.class, "IFrameWidget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(mandatoryFieldsCustomizationEClass, MandatoryFieldsCustomization.class, "MandatoryFieldsCustomization", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMandatoryFieldsCustomization_MandatorySymbol(), theExpressionPackage.getExpression(), null, "mandatorySymbol", null, 0, 1, MandatoryFieldsCustomization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMandatoryFieldsCustomization_MandatoryLabel(), theExpressionPackage.getExpression(), null, "mandatoryLabel", null, 0, 1, MandatoryFieldsCustomization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(htmlWidgetEClass, HtmlWidget.class, "HtmlWidget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(suggestBoxEClass, SuggestBox.class, "SuggestBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSuggestBox_MaxItems(), ecorePackage.getEInt(), "maxItems", "-1", 1, 1, SuggestBox.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSuggestBox_UseMaxItems(), ecorePackage.getEBoolean(), "useMaxItems", "false", 1, 1, SuggestBox.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSuggestBox_Asynchronous(), ecorePackage.getEBoolean(), "asynchronous", "false", 1, 1, SuggestBox.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSuggestBox_Delay(), ecorePackage.getEInt(), "delay", "1000", 0, 1, SuggestBox.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(groupIteratorEClass, GroupIterator.class, "GroupIterator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getGroupIterator_ClassName(), ecorePackage.getEString(), "className", null, 0, 1, GroupIterator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(eventDependencyTypeEEnum, EventDependencyType.class, "EventDependencyType"); //$NON-NLS-1$
		addEEnumLiteral(eventDependencyTypeEEnum, EventDependencyType.ON_VALUE_CHANGE);
		addEEnumLiteral(eventDependencyTypeEEnum, EventDependencyType.ON_CHANGE);
		addEEnumLiteral(eventDependencyTypeEEnum, EventDependencyType.ON_BLUR);
		addEEnumLiteral(eventDependencyTypeEEnum, EventDependencyType.ON_CLICK);

		initEEnum(labelPositionEEnum, LabelPosition.class, "LabelPosition"); //$NON-NLS-1$
		addEEnumLiteral(labelPositionEEnum, LabelPosition.LEFT);
		addEEnumLiteral(labelPositionEEnum, LabelPosition.RIGHT);
		addEEnumLiteral(labelPositionEEnum, LabelPosition.UP);
		addEEnumLiteral(labelPositionEEnum, LabelPosition.DOWN);

		initEEnum(fileWidgetInputTypeEEnum, FileWidgetInputType.class, "FileWidgetInputType"); //$NON-NLS-1$
		addEEnumLiteral(fileWidgetInputTypeEEnum, FileWidgetInputType.DOCUMENT);
		addEEnumLiteral(fileWidgetInputTypeEEnum, FileWidgetInputType.URL);
		addEEnumLiteral(fileWidgetInputTypeEEnum, FileWidgetInputType.RESOURCE);

		initEEnum(fileWidgetDownloadTypeEEnum, FileWidgetDownloadType.class, "FileWidgetDownloadType"); //$NON-NLS-1$
		addEEnumLiteral(fileWidgetDownloadTypeEEnum, FileWidgetDownloadType.BOTH);
		addEEnumLiteral(fileWidgetDownloadTypeEEnum, FileWidgetDownloadType.URL);
		addEEnumLiteral(fileWidgetDownloadTypeEEnum, FileWidgetDownloadType.BROWSE);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/edapt
		createEdaptAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/edapt</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEdaptAnnotations() {
		String source = "http://www.eclipse.org/edapt"; //$NON-NLS-1$
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "historyURI", "process.history" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}

} //FormPackageImpl
