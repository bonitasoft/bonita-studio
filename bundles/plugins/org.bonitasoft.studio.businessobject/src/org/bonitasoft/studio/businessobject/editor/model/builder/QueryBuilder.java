/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.editor.model.builder;

import java.util.Arrays;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelFactory;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.QueryParameter;

public class QueryBuilder {

    private BusinessDataModelFactory factory = BusinessDataModelFactory.eINSTANCE;
    private Query query;

    public QueryBuilder() {
        query = factory.createQuery();
    }

    public QueryBuilder withName(String name) {
        query.setName(name);
        return this;
    }

    public QueryBuilder withDescription(String description) {
        query.setDescription(description);
        return this;
    }

    public QueryBuilder withContent(String content) {
        query.setContent(content);
        return this;
    }

    public QueryBuilder withReturnType(String returnType) {
        query.setReturnType(returnType);
        return this;
    }

    public QueryBuilder withQueryParameters(QueryParameter... queryParameters) {
        query.getQueryParameters().addAll(Arrays.asList(queryParameters));
        return this;
    }

    public Query create() {
        return query;
    }

}
