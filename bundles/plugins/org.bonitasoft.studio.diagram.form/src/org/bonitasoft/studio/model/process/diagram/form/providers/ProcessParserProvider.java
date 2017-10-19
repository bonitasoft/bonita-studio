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
package org.bonitasoft.studio.model.process.diagram.form.providers;

import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.HiddenWidget;
import org.bonitasoft.studio.model.form.HtmlWidget;
import org.bonitasoft.studio.model.form.IFrameWidget;
import org.bonitasoft.studio.model.form.MessageInfo;
import org.bonitasoft.studio.model.process.ProcessPackage;
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
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HiddenWidgetDisplayLabelShowDisplayEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HiddenWidgetLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HtmlWidgetName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HtmlWidgetNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.IFrameWidgetName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.IFrameWidgetNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ImageWidgetName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ImageWidgetNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormFieldName2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormFieldNameEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.MessageInfoLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.MessageInfoLabelEditPart;
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
import org.bonitasoft.studio.model.process.diagram.form.parsers.MessageFormatParser;
import org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class ProcessParserProvider extends AbstractProvider implements IParserProvider {

	/**
	* @generated
	*/
	private IParser checkBoxSingleFormFieldName_5148Parser;

	/**
	* @generated
	*/
	private IParser getCheckBoxSingleFormFieldName_5148Parser() {
		if (checkBoxSingleFormFieldName_5148Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			checkBoxSingleFormFieldName_5148Parser = parser;
		}
		return checkBoxSingleFormFieldName_5148Parser;
	}

	/**
	* @generated
	*/
	private IParser comboFormFieldName_5150Parser;

	/**
	* @generated
	*/
	private IParser getComboFormFieldName_5150Parser() {
		if (comboFormFieldName_5150Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			comboFormFieldName_5150Parser = parser;
		}
		return comboFormFieldName_5150Parser;
	}

	/**
	* @generated
	*/
	private IParser dateFormFieldName_5151Parser;

	/**
	* @generated
	*/
	private IParser getDateFormFieldName_5151Parser() {
		if (dateFormFieldName_5151Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			dateFormFieldName_5151Parser = parser;
		}
		return dateFormFieldName_5151Parser;
	}

	/**
	* @generated
	*/
	private IParser listFormFieldName_5152Parser;

	/**
	* @generated
	*/
	private IParser getListFormFieldName_5152Parser() {
		if (listFormFieldName_5152Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			listFormFieldName_5152Parser = parser;
		}
		return listFormFieldName_5152Parser;
	}

	/**
	* @generated
	*/
	private IParser passwordFormFieldName_5153Parser;

	/**
	* @generated
	*/
	private IParser getPasswordFormFieldName_5153Parser() {
		if (passwordFormFieldName_5153Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			passwordFormFieldName_5153Parser = parser;
		}
		return passwordFormFieldName_5153Parser;
	}

	/**
	* @generated
	*/
	private IParser radioFormFieldName_5154Parser;

	/**
	* @generated
	*/
	private IParser getRadioFormFieldName_5154Parser() {
		if (radioFormFieldName_5154Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			radioFormFieldName_5154Parser = parser;
		}
		return radioFormFieldName_5154Parser;
	}

	/**
	* @generated
	*/
	private IParser selectFormFieldName_5155Parser;

	/**
	* @generated
	*/
	private IParser getSelectFormFieldName_5155Parser() {
		if (selectFormFieldName_5155Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			selectFormFieldName_5155Parser = parser;
		}
		return selectFormFieldName_5155Parser;
	}

	/**
	* @generated
	*/
	private IParser suggestBoxName_5156Parser;

	/**
	* @generated
	*/
	private IParser getSuggestBoxName_5156Parser() {
		if (suggestBoxName_5156Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			suggestBoxName_5156Parser = parser;
		}
		return suggestBoxName_5156Parser;
	}

	/**
	* @generated
	*/
	private IParser textFormFieldName_5157Parser;

	/**
	* @generated
	*/
	private IParser getTextFormFieldName_5157Parser() {
		if (textFormFieldName_5157Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			textFormFieldName_5157Parser = parser;
		}
		return textFormFieldName_5157Parser;
	}

	/**
	* @generated
	*/
	private IParser textAreaFormFieldName_5158Parser;

	/**
	* @generated
	*/
	private IParser getTextAreaFormFieldName_5158Parser() {
		if (textAreaFormFieldName_5158Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			textAreaFormFieldName_5158Parser = parser;
		}
		return textAreaFormFieldName_5158Parser;
	}

	/**
	* @generated
	*/
	private IParser richTextAreaFormFieldName_5159Parser;

	/**
	* @generated
	*/
	private IParser getRichTextAreaFormFieldName_5159Parser() {
		if (richTextAreaFormFieldName_5159Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			richTextAreaFormFieldName_5159Parser = parser;
		}
		return richTextAreaFormFieldName_5159Parser;
	}

	/**
	* @generated
	*/
	private IParser submitFormButtonName_5160Parser;

	/**
	* @generated
	*/
	private IParser getSubmitFormButtonName_5160Parser() {
		if (submitFormButtonName_5160Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			submitFormButtonName_5160Parser = parser;
		}
		return submitFormButtonName_5160Parser;
	}

	/**
	* @generated
	*/
	private IParser nextFormButtonName_5161Parser;

	/**
	* @generated
	*/
	private IParser getNextFormButtonName_5161Parser() {
		if (nextFormButtonName_5161Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			nextFormButtonName_5161Parser = parser;
		}
		return nextFormButtonName_5161Parser;
	}

	/**
	* @generated
	*/
	private IParser previousFormButtonName_5162Parser;

	/**
	* @generated
	*/
	private IParser getPreviousFormButtonName_5162Parser() {
		if (previousFormButtonName_5162Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			previousFormButtonName_5162Parser = parser;
		}
		return previousFormButtonName_5162Parser;
	}

	/**
	* @generated
	*/
	private IParser messageInfoName_5135Parser;

	/**
	* @generated
	*/
	private IParser getMessageInfoName_5135Parser() {
		if (messageInfoName_5135Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			messageInfoName_5135Parser = parser;
		}
		return messageInfoName_5135Parser;
	}

	/**
	* @generated
	*/
	private IParser textInfoName_5188Parser;

	/**
	* @generated
	*/
	private IParser getTextInfoName_5188Parser() {
		if (textInfoName_5188Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			textInfoName_5188Parser = parser;
		}
		return textInfoName_5188Parser;
	}

	/**
	* @generated
	*/
	private IParser fileWidgetName_5189Parser;

	/**
	* @generated
	*/
	private IParser getFileWidgetName_5189Parser() {
		if (fileWidgetName_5189Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			fileWidgetName_5189Parser = parser;
		}
		return fileWidgetName_5189Parser;
	}

	/**
	* @generated
	*/
	private IParser checkBoxMultipleFormFieldName_5190Parser;

	/**
	* @generated
	*/
	private IParser getCheckBoxMultipleFormFieldName_5190Parser() {
		if (checkBoxMultipleFormFieldName_5190Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			checkBoxMultipleFormFieldName_5190Parser = parser;
		}
		return checkBoxMultipleFormFieldName_5190Parser;
	}

	/**
	* @generated
	*/
	private IParser imageWidgetName_5191Parser;

	/**
	* @generated
	*/
	private IParser getImageWidgetName_5191Parser() {
		if (imageWidgetName_5191Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			imageWidgetName_5191Parser = parser;
		}
		return imageWidgetName_5191Parser;
	}

	/**
	* @generated
	*/
	private IParser hiddenWidgetName_5141Parser;

	/**
	* @generated
	*/
	private IParser getHiddenWidgetName_5141Parser() {
		if (hiddenWidgetName_5141Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			hiddenWidgetName_5141Parser = parser;
		}
		return hiddenWidgetName_5141Parser;
	}

	/**
	* @generated
	*/
	private IParser durationFormFieldName_5192Parser;

	/**
	* @generated
	*/
	private IParser getDurationFormFieldName_5192Parser() {
		if (durationFormFieldName_5192Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			durationFormFieldName_5192Parser = parser;
		}
		return durationFormFieldName_5192Parser;
	}

	/**
	* @generated
	*/
	private IParser formButtonName_5193Parser;

	/**
	* @generated
	*/
	private IParser getFormButtonName_5193Parser() {
		if (formButtonName_5193Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			formButtonName_5193Parser = parser;
		}
		return formButtonName_5193Parser;
	}

	/**
	* @generated
	*/
	private IParser tableName_5194Parser;

	/**
	* @generated
	*/
	private IParser getTableName_5194Parser() {
		if (tableName_5194Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			tableName_5194Parser = parser;
		}
		return tableName_5194Parser;
	}

	/**
	* @generated
	*/
	private IParser dynamicTableName_5195Parser;

	/**
	* @generated
	*/
	private IParser getDynamicTableName_5195Parser() {
		if (dynamicTableName_5195Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			dynamicTableName_5195Parser = parser;
		}
		return dynamicTableName_5195Parser;
	}

	/**
	* @generated
	*/
	private IParser iFrameWidgetName_5196Parser;

	/**
	* @generated
	*/
	private IParser getIFrameWidgetName_5196Parser() {
		if (iFrameWidgetName_5196Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			iFrameWidgetName_5196Parser = parser;
		}
		return iFrameWidgetName_5196Parser;
	}

	/**
	* @generated
	*/
	private IParser htmlWidgetName_5197Parser;

	/**
	* @generated
	*/
	private IParser getHtmlWidgetName_5197Parser() {
		if (htmlWidgetName_5197Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			htmlWidgetName_5197Parser = parser;
		}
		return htmlWidgetName_5197Parser;
	}

	/**
	* @generated
	*/
	private IParser previousFormButtonName_5163Parser;

	/**
	* @generated
	*/
	private IParser getPreviousFormButtonName_5163Parser() {
		if (previousFormButtonName_5163Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			previousFormButtonName_5163Parser = parser;
		}
		return previousFormButtonName_5163Parser;
	}

	/**
	* @generated
	*/
	private IParser checkBoxSingleFormFieldName_5149Parser;

	/**
	* @generated
	*/
	private IParser getCheckBoxSingleFormFieldName_5149Parser() {
		if (checkBoxSingleFormFieldName_5149Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			checkBoxSingleFormFieldName_5149Parser = parser;
		}
		return checkBoxSingleFormFieldName_5149Parser;
	}

	/**
	* @generated
	*/
	private IParser comboFormFieldName_5164Parser;

	/**
	* @generated
	*/
	private IParser getComboFormFieldName_5164Parser() {
		if (comboFormFieldName_5164Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			comboFormFieldName_5164Parser = parser;
		}
		return comboFormFieldName_5164Parser;
	}

	/**
	* @generated
	*/
	private IParser nextFormButtonName_5165Parser;

	/**
	* @generated
	*/
	private IParser getNextFormButtonName_5165Parser() {
		if (nextFormButtonName_5165Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			nextFormButtonName_5165Parser = parser;
		}
		return nextFormButtonName_5165Parser;
	}

	/**
	* @generated
	*/
	private IParser dateFormFieldName_5166Parser;

	/**
	* @generated
	*/
	private IParser getDateFormFieldName_5166Parser() {
		if (dateFormFieldName_5166Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			dateFormFieldName_5166Parser = parser;
		}
		return dateFormFieldName_5166Parser;
	}

	/**
	* @generated
	*/
	private IParser listFormFieldName_5167Parser;

	/**
	* @generated
	*/
	private IParser getListFormFieldName_5167Parser() {
		if (listFormFieldName_5167Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			listFormFieldName_5167Parser = parser;
		}
		return listFormFieldName_5167Parser;
	}

	/**
	* @generated
	*/
	private IParser submitFormButtonName_5168Parser;

	/**
	* @generated
	*/
	private IParser getSubmitFormButtonName_5168Parser() {
		if (submitFormButtonName_5168Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			submitFormButtonName_5168Parser = parser;
		}
		return submitFormButtonName_5168Parser;
	}

	/**
	* @generated
	*/
	private IParser passwordFormFieldName_5169Parser;

	/**
	* @generated
	*/
	private IParser getPasswordFormFieldName_5169Parser() {
		if (passwordFormFieldName_5169Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			passwordFormFieldName_5169Parser = parser;
		}
		return passwordFormFieldName_5169Parser;
	}

	/**
	* @generated
	*/
	private IParser radioFormFieldName_5170Parser;

	/**
	* @generated
	*/
	private IParser getRadioFormFieldName_5170Parser() {
		if (radioFormFieldName_5170Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			radioFormFieldName_5170Parser = parser;
		}
		return radioFormFieldName_5170Parser;
	}

	/**
	* @generated
	*/
	private IParser selectFormFieldName_5171Parser;

	/**
	* @generated
	*/
	private IParser getSelectFormFieldName_5171Parser() {
		if (selectFormFieldName_5171Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			selectFormFieldName_5171Parser = parser;
		}
		return selectFormFieldName_5171Parser;
	}

	/**
	* @generated
	*/
	private IParser textFormFieldName_5172Parser;

	/**
	* @generated
	*/
	private IParser getTextFormFieldName_5172Parser() {
		if (textFormFieldName_5172Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			textFormFieldName_5172Parser = parser;
		}
		return textFormFieldName_5172Parser;
	}

	/**
	* @generated
	*/
	private IParser textAreaFormFieldName_5173Parser;

	/**
	* @generated
	*/
	private IParser getTextAreaFormFieldName_5173Parser() {
		if (textAreaFormFieldName_5173Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			textAreaFormFieldName_5173Parser = parser;
		}
		return textAreaFormFieldName_5173Parser;
	}

	/**
	* @generated
	*/
	private IParser richTextAreaFormFieldName_5174Parser;

	/**
	* @generated
	*/
	private IParser getRichTextAreaFormFieldName_5174Parser() {
		if (richTextAreaFormFieldName_5174Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			richTextAreaFormFieldName_5174Parser = parser;
		}
		return richTextAreaFormFieldName_5174Parser;
	}

	/**
	* @generated
	*/
	private IParser fileWidgetName_5175Parser;

	/**
	* @generated
	*/
	private IParser getFileWidgetName_5175Parser() {
		if (fileWidgetName_5175Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			fileWidgetName_5175Parser = parser;
		}
		return fileWidgetName_5175Parser;
	}

	/**
	* @generated
	*/
	private IParser checkBoxMultipleFormFieldName_5176Parser;

	/**
	* @generated
	*/
	private IParser getCheckBoxMultipleFormFieldName_5176Parser() {
		if (checkBoxMultipleFormFieldName_5176Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			checkBoxMultipleFormFieldName_5176Parser = parser;
		}
		return checkBoxMultipleFormFieldName_5176Parser;
	}

	/**
	* @generated
	*/
	private IParser durationFormFieldName_5177Parser;

	/**
	* @generated
	*/
	private IParser getDurationFormFieldName_5177Parser() {
		if (durationFormFieldName_5177Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			durationFormFieldName_5177Parser = parser;
		}
		return durationFormFieldName_5177Parser;
	}

	/**
	* @generated
	*/
	private IParser hiddenWidgetName_5145Parser;

	/**
	* @generated
	*/
	private IParser getHiddenWidgetName_5145Parser() {
		if (hiddenWidgetName_5145Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			hiddenWidgetName_5145Parser = parser;
		}
		return hiddenWidgetName_5145Parser;
	}

	/**
	* @generated
	*/
	private IParser imageWidgetName_5178Parser;

	/**
	* @generated
	*/
	private IParser getImageWidgetName_5178Parser() {
		if (imageWidgetName_5178Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			imageWidgetName_5178Parser = parser;
		}
		return imageWidgetName_5178Parser;
	}

	/**
	* @generated
	*/
	private IParser messageInfoName_5147Parser;

	/**
	* @generated
	*/
	private IParser getMessageInfoName_5147Parser() {
		if (messageInfoName_5147Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			messageInfoName_5147Parser = parser;
		}
		return messageInfoName_5147Parser;
	}

	/**
	* @generated
	*/
	private IParser textInfoName_5179Parser;

	/**
	* @generated
	*/
	private IParser getTextInfoName_5179Parser() {
		if (textInfoName_5179Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			textInfoName_5179Parser = parser;
		}
		return textInfoName_5179Parser;
	}

	/**
	* @generated
	*/
	private IParser formButtonName_5180Parser;

	/**
	* @generated
	*/
	private IParser getFormButtonName_5180Parser() {
		if (formButtonName_5180Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			formButtonName_5180Parser = parser;
		}
		return formButtonName_5180Parser;
	}

	/**
	* @generated
	*/
	private IParser tableName_5181Parser;

	/**
	* @generated
	*/
	private IParser getTableName_5181Parser() {
		if (tableName_5181Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			tableName_5181Parser = parser;
		}
		return tableName_5181Parser;
	}

	/**
	* @generated
	*/
	private IParser dynamicTableName_5182Parser;

	/**
	* @generated
	*/
	private IParser getDynamicTableName_5182Parser() {
		if (dynamicTableName_5182Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			dynamicTableName_5182Parser = parser;
		}
		return dynamicTableName_5182Parser;
	}

	/**
	* @generated
	*/
	private IParser iFrameWidgetName_5183Parser;

	/**
	* @generated
	*/
	private IParser getIFrameWidgetName_5183Parser() {
		if (iFrameWidgetName_5183Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			iFrameWidgetName_5183Parser = parser;
		}
		return iFrameWidgetName_5183Parser;
	}

	/**
	* @generated
	*/
	private IParser htmlWidgetName_5184Parser;

	/**
	* @generated
	*/
	private IParser getHtmlWidgetName_5184Parser() {
		if (htmlWidgetName_5184Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			htmlWidgetName_5184Parser = parser;
		}
		return htmlWidgetName_5184Parser;
	}

	/**
	* @generated
	*/
	private IParser suggestBoxName_5185Parser;

	/**
	* @generated
	*/
	private IParser getSuggestBoxName_5185Parser() {
		if (suggestBoxName_5185Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			suggestBoxName_5185Parser = parser;
		}
		return suggestBoxName_5185Parser;
	}

	/**
	* @generated
	*/
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case CheckBoxSingleFormFieldNameEditPart.VISUAL_ID:
			return getCheckBoxSingleFormFieldName_5148Parser();
		case ComboFormFieldNameEditPart.VISUAL_ID:
			return getComboFormFieldName_5150Parser();
		case DateFormFieldNameEditPart.VISUAL_ID:
			return getDateFormFieldName_5151Parser();
		case ListFormFieldNameEditPart.VISUAL_ID:
			return getListFormFieldName_5152Parser();
		case PasswordFormFieldNameEditPart.VISUAL_ID:
			return getPasswordFormFieldName_5153Parser();
		case RadioFormFieldNameEditPart.VISUAL_ID:
			return getRadioFormFieldName_5154Parser();
		case SelectFormFieldNameEditPart.VISUAL_ID:
			return getSelectFormFieldName_5155Parser();
		case SuggestBoxNameEditPart.VISUAL_ID:
			return getSuggestBoxName_5156Parser();
		case TextFormFieldNameEditPart.VISUAL_ID:
			return getTextFormFieldName_5157Parser();
		case TextAreaFormFieldNameEditPart.VISUAL_ID:
			return getTextAreaFormFieldName_5158Parser();
		case RichTextAreaFormFieldNameEditPart.VISUAL_ID:
			return getRichTextAreaFormFieldName_5159Parser();
		case SubmitFormButtonNameEditPart.VISUAL_ID:
			return getSubmitFormButtonName_5160Parser();
		case NextFormButtonNameEditPart.VISUAL_ID:
			return getNextFormButtonName_5161Parser();
		case PreviousFormButtonNameEditPart.VISUAL_ID:
			return getPreviousFormButtonName_5162Parser();
		case MessageInfoLabelEditPart.VISUAL_ID:
			return getMessageInfoName_5135Parser();
		case TextInfoNameEditPart.VISUAL_ID:
			return getTextInfoName_5188Parser();
		case FileWidgetNameEditPart.VISUAL_ID:
			return getFileWidgetName_5189Parser();
		case CheckBoxMultipleFormFieldNameEditPart.VISUAL_ID:
			return getCheckBoxMultipleFormFieldName_5190Parser();
		case ImageWidgetNameEditPart.VISUAL_ID:
			return getImageWidgetName_5191Parser();
		case HiddenWidgetDisplayLabelShowDisplayEditPart.VISUAL_ID:
			return getHiddenWidgetName_5141Parser();
		case DurationFormFieldNameEditPart.VISUAL_ID:
			return getDurationFormFieldName_5192Parser();
		case FormButtonNameEditPart.VISUAL_ID:
			return getFormButtonName_5193Parser();
		case TableNameEditPart.VISUAL_ID:
			return getTableName_5194Parser();
		case DynamicTableNameEditPart.VISUAL_ID:
			return getDynamicTableName_5195Parser();
		case IFrameWidgetNameEditPart.VISUAL_ID:
			return getIFrameWidgetName_5196Parser();
		case HtmlWidgetNameEditPart.VISUAL_ID:
			return getHtmlWidgetName_5197Parser();
		case PreviousFormButtonName2EditPart.VISUAL_ID:
			return getPreviousFormButtonName_5163Parser();
		case CheckBoxSingleFormFieldName2EditPart.VISUAL_ID:
			return getCheckBoxSingleFormFieldName_5149Parser();
		case ComboFormFieldName2EditPart.VISUAL_ID:
			return getComboFormFieldName_5164Parser();
		case NextFormButtonName2EditPart.VISUAL_ID:
			return getNextFormButtonName_5165Parser();
		case DateFormFieldName2EditPart.VISUAL_ID:
			return getDateFormFieldName_5166Parser();
		case ListFormFieldName2EditPart.VISUAL_ID:
			return getListFormFieldName_5167Parser();
		case SubmitFormButtonName2EditPart.VISUAL_ID:
			return getSubmitFormButtonName_5168Parser();
		case PasswordFormFieldName2EditPart.VISUAL_ID:
			return getPasswordFormFieldName_5169Parser();
		case RadioFormFieldName2EditPart.VISUAL_ID:
			return getRadioFormFieldName_5170Parser();
		case SelectFormFieldName2EditPart.VISUAL_ID:
			return getSelectFormFieldName_5171Parser();
		case TextFormFieldName2EditPart.VISUAL_ID:
			return getTextFormFieldName_5172Parser();
		case TextAreaFormFieldName2EditPart.VISUAL_ID:
			return getTextAreaFormFieldName_5173Parser();
		case RichTextAreaFormFieldName2EditPart.VISUAL_ID:
			return getRichTextAreaFormFieldName_5174Parser();
		case FileWidgetName2EditPart.VISUAL_ID:
			return getFileWidgetName_5175Parser();
		case CheckBoxMultipleFormFieldName2EditPart.VISUAL_ID:
			return getCheckBoxMultipleFormFieldName_5176Parser();
		case DurationFormFieldName2EditPart.VISUAL_ID:
			return getDurationFormFieldName_5177Parser();
		case HiddenWidgetLabelEditPart.VISUAL_ID:
			return getHiddenWidgetName_5145Parser();
		case ImageWidgetName2EditPart.VISUAL_ID:
			return getImageWidgetName_5178Parser();
		case MessageInfoLabel2EditPart.VISUAL_ID:
			return getMessageInfoName_5147Parser();
		case TextInfoName2EditPart.VISUAL_ID:
			return getTextInfoName_5179Parser();
		case FormButtonName2EditPart.VISUAL_ID:
			return getFormButtonName_5180Parser();
		case TableName2EditPart.VISUAL_ID:
			return getTableName_5181Parser();
		case DynamicTableName2EditPart.VISUAL_ID:
			return getDynamicTableName_5182Parser();
		case IFrameWidgetName2EditPart.VISUAL_ID:
			return getIFrameWidgetName_5183Parser();
		case HtmlWidgetName2EditPart.VISUAL_ID:
			return getHtmlWidgetName_5184Parser();
		case SuggestBoxName2EditPart.VISUAL_ID:
			return getSuggestBoxName_5185Parser();
		}
		return null;
	}

	/**
	* Utility method that consults ParserService
	* @generated
	*/
	public static IParser getParser(IElementType type, EObject object, String parserHint) {
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	* @generated
	*/
	public IParser getParser(IAdaptable hint) {
		final EObject element = (EObject) hint.getAdapter(EObject.class);
		if (!(element instanceof MessageInfo) && !(element instanceof HiddenWidget) && !(element instanceof HtmlWidget)
				&& !(element instanceof IFrameWidget)) {
			EAttribute[] features = new EAttribute[] { ExpressionPackage.eINSTANCE.getExpression_Content() };
			EAttribute[] editableFeatures = new EAttribute[] { ExpressionPackage.eINSTANCE.getExpression_Content() };
			return new MessageFormatParser(features, editableFeatures);
		} else {
			String vid = (String) hint.getAdapter(String.class);
			if (vid != null) {
				return getParser(ProcessVisualIDRegistry.getVisualID(vid));
			}
			View view = (View) hint.getAdapter(View.class);
			if (view != null) {
				return getParser(ProcessVisualIDRegistry.getVisualID(view));
			}
		}
		return null;
	}

	/**
	* @generated
	*/
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (ProcessElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	* @generated
	*/
	private static class HintAdapter extends ParserHintAdapter {

		/**
		* @generated
		*/
		private final IElementType elementType;

		/**
		* @generated
		*/
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		* @generated
		*/
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
