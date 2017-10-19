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
package org.bonitasoft.studio.model.process.diagram.form.navigator;

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.*;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditorPlugin;
import org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessElementTypes;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * @generated
 */
public class FormNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider, ITreePathLabelProvider {

	/**
	* @generated
	*/
	static {
		FormDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?UnknownElement", //$NON-NLS-1$
				ImageDescriptor.getMissingImageDescriptor());
		FormDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?ImageNotFound", //$NON-NLS-1$
				ImageDescriptor.getMissingImageDescriptor());
	}

	/**
	* @generated
	*/
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof FormNavigatorItem && !isOwnView(((FormNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	* @generated
	*/
	public Image getImage(Object element) {
		if (element instanceof FormNavigatorGroup) {
			FormNavigatorGroup group = (FormNavigatorGroup) element;
			return FormDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
		}

		if (element instanceof FormNavigatorItem) {
			FormNavigatorItem navigatorItem = (FormNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	* @generated
	*/
	public Image getImage(View view) {
		switch (ProcessVisualIDRegistry.getVisualID(view)) {
		case FormEditPart.VISUAL_ID:
			return getImage("Navigator?Diagram?http://www.bonitasoft.org/ns/studio/form?Form", //$NON-NLS-1$
					ProcessElementTypes.Form_1000);
		case ComboFormFieldEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?ComboFormField", //$NON-NLS-1$
					ProcessElementTypes.ComboFormField_2114);
		case DateFormFieldEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?DateFormField", //$NON-NLS-1$
					ProcessElementTypes.DateFormField_2115);
		case ListFormFieldEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?ListFormField", //$NON-NLS-1$
					ProcessElementTypes.ListFormField_2116);
		case PasswordFormFieldEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?PasswordFormField", //$NON-NLS-1$
					ProcessElementTypes.PasswordFormField_2117);
		case RadioFormFieldEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?RadioFormField", //$NON-NLS-1$
					ProcessElementTypes.RadioFormField_2118);
		case SelectFormFieldEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?SelectFormField", //$NON-NLS-1$
					ProcessElementTypes.SelectFormField_2119);
		case TextFormFieldEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?TextFormField", //$NON-NLS-1$
					ProcessElementTypes.TextFormField_2120);
		case TextAreaFormFieldEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?TextAreaFormField", //$NON-NLS-1$
					ProcessElementTypes.TextAreaFormField_2121);
		case GroupEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?Group", //$NON-NLS-1$
					ProcessElementTypes.Group_2125);
		case SubmitFormButtonEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?SubmitFormButton", //$NON-NLS-1$
					ProcessElementTypes.SubmitFormButton_2126);
		case NextFormButtonEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?NextFormButton", //$NON-NLS-1$
					ProcessElementTypes.NextFormButton_2127);
		case PreviousFormButtonEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?PreviousFormButton", //$NON-NLS-1$
					ProcessElementTypes.PreviousFormButton_2128);
		case CheckBoxSingleFormFieldEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?CheckBoxSingleFormField", //$NON-NLS-1$
					ProcessElementTypes.CheckBoxSingleFormField_2130);
		case MessageInfoEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?MessageInfo", //$NON-NLS-1$
					ProcessElementTypes.MessageInfo_2131);
		case TextInfoEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?TextInfo", //$NON-NLS-1$
					ProcessElementTypes.TextInfo_2132);
		case FileWidgetEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?FileWidget", //$NON-NLS-1$
					ProcessElementTypes.FileWidget_2133);
		case CheckBoxMultipleFormFieldEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?CheckBoxMultipleFormField", //$NON-NLS-1$
					ProcessElementTypes.CheckBoxMultipleFormField_2134);
		case ImageWidgetEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?ImageWidget", //$NON-NLS-1$
					ProcessElementTypes.ImageWidget_2135);
		case HiddenWidgetEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?HiddenWidget", //$NON-NLS-1$
					ProcessElementTypes.HiddenWidget_2136);
		case DurationFormFieldEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?DurationFormField", //$NON-NLS-1$
					ProcessElementTypes.DurationFormField_2137);
		case FormButtonEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?FormButton", //$NON-NLS-1$
					ProcessElementTypes.FormButton_2138);
		case TableEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?Table", //$NON-NLS-1$
					ProcessElementTypes.Table_2139);
		case RichTextAreaFormFieldEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?RichTextAreaFormField", //$NON-NLS-1$
					ProcessElementTypes.RichTextAreaFormField_2140);
		case DynamicTableEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?DynamicTable", //$NON-NLS-1$
					ProcessElementTypes.DynamicTable_2141);
		case IFrameWidgetEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?IFrameWidget", //$NON-NLS-1$
					ProcessElementTypes.IFrameWidget_2142);
		case HtmlWidgetEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?HtmlWidget", //$NON-NLS-1$
					ProcessElementTypes.HtmlWidget_2143);
		case SuggestBoxEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/form?SuggestBox", //$NON-NLS-1$
					ProcessElementTypes.SuggestBox_2144);
		case ComboFormField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?ComboFormField", //$NON-NLS-1$
					ProcessElementTypes.ComboFormField_3103);
		case DateFormField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?DateFormField", //$NON-NLS-1$
					ProcessElementTypes.DateFormField_3105);
		case Group2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?Group", //$NON-NLS-1$
					ProcessElementTypes.Group_3106);
		case ListFormField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?ListFormField", //$NON-NLS-1$
					ProcessElementTypes.ListFormField_3107);
		case PasswordFormField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?PasswordFormField", //$NON-NLS-1$
					ProcessElementTypes.PasswordFormField_3109);
		case RadioFormField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?RadioFormField", //$NON-NLS-1$
					ProcessElementTypes.RadioFormField_3110);
		case SelectFormField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?SelectFormField", //$NON-NLS-1$
					ProcessElementTypes.SelectFormField_3111);
		case TextFormField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?TextFormField", //$NON-NLS-1$
					ProcessElementTypes.TextFormField_3112);
		case TextAreaFormField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?TextAreaFormField", //$NON-NLS-1$
					ProcessElementTypes.TextAreaFormField_3113);
		case PreviousFormButton2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?PreviousFormButton", //$NON-NLS-1$
					ProcessElementTypes.PreviousFormButton_3114);
		case NextFormButton2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?NextFormButton", //$NON-NLS-1$
					ProcessElementTypes.NextFormButton_3115);
		case SubmitFormButton2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?SubmitFormButton", //$NON-NLS-1$
					ProcessElementTypes.SubmitFormButton_3116);
		case CheckBoxSingleFormField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?CheckBoxSingleFormField", //$NON-NLS-1$
					ProcessElementTypes.CheckBoxSingleFormField_3118);
		case FileWidget2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?FileWidget", //$NON-NLS-1$
					ProcessElementTypes.FileWidget_3119);
		case CheckBoxMultipleFormField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?CheckBoxMultipleFormField", //$NON-NLS-1$
					ProcessElementTypes.CheckBoxMultipleFormField_3120);
		case DurationFormField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?DurationFormField", //$NON-NLS-1$
					ProcessElementTypes.DurationFormField_3121);
		case HiddenWidget2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?HiddenWidget", //$NON-NLS-1$
					ProcessElementTypes.HiddenWidget_3122);
		case ImageWidget2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?ImageWidget", //$NON-NLS-1$
					ProcessElementTypes.ImageWidget_3123);
		case MessageInfo2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?MessageInfo", //$NON-NLS-1$
					ProcessElementTypes.MessageInfo_3124);
		case TextInfo2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?TextInfo", //$NON-NLS-1$
					ProcessElementTypes.TextInfo_3125);
		case FormButton2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?FormButton", //$NON-NLS-1$
					ProcessElementTypes.FormButton_3126);
		case Table2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?Table", //$NON-NLS-1$
					ProcessElementTypes.Table_3127);
		case RichTextAreaFormField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?RichTextAreaFormField", //$NON-NLS-1$
					ProcessElementTypes.RichTextAreaFormField_3128);
		case DynamicTable2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?DynamicTable", //$NON-NLS-1$
					ProcessElementTypes.DynamicTable_3129);
		case IFrameWidget2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?IFrameWidget", //$NON-NLS-1$
					ProcessElementTypes.IFrameWidget_3130);
		case HtmlWidget2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?HtmlWidget", //$NON-NLS-1$
					ProcessElementTypes.HtmlWidget_3131);
		case SuggestBox2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/form?SuggestBox", //$NON-NLS-1$
					ProcessElementTypes.SuggestBox_3132);
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = FormDiagramEditorPlugin.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null && ProcessElementTypes.isKnownElementType(elementType)) {
			image = ProcessElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	* @generated
	*/
	public String getText(Object element) {
		if (element instanceof FormNavigatorGroup) {
			FormNavigatorGroup group = (FormNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof FormNavigatorItem) {
			FormNavigatorItem navigatorItem = (FormNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	* @generated
	*/
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (ProcessVisualIDRegistry.getVisualID(view)) {
		case FormEditPart.VISUAL_ID:
			return getForm_1000Text(view);
		case ComboFormFieldEditPart.VISUAL_ID:
			return getComboFormField_2114Text(view);
		case DateFormFieldEditPart.VISUAL_ID:
			return getDateFormField_2115Text(view);
		case ListFormFieldEditPart.VISUAL_ID:
			return getListFormField_2116Text(view);
		case PasswordFormFieldEditPart.VISUAL_ID:
			return getPasswordFormField_2117Text(view);
		case RadioFormFieldEditPart.VISUAL_ID:
			return getRadioFormField_2118Text(view);
		case SelectFormFieldEditPart.VISUAL_ID:
			return getSelectFormField_2119Text(view);
		case TextFormFieldEditPart.VISUAL_ID:
			return getTextFormField_2120Text(view);
		case TextAreaFormFieldEditPart.VISUAL_ID:
			return getTextAreaFormField_2121Text(view);
		case GroupEditPart.VISUAL_ID:
			return getGroup_2125Text(view);
		case SubmitFormButtonEditPart.VISUAL_ID:
			return getSubmitFormButton_2126Text(view);
		case NextFormButtonEditPart.VISUAL_ID:
			return getNextFormButton_2127Text(view);
		case PreviousFormButtonEditPart.VISUAL_ID:
			return getPreviousFormButton_2128Text(view);
		case CheckBoxSingleFormFieldEditPart.VISUAL_ID:
			return getCheckBoxSingleFormField_2130Text(view);
		case MessageInfoEditPart.VISUAL_ID:
			return getMessageInfo_2131Text(view);
		case TextInfoEditPart.VISUAL_ID:
			return getTextInfo_2132Text(view);
		case FileWidgetEditPart.VISUAL_ID:
			return getFileWidget_2133Text(view);
		case CheckBoxMultipleFormFieldEditPart.VISUAL_ID:
			return getCheckBoxMultipleFormField_2134Text(view);
		case ImageWidgetEditPart.VISUAL_ID:
			return getImageWidget_2135Text(view);
		case HiddenWidgetEditPart.VISUAL_ID:
			return getHiddenWidget_2136Text(view);
		case DurationFormFieldEditPart.VISUAL_ID:
			return getDurationFormField_2137Text(view);
		case FormButtonEditPart.VISUAL_ID:
			return getFormButton_2138Text(view);
		case TableEditPart.VISUAL_ID:
			return getTable_2139Text(view);
		case RichTextAreaFormFieldEditPart.VISUAL_ID:
			return getRichTextAreaFormField_2140Text(view);
		case DynamicTableEditPart.VISUAL_ID:
			return getDynamicTable_2141Text(view);
		case IFrameWidgetEditPart.VISUAL_ID:
			return getIFrameWidget_2142Text(view);
		case HtmlWidgetEditPart.VISUAL_ID:
			return getHtmlWidget_2143Text(view);
		case SuggestBoxEditPart.VISUAL_ID:
			return getSuggestBox_2144Text(view);
		case ComboFormField2EditPart.VISUAL_ID:
			return getComboFormField_3103Text(view);
		case DateFormField2EditPart.VISUAL_ID:
			return getDateFormField_3105Text(view);
		case Group2EditPart.VISUAL_ID:
			return getGroup_3106Text(view);
		case ListFormField2EditPart.VISUAL_ID:
			return getListFormField_3107Text(view);
		case PasswordFormField2EditPart.VISUAL_ID:
			return getPasswordFormField_3109Text(view);
		case RadioFormField2EditPart.VISUAL_ID:
			return getRadioFormField_3110Text(view);
		case SelectFormField2EditPart.VISUAL_ID:
			return getSelectFormField_3111Text(view);
		case TextFormField2EditPart.VISUAL_ID:
			return getTextFormField_3112Text(view);
		case TextAreaFormField2EditPart.VISUAL_ID:
			return getTextAreaFormField_3113Text(view);
		case PreviousFormButton2EditPart.VISUAL_ID:
			return getPreviousFormButton_3114Text(view);
		case NextFormButton2EditPart.VISUAL_ID:
			return getNextFormButton_3115Text(view);
		case SubmitFormButton2EditPart.VISUAL_ID:
			return getSubmitFormButton_3116Text(view);
		case CheckBoxSingleFormField2EditPart.VISUAL_ID:
			return getCheckBoxSingleFormField_3118Text(view);
		case FileWidget2EditPart.VISUAL_ID:
			return getFileWidget_3119Text(view);
		case CheckBoxMultipleFormField2EditPart.VISUAL_ID:
			return getCheckBoxMultipleFormField_3120Text(view);
		case DurationFormField2EditPart.VISUAL_ID:
			return getDurationFormField_3121Text(view);
		case HiddenWidget2EditPart.VISUAL_ID:
			return getHiddenWidget_3122Text(view);
		case ImageWidget2EditPart.VISUAL_ID:
			return getImageWidget_3123Text(view);
		case MessageInfo2EditPart.VISUAL_ID:
			return getMessageInfo_3124Text(view);
		case TextInfo2EditPart.VISUAL_ID:
			return getTextInfo_3125Text(view);
		case FormButton2EditPart.VISUAL_ID:
			return getFormButton_3126Text(view);
		case Table2EditPart.VISUAL_ID:
			return getTable_3127Text(view);
		case RichTextAreaFormField2EditPart.VISUAL_ID:
			return getRichTextAreaFormField_3128Text(view);
		case DynamicTable2EditPart.VISUAL_ID:
			return getDynamicTable_3129Text(view);
		case IFrameWidget2EditPart.VISUAL_ID:
			return getIFrameWidget_3130Text(view);
		case HtmlWidget2EditPart.VISUAL_ID:
			return getHtmlWidget_3131Text(view);
		case SuggestBox2EditPart.VISUAL_ID:
			return getSuggestBox_3132Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	* @generated
	*/
	private String getForm_1000Text(View view) {
		Form domainModelElement = (Form) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			FormDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getComboFormField_2114Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ComboFormField_2114,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ComboFormFieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5150); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getDateFormField_2115Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.DateFormField_2115,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(DateFormFieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5151); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getListFormField_2116Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ListFormField_2116,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ListFormFieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5152); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getPasswordFormField_2117Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.PasswordFormField_2117,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(PasswordFormFieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5153); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getRadioFormField_2118Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.RadioFormField_2118,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(RadioFormFieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5154); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSelectFormField_2119Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.SelectFormField_2119,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(SelectFormFieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5155); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTextFormField_2120Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.TextFormField_2120,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(TextFormFieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5157); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTextAreaFormField_2121Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.TextAreaFormField_2121,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(TextAreaFormFieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5158); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getGroup_2125Text(View view) {
		Group domainModelElement = (Group) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			FormDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 2125); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSubmitFormButton_2126Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.SubmitFormButton_2126,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(SubmitFormButtonNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5160); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getNextFormButton_2127Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.NextFormButton_2127,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(NextFormButtonNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5161); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getPreviousFormButton_2128Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.PreviousFormButton_2128,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(PreviousFormButtonNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5162); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getCheckBoxSingleFormField_2130Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.CheckBoxSingleFormField_2130,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(CheckBoxSingleFormFieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5148); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getMessageInfo_2131Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.MessageInfo_2131,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(MessageInfoLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5135); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTextInfo_2132Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.TextInfo_2132,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(TextInfoNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5188); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getFileWidget_2133Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.FileWidget_2133,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(FileWidgetNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5189); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getCheckBoxMultipleFormField_2134Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.CheckBoxMultipleFormField_2134,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(CheckBoxMultipleFormFieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5190); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getImageWidget_2135Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ImageWidget_2135,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ImageWidgetNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5191); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getHiddenWidget_2136Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.HiddenWidget_2136,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(HiddenWidgetDisplayLabelShowDisplayEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5141); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getDurationFormField_2137Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.DurationFormField_2137,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(DurationFormFieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5192); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getFormButton_2138Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.FormButton_2138,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(FormButtonNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5193); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTable_2139Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.Table_2139,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(TableNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5194); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getRichTextAreaFormField_2140Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.RichTextAreaFormField_2140,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(RichTextAreaFormFieldNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5159); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getDynamicTable_2141Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.DynamicTable_2141,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(DynamicTableNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5195); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIFrameWidget_2142Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IFrameWidget_2142,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IFrameWidgetNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5196); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getHtmlWidget_2143Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.HtmlWidget_2143,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(HtmlWidgetNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5197); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSuggestBox_2144Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.SuggestBox_2144,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(SuggestBoxNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5156); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getComboFormField_3103Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ComboFormField_3103,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ComboFormFieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5164); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getDateFormField_3105Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.DateFormField_3105,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(DateFormFieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5166); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getGroup_3106Text(View view) {
		Group domainModelElement = (Group) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			FormDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3106); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getListFormField_3107Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ListFormField_3107,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ListFormFieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5167); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getPasswordFormField_3109Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.PasswordFormField_3109,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(PasswordFormFieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5169); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getRadioFormField_3110Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.RadioFormField_3110,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(RadioFormFieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5170); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSelectFormField_3111Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.SelectFormField_3111,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(SelectFormFieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5171); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTextFormField_3112Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.TextFormField_3112,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(TextFormFieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5172); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTextAreaFormField_3113Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.TextAreaFormField_3113,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(TextAreaFormFieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5173); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getPreviousFormButton_3114Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.PreviousFormButton_3114,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(PreviousFormButtonName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5163); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getNextFormButton_3115Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.NextFormButton_3115,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(NextFormButtonName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5165); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSubmitFormButton_3116Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.SubmitFormButton_3116,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(SubmitFormButtonName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5168); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getCheckBoxSingleFormField_3118Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.CheckBoxSingleFormField_3118,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(CheckBoxSingleFormFieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5149); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getFileWidget_3119Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.FileWidget_3119,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(FileWidgetName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5175); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getCheckBoxMultipleFormField_3120Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.CheckBoxMultipleFormField_3120,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(CheckBoxMultipleFormFieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5176); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getDurationFormField_3121Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.DurationFormField_3121,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(DurationFormFieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5177); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getHiddenWidget_3122Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.HiddenWidget_3122,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(HiddenWidgetLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5145); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getImageWidget_3123Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ImageWidget_3123,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ImageWidgetName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5178); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getMessageInfo_3124Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.MessageInfo_3124,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(MessageInfoLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5147); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTextInfo_3125Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.TextInfo_3125,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(TextInfoName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5179); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getFormButton_3126Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.FormButton_3126,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(FormButtonName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5180); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTable_3127Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.Table_3127,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(TableName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5181); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getRichTextAreaFormField_3128Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.RichTextAreaFormField_3128,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(RichTextAreaFormFieldName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5174); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getDynamicTable_3129Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.DynamicTable_3129,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(DynamicTableName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5182); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIFrameWidget_3130Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IFrameWidget_3130,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IFrameWidgetName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5183); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getHtmlWidget_3131Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.HtmlWidget_3131,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(HtmlWidgetName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5184); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSuggestBox_3132Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.SuggestBox_3132,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(SuggestBoxName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			FormDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5185); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	* @generated
	*/
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	* @generated
	*/
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	* @generated
	*/
	public void restoreState(IMemento aMemento) {
	}

	/**
	* @generated
	*/
	public void saveState(IMemento aMemento) {
	}

	/**
	* @generated
	*/
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	* @generated
	*/
	private boolean isOwnView(View view) {
		return FormEditPart.MODEL_ID.equals(ProcessVisualIDRegistry.getModelID(view));
	}

}
