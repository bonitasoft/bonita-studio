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
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaInsertTableElement;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.provider.BonitaInsertGridContentProvider;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.XMLDataType;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.insert.RemoveInsertColumnAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.PageBook;


/**
 * @author Romain Bioteau
 *
 */
public class BonitaInsertGridViewer extends GridViewer {

	TableColumn c2;
	SQLDomainModel domainModel;
	BonitaDynamicComboBoxCellEditor insertValueCellEditor;

    public BonitaInsertGridViewer(SQLDomainModel domainModel, List<Expression> filteredExpressions, PageBook insertPageBook) {
		super(domainModel, insertPageBook);
		this.domainModel = domainModel;

		PlatformUI.getWorkbench().getHelpSystem().setHelp(table, SQLBuilderContextIds.SQLB_INSERT_VIEW);

		c2 = new TableColumn(table, SWT.NULL);
		c2.setText(Messages._UI_COLUMN_INSERT_VALUE);

		final TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnPixelData(200)); // column name
		layout.addColumnData(new ColumnPixelData(200));
		table.setLayout(layout);

		final String columnProperties[] = { (String) SQLBuilderConstants.P_STATEMENT_COLUMN, (String) SQLBuilderConstants.P_STATEMENT_VALUE };
		setColumnProperties(columnProperties);

		//	        LabelValuePair valueComboItems[] = { new LabelValuePair(SQLBuilderConstants.P_VALUE_DEFAULT, SQLBuilderConstants.P_VALUE_DEFAULT),
		//	                new LabelValuePair(SQLBuilderConstants.P_VALUE_NULL, SQLBuilderConstants.P_VALUE_NULL),
		//	                new LabelValuePair(SQLBuilderConstants.P_BUILD_EXPRESSION, SQLBuilderConstants.P_BUILD_EXPRESSION) };

        insertValueCellEditor = new BonitaDynamicComboBoxCellEditor(table, filteredExpressions, null, this);
		// Create the cell editors
		final CellEditor editors[] = { columnComboBoxCellEditor, insertValueCellEditor };
		setCellEditors(editors);

		setCellModifier(new BonitaModifier());

        final BonitaInsertGridContentProvider gridContentProvider = new BonitaInsertGridContentProvider(domainModel);

