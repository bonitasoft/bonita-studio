/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.form.sections.general.contributions;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.gmf.tools.GMFTools.ElementTypeResolver;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.Group2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.GroupEditPart;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessElementTypes;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * 
 * Allow a form field to be modified into an other type of form field
 * 
 * @author Baptiste Mesta
 *
 */
public class FormFieldsElementTypeResolver  implements ElementTypeResolver {

	
	public IElementType getElementType(GraphicalEditPart parentEditPart, EClass targetEClass) {
		List<IElementType> allowedChildren = new ArrayList<IElementType>();
		if(parentEditPart instanceof FormEditPart){

			allowedChildren.add(ProcessElementTypes.CheckBoxMultipleFormField_2134);
			allowedChildren.add(ProcessElementTypes.CheckBoxSingleFormField_2130);
			allowedChildren.add(ProcessElementTypes.ComboFormField_2114);
			allowedChildren.add(ProcessElementTypes.DateFormField_2115);
			allowedChildren.add(ProcessElementTypes.DurationFormField_2137);
			allowedChildren.add(ProcessElementTypes.FileWidget_2133);
			allowedChildren.add(ProcessElementTypes.Group_2125);
			allowedChildren.add(ProcessElementTypes.HiddenWidget_2136);
			allowedChildren.add(ProcessElementTypes.ImageWidget_2135);
			allowedChildren.add(ProcessElementTypes.ListFormField_2116);
			allowedChildren.add(ProcessElementTypes.MessageInfo_2131);
			allowedChildren.add(ProcessElementTypes.NextFormButton_2127);
			allowedChildren.add(ProcessElementTypes.PasswordFormField_2117);
			allowedChildren.add(ProcessElementTypes.PreviousFormButton_2128);
			allowedChildren.add(ProcessElementTypes.RadioFormField_2118);
			allowedChildren.add(ProcessElementTypes.RichTextAreaFormField_2140);
			allowedChildren.add(ProcessElementTypes.SelectFormField_2119);
			allowedChildren.add(ProcessElementTypes.SubmitFormButton_2126);
			allowedChildren.add(ProcessElementTypes.FormButton_2138);
			allowedChildren.add(ProcessElementTypes.TextFormField_2120);
			allowedChildren.add(ProcessElementTypes.TextAreaFormField_2121);
			allowedChildren.add(ProcessElementTypes.TextInfo_2132);
			allowedChildren.add(ProcessElementTypes.Table_2139);
			allowedChildren.add(ProcessElementTypes.DynamicTable_2141);
			allowedChildren.add(ProcessElementTypes.IFrameWidget_2142);
			allowedChildren.add(ProcessElementTypes.HtmlWidget_2143);
			allowedChildren.add(ProcessElementTypes.SuggestBox_2144);
			
			
		}else if(parentEditPart instanceof GroupEditPart
				|| parentEditPart instanceof Group2EditPart){
			allowedChildren.add(ProcessElementTypes.CheckBoxMultipleFormField_3120);
			allowedChildren.add(ProcessElementTypes.CheckBoxSingleFormField_3118);
			allowedChildren.add(ProcessElementTypes.ComboFormField_3103);
			allowedChildren.add(ProcessElementTypes.DateFormField_3105);
			allowedChildren.add(ProcessElementTypes.DurationFormField_3121);
			allowedChildren.add(ProcessElementTypes.FileWidget_3119);
			allowedChildren.add(ProcessElementTypes.Group_3106);
			allowedChildren.add(ProcessElementTypes.HiddenWidget_3122);
			allowedChildren.add(ProcessElementTypes.ImageWidget_3123);
			allowedChildren.add(ProcessElementTypes.ListFormField_3107);
			allowedChildren.add(ProcessElementTypes.MessageInfo_3124);
			allowedChildren.add(ProcessElementTypes.NextFormButton_3115);
			allowedChildren.add(ProcessElementTypes.FormButton_3126);
			allowedChildren.add(ProcessElementTypes.PasswordFormField_3109);
			allowedChildren.add(ProcessElementTypes.PreviousFormButton_3114);
			allowedChildren.add(ProcessElementTypes.RadioFormField_3110);
			allowedChildren.add(ProcessElementTypes.RichTextAreaFormField_3128);
			allowedChildren.add(ProcessElementTypes.SelectFormField_3111);
			allowedChildren.add(ProcessElementTypes.SubmitFormButton_3116);
			allowedChildren.add(ProcessElementTypes.TextFormField_3112);
			allowedChildren.add(ProcessElementTypes.TextAreaFormField_3113);
			allowedChildren.add(ProcessElementTypes.TextInfo_3125);
			allowedChildren.add(ProcessElementTypes.Table_3127);
			allowedChildren.add(ProcessElementTypes.DynamicTable_3129);
			allowedChildren.add(ProcessElementTypes.IFrameWidget_3130);
			allowedChildren.add(ProcessElementTypes.HtmlWidget_3131);
			allowedChildren.add(ProcessElementTypes.SuggestBox_3132);
			
		}else{
			//can't use anymore ModelingAssistantService with popupbar because it has been deactivated
			//allowedChildren = ModelingAssistantService.getInstance().getTypesForPopupBar(parentEditPart);
		}
		
		for (IElementType child : allowedChildren) {
			if (child.getEClass().equals(targetEClass))
				return child;
		}


		return null;
	}

}
