package org.bonitasoft.studio.document.ui.validator;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.Resource;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class AdditionalResourceBarPathValidatorTest {

    private static String BAR_PATH = "toto.txt";

    AdditionalResourceBarPathValidator validator = new AdditionalResourceBarPathValidator();

    @Test
    public void should_return_error_for_empty_bar_path() {
        Resource resource = ConfigurationFactory.eINSTANCE.createResource();
        IStatus status = validator.validate(resource);
        StatusAssert.assertThat(status).isError();
        resource.setBarPath("");
        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_validate_unicity() {
        Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        validator.setConfiguration(configuration);

        Resource resource1 = ConfigurationFactory.eINSTANCE.createResource();
        resource1.setBarPath(BAR_PATH);
        configuration.getAdditionalResources().add(resource1);

        IStatus status = validator.validate(resource1);
        StatusAssert.assertThat(status).isOK();

        Resource resource2 = ConfigurationFactory.eINSTANCE.createResource();
        resource2.setBarPath(BAR_PATH);
        configuration.getAdditionalResources().add(resource2);

        status = validator.validate(resource1);
        StatusAssert.assertThat(status).isError();
        status = validator.validate(resource2);
        StatusAssert.assertThat(status).isError();
    }

}
