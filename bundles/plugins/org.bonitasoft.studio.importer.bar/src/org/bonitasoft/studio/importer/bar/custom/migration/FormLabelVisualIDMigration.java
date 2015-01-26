/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bar.custom.migration;

import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxMultipleFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxMultipleFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxSingleFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxSingleFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ComboFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ComboFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DateFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DateFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DurationFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DurationFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DynamicTableName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DynamicTableNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidgetName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidgetNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormButtonName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormButtonNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HtmlWidgetName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HtmlWidgetNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.IFrameWidgetName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.IFrameWidgetNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ImageWidgetName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ImageWidgetNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.NextFormButtonName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.NextFormButtonNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PasswordFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PasswordFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PreviousFormButtonName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PreviousFormButtonNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RadioFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RadioFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RichTextAreaFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RichTextAreaFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SelectFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SelectFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SubmitFormButtonName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SubmitFormButtonNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SuggestBoxName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SuggestBoxNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TableName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TableNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextAreaFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextAreaFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextInfoName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextInfoNameEditPart;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.gmf.runtime.notation.NotationPackage;


/**
 * @author Romain Bioteau
 *
 */
public class FormLabelVisualIDMigration extends ReportCustomMigration {

    private static final int COMBO_TYPE = 5102 ;
    private static final int DATE_TYPE = 5103 ;
    private static final int LIST_TYPE = 5104 ;
    private static final int PASSWORD_TYPE = 5105 ;
    private static final int RADIO_TYPE = 5106 ;
    private static final int SELECT_TYPE = 5107 ;
    private static final int TEXTFIELD_TYPE = 5108 ;
    private static final int TEXTAREA_TYPE = 5109 ;
    private static final int SUBMIT_TYPE = 5125 ;
    private static final int NEXT_TYPE = 5126 ;
    private static final int PREVIOUS_TYPE = 5127 ;
    private static final int CHECKBOX_TYPE = 5133 ;
    private static final int TEXT_TYPE = 5136 ;
    private static final int FILE_TYPE = 5138 ;
    private static final int MULTIPLECHECKBOX_TYPE = 5139 ;
    private static final int IMAGE_TYPE = 5140 ;
    private static final int DURATION_TYPE = 5142 ;
    private static final int BUTTON_TYPE = 5150 ;
    private static final int TABLE_TYPE = 5151 ;
    private static final int RICHTEXT_TYPE = 5153 ;
    private static final int DYNAMIC_TABLE_TYPE = 5155 ;
    private static final int IFRAME_TYPE = 5157 ;
    private static final int HTML_TYPE = 5159 ;
    private static final int SUGGESTBOX_TYPE = 5160 ;
    private static final int COMBO_TYPE_2 = 5115 ;
    private static final int DATE_TYPE_2 = 5117 ;
    private static final int LIST_TYPE_2 = 5118 ;
    private static final int PASSWORD_TYPE_2 = 5120 ;
    private static final int RADIO_TYPE_2 = 5121 ;
    private static final int SELECT_TYPE_2 = 5122 ;
    private static final int TEXTFIELD_TYPE_2 = 5123 ;
    private static final int TEXTAREA_TYPE_2 = 5124 ;
    private static final int PREVIOUS_TYPE_2 = 5128 ;
    private static final int NEXT_TYPE_2 = 5129 ;
    private static final int SUBMIT_TYPE_2 = 5130 ;
    private static final int CHECKBOX_TYPE_2 = 5134 ;
    private static final int FILE_TYPE_2 = 5137 ;
    private static final int MULTIPLECHECKBOX_TYPE_2 = 5143 ;
    private static final int DURATION_TYPE_2 = 5144 ;
    private static final int IMAGE_TYPE_2 = 5146 ;
    private static final int TEXT_TYPE_2 = 5148 ;
    private static final int BUTTON_TYPE_2 = 5149 ;
    private static final int TABLE_TYPE_2 = 5152 ;
    private static final int RICHTEXT_TYPE_2 = 5154 ;
    private static final int DYNAMIC_TABLE_TYPE_2 = 5156 ;
    private static final int IFRAME_TYPE_2 = 5158 ;
    private static final int HTML_TYPE_2 = 5161 ;
    private static final int SUGGESTBOX_TYPE_2 = 5162 ;
    
