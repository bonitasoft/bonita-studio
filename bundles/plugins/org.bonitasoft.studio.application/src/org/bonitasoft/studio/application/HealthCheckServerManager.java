package org.bonitasoft.studio.application;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
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
            // 0 first: the preference is persisted, so a failed start would otherwise leave last
            // session's port behind, and the runtime watchdog arms against whatever it holds -
            // striking out against a dead port and killing a healthy runtime
            updatePreference(0);
            server = HttpServer.create(new InetSocketAddress(InetAddress.getByName(null), port), 0);
            server.createContext(BonitaPreferenceConstants.HEALTHCHECK_ENDPOINT_PATH, exchange -> {
                String response = "Studio is alive";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            });
            server.setExecutor(null); // creates a default executor
            server.start();
            // after the bind: assigned before it, a failed start left the field naming a dead port,
            // hidden only by runningPort()'s null check on server
            runningPort = port;
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

    /**
     * Deliberately never called in production: the healthcheck server must live until the
     * Studio JVM exits. The bundled watchdog WAR polls this endpoint and shuts the Tomcat
     * runtime down when it stops answering - stopping it earlier (for example during the
     * pre-shutdown engine stop and H2 cleanup) would let the watchdog kill the runtime
     * mid-shutdown.
     */
    public synchronized void stop() {
        if (server != null) {
            BonitaStudioLog.debug("Stopping healthcheck server...", UIDesignerPlugin.PLUGIN_ID);
            server.stop(0);
            server = null;
            // the field too, so the invariant holds without depending on runningPort()'s guard
            runningPort = 0;
            // enforces the invariant start() establishes - non-zero only while running - in code
            // rather than in a comment: a stale port arms the runtime watchdog against nothing
            updatePreference(0);
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
