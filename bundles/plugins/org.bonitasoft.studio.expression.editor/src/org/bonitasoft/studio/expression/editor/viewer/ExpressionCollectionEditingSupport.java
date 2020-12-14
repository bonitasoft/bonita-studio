/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.viewer;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.autocompletion.IExpressionProposalLabelProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier - transform it to support Collection instead of only
 *         List
 */
public class ExpressionCollectionEditingSupport extends EditingSupport {

    protected EObject context;
    protected final int colIndex;
    protected final List<ViewerFilter> filters = new ArrayList<>();
    protected EditingDomain editingDomain;
    private IExpressionNatureProvider expressionNatureProvider;
    private final SelectionListener removeRowListener;
    private IExpressionProposalLabelProvider expressionProposalLabelProvider;
    private Object input;
    private ExpressionViewerCellEditor editor;

    public ExpressionCollectionEditingSupport(final ColumnViewer viewer, final int colIndex,
            final SelectionListener removeRowListener) {
        this(viewer, colIndex, null, removeRowListener);
    }

    public ExpressionCollectionEditingSupport(final ColumnViewer viewer,
            final int colIndex, final EditingDomain editingDomain, final SelectionListener removeRowListener) {
        super(viewer);
        this.colIndex = colIndex;
        this.editingDomain = editingDomain;
        this.removeRowListener = removeRowListener;
    }

    @Override
    protected void initializeCellEditorValue(final CellEditor cellEditor,
            final ViewerCell cell) {
        super.initializeCellEditorValue(cellEditor, cell);
        cell.setImage(null);
        cell.setText("");
    }

    @Override
    protected void setValue(final Object element, final Object value) {
        getViewer().refresh();
        getViewer().getControl().notifyListeners(SWT.Selection, new Event());
    }

    @Override
    protected Object getValue(final Object element) {
        return element;
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        if (editor == null) {
            editor = createEditor();
        }
        if (element instanceof Expression) {
            editor.setSelection(new StructuredSelection(element));
        }
        if (element instanceof ListExpression) {
            final List<Expression> list = ((ListExpression) element).getExpressions();
            for (int i = 0; i <= colIndex; i++) {
                if (i < list.size()) {
                    if (list.get(i) == null) {
                        if (editingDomain != null) {
                            editingDomain
                                    .getCommandStack()
                                    .execute(
                                            AddCommand
                                                    .create(editingDomain,
                                                            element,
                                                            ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS,
                                                            ExpressionFactory.eINSTANCE
                                                                    .createExpression(),
                                                            i));
                        } else {
                            list.add(i, ExpressionFactory.eINSTANCE
                                    .createExpression());
                        }
                    }
                } else {
                    if (editingDomain != null) {
                        editingDomain
                                .getCommandStack()
                                .execute(
                                        AddCommand
                                                .create(editingDomain,
                                                        element,
                                                        ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS,
                                                        ExpressionFactory.eINSTANCE
                                                                .createExpression(),
                                                        i));
                    } else {
                        list.add(i,
                                ExpressionFactory.eINSTANCE.createExpression());
                    }
                }
            }
            editor.setSelection(new StructuredSelection(
                    ((ListExpression) element).getExpressions().get(colIndex)));
        }

        return editor;
    }

    private ExpressionViewerCellEditor createEditor() {
        final ExpressionViewerCellEditor editor = new ExpressionViewerCellEditor(
                getViewer(), (Composite) getViewer().getControl(),
                editingDomain, removeRowListener, filters.stream()
                        .anyMatch(f -> f.select(null, null, ExpressionHelper.createGroovyScriptExpression("", ""))));
        if (expressionNatureProvider != null) {
            editor.setExpressionNatureProvider(expressionNatureProvider);
        }
        if (expressionProposalLabelProvider != null) {
            editor.setExpressionProposalLableProvider(expressionProposalLabelProvider);
        }
        for (final ViewerFilter filter : filters) {
            editor.addFilter(filter);
        }
        if (context != null) {
            editor.setContext(context);
        }
        if (input != null) {
            editor.setInput(input);
        }
        return editor;
    }

    @Override
    protected boolean canEdit(final Object element) {
        return true;
    }

    public void setInput(final Object input) {
        this.input = input;
    }

    public void setContext(final EObject context) {
        this.context = context;
    }

    public void addFilter(final ViewerFilter filter) {
        filters.add(filter);
    }

    public void setEditingDomain(final EditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    @Override
    protected void saveCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
        super.saveCellEditorValue(cellEditor, cell);
        ExpressionCollectionEditingSupport.this.getViewer().refresh();
    }

    public void setExpressionNatureProvider(
            final IExpressionNatureProvider expressionNatureProvider) {
        this.expressionNatureProvider = expressionNatureProvider;
    }

    public void setExpressionProposalLableProvider(
            final IExpressionProposalLabelProvider expressionProposalLabelProvider) {
        this.expressionProposalLabelProvider = expressionProposalLabelProvider;
    }

}
