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

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaSelectTableElement;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridContentProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaSelectGridContentProvider extends GridContentProvider {

    SQLDomainModel domainModel;
    private final List<Expression> filteredExpressions;

    public BonitaSelectGridContentProvider(SQLDomainModel domainModel, List<Expression> filteredExpressions) {
        super(domainModel.getAdapterFactory());
        this.domainModel = domainModel;
        this.filteredExpressions = filteredExpressions;
    }

    @Override
    public Object[] getElements(java.lang.Object property) {
    	
    	// This is done is super class AdapterFactoryContentProvider.getElements(), but
    	// since this method does not call the superclass method, it must be done here
    	// as well.  Without this call, notifications will not be enabled for the SQL object
        adapterFactory.adapt(property, IStructuredItemContentProvider.class);
        
        if (property instanceof QuerySelectStatement || property instanceof QuerySelect) {
            tableElements = new Vector();
            QuerySelect qSelect = null;
            if (property instanceof QuerySelectStatement) {
                qSelect = SelectHelper.getQuerySelect((QuerySelectStatement) property);
            }
            else {
                qSelect = (QuerySelect) property;
            }

            if (qSelect != null) {
                final List resultColList = qSelect.getSelectClause();
                if (resultColList != null) {
                    tableElements = new Vector();
                    final Iterator resultColListIter = resultColList.iterator();
                    while (resultColListIter.hasNext()) {
                        final QueryResultSpecification resultSpec = (QueryResultSpecification) resultColListIter.next();
                        if (resultSpec instanceof ResultColumn) {
                            final ResultColumn resultCol = (ResultColumn) resultSpec;
                            final BonitaSelectTableElement tblElement = new BonitaSelectTableElement(domainModel, property, resultCol);
                            tableElements.add(tblElement);
                        }
                    }
                }

                if (property instanceof QuerySelectStatement) {
                    final List orderByColList = ((QuerySelectStatement) property).getOrderByClause();
                    if (orderByColList != null && orderByColList.size() > 0) {
                        final Iterator iterator = orderByColList.iterator();
                        while (iterator.hasNext()) {
                            final Object ordCol = iterator.next();
                            if (ordCol instanceof OrderByValueExpression) {
                                final OrderByValueExpression ordValExpr = (OrderByValueExpression) ordCol;
                                final BonitaSelectTableElement tblElement = new BonitaSelectTableElement(domainModel, property, ordValExpr);
                                tableElements.add(tblElement);
                            }
                        }
                    }
                }

                // Now add in the blank row
                tableElements.add(new BonitaSelectTableElement(domainModel, property, (ResultColumn) null));
            }
            return tableElements.toArray();
        }
        return null;
    }
}
