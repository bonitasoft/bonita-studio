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
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

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

    private static final int PORT = 6969;

    private static final long TIMER = 5000;

    private static final String WATCHDOG_PORT = "org.bonitasoft.studio.watchdog.port";

    private static final String WATCHDOG_TIMER = "org.bonitasoft.studio.watchdog.timer";

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
       final Thread watchdog = new Thread(new Runnable() {

            @Override
            public void run() {
                final int port = getPort();
                final long timer = getTimer();
                
                LOGGER.warn("Bonita Studio watchdog process has started on port {} with a delay set to {} ms", port, timer);
                
                try {
                    while (isAlive(port, timer)) {
                    }
                } catch (final IOException e) {
                    LOGGER.warn("Bonita Studio process has been killed, terminating tomcat process properly");
                    LOGGER.debug("Studio is considered killed due to:", e);
                    shutdownTomcat();
                }
            }
        });
       watchdog.setDaemon(true);
       watchdog.start();
    }

    @Override
    public void contextDestroyed(final ServletContextEvent arg0) {
    }

    private static long getTimer() {
        final String timerValue = System.getProperty(WATCHDOG_TIMER, String.valueOf(TIMER));
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

    protected static int getPort() {
        final String portValue = System.getProperty(WATCHDOG_PORT, String.valueOf(PORT));
        int port = PORT;
        try {
            port = Integer.valueOf(portValue);
            if (port < 1024 || port > 65535) {
                throw new Exception("Invalid port range : " + port);
            }
        } catch (final Exception e) {
            LOGGER.warn("org.bonitasoft.studio.watchdog.port property must be a valid port number [1024->65535]");
        }
        return port;
    }

    private static boolean isAlive(final int port, final long timer) throws IOException {
        final SocketChannel sChannel = SocketChannel.open(new InetSocketAddress(InetAddress.getByName(null),port));
        while (!sChannel.finishConnect()) {
            try {
                Thread.sleep(100);
            } catch (final InterruptedException e) {

            }
        }
        try {
            Thread.sleep(timer);
        } catch (final InterruptedException e) {

        }
        sChannel.close();
        while (sChannel.isConnected()) {
            try {
                Thread.sleep(100);
            } catch (final InterruptedException e) {

            }
        }

        LOGGER.debug("Bonita Studio JVM is alive");
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
            try(
                    Socket clientSocket = new Socket(address, port);
                    OutputStream outputStream = clientSocket.getOutputStream();
            ) {
                outputStream.write(shutdown.getBytes(Charset.forName("UTF-8")));
                outputStream.flush();
                outputStream.close();
                clientSocket.close();
            } catch (Exception e) {
                shutdownTomcatForced(e);
            }
        } catch(Exception e) {
            shutdownTomcatForced(e);
        }
   }
    
   private static void shutdownTomcatForced(Exception e) {
       LOGGER.error("Error while trying to shutdown Tomcat (using shutdown command) from watchdog web application. We will call now System.exit(-1)", e);
       System.exit(-1);
   } 

}
