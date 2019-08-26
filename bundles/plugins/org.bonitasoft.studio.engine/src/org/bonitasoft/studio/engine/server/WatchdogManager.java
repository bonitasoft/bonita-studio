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

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.server.core.util.SocketUtil;

public class WatchdogManager {

    public static int WATCHDOG_PORT = 6969;
    private boolean closing = false;
    private static WatchdogManager INSTANCE;
    private ServerSocket watchdogServer;

    public ServerSocket getWatchdogServer() {
        return watchdogServer;
    }

    public synchronized static WatchdogManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WatchdogManager();
        }
        return INSTANCE;
    }

    private WatchdogManager() {

    }

    public final class WatchdogRunnable implements Runnable {

        @Override
        public void run() {
            try {
                if (portIsUsed()) {
                    final int oldPort = WATCHDOG_PORT;
                    WATCHDOG_PORT = SocketUtil.findUnusedPort(PortConfigurator.MIN_PORT_NUMBER,
                            PortConfigurator.MAX_PORT_NUMBER);
                    BonitaStudioLog.debug("Port "
                            + oldPort
                            + " is not available for server watchdog, studio will use next available port : "
                            + WATCHDOG_PORT,
                            EnginePlugin.PLUGIN_ID);
                }
                watchdogServer = createServerSocket();
                if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                    BonitaStudioLog.debug("Starting studio watchdog on " + WATCHDOG_PORT, EnginePlugin.PLUGIN_ID);
                }
                while (watchdogServer != null) {
                    final Socket connection = watchdogServer.accept();
                    connection.close();
                }
                if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                    BonitaStudioLog.debug("Studio watchdog shutdown", EnginePlugin.PLUGIN_ID);
                }
            } catch (final SocketException se) {
                if (!closing) {
                    logErrorDuringWatchDogUsage(se);
                } else {
                    BonitaStudioLog.info("The watchdog socket has been closed.", EnginePlugin.PLUGIN_ID);
                }
            } catch (final IOException e) {
                logErrorDuringWatchDogUsage(e);
            }
        }

        private boolean portIsUsed() {
            try {
                return SocketUtil.isPortInUse(InetAddress.getByName("localhost"), WATCHDOG_PORT)
                        || SocketUtil.isPortInUse(WATCHDOG_PORT);
            } catch (UnknownHostException e) {
                return SocketUtil.isPortInUse(WATCHDOG_PORT);
            }
        }

        protected void logErrorDuringWatchDogUsage(final IOException e) {
            BonitaStudioLog.error("Exception during Watchdog usage.", e, EnginePlugin.PLUGIN_ID);
        }

        protected ServerSocket createServerSocket() throws IOException, UnknownHostException {
            return new ServerSocket(WATCHDOG_PORT, 0, InetAddress.getByName("localhost"));
        }
    }

    public void startWatchdog() {
        if (watchdogServer == null) {
            closing = false;
            final Thread server = new Thread(new WatchdogRunnable());
            server.setDaemon(true);
            server.setName("BonitaBPM Studio server watchdog");
            server.start();
            checkStartsOccuredQuickly();
        }
    }

    protected void checkStartsOccuredQuickly() {
        int i = 0;
        while (i < 100 && watchdogServer == null) {
            try {
                Thread.sleep(50);
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e);
            }
            i++;
        }
        if (i == 100 && watchdogServer == null) {
            BonitaStudioLog.error("Watchdog server wasn't able to start in less than 5 seconds. It may lead to issue.",
                    EnginePlugin.PLUGIN_ID);
        }
    }

    public void stopWatchdog() {
        if (watchdogServer != null) {
            closing = true;
            try {
                if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                    BonitaStudioLog.debug("Shutting down watchdog...", EnginePlugin.PLUGIN_ID);
                }
                watchdogServer.close();
                watchdogServer = null;
                if (BonitaStudioLog.isLoggable(IStatus.OK)) {
                    BonitaStudioLog.debug("Watchdog shutdown ...", EnginePlugin.PLUGIN_ID);
                }
            } catch (final IOException e) {
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            }
        }
    }

}
