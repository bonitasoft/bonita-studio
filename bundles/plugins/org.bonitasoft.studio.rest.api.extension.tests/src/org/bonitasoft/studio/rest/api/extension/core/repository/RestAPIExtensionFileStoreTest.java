/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.resources.IProject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestAPIExtensionFileStoreTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_throw_a_ReadFileStoreException_when_reding_content_and_project_does_not_exists() throws Exception {
        final RestAPIExtensionRepositoryStore parentStore = mock(RestAPIExtensionRepositoryStore.class, Mockito.RETURNS_DEEP_STUBS);
        final IProject restApiProject = mock(IProject.class);
        when(restApiProject.exists()).thenReturn(false);
        when(parentStore.getResource().getWorkspace().getRoot().getProject("my-rest-api")).thenReturn(restApiProject);

        final RestAPIExtensionFileStore fileStore = spy(new RestAPIExtensionFileStore("my-rest-api", parentStore));

        expectedException.expect(ReadFileStoreException.class);
        fileStore.getContent();
    }
}
