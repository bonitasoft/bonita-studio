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

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.bonitasoft.studio.sqlbuilder.ex.builder.BonitaBuilderUtility;
import org.eclipse.datatools.modelbase.sql.query.Grouping;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist;
import org.eclipse.datatools.modelbase.sql.query.GroupingSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupType;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.VendorHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.util.StringUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.EditComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridContentProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ObjectListHelper;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;


public class BonitaGroupByContentViewer extends ContentViewer {

    static protected final String P_NONE = Messages._UI_NONE;
    static protected final String P_SIMPLE = Messages._UI_SIMPLE;
    static protected final String P_CUBE = Messages._UI_CUBE;
    static protected final String P_ROLLUP = Messages._UI_ROLLUP;
    static protected final String P_GROUPING = Messages._UI_GROUPING;
    static protected final String P_GROUPINGSETS = Messages._UI_GROUPINGSETS;
    static protected final String P_GROUPINGROOT = Messages._UI_COMBOBOXITEM_GROUPING_ROOT;
    static protected final String P_GROUPINGEXPRESSIONLIST = "GroupExpressionList"; //$NON-NLS-1$

    SQLDomainModel sqlDomainModel;

    protected List groupByClause = null;
    protected SQLQueryObject selectStatement = null;

    Composite canvas;
    org.eclipse.swt.widgets.List groupByContentList;
    Combo typeCombo;
    ObjectListHelper groupByContentHelper;
    GroupExpressionListGridViewer expressionGrid;


    public BonitaGroupByContentViewer(SQLDomainModel model) {
        sqlDomainModel = model;
        // listen for model changes
        setContentProvider(model.createContentProvider());
    }

    Object currentListSelection = null;
    String currentType = ""; //$NON-NLS-1$

    @Override
    public void setInput(Object input) {
        if (input instanceof QuerySelectStatement || input instanceof QuerySelect) {
            selectStatement = (SQLQueryObject) input;
            setGroupByClause();
            refreshListBox();
            currentListSelection = groupByClause;
            groupByContentHelper.select(groupByClause);
            expressionGrid.setInput(groupByClause);
            updateTypeCombo();
        }
        super.setInput(input);
    }

    protected void setGroupByClause() {
        QuerySelect qSelect = null;
        if (selectStatement instanceof QuerySelectStatement) {
            final QuerySelectStatement selectStmt = (QuerySelectStatement) selectStatement;
            qSelect = SelectHelper.getQuerySelect(selectStmt);
            if (qSelect == null) {
                SelectHelper.initSelectStmt(selectStmt);
                qSelect = SelectHelper.getQuerySelect(selectStmt);
            }
        }
        else if (selectStatement instanceof QuerySelect) {
            qSelect = (QuerySelect) selectStatement;
        }
        if (qSelect != null) {
            groupByClause = qSelect.getGroupByClause();
        }
    }

    int indent = 0;

    // this includes groupby content and grouping set's children
    public Vector getGroupByContentList() {
        final Vector items = new Vector();
        final Iterator iterator = groupByClause.iterator();
        while (iterator.hasNext()) {
            final Object item = iterator.next();
            if (!(item instanceof GroupingExpression)) {
                items.add(item);
                if (item instanceof GroupingSets) {
                    getGroupingSetList((GroupingSets) item, items);
                }
                else if (item instanceof SuperGroup) {
                    getSuperGroupList((SuperGroup) item, items);
                }
            }
        }

        return items;
    }

    @Override
    public Control getControl() {
        return canvas;
    }

    protected void getGroupingSetList(GroupingSets groupingSet, Vector items) {
        final Iterator iterator = groupingSet.getGroupingSetsElementList().iterator();
        while (iterator.hasNext()) {
            Object item = iterator.next();
            if (item instanceof GroupingSetsElementExpression) {
                item = ((GroupingSetsElementExpression) item).getGrouping();
                if (item instanceof SuperGroup) {
                    items.add(item);
                    getSuperGroupList((SuperGroup) item, items);
                }
            }
            else if (item instanceof GroupingSetsElementSublist) {
                items.add(item);
                final Iterator grouping = ((GroupingSetsElementSublist) item).getGroupingSetsElementExprList().iterator();
                while (grouping.hasNext()) {
                    Object groupingItem = grouping.next();
                    if (groupingItem instanceof GroupingSetsElementExpression) {
                        groupingItem = ((GroupingSetsElementExpression) groupingItem).getGrouping();
                        if (groupingItem instanceof SuperGroup) {
                            items.add(groupingItem);
                            getSuperGroupList((SuperGroup) groupingItem, items);
                        }
                    }
                    else if (groupingItem instanceof GroupingSetsElementSublist) {
                        items.add(groupingItem);
                        getExpressionList((GroupingSetsElementSublist) groupingItem, items);
                    }
                }
            }
        }
    }

    protected void getSuperGroupList(SuperGroup superGroup, Vector items) {
        final Iterator iterator = superGroup.getSuperGroupElementList().iterator();
        while (iterator.hasNext()) {
            final Object item = iterator.next();
            if (item instanceof SuperGroupElementSublist) {
                items.add(item);
                getSuperGroupExpressionList((SuperGroupElementSublist) item, items);
            }
        }
    }

    protected void getExpressionList(GroupingSetsElementSublist expressionList, Vector items) {
        final Iterator iterator = expressionList.getGroupingSetsElementExprList().iterator();
        while (iterator.hasNext()) {
            final Object item = iterator.next();
            if (item instanceof GroupingSetsElementSublist) {
                items.add(item);
                getExpressionList((GroupingSetsElementSublist) item, items);
            }
        }
    }

