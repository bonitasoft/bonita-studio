package org.bonitasoft.studio.application.validator;

import java.io.File;
import java.io.IOException;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.Test;

class ConnectorExtensionTypeValidatorTest {

    @Test
    void should_validate_connector_type() throws IOException {
        ConnectorExtensionTypeValidator validator = new ConnectorExtensionTypeValidator();
        
        File restAPIJarFile = new File(
                FileLocator.toFileURL(
                        ConnectorExtensionTypeValidatorTest.class.getResource("my-rest-api-0.0.1-SNAPSHOT.jar"))
                        .getFile());
        File connectorZipFile = new File(
                FileLocator
                        .toFileURL(ConnectorExtensionTypeValidatorTest.class.getResource("myFilter-1.0-SNAPSHOT.zip"))
                        .getFile());
        File connectorJarFile = new File(
                FileLocator
                        .toFileURL(ConnectorExtensionTypeValidatorTest.class.getResource("myFilter-1.0-SNAPSHOT.jar"))
                        .getFile());

        StatusAssert.assertThat(validator.validate(restAPIJarFile)).isError();
        StatusAssert.assertThat(validator.validate(connectorZipFile)).isError();
        StatusAssert.assertThat(validator.validate(connectorJarFile)).isOK();
    }

}
