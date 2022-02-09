/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.maven;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.businessobject.core.repository.BDMArtifactDescriptor;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenInstallFileOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.osgi.service.event.Event;

@RunWith(MockitoJUnitRunner.class)
public class InstallBDMDependenciesEventHandlerTest {

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private MavenInstallFileOperation installCommand;
    @Mock
    private File file;

    private Event event;

    private InstallBDMDependenciesEventHandler handler;

    @Before
    public void setUp() throws Exception {
        handler = spy(new InstallBDMDependenciesEventHandler());
        final Map<String, Object> properties = new HashMap<>();
        BDMArtifactDescriptor bdmArtifactDescriptor = new BDMArtifactDescriptor();
        bdmArtifactDescriptor.setGroupId("com.company.test");
        properties.put("artifactDescriptor", bdmArtifactDescriptor);
        event = new Event("bdm/deployed", properties);
        doReturn(file).when(handler).tmpFile(any(), any());
        doReturn(installCommand).when(handler).newInstallOperation();
        doNothing().when(handler).updateMavenProjects();
        doNothing().when(handler).buildProject();
    }

    @Test
    public void should_install_bdm_client() throws Exception {
        handler.handleEvent(event);

        verify(installCommand).installFile("com.company.test", "bdm-client", "1.0.0", "jar", null, file);
        verify(handler).updateMavenProjects();
    }

    @Test
    public void should_install_bdm_dao() throws Exception {
        handler.handleEvent(event);

        verify(installCommand).installFile(eq("com.company.test"), eq("bdm-dao"), eq("1.0.0"), eq("jar"), eq(null),
                eq(file), Mockito.notNull());
        verify(file,times(2)).delete();
        verify(handler).updateMavenProjects();
    }

}
