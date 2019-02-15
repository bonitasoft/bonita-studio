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
package org.bonitasoft.studio.model.form.util;

import org.bonitasoft.studio.model.form.*;

import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Element;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.form.FormPackage
 * @generated
 */
public class FormAdapterFactory extends AdapterFactoryImpl {
	/**
     * The cached model package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static FormPackage modelPackage;

	/**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FormAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = FormPackage.eINSTANCE;
        }
    }

	/**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
	@Override
	public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

	/**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected FormSwitch<Adapter> modelSwitch =
		new FormSwitch<Adapter>() {
            @Override
            public Adapter caseWidgetDependency(WidgetDependency object) {
                return createWidgetDependencyAdapter();
            }
            @Override
            public Adapter caseValidator(Validator object) {
                return createValidatorAdapter();
            }
            @Override
            public Adapter caseValidable(Validable object) {
                return createValidableAdapter();
            }
            @Override
            public Adapter caseForm(Form object) {
                return createFormAdapter();
            }
            @Override
            public Adapter caseWidgetLayoutInfo(WidgetLayoutInfo object) {
                return createWidgetLayoutInfoAdapter();
            }
            @Override
            public Adapter caseColumn(Column object) {
                return createColumnAdapter();
            }
            @Override
            public Adapter caseLine(Line object) {
                return createLineAdapter();
            }
            @Override
            public Adapter caseItemContainer(ItemContainer object) {
                return createItemContainerAdapter();
            }
            @Override
            public Adapter caseDuplicable(Duplicable object) {
                return createDuplicableAdapter();
            }
            @Override
            public Adapter caseViewForm(ViewForm object) {
                return createViewFormAdapter();
            }
            @Override
            public Adapter caseCSSCustomizable(CSSCustomizable object) {
                return createCSSCustomizableAdapter();
            }
            @Override
            public Adapter caseWidget(Widget object) {
                return createWidgetAdapter();
            }
            @Override
            public Adapter caseGroup(Group object) {
                return createGroupAdapter();
            }
            @Override
            public Adapter caseFormField(FormField object) {
                return createFormFieldAdapter();
            }
            @Override
            public Adapter caseMultipleValuatedFormField(MultipleValuatedFormField object) {
                return createMultipleValuatedFormFieldAdapter();
            }
            @Override
            public Adapter caseSingleValuatedFormField(SingleValuatedFormField object) {
                return createSingleValuatedFormFieldAdapter();
            }
            @Override
            public Adapter caseCheckBoxMultipleFormField(CheckBoxMultipleFormField object) {
                return createCheckBoxMultipleFormFieldAdapter();
            }
            @Override
            public Adapter caseComboFormField(ComboFormField object) {
                return createComboFormFieldAdapter();
            }
            @Override
            public Adapter caseDateFormField(DateFormField object) {
                return createDateFormFieldAdapter();
            }
            @Override
            public Adapter caseListFormField(ListFormField object) {
                return createListFormFieldAdapter();
            }
            @Override
            public Adapter casePasswordFormField(PasswordFormField object) {
                return createPasswordFormFieldAdapter();
            }
            @Override
            public Adapter caseRadioFormField(RadioFormField object) {
                return createRadioFormFieldAdapter();
            }
            @Override
            public Adapter caseSelectFormField(SelectFormField object) {
                return createSelectFormFieldAdapter();
            }
            @Override
            public Adapter caseTextFormField(TextFormField object) {
                return createTextFormFieldAdapter();
            }
            @Override
            public Adapter caseTextAreaFormField(TextAreaFormField object) {
                return createTextAreaFormFieldAdapter();
            }
            @Override
            public Adapter caseRichTextAreaFormField(RichTextAreaFormField object) {
                return createRichTextAreaFormFieldAdapter();
            }
            @Override
            public Adapter caseFormButton(FormButton object) {
                return createFormButtonAdapter();
            }
            @Override
            public Adapter caseSubmitFormButton(SubmitFormButton object) {
                return createSubmitFormButtonAdapter();
            }
            @Override
            public Adapter casePreviousFormButton(PreviousFormButton object) {
                return createPreviousFormButtonAdapter();
            }
            @Override
            public Adapter caseNextFormButton(NextFormButton object) {
                return createNextFormButtonAdapter();
            }
            @Override
            public Adapter caseInfo(Info object) {
                return createInfoAdapter();
            }
            @Override
            public Adapter caseTextInfo(TextInfo object) {
                return createTextInfoAdapter();
            }
            @Override
            public Adapter caseMessageInfo(MessageInfo object) {
                return createMessageInfoAdapter();
            }
            @Override
            public Adapter caseCheckBoxSingleFormField(CheckBoxSingleFormField object) {
                return createCheckBoxSingleFormFieldAdapter();
            }
            @Override
            public Adapter caseFileWidget(FileWidget object) {
                return createFileWidgetAdapter();
            }
            @Override
            public Adapter caseImageWidget(ImageWidget object) {
                return createImageWidgetAdapter();
            }
            @Override
            public Adapter caseHiddenWidget(HiddenWidget object) {
                return createHiddenWidgetAdapter();
            }
            @Override
            public Adapter caseDurationFormField(DurationFormField object) {
                return createDurationFormFieldAdapter();
            }
            @Override
            public Adapter caseAbstractTable(AbstractTable object) {
                return createAbstractTableAdapter();
            }
            @Override
            public Adapter caseTable(Table object) {
                return createTableAdapter();
            }
            @Override
            public Adapter caseDynamicTable(DynamicTable object) {
                return createDynamicTableAdapter();
            }
            @Override
            public Adapter caseIFrameWidget(IFrameWidget object) {
                return createIFrameWidgetAdapter();
            }
            @Override
            public Adapter caseMandatoryFieldsCustomization(MandatoryFieldsCustomization object) {
                return createMandatoryFieldsCustomizationAdapter();
            }
            @Override
            public Adapter caseHtmlWidget(HtmlWidget object) {
                return createHtmlWidgetAdapter();
            }
            @Override
            public Adapter caseSuggestBox(SuggestBox object) {
                return createSuggestBoxAdapter();
            }
            @Override
            public Adapter caseGroupIterator(GroupIterator object) {
                return createGroupIteratorAdapter();
            }
            @Override
            public Adapter caseElement(Element object) {
                return createElementAdapter();
            }
            @Override
            public Adapter caseDataAware(DataAware object) {
                return createDataAwareAdapter();
            }
            @Override
            public Adapter caseConnectableElement(ConnectableElement object) {
                return createConnectableElementAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

	/**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
	@Override
	public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.WidgetDependency <em>Widget Dependency</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.WidgetDependency
     * @generated
     */
	public Adapter createWidgetDependencyAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.Validator <em>Validator</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.Validator
     * @generated
     */
	public Adapter createValidatorAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.Validable <em>Validable</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.Validable
     * @generated
     */
	public Adapter createValidableAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.Form <em>Form</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.Form
     * @generated
     */
	public Adapter createFormAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo <em>Widget Layout Info</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.WidgetLayoutInfo
     * @generated
     */
	public Adapter createWidgetLayoutInfoAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.Column <em>Column</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.Column
     * @generated
     */
	public Adapter createColumnAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.Line <em>Line</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.Line
     * @generated
     */
	public Adapter createLineAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.ItemContainer <em>Item Container</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.ItemContainer
     * @generated
     */
	public Adapter createItemContainerAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.Duplicable <em>Duplicable</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.Duplicable
     * @generated
     */
	public Adapter createDuplicableAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.ViewForm <em>View Form</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.ViewForm
     * @generated
     */
	public Adapter createViewFormAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.CSSCustomizable <em>CSS Customizable</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.CSSCustomizable
     * @generated
     */
	public Adapter createCSSCustomizableAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.Widget <em>Widget</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.Widget
     * @generated
     */
	public Adapter createWidgetAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.Group <em>Group</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.Group
     * @generated
     */
	public Adapter createGroupAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.FormField <em>Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.FormField
     * @generated
     */
	public Adapter createFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.MultipleValuatedFormField <em>Multiple Valuated Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.MultipleValuatedFormField
     * @generated
     */
	public Adapter createMultipleValuatedFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.SingleValuatedFormField <em>Single Valuated Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.SingleValuatedFormField
     * @generated
     */
	public Adapter createSingleValuatedFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.CheckBoxMultipleFormField <em>Check Box Multiple Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.CheckBoxMultipleFormField
     * @generated
     */
	public Adapter createCheckBoxMultipleFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.ComboFormField <em>Combo Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.ComboFormField
     * @generated
     */
	public Adapter createComboFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.DateFormField <em>Date Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.DateFormField
     * @generated
     */
	public Adapter createDateFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.ListFormField <em>List Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.ListFormField
     * @generated
     */
	public Adapter createListFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.PasswordFormField <em>Password Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.PasswordFormField
     * @generated
     */
	public Adapter createPasswordFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.RadioFormField <em>Radio Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.RadioFormField
     * @generated
     */
	public Adapter createRadioFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.SelectFormField <em>Select Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.SelectFormField
     * @generated
     */
	public Adapter createSelectFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.TextFormField <em>Text Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.TextFormField
     * @generated
     */
	public Adapter createTextFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.TextAreaFormField <em>Text Area Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.TextAreaFormField
     * @generated
     */
	public Adapter createTextAreaFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.RichTextAreaFormField <em>Rich Text Area Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.RichTextAreaFormField
     * @generated
     */
	public Adapter createRichTextAreaFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.FormButton <em>Button</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.FormButton
     * @generated
     */
	public Adapter createFormButtonAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.SubmitFormButton <em>Submit Form Button</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.SubmitFormButton
     * @generated
     */
	public Adapter createSubmitFormButtonAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.PreviousFormButton <em>Previous Form Button</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.PreviousFormButton
     * @generated
     */
	public Adapter createPreviousFormButtonAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.NextFormButton <em>Next Form Button</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.NextFormButton
     * @generated
     */
	public Adapter createNextFormButtonAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.Info <em>Info</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.Info
     * @generated
     */
	public Adapter createInfoAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.TextInfo <em>Text Info</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.TextInfo
     * @generated
     */
	public Adapter createTextInfoAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.MessageInfo <em>Message Info</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.MessageInfo
     * @generated
     */
	public Adapter createMessageInfoAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.CheckBoxSingleFormField <em>Check Box Single Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.CheckBoxSingleFormField
     * @generated
     */
	public Adapter createCheckBoxSingleFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.FileWidget <em>File Widget</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.FileWidget
     * @generated
     */
	public Adapter createFileWidgetAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.ImageWidget <em>Image Widget</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.ImageWidget
     * @generated
     */
	public Adapter createImageWidgetAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.HiddenWidget <em>Hidden Widget</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.HiddenWidget
     * @generated
     */
	public Adapter createHiddenWidgetAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.DurationFormField <em>Duration Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.DurationFormField
     * @generated
     */
	public Adapter createDurationFormFieldAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.AbstractTable <em>Abstract Table</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.AbstractTable
     * @generated
     */
	public Adapter createAbstractTableAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.Table <em>Table</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.Table
     * @generated
     */
	public Adapter createTableAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.DynamicTable <em>Dynamic Table</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.DynamicTable
     * @generated
     */
	public Adapter createDynamicTableAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.IFrameWidget <em>IFrame Widget</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.IFrameWidget
     * @generated
     */
	public Adapter createIFrameWidgetAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.MandatoryFieldsCustomization <em>Mandatory Fields Customization</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.MandatoryFieldsCustomization
     * @generated
     */
	public Adapter createMandatoryFieldsCustomizationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.HtmlWidget <em>Html Widget</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.HtmlWidget
     * @generated
     */
	public Adapter createHtmlWidgetAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.SuggestBox <em>Suggest Box</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.SuggestBox
     * @generated
     */
	public Adapter createSuggestBoxAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.form.GroupIterator <em>Group Iterator</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.form.GroupIterator
     * @generated
     */
	public Adapter createGroupIteratorAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Element <em>Element</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Element
     * @generated
     */
	public Adapter createElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.DataAware <em>Data Aware</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.DataAware
     * @generated
     */
	public Adapter createDataAwareAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ConnectableElement <em>Connectable Element</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ConnectableElement
     * @generated
     */
	public Adapter createConnectableElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
	public Adapter createEObjectAdapter() {
        return null;
    }

} //FormAdapterFactory
