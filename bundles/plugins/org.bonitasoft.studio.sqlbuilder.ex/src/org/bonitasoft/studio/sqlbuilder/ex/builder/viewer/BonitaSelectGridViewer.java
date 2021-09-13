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

import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.sqlbuilder.ex.builder.BonitaBuilderUtility;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaSelectTableElement;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.provider.BonitaSelectGridContentProvider;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLResource;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.views.EditComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.select.RemoveOrderByExpressionAction;
import org.eclipse.datatools.sqltools.sqlbuilder.views.select.RemoveSelectColumnAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerRow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;

public class BonitaSelectGridViewer extends GridViewer implements IMenuListener {

    TableColumn c2, c3, c4, c5;
    SelectGridLabelProvider selectGridLabelProvider;
    SortOrderComboBoxCellEditor sortOrderCellEditor;
    BonitaDynamicComboBoxCellEditor columnComboBoxCellEditor; // we don't need to extend GridViewer

    public static final String P_UNSORTED = Messages._UI_COMBO_SORT_UNSORTED;
    public static final String P_ASCENDING = Messages._UI_COMBO_SORT_ASCENDING;
    public static final String P_DESCENDING = Messages._UI_COMBO_SORT_DESCENDING;
    public static final String P_DEFAULT = Messages._UI_COMBO_SORT_DEFAULT;

    public BonitaSelectGridViewer(SQLDomainModel domainModel, List<Expression> filteredExpressions, Composite parent) {
        super(domainModel, parent);
  
        PlatformUI.getWorkbench().getHelpSystem().setHelp(table, SQLBuilderContextIds.SQDS_COLUMNS_TAB_GRID);

        c2 = new TableColumn(table, SWT.NULL);
        c2.setText(Messages._UI_COLUMN_SELECT_ALIAS);
        columnComboBoxCellEditor = new BonitaDynamicComboBoxCellEditor(table, filteredExpressions, null, this);
        
        
        c3 = new TableColumn(table, SWT.NULL);
        c3.setText(Messages._UI_COLUMN_SELECT_OUTPUT);

        c4 = new TableColumn(table, SWT.NULL);
        c4.setText(Messages._UI_COLUMN_SELECT_SORT_TYPE);

        c5 = new TableColumn(table, SWT.NULL);
        c5.setText(Messages._UI_COLUMN_SELECT_SORT_ORDER);

        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnPixelData(200)); // column name
        layout.addColumnData(new ColumnPixelData(80)); // alias
        layout.addColumnData(new ColumnPixelData(60)); // Output
        layout.addColumnData(new ColumnPixelData(90)); // Sort Type
        layout.addColumnData(new ColumnPixelData(90)); // Sort Order

        table.setLayout(layout);

        // Create the column properties
        final String columnProperties[] = { (String) SQLBuilderConstants.P_STATEMENT_COLUMN, (String) SQLBuilderConstants.P_STATEMENT_ALIAS,
                (String) SQLBuilderConstants.P_STATEMENT_OUTPUT, (String) SQLBuilderConstants.P_STATEMENT_SORTTYPE,
                (String) SQLBuilderConstants.P_STATEMENT_SORTORDER };
        setColumnProperties(columnProperties);

        sortOrderCellEditor = new SortOrderComboBoxCellEditor(table);

        // Create the cell editors
        final CellEditor editors[] = { columnComboBoxCellEditor, new AliasCellEditor(table), new OutputCheckboxCellEditor(table),
                new SortTypeComboBoxCellEditor(table), sortOrderCellEditor };
        setCellEditors(editors);

        setCellModifier(new BonitaModifier());

        final BonitaSelectGridContentProvider gridContentProvider = new BonitaSelectGridContentProvider(domainModel, filteredExpressions);

        setContentProvider(gridContentProvider);
        selectGridLabelProvider = new SelectGridLabelProvider();
        setLabelProvider(selectGridLabelProvider);

