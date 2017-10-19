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
public class ProcessModelingAssistantProviderOfFormEditPart extends ProcessModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(27);
		types.add(ProcessElementTypes.CheckBoxSingleFormField_2130);
		types.add(ProcessElementTypes.ComboFormField_2114);
		types.add(ProcessElementTypes.DateFormField_2115);
		types.add(ProcessElementTypes.ListFormField_2116);
		types.add(ProcessElementTypes.PasswordFormField_2117);
		types.add(ProcessElementTypes.RadioFormField_2118);
		types.add(ProcessElementTypes.SelectFormField_2119);
		types.add(ProcessElementTypes.SuggestBox_2144);
		types.add(ProcessElementTypes.TextFormField_2120);
		types.add(ProcessElementTypes.TextAreaFormField_2121);
		types.add(ProcessElementTypes.RichTextAreaFormField_2140);
		types.add(ProcessElementTypes.SubmitFormButton_2126);
		types.add(ProcessElementTypes.NextFormButton_2127);
		types.add(ProcessElementTypes.PreviousFormButton_2128);
		types.add(ProcessElementTypes.Group_2125);
		types.add(ProcessElementTypes.MessageInfo_2131);
		types.add(ProcessElementTypes.TextInfo_2132);
		types.add(ProcessElementTypes.FileWidget_2133);
		types.add(ProcessElementTypes.CheckBoxMultipleFormField_2134);
		types.add(ProcessElementTypes.ImageWidget_2135);
		types.add(ProcessElementTypes.HiddenWidget_2136);
		types.add(ProcessElementTypes.DurationFormField_2137);
		types.add(ProcessElementTypes.FormButton_2138);
		types.add(ProcessElementTypes.Table_2139);
		types.add(ProcessElementTypes.DynamicTable_2141);
		types.add(ProcessElementTypes.IFrameWidget_2142);
		types.add(ProcessElementTypes.HtmlWidget_2143);
		return types;
	}

}
