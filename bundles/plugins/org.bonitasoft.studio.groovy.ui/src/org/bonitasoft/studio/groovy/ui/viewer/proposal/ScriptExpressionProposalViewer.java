/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.groovy.ui.viewer.proposal;

import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.Category;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.ScriptProposal;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class ScriptExpressionProposalViewer extends TreeViewer {

    private Cursor cursorHand;
    private IViewerObservableValue<Object> selectionObservable;
    private Cursor cursorArrow;

    public ScriptExpressionProposalViewer(Composite parent, int style) {
        super(parent, style);
        getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        setLabelProvider(new ScriptExpressionProposalLabelProvider());
        setContentProvider(new ScriptExpressionProposalContentProvider());
        selectionObservable = ViewerProperties.singleSelection().observe(this);

        addDragSupport(DND.DROP_MOVE, new Transfer[] { TextTransfer.getInstance() },
                dragSourceAdapter(selectionObservable));
        this.cursorHand = parent.getDisplay().getSystemCursor(SWT.CURSOR_HAND);
        this.cursorArrow = parent.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
        getTree().addMouseMoveListener(this::updateCursor);
        getTree().addTraverseListener(e -> {
            if (e.detail == SWT.TRAVERSE_RETURN) {
                e.doit = false; //Do not close parent dialog
            }
        });

        addFilter(emptyCategory());
    }

    private ViewerFilter emptyCategory() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof Category) {
                    return ((Category) element).hasProposals()
                            || !((Category) element).getSubcategories().isEmpty();
                }
                return true;
            }
        };
    }

    private void updateCursor(MouseEvent e) {
        ViewerCell cell = getCell(new Point(e.x, e.y));
        if (cell != null && cell.getElement() instanceof ScriptProposal) {
            cell.getControl().setCursor(cursorHand);
        } else if (cell != null) {
            cell.getControl().setCursor(cursorArrow);
        }
    }

    private DragSourceAdapter dragSourceAdapter(IObservableValue<Object> observable) {
        return new DragSourceAdapter() {

            @Override
            public void dragStart(DragSourceEvent event) {
                event.detail = DND.DROP_MOVE;
                dragSetData(event);
            }

            @Override
            public void dragSetData(DragSourceEvent event) {
                event.doit = observable.getValue() instanceof ScriptProposal;
                event.data = ((ScriptProposal) observable.getValue()).getId();
            }
        };
    }

}
