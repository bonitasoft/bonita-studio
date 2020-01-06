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
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.FetchType;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;

public class RelationFieldBuilder {

    private BusinessDataModelFactory factory = BusinessDataModelFactory.eINSTANCE;
    private RelationField relationField;

    public RelationFieldBuilder() {
        relationField = factory.createRelationField();
    }

    public RelationFieldBuilder withName(String name) {
        relationField.setName(name);
        return this;
    }

    public RelationFieldBuilder withDescription(String description) {
        relationField.setDescription(description);
        return this;
    }

    public RelationFieldBuilder withNullable(boolean nullable) {
        relationField.setNullable(nullable);
        return this;
    }

    public RelationFieldBuilder withCollection(boolean collection) {
        relationField.setCollection(collection);
        return this;
    }

    public RelationFieldBuilder withReference(BusinessObject reference) {
        relationField.setReference(reference);
        return this;
    }

    public RelationFieldBuilder withType(RelationType type) {
        relationField.setType(type);
        return this;
    }

    public RelationFieldBuilder withFetchType(FetchType type) {
        relationField.setFetchType(type);
        return this;
    }

    public RelationField create() {
        return relationField;
    }
}
