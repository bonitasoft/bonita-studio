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
package org.bonitasoft.studio.contract.ui.expression;

import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 */
public class ContractInputTableViewer extends TableViewer {

    public ContractInputTableViewer(final Composite parent, final int style) {
        super(parent, style);
    }

    public void initialize() {
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(100, false));
        getTable().setLayout(layout);
        getTable().setHeaderVisible(true);

        final TableViewerColumn columnViewer = new TableViewerColumn(this, SWT.NONE);
        final TableColumn column = columnViewer.getColumn();
        column.setText(Messages.name);
        columnViewer.setLabelProvider(new ContractInputLabelProvider());

        final TableColumnSorter sorter = new TableColumnSorter(this);
        sorter.setColumn(column);

        setContentProvider(ArrayContentProvider.getInstance());
    }

}
