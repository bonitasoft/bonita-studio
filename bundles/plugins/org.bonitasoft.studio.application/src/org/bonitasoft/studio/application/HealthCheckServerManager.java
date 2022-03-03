package org.bonitasoft.studio.application;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;

import com.sun.net.httpserver.HttpServer;

public class HealthCheckServerManager {

    private HttpServer server;
    private int runningPort;
    private static HealthCheckServerManager INSTANCE;

    public static synchronized HealthCheckServerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HealthCheckServerManager();
        }
        return INSTANCE;
    }

    private HealthCheckServerManager() {

    }

    public synchronized void start(int port) throws IOException {
        if (server == null) {
            runningPort = port;
            server = HttpServer.create(new InetSocketAddress(runningPort), 0);
            server.createContext("/api/workspace/status/", exchange -> {
                String response = "Studio is alive";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            });
            server.setExecutor(null); // creates a default executor
            server.start();
            updatePreference(port);
            // BonitaStudioPreferencesPlugin
            BonitaStudioLog.debug(String.format("Studio Healthcheck server started on port %s...", port),
                    UIDesignerPlugin.PLUGIN_ID);
        }
    }

    void updatePreference(int port) {
        BonitaStudioPreferencesPlugin bonitaStudioPreferencesPlugin = BonitaStudioPreferencesPlugin.getDefault();
        if (bonitaStudioPreferencesPlugin != null) {
            bonitaStudioPreferencesPlugin.getPreferenceStore()
                    .setValue(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT, port);
        }
    }

    public synchronized void stop() {
        if (server != null) {
            BonitaStudioLog.debug("Stopping healthcheck server...", UIDesignerPlugin.PLUGIN_ID);
            server.stop(0);
            server = null;
        }
    }

    public int runningPort() {
        if (server == null) {
            throw new IllegalStateException("Studio Healthcheck server is not running");
        }
        return runningPort;
    }

    public boolean isRunning() {
        return server != null;
    }

}
