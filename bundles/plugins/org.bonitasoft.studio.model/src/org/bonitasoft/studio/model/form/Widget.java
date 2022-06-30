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

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;

import org.bonitasoft.studio.model.process.Element;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Widget</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getWidgetLayoutInfo <em>Widget Layout Info</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getShowDisplayLabel <em>Show Display Label</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#isAllowHTMLForDisplayLabel <em>Allow HTML For Display Label</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getDependOn <em>Depend On</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#isDisplayDependentWidgetOnlyOnEventTriggered <em>Display Dependent Widget Only On Event Triggered</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getParentOf <em>Parent Of</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#isReadOnly <em>Read Only</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getLabelPosition <em>Label Position</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getRealHtmlAttributes <em>Real Html Attributes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#isInjectWidgetCondition <em>Inject Widget Condition</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getReturnTypeModifier <em>Return Type Modifier</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition <em>Display Dependent Widget Only After First Event Triggered And Condition</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getDisplayAfterEventDependsOnConditionScript <em>Display After Event Depends On Condition Script</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getInputExpression <em>Input Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getAfterEventExpression <em>After Event Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getHelpMessage <em>Help Message</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getDisplayLabel <em>Display Label</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getInjectWidgetScript <em>Inject Widget Script</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.form.Widget#getAction <em>Action</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.form.FormPackage#getWidget()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Widget extends Element, CSSCustomizable {
	/**
     * Returns the value of the '<em><b>Widget Layout Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widget Layout Info</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Widget Layout Info</em>' containment reference.
     * @see #setWidgetLayoutInfo(WidgetLayoutInfo)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_WidgetLayoutInfo()
     * @model containment="true"
     * @generated
     */
	WidgetLayoutInfo getWidgetLayoutInfo();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getWidgetLayoutInfo <em>Widget Layout Info</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Widget Layout Info</em>' containment reference.
     * @see #getWidgetLayoutInfo()
     * @generated
     */
	void setWidgetLayoutInfo(WidgetLayoutInfo value);

	/**
     * Returns the value of the '<em><b>Show Display Label</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Show Display Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Show Display Label</em>' attribute.
     * @see #setShowDisplayLabel(Boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_ShowDisplayLabel()
     * @model default="true"
     * @generated
     */
	Boolean getShowDisplayLabel();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getShowDisplayLabel <em>Show Display Label</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Show Display Label</em>' attribute.
     * @see #getShowDisplayLabel()
     * @generated
     */
	void setShowDisplayLabel(Boolean value);

	/**
     * Returns the value of the '<em><b>Allow HTML For Display Label</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allow HTML For Display Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Allow HTML For Display Label</em>' attribute.
     * @see #setAllowHTMLForDisplayLabel(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_AllowHTMLForDisplayLabel()
     * @model default="false"
     * @generated
     */
	boolean isAllowHTMLForDisplayLabel();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#isAllowHTMLForDisplayLabel <em>Allow HTML For Display Label</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Allow HTML For Display Label</em>' attribute.
     * @see #isAllowHTMLForDisplayLabel()
     * @generated
     */
	void setAllowHTMLForDisplayLabel(boolean value);

	/**
     * Returns the value of the '<em><b>Depend On</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.form.WidgetDependency}.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The list of widgets in depend on is the list of widgets that need to be filled before the widget appeared and the initial value of widget is filled
     * <!-- end-model-doc -->
     * @return the value of the '<em>Depend On</em>' containment reference list.
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_DependOn()
     * @model containment="true"
     * @generated
     */
	EList<WidgetDependency> getDependOn();

	/**
     * Returns the value of the '<em><b>Display Dependent Widget Only On Event Triggered</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * @Deprecated since 5.5
     * <!-- end-model-doc -->
     * @return the value of the '<em>Display Dependent Widget Only On Event Triggered</em>' attribute.
     * @see #setDisplayDependentWidgetOnlyOnEventTriggered(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_DisplayDependentWidgetOnlyOnEventTriggered()
     * @model default="false"
     * @generated
     */
	boolean isDisplayDependentWidgetOnlyOnEventTriggered();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#isDisplayDependentWidgetOnlyOnEventTriggered <em>Display Dependent Widget Only On Event Triggered</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Dependent Widget Only On Event Triggered</em>' attribute.
     * @see #isDisplayDependentWidgetOnlyOnEventTriggered()
     * @generated
     */
	void setDisplayDependentWidgetOnlyOnEventTriggered(boolean value);

	/**
     * Returns the value of the '<em><b>Parent Of</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.form.WidgetDependency}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Of</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parent Of</em>' containment reference list.
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_ParentOf()
     * @model containment="true"
     * @generated
     */
	EList<WidgetDependency> getParentOf();

	/**
     * Returns the value of the '<em><b>Mandatory</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Mandatory</em>' attribute.
     * @see #setMandatory(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_Mandatory()
     * @model default="false"
     * @generated
     */
	boolean isMandatory();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#isMandatory <em>Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mandatory</em>' attribute.
     * @see #isMandatory()
     * @generated
     */
	void setMandatory(boolean value);

	/**
     * Returns the value of the '<em><b>Read Only</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Read Only</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Read Only</em>' attribute.
     * @see #setReadOnly(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_ReadOnly()
     * @model default="false"
     * @generated
     */
	boolean isReadOnly();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#isReadOnly <em>Read Only</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Read Only</em>' attribute.
     * @see #isReadOnly()
     * @generated
     */
	void setReadOnly(boolean value);

	/**
     * Returns the value of the '<em><b>Label Position</b></em>' attribute.
     * The default value is <code>"Up"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.form.LabelPosition}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Position</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Label Position</em>' attribute.
     * @see org.bonitasoft.studio.model.form.LabelPosition
     * @see #setLabelPosition(LabelPosition)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_LabelPosition()
     * @model default="Up"
     * @generated
     */
	LabelPosition getLabelPosition();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getLabelPosition <em>Label Position</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label Position</em>' attribute.
     * @see org.bonitasoft.studio.model.form.LabelPosition
     * @see #getLabelPosition()
     * @generated
     */
	void setLabelPosition(LabelPosition value);

	/**
     * Returns the value of the '<em><b>Real Html Attributes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * the name have "real" prefix because, for historic reason, htmlAttributes is used for style attribute
     * <!-- end-model-doc -->
     * @return the value of the '<em>Real Html Attributes</em>' attribute.
     * @see #setRealHtmlAttributes(String)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_RealHtmlAttributes()
     * @model
     * @generated
     */
	String getRealHtmlAttributes();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getRealHtmlAttributes <em>Real Html Attributes</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Real Html Attributes</em>' attribute.
     * @see #getRealHtmlAttributes()
     * @generated
     */
	void setRealHtmlAttributes(String value);

	/**
     * Returns the value of the '<em><b>Inject Widget Condition</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inject Widget Condition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Inject Widget Condition</em>' attribute.
     * @see #setInjectWidgetCondition(boolean)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_InjectWidgetCondition()
     * @model default="false"
     * @generated
     */
	boolean isInjectWidgetCondition();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#isInjectWidgetCondition <em>Inject Widget Condition</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Inject Widget Condition</em>' attribute.
     * @see #isInjectWidgetCondition()
     * @generated
     */
	void setInjectWidgetCondition(boolean value);

	/**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_Version()
     * @model
     * @generated
     */
	String getVersion();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
	void setVersion(String value);

	/**
     * Returns the value of the '<em><b>Return Type Modifier</b></em>' attribute.
     * The default value is <code>"java.lang.String"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type Modifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Return Type Modifier</em>' attribute.
     * @see #setReturnTypeModifier(String)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_ReturnTypeModifier()
     * @model default="java.lang.String"
     * @generated
     */
	String getReturnTypeModifier();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getReturnTypeModifier <em>Return Type Modifier</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Return Type Modifier</em>' attribute.
     * @see #getReturnTypeModifier()
     * @generated
     */
	void setReturnTypeModifier(String value);

	/**
     * Returns the value of the '<em><b>Display Dependent Widget Only After First Event Triggered And Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Dependent Widget Only After First Event Triggered And Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Display Dependent Widget Only After First Event Triggered And Condition</em>' containment reference.
     * @see #setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_DisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition()
     * @model containment="true"
     * @generated
     */
	Expression getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition <em>Display Dependent Widget Only After First Event Triggered And Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Dependent Widget Only After First Event Triggered And Condition</em>' containment reference.
     * @see #getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition()
     * @generated
     */
	void setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition(Expression value);

	/**
     * Returns the value of the '<em><b>Display After Event Depends On Condition Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display After Event Depends On Condition Script</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Display After Event Depends On Condition Script</em>' containment reference.
     * @see #setDisplayAfterEventDependsOnConditionScript(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_DisplayAfterEventDependsOnConditionScript()
     * @model containment="true"
     * @generated
     */
	Expression getDisplayAfterEventDependsOnConditionScript();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getDisplayAfterEventDependsOnConditionScript <em>Display After Event Depends On Condition Script</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display After Event Depends On Condition Script</em>' containment reference.
     * @see #getDisplayAfterEventDependsOnConditionScript()
     * @generated
     */
	void setDisplayAfterEventDependsOnConditionScript(Expression value);

	/**
     * Returns the value of the '<em><b>Input Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Input Expression</em>' containment reference.
     * @see #setInputExpression(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_InputExpression()
     * @model containment="true"
     * @generated
     */
	Expression getInputExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getInputExpression <em>Input Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Expression</em>' containment reference.
     * @see #getInputExpression()
     * @generated
     */
	void setInputExpression(Expression value);

	/**
     * Returns the value of the '<em><b>After Event Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>After Event Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>After Event Expression</em>' containment reference.
     * @see #setAfterEventExpression(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_AfterEventExpression()
     * @model containment="true"
     * @generated
     */
	Expression getAfterEventExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getAfterEventExpression <em>After Event Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>After Event Expression</em>' containment reference.
     * @see #getAfterEventExpression()
     * @generated
     */
	void setAfterEventExpression(Expression value);

	/**
     * Returns the value of the '<em><b>Tooltip</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tooltip</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Tooltip</em>' containment reference.
     * @see #setTooltip(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_Tooltip()
     * @model containment="true"
     * @generated
     */
	Expression getTooltip();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getTooltip <em>Tooltip</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Tooltip</em>' containment reference.
     * @see #getTooltip()
     * @generated
     */
	void setTooltip(Expression value);

	/**
     * Returns the value of the '<em><b>Help Message</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Help Message</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Help Message</em>' containment reference.
     * @see #setHelpMessage(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_HelpMessage()
     * @model containment="true"
     * @generated
     */
	Expression getHelpMessage();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getHelpMessage <em>Help Message</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Help Message</em>' containment reference.
     * @see #getHelpMessage()
     * @generated
     */
	void setHelpMessage(Expression value);

	/**
     * Returns the value of the '<em><b>Display Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Label</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Display Label</em>' containment reference.
     * @see #setDisplayLabel(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_DisplayLabel()
     * @model containment="true"
     * @generated
     */
	Expression getDisplayLabel();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getDisplayLabel <em>Display Label</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Label</em>' containment reference.
     * @see #getDisplayLabel()
     * @generated
     */
	void setDisplayLabel(Expression value);

	/**
     * Returns the value of the '<em><b>Inject Widget Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inject Widget Script</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Inject Widget Script</em>' containment reference.
     * @see #setInjectWidgetScript(Expression)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_InjectWidgetScript()
     * @model containment="true"
     * @generated
     */
	Expression getInjectWidgetScript();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getInjectWidgetScript <em>Inject Widget Script</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Inject Widget Script</em>' containment reference.
     * @see #getInjectWidgetScript()
     * @generated
     */
	void setInjectWidgetScript(Expression value);

	/**
     * Returns the value of the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Action</em>' containment reference.
     * @see #setAction(Operation)
     * @see org.bonitasoft.studio.model.form.FormPackage#getWidget_Action()
     * @model containment="true"
     * @generated
     */
	Operation getAction();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.form.Widget#getAction <em>Action</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Action</em>' containment reference.
     * @see #getAction()
     * @generated
     */
	void setAction(Operation value);

} // Widget
