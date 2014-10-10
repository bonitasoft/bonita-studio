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
package org.bonitasoft.studio.contract.ui.property.tree;

import org.eclipse.jface.viewers.CellNavigationStrategy;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TreeItem;


/**
 * @author Romain Bioteau
 *
 */
public class ContracInputTreeViewerCellNavigationStrategy extends CellNavigationStrategy {

    private final TreeViewer treeViewer;
    private final ContractInputController inputController;

    public ContracInputTreeViewerCellNavigationStrategy(final TreeViewer treeViewer, final ContractInputController inputController) {
        this.treeViewer = treeViewer;
        this.inputController = inputController;
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
        if (currentSelectedCell != null) {
            switch (event.keyCode) {
                case SWT.CR:
                    return addNewRow(currentSelectedCell);
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
        inputController.removeInput(treeViewer);
        updateSelection(aboveCell);
        return aboveCell;
    }

    protected void updateSelection(final ViewerCell viewerCell) {
        if (viewerCell != null) {
            treeViewer.getTree().setSelection((TreeItem) viewerCell.getViewerRow().getItem());
        }
    }

    protected ViewerCell addNewRow(final ViewerCell currentSelectedCell) {
        if (isContractInputNameColumn(currentSelectedCell)) {
            ViewerCell nextCell = currentSelectedCell.getNeighbor(ViewerCell.BELOW, false);
            if (nextCell == null) {
                inputController.addInput(treeViewer);
                nextCell = currentSelectedCell.getNeighbor(ViewerCell.BELOW, false);
                updateSelection(nextCell);
                return nextCell;
            }
        }
        return null;
    }

    public boolean isContractInputNameColumn(final ViewerCell currentSelectedCell) {
        return currentSelectedCell.getColumnIndex() == 0;
    }

}
