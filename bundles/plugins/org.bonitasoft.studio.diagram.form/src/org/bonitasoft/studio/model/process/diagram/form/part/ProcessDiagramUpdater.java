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
package org.bonitasoft.studio.model.process.diagram.form.part;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxMultipleFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxMultipleFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxSingleFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxSingleFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ComboFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ComboFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DateFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DateFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DurationFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DurationFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DynamicTable2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DynamicTableEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.Group2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.GroupEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HiddenWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HiddenWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HtmlWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HtmlWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.IFrameWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.IFrameWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ImageWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ImageWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.MessageInfo2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.MessageInfoEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.NextFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.NextFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PasswordFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PasswordFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PreviousFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PreviousFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RadioFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RadioFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RichTextAreaFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RichTextAreaFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SelectFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SelectFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SubmitFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SubmitFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SuggestBox2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SuggestBoxEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.Table2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TableEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextAreaFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextAreaFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextInfo2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextInfoEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

/**
 * @generated
 */
public class ProcessDiagramUpdater {

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getSemanticChildren(View view) {
		switch (ProcessVisualIDRegistry.getVisualID(view)) {
		case FormEditPart.VISUAL_ID:
			return getForm_1000SemanticChildren(view);
		case GroupEditPart.VISUAL_ID:
			return getGroup_2125SemanticChildren(view);
		case Group2EditPart.VISUAL_ID:
			return getGroup_3106SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getForm_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Form modelElement = (Form) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getWidgets().iterator(); it.hasNext();) {
			Widget childElement = (Widget) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == CheckBoxSingleFormFieldEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ComboFormFieldEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DateFormFieldEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ListFormFieldEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == PasswordFormFieldEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == RadioFormFieldEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SelectFormFieldEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SuggestBoxEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextFormFieldEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextAreaFormFieldEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == RichTextAreaFormFieldEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SubmitFormButtonEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == NextFormButtonEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == PreviousFormButtonEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == GroupEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == MessageInfoEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextInfoEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == FileWidgetEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CheckBoxMultipleFormFieldEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ImageWidgetEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == HiddenWidgetEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DurationFormFieldEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == FormButtonEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TableEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DynamicTableEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IFrameWidgetEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == HtmlWidgetEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getGroup_2125SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Group modelElement = (Group) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getWidgets().iterator(); it.hasNext();) {
			Widget childElement = (Widget) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == PreviousFormButton2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CheckBoxSingleFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ComboFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == NextFormButton2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DateFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Group2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ListFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SubmitFormButton2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == PasswordFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == RadioFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SelectFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextAreaFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == RichTextAreaFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == FileWidget2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CheckBoxMultipleFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DurationFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == HiddenWidget2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ImageWidget2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == MessageInfo2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextInfo2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == FormButton2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Table2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DynamicTable2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IFrameWidget2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == HtmlWidget2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SuggestBox2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getGroup_3106SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Group modelElement = (Group) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getWidgets().iterator(); it.hasNext();) {
			Widget childElement = (Widget) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == PreviousFormButton2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CheckBoxSingleFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ComboFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == NextFormButton2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DateFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Group2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ListFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SubmitFormButton2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == PasswordFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == RadioFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SelectFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextAreaFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == RichTextAreaFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == FileWidget2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CheckBoxMultipleFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DurationFormField2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == HiddenWidget2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ImageWidget2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == MessageInfo2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextInfo2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == FormButton2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Table2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DynamicTable2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IFrameWidget2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == HtmlWidget2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SuggestBox2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessLinkDescriptor> getContainedLinks(View view) {
		switch (ProcessVisualIDRegistry.getVisualID(view)) {
		case FormEditPart.VISUAL_ID:
			return getForm_1000ContainedLinks(view);
		case CheckBoxSingleFormFieldEditPart.VISUAL_ID:
			return getCheckBoxSingleFormField_2130ContainedLinks(view);
		case ComboFormFieldEditPart.VISUAL_ID:
			return getComboFormField_2114ContainedLinks(view);
		case DateFormFieldEditPart.VISUAL_ID:
			return getDateFormField_2115ContainedLinks(view);
		case ListFormFieldEditPart.VISUAL_ID:
			return getListFormField_2116ContainedLinks(view);
		case PasswordFormFieldEditPart.VISUAL_ID:
			return getPasswordFormField_2117ContainedLinks(view);
		case RadioFormFieldEditPart.VISUAL_ID:
			return getRadioFormField_2118ContainedLinks(view);
		case SelectFormFieldEditPart.VISUAL_ID:
			return getSelectFormField_2119ContainedLinks(view);
		case SuggestBoxEditPart.VISUAL_ID:
			return getSuggestBox_2144ContainedLinks(view);
		case TextFormFieldEditPart.VISUAL_ID:
			return getTextFormField_2120ContainedLinks(view);
		case TextAreaFormFieldEditPart.VISUAL_ID:
			return getTextAreaFormField_2121ContainedLinks(view);
		case RichTextAreaFormFieldEditPart.VISUAL_ID:
			return getRichTextAreaFormField_2140ContainedLinks(view);
		case SubmitFormButtonEditPart.VISUAL_ID:
			return getSubmitFormButton_2126ContainedLinks(view);
		case NextFormButtonEditPart.VISUAL_ID:
			return getNextFormButton_2127ContainedLinks(view);
		case PreviousFormButtonEditPart.VISUAL_ID:
			return getPreviousFormButton_2128ContainedLinks(view);
		case GroupEditPart.VISUAL_ID:
			return getGroup_2125ContainedLinks(view);
		case MessageInfoEditPart.VISUAL_ID:
			return getMessageInfo_2131ContainedLinks(view);
		case TextInfoEditPart.VISUAL_ID:
			return getTextInfo_2132ContainedLinks(view);
		case FileWidgetEditPart.VISUAL_ID:
			return getFileWidget_2133ContainedLinks(view);
		case CheckBoxMultipleFormFieldEditPart.VISUAL_ID:
			return getCheckBoxMultipleFormField_2134ContainedLinks(view);
		case ImageWidgetEditPart.VISUAL_ID:
			return getImageWidget_2135ContainedLinks(view);
		case HiddenWidgetEditPart.VISUAL_ID:
			return getHiddenWidget_2136ContainedLinks(view);
		case DurationFormFieldEditPart.VISUAL_ID:
			return getDurationFormField_2137ContainedLinks(view);
		case FormButtonEditPart.VISUAL_ID:
			return getFormButton_2138ContainedLinks(view);
		case TableEditPart.VISUAL_ID:
			return getTable_2139ContainedLinks(view);
		case DynamicTableEditPart.VISUAL_ID:
			return getDynamicTable_2141ContainedLinks(view);
		case IFrameWidgetEditPart.VISUAL_ID:
			return getIFrameWidget_2142ContainedLinks(view);
		case HtmlWidgetEditPart.VISUAL_ID:
			return getHtmlWidget_2143ContainedLinks(view);
		case PreviousFormButton2EditPart.VISUAL_ID:
			return getPreviousFormButton_3114ContainedLinks(view);
		case CheckBoxSingleFormField2EditPart.VISUAL_ID:
			return getCheckBoxSingleFormField_3118ContainedLinks(view);
		case ComboFormField2EditPart.VISUAL_ID:
			return getComboFormField_3103ContainedLinks(view);
		case NextFormButton2EditPart.VISUAL_ID:
			return getNextFormButton_3115ContainedLinks(view);
		case DateFormField2EditPart.VISUAL_ID:
			return getDateFormField_3105ContainedLinks(view);
		case Group2EditPart.VISUAL_ID:
			return getGroup_3106ContainedLinks(view);
		case ListFormField2EditPart.VISUAL_ID:
			return getListFormField_3107ContainedLinks(view);
		case SubmitFormButton2EditPart.VISUAL_ID:
			return getSubmitFormButton_3116ContainedLinks(view);
		case PasswordFormField2EditPart.VISUAL_ID:
			return getPasswordFormField_3109ContainedLinks(view);
		case RadioFormField2EditPart.VISUAL_ID:
			return getRadioFormField_3110ContainedLinks(view);
		case SelectFormField2EditPart.VISUAL_ID:
			return getSelectFormField_3111ContainedLinks(view);
		case TextFormField2EditPart.VISUAL_ID:
			return getTextFormField_3112ContainedLinks(view);
		case TextAreaFormField2EditPart.VISUAL_ID:
			return getTextAreaFormField_3113ContainedLinks(view);
		case RichTextAreaFormField2EditPart.VISUAL_ID:
			return getRichTextAreaFormField_3128ContainedLinks(view);
		case FileWidget2EditPart.VISUAL_ID:
			return getFileWidget_3119ContainedLinks(view);
		case CheckBoxMultipleFormField2EditPart.VISUAL_ID:
			return getCheckBoxMultipleFormField_3120ContainedLinks(view);
		case DurationFormField2EditPart.VISUAL_ID:
			return getDurationFormField_3121ContainedLinks(view);
		case HiddenWidget2EditPart.VISUAL_ID:
			return getHiddenWidget_3122ContainedLinks(view);
		case ImageWidget2EditPart.VISUAL_ID:
			return getImageWidget_3123ContainedLinks(view);
		case MessageInfo2EditPart.VISUAL_ID:
			return getMessageInfo_3124ContainedLinks(view);
		case TextInfo2EditPart.VISUAL_ID:
			return getTextInfo_3125ContainedLinks(view);
		case FormButton2EditPart.VISUAL_ID:
			return getFormButton_3126ContainedLinks(view);
		case Table2EditPart.VISUAL_ID:
			return getTable_3127ContainedLinks(view);
		case DynamicTable2EditPart.VISUAL_ID:
			return getDynamicTable_3129ContainedLinks(view);
		case IFrameWidget2EditPart.VISUAL_ID:
			return getIFrameWidget_3130ContainedLinks(view);
		case HtmlWidget2EditPart.VISUAL_ID:
			return getHtmlWidget_3131ContainedLinks(view);
		case SuggestBox2EditPart.VISUAL_ID:
			return getSuggestBox_3132ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<ProcessLinkDescriptor> getIncomingLinks(View view) {
		switch (ProcessVisualIDRegistry.getVisualID(view)) {
		case CheckBoxSingleFormFieldEditPart.VISUAL_ID:
			return getCheckBoxSingleFormField_2130IncomingLinks(view);
		case ComboFormFieldEditPart.VISUAL_ID:
			return getComboFormField_2114IncomingLinks(view);
		case DateFormFieldEditPart.VISUAL_ID:
			return getDateFormField_2115IncomingLinks(view);
		case ListFormFieldEditPart.VISUAL_ID:
			return getListFormField_2116IncomingLinks(view);
		case PasswordFormFieldEditPart.VISUAL_ID:
			return getPasswordFormField_2117IncomingLinks(view);
		case RadioFormFieldEditPart.VISUAL_ID:
			return getRadioFormField_2118IncomingLinks(view);
		case SelectFormFieldEditPart.VISUAL_ID:
			return getSelectFormField_2119IncomingLinks(view);
		case SuggestBoxEditPart.VISUAL_ID:
			return getSuggestBox_2144IncomingLinks(view);
		case TextFormFieldEditPart.VISUAL_ID:
			return getTextFormField_2120IncomingLinks(view);
		case TextAreaFormFieldEditPart.VISUAL_ID:
			return getTextAreaFormField_2121IncomingLinks(view);
		case RichTextAreaFormFieldEditPart.VISUAL_ID:
			return getRichTextAreaFormField_2140IncomingLinks(view);
		case SubmitFormButtonEditPart.VISUAL_ID:
			return getSubmitFormButton_2126IncomingLinks(view);
		case NextFormButtonEditPart.VISUAL_ID:
			return getNextFormButton_2127IncomingLinks(view);
		case PreviousFormButtonEditPart.VISUAL_ID:
			return getPreviousFormButton_2128IncomingLinks(view);
		case GroupEditPart.VISUAL_ID:
			return getGroup_2125IncomingLinks(view);
		case MessageInfoEditPart.VISUAL_ID:
			return getMessageInfo_2131IncomingLinks(view);
		case TextInfoEditPart.VISUAL_ID:
			return getTextInfo_2132IncomingLinks(view);
		case FileWidgetEditPart.VISUAL_ID:
			return getFileWidget_2133IncomingLinks(view);
		case CheckBoxMultipleFormFieldEditPart.VISUAL_ID:
			return getCheckBoxMultipleFormField_2134IncomingLinks(view);
		case ImageWidgetEditPart.VISUAL_ID:
			return getImageWidget_2135IncomingLinks(view);
		case HiddenWidgetEditPart.VISUAL_ID:
			return getHiddenWidget_2136IncomingLinks(view);
		case DurationFormFieldEditPart.VISUAL_ID:
			return getDurationFormField_2137IncomingLinks(view);
		case FormButtonEditPart.VISUAL_ID:
			return getFormButton_2138IncomingLinks(view);
		case TableEditPart.VISUAL_ID:
			return getTable_2139IncomingLinks(view);
		case DynamicTableEditPart.VISUAL_ID:
			return getDynamicTable_2141IncomingLinks(view);
		case IFrameWidgetEditPart.VISUAL_ID:
			return getIFrameWidget_2142IncomingLinks(view);
		case HtmlWidgetEditPart.VISUAL_ID:
			return getHtmlWidget_2143IncomingLinks(view);
		case PreviousFormButton2EditPart.VISUAL_ID:
			return getPreviousFormButton_3114IncomingLinks(view);
		case CheckBoxSingleFormField2EditPart.VISUAL_ID:
			return getCheckBoxSingleFormField_3118IncomingLinks(view);
		case ComboFormField2EditPart.VISUAL_ID:
			return getComboFormField_3103IncomingLinks(view);
		case NextFormButton2EditPart.VISUAL_ID:
			return getNextFormButton_3115IncomingLinks(view);
		case DateFormField2EditPart.VISUAL_ID:
			return getDateFormField_3105IncomingLinks(view);
		case Group2EditPart.VISUAL_ID:
			return getGroup_3106IncomingLinks(view);
		case ListFormField2EditPart.VISUAL_ID:
			return getListFormField_3107IncomingLinks(view);
		case SubmitFormButton2EditPart.VISUAL_ID:
			return getSubmitFormButton_3116IncomingLinks(view);
		case PasswordFormField2EditPart.VISUAL_ID:
			return getPasswordFormField_3109IncomingLinks(view);
		case RadioFormField2EditPart.VISUAL_ID:
			return getRadioFormField_3110IncomingLinks(view);
		case SelectFormField2EditPart.VISUAL_ID:
			return getSelectFormField_3111IncomingLinks(view);
		case TextFormField2EditPart.VISUAL_ID:
			return getTextFormField_3112IncomingLinks(view);
		case TextAreaFormField2EditPart.VISUAL_ID:
			return getTextAreaFormField_3113IncomingLinks(view);
		case RichTextAreaFormField2EditPart.VISUAL_ID:
			return getRichTextAreaFormField_3128IncomingLinks(view);
		case FileWidget2EditPart.VISUAL_ID:
			return getFileWidget_3119IncomingLinks(view);
		case CheckBoxMultipleFormField2EditPart.VISUAL_ID:
			return getCheckBoxMultipleFormField_3120IncomingLinks(view);
		case DurationFormField2EditPart.VISUAL_ID:
			return getDurationFormField_3121IncomingLinks(view);
		case HiddenWidget2EditPart.VISUAL_ID:
			return getHiddenWidget_3122IncomingLinks(view);
		case ImageWidget2EditPart.VISUAL_ID:
			return getImageWidget_3123IncomingLinks(view);
		case MessageInfo2EditPart.VISUAL_ID:
			return getMessageInfo_3124IncomingLinks(view);
		case TextInfo2EditPart.VISUAL_ID:
			return getTextInfo_3125IncomingLinks(view);
		case FormButton2EditPart.VISUAL_ID:
			return getFormButton_3126IncomingLinks(view);
		case Table2EditPart.VISUAL_ID:
			return getTable_3127IncomingLinks(view);
		case DynamicTable2EditPart.VISUAL_ID:
			return getDynamicTable_3129IncomingLinks(view);
		case IFrameWidget2EditPart.VISUAL_ID:
			return getIFrameWidget_3130IncomingLinks(view);
		case HtmlWidget2EditPart.VISUAL_ID:
			return getHtmlWidget_3131IncomingLinks(view);
		case SuggestBox2EditPart.VISUAL_ID:
			return getSuggestBox_3132IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<ProcessLinkDescriptor> getOutgoingLinks(View view) {
		switch (ProcessVisualIDRegistry.getVisualID(view)) {
		case CheckBoxSingleFormFieldEditPart.VISUAL_ID:
			return getCheckBoxSingleFormField_2130OutgoingLinks(view);
		case ComboFormFieldEditPart.VISUAL_ID:
			return getComboFormField_2114OutgoingLinks(view);
		case DateFormFieldEditPart.VISUAL_ID:
			return getDateFormField_2115OutgoingLinks(view);
		case ListFormFieldEditPart.VISUAL_ID:
			return getListFormField_2116OutgoingLinks(view);
		case PasswordFormFieldEditPart.VISUAL_ID:
			return getPasswordFormField_2117OutgoingLinks(view);
		case RadioFormFieldEditPart.VISUAL_ID:
			return getRadioFormField_2118OutgoingLinks(view);
		case SelectFormFieldEditPart.VISUAL_ID:
			return getSelectFormField_2119OutgoingLinks(view);
		case SuggestBoxEditPart.VISUAL_ID:
			return getSuggestBox_2144OutgoingLinks(view);
		case TextFormFieldEditPart.VISUAL_ID:
			return getTextFormField_2120OutgoingLinks(view);
		case TextAreaFormFieldEditPart.VISUAL_ID:
			return getTextAreaFormField_2121OutgoingLinks(view);
		case RichTextAreaFormFieldEditPart.VISUAL_ID:
			return getRichTextAreaFormField_2140OutgoingLinks(view);
		case SubmitFormButtonEditPart.VISUAL_ID:
			return getSubmitFormButton_2126OutgoingLinks(view);
		case NextFormButtonEditPart.VISUAL_ID:
			return getNextFormButton_2127OutgoingLinks(view);
		case PreviousFormButtonEditPart.VISUAL_ID:
			return getPreviousFormButton_2128OutgoingLinks(view);
		case GroupEditPart.VISUAL_ID:
			return getGroup_2125OutgoingLinks(view);
		case MessageInfoEditPart.VISUAL_ID:
			return getMessageInfo_2131OutgoingLinks(view);
		case TextInfoEditPart.VISUAL_ID:
			return getTextInfo_2132OutgoingLinks(view);
		case FileWidgetEditPart.VISUAL_ID:
			return getFileWidget_2133OutgoingLinks(view);
		case CheckBoxMultipleFormFieldEditPart.VISUAL_ID:
			return getCheckBoxMultipleFormField_2134OutgoingLinks(view);
		case ImageWidgetEditPart.VISUAL_ID:
			return getImageWidget_2135OutgoingLinks(view);
		case HiddenWidgetEditPart.VISUAL_ID:
			return getHiddenWidget_2136OutgoingLinks(view);
		case DurationFormFieldEditPart.VISUAL_ID:
			return getDurationFormField_2137OutgoingLinks(view);
		case FormButtonEditPart.VISUAL_ID:
			return getFormButton_2138OutgoingLinks(view);
		case TableEditPart.VISUAL_ID:
			return getTable_2139OutgoingLinks(view);
		case DynamicTableEditPart.VISUAL_ID:
			return getDynamicTable_2141OutgoingLinks(view);
		case IFrameWidgetEditPart.VISUAL_ID:
			return getIFrameWidget_2142OutgoingLinks(view);
		case HtmlWidgetEditPart.VISUAL_ID:
			return getHtmlWidget_2143OutgoingLinks(view);
		case PreviousFormButton2EditPart.VISUAL_ID:
			return getPreviousFormButton_3114OutgoingLinks(view);
		case CheckBoxSingleFormField2EditPart.VISUAL_ID:
			return getCheckBoxSingleFormField_3118OutgoingLinks(view);
		case ComboFormField2EditPart.VISUAL_ID:
			return getComboFormField_3103OutgoingLinks(view);
		case NextFormButton2EditPart.VISUAL_ID:
			return getNextFormButton_3115OutgoingLinks(view);
		case DateFormField2EditPart.VISUAL_ID:
			return getDateFormField_3105OutgoingLinks(view);
		case Group2EditPart.VISUAL_ID:
			return getGroup_3106OutgoingLinks(view);
		case ListFormField2EditPart.VISUAL_ID:
			return getListFormField_3107OutgoingLinks(view);
		case SubmitFormButton2EditPart.VISUAL_ID:
			return getSubmitFormButton_3116OutgoingLinks(view);
		case PasswordFormField2EditPart.VISUAL_ID:
			return getPasswordFormField_3109OutgoingLinks(view);
		case RadioFormField2EditPart.VISUAL_ID:
			return getRadioFormField_3110OutgoingLinks(view);
		case SelectFormField2EditPart.VISUAL_ID:
			return getSelectFormField_3111OutgoingLinks(view);
		case TextFormField2EditPart.VISUAL_ID:
			return getTextFormField_3112OutgoingLinks(view);
		case TextAreaFormField2EditPart.VISUAL_ID:
			return getTextAreaFormField_3113OutgoingLinks(view);
		case RichTextAreaFormField2EditPart.VISUAL_ID:
			return getRichTextAreaFormField_3128OutgoingLinks(view);
		case FileWidget2EditPart.VISUAL_ID:
			return getFileWidget_3119OutgoingLinks(view);
		case CheckBoxMultipleFormField2EditPart.VISUAL_ID:
			return getCheckBoxMultipleFormField_3120OutgoingLinks(view);
		case DurationFormField2EditPart.VISUAL_ID:
			return getDurationFormField_3121OutgoingLinks(view);
		case HiddenWidget2EditPart.VISUAL_ID:
			return getHiddenWidget_3122OutgoingLinks(view);
		case ImageWidget2EditPart.VISUAL_ID:
			return getImageWidget_3123OutgoingLinks(view);
		case MessageInfo2EditPart.VISUAL_ID:
			return getMessageInfo_3124OutgoingLinks(view);
		case TextInfo2EditPart.VISUAL_ID:
			return getTextInfo_3125OutgoingLinks(view);
		case FormButton2EditPart.VISUAL_ID:
			return getFormButton_3126OutgoingLinks(view);
		case Table2EditPart.VISUAL_ID:
			return getTable_3127OutgoingLinks(view);
		case DynamicTable2EditPart.VISUAL_ID:
			return getDynamicTable_3129OutgoingLinks(view);
		case IFrameWidget2EditPart.VISUAL_ID:
			return getIFrameWidget_3130OutgoingLinks(view);
		case HtmlWidget2EditPart.VISUAL_ID:
			return getHtmlWidget_3131OutgoingLinks(view);
		case SuggestBox2EditPart.VISUAL_ID:
			return getSuggestBox_3132OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getForm_1000ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCheckBoxSingleFormField_2130ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getComboFormField_2114ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDateFormField_2115ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getListFormField_2116ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPasswordFormField_2117ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getRadioFormField_2118ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSelectFormField_2119ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSuggestBox_2144ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextFormField_2120ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAreaFormField_2121ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getRichTextAreaFormField_2140ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSubmitFormButton_2126ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getNextFormButton_2127ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPreviousFormButton_2128ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getGroup_2125ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getMessageInfo_2131ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextInfo_2132ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getFileWidget_2133ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCheckBoxMultipleFormField_2134ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getImageWidget_2135ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getHiddenWidget_2136ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDurationFormField_2137ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getFormButton_2138ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTable_2139ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDynamicTable_2141ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIFrameWidget_2142ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getHtmlWidget_2143ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPreviousFormButton_3114ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCheckBoxSingleFormField_3118ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getComboFormField_3103ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getNextFormButton_3115ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDateFormField_3105ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getGroup_3106ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getListFormField_3107ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSubmitFormButton_3116ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPasswordFormField_3109ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getRadioFormField_3110ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSelectFormField_3111ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextFormField_3112ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAreaFormField_3113ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getRichTextAreaFormField_3128ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getFileWidget_3119ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCheckBoxMultipleFormField_3120ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDurationFormField_3121ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getHiddenWidget_3122ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getImageWidget_3123ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getMessageInfo_3124ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextInfo_3125ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getFormButton_3126ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTable_3127ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDynamicTable_3129ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIFrameWidget_3130ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getHtmlWidget_3131ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSuggestBox_3132ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCheckBoxSingleFormField_2130IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getComboFormField_2114IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDateFormField_2115IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getListFormField_2116IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPasswordFormField_2117IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getRadioFormField_2118IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSelectFormField_2119IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSuggestBox_2144IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextFormField_2120IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAreaFormField_2121IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getRichTextAreaFormField_2140IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSubmitFormButton_2126IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getNextFormButton_2127IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPreviousFormButton_2128IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getGroup_2125IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getMessageInfo_2131IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextInfo_2132IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getFileWidget_2133IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCheckBoxMultipleFormField_2134IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getImageWidget_2135IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getHiddenWidget_2136IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDurationFormField_2137IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getFormButton_2138IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTable_2139IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDynamicTable_2141IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIFrameWidget_2142IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getHtmlWidget_2143IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPreviousFormButton_3114IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCheckBoxSingleFormField_3118IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getComboFormField_3103IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getNextFormButton_3115IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDateFormField_3105IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getGroup_3106IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getListFormField_3107IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSubmitFormButton_3116IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPasswordFormField_3109IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getRadioFormField_3110IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSelectFormField_3111IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextFormField_3112IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAreaFormField_3113IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getRichTextAreaFormField_3128IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getFileWidget_3119IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCheckBoxMultipleFormField_3120IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDurationFormField_3121IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getHiddenWidget_3122IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getImageWidget_3123IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getMessageInfo_3124IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextInfo_3125IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getFormButton_3126IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTable_3127IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDynamicTable_3129IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIFrameWidget_3130IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getHtmlWidget_3131IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSuggestBox_3132IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCheckBoxSingleFormField_2130OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getComboFormField_2114OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDateFormField_2115OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getListFormField_2116OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPasswordFormField_2117OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getRadioFormField_2118OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSelectFormField_2119OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSuggestBox_2144OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextFormField_2120OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAreaFormField_2121OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getRichTextAreaFormField_2140OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSubmitFormButton_2126OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getNextFormButton_2127OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPreviousFormButton_2128OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getGroup_2125OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getMessageInfo_2131OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextInfo_2132OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getFileWidget_2133OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCheckBoxMultipleFormField_2134OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getImageWidget_2135OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getHiddenWidget_2136OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDurationFormField_2137OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getFormButton_2138OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTable_2139OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDynamicTable_2141OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIFrameWidget_2142OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getHtmlWidget_2143OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPreviousFormButton_3114OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCheckBoxSingleFormField_3118OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getComboFormField_3103OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getNextFormButton_3115OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDateFormField_3105OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getGroup_3106OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getListFormField_3107OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSubmitFormButton_3116OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPasswordFormField_3109OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getRadioFormField_3110OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSelectFormField_3111OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextFormField_3112OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAreaFormField_3113OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getRichTextAreaFormField_3128OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getFileWidget_3119OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCheckBoxMultipleFormField_3120OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDurationFormField_3121OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getHiddenWidget_3122OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getImageWidget_3123OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getMessageInfo_3124OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextInfo_3125OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getFormButton_3126OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTable_3127OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getDynamicTable_3129OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIFrameWidget_3130OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getHtmlWidget_3131OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSuggestBox_3132OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		* @generated
		*/
		@Override

		public List<ProcessNodeDescriptor> getSemanticChildren(View view) {
			return ProcessDiagramUpdater.getSemanticChildren(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<ProcessLinkDescriptor> getContainedLinks(View view) {
			return ProcessDiagramUpdater.getContainedLinks(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<ProcessLinkDescriptor> getIncomingLinks(View view) {
			return ProcessDiagramUpdater.getIncomingLinks(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<ProcessLinkDescriptor> getOutgoingLinks(View view) {
			return ProcessDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
