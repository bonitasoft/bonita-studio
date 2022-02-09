package org.bonitasoft.studio.businessobject.validator;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.builder.PackageBuilder;
import org.bonitasoft.studio.businessobject.validator.PackageNameValidator;
import org.eclipse.core.runtime.Status;
import org.junit.Before;
import org.junit.Test;

public class PackageNameValidatorTest {

    PackageNameValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = spy(new PackageNameValidator());
        doReturn(Status.OK_STATUS).when(validator).javaPackageValidation(any(String.class));
    }

    @Test
    public void should_validatePackage_returns_an_error_status_for_package_with_reserved_prefix() throws Exception {
        assertThat(validator.validate(createPackage("org.bonitasoft"))).isError();
        assertThat(validator.validate(createPackage("org.bonitasoft.model"))).isError();
        assertThat(validator.validate(createPackage("com.bonitasoft"))).isError();
        assertThat(validator.validate(createPackage("com.bonitasoft.model"))).isError();
    }

    @Test
    public void should_validatePackage_returns_an_error_status_for_null_or_empty_package() throws Exception {
        assertThat(validator.validate(createPackage(""))).isError();
        assertThat(validator.validate(createPackage(null))).isError();
    }

    private Package createPackage(String name) {
        return new PackageBuilder().withName(name).create();
    }

}
