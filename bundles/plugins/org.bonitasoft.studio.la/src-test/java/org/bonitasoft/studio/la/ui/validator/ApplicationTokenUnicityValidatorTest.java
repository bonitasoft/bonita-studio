package org.bonitasoft.studio.la.ui.validator;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
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
        final RepositoryAccessor repositoryAccessor = initRepositoryAccessor();
        final ApplicationTokenUnicityValidator validator = new ApplicationTokenUnicityValidator(repositoryAccessor);

        assertThat(validator.validate("token1")).isNotOK();
        assertThat(validator.validate("token2")).isNotOK();
        assertThat(validator.validate("token3")).isOK();
    }

    @Test
    public void should_validate_uniqueness_with_current_token() throws Exception {
        final RepositoryAccessor repositoryAccessor = initRepositoryAccessor();
        final ApplicationTokenUnicityValidator validator = new ApplicationTokenUnicityValidator(repositoryAccessor,
                "token2");

        assertThat(validator.validate("token1")).isNotOK();
        assertThat(validator.validate("token2")).isOK();
        assertThat(validator.validate("token3")).isOK();
    }

    @Test
    public void should_validation_fails_with_current_token() throws Exception {
        final RepositoryAccessor repositoryAccessor = initRepositoryAccessor();
        final ApplicationTokenUnicityValidator validator = new ApplicationTokenUnicityValidator(repositoryAccessor,
                "token4");

        assertThat(validator.validate("token4")).isNotOK();
    }

    private RepositoryAccessor initRepositoryAccessor() throws Exception {
        final List<ApplicationNode> applications = Arrays.asList(
                createAppWithToken("token1"),
                createAppWithToken("token2"),
                createAppWithToken("token4"),
                createAppWithToken("token4"));

        final ApplicationNodeContainer applicationNodeContainer = mock(ApplicationNodeContainer.class);
        when(applicationNodeContainer.getApplications()).thenReturn(applications);

        final ApplicationFileStore applicationFileStore = mock(ApplicationFileStore.class);
        when(applicationFileStore.getContent()).thenReturn(applicationNodeContainer);

        final List<ApplicationFileStore> applicationFileStores = new ArrayList<>();
        applicationFileStores.add(applicationFileStore);

        final ApplicationRepositoryStore applicationRepositoryStore = mock(ApplicationRepositoryStore.class);
        when(applicationRepositoryStore.getChildren()).thenReturn(applicationFileStores);

        final RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        when(repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class)).thenReturn(applicationRepositoryStore);

        return repositoryAccessor;
    }

    protected ApplicationNode createAppWithToken(String token) {
        final ApplicationNode applicationNode = mock(ApplicationNode.class);
        when(applicationNode.getToken()).thenReturn(token);
        return applicationNode;
    }

}
