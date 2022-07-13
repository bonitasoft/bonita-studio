/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.pages;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.connector.wizard.sapjco3.i18n.Messages;
import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapFunctionField;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * @author Maxence Raoux
 * 
 */
public abstract class AbstractInputOutputPage extends AbstractPage {

	protected Button addField;
	protected Button addEmptyField;
	protected Button removeField;
	protected ExpressionCollectionViewer colViewer;
	protected Label functionNameLabel;
	private org.eclipse.swt.widgets.Text descriptionText;

	@Override
	public Control doCreateControl(Composite parent,
			EMFDataBindingContext context) {
		if (this.libraryLoaded && sapTool != null && sapTool.userWantToUseIt && sapTool.connectionOK) {
			return createExtendedWizard(parent, context);
		} else {
			return super.doCreateControl(parent, context);
		}
	}

	protected abstract Control createExtendedWizard(Composite parent,
			EMFDataBindingContext context);

	protected void updateAll() {
		if (sapTool != null) {
			if (functionNameLabel != null) {
				functionNameLabel.setText(sapTool.selectedFunction);
			}
			if (descriptionText != null) {
				descriptionText.setText(sapTool.getFunction(sapTool.selectedFunction).getDescription());
			}
		}
	}

	protected void createFunctionInfos(Composite parent) {
		final Composite functionComposite = new Composite(parent, SWT.NONE);
		functionComposite.setLayout(GridLayoutFactory.fillDefaults()
				.numColumns(2).create());
		functionComposite.setLayoutData(GridDataFactory.fillDefaults()
				.grab(true, false).span(2, 1).create());

		functionNameLabel = new Label(functionComposite, SWT.NONE);
		functionNameLabel.setLayoutData(GridDataFactory.fillDefaults()
				.grab(true, false).span(3, 1).align(SWT.LEFT, SWT.CENTER)
				.create());
		functionNameLabel.setText(sapTool.selectedFunction);
		final Label functionDescriptionTitleLabel = new Label(
				functionComposite, SWT.NONE);
		functionDescriptionTitleLabel.setLayoutData(GridDataFactory
				.fillDefaults().grab(false, false).align(SWT.LEFT, SWT.CENTER)
				.create());
		functionDescriptionTitleLabel
				.setText(Messages.functionDescriptionTitle);

		descriptionText = new org.eclipse.swt.widgets.Text(functionComposite,
				SWT.READ_ONLY | SWT.WRAP);
		descriptionText.setLayoutData(GridDataFactory.fillDefaults()
				.indent(17, 0).align(SWT.LEFT, SWT.CENTER).grab(true, false)
				.create());
		descriptionText.setText(sapTool.getFunction(sapTool.selectedFunction).getDescription());
	}

	protected void createButtons(final Composite pageComposite) {
		final Composite buttonComposite = new Composite(pageComposite, SWT.NONE);
		buttonComposite.setLayout(GridLayoutFactory.fillDefaults()
				.numColumns(3).create());
		buttonComposite.setLayoutData(GridDataFactory.fillDefaults()
				.grab(true, false).align(SWT.CENTER, SWT.CENTER).create());
		addField = new Button(buttonComposite, SWT.FLAT);
		addEmptyField = new Button(buttonComposite, SWT.FLAT);
		removeField = new Button(buttonComposite, SWT.FLAT);
		removeField.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				removeSelectedRow(colViewer.getViewer());
			}
		});
		removeField.setText(Messages.removeRow);
	}

	protected void addEmptyRow(TableViewer viewer) {
		Object expressionInput = viewer.getInput();
		if (expressionInput instanceof TableExpression) {
			ListExpression rowExp = ExpressionFactory.eINSTANCE
					.createListExpression();
			EList<Expression> expressions = rowExp.getExpressions();
			for (int i = 0; i < viewer.getTable().getColumnCount(); i++) {
				expressions.add(ExpressionHelper.createConstantExpression(" ",
						String.class.getName()));
			}
			((TableExpression) expressionInput).getExpressions().add(rowExp);
		}
		viewer.refresh();
	}

	private void removeSelectedRow(TableViewer viewer) {
		int i = viewer.getTable().getSelectionIndex();
		if (i >= 0) {
			final AbstractExpression expr = (AbstractExpression) viewer
					.getInput();
			((TableExpression) expr).getExpressions().remove(i);
			viewer.refresh();
		}
	}

	protected void addFieldRows(SapFunctionField field) {
		final Object expressionInput = colViewer.getViewer().getInput();
		if (expressionInput instanceof TableExpression) {
			if (field.isStructure() || field.isTable()) {
				for (SapFunctionField child : field.getFieldsList()) {
					addFieldRows(child);
				}
			} else {
				if (canBeAdd(field)) {
					createExpressionField(field, expressionInput);
				}
			}
		}
		colViewer.getViewer().refresh();
	}

	protected void clearColViewer() {
		TableViewer viewer = colViewer.getViewer();
		final TableExpression exprs = (TableExpression) viewer.getInput();
		exprs.getExpressions().removeAll(exprs.getExpressions());
		viewer.refresh();
	}

	protected abstract void createExpressionField(SapFunctionField field,
			Object expressionInput);

	protected abstract Boolean canBeAdd(SapFunctionField field);
}
