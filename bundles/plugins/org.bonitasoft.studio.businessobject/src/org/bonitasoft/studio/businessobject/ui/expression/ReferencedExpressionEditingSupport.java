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
package org.bonitasoft.studio.businessobject.ui.expression;

import org.bonitasoft.studio.expression.editor.autocompletion.IExpressionProposalLabelProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewerCellEditor;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier - transform it to support Collection instead of only
 *         List
 */
public class ReferencedExpressionEditingSupport extends EditingSupport {

    protected Object context;

    private ViewerFilter filter;

    private EditingDomain editingDomain;

    private IExpressionNatureProvider expressionNatureProvider;

    private IExpressionProposalLabelProvider expressionProposalLabelProvider;

    private ExpressionViewerCellEditor editor;

    public ReferencedExpressionEditingSupport(final ColumnViewer viewer) {
        super(viewer);
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
        if (element instanceof Expression) {
            if (!((Expression) element).getReferencedElements().isEmpty()) {
                return ((Expression) element).getReferencedElements().get(0);
            }
        }
        return null;
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        if (editor == null) {
            editor = createEditor();
        }
        if (element instanceof Expression) {
            final Object value = getValue(element);
            if (value != null) {
                editor.setSelection(new StructuredSelection(value));
            }
        }

        return editor;
    }

    private ExpressionViewerCellEditor createEditor() {
        final ExpressionViewerCellEditor editor = new ExpressionViewerCellEditor(
                getViewer(),
                (Composite) getViewer().getControl(),
                editingDomain,
                null,
                true);
        if (expressionNatureProvider != null) {
            editor.setExpressionNatureProvider(expressionNatureProvider);
        }
        if (expressionProposalLabelProvider != null) {
            editor.setExpressionProposalLableProvider(expressionProposalLabelProvider);
        }
        if (filter != null) {
            editor.addFilter(filter);
        }
        if (context != null) {
            editor.setInput(context);
        }
        return editor;
    }

    @Override
    protected boolean canEdit(final Object element) {
        return true;
    }

    public void setInput(final Object input) {
        context = input;
    }

    public void setFilter(final ViewerFilter filter) {
        this.filter = filter;
    }

    public void setEditingDomain(final EditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    @Override
    protected void saveCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
        super.saveCellEditorValue(cellEditor, cell);
        getViewer().refresh();
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
