/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.expression.editor.pattern.richtext;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.List;

import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class VariableSelectionDialog extends Dialog {

    private List<Expression> expressions;
    private Expression selection;
    private DataBindingContext dbc;

    public VariableSelectionDialog(Shell shell, List<Expression> expressions) {
        super(shell);
        this.expressions = expressions;
    }
    
    @Override
    protected void configureShell(Shell newShell) {
        newShell.setText(Messages.selectVariableTitle);
        super.configureShell(newShell);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);

        TableViewer tableViewer = new TableViewer(composite, SWT.FULL_SELECTION | SWT.BORDER | SWT.SINGLE);
        tableViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        tableViewer.setContentProvider(ArrayContentProvider.getInstance());
        tableViewer.getTable().setHeaderVisible(true);
        TableViewerColumn nameColumn = new TableViewerColumn(tableViewer, SWT.FILL);
        ExpressionLabelProvider imageProvider = new ExpressionLabelProvider();
        nameColumn.setLabelProvider(new LabelProviderBuilder<Expression>()
                .withImageProvider(imageProvider::getImage)
                .withTextProvider(Expression::getName)
                .createColumnLabelProvider());
        nameColumn.getColumn().setText(Messages.name);
        nameColumn.getColumn().setWidth(180);

        TableViewerColumn typeColumn = new TableViewerColumn(tableViewer, SWT.FILL);
        typeColumn.setLabelProvider(new LabelProviderBuilder<Expression>()
                .withTextProvider(Expression::getReturnType)
                .createColumnLabelProvider());
        typeColumn.getColumn().setText(Messages.returnType);
        typeColumn.getColumn().setWidth(240);

        tableViewer.setInput(expressions);

        dbc = new DataBindingContext();
        dbc.bindValue(ViewerProperties.singleSelection().observe(tableViewer),
                PojoProperties.value("selection").observe(this),
                updateValueStrategy().withValidator(new IValidator<Expression>() {

                    @Override
                    public IStatus validate(Expression value) {
                        return value == null ? ValidationStatus.error("No variable selected") : ValidationStatus.ok();
                    }
                }).create(), null);

        return composite;
    }

    @Override
    protected Control createContents(Composite parent) {
        Control contents = super.createContents(parent);
        DialogSupport.create(this, dbc);
        return contents;
    }

    public Expression getSelection() {
        return selection;
    }

    public void setSelection(Expression selection) {
        this.selection = selection;
    }

}
