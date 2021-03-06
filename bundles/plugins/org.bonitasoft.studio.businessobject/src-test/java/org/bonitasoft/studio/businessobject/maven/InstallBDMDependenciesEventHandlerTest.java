/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.maven;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.businessobject.core.repository.BDMArtifactDescriptor;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.service.event.Event;

@RunWith(MockitoJUnitRunner.class)
public class InstallBDMDependenciesEventHandlerTest {

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private File daoTmpFile;

    @Mock
    private MavenInstallFileCommand installCommand;
    @Mock
    private File clientFile;

    private Event event;

    private InstallBDMDependenciesEventHandler handler;

    @Before
    public void setUp() throws Exception {
        handler = spy(new InstallBDMDependenciesEventHandler(repositoryAccessor));
        final Map<String, Object> properties = new HashMap<>();
        BDMArtifactDescriptor bdmArtifactDescriptor = new BDMArtifactDescriptor();
        bdmArtifactDescriptor.setGroupId("com.company.test");
        properties.put("artifactDescriptor", bdmArtifactDescriptor);
        event = new Event("bdm/deployed", properties);
        doReturn(daoTmpFile).when(handler).tmpDaoFile(any(byte[].class));
        doReturn(installCommand).when(handler).newInstallCommand();
        final DependencyRepositoryStore depStore = mock(DependencyRepositoryStore.class, RETURNS_DEEP_STUBS);
        when(repositoryAccessor.getRepositoryStore(DependencyRepositoryStore.class)).thenReturn(depStore);
        doNothing().when(handler).updateMavenProjects();
        when(depStore.getChild("bdm-client-pojo.jar", true).getResource().getLocation().toFile()).thenReturn(clientFile);
    }

    @Test
    public void should_install_bdm_client() throws Exception {
        handler.handleEvent(event);

        verify(installCommand).installFile("com.company.test", "bdm-client", "1.0.0", "jar", null, clientFile);
        verify(handler).updateMavenProjects();
    }

    @Test
    public void should_install_bdm_dao() throws Exception {
        handler.handleEvent(event);

        verify(installCommand).installFile(eq("com.company.test"), eq("bdm-dao"), eq("1.0.0"), eq("jar"), eq(null),
                eq(daoTmpFile), notNull(File.class));
        verify(daoTmpFile).delete();
        verify(handler).updateMavenProjects();
    }

}
