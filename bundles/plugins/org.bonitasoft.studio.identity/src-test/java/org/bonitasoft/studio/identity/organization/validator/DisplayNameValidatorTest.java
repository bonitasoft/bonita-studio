package org.bonitasoft.studio.identity.organization.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.junit.Test;

public class DisplayNameValidatorTest {

    private DisplayNameValidator validator = new DisplayNameValidator();

    @Test
    public void should_throw_error_when_display_name_is_too_long() {
        String name = "256 char: ...................."
                + "........................................"
                + ".................................................."
                + "............................................................"
                + "......................................................................"
                + "123456";
        assertThat(name.length()).isEqualTo(256);
        StatusAssert.assertThat(validator.validate(name)).isError();
    }

    @Test
    public void should_validate_display_name_not_too_long() {
        String name = "255 char: ...................."
                + "........................................"
                + ".................................................."
                + "............................................................"
                + "......................................................................"
                + "12345";
        assertThat(name.length()).isEqualTo(255);
        StatusAssert.assertThat(validator.validate(name)).isOK();
    }

}
