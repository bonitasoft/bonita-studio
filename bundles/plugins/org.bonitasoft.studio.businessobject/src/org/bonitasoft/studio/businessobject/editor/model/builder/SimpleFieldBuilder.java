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
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;

public class SimpleFieldBuilder {

    private BusinessDataModelFactory factory = BusinessDataModelFactory.eINSTANCE;
    private SimpleField simpleField;

    public SimpleFieldBuilder() {
        simpleField = factory.createSimpleField();
    }

    public SimpleFieldBuilder withName(String name) {
        simpleField.setName(name);
        return this;
    }

    public SimpleFieldBuilder withDescription(String description) {
        simpleField.setDescription(description);
        return this;
    }

    public SimpleFieldBuilder withNullable(boolean nullable) {
        simpleField.setNullable(nullable);
        return this;
    }

    public SimpleFieldBuilder withCollection(boolean collection) {
        simpleField.setCollection(collection);
        return this;
    }

    public SimpleFieldBuilder withType(FieldType type) {
        simpleField.setType(type);
        return this;
    }

    public SimpleFieldBuilder withLength(int length) {
        simpleField.setLength(length);
        return this;
    }

    public SimpleField create() {
        return simpleField;
    }

}
