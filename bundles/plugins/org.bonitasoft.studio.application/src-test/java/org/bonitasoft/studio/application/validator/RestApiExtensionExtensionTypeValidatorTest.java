package org.bonitasoft.studio.application.validator;

import java.io.File;

import org.bonitasoft.studio.application.handler.ImportExtensionHandlerTest;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.junit.Test;

public class RestApiExtensionExtensionTypeValidatorTest {

    @Test
    public void should_validate_rest_api_type() {
        RestApiExtensionExtensionTypeValidator validator = new RestApiExtensionExtensionTypeValidator();

        File restAPIJarFile = new File(
                ImportExtensionHandlerTest.class.getResource("/my-rest-api-0.0.1-SNAPSHOT.jar").getFile());
        File connectorZipFile = new File(
                ImportExtensionHandlerTest.class.getResource("/myFilter-1.0-SNAPSHOT.zip").getFile());
        File restAPIZipFile = new File(
                ImportExtensionHandlerTest.class.getResource("/my-rest-api-0.0.1-SNAPSHOT.zip").getFile());
        File themeZipFile = new File(
                ImportExtensionHandlerTest.class.getResource("/my-theme-0.0.1-SNAPSHOT.zip").getFile());

        StatusAssert.assertThat(validator.validate(restAPIJarFile)).isError();
        StatusAssert.assertThat(validator.validate(connectorZipFile)).isError();
        StatusAssert.assertThat(validator.validate(themeZipFile)).isError();
        StatusAssert.assertThat(validator.validate(restAPIZipFile)).isOK();
    }

}
