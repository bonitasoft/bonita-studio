package org.bonitasoft.studio.la.application.ui.validator;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.junit.Rule;
import org.junit.Test;

public class ApplicationTokenUnicityValidatorTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_validate_uniqueness_without_current_token() throws Exception {
        final RepositoryAccessor repositoryAccessor = initRepositoryAccessor();
        ApplicationNodeContainer nodeContainer = new ApplicationNodeContainer();
        final ApplicationTokenUnicityValidator validator = new ApplicationTokenUnicityValidator(repositoryAccessor,
                nodeContainer, "filename.xml");

        assertThat(validator.validate("token1")).isNotOK();
        assertThat(validator.validate("token2")).isNotOK();
        assertThat(validator.validate("token3")).isOK();
    }

    @Test
    public void should_validate_uniqueness_with_current_token() throws Exception {
        final RepositoryAccessor repositoryAccessor = initRepositoryAccessor();
        ApplicationNodeContainer nodeContainer = new ApplicationNodeContainer();
        nodeContainer.addApplication(ApplicationNodeBuilder.newApplication("token3", "appName", "1.0").create());
        ApplicationTokenUnicityValidator validator = new ApplicationTokenUnicityValidator(repositoryAccessor,
                nodeContainer, "filename.xml", new WritableValue<>("token2", String.class));

        assertThat(validator.validate("token1")).isNotOK();
        assertThat(validator.validate("token2")).isNotOK();
        assertThat(validator.validate("token3")).isNotOK();

        validator = new ApplicationTokenUnicityValidator(repositoryAccessor,
                nodeContainer, "filename.xml", new WritableValue<>("token3", String.class));

        assertThat(validator.validate("token1")).isNotOK();
        assertThat(validator.validate("TOKEN1")).isNotOK();
        assertThat(validator.validate("token2")).isNotOK();
        assertThat(validator.validate("token3")).isOK();
    }

    @Test
    public void should_validation_fails_with_current_token() throws Exception {
        final RepositoryAccessor repositoryAccessor = initRepositoryAccessor();
        ApplicationNodeContainer nodeContainer = new ApplicationNodeContainer();
        final ApplicationTokenUnicityValidator validator = new ApplicationTokenUnicityValidator(repositoryAccessor,
                nodeContainer, "filename.xml", new WritableValue<>("token4", String.class));

        assertThat(validator.validate("token4")).isNotOK();
    }

    @Test
    public void sould_take_into_account_working_copy() throws Exception {
        final RepositoryAccessor repositoryAccessor = initRepositoryAccessor();
        ApplicationNodeContainer workingCopy = new ApplicationNodeContainer();
        workingCopy.addApplication(ApplicationNodeBuilder.newApplication("workingcpy_token1", "name1", "1.0").create());
        workingCopy.addApplication(ApplicationNodeBuilder.newApplication("workingcpy_token2", "name2", "1.0").create());
        workingCopy.addApplication(ApplicationNodeBuilder.newApplication("token1", "name3", "1.0").create());
        workingCopy.addApplication(ApplicationNodeBuilder.newApplication("duplicatedToken", "name4", "1.0").create());
        workingCopy.addApplication(ApplicationNodeBuilder.newApplication("duplicatedToken", "name5", "1.0").create());

        ApplicationTokenUnicityValidator validator = new ApplicationTokenUnicityValidator(repositoryAccessor,
                workingCopy, "filename.xml", new WritableValue<>("workingcpy_token2", String.class));
        assertThat(validator.validate("token1")).isNotOK();
        assertThat(validator.validate("workingcpy_token1")).isNotOK();
        assertThat(validator.validate("workingcpy_TOKEN1")).isNotOK();
        assertThat(validator.validate("workingcpy_token2")).isOK();

        validator = new ApplicationTokenUnicityValidator(repositoryAccessor, workingCopy, "myApp.xml");
        assertThat(validator.validate("token2")).isOK();

        validator = new ApplicationTokenUnicityValidator(repositoryAccessor, workingCopy, "myApp.xml",
                new WritableValue<>("duplicatedToken", String.class));
        assertThat(validator.validate("duplicatedToken")).isNotOK();
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
        when(applicationFileStore.getName()).thenReturn("myApp.xml");

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