        // BZ 202596 remove call to hookControl as it's called from
        // the base class constructor TableViewer(Table table)
        //hookControl(table);
    }
    
    public QueryStatement getCurrentStatement() {
        return currentStatement;
    }
    
    public void refreshCellEditor(int row) {
        boolean resultExprExistsExpression = false;

        final Object obj = getElementAt(row);
        if (obj instanceof BonitaSelectTableElement) {
            final QueryValueExpression sqlExpr = ((BonitaSelectTableElement) obj).getSQLExpression();
            if (sqlExpr != null) {
                resultExprExistsExpression = true;
            }
        }
        if (getInput() instanceof QueryStatement || getInput() instanceof QuerySelect) {
            BonitaBuilderUtility.fillColumnComboBox((EditComboBoxCellEditor) columnComboBoxCellEditor, (SQLQueryObject) getInput(), false, false);
        }

        final CellEditor editors[] = { columnComboBoxCellEditor, new AliasCellEditor(table), new OutputCheckboxCellEditor(table),
                new SortTypeComboBoxCellEditor(table), sortOrderCellEditor };

        setCellEditors(editors);
    }

    
    
    /**
     * The context menu is about to appear.  Populate it.
     */
    @Override
    public void menuAboutToShow(IMenuManager menu) {
        final RemoveSelectColumnAction removeColumnAction = new RemoveSelectColumnAction(this);
        final RemoveOrderByExpressionAction removeOrderByExpressionAction = new RemoveOrderByExpressionAction(this);
        menu.add(removeColumnAction);
        menu.add(removeOrderByExpressionAction);
    }

    @Override
    protected void inputChanged(java.lang.Object input, java.lang.Object oldInput) {
        super.inputChanged(input, oldInput);
        setGridTitle();
    }

    @Override
    public void refresh() {
        super.refresh();
    }

    private void setGridTitle() {

    }

	/**
	 * Hook up the editing support. This overrides base class ColumnViewer's implementation
	 * which responds to mouseDown events. This implementation responds to mouseUp events
	 * so that there is no duplication of responses with TableNavigator, which also
	 * responds to mouseUp.
	 *
	 * @param control
	 *            the control you want to hook on
	 */
	@Override
    protected void hookEditingSupport(Control control) {

		if (getColumnViewerEditor() != null) {
			control.addMouseListener(new MouseAdapter() {
				@Override
                public void mouseUp(MouseEvent e) {
					// Workaround for bug 185817
					if( e.count != 2 ) {
						handleMouseUp(e);
					}
				}

				@Override
                public void mouseDoubleClick(MouseEvent e) {
					handleMouseUp(e);
				}
			});
		}
	}
    
    /*
     * Copied from ColumnViewer.handleMouseDown - required so it can be called
     * from hookEditingSupport's mouseListener
     * @param e
     */
	private void handleMouseUp(MouseEvent e) {
		final ViewerCell cell = getCell(new Point(e.x, e.y));

		if (cell != null) {
			triggerEditorActivationEvent(new ColumnViewerEditorActivationEvent(
					cell, e));
		}
	}

    /*
     * Copied from ColumnViewer.getCell - required so it can be called
     * from handleMouseDown
     * @param point
     */
	@Override
    public ViewerCell getCell(Point point) {
		final ViewerRow row = getViewerRow(point);
		if (row != null) {
			return row.getCell(point);
		}

		return null;
	}
	
	
    class SelectGridLabelProvider extends LabelProvider implements ITableLabelProvider {

        @Override
        public String getColumnText(Object object, int columnIndex) {
            if (object instanceof BonitaSelectTableElement && columnIndex != 2) // not Output column
            {
            	final BonitaSelectTableElement selectElement = (BonitaSelectTableElement) object;
                return selectElement.getColumnText(columnIndex);
            }
            return ""; //$NON-NLS-1$
        }

        @Override
        public Image getColumnImage(Object object, int columnIndex) {
            if (columnIndex == 2) // Output Column
            {
                if (object instanceof BonitaSelectTableElement) {
                	final BonitaSelectTableElement selectElement = (BonitaSelectTableElement) object;
                    // BZ 202596 - don't show image if it's the empty row at the end
                    if (selectElement.hasColumn()){
                    	final String result = selectElement.getColumnText(columnIndex);
                    	if (result.equals("true")) { //$NON-NLS-1$
                    		return SQLBuilderPlugin.getPlugin().getImage(SQLResource.SQL_OUTPUT_YES);
                    	}
                    	return SQLBuilderPlugin.getPlugin().getImage(SQLResource.SQL_OUTPUT_NO);
                    }
                }
            }
            return null;
        }
    }

    class SortTypeComboBoxCellEditor extends org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor {

        public SortTypeComboBoxCellEditor(Composite parent) {
            super(parent, new LabelValuePair[3]);

            final LabelValuePair[] items = getLabelValuePairs();
            items[0] = new LabelValuePair(P_ASCENDING, P_ASCENDING);
            items[1] = new LabelValuePair(P_DESCENDING, P_DESCENDING);
            items[2] = new LabelValuePair("", ""); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    class SortOrderComboBoxCellEditor extends org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor {

        public SortOrderComboBoxCellEditor(Composite parent) {
            super(parent, null);
        }

        @Override
        protected void doSetValue(Object value) {
            super.doSetValue(value);
        }

        @Override
        protected void refreshComboItems() {
            boolean orderByExpressionsExist = false;
            final int row = getTable().getSelectionIndex();
            boolean hasOrder = false;
            if (row >= 0) {
                final Object obj = getElementAt(row);
                if (obj instanceof BonitaSelectTableElement) {
                    final String sortOrder = ((BonitaSelectTableElement) obj).getSortOrder();
                    if (sortOrder != null && sortOrder != "") { //$NON-NLS-1$
                        hasOrder = true;
                    }
                }
            }

            if (getCurrentStatement() instanceof QuerySelectStatement) {
                final List sqlOrderByClause = ((QuerySelectStatement) getCurrentStatement()).getOrderByClause();
                if (sqlOrderByClause != null && !sqlOrderByClause.isEmpty()) {
                    int orderNumbers = sqlOrderByClause.size();
                    if (orderNumbers >= 0) {
                        orderByExpressionsExist = true;
                        if (!hasOrder) // if the item has no order, then we want to add another numeric for the next order
                        {
                            orderNumbers++; // increment by one for the next order expression to be added
                        }
                        final LabelValuePair[] sortOrderItems = new LabelValuePair[orderNumbers];
                        for (int i = 0; i < orderNumbers; i++) {
                            final String item = Integer.valueOf(i + 1).toString();
                            sortOrderItems[i] = new LabelValuePair(item, item);
                        }

                        sortOrderCellEditor.createItems(sortOrderItems);
                    }
                }
            }
            if (!orderByExpressionsExist) {
                final LabelValuePair[] sortOrderItems = new LabelValuePair[1];
                final String item = Integer.valueOf(1).toString();
                sortOrderItems[0] = new LabelValuePair(item, item);
                sortOrderCellEditor.createItems(sortOrderItems);
            }
        }
    }

    /**
     * Cell editor for column alias
     */
    class AliasCellEditor extends TextCellEditor implements org.eclipse.swt.events.FocusListener {

        public AliasCellEditor(Composite parent) {
            super(parent);
            text.addFocusListener(this);
        }

        @Override
        public void focusLost(org.eclipse.swt.events.FocusEvent e) {
            fireApplyEditorValue();
        }

        @Override
        public void focusGained(org.eclipse.swt.events.FocusEvent e) {

        }

        @Override
        protected void doSetValue(Object value) {
            if (value instanceof BonitaSelectTableElement) {
            	final BonitaSelectTableElement ste = (BonitaSelectTableElement) value;
                final String result = ste.getColumnText(1);
                super.doSetValue(result);
            }
        }
    }

    /**
     * Cell editor for check box. This is an image that toggles based on
     * model value
     */
    class OutputCheckboxCellEditor extends CheckboxCellEditor {

        public OutputCheckboxCellEditor(Composite parent) {
            super(parent);
        }
        
        @Override
        protected void doSetValue(Object value) {
            if (value instanceof BonitaSelectTableElement) {
            	final BonitaSelectTableElement ste = (BonitaSelectTableElement) value;
                final String result = ste.getColumnText(2);

                super.doSetValue(Boolean.valueOf(result));
            }
        }

        @Override
        public void activate() {
        	super.activate();
            deactivate();
            Display.getCurrent().getFocusControl().redraw();
        }

    }

    public void setEnabled(boolean enable) {
        table.setEnabled(enable);
    }
}
