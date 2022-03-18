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
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.views.DesignViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.delete.DeleteViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.FullSelectViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.ValuesRowViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.ValuesViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.update.UpdateViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.with.WithStatementViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.with.WithTableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaDesignViewer extends DesignViewer {

	protected Control fullSelect, valuesRowPage, valuesClause, withStatement, withTable, split;
	protected BonitaSelectViewer selectViewer;
	protected BonitaInsertViewer insertViewer;
    protected UpdateViewer updateViewer;
    protected DeleteViewer deleteViewer;
	protected WithStatementViewer withStatementViewer;
	protected WithTableViewer withTableViewer;
	protected FullSelectViewer fullSelectViewer;
    protected ValuesRowViewer valuesRowViewer;
	protected ValuesViewer valuesViewer;
	protected SQLDomainModel sqlDomainModel;    
	private Object previousObject; // variable for refresh solution
    private final List<Expression> filteredExpressions;

    public BonitaDesignViewer(SQLDomainModel domainModel, List<Expression> filteredExpressions, Composite parent) {
		super(domainModel, parent);
		sqlDomainModel = domainModel ;
        this.filteredExpressions = filteredExpressions;
		createBonitaControl(this) ;
	}

	/**
	 * Returns SWT Widget that will be made the client of the view pane.
	 */
	protected void createBonitaControl(Composite parent) {
		for(final Control c : parent.getChildren()){
			c.dispose() ;
		}
		// select Viewer
        selectViewer = new BonitaSelectViewer(sqlDomainModel, filteredExpressions);
		selectViewer.createControl(parent);

		
		// insert Viewer
        insertViewer = new BonitaInsertViewer(sqlDomainModel, filteredExpressions);
		insertViewer.createControl(parent);
		insertViewer.setDesignViewer(this);

		// update Viewer
        updateViewer = new UpdateViewer(sqlDomainModel);
		updateViewer.createControl(parent);

		// delete Viewer
        deleteViewer = new DeleteViewer(sqlDomainModel);
		deleteViewer.createControl(parent);

		//
		// Add the With Statement viewer
		// 
		withStatementViewer = new WithStatementViewer(sqlDomainModel);
		withStatement = withStatementViewer.createControl(parent);

		//
		// Add the With Table viewer
		//
		withTableViewer = new WithTableViewer(sqlDomainModel);
		withTable = withTableViewer.createControl(parent);

		//
		// Add the FullSelect viewers
		//
		fullSelectViewer = new FullSelectViewer(sqlDomainModel);
		fullSelect = fullSelectViewer.createControl(parent);


        valuesRowViewer = new ValuesRowViewer(sqlDomainModel);
		valuesRowPage = valuesRowViewer.createControl(parent);

		valuesViewer = new ValuesViewer(sqlDomainModel);
		valuesClause = valuesViewer.createControl(parent);

	}

	@Override
	public void inputChanged(Object input) {        
		previousObject = input;
		if (input instanceof QueryInsertStatement) {
			showPage(insertViewer.getControl());            
			insertViewer.setInput(input);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_INSERT_VIEW);
		}
		else if (input instanceof QueryUpdateStatement) {
			showPage(updateViewer.getControl());            
			updateViewer.setInput(input);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_UPDATE_VIEW);
		}
		else if (input instanceof QueryDeleteStatement) {
			showPage(deleteViewer.getControl());            
			deleteViewer.setInput(input);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_DELETE_VIEW);
		}
		else if (input instanceof QuerySelectStatement) {
			final QueryExpressionBody queryBody = SelectHelper.getQueryExpressionBody((QuerySelectStatement) input);
			if (queryBody instanceof QuerySelect) {
				selectViewer.setInput(input);
				showPage(selectViewer.getControl());                
				// WITH statements use this viewer as well, set the help accordingly
				final QuerySelectStatement sqlSelectStatement = (QuerySelectStatement)input;
				final List withClause = sqlSelectStatement.getQueryExpr().getWithClause();
				if(withClause.isEmpty())
				{
					PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_SELECT_VIEW);
				}
				else
				{
					PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_WITH_STATEMENT_VIEW);
				}
			}
			else if (queryBody instanceof QueryCombined) {
				showPage(fullSelect);                
				fullSelectViewer.setInput(queryBody);
				PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_FULL_SELECT_VIEW);
			}
			else if (queryBody instanceof QueryValues) {
				showPage(valuesRowPage);                
				valuesRowViewer.setInput(queryBody);
				PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_VALUES_CLAUSE_VIEW);
			}
			else {
				selectViewer.setInput(input);
				showPage(selectViewer.getControl());                
				PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_SELECT_VIEW);
			}

		}
		else if (input instanceof WithTableSpecification) {
			showPage(withTable);            
			withTableViewer.setInput(input);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_WITH_TABLE_VIEW);
		}
		else if (input instanceof QuerySelect) {
			selectViewer.setInput(input);
			showPage(selectViewer.getControl());            
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_SELECT_VIEW);
		}
		else if (input instanceof QueryCombined) {
			showPage(fullSelect);            
			fullSelectViewer.setInput(input);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_FULL_SELECT_VIEW);
		}
		else if (input instanceof QueryValues) {
			showPage(valuesClause);            
			valuesViewer.setInput(input);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_VALUES_CLAUSE_VIEW);
		}
		else if (input instanceof ValuesRow) {
			showPage(valuesRowPage);             
			valuesRowViewer.setInput(input);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getParent(), SQLBuilderContextIds.SQLB_VALUES_ROW_VIEW);
		}
	}

	/**
	 * Forces the design viewer to changes the input so that the GUI will show the latest
	 * model values.  This is a work around solution for the model notification issues
	 */
	@Override
    public void forceRefresh() {    	
		inputChanged(previousObject);
	}

	@Override
	public void setEnabled(boolean enable) {
		selectViewer.setEnabled(enable);
		insertViewer.setEnabled(enable);
		updateViewer.setEnabled(enable);
		deleteViewer.setEnabled(enable);
		withStatementViewer.setEnabled(enable);
		fullSelectViewer.setEnabled(enable);
	}
}
