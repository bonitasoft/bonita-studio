package org.bonitasoft.studio.businessobject.ui.wizard.validator;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GroupIdValidatorTest {

    @Mock
    private IWorkspace workspace;

    @Test
    public void should_not_accept_empty_value() throws Exception {
        final GroupIdValidator validator = createValidator();

        final IStatus status = validator.validate("");

        StatusAssert.assertThat(status)
                .isNotOK()
                .hasMessage(Messages.bind(org.bonitasoft.studio.common.Messages.emptyField, Messages.groupId));
    }

    @Test
    public void should_not_accept_null_value() throws Exception {
        final GroupIdValidator validator = createValidator();

        final IStatus status = validator.validate(null);

        StatusAssert.assertThat(status)
                .isNotOK()
                .hasMessage(Messages.bind(org.bonitasoft.studio.common.Messages.emptyField, Messages.groupId));
    }

    @Test
    public void should_not_accept_value_with_spaces() throws Exception {
        final GroupIdValidator validator = createValidator();

        final IStatus status = validator.validate("my id");

        StatusAssert.assertThat(status)
                .isNotOK()
                .hasMessage(Messages.groupIdCannotContainsSpace);
    }

    @Test
    public void should_not_accept_value_with_special_characters() throws Exception {
        final GroupIdValidator validator = createValidator();

        when(workspace.validateName("my*/id", IResource.PROJECT)).thenReturn(ValidationStatus.ok());
        final IStatus status = validator.validate("my*/id");

        StatusAssert.assertThat(status)
                .isNotOK()
                .hasMessage(String.format("%s: %s", Messages.groupId, NLS.bind(
                        Messages.invalidGroupId, "my*/id")));
    }

    @Test
    public void should_not_accept_value_with_not_compatible_as_project_name() throws Exception {
        final GroupIdValidator validator = createValidator();

        when(workspace.validateName("aProjectThatAlreadyExists", IResource.PROJECT))
                .thenReturn(ValidationStatus.error("aProjectThatAlreadyExists"));
        final IStatus status = validator.validate("aProjectThatAlreadyExists");

        StatusAssert.assertThat(status)
                .isNotOK()
                .hasMessage(String.format("%s: %s", Messages.groupId, NLS.bind(
                        Messages.invalidGroupId, "aProjectThatAlreadyExists")));
    }

    @Test
    public void should_accept_otherwise() throws Exception {
        final GroupIdValidator validator = createValidator();

        when(workspace.validateName("aValidId", IResource.PROJECT)).thenReturn(ValidationStatus.ok());
        doReturn(ValidationStatus.ok()).when(validator).validateJavaPackageName("aValidId");
        final IStatus status = validator.validate("aValidId");

        StatusAssert.assertThat(status).isOK();
    }

    private GroupIdValidator createValidator() {
        return spy(new GroupIdValidator(workspace));
    }
}
