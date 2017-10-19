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
package org.bonitasoft.studio.model.process.diagram.form.edit.parts;

import org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

/**
 * @generated
 */
public class ProcessEditPartFactory implements EditPartFactory {

	/**
	* @generated
	*/
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (ProcessVisualIDRegistry.getVisualID(view)) {

			case FormEditPart.VISUAL_ID:
				return new FormEditPart(view);

			case CheckBoxSingleFormFieldEditPart.VISUAL_ID:
				return new CheckBoxSingleFormFieldEditPart(view);

			case CheckBoxSingleFormFieldNameEditPart.VISUAL_ID:
				return new CheckBoxSingleFormFieldNameEditPart(view);

			case ComboFormFieldEditPart.VISUAL_ID:
				return new ComboFormFieldEditPart(view);

			case ComboFormFieldNameEditPart.VISUAL_ID:
				return new ComboFormFieldNameEditPart(view);

			case DateFormFieldEditPart.VISUAL_ID:
				return new DateFormFieldEditPart(view);

			case DateFormFieldNameEditPart.VISUAL_ID:
				return new DateFormFieldNameEditPart(view);

			case ListFormFieldEditPart.VISUAL_ID:
				return new ListFormFieldEditPart(view);

			case ListFormFieldNameEditPart.VISUAL_ID:
				return new ListFormFieldNameEditPart(view);

			case PasswordFormFieldEditPart.VISUAL_ID:
				return new PasswordFormFieldEditPart(view);

			case PasswordFormFieldNameEditPart.VISUAL_ID:
				return new PasswordFormFieldNameEditPart(view);

			case RadioFormFieldEditPart.VISUAL_ID:
				return new RadioFormFieldEditPart(view);

			case RadioFormFieldNameEditPart.VISUAL_ID:
				return new RadioFormFieldNameEditPart(view);

			case SelectFormFieldEditPart.VISUAL_ID:
				return new SelectFormFieldEditPart(view);

			case SelectFormFieldNameEditPart.VISUAL_ID:
				return new SelectFormFieldNameEditPart(view);

			case SuggestBoxEditPart.VISUAL_ID:
				return new SuggestBoxEditPart(view);

			case SuggestBoxNameEditPart.VISUAL_ID:
				return new SuggestBoxNameEditPart(view);

			case TextFormFieldEditPart.VISUAL_ID:
				return new TextFormFieldEditPart(view);

			case TextFormFieldNameEditPart.VISUAL_ID:
				return new TextFormFieldNameEditPart(view);

			case TextAreaFormFieldEditPart.VISUAL_ID:
				return new TextAreaFormFieldEditPart(view);

			case TextAreaFormFieldNameEditPart.VISUAL_ID:
				return new TextAreaFormFieldNameEditPart(view);

			case RichTextAreaFormFieldEditPart.VISUAL_ID:
				return new RichTextAreaFormFieldEditPart(view);

			case RichTextAreaFormFieldNameEditPart.VISUAL_ID:
				return new RichTextAreaFormFieldNameEditPart(view);

			case SubmitFormButtonEditPart.VISUAL_ID:
				return new SubmitFormButtonEditPart(view);

			case SubmitFormButtonNameEditPart.VISUAL_ID:
				return new SubmitFormButtonNameEditPart(view);

			case NextFormButtonEditPart.VISUAL_ID:
				return new NextFormButtonEditPart(view);

			case NextFormButtonNameEditPart.VISUAL_ID:
				return new NextFormButtonNameEditPart(view);

			case PreviousFormButtonEditPart.VISUAL_ID:
				return new PreviousFormButtonEditPart(view);

			case PreviousFormButtonNameEditPart.VISUAL_ID:
				return new PreviousFormButtonNameEditPart(view);

			case GroupEditPart.VISUAL_ID:
				return new GroupEditPart(view);

			case MessageInfoEditPart.VISUAL_ID:
				return new MessageInfoEditPart(view);

			case MessageInfoLabelEditPart.VISUAL_ID:
				return new MessageInfoLabelEditPart(view);

			case TextInfoEditPart.VISUAL_ID:
				return new TextInfoEditPart(view);

			case TextInfoNameEditPart.VISUAL_ID:
				return new TextInfoNameEditPart(view);

			case FileWidgetEditPart.VISUAL_ID:
				return new FileWidgetEditPart(view);

			case FileWidgetNameEditPart.VISUAL_ID:
				return new FileWidgetNameEditPart(view);

			case CheckBoxMultipleFormFieldEditPart.VISUAL_ID:
				return new CheckBoxMultipleFormFieldEditPart(view);

			case CheckBoxMultipleFormFieldNameEditPart.VISUAL_ID:
				return new CheckBoxMultipleFormFieldNameEditPart(view);

			case ImageWidgetEditPart.VISUAL_ID:
				return new ImageWidgetEditPart(view);

			case ImageWidgetNameEditPart.VISUAL_ID:
				return new ImageWidgetNameEditPart(view);

			case HiddenWidgetEditPart.VISUAL_ID:
				return new HiddenWidgetEditPart(view);

			case HiddenWidgetDisplayLabelShowDisplayEditPart.VISUAL_ID:
				return new HiddenWidgetDisplayLabelShowDisplayEditPart(view);

			case DurationFormFieldEditPart.VISUAL_ID:
				return new DurationFormFieldEditPart(view);

			case DurationFormFieldNameEditPart.VISUAL_ID:
				return new DurationFormFieldNameEditPart(view);

			case FormButtonEditPart.VISUAL_ID:
				return new FormButtonEditPart(view);

			case FormButtonNameEditPart.VISUAL_ID:
				return new FormButtonNameEditPart(view);

			case TableEditPart.VISUAL_ID:
				return new TableEditPart(view);

			case TableNameEditPart.VISUAL_ID:
				return new TableNameEditPart(view);

			case DynamicTableEditPart.VISUAL_ID:
				return new DynamicTableEditPart(view);

			case DynamicTableNameEditPart.VISUAL_ID:
				return new DynamicTableNameEditPart(view);

			case IFrameWidgetEditPart.VISUAL_ID:
				return new IFrameWidgetEditPart(view);

			case IFrameWidgetNameEditPart.VISUAL_ID:
				return new IFrameWidgetNameEditPart(view);

			case HtmlWidgetEditPart.VISUAL_ID:
				return new HtmlWidgetEditPart(view);

			case HtmlWidgetNameEditPart.VISUAL_ID:
				return new HtmlWidgetNameEditPart(view);

			case PreviousFormButton2EditPart.VISUAL_ID:
				return new PreviousFormButton2EditPart(view);

			case PreviousFormButtonName2EditPart.VISUAL_ID:
				return new PreviousFormButtonName2EditPart(view);

			case CheckBoxSingleFormField2EditPart.VISUAL_ID:
				return new CheckBoxSingleFormField2EditPart(view);

			case CheckBoxSingleFormFieldName2EditPart.VISUAL_ID:
				return new CheckBoxSingleFormFieldName2EditPart(view);

			case ComboFormField2EditPart.VISUAL_ID:
				return new ComboFormField2EditPart(view);

			case ComboFormFieldName2EditPart.VISUAL_ID:
				return new ComboFormFieldName2EditPart(view);

			case NextFormButton2EditPart.VISUAL_ID:
				return new NextFormButton2EditPart(view);

			case NextFormButtonName2EditPart.VISUAL_ID:
				return new NextFormButtonName2EditPart(view);

			case DateFormField2EditPart.VISUAL_ID:
				return new DateFormField2EditPart(view);

			case DateFormFieldName2EditPart.VISUAL_ID:
				return new DateFormFieldName2EditPart(view);

			case Group2EditPart.VISUAL_ID:
				return new Group2EditPart(view);

			case ListFormField2EditPart.VISUAL_ID:
				return new ListFormField2EditPart(view);

			case ListFormFieldName2EditPart.VISUAL_ID:
				return new ListFormFieldName2EditPart(view);

			case SubmitFormButton2EditPart.VISUAL_ID:
				return new SubmitFormButton2EditPart(view);

			case SubmitFormButtonName2EditPart.VISUAL_ID:
				return new SubmitFormButtonName2EditPart(view);

			case PasswordFormField2EditPart.VISUAL_ID:
				return new PasswordFormField2EditPart(view);

			case PasswordFormFieldName2EditPart.VISUAL_ID:
				return new PasswordFormFieldName2EditPart(view);

			case RadioFormField2EditPart.VISUAL_ID:
				return new RadioFormField2EditPart(view);

			case RadioFormFieldName2EditPart.VISUAL_ID:
				return new RadioFormFieldName2EditPart(view);

			case SelectFormField2EditPart.VISUAL_ID:
				return new SelectFormField2EditPart(view);

			case SelectFormFieldName2EditPart.VISUAL_ID:
				return new SelectFormFieldName2EditPart(view);

			case TextFormField2EditPart.VISUAL_ID:
				return new TextFormField2EditPart(view);

			case TextFormFieldName2EditPart.VISUAL_ID:
				return new TextFormFieldName2EditPart(view);

			case TextAreaFormField2EditPart.VISUAL_ID:
				return new TextAreaFormField2EditPart(view);

			case TextAreaFormFieldName2EditPart.VISUAL_ID:
				return new TextAreaFormFieldName2EditPart(view);

			case RichTextAreaFormField2EditPart.VISUAL_ID:
				return new RichTextAreaFormField2EditPart(view);

			case RichTextAreaFormFieldName2EditPart.VISUAL_ID:
				return new RichTextAreaFormFieldName2EditPart(view);

			case FileWidget2EditPart.VISUAL_ID:
				return new FileWidget2EditPart(view);

			case FileWidgetName2EditPart.VISUAL_ID:
				return new FileWidgetName2EditPart(view);

			case CheckBoxMultipleFormField2EditPart.VISUAL_ID:
				return new CheckBoxMultipleFormField2EditPart(view);

			case CheckBoxMultipleFormFieldName2EditPart.VISUAL_ID:
				return new CheckBoxMultipleFormFieldName2EditPart(view);

			case DurationFormField2EditPart.VISUAL_ID:
				return new DurationFormField2EditPart(view);

			case DurationFormFieldName2EditPart.VISUAL_ID:
				return new DurationFormFieldName2EditPart(view);

			case HiddenWidget2EditPart.VISUAL_ID:
				return new HiddenWidget2EditPart(view);

			case HiddenWidgetLabelEditPart.VISUAL_ID:
				return new HiddenWidgetLabelEditPart(view);

			case ImageWidget2EditPart.VISUAL_ID:
				return new ImageWidget2EditPart(view);

			case ImageWidgetName2EditPart.VISUAL_ID:
				return new ImageWidgetName2EditPart(view);

			case MessageInfo2EditPart.VISUAL_ID:
				return new MessageInfo2EditPart(view);

			case MessageInfoLabel2EditPart.VISUAL_ID:
				return new MessageInfoLabel2EditPart(view);

			case TextInfo2EditPart.VISUAL_ID:
				return new TextInfo2EditPart(view);

			case TextInfoName2EditPart.VISUAL_ID:
				return new TextInfoName2EditPart(view);

			case FormButton2EditPart.VISUAL_ID:
				return new FormButton2EditPart(view);

			case FormButtonName2EditPart.VISUAL_ID:
				return new FormButtonName2EditPart(view);

			case Table2EditPart.VISUAL_ID:
				return new Table2EditPart(view);

			case TableName2EditPart.VISUAL_ID:
				return new TableName2EditPart(view);

			case DynamicTable2EditPart.VISUAL_ID:
				return new DynamicTable2EditPart(view);

			case DynamicTableName2EditPart.VISUAL_ID:
				return new DynamicTableName2EditPart(view);

			case IFrameWidget2EditPart.VISUAL_ID:
				return new IFrameWidget2EditPart(view);

			case IFrameWidgetName2EditPart.VISUAL_ID:
				return new IFrameWidgetName2EditPart(view);

			case HtmlWidget2EditPart.VISUAL_ID:
				return new HtmlWidget2EditPart(view);

			case HtmlWidgetName2EditPart.VISUAL_ID:
				return new HtmlWidgetName2EditPart(view);

			case SuggestBox2EditPart.VISUAL_ID:
				return new SuggestBox2EditPart(view);

			case SuggestBoxName2EditPart.VISUAL_ID:
				return new SuggestBoxName2EditPart(view);
			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	* @generated
	*/
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	* @generated
	*/
	public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
		return CellEditorLocatorAccess.INSTANCE.getTextCellEditorLocator(source);
	}

}
