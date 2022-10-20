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
import java.util.Vector;

import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaValueTableElement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BaseWindow;
import org.eclipse.datatools.sqltools.sqlbuilder.views.EditComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridContentProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.views.Modifier;
import org.eclipse.datatools.sqltools.sqlbuilder.views.NavigableTableViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.RemoveValuesRowAction;
import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.ValueTableElement;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaValuesRowViewer extends BaseWindow {
	
	protected SQLDomainModel domainModel;
    protected ValuesGridViewer valuesGrid;
    protected ValuesRow valuesRow;

	public BonitaValuesRowViewer(SQLDomainModel domainModel) {
		super(domainModel);
		 this.domainModel = domainModel;
	}
	
    public Control createControl(Composite parent) {
        client = ViewUtility.createComposite(parent, 1);
        valuesGrid = new ValuesGridViewer(client);
        return client;
    }

    public void setInput(Object element) {
        super.setInput(element);
        valuesGrid.setInput(element);
        valuesRow = (ValuesRow) element;
    }

    public void handleEvent(Event e) {
    }

    public class ValuesGridViewer extends NavigableTableViewer implements IMenuListener {

        protected Table table;
        protected TableColumn c1;
        protected ValuesComboBoxCellEditor valuesRowCellEditor;
        protected ValuesRowGridLabelProvider valuesRowLabelProvider;

        public ValuesGridViewer(Composite parent) {
            super(new Table(parent, SWT.FULL_SELECTION | SWT.MULTI));

            table = getTable();
            table.setLinesVisible(true);
            table.setHeaderVisible(true);

            table.setLayoutData(ViewUtility.createFill());
            TableLayout layout = new TableLayout();
            layout.addColumnData(new ColumnWeightData(100, true)); // expression
            table.setLayout(layout);

            c1 = new TableColumn(table, SWT.NULL);
            c1.setText(Messages._UI_COLUMN_VALUES_ROW_ITEM);

            String columnProperties[] = { (String) SQLBuilderConstants.P_STATEMENT_VALUE };
            setColumnProperties(columnProperties);

            LabelValuePair valueComboItems[] = {};// new LabelValuePair(SQLBuilderConstants.P_BUILD_EXPRESSION, SQLBuilderConstants.P_BUILD_EXPRESSION) };

            valuesRowCellEditor = new ValuesComboBoxCellEditor(table, valueComboItems, false, this);
            CellEditor editors[] = { valuesRowCellEditor };

            setCellEditors(editors);
            setCellModifier(new Modifier());

            this.setContentProvider(new ValuesRowGridContentProvider(domainModel.getAdapterFactory()));
            this.setLabelProvider(new ValuesRowGridLabelProvider());

            MenuManager contextMenu = new MenuManager("#PopUp");
            contextMenu.add(new Separator("additions"));
            contextMenu.setRemoveAllWhenShown(true);
            contextMenu.addMenuListener(this);
            Menu menu = contextMenu.createContextMenu(getControl());
            getControl().setMenu(menu);
        }

        /**
         * The context menu is about to appear.  Populate it.
         */
        public void menuAboutToShow(IMenuManager menu) {
            RemoveValuesRowAction removeValuesRowAction = new RemoveValuesRowAction(this);
            menu.add(removeValuesRowAction);
        }

        protected void inputChanged(Object newInput, Object oldInput) {
            super.inputChanged(newInput, oldInput);
        }

        public void refresh() {
            if (!isCellEditorActive()) {
                super.refresh();
            }
        }

        public void refreshCellEditor(int row) {
            Object obj = getElementAt(row);

            LabelValuePair valueComboItems[] = {};
            valuesRowCellEditor.createItems(valueComboItems);
            if (obj instanceof BonitaValueTableElement) {
                Object expr = ((BonitaValueTableElement) obj).getExpression();
                if (expr != null) {
                    LabelValuePair valueComboItems2[] = { new LabelValuePair(SQLBuilderConstants.P_EDIT_EXPRESSION, SQLBuilderConstants.P_EDIT_EXPRESSION),
                            new LabelValuePair(SQLBuilderConstants.P_REPLACE_EXPRESSION, SQLBuilderConstants.P_REPLACE_EXPRESSION) };
                    valuesRowCellEditor.createItems(valueComboItems2);
                }
            }
            CellEditor editors[] = { valuesRowCellEditor };
            setCellEditors(editors);
        }
    }

    /**
     * Value grid content provider
     */
    public class ValuesRowGridContentProvider extends GridContentProvider {

        public ValuesRowGridContentProvider(AdapterFactory adapterFactory) {
            super(adapterFactory);
        }

        public Object[] getElements(Object property) {
        	
        	// This is done is super class AdapterFactoryContentProvider.getElements(), but
        	// since this method does not call the superclass method, it must be done here
        	// as well.  Without this call, notifications will not be enabled for the SQL object
            adapterFactory.adapt(property, IStructuredItemContentProvider.class);
            
            if (property instanceof ValuesRow) {
                tableElements = new Vector();

                ValuesRow valRow = (ValuesRow) property;
                if (valRow != null) {
                    Iterator iterator = valRow.getExprList().iterator();
                    while (iterator.hasNext()) {
                        QueryValueExpression expr = (QueryValueExpression) iterator.next();
                        createNewValueElement(valRow, expr);
                    }
                }

                createNewValueElement(valRow, null);
            }
            return tableElements.toArray();
        }
        
        /**
         * Create a new ValueTableElement
         */
        private void createNewValueElement(ValuesRow row, QueryValueExpression expr) {
            tableElements.add(new BonitaValueTableElement(domainModel, row, expr));
        }
    }

    /**
     * Value grid label provider
     */
    class ValuesRowGridLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            if (object instanceof ValueTableElement) {
                ValueTableElement element = (ValueTableElement) object;
                return element.getColumnText(columnIndex);
            }
            return "";
        }

        public Image getColumnImage(Object object, int columnIndex) {
            return null;
        }
    }

    class ValuesComboBoxCellEditor extends EditComboBoxCellEditor {

        ValuesGridViewer viewer;

        public ValuesComboBoxCellEditor(Composite parent, LabelValuePair[] items, boolean addItems, ValuesGridViewer viewer) {
            super(parent, items, addItems);
            this.viewer = viewer;
        }

        protected void refreshComboItems() {
            int row = viewer.getTable().getSelectionIndex();
            if (row >= 0) {
                viewer.refreshCellEditor(row);
            }
        }

        /**
         * Creates the new LabelValuePair
         */

        protected LabelValuePair createComboBoxItem(String newValue) {
            if (!newValue.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) && !newValue.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)
                    && !newValue.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION)) {
                return new LabelValuePair(newValue, ExpressionHelper.createExpression(newValue));
            }

            return new LabelValuePair(newValue, newValue);
        }

        /**
         * Override cell editor doSetValue to launch the expression builder
         *  for Build expression... selection from combo box
         */

        protected void doSetValue(Object value) {
            super.doSetValue(value);
        }
    }

}
