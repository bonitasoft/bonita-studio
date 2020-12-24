/**
 * Copyright (C) 2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.server;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.server.tomcat.core.internal.Tomcat85Configuration;
import org.eclipse.jst.server.tomcat.core.internal.TomcatServer;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.ServerPort;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class PortConfiguratorTest {

    private PortConfigurator portConfigurator;
    @Mock
    private IServer server;
    @Mock
    private IProgressMonitor monitor;
    @Spy
    private ClientBonitaHomeBuildler clientBonitaHomeBuilder;
    @Mock
    private IPreferenceStore preferenceStore;
    @Mock
    private TomcatServer tomcatServer;
    @Mock
    private IServerWorkingCopy serverWC;
    @Mock
    private Tomcat85Configuration tomcatConfiguration;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        portConfigurator = spy(new PortConfigurator(server, clientBonitaHomeBuilder, preferenceStore));
        when(server.loadAdapter(TomcatServer.class, monitor)).thenReturn(tomcatServer);
        when(server.createWorkingCopy()).thenReturn(serverWC);
        when(serverWC.loadAdapter(TomcatServer.class, monitor)).thenReturn(tomcatServer);
        when(tomcatServer.getTomcatConfiguration()).thenReturn(tomcatConfiguration);
        doNothing().when(clientBonitaHomeBuilder).writeClientProperties();
    }

    @Test
    public void should_update_client_home_property_with_tomcat_port() throws Exception {
        //Given
        doReturn(false).when(portConfigurator).isPortInUse(anyInt());
        when(tomcatServer.getServerPorts())
                .thenReturn(toArray(newArrayList(new ServerPort("0", "p1", 8080, "")), ServerPort.class));

        //When
        portConfigurator.configureServerPort(monitor);

        //Then
        verify(clientBonitaHomeBuilder).withPort(8080);
        verify(clientBonitaHomeBuilder).writeClientProperties();
    }

    @Test(expected = CoreException.class)
    public void should_thow_a_CoreExcpetion_if_writing_client_property_file_fails() throws Exception {
        //Given
        doReturn(false).when(portConfigurator).isPortInUse(anyInt());
        doThrow(IOException.class).when(clientBonitaHomeBuilder).writeClientProperties();
        when(tomcatServer.getServerPorts())
                .thenReturn(toArray(newArrayList(new ServerPort("0", "p1", 8080, "")), ServerPort.class));

        //When
        portConfigurator.configureServerPort(monitor);
    }

    @Test
    public void should_update_port_preference() throws Exception {
        //Given
        doReturn(false).when(portConfigurator).isPortInUse(anyInt());
        when(tomcatServer.getServerPorts())
                .thenReturn(toArray(newArrayList(new ServerPort("0", "p1", 8182, "")), ServerPort.class));

        //When
        portConfigurator.configureServerPort(monitor);

        //Then
        verify(preferenceStore).setValue(BonitaPreferenceConstants.CONSOLE_PORT,
                8182);
    }

    @Test
    public void should_update_port_if_current_port_is_not_available() throws Exception {
        //Given
        doReturn(true).when(portConfigurator).isPortInUse(8080);
        doReturn(8081).when(portConfigurator).findUnusedPort(PortConfigurator.MIN_PORT_NUMBER,
                PortConfigurator.MAX_PORT_NUMBER);
        when(tomcatServer.getServerPorts())
                .thenReturn(toArray(newArrayList(new ServerPort("0", "p1", 8080, "")), ServerPort.class));

        //When
        portConfigurator.configureServerPort(monitor);

        //Then
        verify(tomcatConfiguration).modifyServerPort("0", 8081);
    }

    @Test(expected = IllegalStateException.class)
    public void should_throw_an_IllegalStateException_when_server_cannot_be_adapt_to_TomcatServer() throws Exception {
        //Given
        when(server.loadAdapter(TomcatServer.class, monitor)).thenReturn(null);

        //When
        portConfigurator.configureServerPort(monitor);
    }

}
