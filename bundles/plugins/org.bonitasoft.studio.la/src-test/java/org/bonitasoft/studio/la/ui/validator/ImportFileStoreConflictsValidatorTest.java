package org.bonitasoft.studio.la.ui.validator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class ImportFileStoreConflictsValidatorTest {

    @Test
    public void should_return_a_warning_status() throws Exception {
        final ImportFileStoreConflictsValidator validator = new ImportFileStoreConflictsValidator(
                appRepository());

        final IStatus status = validator.validate("myApp.xml");

        StatusAssert.assertThat(status).hasSeverity(IStatus.WARNING);
    }

    @Test
    public void should_return_a_valid_status() throws Exception {
        final ImportFileStoreConflictsValidator validator = new ImportFileStoreConflictsValidator(
                appRepository());

        final IStatus status = validator.validate("myApp2.xml");

        StatusAssert.assertThat(status).isOK();
    }

    private AbstractRepositoryStore<? extends IRepositoryFileStore> appRepository() {
        final AbstractRepositoryStore<ApplicationFileStore> repo = mock(AbstractRepositoryStore.class);
        when(repo.getChild("myApp.xml")).thenReturn(mock(ApplicationFileStore.class));
        return repo;
    }
}
