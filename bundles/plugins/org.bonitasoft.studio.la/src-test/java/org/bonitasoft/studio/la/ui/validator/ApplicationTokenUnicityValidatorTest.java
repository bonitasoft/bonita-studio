package org.bonitasoft.studio.la.ui.validator;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.junit.Test;

public class ApplicationTokenUnicityValidatorTest {

    @Test
    public void should_validate_uniqueness_without_current_token() throws Exception {

        RepositoryAccessor repositoryAccessor = initRepositoryAccessor();
        ApplicationTokenUnicityValidator validator = new ApplicationTokenUnicityValidator(repositoryAccessor);

        assertThat(validator.validate("token1")).isNotOK();
        assertThat(validator.validate("token2")).isNotOK();
        assertThat(validator.validate("token3")).isOK();

    }

    @Test
    public void should_validate_uniqueness_with_current_token() throws Exception {

        RepositoryAccessor repositoryAccessor = initRepositoryAccessor();
        ApplicationTokenUnicityValidator validator = new ApplicationTokenUnicityValidator(repositoryAccessor, "token2");

        assertThat(validator.validate("token1")).isNotOK();
        assertThat(validator.validate("token2")).isOK();
        assertThat(validator.validate("token3")).isOK();

    }

    private RepositoryAccessor initRepositoryAccessor() throws Exception {
        ApplicationNode applicationNode = mock(ApplicationNode.class);
        when(applicationNode.getToken()).thenReturn("token1");

        ApplicationNode applicationNode2 = mock(ApplicationNode.class);
        when(applicationNode2.getToken()).thenReturn("token2");

        List<ApplicationNode> applications = new ArrayList<>();
        applications.add(applicationNode);
        applications.add(applicationNode2);

        ApplicationNodeContainer applicationNodeContainer = mock(ApplicationNodeContainer.class);
        when(applicationNodeContainer.getApplications()).thenReturn(applications);

        ApplicationFileStore applicationFileStore = mock(ApplicationFileStore.class);
        when(applicationFileStore.getContent()).thenReturn(applicationNodeContainer);

        List<ApplicationFileStore> applicationFileStores = new ArrayList<>();
        applicationFileStores.add(applicationFileStore);

        ApplicationRepositoryStore applicationRepositoryStore = mock(ApplicationRepositoryStore.class);
        when(applicationRepositoryStore.getChildren()).thenReturn(applicationFileStores);

        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        when(repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class)).thenReturn(applicationRepositoryStore);

        return repositoryAccessor;
    }

}