    protected void getSuperGroupExpressionList(SuperGroupElementSublist expressionList, Vector items) {
        final Iterator iterator = expressionList.getSuperGroupElementExprList().iterator();
        while (iterator.hasNext()) {
            final Object item = iterator.next();
            if (item instanceof SuperGroupElementSublist) {
                items.add(item);
                getSuperGroupExpressionList((SuperGroupElementSublist) item, items);
            }
        }
    }

    public void refreshListBox() {
        final Vector listItems = getGroupByContentList();

        // add 1 for the root guy
        final LabelValuePair items[] = new LabelValuePair[listItems.size() + 1];

        items[0] = new LabelValuePair(Messages._UI_COMBOBOXITEM_GROUPING_ROOT, groupByClause); //$NON-NLS-1$
        for (int i = 1; i < items.length; i++) {
            final Object item = listItems.elementAt(i - 1);
            String label;
            if (item instanceof SQLQueryObject) {
                label = ((SQLQueryObject) item).getSQL();
            }
            else {
                if (item != null) {
                    label = item.toString();
                }
                else {
                    label = ""; //$NON-NLS-1$
                }
            }
            items[i] = new LabelValuePair(convertForDisplay(label, (EObject) item), item);
        } // end of for ()

        groupByContentHelper.setItems(items);

        // now try and reselect the old selection
        if (groupByContentHelper.indexOf(currentListSelection) == -1) {
            currentListSelection = groupByClause;
        }
        else if (currentListSelection instanceof GroupingSpecification && groupByContentHelper.indexOf(currentListSelection) != 0) {
            typeCombo.setEnabled(true);
        }
        groupByContentHelper.select(currentListSelection);
    }

    public String convertForDisplay(String label, EObject item) {
        String newLabel = StringUtility.stripNewLines(label);
        if (item instanceof GroupingExpression) {
            return newLabel;
        }

        EObject parent = item;

        while (!(parent instanceof QuerySelect) && parent != null) {
            if (!(parent instanceof GroupingSetsElementExpression)) {
                newLabel = "    " + newLabel; //$NON-NLS-1$
            }
            parent = parent.eContainer();
        } // end of while ()
        return newLabel;
    }

    @Override
    public void refresh() {
        if (getInput() != null) {
            refreshListBox();
            updateTypeCombo();
            expressionGrid.setInput(currentListSelection);
            //following is to refresh the UI when change is made in the source
            setInput(getInput());
        }
    }

    @Override
    public ISelection getSelection() {
        return null;
    }

    @Override
    public void setSelection(ISelection selection, boolean reveal) {
    }

