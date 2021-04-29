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

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.sqlbuilder.ex.builder.BonitaBuilderUtility;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaCriteriaElement;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.provider.CriteriaContentProvider;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.VendorHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.EditComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.NavigableTableViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.TableNavigator;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaValueExpression;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.RemoveCriteriaAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaCriteriaGridViewer extends NavigableTableViewer implements IMenuListener,SWTBotConstants {

	public final static String P_STATEMENT_COLUMN = "org.eclipse.datatools.sqltools.sqlbuilder.CriteriaGridViewer.P_STATEMENT_COLUMN";
	public final static String P_STATEMENT_OPERATOR = "org.eclipse.datatools.sqltools.sqlbuilder.CriteriaGridViewer.P_STATEMENT_OPERATOR";
	public final static String P_STATEMENT_VALUE = "org.eclipse.datatools.sqltools.sqlbuilder.CriteriaGridViewer.P_STATEMENT_VALUE";
	public final static String P_STATEMENT_ANDOR = "org.eclipse.datatools.sqltools.sqlbuilder.CriteriaGridViewer.P_STATEMENT_ANDOR";

	public final static String P_STATEMENT_AS_TABLE_ROWS = "org.eclipse.datatools.sqltools.sqlbuilder.CriteriaGridViewer.P_STATEMENT_AS_TABLE_ROWS";

	// We do not want empty strings in the dropdowns, hence removed the "" from the array 
	public static final String dB2EveryplaceOperators[] = 
		{  "=", "<", "<=", ">", ">=", "<>", "IS NULL", "IS NOT NULL", "LIKE", "NOT LIKE", "IN", "NOT IN" };

	// We do not want empty strings in the dropdowns, hence removed the "" from the array 
	public static final String operators[] =
		{  "=", "<", "<=", ">", ">=", "<>", "BETWEEN", "NOT BETWEEN", "IS NULL", "IS NOT NULL", "LIKE", "NOT LIKE",
		"IN", "NOT IN", "EXISTS", "XMLEXISTS" }; 

	String[] columnProperties;
	boolean isHaving;

	BonitaDynamicComboBoxCellEditor columnEditor;
	ComboBoxCellEditor operatorEditor;
	ComboBoxCellEditor andOrEditor;
	BonitaDynamicComboBoxCellEditor rightPredicateValueEditor;

	Table table;
	SQLQueryObject currentSQLStatement;
	TableColumn c1, c2, c3, c4;
	SQLDomainModel domainModel;

    public BonitaCriteriaGridViewer(int style, SQLDomainModel domainModel, List<Expression> filteredExpressions, Composite parent, boolean having) {
		super(new Table(parent, style ));        
		this.domainModel = domainModel;
		this.isHaving = having;
		table = getTable();
		final TableNavigator navigator =  getNavigator();
		navigator.setVisible(true);
		table.setData(SWTBOT_WIDGET_ID_KEY, SQLBUILDER_CRITERIA_TREE_ID);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(table, SQLBuilderContextIds.SQSS_SHARED_SEL_UP_DEL_GRID);

        columnEditor = new BonitaDynamicComboBoxCellEditor(table, filteredExpressions, null, this);
		c1 = new TableColumn(table, SWT.NULL);
		c1.setText(Messages._UI_COLUMN_COLUMN);

		operatorEditor = new ComboBoxCellEditor(table, null);
		c2 = new TableColumn(table, SWT.NULL);
		c2.setText(Messages._UI_COLUMN_CRITERIA_OPERATOR);

		final VendorHelper vHelper = new VendorHelper(domainModel.getDatabase());
		if (vHelper.isDB2Everyplace()) {
			fillOperatorComboBox(dB2EveryplaceOperators);
		}
		else {
			fillOperatorComboBox(operators);
		}

        rightPredicateValueEditor = new BonitaDynamicComboBoxCellEditor(table, filteredExpressions, null, this);
		c3 = new TableColumn(table, SWT.NULL);
		c3.setText(Messages._UI_COLUMN_CRITERIA_VALUE);

		andOrEditor = new org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor(table, null);
		c4 = new TableColumn(table, SWT.NULL);
		c4.setText(Messages._UI_COLUMN_CRTIERIA_AND_OR);
		fillAndOrComboBox();

		final TableLayout layout = new TableLayout();

		layout.addColumnData(new ColumnPixelData(160));
		layout.addColumnData(new ColumnPixelData(110));
		layout.addColumnData(new ColumnPixelData(140));
		layout.addColumnData(new ColumnPixelData(70));
		table.setLayout(layout);

		final String properties[] = { P_STATEMENT_COLUMN, P_STATEMENT_OPERATOR, P_STATEMENT_VALUE, P_STATEMENT_ANDOR };
		setColumnProperties(properties);

		setCellModifier(new CriteriaModifier());

		final CellEditor editors[] = { columnEditor, operatorEditor, rightPredicateValueEditor, andOrEditor };
		setCellEditors(editors);
		initialize();
	}

	@Override
    public void refresh() {
		if (!isCellEditorActive()) {
			super.refresh();
		}
	}

	@Override
    public void menuAboutToShow(IMenuManager menu) {
		final RemoveCriteriaAction removeCriteriaAction = new RemoveCriteriaAction(this);
		menu.add(removeCriteriaAction);

		final CriteriaValueExpression cvAction = new CriteriaValueExpression(this);
		menu.add(cvAction);
	}

	public void refreshCellEditor(int row) {
		boolean leftExprExistsExpression = false;
		boolean rightExprExistsExpression = false;

		final Object obj = getElementAt(row);
		if (obj instanceof BonitaCriteriaElement) {
			final BonitaCriteriaElement criteria = (BonitaCriteriaElement) obj;
			{
				final QueryValueExpression left = criteria.getColumn();
				final Object right = criteria.getObjectValue();
				if (left != null) {
					leftExprExistsExpression = true;
					//b370 nb 20040729 - begin
					//*****Added following to support single quotes in Expressionbuilder
					if (left instanceof ValueExpressionColumn) {
						final TableItem titem = table.getItem(row);
						final String opr = titem.getText(1);
						if (opr.equalsIgnoreCase("") || opr.equalsIgnoreCase("=") || opr.equalsIgnoreCase("<") || opr.equalsIgnoreCase("<=")
								|| opr.equalsIgnoreCase(">") || opr.equalsIgnoreCase(">=") || opr.equalsIgnoreCase("<>") || opr.equalsIgnoreCase("LIKE")
								|| opr.equalsIgnoreCase("NOT LIKE")) {
							rightPredicateValueEditor.setNeedQuotes(true);
						}
						else {
							rightPredicateValueEditor.setNeedQuotes(false);
						}
						final ValueExpressionColumn sce = (ValueExpressionColumn) left;
						rightPredicateValueEditor.setPairDataType(sce.getDataType());
					}
					else {
						rightPredicateValueEditor.setNeedQuotes(false);
					}
					//*****END-Added following to support single quotes in Expressionbuilder
					//b370 nb 20040729 - end
				}
				if (right != null && right instanceof QueryValueExpression) {
					rightExprExistsExpression = true;
				}

			}
		}
		if (getInput() instanceof QueryStatement || getInput() instanceof QuerySelect) {
			BonitaBuilderUtility.fillColumnComboBox((EditComboBoxCellEditor) columnEditor, (SQLQueryObject) getInput(), false, false);
			BonitaBuilderUtility.fillColumnComboBox((EditComboBoxCellEditor) rightPredicateValueEditor, (SQLQueryObject) getInput(), false, false);
		}
		final CellEditor editors[] = { columnEditor, operatorEditor, rightPredicateValueEditor, andOrEditor };

		setCellEditors(editors);
	}

	protected void initialize() {
		initializeContentViewer();
		final MenuManager contextMenu = new MenuManager("#PopUp");
		contextMenu.add(new Separator("additions"));
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		final Menu menu = contextMenu.createContextMenu(getControl());
		getControl().setMenu(menu);
	}

	protected void initializeContentViewer() {
		columnProperties = new String[4];
		columnProperties[0] = P_STATEMENT_COLUMN;
		columnProperties[1] = P_STATEMENT_OPERATOR;
		columnProperties[2] = P_STATEMENT_VALUE;
		columnProperties[3] = P_STATEMENT_ANDOR;

        final CriteriaContentProvider contentProvider = new CriteriaContentProvider(domainModel, isHaving);
		setContentProvider(contentProvider);
		setLabelProvider(new CriteriaGridLabelProvider());
	}

	@Override
    protected void inputChanged(java.lang.Object input, java.lang.Object oldInput) {
		super.inputChanged(input, oldInput);
		currentSQLStatement = (SQLQueryObject) input;

		setGridTitle();
	}

	private void setGridTitle() {
	}

	//
	// Note: the items in this comboBox are NOT translatable
	//
	protected void fillOperatorComboBox(String operatorList[]) {
		LabelValuePair[] operatorItems;
		// Do not show XMLEXISTS unless connection supports xml functions
		final DatabaseDefinition dbDef = domainModel.getDatabaseDefinition();
		if (dbDef != null && dbDef.supportsXML()) { 
			operatorItems = new LabelValuePair[operatorList.length];    		
		}
		else {
			// the last Item is XMLEXISTS, don't include it
			operatorItems = new LabelValuePair[operatorList.length - 1];
		}    	

		for (int i = 0; i < operatorItems.length; i++) {
			operatorItems[i] = new LabelValuePair(operatorList[i], operatorList[i]);
		}
		operatorEditor.createItems(operatorItems);
	}

	protected void fillAndOrComboBox() {
		final LabelValuePair[] andOrItems = new LabelValuePair[3];
		andOrItems[0] = new LabelValuePair("", "");
		andOrItems[1] = new LabelValuePair("AND", "AND");
		andOrItems[2] = new LabelValuePair("OR", "OR");
		andOrEditor.createItems(andOrItems);
	}

	/**
	 * Cell editor for value
	 */
	class ValueCellEditor extends TextCellEditor {

		public ValueCellEditor(Composite parent) {
			super(parent);
		}

		@Override
        protected void doSetValue(Object value) {
			if (value instanceof CriteriaElement) {
				final CriteriaElement cre = (CriteriaElement) value;
				final String result = cre.getColumnText(2);
				super.doSetValue(result);
			}
		}
	}

	class CriteriaGridLabelProvider extends LabelProvider implements ITableLabelProvider {

		@Override
        public String getColumnText(Object object, int columnIndex) {
			if (object instanceof CriteriaElement) {
				final CriteriaElement criteriaElement = (CriteriaElement) object;
				return criteriaElement.getColumnText(columnIndex);
			}
			return "";
		}

		@Override
        public Image getColumnImage(Object object, int columnIndex) {
			return null;
		}

	}

	public void setEnabled(boolean enable) {
		table.setEnabled(enable);
	}
}
