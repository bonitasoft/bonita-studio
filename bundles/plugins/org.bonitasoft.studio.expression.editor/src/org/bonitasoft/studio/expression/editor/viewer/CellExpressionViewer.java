/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class CellExpressionViewer extends ExpressionViewer {

    private EditExpressionDialog editDialog;

    private ColumnViewer columnViewer;

    public CellExpressionViewer(final Composite composite, final int style,
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final EditingDomain editingDomain, final EReference expressionReference, boolean enableEditExpression) {
        super(composite, style, widgetFactory, editingDomain, expressionReference, enableEditExpression);
    }

    @Override
    public void proposalAccepted(final IContentProposal proposal) {
        super.proposalAccepted(proposal);
        columnViewer.getControl().getParent().setFocus();
    }

    @Override
    protected void openEditDialog(final EditExpressionDialog dialog) {
        super.openEditDialog(dialog);
        columnViewer.refresh(null);
    }

    public void setColumnViewer(final ColumnViewer columnViewer) {
        this.columnViewer = columnViewer;
    }

    public Shell getEditDialogShell() {
        if (editDialog != null) {
            return editDialog.getShell();
        }
        return null;

    }

}