    public Control createControl(Composite parent) {
        final SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
        canvas = sashForm;

        groupByContentList = ViewUtility.createListBox(canvas, 10, false);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(groupByContentList, SQLBuilderContextIds.SQDS_SELECT_GROUPS_WHITE_FIELD);

        // ratio 0.3
        groupByContentList.setData("layout ratio", new Long((((long) 30 << 16) + 99) / 100)); //$NON-NLS-1$
        groupByContentHelper = new ObjectListHelper(groupByContentList);

        groupByContentList.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event e) {
                final Object selectedObject = groupByContentHelper.getSelectedObject();
                expressionGrid.setInput(selectedObject);
                currentListSelection = selectedObject;
                if (selectedObject instanceof Grouping || selectedObject instanceof GroupingSets) {
                    typeCombo.setEnabled(true);
                    updateTypeCombo();
                }
                else {
                    typeCombo.setEnabled(false);
                } // end of else
            }
        });

        final Composite typeGroup = ViewUtility.createComposite(canvas, 2, true);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(typeGroup, SQLBuilderContextIds.SQDS_SELECT_GROUPS_WHITE_FIELD);

        // ratio 0.7
        typeGroup.setData("layout ratio", new Long((((long) 70 << 16) + 99) / 100)); //$NON-NLS-1$
        final Label label = new Label(typeGroup, SWT.NULL);
        label.setText(Messages._UI_LABEL_GROUPS_TYPE);

        typeCombo = new Combo(typeGroup, SWT.READ_ONLY);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(typeCombo, SQLBuilderContextIds.SQDS_SELECT_GROUPS_WHITE_FIELD);

        typeCombo.add(P_NONE);
        typeCombo.add(P_ROLLUP);
        typeCombo.add(P_CUBE);
        typeCombo.add(P_GROUPINGSETS);
        typeCombo.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event e) {
                if (getInput() != null) {
                    final int index = typeCombo.getSelectionIndex();
                    if (index != -1) {
                        final String type = typeCombo.getItem(index);
                        replaceType(type);
                    }
                }
            }
        });

        expressionGrid = new GroupExpressionListGridViewer(sqlDomainModel, typeGroup);
        final GridData gridData = ViewUtility.createFill();
        gridData.horizontalSpan = 2;
        expressionGrid.getTable().setLayoutData(gridData);
        hookControl(getControl());
        return getControl();
    }

    public void updateTypeCombo() {
        currentType = P_NONE;
        if (currentListSelection != null) {
            if (currentListSelection instanceof SuperGroup) {
                if (((SuperGroup) currentListSelection).getSuperGroupType().getValue() == SuperGroupType.ROLLUP) {
                    currentType = P_ROLLUP;
                }
                else {
                    currentType = P_CUBE;
                }
            }
            else if (currentListSelection instanceof GroupingSets)

            {
                currentType = P_GROUPINGSETS;
            }
            else {
                currentType = P_SIMPLE;
            }

            typeCombo.setEnabled(!(currentListSelection == groupByClause));
        }
        typeCombo.select(typeCombo.indexOf(currentType));
    }

    public void replaceType(String type) {
        if (!currentType.equals(type)) {
            if (currentListSelection instanceof GroupingSets) {
                final int currIdx = groupByClause.indexOf(currentListSelection);
                groupByClause.remove(currentListSelection);
                if (type.equals(P_CUBE)) {
                    final SuperGroup supGrp = SelectHelper.createSuperGroup(SuperGroupType.CUBE);
                    if (currIdx > -1) {
                        groupByClause.add(currIdx, supGrp);
                    }
                    else {
                        groupByClause.add(supGrp);
                    }
                }
                if (type.equals(P_ROLLUP)) {
                    final SuperGroup supGrp = SelectHelper.createSuperGroup(SuperGroupType.ROLLUP);
                    if (currIdx > -1) {
                        groupByClause.add(currIdx, supGrp);
                    }
                    else {
                        groupByClause.add(supGrp);
                    }
                }
            }
            else if (currentListSelection instanceof SuperGroup) {
                if (type.equals(P_CUBE)) {
                    ((SuperGroup) currentListSelection).setSuperGroupType(SuperGroupType.get(SuperGroupType.CUBE));
                }
                else if (type.equals(P_ROLLUP)) {
                    ((SuperGroup) currentListSelection).setSuperGroupType(SuperGroupType.get(SuperGroupType.ROLLUP));
                }
                else if (type.equals(P_GROUPINGSETS)) {
                    final int currIdx = groupByClause.indexOf(currentListSelection);
                    if (currIdx > -1) {
                        groupByClause.remove(currentListSelection);
                        final GroupingSets grpSets = SelectHelper.createGroupingSets();
                        groupByClause.add(currIdx, grpSets);
                    }
                }
            }
            refresh();
        }
    }

    public class GroupExpressionListGridViewer extends GridViewer implements IMenuListener {

        GroupEditComboBoxCellEditor columnComboBoxCellEditor; // we don't need to extend GridViewer

        class GroupEditComboBoxCellEditor extends EditComboBoxCellEditor {

            GroupExpressionListGridViewer tableViewer = null;

            public GroupEditComboBoxCellEditor(Composite parent, LabelValuePair[] items, GroupExpressionListGridViewer tv) {
                super(parent, items, false);
                tableViewer = tv;
            }

            @Override
            protected void doSetValue(Object value) {
                super.doSetValue(value);
            }

            @Override
            protected LabelValuePair createComboBoxItem(String newValue) {
                return new LabelValuePair(newValue, ExpressionHelper.createExpression(newValue));
            }

            @Override
            protected void refreshComboItems() {
                final int row = tableViewer.getTable().getSelectionIndex();
                if (row >= 0) {
                    tableViewer.refreshCellEditor(row);
                }
            }
        }

        public GroupExpressionListGridViewer(SQLDomainModel domainModel, Composite parent) {
            super(domainModel, parent);

            final TableLayout layout = new TableLayout();
            layout.addColumnData(new ColumnWeightData(100, true)); // column name

            table.setLayout(layout);

            // Create the column properties
            final String columnProperties[] = { (String) SQLBuilderConstants.P_STATEMENT_COLUMN, };
            setColumnProperties(columnProperties);

            columnComboBoxCellEditor = new GroupEditComboBoxCellEditor(table, null, this);
            // Create the cell editors
            final CellEditor editors[] = { columnComboBoxCellEditor, };
            setCellEditors(editors);
            setCellModifier(new GroupByExpressionModifier());
            this.setContentProvider(new GroupByExpressionContentProvider(sqlDomainModel.getAdapterFactory()));
            this.setLabelProvider(new GroupByExpressionLabelProvider());
        }

        /**
         * The context menu is about to appear.  Populate it.
         */
        @Override
        public void menuAboutToShow(IMenuManager menu) {
            final RemoveCurrentRowAction removeAction = new RemoveCurrentRowAction(this);
            menu.add(removeAction);
        }

        public void refreshCellEditor(int row) {
            final boolean exprExists = expressionExists(row);
            updateMenuItems(super.getInput(), exprExists);
        }

        private boolean expressionExists(int row) {
            final Object obj = getElementAt(row);

            if (obj instanceof Holder) {
                final Object expr = ((Holder) obj).getExpression();
                if (expr != null) {
                    // Expression Exists
                    return true;
                }
            }
            // Expression Does Not Exist
            return false;
        }

        private void updateMenuItems(Object input, boolean exprExists) {
            if (input instanceof GroupingSets) {
                // add 3 more for add cube, add rollup, group
                final LabelValuePair[] extraItems = { new LabelValuePair(P_CUBE, P_CUBE), new LabelValuePair(P_ROLLUP, P_ROLLUP),
                        new LabelValuePair(P_GROUPING, P_GROUPING) };
                addComboBoxItems(extraItems, exprExists);
            }
            else if (input instanceof GroupingSetsElementSublist) {
                // add 3 more for add cube, add rollup, group
                final LabelValuePair[] extraItems = { new LabelValuePair(P_CUBE, P_CUBE), new LabelValuePair(P_ROLLUP, P_ROLLUP) };
                addComboBoxItems(extraItems, exprExists);
            }
            else if (input instanceof SuperGroup)
            //	|| input instanceof SQLExpressionList) // is expressionlist valid here or are we just allowed 1 level of nesting in the supergroup?
            {
                // add 1 more for add cube, add rollup
                final LabelValuePair[] extraItems = { new LabelValuePair(P_GROUPING, P_GROUPING) };
                addComboBoxItems(extraItems, exprExists);
            }
            else if (input == groupByClause) {
                final VendorHelper vendor = sqlDomainModel.getVendor();
                if (vendor.isDB2() && !vendor.isDB2UDBOS390_V6()) {
                    // db2 supports these 3
                    // add 3 more for add cube, add rollup, add grouping set
                    final LabelValuePair[] extraItems = { new LabelValuePair(P_CUBE, P_CUBE), new LabelValuePair(P_ROLLUP, P_ROLLUP),
                            new LabelValuePair(P_GROUPINGSETS, P_GROUPINGSETS) };
                    addComboBoxItems(extraItems, exprExists);
                }
                else if (vendor.isOracle()) {
                    // oracle only supports cube and rollup
                    // add 2 more for add cube, add rollup
                    final LabelValuePair[] extraItems = { new LabelValuePair(P_CUBE, P_CUBE), new LabelValuePair(P_ROLLUP, P_ROLLUP) };
                    addComboBoxItems(extraItems, exprExists);
                }
                else {
                    // don't add any of these for the other db's
                    final LabelValuePair[] extraItems = {};
                    addComboBoxItems(extraItems, exprExists);            	
                }
            }
            else {
                // don't add any of these for the other db's
                final LabelValuePair[] extraItems = {};
                addComboBoxItems(extraItems, exprExists);
            }

        }

        public void addComboBoxItems(LabelValuePair[] extraItems, boolean exprExists) {
            final LabelValuePair[] items = (LabelValuePair[]) BonitaBuilderUtility.getDistinctColumnItems(selectStatement, true, exprExists);
            final LabelValuePair[] extendedItems = new LabelValuePair[items.length + extraItems.length];
            // we subtract 1 because we want the empty entry to be last
            int i = 0;
            for (; i < items.length - 1; i++) {
                extendedItems[i] = items[i];
            } // end of for ()
            // Now add in the extra items
            for (int j = 0; j < extraItems.length; j++) {
                extendedItems[i++] = extraItems[j];
            } // end of for ()
            // add the empty entry
            extendedItems[i] = items[items.length - 1];
            columnComboBoxCellEditor.createItems(extendedItems);
        }

        /**
         * Temporary work around to cause the table to refresh. The TableViewer::doUpdateItem()
         * implementation has a cast problem.
         */
        @Override
        public void refresh() {
            super.refresh();
            // if the select statement is null, then we are in the middle of a refresh!
            if (currentListSelection != null) {
                setGroupByClause();
            }
        }

        public void setEnabled(boolean enable) {
            table.setEnabled(enable);
        }
    }

    abstract public class Holder {

        Object parent;
        Object child;

        public Holder(Object parent, Object child) {
            this.parent = parent;
            this.child = child;
        }

        @Override
        public String toString() {
            String label = ""; //$NON-NLS-1$
            if (child != null) {
                if (child instanceof SQLQueryObject) {
                    label = ((SQLQueryObject) child).getSQL();
                }
                else {
                    label = child.toString();
                }
            }
            return label;
        }

        abstract public QueryValueExpression getExpression();

        abstract public void setExpression(QueryValueExpression newExpression);

        abstract public void empty();

        abstract public void create(String objectType);
    }

    /**
     * SuperGroupExpressionHolder 
     */
    public class SuperGroupExpressionHolder extends Holder {

        public SuperGroupExpressionHolder(SuperGroup superGroup, Object expression) {
            super(superGroup, expression);
        }

        public SuperGroup getSuperGroup() {
            return (SuperGroup) parent;
        }

        @Override
        public QueryValueExpression getExpression() {
            if (child != null) {
                if (child instanceof SuperGroupElementExpression) {
                    final SuperGroupElementExpression superGrpExpr = (SuperGroupElementExpression) child;
                    return superGrpExpr.getGroupingExpr().getValueExpr();
                }
            }
            return null;
        }

        @Override
        public void setExpression(QueryValueExpression newExpression) {
            final QueryValueExpression previousExpression = getExpression();
            final SuperGroup superGroup = getSuperGroup();
            QueryValueExpression valExpr;
            if (newExpression instanceof ValueExpressionColumn) {
                valExpr = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn) newExpression);
            }
            else {
                valExpr = newExpression;
            }

            if (previousExpression != null) {
                final int previousIndex = superGroup.getSuperGroupElementList().indexOf(child);
                // this has to be true!
                if (previousIndex != -1) {
                    final Object prevObject = superGroup.getSuperGroupElementList().get(previousIndex);
                    if (prevObject instanceof SuperGroupElementExpression) {
                        final SuperGroupElementExpression superGrpExpr = (SuperGroupElementExpression) prevObject;
                        superGrpExpr.getGroupingExpr().setValueExpr(valExpr);
                    }
                }
            }
            else {
                final SuperGroupElementExpression superGrpExpr = SelectHelper.createSuperGroupElementExpression(valExpr);
                superGroup.getSuperGroupElementList().add(superGrpExpr);
            } // end of else

        }

        @Override
        public void empty() {
            if (child != null) {
                getSuperGroup().getSuperGroupElementList().remove(child);
            }
        }

        @Override
        public void create(String objectType) {
            if (objectType.equals(P_GROUPING)) {
                final SuperGroup superGroup = getSuperGroup();
                final SuperGroupElementSublist superGrpElementList = SelectHelper.createSuperGroupElementSublist();
                superGroup.getSuperGroupElementList().add(superGrpElementList);
                //getSuperGroup().addGrouping((QueryValueExpression)child);
            }
        }
    }

    // **END** SuperGroupExpressionHolder

    /**
     * SuperGroupExpressionSublistHolder 
     */
    public class SuperGroupExpressionSublistHolder extends Holder {

        public SuperGroupExpressionSublistHolder(SuperGroupElementSublist superGroupElSublist, Object expression) {
            super(superGroupElSublist, expression);
        }

        public SuperGroupElementSublist getSuperGroupSublist() {
            return (SuperGroupElementSublist) parent;
        }

        @Override
        public QueryValueExpression getExpression() {
            if (child != null) {
                if (child instanceof SuperGroupElementExpression) {
                    final SuperGroupElementExpression superGrpExpr = (SuperGroupElementExpression) child;
                    return superGrpExpr.getGroupingExpr().getValueExpr();
                }
            }
            return null;
        }

        @Override
        public void setExpression(QueryValueExpression newExpression) {
            final QueryValueExpression previousExpression = getExpression();
            final SuperGroupElementSublist superGroupSublist = getSuperGroupSublist();
            QueryValueExpression valExpr;
            if (newExpression instanceof ValueExpressionColumn) {
                valExpr = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn) newExpression);
            }
            else {
                valExpr = newExpression;
            }

            if (previousExpression != null) {
                final int previousIndex = superGroupSublist.getSuperGroupElementExprList().indexOf(child);
                // this has to be true!
                if (previousIndex != -1) {
                    final Object prevObject = superGroupSublist.getSuperGroupElementExprList().get(previousIndex);
                    if (prevObject instanceof SuperGroupElementExpression) {
                        final SuperGroupElementExpression superGrpExpr = (SuperGroupElementExpression) prevObject;
                        superGrpExpr.getGroupingExpr().setValueExpr(valExpr);
                    }
                }
            }
            else {
                final SuperGroupElementExpression superGrpExpr = SelectHelper.createSuperGroupElementExpression(valExpr);
                superGroupSublist.getSuperGroupElementExprList().add(superGrpExpr);
            } // end of else

        }

        @Override
        public void empty() {
            if (child != null) {
                getSuperGroupSublist().getSuperGroupElementExprList().remove(child);
            }
        }

        @Override
        public void create(String objectType) {
            if (objectType.equals(P_GROUPING)) {
                final SuperGroupElementSublist superGroupSublist = getSuperGroupSublist();
                final SuperGroupElementSublist superGrpElementList = SelectHelper.createSuperGroupElementSublist();
                superGroupSublist.getSuperGroupElementExprList().add(superGrpElementList);
            }
        }
    }

    // **END** SuperGroupExpressionHolder

    /**
     * GroupByContentHolder  contains the base list of grouping Expression
     */
    public class GroupByContentHolder extends Holder {

        public GroupByContentHolder(List grpSpecificationList, Object grpSpecification) {
            super(grpSpecificationList, grpSpecification);
        }

        public List getGroupByClause() {
            return (List) parent;
        }

        public GroupingSpecification getGroupByContent() {
            if (child != null) {
                return (GroupingSpecification) child;
            }
            return null;
        }

        @Override
        public QueryValueExpression getExpression() {
            if (child != null) {
                if (child instanceof GroupingExpression) {
                    return ((GroupingExpression) child).getValueExpr();
                }
            }
            return null;
        }

        @Override
        public void setExpression(QueryValueExpression newExpression) {
            final GroupingSpecification previousExpression = getGroupByContent();
            final List groupByList = getGroupByClause();
            QueryValueExpression valExpr = null;
            if (newExpression != null && newExpression instanceof ValueExpressionColumn) {
                valExpr = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn) newExpression);
            }
            else {
                valExpr = newExpression;
            }

            if (previousExpression != null) {
                final int previousIndex = groupByList.indexOf(previousExpression);
                // this has to be true!
                if (previousIndex != -1) {
                    if (previousExpression instanceof GroupingExpression) {
                        final GroupingExpression grpExpr = (GroupingExpression) previousExpression;
                        grpExpr.setValueExpr(valExpr);
                    }
                    else {
                        groupByList.remove(previousIndex);
                        final GroupingExpression grpExpr = SelectHelper.createGroupingExpression(valExpr);
                        groupByList.add(previousIndex, grpExpr);
                    }
                }
            }
            else {
                final GroupingExpression grpExpr = SelectHelper.createGroupingExpression(valExpr);
                groupByList.add(grpExpr);
            } // end of else
        }

        public void createSuperGroup(String groupType) {
            final int groupKind = groupType.equals(P_CUBE) ? SuperGroupType.CUBE : SuperGroupType.ROLLUP;
            final SuperGroup superGrp = SelectHelper.createSuperGroup(groupKind);
            final List groupByList = getGroupByClause();
            groupByList.add(superGrp);
        }

        public void createGroupingSet() {
            final GroupingSets grpSets = SelectHelper.createGroupingSets();
            final List groupByList = getGroupByClause();
            groupByList.add(grpSets);
        }

        @Override
        public void empty() {
            final int index = getGroupByClause().indexOf(child);
            if (index != -1) {
                getGroupByClause().remove(index);
            }
        }

        @Override
        public void create(String objectType) {
            if (objectType.equals(P_CUBE) || objectType.equals(P_ROLLUP)) {
                createSuperGroup(objectType);
            }
            else if (objectType.equals(P_GROUPINGSETS)) {
                createGroupingSet();
            } // end of else
        }
    }

    //  **END**  GroupByContentHolder 

    /**
     * GroupingSetContentHolder
     */
    public class GroupingSetContentHolder extends Holder {

        public GroupingSetContentHolder(GroupingSets grpSet, Object set) {
            super(grpSet, set);
        }

        public GroupingSets getGroupingSet() {
            return (GroupingSets) parent;
        }

        @Override
        public QueryValueExpression getExpression() {
            if (child != null) {
                if (child instanceof GroupingSetsElementExpression && ((GroupingSetsElementExpression) child).getGrouping() instanceof GroupingExpression) {
                    final GroupingSetsElementExpression grpSetsExpr = (GroupingSetsElementExpression) child;
                    return ((GroupingExpression) grpSetsExpr.getGrouping()).getValueExpr();
                }
            }
            return null;
        }

        @Override
        public void setExpression(QueryValueExpression newExpression) {
            final QueryValueExpression previousExpression = getExpression();
            final GroupingSets grpSet = getGroupingSet();
            QueryValueExpression valExpr;
            if (newExpression instanceof ValueExpressionColumn) {
                valExpr = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn) newExpression);
            }
            else {
                valExpr = newExpression;
            }

            if (previousExpression != null) {
                final int previousIndex = grpSet.getGroupingSetsElementList().indexOf(child);
                // this has to be true!
                if (previousIndex != -1) {
                    final Object prevObject = grpSet.getGroupingSetsElementList().get(previousIndex);
                    if (prevObject instanceof GroupingSetsElementExpression
                            && ((GroupingSetsElementExpression) prevObject).getGrouping() instanceof GroupingExpression) {

                        final GroupingSetsElementExpression grpSetsExpr = (GroupingSetsElementExpression) prevObject;
                        ((GroupingExpression) grpSetsExpr.getGrouping()).setValueExpr(valExpr);
                    }
                }
            }
            else {
                final GroupingSetsElementExpression superGrpExpr = SelectHelper.createGroupingSetsElementExpression(valExpr);
                grpSet.getGroupingSetsElementList().add(superGrpExpr);
            } // end of else

        }

        public void createSuperGroup(String groupType) {
            final int groupKind = groupType.equals(P_CUBE) ? SuperGroupType.CUBE : SuperGroupType.ROLLUP;
            final SuperGroup superGrp = SelectHelper.createSuperGroup(groupKind);
            final GroupingSetsElementExpression grpSetElementExpr = SelectHelper.createGroupingSetsElementExpression();
            grpSetElementExpr.setGrouping(superGrp);
            final GroupingSets grpSet = getGroupingSet();
            grpSet.getGroupingSetsElementList().add(grpSetElementExpr);
        }

        public void createGrouping() {
            final GroupingSetsElementSublist grpSetsElementSublist = SelectHelper.createGroupingSetsElementSublist();
            final GroupingSets grpSet = getGroupingSet();
            grpSet.getGroupingSetsElementList().add(grpSetsElementSublist);
        }

        @Override
        public void empty() {
            final int index = getGroupingSet().getGroupingSetsElementList().indexOf(child);
            if (index != -1) {
                getGroupingSet().getGroupingSetsElementList().remove(index);
            }
        }

        @Override
        public void create(String objectType) {
            if (objectType.equals(P_CUBE) || objectType.equals(P_ROLLUP)) {
                createSuperGroup(objectType);
            }
            else if (objectType.equals(P_GROUPING)) {
                createGrouping();
            } // end of else
        }
    }

    // **END GroupingSetContentHolder

    /**
     * GroupingSetGroupContentHolder
     */
    public class GroupingSetGroupContentHolder extends Holder {

        public GroupingSetGroupContentHolder(GroupingSetsElementSublist grpSet, Object set) {
            super(grpSet, set);
        }

        public GroupingSetsElementSublist getGroupingSetGroup() {
            return (GroupingSetsElementSublist) parent;
        }

        public Grouping getGroupExpressionOrSuperGroup() {
            if (child != null) {
            }
            return null;
        }

        @Override
        public QueryValueExpression getExpression() {
            if (child != null) {
                if (child instanceof GroupingSetsElementExpression && ((GroupingSetsElementExpression) child).getGrouping() instanceof GroupingExpression) {
                    final GroupingSetsElementExpression grpSetsExpr = (GroupingSetsElementExpression) child;
                    return ((GroupingExpression) grpSetsExpr.getGrouping()).getValueExpr();
                }
            }
            return null;

        }

        @Override
        public void setExpression(QueryValueExpression newExpression) {

            final QueryValueExpression previousExpression = getExpression();
            final GroupingSetsElementSublist grpSetSublist = getGroupingSetGroup();
            QueryValueExpression valExpr;
            if (newExpression instanceof ValueExpressionColumn) {
                valExpr = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn) newExpression);
            }
            else {
                valExpr = newExpression;
            }

            if (previousExpression != null) {
                final int previousIndex = grpSetSublist.getGroupingSetsElementExprList().indexOf(child);
                // this has to be true!
                if (previousIndex != -1) {
                    final Object prevObject = grpSetSublist.getGroupingSetsElementExprList().get(previousIndex);
                    if (prevObject instanceof GroupingSetsElementExpression
                            && ((GroupingSetsElementExpression) prevObject).getGrouping() instanceof GroupingExpression) {

                        final GroupingSetsElementExpression grpSetsExpr = (GroupingSetsElementExpression) prevObject;
                        ((GroupingExpression) grpSetsExpr.getGrouping()).setValueExpr(valExpr);
                    }
                }
            }
            else {
                final GroupingSetsElementExpression superGrpExpr = SelectHelper.createGroupingSetsElementExpression(valExpr);
                grpSetSublist.getGroupingSetsElementExprList().add(superGrpExpr);
            } // end of else
        }

        public void createSuperGroup(String groupType) {
            final int groupKind = groupType.equals(P_CUBE) ? SuperGroupType.CUBE : SuperGroupType.ROLLUP;
            final SuperGroup superGrp = SelectHelper.createSuperGroup(groupKind);
            final GroupingSetsElementExpression grpSetElementExpr = SelectHelper.createGroupingSetsElementExpression();
            grpSetElementExpr.setGrouping(superGrp);
            final GroupingSetsElementSublist grpSetSublist = getGroupingSetGroup();
            grpSetSublist.getGroupingSetsElementExprList().add(grpSetElementExpr);
        }

        @Override
        public void empty() {
            final int index = getGroupingSetGroup().getGroupingSetsElementExprList().indexOf(child);
            if (index != -1) {
                getGroupingSetGroup().getGroupingSetsElementExprList().remove(index);
            }
        }

        @Override
        public void create(String objectType) {
            if (objectType.equals(P_CUBE) || objectType.equals(P_ROLLUP)) {
                createSuperGroup(objectType);
            }
        }
    }

    // **END GroupingSetGroupContentHolder

    public class GroupByExpressionContentProvider extends GridContentProvider {

        public GroupByExpressionContentProvider(AdapterFactory adapterFactory) {
            super(adapterFactory);
        }

        @Override
        public Object[] getElements(java.lang.Object object) {
        	
        	// This is done is super class AdapterFactoryContentProvider.getElements(), but
        	// since this method does not call the superclass method, it must be done here
        	// as well.  Without this call, notifications will not be enabled for the SQL object
            adapterFactory.adapt(object, IStructuredItemContentProvider.class);
            
            if (groupByClause.size() > 0) {
                if (object instanceof SuperGroup) {
                    tableElements = new Vector();
                    final SuperGroup superGroup = (SuperGroup) object;
                    final Iterator iterator = superGroup.getSuperGroupElementList().iterator();
                    while (iterator.hasNext()) {
                        tableElements.add(new SuperGroupExpressionHolder(superGroup, iterator.next()));
                    } // end of while ()
                    tableElements.add(new SuperGroupExpressionHolder(superGroup, null));
                    return tableElements.toArray();
                }
                else if (object instanceof GroupingSets) {
                    tableElements = new Vector();
                    final GroupingSets groupingSet = (GroupingSets) object;
                    final Iterator iterator = groupingSet.getGroupingSetsElementList().iterator();
                    while (iterator.hasNext()) {
                        tableElements.add(new GroupingSetContentHolder(groupingSet, iterator.next()));
                    }
                    tableElements.add(new GroupingSetContentHolder(groupingSet, null));
                    return tableElements.toArray();
                }
                else if (object instanceof GroupingSetsElementSublist) {
                    tableElements = new Vector();
                    final GroupingSetsElementSublist grpSetElSublist = (GroupingSetsElementSublist) object;
                    final Iterator iterator = grpSetElSublist.getGroupingSetsElementExprList().iterator();
                    while (iterator.hasNext()) {
                        tableElements.add(new GroupingSetGroupContentHolder(grpSetElSublist, iterator.next()));
                    }
                    tableElements.add(new GroupingSetGroupContentHolder(grpSetElSublist, null));
                    return tableElements.toArray();
                }
                else if (object instanceof SuperGroupElementSublist) {
                    tableElements = new Vector();
                    final SuperGroupElementSublist superGrpElSublist = (SuperGroupElementSublist) object;
                    final Iterator iterator = superGrpElSublist.getSuperGroupElementExprList().iterator();
                    while (iterator.hasNext()) {
                        tableElements.add(new SuperGroupExpressionSublistHolder(superGrpElSublist, iterator.next()));
                    }
                    tableElements.add(new SuperGroupExpressionSublistHolder(superGrpElSublist, null));
                    return tableElements.toArray();
                }
                else if (object instanceof GroupingExpression) {
                }
                else {
                    tableElements.clear();
                    final List expressionList = (List) object;
                    final Iterator iterator = expressionList.iterator();
                    while (iterator.hasNext()) {
                        tableElements.add(new GroupByContentHolder(expressionList, iterator.next()));
                    } // end of while () 
                    tableElements.add(new GroupByContentHolder(expressionList, null));
                    return tableElements.toArray();
                }
            }
            else {
                tableElements.clear();
                tableElements.add(new GroupByContentHolder(groupByClause, null));
                return tableElements.toArray();
            }
            return null;
        }
    }

    public class GroupByExpressionModifier implements ICellModifier {

        @Override
        public boolean canModify(Object element, String property) {
            return true;
        }

        @Override
        public Object getValue(Object element, String property) {
            // This implicitly uses the element's toString method
            return element;
        }

        Object currentData, currentProperty, currentValue;

        @Override
        public void modify(Object element, String property, Object value) {

            final Object data = ((TableItem) element).getData();

            if (value == null) {
                return;
            }

            currentData = data;
            currentProperty = property;
            currentValue = value;

            Display.getCurrent().asyncExec(new Runnable() {

                @Override
                public void run() {
                    final QueryValueExpression previousExpression = ((Holder) currentData).getExpression();

                    String selectionString = ""; //$NON-NLS-1$
                    QueryValueExpression newExpression = null;
                    if (currentValue instanceof String) {
                        // this will either be the "build expression" or the blank entry
                        selectionString = (String) currentValue;
                        if (selectionString.equals(SQLBuilderConstants.P_BUILD_EXPRESSION)) {
                            newExpression = showExpressionBuilder(previousExpression, false, SQLBuilderConstants.P_BUILD_EXPRESSION);
                            // treat the action as a cancel
                            if (newExpression == null) {
                                return;
                            }
                        }
                        else if (selectionString.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION)) {
                            newExpression = showExpressionBuilder(previousExpression, false, SQLBuilderConstants.P_REPLACE_EXPRESSION);
                            if (newExpression == null) {
                                return;
                            }
                        }
                        else if (selectionString.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)) {
                            newExpression = showExpressionBuilder(previousExpression, false, SQLBuilderConstants.P_EDIT_EXPRESSION);
                            if (newExpression == null) {
                                return;
                            }
                        }
                        else if (selectionString.equals(P_CUBE) || selectionString.equals(P_ROLLUP) || selectionString.equals(P_GROUPING)) {
                            ((Holder) currentData).create(selectionString);
                        }
                        else if (selectionString.equals(P_GROUPINGSETS)) {
                            if (currentData instanceof GroupByContentHolder) {
                                ((GroupByContentHolder) currentData).createGroupingSet();
                            }
                        }
                        else {
                            // this is the " " empty entry which means they want to remove it
                        } // end of else

                    }
                    else {
                        if (currentValue instanceof QueryValueExpression) {
                            if (((QueryValueExpression) currentValue).toString().equals("")) //$NON-NLS-1$
                                newExpression = null;
                            else
                                // probably a column expression here
                                newExpression = (QueryValueExpression) currentValue;
                        }
                        else {
                            return;
                        } // end of else
                    } // end of else

                    // newExpression can only be null at this point if the user selected the
                    // empty entry
                    updateValue(currentData, newExpression);
                    refreshListBox();
                    SelectHelper.refresh(selectStatement);
                    setInput(selectStatement);
                }
            });
        }

        protected void updateValue(Object data, QueryValueExpression newExpression) {
            if (newExpression == null) {
                ((Holder) data).empty();
            }
            else {
                ((Holder) data).setExpression(newExpression);
            }
        }
    }

    public QueryValueExpression showExpressionBuilder(Object obj, boolean isColumn, String action) {
//    	Shell fakeShell = new Shell() ; 
//    	TextOrData textOrData = textOrDataFactory.createTextOrData(fakeShell,processElement) ;
//    	BonitaGroovyEditorDialog dialog = new BonitaGroovyEditorDialog(Display.getDefault().getActiveShell(), "",textOrData.computeAccessibleData(),(IFile) GroovyScriptRepository.getInstance().getArtifact(GroovyScriptRepository.DEFAULT_SCRIPT_FILENAME).getContent()); //$NON-NLS-1$
//    	fakeShell.dispose() ;
//    	textOrData = null ;
//    	dialog.setBlockOnOpen(true);
//		int result = dialog.open() ;
//		
//		 if (result == 0) {
//	          return ExpressionHelper.createExpression("'"+dialog.getExpression()+"'") ;
//		 }

        return null;
    }

    class RemoveCurrentRowAction extends Action {

        TableViewer gridViewer;

        public RemoveCurrentRowAction(TableViewer gridViewer) {
            super(Messages._UI_ACTION_REMOVE);
            this.gridViewer = gridViewer;
        }

        @Override
        public void run() {
            final ISelection selection = gridViewer.getSelection();

            if (selection.isEmpty() || !(selection instanceof IStructuredSelection))
                return;

            final IStructuredSelection es = (IStructuredSelection) selection;
            final Iterator elements = es.iterator();

            gridViewer.cancelEditing();

            while (elements.hasNext()) {
                final Object item = elements.next();

                if (item instanceof Holder) {
                    ((Holder) item).empty();
                }
            }
            //following two lines are to force refresh.
            SelectHelper.refresh(selectStatement);
            setInput(selectStatement);
        }

    }

    class GroupByExpressionLabelProvider extends LabelProvider implements ITableLabelProvider {

        @Override
        public String getColumnText(Object object, int columnIndex) {
            if (columnIndex == 0) {
                return StringUtility.stripNewLines(object.toString());
            }
            return ""; //$NON-NLS-1$
        }

        @Override
        public Image getColumnImage(Object object, int column) {
            return null;
        }
    }

    public void setEnabled(boolean enable) {
        expressionGrid.setEnabled(enable);
        groupByContentList.setEnabled(enable);
        if (enable) {
            groupByContentList.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        }
        else {
            groupByContentList.setBackground(canvas.getBackground());
        }
    }
}// GroupByExpressionViewer
