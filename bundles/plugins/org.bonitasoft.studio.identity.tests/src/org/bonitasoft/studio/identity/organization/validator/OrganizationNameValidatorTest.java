package org.bonitasoft.studio.identity.organization.validator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.identity.organization.editor.formpage.AbstractOrganizationFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class OrganizationNameValidatorTest {

    static final String CURRENT_ORGA_NAME = "currentOrga";
    static final String OTHER_ORGA_NAME = "otherOrga";

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    OrganizationNameValidator validator;

    @Before
    public void init() {
        OrganizationFileStore fileStore1 = mock(OrganizationFileStore.class);
        when(fileStore1.getDisplayName()).thenReturn(CURRENT_ORGA_NAME);

        OrganizationFileStore fileStore2 = mock(OrganizationFileStore.class);
        when(fileStore2.getDisplayName()).thenReturn(OTHER_ORGA_NAME);

        OrganizationRepositoryStore repositoryStore = mock(OrganizationRepositoryStore.class);
        when(repositoryStore.getChildren()).thenReturn(Arrays.asList(fileStore1, fileStore2));

        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        when(repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class)).thenReturn(repositoryStore);

        Organization currentOrga = mock(Organization.class);
        when(currentOrga.getName()).thenReturn(CURRENT_ORGA_NAME);

        IObservableValue<Organization> workingCopyObservable = new WritableValue();
        workingCopyObservable.setValue(currentOrga);

        AbstractOrganizationFormPage formPage = mock(AbstractOrganizationFormPage.class);
        when(formPage.observeWorkingCopy()).thenReturn(workingCopyObservable);
        when(formPage.getRepositoryAccessor()).thenReturn(repositoryAccessor);

        validator = new OrganizationNameValidator(formPage);
    }

    @Test
    public void should_accept_valid_name() {
        IStatus status = validator.validate(CURRENT_ORGA_NAME + "_renamed");
        StatusAssert.assertThat(status).isOK();

        status = validator.validate(CURRENT_ORGA_NAME);
        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_throw_error_when_new_name_is_null_or_empty() {
        IStatus status = validator.validate(null);
        StatusAssert.assertThat(status).isError();

        status = validator.validate("");
        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_throw_error_when_name_contains_forbidden_char() {
        Arrays.asList('#', '%', '$', '\\', '/', '"', '*', '?', ':', '<', '>', '|').stream()
                .map(forbiddenChar -> CURRENT_ORGA_NAME + forbiddenChar)
                .map(validator::validate)
                .forEach(status -> StatusAssert.assertThat(status).isError());
    }

    @Test
    public void should_throw_error_when_name_is_duplicated() {
        IStatus status = validator.validate(OTHER_ORGA_NAME);
        StatusAssert.assertThat(status).isError();
    }

}
