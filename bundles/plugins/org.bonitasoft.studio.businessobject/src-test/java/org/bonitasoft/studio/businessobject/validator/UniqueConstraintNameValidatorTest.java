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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Arrays;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.UniqueConstraintBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;

public class UniqueConstraintNameValidatorTest {

    UniqueConstraintNameValidator validator;
    UniqueConstraint constraint;

    @Before
    public void init() {
        BusinessObject businessObject = createBusinessObject("businessObject1");
        validator = spy(new UniqueConstraintNameValidator());
        constraint = createConstraint("constraint1");
        UniqueConstraint field2 = createConstraint("constraint2");
        businessObject.getUniqueConstraints().addAll(Arrays.asList(constraint, field2));
        doReturn(ValidationStatus.ok()).when(validator).validateJavaConvention(anyString());
    }

    private UniqueConstraint createConstraint(String name) {
        return new UniqueConstraintBuilder()
                .withName(name)
                .create();
    }

    private BusinessObject createBusinessObject(String name) {
        return new BusinessObjectBuilder()
                .withQualifiedName(String.format("com.%s", name))
                .create();
    }

    @Test
    public void should_validate_empty_name() {
        String emptyName = "";
        constraint.setName(emptyName);
        IStatus status = validator.validate(constraint);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.constraintNameRequired);

        String nullName = null;
        constraint.setName(nullName);
        status = validator.validate(constraint);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.constraintNameRequired);
    }

    @Test
    public void should_validate_name_length() {
        String nameToLong = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        constraint.setName(nameToLong);
        IStatus status = validator.validate(constraint);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(org.bonitasoft.studio.common.Messages.bind(org.bonitasoft.studio.common.Messages.fieldIsTooLong,
                        nameToLong, FieldNameValidator.MAX_COLUMN_NAME_LENGTH));
    }

    @Test
    public void should_validate_sql_keyword() {
        String invalidName = "SELECT";
        constraint.setName(invalidName);
        IStatus status = validator.validate(constraint);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.bind(Messages.reservedKeyWord, invalidName));
    }

    @Test
    public void should_validate_sql_invalid_identifier() {
        String invalidName = "*";
        constraint.setName(invalidName);
        IStatus status = validator.validate(constraint);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.bind(Messages.invalidSQLIdentifier, invalidName));
    }

    @Test
    public void should_validate_uniqueness() {
        String duplicatedName = "constraint2";
        constraint.setName(duplicatedName);
        IStatus status = validator.validate(constraint);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.constraintNameAlreadyExists);
    }
}
