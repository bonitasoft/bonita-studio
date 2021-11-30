/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Properties;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestAPIExtensionDescriptorTest {

    @Mock
    private IProject project;
    @Mock
    private IFile indexFile;

    @Test
    public void testIndexRetrievedEvenWithProjectClosed() throws Exception {
        doReturn(false).when(project).isOpen();
        doReturn(indexFile).when(project).getFile(Path.fromOSString("src/main/groovy/Index.groovy"));
        final RestAPIExtensionDescriptor descriptor = spy(new RestAPIExtensionDescriptor(project));

        final Properties pageProperties = new Properties();
        pageProperties.put("apiExtensions", "myApi");
        pageProperties.put("myApi.classFileName", "Index.groovy");
        doReturn(pageProperties).when(descriptor).getPageProperties();

        assertThat(descriptor.getFilesToOpen()).contains(indexFile);
        verify(project).open(AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @Test
    public void testIndexRetrievedWithProjectOpened() throws Exception {
        doReturn(true).when(project).isOpen();
        doReturn(indexFile).when(project).getFile(Path.fromOSString("src/main/groovy/Index.groovy"));
        final RestAPIExtensionDescriptor descriptor = spy(new RestAPIExtensionDescriptor(project));

        final Properties pageProperties = new Properties();
        pageProperties.put("apiExtensions", "myApi");
        pageProperties.put("myApi.classFileName", "Index.groovy");
        doReturn(pageProperties).when(descriptor).getPageProperties();

        assertThat(descriptor.getFilesToOpen()).contains(indexFile);
        verify(project, never()).open(AbstractRepository.NULL_PROGRESS_MONITOR);
    }
}
