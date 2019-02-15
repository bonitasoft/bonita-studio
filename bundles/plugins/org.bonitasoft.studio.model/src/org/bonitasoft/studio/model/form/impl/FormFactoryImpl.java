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

import org.bonitasoft.studio.model.form.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FormFactoryImpl extends EFactoryImpl implements FormFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static FormFactory init() {
        try {
            FormFactory theFormFactory = (FormFactory)EPackage.Registry.INSTANCE.getEFactory(FormPackage.eNS_URI);
            if (theFormFactory != null) {
                return theFormFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new FormFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FormFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case FormPackage.WIDGET_DEPENDENCY: return createWidgetDependency();
            case FormPackage.VALIDATOR: return createValidator();
            case FormPackage.VALIDABLE: return createValidable();
            case FormPackage.FORM: return createForm();
            case FormPackage.WIDGET_LAYOUT_INFO: return createWidgetLayoutInfo();
            case FormPackage.COLUMN: return createColumn();
            case FormPackage.LINE: return createLine();
            case FormPackage.VIEW_FORM: return createViewForm();
            case FormPackage.GROUP: return createGroup();
            case FormPackage.CHECK_BOX_MULTIPLE_FORM_FIELD: return createCheckBoxMultipleFormField();
            case FormPackage.COMBO_FORM_FIELD: return createComboFormField();
            case FormPackage.DATE_FORM_FIELD: return createDateFormField();
            case FormPackage.LIST_FORM_FIELD: return createListFormField();
            case FormPackage.PASSWORD_FORM_FIELD: return createPasswordFormField();
            case FormPackage.RADIO_FORM_FIELD: return createRadioFormField();
            case FormPackage.SELECT_FORM_FIELD: return createSelectFormField();
            case FormPackage.TEXT_FORM_FIELD: return createTextFormField();
            case FormPackage.TEXT_AREA_FORM_FIELD: return createTextAreaFormField();
            case FormPackage.RICH_TEXT_AREA_FORM_FIELD: return createRichTextAreaFormField();
            case FormPackage.FORM_BUTTON: return createFormButton();
            case FormPackage.SUBMIT_FORM_BUTTON: return createSubmitFormButton();
            case FormPackage.PREVIOUS_FORM_BUTTON: return createPreviousFormButton();
            case FormPackage.NEXT_FORM_BUTTON: return createNextFormButton();
            case FormPackage.INFO: return createInfo();
            case FormPackage.TEXT_INFO: return createTextInfo();
            case FormPackage.MESSAGE_INFO: return createMessageInfo();
            case FormPackage.CHECK_BOX_SINGLE_FORM_FIELD: return createCheckBoxSingleFormField();
            case FormPackage.FILE_WIDGET: return createFileWidget();
            case FormPackage.IMAGE_WIDGET: return createImageWidget();
            case FormPackage.HIDDEN_WIDGET: return createHiddenWidget();
            case FormPackage.DURATION_FORM_FIELD: return createDurationFormField();
            case FormPackage.TABLE: return createTable();
            case FormPackage.DYNAMIC_TABLE: return createDynamicTable();
            case FormPackage.IFRAME_WIDGET: return createIFrameWidget();
            case FormPackage.HTML_WIDGET: return createHtmlWidget();
            case FormPackage.SUGGEST_BOX: return createSuggestBox();
            case FormPackage.GROUP_ITERATOR: return createGroupIterator();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case FormPackage.EVENT_DEPENDENCY_TYPE:
                return createEventDependencyTypeFromString(eDataType, initialValue);
            case FormPackage.LABEL_POSITION:
                return createLabelPositionFromString(eDataType, initialValue);
            case FormPackage.FILE_WIDGET_INPUT_TYPE:
                return createFileWidgetInputTypeFromString(eDataType, initialValue);
            case FormPackage.FILE_WIDGET_DOWNLOAD_TYPE:
                return createFileWidgetDownloadTypeFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case FormPackage.EVENT_DEPENDENCY_TYPE:
                return convertEventDependencyTypeToString(eDataType, instanceValue);
            case FormPackage.LABEL_POSITION:
                return convertLabelPositionToString(eDataType, instanceValue);
            case FormPackage.FILE_WIDGET_INPUT_TYPE:
                return convertFileWidgetInputTypeToString(eDataType, instanceValue);
            case FormPackage.FILE_WIDGET_DOWNLOAD_TYPE:
                return convertFileWidgetDownloadTypeToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public WidgetDependency createWidgetDependency() {
        WidgetDependencyImpl widgetDependency = new WidgetDependencyImpl();
        return widgetDependency;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Validator createValidator() {
        ValidatorImpl validator = new ValidatorImpl();
        return validator;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Validable createValidable() {
        ValidableImpl validable = new ValidableImpl();
        return validable;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Form createForm() {
        FormImpl form = new FormImpl();
        return form;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public WidgetLayoutInfo createWidgetLayoutInfo() {
        WidgetLayoutInfoImpl widgetLayoutInfo = new WidgetLayoutInfoImpl();
        return widgetLayoutInfo;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Column createColumn() {
        ColumnImpl column = new ColumnImpl();
        return column;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Line createLine() {
        LineImpl line = new LineImpl();
        return line;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ViewForm createViewForm() {
        ViewFormImpl viewForm = new ViewFormImpl();
        return viewForm;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Group createGroup() {
        GroupImpl group = new GroupImpl();
        return group;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public CheckBoxMultipleFormField createCheckBoxMultipleFormField() {
        CheckBoxMultipleFormFieldImpl checkBoxMultipleFormField = new CheckBoxMultipleFormFieldImpl();
        return checkBoxMultipleFormField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ComboFormField createComboFormField() {
        ComboFormFieldImpl comboFormField = new ComboFormFieldImpl();
        return comboFormField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public DateFormField createDateFormField() {
        DateFormFieldImpl dateFormField = new DateFormFieldImpl();
        return dateFormField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ListFormField createListFormField() {
        ListFormFieldImpl listFormField = new ListFormFieldImpl();
        return listFormField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public PasswordFormField createPasswordFormField() {
        PasswordFormFieldImpl passwordFormField = new PasswordFormFieldImpl();
        return passwordFormField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public RadioFormField createRadioFormField() {
        RadioFormFieldImpl radioFormField = new RadioFormFieldImpl();
        return radioFormField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public SelectFormField createSelectFormField() {
        SelectFormFieldImpl selectFormField = new SelectFormFieldImpl();
        return selectFormField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public TextFormField createTextFormField() {
        TextFormFieldImpl textFormField = new TextFormFieldImpl();
        return textFormField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public TextAreaFormField createTextAreaFormField() {
        TextAreaFormFieldImpl textAreaFormField = new TextAreaFormFieldImpl();
        return textAreaFormField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public RichTextAreaFormField createRichTextAreaFormField() {
        RichTextAreaFormFieldImpl richTextAreaFormField = new RichTextAreaFormFieldImpl();
        return richTextAreaFormField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public FormButton createFormButton() {
        FormButtonImpl formButton = new FormButtonImpl();
        return formButton;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public SubmitFormButton createSubmitFormButton() {
        SubmitFormButtonImpl submitFormButton = new SubmitFormButtonImpl();
        return submitFormButton;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public PreviousFormButton createPreviousFormButton() {
        PreviousFormButtonImpl previousFormButton = new PreviousFormButtonImpl();
        return previousFormButton;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NextFormButton createNextFormButton() {
        NextFormButtonImpl nextFormButton = new NextFormButtonImpl();
        return nextFormButton;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Info createInfo() {
        InfoImpl info = new InfoImpl();
        return info;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public TextInfo createTextInfo() {
        TextInfoImpl textInfo = new TextInfoImpl();
        return textInfo;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public MessageInfo createMessageInfo() {
        MessageInfoImpl messageInfo = new MessageInfoImpl();
        return messageInfo;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public CheckBoxSingleFormField createCheckBoxSingleFormField() {
        CheckBoxSingleFormFieldImpl checkBoxSingleFormField = new CheckBoxSingleFormFieldImpl();
        return checkBoxSingleFormField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public FileWidget createFileWidget() {
        FileWidgetImpl fileWidget = new FileWidgetImpl();
        return fileWidget;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ImageWidget createImageWidget() {
        ImageWidgetImpl imageWidget = new ImageWidgetImpl();
        return imageWidget;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public HiddenWidget createHiddenWidget() {
        HiddenWidgetImpl hiddenWidget = new HiddenWidgetImpl();
        return hiddenWidget;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public DurationFormField createDurationFormField() {
        DurationFormFieldImpl durationFormField = new DurationFormFieldImpl();
        return durationFormField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Table createTable() {
        TableImpl table = new TableImpl();
        return table;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public DynamicTable createDynamicTable() {
        DynamicTableImpl dynamicTable = new DynamicTableImpl();
        return dynamicTable;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public IFrameWidget createIFrameWidget() {
        IFrameWidgetImpl iFrameWidget = new IFrameWidgetImpl();
        return iFrameWidget;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public HtmlWidget createHtmlWidget() {
        HtmlWidgetImpl htmlWidget = new HtmlWidgetImpl();
        return htmlWidget;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public SuggestBox createSuggestBox() {
        SuggestBoxImpl suggestBox = new SuggestBoxImpl();
        return suggestBox;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public GroupIterator createGroupIterator() {
        GroupIteratorImpl groupIterator = new GroupIteratorImpl();
        return groupIterator;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EventDependencyType createEventDependencyTypeFromString(EDataType eDataType, String initialValue) {
        EventDependencyType result = EventDependencyType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertEventDependencyTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public LabelPosition createLabelPositionFromString(EDataType eDataType, String initialValue) {
        LabelPosition result = LabelPosition.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertLabelPositionToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FileWidgetInputType createFileWidgetInputTypeFromString(EDataType eDataType, String initialValue) {
        FileWidgetInputType result = FileWidgetInputType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertFileWidgetInputTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FileWidgetDownloadType createFileWidgetDownloadTypeFromString(EDataType eDataType, String initialValue) {
        FileWidgetDownloadType result = FileWidgetDownloadType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertFileWidgetDownloadTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public FormPackage getFormPackage() {
        return (FormPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static FormPackage getPackage() {
        return FormPackage.eINSTANCE;
    }

} //FormFactoryImpl
