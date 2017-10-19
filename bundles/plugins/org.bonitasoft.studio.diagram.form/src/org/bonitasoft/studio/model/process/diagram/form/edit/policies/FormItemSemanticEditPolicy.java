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

import org.bonitasoft.studio.model.process.diagram.form.edit.commands.CheckBoxMultipleFormFieldCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.CheckBoxSingleFormFieldCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.ComboFormFieldCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.DateFormFieldCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.DurationFormFieldCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.DynamicTableCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.FileWidgetCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.FormButtonCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.GroupCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.HiddenWidgetCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.HtmlWidgetCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.IFrameWidgetCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.ImageWidgetCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.ListFormFieldCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.MessageInfoCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.NextFormButtonCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.PasswordFormFieldCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.PreviousFormButtonCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.RadioFormFieldCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.RichTextAreaFormFieldCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.SelectFormFieldCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.SubmitFormButtonCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.SuggestBoxCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.TableCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.TextAreaFormFieldCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.TextFormFieldCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.commands.TextInfoCreateCommand;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessElementTypes;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

/**
 * @generated
 */
public class FormItemSemanticEditPolicy extends ProcessBaseItemSemanticEditPolicy {

	/**
	* @generated
	*/
	public FormItemSemanticEditPolicy() {
		super(ProcessElementTypes.Form_1000);
	}

	/**
	* @generated
	*/
	protected Command getCreateCommand(CreateElementRequest req) {
		if (ProcessElementTypes.CheckBoxSingleFormField_2130 == req.getElementType()) {
			return getGEFWrapper(new CheckBoxSingleFormFieldCreateCommand(req));
		}
		if (ProcessElementTypes.ComboFormField_2114 == req.getElementType()) {
			return getGEFWrapper(new ComboFormFieldCreateCommand(req));
		}
		if (ProcessElementTypes.DateFormField_2115 == req.getElementType()) {
			return getGEFWrapper(new DateFormFieldCreateCommand(req));
		}
		if (ProcessElementTypes.ListFormField_2116 == req.getElementType()) {
			return getGEFWrapper(new ListFormFieldCreateCommand(req));
		}
		if (ProcessElementTypes.PasswordFormField_2117 == req.getElementType()) {
			return getGEFWrapper(new PasswordFormFieldCreateCommand(req));
		}
		if (ProcessElementTypes.RadioFormField_2118 == req.getElementType()) {
			return getGEFWrapper(new RadioFormFieldCreateCommand(req));
		}
		if (ProcessElementTypes.SelectFormField_2119 == req.getElementType()) {
			return getGEFWrapper(new SelectFormFieldCreateCommand(req));
		}
		if (ProcessElementTypes.SuggestBox_2144 == req.getElementType()) {
			return getGEFWrapper(new SuggestBoxCreateCommand(req));
		}
		if (ProcessElementTypes.TextFormField_2120 == req.getElementType()) {
			return getGEFWrapper(new TextFormFieldCreateCommand(req));
		}
		if (ProcessElementTypes.TextAreaFormField_2121 == req.getElementType()) {
			return getGEFWrapper(new TextAreaFormFieldCreateCommand(req));
		}
		if (ProcessElementTypes.RichTextAreaFormField_2140 == req.getElementType()) {
			return getGEFWrapper(new RichTextAreaFormFieldCreateCommand(req));
		}
		if (ProcessElementTypes.SubmitFormButton_2126 == req.getElementType()) {
			return getGEFWrapper(new SubmitFormButtonCreateCommand(req));
		}
		if (ProcessElementTypes.NextFormButton_2127 == req.getElementType()) {
			return getGEFWrapper(new NextFormButtonCreateCommand(req));
		}
		if (ProcessElementTypes.PreviousFormButton_2128 == req.getElementType()) {
			return getGEFWrapper(new PreviousFormButtonCreateCommand(req));
		}
		if (ProcessElementTypes.Group_2125 == req.getElementType()) {
			return getGEFWrapper(new GroupCreateCommand(req));
		}
		if (ProcessElementTypes.MessageInfo_2131 == req.getElementType()) {
			return getGEFWrapper(new MessageInfoCreateCommand(req));
		}
		if (ProcessElementTypes.TextInfo_2132 == req.getElementType()) {
			return getGEFWrapper(new TextInfoCreateCommand(req));
		}
		if (ProcessElementTypes.FileWidget_2133 == req.getElementType()) {
			return getGEFWrapper(new FileWidgetCreateCommand(req));
		}
		if (ProcessElementTypes.CheckBoxMultipleFormField_2134 == req.getElementType()) {
			return getGEFWrapper(new CheckBoxMultipleFormFieldCreateCommand(req));
		}
		if (ProcessElementTypes.ImageWidget_2135 == req.getElementType()) {
			return getGEFWrapper(new ImageWidgetCreateCommand(req));
		}
		if (ProcessElementTypes.HiddenWidget_2136 == req.getElementType()) {
			return getGEFWrapper(new HiddenWidgetCreateCommand(req));
		}
		if (ProcessElementTypes.DurationFormField_2137 == req.getElementType()) {
			return getGEFWrapper(new DurationFormFieldCreateCommand(req));
		}
		if (ProcessElementTypes.FormButton_2138 == req.getElementType()) {
			return getGEFWrapper(new FormButtonCreateCommand(req));
		}
		if (ProcessElementTypes.Table_2139 == req.getElementType()) {
			return getGEFWrapper(new TableCreateCommand(req));
		}
		if (ProcessElementTypes.DynamicTable_2141 == req.getElementType()) {
			return getGEFWrapper(new DynamicTableCreateCommand(req));
		}
		if (ProcessElementTypes.IFrameWidget_2142 == req.getElementType()) {
			return getGEFWrapper(new IFrameWidgetCreateCommand(req));
		}
		if (ProcessElementTypes.HtmlWidget_2143 == req.getElementType()) {
			return getGEFWrapper(new HtmlWidgetCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	* @generated
	*/
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	* @generated
	*/
	private static class DuplicateAnythingCommand extends DuplicateEObjectsCommand {

		/**
		* @generated
		*/
		public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap());
		}

	}

}
