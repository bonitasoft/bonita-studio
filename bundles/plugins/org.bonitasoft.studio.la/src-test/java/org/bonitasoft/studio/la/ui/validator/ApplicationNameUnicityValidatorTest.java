package org.bonitasoft.studio.la.ui.validator;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.jface.databinding.validator.UniqueValidator;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.junit.Test;

public class ApplicationNameUnicityValidatorTest {

    @Test
    public void should_validate_uniqueness() {

        ApplicationFileStore fileStore1 = mock(ApplicationFileStore.class);
        when(fileStore1.getName()).thenReturn("fileName1.xml");

        ApplicationFileStore fileStore2 = mock(ApplicationFileStore.class);
        when(fileStore2.getName()).thenReturn("fileName2.xml");

        List<ApplicationFileStore> children = new ArrayList<ApplicationFileStore>();
        children.add(fileStore1);
        children.add(fileStore2);

        UniqueValidator validator = new ApplicationNameUnicityValidator().withApplicationDescriptors(children).create();

        assertThat(validator.validate("fileName1")).isNotOK();
        assertThat(validator.validate("fileName2")).isNotOK();
        assertThat(validator.validate("fileName3")).isOK();
    }
}
