/**
 * Copyright (C) 2016 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLDecoder;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.core.externaltools.internal.IExternalToolConstants;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OpenH2ConsoleHandlerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private ILaunchManager launchManager;

    @Spy
    private OpenH2ConsoleHandler openH2ConsoleHandler;

    @Mock
    private ILaunchConfigurationType lanchConfigurationType;

    @Mock
    private ILaunchConfigurationWorkingCopy workingCopy;

    @Mock
    private ILaunch launch;

    @Before
    public void setUp() throws Exception {
        doReturn("/test/h2_db").when(openH2ConsoleHandler).pathToDBFolder(repositoryAccessor);
        doReturn(rootFile()).when(openH2ConsoleHandler).rootFile(repositoryAccessor);
        doReturn(launchManager).when(openH2ConsoleHandler).getLaunchManager();
        doReturn("/usr/bin/java").when(openH2ConsoleHandler).javaBinaryLocation();
        when(launchManager.getLaunchConfigurationType(IExternalToolConstants.ID_PROGRAM_LAUNCH_CONFIGURATION_TYPE))
                .thenReturn(lanchConfigurationType);
        when(lanchConfigurationType.newInstance(any(), notNull())).thenReturn(workingCopy);
        when(workingCopy.launch(ILaunchManager.RUN_MODE, AbstractRepository.NULL_PROGRESS_MONITOR)).thenReturn(launch);
    }

    @Test
    public void should_locate_h2_jar_from_tomcat_folder() throws Exception {
        final String path = openH2ConsoleHandler.locateH2jar(repositoryAccessor);

        assertThat(path).contains("tomcat");
    }

    private File rootFile() throws Exception {
        return new File(FileLocator.toFileURL(OpenH2ConsoleHandlerTest.class.getResource("/workspace")).getFile());
    }

    @Test
    public void should_throw_FileNotFoundException_if_h2_jar_is_missing() throws Exception {
        doReturn(new File(URLDecoder.decode(OpenH2ConsoleHandlerTest.class.getResource("/workspaceWithoutH2").getFile(),
                "UTF-8")))
                        .when(openH2ConsoleHandler)
                        .rootFile(repositoryAccessor);

        expectedException.expect(FileNotFoundException.class);

        openH2ConsoleHandler.locateH2jar(repositoryAccessor);
    }

    @Test
    public void should_build_java_command() throws Exception {
        final File logFile = new File("");
        doReturn(logFile).when(openH2ConsoleHandler).logFile();
        doReturn("h2.jar").when(openH2ConsoleHandler).locateH2jar(repositoryAccessor);

        openH2ConsoleHandler.execute(repositoryAccessor);

        verify(workingCopy).setAttribute(IExternalToolConstants.ATTR_LOCATION, "/usr/bin/java");
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(workingCopy).setAttribute(eq(IExternalToolConstants.ATTR_TOOL_ARGUMENTS), captor.capture());
        assertThat(captor.getValue()).contains("-jar \"h2.jar\" -browser -webPort",
                "-tcp -user sa -url \"jdbc:h2:file:/test/h2_db/business_data.db;MVCC=TRUE;DB_CLOSE_ON_EXIT=TRUE;IGNORECASE=TRUE;AUTO_SERVER=TRUE;\" -driver org.h2.Driver");
        verify(workingCopy).launch("run", AbstractRepository.NULL_PROGRESS_MONITOR);
    }

}
