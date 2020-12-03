package org.bonitasoft.studio.ui.validator;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.junit.Test;

public class FileNameValidatorTest {

    @Test
    public void should_validate_name_uniqueness() {

        final IRepositoryFileStore fileStore1 = mock(IRepositoryFileStore.class);
        when(fileStore1.getName()).thenReturn("fileName1.xml");

        final IRepositoryFileStore fileStore2 = mock(IRepositoryFileStore.class);
        when(fileStore2.getName()).thenReturn("fileName2.xml");

        final List<IRepositoryFileStore> children = new ArrayList<>();
        children.add(fileStore1);
        children.add(fileStore2);

        final IRepositoryStore repositoryStore = mock(IRepositoryStore.class);
        when(repositoryStore.getChildren()).thenReturn(children);

        final FileNameValidator validator = new FileNameValidator(repositoryStore, ExtensionSupported.XML);

        assertThat(validator.validate("fileName1")).isNotOK();
        assertThat(validator.validate("fileName1.xml")).isNotOK();
        assertThat(validator.validate("FiLeNaMe1")).isNotOK();
        assertThat(validator.validate("fileName2")).isNotOK();
        assertThat(validator.validate("fileName3")).isOK();
    }

    @Test
    public void should_reject_invalid_char() {

        final IRepositoryStore repositoryStore = mock(IRepositoryStore.class);
        when(repositoryStore.getChildren()).thenReturn(new ArrayList<>());

        final FileNameValidator validator = new FileNameValidator(repositoryStore, ExtensionSupported.XML);

        assertThat(validator.validate("fileName1/")).isNotOK();
        assertThat(validator.validate("fileName2:")).isNotOK();
        assertThat(validator.validate("fileName3")).isOK();
    }

    @Test
    public void should_fail_if_no_filename() {
        final IRepositoryStore repositoryStore = mock(IRepositoryStore.class);
        when(repositoryStore.getChildren()).thenReturn(new ArrayList<>());

        final FileNameValidator validator = new FileNameValidator(repositoryStore, ExtensionSupported.XML);

        assertThat(validator.validate(null)).isNotOK();
    }
}
