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
package org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.provider;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaInsertTableElement;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridContentProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaInsertGridContentProvider extends GridContentProvider {

	  SQLDomainModel domainModel;

    public BonitaInsertGridContentProvider(SQLDomainModel domainModel) {
	        super(domainModel.getAdapterFactory());
	        this.domainModel = domainModel;
	    }

	    @Override
        public Object[] getElements(java.lang.Object property) {
	    	
	    	// This is done is super class AdapterFactoryContentProvider.getElements(), but
	    	// since this method does not call the superclass method, it must be done here
	    	// as well.  Without this call, notifications will not be enabled for the SQL object
	    	adapterFactory.adapt(property, IStructuredItemContentProvider.class);
	    	
	        Object[] valuesArray = null;
	        if (property instanceof QueryInsertStatement) {
	            final List tableElementsList = new ArrayList();
	            final QueryInsertStatement insertStmt = (QueryInsertStatement) property;
	            final List columnList = insertStmt.getTargetColumnList();
	            final List valuesRowList = insertStmt.getSourceValuesRowList();
	            for (int i = 0; i < columnList.size(); i++) {
	                if (valuesRowList != null && !valuesRowList.isEmpty()) {
	                    final ValueExpressionColumn column = (ValueExpressionColumn) columnList.get(i);

	                    final ValuesRow row = (ValuesRow) valuesRowList.get(0); //we deal with only single row insert for now
	                    final List exprList = row.getExprList();
	                    final int size = exprList.size();
	                    QueryValueExpression expr = null;
	                    if (columnList.size() == size) {
	                        expr = (QueryValueExpression) exprList.get(i);
	                    }

                    final BonitaInsertTableElement tableElement = new BonitaInsertTableElement(domainModel, insertStmt, column, expr);
	                    tableElementsList.add(tableElement);
	                }
	            }
	            //add a blank row 
            tableElementsList.add(new BonitaInsertTableElement(domainModel, insertStmt, null, null));
	            valuesArray = tableElementsList.toArray();
	        }
	        return valuesArray;
	    }
}
