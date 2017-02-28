/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.ui.wizard.editingsupport;

import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aCompositionField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aRelationField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aSimpleField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aStringField;
import static org.bonitasoft.studio.model.businessObject.FieldBuilder.aTextField;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.function.Predicate;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.junit.Test;

public class IndexableFieldFilterTest {

    @Test
    public void should_filter_multiple_attributes() throws Exception {
        final BusinessObject businessObject = aBusinessObjectWithFields();

        final IndexableFieldFilter filter = new IndexableFieldFilter();
        final List<Field> selectedFields = filter.selectIndexableFields(businessObject);

        assertTrue(selectedFields.stream().noneMatch(f -> f.isCollection()));
    }

    @Test
    public void should_filter_text_attributes() throws Exception {
        final BusinessObject businessObject = aBusinessObjectWithFields();

        final IndexableFieldFilter filter = new IndexableFieldFilter();
        final List<Field> selectedFields = filter.selectIndexableFields(businessObject);

        assertTrue(selectedFields.stream()
                .filter(isSimpleField())
                .map(SimpleField.class::cast)
                .noneMatch(isTextField()));
    }

    @Test
    public void should_filter_relationFields_with_null_reference() throws Exception {
        final BusinessObject businessObject = aBusinessObjectWithFields();

        final IndexableFieldFilter filter = new IndexableFieldFilter();
        final List<Field> selectedFields = filter.selectIndexableFields(businessObject);

        assertTrue(selectedFields.stream()
                .filter(isRelationFieldField())
                .map(RelationField.class::cast)
                .noneMatch(hasNullReference()));
    }

    private Predicate<Field> isRelationFieldField() {
        return field -> RelationField.class.isInstance(field);
    }

    private Predicate<RelationField> hasNullReference() {
        return field -> field.getReference() == null;
    }

    private Predicate<Field> isSimpleField() {
        return field -> SimpleField.class.isInstance(field);
    }

    private Predicate<SimpleField> isTextField() {
        return field -> FieldType.TEXT == field.getType();
    }

    protected BusinessObject aBusinessObjectWithFields() {
        return aBO("MyObject")
                .withField(aSimpleField().multiple().build())
                .withField(aTextField("textField").build())
                .withField(aStringField(("stringField")).build())
                .withField(aRelationField().build())
                .withField(aCompositionField("fieldWithRef", aBO("reference").build()))
                .build();
    }

}
