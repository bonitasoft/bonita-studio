/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

import org.bonitasoft.studio.expression.editor.autocompletion.IExpressionProposalLabelProvider;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class ExpressionViewerCellEditor extends CellEditor {

    private final EditingDomain editingDomain;

    private CellExpressionViewer viewer;

    private Listener focusListener;

    private final ColumnViewer columnViewer;

    private ControlDecoration deleteRow;

    private final SelectionListener removeRowListener;

    private final Composite parent;

    private boolean enableEditExpression;

    public ExpressionViewerCellEditor(final ColumnViewer columnViewer,
            final Composite parent, final EditingDomain editingDomain,
            final SelectionListener removeRowListener,
            boolean enableEditExpression) {
        super();
        this.enableEditExpression = enableEditExpression;
        create(parent);
        viewer.setColumnViewer(columnViewer);
        this.editingDomain = editingDomain;
        this.columnViewer = columnViewer;
        this.removeRowListener = null;
        this.parent = parent;
    }
    
    private void createRemoveRowDecorator(final Composite parent, final SelectionListener removeRowListener) {
        deleteRow = new ControlDecoration(parent.getParent(), SWT.LEFT | SWT.TOP) {

            @Override
            protected org.eclipse.swt.graphics.Rectangle getDecorationRectangle(final Control targetControl) {
                final Rectangle r = super.getDecorationRectangle(targetControl);
                if (ExpressionViewerCellEditor.this.getControl() != null
                        && ExpressionViewerCellEditor.this.getControl().getBounds() != null) {
                    final Rectangle bounds = ExpressionViewerCellEditor.this.getControl().getBounds();
                    final Point p = new Point(0, bounds.y);
                    int y = p.y;

                    int headerOffeset = 0;
                    if (((Table) parent).getHeaderVisible()) {
                        headerOffeset = bounds.height + 2;
                    } else {
                        headerOffeset = 4;
                    }
                    y = y + headerOffeset;

                    final Point newPoint = ((Table) parent).getVerticalBar().getSize();
                    r.y = r.y + y % newPoint.y;
                    r.width = r.width + 10;
                    return r;
                }
                return new Rectangle(0, 4, 0, 0);
            }
        };
        deleteRow.setImage(Pics.getImage("delete.png"));
        deleteRow.setMarginWidth(1);
        deleteRow.setDescriptionText(Messages.removeRow);
        deleteRow.addSelectionListener(removeRowListener);
        deleteRow.show();
    }

    @Override
    public void dispose() {
        super.dispose();
        if (viewer != null) {
            final Control viewerControl = viewer.getControl();
            if (viewerControl != null && !viewerControl.isDisposed()) {
                viewerControl.dispose();
            }
        }
        viewer = null;
        Display.getDefault().removeFilter(SWT.FocusIn, focusListener);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.CellEditor#createControl(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    protected Control createControl(final Composite parent) {
        if (viewer != null) {
            final Control viewerControl = viewer.getControl();
            if (viewerControl != null && !viewerControl.isDisposed()) {
                viewerControl.dispose();
            }
        }
        viewer = new CellExpressionViewer(parent, SWT.NONE, null, editingDomain, null, enableEditExpression);
        final Text text = (Text) viewer.getTextControl();
        text.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(20, SWT.DEFAULT).create());
        text.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
                handleDefaultSelection(e);
            }
        });

        text.addKeyListener(new KeyAdapter() {

            // hook key pressed - see PR 14201
            @Override
            public void keyPressed(final KeyEvent e) {
                keyReleaseOccured(e);

                // as a result of processing the above call, clients may have
                // disposed this cell editor
                if (getControl() == null || getControl().isDisposed()) {
                    return;
                }
            }
        });
        text.addTraverseListener(new TraverseListener() {

            @Override
            public void keyTraversed(final TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_ESCAPE
                        || e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                }
            }
        });
        
        viewer.addSelectionChangedListener(event -> parent.getDisplay().asyncExec(() -> {
            if(!isActivated()) {
                columnViewer.refresh(true);
            }
        }));

        final Control viewerControl = viewer.getControl();
        focusListener = new Listener() {

            @Override
            public void handleEvent(final Event e) {
                if (e.widget != null) {
                    final Composite widgetParentOFEvent = ((Control) e.widget).getParent();
                    if (viewerControl != null
                            && !viewerControl.isDisposed()
                            && widgetParentOFEvent != null
                            && !viewerControl.equals(widgetParentOFEvent.getParent())
                            && !viewerControl.equals(widgetParentOFEvent)
                            && !e.widget.getDisplay().getActiveShell().equals(viewer.getEditDialogShell())) {
                        if (!viewer.getContentProposal().hasProposalPopupFocus()) {
                            ExpressionViewerCellEditor.this.focusLost();
                            if (columnViewer.getControl() != null && !columnViewer.getControl().isDisposed()) {
                                columnViewer.refresh(true);
                            }
                        }
                    }
                }
            }
        };

        text.setFont(parent.getFont());
        return viewerControl;
    }
    
    @Override
    public LayoutData getLayoutData() {
        LayoutData layoutData = super.getLayoutData();
        layoutData.minimumWidth = 0;
        return layoutData;
    }
    
    @Override
    protected Object doGetValue() {
        return ((IStructuredSelection) viewer
                .getSelection()).getFirstElement();
    }

    @Override
    protected void doSetFocus() {
        viewer.getTextControl().setFocus();
        if (deleteRow != null) {
            deleteRow.hide();
            deleteRow.dispose();
        }
        if (removeRowListener != null) {
            createRemoveRowDecorator(parent, removeRowListener);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.CellEditor#doSetValue(java.lang.Object)
     */
    @Override
    protected void doSetValue(final Object value) {
        Expression exp = (Expression) ((IStructuredSelection) viewer
                .getSelection()).getFirstElement();
        if (exp == null && value instanceof Expression) {
            exp = (Expression) value;
        }
        if (exp != null) {
            viewer.setSelection(new StructuredSelection(exp));
        }
    }
    
    public void setInput(final Object input) {
        viewer.setInput(input);
    }

    public void setSelection(final ISelection selection) {
        viewer.setSelection(selection);
    }

    @Override
    public void activate() {
        super.activate();
        Display.getDefault().addFilter(SWT.FocusIn, focusListener);
    }

    @Override
    protected void focusLost() {
        super.focusLost();
        if (deleteRow != null) {
            deleteRow.hide();
            deleteRow.dispose();
        }
    }

    @Override
    public void deactivate() {
        super.deactivate();
        Display.getDefault().removeFilter(SWT.FocusIn, focusListener);
    }
    
    /**
     * Handles a default selection event from the text control by applying the
     * editor value and deactivating this cell editor.
     * 
     * @param event
     *        the selection event
     * @since 3.0
     */
    protected void handleDefaultSelection(final SelectionEvent event) {
        // same with enter-key handling code in keyReleaseOccured(e);
        fireApplyEditorValue();
        deactivate();
    }

    public void addFilter(final ViewerFilter filter) {
        viewer.addFilter(filter);
    }

    public void setContext(final EObject context) {
        viewer.setContext(context);
    }

    public void setExpressionNatureProvider(
            final IExpressionNatureProvider expressionNatureProvider) {
        viewer.setExpressionNatureProvider(expressionNatureProvider);
    }

    public void setExpressionProposalLableProvider(
            final IExpressionProposalLabelProvider expressionProposalLabelProvider) {
        viewer.setExpressionProposalLableProvider(expressionProposalLabelProvider);
    }

}
