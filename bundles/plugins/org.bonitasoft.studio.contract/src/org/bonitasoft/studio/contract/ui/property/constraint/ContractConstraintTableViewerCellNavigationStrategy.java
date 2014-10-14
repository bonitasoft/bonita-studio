/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.ui.property.constraint;

import org.eclipse.jface.viewers.CellNavigationStrategy;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TableItem;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintTableViewerCellNavigationStrategy extends CellNavigationStrategy {

    private final TableViewer tableViewer;
    private final ContractConstraintController constraintController;
    private boolean cancelEvent;

    public ContractConstraintTableViewerCellNavigationStrategy(final TableViewer treeViewer, final ContractConstraintController constraintController) {
        tableViewer = treeViewer;
        this.constraintController = constraintController;
    }

    @Override
    public boolean isNavigationEvent(final ColumnViewer viewer, final Event event) {
        switch (event.keyCode) {
            case SWT.CR:
                return true;
            case SWT.DEL:
                return true;
            default:
                return super.isNavigationEvent(viewer, event);
        }
    }

    @Override
    public ViewerCell findSelectedCell(final ColumnViewer viewer, final ViewerCell currentSelectedCell, final Event event) {
        setCancelEvent(false);
        if (currentSelectedCell != null) {
            switch (event.keyCode) {
                case SWT.CR:
                    return addNewRow(currentSelectedCell, event);
                case SWT.DEL:
                    return removeRow(currentSelectedCell);
                default:
                    break;
            }
        }
        return super.findSelectedCell(viewer, currentSelectedCell, event);
    }

    protected ViewerCell removeRow(final ViewerCell currentSelectedCell) {
        final ViewerCell aboveCell = currentSelectedCell.getNeighbor(ViewerCell.ABOVE, false);
        constraintController.removeConstraint(tableViewer);
        updateSelection(aboveCell);
        return aboveCell;
    }

    protected void updateSelection(final ViewerCell viewerCell) {
        if (viewerCell != null) {
            tableViewer.getTable().setSelection((TableItem) viewerCell.getViewerRow().getItem());
        }
    }

    protected ViewerCell addNewRow(final ViewerCell currentSelectedCell, final Event event) {
        if (isContractInputNameColumn(currentSelectedCell)) {
            ViewerCell nextCell = currentSelectedCell.getNeighbor(ViewerCell.BELOW, false);
            if (nextCell == null) {
                setCancelEvent(true);
                constraintController.addConstraint(tableViewer);
                nextCell = currentSelectedCell.getNeighbor(ViewerCell.BELOW, false);
                updateSelection(nextCell);
                return nextCell;
            }
        }
        return null;
    }

    private void setCancelEvent(final boolean cancelEvent) {
        this.cancelEvent = cancelEvent;
    }

    @Override
    public boolean shouldCancelEvent(final ColumnViewer viewer, final Event event) {
        return cancelEvent;
    }

    public boolean isContractInputNameColumn(final ViewerCell currentSelectedCell) {
        return currentSelectedCell.getColumnIndex() == 0;
    }

}
