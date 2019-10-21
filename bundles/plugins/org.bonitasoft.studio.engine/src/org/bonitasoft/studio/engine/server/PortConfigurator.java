/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine.server;

import static com.google.common.collect.Iterators.forArray;
import static com.google.common.collect.Maps.uniqueIndex;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.server.tomcat.core.internal.TomcatConfiguration;
import org.eclipse.jst.server.tomcat.core.internal.TomcatServer;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.ServerPort;
import org.eclipse.wst.server.core.model.ServerDelegate;
import org.eclipse.wst.server.core.util.SocketUtil;

import com.google.common.base.Function;

/**
 * @author Romain Bioteau
 */
public class PortConfigurator {

    public static final int MIN_PORT_NUMBER = 1024;
    public static final int MAX_PORT_NUMBER = 65535;

    private static final String TOMCAT_PORT_ID = "0";

    private final IServer server;
    private final ClientBonitaHomeBuildler clientBonitaHomeBuildler;
    private final IPreferenceStore preferenceStore;

    public PortConfigurator(final IServer server,
            final ClientBonitaHomeBuildler clientBonitaHomeBuildler,
            final IPreferenceStore preferenceStore) {
        this.server = server;
        this.clientBonitaHomeBuildler = clientBonitaHomeBuildler;
        this.preferenceStore = preferenceStore;
    }

    public void configureServerPort(final IProgressMonitor monitor) throws CoreException {
        final TomcatServer serverDelegate = asTomcatServer(monitor);
        final Map<String, ServerPort> portsById = serverPortByName(serverDelegate);
        for (final ServerPort port : portsById.values()) {
            configurePort(port, monitor);
        }
        final TomcatServer tomcatServer = asTomcatServer(monitor);
        final Map<String, ServerPort> newPortByIds = serverPortByName(tomcatServer);
        final int tomcatPort = newPortByIds.get(TOMCAT_PORT_ID).getPort();

        try {
            clientBonitaHomeBuildler.withHTTPAPIType().withHost("localhost").withPort(tomcatPort).writeClientProperties();
        } catch (final IOException e) {
            throw new CoreException(new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID,
                    "Failed to write bonita home client property file", e));
        }
        preferenceStore.setValue(BonitaPreferenceConstants.CONSOLE_PORT,
                tomcatPort);
    }

    private TomcatServer asTomcatServer(final IProgressMonitor monitor) {
        final Object adapter = server.loadAdapter(TomcatServer.class, monitor);
        if (adapter instanceof TomcatServer) {
            return (TomcatServer) adapter;
        }
        throw new IllegalStateException(String.format("Unbable to retrieve TomcatServer from server: %s", server.getId()));
    }

    private void configurePort(final ServerPort port, final IProgressMonitor monitor) throws CoreException {
        final int tomcatPort = preferenceStore.getInt(BonitaPreferenceConstants.CONSOLE_PORT);
        if (TOMCAT_PORT_ID.equals(port.getId())) {
            if (tomcatPort != port.getPort() && !isPortInUse(tomcatPort)) {
                updatePort(port, tomcatPort, monitor);
            }
        }
        if (isPortInUse(port.getPort())) {
            updatePort(port, findUnusedPort(MIN_PORT_NUMBER, MAX_PORT_NUMBER), monitor);
        }
    }

    private void updatePort(final ServerPort serverPort, final int newPort, final IProgressMonitor monitor)
            throws CoreException {
        BonitaStudioLog.debug(
                String.format("Unable to use port %s for %s, port %s will be used instead.", serverPort.getPort(),
                        serverPort.getName(), newPort),
                EnginePlugin.PLUGIN_ID);
        final IServerWorkingCopy workingCopy = server.createWorkingCopy();
        final TomcatServer loadAdapter = (TomcatServer) workingCopy.loadAdapter(TomcatServer.class, monitor);
        final TomcatConfiguration configuration = loadAdapter.getTomcatConfiguration();
        configuration.modifyServerPort(serverPort.getId(), newPort);
        workingCopy.saveAll(true, monitor);
    }

    private Map<String, ServerPort> serverPortByName(final ServerDelegate serverDelegate) {
        return uniqueIndex(forArray(serverDelegate.getServerPorts()), withPortIdAsKey());
    }

    private Function<ServerPort, String> withPortIdAsKey() {
        return new Function<ServerPort, String>() {

            @Override
            public String apply(final ServerPort serverPort) {
                return serverPort.getId();
            }
        };
    }

    protected boolean isPortInUse(final int port) {
        try {
            return SocketUtil.isPortInUse(InetAddress.getByName("localhost"), port) || SocketUtil.isPortInUse(port);
        } catch (UnknownHostException e) {
            return SocketUtil.isPortInUse(port);
        }
    }

    protected int findUnusedPort(final int low, final int hight) {
        return SocketUtil.findUnusedPort(low, hight);
    }

    public int getHttpPort() {
        return preferenceStore.getInt(BonitaPreferenceConstants.CONSOLE_PORT);

    }
}
