/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.sqlbuilder.ex.builder.viewer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaUpdateTreeElement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSource;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLResource;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.UpdateHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.VendorHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ObjectListHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.views.update.UpdateTreeElement;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaUpdateDesignViewer extends ContentViewer implements ISelectionProvider, ISelectionChangedListener {

    QueryUpdateStatement update;
    SQLDomainModel domainModel;

    Composite canvas;
    org.eclipse.swt.widgets.List setContentList;
    ObjectListHelper setContentHelper;
    String[] listSelections;
    int selectionNumber = 0;

    Button addButton;
    Button groupButton;
    Button removeButton;

    SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;

    BonitaUpdateTreeViewer updateTreeViewer;

    Object element;

    VendorHelper vendorHelper;

    protected Vector selectionListenerList = new Vector();
    private final List<Expression> filteredExpressions;

    public BonitaUpdateDesignViewer(SQLDomainModel sqlDomainModel, List<Expression> filteredExpressions) {
        domainModel = sqlDomainModel;
        this.filteredExpressions = filteredExpressions;
        vendorHelper = new VendorHelper(domainModel.getDatabase());
        // listen for model changes
        setContentProvider(domainModel.createContentProvider());
        update = null;
    }

    @Override
    public void setInput(Object input) {
        if (input instanceof QueryUpdateStatement) {
            update = (QueryUpdateStatement) input;
            initializeView();
            updateTreeViewer.setInput(input);
        }
        super.setInput(input);
    }

    public Control createControl(Composite parent) {
        final SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
        canvas = sashForm;

        setContentList = new org.eclipse.swt.widgets.List(canvas, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        // ratio 0.35
        PlatformUI.getWorkbench().getHelpSystem().setHelp(setContentList, SQLBuilderContextIds.SQDU_SET_TAB);

        setContentHelper = new ObjectListHelper(setContentList);
        final SetContentListener setContentListener = new SetContentListener();
        setContentList.addListener(SWT.Selection, setContentListener);

        final Composite buttonPanel = ViewUtility.createComposite(canvas, 1, true);
        //ratio 0.10
        addButton = ViewUtility.createPushButton(buttonPanel, ">");
        final ButtonSelectListener addButtonListener = new ButtonSelectListener();
        addButton.addSelectionListener(addButtonListener);
        addButton.setToolTipText(Messages._UI_TOOLTIP_UPDATE_ADD_COL);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(addButton, SQLBuilderContextIds.SQDU_SET_TAB);

        removeButton = ViewUtility.createPushButton(buttonPanel, "<");
        final ButtonSelectListener removeButtonListener = new ButtonSelectListener();
        removeButton.addSelectionListener(removeButtonListener);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(removeButton, SQLBuilderContextIds.SQDU_SET_TAB);

        groupButton = ViewUtility.createPushButton(buttonPanel, ">");
        groupButton.setImage(SQLBuilderPlugin.getPlugin().getImage(SQLResource.SQL_COLUMN_GROUP));
        groupButton.setToolTipText(Messages._UI_TOOLTIP_UPDATE_ADD_GRP);
        final ButtonSelectListener groupButtonListener = new ButtonSelectListener();
        groupButton.addSelectionListener(groupButtonListener);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(groupButton, SQLBuilderContextIds.SQDU_SET_TAB);

        updateTreeViewer = new BonitaUpdateTreeViewer(domainModel, filteredExpressions, canvas);
        // ratio 0.55
        updateTreeViewer.setInput(null);
        updateTreeViewer.addSelectionChangedListener(this);

        // Set weights for components of SashForm
        sashForm.setWeights(new int[] { 35, 10, 55 });

        hookControl(canvas);
        return getControl();
    }

    @Override
    public ISelection getSelection() {
        return null;
    }

    @Override
    public void setSelection(ISelection selection, boolean reveal) {
    }

    protected void initializeView() {
        if (update != null) {
            if (update.getTargetTable() != null) {
                populateSetList();
            }
            else {
                setContentList.removeAll();
            }
        }
        setContentHelper.deselectAll();
        addButton.setEnabled(false);
        removeButton.setEnabled(false);
        groupButton.setEnabled(false);
    }

    protected void populateSetList() {
        final Vector availColNames = new Vector();
        final TableExpression tableExpr = update.getTargetTable();
        final Iterator columnsItr = tableExpr.getColumnList().iterator(); //tableExpr.getColumnList().iterator();
        final List targetColList = UpdateHelper.getTargetColumns(update);
        String currentName, targetName;
        ValueExpressionColumn currentValExprCol;
        while (columnsItr.hasNext()) {
            currentValExprCol = (ValueExpressionColumn) columnsItr.next();
            currentName = currentValExprCol.getName();
            if (targetColList != null) {
                final Iterator targetListItr = targetColList.iterator();
                boolean found = false;
                while (!(found) && targetListItr.hasNext()) {
                    targetName = ((ValueExpressionColumn) targetListItr.next()).getName();
                    if (currentName.equals(targetName)) {
                        found = true;
                    }
                }
                if (!found) {
                    availColNames.add(currentName);
                }
            }
            else {
                availColNames.add(currentName);
            }
        }
        final String[] listColumns = new String[availColNames.size()];
        final Iterator addColumns = availColNames.iterator();
        int count = 0;
        while (addColumns.hasNext()) {
            final String nextCol = (String) addColumns.next();
            listColumns[count] = nextCol;
            count++;
        }
        setContentList.setItems(listColumns);
    }

    Column findSelectionInColumnList(String selection, Iterator columnList) {
        Column result = null;
        while (columnList.hasNext()) {
            final ValueExpressionColumn columnExpression = (ValueExpressionColumn) columnList.next();
            final Column currentColumn = TableHelper.getColumnForColumnExpression(ExpressionHelper.getTableExprForValueExpressionColumn(columnExpression),
                    columnExpression);
            final String columnName = currentColumn.getName();
            if (columnName.equals(selection)) {
                result = currentColumn;
                break;
            }
        }
        return result;
    }

    @Override
    public Control getControl() {
        return canvas;
    }

    @Override
    public Object getInput() {
        return update;
    }

    @Override
    public void refresh() {
        if (getInput() != null) {
            initializeView();
        }
    }

    @Override
    public void inputChanged(Object newElement, Object oldElement) {
        initializeView();
    }

    // implement ISelectionProvider
    //
    @Override
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        selectionListenerList.add(listener);
    }

    // implement ISelectionChangedListener
    //
    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        final Object selection = ((StructuredSelection) updateTreeViewer.getSelection()).getFirstElement();
        if (selection instanceof UpdateTreeElement) {
            final UpdateTreeElement treeElement = (UpdateTreeElement) selection;
            if (treeElement != null) {
                removeButton.setEnabled(true);
            }
            else {
                removeButton.setEnabled(false);
            }
        }
        notifySelectionChanged(event);
    }

    public void notifySelectionChanged(SelectionChangedEvent event) {
        for (final Iterator i = selectionListenerList.iterator(); i.hasNext();) {
            final ISelectionChangedListener listener = (ISelectionChangedListener) i.next();
            listener.selectionChanged(event);
        }
    }

    class SetContentListener implements Listener {

        @Override
        public void handleEvent(Event e) {
            selectionNumber = setContentHelper.getSelectionCount();
            listSelections = setContentHelper.getSelection();

            if (selectionNumber == 1) {
                addButton.setEnabled(true);
                enableGroupButton();
            }
            else if (selectionNumber > 1) {
                addButton.setEnabled(false);
                enableGroupButton();
            }
            else {
                addButton.setEnabled(false);
                groupButton.setEnabled(false);
            }
        }

        private void enableGroupButton() {
            if (!vendorHelper.isCloudscape() && !vendorHelper.isSybase()) {
                groupButton.setEnabled(true);
            }
        }
    }

    class ButtonSelectListener implements SelectionListener {

        @Override
        public void widgetDefaultSelected(SelectionEvent e) {
        }

        @Override
        public void widgetSelected(SelectionEvent e) {
            final TableExpression tableExpr = update.getTargetTable();
            if (e.widget == addButton) {
                final Column column = TableHelper.getColumnForName((TableInDatabase) tableExpr, listSelections[0]);
                if (column != null) {
                    UpdateHelper.addColumn(update, column);
                }
            }
            else if (e.widget == groupButton) {
                final List columns = new ArrayList();
                for (int i = 0; i < selectionNumber; i++) {
                    final Column column = TableHelper.getColumnForName((TableInDatabase) tableExpr, listSelections[i]);
                    if (column != null) {
                        columns.add(column);
                    }
                }
                if (!columns.isEmpty()) {
                    UpdateHelper.addColumns(update, columns);
                }
            }
            else if (e.widget == removeButton) {
                final Object selection = ((StructuredSelection) updateTreeViewer.getSelection()).getFirstElement();
                final BonitaUpdateTreeElement localElement = (BonitaUpdateTreeElement) selection;
                final UpdateSource source = localElement.getUpdateSource();
                if (localElement.hasChildren()) {
                    if (source != null) {
                        final List exprList = update.getAssignmentClause();
                        final UpdateAssignmentExpression expr = source.getUpdateAssignmentExpr();
                        exprList.remove(expr);
                    }
                }
                else {
                    final ValueExpressionColumn selectedColumn = ((BonitaUpdateTreeElement) selection).getRDBColumn();
                    if (selectedColumn != null) {
                        UpdateHelper.removeColumn(update, selectedColumn);
                    }
                }
            }
            refresh();

            updateTreeViewer.refresh();
            updateTreeViewer.setInput(update);
            setContentHelper.deselectAll();
        }
    }

    public void setEnabled(boolean enable) {
        if (enable) {
            setContentList.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        }
        else {
            setContentList.setBackground(canvas.getBackground());
        }
        updateTreeViewer.setEnabled(enable);

    }
}
