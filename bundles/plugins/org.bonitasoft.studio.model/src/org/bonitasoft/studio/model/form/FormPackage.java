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

import org.bonitasoft.studio.model.process.ProcessPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.form.FormFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/edapt historyURI='process.history'"
 * @generated
 */
public interface FormPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "form"; //$NON-NLS-1$

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://www.bonitasoft.org/ns/studio/form"; //$NON-NLS-1$

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "form"; //$NON-NLS-1$

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	FormPackage eINSTANCE = org.bonitasoft.studio.model.form.impl.FormPackageImpl.init();

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.WidgetDependencyImpl <em>Widget Dependency</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.WidgetDependencyImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getWidgetDependency()
     * @generated
     */
	int WIDGET_DEPENDENCY = 0;

	/**
     * The feature id for the '<em><b>Trigger Refresh On Modification</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET_DEPENDENCY__TRIGGER_REFRESH_ON_MODIFICATION = 0;

	/**
     * The feature id for the '<em><b>Event Types</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET_DEPENDENCY__EVENT_TYPES = 1;

	/**
     * The feature id for the '<em><b>Widget</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET_DEPENDENCY__WIDGET = 2;

	/**
     * The number of structural features of the '<em>Widget Dependency</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET_DEPENDENCY_FEATURE_COUNT = 3;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.ValidatorImpl <em>Validator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.ValidatorImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getValidator()
     * @generated
     */
	int VALIDATOR = 1;

	/**
     * The feature id for the '<em><b>Validator Class</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALIDATOR__VALIDATOR_CLASS = 0;

	/**
     * The feature id for the '<em><b>Html Class</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALIDATOR__HTML_CLASS = 1;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALIDATOR__NAME = 2;

	/**
     * The feature id for the '<em><b>Below Field</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALIDATOR__BELOW_FIELD = 3;

	/**
     * The feature id for the '<em><b>Parameter</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALIDATOR__PARAMETER = 4;

	/**
     * The feature id for the '<em><b>Display Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALIDATOR__DISPLAY_NAME = 5;

	/**
     * The number of structural features of the '<em>Validator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALIDATOR_FEATURE_COUNT = 6;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.ValidableImpl <em>Validable</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.ValidableImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getValidable()
     * @generated
     */
	int VALIDABLE = 2;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALIDABLE__VALIDATORS = 0;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALIDABLE__USE_DEFAULT_VALIDATOR = 1;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALIDABLE__BELOW = 2;

	/**
     * The number of structural features of the '<em>Validable</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALIDABLE_FEATURE_COUNT = 3;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.FormImpl <em>Form</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.FormImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getForm()
     * @generated
     */
	int FORM = 3;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__DOCUMENTATION = ProcessPackage.CONNECTABLE_ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__NAME = ProcessPackage.CONNECTABLE_ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__TEXT_ANNOTATION_ATTACHMENT = ProcessPackage.CONNECTABLE_ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__DATA = ProcessPackage.CONNECTABLE_ELEMENT__DATA;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__CONNECTORS = ProcessPackage.CONNECTABLE_ELEMENT__CONNECTORS;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__KPIS = ProcessPackage.CONNECTABLE_ELEMENT__KPIS;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__VALIDATORS = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__USE_DEFAULT_VALIDATOR = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__BELOW = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>NColumn</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__NCOLUMN = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>String Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__STRING_ATTRIBUTES = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>NLine</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__NLINE = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Show Page Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__SHOW_PAGE_LABEL = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Allow HTML In Page Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__ALLOW_HTML_IN_PAGE_LABEL = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__VERSION = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Columns</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__COLUMNS = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>Lines</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__LINES = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 10;

	/**
     * The feature id for the '<em><b>Widgets</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__WIDGETS = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 11;

	/**
     * The feature id for the '<em><b>Page Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__PAGE_LABEL = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 12;

	/**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM__ACTIONS = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 13;

	/**
     * The number of structural features of the '<em>Form</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FEATURE_COUNT = ProcessPackage.CONNECTABLE_ELEMENT_FEATURE_COUNT + 14;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.WidgetLayoutInfoImpl <em>Widget Layout Info</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.WidgetLayoutInfoImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getWidgetLayoutInfo()
     * @generated
     */
	int WIDGET_LAYOUT_INFO = 4;

	/**
     * The feature id for the '<em><b>Line</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET_LAYOUT_INFO__LINE = 0;

	/**
     * The feature id for the '<em><b>Column</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET_LAYOUT_INFO__COLUMN = 1;

	/**
     * The feature id for the '<em><b>Vertical Span</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET_LAYOUT_INFO__VERTICAL_SPAN = 2;

	/**
     * The feature id for the '<em><b>Horizontal Span</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET_LAYOUT_INFO__HORIZONTAL_SPAN = 3;

	/**
     * The number of structural features of the '<em>Widget Layout Info</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET_LAYOUT_INFO_FEATURE_COUNT = 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.ColumnImpl <em>Column</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.ColumnImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getColumn()
     * @generated
     */
	int COLUMN = 5;

	/**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN__WIDTH = 0;

	/**
     * The feature id for the '<em><b>Number</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN__NUMBER = 1;

	/**
     * The number of structural features of the '<em>Column</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.LineImpl <em>Line</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.LineImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getLine()
     * @generated
     */
	int LINE = 6;

	/**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LINE__HEIGHT = 0;

	/**
     * The feature id for the '<em><b>Number</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LINE__NUMBER = 1;

	/**
     * The number of structural features of the '<em>Line</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LINE_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.ItemContainer <em>Item Container</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.ItemContainer
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getItemContainer()
     * @generated
     */
	int ITEM_CONTAINER = 7;

	/**
     * The feature id for the '<em><b>Item Class</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ITEM_CONTAINER__ITEM_CLASS = 0;

	/**
     * The number of structural features of the '<em>Item Container</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ITEM_CONTAINER_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.Duplicable <em>Duplicable</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.Duplicable
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getDuplicable()
     * @generated
     */
	int DUPLICABLE = 8;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DUPLICABLE__DUPLICATE = 0;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DUPLICABLE__LIMIT_NUMBER_OF_DUPLICATION = 1;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DUPLICABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION = 2;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DUPLICABLE__MAX_NUMBER_OF_DUPLICATION = 3;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DUPLICABLE__MIN_NUMBER_OF_DUPLICATION = 4;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DUPLICABLE__DISPLAY_LABEL_FOR_ADD = 5;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DUPLICABLE__TOOLTIP_FOR_ADD = 6;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DUPLICABLE__DISPLAY_LABEL_FOR_REMOVE = 7;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DUPLICABLE__TOOLTIP_FOR_REMOVE = 8;

	/**
     * The number of structural features of the '<em>Duplicable</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DUPLICABLE_FEATURE_COUNT = 9;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.ViewFormImpl <em>View Form</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.ViewFormImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getViewForm()
     * @generated
     */
	int VIEW_FORM = 9;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__DOCUMENTATION = FORM__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__NAME = FORM__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__TEXT_ANNOTATION_ATTACHMENT = FORM__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__DATA = FORM__DATA;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__CONNECTORS = FORM__CONNECTORS;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__KPIS = FORM__KPIS;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__VALIDATORS = FORM__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__USE_DEFAULT_VALIDATOR = FORM__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__BELOW = FORM__BELOW;

	/**
     * The feature id for the '<em><b>NColumn</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__NCOLUMN = FORM__NCOLUMN;

	/**
     * The feature id for the '<em><b>String Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__STRING_ATTRIBUTES = FORM__STRING_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>NLine</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__NLINE = FORM__NLINE;

	/**
     * The feature id for the '<em><b>Show Page Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__SHOW_PAGE_LABEL = FORM__SHOW_PAGE_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML In Page Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__ALLOW_HTML_IN_PAGE_LABEL = FORM__ALLOW_HTML_IN_PAGE_LABEL;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__VERSION = FORM__VERSION;

	/**
     * The feature id for the '<em><b>Columns</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__COLUMNS = FORM__COLUMNS;

	/**
     * The feature id for the '<em><b>Lines</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__LINES = FORM__LINES;

	/**
     * The feature id for the '<em><b>Widgets</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__WIDGETS = FORM__WIDGETS;

	/**
     * The feature id for the '<em><b>Page Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__PAGE_LABEL = FORM__PAGE_LABEL;

	/**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM__ACTIONS = FORM__ACTIONS;

	/**
     * The number of structural features of the '<em>View Form</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VIEW_FORM_FEATURE_COUNT = FORM_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.CSSCustomizableImpl <em>CSS Customizable</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.CSSCustomizableImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getCSSCustomizable()
     * @generated
     */
	int CSS_CUSTOMIZABLE = 10;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CSS_CUSTOMIZABLE__HTML_ATTRIBUTES = 0;

	/**
     * The number of structural features of the '<em>CSS Customizable</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CSS_CUSTOMIZABLE_FEATURE_COUNT = 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.Widget <em>Widget</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.Widget
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getWidget()
     * @generated
     */
	int WIDGET = 11;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__DOCUMENTATION = ProcessPackage.ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__NAME = ProcessPackage.ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__TEXT_ANNOTATION_ATTACHMENT = ProcessPackage.ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__HTML_ATTRIBUTES = ProcessPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__WIDGET_LAYOUT_INFO = ProcessPackage.ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__SHOW_DISPLAY_LABEL = ProcessPackage.ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL = ProcessPackage.ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__DEPEND_ON = ProcessPackage.ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = ProcessPackage.ELEMENT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__PARENT_OF = ProcessPackage.ELEMENT_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__MANDATORY = ProcessPackage.ELEMENT_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__READ_ONLY = ProcessPackage.ELEMENT_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__LABEL_POSITION = ProcessPackage.ELEMENT_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__REAL_HTML_ATTRIBUTES = ProcessPackage.ELEMENT_FEATURE_COUNT + 10;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__INJECT_WIDGET_CONDITION = ProcessPackage.ELEMENT_FEATURE_COUNT + 11;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__VERSION = ProcessPackage.ELEMENT_FEATURE_COUNT + 12;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__RETURN_TYPE_MODIFIER = ProcessPackage.ELEMENT_FEATURE_COUNT + 13;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = ProcessPackage.ELEMENT_FEATURE_COUNT + 14;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = ProcessPackage.ELEMENT_FEATURE_COUNT + 15;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__INPUT_EXPRESSION = ProcessPackage.ELEMENT_FEATURE_COUNT + 16;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__AFTER_EVENT_EXPRESSION = ProcessPackage.ELEMENT_FEATURE_COUNT + 17;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__TOOLTIP = ProcessPackage.ELEMENT_FEATURE_COUNT + 18;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__HELP_MESSAGE = ProcessPackage.ELEMENT_FEATURE_COUNT + 19;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__DISPLAY_LABEL = ProcessPackage.ELEMENT_FEATURE_COUNT + 20;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__INJECT_WIDGET_SCRIPT = ProcessPackage.ELEMENT_FEATURE_COUNT + 21;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET__ACTION = ProcessPackage.ELEMENT_FEATURE_COUNT + 22;

	/**
     * The number of structural features of the '<em>Widget</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WIDGET_FEATURE_COUNT = ProcessPackage.ELEMENT_FEATURE_COUNT + 23;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.GroupImpl <em>Group</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.GroupImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getGroup()
     * @generated
     */
	int GROUP = 12;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__DOCUMENTATION = WIDGET__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__NAME = WIDGET__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__TEXT_ANNOTATION_ATTACHMENT = WIDGET__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__HTML_ATTRIBUTES = WIDGET__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__WIDGET_LAYOUT_INFO = WIDGET__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__SHOW_DISPLAY_LABEL = WIDGET__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__ALLOW_HTML_FOR_DISPLAY_LABEL = WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__DEPEND_ON = WIDGET__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__PARENT_OF = WIDGET__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__MANDATORY = WIDGET__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__READ_ONLY = WIDGET__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__LABEL_POSITION = WIDGET__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__REAL_HTML_ATTRIBUTES = WIDGET__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__INJECT_WIDGET_CONDITION = WIDGET__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__VERSION = WIDGET__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__RETURN_TYPE_MODIFIER = WIDGET__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__INPUT_EXPRESSION = WIDGET__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__AFTER_EVENT_EXPRESSION = WIDGET__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__TOOLTIP = WIDGET__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__HELP_MESSAGE = WIDGET__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__DISPLAY_LABEL = WIDGET__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__INJECT_WIDGET_SCRIPT = WIDGET__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__ACTION = WIDGET__ACTION;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__DUPLICATE = WIDGET_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__LIMIT_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__LIMIT_MIN_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__MAX_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__MIN_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__DISPLAY_LABEL_FOR_ADD = WIDGET_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__TOOLTIP_FOR_ADD = WIDGET_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__DISPLAY_LABEL_FOR_REMOVE = WIDGET_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__TOOLTIP_FOR_REMOVE = WIDGET_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Widgets</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__WIDGETS = WIDGET_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>Show Border</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__SHOW_BORDER = WIDGET_FEATURE_COUNT + 10;

	/**
     * The feature id for the '<em><b>Columns</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__COLUMNS = WIDGET_FEATURE_COUNT + 11;

	/**
     * The feature id for the '<em><b>Lines</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__LINES = WIDGET_FEATURE_COUNT + 12;

	/**
     * The feature id for the '<em><b>Use Iterator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__USE_ITERATOR = WIDGET_FEATURE_COUNT + 13;

	/**
     * The feature id for the '<em><b>Iterator</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP__ITERATOR = WIDGET_FEATURE_COUNT + 14;

	/**
     * The number of structural features of the '<em>Group</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 15;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.FormFieldImpl <em>Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.FormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getFormField()
     * @generated
     */
	int FORM_FIELD = 13;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__DOCUMENTATION = WIDGET__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__NAME = WIDGET__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = WIDGET__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__HTML_ATTRIBUTES = WIDGET__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__WIDGET_LAYOUT_INFO = WIDGET__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__SHOW_DISPLAY_LABEL = WIDGET__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__DEPEND_ON = WIDGET__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__PARENT_OF = WIDGET__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__MANDATORY = WIDGET__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__READ_ONLY = WIDGET__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__LABEL_POSITION = WIDGET__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__REAL_HTML_ATTRIBUTES = WIDGET__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__INJECT_WIDGET_CONDITION = WIDGET__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__VERSION = WIDGET__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__RETURN_TYPE_MODIFIER = WIDGET__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__INPUT_EXPRESSION = WIDGET__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__AFTER_EVENT_EXPRESSION = WIDGET__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__TOOLTIP = WIDGET__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__HELP_MESSAGE = WIDGET__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__DISPLAY_LABEL = WIDGET__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__INJECT_WIDGET_SCRIPT = WIDGET__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__ACTION = WIDGET__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__VALIDATORS = WIDGET_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__USE_DEFAULT_VALIDATOR = WIDGET_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__BELOW = WIDGET_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__DUPLICATE = WIDGET_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__DISPLAY_LABEL_FOR_ADD = WIDGET_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__TOOLTIP_FOR_ADD = WIDGET_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = WIDGET_FEATURE_COUNT + 10;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__TOOLTIP_FOR_REMOVE = WIDGET_FEATURE_COUNT + 11;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__DESCRIPTION = WIDGET_FEATURE_COUNT + 12;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__EXAMPLE_MESSAGE_POSITION = WIDGET_FEATURE_COUNT + 13;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD__EXAMPLE_MESSAGE = WIDGET_FEATURE_COUNT + 14;

	/**
     * The number of structural features of the '<em>Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_FIELD_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 15;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.MultipleValuatedFormFieldImpl <em>Multiple Valuated Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.MultipleValuatedFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getMultipleValuatedFormField()
     * @generated
     */
	int MULTIPLE_VALUATED_FORM_FIELD = 14;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__DOCUMENTATION = FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__NAME = FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES = FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO = FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL = FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__DEPEND_ON = FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__PARENT_OF = FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__MANDATORY = FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__READ_ONLY = FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__LABEL_POSITION = FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES = FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION = FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__VERSION = FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER = FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION = FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION = FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP = FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__HELP_MESSAGE = FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL = FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT = FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__ACTION = FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__VALIDATORS = FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR = FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__BELOW = FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__DUPLICATE = FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD = FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE = FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__DESCRIPTION = FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE = FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Default Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION = FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Default Expression After Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT = FORM_FIELD_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Multiple Valuated Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT = FORM_FIELD_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.SingleValuatedFormFieldImpl <em>Single Valuated Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.SingleValuatedFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getSingleValuatedFormField()
     * @generated
     */
	int SINGLE_VALUATED_FORM_FIELD = 15;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__DOCUMENTATION = FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__NAME = FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES = FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO = FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL = FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__DEPEND_ON = FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__PARENT_OF = FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__MANDATORY = FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__READ_ONLY = FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__LABEL_POSITION = FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES = FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION = FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__VERSION = FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER = FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION = FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION = FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__TOOLTIP = FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__HELP_MESSAGE = FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL = FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT = FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__ACTION = FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__VALIDATORS = FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR = FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__BELOW = FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__DUPLICATE = FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD = FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE = FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__DESCRIPTION = FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE = FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The number of structural features of the '<em>Single Valuated Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT = FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.CheckBoxMultipleFormFieldImpl <em>Check Box Multiple Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.CheckBoxMultipleFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getCheckBoxMultipleFormField()
     * @generated
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD = 16;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__DOCUMENTATION = MULTIPLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__NAME = MULTIPLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = MULTIPLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__HTML_ATTRIBUTES = MULTIPLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__WIDGET_LAYOUT_INFO = MULTIPLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__SHOW_DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__DEPEND_ON = MULTIPLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__PARENT_OF = MULTIPLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__MANDATORY = MULTIPLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__READ_ONLY = MULTIPLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__LABEL_POSITION = MULTIPLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__REAL_HTML_ATTRIBUTES = MULTIPLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__INJECT_WIDGET_CONDITION = MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__VERSION = MULTIPLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__RETURN_TYPE_MODIFIER = MULTIPLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__INPUT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__AFTER_EVENT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__TOOLTIP = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__HELP_MESSAGE = MULTIPLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__INJECT_WIDGET_SCRIPT = MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__ACTION = MULTIPLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__VALIDATORS = MULTIPLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__USE_DEFAULT_VALIDATOR = MULTIPLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__BELOW = MULTIPLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__DUPLICATE = MULTIPLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__TOOLTIP_FOR_ADD = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__TOOLTIP_FOR_REMOVE = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__DESCRIPTION = MULTIPLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__EXAMPLE_MESSAGE = MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Default Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__DEFAULT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Default Expression After Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT = MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT;

	/**
     * The feature id for the '<em><b>Item Class</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD__ITEM_CLASS = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Check Box Multiple Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_MULTIPLE_FORM_FIELD_FEATURE_COUNT = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.ComboFormFieldImpl <em>Combo Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.ComboFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getComboFormField()
     * @generated
     */
	int COMBO_FORM_FIELD = 17;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__DOCUMENTATION = MULTIPLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__NAME = MULTIPLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = MULTIPLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__HTML_ATTRIBUTES = MULTIPLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__WIDGET_LAYOUT_INFO = MULTIPLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__SHOW_DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__DEPEND_ON = MULTIPLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__PARENT_OF = MULTIPLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__MANDATORY = MULTIPLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__READ_ONLY = MULTIPLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__LABEL_POSITION = MULTIPLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__REAL_HTML_ATTRIBUTES = MULTIPLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__INJECT_WIDGET_CONDITION = MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__VERSION = MULTIPLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__RETURN_TYPE_MODIFIER = MULTIPLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__INPUT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__AFTER_EVENT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__TOOLTIP = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__HELP_MESSAGE = MULTIPLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__INJECT_WIDGET_SCRIPT = MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__ACTION = MULTIPLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__VALIDATORS = MULTIPLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__USE_DEFAULT_VALIDATOR = MULTIPLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__BELOW = MULTIPLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__DUPLICATE = MULTIPLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__TOOLTIP_FOR_ADD = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__TOOLTIP_FOR_REMOVE = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__DESCRIPTION = MULTIPLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__EXAMPLE_MESSAGE = MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Default Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__DEFAULT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Default Expression After Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT = MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT;

	/**
     * The number of structural features of the '<em>Combo Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMBO_FORM_FIELD_FEATURE_COUNT = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.DateFormFieldImpl <em>Date Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.DateFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getDateFormField()
     * @generated
     */
	int DATE_FORM_FIELD = 18;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__DOCUMENTATION = SINGLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__NAME = SINGLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = SINGLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__WIDGET_LAYOUT_INFO = SINGLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__SHOW_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__DEPEND_ON = SINGLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__PARENT_OF = SINGLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__MANDATORY = SINGLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__READ_ONLY = SINGLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__LABEL_POSITION = SINGLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__REAL_HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__INJECT_WIDGET_CONDITION = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__VERSION = SINGLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__RETURN_TYPE_MODIFIER = SINGLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = SINGLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__INPUT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__AFTER_EVENT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__TOOLTIP = SINGLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__HELP_MESSAGE = SINGLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__INJECT_WIDGET_SCRIPT = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__ACTION = SINGLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__VALIDATORS = SINGLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__USE_DEFAULT_VALIDATOR = SINGLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__BELOW = SINGLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__DUPLICATE = SINGLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__TOOLTIP_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__TOOLTIP_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__DESCRIPTION = SINGLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__EXAMPLE_MESSAGE = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Initial Format</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__INITIAL_FORMAT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Display Format</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD__DISPLAY_FORMAT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Date Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATE_FORM_FIELD_FEATURE_COUNT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.ListFormFieldImpl <em>List Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.ListFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getListFormField()
     * @generated
     */
	int LIST_FORM_FIELD = 19;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__DOCUMENTATION = MULTIPLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__NAME = MULTIPLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = MULTIPLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__HTML_ATTRIBUTES = MULTIPLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__WIDGET_LAYOUT_INFO = MULTIPLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__SHOW_DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__DEPEND_ON = MULTIPLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__PARENT_OF = MULTIPLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__MANDATORY = MULTIPLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__READ_ONLY = MULTIPLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__LABEL_POSITION = MULTIPLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__REAL_HTML_ATTRIBUTES = MULTIPLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__INJECT_WIDGET_CONDITION = MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__VERSION = MULTIPLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__RETURN_TYPE_MODIFIER = MULTIPLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__INPUT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__AFTER_EVENT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__TOOLTIP = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__HELP_MESSAGE = MULTIPLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__INJECT_WIDGET_SCRIPT = MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__ACTION = MULTIPLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__VALIDATORS = MULTIPLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__USE_DEFAULT_VALIDATOR = MULTIPLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__BELOW = MULTIPLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__DUPLICATE = MULTIPLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__TOOLTIP_FOR_ADD = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__TOOLTIP_FOR_REMOVE = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__DESCRIPTION = MULTIPLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__EXAMPLE_MESSAGE = MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Default Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__DEFAULT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Default Expression After Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT = MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT;

	/**
     * The feature id for the '<em><b>Max Heigth</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD__MAX_HEIGTH = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>List Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LIST_FORM_FIELD_FEATURE_COUNT = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.PasswordFormFieldImpl <em>Password Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.PasswordFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getPasswordFormField()
     * @generated
     */
	int PASSWORD_FORM_FIELD = 20;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__DOCUMENTATION = SINGLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__NAME = SINGLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = SINGLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__WIDGET_LAYOUT_INFO = SINGLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__SHOW_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__DEPEND_ON = SINGLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__PARENT_OF = SINGLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__MANDATORY = SINGLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__READ_ONLY = SINGLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__LABEL_POSITION = SINGLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__REAL_HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__INJECT_WIDGET_CONDITION = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__VERSION = SINGLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__RETURN_TYPE_MODIFIER = SINGLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = SINGLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__INPUT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__AFTER_EVENT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__TOOLTIP = SINGLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__HELP_MESSAGE = SINGLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__INJECT_WIDGET_SCRIPT = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__ACTION = SINGLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__VALIDATORS = SINGLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__USE_DEFAULT_VALIDATOR = SINGLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__BELOW = SINGLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__DUPLICATE = SINGLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__TOOLTIP_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__TOOLTIP_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__DESCRIPTION = SINGLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__EXAMPLE_MESSAGE = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Max Length</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD__MAX_LENGTH = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Password Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSWORD_FORM_FIELD_FEATURE_COUNT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.RadioFormFieldImpl <em>Radio Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.RadioFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getRadioFormField()
     * @generated
     */
	int RADIO_FORM_FIELD = 21;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__DOCUMENTATION = MULTIPLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__NAME = MULTIPLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = MULTIPLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__HTML_ATTRIBUTES = MULTIPLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__WIDGET_LAYOUT_INFO = MULTIPLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__SHOW_DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__DEPEND_ON = MULTIPLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__PARENT_OF = MULTIPLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__MANDATORY = MULTIPLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__READ_ONLY = MULTIPLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__LABEL_POSITION = MULTIPLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__REAL_HTML_ATTRIBUTES = MULTIPLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__INJECT_WIDGET_CONDITION = MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__VERSION = MULTIPLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__RETURN_TYPE_MODIFIER = MULTIPLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__INPUT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__AFTER_EVENT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__TOOLTIP = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__HELP_MESSAGE = MULTIPLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__INJECT_WIDGET_SCRIPT = MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__ACTION = MULTIPLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__VALIDATORS = MULTIPLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__USE_DEFAULT_VALIDATOR = MULTIPLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__BELOW = MULTIPLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__DUPLICATE = MULTIPLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__TOOLTIP_FOR_ADD = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__TOOLTIP_FOR_REMOVE = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__DESCRIPTION = MULTIPLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__EXAMPLE_MESSAGE = MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Default Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__DEFAULT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Default Expression After Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT = MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT;

	/**
     * The feature id for the '<em><b>Item Class</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD__ITEM_CLASS = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Radio Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RADIO_FORM_FIELD_FEATURE_COUNT = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.SelectFormFieldImpl <em>Select Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.SelectFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getSelectFormField()
     * @generated
     */
	int SELECT_FORM_FIELD = 22;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__DOCUMENTATION = MULTIPLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__NAME = MULTIPLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = MULTIPLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__HTML_ATTRIBUTES = MULTIPLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__WIDGET_LAYOUT_INFO = MULTIPLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__SHOW_DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__DEPEND_ON = MULTIPLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__PARENT_OF = MULTIPLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__MANDATORY = MULTIPLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__READ_ONLY = MULTIPLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__LABEL_POSITION = MULTIPLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__REAL_HTML_ATTRIBUTES = MULTIPLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__INJECT_WIDGET_CONDITION = MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__VERSION = MULTIPLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__RETURN_TYPE_MODIFIER = MULTIPLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__INPUT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__AFTER_EVENT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__TOOLTIP = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__HELP_MESSAGE = MULTIPLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__INJECT_WIDGET_SCRIPT = MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__ACTION = MULTIPLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__VALIDATORS = MULTIPLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__USE_DEFAULT_VALIDATOR = MULTIPLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__BELOW = MULTIPLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__DUPLICATE = MULTIPLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__TOOLTIP_FOR_ADD = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__TOOLTIP_FOR_REMOVE = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__DESCRIPTION = MULTIPLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__EXAMPLE_MESSAGE = MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Default Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__DEFAULT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Default Expression After Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT = MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT;

	/**
     * The number of structural features of the '<em>Select Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SELECT_FORM_FIELD_FEATURE_COUNT = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.TextFormFieldImpl <em>Text Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.TextFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getTextFormField()
     * @generated
     */
	int TEXT_FORM_FIELD = 23;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__DOCUMENTATION = SINGLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__NAME = SINGLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = SINGLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__WIDGET_LAYOUT_INFO = SINGLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__SHOW_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__DEPEND_ON = SINGLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__PARENT_OF = SINGLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__MANDATORY = SINGLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__READ_ONLY = SINGLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__LABEL_POSITION = SINGLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__REAL_HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__INJECT_WIDGET_CONDITION = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__VERSION = SINGLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__RETURN_TYPE_MODIFIER = SINGLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = SINGLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__INPUT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__AFTER_EVENT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__TOOLTIP = SINGLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__HELP_MESSAGE = SINGLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__INJECT_WIDGET_SCRIPT = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__ACTION = SINGLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__VALIDATORS = SINGLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__USE_DEFAULT_VALIDATOR = SINGLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__BELOW = SINGLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__DUPLICATE = SINGLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__TOOLTIP_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__TOOLTIP_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__DESCRIPTION = SINGLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__EXAMPLE_MESSAGE = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Max Length</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD__MAX_LENGTH = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Text Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_FORM_FIELD_FEATURE_COUNT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.TextAreaFormFieldImpl <em>Text Area Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.TextAreaFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getTextAreaFormField()
     * @generated
     */
	int TEXT_AREA_FORM_FIELD = 24;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__DOCUMENTATION = SINGLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__NAME = SINGLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = SINGLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__WIDGET_LAYOUT_INFO = SINGLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__SHOW_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__DEPEND_ON = SINGLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__PARENT_OF = SINGLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__MANDATORY = SINGLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__READ_ONLY = SINGLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__LABEL_POSITION = SINGLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__REAL_HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__INJECT_WIDGET_CONDITION = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__VERSION = SINGLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__RETURN_TYPE_MODIFIER = SINGLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = SINGLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__INPUT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__AFTER_EVENT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__TOOLTIP = SINGLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__HELP_MESSAGE = SINGLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__INJECT_WIDGET_SCRIPT = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__ACTION = SINGLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__VALIDATORS = SINGLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__USE_DEFAULT_VALIDATOR = SINGLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__BELOW = SINGLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__DUPLICATE = SINGLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__TOOLTIP_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__TOOLTIP_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__DESCRIPTION = SINGLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__EXAMPLE_MESSAGE = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Max Length</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__MAX_LENGTH = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Max Heigth</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD__MAX_HEIGTH = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Text Area Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_AREA_FORM_FIELD_FEATURE_COUNT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.RichTextAreaFormFieldImpl <em>Rich Text Area Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.RichTextAreaFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getRichTextAreaFormField()
     * @generated
     */
	int RICH_TEXT_AREA_FORM_FIELD = 25;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__DOCUMENTATION = SINGLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__NAME = SINGLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = SINGLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__WIDGET_LAYOUT_INFO = SINGLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__SHOW_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__DEPEND_ON = SINGLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__PARENT_OF = SINGLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__MANDATORY = SINGLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__READ_ONLY = SINGLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__LABEL_POSITION = SINGLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__REAL_HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__INJECT_WIDGET_CONDITION = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__VERSION = SINGLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__RETURN_TYPE_MODIFIER = SINGLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = SINGLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__INPUT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__AFTER_EVENT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__TOOLTIP = SINGLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__HELP_MESSAGE = SINGLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__INJECT_WIDGET_SCRIPT = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__ACTION = SINGLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__VALIDATORS = SINGLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__USE_DEFAULT_VALIDATOR = SINGLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__BELOW = SINGLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__DUPLICATE = SINGLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__TOOLTIP_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__TOOLTIP_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__DESCRIPTION = SINGLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD__EXAMPLE_MESSAGE = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The number of structural features of the '<em>Rich Text Area Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RICH_TEXT_AREA_FORM_FIELD_FEATURE_COUNT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.FormButtonImpl <em>Button</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.FormButtonImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getFormButton()
     * @generated
     */
	int FORM_BUTTON = 26;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__DOCUMENTATION = WIDGET__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__NAME = WIDGET__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__TEXT_ANNOTATION_ATTACHMENT = WIDGET__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__HTML_ATTRIBUTES = WIDGET__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__WIDGET_LAYOUT_INFO = WIDGET__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__SHOW_DISPLAY_LABEL = WIDGET__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__ALLOW_HTML_FOR_DISPLAY_LABEL = WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__DEPEND_ON = WIDGET__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__PARENT_OF = WIDGET__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__MANDATORY = WIDGET__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__READ_ONLY = WIDGET__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__LABEL_POSITION = WIDGET__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__REAL_HTML_ATTRIBUTES = WIDGET__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__INJECT_WIDGET_CONDITION = WIDGET__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__VERSION = WIDGET__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__RETURN_TYPE_MODIFIER = WIDGET__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__INPUT_EXPRESSION = WIDGET__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__AFTER_EVENT_EXPRESSION = WIDGET__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__TOOLTIP = WIDGET__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__HELP_MESSAGE = WIDGET__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__DISPLAY_LABEL = WIDGET__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__INJECT_WIDGET_SCRIPT = WIDGET__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__ACTION = WIDGET__ACTION;

	/**
     * The feature id for the '<em><b>Label Behavior</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON__LABEL_BEHAVIOR = WIDGET_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Button</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FORM_BUTTON_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.SubmitFormButtonImpl <em>Submit Form Button</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.SubmitFormButtonImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getSubmitFormButton()
     * @generated
     */
	int SUBMIT_FORM_BUTTON = 27;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__DOCUMENTATION = FORM_BUTTON__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__NAME = FORM_BUTTON__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__TEXT_ANNOTATION_ATTACHMENT = FORM_BUTTON__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__HTML_ATTRIBUTES = FORM_BUTTON__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__WIDGET_LAYOUT_INFO = FORM_BUTTON__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__SHOW_DISPLAY_LABEL = FORM_BUTTON__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__ALLOW_HTML_FOR_DISPLAY_LABEL = FORM_BUTTON__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__DEPEND_ON = FORM_BUTTON__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__PARENT_OF = FORM_BUTTON__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__MANDATORY = FORM_BUTTON__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__READ_ONLY = FORM_BUTTON__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__LABEL_POSITION = FORM_BUTTON__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__REAL_HTML_ATTRIBUTES = FORM_BUTTON__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__INJECT_WIDGET_CONDITION = FORM_BUTTON__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__VERSION = FORM_BUTTON__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__RETURN_TYPE_MODIFIER = FORM_BUTTON__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = FORM_BUTTON__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__INPUT_EXPRESSION = FORM_BUTTON__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__AFTER_EVENT_EXPRESSION = FORM_BUTTON__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__TOOLTIP = FORM_BUTTON__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__HELP_MESSAGE = FORM_BUTTON__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__DISPLAY_LABEL = FORM_BUTTON__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__INJECT_WIDGET_SCRIPT = FORM_BUTTON__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__ACTION = FORM_BUTTON__ACTION;

	/**
     * The feature id for the '<em><b>Label Behavior</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__LABEL_BEHAVIOR = FORM_BUTTON__LABEL_BEHAVIOR;

	/**
     * The feature id for the '<em><b>Data</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__DATA = FORM_BUTTON_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__CONNECTORS = FORM_BUTTON_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Kpis</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__KPIS = FORM_BUTTON_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON__ACTIONS = FORM_BUTTON_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Submit Form Button</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUBMIT_FORM_BUTTON_FEATURE_COUNT = FORM_BUTTON_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.PreviousFormButtonImpl <em>Previous Form Button</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.PreviousFormButtonImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getPreviousFormButton()
     * @generated
     */
	int PREVIOUS_FORM_BUTTON = 28;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__DOCUMENTATION = FORM_BUTTON__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__NAME = FORM_BUTTON__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__TEXT_ANNOTATION_ATTACHMENT = FORM_BUTTON__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__HTML_ATTRIBUTES = FORM_BUTTON__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__WIDGET_LAYOUT_INFO = FORM_BUTTON__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__SHOW_DISPLAY_LABEL = FORM_BUTTON__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__ALLOW_HTML_FOR_DISPLAY_LABEL = FORM_BUTTON__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__DEPEND_ON = FORM_BUTTON__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__PARENT_OF = FORM_BUTTON__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__MANDATORY = FORM_BUTTON__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__READ_ONLY = FORM_BUTTON__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__LABEL_POSITION = FORM_BUTTON__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__REAL_HTML_ATTRIBUTES = FORM_BUTTON__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__INJECT_WIDGET_CONDITION = FORM_BUTTON__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__VERSION = FORM_BUTTON__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__RETURN_TYPE_MODIFIER = FORM_BUTTON__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = FORM_BUTTON__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__INPUT_EXPRESSION = FORM_BUTTON__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__AFTER_EVENT_EXPRESSION = FORM_BUTTON__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__TOOLTIP = FORM_BUTTON__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__HELP_MESSAGE = FORM_BUTTON__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__DISPLAY_LABEL = FORM_BUTTON__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__INJECT_WIDGET_SCRIPT = FORM_BUTTON__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__ACTION = FORM_BUTTON__ACTION;

	/**
     * The feature id for the '<em><b>Label Behavior</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON__LABEL_BEHAVIOR = FORM_BUTTON__LABEL_BEHAVIOR;

	/**
     * The number of structural features of the '<em>Previous Form Button</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREVIOUS_FORM_BUTTON_FEATURE_COUNT = FORM_BUTTON_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.NextFormButtonImpl <em>Next Form Button</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.NextFormButtonImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getNextFormButton()
     * @generated
     */
	int NEXT_FORM_BUTTON = 29;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__DOCUMENTATION = FORM_BUTTON__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__NAME = FORM_BUTTON__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__TEXT_ANNOTATION_ATTACHMENT = FORM_BUTTON__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__HTML_ATTRIBUTES = FORM_BUTTON__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__WIDGET_LAYOUT_INFO = FORM_BUTTON__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__SHOW_DISPLAY_LABEL = FORM_BUTTON__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__ALLOW_HTML_FOR_DISPLAY_LABEL = FORM_BUTTON__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__DEPEND_ON = FORM_BUTTON__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__PARENT_OF = FORM_BUTTON__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__MANDATORY = FORM_BUTTON__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__READ_ONLY = FORM_BUTTON__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__LABEL_POSITION = FORM_BUTTON__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__REAL_HTML_ATTRIBUTES = FORM_BUTTON__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__INJECT_WIDGET_CONDITION = FORM_BUTTON__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__VERSION = FORM_BUTTON__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__RETURN_TYPE_MODIFIER = FORM_BUTTON__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = FORM_BUTTON__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = FORM_BUTTON__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__INPUT_EXPRESSION = FORM_BUTTON__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__AFTER_EVENT_EXPRESSION = FORM_BUTTON__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__TOOLTIP = FORM_BUTTON__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__HELP_MESSAGE = FORM_BUTTON__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__DISPLAY_LABEL = FORM_BUTTON__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__INJECT_WIDGET_SCRIPT = FORM_BUTTON__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__ACTION = FORM_BUTTON__ACTION;

	/**
     * The feature id for the '<em><b>Label Behavior</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON__LABEL_BEHAVIOR = FORM_BUTTON__LABEL_BEHAVIOR;

	/**
     * The number of structural features of the '<em>Next Form Button</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int NEXT_FORM_BUTTON_FEATURE_COUNT = FORM_BUTTON_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.InfoImpl <em>Info</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.InfoImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getInfo()
     * @generated
     */
	int INFO = 30;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__DOCUMENTATION = WIDGET__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__NAME = WIDGET__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__TEXT_ANNOTATION_ATTACHMENT = WIDGET__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__HTML_ATTRIBUTES = WIDGET__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__WIDGET_LAYOUT_INFO = WIDGET__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__SHOW_DISPLAY_LABEL = WIDGET__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__ALLOW_HTML_FOR_DISPLAY_LABEL = WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__DEPEND_ON = WIDGET__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__PARENT_OF = WIDGET__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__MANDATORY = WIDGET__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__READ_ONLY = WIDGET__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__LABEL_POSITION = WIDGET__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__REAL_HTML_ATTRIBUTES = WIDGET__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__INJECT_WIDGET_CONDITION = WIDGET__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__VERSION = WIDGET__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__RETURN_TYPE_MODIFIER = WIDGET__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__INPUT_EXPRESSION = WIDGET__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__AFTER_EVENT_EXPRESSION = WIDGET__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__TOOLTIP = WIDGET__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__HELP_MESSAGE = WIDGET__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__DISPLAY_LABEL = WIDGET__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__INJECT_WIDGET_SCRIPT = WIDGET__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO__ACTION = WIDGET__ACTION;

	/**
     * The number of structural features of the '<em>Info</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INFO_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.TextInfoImpl <em>Text Info</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.TextInfoImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getTextInfo()
     * @generated
     */
	int TEXT_INFO = 31;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__DOCUMENTATION = INFO__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__NAME = INFO__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__TEXT_ANNOTATION_ATTACHMENT = INFO__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__HTML_ATTRIBUTES = INFO__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__WIDGET_LAYOUT_INFO = INFO__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__SHOW_DISPLAY_LABEL = INFO__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__ALLOW_HTML_FOR_DISPLAY_LABEL = INFO__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__DEPEND_ON = INFO__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__PARENT_OF = INFO__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__MANDATORY = INFO__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__READ_ONLY = INFO__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__LABEL_POSITION = INFO__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__REAL_HTML_ATTRIBUTES = INFO__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__INJECT_WIDGET_CONDITION = INFO__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__VERSION = INFO__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__RETURN_TYPE_MODIFIER = INFO__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = INFO__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__INPUT_EXPRESSION = INFO__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__AFTER_EVENT_EXPRESSION = INFO__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__TOOLTIP = INFO__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__HELP_MESSAGE = INFO__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__DISPLAY_LABEL = INFO__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__INJECT_WIDGET_SCRIPT = INFO__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__ACTION = INFO__ACTION;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__DUPLICATE = INFO_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__LIMIT_NUMBER_OF_DUPLICATION = INFO_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__LIMIT_MIN_NUMBER_OF_DUPLICATION = INFO_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__MAX_NUMBER_OF_DUPLICATION = INFO_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__MIN_NUMBER_OF_DUPLICATION = INFO_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__DISPLAY_LABEL_FOR_ADD = INFO_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__TOOLTIP_FOR_ADD = INFO_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__DISPLAY_LABEL_FOR_REMOVE = INFO_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO__TOOLTIP_FOR_REMOVE = INFO_FEATURE_COUNT + 8;

	/**
     * The number of structural features of the '<em>Text Info</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEXT_INFO_FEATURE_COUNT = INFO_FEATURE_COUNT + 9;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.MessageInfoImpl <em>Message Info</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.MessageInfoImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getMessageInfo()
     * @generated
     */
	int MESSAGE_INFO = 32;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__DOCUMENTATION = INFO__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__NAME = INFO__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__TEXT_ANNOTATION_ATTACHMENT = INFO__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__HTML_ATTRIBUTES = INFO__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__WIDGET_LAYOUT_INFO = INFO__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__SHOW_DISPLAY_LABEL = INFO__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__ALLOW_HTML_FOR_DISPLAY_LABEL = INFO__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__DEPEND_ON = INFO__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__PARENT_OF = INFO__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__MANDATORY = INFO__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__READ_ONLY = INFO__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__LABEL_POSITION = INFO__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__REAL_HTML_ATTRIBUTES = INFO__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__INJECT_WIDGET_CONDITION = INFO__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__VERSION = INFO__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__RETURN_TYPE_MODIFIER = INFO__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = INFO__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__INPUT_EXPRESSION = INFO__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__AFTER_EVENT_EXPRESSION = INFO__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__TOOLTIP = INFO__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__HELP_MESSAGE = INFO__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__DISPLAY_LABEL = INFO__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__INJECT_WIDGET_SCRIPT = INFO__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO__ACTION = INFO__ACTION;

	/**
     * The number of structural features of the '<em>Message Info</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MESSAGE_INFO_FEATURE_COUNT = INFO_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.CheckBoxSingleFormFieldImpl <em>Check Box Single Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.CheckBoxSingleFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getCheckBoxSingleFormField()
     * @generated
     */
	int CHECK_BOX_SINGLE_FORM_FIELD = 33;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__DOCUMENTATION = SINGLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__NAME = SINGLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = SINGLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__WIDGET_LAYOUT_INFO = SINGLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__SHOW_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__DEPEND_ON = SINGLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__PARENT_OF = SINGLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__MANDATORY = SINGLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__READ_ONLY = SINGLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__LABEL_POSITION = SINGLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__REAL_HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__INJECT_WIDGET_CONDITION = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__VERSION = SINGLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__RETURN_TYPE_MODIFIER = SINGLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = SINGLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__INPUT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__AFTER_EVENT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__TOOLTIP = SINGLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__HELP_MESSAGE = SINGLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__INJECT_WIDGET_SCRIPT = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__ACTION = SINGLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__VALIDATORS = SINGLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__USE_DEFAULT_VALIDATOR = SINGLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__BELOW = SINGLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__DUPLICATE = SINGLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__TOOLTIP_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__TOOLTIP_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__DESCRIPTION = SINGLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD__EXAMPLE_MESSAGE = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The number of structural features of the '<em>Check Box Single Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CHECK_BOX_SINGLE_FORM_FIELD_FEATURE_COUNT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.FileWidgetImpl <em>File Widget</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.FileWidgetImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getFileWidget()
     * @generated
     */
	int FILE_WIDGET = 34;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DOCUMENTATION = SINGLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__NAME = SINGLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__TEXT_ANNOTATION_ATTACHMENT = SINGLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__WIDGET_LAYOUT_INFO = SINGLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__SHOW_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DEPEND_ON = SINGLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__PARENT_OF = SINGLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__MANDATORY = SINGLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__READ_ONLY = SINGLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__LABEL_POSITION = SINGLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__REAL_HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__INJECT_WIDGET_CONDITION = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__VERSION = SINGLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__RETURN_TYPE_MODIFIER = SINGLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = SINGLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__INPUT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__AFTER_EVENT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__TOOLTIP = SINGLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__HELP_MESSAGE = SINGLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__INJECT_WIDGET_SCRIPT = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__ACTION = SINGLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__VALIDATORS = SINGLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__USE_DEFAULT_VALIDATOR = SINGLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__BELOW = SINGLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DUPLICATE = SINGLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__LIMIT_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__LIMIT_MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__MAX_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DISPLAY_LABEL_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__TOOLTIP_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DISPLAY_LABEL_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__TOOLTIP_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DESCRIPTION = SINGLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__EXAMPLE_MESSAGE_POSITION = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__EXAMPLE_MESSAGE = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Download Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DOWNLOAD_ONLY = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Use Preview</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__USE_PREVIEW = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Document</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DOCUMENT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Initial Resource Path</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__INITIAL_RESOURCE_PATH = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Output Document Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__OUTPUT_DOCUMENT_NAME = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Update Document</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__UPDATE_DOCUMENT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Intial Resource List</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__INTIAL_RESOURCE_LIST = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Input Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__INPUT_TYPE = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Output Document List Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Download Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET__DOWNLOAD_TYPE = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 9;

	/**
     * The number of structural features of the '<em>File Widget</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FILE_WIDGET_FEATURE_COUNT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 10;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.ImageWidgetImpl <em>Image Widget</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.ImageWidgetImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getImageWidget()
     * @generated
     */
	int IMAGE_WIDGET = 35;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__DOCUMENTATION = WIDGET__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__NAME = WIDGET__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__TEXT_ANNOTATION_ATTACHMENT = WIDGET__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__HTML_ATTRIBUTES = WIDGET__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__WIDGET_LAYOUT_INFO = WIDGET__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__SHOW_DISPLAY_LABEL = WIDGET__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL = WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__DEPEND_ON = WIDGET__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__PARENT_OF = WIDGET__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__MANDATORY = WIDGET__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__READ_ONLY = WIDGET__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__LABEL_POSITION = WIDGET__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__REAL_HTML_ATTRIBUTES = WIDGET__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__INJECT_WIDGET_CONDITION = WIDGET__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__VERSION = WIDGET__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__RETURN_TYPE_MODIFIER = WIDGET__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__INPUT_EXPRESSION = WIDGET__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__AFTER_EVENT_EXPRESSION = WIDGET__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__TOOLTIP = WIDGET__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__HELP_MESSAGE = WIDGET__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__DISPLAY_LABEL = WIDGET__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__INJECT_WIDGET_SCRIPT = WIDGET__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__ACTION = WIDGET__ACTION;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__DUPLICATE = WIDGET_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__LIMIT_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__LIMIT_MIN_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__MAX_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__MIN_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__DISPLAY_LABEL_FOR_ADD = WIDGET_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__TOOLTIP_FOR_ADD = WIDGET_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__DISPLAY_LABEL_FOR_REMOVE = WIDGET_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__TOOLTIP_FOR_REMOVE = WIDGET_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Is ADocument</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__IS_ADOCUMENT = WIDGET_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>Document</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__DOCUMENT = WIDGET_FEATURE_COUNT + 10;

	/**
     * The feature id for the '<em><b>Img Path</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET__IMG_PATH = WIDGET_FEATURE_COUNT + 11;

	/**
     * The number of structural features of the '<em>Image Widget</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_WIDGET_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 12;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.HiddenWidgetImpl <em>Hidden Widget</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.HiddenWidgetImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getHiddenWidget()
     * @generated
     */
	int HIDDEN_WIDGET = 36;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__DOCUMENTATION = SINGLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__NAME = SINGLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__TEXT_ANNOTATION_ATTACHMENT = SINGLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__WIDGET_LAYOUT_INFO = SINGLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__SHOW_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__DEPEND_ON = SINGLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__PARENT_OF = SINGLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__MANDATORY = SINGLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__READ_ONLY = SINGLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__LABEL_POSITION = SINGLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__REAL_HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__INJECT_WIDGET_CONDITION = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__VERSION = SINGLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__RETURN_TYPE_MODIFIER = SINGLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = SINGLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__INPUT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__AFTER_EVENT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__TOOLTIP = SINGLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__HELP_MESSAGE = SINGLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__INJECT_WIDGET_SCRIPT = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__ACTION = SINGLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__VALIDATORS = SINGLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__USE_DEFAULT_VALIDATOR = SINGLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__BELOW = SINGLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__DUPLICATE = SINGLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__LIMIT_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__LIMIT_MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__MAX_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__DISPLAY_LABEL_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__TOOLTIP_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__DISPLAY_LABEL_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__TOOLTIP_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__DESCRIPTION = SINGLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__EXAMPLE_MESSAGE_POSITION = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET__EXAMPLE_MESSAGE = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The number of structural features of the '<em>Hidden Widget</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HIDDEN_WIDGET_FEATURE_COUNT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.DurationFormFieldImpl <em>Duration Form Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.DurationFormFieldImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getDurationFormField()
     * @generated
     */
	int DURATION_FORM_FIELD = 37;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__DOCUMENTATION = SINGLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__NAME = SINGLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT = SINGLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__WIDGET_LAYOUT_INFO = SINGLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__SHOW_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__DEPEND_ON = SINGLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__PARENT_OF = SINGLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__MANDATORY = SINGLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__READ_ONLY = SINGLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__LABEL_POSITION = SINGLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__REAL_HTML_ATTRIBUTES = SINGLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__INJECT_WIDGET_CONDITION = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__VERSION = SINGLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__RETURN_TYPE_MODIFIER = SINGLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = SINGLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = SINGLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__INPUT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__AFTER_EVENT_EXPRESSION = SINGLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__TOOLTIP = SINGLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__HELP_MESSAGE = SINGLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__DISPLAY_LABEL = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__INJECT_WIDGET_SCRIPT = SINGLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__ACTION = SINGLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__VALIDATORS = SINGLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__USE_DEFAULT_VALIDATOR = SINGLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__BELOW = SINGLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__DUPLICATE = SINGLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION = SINGLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__DISPLAY_LABEL_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__TOOLTIP_FOR_ADD = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__TOOLTIP_FOR_REMOVE = SINGLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__DESCRIPTION = SINGLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__EXAMPLE_MESSAGE_POSITION = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__EXAMPLE_MESSAGE = SINGLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Item Class</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__ITEM_CLASS = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Day</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__DAY = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Hour</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__HOUR = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Min</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__MIN = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Sec</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD__SEC = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>Duration Form Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DURATION_FORM_FIELD_FEATURE_COUNT = SINGLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.AbstractTableImpl <em>Abstract Table</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.AbstractTableImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getAbstractTable()
     * @generated
     */
	int ABSTRACT_TABLE = 38;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__DOCUMENTATION = WIDGET__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__NAME = WIDGET__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__TEXT_ANNOTATION_ATTACHMENT = WIDGET__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__HTML_ATTRIBUTES = WIDGET__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__WIDGET_LAYOUT_INFO = WIDGET__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__SHOW_DISPLAY_LABEL = WIDGET__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__ALLOW_HTML_FOR_DISPLAY_LABEL = WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__DEPEND_ON = WIDGET__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__PARENT_OF = WIDGET__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__MANDATORY = WIDGET__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__READ_ONLY = WIDGET__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__LABEL_POSITION = WIDGET__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__REAL_HTML_ATTRIBUTES = WIDGET__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__INJECT_WIDGET_CONDITION = WIDGET__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__VERSION = WIDGET__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__RETURN_TYPE_MODIFIER = WIDGET__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__INPUT_EXPRESSION = WIDGET__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__AFTER_EVENT_EXPRESSION = WIDGET__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__TOOLTIP = WIDGET__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__HELP_MESSAGE = WIDGET__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__DISPLAY_LABEL = WIDGET__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__INJECT_WIDGET_SCRIPT = WIDGET__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__ACTION = WIDGET__ACTION;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__DUPLICATE = WIDGET_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__LIMIT_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__MAX_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__MIN_NUMBER_OF_DUPLICATION = WIDGET_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__DISPLAY_LABEL_FOR_ADD = WIDGET_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__TOOLTIP_FOR_ADD = WIDGET_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__DISPLAY_LABEL_FOR_REMOVE = WIDGET_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__TOOLTIP_FOR_REMOVE = WIDGET_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Left Column Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__LEFT_COLUMN_IS_HEADER = WIDGET_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>Right Column Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__RIGHT_COLUMN_IS_HEADER = WIDGET_FEATURE_COUNT + 10;

	/**
     * The feature id for the '<em><b>First Row Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__FIRST_ROW_IS_HEADER = WIDGET_FEATURE_COUNT + 11;

	/**
     * The feature id for the '<em><b>Last Row Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__LAST_ROW_IS_HEADER = WIDGET_FEATURE_COUNT + 12;

	/**
     * The feature id for the '<em><b>Initialized Using Cells</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__INITIALIZED_USING_CELLS = WIDGET_FEATURE_COUNT + 13;

	/**
     * The feature id for the '<em><b>Use Horizontal Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__USE_HORIZONTAL_HEADER = WIDGET_FEATURE_COUNT + 14;

	/**
     * The feature id for the '<em><b>Use Vertical Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__USE_VERTICAL_HEADER = WIDGET_FEATURE_COUNT + 15;

	/**
     * The feature id for the '<em><b>Horizontal Header Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__HORIZONTAL_HEADER_EXPRESSION = WIDGET_FEATURE_COUNT + 16;

	/**
     * The feature id for the '<em><b>Vertical Header Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__VERTICAL_HEADER_EXPRESSION = WIDGET_FEATURE_COUNT + 17;

	/**
     * The feature id for the '<em><b>Table Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE__TABLE_EXPRESSION = WIDGET_FEATURE_COUNT + 18;

	/**
     * The number of structural features of the '<em>Abstract Table</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_TABLE_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 19;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.TableImpl <em>Table</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.TableImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getTable()
     * @generated
     */
	int TABLE = 39;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__DOCUMENTATION = ABSTRACT_TABLE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__NAME = ABSTRACT_TABLE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__TEXT_ANNOTATION_ATTACHMENT = ABSTRACT_TABLE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__HTML_ATTRIBUTES = ABSTRACT_TABLE__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__WIDGET_LAYOUT_INFO = ABSTRACT_TABLE__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__SHOW_DISPLAY_LABEL = ABSTRACT_TABLE__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__ALLOW_HTML_FOR_DISPLAY_LABEL = ABSTRACT_TABLE__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__DEPEND_ON = ABSTRACT_TABLE__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = ABSTRACT_TABLE__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__PARENT_OF = ABSTRACT_TABLE__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__MANDATORY = ABSTRACT_TABLE__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__READ_ONLY = ABSTRACT_TABLE__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__LABEL_POSITION = ABSTRACT_TABLE__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__REAL_HTML_ATTRIBUTES = ABSTRACT_TABLE__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__INJECT_WIDGET_CONDITION = ABSTRACT_TABLE__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__VERSION = ABSTRACT_TABLE__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__RETURN_TYPE_MODIFIER = ABSTRACT_TABLE__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = ABSTRACT_TABLE__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = ABSTRACT_TABLE__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__INPUT_EXPRESSION = ABSTRACT_TABLE__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__AFTER_EVENT_EXPRESSION = ABSTRACT_TABLE__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__TOOLTIP = ABSTRACT_TABLE__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__HELP_MESSAGE = ABSTRACT_TABLE__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__DISPLAY_LABEL = ABSTRACT_TABLE__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__INJECT_WIDGET_SCRIPT = ABSTRACT_TABLE__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__ACTION = ABSTRACT_TABLE__ACTION;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__DUPLICATE = ABSTRACT_TABLE__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__LIMIT_NUMBER_OF_DUPLICATION = ABSTRACT_TABLE__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION = ABSTRACT_TABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__MAX_NUMBER_OF_DUPLICATION = ABSTRACT_TABLE__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__MIN_NUMBER_OF_DUPLICATION = ABSTRACT_TABLE__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__DISPLAY_LABEL_FOR_ADD = ABSTRACT_TABLE__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__TOOLTIP_FOR_ADD = ABSTRACT_TABLE__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__DISPLAY_LABEL_FOR_REMOVE = ABSTRACT_TABLE__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__TOOLTIP_FOR_REMOVE = ABSTRACT_TABLE__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Left Column Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__LEFT_COLUMN_IS_HEADER = ABSTRACT_TABLE__LEFT_COLUMN_IS_HEADER;

	/**
     * The feature id for the '<em><b>Right Column Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__RIGHT_COLUMN_IS_HEADER = ABSTRACT_TABLE__RIGHT_COLUMN_IS_HEADER;

	/**
     * The feature id for the '<em><b>First Row Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__FIRST_ROW_IS_HEADER = ABSTRACT_TABLE__FIRST_ROW_IS_HEADER;

	/**
     * The feature id for the '<em><b>Last Row Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__LAST_ROW_IS_HEADER = ABSTRACT_TABLE__LAST_ROW_IS_HEADER;

	/**
     * The feature id for the '<em><b>Initialized Using Cells</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__INITIALIZED_USING_CELLS = ABSTRACT_TABLE__INITIALIZED_USING_CELLS;

	/**
     * The feature id for the '<em><b>Use Horizontal Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__USE_HORIZONTAL_HEADER = ABSTRACT_TABLE__USE_HORIZONTAL_HEADER;

	/**
     * The feature id for the '<em><b>Use Vertical Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__USE_VERTICAL_HEADER = ABSTRACT_TABLE__USE_VERTICAL_HEADER;

	/**
     * The feature id for the '<em><b>Horizontal Header Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__HORIZONTAL_HEADER_EXPRESSION = ABSTRACT_TABLE__HORIZONTAL_HEADER_EXPRESSION;

	/**
     * The feature id for the '<em><b>Vertical Header Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__VERTICAL_HEADER_EXPRESSION = ABSTRACT_TABLE__VERTICAL_HEADER_EXPRESSION;

	/**
     * The feature id for the '<em><b>Table Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__TABLE_EXPRESSION = ABSTRACT_TABLE__TABLE_EXPRESSION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__VALIDATORS = ABSTRACT_TABLE_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__USE_DEFAULT_VALIDATOR = ABSTRACT_TABLE_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__BELOW = ABSTRACT_TABLE_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__DESCRIPTION = ABSTRACT_TABLE_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__EXAMPLE_MESSAGE_POSITION = ABSTRACT_TABLE_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__EXAMPLE_MESSAGE = ABSTRACT_TABLE_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Default Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__DEFAULT_EXPRESSION = ABSTRACT_TABLE_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Default Expression After Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__DEFAULT_EXPRESSION_AFTER_EVENT = ABSTRACT_TABLE_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Use Pagination</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__USE_PAGINATION = ABSTRACT_TABLE_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Allow Selection</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__ALLOW_SELECTION = ABSTRACT_TABLE_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>Selection Mode Is Multiple</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__SELECTION_MODE_IS_MULTIPLE = ABSTRACT_TABLE_FEATURE_COUNT + 10;

	/**
     * The feature id for the '<em><b>Max Row For Pagination</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__MAX_ROW_FOR_PAGINATION = ABSTRACT_TABLE_FEATURE_COUNT + 11;

	/**
     * The feature id for the '<em><b>Column For Initial Selection Index</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX = ABSTRACT_TABLE_FEATURE_COUNT + 12;

	/**
     * The feature id for the '<em><b>Selected Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__SELECTED_VALUES = ABSTRACT_TABLE_FEATURE_COUNT + 13;

	/**
     * The number of structural features of the '<em>Table</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FEATURE_COUNT = ABSTRACT_TABLE_FEATURE_COUNT + 14;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl <em>Dynamic Table</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.DynamicTableImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getDynamicTable()
     * @generated
     */
	int DYNAMIC_TABLE = 40;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__DOCUMENTATION = ABSTRACT_TABLE__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__NAME = ABSTRACT_TABLE__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__TEXT_ANNOTATION_ATTACHMENT = ABSTRACT_TABLE__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__HTML_ATTRIBUTES = ABSTRACT_TABLE__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__WIDGET_LAYOUT_INFO = ABSTRACT_TABLE__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__SHOW_DISPLAY_LABEL = ABSTRACT_TABLE__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__ALLOW_HTML_FOR_DISPLAY_LABEL = ABSTRACT_TABLE__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__DEPEND_ON = ABSTRACT_TABLE__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = ABSTRACT_TABLE__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__PARENT_OF = ABSTRACT_TABLE__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__MANDATORY = ABSTRACT_TABLE__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__READ_ONLY = ABSTRACT_TABLE__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__LABEL_POSITION = ABSTRACT_TABLE__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__REAL_HTML_ATTRIBUTES = ABSTRACT_TABLE__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__INJECT_WIDGET_CONDITION = ABSTRACT_TABLE__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__VERSION = ABSTRACT_TABLE__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__RETURN_TYPE_MODIFIER = ABSTRACT_TABLE__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = ABSTRACT_TABLE__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = ABSTRACT_TABLE__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__INPUT_EXPRESSION = ABSTRACT_TABLE__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__AFTER_EVENT_EXPRESSION = ABSTRACT_TABLE__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__TOOLTIP = ABSTRACT_TABLE__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__HELP_MESSAGE = ABSTRACT_TABLE__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__DISPLAY_LABEL = ABSTRACT_TABLE__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__INJECT_WIDGET_SCRIPT = ABSTRACT_TABLE__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__ACTION = ABSTRACT_TABLE__ACTION;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__DUPLICATE = ABSTRACT_TABLE__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__LIMIT_NUMBER_OF_DUPLICATION = ABSTRACT_TABLE__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION = ABSTRACT_TABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__MAX_NUMBER_OF_DUPLICATION = ABSTRACT_TABLE__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__MIN_NUMBER_OF_DUPLICATION = ABSTRACT_TABLE__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__DISPLAY_LABEL_FOR_ADD = ABSTRACT_TABLE__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__TOOLTIP_FOR_ADD = ABSTRACT_TABLE__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__DISPLAY_LABEL_FOR_REMOVE = ABSTRACT_TABLE__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__TOOLTIP_FOR_REMOVE = ABSTRACT_TABLE__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Left Column Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__LEFT_COLUMN_IS_HEADER = ABSTRACT_TABLE__LEFT_COLUMN_IS_HEADER;

	/**
     * The feature id for the '<em><b>Right Column Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__RIGHT_COLUMN_IS_HEADER = ABSTRACT_TABLE__RIGHT_COLUMN_IS_HEADER;

	/**
     * The feature id for the '<em><b>First Row Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__FIRST_ROW_IS_HEADER = ABSTRACT_TABLE__FIRST_ROW_IS_HEADER;

	/**
     * The feature id for the '<em><b>Last Row Is Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__LAST_ROW_IS_HEADER = ABSTRACT_TABLE__LAST_ROW_IS_HEADER;

	/**
     * The feature id for the '<em><b>Initialized Using Cells</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__INITIALIZED_USING_CELLS = ABSTRACT_TABLE__INITIALIZED_USING_CELLS;

	/**
     * The feature id for the '<em><b>Use Horizontal Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__USE_HORIZONTAL_HEADER = ABSTRACT_TABLE__USE_HORIZONTAL_HEADER;

	/**
     * The feature id for the '<em><b>Use Vertical Header</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__USE_VERTICAL_HEADER = ABSTRACT_TABLE__USE_VERTICAL_HEADER;

	/**
     * The feature id for the '<em><b>Horizontal Header Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__HORIZONTAL_HEADER_EXPRESSION = ABSTRACT_TABLE__HORIZONTAL_HEADER_EXPRESSION;

	/**
     * The feature id for the '<em><b>Vertical Header Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__VERTICAL_HEADER_EXPRESSION = ABSTRACT_TABLE__VERTICAL_HEADER_EXPRESSION;

	/**
     * The feature id for the '<em><b>Table Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__TABLE_EXPRESSION = ABSTRACT_TABLE__TABLE_EXPRESSION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__VALIDATORS = ABSTRACT_TABLE_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__USE_DEFAULT_VALIDATOR = ABSTRACT_TABLE_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__BELOW = ABSTRACT_TABLE_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__DESCRIPTION = ABSTRACT_TABLE_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__EXAMPLE_MESSAGE_POSITION = ABSTRACT_TABLE_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__EXAMPLE_MESSAGE = ABSTRACT_TABLE_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Column</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_COLUMN = ABSTRACT_TABLE_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Row</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_ROW = ABSTRACT_TABLE_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Allow Add Remove Row</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW = ABSTRACT_TABLE_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Allow Add Remove Column</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__ALLOW_ADD_REMOVE_COLUMN = ABSTRACT_TABLE_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>Limit Max Number Of Column</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_COLUMN = ABSTRACT_TABLE_FEATURE_COUNT + 10;

	/**
     * The feature id for the '<em><b>Limit Max Number Of Row</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_ROW = ABSTRACT_TABLE_FEATURE_COUNT + 11;

	/**
     * The feature id for the '<em><b>Min Number Of Column</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN = ABSTRACT_TABLE_FEATURE_COUNT + 12;

	/**
     * The feature id for the '<em><b>Min Number Of Row</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__MIN_NUMBER_OF_ROW = ABSTRACT_TABLE_FEATURE_COUNT + 13;

	/**
     * The feature id for the '<em><b>Max Number Of Column</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN = ABSTRACT_TABLE_FEATURE_COUNT + 14;

	/**
     * The feature id for the '<em><b>Max Number Of Row</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE__MAX_NUMBER_OF_ROW = ABSTRACT_TABLE_FEATURE_COUNT + 15;

	/**
     * The number of structural features of the '<em>Dynamic Table</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DYNAMIC_TABLE_FEATURE_COUNT = ABSTRACT_TABLE_FEATURE_COUNT + 16;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.IFrameWidgetImpl <em>IFrame Widget</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.IFrameWidgetImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getIFrameWidget()
     * @generated
     */
	int IFRAME_WIDGET = 41;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__DOCUMENTATION = INFO__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__NAME = INFO__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__TEXT_ANNOTATION_ATTACHMENT = INFO__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__HTML_ATTRIBUTES = INFO__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__WIDGET_LAYOUT_INFO = INFO__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__SHOW_DISPLAY_LABEL = INFO__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL = INFO__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__DEPEND_ON = INFO__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__PARENT_OF = INFO__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__MANDATORY = INFO__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__READ_ONLY = INFO__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__LABEL_POSITION = INFO__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__REAL_HTML_ATTRIBUTES = INFO__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__INJECT_WIDGET_CONDITION = INFO__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__VERSION = INFO__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__RETURN_TYPE_MODIFIER = INFO__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = INFO__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__INPUT_EXPRESSION = INFO__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__AFTER_EVENT_EXPRESSION = INFO__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__TOOLTIP = INFO__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__HELP_MESSAGE = INFO__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__DISPLAY_LABEL = INFO__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__INJECT_WIDGET_SCRIPT = INFO__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET__ACTION = INFO__ACTION;

	/**
     * The number of structural features of the '<em>IFrame Widget</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IFRAME_WIDGET_FEATURE_COUNT = INFO_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.MandatoryFieldsCustomizationImpl <em>Mandatory Fields Customization</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.MandatoryFieldsCustomizationImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getMandatoryFieldsCustomization()
     * @generated
     */
	int MANDATORY_FIELDS_CUSTOMIZATION = 42;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MANDATORY_FIELDS_CUSTOMIZATION__HTML_ATTRIBUTES = CSS_CUSTOMIZABLE__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Mandatory Symbol</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL = CSS_CUSTOMIZABLE_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Mandatory Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL = CSS_CUSTOMIZABLE_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Mandatory Fields Customization</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MANDATORY_FIELDS_CUSTOMIZATION_FEATURE_COUNT = CSS_CUSTOMIZABLE_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.HtmlWidgetImpl <em>Html Widget</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.HtmlWidgetImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getHtmlWidget()
     * @generated
     */
	int HTML_WIDGET = 43;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__DOCUMENTATION = INFO__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__NAME = INFO__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__TEXT_ANNOTATION_ATTACHMENT = INFO__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__HTML_ATTRIBUTES = INFO__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__WIDGET_LAYOUT_INFO = INFO__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__SHOW_DISPLAY_LABEL = INFO__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL = INFO__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__DEPEND_ON = INFO__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__PARENT_OF = INFO__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__MANDATORY = INFO__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__READ_ONLY = INFO__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__LABEL_POSITION = INFO__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__REAL_HTML_ATTRIBUTES = INFO__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__INJECT_WIDGET_CONDITION = INFO__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__VERSION = INFO__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__RETURN_TYPE_MODIFIER = INFO__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = INFO__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = INFO__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__INPUT_EXPRESSION = INFO__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__AFTER_EVENT_EXPRESSION = INFO__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__TOOLTIP = INFO__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__HELP_MESSAGE = INFO__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__DISPLAY_LABEL = INFO__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__INJECT_WIDGET_SCRIPT = INFO__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET__ACTION = INFO__ACTION;

	/**
     * The number of structural features of the '<em>Html Widget</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HTML_WIDGET_FEATURE_COUNT = INFO_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.SuggestBoxImpl <em>Suggest Box</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.SuggestBoxImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getSuggestBox()
     * @generated
     */
	int SUGGEST_BOX = 44;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DOCUMENTATION = MULTIPLE_VALUATED_FORM_FIELD__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__NAME = MULTIPLE_VALUATED_FORM_FIELD__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__TEXT_ANNOTATION_ATTACHMENT = MULTIPLE_VALUATED_FORM_FIELD__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Html Attributes</b></em>' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__HTML_ATTRIBUTES = MULTIPLE_VALUATED_FORM_FIELD__HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__WIDGET_LAYOUT_INFO = MULTIPLE_VALUATED_FORM_FIELD__WIDGET_LAYOUT_INFO;

	/**
     * The feature id for the '<em><b>Show Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__SHOW_DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__SHOW_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__ALLOW_HTML_FOR_DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__ALLOW_HTML_FOR_DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Depend On</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DEPEND_ON = MULTIPLE_VALUATED_FORM_FIELD__DEPEND_ON;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED;

	/**
     * The feature id for the '<em><b>Parent Of</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__PARENT_OF = MULTIPLE_VALUATED_FORM_FIELD__PARENT_OF;

	/**
     * The feature id for the '<em><b>Mandatory</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__MANDATORY = MULTIPLE_VALUATED_FORM_FIELD__MANDATORY;

	/**
     * The feature id for the '<em><b>Read Only</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__READ_ONLY = MULTIPLE_VALUATED_FORM_FIELD__READ_ONLY;

	/**
     * The feature id for the '<em><b>Label Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__LABEL_POSITION = MULTIPLE_VALUATED_FORM_FIELD__LABEL_POSITION;

	/**
     * The feature id for the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__REAL_HTML_ATTRIBUTES = MULTIPLE_VALUATED_FORM_FIELD__REAL_HTML_ATTRIBUTES;

	/**
     * The feature id for the '<em><b>Inject Widget Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__INJECT_WIDGET_CONDITION = MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_CONDITION;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__VERSION = MULTIPLE_VALUATED_FORM_FIELD__VERSION;

	/**
     * The feature id for the '<em><b>Return Type Modifier</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__RETURN_TYPE_MODIFIER = MULTIPLE_VALUATED_FORM_FIELD__RETURN_TYPE_MODIFIER;

	/**
     * The feature id for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION;

	/**
     * The feature id for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT;

	/**
     * The feature id for the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__INPUT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__INPUT_EXPRESSION;

	/**
     * The feature id for the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__AFTER_EVENT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__AFTER_EVENT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__TOOLTIP = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP;

	/**
     * The feature id for the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__HELP_MESSAGE = MULTIPLE_VALUATED_FORM_FIELD__HELP_MESSAGE;

	/**
     * The feature id for the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DISPLAY_LABEL = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL;

	/**
     * The feature id for the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__INJECT_WIDGET_SCRIPT = MULTIPLE_VALUATED_FORM_FIELD__INJECT_WIDGET_SCRIPT;

	/**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__ACTION = MULTIPLE_VALUATED_FORM_FIELD__ACTION;

	/**
     * The feature id for the '<em><b>Validators</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__VALIDATORS = MULTIPLE_VALUATED_FORM_FIELD__VALIDATORS;

	/**
     * The feature id for the '<em><b>Use Default Validator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__USE_DEFAULT_VALIDATOR = MULTIPLE_VALUATED_FORM_FIELD__USE_DEFAULT_VALIDATOR;

	/**
     * The feature id for the '<em><b>Below</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__BELOW = MULTIPLE_VALUATED_FORM_FIELD__BELOW;

	/**
     * The feature id for the '<em><b>Duplicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DUPLICATE = MULTIPLE_VALUATED_FORM_FIELD__DUPLICATE;

	/**
     * The feature id for the '<em><b>Limit Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__LIMIT_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__LIMIT_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__LIMIT_MIN_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__LIMIT_MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Max Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__MAX_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__MAX_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Min Number Of Duplication</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__MIN_NUMBER_OF_DUPLICATION = MULTIPLE_VALUATED_FORM_FIELD__MIN_NUMBER_OF_DUPLICATION;

	/**
     * The feature id for the '<em><b>Display Label For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DISPLAY_LABEL_FOR_ADD = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_ADD;

	/**
     * The feature id for the '<em><b>Tooltip For Add</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__TOOLTIP_FOR_ADD = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_ADD;

	/**
     * The feature id for the '<em><b>Display Label For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DISPLAY_LABEL_FOR_REMOVE = MULTIPLE_VALUATED_FORM_FIELD__DISPLAY_LABEL_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Tooltip For Remove</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__TOOLTIP_FOR_REMOVE = MULTIPLE_VALUATED_FORM_FIELD__TOOLTIP_FOR_REMOVE;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DESCRIPTION = MULTIPLE_VALUATED_FORM_FIELD__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Example Message Position</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__EXAMPLE_MESSAGE_POSITION = MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE_POSITION;

	/**
     * The feature id for the '<em><b>Example Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__EXAMPLE_MESSAGE = MULTIPLE_VALUATED_FORM_FIELD__EXAMPLE_MESSAGE;

	/**
     * The feature id for the '<em><b>Default Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DEFAULT_EXPRESSION = MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION;

	/**
     * The feature id for the '<em><b>Default Expression After Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DEFAULT_EXPRESSION_AFTER_EVENT = MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT;

	/**
     * The feature id for the '<em><b>Max Items</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__MAX_ITEMS = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Use Max Items</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__USE_MAX_ITEMS = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Asynchronous</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__ASYNCHRONOUS = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Delay</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX__DELAY = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Suggest Box</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUGGEST_BOX_FEATURE_COUNT = MULTIPLE_VALUATED_FORM_FIELD_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.impl.GroupIteratorImpl <em>Group Iterator</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.impl.GroupIteratorImpl
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getGroupIterator()
     * @generated
     */
	int GROUP_ITERATOR = 45;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP_ITERATOR__DOCUMENTATION = ProcessPackage.ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP_ITERATOR__NAME = ProcessPackage.ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP_ITERATOR__TEXT_ANNOTATION_ATTACHMENT = ProcessPackage.ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP_ITERATOR__CLASS_NAME = ProcessPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Group Iterator</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUP_ITERATOR_FEATURE_COUNT = ProcessPackage.ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.EventDependencyType <em>Event Dependency Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.EventDependencyType
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getEventDependencyType()
     * @generated
     */
	int EVENT_DEPENDENCY_TYPE = 46;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.LabelPosition <em>Label Position</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.LabelPosition
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getLabelPosition()
     * @generated
     */
	int LABEL_POSITION = 47;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.FileWidgetInputType <em>File Widget Input Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.FileWidgetInputType
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getFileWidgetInputType()
     * @generated
     */
	int FILE_WIDGET_INPUT_TYPE = 48;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.form.FileWidgetDownloadType <em>File Widget Download Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.form.FileWidgetDownloadType
     * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getFileWidgetDownloadType()
     * @generated
     */
	int FILE_WIDGET_DOWNLOAD_TYPE = 49;


	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.WidgetDependency <em>Widget Dependency</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Widget Dependency</em>'.
     * @see org.bonitasoft.studio.model.form.WidgetDependency
     * @generated
     */
	EClass getWidgetDependency();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.WidgetDependency#isTriggerRefreshOnModification <em>Trigger Refresh On Modification</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Trigger Refresh On Modification</em>'.
     * @see org.bonitasoft.studio.model.form.WidgetDependency#isTriggerRefreshOnModification()
     * @see #getWidgetDependency()
     * @generated
     */
	EAttribute getWidgetDependency_TriggerRefreshOnModification();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.form.WidgetDependency#getEventTypes <em>Event Types</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Event Types</em>'.
     * @see org.bonitasoft.studio.model.form.WidgetDependency#getEventTypes()
     * @see #getWidgetDependency()
     * @generated
     */
	EAttribute getWidgetDependency_EventTypes();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.form.WidgetDependency#getWidget <em>Widget</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Widget</em>'.
     * @see org.bonitasoft.studio.model.form.WidgetDependency#getWidget()
     * @see #getWidgetDependency()
     * @generated
     */
	EReference getWidgetDependency_Widget();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.Validator <em>Validator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Validator</em>'.
     * @see org.bonitasoft.studio.model.form.Validator
     * @generated
     */
	EClass getValidator();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Validator#getValidatorClass <em>Validator Class</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Validator Class</em>'.
     * @see org.bonitasoft.studio.model.form.Validator#getValidatorClass()
     * @see #getValidator()
     * @generated
     */
	EAttribute getValidator_ValidatorClass();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Validator#getHtmlClass <em>Html Class</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Html Class</em>'.
     * @see org.bonitasoft.studio.model.form.Validator#getHtmlClass()
     * @see #getValidator()
     * @generated
     */
	EAttribute getValidator_HtmlClass();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Validator#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.form.Validator#getName()
     * @see #getValidator()
     * @generated
     */
	EAttribute getValidator_Name();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Validator#isBelowField <em>Below Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Below Field</em>'.
     * @see org.bonitasoft.studio.model.form.Validator#isBelowField()
     * @see #getValidator()
     * @generated
     */
	EAttribute getValidator_BelowField();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Validator#getParameter <em>Parameter</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Parameter</em>'.
     * @see org.bonitasoft.studio.model.form.Validator#getParameter()
     * @see #getValidator()
     * @generated
     */
	EReference getValidator_Parameter();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Validator#getDisplayName <em>Display Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Display Name</em>'.
     * @see org.bonitasoft.studio.model.form.Validator#getDisplayName()
     * @see #getValidator()
     * @generated
     */
	EReference getValidator_DisplayName();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.Validable <em>Validable</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Validable</em>'.
     * @see org.bonitasoft.studio.model.form.Validable
     * @generated
     */
	EClass getValidable();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.form.Validable#getValidators <em>Validators</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Validators</em>'.
     * @see org.bonitasoft.studio.model.form.Validable#getValidators()
     * @see #getValidable()
     * @generated
     */
	EReference getValidable_Validators();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Validable#getUseDefaultValidator <em>Use Default Validator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Default Validator</em>'.
     * @see org.bonitasoft.studio.model.form.Validable#getUseDefaultValidator()
     * @see #getValidable()
     * @generated
     */
	EAttribute getValidable_UseDefaultValidator();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Validable#isBelow <em>Below</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Below</em>'.
     * @see org.bonitasoft.studio.model.form.Validable#isBelow()
     * @see #getValidable()
     * @generated
     */
	EAttribute getValidable_Below();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.Form <em>Form</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Form</em>'.
     * @see org.bonitasoft.studio.model.form.Form
     * @generated
     */
	EClass getForm();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Form#getNColumn <em>NColumn</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>NColumn</em>'.
     * @see org.bonitasoft.studio.model.form.Form#getNColumn()
     * @see #getForm()
     * @generated
     */
	EAttribute getForm_NColumn();

	/**
     * Returns the meta object for the map '{@link org.bonitasoft.studio.model.form.Form#getStringAttributes <em>String Attributes</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the map '<em>String Attributes</em>'.
     * @see org.bonitasoft.studio.model.form.Form#getStringAttributes()
     * @see #getForm()
     * @generated
     */
	EReference getForm_StringAttributes();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Form#getNLine <em>NLine</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>NLine</em>'.
     * @see org.bonitasoft.studio.model.form.Form#getNLine()
     * @see #getForm()
     * @generated
     */
	EAttribute getForm_NLine();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Form#getShowPageLabel <em>Show Page Label</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Show Page Label</em>'.
     * @see org.bonitasoft.studio.model.form.Form#getShowPageLabel()
     * @see #getForm()
     * @generated
     */
	EAttribute getForm_ShowPageLabel();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Form#isAllowHTMLInPageLabel <em>Allow HTML In Page Label</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Allow HTML In Page Label</em>'.
     * @see org.bonitasoft.studio.model.form.Form#isAllowHTMLInPageLabel()
     * @see #getForm()
     * @generated
     */
	EAttribute getForm_AllowHTMLInPageLabel();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Form#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.bonitasoft.studio.model.form.Form#getVersion()
     * @see #getForm()
     * @generated
     */
	EAttribute getForm_Version();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.form.Form#getColumns <em>Columns</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Columns</em>'.
     * @see org.bonitasoft.studio.model.form.Form#getColumns()
     * @see #getForm()
     * @generated
     */
	EReference getForm_Columns();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.form.Form#getLines <em>Lines</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Lines</em>'.
     * @see org.bonitasoft.studio.model.form.Form#getLines()
     * @see #getForm()
     * @generated
     */
	EReference getForm_Lines();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.form.Form#getWidgets <em>Widgets</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Widgets</em>'.
     * @see org.bonitasoft.studio.model.form.Form#getWidgets()
     * @see #getForm()
     * @generated
     */
	EReference getForm_Widgets();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Form#getPageLabel <em>Page Label</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Page Label</em>'.
     * @see org.bonitasoft.studio.model.form.Form#getPageLabel()
     * @see #getForm()
     * @generated
     */
	EReference getForm_PageLabel();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.form.Form#getActions <em>Actions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Actions</em>'.
     * @see org.bonitasoft.studio.model.form.Form#getActions()
     * @see #getForm()
     * @generated
     */
	EReference getForm_Actions();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo <em>Widget Layout Info</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Widget Layout Info</em>'.
     * @see org.bonitasoft.studio.model.form.WidgetLayoutInfo
     * @generated
     */
	EClass getWidgetLayoutInfo();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo#getLine <em>Line</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Line</em>'.
     * @see org.bonitasoft.studio.model.form.WidgetLayoutInfo#getLine()
     * @see #getWidgetLayoutInfo()
     * @generated
     */
	EAttribute getWidgetLayoutInfo_Line();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo#getColumn <em>Column</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Column</em>'.
     * @see org.bonitasoft.studio.model.form.WidgetLayoutInfo#getColumn()
     * @see #getWidgetLayoutInfo()
     * @generated
     */
	EAttribute getWidgetLayoutInfo_Column();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo#getVerticalSpan <em>Vertical Span</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Vertical Span</em>'.
     * @see org.bonitasoft.studio.model.form.WidgetLayoutInfo#getVerticalSpan()
     * @see #getWidgetLayoutInfo()
     * @generated
     */
	EAttribute getWidgetLayoutInfo_VerticalSpan();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.WidgetLayoutInfo#getHorizontalSpan <em>Horizontal Span</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Horizontal Span</em>'.
     * @see org.bonitasoft.studio.model.form.WidgetLayoutInfo#getHorizontalSpan()
     * @see #getWidgetLayoutInfo()
     * @generated
     */
	EAttribute getWidgetLayoutInfo_HorizontalSpan();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.Column <em>Column</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Column</em>'.
     * @see org.bonitasoft.studio.model.form.Column
     * @generated
     */
	EClass getColumn();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Column#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.bonitasoft.studio.model.form.Column#getWidth()
     * @see #getColumn()
     * @generated
     */
	EAttribute getColumn_Width();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Column#getNumber <em>Number</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Number</em>'.
     * @see org.bonitasoft.studio.model.form.Column#getNumber()
     * @see #getColumn()
     * @generated
     */
	EAttribute getColumn_Number();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.Line <em>Line</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Line</em>'.
     * @see org.bonitasoft.studio.model.form.Line
     * @generated
     */
	EClass getLine();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Line#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.bonitasoft.studio.model.form.Line#getHeight()
     * @see #getLine()
     * @generated
     */
	EAttribute getLine_Height();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Line#getNumber <em>Number</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Number</em>'.
     * @see org.bonitasoft.studio.model.form.Line#getNumber()
     * @see #getLine()
     * @generated
     */
	EAttribute getLine_Number();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.ItemContainer <em>Item Container</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Item Container</em>'.
     * @see org.bonitasoft.studio.model.form.ItemContainer
     * @generated
     */
	EClass getItemContainer();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.ItemContainer#getItemClass <em>Item Class</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Item Class</em>'.
     * @see org.bonitasoft.studio.model.form.ItemContainer#getItemClass()
     * @see #getItemContainer()
     * @generated
     */
	EAttribute getItemContainer_ItemClass();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.Duplicable <em>Duplicable</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Duplicable</em>'.
     * @see org.bonitasoft.studio.model.form.Duplicable
     * @generated
     */
	EClass getDuplicable();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Duplicable#isDuplicate <em>Duplicate</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Duplicate</em>'.
     * @see org.bonitasoft.studio.model.form.Duplicable#isDuplicate()
     * @see #getDuplicable()
     * @generated
     */
	EAttribute getDuplicable_Duplicate();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Duplicable#isLimitNumberOfDuplication <em>Limit Number Of Duplication</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Limit Number Of Duplication</em>'.
     * @see org.bonitasoft.studio.model.form.Duplicable#isLimitNumberOfDuplication()
     * @see #getDuplicable()
     * @generated
     */
	EAttribute getDuplicable_LimitNumberOfDuplication();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Duplicable#isLimitMinNumberOfDuplication <em>Limit Min Number Of Duplication</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Limit Min Number Of Duplication</em>'.
     * @see org.bonitasoft.studio.model.form.Duplicable#isLimitMinNumberOfDuplication()
     * @see #getDuplicable()
     * @generated
     */
	EAttribute getDuplicable_LimitMinNumberOfDuplication();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Duplicable#getMaxNumberOfDuplication <em>Max Number Of Duplication</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Max Number Of Duplication</em>'.
     * @see org.bonitasoft.studio.model.form.Duplicable#getMaxNumberOfDuplication()
     * @see #getDuplicable()
     * @generated
     */
	EReference getDuplicable_MaxNumberOfDuplication();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Duplicable#getMinNumberOfDuplication <em>Min Number Of Duplication</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Min Number Of Duplication</em>'.
     * @see org.bonitasoft.studio.model.form.Duplicable#getMinNumberOfDuplication()
     * @see #getDuplicable()
     * @generated
     */
	EReference getDuplicable_MinNumberOfDuplication();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Duplicable#getDisplayLabelForAdd <em>Display Label For Add</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Display Label For Add</em>'.
     * @see org.bonitasoft.studio.model.form.Duplicable#getDisplayLabelForAdd()
     * @see #getDuplicable()
     * @generated
     */
	EReference getDuplicable_DisplayLabelForAdd();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Duplicable#getTooltipForAdd <em>Tooltip For Add</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Tooltip For Add</em>'.
     * @see org.bonitasoft.studio.model.form.Duplicable#getTooltipForAdd()
     * @see #getDuplicable()
     * @generated
     */
	EReference getDuplicable_TooltipForAdd();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Duplicable#getDisplayLabelForRemove <em>Display Label For Remove</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Display Label For Remove</em>'.
     * @see org.bonitasoft.studio.model.form.Duplicable#getDisplayLabelForRemove()
     * @see #getDuplicable()
     * @generated
     */
	EReference getDuplicable_DisplayLabelForRemove();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Duplicable#getTooltipForRemove <em>Tooltip For Remove</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Tooltip For Remove</em>'.
     * @see org.bonitasoft.studio.model.form.Duplicable#getTooltipForRemove()
     * @see #getDuplicable()
     * @generated
     */
	EReference getDuplicable_TooltipForRemove();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.ViewForm <em>View Form</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>View Form</em>'.
     * @see org.bonitasoft.studio.model.form.ViewForm
     * @generated
     */
	EClass getViewForm();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.CSSCustomizable <em>CSS Customizable</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>CSS Customizable</em>'.
     * @see org.bonitasoft.studio.model.form.CSSCustomizable
     * @generated
     */
	EClass getCSSCustomizable();

	/**
     * Returns the meta object for the map '{@link org.bonitasoft.studio.model.form.CSSCustomizable#getHtmlAttributes <em>Html Attributes</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Html Attributes</em>'.
     * @see org.bonitasoft.studio.model.form.CSSCustomizable#getHtmlAttributes()
     * @see #getCSSCustomizable()
     * @generated
     */
	EReference getCSSCustomizable_HtmlAttributes();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.Widget <em>Widget</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Widget</em>'.
     * @see org.bonitasoft.studio.model.form.Widget
     * @generated
     */
	EClass getWidget();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Widget#getWidgetLayoutInfo <em>Widget Layout Info</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Widget Layout Info</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getWidgetLayoutInfo()
     * @see #getWidget()
     * @generated
     */
	EReference getWidget_WidgetLayoutInfo();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Widget#getShowDisplayLabel <em>Show Display Label</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Show Display Label</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getShowDisplayLabel()
     * @see #getWidget()
     * @generated
     */
	EAttribute getWidget_ShowDisplayLabel();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Widget#isAllowHTMLForDisplayLabel <em>Allow HTML For Display Label</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Allow HTML For Display Label</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#isAllowHTMLForDisplayLabel()
     * @see #getWidget()
     * @generated
     */
	EAttribute getWidget_AllowHTMLForDisplayLabel();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.form.Widget#getDependOn <em>Depend On</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Depend On</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getDependOn()
     * @see #getWidget()
     * @generated
     */
	EReference getWidget_DependOn();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Widget#isDisplayDependentWidgetOnlyOnEventTriggered <em>Display Dependent Widget Only On Event Triggered</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Display Dependent Widget Only On Event Triggered</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#isDisplayDependentWidgetOnlyOnEventTriggered()
     * @see #getWidget()
     * @generated
     */
	EAttribute getWidget_DisplayDependentWidgetOnlyOnEventTriggered();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.form.Widget#getParentOf <em>Parent Of</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Parent Of</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getParentOf()
     * @see #getWidget()
     * @generated
     */
	EReference getWidget_ParentOf();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Widget#isMandatory <em>Mandatory</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Mandatory</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#isMandatory()
     * @see #getWidget()
     * @generated
     */
	EAttribute getWidget_Mandatory();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Widget#isReadOnly <em>Read Only</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Read Only</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#isReadOnly()
     * @see #getWidget()
     * @generated
     */
	EAttribute getWidget_ReadOnly();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Widget#getLabelPosition <em>Label Position</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label Position</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getLabelPosition()
     * @see #getWidget()
     * @generated
     */
	EAttribute getWidget_LabelPosition();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Widget#getRealHtmlAttributes <em>Real Html Attributes</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Real Html Attributes</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getRealHtmlAttributes()
     * @see #getWidget()
     * @generated
     */
	EAttribute getWidget_RealHtmlAttributes();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Widget#isInjectWidgetCondition <em>Inject Widget Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Inject Widget Condition</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#isInjectWidgetCondition()
     * @see #getWidget()
     * @generated
     */
	EAttribute getWidget_InjectWidgetCondition();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Widget#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getVersion()
     * @see #getWidget()
     * @generated
     */
	EAttribute getWidget_Version();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Widget#getReturnTypeModifier <em>Return Type Modifier</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Return Type Modifier</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getReturnTypeModifier()
     * @see #getWidget()
     * @generated
     */
	EAttribute getWidget_ReturnTypeModifier();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Widget#getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition <em>Display Dependent Widget Only After First Event Triggered And Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Display Dependent Widget Only After First Event Triggered And Condition</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition()
     * @see #getWidget()
     * @generated
     */
	EReference getWidget_DisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Widget#getDisplayAfterEventDependsOnConditionScript <em>Display After Event Depends On Condition Script</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Display After Event Depends On Condition Script</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getDisplayAfterEventDependsOnConditionScript()
     * @see #getWidget()
     * @generated
     */
	EReference getWidget_DisplayAfterEventDependsOnConditionScript();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Widget#getInputExpression <em>Input Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Input Expression</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getInputExpression()
     * @see #getWidget()
     * @generated
     */
	EReference getWidget_InputExpression();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Widget#getAfterEventExpression <em>After Event Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>After Event Expression</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getAfterEventExpression()
     * @see #getWidget()
     * @generated
     */
	EReference getWidget_AfterEventExpression();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Widget#getTooltip <em>Tooltip</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Tooltip</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getTooltip()
     * @see #getWidget()
     * @generated
     */
	EReference getWidget_Tooltip();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Widget#getHelpMessage <em>Help Message</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Help Message</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getHelpMessage()
     * @see #getWidget()
     * @generated
     */
	EReference getWidget_HelpMessage();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Widget#getDisplayLabel <em>Display Label</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Display Label</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getDisplayLabel()
     * @see #getWidget()
     * @generated
     */
	EReference getWidget_DisplayLabel();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Widget#getInjectWidgetScript <em>Inject Widget Script</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Inject Widget Script</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getInjectWidgetScript()
     * @see #getWidget()
     * @generated
     */
	EReference getWidget_InjectWidgetScript();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Widget#getAction <em>Action</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Action</em>'.
     * @see org.bonitasoft.studio.model.form.Widget#getAction()
     * @see #getWidget()
     * @generated
     */
	EReference getWidget_Action();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.Group <em>Group</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Group</em>'.
     * @see org.bonitasoft.studio.model.form.Group
     * @generated
     */
	EClass getGroup();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.form.Group#getWidgets <em>Widgets</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Widgets</em>'.
     * @see org.bonitasoft.studio.model.form.Group#getWidgets()
     * @see #getGroup()
     * @generated
     */
	EReference getGroup_Widgets();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Group#isShowBorder <em>Show Border</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Show Border</em>'.
     * @see org.bonitasoft.studio.model.form.Group#isShowBorder()
     * @see #getGroup()
     * @generated
     */
	EAttribute getGroup_ShowBorder();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.form.Group#getColumns <em>Columns</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Columns</em>'.
     * @see org.bonitasoft.studio.model.form.Group#getColumns()
     * @see #getGroup()
     * @generated
     */
	EReference getGroup_Columns();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.form.Group#getLines <em>Lines</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Lines</em>'.
     * @see org.bonitasoft.studio.model.form.Group#getLines()
     * @see #getGroup()
     * @generated
     */
	EReference getGroup_Lines();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Group#isUseIterator <em>Use Iterator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Iterator</em>'.
     * @see org.bonitasoft.studio.model.form.Group#isUseIterator()
     * @see #getGroup()
     * @generated
     */
	EAttribute getGroup_UseIterator();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Group#getIterator <em>Iterator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Iterator</em>'.
     * @see org.bonitasoft.studio.model.form.Group#getIterator()
     * @see #getGroup()
     * @generated
     */
	EReference getGroup_Iterator();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.FormField <em>Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Field</em>'.
     * @see org.bonitasoft.studio.model.form.FormField
     * @generated
     */
	EClass getFormField();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.FormField#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.model.form.FormField#getDescription()
     * @see #getFormField()
     * @generated
     */
	EAttribute getFormField_Description();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.FormField#getExampleMessagePosition <em>Example Message Position</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Example Message Position</em>'.
     * @see org.bonitasoft.studio.model.form.FormField#getExampleMessagePosition()
     * @see #getFormField()
     * @generated
     */
	EAttribute getFormField_ExampleMessagePosition();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.FormField#getExampleMessage <em>Example Message</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Example Message</em>'.
     * @see org.bonitasoft.studio.model.form.FormField#getExampleMessage()
     * @see #getFormField()
     * @generated
     */
	EReference getFormField_ExampleMessage();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.MultipleValuatedFormField <em>Multiple Valuated Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Multiple Valuated Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.MultipleValuatedFormField
     * @generated
     */
	EClass getMultipleValuatedFormField();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.MultipleValuatedFormField#getDefaultExpression <em>Default Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Default Expression</em>'.
     * @see org.bonitasoft.studio.model.form.MultipleValuatedFormField#getDefaultExpression()
     * @see #getMultipleValuatedFormField()
     * @generated
     */
	EReference getMultipleValuatedFormField_DefaultExpression();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.MultipleValuatedFormField#getDefaultExpressionAfterEvent <em>Default Expression After Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Default Expression After Event</em>'.
     * @see org.bonitasoft.studio.model.form.MultipleValuatedFormField#getDefaultExpressionAfterEvent()
     * @see #getMultipleValuatedFormField()
     * @generated
     */
	EReference getMultipleValuatedFormField_DefaultExpressionAfterEvent();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.SingleValuatedFormField <em>Single Valuated Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Single Valuated Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.SingleValuatedFormField
     * @generated
     */
	EClass getSingleValuatedFormField();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.CheckBoxMultipleFormField <em>Check Box Multiple Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Check Box Multiple Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.CheckBoxMultipleFormField
     * @generated
     */
	EClass getCheckBoxMultipleFormField();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.ComboFormField <em>Combo Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Combo Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.ComboFormField
     * @generated
     */
	EClass getComboFormField();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.DateFormField <em>Date Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Date Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.DateFormField
     * @generated
     */
	EClass getDateFormField();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.DateFormField#getInitialFormat <em>Initial Format</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Initial Format</em>'.
     * @see org.bonitasoft.studio.model.form.DateFormField#getInitialFormat()
     * @see #getDateFormField()
     * @generated
     */
	EAttribute getDateFormField_InitialFormat();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.DateFormField#getDisplayFormat <em>Display Format</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Display Format</em>'.
     * @see org.bonitasoft.studio.model.form.DateFormField#getDisplayFormat()
     * @see #getDateFormField()
     * @generated
     */
	EAttribute getDateFormField_DisplayFormat();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.ListFormField <em>List Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>List Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.ListFormField
     * @generated
     */
	EClass getListFormField();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.ListFormField#getMaxHeigth <em>Max Heigth</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max Heigth</em>'.
     * @see org.bonitasoft.studio.model.form.ListFormField#getMaxHeigth()
     * @see #getListFormField()
     * @generated
     */
	EAttribute getListFormField_MaxHeigth();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.PasswordFormField <em>Password Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Password Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.PasswordFormField
     * @generated
     */
	EClass getPasswordFormField();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.PasswordFormField#getMaxLength <em>Max Length</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max Length</em>'.
     * @see org.bonitasoft.studio.model.form.PasswordFormField#getMaxLength()
     * @see #getPasswordFormField()
     * @generated
     */
	EAttribute getPasswordFormField_MaxLength();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.RadioFormField <em>Radio Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Radio Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.RadioFormField
     * @generated
     */
	EClass getRadioFormField();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.SelectFormField <em>Select Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Select Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.SelectFormField
     * @generated
     */
	EClass getSelectFormField();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.TextFormField <em>Text Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Text Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.TextFormField
     * @generated
     */
	EClass getTextFormField();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.TextFormField#getMaxLength <em>Max Length</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max Length</em>'.
     * @see org.bonitasoft.studio.model.form.TextFormField#getMaxLength()
     * @see #getTextFormField()
     * @generated
     */
	EAttribute getTextFormField_MaxLength();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.TextAreaFormField <em>Text Area Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Text Area Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.TextAreaFormField
     * @generated
     */
	EClass getTextAreaFormField();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.TextAreaFormField#getMaxLength <em>Max Length</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max Length</em>'.
     * @see org.bonitasoft.studio.model.form.TextAreaFormField#getMaxLength()
     * @see #getTextAreaFormField()
     * @generated
     */
	EAttribute getTextAreaFormField_MaxLength();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.TextAreaFormField#getMaxHeigth <em>Max Heigth</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max Heigth</em>'.
     * @see org.bonitasoft.studio.model.form.TextAreaFormField#getMaxHeigth()
     * @see #getTextAreaFormField()
     * @generated
     */
	EAttribute getTextAreaFormField_MaxHeigth();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.RichTextAreaFormField <em>Rich Text Area Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Rich Text Area Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.RichTextAreaFormField
     * @generated
     */
	EClass getRichTextAreaFormField();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.FormButton <em>Button</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Button</em>'.
     * @see org.bonitasoft.studio.model.form.FormButton
     * @generated
     */
	EClass getFormButton();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.FormButton#getLabelBehavior <em>Label Behavior</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label Behavior</em>'.
     * @see org.bonitasoft.studio.model.form.FormButton#getLabelBehavior()
     * @see #getFormButton()
     * @generated
     */
	EAttribute getFormButton_LabelBehavior();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.SubmitFormButton <em>Submit Form Button</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Submit Form Button</em>'.
     * @see org.bonitasoft.studio.model.form.SubmitFormButton
     * @generated
     */
	EClass getSubmitFormButton();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.form.SubmitFormButton#getActions <em>Actions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Actions</em>'.
     * @see org.bonitasoft.studio.model.form.SubmitFormButton#getActions()
     * @see #getSubmitFormButton()
     * @generated
     */
	EReference getSubmitFormButton_Actions();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.PreviousFormButton <em>Previous Form Button</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Previous Form Button</em>'.
     * @see org.bonitasoft.studio.model.form.PreviousFormButton
     * @generated
     */
	EClass getPreviousFormButton();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.NextFormButton <em>Next Form Button</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Next Form Button</em>'.
     * @see org.bonitasoft.studio.model.form.NextFormButton
     * @generated
     */
	EClass getNextFormButton();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.Info <em>Info</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Info</em>'.
     * @see org.bonitasoft.studio.model.form.Info
     * @generated
     */
	EClass getInfo();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.TextInfo <em>Text Info</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Text Info</em>'.
     * @see org.bonitasoft.studio.model.form.TextInfo
     * @generated
     */
	EClass getTextInfo();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.MessageInfo <em>Message Info</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Message Info</em>'.
     * @see org.bonitasoft.studio.model.form.MessageInfo
     * @generated
     */
	EClass getMessageInfo();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.CheckBoxSingleFormField <em>Check Box Single Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Check Box Single Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.CheckBoxSingleFormField
     * @generated
     */
	EClass getCheckBoxSingleFormField();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.FileWidget <em>File Widget</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>File Widget</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidget
     * @generated
     */
	EClass getFileWidget();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.FileWidget#isDownloadOnly <em>Download Only</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Download Only</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidget#isDownloadOnly()
     * @see #getFileWidget()
     * @generated
     */
	EAttribute getFileWidget_DownloadOnly();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.FileWidget#isUsePreview <em>Use Preview</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Preview</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidget#isUsePreview()
     * @see #getFileWidget()
     * @generated
     */
	EAttribute getFileWidget_UsePreview();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.form.FileWidget#getDocument <em>Document</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Document</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidget#getDocument()
     * @see #getFileWidget()
     * @generated
     */
	EReference getFileWidget_Document();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.FileWidget#getInitialResourcePath <em>Initial Resource Path</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Initial Resource Path</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidget#getInitialResourcePath()
     * @see #getFileWidget()
     * @generated
     */
	EAttribute getFileWidget_InitialResourcePath();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.FileWidget#getOutputDocumentName <em>Output Document Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Output Document Name</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidget#getOutputDocumentName()
     * @see #getFileWidget()
     * @generated
     */
	EAttribute getFileWidget_OutputDocumentName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.FileWidget#isUpdateDocument <em>Update Document</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Update Document</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidget#isUpdateDocument()
     * @see #getFileWidget()
     * @generated
     */
	EAttribute getFileWidget_UpdateDocument();

	/**
     * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.model.form.FileWidget#getIntialResourceList <em>Intial Resource List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Intial Resource List</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidget#getIntialResourceList()
     * @see #getFileWidget()
     * @generated
     */
	EAttribute getFileWidget_IntialResourceList();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.FileWidget#getInputType <em>Input Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Input Type</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidget#getInputType()
     * @see #getFileWidget()
     * @generated
     */
	EAttribute getFileWidget_InputType();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.FileWidget#getOutputDocumentListExpression <em>Output Document List Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Output Document List Expression</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidget#getOutputDocumentListExpression()
     * @see #getFileWidget()
     * @generated
     */
	EReference getFileWidget_OutputDocumentListExpression();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.FileWidget#getDownloadType <em>Download Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Download Type</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidget#getDownloadType()
     * @see #getFileWidget()
     * @generated
     */
	EAttribute getFileWidget_DownloadType();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.ImageWidget <em>Image Widget</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Image Widget</em>'.
     * @see org.bonitasoft.studio.model.form.ImageWidget
     * @generated
     */
	EClass getImageWidget();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.ImageWidget#isIsADocument <em>Is ADocument</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Is ADocument</em>'.
     * @see org.bonitasoft.studio.model.form.ImageWidget#isIsADocument()
     * @see #getImageWidget()
     * @generated
     */
	EAttribute getImageWidget_IsADocument();

	/**
     * Returns the meta object for the reference '{@link org.bonitasoft.studio.model.form.ImageWidget#getDocument <em>Document</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Document</em>'.
     * @see org.bonitasoft.studio.model.form.ImageWidget#getDocument()
     * @see #getImageWidget()
     * @generated
     */
	EReference getImageWidget_Document();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.ImageWidget#getImgPath <em>Img Path</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Img Path</em>'.
     * @see org.bonitasoft.studio.model.form.ImageWidget#getImgPath()
     * @see #getImageWidget()
     * @generated
     */
	EReference getImageWidget_ImgPath();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.HiddenWidget <em>Hidden Widget</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Hidden Widget</em>'.
     * @see org.bonitasoft.studio.model.form.HiddenWidget
     * @generated
     */
	EClass getHiddenWidget();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.DurationFormField <em>Duration Form Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Duration Form Field</em>'.
     * @see org.bonitasoft.studio.model.form.DurationFormField
     * @generated
     */
	EClass getDurationFormField();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.DurationFormField#getDay <em>Day</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Day</em>'.
     * @see org.bonitasoft.studio.model.form.DurationFormField#getDay()
     * @see #getDurationFormField()
     * @generated
     */
	EAttribute getDurationFormField_Day();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.DurationFormField#getHour <em>Hour</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Hour</em>'.
     * @see org.bonitasoft.studio.model.form.DurationFormField#getHour()
     * @see #getDurationFormField()
     * @generated
     */
	EAttribute getDurationFormField_Hour();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.DurationFormField#getMin <em>Min</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Min</em>'.
     * @see org.bonitasoft.studio.model.form.DurationFormField#getMin()
     * @see #getDurationFormField()
     * @generated
     */
	EAttribute getDurationFormField_Min();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.DurationFormField#getSec <em>Sec</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Sec</em>'.
     * @see org.bonitasoft.studio.model.form.DurationFormField#getSec()
     * @see #getDurationFormField()
     * @generated
     */
	EAttribute getDurationFormField_Sec();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.AbstractTable <em>Abstract Table</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Table</em>'.
     * @see org.bonitasoft.studio.model.form.AbstractTable
     * @generated
     */
	EClass getAbstractTable();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.AbstractTable#isLeftColumnIsHeader <em>Left Column Is Header</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Left Column Is Header</em>'.
     * @see org.bonitasoft.studio.model.form.AbstractTable#isLeftColumnIsHeader()
     * @see #getAbstractTable()
     * @generated
     */
	EAttribute getAbstractTable_LeftColumnIsHeader();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.AbstractTable#isRightColumnIsHeader <em>Right Column Is Header</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Right Column Is Header</em>'.
     * @see org.bonitasoft.studio.model.form.AbstractTable#isRightColumnIsHeader()
     * @see #getAbstractTable()
     * @generated
     */
	EAttribute getAbstractTable_RightColumnIsHeader();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.AbstractTable#isFirstRowIsHeader <em>First Row Is Header</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>First Row Is Header</em>'.
     * @see org.bonitasoft.studio.model.form.AbstractTable#isFirstRowIsHeader()
     * @see #getAbstractTable()
     * @generated
     */
	EAttribute getAbstractTable_FirstRowIsHeader();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.AbstractTable#isLastRowIsHeader <em>Last Row Is Header</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Last Row Is Header</em>'.
     * @see org.bonitasoft.studio.model.form.AbstractTable#isLastRowIsHeader()
     * @see #getAbstractTable()
     * @generated
     */
	EAttribute getAbstractTable_LastRowIsHeader();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.AbstractTable#isInitializedUsingCells <em>Initialized Using Cells</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Initialized Using Cells</em>'.
     * @see org.bonitasoft.studio.model.form.AbstractTable#isInitializedUsingCells()
     * @see #getAbstractTable()
     * @generated
     */
	EAttribute getAbstractTable_InitializedUsingCells();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.AbstractTable#isUseHorizontalHeader <em>Use Horizontal Header</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Horizontal Header</em>'.
     * @see org.bonitasoft.studio.model.form.AbstractTable#isUseHorizontalHeader()
     * @see #getAbstractTable()
     * @generated
     */
	EAttribute getAbstractTable_UseHorizontalHeader();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.AbstractTable#isUseVerticalHeader <em>Use Vertical Header</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Vertical Header</em>'.
     * @see org.bonitasoft.studio.model.form.AbstractTable#isUseVerticalHeader()
     * @see #getAbstractTable()
     * @generated
     */
	EAttribute getAbstractTable_UseVerticalHeader();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.AbstractTable#getHorizontalHeaderExpression <em>Horizontal Header Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Horizontal Header Expression</em>'.
     * @see org.bonitasoft.studio.model.form.AbstractTable#getHorizontalHeaderExpression()
     * @see #getAbstractTable()
     * @generated
     */
	EReference getAbstractTable_HorizontalHeaderExpression();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.AbstractTable#getVerticalHeaderExpression <em>Vertical Header Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Vertical Header Expression</em>'.
     * @see org.bonitasoft.studio.model.form.AbstractTable#getVerticalHeaderExpression()
     * @see #getAbstractTable()
     * @generated
     */
	EReference getAbstractTable_VerticalHeaderExpression();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.AbstractTable#getTableExpression <em>Table Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Table Expression</em>'.
     * @see org.bonitasoft.studio.model.form.AbstractTable#getTableExpression()
     * @see #getAbstractTable()
     * @generated
     */
	EReference getAbstractTable_TableExpression();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.Table <em>Table</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table</em>'.
     * @see org.bonitasoft.studio.model.form.Table
     * @generated
     */
	EClass getTable();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Table#isUsePagination <em>Use Pagination</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Pagination</em>'.
     * @see org.bonitasoft.studio.model.form.Table#isUsePagination()
     * @see #getTable()
     * @generated
     */
	EAttribute getTable_UsePagination();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Table#isAllowSelection <em>Allow Selection</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Allow Selection</em>'.
     * @see org.bonitasoft.studio.model.form.Table#isAllowSelection()
     * @see #getTable()
     * @generated
     */
	EAttribute getTable_AllowSelection();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.Table#isSelectionModeIsMultiple <em>Selection Mode Is Multiple</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Selection Mode Is Multiple</em>'.
     * @see org.bonitasoft.studio.model.form.Table#isSelectionModeIsMultiple()
     * @see #getTable()
     * @generated
     */
	EAttribute getTable_SelectionModeIsMultiple();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Table#getMaxRowForPagination <em>Max Row For Pagination</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Max Row For Pagination</em>'.
     * @see org.bonitasoft.studio.model.form.Table#getMaxRowForPagination()
     * @see #getTable()
     * @generated
     */
	EReference getTable_MaxRowForPagination();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Table#getColumnForInitialSelectionIndex <em>Column For Initial Selection Index</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Column For Initial Selection Index</em>'.
     * @see org.bonitasoft.studio.model.form.Table#getColumnForInitialSelectionIndex()
     * @see #getTable()
     * @generated
     */
	EReference getTable_ColumnForInitialSelectionIndex();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.Table#getSelectedValues <em>Selected Values</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Selected Values</em>'.
     * @see org.bonitasoft.studio.model.form.Table#getSelectedValues()
     * @see #getTable()
     * @generated
     */
	EReference getTable_SelectedValues();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.DynamicTable <em>Dynamic Table</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Dynamic Table</em>'.
     * @see org.bonitasoft.studio.model.form.DynamicTable
     * @generated
     */
	EClass getDynamicTable();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.DynamicTable#isLimitMinNumberOfColumn <em>Limit Min Number Of Column</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Limit Min Number Of Column</em>'.
     * @see org.bonitasoft.studio.model.form.DynamicTable#isLimitMinNumberOfColumn()
     * @see #getDynamicTable()
     * @generated
     */
	EAttribute getDynamicTable_LimitMinNumberOfColumn();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.DynamicTable#isLimitMinNumberOfRow <em>Limit Min Number Of Row</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Limit Min Number Of Row</em>'.
     * @see org.bonitasoft.studio.model.form.DynamicTable#isLimitMinNumberOfRow()
     * @see #getDynamicTable()
     * @generated
     */
	EAttribute getDynamicTable_LimitMinNumberOfRow();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.DynamicTable#isAllowAddRemoveRow <em>Allow Add Remove Row</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Allow Add Remove Row</em>'.
     * @see org.bonitasoft.studio.model.form.DynamicTable#isAllowAddRemoveRow()
     * @see #getDynamicTable()
     * @generated
     */
	EAttribute getDynamicTable_AllowAddRemoveRow();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.DynamicTable#isAllowAddRemoveColumn <em>Allow Add Remove Column</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Allow Add Remove Column</em>'.
     * @see org.bonitasoft.studio.model.form.DynamicTable#isAllowAddRemoveColumn()
     * @see #getDynamicTable()
     * @generated
     */
	EAttribute getDynamicTable_AllowAddRemoveColumn();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.DynamicTable#isLimitMaxNumberOfColumn <em>Limit Max Number Of Column</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Limit Max Number Of Column</em>'.
     * @see org.bonitasoft.studio.model.form.DynamicTable#isLimitMaxNumberOfColumn()
     * @see #getDynamicTable()
     * @generated
     */
	EAttribute getDynamicTable_LimitMaxNumberOfColumn();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.DynamicTable#isLimitMaxNumberOfRow <em>Limit Max Number Of Row</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Limit Max Number Of Row</em>'.
     * @see org.bonitasoft.studio.model.form.DynamicTable#isLimitMaxNumberOfRow()
     * @see #getDynamicTable()
     * @generated
     */
	EAttribute getDynamicTable_LimitMaxNumberOfRow();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.DynamicTable#getMinNumberOfColumn <em>Min Number Of Column</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Min Number Of Column</em>'.
     * @see org.bonitasoft.studio.model.form.DynamicTable#getMinNumberOfColumn()
     * @see #getDynamicTable()
     * @generated
     */
	EReference getDynamicTable_MinNumberOfColumn();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.DynamicTable#getMinNumberOfRow <em>Min Number Of Row</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Min Number Of Row</em>'.
     * @see org.bonitasoft.studio.model.form.DynamicTable#getMinNumberOfRow()
     * @see #getDynamicTable()
     * @generated
     */
	EReference getDynamicTable_MinNumberOfRow();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.DynamicTable#getMaxNumberOfColumn <em>Max Number Of Column</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Max Number Of Column</em>'.
     * @see org.bonitasoft.studio.model.form.DynamicTable#getMaxNumberOfColumn()
     * @see #getDynamicTable()
     * @generated
     */
	EReference getDynamicTable_MaxNumberOfColumn();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.DynamicTable#getMaxNumberOfRow <em>Max Number Of Row</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Max Number Of Row</em>'.
     * @see org.bonitasoft.studio.model.form.DynamicTable#getMaxNumberOfRow()
     * @see #getDynamicTable()
     * @generated
     */
	EReference getDynamicTable_MaxNumberOfRow();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.IFrameWidget <em>IFrame Widget</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>IFrame Widget</em>'.
     * @see org.bonitasoft.studio.model.form.IFrameWidget
     * @generated
     */
	EClass getIFrameWidget();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.MandatoryFieldsCustomization <em>Mandatory Fields Customization</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Mandatory Fields Customization</em>'.
     * @see org.bonitasoft.studio.model.form.MandatoryFieldsCustomization
     * @generated
     */
	EClass getMandatoryFieldsCustomization();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.MandatoryFieldsCustomization#getMandatorySymbol <em>Mandatory Symbol</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Mandatory Symbol</em>'.
     * @see org.bonitasoft.studio.model.form.MandatoryFieldsCustomization#getMandatorySymbol()
     * @see #getMandatoryFieldsCustomization()
     * @generated
     */
	EReference getMandatoryFieldsCustomization_MandatorySymbol();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.form.MandatoryFieldsCustomization#getMandatoryLabel <em>Mandatory Label</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Mandatory Label</em>'.
     * @see org.bonitasoft.studio.model.form.MandatoryFieldsCustomization#getMandatoryLabel()
     * @see #getMandatoryFieldsCustomization()
     * @generated
     */
	EReference getMandatoryFieldsCustomization_MandatoryLabel();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.HtmlWidget <em>Html Widget</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Html Widget</em>'.
     * @see org.bonitasoft.studio.model.form.HtmlWidget
     * @generated
     */
	EClass getHtmlWidget();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.SuggestBox <em>Suggest Box</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Suggest Box</em>'.
     * @see org.bonitasoft.studio.model.form.SuggestBox
     * @generated
     */
	EClass getSuggestBox();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.SuggestBox#getMaxItems <em>Max Items</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max Items</em>'.
     * @see org.bonitasoft.studio.model.form.SuggestBox#getMaxItems()
     * @see #getSuggestBox()
     * @generated
     */
	EAttribute getSuggestBox_MaxItems();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.SuggestBox#isUseMaxItems <em>Use Max Items</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Max Items</em>'.
     * @see org.bonitasoft.studio.model.form.SuggestBox#isUseMaxItems()
     * @see #getSuggestBox()
     * @generated
     */
	EAttribute getSuggestBox_UseMaxItems();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.SuggestBox#isAsynchronous <em>Asynchronous</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Asynchronous</em>'.
     * @see org.bonitasoft.studio.model.form.SuggestBox#isAsynchronous()
     * @see #getSuggestBox()
     * @generated
     */
	EAttribute getSuggestBox_Asynchronous();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.SuggestBox#getDelay <em>Delay</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Delay</em>'.
     * @see org.bonitasoft.studio.model.form.SuggestBox#getDelay()
     * @see #getSuggestBox()
     * @generated
     */
	EAttribute getSuggestBox_Delay();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.form.GroupIterator <em>Group Iterator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Group Iterator</em>'.
     * @see org.bonitasoft.studio.model.form.GroupIterator
     * @generated
     */
	EClass getGroupIterator();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.form.GroupIterator#getClassName <em>Class Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Class Name</em>'.
     * @see org.bonitasoft.studio.model.form.GroupIterator#getClassName()
     * @see #getGroupIterator()
     * @generated
     */
	EAttribute getGroupIterator_ClassName();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.form.EventDependencyType <em>Event Dependency Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Event Dependency Type</em>'.
     * @see org.bonitasoft.studio.model.form.EventDependencyType
     * @generated
     */
	EEnum getEventDependencyType();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.form.LabelPosition <em>Label Position</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Label Position</em>'.
     * @see org.bonitasoft.studio.model.form.LabelPosition
     * @generated
     */
	EEnum getLabelPosition();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.form.FileWidgetInputType <em>File Widget Input Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>File Widget Input Type</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidgetInputType
     * @generated
     */
	EEnum getFileWidgetInputType();

	/**
     * Returns the meta object for enum '{@link org.bonitasoft.studio.model.form.FileWidgetDownloadType <em>File Widget Download Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>File Widget Download Type</em>'.
     * @see org.bonitasoft.studio.model.form.FileWidgetDownloadType
     * @generated
     */
	EEnum getFileWidgetDownloadType();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	FormFactory getFormFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.WidgetDependencyImpl <em>Widget Dependency</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.WidgetDependencyImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getWidgetDependency()
         * @generated
         */
		EClass WIDGET_DEPENDENCY = eINSTANCE.getWidgetDependency();

		/**
         * The meta object literal for the '<em><b>Trigger Refresh On Modification</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET_DEPENDENCY__TRIGGER_REFRESH_ON_MODIFICATION = eINSTANCE.getWidgetDependency_TriggerRefreshOnModification();

		/**
         * The meta object literal for the '<em><b>Event Types</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET_DEPENDENCY__EVENT_TYPES = eINSTANCE.getWidgetDependency_EventTypes();

		/**
         * The meta object literal for the '<em><b>Widget</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET_DEPENDENCY__WIDGET = eINSTANCE.getWidgetDependency_Widget();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.ValidatorImpl <em>Validator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.ValidatorImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getValidator()
         * @generated
         */
		EClass VALIDATOR = eINSTANCE.getValidator();

		/**
         * The meta object literal for the '<em><b>Validator Class</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALIDATOR__VALIDATOR_CLASS = eINSTANCE.getValidator_ValidatorClass();

		/**
         * The meta object literal for the '<em><b>Html Class</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALIDATOR__HTML_CLASS = eINSTANCE.getValidator_HtmlClass();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALIDATOR__NAME = eINSTANCE.getValidator_Name();

		/**
         * The meta object literal for the '<em><b>Below Field</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALIDATOR__BELOW_FIELD = eINSTANCE.getValidator_BelowField();

		/**
         * The meta object literal for the '<em><b>Parameter</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALIDATOR__PARAMETER = eINSTANCE.getValidator_Parameter();

		/**
         * The meta object literal for the '<em><b>Display Name</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALIDATOR__DISPLAY_NAME = eINSTANCE.getValidator_DisplayName();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.ValidableImpl <em>Validable</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.ValidableImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getValidable()
         * @generated
         */
		EClass VALIDABLE = eINSTANCE.getValidable();

		/**
         * The meta object literal for the '<em><b>Validators</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALIDABLE__VALIDATORS = eINSTANCE.getValidable_Validators();

		/**
         * The meta object literal for the '<em><b>Use Default Validator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALIDABLE__USE_DEFAULT_VALIDATOR = eINSTANCE.getValidable_UseDefaultValidator();

		/**
         * The meta object literal for the '<em><b>Below</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALIDABLE__BELOW = eINSTANCE.getValidable_Below();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.FormImpl <em>Form</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.FormImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getForm()
         * @generated
         */
		EClass FORM = eINSTANCE.getForm();

		/**
         * The meta object literal for the '<em><b>NColumn</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FORM__NCOLUMN = eINSTANCE.getForm_NColumn();

		/**
         * The meta object literal for the '<em><b>String Attributes</b></em>' map feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FORM__STRING_ATTRIBUTES = eINSTANCE.getForm_StringAttributes();

		/**
         * The meta object literal for the '<em><b>NLine</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FORM__NLINE = eINSTANCE.getForm_NLine();

		/**
         * The meta object literal for the '<em><b>Show Page Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FORM__SHOW_PAGE_LABEL = eINSTANCE.getForm_ShowPageLabel();

		/**
         * The meta object literal for the '<em><b>Allow HTML In Page Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FORM__ALLOW_HTML_IN_PAGE_LABEL = eINSTANCE.getForm_AllowHTMLInPageLabel();

		/**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FORM__VERSION = eINSTANCE.getForm_Version();

		/**
         * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FORM__COLUMNS = eINSTANCE.getForm_Columns();

		/**
         * The meta object literal for the '<em><b>Lines</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FORM__LINES = eINSTANCE.getForm_Lines();

		/**
         * The meta object literal for the '<em><b>Widgets</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FORM__WIDGETS = eINSTANCE.getForm_Widgets();

		/**
         * The meta object literal for the '<em><b>Page Label</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FORM__PAGE_LABEL = eINSTANCE.getForm_PageLabel();

		/**
         * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FORM__ACTIONS = eINSTANCE.getForm_Actions();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.WidgetLayoutInfoImpl <em>Widget Layout Info</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.WidgetLayoutInfoImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getWidgetLayoutInfo()
         * @generated
         */
		EClass WIDGET_LAYOUT_INFO = eINSTANCE.getWidgetLayoutInfo();

		/**
         * The meta object literal for the '<em><b>Line</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET_LAYOUT_INFO__LINE = eINSTANCE.getWidgetLayoutInfo_Line();

		/**
         * The meta object literal for the '<em><b>Column</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET_LAYOUT_INFO__COLUMN = eINSTANCE.getWidgetLayoutInfo_Column();

		/**
         * The meta object literal for the '<em><b>Vertical Span</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET_LAYOUT_INFO__VERTICAL_SPAN = eINSTANCE.getWidgetLayoutInfo_VerticalSpan();

		/**
         * The meta object literal for the '<em><b>Horizontal Span</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET_LAYOUT_INFO__HORIZONTAL_SPAN = eINSTANCE.getWidgetLayoutInfo_HorizontalSpan();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.ColumnImpl <em>Column</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.ColumnImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getColumn()
         * @generated
         */
		EClass COLUMN = eINSTANCE.getColumn();

		/**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute COLUMN__WIDTH = eINSTANCE.getColumn_Width();

		/**
         * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute COLUMN__NUMBER = eINSTANCE.getColumn_Number();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.LineImpl <em>Line</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.LineImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getLine()
         * @generated
         */
		EClass LINE = eINSTANCE.getLine();

		/**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute LINE__HEIGHT = eINSTANCE.getLine_Height();

		/**
         * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute LINE__NUMBER = eINSTANCE.getLine_Number();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.ItemContainer <em>Item Container</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.ItemContainer
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getItemContainer()
         * @generated
         */
		EClass ITEM_CONTAINER = eINSTANCE.getItemContainer();

		/**
         * The meta object literal for the '<em><b>Item Class</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ITEM_CONTAINER__ITEM_CLASS = eINSTANCE.getItemContainer_ItemClass();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.Duplicable <em>Duplicable</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.Duplicable
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getDuplicable()
         * @generated
         */
		EClass DUPLICABLE = eINSTANCE.getDuplicable();

		/**
         * The meta object literal for the '<em><b>Duplicate</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DUPLICABLE__DUPLICATE = eINSTANCE.getDuplicable_Duplicate();

		/**
         * The meta object literal for the '<em><b>Limit Number Of Duplication</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DUPLICABLE__LIMIT_NUMBER_OF_DUPLICATION = eINSTANCE.getDuplicable_LimitNumberOfDuplication();

		/**
         * The meta object literal for the '<em><b>Limit Min Number Of Duplication</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DUPLICABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION = eINSTANCE.getDuplicable_LimitMinNumberOfDuplication();

		/**
         * The meta object literal for the '<em><b>Max Number Of Duplication</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DUPLICABLE__MAX_NUMBER_OF_DUPLICATION = eINSTANCE.getDuplicable_MaxNumberOfDuplication();

		/**
         * The meta object literal for the '<em><b>Min Number Of Duplication</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DUPLICABLE__MIN_NUMBER_OF_DUPLICATION = eINSTANCE.getDuplicable_MinNumberOfDuplication();

		/**
         * The meta object literal for the '<em><b>Display Label For Add</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DUPLICABLE__DISPLAY_LABEL_FOR_ADD = eINSTANCE.getDuplicable_DisplayLabelForAdd();

		/**
         * The meta object literal for the '<em><b>Tooltip For Add</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DUPLICABLE__TOOLTIP_FOR_ADD = eINSTANCE.getDuplicable_TooltipForAdd();

		/**
         * The meta object literal for the '<em><b>Display Label For Remove</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DUPLICABLE__DISPLAY_LABEL_FOR_REMOVE = eINSTANCE.getDuplicable_DisplayLabelForRemove();

		/**
         * The meta object literal for the '<em><b>Tooltip For Remove</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DUPLICABLE__TOOLTIP_FOR_REMOVE = eINSTANCE.getDuplicable_TooltipForRemove();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.ViewFormImpl <em>View Form</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.ViewFormImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getViewForm()
         * @generated
         */
		EClass VIEW_FORM = eINSTANCE.getViewForm();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.CSSCustomizableImpl <em>CSS Customizable</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.CSSCustomizableImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getCSSCustomizable()
         * @generated
         */
		EClass CSS_CUSTOMIZABLE = eINSTANCE.getCSSCustomizable();

		/**
         * The meta object literal for the '<em><b>Html Attributes</b></em>' map feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CSS_CUSTOMIZABLE__HTML_ATTRIBUTES = eINSTANCE.getCSSCustomizable_HtmlAttributes();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.Widget <em>Widget</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.Widget
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getWidget()
         * @generated
         */
		EClass WIDGET = eINSTANCE.getWidget();

		/**
         * The meta object literal for the '<em><b>Widget Layout Info</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET__WIDGET_LAYOUT_INFO = eINSTANCE.getWidget_WidgetLayoutInfo();

		/**
         * The meta object literal for the '<em><b>Show Display Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET__SHOW_DISPLAY_LABEL = eINSTANCE.getWidget_ShowDisplayLabel();

		/**
         * The meta object literal for the '<em><b>Allow HTML For Display Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL = eINSTANCE.getWidget_AllowHTMLForDisplayLabel();

		/**
         * The meta object literal for the '<em><b>Depend On</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET__DEPEND_ON = eINSTANCE.getWidget_DependOn();

		/**
         * The meta object literal for the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED = eINSTANCE.getWidget_DisplayDependentWidgetOnlyOnEventTriggered();

		/**
         * The meta object literal for the '<em><b>Parent Of</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET__PARENT_OF = eINSTANCE.getWidget_ParentOf();

		/**
         * The meta object literal for the '<em><b>Mandatory</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET__MANDATORY = eINSTANCE.getWidget_Mandatory();

		/**
         * The meta object literal for the '<em><b>Read Only</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET__READ_ONLY = eINSTANCE.getWidget_ReadOnly();

		/**
         * The meta object literal for the '<em><b>Label Position</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET__LABEL_POSITION = eINSTANCE.getWidget_LabelPosition();

		/**
         * The meta object literal for the '<em><b>Real Html Attributes</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET__REAL_HTML_ATTRIBUTES = eINSTANCE.getWidget_RealHtmlAttributes();

		/**
         * The meta object literal for the '<em><b>Inject Widget Condition</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET__INJECT_WIDGET_CONDITION = eINSTANCE.getWidget_InjectWidgetCondition();

		/**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET__VERSION = eINSTANCE.getWidget_Version();

		/**
         * The meta object literal for the '<em><b>Return Type Modifier</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute WIDGET__RETURN_TYPE_MODIFIER = eINSTANCE.getWidget_ReturnTypeModifier();

		/**
         * The meta object literal for the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION = eINSTANCE.getWidget_DisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition();

		/**
         * The meta object literal for the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT = eINSTANCE.getWidget_DisplayAfterEventDependsOnConditionScript();

		/**
         * The meta object literal for the '<em><b>Input Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET__INPUT_EXPRESSION = eINSTANCE.getWidget_InputExpression();

		/**
         * The meta object literal for the '<em><b>After Event Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET__AFTER_EVENT_EXPRESSION = eINSTANCE.getWidget_AfterEventExpression();

		/**
         * The meta object literal for the '<em><b>Tooltip</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET__TOOLTIP = eINSTANCE.getWidget_Tooltip();

		/**
         * The meta object literal for the '<em><b>Help Message</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET__HELP_MESSAGE = eINSTANCE.getWidget_HelpMessage();

		/**
         * The meta object literal for the '<em><b>Display Label</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET__DISPLAY_LABEL = eINSTANCE.getWidget_DisplayLabel();

		/**
         * The meta object literal for the '<em><b>Inject Widget Script</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET__INJECT_WIDGET_SCRIPT = eINSTANCE.getWidget_InjectWidgetScript();

		/**
         * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WIDGET__ACTION = eINSTANCE.getWidget_Action();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.GroupImpl <em>Group</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.GroupImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getGroup()
         * @generated
         */
		EClass GROUP = eINSTANCE.getGroup();

		/**
         * The meta object literal for the '<em><b>Widgets</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUP__WIDGETS = eINSTANCE.getGroup_Widgets();

		/**
         * The meta object literal for the '<em><b>Show Border</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GROUP__SHOW_BORDER = eINSTANCE.getGroup_ShowBorder();

		/**
         * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUP__COLUMNS = eINSTANCE.getGroup_Columns();

		/**
         * The meta object literal for the '<em><b>Lines</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUP__LINES = eINSTANCE.getGroup_Lines();

		/**
         * The meta object literal for the '<em><b>Use Iterator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GROUP__USE_ITERATOR = eINSTANCE.getGroup_UseIterator();

		/**
         * The meta object literal for the '<em><b>Iterator</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUP__ITERATOR = eINSTANCE.getGroup_Iterator();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.FormFieldImpl <em>Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.FormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getFormField()
         * @generated
         */
		EClass FORM_FIELD = eINSTANCE.getFormField();

		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FORM_FIELD__DESCRIPTION = eINSTANCE.getFormField_Description();

		/**
         * The meta object literal for the '<em><b>Example Message Position</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FORM_FIELD__EXAMPLE_MESSAGE_POSITION = eINSTANCE.getFormField_ExampleMessagePosition();

		/**
         * The meta object literal for the '<em><b>Example Message</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FORM_FIELD__EXAMPLE_MESSAGE = eINSTANCE.getFormField_ExampleMessage();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.MultipleValuatedFormFieldImpl <em>Multiple Valuated Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.MultipleValuatedFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getMultipleValuatedFormField()
         * @generated
         */
		EClass MULTIPLE_VALUATED_FORM_FIELD = eINSTANCE.getMultipleValuatedFormField();

		/**
         * The meta object literal for the '<em><b>Default Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION = eINSTANCE.getMultipleValuatedFormField_DefaultExpression();

		/**
         * The meta object literal for the '<em><b>Default Expression After Event</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MULTIPLE_VALUATED_FORM_FIELD__DEFAULT_EXPRESSION_AFTER_EVENT = eINSTANCE.getMultipleValuatedFormField_DefaultExpressionAfterEvent();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.SingleValuatedFormFieldImpl <em>Single Valuated Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.SingleValuatedFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getSingleValuatedFormField()
         * @generated
         */
		EClass SINGLE_VALUATED_FORM_FIELD = eINSTANCE.getSingleValuatedFormField();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.CheckBoxMultipleFormFieldImpl <em>Check Box Multiple Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.CheckBoxMultipleFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getCheckBoxMultipleFormField()
         * @generated
         */
		EClass CHECK_BOX_MULTIPLE_FORM_FIELD = eINSTANCE.getCheckBoxMultipleFormField();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.ComboFormFieldImpl <em>Combo Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.ComboFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getComboFormField()
         * @generated
         */
		EClass COMBO_FORM_FIELD = eINSTANCE.getComboFormField();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.DateFormFieldImpl <em>Date Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.DateFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getDateFormField()
         * @generated
         */
		EClass DATE_FORM_FIELD = eINSTANCE.getDateFormField();

		/**
         * The meta object literal for the '<em><b>Initial Format</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DATE_FORM_FIELD__INITIAL_FORMAT = eINSTANCE.getDateFormField_InitialFormat();

		/**
         * The meta object literal for the '<em><b>Display Format</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DATE_FORM_FIELD__DISPLAY_FORMAT = eINSTANCE.getDateFormField_DisplayFormat();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.ListFormFieldImpl <em>List Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.ListFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getListFormField()
         * @generated
         */
		EClass LIST_FORM_FIELD = eINSTANCE.getListFormField();

		/**
         * The meta object literal for the '<em><b>Max Heigth</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute LIST_FORM_FIELD__MAX_HEIGTH = eINSTANCE.getListFormField_MaxHeigth();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.PasswordFormFieldImpl <em>Password Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.PasswordFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getPasswordFormField()
         * @generated
         */
		EClass PASSWORD_FORM_FIELD = eINSTANCE.getPasswordFormField();

		/**
         * The meta object literal for the '<em><b>Max Length</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PASSWORD_FORM_FIELD__MAX_LENGTH = eINSTANCE.getPasswordFormField_MaxLength();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.RadioFormFieldImpl <em>Radio Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.RadioFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getRadioFormField()
         * @generated
         */
		EClass RADIO_FORM_FIELD = eINSTANCE.getRadioFormField();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.SelectFormFieldImpl <em>Select Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.SelectFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getSelectFormField()
         * @generated
         */
		EClass SELECT_FORM_FIELD = eINSTANCE.getSelectFormField();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.TextFormFieldImpl <em>Text Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.TextFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getTextFormField()
         * @generated
         */
		EClass TEXT_FORM_FIELD = eINSTANCE.getTextFormField();

		/**
         * The meta object literal for the '<em><b>Max Length</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TEXT_FORM_FIELD__MAX_LENGTH = eINSTANCE.getTextFormField_MaxLength();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.TextAreaFormFieldImpl <em>Text Area Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.TextAreaFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getTextAreaFormField()
         * @generated
         */
		EClass TEXT_AREA_FORM_FIELD = eINSTANCE.getTextAreaFormField();

		/**
         * The meta object literal for the '<em><b>Max Length</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TEXT_AREA_FORM_FIELD__MAX_LENGTH = eINSTANCE.getTextAreaFormField_MaxLength();

		/**
         * The meta object literal for the '<em><b>Max Heigth</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TEXT_AREA_FORM_FIELD__MAX_HEIGTH = eINSTANCE.getTextAreaFormField_MaxHeigth();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.RichTextAreaFormFieldImpl <em>Rich Text Area Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.RichTextAreaFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getRichTextAreaFormField()
         * @generated
         */
		EClass RICH_TEXT_AREA_FORM_FIELD = eINSTANCE.getRichTextAreaFormField();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.FormButtonImpl <em>Button</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.FormButtonImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getFormButton()
         * @generated
         */
		EClass FORM_BUTTON = eINSTANCE.getFormButton();

		/**
         * The meta object literal for the '<em><b>Label Behavior</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FORM_BUTTON__LABEL_BEHAVIOR = eINSTANCE.getFormButton_LabelBehavior();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.SubmitFormButtonImpl <em>Submit Form Button</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.SubmitFormButtonImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getSubmitFormButton()
         * @generated
         */
		EClass SUBMIT_FORM_BUTTON = eINSTANCE.getSubmitFormButton();

		/**
         * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SUBMIT_FORM_BUTTON__ACTIONS = eINSTANCE.getSubmitFormButton_Actions();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.PreviousFormButtonImpl <em>Previous Form Button</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.PreviousFormButtonImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getPreviousFormButton()
         * @generated
         */
		EClass PREVIOUS_FORM_BUTTON = eINSTANCE.getPreviousFormButton();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.NextFormButtonImpl <em>Next Form Button</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.NextFormButtonImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getNextFormButton()
         * @generated
         */
		EClass NEXT_FORM_BUTTON = eINSTANCE.getNextFormButton();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.InfoImpl <em>Info</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.InfoImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getInfo()
         * @generated
         */
		EClass INFO = eINSTANCE.getInfo();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.TextInfoImpl <em>Text Info</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.TextInfoImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getTextInfo()
         * @generated
         */
		EClass TEXT_INFO = eINSTANCE.getTextInfo();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.MessageInfoImpl <em>Message Info</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.MessageInfoImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getMessageInfo()
         * @generated
         */
		EClass MESSAGE_INFO = eINSTANCE.getMessageInfo();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.CheckBoxSingleFormFieldImpl <em>Check Box Single Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.CheckBoxSingleFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getCheckBoxSingleFormField()
         * @generated
         */
		EClass CHECK_BOX_SINGLE_FORM_FIELD = eINSTANCE.getCheckBoxSingleFormField();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.FileWidgetImpl <em>File Widget</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.FileWidgetImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getFileWidget()
         * @generated
         */
		EClass FILE_WIDGET = eINSTANCE.getFileWidget();

		/**
         * The meta object literal for the '<em><b>Download Only</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FILE_WIDGET__DOWNLOAD_ONLY = eINSTANCE.getFileWidget_DownloadOnly();

		/**
         * The meta object literal for the '<em><b>Use Preview</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FILE_WIDGET__USE_PREVIEW = eINSTANCE.getFileWidget_UsePreview();

		/**
         * The meta object literal for the '<em><b>Document</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FILE_WIDGET__DOCUMENT = eINSTANCE.getFileWidget_Document();

		/**
         * The meta object literal for the '<em><b>Initial Resource Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FILE_WIDGET__INITIAL_RESOURCE_PATH = eINSTANCE.getFileWidget_InitialResourcePath();

		/**
         * The meta object literal for the '<em><b>Output Document Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FILE_WIDGET__OUTPUT_DOCUMENT_NAME = eINSTANCE.getFileWidget_OutputDocumentName();

		/**
         * The meta object literal for the '<em><b>Update Document</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FILE_WIDGET__UPDATE_DOCUMENT = eINSTANCE.getFileWidget_UpdateDocument();

		/**
         * The meta object literal for the '<em><b>Intial Resource List</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FILE_WIDGET__INTIAL_RESOURCE_LIST = eINSTANCE.getFileWidget_IntialResourceList();

		/**
         * The meta object literal for the '<em><b>Input Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FILE_WIDGET__INPUT_TYPE = eINSTANCE.getFileWidget_InputType();

		/**
         * The meta object literal for the '<em><b>Output Document List Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FILE_WIDGET__OUTPUT_DOCUMENT_LIST_EXPRESSION = eINSTANCE.getFileWidget_OutputDocumentListExpression();

		/**
         * The meta object literal for the '<em><b>Download Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FILE_WIDGET__DOWNLOAD_TYPE = eINSTANCE.getFileWidget_DownloadType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.ImageWidgetImpl <em>Image Widget</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.ImageWidgetImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getImageWidget()
         * @generated
         */
		EClass IMAGE_WIDGET = eINSTANCE.getImageWidget();

		/**
         * The meta object literal for the '<em><b>Is ADocument</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute IMAGE_WIDGET__IS_ADOCUMENT = eINSTANCE.getImageWidget_IsADocument();

		/**
         * The meta object literal for the '<em><b>Document</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference IMAGE_WIDGET__DOCUMENT = eINSTANCE.getImageWidget_Document();

		/**
         * The meta object literal for the '<em><b>Img Path</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference IMAGE_WIDGET__IMG_PATH = eINSTANCE.getImageWidget_ImgPath();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.HiddenWidgetImpl <em>Hidden Widget</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.HiddenWidgetImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getHiddenWidget()
         * @generated
         */
		EClass HIDDEN_WIDGET = eINSTANCE.getHiddenWidget();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.DurationFormFieldImpl <em>Duration Form Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.DurationFormFieldImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getDurationFormField()
         * @generated
         */
		EClass DURATION_FORM_FIELD = eINSTANCE.getDurationFormField();

		/**
         * The meta object literal for the '<em><b>Day</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DURATION_FORM_FIELD__DAY = eINSTANCE.getDurationFormField_Day();

		/**
         * The meta object literal for the '<em><b>Hour</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DURATION_FORM_FIELD__HOUR = eINSTANCE.getDurationFormField_Hour();

		/**
         * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DURATION_FORM_FIELD__MIN = eINSTANCE.getDurationFormField_Min();

		/**
         * The meta object literal for the '<em><b>Sec</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DURATION_FORM_FIELD__SEC = eINSTANCE.getDurationFormField_Sec();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.AbstractTableImpl <em>Abstract Table</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.AbstractTableImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getAbstractTable()
         * @generated
         */
		EClass ABSTRACT_TABLE = eINSTANCE.getAbstractTable();

		/**
         * The meta object literal for the '<em><b>Left Column Is Header</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_TABLE__LEFT_COLUMN_IS_HEADER = eINSTANCE.getAbstractTable_LeftColumnIsHeader();

		/**
         * The meta object literal for the '<em><b>Right Column Is Header</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_TABLE__RIGHT_COLUMN_IS_HEADER = eINSTANCE.getAbstractTable_RightColumnIsHeader();

		/**
         * The meta object literal for the '<em><b>First Row Is Header</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_TABLE__FIRST_ROW_IS_HEADER = eINSTANCE.getAbstractTable_FirstRowIsHeader();

		/**
         * The meta object literal for the '<em><b>Last Row Is Header</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_TABLE__LAST_ROW_IS_HEADER = eINSTANCE.getAbstractTable_LastRowIsHeader();

		/**
         * The meta object literal for the '<em><b>Initialized Using Cells</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_TABLE__INITIALIZED_USING_CELLS = eINSTANCE.getAbstractTable_InitializedUsingCells();

		/**
         * The meta object literal for the '<em><b>Use Horizontal Header</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_TABLE__USE_HORIZONTAL_HEADER = eINSTANCE.getAbstractTable_UseHorizontalHeader();

		/**
         * The meta object literal for the '<em><b>Use Vertical Header</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_TABLE__USE_VERTICAL_HEADER = eINSTANCE.getAbstractTable_UseVerticalHeader();

		/**
         * The meta object literal for the '<em><b>Horizontal Header Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_TABLE__HORIZONTAL_HEADER_EXPRESSION = eINSTANCE.getAbstractTable_HorizontalHeaderExpression();

		/**
         * The meta object literal for the '<em><b>Vertical Header Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_TABLE__VERTICAL_HEADER_EXPRESSION = eINSTANCE.getAbstractTable_VerticalHeaderExpression();

		/**
         * The meta object literal for the '<em><b>Table Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_TABLE__TABLE_EXPRESSION = eINSTANCE.getAbstractTable_TableExpression();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.TableImpl <em>Table</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.TableImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getTable()
         * @generated
         */
		EClass TABLE = eINSTANCE.getTable();

		/**
         * The meta object literal for the '<em><b>Use Pagination</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TABLE__USE_PAGINATION = eINSTANCE.getTable_UsePagination();

		/**
         * The meta object literal for the '<em><b>Allow Selection</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TABLE__ALLOW_SELECTION = eINSTANCE.getTable_AllowSelection();

		/**
         * The meta object literal for the '<em><b>Selection Mode Is Multiple</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TABLE__SELECTION_MODE_IS_MULTIPLE = eINSTANCE.getTable_SelectionModeIsMultiple();

		/**
         * The meta object literal for the '<em><b>Max Row For Pagination</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE__MAX_ROW_FOR_PAGINATION = eINSTANCE.getTable_MaxRowForPagination();

		/**
         * The meta object literal for the '<em><b>Column For Initial Selection Index</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE__COLUMN_FOR_INITIAL_SELECTION_INDEX = eINSTANCE.getTable_ColumnForInitialSelectionIndex();

		/**
         * The meta object literal for the '<em><b>Selected Values</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE__SELECTED_VALUES = eINSTANCE.getTable_SelectedValues();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.DynamicTableImpl <em>Dynamic Table</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.DynamicTableImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getDynamicTable()
         * @generated
         */
		EClass DYNAMIC_TABLE = eINSTANCE.getDynamicTable();

		/**
         * The meta object literal for the '<em><b>Limit Min Number Of Column</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_COLUMN = eINSTANCE.getDynamicTable_LimitMinNumberOfColumn();

		/**
         * The meta object literal for the '<em><b>Limit Min Number Of Row</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DYNAMIC_TABLE__LIMIT_MIN_NUMBER_OF_ROW = eINSTANCE.getDynamicTable_LimitMinNumberOfRow();

		/**
         * The meta object literal for the '<em><b>Allow Add Remove Row</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DYNAMIC_TABLE__ALLOW_ADD_REMOVE_ROW = eINSTANCE.getDynamicTable_AllowAddRemoveRow();

		/**
         * The meta object literal for the '<em><b>Allow Add Remove Column</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DYNAMIC_TABLE__ALLOW_ADD_REMOVE_COLUMN = eINSTANCE.getDynamicTable_AllowAddRemoveColumn();

		/**
         * The meta object literal for the '<em><b>Limit Max Number Of Column</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_COLUMN = eINSTANCE.getDynamicTable_LimitMaxNumberOfColumn();

		/**
         * The meta object literal for the '<em><b>Limit Max Number Of Row</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DYNAMIC_TABLE__LIMIT_MAX_NUMBER_OF_ROW = eINSTANCE.getDynamicTable_LimitMaxNumberOfRow();

		/**
         * The meta object literal for the '<em><b>Min Number Of Column</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DYNAMIC_TABLE__MIN_NUMBER_OF_COLUMN = eINSTANCE.getDynamicTable_MinNumberOfColumn();

		/**
         * The meta object literal for the '<em><b>Min Number Of Row</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DYNAMIC_TABLE__MIN_NUMBER_OF_ROW = eINSTANCE.getDynamicTable_MinNumberOfRow();

		/**
         * The meta object literal for the '<em><b>Max Number Of Column</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DYNAMIC_TABLE__MAX_NUMBER_OF_COLUMN = eINSTANCE.getDynamicTable_MaxNumberOfColumn();

		/**
         * The meta object literal for the '<em><b>Max Number Of Row</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DYNAMIC_TABLE__MAX_NUMBER_OF_ROW = eINSTANCE.getDynamicTable_MaxNumberOfRow();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.IFrameWidgetImpl <em>IFrame Widget</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.IFrameWidgetImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getIFrameWidget()
         * @generated
         */
		EClass IFRAME_WIDGET = eINSTANCE.getIFrameWidget();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.MandatoryFieldsCustomizationImpl <em>Mandatory Fields Customization</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.MandatoryFieldsCustomizationImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getMandatoryFieldsCustomization()
         * @generated
         */
		EClass MANDATORY_FIELDS_CUSTOMIZATION = eINSTANCE.getMandatoryFieldsCustomization();

		/**
         * The meta object literal for the '<em><b>Mandatory Symbol</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL = eINSTANCE.getMandatoryFieldsCustomization_MandatorySymbol();

		/**
         * The meta object literal for the '<em><b>Mandatory Label</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL = eINSTANCE.getMandatoryFieldsCustomization_MandatoryLabel();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.HtmlWidgetImpl <em>Html Widget</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.HtmlWidgetImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getHtmlWidget()
         * @generated
         */
		EClass HTML_WIDGET = eINSTANCE.getHtmlWidget();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.SuggestBoxImpl <em>Suggest Box</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.SuggestBoxImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getSuggestBox()
         * @generated
         */
		EClass SUGGEST_BOX = eINSTANCE.getSuggestBox();

		/**
         * The meta object literal for the '<em><b>Max Items</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SUGGEST_BOX__MAX_ITEMS = eINSTANCE.getSuggestBox_MaxItems();

		/**
         * The meta object literal for the '<em><b>Use Max Items</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SUGGEST_BOX__USE_MAX_ITEMS = eINSTANCE.getSuggestBox_UseMaxItems();

		/**
         * The meta object literal for the '<em><b>Asynchronous</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SUGGEST_BOX__ASYNCHRONOUS = eINSTANCE.getSuggestBox_Asynchronous();

		/**
         * The meta object literal for the '<em><b>Delay</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SUGGEST_BOX__DELAY = eINSTANCE.getSuggestBox_Delay();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.impl.GroupIteratorImpl <em>Group Iterator</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.impl.GroupIteratorImpl
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getGroupIterator()
         * @generated
         */
		EClass GROUP_ITERATOR = eINSTANCE.getGroupIterator();

		/**
         * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute GROUP_ITERATOR__CLASS_NAME = eINSTANCE.getGroupIterator_ClassName();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.EventDependencyType <em>Event Dependency Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.EventDependencyType
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getEventDependencyType()
         * @generated
         */
		EEnum EVENT_DEPENDENCY_TYPE = eINSTANCE.getEventDependencyType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.LabelPosition <em>Label Position</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.LabelPosition
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getLabelPosition()
         * @generated
         */
		EEnum LABEL_POSITION = eINSTANCE.getLabelPosition();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.FileWidgetInputType <em>File Widget Input Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.FileWidgetInputType
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getFileWidgetInputType()
         * @generated
         */
		EEnum FILE_WIDGET_INPUT_TYPE = eINSTANCE.getFileWidgetInputType();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.form.FileWidgetDownloadType <em>File Widget Download Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.form.FileWidgetDownloadType
         * @see org.bonitasoft.studio.model.form.impl.FormPackageImpl#getFileWidgetDownloadType()
         * @generated
         */
		EEnum FILE_WIDGET_DOWNLOAD_TYPE = eINSTANCE.getFileWidgetDownloadType();

	}

} //FormPackage
