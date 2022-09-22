/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard.validator;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArtifactIdValidatorTest {

    @Mock
    private IWorkspace workspace;

    @Test
    public void should_not_accept_empty_value() throws Exception {
        final ArtifactIdValidator validator = createValidator();
        
        final IStatus status = validator.validate("");
        
        StatusAssert.assertThat(status)
                .isNotOK()
                .hasMessage(Messages.emptyProjectName);
    }

    @Test
    public void should_not_accept_null_value() throws Exception {
        final ArtifactIdValidator validator = createValidator();

        final IStatus status = validator.validate(null);

        StatusAssert.assertThat(status)
                .isNotOK()
                .hasMessage(Messages.emptyProjectName);
    }

    @Test
    public void should_not_accept_value_with_spaces() throws Exception {
        final ArtifactIdValidator validator = createValidator();

        final IStatus status = validator.validate("my id");

        StatusAssert.assertThat(status)
                .isNotOK()
                .hasMessage(Messages.projectNameContainSpaces);
    }

    @Test
    public void should_not_accept_value_with_special_characters() throws Exception {
        final ArtifactIdValidator validator = createValidator();

        when(workspace.validateName("my*/id", IResource.PROJECT)).thenReturn(ValidationStatus.ok());
        final IStatus status = validator.validate("my*/id");

        StatusAssert.assertThat(status)
                .isNotOK()
                .hasMessage("fieldName: " + NLS.bind(org.eclipse.m2e.core.ui.internal.Messages.wizardProjectPageMaven2ValidatorArtifactIDinvalid, "my*/id"));
    }

    @Test
    public void should_not_accept_value_that_is_not_a_java_identifier() throws Exception {
        final ArtifactIdValidator validator = createValidator();
        when(workspace.validateName(anyString(), eq(IResource.PROJECT))).thenReturn(ValidationStatus.ok());

        final IStatus status = validator.validate("my-id");

        StatusAssert.assertThat(status)
                .isNotOK();
    }

    @Test
    public void should_not_accept_value_with_not_compatible_as_project_name() throws Exception {
        final ArtifactIdValidator validator = createValidator();

        when(workspace.validateName("aProjectThatAlreadyExists", IResource.PROJECT)).thenReturn(ValidationStatus.error("aProjectThatAlreadyExists"));
        final IStatus status = validator.validate("aProjectThatAlreadyExists");

        StatusAssert.assertThat(status)
                .isNotOK()
                .hasMessage("fieldName: "
                        + NLS.bind(org.eclipse.m2e.core.ui.internal.Messages.wizardProjectPageMaven2ValidatorArtifactIDinvalid, "aProjectThatAlreadyExists"));
    }

    @Test
    public void should_accept_otherwise() throws Exception {
        final ArtifactIdValidator validator = createValidator();

        when(workspace.validateName("aValidId", IResource.PROJECT)).thenReturn(ValidationStatus.ok());
        final IStatus status = validator.validate("aValidId");

        StatusAssert.assertThat(status).isOK();
    }

    private ArtifactIdValidator createValidator() {
        return new ArtifactIdValidator(workspace, "fieldName");
    }
}
