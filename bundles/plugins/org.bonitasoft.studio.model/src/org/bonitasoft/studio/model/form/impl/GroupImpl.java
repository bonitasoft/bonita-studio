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

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;

import org.bonitasoft.studio.model.form.CSSCustomizable;
import org.bonitasoft.studio.model.form.Column;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.GroupIterator;
import org.bonitasoft.studio.model.form.LabelPosition;
import org.bonitasoft.studio.model.form.Line;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetDependency;
import org.bonitasoft.studio.model.form.WidgetLayoutInfo;

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getTextAnnotationAttachment <em>Text Annotation Attachment</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getHtmlAttributes <em>Html Attributes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getWidgetLayoutInfo <em>Widget Layout Info</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getShowDisplayLabel <em>Show Display Label</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#isAllowHTMLForDisplayLabel <em>Allow HTML For Display Label</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getDependOn <em>Depend On</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#isDisplayDependentWidgetOnlyOnEventTriggered <em>Display Dependent Widget Only On Event Triggered</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getParentOf <em>Parent Of</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#isReadOnly <em>Read Only</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getLabelPosition <em>Label Position</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getRealHtmlAttributes <em>Real Html Attributes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#isInjectWidgetCondition <em>Inject Widget Condition</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getReturnTypeModifier <em>Return Type Modifier</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition <em>Display Dependent Widget Only After First Event Triggered And Condition</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getDisplayAfterEventDependsOnConditionScript <em>Display After Event Depends On Condition Script</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getInputExpression <em>Input Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getAfterEventExpression <em>After Event Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getHelpMessage <em>Help Message</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getDisplayLabel <em>Display Label</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getInjectWidgetScript <em>Inject Widget Script</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getAction <em>Action</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#isDuplicate <em>Duplicate</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#isLimitNumberOfDuplication <em>Limit Number Of Duplication</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#isLimitMinNumberOfDuplication <em>Limit Min Number Of Duplication</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getMaxNumberOfDuplication <em>Max Number Of Duplication</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getMinNumberOfDuplication <em>Min Number Of Duplication</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getDisplayLabelForAdd <em>Display Label For Add</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getTooltipForAdd <em>Tooltip For Add</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getDisplayLabelForRemove <em>Display Label For Remove</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getTooltipForRemove <em>Tooltip For Remove</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getWidgets <em>Widgets</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#isShowBorder <em>Show Border</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getColumns <em>Columns</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getLines <em>Lines</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#isUseIterator <em>Use Iterator</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.impl.GroupImpl#getIterator <em>Iterator</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GroupImpl extends EObjectImpl implements Group {
	/**
     * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
	protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
	protected String documentation = DOCUMENTATION_EDEFAULT;

	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected String name = NAME_EDEFAULT;

	/**
     * The cached value of the '{@link #getTextAnnotationAttachment() <em>Text Annotation Attachment</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTextAnnotationAttachment()
     * @generated
     * @ordered
     */
	protected EList<TextAnnotationAttachment> textAnnotationAttachment;

	/**
     * The cached value of the '{@link #getHtmlAttributes() <em>Html Attributes</em>}' map.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHtmlAttributes()
     * @generated
     * @ordered
     */
	protected EMap<String, String> htmlAttributes;

	/**
     * The cached value of the '{@link #getWidgetLayoutInfo() <em>Widget Layout Info</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getWidgetLayoutInfo()
     * @generated
     * @ordered
     */
	protected WidgetLayoutInfo widgetLayoutInfo;

	/**
     * The default value of the '{@link #getShowDisplayLabel() <em>Show Display Label</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getShowDisplayLabel()
     * @generated
     * @ordered
     */
	protected static final Boolean SHOW_DISPLAY_LABEL_EDEFAULT = Boolean.TRUE;

	/**
     * The cached value of the '{@link #getShowDisplayLabel() <em>Show Display Label</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getShowDisplayLabel()
     * @generated
     * @ordered
     */
	protected Boolean showDisplayLabel = SHOW_DISPLAY_LABEL_EDEFAULT;

	/**
     * The default value of the '{@link #isAllowHTMLForDisplayLabel() <em>Allow HTML For Display Label</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isAllowHTMLForDisplayLabel()
     * @generated
     * @ordered
     */
	protected static final boolean ALLOW_HTML_FOR_DISPLAY_LABEL_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isAllowHTMLForDisplayLabel() <em>Allow HTML For Display Label</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isAllowHTMLForDisplayLabel()
     * @generated
     * @ordered
     */
	protected boolean allowHTMLForDisplayLabel = ALLOW_HTML_FOR_DISPLAY_LABEL_EDEFAULT;

	/**
     * The cached value of the '{@link #getDependOn() <em>Depend On</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDependOn()
     * @generated
     * @ordered
     */
	protected EList<WidgetDependency> dependOn;

	/**
     * The default value of the '{@link #isDisplayDependentWidgetOnlyOnEventTriggered() <em>Display Dependent Widget Only On Event Triggered</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isDisplayDependentWidgetOnlyOnEventTriggered()
     * @generated
     * @ordered
     */
	protected static final boolean DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isDisplayDependentWidgetOnlyOnEventTriggered() <em>Display Dependent Widget Only On Event Triggered</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isDisplayDependentWidgetOnlyOnEventTriggered()
     * @generated
     * @ordered
     */
	protected boolean displayDependentWidgetOnlyOnEventTriggered = DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED_EDEFAULT;

	/**
     * The cached value of the '{@link #getParentOf() <em>Parent Of</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getParentOf()
     * @generated
     * @ordered
     */
	protected EList<WidgetDependency> parentOf;

	/**
     * The default value of the '{@link #isMandatory() <em>Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isMandatory()
     * @generated
     * @ordered
     */
	protected static final boolean MANDATORY_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isMandatory() <em>Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isMandatory()
     * @generated
     * @ordered
     */
	protected boolean mandatory = MANDATORY_EDEFAULT;

	/**
     * The default value of the '{@link #isReadOnly() <em>Read Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isReadOnly()
     * @generated
     * @ordered
     */
	protected static final boolean READ_ONLY_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isReadOnly() <em>Read Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isReadOnly()
     * @generated
     * @ordered
     */
	protected boolean readOnly = READ_ONLY_EDEFAULT;

	/**
     * The default value of the '{@link #getLabelPosition() <em>Label Position</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLabelPosition()
     * @generated
     * @ordered
     */
	protected static final LabelPosition LABEL_POSITION_EDEFAULT = LabelPosition.UP;

	/**
     * The cached value of the '{@link #getLabelPosition() <em>Label Position</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLabelPosition()
     * @generated
     * @ordered
     */
	protected LabelPosition labelPosition = LABEL_POSITION_EDEFAULT;

	/**
     * The default value of the '{@link #getRealHtmlAttributes() <em>Real Html Attributes</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRealHtmlAttributes()
     * @generated
     * @ordered
     */
	protected static final String REAL_HTML_ATTRIBUTES_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getRealHtmlAttributes() <em>Real Html Attributes</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRealHtmlAttributes()
     * @generated
     * @ordered
     */
	protected String realHtmlAttributes = REAL_HTML_ATTRIBUTES_EDEFAULT;

	/**
     * The default value of the '{@link #isInjectWidgetCondition() <em>Inject Widget Condition</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isInjectWidgetCondition()
     * @generated
     * @ordered
     */
	protected static final boolean INJECT_WIDGET_CONDITION_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isInjectWidgetCondition() <em>Inject Widget Condition</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isInjectWidgetCondition()
     * @generated
     * @ordered
     */
	protected boolean injectWidgetCondition = INJECT_WIDGET_CONDITION_EDEFAULT;

	/**
     * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
	protected static final String VERSION_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
	protected String version = VERSION_EDEFAULT;

	/**
     * The default value of the '{@link #getReturnTypeModifier() <em>Return Type Modifier</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getReturnTypeModifier()
     * @generated
     * @ordered
     */
	protected static final String RETURN_TYPE_MODIFIER_EDEFAULT = "java.lang.String"; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getReturnTypeModifier() <em>Return Type Modifier</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getReturnTypeModifier()
     * @generated
     * @ordered
     */
	protected String returnTypeModifier = RETURN_TYPE_MODIFIER_EDEFAULT;

	/**
     * The cached value of the '{@link #getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition() <em>Display Dependent Widget Only After First Event Triggered And Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition()
     * @generated
     * @ordered
     */
	protected Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition;

	/**
     * The cached value of the '{@link #getDisplayAfterEventDependsOnConditionScript() <em>Display After Event Depends On Condition Script</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayAfterEventDependsOnConditionScript()
     * @generated
     * @ordered
     */
	protected Expression displayAfterEventDependsOnConditionScript;

	/**
     * The cached value of the '{@link #getInputExpression() <em>Input Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInputExpression()
     * @generated
     * @ordered
     */
	protected Expression inputExpression;

	/**
     * The cached value of the '{@link #getAfterEventExpression() <em>After Event Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAfterEventExpression()
     * @generated
     * @ordered
     */
	protected Expression afterEventExpression;

	/**
     * The cached value of the '{@link #getTooltip() <em>Tooltip</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTooltip()
     * @generated
     * @ordered
     */
	protected Expression tooltip;

	/**
     * The cached value of the '{@link #getHelpMessage() <em>Help Message</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHelpMessage()
     * @generated
     * @ordered
     */
	protected Expression helpMessage;

	/**
     * The cached value of the '{@link #getDisplayLabel() <em>Display Label</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayLabel()
     * @generated
     * @ordered
     */
	protected Expression displayLabel;

	/**
     * The cached value of the '{@link #getInjectWidgetScript() <em>Inject Widget Script</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInjectWidgetScript()
     * @generated
     * @ordered
     */
	protected Expression injectWidgetScript;

	/**
     * The cached value of the '{@link #getAction() <em>Action</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAction()
     * @generated
     * @ordered
     */
	protected Operation action;

	/**
     * The default value of the '{@link #isDuplicate() <em>Duplicate</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isDuplicate()
     * @generated
     * @ordered
     */
	protected static final boolean DUPLICATE_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isDuplicate() <em>Duplicate</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isDuplicate()
     * @generated
     * @ordered
     */
	protected boolean duplicate = DUPLICATE_EDEFAULT;

	/**
     * The default value of the '{@link #isLimitNumberOfDuplication() <em>Limit Number Of Duplication</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isLimitNumberOfDuplication()
     * @generated
     * @ordered
     */
	protected static final boolean LIMIT_NUMBER_OF_DUPLICATION_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isLimitNumberOfDuplication() <em>Limit Number Of Duplication</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isLimitNumberOfDuplication()
     * @generated
     * @ordered
     */
	protected boolean limitNumberOfDuplication = LIMIT_NUMBER_OF_DUPLICATION_EDEFAULT;

	/**
     * The default value of the '{@link #isLimitMinNumberOfDuplication() <em>Limit Min Number Of Duplication</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isLimitMinNumberOfDuplication()
     * @generated
     * @ordered
     */
	protected static final boolean LIMIT_MIN_NUMBER_OF_DUPLICATION_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isLimitMinNumberOfDuplication() <em>Limit Min Number Of Duplication</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isLimitMinNumberOfDuplication()
     * @generated
     * @ordered
     */
	protected boolean limitMinNumberOfDuplication = LIMIT_MIN_NUMBER_OF_DUPLICATION_EDEFAULT;

	/**
     * The cached value of the '{@link #getMaxNumberOfDuplication() <em>Max Number Of Duplication</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMaxNumberOfDuplication()
     * @generated
     * @ordered
     */
	protected Expression maxNumberOfDuplication;

	/**
     * The cached value of the '{@link #getMinNumberOfDuplication() <em>Min Number Of Duplication</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMinNumberOfDuplication()
     * @generated
     * @ordered
     */
	protected Expression minNumberOfDuplication;

	/**
     * The cached value of the '{@link #getDisplayLabelForAdd() <em>Display Label For Add</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayLabelForAdd()
     * @generated
     * @ordered
     */
	protected Expression displayLabelForAdd;

	/**
     * The cached value of the '{@link #getTooltipForAdd() <em>Tooltip For Add</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTooltipForAdd()
     * @generated
     * @ordered
     */
	protected Expression tooltipForAdd;

	/**
     * The cached value of the '{@link #getDisplayLabelForRemove() <em>Display Label For Remove</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayLabelForRemove()
     * @generated
     * @ordered
     */
	protected Expression displayLabelForRemove;

	/**
     * The cached value of the '{@link #getTooltipForRemove() <em>Tooltip For Remove</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTooltipForRemove()
     * @generated
     * @ordered
     */
	protected Expression tooltipForRemove;

	/**
     * The cached value of the '{@link #getWidgets() <em>Widgets</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getWidgets()
     * @generated
     * @ordered
     */
	protected EList<Widget> widgets;

	/**
     * The default value of the '{@link #isShowBorder() <em>Show Border</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isShowBorder()
     * @generated
     * @ordered
     */
	protected static final boolean SHOW_BORDER_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isShowBorder() <em>Show Border</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isShowBorder()
     * @generated
     * @ordered
     */
	protected boolean showBorder = SHOW_BORDER_EDEFAULT;

	/**
     * The cached value of the '{@link #getColumns() <em>Columns</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getColumns()
     * @generated
     * @ordered
     */
	protected EList<Column> columns;

	/**
     * The cached value of the '{@link #getLines() <em>Lines</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLines()
     * @generated
     * @ordered
     */
	protected EList<Line> lines;

	/**
     * The default value of the '{@link #isUseIterator() <em>Use Iterator</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUseIterator()
     * @generated
     * @ordered
     */
	protected static final boolean USE_ITERATOR_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isUseIterator() <em>Use Iterator</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isUseIterator()
     * @generated
     * @ordered
     */
	protected boolean useIterator = USE_ITERATOR_EDEFAULT;

	/**
     * The cached value of the '{@link #getIterator() <em>Iterator</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIterator()
     * @generated
     * @ordered
     */
	protected GroupIterator iterator;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected GroupImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return FormPackage.Literals.GROUP;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getDocumentation() {
        return documentation;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DOCUMENTATION, oldDocumentation, documentation));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<TextAnnotationAttachment> getTextAnnotationAttachment() {
        if (textAnnotationAttachment == null) {
            textAnnotationAttachment = new EObjectContainmentWithInverseEList<TextAnnotationAttachment>(TextAnnotationAttachment.class, this, FormPackage.GROUP__TEXT_ANNOTATION_ATTACHMENT, ProcessPackage.TEXT_ANNOTATION_ATTACHMENT__TARGET);
        }
        return textAnnotationAttachment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EMap<String, String> getHtmlAttributes() {
        if (htmlAttributes == null) {
            htmlAttributes = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, FormPackage.GROUP__HTML_ATTRIBUTES);
        }
        return htmlAttributes;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public WidgetLayoutInfo getWidgetLayoutInfo() {
        return widgetLayoutInfo;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetWidgetLayoutInfo(WidgetLayoutInfo newWidgetLayoutInfo, NotificationChain msgs) {
        WidgetLayoutInfo oldWidgetLayoutInfo = widgetLayoutInfo;
        widgetLayoutInfo = newWidgetLayoutInfo;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__WIDGET_LAYOUT_INFO, oldWidgetLayoutInfo, newWidgetLayoutInfo);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setWidgetLayoutInfo(WidgetLayoutInfo newWidgetLayoutInfo) {
        if (newWidgetLayoutInfo != widgetLayoutInfo) {
            NotificationChain msgs = null;
            if (widgetLayoutInfo != null)
                msgs = ((InternalEObject)widgetLayoutInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__WIDGET_LAYOUT_INFO, null, msgs);
            if (newWidgetLayoutInfo != null)
                msgs = ((InternalEObject)newWidgetLayoutInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__WIDGET_LAYOUT_INFO, null, msgs);
            msgs = basicSetWidgetLayoutInfo(newWidgetLayoutInfo, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__WIDGET_LAYOUT_INFO, newWidgetLayoutInfo, newWidgetLayoutInfo));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Boolean getShowDisplayLabel() {
        return showDisplayLabel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setShowDisplayLabel(Boolean newShowDisplayLabel) {
        Boolean oldShowDisplayLabel = showDisplayLabel;
        showDisplayLabel = newShowDisplayLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__SHOW_DISPLAY_LABEL, oldShowDisplayLabel, showDisplayLabel));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isAllowHTMLForDisplayLabel() {
        return allowHTMLForDisplayLabel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setAllowHTMLForDisplayLabel(boolean newAllowHTMLForDisplayLabel) {
        boolean oldAllowHTMLForDisplayLabel = allowHTMLForDisplayLabel;
        allowHTMLForDisplayLabel = newAllowHTMLForDisplayLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__ALLOW_HTML_FOR_DISPLAY_LABEL, oldAllowHTMLForDisplayLabel, allowHTMLForDisplayLabel));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<WidgetDependency> getDependOn() {
        if (dependOn == null) {
            dependOn = new EObjectContainmentEList<WidgetDependency>(WidgetDependency.class, this, FormPackage.GROUP__DEPEND_ON);
        }
        return dependOn;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isDisplayDependentWidgetOnlyOnEventTriggered() {
        return displayDependentWidgetOnlyOnEventTriggered;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDisplayDependentWidgetOnlyOnEventTriggered(boolean newDisplayDependentWidgetOnlyOnEventTriggered) {
        boolean oldDisplayDependentWidgetOnlyOnEventTriggered = displayDependentWidgetOnlyOnEventTriggered;
        displayDependentWidgetOnlyOnEventTriggered = newDisplayDependentWidgetOnlyOnEventTriggered;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED, oldDisplayDependentWidgetOnlyOnEventTriggered, displayDependentWidgetOnlyOnEventTriggered));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<WidgetDependency> getParentOf() {
        if (parentOf == null) {
            parentOf = new EObjectContainmentEList<WidgetDependency>(WidgetDependency.class, this, FormPackage.GROUP__PARENT_OF);
        }
        return parentOf;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isMandatory() {
        return mandatory;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setMandatory(boolean newMandatory) {
        boolean oldMandatory = mandatory;
        mandatory = newMandatory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__MANDATORY, oldMandatory, mandatory));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isReadOnly() {
        return readOnly;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setReadOnly(boolean newReadOnly) {
        boolean oldReadOnly = readOnly;
        readOnly = newReadOnly;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__READ_ONLY, oldReadOnly, readOnly));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public LabelPosition getLabelPosition() {
        return labelPosition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setLabelPosition(LabelPosition newLabelPosition) {
        LabelPosition oldLabelPosition = labelPosition;
        labelPosition = newLabelPosition == null ? LABEL_POSITION_EDEFAULT : newLabelPosition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__LABEL_POSITION, oldLabelPosition, labelPosition));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getRealHtmlAttributes() {
        return realHtmlAttributes;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setRealHtmlAttributes(String newRealHtmlAttributes) {
        String oldRealHtmlAttributes = realHtmlAttributes;
        realHtmlAttributes = newRealHtmlAttributes;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__REAL_HTML_ATTRIBUTES, oldRealHtmlAttributes, realHtmlAttributes));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isInjectWidgetCondition() {
        return injectWidgetCondition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setInjectWidgetCondition(boolean newInjectWidgetCondition) {
        boolean oldInjectWidgetCondition = injectWidgetCondition;
        injectWidgetCondition = newInjectWidgetCondition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__INJECT_WIDGET_CONDITION, oldInjectWidgetCondition, injectWidgetCondition));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getVersion() {
        return version;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__VERSION, oldVersion, version));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getReturnTypeModifier() {
        return returnTypeModifier;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setReturnTypeModifier(String newReturnTypeModifier) {
        String oldReturnTypeModifier = returnTypeModifier;
        returnTypeModifier = newReturnTypeModifier;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__RETURN_TYPE_MODIFIER, oldReturnTypeModifier, returnTypeModifier));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition() {
        return displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition(Expression newDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition, NotificationChain msgs) {
        Expression oldDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition;
        displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition = newDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION, oldDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition, newDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition(Expression newDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition) {
        if (newDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition != displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition) {
            NotificationChain msgs = null;
            if (displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition != null)
                msgs = ((InternalEObject)displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION, null, msgs);
            if (newDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition != null)
                msgs = ((InternalEObject)newDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION, null, msgs);
            msgs = basicSetDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition(newDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION, newDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition, newDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getDisplayAfterEventDependsOnConditionScript() {
        return displayAfterEventDependsOnConditionScript;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDisplayAfterEventDependsOnConditionScript(Expression newDisplayAfterEventDependsOnConditionScript, NotificationChain msgs) {
        Expression oldDisplayAfterEventDependsOnConditionScript = displayAfterEventDependsOnConditionScript;
        displayAfterEventDependsOnConditionScript = newDisplayAfterEventDependsOnConditionScript;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT, oldDisplayAfterEventDependsOnConditionScript, newDisplayAfterEventDependsOnConditionScript);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDisplayAfterEventDependsOnConditionScript(Expression newDisplayAfterEventDependsOnConditionScript) {
        if (newDisplayAfterEventDependsOnConditionScript != displayAfterEventDependsOnConditionScript) {
            NotificationChain msgs = null;
            if (displayAfterEventDependsOnConditionScript != null)
                msgs = ((InternalEObject)displayAfterEventDependsOnConditionScript).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT, null, msgs);
            if (newDisplayAfterEventDependsOnConditionScript != null)
                msgs = ((InternalEObject)newDisplayAfterEventDependsOnConditionScript).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT, null, msgs);
            msgs = basicSetDisplayAfterEventDependsOnConditionScript(newDisplayAfterEventDependsOnConditionScript, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT, newDisplayAfterEventDependsOnConditionScript, newDisplayAfterEventDependsOnConditionScript));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getInputExpression() {
        return inputExpression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInputExpression(Expression newInputExpression, NotificationChain msgs) {
        Expression oldInputExpression = inputExpression;
        inputExpression = newInputExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__INPUT_EXPRESSION, oldInputExpression, newInputExpression);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setInputExpression(Expression newInputExpression) {
        if (newInputExpression != inputExpression) {
            NotificationChain msgs = null;
            if (inputExpression != null)
                msgs = ((InternalEObject)inputExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__INPUT_EXPRESSION, null, msgs);
            if (newInputExpression != null)
                msgs = ((InternalEObject)newInputExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__INPUT_EXPRESSION, null, msgs);
            msgs = basicSetInputExpression(newInputExpression, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__INPUT_EXPRESSION, newInputExpression, newInputExpression));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getAfterEventExpression() {
        return afterEventExpression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetAfterEventExpression(Expression newAfterEventExpression, NotificationChain msgs) {
        Expression oldAfterEventExpression = afterEventExpression;
        afterEventExpression = newAfterEventExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__AFTER_EVENT_EXPRESSION, oldAfterEventExpression, newAfterEventExpression);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setAfterEventExpression(Expression newAfterEventExpression) {
        if (newAfterEventExpression != afterEventExpression) {
            NotificationChain msgs = null;
            if (afterEventExpression != null)
                msgs = ((InternalEObject)afterEventExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__AFTER_EVENT_EXPRESSION, null, msgs);
            if (newAfterEventExpression != null)
                msgs = ((InternalEObject)newAfterEventExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__AFTER_EVENT_EXPRESSION, null, msgs);
            msgs = basicSetAfterEventExpression(newAfterEventExpression, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__AFTER_EVENT_EXPRESSION, newAfterEventExpression, newAfterEventExpression));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getTooltip() {
        return tooltip;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTooltip(Expression newTooltip, NotificationChain msgs) {
        Expression oldTooltip = tooltip;
        tooltip = newTooltip;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__TOOLTIP, oldTooltip, newTooltip);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setTooltip(Expression newTooltip) {
        if (newTooltip != tooltip) {
            NotificationChain msgs = null;
            if (tooltip != null)
                msgs = ((InternalEObject)tooltip).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__TOOLTIP, null, msgs);
            if (newTooltip != null)
                msgs = ((InternalEObject)newTooltip).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__TOOLTIP, null, msgs);
            msgs = basicSetTooltip(newTooltip, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__TOOLTIP, newTooltip, newTooltip));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getHelpMessage() {
        return helpMessage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetHelpMessage(Expression newHelpMessage, NotificationChain msgs) {
        Expression oldHelpMessage = helpMessage;
        helpMessage = newHelpMessage;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__HELP_MESSAGE, oldHelpMessage, newHelpMessage);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setHelpMessage(Expression newHelpMessage) {
        if (newHelpMessage != helpMessage) {
            NotificationChain msgs = null;
            if (helpMessage != null)
                msgs = ((InternalEObject)helpMessage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__HELP_MESSAGE, null, msgs);
            if (newHelpMessage != null)
                msgs = ((InternalEObject)newHelpMessage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__HELP_MESSAGE, null, msgs);
            msgs = basicSetHelpMessage(newHelpMessage, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__HELP_MESSAGE, newHelpMessage, newHelpMessage));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getDisplayLabel() {
        return displayLabel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDisplayLabel(Expression newDisplayLabel, NotificationChain msgs) {
        Expression oldDisplayLabel = displayLabel;
        displayLabel = newDisplayLabel;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DISPLAY_LABEL, oldDisplayLabel, newDisplayLabel);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDisplayLabel(Expression newDisplayLabel) {
        if (newDisplayLabel != displayLabel) {
            NotificationChain msgs = null;
            if (displayLabel != null)
                msgs = ((InternalEObject)displayLabel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__DISPLAY_LABEL, null, msgs);
            if (newDisplayLabel != null)
                msgs = ((InternalEObject)newDisplayLabel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__DISPLAY_LABEL, null, msgs);
            msgs = basicSetDisplayLabel(newDisplayLabel, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DISPLAY_LABEL, newDisplayLabel, newDisplayLabel));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getInjectWidgetScript() {
        return injectWidgetScript;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetInjectWidgetScript(Expression newInjectWidgetScript, NotificationChain msgs) {
        Expression oldInjectWidgetScript = injectWidgetScript;
        injectWidgetScript = newInjectWidgetScript;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__INJECT_WIDGET_SCRIPT, oldInjectWidgetScript, newInjectWidgetScript);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setInjectWidgetScript(Expression newInjectWidgetScript) {
        if (newInjectWidgetScript != injectWidgetScript) {
            NotificationChain msgs = null;
            if (injectWidgetScript != null)
                msgs = ((InternalEObject)injectWidgetScript).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__INJECT_WIDGET_SCRIPT, null, msgs);
            if (newInjectWidgetScript != null)
                msgs = ((InternalEObject)newInjectWidgetScript).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__INJECT_WIDGET_SCRIPT, null, msgs);
            msgs = basicSetInjectWidgetScript(newInjectWidgetScript, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__INJECT_WIDGET_SCRIPT, newInjectWidgetScript, newInjectWidgetScript));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Operation getAction() {
        return action;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetAction(Operation newAction, NotificationChain msgs) {
        Operation oldAction = action;
        action = newAction;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__ACTION, oldAction, newAction);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setAction(Operation newAction) {
        if (newAction != action) {
            NotificationChain msgs = null;
            if (action != null)
                msgs = ((InternalEObject)action).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__ACTION, null, msgs);
            if (newAction != null)
                msgs = ((InternalEObject)newAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__ACTION, null, msgs);
            msgs = basicSetAction(newAction, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__ACTION, newAction, newAction));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isDuplicate() {
        return duplicate;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDuplicate(boolean newDuplicate) {
        boolean oldDuplicate = duplicate;
        duplicate = newDuplicate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DUPLICATE, oldDuplicate, duplicate));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isLimitNumberOfDuplication() {
        return limitNumberOfDuplication;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setLimitNumberOfDuplication(boolean newLimitNumberOfDuplication) {
        boolean oldLimitNumberOfDuplication = limitNumberOfDuplication;
        limitNumberOfDuplication = newLimitNumberOfDuplication;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__LIMIT_NUMBER_OF_DUPLICATION, oldLimitNumberOfDuplication, limitNumberOfDuplication));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isLimitMinNumberOfDuplication() {
        return limitMinNumberOfDuplication;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setLimitMinNumberOfDuplication(boolean newLimitMinNumberOfDuplication) {
        boolean oldLimitMinNumberOfDuplication = limitMinNumberOfDuplication;
        limitMinNumberOfDuplication = newLimitMinNumberOfDuplication;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__LIMIT_MIN_NUMBER_OF_DUPLICATION, oldLimitMinNumberOfDuplication, limitMinNumberOfDuplication));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getMaxNumberOfDuplication() {
        return maxNumberOfDuplication;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMaxNumberOfDuplication(Expression newMaxNumberOfDuplication, NotificationChain msgs) {
        Expression oldMaxNumberOfDuplication = maxNumberOfDuplication;
        maxNumberOfDuplication = newMaxNumberOfDuplication;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__MAX_NUMBER_OF_DUPLICATION, oldMaxNumberOfDuplication, newMaxNumberOfDuplication);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setMaxNumberOfDuplication(Expression newMaxNumberOfDuplication) {
        if (newMaxNumberOfDuplication != maxNumberOfDuplication) {
            NotificationChain msgs = null;
            if (maxNumberOfDuplication != null)
                msgs = ((InternalEObject)maxNumberOfDuplication).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__MAX_NUMBER_OF_DUPLICATION, null, msgs);
            if (newMaxNumberOfDuplication != null)
                msgs = ((InternalEObject)newMaxNumberOfDuplication).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__MAX_NUMBER_OF_DUPLICATION, null, msgs);
            msgs = basicSetMaxNumberOfDuplication(newMaxNumberOfDuplication, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__MAX_NUMBER_OF_DUPLICATION, newMaxNumberOfDuplication, newMaxNumberOfDuplication));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getMinNumberOfDuplication() {
        return minNumberOfDuplication;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMinNumberOfDuplication(Expression newMinNumberOfDuplication, NotificationChain msgs) {
        Expression oldMinNumberOfDuplication = minNumberOfDuplication;
        minNumberOfDuplication = newMinNumberOfDuplication;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__MIN_NUMBER_OF_DUPLICATION, oldMinNumberOfDuplication, newMinNumberOfDuplication);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setMinNumberOfDuplication(Expression newMinNumberOfDuplication) {
        if (newMinNumberOfDuplication != minNumberOfDuplication) {
            NotificationChain msgs = null;
            if (minNumberOfDuplication != null)
                msgs = ((InternalEObject)minNumberOfDuplication).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__MIN_NUMBER_OF_DUPLICATION, null, msgs);
            if (newMinNumberOfDuplication != null)
                msgs = ((InternalEObject)newMinNumberOfDuplication).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__MIN_NUMBER_OF_DUPLICATION, null, msgs);
            msgs = basicSetMinNumberOfDuplication(newMinNumberOfDuplication, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__MIN_NUMBER_OF_DUPLICATION, newMinNumberOfDuplication, newMinNumberOfDuplication));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getDisplayLabelForAdd() {
        return displayLabelForAdd;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDisplayLabelForAdd(Expression newDisplayLabelForAdd, NotificationChain msgs) {
        Expression oldDisplayLabelForAdd = displayLabelForAdd;
        displayLabelForAdd = newDisplayLabelForAdd;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DISPLAY_LABEL_FOR_ADD, oldDisplayLabelForAdd, newDisplayLabelForAdd);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDisplayLabelForAdd(Expression newDisplayLabelForAdd) {
        if (newDisplayLabelForAdd != displayLabelForAdd) {
            NotificationChain msgs = null;
            if (displayLabelForAdd != null)
                msgs = ((InternalEObject)displayLabelForAdd).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__DISPLAY_LABEL_FOR_ADD, null, msgs);
            if (newDisplayLabelForAdd != null)
                msgs = ((InternalEObject)newDisplayLabelForAdd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__DISPLAY_LABEL_FOR_ADD, null, msgs);
            msgs = basicSetDisplayLabelForAdd(newDisplayLabelForAdd, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DISPLAY_LABEL_FOR_ADD, newDisplayLabelForAdd, newDisplayLabelForAdd));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getTooltipForAdd() {
        return tooltipForAdd;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTooltipForAdd(Expression newTooltipForAdd, NotificationChain msgs) {
        Expression oldTooltipForAdd = tooltipForAdd;
        tooltipForAdd = newTooltipForAdd;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__TOOLTIP_FOR_ADD, oldTooltipForAdd, newTooltipForAdd);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setTooltipForAdd(Expression newTooltipForAdd) {
        if (newTooltipForAdd != tooltipForAdd) {
            NotificationChain msgs = null;
            if (tooltipForAdd != null)
                msgs = ((InternalEObject)tooltipForAdd).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__TOOLTIP_FOR_ADD, null, msgs);
            if (newTooltipForAdd != null)
                msgs = ((InternalEObject)newTooltipForAdd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__TOOLTIP_FOR_ADD, null, msgs);
            msgs = basicSetTooltipForAdd(newTooltipForAdd, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__TOOLTIP_FOR_ADD, newTooltipForAdd, newTooltipForAdd));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getDisplayLabelForRemove() {
        return displayLabelForRemove;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDisplayLabelForRemove(Expression newDisplayLabelForRemove, NotificationChain msgs) {
        Expression oldDisplayLabelForRemove = displayLabelForRemove;
        displayLabelForRemove = newDisplayLabelForRemove;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DISPLAY_LABEL_FOR_REMOVE, oldDisplayLabelForRemove, newDisplayLabelForRemove);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setDisplayLabelForRemove(Expression newDisplayLabelForRemove) {
        if (newDisplayLabelForRemove != displayLabelForRemove) {
            NotificationChain msgs = null;
            if (displayLabelForRemove != null)
                msgs = ((InternalEObject)displayLabelForRemove).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__DISPLAY_LABEL_FOR_REMOVE, null, msgs);
            if (newDisplayLabelForRemove != null)
                msgs = ((InternalEObject)newDisplayLabelForRemove).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__DISPLAY_LABEL_FOR_REMOVE, null, msgs);
            msgs = basicSetDisplayLabelForRemove(newDisplayLabelForRemove, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__DISPLAY_LABEL_FOR_REMOVE, newDisplayLabelForRemove, newDisplayLabelForRemove));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Expression getTooltipForRemove() {
        return tooltipForRemove;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTooltipForRemove(Expression newTooltipForRemove, NotificationChain msgs) {
        Expression oldTooltipForRemove = tooltipForRemove;
        tooltipForRemove = newTooltipForRemove;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__TOOLTIP_FOR_REMOVE, oldTooltipForRemove, newTooltipForRemove);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setTooltipForRemove(Expression newTooltipForRemove) {
        if (newTooltipForRemove != tooltipForRemove) {
            NotificationChain msgs = null;
            if (tooltipForRemove != null)
                msgs = ((InternalEObject)tooltipForRemove).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__TOOLTIP_FOR_REMOVE, null, msgs);
            if (newTooltipForRemove != null)
                msgs = ((InternalEObject)newTooltipForRemove).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__TOOLTIP_FOR_REMOVE, null, msgs);
            msgs = basicSetTooltipForRemove(newTooltipForRemove, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__TOOLTIP_FOR_REMOVE, newTooltipForRemove, newTooltipForRemove));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Widget> getWidgets() {
        if (widgets == null) {
            widgets = new EObjectContainmentEList<Widget>(Widget.class, this, FormPackage.GROUP__WIDGETS);
        }
        return widgets;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isShowBorder() {
        return showBorder;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setShowBorder(boolean newShowBorder) {
        boolean oldShowBorder = showBorder;
        showBorder = newShowBorder;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__SHOW_BORDER, oldShowBorder, showBorder));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Column> getColumns() {
        if (columns == null) {
            columns = new EObjectContainmentEList<Column>(Column.class, this, FormPackage.GROUP__COLUMNS);
        }
        return columns;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<Line> getLines() {
        if (lines == null) {
            lines = new EObjectContainmentEList<Line>(Line.class, this, FormPackage.GROUP__LINES);
        }
        return lines;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean isUseIterator() {
        return useIterator;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setUseIterator(boolean newUseIterator) {
        boolean oldUseIterator = useIterator;
        useIterator = newUseIterator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__USE_ITERATOR, oldUseIterator, useIterator));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public GroupIterator getIterator() {
        return iterator;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetIterator(GroupIterator newIterator, NotificationChain msgs) {
        GroupIterator oldIterator = iterator;
        iterator = newIterator;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__ITERATOR, oldIterator, newIterator);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setIterator(GroupIterator newIterator) {
        if (newIterator != iterator) {
            NotificationChain msgs = null;
            if (iterator != null)
                msgs = ((InternalEObject)iterator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__ITERATOR, null, msgs);
            if (newIterator != null)
                msgs = ((InternalEObject)newIterator).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.GROUP__ITERATOR, null, msgs);
            msgs = basicSetIterator(newIterator, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.GROUP__ITERATOR, newIterator, newIterator));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FormPackage.GROUP__TEXT_ANNOTATION_ATTACHMENT:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getTextAnnotationAttachment()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FormPackage.GROUP__TEXT_ANNOTATION_ATTACHMENT:
                return ((InternalEList<?>)getTextAnnotationAttachment()).basicRemove(otherEnd, msgs);
            case FormPackage.GROUP__HTML_ATTRIBUTES:
                return ((InternalEList<?>)getHtmlAttributes()).basicRemove(otherEnd, msgs);
            case FormPackage.GROUP__WIDGET_LAYOUT_INFO:
                return basicSetWidgetLayoutInfo(null, msgs);
            case FormPackage.GROUP__DEPEND_ON:
                return ((InternalEList<?>)getDependOn()).basicRemove(otherEnd, msgs);
            case FormPackage.GROUP__PARENT_OF:
                return ((InternalEList<?>)getParentOf()).basicRemove(otherEnd, msgs);
            case FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION:
                return basicSetDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition(null, msgs);
            case FormPackage.GROUP__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT:
                return basicSetDisplayAfterEventDependsOnConditionScript(null, msgs);
            case FormPackage.GROUP__INPUT_EXPRESSION:
                return basicSetInputExpression(null, msgs);
            case FormPackage.GROUP__AFTER_EVENT_EXPRESSION:
                return basicSetAfterEventExpression(null, msgs);
            case FormPackage.GROUP__TOOLTIP:
                return basicSetTooltip(null, msgs);
            case FormPackage.GROUP__HELP_MESSAGE:
                return basicSetHelpMessage(null, msgs);
            case FormPackage.GROUP__DISPLAY_LABEL:
                return basicSetDisplayLabel(null, msgs);
            case FormPackage.GROUP__INJECT_WIDGET_SCRIPT:
                return basicSetInjectWidgetScript(null, msgs);
            case FormPackage.GROUP__ACTION:
                return basicSetAction(null, msgs);
            case FormPackage.GROUP__MAX_NUMBER_OF_DUPLICATION:
                return basicSetMaxNumberOfDuplication(null, msgs);
            case FormPackage.GROUP__MIN_NUMBER_OF_DUPLICATION:
                return basicSetMinNumberOfDuplication(null, msgs);
            case FormPackage.GROUP__DISPLAY_LABEL_FOR_ADD:
                return basicSetDisplayLabelForAdd(null, msgs);
            case FormPackage.GROUP__TOOLTIP_FOR_ADD:
                return basicSetTooltipForAdd(null, msgs);
            case FormPackage.GROUP__DISPLAY_LABEL_FOR_REMOVE:
                return basicSetDisplayLabelForRemove(null, msgs);
            case FormPackage.GROUP__TOOLTIP_FOR_REMOVE:
                return basicSetTooltipForRemove(null, msgs);
            case FormPackage.GROUP__WIDGETS:
                return ((InternalEList<?>)getWidgets()).basicRemove(otherEnd, msgs);
            case FormPackage.GROUP__COLUMNS:
                return ((InternalEList<?>)getColumns()).basicRemove(otherEnd, msgs);
            case FormPackage.GROUP__LINES:
                return ((InternalEList<?>)getLines()).basicRemove(otherEnd, msgs);
            case FormPackage.GROUP__ITERATOR:
                return basicSetIterator(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FormPackage.GROUP__DOCUMENTATION:
                return getDocumentation();
            case FormPackage.GROUP__NAME:
                return getName();
            case FormPackage.GROUP__TEXT_ANNOTATION_ATTACHMENT:
                return getTextAnnotationAttachment();
            case FormPackage.GROUP__HTML_ATTRIBUTES:
                if (coreType) return getHtmlAttributes();
                else return getHtmlAttributes().map();
            case FormPackage.GROUP__WIDGET_LAYOUT_INFO:
                return getWidgetLayoutInfo();
            case FormPackage.GROUP__SHOW_DISPLAY_LABEL:
                return getShowDisplayLabel();
            case FormPackage.GROUP__ALLOW_HTML_FOR_DISPLAY_LABEL:
                return isAllowHTMLForDisplayLabel();
            case FormPackage.GROUP__DEPEND_ON:
                return getDependOn();
            case FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED:
                return isDisplayDependentWidgetOnlyOnEventTriggered();
            case FormPackage.GROUP__PARENT_OF:
                return getParentOf();
            case FormPackage.GROUP__MANDATORY:
                return isMandatory();
            case FormPackage.GROUP__READ_ONLY:
                return isReadOnly();
            case FormPackage.GROUP__LABEL_POSITION:
                return getLabelPosition();
            case FormPackage.GROUP__REAL_HTML_ATTRIBUTES:
                return getRealHtmlAttributes();
            case FormPackage.GROUP__INJECT_WIDGET_CONDITION:
                return isInjectWidgetCondition();
            case FormPackage.GROUP__VERSION:
                return getVersion();
            case FormPackage.GROUP__RETURN_TYPE_MODIFIER:
                return getReturnTypeModifier();
            case FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION:
                return getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition();
            case FormPackage.GROUP__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT:
                return getDisplayAfterEventDependsOnConditionScript();
            case FormPackage.GROUP__INPUT_EXPRESSION:
                return getInputExpression();
            case FormPackage.GROUP__AFTER_EVENT_EXPRESSION:
                return getAfterEventExpression();
            case FormPackage.GROUP__TOOLTIP:
                return getTooltip();
            case FormPackage.GROUP__HELP_MESSAGE:
                return getHelpMessage();
            case FormPackage.GROUP__DISPLAY_LABEL:
                return getDisplayLabel();
            case FormPackage.GROUP__INJECT_WIDGET_SCRIPT:
                return getInjectWidgetScript();
            case FormPackage.GROUP__ACTION:
                return getAction();
            case FormPackage.GROUP__DUPLICATE:
                return isDuplicate();
            case FormPackage.GROUP__LIMIT_NUMBER_OF_DUPLICATION:
                return isLimitNumberOfDuplication();
            case FormPackage.GROUP__LIMIT_MIN_NUMBER_OF_DUPLICATION:
                return isLimitMinNumberOfDuplication();
            case FormPackage.GROUP__MAX_NUMBER_OF_DUPLICATION:
                return getMaxNumberOfDuplication();
            case FormPackage.GROUP__MIN_NUMBER_OF_DUPLICATION:
                return getMinNumberOfDuplication();
            case FormPackage.GROUP__DISPLAY_LABEL_FOR_ADD:
                return getDisplayLabelForAdd();
            case FormPackage.GROUP__TOOLTIP_FOR_ADD:
                return getTooltipForAdd();
            case FormPackage.GROUP__DISPLAY_LABEL_FOR_REMOVE:
                return getDisplayLabelForRemove();
            case FormPackage.GROUP__TOOLTIP_FOR_REMOVE:
                return getTooltipForRemove();
            case FormPackage.GROUP__WIDGETS:
                return getWidgets();
            case FormPackage.GROUP__SHOW_BORDER:
                return isShowBorder();
            case FormPackage.GROUP__COLUMNS:
                return getColumns();
            case FormPackage.GROUP__LINES:
                return getLines();
            case FormPackage.GROUP__USE_ITERATOR:
                return isUseIterator();
            case FormPackage.GROUP__ITERATOR:
                return getIterator();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case FormPackage.GROUP__DOCUMENTATION:
                setDocumentation((String)newValue);
                return;
            case FormPackage.GROUP__NAME:
                setName((String)newValue);
                return;
            case FormPackage.GROUP__TEXT_ANNOTATION_ATTACHMENT:
                getTextAnnotationAttachment().clear();
                getTextAnnotationAttachment().addAll((Collection<? extends TextAnnotationAttachment>)newValue);
                return;
            case FormPackage.GROUP__HTML_ATTRIBUTES:
                ((EStructuralFeature.Setting)getHtmlAttributes()).set(newValue);
                return;
            case FormPackage.GROUP__WIDGET_LAYOUT_INFO:
                setWidgetLayoutInfo((WidgetLayoutInfo)newValue);
                return;
            case FormPackage.GROUP__SHOW_DISPLAY_LABEL:
                setShowDisplayLabel((Boolean)newValue);
                return;
            case FormPackage.GROUP__ALLOW_HTML_FOR_DISPLAY_LABEL:
                setAllowHTMLForDisplayLabel((Boolean)newValue);
                return;
            case FormPackage.GROUP__DEPEND_ON:
                getDependOn().clear();
                getDependOn().addAll((Collection<? extends WidgetDependency>)newValue);
                return;
            case FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED:
                setDisplayDependentWidgetOnlyOnEventTriggered((Boolean)newValue);
                return;
            case FormPackage.GROUP__PARENT_OF:
                getParentOf().clear();
                getParentOf().addAll((Collection<? extends WidgetDependency>)newValue);
                return;
            case FormPackage.GROUP__MANDATORY:
                setMandatory((Boolean)newValue);
                return;
            case FormPackage.GROUP__READ_ONLY:
                setReadOnly((Boolean)newValue);
                return;
            case FormPackage.GROUP__LABEL_POSITION:
                setLabelPosition((LabelPosition)newValue);
                return;
            case FormPackage.GROUP__REAL_HTML_ATTRIBUTES:
                setRealHtmlAttributes((String)newValue);
                return;
            case FormPackage.GROUP__INJECT_WIDGET_CONDITION:
                setInjectWidgetCondition((Boolean)newValue);
                return;
            case FormPackage.GROUP__VERSION:
                setVersion((String)newValue);
                return;
            case FormPackage.GROUP__RETURN_TYPE_MODIFIER:
                setReturnTypeModifier((String)newValue);
                return;
            case FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION:
                setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression)newValue);
                return;
            case FormPackage.GROUP__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT:
                setDisplayAfterEventDependsOnConditionScript((Expression)newValue);
                return;
            case FormPackage.GROUP__INPUT_EXPRESSION:
                setInputExpression((Expression)newValue);
                return;
            case FormPackage.GROUP__AFTER_EVENT_EXPRESSION:
                setAfterEventExpression((Expression)newValue);
                return;
            case FormPackage.GROUP__TOOLTIP:
                setTooltip((Expression)newValue);
                return;
            case FormPackage.GROUP__HELP_MESSAGE:
                setHelpMessage((Expression)newValue);
                return;
            case FormPackage.GROUP__DISPLAY_LABEL:
                setDisplayLabel((Expression)newValue);
                return;
            case FormPackage.GROUP__INJECT_WIDGET_SCRIPT:
                setInjectWidgetScript((Expression)newValue);
                return;
            case FormPackage.GROUP__ACTION:
                setAction((Operation)newValue);
                return;
            case FormPackage.GROUP__DUPLICATE:
                setDuplicate((Boolean)newValue);
                return;
            case FormPackage.GROUP__LIMIT_NUMBER_OF_DUPLICATION:
                setLimitNumberOfDuplication((Boolean)newValue);
                return;
            case FormPackage.GROUP__LIMIT_MIN_NUMBER_OF_DUPLICATION:
                setLimitMinNumberOfDuplication((Boolean)newValue);
                return;
            case FormPackage.GROUP__MAX_NUMBER_OF_DUPLICATION:
                setMaxNumberOfDuplication((Expression)newValue);
                return;
            case FormPackage.GROUP__MIN_NUMBER_OF_DUPLICATION:
                setMinNumberOfDuplication((Expression)newValue);
                return;
            case FormPackage.GROUP__DISPLAY_LABEL_FOR_ADD:
                setDisplayLabelForAdd((Expression)newValue);
                return;
            case FormPackage.GROUP__TOOLTIP_FOR_ADD:
                setTooltipForAdd((Expression)newValue);
                return;
            case FormPackage.GROUP__DISPLAY_LABEL_FOR_REMOVE:
                setDisplayLabelForRemove((Expression)newValue);
                return;
            case FormPackage.GROUP__TOOLTIP_FOR_REMOVE:
                setTooltipForRemove((Expression)newValue);
                return;
            case FormPackage.GROUP__WIDGETS:
                getWidgets().clear();
                getWidgets().addAll((Collection<? extends Widget>)newValue);
                return;
            case FormPackage.GROUP__SHOW_BORDER:
                setShowBorder((Boolean)newValue);
                return;
            case FormPackage.GROUP__COLUMNS:
                getColumns().clear();
                getColumns().addAll((Collection<? extends Column>)newValue);
                return;
            case FormPackage.GROUP__LINES:
                getLines().clear();
                getLines().addAll((Collection<? extends Line>)newValue);
                return;
            case FormPackage.GROUP__USE_ITERATOR:
                setUseIterator((Boolean)newValue);
                return;
            case FormPackage.GROUP__ITERATOR:
                setIterator((GroupIterator)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eUnset(int featureID) {
        switch (featureID) {
            case FormPackage.GROUP__DOCUMENTATION:
                setDocumentation(DOCUMENTATION_EDEFAULT);
                return;
            case FormPackage.GROUP__NAME:
                setName(NAME_EDEFAULT);
                return;
            case FormPackage.GROUP__TEXT_ANNOTATION_ATTACHMENT:
                getTextAnnotationAttachment().clear();
                return;
            case FormPackage.GROUP__HTML_ATTRIBUTES:
                getHtmlAttributes().clear();
                return;
            case FormPackage.GROUP__WIDGET_LAYOUT_INFO:
                setWidgetLayoutInfo((WidgetLayoutInfo)null);
                return;
            case FormPackage.GROUP__SHOW_DISPLAY_LABEL:
                setShowDisplayLabel(SHOW_DISPLAY_LABEL_EDEFAULT);
                return;
            case FormPackage.GROUP__ALLOW_HTML_FOR_DISPLAY_LABEL:
                setAllowHTMLForDisplayLabel(ALLOW_HTML_FOR_DISPLAY_LABEL_EDEFAULT);
                return;
            case FormPackage.GROUP__DEPEND_ON:
                getDependOn().clear();
                return;
            case FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED:
                setDisplayDependentWidgetOnlyOnEventTriggered(DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED_EDEFAULT);
                return;
            case FormPackage.GROUP__PARENT_OF:
                getParentOf().clear();
                return;
            case FormPackage.GROUP__MANDATORY:
                setMandatory(MANDATORY_EDEFAULT);
                return;
            case FormPackage.GROUP__READ_ONLY:
                setReadOnly(READ_ONLY_EDEFAULT);
                return;
            case FormPackage.GROUP__LABEL_POSITION:
                setLabelPosition(LABEL_POSITION_EDEFAULT);
                return;
            case FormPackage.GROUP__REAL_HTML_ATTRIBUTES:
                setRealHtmlAttributes(REAL_HTML_ATTRIBUTES_EDEFAULT);
                return;
            case FormPackage.GROUP__INJECT_WIDGET_CONDITION:
                setInjectWidgetCondition(INJECT_WIDGET_CONDITION_EDEFAULT);
                return;
            case FormPackage.GROUP__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case FormPackage.GROUP__RETURN_TYPE_MODIFIER:
                setReturnTypeModifier(RETURN_TYPE_MODIFIER_EDEFAULT);
                return;
            case FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION:
                setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression)null);
                return;
            case FormPackage.GROUP__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT:
                setDisplayAfterEventDependsOnConditionScript((Expression)null);
                return;
            case FormPackage.GROUP__INPUT_EXPRESSION:
                setInputExpression((Expression)null);
                return;
            case FormPackage.GROUP__AFTER_EVENT_EXPRESSION:
                setAfterEventExpression((Expression)null);
                return;
            case FormPackage.GROUP__TOOLTIP:
                setTooltip((Expression)null);
                return;
            case FormPackage.GROUP__HELP_MESSAGE:
                setHelpMessage((Expression)null);
                return;
            case FormPackage.GROUP__DISPLAY_LABEL:
                setDisplayLabel((Expression)null);
                return;
            case FormPackage.GROUP__INJECT_WIDGET_SCRIPT:
                setInjectWidgetScript((Expression)null);
                return;
            case FormPackage.GROUP__ACTION:
                setAction((Operation)null);
                return;
            case FormPackage.GROUP__DUPLICATE:
                setDuplicate(DUPLICATE_EDEFAULT);
                return;
            case FormPackage.GROUP__LIMIT_NUMBER_OF_DUPLICATION:
                setLimitNumberOfDuplication(LIMIT_NUMBER_OF_DUPLICATION_EDEFAULT);
                return;
            case FormPackage.GROUP__LIMIT_MIN_NUMBER_OF_DUPLICATION:
                setLimitMinNumberOfDuplication(LIMIT_MIN_NUMBER_OF_DUPLICATION_EDEFAULT);
                return;
            case FormPackage.GROUP__MAX_NUMBER_OF_DUPLICATION:
                setMaxNumberOfDuplication((Expression)null);
                return;
            case FormPackage.GROUP__MIN_NUMBER_OF_DUPLICATION:
                setMinNumberOfDuplication((Expression)null);
                return;
            case FormPackage.GROUP__DISPLAY_LABEL_FOR_ADD:
                setDisplayLabelForAdd((Expression)null);
                return;
            case FormPackage.GROUP__TOOLTIP_FOR_ADD:
                setTooltipForAdd((Expression)null);
                return;
            case FormPackage.GROUP__DISPLAY_LABEL_FOR_REMOVE:
                setDisplayLabelForRemove((Expression)null);
                return;
            case FormPackage.GROUP__TOOLTIP_FOR_REMOVE:
                setTooltipForRemove((Expression)null);
                return;
            case FormPackage.GROUP__WIDGETS:
                getWidgets().clear();
                return;
            case FormPackage.GROUP__SHOW_BORDER:
                setShowBorder(SHOW_BORDER_EDEFAULT);
                return;
            case FormPackage.GROUP__COLUMNS:
                getColumns().clear();
                return;
            case FormPackage.GROUP__LINES:
                getLines().clear();
                return;
            case FormPackage.GROUP__USE_ITERATOR:
                setUseIterator(USE_ITERATOR_EDEFAULT);
                return;
            case FormPackage.GROUP__ITERATOR:
                setIterator((GroupIterator)null);
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case FormPackage.GROUP__DOCUMENTATION:
                return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
            case FormPackage.GROUP__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case FormPackage.GROUP__TEXT_ANNOTATION_ATTACHMENT:
                return textAnnotationAttachment != null && !textAnnotationAttachment.isEmpty();
            case FormPackage.GROUP__HTML_ATTRIBUTES:
                return htmlAttributes != null && !htmlAttributes.isEmpty();
            case FormPackage.GROUP__WIDGET_LAYOUT_INFO:
                return widgetLayoutInfo != null;
            case FormPackage.GROUP__SHOW_DISPLAY_LABEL:
                return SHOW_DISPLAY_LABEL_EDEFAULT == null ? showDisplayLabel != null : !SHOW_DISPLAY_LABEL_EDEFAULT.equals(showDisplayLabel);
            case FormPackage.GROUP__ALLOW_HTML_FOR_DISPLAY_LABEL:
                return allowHTMLForDisplayLabel != ALLOW_HTML_FOR_DISPLAY_LABEL_EDEFAULT;
            case FormPackage.GROUP__DEPEND_ON:
                return dependOn != null && !dependOn.isEmpty();
            case FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED:
                return displayDependentWidgetOnlyOnEventTriggered != DISPLAY_DEPENDENT_WIDGET_ONLY_ON_EVENT_TRIGGERED_EDEFAULT;
            case FormPackage.GROUP__PARENT_OF:
                return parentOf != null && !parentOf.isEmpty();
            case FormPackage.GROUP__MANDATORY:
                return mandatory != MANDATORY_EDEFAULT;
            case FormPackage.GROUP__READ_ONLY:
                return readOnly != READ_ONLY_EDEFAULT;
            case FormPackage.GROUP__LABEL_POSITION:
                return labelPosition != LABEL_POSITION_EDEFAULT;
            case FormPackage.GROUP__REAL_HTML_ATTRIBUTES:
                return REAL_HTML_ATTRIBUTES_EDEFAULT == null ? realHtmlAttributes != null : !REAL_HTML_ATTRIBUTES_EDEFAULT.equals(realHtmlAttributes);
            case FormPackage.GROUP__INJECT_WIDGET_CONDITION:
                return injectWidgetCondition != INJECT_WIDGET_CONDITION_EDEFAULT;
            case FormPackage.GROUP__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case FormPackage.GROUP__RETURN_TYPE_MODIFIER:
                return RETURN_TYPE_MODIFIER_EDEFAULT == null ? returnTypeModifier != null : !RETURN_TYPE_MODIFIER_EDEFAULT.equals(returnTypeModifier);
            case FormPackage.GROUP__DISPLAY_DEPENDENT_WIDGET_ONLY_AFTER_FIRST_EVENT_TRIGGERED_AND_CONDITION:
                return displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition != null;
            case FormPackage.GROUP__DISPLAY_AFTER_EVENT_DEPENDS_ON_CONDITION_SCRIPT:
                return displayAfterEventDependsOnConditionScript != null;
            case FormPackage.GROUP__INPUT_EXPRESSION:
                return inputExpression != null;
            case FormPackage.GROUP__AFTER_EVENT_EXPRESSION:
                return afterEventExpression != null;
            case FormPackage.GROUP__TOOLTIP:
                return tooltip != null;
            case FormPackage.GROUP__HELP_MESSAGE:
                return helpMessage != null;
            case FormPackage.GROUP__DISPLAY_LABEL:
                return displayLabel != null;
            case FormPackage.GROUP__INJECT_WIDGET_SCRIPT:
                return injectWidgetScript != null;
            case FormPackage.GROUP__ACTION:
                return action != null;
            case FormPackage.GROUP__DUPLICATE:
                return duplicate != DUPLICATE_EDEFAULT;
            case FormPackage.GROUP__LIMIT_NUMBER_OF_DUPLICATION:
                return limitNumberOfDuplication != LIMIT_NUMBER_OF_DUPLICATION_EDEFAULT;
            case FormPackage.GROUP__LIMIT_MIN_NUMBER_OF_DUPLICATION:
                return limitMinNumberOfDuplication != LIMIT_MIN_NUMBER_OF_DUPLICATION_EDEFAULT;
            case FormPackage.GROUP__MAX_NUMBER_OF_DUPLICATION:
                return maxNumberOfDuplication != null;
            case FormPackage.GROUP__MIN_NUMBER_OF_DUPLICATION:
                return minNumberOfDuplication != null;
            case FormPackage.GROUP__DISPLAY_LABEL_FOR_ADD:
                return displayLabelForAdd != null;
            case FormPackage.GROUP__TOOLTIP_FOR_ADD:
                return tooltipForAdd != null;
            case FormPackage.GROUP__DISPLAY_LABEL_FOR_REMOVE:
                return displayLabelForRemove != null;
            case FormPackage.GROUP__TOOLTIP_FOR_REMOVE:
                return tooltipForRemove != null;
            case FormPackage.GROUP__WIDGETS:
                return widgets != null && !widgets.isEmpty();
            case FormPackage.GROUP__SHOW_BORDER:
                return showBorder != SHOW_BORDER_EDEFAULT;
            case FormPackage.GROUP__COLUMNS:
                return columns != null && !columns.isEmpty();
            case FormPackage.GROUP__LINES:
                return lines != null && !lines.isEmpty();
            case FormPackage.GROUP__USE_ITERATOR:
                return useIterator != USE_ITERATOR_EDEFAULT;
            case FormPackage.GROUP__ITERATOR:
                return iterator != null;
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == CSSCustomizable.class) {
            switch (derivedFeatureID) {
                case FormPackage.GROUP__HTML_ATTRIBUTES: return FormPackage.CSS_CUSTOMIZABLE__HTML_ATTRIBUTES;
                default: return -1;
            }
        }
        if (baseClass == Duplicable.class) {
            switch (derivedFeatureID) {
                case FormPackage.GROUP__DUPLICATE: return FormPackage.DUPLICABLE__DUPLICATE;
                case FormPackage.GROUP__LIMIT_NUMBER_OF_DUPLICATION: return FormPackage.DUPLICABLE__LIMIT_NUMBER_OF_DUPLICATION;
                case FormPackage.GROUP__LIMIT_MIN_NUMBER_OF_DUPLICATION: return FormPackage.DUPLICABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION;
                case FormPackage.GROUP__MAX_NUMBER_OF_DUPLICATION: return FormPackage.DUPLICABLE__MAX_NUMBER_OF_DUPLICATION;
                case FormPackage.GROUP__MIN_NUMBER_OF_DUPLICATION: return FormPackage.DUPLICABLE__MIN_NUMBER_OF_DUPLICATION;
                case FormPackage.GROUP__DISPLAY_LABEL_FOR_ADD: return FormPackage.DUPLICABLE__DISPLAY_LABEL_FOR_ADD;
                case FormPackage.GROUP__TOOLTIP_FOR_ADD: return FormPackage.DUPLICABLE__TOOLTIP_FOR_ADD;
                case FormPackage.GROUP__DISPLAY_LABEL_FOR_REMOVE: return FormPackage.DUPLICABLE__DISPLAY_LABEL_FOR_REMOVE;
                case FormPackage.GROUP__TOOLTIP_FOR_REMOVE: return FormPackage.DUPLICABLE__TOOLTIP_FOR_REMOVE;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == CSSCustomizable.class) {
            switch (baseFeatureID) {
                case FormPackage.CSS_CUSTOMIZABLE__HTML_ATTRIBUTES: return FormPackage.GROUP__HTML_ATTRIBUTES;
                default: return -1;
            }
        }
        if (baseClass == Duplicable.class) {
            switch (baseFeatureID) {
                case FormPackage.DUPLICABLE__DUPLICATE: return FormPackage.GROUP__DUPLICATE;
                case FormPackage.DUPLICABLE__LIMIT_NUMBER_OF_DUPLICATION: return FormPackage.GROUP__LIMIT_NUMBER_OF_DUPLICATION;
                case FormPackage.DUPLICABLE__LIMIT_MIN_NUMBER_OF_DUPLICATION: return FormPackage.GROUP__LIMIT_MIN_NUMBER_OF_DUPLICATION;
                case FormPackage.DUPLICABLE__MAX_NUMBER_OF_DUPLICATION: return FormPackage.GROUP__MAX_NUMBER_OF_DUPLICATION;
                case FormPackage.DUPLICABLE__MIN_NUMBER_OF_DUPLICATION: return FormPackage.GROUP__MIN_NUMBER_OF_DUPLICATION;
                case FormPackage.DUPLICABLE__DISPLAY_LABEL_FOR_ADD: return FormPackage.GROUP__DISPLAY_LABEL_FOR_ADD;
                case FormPackage.DUPLICABLE__TOOLTIP_FOR_ADD: return FormPackage.GROUP__TOOLTIP_FOR_ADD;
                case FormPackage.DUPLICABLE__DISPLAY_LABEL_FOR_REMOVE: return FormPackage.GROUP__DISPLAY_LABEL_FOR_REMOVE;
                case FormPackage.DUPLICABLE__TOOLTIP_FOR_REMOVE: return FormPackage.GROUP__TOOLTIP_FOR_REMOVE;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (documentation: "); //$NON-NLS-1$
        result.append(documentation);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", showDisplayLabel: "); //$NON-NLS-1$
        result.append(showDisplayLabel);
        result.append(", allowHTMLForDisplayLabel: "); //$NON-NLS-1$
        result.append(allowHTMLForDisplayLabel);
        result.append(", displayDependentWidgetOnlyOnEventTriggered: "); //$NON-NLS-1$
        result.append(displayDependentWidgetOnlyOnEventTriggered);
        result.append(", mandatory: "); //$NON-NLS-1$
        result.append(mandatory);
        result.append(", readOnly: "); //$NON-NLS-1$
        result.append(readOnly);
        result.append(", labelPosition: "); //$NON-NLS-1$
        result.append(labelPosition);
        result.append(", realHtmlAttributes: "); //$NON-NLS-1$
        result.append(realHtmlAttributes);
        result.append(", injectWidgetCondition: "); //$NON-NLS-1$
        result.append(injectWidgetCondition);
        result.append(", version: "); //$NON-NLS-1$
        result.append(version);
        result.append(", returnTypeModifier: "); //$NON-NLS-1$
        result.append(returnTypeModifier);
        result.append(", duplicate: "); //$NON-NLS-1$
        result.append(duplicate);
        result.append(", limitNumberOfDuplication: "); //$NON-NLS-1$
        result.append(limitNumberOfDuplication);
        result.append(", limitMinNumberOfDuplication: "); //$NON-NLS-1$
        result.append(limitMinNumberOfDuplication);
        result.append(", showBorder: "); //$NON-NLS-1$
        result.append(showBorder);
        result.append(", useIterator: "); //$NON-NLS-1$
        result.append(useIterator);
        result.append(')');
        return result.toString();
    }

} //GroupImpl
