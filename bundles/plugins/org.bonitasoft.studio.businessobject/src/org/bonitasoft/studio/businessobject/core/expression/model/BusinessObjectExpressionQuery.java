/*******************************************************************************
 * Copyright (C) 2009, 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.core.expression.model;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;

/**
 * @author Romain Bioteau
 * 
 */
public class BusinessObjectExpressionQuery {

    private List<Expression> queryExpressions = new ArrayList<Expression>();

    private String qualifiedName;

    public BusinessObjectExpressionQuery(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public List<Expression> getQueryExpressions() {
        return queryExpressions;
    }

    public void setQueryExpressions(List<Expression> queryExpressions) {
        this.queryExpressions = queryExpressions;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

}
