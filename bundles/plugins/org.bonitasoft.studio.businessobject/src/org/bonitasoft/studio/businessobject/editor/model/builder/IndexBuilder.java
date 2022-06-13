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
import org.bonitasoft.studio.businessobject.editor.model.Index;

public class IndexBuilder {

    private BusinessDataModelFactory factory = BusinessDataModelFactory.eINSTANCE;
    public Index index;

    public IndexBuilder() {
        index = factory.createIndex();
    }

    public IndexBuilder withName(String name) {
        index.setName(name);
        return this;
    }

    public IndexBuilder withDescription(String description) {
        index.setDescription(description);
        return this;
    }

    public IndexBuilder withFieldNames(String... fieldNames) {
        index.getFieldNames().addAll(Arrays.asList(fieldNames));
        return this;
    }

    public Index create() {
        return index;
    }

}
