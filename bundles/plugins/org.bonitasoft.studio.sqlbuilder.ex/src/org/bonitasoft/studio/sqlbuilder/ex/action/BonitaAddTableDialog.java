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
package org.bonitasoft.studio.sqlbuilder.ex.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLStringHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.VendorHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.provider.rdbschema.AvailableTablesTreeProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaAddTableDialog extends Dialog {

	public static Object REPLACE_TABLE = "org.eclipse.datatools.sqltools.sqlbuilder.dialogs.AddTableDialog.ReplaceTable";
	public static Object ADD_TABLE = "org.eclipse.datatools.sqltools.sqlbuilder.dialogs.AddTableDialog.AddTable";
	public static Object TABLE_ALREADY_ADDED = "org.eclipse.datatools.sqltools.sqlbuilder.dialogs.AddTableDialog.TableAlreadyAdded";
	public static Object ADDING_PARENT_VIEW = "org.eclipse.datatools.sqltools.sqlbuilder.dialogs.AddTableDialog.AddingParentView";

	SQLObject tableValue;
	List tablesList;
	/* tree for selecting a schema/table */
	Tree availableTablesTree; 
	/* tree viewer for select schema/table tree */
	TreeViewer availableTablesTreeViewer; 
	Text aliasTextField;
	Label tableAliasLabel;
	Label errorLabel;
	String tableAlias;
	SQLDomainModel domainModel;
	Vector tableNames; // Holds tables that you specifically want to be
	// displayed. Passed in as arg.
	Object object; // SQLStatement
	Object action; // one of replace, add or tableAlreadyAdded
	//  TableComboSelectListener tableSelectListener;
	StatementHelper createStmtHelper;

	/**
	 * Creates a dialog with an OK and Cancel button.
	 */

	public BonitaAddTableDialog(Shell parentShell, SQLDomainModel domainModel, Object obj, Vector tables) {
		super(parentShell);
		this.domainModel = domainModel;
		this.object = obj;
		this.tableNames = tables;

		setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.CLOSE | SWT.BORDER | SWT.SYSTEM_MODAL);
		setBlockOnOpen(true);
		createStmtHelper = new StatementHelper(domainModel.getDatabase());
	}

	public int open() {
		VendorHelper vendorHelper = new VendorHelper(domainModel.getDatabase());
		QueryStatement stmt = domainModel.getSQLStatement();
		if (stmt instanceof QueryInsertStatement || ((stmt instanceof QueryDeleteStatement) && 
				(vendorHelper.isCloudscape() || vendorHelper.isSybase()))) {
			aliasTextField.setEnabled(false);
		}

		// Disable the OK button until the user selects a table.
		Button okButton = getButton(IDialogConstants.OK_ID);
		if (okButton != null) {
			okButton.setEnabled(false);
		}

		return super.open();
	}

	public void setFocusAliasTextField() {
		aliasTextField.forceFocus();
	}

	public void setAction(Object action) {
		this.action = action;
	}

	String replaceTitleText;

	public void setReplaceTitle(String tableName) {
		replaceTitleText = Messages._UI_DIALOG_REPLACE_TABLE_TITLE + " " + tableName;
	}

	protected void configureShell(Shell shell) {
		super.configureShell(shell);

		if (action == REPLACE_TABLE) {
			shell.setText(replaceTitleText);
		}
		else if (action == TABLE_ALREADY_ADDED) {
			shell.setText(Messages._UI_DIALOG_TABLE_ALREADY_ADDED);
		}
		else if (action == ADDING_PARENT_VIEW) {
			shell.setText(Messages._UI_DIALOG_ADD_VIEW_TO_SELF);
		}

		else {
			shell.setText(Messages._UI_DIALOG_ADD_TABLE_TITLE);
		}
	}

	protected void buttonPressed(int buttonId) {
		//int index = comboTables.getSelectionIndex();
		TreeItem[] items = availableTablesTreeViewer.getTree().getSelection();
		boolean aliasIsUnique = true;
		tableValue = null;
		tablesList = new ArrayList();
		tableAlias = aliasTextField.getText().trim();

		if (buttonId == Dialog.OK && items != null && items.length > 0) {
			if (!(object instanceof QueryInsertStatement)) {
				if (items.length > 1) {
					boolean currentTableExists = false;
					for (int i = 0; i < items.length; i++) {
						TreeItem treeItem = items[i];
						Object treeItemData = treeItem.getData();
						if (treeItemData instanceof Table) {
							tableValue = (Table) treeItemData;
						}
						else if (treeItemData instanceof WithTableSpecification) {
							tableValue = (WithTableSpecification) treeItemData;
						}
						if (tableValue != null) {

							Vector refTables = new Vector();
							SQLQueryObject stmt = (SQLQueryObject) object;
							List refTableList = StatementHelper.getTablesForStatement(stmt);
							if (refTableList.size() > 0) {
								refTables.addAll(refTableList);
								currentTableExists = checkForTable(refTables, tableValue);
							}
							if (!currentTableExists) {
								tablesList.add(tableValue);
							}
							else {
								errorLabel.setText(Messages._ERROR_TABLE_USED);
								availableTablesTree.forceFocus();
								return;
							}

						}
					}
				}
				else {
					//make sure all characters in alias are alphanumeric
					if (!SQLStringHelper.isAlphanumericOrUnderscore(tableAlias)) {
						errorLabel.setText(Messages._ERROR_ALIAS_NOT_ALPHANUMERIC);
						return;
					}

					boolean tableExists = false;

					if (aliasIsUnique) {
						//tableValue = (Table)tableList.elementAt(index);
						TreeItem treeItem = items[0];
						Object treeItemData = treeItem.getData();
						if (treeItemData instanceof Table) {
							tableValue = (Table) treeItemData;
						}
						else if (treeItemData instanceof WithTableSpecification) {
							tableValue = (WithTableSpecification) treeItemData;
						}

						if (tableValue == null) {
							//TODO: QMP-BGP need to either enable/disable OK
							// button to prevent this case or add error message
							return;
						}

						Vector refTables = new Vector();
						SQLQueryObject stmt = (SQLQueryObject) object;
						List refTableList = StatementHelper.getTablesForStatement(stmt);
						if (refTableList.size() > 0) {
							refTables.addAll(refTableList);
							tableExists = checkForTable(refTables, tableValue);
							aliasIsUnique = checkforAlias(refTables, aliasTextField.getText().trim());

						}

						if (tableExists && (tableAlias.length() < 1)) {
							errorLabel.setText(Messages._ERROR_TABLE_USED);
							//comboTables.forceFocus();
							availableTablesTree.forceFocus();
							return;
						}
						//createStmtHelper.addNewName(tableValue);
					}

					if (!aliasIsUnique) // alias is not unique
					{
						errorLabel.setText(Messages._ERROR_ALIAS_USED);
						aliasTextField.selectAll();
						aliasTextField.forceFocus();
						return;
					}
					setReturnCode(Dialog.OK);
					if (tableValue != null) {
						tablesList.add(tableValue);
					}
				}
			}
			else if (object instanceof QueryInsertStatement) {
				//tableValue = (Table) tableList.elementAt(index);
				TreeItem treeItem = items[0];
				Object treeItemData = treeItem.getData();
				if (treeItemData instanceof Table) {
					tableValue = (Table) treeItemData;
				}
				if (tableValue == null) {
					//TODO: QMP-BGP need to either enable/disable OK button to
					// prevent this case or add error message
					return;
				}

				tablesList.add(tableValue);
			}
		}
		else if (buttonId == Dialog.CANCEL) {
			setReturnCode(Dialog.CANCEL);
		}
		close();
	}

	public boolean checkForTable(Vector tableVector, SQLObject checkTable) {
		Iterator iterator = tableVector.iterator();
		while (iterator.hasNext()) {
			TableExpression sqlTable = (TableExpression) iterator.next();
			if (TableHelper.getExposedTableName(sqlTable).equalsIgnoreCase(checkTable.getName())) {
				return true;
			}
		}
		return false;
	}

	public boolean checkforAlias(Vector tableVector, String aliasName) {
		Iterator iterator = tableVector.iterator();
		while (iterator.hasNext()) {
			TableExpression sqlTable = (TableExpression) iterator.next();
			if (TableHelper.getExposedTableName(sqlTable).equalsIgnoreCase(aliasName)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Creates and returns the contents of an area of the dialog which appears
	 * below the message and above the button bar.
	 */
	public Control createDialogArea(Composite parent) {
		Composite panel = ViewUtility.createComposite(parent, 1, true, true);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(panel, SQLBuilderContextIds.SQLB_ADD_TABLE_DIALOG);
		createInputPanel(panel);

		return panel;
	}

	Composite createInputPanel(Composite parent) {
		Composite panel = ViewUtility.createComposite(parent, 2, true, true);
		ViewUtility.createLabel(panel, Messages._UI_LABEL_TABLE_NAME);

		// Create a tree control for selecting a schema/table. The tree is
		// dynamic in that
		// initially only schemas are loaded. Tables under schemas are loaded
		// only when the
		// schema node is expanded.
		QueryStatement stmt = domainModel.getSQLStatement();
		if (stmt instanceof QuerySelectStatement || stmt instanceof QuerySelect) {
			availableTablesTree = new Tree(panel, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		}
		else {
			availableTablesTree = new Tree(panel, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		}
		availableTablesTree.addSelectionListener(new TableSelectionListener());

		GridData treeGridData = new GridData(GridData.FILL_BOTH);
		treeGridData.widthHint = 50;
		treeGridData.heightHint = 100;
		availableTablesTree.setLayoutData(treeGridData);

		// Create a tree viewer on the tree that is customized so that
		// "isExpandable" is true
		// for the schema nodes at the top level. This ensures that the schema
		// nodes are
		// expandable even though we haven't loaded the children yet (and thus
		// don't yet know if
		// there are any children).
		availableTablesTreeViewer = new TreeViewer(availableTablesTree) {

			/**
			 * Overrides isExpandable in TreeViewer to make the schema (top
			 * level) nodes to be always expandable.
			 * 
			 * @param node the tree node in question
			 * @return true if node is expandable, false if not
			 */
			public boolean isExpandable(Object node) {
				boolean isExpandable = false;
				if (node instanceof Schema) {
					isExpandable = true;
				}
				else if (node instanceof TableCorrelation) { //added
					// TableCorrelation
					// for With Tables
					// Node
					isExpandable = true;
				}
				return isExpandable;
			}
		};

		// Get a list of available schemas from the database object.
		List schemaList = new ArrayList();
		Database database = domainModel.getDatabase();
		if (database != null){
			schemaList.addAll(getSchemaList(database));
		}

		// Create a content/label provider for the available tables tree viewer
		// and
		// associate it with the viewer.
		AvailableTablesTreeProvider provider = new AvailableTablesTreeProvider(schemaList, domainModel);
		availableTablesTreeViewer.setContentProvider(provider);
		availableTablesTreeViewer.setLabelProvider(provider);
		availableTablesTreeViewer.setInput(provider);

		// Create the table alias (correlation ID) field and add it to the
		// panel.
		tableAliasLabel = ViewUtility.createLabel(panel, Messages._UI_LABEL_TABLE_ALIAS);
		aliasTextField = ViewUtility.createTextField(panel, 240);
		errorLabel = ViewUtility.createLabel(panel, "");
		GridData gd = (GridData) errorLabel.getLayoutData();
		gd.horizontalSpan = 2;
		return panel;
	}

	private Collection getSchemaList(Database database) {
		List tempList = new ArrayList();

		if (database != null && database.getSchemas() != null && database.getSchemas().size() > 0) {
			tempList.addAll(database.getSchemas());
		}
		else if (database != null && database.getCatalogs() != null
				&& database.getCatalogs().size() > 0) {
			List catalogs = database.getCatalogs();
			Iterator itCatalogs = catalogs.iterator();
			while (itCatalogs.hasNext()){
				Catalog catalog = (Catalog) itCatalogs.next();
				String catName = catalog.getName();
				if (catName.equals(database.getName()) && catalog.getSchemas()  != null && catalog.getSchemas().size() > 0){
					for(Object schema : catalog.getSchemas()){
						if(((Schema)schema).getTables().size() > 0)
							tempList.add(schema);
					}
				}
			}
		}

		//Workaround if no table founds
		if(tempList.isEmpty()){
			List catalogs = database.getCatalogs();
			Iterator itCatalogs = catalogs.iterator();
			while (itCatalogs.hasNext()){
				Catalog catalog = (Catalog) itCatalogs.next();
				for(Object schema : catalog.getSchemas()){
					if(((Schema)schema).getTables().size() > 0){
						tempList.add(schema);
					}
				}
			}
		}

		return tempList;
	}

	/**
	 * Return the selected table
	 */
	public SQLObject getTableValue() {
		return tableValue;
	}

	public List getTablesList() {
		return tablesList;
	}

	/**
	 * Return the table alias for the selected table
	 */
	public String getTableAlias() {
		return tableAlias;
	}

	class TableSelectionListener implements SelectionListener {

		public void widgetDefaultSelected(SelectionEvent e) {

		}

		public void widgetSelected(SelectionEvent e) {
			TreeItem[] items = availableTablesTreeViewer.getTree().getSelection();

			// If the user has selected one or more tables then enable the OK button.
			// Otherwise disable it.
			boolean tableSelected = false;
			for (int i = 0; i < items.length; i++) {
				TreeItem treeItem = items[i];
				Object treeItemData = treeItem.getData();
				if (treeItemData instanceof Table || treeItemData instanceof WithTableSpecification) {
					tableSelected = true;
				}
			}

			Button okButton = getButton(IDialogConstants.OK_ID);
			if (okButton != null) {
				okButton.setEnabled(tableSelected);
			}

			// INSERT statement doesn't allow correlation ID (alias).
			if ((object instanceof QueryInsertStatement) == false) {
				// Disable and clear the alias field when more than one table is selected.
				if (items.length > 1) {
					aliasTextField.setEnabled(false);
					aliasTextField.setText("");
				}
				else {
					aliasTextField.setEnabled(true);
				}
			}
		}
	}
}
