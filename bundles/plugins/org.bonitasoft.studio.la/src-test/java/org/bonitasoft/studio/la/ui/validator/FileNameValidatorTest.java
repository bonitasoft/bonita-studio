package org.bonitasoft.studio.la.ui.validator;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.junit.Test;

public class FileNameValidatorTest {

    @Test
    public void should_validate_name_uniqueness() {

        final ApplicationFileStore fileStore1 = mock(ApplicationFileStore.class);
        when(fileStore1.getName()).thenReturn("fileName1.xml");

        final ApplicationFileStore fileStore2 = mock(ApplicationFileStore.class);
        when(fileStore2.getName()).thenReturn("fileName2.xml");

        final List<ApplicationFileStore> children = new ArrayList<>();
        children.add(fileStore1);
        children.add(fileStore2);

        final ApplicationRepositoryStore applicationRepositoryStore = mock(ApplicationRepositoryStore.class);
        when(applicationRepositoryStore.getChildren()).thenReturn(children);

        final FileNameValidator validator = new FileNameValidator(
                applicationRepositoryStore);

        assertThat(validator.validate("fileName1")).isNotOK();
        assertThat(validator.validate("fileName2")).isNotOK();
        assertThat(validator.validate("fileName3")).isOK();
    }

    @Test
    public void should_reject_invalid_char() {

        final ApplicationRepositoryStore applicationRepositoryStore = mock(ApplicationRepositoryStore.class);
        when(applicationRepositoryStore.getChildren()).thenReturn(new ArrayList<ApplicationFileStore>());

        final FileNameValidator validator = new FileNameValidator(
                applicationRepositoryStore);

        assertThat(validator.validate("fileName1/")).isNotOK();
        assertThat(validator.validate("fileName2:")).isNotOK();
        assertThat(validator.validate("fileName3")).isOK();
    }

    @Test
    public void should_fail_if_no_filename() {
        final ApplicationRepositoryStore applicationRepositoryStore = mock(ApplicationRepositoryStore.class);
        when(applicationRepositoryStore.getChildren()).thenReturn(new ArrayList<ApplicationFileStore>());

        final FileNameValidator validator = new FileNameValidator(
                applicationRepositoryStore);

        assertThat(validator.validate(null)).isNotOK();
    }
}