    @Override
    public void migrateBefore(Model model, Metamodel metamodel) throws MigrationException {
        for(Instance decorator :model.getAllInstances(NotationPackage.Literals.DECORATION_NODE)){
            int viewType = Integer.valueOf((String) decorator.get(NotationPackage.Literals.VIEW__TYPE));
            switch(viewType){
            case COMBO_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( ComboFormFieldNameEditPart.VISUAL_ID)); break;
            case COMBO_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( ComboFormFieldName2EditPart.VISUAL_ID)); break;
            case DATE_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( DateFormFieldNameEditPart.VISUAL_ID)); break;
            case DATE_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( DateFormFieldName2EditPart.VISUAL_ID)); break;
            case LIST_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( ListFormFieldNameEditPart.VISUAL_ID)); break;
            case LIST_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( ListFormFieldName2EditPart.VISUAL_ID)); break;
            case PASSWORD_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( PasswordFormFieldNameEditPart.VISUAL_ID)); break;
            case PASSWORD_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( PasswordFormFieldName2EditPart.VISUAL_ID)); break;
            case RADIO_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( RadioFormFieldNameEditPart.VISUAL_ID)); break;
            case RADIO_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( RadioFormFieldName2EditPart.VISUAL_ID)); break;
            case SELECT_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( SelectFormFieldNameEditPart.VISUAL_ID)); break;
            case SELECT_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( SelectFormFieldName2EditPart.VISUAL_ID)); break;
            case TEXTFIELD_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( TextFormFieldNameEditPart.VISUAL_ID)); break;
            case TEXTFIELD_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( TextFormFieldName2EditPart.VISUAL_ID)); break;
            case TEXTAREA_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( TextAreaFormFieldNameEditPart.VISUAL_ID)); break;
            case TEXTAREA_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( TextAreaFormFieldName2EditPart.VISUAL_ID)); break;
            case SUBMIT_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( SubmitFormButtonNameEditPart.VISUAL_ID)); break;
            case SUBMIT_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( SubmitFormButtonName2EditPart.VISUAL_ID)); break;
            case NEXT_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( NextFormButtonNameEditPart.VISUAL_ID)); break;
            case NEXT_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( NextFormButtonName2EditPart.VISUAL_ID)); break;
            case PREVIOUS_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( PreviousFormButtonNameEditPart.VISUAL_ID)); break;
            case PREVIOUS_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( PreviousFormButtonName2EditPart.VISUAL_ID)); break;
            case CHECKBOX_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( CheckBoxSingleFormFieldNameEditPart.VISUAL_ID)); break;
            case CHECKBOX_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( CheckBoxSingleFormFieldName2EditPart.VISUAL_ID)); break;
            case TEXT_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( TextInfoNameEditPart.VISUAL_ID)); break;
            case TEXT_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( TextInfoName2EditPart.VISUAL_ID)); break;
            case FILE_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( FileWidgetNameEditPart.VISUAL_ID)); break;
            case FILE_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( FileWidgetName2EditPart.VISUAL_ID)); break;
            case MULTIPLECHECKBOX_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( CheckBoxMultipleFormFieldNameEditPart.VISUAL_ID)); break;
            case MULTIPLECHECKBOX_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( CheckBoxMultipleFormFieldName2EditPart.VISUAL_ID)); break;
            case IMAGE_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( ImageWidgetNameEditPart.VISUAL_ID)); break;
            case IMAGE_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( ImageWidgetName2EditPart.VISUAL_ID)); break;
            case DURATION_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( DurationFormFieldNameEditPart.VISUAL_ID)); break;
            case DURATION_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( DurationFormFieldName2EditPart.VISUAL_ID)); break;
            case BUTTON_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( FormButtonNameEditPart.VISUAL_ID)); break;
            case BUTTON_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( FormButtonName2EditPart.VISUAL_ID)); break;
            case TABLE_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( TableNameEditPart.VISUAL_ID)); break;
            case TABLE_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( TableName2EditPart.VISUAL_ID)); break;
            case RICHTEXT_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( RichTextAreaFormFieldNameEditPart.VISUAL_ID)); break;
            case RICHTEXT_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( RichTextAreaFormFieldName2EditPart.VISUAL_ID)); break;
            case DYNAMIC_TABLE_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( DynamicTableNameEditPart.VISUAL_ID)); break;
            case DYNAMIC_TABLE_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( DynamicTableName2EditPart.VISUAL_ID)); break;
            case IFRAME_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( IFrameWidgetNameEditPart.VISUAL_ID)); break;
            case IFRAME_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( IFrameWidgetName2EditPart.VISUAL_ID)); break;
            case HTML_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( HtmlWidgetNameEditPart.VISUAL_ID)); break;
            case HTML_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( HtmlWidgetName2EditPart.VISUAL_ID)); break;
            case SUGGESTBOX_TYPE :decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( SuggestBoxNameEditPart.VISUAL_ID)); break;
            case SUGGESTBOX_TYPE_2: decorator.set(NotationPackage.Literals.VIEW__TYPE, String.valueOf( SuggestBoxName2EditPart.VISUAL_ID)); break;
            default: break;
            }
           
        }
    }

    @Override
    public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {
    
    }

}
