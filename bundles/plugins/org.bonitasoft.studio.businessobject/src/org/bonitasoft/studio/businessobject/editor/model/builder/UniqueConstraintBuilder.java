/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.editor.model.builder;

import java.util.Arrays;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelFactory;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;

public class UniqueConstraintBuilder {

    private BusinessDataModelFactory factory = BusinessDataModelFactory.eINSTANCE;
    private UniqueConstraint uniqueConstraint;

    public UniqueConstraintBuilder() {
        uniqueConstraint = factory.createUniqueConstraint();
    }

    public UniqueConstraintBuilder withName(String name) {
        uniqueConstraint.setName(name);
        return this;
    }

    public UniqueConstraintBuilder withFieldNames(String... fieldNames) {
        uniqueConstraint.getFieldNames().addAll(Arrays.asList(fieldNames));
        return this;
    }

    public UniqueConstraint create() {
        return uniqueConstraint;
    }

}
