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
package org.bonitasoft.studio.model.form;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.form.FormPackage
 * @generated
 */
public interface FormFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	FormFactory eINSTANCE = org.bonitasoft.studio.model.form.impl.FormFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Widget Dependency</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Widget Dependency</em>'.
     * @generated
     */
	WidgetDependency createWidgetDependency();

	/**
     * Returns a new object of class '<em>Validator</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Validator</em>'.
     * @generated
     */
	Validator createValidator();

	/**
     * Returns a new object of class '<em>Validable</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Validable</em>'.
     * @generated
     */
	Validable createValidable();

	/**
     * Returns a new object of class '<em>Form</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Form</em>'.
     * @generated
     */
	Form createForm();

	/**
     * Returns a new object of class '<em>Widget Layout Info</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Widget Layout Info</em>'.
     * @generated
     */
	WidgetLayoutInfo createWidgetLayoutInfo();

	/**
     * Returns a new object of class '<em>Column</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Column</em>'.
     * @generated
     */
	Column createColumn();

	/**
     * Returns a new object of class '<em>Line</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Line</em>'.
     * @generated
     */
	Line createLine();

	/**
     * Returns a new object of class '<em>View Form</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>View Form</em>'.
     * @generated
     */
	ViewForm createViewForm();

	/**
     * Returns a new object of class '<em>Group</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Group</em>'.
     * @generated
     */
	Group createGroup();

	/**
     * Returns a new object of class '<em>Check Box Multiple Form Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Check Box Multiple Form Field</em>'.
     * @generated
     */
	CheckBoxMultipleFormField createCheckBoxMultipleFormField();

	/**
     * Returns a new object of class '<em>Combo Form Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Combo Form Field</em>'.
     * @generated
     */
	ComboFormField createComboFormField();

	/**
     * Returns a new object of class '<em>Date Form Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Date Form Field</em>'.
     * @generated
     */
	DateFormField createDateFormField();

	/**
     * Returns a new object of class '<em>List Form Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>List Form Field</em>'.
     * @generated
     */
	ListFormField createListFormField();

	/**
     * Returns a new object of class '<em>Password Form Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Password Form Field</em>'.
     * @generated
     */
	PasswordFormField createPasswordFormField();

	/**
     * Returns a new object of class '<em>Radio Form Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Radio Form Field</em>'.
     * @generated
     */
	RadioFormField createRadioFormField();

	/**
     * Returns a new object of class '<em>Select Form Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Select Form Field</em>'.
     * @generated
     */
	SelectFormField createSelectFormField();

	/**
     * Returns a new object of class '<em>Text Form Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Text Form Field</em>'.
     * @generated
     */
	TextFormField createTextFormField();

	/**
     * Returns a new object of class '<em>Text Area Form Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Text Area Form Field</em>'.
     * @generated
     */
	TextAreaFormField createTextAreaFormField();

	/**
     * Returns a new object of class '<em>Rich Text Area Form Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Rich Text Area Form Field</em>'.
     * @generated
     */
	RichTextAreaFormField createRichTextAreaFormField();

	/**
     * Returns a new object of class '<em>Button</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Button</em>'.
     * @generated
     */
	FormButton createFormButton();

	/**
     * Returns a new object of class '<em>Submit Form Button</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Submit Form Button</em>'.
     * @generated
     */
	SubmitFormButton createSubmitFormButton();

	/**
     * Returns a new object of class '<em>Previous Form Button</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Previous Form Button</em>'.
     * @generated
     */
	PreviousFormButton createPreviousFormButton();

	/**
     * Returns a new object of class '<em>Next Form Button</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Next Form Button</em>'.
     * @generated
     */
	NextFormButton createNextFormButton();

	/**
     * Returns a new object of class '<em>Info</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Info</em>'.
     * @generated
     */
	Info createInfo();

	/**
     * Returns a new object of class '<em>Text Info</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Text Info</em>'.
     * @generated
     */
	TextInfo createTextInfo();

	/**
     * Returns a new object of class '<em>Message Info</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Message Info</em>'.
     * @generated
     */
	MessageInfo createMessageInfo();

	/**
     * Returns a new object of class '<em>Check Box Single Form Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Check Box Single Form Field</em>'.
     * @generated
     */
	CheckBoxSingleFormField createCheckBoxSingleFormField();

	/**
     * Returns a new object of class '<em>File Widget</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>File Widget</em>'.
     * @generated
     */
	FileWidget createFileWidget();

	/**
     * Returns a new object of class '<em>Image Widget</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Image Widget</em>'.
     * @generated
     */
	ImageWidget createImageWidget();

	/**
     * Returns a new object of class '<em>Hidden Widget</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Hidden Widget</em>'.
     * @generated
     */
	HiddenWidget createHiddenWidget();

	/**
     * Returns a new object of class '<em>Duration Form Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Duration Form Field</em>'.
     * @generated
     */
	DurationFormField createDurationFormField();

	/**
     * Returns a new object of class '<em>Table</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Table</em>'.
     * @generated
     */
	Table createTable();

	/**
     * Returns a new object of class '<em>Dynamic Table</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Dynamic Table</em>'.
     * @generated
     */
	DynamicTable createDynamicTable();

	/**
     * Returns a new object of class '<em>IFrame Widget</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>IFrame Widget</em>'.
     * @generated
     */
	IFrameWidget createIFrameWidget();

	/**
     * Returns a new object of class '<em>Html Widget</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Html Widget</em>'.
     * @generated
     */
	HtmlWidget createHtmlWidget();

	/**
     * Returns a new object of class '<em>Suggest Box</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Suggest Box</em>'.
     * @generated
     */
	SuggestBox createSuggestBox();

	/**
     * Returns a new object of class '<em>Group Iterator</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Group Iterator</em>'.
     * @generated
     */
	GroupIterator createGroupIterator();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	FormPackage getFormPackage();

} //FormFactory