		//TODO QMP-ALL provider
		setContentProvider(gridContentProvider);
		final InsertGridLabelProvider insertGridLabelProvider = new InsertGridLabelProvider();
		setLabelProvider(insertGridLabelProvider);

	}

	public SQLDomainModel getDomainModel() {
		return domainModel;
	}

	class InsertGridLabelProvider extends LabelProvider implements ITableLabelProvider {

		@Override
        public String getColumnText(Object object, int columnIndex) {
			if (object instanceof BonitaInsertTableElement) {
				final BonitaInsertTableElement insertElement = (BonitaInsertTableElement) object;
				return insertElement.getColumnText(columnIndex);
			}
			return ""; //$NON-NLS-1$
		}

		@Override
        public Image getColumnImage(Object object, int columnIndex) {
			return null;
		}
	}

	@Override
    public void menuAboutToShow(IMenuManager menu) {
		final RemoveInsertColumnAction removeColumnAction = new RemoveInsertColumnAction(this);
		menu.add(removeColumnAction);
	}

	@Override
    public void inputChanged(java.lang.Object input, java.lang.Object oldInput) {
		super.inputChanged(input, oldInput);
		setGridTitle();
	}

	private void setGridTitle() {
		//setTitle("Insert Row");
	}

	public void refreshCellEditor(int row) {
		final Object obj = getElementAt(row);
		int numOfItems = 1;
		if (domainModel.getVendor().isDB2()) {
			numOfItems = 2;
		}

		final LabelValuePair[] valueComboItems = new LabelValuePair[numOfItems];

		if (domainModel.getVendor().isDB2()) {
			valueComboItems[0] = new LabelValuePair(SQLBuilderConstants.P_VALUE_DEFAULT, SQLBuilderConstants.P_VALUE_DEFAULT);
			valueComboItems[1] = new LabelValuePair(SQLBuilderConstants.P_VALUE_NULL, SQLBuilderConstants.P_VALUE_NULL);
//			valueComboItems[2] = new LabelValuePair(SQLBuilderConstants.P_BUILD_EXPRESSION, SQLBuilderConstants.P_BUILD_EXPRESSION);
		}
		else {
			valueComboItems[0] = new LabelValuePair(SQLBuilderConstants.P_VALUE_NULL, SQLBuilderConstants.P_VALUE_NULL);
//			valueComboItems[1] = new LabelValuePair(SQLBuilderConstants.P_EDIT_EXPRESSION, SQLBuilderConstants.P_EDIT_EXPRESSION);
		}

		insertValueCellEditor.createItems(valueComboItems);
		insertValueCellEditor.setNeedQuotes(false);

		//LabelValuePair[] valueComboItems2 = null;
		if (obj instanceof BonitaInsertTableElement) {
			final Object expr = ((BonitaInsertTableElement) obj).getExpression();
			final BonitaInsertTableElement insTE = (BonitaInsertTableElement) obj;
			final ValueExpressionColumn colExpr =  insTE.getColumn();
			if (expr != null) {
				int numOfItems2 = 2;
				if (domainModel.getVendor().isDB2()) {
					if (colExpr != null && (colExpr.getDataType() instanceof XMLDataType ||
							colExpr.getDataType() instanceof CharacterStringDataType)) {
						numOfItems2 = 5;
					}
					else {
						numOfItems2 = 4;
					}
				}

				final LabelValuePair[] valueComboItems2 = new LabelValuePair[numOfItems2];

				if (domainModel.getVendor().isDB2()) {
					valueComboItems2[0] = new LabelValuePair(SQLBuilderConstants.P_VALUE_DEFAULT, SQLBuilderConstants.P_VALUE_DEFAULT);
					valueComboItems2[1] = new LabelValuePair(SQLBuilderConstants.P_VALUE_NULL, SQLBuilderConstants.P_VALUE_NULL);
//					valueComboItems2[2] = new LabelValuePair(SQLBuilderConstants.P_EDIT_EXPRESSION, SQLBuilderConstants.P_EDIT_EXPRESSION);
//					valueComboItems2[3] = new LabelValuePair(SQLBuilderConstants.P_REPLACE_EXPRESSION, SQLBuilderConstants.P_REPLACE_EXPRESSION);  
//					if (colExpr != null && (colExpr.getDataType() instanceof XMLDataType ||
//							colExpr.getDataType() instanceof CharacterStringDataType)) {
//						valueComboItems2[4] = new LabelValuePair(SQLBuilderConstants.P_EDIT_INPUT_VALUE,
//								SQLBuilderConstants.P_EDIT_INPUT_VALUE);
//					}
				}
				else {
					valueComboItems2[0] = new LabelValuePair(SQLBuilderConstants.P_VALUE_NULL, SQLBuilderConstants.P_VALUE_NULL);
			//		valueComboItems2[1] = new LabelValuePair(SQLBuilderConstants.P_EDIT_EXPRESSION, SQLBuilderConstants.P_EDIT_EXPRESSION);
//					valueComboItems2[2] = new LabelValuePair(SQLBuilderConstants.P_REPLACE_EXPRESSION, SQLBuilderConstants.P_REPLACE_EXPRESSION);
				}
				//insertValueCellEditor.createItems(valueComboItems2);
			}
			//InsertTableElement insTE = (InsertTableElement) obj;
			//ValueExpressionColumn colExpr =  insTE.getColumn();
			if (colExpr != null) {
				insertValueCellEditor.setNeedQuotes(true);
				insertValueCellEditor.setQuotesContext("insert"); //$NON-NLS-1$
				insertValueCellEditor.setPairDataType(colExpr.getDataType());              
			}
			else {
				insertValueCellEditor.setNeedQuotes(false);
			}

		}
		final CellEditor editors[] = { columnComboBoxCellEditor, insertValueCellEditor };
		setCellEditors(editors);
	}

	public void setEnabled(boolean enable) {
		table.setEnabled(enable);
	}
}
