
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
package org.bonitasoft.studio.model.process.diagram.form.parsers;

import java.text.FieldPosition;
import java.text.MessageFormat;
import java.text.ParsePosition;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditorPlugin;
import org.bonitasoft.studio.model.process.diagram.form.part.Messages;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.tooling.runtime.parsers.AbstractAttributeParser;
import org.eclipse.osgi.util.NLS;

/**
 * @generated
 */
public class MessageFormatParser extends AbstractAttributeParser {

	/**
	* @generated
	*/
	private String defaultPattern;
	/**
	* @generated
	*/
	private String defaultEditablePattern;

	/**
	* @generated
	*/
	private MessageFormat viewProcessor;

	/**
	* @generated
	*/
	private MessageFormat editorProcessor;

	/**
	* @generated
	*/
	private MessageFormat editProcessor;

	/**
	* @generated
	*/
	public MessageFormatParser(EAttribute[] features) {
		super(features);
	}

	/**
	* @generated
	*/
	public MessageFormatParser(EAttribute[] features, EAttribute[] editableFeatures) {
		super(features, editableFeatures);
	}

	/**
	* @generated
	*/
	protected String getDefaultPattern() {
		if (defaultPattern == null) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < features.length; i++) {
				if (i > 0) {
					sb.append(' ');
				}
				sb.append('{');
				sb.append(i);
				sb.append('}');
			}
			defaultPattern = sb.toString();
		}
		return defaultPattern;
	}

	/**
	* @generated
	*/
	public void setViewPattern(String viewPattern) {
		super.setViewPattern(viewPattern);
		viewProcessor = null;
	}

	/**
	* @generated
	*/
	public void setEditorPattern(String editorPattern) {
		super.setEditorPattern(editorPattern);
		editorProcessor = null;
	}

	/**
	* @generated
	*/
	protected MessageFormat getViewProcessor() {
		if (viewProcessor == null) {
			viewProcessor = new MessageFormat(getViewPattern() == null ? getDefaultPattern() : getViewPattern());
		}
		return viewProcessor;
	}

	/**
	* @generated
	*/
	protected MessageFormat getEditorProcessor() {
		if (editorProcessor == null) {
			editorProcessor = new MessageFormat(
					getEditorPattern() == null ? getDefaultEditablePattern() : getEditorPattern());
		}
		return editorProcessor;
	}

	/**
	* @generated
	*/
	protected String getDefaultEditablePattern() {
		if (defaultEditablePattern == null) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < editableFeatures.length; i++) {
				if (i > 0) {
					sb.append(' ');
				}
				sb.append('{');
				sb.append(i);
				sb.append('}');
			}
			defaultEditablePattern = sb.toString();
		}
		return defaultEditablePattern;
	}

	/**
	* @generated
	*/
	public void setEditPattern(String editPattern) {
		super.setEditPattern(editPattern);
		editProcessor = null;
	}

	/**
	* @generated
	*/
	protected MessageFormat getEditProcessor() {
		if (editProcessor == null) {
			editProcessor = new MessageFormat(
					getEditPattern() == null ? getDefaultEditablePattern() : getEditPattern());
		}
		return editProcessor;
	}

	/**
	* @generated
	*/
	public String getEditString(IAdaptable adapter, int flags) {
		EObject element = (EObject) adapter.getAdapter(EObject.class);
		return getEditorProcessor().format(getEditableValues(element), new StringBuffer(), new FieldPosition(0))
				.toString();
	}

	/**
	* @generated
	*/
	public IParserEditStatus isValidEditString(IAdaptable adapter, String editString) {
		ParsePosition pos = new ParsePosition(0);
		Object[] values = getEditProcessor().parse(editString, pos);
		if (values == null) {
			return new ParserEditStatus(FormDiagramEditorPlugin.ID, IParserEditStatus.UNEDITABLE,
					NLS.bind(Messages.MessageFormatParser_InvalidInputError, new Integer(pos.getErrorIndex())));
		}
		return validateNewValues(values);
	}

	/**
	* @generated
	*/
	public ICommand getParseCommand(IAdaptable adapter, String newString, int flags) {
		Object[] values = getEditProcessor().parse(newString, new ParsePosition(0));
		if (values == null || validateNewValues(values).getCode() != IParserEditStatus.EDITABLE) {
			return UnexecutableCommand.INSTANCE;
		}
		EObject element = (EObject) adapter.getAdapter(EObject.class);
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(element);
		if (editingDomain == null) {
			return UnexecutableCommand.INSTANCE;
		}
		CompositeTransactionalCommand command = new CompositeTransactionalCommand(editingDomain, "Set Values"); //$NON-NLS-1$
		for (int i = 0; i < values.length; i++) {
			command.compose(getModificationCommand(element, editableFeatures[i], values[i]));
		}
		if (element instanceof Expression) {
			command.compose(
					getModificationCommand(element, ExpressionPackage.eINSTANCE.getExpression_Name(), values[0]));
		}
		return command;
	}

	/**
	* @generated
	*/
	public String getPrintString(IAdaptable adapter, int flags) {
		EObject element = (EObject) adapter.getAdapter(EObject.class);
		return getViewProcessor().format(getValues(element), new StringBuffer(), new FieldPosition(0)).toString();
	}

}
