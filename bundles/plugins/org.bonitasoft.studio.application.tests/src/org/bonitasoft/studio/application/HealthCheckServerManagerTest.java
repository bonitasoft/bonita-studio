package org.bonitasoft.studio.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.bonitasoft.studio.common.net.HttpClientFactory;
import org.eclipse.jdt.launching.SocketUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HealthCheckServerManagerTest {

    private HealthCheckServerManager workspaceResourceServerManager;
    private int port;

    @BeforeEach
    public void setUp() throws Exception {
        workspaceResourceServerManager = HealthCheckServerManager.getInstance();
        port = SocketUtil.findFreePort();
        workspaceResourceServerManager.start(port);
    }

    @AfterEach
    public void cleanup() throws Exception {
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
    void should_return_200_status_when_alive() throws Exception {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://localhost:%s/api/workspace/status/",
                        workspaceResourceServerManager.runningPort())))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
        HttpResponse<String> response = HttpClientFactory.INSTANCE.send(request, BodyHandlers.ofString());
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    void should_manager_be_a_singleton() throws Exception {
        assertThat(workspaceResourceServerManager).isSameAs(HealthCheckServerManager.getInstance());
    }

    @Test
    void should_stop_the_restlet_component() throws Exception {
        workspaceResourceServerManager.stop();
        assertThat(workspaceResourceServerManager.isRunning()).isFalse();
    }

    @Test
    void should_runningPort_throw_IllegalStateException_if_server_is_not_running() throws Exception {
        workspaceResourceServerManager.stop();
       assertThrows(IllegalStateException.class, () -> workspaceResourceServerManager.runningPort());
    }

    @Test
    void should_runningPort_return_server_port() throws Exception {
        assertThat(workspaceResourceServerManager.runningPort()).isEqualTo(port);
    }

    
}
