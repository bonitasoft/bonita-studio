package org.bonitasoft.studio.common.model.validator;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;

import java.util.Collections;

import org.bonitasoft.studio.common.Messages;
import org.junit.Before;
import org.junit.Test;

public class ModelNamespaceValidatorTest {

    static final String NAMESPACE = "a/namespace/2.0";
    static final String LEGACY_NAMESPACE = "a/legacy/namespace";

    ModelNamespaceValidator validator;
    
    @Before
    public void setUp() throws Exception {
        validator = new ModelNamespaceValidator(NAMESPACE, "error", Collections.singleton(LEGACY_NAMESPACE));
    }

    @Test
    public void should_failed_when_not_a_valid_namespace() throws Exception {
        assertThat(validator.validate("a/random/namespace")).isError();
        assertThat(validator.validate("a/random/namespace/1.0")).isError();
    }

    @Test
    public void should_failed_when_namespace_version_is_greater_than_current() {
        assertThat(validator.validate("a/namespace/3.0")).isError();
    }

    @Test
    public void should_be_ok_when_namespace_version_is_same_than_current() {
        assertThat(validator.validate(NAMESPACE)).isOK();
    }

    @Test
    public void should_be_warning_when_namespace_is_a_legacy_namespace() {
        assertThat(validator.validate(LEGACY_NAMESPACE))
                .isWarning()
                .hasMessage(Messages.migrationWillBreakRetroCompatibility);
    }

    @Test
    public void should_be_warning_when_a_migration_is_required() {
        assertThat(validator.validate("a/namespace/1.0"))
                .isWarning()
                .hasMessage(Messages.migrationWillBreakRetroCompatibility);
    }
    

}

