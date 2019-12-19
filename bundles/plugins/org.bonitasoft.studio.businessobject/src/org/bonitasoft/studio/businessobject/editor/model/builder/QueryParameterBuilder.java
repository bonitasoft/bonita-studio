/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.editor.model.builder;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelFactory;
import org.bonitasoft.studio.businessobject.editor.model.QueryParameter;

public class QueryParameterBuilder {

    private BusinessDataModelFactory factory = BusinessDataModelFactory.eINSTANCE;
    private QueryParameter queryParameter;

    public QueryParameterBuilder() {
        queryParameter = factory.createQueryParameter();
    }

    public QueryParameterBuilder withName(String name) {
        queryParameter.setName(name);
        return this;
    }

    public QueryParameterBuilder withClassName(String className) {
        queryParameter.setClassName(className);
        return this;
    }

    public QueryParameter create() {
        return queryParameter;
    }

}
