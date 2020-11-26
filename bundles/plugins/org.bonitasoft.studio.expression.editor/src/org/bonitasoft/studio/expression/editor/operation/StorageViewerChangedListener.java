/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.operation;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Link;

class StorageViewerChangedListener implements ISelectionChangedListener {

    private final OperationViewer operationViewer;

    StorageViewerChangedListener(final OperationViewer operationViewer) {
        this.operationViewer = operationViewer;
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
        final Expression selectedExpression = (Expression) selection.getFirstElement();
        if (selectedExpression != null) {
            operationViewer.getActionExpression().validate();
            refreshOperatorLink();
            operationViewer.refreshActionExpressionTooltip(selectedExpression);
        }
    }


    private void refreshOperatorLink() {
        final Link operatorLink = operationViewer.getOperatorLink();
        if (operatorLink != null) {
            operatorLink.getParent().layout(true, true);
        }
    }


}
