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
package org.bonitasoft.studio.common.jface;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * @author Romain Bioteau
 */
public class TreeExplorer extends Composite implements SWTBotConstants {

    private TreeViewer leftTree;
    private TableViewer rightTable;
    private ITreeContentProvider contentProvider;
    private ILabelProvider labelProvider;
    private Composite additionalComposite;

    public TreeExplorer(final Composite parent, final int style) {
        super(parent, style);
        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final Text searchField = new Text(this, SWT.BORDER | SWT.SEARCH | SWT.ICON_SEARCH | SWT.ICON_CANCEL);
        searchField.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        searchField.setMessage(Messages.filterLabel);
        searchField.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.common.jface.treeExplorer.searchFieldText");

        additionalComposite = new Composite(this, SWT.NONE);
        additionalComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).hint(0, 0).create());
        additionalComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());

        final Composite content = new Composite(this, SWT.BORDER | SWT.FLAT);
        content.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(3).equalWidth(false).spacing(0, 0).margins(0, 0).create());
        content.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());

        leftTree = new TreeViewer(content, SWT.V_SCROLL);
        leftTree.getTree().setData(SWTBOT_WIDGET_ID_KEY, SWTBOT_ID_EXPLORER_LEFT_TREE);
        leftTree.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(180, SWT.DEFAULT).create());
        final Label vSeparator = new Label(content, SWT.SEPARATOR | SWT.VERTICAL);
        vSeparator.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        rightTable = new TableViewer(content, SWT.NONE);
        rightTable.getTable().setData(SWTBOT_WIDGET_ID_KEY, SWTBOT_ID_EXPLORER_RIGHT_TABLE);
        rightTable.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(180, SWT.DEFAULT).create());
        rightTable.setSorter(new ViewerSorter());
        rightTable.addFilter(new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                final Object leftSelection = ((IStructuredSelection) leftTree.getSelection()).getFirstElement();
                final Set<Object> parents = new HashSet<>();
                final Set<String> candidates = new HashSet<>();
                Object p = contentProvider.getParent(element);
                if (p != null) {
                    parents.add(p);
                    candidates.add(labelProvider.getText(p));
                }
                while (p != null) {
                    p = contentProvider.getParent(p);
                    if (p != null) {
                        parents.add(p);
                        candidates.add(labelProvider.getText(p));
                    }
                }
                if (searchField.getText().isEmpty()) {
                    return leftSelection == null || parents.contains(leftSelection);
                }
                final String text = labelProvider.getText(element);
                candidates.add(text);
                return text != null &&
                        isCandidate(candidates, searchField.getText()) &&
                        (leftSelection == null || parents.contains(leftSelection));
            }
        });

        leftTree.addDoubleClickListener(event -> {
            final Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
            if (selection != null) {
                if (leftTree.getExpandedState(selection)) {
                    leftTree.collapseToLevel(selection, 1);
                } else {
                    leftTree.expandToLevel(selection, 1);
                }

            }
        });

        rightTable.addSelectionChangedListener(event -> {
            final Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
            if (selection != null) {
                final Object parent1 = contentProvider.getParent(selection);
                if (parent1 != null) {
                    leftTree.setSelection(new StructuredSelection(parent1), true);
                }
            }
        });

        searchField.addModifyListener(e -> Display.getDefault().asyncExec(() -> rightTable.refresh()));
    }

    protected boolean isCandidate(Set<String> candidates, String text) {
        for (final String c : candidates) {
            if (c.toLowerCase().contains(text.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    protected Object[] getSubtree(final Object selection) {
        final Set<Object> result = new HashSet<>();
        addChildren(result, selection);
        return result.toArray();
    }

    private void addChildren(final Set<Object> result, final Object element) {
        if (contentProvider.hasChildren(element)) {
            for (final Object c : contentProvider.getChildren(element)) {
                result.add(c);
                addChildren(result, c);
            }
        }
    }

    public void setContentProvider(final ITreeContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    public void addLeftTreeFilter(final ViewerFilter filter) {
        leftTree.addFilter(filter);
    }

    public void addRightTreeFilter(final ViewerFilter filter) {
        if (!Arrays.asList(rightTable.getFilters()).contains(filter)) {
            rightTable.addFilter(filter);
        }
    }

    public void removeRightTreeFilter(final ViewerFilter filter) {
        rightTable.removeFilter(filter);
    }

    public void removeLeftTreeFilter(final ViewerFilter filter) {
        leftTree.removeFilter(filter);
    }

    public void setLabelProvider(final ILabelProvider labelProvider) {
        this.labelProvider = labelProvider;
    }

    public void setInput(final Object input) {
        Assert.isNotNull(contentProvider);
        Assert.isNotNull(labelProvider);
        leftTree.setContentProvider(contentProvider);
        leftTree.setLabelProvider(labelProvider);
        rightTable.setContentProvider(new ArrayContentProvider());
        rightTable.setLabelProvider(labelProvider);
        leftTree.setInput(input);
    }

    public Composite getAdditionalComposite() {
        return additionalComposite;
    }

    public void setLeftHeader(final String title) {
        leftTree.getTree().setHeaderVisible(true);
        final TreeColumn columnName = new TreeColumn(leftTree.getTree(), SWT.NONE);
        columnName.setText(title);
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, 300));
        leftTree.getTree().setLayout(layout);
    }

    public void setRightHeader(final String title) {
        rightTable.getTable().setHeaderVisible(true);
        final TableColumn columnName = new TableColumn(rightTable.getTable(), SWT.NONE);
        columnName.setText(title);
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, 300));
        rightTable.getTable().setLayout(layout);
    }

    public TableViewer getRightTableViewer() {
        return rightTable;
    }

    public TreeViewer geLeftTreeViewer() {
        return leftTree;
    }

}
