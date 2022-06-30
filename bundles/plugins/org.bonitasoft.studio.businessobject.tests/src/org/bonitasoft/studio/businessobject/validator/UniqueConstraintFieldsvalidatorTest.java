/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.RelationFieldBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.SimpleFieldBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.UniqueConstraintBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;

public class UniqueConstraintFieldsvalidatorTest {

    static final String FIELD_WITH_TEXT_TYPE = "fieldWithTextType";
    static final String FIELD1 = "field1";
    static final String FIELD_WITH_MULTIPLE_TYPE = "fieldWithMultipleType";
    static final String FIELD_WITH_NULL_REF = "fieldWithNullRef";

    UniqueConstraintFieldsValidator validator;
    BusinessObject bo;

    @Before
    public void init() {
        validator = new UniqueConstraintFieldsValidator();
        bo = createBusinessObject("bo");
        bo.getFields().add(createField(FIELD1));
    }

    private BusinessObject createBusinessObject(String name) {
        return new BusinessObjectBuilder()
                .withQualifiedName(String.format("com.%s", name))
                .create();
    }

    public Field createField(String name) {
        return new SimpleFieldBuilder()
                .withName(name)
                .create();
    }

    @Test
    public void should_validate_field_list_is_not_empty() {
        UniqueConstraint constraint = new UniqueConstraintBuilder()
                .withName("constraint")
                .create();
        IStatus status = validator.validate(constraint);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()))
                .flatExtracting(IStatus::getMessage)
                .contains(String.format(Messages.constraintFieldEmptiness, constraint.getName()));
    }

    @Test
    public void should_validate_unknown_fields() {
        UniqueConstraint constraint = new UniqueConstraintBuilder()
                .withName("constraint")
                .withFieldNames(FIELD1, "unknownField")
                .create();
        bo.getUniqueConstraints().add(constraint);
        IStatus status = validator.validate(constraint);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()))
                .flatExtracting(IStatus::getMessage)
                .contains(String.format(Messages.constraintReferencesUnknownAttributes, constraint.getName(),
                        Arrays.asList("unknownField").toString()));
    }

    @Test
    public void should_validate_duplicate() {
        UniqueConstraint constraint = new UniqueConstraintBuilder()
                .withName("constraint")
                .withFieldNames(FIELD1, FIELD1)
                .create();
        bo.getUniqueConstraints().add(constraint);
        IStatus status = validator.validate(constraint);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()))
                .flatExtracting(IStatus::getMessage)
                .contains(String.format(Messages.fieldsDuplicated, constraint.getName()));
    }

    @Test
    public void should_validate_invalid_types() {
        bo.getFields().add(new SimpleFieldBuilder()
                .withName(FIELD_WITH_TEXT_TYPE)
                .withCollection(false)
                .withType(FieldType.TEXT)
                .create());
        bo.getFields().add(new SimpleFieldBuilder()
                .withName(FIELD_WITH_MULTIPLE_TYPE)
                .withCollection(true)
                .withType(FieldType.INTEGER)
                .create());
        bo.getFields().add(new RelationFieldBuilder()
                .withName(FIELD_WITH_NULL_REF)
                .withReference(null)
                .create());
        UniqueConstraint constraint = new UniqueConstraintBuilder()
                .withName("constraint")
                .withFieldNames(FIELD_WITH_TEXT_TYPE, FIELD_WITH_MULTIPLE_TYPE, FIELD_WITH_NULL_REF)
                .create();
        bo.getUniqueConstraints().add(constraint);
        IStatus status = validator.validate(constraint);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()))
                .flatExtracting(IStatus::getMessage)
                .contains(String.format("%s\n%s\n%s",
                        String.format(Messages.cantUseTextTypeInConstraint, FIELD_WITH_TEXT_TYPE),
                        String.format(Messages.cantUseMultipleTypeInConstraint, FIELD_WITH_MULTIPLE_TYPE),
                        String.format(Messages.cantHaveNullReferenceInConstraint, FIELD_WITH_NULL_REF)));
    }

}
