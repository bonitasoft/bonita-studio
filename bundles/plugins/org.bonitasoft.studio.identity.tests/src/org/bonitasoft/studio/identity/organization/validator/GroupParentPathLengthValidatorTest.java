package org.bonitasoft.studio.identity.organization.validator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.junit.Test;

public class GroupParentPathLengthValidatorTest {

    @Test
    public void should_throw_error_when_parent_path_is_too_long() {
        Group group = mock(Group.class);
        when(group.getParentPath()).thenReturn(
                "Path to long: ................................................................................."
                        + "......................................................................................"
                        + ".......................................................................................");

        GroupParentPathLengthValidator validator = new GroupParentPathLengthValidator();
        StatusAssert.assertThat(validator.validate(group)).isError();
    }

    @Test
    public void should_accept_correct_length_parent_path() {
        Group group = mock(Group.class);
        when(group.getParentPath()).thenReturn("acme/sales");

        GroupParentPathLengthValidator validator = new GroupParentPathLengthValidator();
        StatusAssert.assertThat(validator.validate(group)).isOK();
    }

}
