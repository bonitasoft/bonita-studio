package org.bonitasoft.studio.la.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationPageNode;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class ApplicationHomepageValidatorTest {

    @Test
    public void should_return_error_for_application_without_homepage() {
        ApplicationNode applicationNode = new ApplicationNode();
        ApplicationHomepageValidator validator = new ApplicationHomepageValidator();
        IStatus status = validator.validate(applicationNode);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(String.format(Messages.applicationWithoutHomepage,
                applicationNode.getDisplayName()));
    }

    @Test
    public void should_return_error_for_application_with_unknown_homepage() {
        ApplicationNode applicationNode = new ApplicationNode();
        applicationNode.setHomePage("token");
        ApplicationHomepageValidator validator = new ApplicationHomepageValidator();
        IStatus status = validator.validate(applicationNode);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(String.format(Messages.applicationWithUnknownHomepage,
                applicationNode.getDisplayName()));
    }

    @Test
    public void should_return_ok_for_application_with_known_homepage() {
        ApplicationNode applicationNode = new ApplicationNode();
        applicationNode.setHomePage("token");
        ApplicationPageNode applicationPageNode = new ApplicationPageNode();
        applicationPageNode.setToken("token");
        applicationNode.getApplicationPages().add(applicationPageNode);
        ApplicationHomepageValidator validator = new ApplicationHomepageValidator();
        IStatus status = validator.validate(applicationNode);
        StatusAssert.assertThat(status).isOK();
    }

}
