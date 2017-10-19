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
package org.bonitasoft.studio.model.process.diagram.form.edit.policies;

import java.util.Iterator;

import org.bonitasoft.studio.model.process.diagram.form.edit.commands.CheckBoxMultipleFormField2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.CheckBoxSingleFormField2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.ComboFormField2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.DateFormField2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.DurationFormField2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.DynamicTable2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.FileWidget2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.FormButton2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.Group2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.HiddenWidget2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.HtmlWidget2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.IFrameWidget2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.ImageWidget2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.ListFormField2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.MessageInfo2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.NextFormButton2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.PasswordFormField2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.PreviousFormButton2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.RadioFormField2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.RichTextAreaFormField2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.SelectFormField2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.SubmitFormButton2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.SuggestBox2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.Table2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.TextAreaFormField2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.TextFormField2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.TextInfo2CreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxMultipleFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxSingleFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ComboFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DateFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DurationFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DynamicTable2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.Group2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HiddenWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HtmlWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.IFrameWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ImageWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.MessageInfo2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.NextFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PasswordFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PreviousFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RadioFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RichTextAreaFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SelectFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SubmitFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SuggestBox2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.Table2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextAreaFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextInfo2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessElementTypes;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class Group2ItemSemanticEditPolicy extends ProcessBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public Group2ItemSemanticEditPolicy() {
		super(ProcessElementTypes.Group_3106);
	}

	/**
	* @generated
	*/
	protected Command getCreateCommand(CreateElementRequest req) {
		if (ProcessElementTypes.PreviousFormButton_3114 == req.getElementType()) {
			return getGEFWrapper(new PreviousFormButton2CreateCommand(req));
		}
		if (ProcessElementTypes.CheckBoxSingleFormField_3118 == req.getElementType()) {
			return getGEFWrapper(new CheckBoxSingleFormField2CreateCommand(req));
		}
		if (ProcessElementTypes.ComboFormField_3103 == req.getElementType()) {
			return getGEFWrapper(new ComboFormField2CreateCommand(req));
		}
		if (ProcessElementTypes.NextFormButton_3115 == req.getElementType()) {
			return getGEFWrapper(new NextFormButton2CreateCommand(req));
		}
		if (ProcessElementTypes.DateFormField_3105 == req.getElementType()) {
			return getGEFWrapper(new DateFormField2CreateCommand(req));
		}
		if (ProcessElementTypes.Group_3106 == req.getElementType()) {
			return getGEFWrapper(new Group2CreateCommand(req));
		}
		if (ProcessElementTypes.ListFormField_3107 == req.getElementType()) {
			return getGEFWrapper(new ListFormField2CreateCommand(req));
		}
		if (ProcessElementTypes.SubmitFormButton_3116 == req.getElementType()) {
			return getGEFWrapper(new SubmitFormButton2CreateCommand(req));
		}
		if (ProcessElementTypes.PasswordFormField_3109 == req.getElementType()) {
			return getGEFWrapper(new PasswordFormField2CreateCommand(req));
		}
		if (ProcessElementTypes.RadioFormField_3110 == req.getElementType()) {
			return getGEFWrapper(new RadioFormField2CreateCommand(req));
		}
		if (ProcessElementTypes.SelectFormField_3111 == req.getElementType()) {
			return getGEFWrapper(new SelectFormField2CreateCommand(req));
		}
		if (ProcessElementTypes.TextFormField_3112 == req.getElementType()) {
			return getGEFWrapper(new TextFormField2CreateCommand(req));
		}
		if (ProcessElementTypes.TextAreaFormField_3113 == req.getElementType()) {
			return getGEFWrapper(new TextAreaFormField2CreateCommand(req));
		}
		if (ProcessElementTypes.RichTextAreaFormField_3128 == req.getElementType()) {
			return getGEFWrapper(new RichTextAreaFormField2CreateCommand(req));
		}
		if (ProcessElementTypes.FileWidget_3119 == req.getElementType()) {
			return getGEFWrapper(new FileWidget2CreateCommand(req));
		}
		if (ProcessElementTypes.CheckBoxMultipleFormField_3120 == req.getElementType()) {
			return getGEFWrapper(new CheckBoxMultipleFormField2CreateCommand(req));
		}
		if (ProcessElementTypes.DurationFormField_3121 == req.getElementType()) {
			return getGEFWrapper(new DurationFormField2CreateCommand(req));
		}
		if (ProcessElementTypes.HiddenWidget_3122 == req.getElementType()) {
			return getGEFWrapper(new HiddenWidget2CreateCommand(req));
		}
		if (ProcessElementTypes.ImageWidget_3123 == req.getElementType()) {
			return getGEFWrapper(new ImageWidget2CreateCommand(req));
		}
		if (ProcessElementTypes.MessageInfo_3124 == req.getElementType()) {
			return getGEFWrapper(new MessageInfo2CreateCommand(req));
		}
		if (ProcessElementTypes.TextInfo_3125 == req.getElementType()) {
			return getGEFWrapper(new TextInfo2CreateCommand(req));
		}
		if (ProcessElementTypes.FormButton_3126 == req.getElementType()) {
			return getGEFWrapper(new FormButton2CreateCommand(req));
		}
		if (ProcessElementTypes.Table_3127 == req.getElementType()) {
			return getGEFWrapper(new Table2CreateCommand(req));
		}
		if (ProcessElementTypes.DynamicTable_3129 == req.getElementType()) {
			return getGEFWrapper(new DynamicTable2CreateCommand(req));
		}
		if (ProcessElementTypes.IFrameWidget_3130 == req.getElementType()) {
			return getGEFWrapper(new IFrameWidget2CreateCommand(req));
		}
		if (ProcessElementTypes.HtmlWidget_3131 == req.getElementType()) {
			return getGEFWrapper(new HtmlWidget2CreateCommand(req));
		}
		if (ProcessElementTypes.SuggestBox_3132 == req.getElementType()) {
			return getGEFWrapper(new SuggestBox2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	* @generated
	*/
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyChildNodesCommand(cmd);
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	* @generated
	*/
	private void addDestroyChildNodesCommand(ICompositeCommand cmd) {
		View view = (View) getHost().getModel();
		for (Iterator<?> nit = view.getChildren().iterator(); nit.hasNext();) {
			Node node = (Node) nit.next();
			switch (ProcessVisualIDRegistry.getVisualID(node)) {
			case PreviousFormButton2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case CheckBoxSingleFormField2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case ComboFormField2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case NextFormButton2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case DateFormField2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case Group2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case ListFormField2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case SubmitFormButton2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case PasswordFormField2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case RadioFormField2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case SelectFormField2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case TextFormField2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case TextAreaFormField2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case RichTextAreaFormField2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case FileWidget2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case CheckBoxMultipleFormField2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case DurationFormField2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case HiddenWidget2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case ImageWidget2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case MessageInfo2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case TextInfo2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case FormButton2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case Table2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case DynamicTable2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case IFrameWidget2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case HtmlWidget2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			case SuggestBox2EditPart.VISUAL_ID:
				cmd.add(new DestroyElementCommand(
						new DestroyElementRequest(getEditingDomain(), node.getElement(), false))); // directlyOwned: true
				// don't need explicit deletion of node as parent's view deletion would clean child views as well 
				// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), node));
				break;
			}
		}
	}

}
