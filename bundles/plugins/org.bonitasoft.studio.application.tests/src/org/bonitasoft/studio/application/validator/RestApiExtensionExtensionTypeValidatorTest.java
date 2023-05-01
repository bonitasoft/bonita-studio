package org.bonitasoft.studio.application.validator;

import java.io.File;
import java.io.IOException;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.Test;

class RestApiExtensionExtensionTypeValidatorTest {

    @Test
    void should_validate_rest_api_type() throws IOException {
        RestApiExtensionExtensionTypeValidator validator = new RestApiExtensionExtensionTypeValidator();

        File restAPIJarFile = new File(
                FileLocator.toFileURL(
                        RestApiExtensionExtensionTypeValidatorTest.class.getResource("my-rest-api-0.0.1-SNAPSHOT.jar"))
                        .getFile());
        File connectorZipFile = new File(
                FileLocator.toFileURL(
                        RestApiExtensionExtensionTypeValidatorTest.class.getResource("myFilter-1.0-SNAPSHOT.zip"))
                        .getFile());
        File restAPIZipFile = new File(
                FileLocator.toFileURL(
                        RestApiExtensionExtensionTypeValidatorTest.class.getResource("my-rest-api-0.0.1-SNAPSHOT.zip"))
                        .getFile());
        File themeZipFile = new File(
                FileLocator.toFileURL(RestApiExtensionExtensionTypeValidatorTest.class.getResource("my-theme-0.0.1-SNAPSHOT.zip"))
                        .getFile());

        StatusAssert.assertThat(validator.validate(restAPIJarFile)).isError();
        StatusAssert.assertThat(validator.validate(connectorZipFile)).isError();
        StatusAssert.assertThat(validator.validate(themeZipFile)).isError();
        StatusAssert.assertThat(validator.validate(restAPIZipFile)).isOK();
    }

}
