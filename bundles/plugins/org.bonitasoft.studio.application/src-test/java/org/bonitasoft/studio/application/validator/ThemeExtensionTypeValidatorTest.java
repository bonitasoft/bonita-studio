package org.bonitasoft.studio.application.validator;

import java.io.File;

import org.bonitasoft.studio.application.handler.ImportExtensionHandlerTest;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.junit.Test;

public class ThemeExtensionTypeValidatorTest {

    @Test
    public void should_validate_theme_type() {
        ThemeExtensionTypeValidator validator = new ThemeExtensionTypeValidator();

        File restAPIJarFile = new File(
                ImportExtensionHandlerTest.class.getResource("/my-rest-api-0.0.1-SNAPSHOT.jar").getFile());
        File restAPIZipFile = new File(
                ImportExtensionHandlerTest.class.getResource("/my-rest-api-0.0.1-SNAPSHOT.zip").getFile());
        File themeZipFile = new File(
                ImportExtensionHandlerTest.class.getResource("/my-theme-0.0.1-SNAPSHOT.zip").getFile());

        StatusAssert.assertThat(validator.validate(restAPIJarFile)).isError();
        StatusAssert.assertThat(validator.validate(restAPIZipFile)).isError();
        StatusAssert.assertThat(validator.validate(themeZipFile)).isOK();
    }

}
