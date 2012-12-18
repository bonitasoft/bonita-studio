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
package org.bonitasoft.studio.diagram.form.custom.providers;

import org.bonitasoft.studio.diagram.form.custom.parts.CustomCheckBoxFormField2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomCheckBoxFormFieldEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomComboFormField2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomComboFormFieldEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomDateFormField2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomDateFormFieldEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomFileWidget2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomFileWidgetEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomFormEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomListFormField2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomListFormFieldEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomNextFormButton2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomNextFormButtonEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomPasswordFormField2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomPasswordFormFieldEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomPreviousFormButton2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomPreviousFormButtonEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomRadioFormField2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomRadioFormFieldEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomSelectFormField2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomSelectFormFieldEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomSubmitFormButton2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomSubmitFormButtonEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomTextAreaFormField2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomTextAreaFormFieldEditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomTextFormField2EditPart;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomTextFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxSingleFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxSingleFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ComboFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ComboFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DateFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DateFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.NextFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.NextFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PasswordFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PasswordFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PreviousFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PreviousFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ProcessEditPartFactory;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RadioFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RadioFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SelectFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SelectFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SubmitFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SubmitFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextAreaFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextAreaFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Aurelien Pupier
 *
 */
public class CustomFormEditPartFactory extends ProcessEditPartFactory {
	
	/**
	 * Override in order to create acustom EditParts.
	 *  (non-Javadoc)
	 * @see org.bonitasoft.studio.model.process.diagram.form.edit.parts.ProcessEditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (ProcessVisualIDRegistry.getVisualID(view)) {
			case FormEditPart.VISUAL_ID:
				return new CustomFormEditPart(view);
			case CheckBoxSingleFormFieldEditPart.VISUAL_ID:
				return new CustomCheckBoxFormFieldEditPart(view);
			case CheckBoxSingleFormField2EditPart.VISUAL_ID:
				return new CustomCheckBoxFormField2EditPart(view);
			case PreviousFormButtonEditPart.VISUAL_ID:
				return new CustomPreviousFormButtonEditPart(view);
			case PreviousFormButton2EditPart.VISUAL_ID:
				return new CustomPreviousFormButton2EditPart(view);
			case ComboFormFieldEditPart.VISUAL_ID:
				return new CustomComboFormFieldEditPart(view);
			case ComboFormField2EditPart.VISUAL_ID:
				return new CustomComboFormField2EditPart(view);
			case NextFormButtonEditPart.VISUAL_ID:
				return new CustomNextFormButtonEditPart(view);
			case NextFormButton2EditPart.VISUAL_ID:
				return new CustomNextFormButton2EditPart(view);
			case DateFormFieldEditPart.VISUAL_ID:
				return new CustomDateFormFieldEditPart(view);
			case DateFormField2EditPart.VISUAL_ID:
				return new CustomDateFormField2EditPart(view);
			case ListFormFieldEditPart.VISUAL_ID:
				return new CustomListFormFieldEditPart(view);
			case ListFormField2EditPart.VISUAL_ID:
				return new CustomListFormField2EditPart(view);
			case SubmitFormButtonEditPart.VISUAL_ID:
				return new CustomSubmitFormButtonEditPart(view);
			case SubmitFormButton2EditPart.VISUAL_ID:
				return new CustomSubmitFormButton2EditPart(view);
			case PasswordFormFieldEditPart.VISUAL_ID:
				return new CustomPasswordFormFieldEditPart(view);
			case PasswordFormField2EditPart.VISUAL_ID:
				return new CustomPasswordFormField2EditPart(view);
			case RadioFormFieldEditPart.VISUAL_ID:
				return new CustomRadioFormFieldEditPart(view);
			case RadioFormField2EditPart.VISUAL_ID:
				return new CustomRadioFormField2EditPart(view);
			case SelectFormFieldEditPart.VISUAL_ID:
				return new CustomSelectFormFieldEditPart(view);
			case SelectFormField2EditPart.VISUAL_ID:
				return new CustomSelectFormField2EditPart(view);
			case TextAreaFormFieldEditPart.VISUAL_ID:
				return new CustomTextAreaFormFieldEditPart(view);
			case TextAreaFormField2EditPart.VISUAL_ID:
				return new CustomTextAreaFormField2EditPart(view);
			case TextFormFieldEditPart.VISUAL_ID:
				return new CustomTextFormFieldEditPart(view);
			case TextFormField2EditPart.VISUAL_ID:
				return new CustomTextFormField2EditPart(view);
			case FileWidgetEditPart.VISUAL_ID:
				return new CustomFileWidgetEditPart(view);
			case FileWidget2EditPart.VISUAL_ID:
				return new CustomFileWidget2EditPart(view);
			}
		}
		return super.createEditPart(context, model);
	}
	
	
	

}
