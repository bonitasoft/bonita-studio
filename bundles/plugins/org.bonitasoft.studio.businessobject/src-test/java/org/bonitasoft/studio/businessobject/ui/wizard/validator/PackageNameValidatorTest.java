package org.bonitasoft.studio.businessobject.ui.wizard.validator;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class PackageNameValidatorTest {

    @Test
    public void should_validatePackage_returns_an_error_status_for_package_with_reserved_prefix() throws Exception {
        PackageNameValidator validator = new PackageNameValidator();

        IStatus status = validator.validateReservedPackages("com.bonitasoft");
        StatusAssert.assertThat(status).isError();

        status = validator.validateReservedPackages("com.bonitasoft.model");
        StatusAssert.assertThat(status).isError();

        status = validator.validateReservedPackages("org.bonitasoft");
        StatusAssert.assertThat(status).isError();

        status = validator.validateReservedPackages("org.bonitasoft.model");
        StatusAssert.assertThat(status).isError();
    }

}
