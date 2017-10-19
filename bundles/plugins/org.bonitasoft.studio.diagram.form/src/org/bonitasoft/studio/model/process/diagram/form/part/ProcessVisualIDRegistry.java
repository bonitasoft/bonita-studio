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

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.*;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class ProcessVisualIDRegistry {

	/**
	* @generated
	*/
	private static final String DEBUG_KEY = "org.bonitasoft.studio.diagram.form/debug/visualID"; //$NON-NLS-1$

	/**
	* @generated
	*/
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (FormEditPart.MODEL_ID.equals(view.getType())) {
				return FormEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	* @generated
	*/
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	* @generated
	*/
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
				FormDiagramEditorPlugin.getInstance()
						.logError("Unable to parse view type as a visualID number: " + type);
			}
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	* @generated
	*/
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (FormPackage.eINSTANCE.getForm().isSuperTypeOf(domainElement.eClass()) && isDiagram((Form) domainElement)) {
			return FormEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry
				.getModelID(containerView);
		if (!FormEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (FormEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = FormEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case FormEditPart.VISUAL_ID:
			if (FormPackage.eINSTANCE.getCheckBoxSingleFormField().isSuperTypeOf(domainElement.eClass())) {
				return CheckBoxSingleFormFieldEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getComboFormField().isSuperTypeOf(domainElement.eClass())) {
				return ComboFormFieldEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getDateFormField().isSuperTypeOf(domainElement.eClass())) {
				return DateFormFieldEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getListFormField().isSuperTypeOf(domainElement.eClass())) {
				return ListFormFieldEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getPasswordFormField().isSuperTypeOf(domainElement.eClass())) {
				return PasswordFormFieldEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getRadioFormField().isSuperTypeOf(domainElement.eClass())) {
				return RadioFormFieldEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getSelectFormField().isSuperTypeOf(domainElement.eClass())) {
				return SelectFormFieldEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getSuggestBox().isSuperTypeOf(domainElement.eClass())) {
				return SuggestBoxEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getTextFormField().isSuperTypeOf(domainElement.eClass())) {
				return TextFormFieldEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getTextAreaFormField().isSuperTypeOf(domainElement.eClass())) {
				return TextAreaFormFieldEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getRichTextAreaFormField().isSuperTypeOf(domainElement.eClass())) {
				return RichTextAreaFormFieldEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getSubmitFormButton().isSuperTypeOf(domainElement.eClass())) {
				return SubmitFormButtonEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getNextFormButton().isSuperTypeOf(domainElement.eClass())) {
				return NextFormButtonEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getPreviousFormButton().isSuperTypeOf(domainElement.eClass())) {
				return PreviousFormButtonEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getGroup().isSuperTypeOf(domainElement.eClass())) {
				return GroupEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getMessageInfo().isSuperTypeOf(domainElement.eClass())) {
				return MessageInfoEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getTextInfo().isSuperTypeOf(domainElement.eClass())) {
				return TextInfoEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getFileWidget().isSuperTypeOf(domainElement.eClass())) {
				return FileWidgetEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getCheckBoxMultipleFormField().isSuperTypeOf(domainElement.eClass())) {
				return CheckBoxMultipleFormFieldEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getImageWidget().isSuperTypeOf(domainElement.eClass())) {
				return ImageWidgetEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getHiddenWidget().isSuperTypeOf(domainElement.eClass())) {
				return HiddenWidgetEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getDurationFormField().isSuperTypeOf(domainElement.eClass())) {
				return DurationFormFieldEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getFormButton().isSuperTypeOf(domainElement.eClass())) {
				return FormButtonEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getTable().isSuperTypeOf(domainElement.eClass())) {
				return TableEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getDynamicTable().isSuperTypeOf(domainElement.eClass())) {
				return DynamicTableEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getIFrameWidget().isSuperTypeOf(domainElement.eClass())) {
				return IFrameWidgetEditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getHtmlWidget().isSuperTypeOf(domainElement.eClass())) {
				return HtmlWidgetEditPart.VISUAL_ID;
			}
			break;
		case GroupEditPart.VISUAL_ID:
			if (FormPackage.eINSTANCE.getPreviousFormButton().isSuperTypeOf(domainElement.eClass())) {
				return PreviousFormButton2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getCheckBoxSingleFormField().isSuperTypeOf(domainElement.eClass())) {
				return CheckBoxSingleFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getComboFormField().isSuperTypeOf(domainElement.eClass())) {
				return ComboFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getNextFormButton().isSuperTypeOf(domainElement.eClass())) {
				return NextFormButton2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getDateFormField().isSuperTypeOf(domainElement.eClass())) {
				return DateFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getGroup().isSuperTypeOf(domainElement.eClass())) {
				return Group2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getListFormField().isSuperTypeOf(domainElement.eClass())) {
				return ListFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getSubmitFormButton().isSuperTypeOf(domainElement.eClass())) {
				return SubmitFormButton2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getPasswordFormField().isSuperTypeOf(domainElement.eClass())) {
				return PasswordFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getRadioFormField().isSuperTypeOf(domainElement.eClass())) {
				return RadioFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getSelectFormField().isSuperTypeOf(domainElement.eClass())) {
				return SelectFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getTextFormField().isSuperTypeOf(domainElement.eClass())) {
				return TextFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getTextAreaFormField().isSuperTypeOf(domainElement.eClass())) {
				return TextAreaFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getRichTextAreaFormField().isSuperTypeOf(domainElement.eClass())) {
				return RichTextAreaFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getFileWidget().isSuperTypeOf(domainElement.eClass())) {
				return FileWidget2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getCheckBoxMultipleFormField().isSuperTypeOf(domainElement.eClass())) {
				return CheckBoxMultipleFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getDurationFormField().isSuperTypeOf(domainElement.eClass())) {
				return DurationFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getHiddenWidget().isSuperTypeOf(domainElement.eClass())) {
				return HiddenWidget2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getImageWidget().isSuperTypeOf(domainElement.eClass())) {
				return ImageWidget2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getMessageInfo().isSuperTypeOf(domainElement.eClass())) {
				return MessageInfo2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getTextInfo().isSuperTypeOf(domainElement.eClass())) {
				return TextInfo2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getFormButton().isSuperTypeOf(domainElement.eClass())) {
				return FormButton2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getTable().isSuperTypeOf(domainElement.eClass())) {
				return Table2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getDynamicTable().isSuperTypeOf(domainElement.eClass())) {
				return DynamicTable2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getIFrameWidget().isSuperTypeOf(domainElement.eClass())) {
				return IFrameWidget2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getHtmlWidget().isSuperTypeOf(domainElement.eClass())) {
				return HtmlWidget2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getSuggestBox().isSuperTypeOf(domainElement.eClass())) {
				return SuggestBox2EditPart.VISUAL_ID;
			}
			break;
		case Group2EditPart.VISUAL_ID:
			if (FormPackage.eINSTANCE.getPreviousFormButton().isSuperTypeOf(domainElement.eClass())) {
				return PreviousFormButton2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getCheckBoxSingleFormField().isSuperTypeOf(domainElement.eClass())) {
				return CheckBoxSingleFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getComboFormField().isSuperTypeOf(domainElement.eClass())) {
				return ComboFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getNextFormButton().isSuperTypeOf(domainElement.eClass())) {
				return NextFormButton2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getDateFormField().isSuperTypeOf(domainElement.eClass())) {
				return DateFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getGroup().isSuperTypeOf(domainElement.eClass())) {
				return Group2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getListFormField().isSuperTypeOf(domainElement.eClass())) {
				return ListFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getSubmitFormButton().isSuperTypeOf(domainElement.eClass())) {
				return SubmitFormButton2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getPasswordFormField().isSuperTypeOf(domainElement.eClass())) {
				return PasswordFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getRadioFormField().isSuperTypeOf(domainElement.eClass())) {
				return RadioFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getSelectFormField().isSuperTypeOf(domainElement.eClass())) {
				return SelectFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getTextFormField().isSuperTypeOf(domainElement.eClass())) {
				return TextFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getTextAreaFormField().isSuperTypeOf(domainElement.eClass())) {
				return TextAreaFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getRichTextAreaFormField().isSuperTypeOf(domainElement.eClass())) {
				return RichTextAreaFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getFileWidget().isSuperTypeOf(domainElement.eClass())) {
				return FileWidget2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getCheckBoxMultipleFormField().isSuperTypeOf(domainElement.eClass())) {
				return CheckBoxMultipleFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getDurationFormField().isSuperTypeOf(domainElement.eClass())) {
				return DurationFormField2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getHiddenWidget().isSuperTypeOf(domainElement.eClass())) {
				return HiddenWidget2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getImageWidget().isSuperTypeOf(domainElement.eClass())) {
				return ImageWidget2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getMessageInfo().isSuperTypeOf(domainElement.eClass())) {
				return MessageInfo2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getTextInfo().isSuperTypeOf(domainElement.eClass())) {
				return TextInfo2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getFormButton().isSuperTypeOf(domainElement.eClass())) {
				return FormButton2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getTable().isSuperTypeOf(domainElement.eClass())) {
				return Table2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getDynamicTable().isSuperTypeOf(domainElement.eClass())) {
				return DynamicTable2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getIFrameWidget().isSuperTypeOf(domainElement.eClass())) {
				return IFrameWidget2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getHtmlWidget().isSuperTypeOf(domainElement.eClass())) {
				return HtmlWidget2EditPart.VISUAL_ID;
			}
			if (FormPackage.eINSTANCE.getSuggestBox().isSuperTypeOf(domainElement.eClass())) {
				return SuggestBox2EditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry
				.getModelID(containerView);
		if (!FormEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (FormEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = FormEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case FormEditPart.VISUAL_ID:
			if (CheckBoxSingleFormFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ComboFormFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DateFormFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ListFormFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PasswordFormFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RadioFormFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SelectFormFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SuggestBoxEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextFormFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextAreaFormFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RichTextAreaFormFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubmitFormButtonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NextFormButtonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PreviousFormButtonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (GroupEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (MessageInfoEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextInfoEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (FileWidgetEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CheckBoxMultipleFormFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ImageWidgetEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HiddenWidgetEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DurationFormFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (FormButtonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TableEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DynamicTableEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IFrameWidgetEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HtmlWidgetEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CheckBoxSingleFormFieldEditPart.VISUAL_ID:
			if (CheckBoxSingleFormFieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ComboFormFieldEditPart.VISUAL_ID:
			if (ComboFormFieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DateFormFieldEditPart.VISUAL_ID:
			if (DateFormFieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ListFormFieldEditPart.VISUAL_ID:
			if (ListFormFieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PasswordFormFieldEditPart.VISUAL_ID:
			if (PasswordFormFieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RadioFormFieldEditPart.VISUAL_ID:
			if (RadioFormFieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SelectFormFieldEditPart.VISUAL_ID:
			if (SelectFormFieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SuggestBoxEditPart.VISUAL_ID:
			if (SuggestBoxNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TextFormFieldEditPart.VISUAL_ID:
			if (TextFormFieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TextAreaFormFieldEditPart.VISUAL_ID:
			if (TextAreaFormFieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RichTextAreaFormFieldEditPart.VISUAL_ID:
			if (RichTextAreaFormFieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SubmitFormButtonEditPart.VISUAL_ID:
			if (SubmitFormButtonNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NextFormButtonEditPart.VISUAL_ID:
			if (NextFormButtonNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PreviousFormButtonEditPart.VISUAL_ID:
			if (PreviousFormButtonNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case GroupEditPart.VISUAL_ID:
			if (PreviousFormButton2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CheckBoxSingleFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ComboFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NextFormButton2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DateFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Group2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ListFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubmitFormButton2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PasswordFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RadioFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SelectFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextAreaFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RichTextAreaFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (FileWidget2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CheckBoxMultipleFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DurationFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HiddenWidget2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ImageWidget2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (MessageInfo2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextInfo2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (FormButton2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Table2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DynamicTable2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IFrameWidget2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HtmlWidget2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SuggestBox2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case MessageInfoEditPart.VISUAL_ID:
			if (MessageInfoLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TextInfoEditPart.VISUAL_ID:
			if (TextInfoNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case FileWidgetEditPart.VISUAL_ID:
			if (FileWidgetNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CheckBoxMultipleFormFieldEditPart.VISUAL_ID:
			if (CheckBoxMultipleFormFieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ImageWidgetEditPart.VISUAL_ID:
			if (ImageWidgetNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case HiddenWidgetEditPart.VISUAL_ID:
			if (HiddenWidgetDisplayLabelShowDisplayEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DurationFormFieldEditPart.VISUAL_ID:
			if (DurationFormFieldNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case FormButtonEditPart.VISUAL_ID:
			if (FormButtonNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TableEditPart.VISUAL_ID:
			if (TableNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DynamicTableEditPart.VISUAL_ID:
			if (DynamicTableNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IFrameWidgetEditPart.VISUAL_ID:
			if (IFrameWidgetNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case HtmlWidgetEditPart.VISUAL_ID:
			if (HtmlWidgetNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PreviousFormButton2EditPart.VISUAL_ID:
			if (PreviousFormButtonName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CheckBoxSingleFormField2EditPart.VISUAL_ID:
			if (CheckBoxSingleFormFieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ComboFormField2EditPart.VISUAL_ID:
			if (ComboFormFieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NextFormButton2EditPart.VISUAL_ID:
			if (NextFormButtonName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DateFormField2EditPart.VISUAL_ID:
			if (DateFormFieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Group2EditPart.VISUAL_ID:
			if (PreviousFormButton2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CheckBoxSingleFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ComboFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NextFormButton2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DateFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Group2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ListFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubmitFormButton2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PasswordFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RadioFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SelectFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextAreaFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RichTextAreaFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (FileWidget2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CheckBoxMultipleFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DurationFormField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HiddenWidget2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ImageWidget2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (MessageInfo2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextInfo2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (FormButton2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Table2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DynamicTable2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IFrameWidget2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HtmlWidget2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SuggestBox2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ListFormField2EditPart.VISUAL_ID:
			if (ListFormFieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SubmitFormButton2EditPart.VISUAL_ID:
			if (SubmitFormButtonName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PasswordFormField2EditPart.VISUAL_ID:
			if (PasswordFormFieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RadioFormField2EditPart.VISUAL_ID:
			if (RadioFormFieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SelectFormField2EditPart.VISUAL_ID:
			if (SelectFormFieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TextFormField2EditPart.VISUAL_ID:
			if (TextFormFieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TextAreaFormField2EditPart.VISUAL_ID:
			if (TextAreaFormFieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RichTextAreaFormField2EditPart.VISUAL_ID:
			if (RichTextAreaFormFieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case FileWidget2EditPart.VISUAL_ID:
			if (FileWidgetName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CheckBoxMultipleFormField2EditPart.VISUAL_ID:
			if (CheckBoxMultipleFormFieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DurationFormField2EditPart.VISUAL_ID:
			if (DurationFormFieldName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case HiddenWidget2EditPart.VISUAL_ID:
			if (HiddenWidgetLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ImageWidget2EditPart.VISUAL_ID:
			if (ImageWidgetName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case MessageInfo2EditPart.VISUAL_ID:
			if (MessageInfoLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TextInfo2EditPart.VISUAL_ID:
			if (TextInfoName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case FormButton2EditPart.VISUAL_ID:
			if (FormButtonName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Table2EditPart.VISUAL_ID:
			if (TableName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DynamicTable2EditPart.VISUAL_ID:
			if (DynamicTableName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IFrameWidget2EditPart.VISUAL_ID:
			if (IFrameWidgetName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case HtmlWidget2EditPart.VISUAL_ID:
			if (HtmlWidgetName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SuggestBox2EditPart.VISUAL_ID:
			if (SuggestBoxName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	* @generated
	*/
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		return -1;
	}

	/**
	* User can change implementation of this method to handle some specific
	* situations not covered by default logic.
	* 
	* @generated
	*/
	private static boolean isDiagram(Form element) {
		return true;
	}

	/**
	* @generated
	*/
	public static boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	* @generated
	*/
	public static boolean isCompartmentVisualID(int visualID) {
		return false;
	}

	/**
	* @generated
	*/
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case FormEditPart.VISUAL_ID:
			return false;
		case ComboFormFieldEditPart.VISUAL_ID:
		case DateFormFieldEditPart.VISUAL_ID:
		case ListFormFieldEditPart.VISUAL_ID:
		case PasswordFormFieldEditPart.VISUAL_ID:
		case RadioFormFieldEditPart.VISUAL_ID:
		case SelectFormFieldEditPart.VISUAL_ID:
		case TextFormFieldEditPart.VISUAL_ID:
		case TextAreaFormFieldEditPart.VISUAL_ID:
		case SubmitFormButtonEditPart.VISUAL_ID:
		case NextFormButtonEditPart.VISUAL_ID:
		case PreviousFormButtonEditPart.VISUAL_ID:
		case CheckBoxSingleFormFieldEditPart.VISUAL_ID:
		case MessageInfoEditPart.VISUAL_ID:
		case TextInfoEditPart.VISUAL_ID:
		case FileWidgetEditPart.VISUAL_ID:
		case CheckBoxMultipleFormFieldEditPart.VISUAL_ID:
		case ImageWidgetEditPart.VISUAL_ID:
		case HiddenWidgetEditPart.VISUAL_ID:
		case DurationFormFieldEditPart.VISUAL_ID:
		case FormButtonEditPart.VISUAL_ID:
		case TableEditPart.VISUAL_ID:
		case RichTextAreaFormFieldEditPart.VISUAL_ID:
		case DynamicTableEditPart.VISUAL_ID:
		case IFrameWidgetEditPart.VISUAL_ID:
		case HtmlWidgetEditPart.VISUAL_ID:
		case SuggestBoxEditPart.VISUAL_ID:
		case ComboFormField2EditPart.VISUAL_ID:
		case DateFormField2EditPart.VISUAL_ID:
		case ListFormField2EditPart.VISUAL_ID:
		case PasswordFormField2EditPart.VISUAL_ID:
		case RadioFormField2EditPart.VISUAL_ID:
		case SelectFormField2EditPart.VISUAL_ID:
		case TextFormField2EditPart.VISUAL_ID:
		case TextAreaFormField2EditPart.VISUAL_ID:
		case PreviousFormButton2EditPart.VISUAL_ID:
		case NextFormButton2EditPart.VISUAL_ID:
		case SubmitFormButton2EditPart.VISUAL_ID:
		case CheckBoxSingleFormField2EditPart.VISUAL_ID:
		case FileWidget2EditPart.VISUAL_ID:
		case CheckBoxMultipleFormField2EditPart.VISUAL_ID:
		case DurationFormField2EditPart.VISUAL_ID:
		case HiddenWidget2EditPart.VISUAL_ID:
		case ImageWidget2EditPart.VISUAL_ID:
		case MessageInfo2EditPart.VISUAL_ID:
		case TextInfo2EditPart.VISUAL_ID:
		case FormButton2EditPart.VISUAL_ID:
		case Table2EditPart.VISUAL_ID:
		case RichTextAreaFormField2EditPart.VISUAL_ID:
		case DynamicTable2EditPart.VISUAL_ID:
		case IFrameWidget2EditPart.VISUAL_ID:
		case HtmlWidget2EditPart.VISUAL_ID:
		case SuggestBox2EditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	* @generated
	*/
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		* @generated
		*/
		@Override

		public int getVisualID(View view) {
			return org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry.getVisualID(view);
		}

		/**
		* @generated
		*/
		@Override

		public String getModelID(View view) {
			return org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry.getModelID(view);
		}

		/**
		* @generated
		*/
		@Override

		public int getNodeVisualID(View containerView, EObject domainElement) {
			return org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry
					.getNodeVisualID(containerView, domainElement);
		}

		/**
		* @generated
		*/
		@Override

		public boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
			return org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry
					.checkNodeVisualID(containerView, domainElement, candidate);
		}

		/**
		* @generated
		*/
		@Override

		public boolean isCompartmentVisualID(int visualID) {
			return org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry
					.isCompartmentVisualID(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public boolean isSemanticLeafVisualID(int visualID) {
			return org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry
					.isSemanticLeafVisualID(visualID);
		}
	};

}
