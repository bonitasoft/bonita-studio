/*
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.model.process.diagram.form.providers;

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetModifiersSwitch;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.form.CheckBoxMultipleFormField;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.ComboFormField;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.DurationFormField;
import org.bonitasoft.studio.model.form.DynamicTable;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.FormButton;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.GroupIterator;
import org.bonitasoft.studio.model.form.HiddenWidget;
import org.bonitasoft.studio.model.form.HtmlWidget;
import org.bonitasoft.studio.model.form.IFrameWidget;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.MessageInfo;
import org.bonitasoft.studio.model.form.NextFormButton;
import org.bonitasoft.studio.model.form.PasswordFormField;
import org.bonitasoft.studio.model.form.PreviousFormButton;
import org.bonitasoft.studio.model.form.RadioFormField;
import org.bonitasoft.studio.model.form.RichTextAreaFormField;
import org.bonitasoft.studio.model.form.SelectFormField;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.form.SuggestBox;
import org.bonitasoft.studio.model.form.Table;
import org.bonitasoft.studio.model.form.TextAreaFormField;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.TextInfo;
import org.bonitasoft.studio.model.process.diagram.form.expressions.ProcessAbstractExpression;
import org.bonitasoft.studio.model.process.diagram.form.expressions.ProcessOCLFactory;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	* @generated
	*/
	public void init_CheckBoxSingleFormField_2130(CheckBoxSingleFormField instance) {
		try {
			Object value_0 = name_CheckBoxSingleFormField_2130(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_CheckBoxSingleFormField_2130(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_CheckBoxSingleFormField_2130(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_CheckBoxSingleFormField_2130(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_CheckBoxSingleFormField_2130(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_CheckBoxSingleFormField_2130(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_CheckBoxSingleFormField_2130(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_CheckBoxSingleFormField_2130(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_CheckBoxSingleFormField_2130(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_CheckBoxSingleFormField_2130(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_CheckBoxSingleFormField_2130(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_CheckBoxSingleFormField_2130(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_CheckBoxSingleFormField_2130(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_CheckBoxSingleFormField_2130(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_CheckBoxSingleFormField_2130(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_CheckBoxSingleFormField_2130(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_CheckBoxSingleFormField_2130(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ComboFormField_2114(ComboFormField instance) {
		try {
			Object value_0 = name_ComboFormField_2114(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_ComboFormField_2114(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_ComboFormField_2114(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_ComboFormField_2114(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_ComboFormField_2114(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_ComboFormField_2114(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_ComboFormField_2114(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_ComboFormField_2114(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_ComboFormField_2114(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_ComboFormField_2114(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_ComboFormField_2114(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_ComboFormField_2114(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_ComboFormField_2114(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_ComboFormField_2114(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_ComboFormField_2114(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_ComboFormField_2114(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = defaultExpression_ComboFormField_2114(instance);
			instance.setDefaultExpression((Expression) value_16);
			Object value_17 = defaultExpressionAfterEvent_ComboFormField_2114(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_17);
			Object value_18 = action_ComboFormField_2114(instance);
			instance.setAction((Operation) value_18);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_DateFormField_2115(DateFormField instance) {
		try {
			Object value_0 = name_DateFormField_2115(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_DateFormField_2115(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_DateFormField_2115(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_DateFormField_2115(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_DateFormField_2115(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_DateFormField_2115(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_DateFormField_2115(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_DateFormField_2115(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_DateFormField_2115(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_DateFormField_2115(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_DateFormField_2115(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_DateFormField_2115(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_DateFormField_2115(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_DateFormField_2115(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_DateFormField_2115(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_DateFormField_2115(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_DateFormField_2115(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ListFormField_2116(ListFormField instance) {
		try {
			Object value_0 = name_ListFormField_2116(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_ListFormField_2116(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_ListFormField_2116(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_ListFormField_2116(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_ListFormField_2116(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_ListFormField_2116(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_ListFormField_2116(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_ListFormField_2116(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_ListFormField_2116(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_ListFormField_2116(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_ListFormField_2116(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_ListFormField_2116(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_ListFormField_2116(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_ListFormField_2116(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_ListFormField_2116(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_ListFormField_2116(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = defaultExpression_ListFormField_2116(instance);
			instance.setDefaultExpression((Expression) value_16);
			Object value_17 = defaultExpressionAfterEvent_ListFormField_2116(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_17);
			Object value_18 = action_ListFormField_2116(instance);
			instance.setAction((Operation) value_18);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_PasswordFormField_2117(PasswordFormField instance) {
		try {
			Object value_0 = name_PasswordFormField_2117(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_PasswordFormField_2117(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_PasswordFormField_2117(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_PasswordFormField_2117(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_PasswordFormField_2117(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_PasswordFormField_2117(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_PasswordFormField_2117(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_PasswordFormField_2117(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_PasswordFormField_2117(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_PasswordFormField_2117(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_PasswordFormField_2117(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_PasswordFormField_2117(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_PasswordFormField_2117(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_PasswordFormField_2117(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_PasswordFormField_2117(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_PasswordFormField_2117(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_PasswordFormField_2117(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_RadioFormField_2118(RadioFormField instance) {
		try {
			Object value_0 = name_RadioFormField_2118(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_RadioFormField_2118(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_RadioFormField_2118(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_RadioFormField_2118(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_RadioFormField_2118(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_RadioFormField_2118(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_RadioFormField_2118(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_RadioFormField_2118(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_RadioFormField_2118(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_RadioFormField_2118(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_RadioFormField_2118(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_RadioFormField_2118(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_RadioFormField_2118(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_RadioFormField_2118(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_RadioFormField_2118(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_RadioFormField_2118(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = defaultExpression_RadioFormField_2118(instance);
			instance.setDefaultExpression((Expression) value_16);
			Object value_17 = defaultExpressionAfterEvent_RadioFormField_2118(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_17);
			Object value_18 = action_RadioFormField_2118(instance);
			instance.setAction((Operation) value_18);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_SelectFormField_2119(SelectFormField instance) {
		try {
			Object value_0 = name_SelectFormField_2119(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_SelectFormField_2119(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_SelectFormField_2119(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_SelectFormField_2119(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_SelectFormField_2119(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_SelectFormField_2119(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_SelectFormField_2119(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_SelectFormField_2119(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_SelectFormField_2119(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_SelectFormField_2119(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_SelectFormField_2119(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_SelectFormField_2119(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_SelectFormField_2119(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_SelectFormField_2119(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_SelectFormField_2119(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_SelectFormField_2119(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = defaultExpression_SelectFormField_2119(instance);
			instance.setDefaultExpression((Expression) value_16);
			Object value_17 = defaultExpressionAfterEvent_SelectFormField_2119(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_17);
			Object value_18 = action_SelectFormField_2119(instance);
			instance.setAction((Operation) value_18);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_SuggestBox_2144(SuggestBox instance) {
		try {
			Object value_0 = name_SuggestBox_2144(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_SuggestBox_2144(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_SuggestBox_2144(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_SuggestBox_2144(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_SuggestBox_2144(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_SuggestBox_2144(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_SuggestBox_2144(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_SuggestBox_2144(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_SuggestBox_2144(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_SuggestBox_2144(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_SuggestBox_2144(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_SuggestBox_2144(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_SuggestBox_2144(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_SuggestBox_2144(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_SuggestBox_2144(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_SuggestBox_2144(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = defaultExpression_SuggestBox_2144(instance);
			instance.setDefaultExpression((Expression) value_16);
			Object value_17 = defaultExpressionAfterEvent_SuggestBox_2144(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_17);
			Object value_18 = action_SuggestBox_2144(instance);
			instance.setAction((Operation) value_18);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_TextFormField_2120(TextFormField instance) {
		try {
			Object value_0 = name_TextFormField_2120(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_TextFormField_2120(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_TextFormField_2120(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_TextFormField_2120(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_TextFormField_2120(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_TextFormField_2120(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_TextFormField_2120(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_TextFormField_2120(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_TextFormField_2120(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_TextFormField_2120(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_TextFormField_2120(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_TextFormField_2120(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_TextFormField_2120(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_TextFormField_2120(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_TextFormField_2120(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_TextFormField_2120(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_TextFormField_2120(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_TextAreaFormField_2121(TextAreaFormField instance) {
		try {
			Object value_0 = name_TextAreaFormField_2121(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_TextAreaFormField_2121(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_TextAreaFormField_2121(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_TextAreaFormField_2121(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_TextAreaFormField_2121(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_TextAreaFormField_2121(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_TextAreaFormField_2121(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_TextAreaFormField_2121(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_TextAreaFormField_2121(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_TextAreaFormField_2121(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_TextAreaFormField_2121(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_TextAreaFormField_2121(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_TextAreaFormField_2121(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_TextAreaFormField_2121(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_TextAreaFormField_2121(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_TextAreaFormField_2121(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_TextAreaFormField_2121(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_RichTextAreaFormField_2140(RichTextAreaFormField instance) {
		try {
			Object value_0 = name_RichTextAreaFormField_2140(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_RichTextAreaFormField_2140(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_RichTextAreaFormField_2140(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_RichTextAreaFormField_2140(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_RichTextAreaFormField_2140(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_RichTextAreaFormField_2140(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_RichTextAreaFormField_2140(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_RichTextAreaFormField_2140(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_RichTextAreaFormField_2140(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_RichTextAreaFormField_2140(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_RichTextAreaFormField_2140(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_RichTextAreaFormField_2140(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_RichTextAreaFormField_2140(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_RichTextAreaFormField_2140(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_RichTextAreaFormField_2140(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_RichTextAreaFormField_2140(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_RichTextAreaFormField_2140(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_SubmitFormButton_2126(SubmitFormButton instance) {
		try {
			Object value_0 = name_SubmitFormButton_2126(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_SubmitFormButton_2126(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_SubmitFormButton_2126(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_SubmitFormButton_2126(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_SubmitFormButton_2126(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_SubmitFormButton_2126(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_SubmitFormButton_2126(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_SubmitFormButton_2126(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_SubmitFormButton_2126(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_SubmitFormButton_2126(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_NextFormButton_2127(NextFormButton instance) {
		try {
			Object value_0 = name_NextFormButton_2127(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_NextFormButton_2127(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_NextFormButton_2127(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_NextFormButton_2127(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_NextFormButton_2127(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_NextFormButton_2127(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_NextFormButton_2127(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_NextFormButton_2127(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_NextFormButton_2127(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_NextFormButton_2127(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_PreviousFormButton_2128(PreviousFormButton instance) {
		try {
			Object value_0 = name_PreviousFormButton_2128(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_PreviousFormButton_2128(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_PreviousFormButton_2128(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_PreviousFormButton_2128(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_PreviousFormButton_2128(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_PreviousFormButton_2128(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_PreviousFormButton_2128(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_PreviousFormButton_2128(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_PreviousFormButton_2128(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_PreviousFormButton_2128(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_Group_2125(Group instance) {
		try {
			Object value_0 = name_Group_2125(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_Group_2125(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_Group_2125(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_Group_2125(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_Group_2125(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_Group_2125(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_Group_2125(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_Group_2125(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_Group_2125(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = tooltipForRemove_Group_2125(instance);
			instance.setTooltipForRemove((Expression) value_9);
			Object value_10 = tooltipForAdd_Group_2125(instance);
			instance.setTooltipForAdd((Expression) value_10);
			Object value_11 = displayLabelForRemove_Group_2125(instance);
			instance.setDisplayLabelForRemove((Expression) value_11);
			Object value_12 = displayLabelForAdd_Group_2125(instance);
			instance.setDisplayLabelForAdd((Expression) value_12);
			Object value_13 = minNumberOfDuplication_Group_2125(instance);
			instance.setMinNumberOfDuplication((Expression) value_13);
			Object value_14 = maxNumberOfDuplication_Group_2125(instance);
			instance.setMaxNumberOfDuplication((Expression) value_14);
			Object value_15 = action_Group_2125(instance);
			instance.setAction((Operation) value_15);
			Object value_16 = iterator_Group_2125(instance);
			instance.setIterator((GroupIterator) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_MessageInfo_2131(MessageInfo instance) {
		try {
			Object value_0 = name_MessageInfo_2131(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_MessageInfo_2131(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_MessageInfo_2131(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_MessageInfo_2131(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_MessageInfo_2131(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_MessageInfo_2131(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_MessageInfo_2131(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_MessageInfo_2131(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_MessageInfo_2131(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_MessageInfo_2131(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_TextInfo_2132(TextInfo instance) {
		try {
			Object value_0 = name_TextInfo_2132(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_TextInfo_2132(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_TextInfo_2132(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_TextInfo_2132(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_TextInfo_2132(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_TextInfo_2132(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_TextInfo_2132(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_TextInfo_2132(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_TextInfo_2132(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_TextInfo_2132(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_FileWidget_2133(FileWidget instance) {
		try {
			Object value_0 = inputType_FileWidget_2133(instance);

			value_0 = ProcessAbstractExpression.performCast(value_0, FormPackage.eINSTANCE.getFileWidgetInputType());
			instance.setInputType((FileWidgetInputType) value_0);
			Object value_1 = name_FileWidget_2133(instance);
			instance.setName((java.lang.String) value_1);
			Object value_2 = displayLabel_FileWidget_2133(instance);
			instance.setDisplayLabel((Expression) value_2);
			Object value_3 = injectWidgetScript_FileWidget_2133(instance);
			instance.setInjectWidgetScript((Expression) value_3);
			Object value_4 = exampleMessage_FileWidget_2133(instance);
			instance.setExampleMessage((Expression) value_4);
			Object value_5 = helpMessage_FileWidget_2133(instance);
			instance.setHelpMessage((Expression) value_5);
			Object value_6 = tooltip_FileWidget_2133(instance);
			instance.setTooltip((Expression) value_6);
			Object value_7 = inputExpression_FileWidget_2133(instance);
			instance.setInputExpression((Expression) value_7);
			Object value_8 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_FileWidget_2133(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_8);
			Object value_9 = displayAfterEventDependsOnConditionScript_FileWidget_2133(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_9);
			Object value_10 = afterEventExpression_FileWidget_2133(instance);
			instance.setAfterEventExpression((Expression) value_10);
			Object value_11 = tooltipForRemove_FileWidget_2133(instance);
			instance.setTooltipForRemove((Expression) value_11);
			Object value_12 = tooltipForAdd_FileWidget_2133(instance);
			instance.setTooltipForAdd((Expression) value_12);
			Object value_13 = displayLabelForRemove_FileWidget_2133(instance);
			instance.setDisplayLabelForRemove((Expression) value_13);
			Object value_14 = displayLabelForAdd_FileWidget_2133(instance);
			instance.setDisplayLabelForAdd((Expression) value_14);
			Object value_15 = minNumberOfDuplication_FileWidget_2133(instance);
			instance.setMinNumberOfDuplication((Expression) value_15);
			Object value_16 = maxNumberOfDuplication_FileWidget_2133(instance);
			instance.setMaxNumberOfDuplication((Expression) value_16);
			Object value_17 = outputDocumentListExpression_FileWidget_2133(instance);
			instance.setOutputDocumentListExpression((Expression) value_17);
			Object value_18 = action_FileWidget_2133(instance);
			instance.setAction((Operation) value_18);
			Object value_19 = returnTypeModifier_FileWidget_2133(instance);
			instance.setReturnTypeModifier((java.lang.String) value_19);
			Object value_20 = ProcessOCLFactory.getExpression(0, FormPackage.eINSTANCE.getFileWidget(), null)
					.evaluate(instance);
			instance.setInitialResourcePath((java.lang.String) value_20);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField instance) {
		try {
			Object value_0 = name_CheckBoxMultipleFormField_2134(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_CheckBoxMultipleFormField_2134(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_CheckBoxMultipleFormField_2134(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_CheckBoxMultipleFormField_2134(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_CheckBoxMultipleFormField_2134(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_CheckBoxMultipleFormField_2134(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_CheckBoxMultipleFormField_2134(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_CheckBoxMultipleFormField_2134(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_CheckBoxMultipleFormField_2134(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_CheckBoxMultipleFormField_2134(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_CheckBoxMultipleFormField_2134(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_CheckBoxMultipleFormField_2134(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_CheckBoxMultipleFormField_2134(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_CheckBoxMultipleFormField_2134(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_CheckBoxMultipleFormField_2134(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_CheckBoxMultipleFormField_2134(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = defaultExpression_CheckBoxMultipleFormField_2134(instance);
			instance.setDefaultExpression((Expression) value_16);
			Object value_17 = defaultExpressionAfterEvent_CheckBoxMultipleFormField_2134(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_17);
			Object value_18 = action_CheckBoxMultipleFormField_2134(instance);
			instance.setAction((Operation) value_18);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ImageWidget_2135(ImageWidget instance) {
		try {
			Object value_0 = name_ImageWidget_2135(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_ImageWidget_2135(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_ImageWidget_2135(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_ImageWidget_2135(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_ImageWidget_2135(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_ImageWidget_2135(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_ImageWidget_2135(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_ImageWidget_2135(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_ImageWidget_2135(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = tooltipForRemove_ImageWidget_2135(instance);
			instance.setTooltipForRemove((Expression) value_9);
			Object value_10 = tooltipForAdd_ImageWidget_2135(instance);
			instance.setTooltipForAdd((Expression) value_10);
			Object value_11 = displayLabelForRemove_ImageWidget_2135(instance);
			instance.setDisplayLabelForRemove((Expression) value_11);
			Object value_12 = displayLabelForAdd_ImageWidget_2135(instance);
			instance.setDisplayLabelForAdd((Expression) value_12);
			Object value_13 = minNumberOfDuplication_ImageWidget_2135(instance);
			instance.setMinNumberOfDuplication((Expression) value_13);
			Object value_14 = maxNumberOfDuplication_ImageWidget_2135(instance);
			instance.setMaxNumberOfDuplication((Expression) value_14);
			Object value_15 = imgPath_ImageWidget_2135(instance);
			instance.setImgPath((Expression) value_15);
			Object value_16 = action_ImageWidget_2135(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_HiddenWidget_2136(HiddenWidget instance) {
		try {
			Object value_0 = name_HiddenWidget_2136(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = injectWidgetScript_HiddenWidget_2136(instance);
			instance.setInjectWidgetScript((Expression) value_1);
			Object value_2 = exampleMessage_HiddenWidget_2136(instance);
			instance.setExampleMessage((Expression) value_2);
			Object value_3 = helpMessage_HiddenWidget_2136(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_HiddenWidget_2136(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_HiddenWidget_2136(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_HiddenWidget_2136(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_HiddenWidget_2136(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_HiddenWidget_2136(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = tooltipForRemove_HiddenWidget_2136(instance);
			instance.setTooltipForRemove((Expression) value_9);
			Object value_10 = tooltipForAdd_HiddenWidget_2136(instance);
			instance.setTooltipForAdd((Expression) value_10);
			Object value_11 = displayLabelForRemove_HiddenWidget_2136(instance);
			instance.setDisplayLabelForRemove((Expression) value_11);
			Object value_12 = displayLabelForAdd_HiddenWidget_2136(instance);
			instance.setDisplayLabelForAdd((Expression) value_12);
			Object value_13 = minNumberOfDuplication_HiddenWidget_2136(instance);
			instance.setMinNumberOfDuplication((Expression) value_13);
			Object value_14 = maxNumberOfDuplication_HiddenWidget_2136(instance);
			instance.setMaxNumberOfDuplication((Expression) value_14);
			Object value_15 = action_HiddenWidget_2136(instance);
			instance.setAction((Operation) value_15);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_DurationFormField_2137(DurationFormField instance) {
		try {
			Object value_0 = name_DurationFormField_2137(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_DurationFormField_2137(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_DurationFormField_2137(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_DurationFormField_2137(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_DurationFormField_2137(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_DurationFormField_2137(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_DurationFormField_2137(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_DurationFormField_2137(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_DurationFormField_2137(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_DurationFormField_2137(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_DurationFormField_2137(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_DurationFormField_2137(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_DurationFormField_2137(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_DurationFormField_2137(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_DurationFormField_2137(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_DurationFormField_2137(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_DurationFormField_2137(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_FormButton_2138(FormButton instance) {
		try {
			Object value_0 = name_FormButton_2138(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_FormButton_2138(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_FormButton_2138(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_FormButton_2138(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_FormButton_2138(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_FormButton_2138(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_FormButton_2138(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_FormButton_2138(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_FormButton_2138(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_FormButton_2138(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_Table_2139(Table instance) {
		try {
			Object value_0 = name_Table_2139(instance);
			instance.setName((java.lang.String) value_0);
			instance.setInitializedUsingCells(true);
			Object value_2 = displayLabel_Table_2139(instance);
			instance.setDisplayLabel((Expression) value_2);
			Object value_3 = injectWidgetScript_Table_2139(instance);
			instance.setInjectWidgetScript((Expression) value_3);
			Object value_4 = exampleMessage_Table_2139(instance);
			instance.setExampleMessage((Expression) value_4);
			Object value_5 = helpMessage_Table_2139(instance);
			instance.setHelpMessage((Expression) value_5);
			Object value_6 = tooltip_Table_2139(instance);
			instance.setTooltip((Expression) value_6);
			Object value_7 = inputExpression_Table_2139(instance);
			instance.setInputExpression((Expression) value_7);
			Object value_8 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_Table_2139(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_8);
			Object value_9 = displayAfterEventDependsOnConditionScript_Table_2139(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_9);
			Object value_10 = afterEventExpression_Table_2139(instance);
			instance.setAfterEventExpression((Expression) value_10);
			Object value_11 = tooltipForRemove_Table_2139(instance);
			instance.setTooltipForRemove((Expression) value_11);
			Object value_12 = tooltipForAdd_Table_2139(instance);
			instance.setTooltipForAdd((Expression) value_12);
			Object value_13 = displayLabelForRemove_Table_2139(instance);
			instance.setDisplayLabelForRemove((Expression) value_13);
			Object value_14 = displayLabelForAdd_Table_2139(instance);
			instance.setDisplayLabelForAdd((Expression) value_14);
			Object value_15 = minNumberOfDuplication_Table_2139(instance);
			instance.setMinNumberOfDuplication((Expression) value_15);
			Object value_16 = maxNumberOfDuplication_Table_2139(instance);
			instance.setMaxNumberOfDuplication((Expression) value_16);
			Object value_17 = maxRowForPagination_Table_2139(instance);
			instance.setMaxRowForPagination((Expression) value_17);
			Object value_18 = defaultExpression_Table_2139(instance);
			instance.setDefaultExpression((Expression) value_18);
			Object value_19 = defaultExpressionAfterEvent_Table_2139(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_19);
			Object value_20 = columnForInitialSelectionIndex_Table_2139(instance);
			instance.setColumnForInitialSelectionIndex((Expression) value_20);
			Object value_21 = selectedValues_Table_2139(instance);
			instance.setSelectedValues((Expression) value_21);
			Object value_22 = verticalHeaderExpression_Table_2139(instance);
			instance.setVerticalHeaderExpression((Expression) value_22);
			Object value_23 = tableExpression_Table_2139(instance);
			instance.setTableExpression((TableExpression) value_23);
			Object value_24 = horizontalHeaderExpression_Table_2139(instance);
			instance.setHorizontalHeaderExpression((Expression) value_24);
			Object value_25 = action_Table_2139(instance);
			instance.setAction((Operation) value_25);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_DynamicTable_2141(DynamicTable instance) {
		try {
			Object value_0 = name_DynamicTable_2141(instance);
			instance.setName((java.lang.String) value_0);
			instance.setInitializedUsingCells(true);
			Object value_2 = displayLabel_DynamicTable_2141(instance);
			instance.setDisplayLabel((Expression) value_2);
			Object value_3 = injectWidgetScript_DynamicTable_2141(instance);
			instance.setInjectWidgetScript((Expression) value_3);
			Object value_4 = exampleMessage_DynamicTable_2141(instance);
			instance.setExampleMessage((Expression) value_4);
			Object value_5 = helpMessage_DynamicTable_2141(instance);
			instance.setHelpMessage((Expression) value_5);
			Object value_6 = tooltip_DynamicTable_2141(instance);
			instance.setTooltip((Expression) value_6);
			Object value_7 = inputExpression_DynamicTable_2141(instance);
			instance.setInputExpression((Expression) value_7);
			Object value_8 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_DynamicTable_2141(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_8);
			Object value_9 = displayAfterEventDependsOnConditionScript_DynamicTable_2141(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_9);
			Object value_10 = afterEventExpression_DynamicTable_2141(instance);
			instance.setAfterEventExpression((Expression) value_10);
			Object value_11 = tooltipForRemove_DynamicTable_2141(instance);
			instance.setTooltipForRemove((Expression) value_11);
			Object value_12 = tooltipForAdd_DynamicTable_2141(instance);
			instance.setTooltipForAdd((Expression) value_12);
			Object value_13 = displayLabelForRemove_DynamicTable_2141(instance);
			instance.setDisplayLabelForRemove((Expression) value_13);
			Object value_14 = displayLabelForAdd_DynamicTable_2141(instance);
			instance.setDisplayLabelForAdd((Expression) value_14);
			Object value_15 = minNumberOfDuplication_DynamicTable_2141(instance);
			instance.setMinNumberOfDuplication((Expression) value_15);
			Object value_16 = maxNumberOfDuplication_DynamicTable_2141(instance);
			instance.setMaxNumberOfDuplication((Expression) value_16);
			Object value_17 = verticalHeaderExpression_DynamicTable_2141(instance);
			instance.setVerticalHeaderExpression((Expression) value_17);
			Object value_18 = tableExpression_DynamicTable_2141(instance);
			instance.setTableExpression((TableExpression) value_18);
			Object value_19 = horizontalHeaderExpression_DynamicTable_2141(instance);
			instance.setHorizontalHeaderExpression((Expression) value_19);
			Object value_20 = maxNumberOfColumn_DynamicTable_2141(instance);
			instance.setMaxNumberOfColumn((Expression) value_20);
			Object value_21 = maxNumberOfRow_DynamicTable_2141(instance);
			instance.setMaxNumberOfRow((Expression) value_21);
			Object value_22 = minNumberOfColumn_DynamicTable_2141(instance);
			instance.setMinNumberOfColumn((Expression) value_22);
			Object value_23 = minNumberOfRow_DynamicTable_2141(instance);
			instance.setMinNumberOfRow((Expression) value_23);
			Object value_24 = action_DynamicTable_2141(instance);
			instance.setAction((Operation) value_24);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IFrameWidget_2142(IFrameWidget instance) {
		try {
			Object value_0 = name_IFrameWidget_2142(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_IFrameWidget_2142(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_IFrameWidget_2142(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_IFrameWidget_2142(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_IFrameWidget_2142(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_IFrameWidget_2142(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_IFrameWidget_2142(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_IFrameWidget_2142(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_IFrameWidget_2142(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_IFrameWidget_2142(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_HtmlWidget_2143(HtmlWidget instance) {
		try {
			Object value_0 = name_HtmlWidget_2143(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_HtmlWidget_2143(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_HtmlWidget_2143(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_HtmlWidget_2143(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_HtmlWidget_2143(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_HtmlWidget_2143(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_HtmlWidget_2143(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_HtmlWidget_2143(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_HtmlWidget_2143(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_HtmlWidget_2143(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_PreviousFormButton_3114(PreviousFormButton instance) {
		try {
			Object value_0 = name_PreviousFormButton_3114(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_PreviousFormButton_3114(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_PreviousFormButton_3114(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_PreviousFormButton_3114(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_PreviousFormButton_3114(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_PreviousFormButton_3114(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_PreviousFormButton_3114(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_PreviousFormButton_3114(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_PreviousFormButton_3114(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_PreviousFormButton_3114(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_CheckBoxSingleFormField_3118(CheckBoxSingleFormField instance) {
		try {
			Object value_0 = name_CheckBoxSingleFormField_3118(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_CheckBoxSingleFormField_3118(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_CheckBoxSingleFormField_3118(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_CheckBoxSingleFormField_3118(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_CheckBoxSingleFormField_3118(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_CheckBoxSingleFormField_3118(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_CheckBoxSingleFormField_3118(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_CheckBoxSingleFormField_3118(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_CheckBoxSingleFormField_3118(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_CheckBoxSingleFormField_3118(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_CheckBoxSingleFormField_3118(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_CheckBoxSingleFormField_3118(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_CheckBoxSingleFormField_3118(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_CheckBoxSingleFormField_3118(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_CheckBoxSingleFormField_3118(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_CheckBoxSingleFormField_3118(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_CheckBoxSingleFormField_3118(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ComboFormField_3103(ComboFormField instance) {
		try {
			Object value_0 = name_ComboFormField_3103(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_ComboFormField_3103(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_ComboFormField_3103(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_ComboFormField_3103(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_ComboFormField_3103(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_ComboFormField_3103(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_ComboFormField_3103(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_ComboFormField_3103(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_ComboFormField_3103(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_ComboFormField_3103(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_ComboFormField_3103(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_ComboFormField_3103(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_ComboFormField_3103(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_ComboFormField_3103(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_ComboFormField_3103(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_ComboFormField_3103(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = defaultExpression_ComboFormField_3103(instance);
			instance.setDefaultExpression((Expression) value_16);
			Object value_17 = defaultExpressionAfterEvent_ComboFormField_3103(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_17);
			Object value_18 = action_ComboFormField_3103(instance);
			instance.setAction((Operation) value_18);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_NextFormButton_3115(NextFormButton instance) {
		try {
			Object value_0 = name_NextFormButton_3115(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_NextFormButton_3115(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_NextFormButton_3115(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_NextFormButton_3115(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_NextFormButton_3115(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_NextFormButton_3115(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_NextFormButton_3115(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_NextFormButton_3115(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_NextFormButton_3115(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_NextFormButton_3115(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_DateFormField_3105(DateFormField instance) {
		try {
			Object value_0 = name_DateFormField_3105(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_DateFormField_3105(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_DateFormField_3105(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_DateFormField_3105(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_DateFormField_3105(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_DateFormField_3105(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_DateFormField_3105(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_DateFormField_3105(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_DateFormField_3105(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_DateFormField_3105(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_DateFormField_3105(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_DateFormField_3105(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_DateFormField_3105(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_DateFormField_3105(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_DateFormField_3105(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_DateFormField_3105(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_DateFormField_3105(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_Group_3106(Group instance) {
		try {
			Object value_0 = name_Group_3106(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_Group_3106(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_Group_3106(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_Group_3106(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_Group_3106(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_Group_3106(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_Group_3106(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_Group_3106(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_Group_3106(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = tooltipForRemove_Group_3106(instance);
			instance.setTooltipForRemove((Expression) value_9);
			Object value_10 = tooltipForAdd_Group_3106(instance);
			instance.setTooltipForAdd((Expression) value_10);
			Object value_11 = displayLabelForRemove_Group_3106(instance);
			instance.setDisplayLabelForRemove((Expression) value_11);
			Object value_12 = displayLabelForAdd_Group_3106(instance);
			instance.setDisplayLabelForAdd((Expression) value_12);
			Object value_13 = minNumberOfDuplication_Group_3106(instance);
			instance.setMinNumberOfDuplication((Expression) value_13);
			Object value_14 = maxNumberOfDuplication_Group_3106(instance);
			instance.setMaxNumberOfDuplication((Expression) value_14);
			Object value_15 = action_Group_3106(instance);
			instance.setAction((Operation) value_15);
			Object value_16 = iterator_Group_3106(instance);
			instance.setIterator((GroupIterator) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ListFormField_3107(ListFormField instance) {
		try {
			Object value_0 = name_ListFormField_3107(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_ListFormField_3107(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_ListFormField_3107(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_ListFormField_3107(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_ListFormField_3107(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_ListFormField_3107(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_ListFormField_3107(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_ListFormField_3107(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_ListFormField_3107(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_ListFormField_3107(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_ListFormField_3107(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_ListFormField_3107(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_ListFormField_3107(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_ListFormField_3107(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_ListFormField_3107(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_ListFormField_3107(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = defaultExpression_ListFormField_3107(instance);
			instance.setDefaultExpression((Expression) value_16);
			Object value_17 = defaultExpressionAfterEvent_ListFormField_3107(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_17);
			Object value_18 = action_ListFormField_3107(instance);
			instance.setAction((Operation) value_18);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_SubmitFormButton_3116(SubmitFormButton instance) {
		try {
			Object value_0 = name_SubmitFormButton_3116(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_SubmitFormButton_3116(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_SubmitFormButton_3116(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_SubmitFormButton_3116(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_SubmitFormButton_3116(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_SubmitFormButton_3116(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_SubmitFormButton_3116(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_SubmitFormButton_3116(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_SubmitFormButton_3116(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_SubmitFormButton_3116(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_PasswordFormField_3109(PasswordFormField instance) {
		try {
			Object value_0 = name_PasswordFormField_3109(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_PasswordFormField_3109(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_PasswordFormField_3109(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_PasswordFormField_3109(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_PasswordFormField_3109(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_PasswordFormField_3109(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_PasswordFormField_3109(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_PasswordFormField_3109(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_PasswordFormField_3109(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_PasswordFormField_3109(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_PasswordFormField_3109(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_PasswordFormField_3109(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_PasswordFormField_3109(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_PasswordFormField_3109(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_PasswordFormField_3109(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_PasswordFormField_3109(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_PasswordFormField_3109(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_RadioFormField_3110(RadioFormField instance) {
		try {
			Object value_0 = name_RadioFormField_3110(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_RadioFormField_3110(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_RadioFormField_3110(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_RadioFormField_3110(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_RadioFormField_3110(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_RadioFormField_3110(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_RadioFormField_3110(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_RadioFormField_3110(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_RadioFormField_3110(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_RadioFormField_3110(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_RadioFormField_3110(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_RadioFormField_3110(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_RadioFormField_3110(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_RadioFormField_3110(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_RadioFormField_3110(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_RadioFormField_3110(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = defaultExpression_RadioFormField_3110(instance);
			instance.setDefaultExpression((Expression) value_16);
			Object value_17 = defaultExpressionAfterEvent_RadioFormField_3110(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_17);
			Object value_18 = action_RadioFormField_3110(instance);
			instance.setAction((Operation) value_18);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_SelectFormField_3111(SelectFormField instance) {
		try {
			Object value_0 = name_SelectFormField_3111(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_SelectFormField_3111(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_SelectFormField_3111(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_SelectFormField_3111(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_SelectFormField_3111(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_SelectFormField_3111(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_SelectFormField_3111(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_SelectFormField_3111(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_SelectFormField_3111(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_SelectFormField_3111(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_SelectFormField_3111(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_SelectFormField_3111(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_SelectFormField_3111(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_SelectFormField_3111(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_SelectFormField_3111(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_SelectFormField_3111(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = defaultExpression_SelectFormField_3111(instance);
			instance.setDefaultExpression((Expression) value_16);
			Object value_17 = defaultExpressionAfterEvent_SelectFormField_3111(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_17);
			Object value_18 = action_SelectFormField_3111(instance);
			instance.setAction((Operation) value_18);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_TextFormField_3112(TextFormField instance) {
		try {
			Object value_0 = name_TextFormField_3112(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_TextFormField_3112(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_TextFormField_3112(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_TextFormField_3112(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_TextFormField_3112(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_TextFormField_3112(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_TextFormField_3112(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_TextFormField_3112(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_TextFormField_3112(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_TextFormField_3112(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_TextFormField_3112(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_TextFormField_3112(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_TextFormField_3112(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_TextFormField_3112(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_TextFormField_3112(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_TextFormField_3112(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_TextFormField_3112(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_TextAreaFormField_3113(TextAreaFormField instance) {
		try {
			Object value_0 = name_TextAreaFormField_3113(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_TextAreaFormField_3113(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_TextAreaFormField_3113(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_TextAreaFormField_3113(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_TextAreaFormField_3113(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_TextAreaFormField_3113(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_TextAreaFormField_3113(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_TextAreaFormField_3113(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_TextAreaFormField_3113(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_TextAreaFormField_3113(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_TextAreaFormField_3113(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_TextAreaFormField_3113(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_TextAreaFormField_3113(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_TextAreaFormField_3113(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_TextAreaFormField_3113(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_TextAreaFormField_3113(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_TextAreaFormField_3113(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_RichTextAreaFormField_3128(RichTextAreaFormField instance) {
		try {
			Object value_0 = name_RichTextAreaFormField_3128(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_RichTextAreaFormField_3128(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_RichTextAreaFormField_3128(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_RichTextAreaFormField_3128(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_RichTextAreaFormField_3128(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_RichTextAreaFormField_3128(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_RichTextAreaFormField_3128(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_RichTextAreaFormField_3128(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_RichTextAreaFormField_3128(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_RichTextAreaFormField_3128(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_RichTextAreaFormField_3128(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_RichTextAreaFormField_3128(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_RichTextAreaFormField_3128(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_RichTextAreaFormField_3128(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_RichTextAreaFormField_3128(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_RichTextAreaFormField_3128(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_RichTextAreaFormField_3128(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_FileWidget_3119(FileWidget instance) {
		try {
			Object value_0 = inputType_FileWidget_3119(instance);

			value_0 = ProcessAbstractExpression.performCast(value_0, FormPackage.eINSTANCE.getFileWidgetInputType());
			instance.setInputType((FileWidgetInputType) value_0);
			Object value_1 = name_FileWidget_3119(instance);
			instance.setName((java.lang.String) value_1);
			Object value_2 = displayLabel_FileWidget_3119(instance);
			instance.setDisplayLabel((Expression) value_2);
			Object value_3 = injectWidgetScript_FileWidget_3119(instance);
			instance.setInjectWidgetScript((Expression) value_3);
			Object value_4 = exampleMessage_FileWidget_3119(instance);
			instance.setExampleMessage((Expression) value_4);
			Object value_5 = helpMessage_FileWidget_3119(instance);
			instance.setHelpMessage((Expression) value_5);
			Object value_6 = tooltip_FileWidget_3119(instance);
			instance.setTooltip((Expression) value_6);
			Object value_7 = inputExpression_FileWidget_3119(instance);
			instance.setInputExpression((Expression) value_7);
			Object value_8 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_FileWidget_3119(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_8);
			Object value_9 = displayAfterEventDependsOnConditionScript_FileWidget_3119(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_9);
			Object value_10 = afterEventExpression_FileWidget_3119(instance);
			instance.setAfterEventExpression((Expression) value_10);
			Object value_11 = tooltipForRemove_FileWidget_3119(instance);
			instance.setTooltipForRemove((Expression) value_11);
			Object value_12 = tooltipForAdd_FileWidget_3119(instance);
			instance.setTooltipForAdd((Expression) value_12);
			Object value_13 = displayLabelForRemove_FileWidget_3119(instance);
			instance.setDisplayLabelForRemove((Expression) value_13);
			Object value_14 = displayLabelForAdd_FileWidget_3119(instance);
			instance.setDisplayLabelForAdd((Expression) value_14);
			Object value_15 = minNumberOfDuplication_FileWidget_3119(instance);
			instance.setMinNumberOfDuplication((Expression) value_15);
			Object value_16 = maxNumberOfDuplication_FileWidget_3119(instance);
			instance.setMaxNumberOfDuplication((Expression) value_16);
			Object value_17 = outputDocumentListExpression_FileWidget_3119(instance);
			instance.setOutputDocumentListExpression((Expression) value_17);
			Object value_18 = action_FileWidget_3119(instance);
			instance.setAction((Operation) value_18);
			Object value_19 = returnTypeModifier_FileWidget_3119(instance);
			instance.setReturnTypeModifier((java.lang.String) value_19);
			Object value_20 = ProcessOCLFactory.getExpression(0, FormPackage.eINSTANCE.getFileWidget(), null)
					.evaluate(instance);
			instance.setInitialResourcePath((java.lang.String) value_20);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField instance) {
		try {
			Object value_0 = name_CheckBoxMultipleFormField_3120(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_CheckBoxMultipleFormField_3120(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_CheckBoxMultipleFormField_3120(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_CheckBoxMultipleFormField_3120(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_CheckBoxMultipleFormField_3120(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_CheckBoxMultipleFormField_3120(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_CheckBoxMultipleFormField_3120(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_CheckBoxMultipleFormField_3120(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_CheckBoxMultipleFormField_3120(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_CheckBoxMultipleFormField_3120(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_CheckBoxMultipleFormField_3120(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_CheckBoxMultipleFormField_3120(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_CheckBoxMultipleFormField_3120(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_CheckBoxMultipleFormField_3120(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_CheckBoxMultipleFormField_3120(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_CheckBoxMultipleFormField_3120(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = defaultExpression_CheckBoxMultipleFormField_3120(instance);
			instance.setDefaultExpression((Expression) value_16);
			Object value_17 = defaultExpressionAfterEvent_CheckBoxMultipleFormField_3120(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_17);
			Object value_18 = action_CheckBoxMultipleFormField_3120(instance);
			instance.setAction((Operation) value_18);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_DurationFormField_3121(DurationFormField instance) {
		try {
			Object value_0 = name_DurationFormField_3121(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_DurationFormField_3121(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_DurationFormField_3121(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_DurationFormField_3121(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_DurationFormField_3121(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_DurationFormField_3121(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_DurationFormField_3121(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_DurationFormField_3121(
					instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_DurationFormField_3121(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_DurationFormField_3121(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_DurationFormField_3121(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_DurationFormField_3121(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_DurationFormField_3121(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_DurationFormField_3121(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_DurationFormField_3121(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_DurationFormField_3121(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = action_DurationFormField_3121(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_HiddenWidget_3122(HiddenWidget instance) {
		try {
			Object value_0 = name_HiddenWidget_3122(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = injectWidgetScript_HiddenWidget_3122(instance);
			instance.setInjectWidgetScript((Expression) value_1);
			Object value_2 = exampleMessage_HiddenWidget_3122(instance);
			instance.setExampleMessage((Expression) value_2);
			Object value_3 = helpMessage_HiddenWidget_3122(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_HiddenWidget_3122(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_HiddenWidget_3122(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_HiddenWidget_3122(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_HiddenWidget_3122(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_HiddenWidget_3122(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = tooltipForRemove_HiddenWidget_3122(instance);
			instance.setTooltipForRemove((Expression) value_9);
			Object value_10 = tooltipForAdd_HiddenWidget_3122(instance);
			instance.setTooltipForAdd((Expression) value_10);
			Object value_11 = displayLabelForRemove_HiddenWidget_3122(instance);
			instance.setDisplayLabelForRemove((Expression) value_11);
			Object value_12 = displayLabelForAdd_HiddenWidget_3122(instance);
			instance.setDisplayLabelForAdd((Expression) value_12);
			Object value_13 = minNumberOfDuplication_HiddenWidget_3122(instance);
			instance.setMinNumberOfDuplication((Expression) value_13);
			Object value_14 = maxNumberOfDuplication_HiddenWidget_3122(instance);
			instance.setMaxNumberOfDuplication((Expression) value_14);
			Object value_15 = action_HiddenWidget_3122(instance);
			instance.setAction((Operation) value_15);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ImageWidget_3123(ImageWidget instance) {
		try {
			Object value_0 = name_ImageWidget_3123(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_ImageWidget_3123(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_ImageWidget_3123(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_ImageWidget_3123(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_ImageWidget_3123(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_ImageWidget_3123(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_ImageWidget_3123(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_ImageWidget_3123(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_ImageWidget_3123(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = tooltipForRemove_ImageWidget_3123(instance);
			instance.setTooltipForRemove((Expression) value_9);
			Object value_10 = tooltipForAdd_ImageWidget_3123(instance);
			instance.setTooltipForAdd((Expression) value_10);
			Object value_11 = displayLabelForRemove_ImageWidget_3123(instance);
			instance.setDisplayLabelForRemove((Expression) value_11);
			Object value_12 = displayLabelForAdd_ImageWidget_3123(instance);
			instance.setDisplayLabelForAdd((Expression) value_12);
			Object value_13 = minNumberOfDuplication_ImageWidget_3123(instance);
			instance.setMinNumberOfDuplication((Expression) value_13);
			Object value_14 = maxNumberOfDuplication_ImageWidget_3123(instance);
			instance.setMaxNumberOfDuplication((Expression) value_14);
			Object value_15 = imgPath_ImageWidget_3123(instance);
			instance.setImgPath((Expression) value_15);
			Object value_16 = action_ImageWidget_3123(instance);
			instance.setAction((Operation) value_16);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_MessageInfo_3124(MessageInfo instance) {
		try {
			Object value_0 = name_MessageInfo_3124(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_MessageInfo_3124(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_MessageInfo_3124(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_MessageInfo_3124(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_MessageInfo_3124(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_MessageInfo_3124(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_MessageInfo_3124(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_MessageInfo_3124(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_MessageInfo_3124(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_MessageInfo_3124(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_TextInfo_3125(TextInfo instance) {
		try {
			Object value_0 = name_TextInfo_3125(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_TextInfo_3125(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_TextInfo_3125(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_TextInfo_3125(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_TextInfo_3125(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_TextInfo_3125(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_TextInfo_3125(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_TextInfo_3125(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_TextInfo_3125(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_TextInfo_3125(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_FormButton_3126(FormButton instance) {
		try {
			Object value_0 = name_FormButton_3126(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_FormButton_3126(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_FormButton_3126(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_FormButton_3126(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_FormButton_3126(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_FormButton_3126(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_FormButton_3126(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_FormButton_3126(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_FormButton_3126(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_FormButton_3126(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_Table_3127(Table instance) {
		try {
			Object value_0 = name_Table_3127(instance);
			instance.setName((java.lang.String) value_0);
			instance.setInitializedUsingCells(true);
			Object value_2 = displayLabel_Table_3127(instance);
			instance.setDisplayLabel((Expression) value_2);
			Object value_3 = injectWidgetScript_Table_3127(instance);
			instance.setInjectWidgetScript((Expression) value_3);
			Object value_4 = exampleMessage_Table_3127(instance);
			instance.setExampleMessage((Expression) value_4);
			Object value_5 = helpMessage_Table_3127(instance);
			instance.setHelpMessage((Expression) value_5);
			Object value_6 = tooltip_Table_3127(instance);
			instance.setTooltip((Expression) value_6);
			Object value_7 = inputExpression_Table_3127(instance);
			instance.setInputExpression((Expression) value_7);
			Object value_8 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_Table_3127(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_8);
			Object value_9 = displayAfterEventDependsOnConditionScript_Table_3127(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_9);
			Object value_10 = afterEventExpression_Table_3127(instance);
			instance.setAfterEventExpression((Expression) value_10);
			Object value_11 = tooltipForRemove_Table_3127(instance);
			instance.setTooltipForRemove((Expression) value_11);
			Object value_12 = tooltipForAdd_Table_3127(instance);
			instance.setTooltipForAdd((Expression) value_12);
			Object value_13 = displayLabelForRemove_Table_3127(instance);
			instance.setDisplayLabelForRemove((Expression) value_13);
			Object value_14 = displayLabelForAdd_Table_3127(instance);
			instance.setDisplayLabelForAdd((Expression) value_14);
			Object value_15 = minNumberOfDuplication_Table_3127(instance);
			instance.setMinNumberOfDuplication((Expression) value_15);
			Object value_16 = maxNumberOfDuplication_Table_3127(instance);
			instance.setMaxNumberOfDuplication((Expression) value_16);
			Object value_17 = maxRowForPagination_Table_3127(instance);
			instance.setMaxRowForPagination((Expression) value_17);
			Object value_18 = defaultExpression_Table_3127(instance);
			instance.setDefaultExpression((Expression) value_18);
			Object value_19 = defaultExpressionAfterEvent_Table_3127(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_19);
			Object value_20 = columnForInitialSelectionIndex_Table_3127(instance);
			instance.setColumnForInitialSelectionIndex((Expression) value_20);
			Object value_21 = selectedValues_Table_3127(instance);
			instance.setSelectedValues((Expression) value_21);
			Object value_22 = verticalHeaderExpression_Table_3127(instance);
			instance.setVerticalHeaderExpression((Expression) value_22);
			Object value_23 = tableExpression_Table_3127(instance);
			instance.setTableExpression((TableExpression) value_23);
			Object value_24 = horizontalHeaderExpression_Table_3127(instance);
			instance.setHorizontalHeaderExpression((Expression) value_24);
			Object value_25 = action_Table_3127(instance);
			instance.setAction((Operation) value_25);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_DynamicTable_3129(DynamicTable instance) {
		try {
			Object value_0 = name_DynamicTable_3129(instance);
			instance.setName((java.lang.String) value_0);
			instance.setInitializedUsingCells(true);
			Object value_2 = displayLabel_DynamicTable_3129(instance);
			instance.setDisplayLabel((Expression) value_2);
			Object value_3 = injectWidgetScript_DynamicTable_3129(instance);
			instance.setInjectWidgetScript((Expression) value_3);
			Object value_4 = exampleMessage_DynamicTable_3129(instance);
			instance.setExampleMessage((Expression) value_4);
			Object value_5 = helpMessage_DynamicTable_3129(instance);
			instance.setHelpMessage((Expression) value_5);
			Object value_6 = tooltip_DynamicTable_3129(instance);
			instance.setTooltip((Expression) value_6);
			Object value_7 = inputExpression_DynamicTable_3129(instance);
			instance.setInputExpression((Expression) value_7);
			Object value_8 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_DynamicTable_3129(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_8);
			Object value_9 = displayAfterEventDependsOnConditionScript_DynamicTable_3129(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_9);
			Object value_10 = afterEventExpression_DynamicTable_3129(instance);
			instance.setAfterEventExpression((Expression) value_10);
			Object value_11 = tooltipForRemove_DynamicTable_3129(instance);
			instance.setTooltipForRemove((Expression) value_11);
			Object value_12 = tooltipForAdd_DynamicTable_3129(instance);
			instance.setTooltipForAdd((Expression) value_12);
			Object value_13 = displayLabelForRemove_DynamicTable_3129(instance);
			instance.setDisplayLabelForRemove((Expression) value_13);
			Object value_14 = displayLabelForAdd_DynamicTable_3129(instance);
			instance.setDisplayLabelForAdd((Expression) value_14);
			Object value_15 = minNumberOfDuplication_DynamicTable_3129(instance);
			instance.setMinNumberOfDuplication((Expression) value_15);
			Object value_16 = maxNumberOfDuplication_DynamicTable_3129(instance);
			instance.setMaxNumberOfDuplication((Expression) value_16);
			Object value_17 = verticalHeaderExpression_DynamicTable_3129(instance);
			instance.setVerticalHeaderExpression((Expression) value_17);
			Object value_18 = tableExpression_DynamicTable_3129(instance);
			instance.setTableExpression((TableExpression) value_18);
			Object value_19 = horizontalHeaderExpression_DynamicTable_3129(instance);
			instance.setHorizontalHeaderExpression((Expression) value_19);
			Object value_20 = maxNumberOfColumn_DynamicTable_3129(instance);
			instance.setMaxNumberOfColumn((Expression) value_20);
			Object value_21 = maxNumberOfRow_DynamicTable_3129(instance);
			instance.setMaxNumberOfRow((Expression) value_21);
			Object value_22 = minNumberOfColumn_DynamicTable_3129(instance);
			instance.setMinNumberOfColumn((Expression) value_22);
			Object value_23 = minNumberOfRow_DynamicTable_3129(instance);
			instance.setMinNumberOfRow((Expression) value_23);
			Object value_24 = action_DynamicTable_3129(instance);
			instance.setAction((Operation) value_24);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IFrameWidget_3130(IFrameWidget instance) {
		try {
			Object value_0 = name_IFrameWidget_3130(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_IFrameWidget_3130(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_IFrameWidget_3130(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_IFrameWidget_3130(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_IFrameWidget_3130(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_IFrameWidget_3130(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_IFrameWidget_3130(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_IFrameWidget_3130(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_IFrameWidget_3130(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_IFrameWidget_3130(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_HtmlWidget_3131(HtmlWidget instance) {
		try {
			Object value_0 = name_HtmlWidget_3131(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_HtmlWidget_3131(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_HtmlWidget_3131(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = helpMessage_HtmlWidget_3131(instance);
			instance.setHelpMessage((Expression) value_3);
			Object value_4 = tooltip_HtmlWidget_3131(instance);
			instance.setTooltip((Expression) value_4);
			Object value_5 = inputExpression_HtmlWidget_3131(instance);
			instance.setInputExpression((Expression) value_5);
			Object value_6 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_HtmlWidget_3131(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_6);
			Object value_7 = displayAfterEventDependsOnConditionScript_HtmlWidget_3131(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_7);
			Object value_8 = afterEventExpression_HtmlWidget_3131(instance);
			instance.setAfterEventExpression((Expression) value_8);
			Object value_9 = action_HtmlWidget_3131(instance);
			instance.setAction((Operation) value_9);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_SuggestBox_3132(SuggestBox instance) {
		try {
			Object value_0 = name_SuggestBox_3132(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = displayLabel_SuggestBox_3132(instance);
			instance.setDisplayLabel((Expression) value_1);
			Object value_2 = injectWidgetScript_SuggestBox_3132(instance);
			instance.setInjectWidgetScript((Expression) value_2);
			Object value_3 = exampleMessage_SuggestBox_3132(instance);
			instance.setExampleMessage((Expression) value_3);
			Object value_4 = helpMessage_SuggestBox_3132(instance);
			instance.setHelpMessage((Expression) value_4);
			Object value_5 = tooltip_SuggestBox_3132(instance);
			instance.setTooltip((Expression) value_5);
			Object value_6 = inputExpression_SuggestBox_3132(instance);
			instance.setInputExpression((Expression) value_6);
			Object value_7 = displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_SuggestBox_3132(instance);
			instance.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition((Expression) value_7);
			Object value_8 = displayAfterEventDependsOnConditionScript_SuggestBox_3132(instance);
			instance.setDisplayAfterEventDependsOnConditionScript((Expression) value_8);
			Object value_9 = afterEventExpression_SuggestBox_3132(instance);
			instance.setAfterEventExpression((Expression) value_9);
			Object value_10 = tooltipForRemove_SuggestBox_3132(instance);
			instance.setTooltipForRemove((Expression) value_10);
			Object value_11 = tooltipForAdd_SuggestBox_3132(instance);
			instance.setTooltipForAdd((Expression) value_11);
			Object value_12 = displayLabelForRemove_SuggestBox_3132(instance);
			instance.setDisplayLabelForRemove((Expression) value_12);
			Object value_13 = displayLabelForAdd_SuggestBox_3132(instance);
			instance.setDisplayLabelForAdd((Expression) value_13);
			Object value_14 = minNumberOfDuplication_SuggestBox_3132(instance);
			instance.setMinNumberOfDuplication((Expression) value_14);
			Object value_15 = maxNumberOfDuplication_SuggestBox_3132(instance);
			instance.setMaxNumberOfDuplication((Expression) value_15);
			Object value_16 = defaultExpression_SuggestBox_3132(instance);
			instance.setDefaultExpression((Expression) value_16);
			Object value_17 = defaultExpressionAfterEvent_SuggestBox_3132(instance);
			instance.setDefaultExpressionAfterEvent((Expression) value_17);
			Object value_18 = action_SuggestBox_3132(instance);
			instance.setAction((Operation) value_18);
		} catch (RuntimeException e) {
			FormDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	private java.lang.String name_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_CheckBoxSingleFormField_2130(
			CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_CheckBoxSingleFormField_2130(
			CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_CheckBoxSingleFormField_2130(CheckBoxSingleFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_ComboFormField_2114(ComboFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_ComboFormField_2114(ComboFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_ComboFormField_2114(
			ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setType(ExpressionConstants.SCRIPT_TYPE);
		exp.setContent("[]");
		exp.setReturnType(List.class.getName());

		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_ComboFormField_2114(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_ComboFormField_2114(ComboFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Operation action_ComboFormField_2114(ComboFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_DateFormField_2115(DateFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_DateFormField_2115(
			DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_DateFormField_2115(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_DateFormField_2115(DateFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_ListFormField_2116(ListFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_ListFormField_2116(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_ListFormField_2116(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_ListFormField_2116(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_ListFormField_2116(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_ListFormField_2116(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_ListFormField_2116(ListFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_ListFormField_2116(
			ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_ListFormField_2116(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_ListFormField_2116(ListFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_ListFormField_2116(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_ListFormField_2116(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_ListFormField_2116(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_ListFormField_2116(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_ListFormField_2116(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_ListFormField_2116(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_ListFormField_2116(ListFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_ListFormField_2116(ListFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Operation action_ListFormField_2116(ListFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_PasswordFormField_2117(PasswordFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_PasswordFormField_2117(
			PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_PasswordFormField_2117(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_PasswordFormField_2117(PasswordFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_RadioFormField_2118(RadioFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_RadioFormField_2118(RadioFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_RadioFormField_2118(
			RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_RadioFormField_2118(RadioFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_RadioFormField_2118(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_RadioFormField_2118(RadioFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_SelectFormField_2119(SelectFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_SelectFormField_2119(SelectFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_SelectFormField_2119(
			SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_SelectFormField_2119(SelectFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_SelectFormField_2119(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_SelectFormField_2119(SelectFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_SuggestBox_2144(SuggestBox self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_SuggestBox_2144(SuggestBox self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_SuggestBox_2144(SuggestBox self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_SuggestBox_2144(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_SuggestBox_2144(SuggestBox self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_TextFormField_2120(TextFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_TextFormField_2120(
			TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_TextFormField_2120(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_TextFormField_2120(TextFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_TextAreaFormField_2121(TextAreaFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_TextAreaFormField_2121(
			TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_TextAreaFormField_2121(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_TextAreaFormField_2121(TextAreaFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_RichTextAreaFormField_2140(
			RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_RichTextAreaFormField_2140(
			RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_RichTextAreaFormField_2140(RichTextAreaFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_SubmitFormButton_2126(SubmitFormButton self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_SubmitFormButton_2126(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_SubmitFormButton_2126(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_SubmitFormButton_2126(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_SubmitFormButton_2126(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_SubmitFormButton_2126(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_SubmitFormButton_2126(
			SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_SubmitFormButton_2126(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_SubmitFormButton_2126(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_SubmitFormButton_2126(SubmitFormButton self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_NextFormButton_2127(NextFormButton self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_NextFormButton_2127(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_NextFormButton_2127(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_NextFormButton_2127(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_NextFormButton_2127(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_NextFormButton_2127(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_NextFormButton_2127(
			NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_NextFormButton_2127(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_NextFormButton_2127(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_NextFormButton_2127(NextFormButton self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_PreviousFormButton_2128(PreviousFormButton self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_PreviousFormButton_2128(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_PreviousFormButton_2128(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_PreviousFormButton_2128(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_PreviousFormButton_2128(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_PreviousFormButton_2128(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_PreviousFormButton_2128(
			PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_PreviousFormButton_2128(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_PreviousFormButton_2128(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_PreviousFormButton_2128(PreviousFormButton self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_Group_2125(Group self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_Group_2125(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_Group_2125(Group self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private GroupIterator iterator_Group_2125(Group self) {
		GroupIterator iterator = FormFactory.eINSTANCE.createGroupIterator();
		iterator.setName("");
		iterator.setClassName(Object.class.getName());
		return iterator;
	}

	/**
	* @generated
	*/
	private java.lang.String name_MessageInfo_2131(MessageInfo self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_MessageInfo_2131(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_MessageInfo_2131(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_MessageInfo_2131(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_MessageInfo_2131(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_MessageInfo_2131(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_MessageInfo_2131(
			MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_MessageInfo_2131(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_MessageInfo_2131(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_MessageInfo_2131(MessageInfo self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_TextInfo_2132(TextInfo self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_TextInfo_2132(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_TextInfo_2132(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_TextInfo_2132(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_TextInfo_2132(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_TextInfo_2132(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_TextInfo_2132(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_TextInfo_2132(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_TextInfo_2132(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_TextInfo_2132(TextInfo self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private FileWidgetInputType inputType_FileWidget_2133(FileWidget self) {
		return ModelHelper.getDefaultFileWidgetInputType(self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_FileWidget_2133(FileWidget self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression outputDocumentListExpression_FileWidget_2133(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_FileWidget_2133(FileWidget self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String returnTypeModifier_FileWidget_2133(FileWidget self) {
		return WidgetModifiersSwitch.ENGINE_DOCUMENT_QUALIFIED_NAME;
	}

	/**
	* @generated
	*/
	private java.lang.String name_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_CheckBoxMultipleFormField_2134(
			CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_CheckBoxMultipleFormField_2134(
			CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Operation action_CheckBoxMultipleFormField_2134(CheckBoxMultipleFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_ImageWidget_2135(ImageWidget self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_ImageWidget_2135(
			ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression imgPath_ImageWidget_2135(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_ImageWidget_2135(ImageWidget self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_HiddenWidget_2136(HiddenWidget self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_HiddenWidget_2136(
			HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_HiddenWidget_2136(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_HiddenWidget_2136(HiddenWidget self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_DurationFormField_2137(DurationFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Long.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_DurationFormField_2137(
			DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_DurationFormField_2137(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_DurationFormField_2137(DurationFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_FormButton_2138(FormButton self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_FormButton_2138(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_FormButton_2138(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_FormButton_2138(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_FormButton_2138(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_FormButton_2138(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_FormButton_2138(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_FormButton_2138(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_FormButton_2138(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_FormButton_2138(FormButton self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_Table_2139(Table self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(List.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Collection.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxRowForPagination_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Collection.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Expression columnForInitialSelectionIndex_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression selectedValues_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression verticalHeaderExpression_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private TableExpression tableExpression_Table_2139(Table self) {
		TableExpression exp = ExpressionFactory.eINSTANCE.createTableExpression();
		return exp;
	}

	/**
	* @generated
	*/
	private Expression horizontalHeaderExpression_Table_2139(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_Table_2139(Table self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_DynamicTable_2141(DynamicTable self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(List.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_DynamicTable_2141(
			DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Collection.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression verticalHeaderExpression_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private TableExpression tableExpression_DynamicTable_2141(DynamicTable self) {
		TableExpression exp = ExpressionFactory.eINSTANCE.createTableExpression();
		return exp;
	}

	/**
	* @generated
	*/
	private Expression horizontalHeaderExpression_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfColumn_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfRow_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfColumn_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfRow_DynamicTable_2141(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_DynamicTable_2141(DynamicTable self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_IFrameWidget_2142(IFrameWidget self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_IFrameWidget_2142(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_IFrameWidget_2142(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_IFrameWidget_2142(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_IFrameWidget_2142(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_IFrameWidget_2142(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_IFrameWidget_2142(
			IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_IFrameWidget_2142(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_IFrameWidget_2142(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_IFrameWidget_2142(IFrameWidget self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_HtmlWidget_2143(HtmlWidget self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_HtmlWidget_2143(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_HtmlWidget_2143(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_HtmlWidget_2143(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_HtmlWidget_2143(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_HtmlWidget_2143(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_HtmlWidget_2143(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_HtmlWidget_2143(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_HtmlWidget_2143(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_HtmlWidget_2143(HtmlWidget self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_PreviousFormButton_3114(PreviousFormButton self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_PreviousFormButton_3114(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_PreviousFormButton_3114(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_PreviousFormButton_3114(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_PreviousFormButton_3114(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_PreviousFormButton_3114(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_PreviousFormButton_3114(
			PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_PreviousFormButton_3114(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_PreviousFormButton_3114(PreviousFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_PreviousFormButton_3114(PreviousFormButton self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_CheckBoxSingleFormField_3118(
			CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_CheckBoxSingleFormField_3118(
			CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_CheckBoxSingleFormField_3118(CheckBoxSingleFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_ComboFormField_3103(ComboFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_ComboFormField_3103(ComboFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_ComboFormField_3103(
			ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setType(ExpressionConstants.SCRIPT_TYPE);
		exp.setContent("[]");
		exp.setReturnType(List.class.getName());

		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_ComboFormField_3103(ComboFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_ComboFormField_3103(ComboFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Operation action_ComboFormField_3103(ComboFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_NextFormButton_3115(NextFormButton self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_NextFormButton_3115(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_NextFormButton_3115(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_NextFormButton_3115(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_NextFormButton_3115(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_NextFormButton_3115(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_NextFormButton_3115(
			NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_NextFormButton_3115(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_NextFormButton_3115(NextFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_NextFormButton_3115(NextFormButton self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_DateFormField_3105(DateFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_DateFormField_3105(
			DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_DateFormField_3105(DateFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_DateFormField_3105(DateFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_Group_3106(Group self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_Group_3106(Group self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_Group_3106(Group self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private GroupIterator iterator_Group_3106(Group self) {
		GroupIterator iterator = FormFactory.eINSTANCE.createGroupIterator();
		iterator.setName("");
		iterator.setClassName(Object.class.getName());
		return iterator;
	}

	/**
	* @generated
	*/
	private java.lang.String name_ListFormField_3107(ListFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_ListFormField_3107(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_ListFormField_3107(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_ListFormField_3107(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_ListFormField_3107(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_ListFormField_3107(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_ListFormField_3107(ListFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_ListFormField_3107(
			ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_ListFormField_3107(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_ListFormField_3107(ListFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_ListFormField_3107(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_ListFormField_3107(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_ListFormField_3107(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_ListFormField_3107(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_ListFormField_3107(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_ListFormField_3107(ListFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_ListFormField_3107(ListFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_ListFormField_3107(ListFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Operation action_ListFormField_3107(ListFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_SubmitFormButton_3116(SubmitFormButton self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_SubmitFormButton_3116(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_SubmitFormButton_3116(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_SubmitFormButton_3116(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_SubmitFormButton_3116(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_SubmitFormButton_3116(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_SubmitFormButton_3116(
			SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_SubmitFormButton_3116(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_SubmitFormButton_3116(SubmitFormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_SubmitFormButton_3116(SubmitFormButton self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_PasswordFormField_3109(PasswordFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_PasswordFormField_3109(
			PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_PasswordFormField_3109(PasswordFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_PasswordFormField_3109(PasswordFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_RadioFormField_3110(RadioFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_RadioFormField_3110(RadioFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_RadioFormField_3110(
			RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_RadioFormField_3110(RadioFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_RadioFormField_3110(RadioFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_RadioFormField_3110(RadioFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_SelectFormField_3111(SelectFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_SelectFormField_3111(SelectFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_SelectFormField_3111(
			SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_SelectFormField_3111(SelectFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_SelectFormField_3111(SelectFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_SelectFormField_3111(SelectFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_TextFormField_3112(TextFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_TextFormField_3112(
			TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_TextFormField_3112(TextFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_TextFormField_3112(TextFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_TextAreaFormField_3113(TextAreaFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_TextAreaFormField_3113(
			TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_TextAreaFormField_3113(TextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_TextAreaFormField_3113(TextAreaFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_RichTextAreaFormField_3128(
			RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_RichTextAreaFormField_3128(
			RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_RichTextAreaFormField_3128(RichTextAreaFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private FileWidgetInputType inputType_FileWidget_3119(FileWidget self) {
		return ModelHelper.getDefaultFileWidgetInputType(self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_FileWidget_3119(FileWidget self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression outputDocumentListExpression_FileWidget_3119(FileWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_FileWidget_3119(FileWidget self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String returnTypeModifier_FileWidget_3119(FileWidget self) {
		return WidgetModifiersSwitch.ENGINE_DOCUMENT_QUALIFIED_NAME;
	}

	/**
	* @generated
	*/
	private java.lang.String name_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_CheckBoxMultipleFormField_3120(
			CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_CheckBoxMultipleFormField_3120(
			CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Operation action_CheckBoxMultipleFormField_3120(CheckBoxMultipleFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_DurationFormField_3121(DurationFormField self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Long.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_DurationFormField_3121(
			DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_DurationFormField_3121(DurationFormField self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_DurationFormField_3121(DurationFormField self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_HiddenWidget_3122(HiddenWidget self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_HiddenWidget_3122(
			HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_HiddenWidget_3122(HiddenWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_HiddenWidget_3122(HiddenWidget self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_ImageWidget_3123(ImageWidget self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_ImageWidget_3123(
			ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression imgPath_ImageWidget_3123(ImageWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_ImageWidget_3123(ImageWidget self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_MessageInfo_3124(MessageInfo self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_MessageInfo_3124(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_MessageInfo_3124(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_MessageInfo_3124(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_MessageInfo_3124(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_MessageInfo_3124(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_MessageInfo_3124(
			MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_MessageInfo_3124(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_MessageInfo_3124(MessageInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_MessageInfo_3124(MessageInfo self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_TextInfo_3125(TextInfo self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_TextInfo_3125(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_TextInfo_3125(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_TextInfo_3125(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_TextInfo_3125(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_TextInfo_3125(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_TextInfo_3125(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_TextInfo_3125(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_TextInfo_3125(TextInfo self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_TextInfo_3125(TextInfo self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_FormButton_3126(FormButton self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_FormButton_3126(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_FormButton_3126(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_FormButton_3126(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_FormButton_3126(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_FormButton_3126(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_FormButton_3126(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_FormButton_3126(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_FormButton_3126(FormButton self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_FormButton_3126(FormButton self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_Table_3127(Table self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(List.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Collection.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxRowForPagination_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Collection.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Expression columnForInitialSelectionIndex_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression selectedValues_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression verticalHeaderExpression_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private TableExpression tableExpression_Table_3127(Table self) {
		TableExpression exp = ExpressionFactory.eINSTANCE.createTableExpression();
		return exp;
	}

	/**
	* @generated
	*/
	private Expression horizontalHeaderExpression_Table_3127(Table self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_Table_3127(Table self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_DynamicTable_3129(DynamicTable self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(List.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_DynamicTable_3129(
			DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Collection.class.getName());
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression verticalHeaderExpression_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private TableExpression tableExpression_DynamicTable_3129(DynamicTable self) {
		TableExpression exp = ExpressionFactory.eINSTANCE.createTableExpression();
		return exp;
	}

	/**
	* @generated
	*/
	private Expression horizontalHeaderExpression_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfColumn_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfRow_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfColumn_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfRow_DynamicTable_3129(DynamicTable self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_DynamicTable_3129(DynamicTable self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_IFrameWidget_3130(IFrameWidget self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_IFrameWidget_3130(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_IFrameWidget_3130(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_IFrameWidget_3130(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_IFrameWidget_3130(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_IFrameWidget_3130(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_IFrameWidget_3130(
			IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_IFrameWidget_3130(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_IFrameWidget_3130(IFrameWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_IFrameWidget_3130(IFrameWidget self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_HtmlWidget_3131(HtmlWidget self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_HtmlWidget_3131(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_HtmlWidget_3131(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_HtmlWidget_3131(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_HtmlWidget_3131(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_HtmlWidget_3131(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_HtmlWidget_3131(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_HtmlWidget_3131(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_HtmlWidget_3131(HtmlWidget self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_HtmlWidget_3131(HtmlWidget self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	private java.lang.String name_SuggestBox_3132(SuggestBox self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression displayLabel_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression(self.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression injectWidgetScript_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression exampleMessage_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression helpMessage_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltip_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression inputExpression_SuggestBox_3132(SuggestBox self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayAfterEventDependsOnConditionScript_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression afterEventExpression_SuggestBox_3132(SuggestBox self) {
		return ExpressionHelper.createEmptyListGroovyScriptExpression();
	}

	/**
	* @generated
	*/
	private Expression tooltipForRemove_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression tooltipForAdd_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForRemove_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression displayLabelForAdd_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression minNumberOfDuplication_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression maxNumberOfDuplication_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(Integer.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpression_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Expression defaultExpressionAfterEvent_SuggestBox_3132(SuggestBox self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		return exp;
	}

	/**
	* @generated
	*/
	private Operation action_SuggestBox_3132(SuggestBox self) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation();
		Operator op = ExpressionFactory.eINSTANCE.createOperator();
		op.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
		op.setExpression("=");
		action.setOperator(op);

		Expression variableExp = ExpressionFactory.eINSTANCE.createExpression();
		Expression actionExp = ExpressionFactory.eINSTANCE.createExpression();
		action.setLeftOperand(variableExp);
		action.setRightOperand(actionExp);
		return action;
	}

	/**
	* @generated
	*/
	public static ElementInitializers getInstance() {
		ElementInitializers cached = FormDiagramEditorPlugin.getInstance().getElementInitializers();
		if (cached == null) {
			FormDiagramEditorPlugin.getInstance().setElementInitializers(cached = new ElementInitializers());
		}
		return cached;
	}
}
