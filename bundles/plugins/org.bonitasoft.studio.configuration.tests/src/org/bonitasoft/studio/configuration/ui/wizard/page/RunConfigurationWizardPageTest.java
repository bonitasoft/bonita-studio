package org.bonitasoft.studio.configuration.ui.wizard.page;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class RunConfigurationWizardPageTest {

    @Test
    public void should_validate_empty_user_name() {
        IValidator<String> validator = new RunConfigurationWizardPage().createUserNameValidator();

        IStatus status = validator.validate(null);
        StatusAssert.assertThat(status).isWarning();
        assertThat(status.getMessage()).isEqualTo(Messages.usernameIsMissingForConfiguration);

        status = validator.validate("");
        StatusAssert.assertThat(status).isWarning();
        assertThat(status.getMessage()).isEqualTo(Messages.usernameIsMissingForConfiguration);
    }

    @Test
    public void should_validate_unknown_user() {
        RunConfigurationWizardPage page = new RunConfigurationWizardPage();
        page.users = Arrays.asList("walter.bates", "jan.fisher");
        IValidator<String> validator = page.createUserNameValidator();

        IStatus status = validator.validate("walter.bates");
        StatusAssert.assertThat(status).isOK();

        status = validator.validate("unknown");
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(String.format(Messages.unknownUser, "unknown"));
    }

}
