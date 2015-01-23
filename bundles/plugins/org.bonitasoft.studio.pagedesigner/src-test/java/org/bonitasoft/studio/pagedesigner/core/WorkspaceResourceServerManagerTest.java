/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.pagedesigner.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

import org.bonitasoft.studio.pagedesigner.core.resources.WorkspaceServerResource;
import org.eclipse.jdt.launching.SocketUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Finder;
import org.restlet.routing.TemplateRoute;
import org.restlet.routing.VirtualHost;
import org.restlet.util.RouteList;


/**
 * @author Romain Bioteau
 *
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
        workspaceResourceServerManager.stop();
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

    @Test(expected = BindException.class)
    public void should_throw_an_exception_if_port_already_in_use() throws Exception {
        workspaceResourceServerManager.stop();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(port);) {
                    serverSocket.accept();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        workspaceResourceServerManager.start(port);
    }

    @Test
    public void should_start_a_restlet_component_with_attached_server_routes() throws Exception {
        final RouteList routes = defaultHost.getRoutes();
        assertThat(routes).hasSize(2);
        final TemplateRoute r1 = (TemplateRoute) routes.get(0);
        assertThat(r1.getTemplate().getPattern()).isEqualTo("/workspace/{filePath}/{action}");
        assertThat(((Finder) r1.getNext()).getTargetClass()).isEqualTo(WorkspaceServerResource.class);

        final TemplateRoute r2 = (TemplateRoute) routes.get(1);
        assertThat(r2.getTemplate().getPattern()).isEqualTo("/workspace/{action}");
        assertThat(((Finder) r2.getNext()).getTargetClass()).isEqualTo(WorkspaceServerResource.class);
    }

}
