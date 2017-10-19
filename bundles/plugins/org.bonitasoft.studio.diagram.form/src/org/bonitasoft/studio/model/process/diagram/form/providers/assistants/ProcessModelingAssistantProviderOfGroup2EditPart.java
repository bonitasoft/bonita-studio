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
package org.bonitasoft.studio.model.process.diagram.form.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessElementTypes;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessModelingAssistantProvider;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class ProcessModelingAssistantProviderOfGroup2EditPart extends ProcessModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(27);
		types.add(ProcessElementTypes.PreviousFormButton_3114);
		types.add(ProcessElementTypes.CheckBoxSingleFormField_3118);
		types.add(ProcessElementTypes.ComboFormField_3103);
		types.add(ProcessElementTypes.NextFormButton_3115);
		types.add(ProcessElementTypes.DateFormField_3105);
		types.add(ProcessElementTypes.Group_3106);
		types.add(ProcessElementTypes.ListFormField_3107);
		types.add(ProcessElementTypes.SubmitFormButton_3116);
		types.add(ProcessElementTypes.PasswordFormField_3109);
		types.add(ProcessElementTypes.RadioFormField_3110);
		types.add(ProcessElementTypes.SelectFormField_3111);
		types.add(ProcessElementTypes.TextFormField_3112);
		types.add(ProcessElementTypes.TextAreaFormField_3113);
		types.add(ProcessElementTypes.RichTextAreaFormField_3128);
		types.add(ProcessElementTypes.FileWidget_3119);
		types.add(ProcessElementTypes.CheckBoxMultipleFormField_3120);
		types.add(ProcessElementTypes.DurationFormField_3121);
		types.add(ProcessElementTypes.HiddenWidget_3122);
		types.add(ProcessElementTypes.ImageWidget_3123);
		types.add(ProcessElementTypes.MessageInfo_3124);
		types.add(ProcessElementTypes.TextInfo_3125);
		types.add(ProcessElementTypes.FormButton_3126);
		types.add(ProcessElementTypes.Table_3127);
		types.add(ProcessElementTypes.DynamicTable_3129);
		types.add(ProcessElementTypes.IFrameWidget_3130);
		types.add(ProcessElementTypes.HtmlWidget_3131);
		types.add(ProcessElementTypes.SuggestBox_3132);
		return types;
	}

}
