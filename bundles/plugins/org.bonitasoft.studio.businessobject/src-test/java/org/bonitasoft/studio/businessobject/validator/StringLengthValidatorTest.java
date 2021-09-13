package org.bonitasoft.studio.businessobject.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.editor.model.builder.SimpleFieldBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.validator.StringLengthValidator;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;

public class StringLengthValidatorTest {

    IObservableValue<Field> selectedFieldObservable;

    @Before
    public void init() {
        SimpleField field = new SimpleFieldBuilder().withType(FieldType.STRING).create();
        selectedFieldObservable = mock(IObservableValue.class);
        when(selectedFieldObservable.getValue()).thenReturn(field);
    }

    @Test
    public void should_validate_length_is_not_null_or_empty() {
        StringLengthValidator validator = new StringLengthValidator(selectedFieldObservable);

        IStatus status = validator.validate(null);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.lengthCannotBeEmpty);

        status = validator.validate("");
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.lengthCannotBeEmpty);
    }

    @Test
    public void should_validate_length_is_an_positive_integer() {
        StringLengthValidator validator = new StringLengthValidator(selectedFieldObservable);

        IStatus status = validator.validate("Not an integer");
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.lengthIsNotANumber);

        status = validator.validate("0");
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.lengthIsNotAPositiveNumber);
    }

    @Test
    public void should_perform_validation_only_if_selected_field_has_type_string() {
        SimpleField field = new SimpleFieldBuilder().withType(FieldType.BOOLEAN).create();
        when(selectedFieldObservable.getValue()).thenReturn(field);
        StringLengthValidator validator = new StringLengthValidator(selectedFieldObservable);

        IStatus status = validator.validate("invalid length");
        StatusAssert.assertThat(status).isOK();
    }

}
