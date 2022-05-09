package org.bonitasoft.studio.la.application.ui.validator;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.la.application.ui.editor.ApplicationFormPage;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class ProfileValidatorTest {

    @Test
    public void should_return_warning_for_empty_profile() {
        ProfileValidator validator = new ProfileValidator();
        IStatus status = validator.validate(null);
        StatusAssert.assertThat(status).isWarning();
        status = validator.validate("");
        StatusAssert.assertThat(status).isWarning();
    }

    @Test
    public void should_return_error_for_unknow_profile() {
        ProfileValidator validator = new ProfileValidator();
        IStatus status = validator.validate("unknown");
        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_validate_default_profiles() {
        ProfileValidator validator = new ProfileValidator();
        IStatus status = validator.validate(ApplicationFormPage.DEFAULT_USER_ID);
        StatusAssert.assertThat(status).isOK();
        status = validator.validate(ApplicationFormPage.DEFAULT_ADMINISTRATOR_ID);
        StatusAssert.assertThat(status).isOK();
    }

}
