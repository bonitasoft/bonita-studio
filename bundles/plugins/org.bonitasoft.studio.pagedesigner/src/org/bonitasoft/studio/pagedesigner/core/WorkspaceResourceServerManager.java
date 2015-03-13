package org.bonitasoft.studio.pagedesigner.core;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pagedesigner.PageDesignerPlugin;
import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;

public class WorkspaceResourceServerManager {

    private Component component;
    private Server server;
    private static WorkspaceResourceServerManager INSTANCE;

    public synchronized static WorkspaceResourceServerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WorkspaceResourceServerManager();
        }
        return INSTANCE;
    }

    private WorkspaceResourceServerManager() {

    }

    public synchronized void start(final int port) throws Exception {
        if (component == null || !component.isStarted()) {
            component = new Component();
            server = component.getServers().add(Protocol.HTTP, port);
            component.getDefaultHost().attach("/api", new WorkspaceApplication());

            BonitaStudioLog.debug("Starting RESTLET server on port " + port + "...", PageDesignerPlugin.PLUGIN_ID);
            component.start();
        }
    }

    public synchronized void stop() throws Exception {
        if (component != null && component.isStarted()) {
            BonitaStudioLog.debug("Stopping RESTLET server...", PageDesignerPlugin.PLUGIN_ID);

            component.stop();
            component = null;
            server = null;
        }
    }

    protected Component getComponent() {
        return component;
    }

    public int runningPort() {
        if (server == null || server.isStopped()) {
            throw new IllegalStateException("Restlet server is not running");
        }
        return server.getPort();
    }

    public boolean isRunning() {
        return component != null && component.isStarted();
    }

}
