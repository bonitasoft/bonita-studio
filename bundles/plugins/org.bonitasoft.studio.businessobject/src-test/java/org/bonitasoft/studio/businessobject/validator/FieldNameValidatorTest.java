package org.bonitasoft.studio.businessobject.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Arrays;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.SimpleFieldBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.validator.FieldNameValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;

public class FieldNameValidatorTest {

    FieldNameValidator validator;
    Field field;

    @Before
    public void init() {
        BusinessObject businessObject = createBusinessObject("businessObject1");
        validator = spy(new FieldNameValidator());
        field = createField("field1");
        Field field2 = createField("field2");
        businessObject.getFields().addAll(Arrays.asList(field, field2));
        doReturn(ValidationStatus.ok()).when(validator).validateJavaConvention(anyString());
    }

    private Field createField(String name) {
        return new SimpleFieldBuilder()
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
        field.setName(emptyName);
        IStatus status = validator.validate(field);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.attributeNameRequired);

        String nullName = null;
        field.setName(nullName);
        status = validator.validate(field);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.attributeNameRequired);
    }

    @Test
    public void should_validate_name_length() {
        String nameToLong = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        field.setName(nameToLong);
        IStatus status = validator.validate(field);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(org.bonitasoft.studio.common.Messages.bind(org.bonitasoft.studio.common.Messages.fieldIsTooLong,
                        nameToLong, FieldNameValidator.MAX_COLUMN_NAME_LENGTH));
    }

    @Test
    public void should_validate_sql_keyword() {
        String invalidName = "SELECT";
        field.setName(invalidName);
        IStatus status = validator.validate(field);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.bind(Messages.reservedKeyWord, invalidName));
    }

    @Test
    public void should_validate_sql_invalid_identifier() {
        String invalidName = "*";
        field.setName(invalidName);
        IStatus status = validator.validate(field);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.bind(Messages.invalidSQLIdentifier, invalidName));
    }

    @Test
    public void should_validate_psersitenceId_name() {
        String invalidName = org.bonitasoft.engine.bdm.model.field.Field.PERSISTENCE_ID;
        field.setName(invalidName);
        IStatus status = validator.validate(field);
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(
                        Messages.bind(Messages.reservedKeyWord, org.bonitasoft.engine.bdm.model.field.Field.PERSISTENCE_ID));
    }

    @Test
    public void should_validate_psersitencevERSION_name() {
        String invalidName = org.bonitasoft.engine.bdm.model.field.Field.PERSISTENCE_VERSION.toLowerCase();
        field.setName(invalidName);
        IStatus status = validator.validate(field);
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(
                        Messages.bind(Messages.reservedKeyWord,
                                org.bonitasoft.engine.bdm.model.field.Field.PERSISTENCE_VERSION));
    }

    @Test
    public void should_validate_uniqueness() {
        String duplicatedName = "field2";
        field.setName(duplicatedName);
        IStatus status = validator.validate(field);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.fieldNameAlreadyExists);
    }

    @Test
    public void should_validate_first_character_is_lowerCase() {
        String invalidFirstCharName = "StartWithUpperCase";
        field.setName(invalidFirstCharName);
        IStatus status = validator.validate(field);
        StatusAssert.assertThat(status).isWarning();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.fieldNameShouldStartsWithLowercase);
    }

}
