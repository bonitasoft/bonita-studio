/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.ServerSocket;

import org.bonitasoft.studio.designer.core.WorkspaceApplication;
import org.bonitasoft.studio.designer.core.WorkspaceResourceServerManager;
import org.eclipse.jdt.launching.SocketUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.TemplateRoute;
import org.restlet.routing.VirtualHost;
import org.restlet.util.RouteList;

/**
 * @author Romain Bioteau
 */
public class WorkspaceResourceServerManagerTest {

    private WorkspaceResourceServerManager workspaceResourceServerManager;
    private int port;
    private Server server;
    private VirtualHost defaultHost;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        workspaceResourceServerManager = WorkspaceResourceServerManager.getInstance();
        port = SocketUtil.findFreePort();
        workspaceResourceServerManager.start(port);

        final Component component = workspaceResourceServerManager.getComponent();
        assertThat(component).isNotNull();
        assertThat(component.isStarted()).isTrue();
        assertThat(component.getServers()).hasSize(1);
        server = component.getServers().get(0);

        defaultHost = component.getDefaultHost();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        if (workspaceResourceServerManager.isRunning()) {
            final int runningPort = workspaceResourceServerManager.runningPort();
            workspaceResourceServerManager.stop();
            assertThat(workspaceResourceServerManager.isRunning()).isFalse();
            assertThat(portAvailable(runningPort)).isTrue();
        }
    }

    private boolean portAvailable(final int port) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (final IOException e) {
            return false;
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
        return true;
    }

    @Test
    public void should_manager_be_a_singleton() throws Exception {
        assertThat(workspaceResourceServerManager).isSameAs(WorkspaceResourceServerManager.getInstance());
    }

    @Test
    public void should_start_a_restlet_component_on_the_given_port() throws Exception {
        assertThat(server.getPort()).isEqualTo(port);
    }

    @Test
    public void should_start_a_restlet_component_with_http_protocol() throws Exception {
        assertThat(server.getProtocols().get(0)).isEqualTo(Protocol.HTTP);
    }

    @Test
    public void should_stop_the_restlet_component() throws Exception {
        workspaceResourceServerManager.stop();
        assertThat(workspaceResourceServerManager.getComponent()).isNull();
    }

    @Test
    public void should_start_a_restlet_component_with_attached_application() throws Exception {
        final RouteList routes = defaultHost.getRoutes();
        assertThat(routes).hasSize(1);
        final TemplateRoute route = (TemplateRoute) routes.get(0);
        assertThat(route.getTemplate().getPattern()).isEqualTo("/api");
        assertThat(route.getNext()).isInstanceOf(WorkspaceApplication.class);
    }

    @Test(expected = IllegalStateException.class)
    public void should_runningPort_throw_IllegalStateException_if_server_is_not_running() throws Exception {
        workspaceResourceServerManager.stop();
        workspaceResourceServerManager.runningPort();
    }

    @Test
    public void should_runningPort_return_server_port() throws Exception {
        assertThat(workspaceResourceServerManager.runningPort()).isEqualTo(port);
    }

}
