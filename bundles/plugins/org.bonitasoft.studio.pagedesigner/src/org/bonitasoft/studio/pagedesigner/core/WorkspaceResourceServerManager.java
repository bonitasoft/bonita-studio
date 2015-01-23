package org.bonitasoft.studio.pagedesigner.core;

import org.bonitasoft.studio.pagedesigner.core.resources.WorkspaceServerResource;
import org.restlet.Component;
import org.restlet.data.Protocol;

public class WorkspaceResourceServerManager {

    private Component component;
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
            component.getServers().add(Protocol.HTTP, port);
            component.getDefaultHost().attach(
                    "/workspace/{filePath}/{action}",
                    WorkspaceServerResource.class);
            component.getDefaultHost().attach("/workspace/{action}", WorkspaceServerResource.class);
            component.start();
        }
    }

    public synchronized void stop() throws Exception {
        if (component != null && component.isStarted()) {
            component.stop();
            component = null;
        }
    }

    protected Component getComponent() {
        return component;
    }


}
