/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.palette;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.model.form.FormPackage;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EClass;

/**
 * @author Romain Bioteau
 *
 */
public class FormPaletteLabelProvider {

    private static Map<Integer, String> classifierIDToTitle;
    private static Map<Integer, String> classifierIDToDescription;
    static {
        classifierIDToTitle = new HashMap<Integer, String>();
        classifierIDToDescription = new HashMap<Integer, String>();

        classifierIDToTitle.put(FormPackage.CHECK_BOX_SINGLE_FORM_FIELD, Messages.Checkbox_title);
        classifierIDToDescription.put(FormPackage.CHECK_BOX_SINGLE_FORM_FIELD, Messages.Checkbox_desc);

        classifierIDToTitle.put(FormPackage.CHECK_BOX_MULTIPLE_FORM_FIELD, Messages.CheckboxList_title);
        classifierIDToDescription.put(FormPackage.CHECK_BOX_MULTIPLE_FORM_FIELD, Messages.CheckboxList_desc);

        classifierIDToTitle.put(FormPackage.DATE_FORM_FIELD, Messages.Date_title);
        classifierIDToDescription.put(FormPackage.DATE_FORM_FIELD, Messages.Date_desc);

        classifierIDToTitle.put(FormPackage.PASSWORD_FORM_FIELD, Messages.Password_title);
        classifierIDToDescription.put(FormPackage.PASSWORD_FORM_FIELD, Messages.Password_desc);

        classifierIDToTitle.put(FormPackage.DURATION_FORM_FIELD, Messages.Duration_title);
        classifierIDToDescription.put(FormPackage.DURATION_FORM_FIELD, Messages.Duration_desc);

        classifierIDToTitle.put(FormPackage.LIST_FORM_FIELD, Messages.List_title);
        classifierIDToDescription.put(FormPackage.LIST_FORM_FIELD, Messages.List_desc);

        classifierIDToTitle.put(FormPackage.RADIO_FORM_FIELD, Messages.Radio_title);
        classifierIDToDescription.put(FormPackage.RADIO_FORM_FIELD, Messages.Radio_desc);

        classifierIDToTitle.put(FormPackage.SELECT_FORM_FIELD, Messages.Select_title);
        classifierIDToDescription.put(FormPackage.SELECT_FORM_FIELD, Messages.Select_desc);

        classifierIDToTitle.put(FormPackage.SUGGEST_BOX, Messages.SuggestBox_title);
        classifierIDToDescription.put(FormPackage.SUGGEST_BOX, Messages.SuggestBox_desc);

        classifierIDToTitle.put(FormPackage.TEXT_INFO, Messages.Text_title);
        classifierIDToDescription.put(FormPackage.TEXT_INFO, Messages.Text_desc);

        classifierIDToTitle.put(FormPackage.TEXT_FORM_FIELD, Messages.TextBox_title);
        classifierIDToDescription.put(FormPackage.TEXT_FORM_FIELD, Messages.TextBox_desc);

        classifierIDToTitle.put(FormPackage.TEXT_AREA_FORM_FIELD, Messages.TextArea_title);
        classifierIDToDescription.put(FormPackage.TEXT_AREA_FORM_FIELD, Messages.TextArea_desc);

        classifierIDToTitle.put(FormPackage.RICH_TEXT_AREA_FORM_FIELD, Messages.RichTextArea_title);
        classifierIDToDescription.put(FormPackage.RICH_TEXT_AREA_FORM_FIELD, Messages.RichTextArea_desc);

        classifierIDToTitle.put(FormPackage.MESSAGE_INFO, Messages.Message_title);
        classifierIDToDescription.put(FormPackage.MESSAGE_INFO, Messages.Message_desc);

        classifierIDToTitle.put(FormPackage.SUBMIT_FORM_BUTTON, Messages.Submit_title);
        classifierIDToDescription.put(FormPackage.SUBMIT_FORM_BUTTON, Messages.Submit_desc);

        classifierIDToTitle.put(FormPackage.PREVIOUS_FORM_BUTTON, Messages.Previous_title);
        classifierIDToDescription.put(FormPackage.PREVIOUS_FORM_BUTTON, Messages.Previous_desc);

        classifierIDToTitle.put(FormPackage.NEXT_FORM_BUTTON, Messages.Next_title);
        classifierIDToDescription.put(FormPackage.NEXT_FORM_BUTTON, Messages.Next_desc);

        classifierIDToTitle.put(FormPackage.FORM_BUTTON, Messages.SimpleButton_title);
        classifierIDToDescription.put(FormPackage.FORM_BUTTON, Messages.SimpleButton_desc);

        classifierIDToTitle.put(FormPackage.FILE_WIDGET, Messages.File_title);
        classifierIDToDescription.put(FormPackage.FILE_WIDGET, Messages.File_desc);

        classifierIDToTitle.put(FormPackage.IMAGE_WIDGET, Messages.Image_title);
        classifierIDToDescription.put(FormPackage.IMAGE_WIDGET, Messages.Image_desc);

        classifierIDToTitle.put(FormPackage.TABLE, Messages.Table_title);
        classifierIDToDescription.put(FormPackage.TABLE, Messages.Table_desc);

        classifierIDToTitle.put(FormPackage.DYNAMIC_TABLE, Messages.EditableGrid_title);
        classifierIDToDescription.put(FormPackage.DYNAMIC_TABLE, Messages.EditableGrid_desc);

        classifierIDToTitle.put(FormPackage.HIDDEN_WIDGET, Messages.Hidden_title);
        classifierIDToDescription.put(FormPackage.HIDDEN_WIDGET, Messages.Hidden_desc);

        classifierIDToTitle.put(FormPackage.IFRAME_WIDGET, Messages.IFrame_title);
        classifierIDToDescription.put(FormPackage.IFRAME_WIDGET, Messages.IFrame_desc);

        classifierIDToTitle.put(FormPackage.HTML_WIDGET, Messages.HTML_title);
        classifierIDToDescription.put(FormPackage.HTML_WIDGET, Messages.HTML_desc);

        classifierIDToTitle.put(FormPackage.GROUP, Messages.Group_title);
        classifierIDToDescription.put(FormPackage.GROUP, Messages.Group_desc);
    }

    public String getFormPaletteDescription(final EClass eClass) {
        final int id = getEClassifierId(eClass);
        return classifierIDToDescription.get(id);
    }

    public String getFormPaletteText(final EClass eClass) {
        final int id = getEClassifierId(eClass);
        final String label = classifierIDToTitle.get(id);
        Assert.isLegal(label != null, MessageFormat.format("EClass {0} is not supported.", eClass.getName()));
        return label;
    }

    protected int getEClassifierId(final EClass eClass) {
        Assert.isLegal(eClass != null);
        Assert.isLegal(FormPackage.Literals.WIDGET.isSuperTypeOf(eClass), MessageFormat.format("EClass {0} is not supported.", eClass.getName()));
        return eClass.getClassifierID();
    }


}
