package org.bonitasoft.studio.application.validator;

import java.io.File;
import java.io.IOException;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.Test;

class ThemeExtensionTypeValidatorTest {

    @Test
    void should_validate_theme_type() throws IOException {
        ThemeExtensionTypeValidator validator = new ThemeExtensionTypeValidator();

        File restAPIJarFile = new File(
                FileLocator.toFileURL(ThemeExtensionTypeValidatorTest.class.getResource("my-rest-api-0.0.1-SNAPSHOT.jar")).getFile());
        File restAPIZipFile = new File(
                FileLocator.toFileURL(ThemeExtensionTypeValidatorTest.class.getResource("my-rest-api-0.0.1-SNAPSHOT.zip")).getFile());
        File themeZipFile = new File(
                FileLocator.toFileURL(ThemeExtensionTypeValidatorTest.class.getResource("my-theme-0.0.1-SNAPSHOT.zip")).getFile());

        StatusAssert.assertThat(validator.validate(restAPIJarFile)).isError();
        StatusAssert.assertThat(validator.validate(restAPIZipFile)).isError();
        StatusAssert.assertThat(validator.validate(themeZipFile)).isOK();
    }

}
