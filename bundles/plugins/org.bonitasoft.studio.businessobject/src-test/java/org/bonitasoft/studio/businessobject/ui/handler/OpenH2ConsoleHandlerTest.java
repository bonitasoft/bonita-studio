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
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OpenH2ConsoleHandlerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    private Runtime runtime;
    @Mock
    private RepositoryAccessor repositoryAccessor;
    @Mock
    private Process process;
    @Spy
    private OpenH2ConsoleHandler openH2ConsoleHandler;

    @Before
    public void setUp() throws Exception {
        doReturn("/test/h2_db").when(openH2ConsoleHandler).pathToDBFolder(repositoryAccessor);
        doReturn(rootFile()).when(openH2ConsoleHandler).rootFile(repositoryAccessor);
    }

    @Test
    public void should_locate_h2_jar_from_tomcat_folder() throws Exception {
        final String path = openH2ConsoleHandler.locateH2jar(repositoryAccessor);

        assertThat(path).contains("tomcat");
    }

    private File rootFile() throws MalformedURLException, URISyntaxException {
        return new File(OpenH2ConsoleHandlerTest.class.getResource("/workspace").toURI().toURL().getFile());
    }

    @Test
    public void should_throw_FileNotFoundException_if_h2_jar_is_missing() throws Exception {
        doReturn(new File(OpenH2ConsoleHandlerTest.class.getResource("/workspaceWithoutH2").toURI().toURL().getFile())).when(openH2ConsoleHandler)
                .rootFile(repositoryAccessor);
        
        expectedException.expect(FileNotFoundException.class);

        openH2ConsoleHandler.locateH2jar(repositoryAccessor);
    }

    @Test
    public void should_destroy_processes_on_shutdown() throws Exception {
        doReturn(runtime).when(openH2ConsoleHandler).getRuntime();
        doReturn(process).when(runtime).exec(anyString());
        doReturn(rootFile()).when(openH2ConsoleHandler).rootFile(repositoryAccessor);
        doReturn("h2.jar").when(openH2ConsoleHandler).locateH2jar(repositoryAccessor);

        openH2ConsoleHandler.execute(repositoryAccessor);

        verify(runtime).addShutdownHook(notNull(Thread.class));
    }

    @Test
    public void should_execute_java_command() throws Exception {
        doReturn(runtime).when(openH2ConsoleHandler).getRuntime();
        doReturn(rootFile()).when(openH2ConsoleHandler).rootFile(repositoryAccessor);
        doReturn("h2.jar").when(openH2ConsoleHandler).locateH2jar(repositoryAccessor);

        openH2ConsoleHandler.execute(repositoryAccessor);
        
        final ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(runtime).exec(argument.capture());

        assertThat(argument.getValue()).contains("-webPort").startsWith("java -jar h2.jar -browser").endsWith(
                "-tcp -user sa -url jdbc:h2:file:/test/h2_db/business_data.db;MVCC=TRUE;DB_CLOSE_ON_EXIT=TRUE;IGNORECASE=TRUE;AUTO_SERVER=TRUE; -driver org.h2.Driver");
    }

}
