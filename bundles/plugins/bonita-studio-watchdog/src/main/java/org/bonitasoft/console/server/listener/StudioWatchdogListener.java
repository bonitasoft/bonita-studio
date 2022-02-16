/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
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
package org.bonitasoft.console.server.listener;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.catalina.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Studio Watchdog Servlet.
 * 
 * @author Julien Mege
 * @author Antoine Mottier
 */
public class StudioWatchdogListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudioWatchdogListener.class);

    private static final long TIMER = 5000;

    private static final String HEALTHCHECK_ENDPOINT = "org.bonitasoft.studio.healthcheck.endpoint";
    private static final String POLLING_INTERVAL = "org.bonitasoft.studio.healthcheck.interval";
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        executor.submit(() -> {
            final String url = getHealthCheckEndpoint();
            if (url == null) {
                shutdownTomcat();
            }
            final long interval = getPollingInterval();

            LOGGER.warn("Polling Studio healthcheck endpoint {} with a delay set to {} ms", url, interval);

            var client = HttpClient.newBuilder()
                    .build();
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();

            try {
                while (isHealthy(client, request, interval)) {
                }
            } catch (final IOException | InterruptedException e) {
                LOGGER.warn("Bonita Studio process has been killed, terminating tomcat process properly");
                LOGGER.debug("Studio is considered killed due to:", e);
                shutdownTomcat();
            }
        });
 
    }

    @Override
    public void contextDestroyed(final ServletContextEvent arg0) {
    }

    private static long getPollingInterval() {
        final String timerValue = System.getProperty(POLLING_INTERVAL, String.valueOf(TIMER));
        long timer = TIMER;
        try {
            timer = Long.valueOf(timerValue);
            if (timer < 0) {
                throw new Exception("Invalid timer value : " + timer);
            }
        } catch (final Exception e) {
            LOGGER.warn("org.bonitasoft.studio.watchdog.timer property must be a valid timer value (> 0)");
        }
        return timer;
    }

    protected static String getHealthCheckEndpoint() {
        final String url = System.getProperty(HEALTHCHECK_ENDPOINT);
        if (url == null) {
            LOGGER.warn("org.bonitasoft.studio.healthcheck.endpoint property must be a set.");
        }
        return url;
    }

    private static boolean isHealthy(HttpClient client, final HttpRequest request, final long interval)
            throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException("Studio Healthcheck does not respond !");
        }
        try {
            Thread.sleep(interval);
        } catch (final InterruptedException e) {

        }
        LOGGER.debug("Studio is healthy");
        return true;
    }

    private static void shutdownTomcat() {
        try {
            // We expect to have only one MBean server. Get it.
            MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);

            // Get Server object name
            ObjectName name = new ObjectName("Catalina", "type", "Server");

            // Get the server object
            Server server = (Server) mBeanServer.getAttribute(name, "managedResource");

            // Get address and port on which we can send the shutdown command
            String address = server.getAddress();
            int port = server.getPort();

            // Get the shutdown command that we need to send
            String shutdown = server.getShutdown();

            // Connect and send the shutdown command            
            try (
                    Socket clientSocket = new Socket(address, port);
                    OutputStream outputStream = clientSocket.getOutputStream();) {
                outputStream.write(shutdown.getBytes(Charset.forName("UTF-8")));
                outputStream.flush();
                outputStream.close();
                clientSocket.close();
            } catch (Exception e) {
                shutdownTomcatForced(e);
            }
        } catch (Exception e) {
            shutdownTomcatForced(e);
        }
    }

    private static void shutdownTomcatForced(Exception e) {
        LOGGER.error(
                "Error while trying to shutdown Tomcat (using shutdown command) from watchdog web application. We will call now System.exit(-1)",
                e);
        System.exit(-1);
    }

}
