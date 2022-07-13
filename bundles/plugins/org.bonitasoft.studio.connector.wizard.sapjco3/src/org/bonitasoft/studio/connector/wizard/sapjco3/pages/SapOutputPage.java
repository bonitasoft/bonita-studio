/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.pages;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connector.wizard.sapjco3.i18n.Messages;
import org.bonitasoft.studio.connector.wizard.sapjco3.pages.dialogs.SapNewOutputDialog;
import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapFunctionField;
import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapTool;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Maxence Raoux
 * 
 */
public class SapOutputPage extends AbstractInputOutputPage {

	protected Control createExtendedWizard(Composite parent,
			EMFDataBindingContext context) {
		final Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).spacing(3, 10).create());
		pageComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		createFunctionInfos(pageComposite);
		createHtmlOutput(pageComposite,context);
		createColViewer(pageComposite);
		createButtons(pageComposite);

		configureAddFieldButton();
		configureAddEmptyFieldButton();

		return pageComposite;
	}

	private void createHtmlOutput(Composite pageComposite, EMFDataBindingContext context) {
		
		final Composite htmlComposite = new Composite(pageComposite, SWT.NONE);
		htmlComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		htmlComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		PageComponentSwitch componentSwitch = getPageComponentSwitch(context, htmlComposite);
		componentSwitch.doSwitch(this.getPage().getWidget().get(0));
	}

	protected void createColViewer(final Composite pageComposite) {
		final String outputName = "outputParameters";
		final Input input = getInput(outputName);
		final Array oldArray = (Array) this.getPage().getWidget().get(1);
		final ConnectorParameter parameter = getConnectorParameter(input);
		colViewer = new ExpressionCollectionViewer(pageComposite, 0, true,
				oldArray.getColsCaption().size(), true,
				oldArray.getColsCaption(), false, false);
		colViewer.setLayoutData(GridDataFactory.fillDefaults().grab(true, true)
				.span(2, 1).create());
		colViewer.setSelection(parameter.getExpression());
		final TableExpression exp = (TableExpression) parameter.getExpression();
		colViewer.setInput(exp);
	}

	private void configureAddEmptyFieldButton() {
		addEmptyField.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				addEmptyRow(colViewer.getViewer());
			}
		});
		addEmptyField.setText(Messages.newEmptyOutputButton);
	}

	private void configureAddFieldButton() {
		final SapNewOutputDialog newOutputDialog = new SapNewOutputDialog(
				this.getShell(), sapTool);
		addField.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				if (newOutputDialog.open() == 0) {
					List<SapFunctionField> newOutput = newOutputDialog
							.getSelectedElement();
					if (newOutput != null) {
						for (SapFunctionField sapFunctionField : newOutput) {
							addFieldRows(sapFunctionField);
						}
					}
				}
			}
		});
		addField.setText(Messages.newOutputButton);
	}

	protected Boolean canBeAdd(SapFunctionField field) {
		if (field.isStructure() || field.isTable()) {
			return false;
		} else {
			final Object expressionInput = colViewer.getViewer().getInput();
			if (expressionInput instanceof TableExpression) {
				EList<ListExpression> expressions = ((TableExpression) expressionInput)
						.getExpressions();
				for (ListExpression listExpression : expressions) {
					EList<Expression> l = listExpression.getExpressions();
					if (l != null && l.size() >= 4) {
						String type = l.get(0).getContent();
						String tableName = l.get(1).getContent();
						String parameterName = l.get(3).getContent();
						if (field.getParent() == null
								&& type.equals(SapTool.OUTPUT_PREFIX+ "" + field.getInputType())
								&& tableName.equals(" ")
								&& field.getName().equals(parameterName)) {
							return false;
						}
						if (field.getParent() != null
								&& type.equals(SapTool.OUTPUT_PREFIX+ "" + field.getParent().getInputType())
								&& field.getParent().getName().equals(tableName)
								&& field.getName().equals(parameterName)) {
							return false;
						}
					}
				}
			}
			return true;
		}
	}

	protected void createExpressionField(SapFunctionField field,
			Object expressionInput) {
		ListExpression rowExp = ExpressionFactory.eINSTANCE
				.createListExpression();
		EList<Expression> expressions = rowExp.getExpressions();
		SapFunctionField parent = field.getParent();
		if (parent != null && (parent.isStructure() || parent.isTable())) {
			if (parent.isTable()) {
				expressions.add(ExpressionHelper.createConstantExpression(
						parent.getInputType() + "" + SapTool.OUTPUT_SUFFIX,
						String.class.getName()));
			} else {
				expressions.add(ExpressionHelper.createConstantExpression(
								SapTool.OUTPUT_PREFIX + "" + parent.getInputType(),
								String.class.getName()));
			}
			expressions.add(ExpressionHelper.createConstantExpression(
					parent.getName(), String.class.getName()));
		} else {
			if (field.isTable()) {
				expressions.add(ExpressionHelper.createConstantExpression(
						field.getInputType() + ""+ SapTool.OUTPUT_SUFFIX,
						String.class.getName()));
			} else {
				expressions.add(ExpressionHelper.createConstantExpression(
								SapTool.OUTPUT_PREFIX + ""+ field.getInputType(),
								String.class.getName()));
			}
			expressions.add(ExpressionHelper.createConstantExpression(" ",
					String.class.getName()));
		}
		expressions.add(ExpressionHelper.createConstantExpression(
				field.getName(), String.class.getName()));
		((TableExpression) expressionInput).getExpressions().add(rowExp);
	}

	public void updateRef() {
		if (colViewer != null&& !functionNameLabel.getText().equalsIgnoreCase(sapTool.selectedFunction)) {
			clearColViewer();
			updateAll();
		}
	}

}
