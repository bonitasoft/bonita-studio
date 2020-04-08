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
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;
import org.bonitasoft.studio.common.NamingUtils;

public class BusinessObjectBuilder {

    private BusinessDataModelFactory factory = BusinessDataModelFactory.eINSTANCE;
    private BusinessObject businessObject;

    public BusinessObjectBuilder() {
        businessObject = factory.createBusinessObject();
    }

    public BusinessObjectBuilder withQualifiedName(String qualifiedName) {
        businessObject.setQualifiedName(qualifiedName);
        businessObject.setSimpleName(NamingUtils.getSimpleName(qualifiedName));
        return this;
    }

    public BusinessObjectBuilder withdescription(String description) {
        businessObject.setDescription(description);
        return this;
    }

    public BusinessObjectBuilder withFields(Field... fields) {
        businessObject.getFields().addAll(Arrays.asList(fields));
        return this;
    }

    public BusinessObjectBuilder withUniqueConstraints(UniqueConstraint... uniqueConstraints) {
        businessObject.getUniqueConstraints().addAll(Arrays.asList(uniqueConstraints));
        return this;
    }

    public BusinessObjectBuilder withIndexes(Index... indexes) {
        businessObject.getIndexes().addAll(Arrays.asList(indexes));
        return this;
    }

    public BusinessObjectBuilder withQueries(Query... queries) {
        businessObject.getQueries().addAll(Arrays.asList(queries));
        return this;
    }

    public BusinessObjectBuilder withDefaultQueries(Query... queries) {
        businessObject.getDefaultQueries().addAll(Arrays.asList(queries));
        return this;
    }

    public BusinessObject create() {
        return businessObject;
    }

}
