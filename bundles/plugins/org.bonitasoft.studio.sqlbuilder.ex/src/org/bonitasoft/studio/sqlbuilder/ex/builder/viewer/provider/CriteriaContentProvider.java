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

import java.lang.ref.WeakReference;
import java.util.Vector;

import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.element.BonitaCriteriaElement;
import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridContentProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;

/**
 * @author Romain Bioteau
 *
 */
public class CriteriaContentProvider extends GridContentProvider {
	
    QuerySearchCondition searchCon;
    boolean isHaving = false;
    SQLDomainModel domainModel;
    protected boolean firstClause;

    public CriteriaContentProvider(SQLDomainModel domainModel, boolean isHavingClause) {
        super(domainModel.getAdapterFactory());
        this.domainModel = domainModel;
        this.isHaving = isHavingClause;
    }

    /**
     * Get all the predicates from the where clause and map each of
     * into a CriteriaElement
     */
    @Override
    public Object[] getElements(Object property) {

       	// This is done is super class AdapterFactoryContentProvider.getElements(), but
    	// since this method does not call the superclass method, it must be done here
    	// as well.  Without this call, notifications will not be enabled for the SQL object
    	adapterFactory.adapt(property, IStructuredItemContentProvider.class);
    	
        SQLQueryObject queryStmt = null;
        tableElements = new Vector();
        searchCon = null;
        if (property instanceof QueryStatement || property instanceof QuerySelect) {
            queryStmt = (SQLQueryObject) property;
            if ((!isHaving)) {
                searchCon = StatementHelper.getSearchCondition(queryStmt);
            }
            else {
                searchCon = StatementHelper.getHavingCondition(queryStmt);
            }
            firstClause = true;
            if (searchCon != null) {
                getAllPredicates(property, searchCon);
                if (searchCon == null) {
                    createNewCriteriaElement(property, null);
                }
            }
            else {
                createNewCriteriaElement(property, null);
            }
        }

        return tableElements.toArray();
    }

    //
    // Create a CriteriaElement for each predicate
    //
    private void getAllPredicates(java.lang.Object property, QuerySearchCondition cond) {
        if (cond instanceof Predicate) {
            createNewCriteriaElement(property, (Predicate) cond);
            firstClause = false;
        }
        else if (cond instanceof SearchConditionCombined) {
            final QuerySearchCondition left = ((SearchConditionCombined) cond).getLeftCondition();
            getAllPredicates(property, left);

            final QuerySearchCondition right = ((SearchConditionCombined) cond).getRightCondition();
            getAllPredicates(property, right);
        }
    }

    //
    // Make a CriteriaElement and add to the vector
    //
    void createNewCriteriaElement(java.lang.Object property, Predicate predicate) {
        final BonitaCriteriaElement criElement = new BonitaCriteriaElement(domainModel, property, searchCon, predicate, isHaving, firstClause);
        // creating the weak reference so that criteria element can find other criteria elements
        final WeakReference tableElementRef = new WeakReference(tableElements);
        criElement.setCriteriaElementVectorRef(tableElementRef);
        tableElements.add(criElement);
    }

}
