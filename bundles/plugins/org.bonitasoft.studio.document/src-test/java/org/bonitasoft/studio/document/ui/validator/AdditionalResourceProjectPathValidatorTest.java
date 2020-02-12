package org.bonitasoft.studio.document.ui.validator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.Resource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;

public class AdditionalResourceProjectPathValidatorTest {

    private static final String KNOWN_RESOURCE = "toto.txt";
    private static final String UNKNOWN_RESOURCE = "tutu.txt";

    AdditionalResourceProjectPathValidator validator = new AdditionalResourceProjectPathValidator();

    @Before
    public void init() {
        IFile knownFile = mock(IFile.class);
        IFile unknownFile = mock(IFile.class);
        IProject project = mock(IProject.class);
        Repository repository = mock(Repository.class);
        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        validator.repositoryAccessor = repositoryAccessor;

        when(repositoryAccessor.getCurrentRepository()).thenReturn(repository);
        when(repository.getProject()).thenReturn(project);
        when(project.getFile(KNOWN_RESOURCE)).thenReturn(knownFile);
        when(project.getFile(UNKNOWN_RESOURCE)).thenReturn(unknownFile);
        when(knownFile.exists()).thenReturn(true);
        when(unknownFile.exists()).thenReturn(false);
    }

    @Test
    public void should_return_error_for_empty_project_path() {
        Resource resource = ConfigurationFactory.eINSTANCE.createResource();
        IStatus status = validator.validate(resource);
        StatusAssert.assertThat(status).isError();
        resource.setProjectPath("");
        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_return_error_for_unknown_project_path() {
        Resource resource = ConfigurationFactory.eINSTANCE.createResource();
        resource.setProjectPath(UNKNOWN_RESOURCE);
        IStatus status = validator.validate(resource);
        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_return_ok_for_known_project_path() {
        Resource resource = ConfigurationFactory.eINSTANCE.createResource();
        resource.setProjectPath(KNOWN_RESOURCE);
        IStatus status = validator.validate(resource);
        StatusAssert.assertThat(status).isOK();
    }

}
