package org.bonitasoft.studio.identity.organization.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Objects;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.junit.Rule;
import org.junit.Test;

public class UserPasswordValidatorTest {

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    private UserPasswordValidator validator = new UserPasswordValidator();

    @Test
    public void should_validate_valid_password() {
        StatusAssert.assertThat(validator.validate("bpm")).isOK();
    }

    @Test
    public void should_throw_error_when_user_password_is_empty() {
        IStatus status = validator.validate(null);
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status))
                .isEqualTo(Messages.bind(org.bonitasoft.studio.common.Messages.emptyField, Messages.password));

        status = validator.validate("");
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status))
                .isEqualTo(Messages.bind(org.bonitasoft.studio.common.Messages.emptyField, Messages.password));
    }

    @Test
    public void should_throw_error_when_user_name_is_too_long() {
        IStatus status = validator.validate("................................................................."
                + "......................................................................"
                + "......................................................................."
                + "........................................................................"
                + ".........................................................................");
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status))
                .isEqualTo(Messages.bind(org.bonitasoft.studio.common.Messages.fieldIsTooLong, Messages.password,
                        UserNameValidator.USERNAME_MAX_LENGTH));
    }

    private String getErrorMessage(IStatus status) {
        if (!(status instanceof MultiStatus)) {
            return status.getMessage();
        }
        return Arrays.asList(((MultiStatus) status).getChildren()).stream()
                .filter(s -> Objects.equals(s.getSeverity(), IStatus.ERROR))
                .findFirst()
                .map(IStatus::getMessage).orElse("No error");
    }

}
