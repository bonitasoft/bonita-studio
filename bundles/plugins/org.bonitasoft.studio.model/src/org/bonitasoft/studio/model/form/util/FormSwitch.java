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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.form.FormPackage
 * @generated
 */
public class FormSwitch<T> extends Switch<T> {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static FormPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FormSwitch() {
        if (modelPackage == null) {
            modelPackage = FormPackage.eINSTANCE;
        }
    }

	/**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case FormPackage.WIDGET_DEPENDENCY: {
                WidgetDependency widgetDependency = (WidgetDependency)theEObject;
                T result = caseWidgetDependency(widgetDependency);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.VALIDATOR: {
                Validator validator = (Validator)theEObject;
                T result = caseValidator(validator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.VALIDABLE: {
                Validable validable = (Validable)theEObject;
                T result = caseValidable(validable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.FORM: {
                Form form = (Form)theEObject;
                T result = caseForm(form);
                if (result == null) result = caseConnectableElement(form);
                if (result == null) result = caseValidable(form);
                if (result == null) result = caseElement(form);
                if (result == null) result = caseDataAware(form);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.WIDGET_LAYOUT_INFO: {
                WidgetLayoutInfo widgetLayoutInfo = (WidgetLayoutInfo)theEObject;
                T result = caseWidgetLayoutInfo(widgetLayoutInfo);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.COLUMN: {
                Column column = (Column)theEObject;
                T result = caseColumn(column);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.LINE: {
                Line line = (Line)theEObject;
                T result = caseLine(line);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.ITEM_CONTAINER: {
                ItemContainer itemContainer = (ItemContainer)theEObject;
                T result = caseItemContainer(itemContainer);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.DUPLICABLE: {
                Duplicable duplicable = (Duplicable)theEObject;
                T result = caseDuplicable(duplicable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.VIEW_FORM: {
                ViewForm viewForm = (ViewForm)theEObject;
                T result = caseViewForm(viewForm);
                if (result == null) result = caseForm(viewForm);
                if (result == null) result = caseConnectableElement(viewForm);
                if (result == null) result = caseValidable(viewForm);
                if (result == null) result = caseElement(viewForm);
                if (result == null) result = caseDataAware(viewForm);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.CSS_CUSTOMIZABLE: {
                CSSCustomizable cssCustomizable = (CSSCustomizable)theEObject;
                T result = caseCSSCustomizable(cssCustomizable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.WIDGET: {
                Widget widget = (Widget)theEObject;
                T result = caseWidget(widget);
                if (result == null) result = caseElement(widget);
                if (result == null) result = caseCSSCustomizable(widget);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.GROUP: {
                Group group = (Group)theEObject;
                T result = caseGroup(group);
                if (result == null) result = caseWidget(group);
                if (result == null) result = caseDuplicable(group);
                if (result == null) result = caseElement(group);
                if (result == null) result = caseCSSCustomizable(group);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.FORM_FIELD: {
                FormField formField = (FormField)theEObject;
                T result = caseFormField(formField);
                if (result == null) result = caseWidget(formField);
                if (result == null) result = caseValidable(formField);
                if (result == null) result = caseDuplicable(formField);
                if (result == null) result = caseElement(formField);
                if (result == null) result = caseCSSCustomizable(formField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.MULTIPLE_VALUATED_FORM_FIELD: {
                MultipleValuatedFormField multipleValuatedFormField = (MultipleValuatedFormField)theEObject;
                T result = caseMultipleValuatedFormField(multipleValuatedFormField);
                if (result == null) result = caseFormField(multipleValuatedFormField);
                if (result == null) result = caseWidget(multipleValuatedFormField);
                if (result == null) result = caseValidable(multipleValuatedFormField);
                if (result == null) result = caseDuplicable(multipleValuatedFormField);
                if (result == null) result = caseElement(multipleValuatedFormField);
                if (result == null) result = caseCSSCustomizable(multipleValuatedFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.SINGLE_VALUATED_FORM_FIELD: {
                SingleValuatedFormField singleValuatedFormField = (SingleValuatedFormField)theEObject;
                T result = caseSingleValuatedFormField(singleValuatedFormField);
                if (result == null) result = caseFormField(singleValuatedFormField);
                if (result == null) result = caseWidget(singleValuatedFormField);
                if (result == null) result = caseValidable(singleValuatedFormField);
                if (result == null) result = caseDuplicable(singleValuatedFormField);
                if (result == null) result = caseElement(singleValuatedFormField);
                if (result == null) result = caseCSSCustomizable(singleValuatedFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.CHECK_BOX_MULTIPLE_FORM_FIELD: {
                CheckBoxMultipleFormField checkBoxMultipleFormField = (CheckBoxMultipleFormField)theEObject;
                T result = caseCheckBoxMultipleFormField(checkBoxMultipleFormField);
                if (result == null) result = caseMultipleValuatedFormField(checkBoxMultipleFormField);
                if (result == null) result = caseItemContainer(checkBoxMultipleFormField);
                if (result == null) result = caseFormField(checkBoxMultipleFormField);
                if (result == null) result = caseWidget(checkBoxMultipleFormField);
                if (result == null) result = caseValidable(checkBoxMultipleFormField);
                if (result == null) result = caseDuplicable(checkBoxMultipleFormField);
                if (result == null) result = caseElement(checkBoxMultipleFormField);
                if (result == null) result = caseCSSCustomizable(checkBoxMultipleFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.COMBO_FORM_FIELD: {
                ComboFormField comboFormField = (ComboFormField)theEObject;
                T result = caseComboFormField(comboFormField);
                if (result == null) result = caseMultipleValuatedFormField(comboFormField);
                if (result == null) result = caseFormField(comboFormField);
                if (result == null) result = caseWidget(comboFormField);
                if (result == null) result = caseValidable(comboFormField);
                if (result == null) result = caseDuplicable(comboFormField);
                if (result == null) result = caseElement(comboFormField);
                if (result == null) result = caseCSSCustomizable(comboFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.DATE_FORM_FIELD: {
                DateFormField dateFormField = (DateFormField)theEObject;
                T result = caseDateFormField(dateFormField);
                if (result == null) result = caseSingleValuatedFormField(dateFormField);
                if (result == null) result = caseFormField(dateFormField);
                if (result == null) result = caseWidget(dateFormField);
                if (result == null) result = caseValidable(dateFormField);
                if (result == null) result = caseDuplicable(dateFormField);
                if (result == null) result = caseElement(dateFormField);
                if (result == null) result = caseCSSCustomizable(dateFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.LIST_FORM_FIELD: {
                ListFormField listFormField = (ListFormField)theEObject;
                T result = caseListFormField(listFormField);
                if (result == null) result = caseMultipleValuatedFormField(listFormField);
                if (result == null) result = caseFormField(listFormField);
                if (result == null) result = caseWidget(listFormField);
                if (result == null) result = caseValidable(listFormField);
                if (result == null) result = caseDuplicable(listFormField);
                if (result == null) result = caseElement(listFormField);
                if (result == null) result = caseCSSCustomizable(listFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.PASSWORD_FORM_FIELD: {
                PasswordFormField passwordFormField = (PasswordFormField)theEObject;
                T result = casePasswordFormField(passwordFormField);
                if (result == null) result = caseSingleValuatedFormField(passwordFormField);
                if (result == null) result = caseFormField(passwordFormField);
                if (result == null) result = caseWidget(passwordFormField);
                if (result == null) result = caseValidable(passwordFormField);
                if (result == null) result = caseDuplicable(passwordFormField);
                if (result == null) result = caseElement(passwordFormField);
                if (result == null) result = caseCSSCustomizable(passwordFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.RADIO_FORM_FIELD: {
                RadioFormField radioFormField = (RadioFormField)theEObject;
                T result = caseRadioFormField(radioFormField);
                if (result == null) result = caseMultipleValuatedFormField(radioFormField);
                if (result == null) result = caseItemContainer(radioFormField);
                if (result == null) result = caseFormField(radioFormField);
                if (result == null) result = caseWidget(radioFormField);
                if (result == null) result = caseValidable(radioFormField);
                if (result == null) result = caseDuplicable(radioFormField);
                if (result == null) result = caseElement(radioFormField);
                if (result == null) result = caseCSSCustomizable(radioFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.SELECT_FORM_FIELD: {
                SelectFormField selectFormField = (SelectFormField)theEObject;
                T result = caseSelectFormField(selectFormField);
                if (result == null) result = caseMultipleValuatedFormField(selectFormField);
                if (result == null) result = caseFormField(selectFormField);
                if (result == null) result = caseWidget(selectFormField);
                if (result == null) result = caseValidable(selectFormField);
                if (result == null) result = caseDuplicable(selectFormField);
                if (result == null) result = caseElement(selectFormField);
                if (result == null) result = caseCSSCustomizable(selectFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.TEXT_FORM_FIELD: {
                TextFormField textFormField = (TextFormField)theEObject;
                T result = caseTextFormField(textFormField);
                if (result == null) result = caseSingleValuatedFormField(textFormField);
                if (result == null) result = caseFormField(textFormField);
                if (result == null) result = caseWidget(textFormField);
                if (result == null) result = caseValidable(textFormField);
                if (result == null) result = caseDuplicable(textFormField);
                if (result == null) result = caseElement(textFormField);
                if (result == null) result = caseCSSCustomizable(textFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.TEXT_AREA_FORM_FIELD: {
                TextAreaFormField textAreaFormField = (TextAreaFormField)theEObject;
                T result = caseTextAreaFormField(textAreaFormField);
                if (result == null) result = caseSingleValuatedFormField(textAreaFormField);
                if (result == null) result = caseFormField(textAreaFormField);
                if (result == null) result = caseWidget(textAreaFormField);
                if (result == null) result = caseValidable(textAreaFormField);
                if (result == null) result = caseDuplicable(textAreaFormField);
                if (result == null) result = caseElement(textAreaFormField);
                if (result == null) result = caseCSSCustomizable(textAreaFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.RICH_TEXT_AREA_FORM_FIELD: {
                RichTextAreaFormField richTextAreaFormField = (RichTextAreaFormField)theEObject;
                T result = caseRichTextAreaFormField(richTextAreaFormField);
                if (result == null) result = caseSingleValuatedFormField(richTextAreaFormField);
                if (result == null) result = caseFormField(richTextAreaFormField);
                if (result == null) result = caseWidget(richTextAreaFormField);
                if (result == null) result = caseValidable(richTextAreaFormField);
                if (result == null) result = caseDuplicable(richTextAreaFormField);
                if (result == null) result = caseElement(richTextAreaFormField);
                if (result == null) result = caseCSSCustomizable(richTextAreaFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.FORM_BUTTON: {
                FormButton formButton = (FormButton)theEObject;
                T result = caseFormButton(formButton);
                if (result == null) result = caseWidget(formButton);
                if (result == null) result = caseElement(formButton);
                if (result == null) result = caseCSSCustomizable(formButton);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.SUBMIT_FORM_BUTTON: {
                SubmitFormButton submitFormButton = (SubmitFormButton)theEObject;
                T result = caseSubmitFormButton(submitFormButton);
                if (result == null) result = caseFormButton(submitFormButton);
                if (result == null) result = caseConnectableElement(submitFormButton);
                if (result == null) result = caseWidget(submitFormButton);
                if (result == null) result = caseDataAware(submitFormButton);
                if (result == null) result = caseElement(submitFormButton);
                if (result == null) result = caseCSSCustomizable(submitFormButton);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.PREVIOUS_FORM_BUTTON: {
                PreviousFormButton previousFormButton = (PreviousFormButton)theEObject;
                T result = casePreviousFormButton(previousFormButton);
                if (result == null) result = caseFormButton(previousFormButton);
                if (result == null) result = caseWidget(previousFormButton);
                if (result == null) result = caseElement(previousFormButton);
                if (result == null) result = caseCSSCustomizable(previousFormButton);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.NEXT_FORM_BUTTON: {
                NextFormButton nextFormButton = (NextFormButton)theEObject;
                T result = caseNextFormButton(nextFormButton);
                if (result == null) result = caseFormButton(nextFormButton);
                if (result == null) result = caseWidget(nextFormButton);
                if (result == null) result = caseElement(nextFormButton);
                if (result == null) result = caseCSSCustomizable(nextFormButton);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.INFO: {
                Info info = (Info)theEObject;
                T result = caseInfo(info);
                if (result == null) result = caseWidget(info);
                if (result == null) result = caseElement(info);
                if (result == null) result = caseCSSCustomizable(info);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.TEXT_INFO: {
                TextInfo textInfo = (TextInfo)theEObject;
                T result = caseTextInfo(textInfo);
                if (result == null) result = caseInfo(textInfo);
                if (result == null) result = caseDuplicable(textInfo);
                if (result == null) result = caseWidget(textInfo);
                if (result == null) result = caseElement(textInfo);
                if (result == null) result = caseCSSCustomizable(textInfo);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.MESSAGE_INFO: {
                MessageInfo messageInfo = (MessageInfo)theEObject;
                T result = caseMessageInfo(messageInfo);
                if (result == null) result = caseInfo(messageInfo);
                if (result == null) result = caseWidget(messageInfo);
                if (result == null) result = caseElement(messageInfo);
                if (result == null) result = caseCSSCustomizable(messageInfo);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.CHECK_BOX_SINGLE_FORM_FIELD: {
                CheckBoxSingleFormField checkBoxSingleFormField = (CheckBoxSingleFormField)theEObject;
                T result = caseCheckBoxSingleFormField(checkBoxSingleFormField);
                if (result == null) result = caseSingleValuatedFormField(checkBoxSingleFormField);
                if (result == null) result = caseFormField(checkBoxSingleFormField);
                if (result == null) result = caseWidget(checkBoxSingleFormField);
                if (result == null) result = caseValidable(checkBoxSingleFormField);
                if (result == null) result = caseDuplicable(checkBoxSingleFormField);
                if (result == null) result = caseElement(checkBoxSingleFormField);
                if (result == null) result = caseCSSCustomizable(checkBoxSingleFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.FILE_WIDGET: {
                FileWidget fileWidget = (FileWidget)theEObject;
                T result = caseFileWidget(fileWidget);
                if (result == null) result = caseSingleValuatedFormField(fileWidget);
                if (result == null) result = caseFormField(fileWidget);
                if (result == null) result = caseWidget(fileWidget);
                if (result == null) result = caseValidable(fileWidget);
                if (result == null) result = caseDuplicable(fileWidget);
                if (result == null) result = caseElement(fileWidget);
                if (result == null) result = caseCSSCustomizable(fileWidget);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.IMAGE_WIDGET: {
                ImageWidget imageWidget = (ImageWidget)theEObject;
                T result = caseImageWidget(imageWidget);
                if (result == null) result = caseWidget(imageWidget);
                if (result == null) result = caseDuplicable(imageWidget);
                if (result == null) result = caseElement(imageWidget);
                if (result == null) result = caseCSSCustomizable(imageWidget);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.HIDDEN_WIDGET: {
                HiddenWidget hiddenWidget = (HiddenWidget)theEObject;
                T result = caseHiddenWidget(hiddenWidget);
                if (result == null) result = caseSingleValuatedFormField(hiddenWidget);
                if (result == null) result = caseFormField(hiddenWidget);
                if (result == null) result = caseWidget(hiddenWidget);
                if (result == null) result = caseValidable(hiddenWidget);
                if (result == null) result = caseDuplicable(hiddenWidget);
                if (result == null) result = caseElement(hiddenWidget);
                if (result == null) result = caseCSSCustomizable(hiddenWidget);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.DURATION_FORM_FIELD: {
                DurationFormField durationFormField = (DurationFormField)theEObject;
                T result = caseDurationFormField(durationFormField);
                if (result == null) result = caseSingleValuatedFormField(durationFormField);
                if (result == null) result = caseItemContainer(durationFormField);
                if (result == null) result = caseFormField(durationFormField);
                if (result == null) result = caseWidget(durationFormField);
                if (result == null) result = caseValidable(durationFormField);
                if (result == null) result = caseDuplicable(durationFormField);
                if (result == null) result = caseElement(durationFormField);
                if (result == null) result = caseCSSCustomizable(durationFormField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.ABSTRACT_TABLE: {
                AbstractTable abstractTable = (AbstractTable)theEObject;
                T result = caseAbstractTable(abstractTable);
                if (result == null) result = caseWidget(abstractTable);
                if (result == null) result = caseDuplicable(abstractTable);
                if (result == null) result = caseElement(abstractTable);
                if (result == null) result = caseCSSCustomizable(abstractTable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.TABLE: {
                Table table = (Table)theEObject;
                T result = caseTable(table);
                if (result == null) result = caseAbstractTable(table);
                if (result == null) result = caseMultipleValuatedFormField(table);
                if (result == null) result = caseFormField(table);
                if (result == null) result = caseWidget(table);
                if (result == null) result = caseElement(table);
                if (result == null) result = caseCSSCustomizable(table);
                if (result == null) result = caseDuplicable(table);
                if (result == null) result = caseValidable(table);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.DYNAMIC_TABLE: {
                DynamicTable dynamicTable = (DynamicTable)theEObject;
                T result = caseDynamicTable(dynamicTable);
                if (result == null) result = caseAbstractTable(dynamicTable);
                if (result == null) result = caseSingleValuatedFormField(dynamicTable);
                if (result == null) result = caseFormField(dynamicTable);
                if (result == null) result = caseWidget(dynamicTable);
                if (result == null) result = caseElement(dynamicTable);
                if (result == null) result = caseCSSCustomizable(dynamicTable);
                if (result == null) result = caseDuplicable(dynamicTable);
                if (result == null) result = caseValidable(dynamicTable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.IFRAME_WIDGET: {
                IFrameWidget iFrameWidget = (IFrameWidget)theEObject;
                T result = caseIFrameWidget(iFrameWidget);
                if (result == null) result = caseInfo(iFrameWidget);
                if (result == null) result = caseWidget(iFrameWidget);
                if (result == null) result = caseElement(iFrameWidget);
                if (result == null) result = caseCSSCustomizable(iFrameWidget);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.MANDATORY_FIELDS_CUSTOMIZATION: {
                MandatoryFieldsCustomization mandatoryFieldsCustomization = (MandatoryFieldsCustomization)theEObject;
                T result = caseMandatoryFieldsCustomization(mandatoryFieldsCustomization);
                if (result == null) result = caseCSSCustomizable(mandatoryFieldsCustomization);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.HTML_WIDGET: {
                HtmlWidget htmlWidget = (HtmlWidget)theEObject;
                T result = caseHtmlWidget(htmlWidget);
                if (result == null) result = caseInfo(htmlWidget);
                if (result == null) result = caseWidget(htmlWidget);
                if (result == null) result = caseElement(htmlWidget);
                if (result == null) result = caseCSSCustomizable(htmlWidget);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.SUGGEST_BOX: {
                SuggestBox suggestBox = (SuggestBox)theEObject;
                T result = caseSuggestBox(suggestBox);
                if (result == null) result = caseMultipleValuatedFormField(suggestBox);
                if (result == null) result = caseFormField(suggestBox);
                if (result == null) result = caseWidget(suggestBox);
                if (result == null) result = caseValidable(suggestBox);
                if (result == null) result = caseDuplicable(suggestBox);
                if (result == null) result = caseElement(suggestBox);
                if (result == null) result = caseCSSCustomizable(suggestBox);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case FormPackage.GROUP_ITERATOR: {
                GroupIterator groupIterator = (GroupIterator)theEObject;
                T result = caseGroupIterator(groupIterator);
                if (result == null) result = caseElement(groupIterator);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Widget Dependency</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Widget Dependency</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseWidgetDependency(WidgetDependency object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Validator</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Validator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseValidator(Validator object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Validable</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Validable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseValidable(Validable object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Form</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Form</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseForm(Form object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Widget Layout Info</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Widget Layout Info</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseWidgetLayoutInfo(WidgetLayoutInfo object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Column</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Column</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseColumn(Column object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Line</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Line</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseLine(Line object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Item Container</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Item Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseItemContainer(ItemContainer object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Duplicable</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Duplicable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDuplicable(Duplicable object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>View Form</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>View Form</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseViewForm(ViewForm object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>CSS Customizable</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CSS Customizable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCSSCustomizable(CSSCustomizable object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Widget</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Widget</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseWidget(Widget object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Group</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseGroup(Group object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseFormField(FormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Multiple Valuated Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Multiple Valuated Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMultipleValuatedFormField(MultipleValuatedFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Single Valuated Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Single Valuated Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSingleValuatedFormField(SingleValuatedFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Check Box Multiple Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Check Box Multiple Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCheckBoxMultipleFormField(CheckBoxMultipleFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Combo Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Combo Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseComboFormField(ComboFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Date Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Date Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDateFormField(DateFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>List Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>List Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseListFormField(ListFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Password Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Password Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T casePasswordFormField(PasswordFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Radio Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Radio Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseRadioFormField(RadioFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Select Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Select Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSelectFormField(SelectFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Text Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTextFormField(TextFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Text Area Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Area Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTextAreaFormField(TextAreaFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Rich Text Area Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Rich Text Area Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseRichTextAreaFormField(RichTextAreaFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Button</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Button</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseFormButton(FormButton object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Submit Form Button</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Submit Form Button</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSubmitFormButton(SubmitFormButton object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Previous Form Button</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Previous Form Button</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T casePreviousFormButton(PreviousFormButton object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Next Form Button</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Next Form Button</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseNextFormButton(NextFormButton object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Info</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Info</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseInfo(Info object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Text Info</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Info</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTextInfo(TextInfo object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Message Info</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message Info</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMessageInfo(MessageInfo object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Check Box Single Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Check Box Single Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCheckBoxSingleFormField(CheckBoxSingleFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>File Widget</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>File Widget</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseFileWidget(FileWidget object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Image Widget</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Image Widget</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseImageWidget(ImageWidget object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Hidden Widget</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Hidden Widget</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseHiddenWidget(HiddenWidget object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Duration Form Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Duration Form Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDurationFormField(DurationFormField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAbstractTable(AbstractTable object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTable(Table object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Dynamic Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dynamic Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDynamicTable(DynamicTable object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>IFrame Widget</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>IFrame Widget</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseIFrameWidget(IFrameWidget object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Mandatory Fields Customization</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Mandatory Fields Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMandatoryFieldsCustomization(MandatoryFieldsCustomization object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Html Widget</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Html Widget</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseHtmlWidget(HtmlWidget object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Suggest Box</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Suggest Box</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSuggestBox(SuggestBox object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Group Iterator</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group Iterator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseGroupIterator(GroupIterator object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseElement(Element object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Data Aware</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Aware</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDataAware(DataAware object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Connectable Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Connectable Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseConnectableElement(ConnectableElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
	@Override
	public T defaultCase(EObject object) {
        return null;
    }

} //FormSwitch
