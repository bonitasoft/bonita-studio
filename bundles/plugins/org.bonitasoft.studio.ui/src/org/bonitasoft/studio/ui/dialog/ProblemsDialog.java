/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.ui.dialog;

import java.util.Collection;

import org.bonitasoft.studio.ui.provider.TypedLabelProvider;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public abstract class ProblemsDialog<T> extends MessageDialog {

    private TableViewerColumn tableViewerColumn;

    public ProblemsDialog(Shell parentShell, String dialogTitle, String dialogMessage, int dialogImageType,
            String[] dialogButtonLabels) {
        super(parentShell, dialogTitle, null, dialogMessage, dialogImageType, dialogButtonLabels, 0);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.MessageDialog#createCustomArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createCustomArea(Composite parent) {
        Collection<T> input = getInput();
        Assert.isNotNull(input);
        if (input.isEmpty()) {
            return super.createCustomArea(parent);
        }
        TableViewer problemsViewer = new TableViewer(parent,
                SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
        problemsViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(350, 100).create());
        problemsViewer.setContentProvider(ArrayContentProvider.getInstance());
        problemsViewer.setComparator(getComparator());

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        problemsViewer.getTable().setLayout(layout);

        tableViewerColumn = new TableViewerColumn(problemsViewer, SWT.NONE);
        TypedLabelProvider<T> typedLabelProvider = getTypedLabelProvider();
        Assert.isNotNull(typedLabelProvider);
        tableViewerColumn.setLabelProvider(new LabelProviderBuilder<T>()
                .withTextProvider(typedLabelProvider::getText)
                .withImageProvider(typedLabelProvider::getImage)
                .withTooltipProvider(typedLabelProvider::getToolTipText)
                .createColumnLabelProvider());

        problemsViewer.setInput(input);
        ColumnViewerToolTipSupport.enableFor(problemsViewer);

        return problemsViewer.getControl();
    }

    protected ViewerComparator getComparator() {
        return new ViewerComparator();
    }

    @Override
    public void create() {
        super.create();
        if(tableViewerColumn != null) {
            tableViewerColumn.getColumn().pack();
        }
    }

    protected abstract TypedLabelProvider<T> getTypedLabelProvider();

    protected abstract Collection<T> getInput();

    @Override
    protected boolean isResizable() {
        return true;
    }

}
