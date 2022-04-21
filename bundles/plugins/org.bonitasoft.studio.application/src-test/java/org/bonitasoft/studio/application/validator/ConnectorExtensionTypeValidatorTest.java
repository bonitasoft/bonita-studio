package org.bonitasoft.studio.application.validator;

import java.io.File;

import org.bonitasoft.studio.application.handler.ImportExtensionHandlerTest;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.junit.Test;

public class ConnectorExtensionTypeValidatorTest {

    @Test
    public void should_validate_connector_type() {
        ConnectorExtensionTypeValidator validator = new ConnectorExtensionTypeValidator();

        File restAPIJarFile = new File(
                ImportExtensionHandlerTest.class.getResource("/my-rest-api-0.0.1-SNAPSHOT.jar").getFile());
        File connectorZipFile = new File(
                ImportExtensionHandlerTest.class.getResource("/myFilter-1.0-SNAPSHOT.zip").getFile());
        File connectorJarFile = new File(
                ImportExtensionHandlerTest.class.getResource("/myFilter-1.0-SNAPSHOT.jar").getFile());

        StatusAssert.assertThat(validator.validate(restAPIJarFile)).isError();
        StatusAssert.assertThat(validator.validate(connectorZipFile)).isError();
        StatusAssert.assertThat(validator.validate(connectorJarFile)).isOK();
    }

}
