/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
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
package org.bonitasoft.studio.expression.editor.viewer;

import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author aurelie
 * 
 */
public class CellExpressionViewer extends ExpressionViewer {

    private EditExpressionDialog editDialog;

    private ColumnViewer columnViewer;

    public CellExpressionViewer(Composite composite, int style,
            TabbedPropertySheetWidgetFactory widgetFactory,
            EditingDomain editingDomain, EReference expressionReference) {
        super(composite, style, widgetFactory, editingDomain, expressionReference);
    }

    @Override
    protected void createToolbar(int style, TabbedPropertySheetWidgetFactory widgetFactory) {
        final Link editControl = new Link(control, SWT.NO_FOCUS);
        editControl.setText("<A>" + Messages.editAndContinue + "</A>");
        editControl.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        editControl.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(false, false).create());
        control.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        editControl.setData(SWTBOT_WIDGET_ID_KEY, SWTBOT_ID_EDITBUTTON);
        editControl.addDisposeListener(disposeListener);
        editControl.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                editDialog = CellExpressionViewer.this.createEditDialog();
                openEditDialog(editDialog);
            }
        });
    }

    @Override
    public void proposalAccepted(IContentProposal proposal) {
        super.proposalAccepted(proposal);
        columnViewer.getControl().getParent().setFocus();
    }

    @Override
    protected void openEditDialog(EditExpressionDialog dialog) {
        super.openEditDialog(dialog);
        columnViewer.refresh(null);
    }

    public void setColumnViewer(ColumnViewer columnViewer) {
        this.columnViewer = columnViewer;
    }

    public Shell getEditDialogShell() {
        if (editDialog != null) {
            return editDialog.getShell();
        }
        return null;

    }

}
