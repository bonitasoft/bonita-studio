/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard.control;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class StringListViewer {

    private final TableViewer viewer;

    private IObservableList input;

    public StringListViewer(final Composite parent, final String headerLabel, final String id, final WidgetFactory widgetFactory) {
        viewer = widgetFactory.newTableViewer(parent, id);
        viewer.setContentProvider(new ObservableListContentProvider());
        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().setLinesVisible(true);
        final TableViewerColumn tableViewerColumn = new TableViewerColumn(viewer, SWT.FILL);
        tableViewerColumn.getColumn().setText(headerLabel);
        tableViewerColumn.setLabelProvider(new ColumnLabelProvider());
        tableViewerColumn.setEditingSupport(new EditingSupport(viewer) {

            @SuppressWarnings("unchecked")
            @Override
            protected void setValue(final Object element, final Object value) {
                final int i = input.indexOf(element);
                if (!Objects.equals(element, value)) {
                    input.set(i, value);
                }
                viewer.getTable().select(i);
            }

            @Override
            protected Object getValue(final Object element) {
                return element;
            }

            @Override
            protected CellEditor getCellEditor(final Object element) {
                return new TextCellEditor((Composite) viewer.getControl());
            }

            @Override
            protected boolean canEdit(final Object element) {
                return true;
            }
        });
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, false));
        viewer.getTable().setLayout(layout);
    }

    public void setInput(final IObservableList input) {
        this.input = input;
        viewer.setInput(input);
    }

    public void editElement(final String element) {
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                viewer.editElement(element, 0);
            }
        });

    }

    @SuppressWarnings("unchecked")
    public List<String> getSelection() {
        return ((IStructuredSelection) viewer.getSelection()).toList();
    }

    public TableViewer getViewer() {
        return viewer;
    }

    public Control getControl() {
        return viewer.getControl();
    }
}
