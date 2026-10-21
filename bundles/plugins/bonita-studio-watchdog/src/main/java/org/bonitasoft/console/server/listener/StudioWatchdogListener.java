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
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.apache.catalina.LifecycleState;
import org.apache.catalina.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stops the Tomcat runtime when the Studio that launched it dies.
 * <p>
 * The poller probes the Studio healthcheck endpoint at the interval the launch configures and
 * requires {@link #HEALTHCHECK_FAILURE_STRIKES} consecutive failures before acting, so a GC pause in
 * the Studio does not cost a runtime.
 * <p>
 * HttpURLConnection rather than java.net.http.HttpClient: on Java 17 the client has no close() and
 * its selector thread, created under the webapp classloader, outlives an undeploy. The probe pins
 * NO_PROXY, refuses redirects and disables caches, closing the routes by which something other than
 * Studio could answer 200; it trusts the status line only, so a process that binds the released
 * loopback port and answers 200 there is still believed.
 * <p>
 * Three states must never end in a forced exit, and none of them is visible from the same place:
 * {@code stopping} marks this context being destroyed (an undeploy is not a Studio death),
 * {@code exiting} marks our own System.exit unwinding - contextDestroyed then runs on the
 * shutdown-hook thread while the poller is parked in Shutdown.runHooks() and can never terminate
 * first - and {@link #GOING_DOWN_STATES} marks Catalina stopping, which begins before contexts are
 * destroyed.
 *
 * @author Julien Mege
 * @author Antoine Mottier
 */
public class StudioWatchdogListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudioWatchdogListener.class);

    /** Fallback only: the launch configures the interval. */
    static final long DEFAULT_POLLING_INTERVAL_MILLIS = 5000;
    /** Applied to the connect and to EACH read, so one probe can block up to twice this. */
    private static final int HEALTHCHECK_STAGE_TIMEOUT_MILLIS = 5_000;
    /** Consecutive failures tolerated, so a single blip does not take the runtime down. */
    private static final int HEALTHCHECK_FAILURE_STRIKES = 3;
    /** Shorter than the polling interval, or the strikes would triple the orphan window. */
    private static final long HEALTHCHECK_RETRY_DELAY_MILLIS = 1_000;
    private static final int SHUTDOWN_CONNECT_TIMEOUT_MILLIS = 5_000;
    /** Highest port a URL can be connected to: above it every connect throws before any I/O. */
    private static final int MAX_ENDPOINT_PORT = 65535;
    /**
     * Backstop for the undeploy wait; it does not prove termination. contextDestroyed closes the
     * in-flight probe first, so the poller normally terminates in microseconds; this bound covers the
     * case where that close does not take effect. It must still exceed one probe's two stage budgets
     * - HttpURLConnection is uninterruptible and its connect and per-read budgets are independent, so
     * 2 x {@link #HEALTHCHECK_STAGE_TIMEOUT_MILLIS} is typical and a drip-fed response exceeds it -
     * or the leaked-thread warning fires spuriously.
     */
    private static final long POLLER_STOP_TIMEOUT_SECONDS = 12;

    /**
     * Keep in sync with TomcatVmArgsBuilder (the producer): separate build worlds cannot share
     * the constant, and a rename there disarms the watchdog silently. Pinned by
     * TomcatVmArgsBuilderTest; package-private so this module's test can alias it.
     */
    static final String HEALTHCHECK_ENDPOINT = "org.bonitasoft.studio.healthcheck.endpoint";
    /** Keep in sync with TomcatVmArgsBuilder, see HEALTHCHECK_ENDPOINT. Package-private for the test. */
    static final String POLLING_INTERVAL = "org.bonitasoft.studio.healthcheck.interval";
    /** Shared with the test's thread scan. */
    static final String POLLER_THREAD_NAME = "studio-watchdog-poller";
    /**
     * Catalina states meaning the container is already going down. Static so a class-visibility
     * problem or an enum rename fails the deployment rather than only the shutdown that needed it.
     * <p>
     * A state set rather than LifecycleState.isAvailable(), which is wrong both ways here:
     * STOPPING_PREP reports available (stop() sets it before stopping anything) and FAILED reports
     * unavailable, though a failed server is what most needs the forced exit.
     */
    private static final Set<LifecycleState> GOING_DOWN_STATES = EnumSet.of(LifecycleState.STOPPING_PREP,
            LifecycleState.STOPPING, LifecycleState.STOPPED, LifecycleState.DESTROYING,
            LifecycleState.DESTROYED);
    // daemon so the poller can never keep the JVM alive after Catalina stops
    private final ExecutorService executor = Executors.newSingleThreadExecutor(runnable -> {
        var thread = new Thread(runnable, POLLER_THREAD_NAME);
        thread.setDaemon(true);
        return thread;
    });
    // instance fields: a redeployment starts armed, with no state surviving from the last one
    private volatile boolean stopping = false;
    /** Package-private for the test. */
    volatile boolean exiting = false;
    /**
     * The probe the poller is currently blocked in, or null. Written by the poller and read by
     * Catalina's stop thread: closing it from there is what turns the bounded wait below into an
     * immediate one when the Studio is frozen rather than dead.
     * <p>
     * It closes a blocked READ. Published before the connection is established, so during the TCP
     * connect the close is a no-op and the poller still takes up to
     * {@link #HEALTHCHECK_STAGE_TIMEOUT_MILLIS} to notice - which is a window the bounded wait
     * covers, not one this field removes.
     */
    private volatile HttpURLConnection inFlightProbe;

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        // execute, not submit: a Throwable escaping a submitted task lands in an unread Future
        executor.execute(() -> {
            try {
                runWatchdog();
            } finally {
                // shut down on EVERY exit path: an idle worker reads as armed in a thread dump, and
                // after an abrupt exit ThreadPoolExecutor would spawn a replacement with the same
                // name (addWorker refuses once the pool is shut down)
                executor.shutdown();
            }
        });
    }

    private void runWatchdog() {
        final String url = System.getProperty(HEALTHCHECK_ENDPOINT);
        if (url == null) {
            // deployed outside a Studio launch: stay disarmed rather than stopping a server no
            // Studio manages. The shutdown port is not listening this early in Catalina startup, so
            // an attempt could only end in the forced System.exit(-1)
            LOGGER.error("{} system property is not set:"
                    + " the Studio watchdog is disabled and will not stop this server if the Studio dies",
                    HEALTHCHECK_ENDPOINT);
            return;
        }
        var endpoint = parseEndpoint(url);
        if (endpoint == null) {
            return;
        }
        final long interval = getPollingInterval();
        // info: the bundle enables it for org.bonitasoft, so this stays a catalina.log audit line
        LOGGER.info("Polling Studio healthcheck endpoint {} with a delay set to {} ms", url, interval);

        try {
            poll(endpoint, interval);
        } catch (final InterruptedException e) {
            reportStudioDeathUnlessStopping(e);
            // restored after the shutdown attempt: that path must not run carrying an interrupt,
            // which its next blocking call would inherit
            Thread.currentThread().interrupt();
        } catch (final Exception e) {
            reportStudioDeathUnlessStopping(e);
        } catch (final Error e) {
            // rethrown: an OutOfMemoryError must not be converted into a log line
            LOGGER.error("Studio watchdog poller terminated unexpectedly for endpoint {}", url, e);
            throw e;
        } finally {
            // info: pairs with the arm line for the undeploy audit in catalina.log
            LOGGER.info("Studio watchdog polling of {} stopped", url);
        }
    }

    private void reportStudioDeathUnlessStopping(final Exception cause) {
        if (stopping) {
            // context stop, not a Studio death: do not shut the server down
            return;
        }
        // the cause travels with the message, not on a debug line: this is the only record of why
        // the Studio was considered dead, read after a runtime stopped unexpectedly
        LOGGER.warn("Bonita Studio process has been killed, terminating tomcat process properly", cause);
        shutdownTomcat();
    }

    /**
     * Parses the configured endpoint, or returns null after logging the disarm error: an unusable
     * value must disarm loudly rather than arm against an unreachable target.
     */
    static URL parseEndpoint(String url) {
        try {
            var endpoint = URI.create(url);
            // lower-cased first: RFC 3986 schemes are case-insensitive and URI keeps the case it
            // was given, so a hand-set HTTP:// would otherwise be rejected as unsupported
            var scheme = endpoint.getScheme() == null ? null : endpoint.getScheme().toLowerCase(Locale.ROOT);
            if (!"http".equals(scheme) && !"https".equals(scheme)) {
                throw new IllegalArgumentException("unsupported endpoint scheme: " + scheme);
            }
            if (endpoint.getHost() == null || endpoint.getHost().isBlank()) {
                // an empty authority is legal URI syntax but can never be connected to: arming
                // would strike out into the false 'Studio has been killed' diagnosis
                throw new IllegalArgumentException("endpoint has no host");
            }
            if (!InetAddress.getByName(endpoint.getHost()).isLoopbackAddress()) {
                // the probe pins Proxy.NO_PROXY on the premise that the endpoint is loopback:
                // enforce it, so a hand-set remote endpoint answering 200 cannot make a dead
                // Studio look alive
                throw new IllegalArgumentException("endpoint host is not a loopback address: " + endpoint.getHost());
            }
            if (endpoint.getPort() == 0 || endpoint.getPort() > MAX_ENDPOINT_PORT) {
                // URI range-checks neither bound, and both shapes fail every connect, so arming
                // would kill a healthy runtime on configuration alone. 0 is what an unset port
                // preference produces; -1 means "no port" and stays valid
                throw new IllegalArgumentException(
                        "port " + endpoint.getPort() + " is not a connectable endpoint port");
            }
            return endpoint.toURL();
        } catch (final IllegalArgumentException | IOException e) {
            // IOException covers UnknownHostException from the loopback check and
            // MalformedURLException from toURL: an unresolvable host disarms like the rest
            LOGGER.error("{} system property value '{}' is not a usable endpoint URL:"
                    + " the Studio watchdog is disabled and will not stop this server if the Studio dies",
                    HEALTHCHECK_ENDPOINT, url, e);
            return null;
        }
    }



    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        stopping = true;
        executor.shutdownNow();
        // an interrupt does nothing to a thread blocked in getResponseCode; closing the socket
        // under it makes that read throw, so the poller reaches its stopping check at once instead
        // of after the stage timeouts. This hook runs on Catalina's stop thread, so a frozen Studio
        // would otherwise delay every undeploy. Best effort - disconnect() is not specified for
        // cross-thread use - hence the bounded wait below
        var probe = inFlightProbe;
        if (probe != null) {
            try {
                probe.disconnect();
            } catch (RuntimeException e) {
                // best effort by contract: an exception here must not skip the exiting check, the
                // bounded wait or its warning, which are what actually hold the stop together
                LOGGER.debug("Could not close the in-flight healthcheck probe", e);
            }
        }
        if (exiting) {
            // the poller cannot terminate before this hook returns: see the class javadoc
            return;
        }
        awaitPollerTermination();
    }

    /**
     * Behind a seam so a test can assert whether it was entered: since contextDestroyed closes the
     * in-flight probe, the poller terminates fast either way and timing cannot tell the branches apart.
     */
    void awaitPollerTermination() {
        try {
            // bounded wait so Tomcat's clearReferencesThreads does not flag the worker as leaked
            // on undeploy (daemon threads are not exempted)
            // exiting re-read AFTER the wait: the poller can reach System.exit in between, and the
            // timeout is then expected rather than a leak worth warning about
            if (!executor.awaitTermination(POLLER_STOP_TIMEOUT_SECONDS, TimeUnit.SECONDS) && !exiting) {
                LOGGER.warn(
                        "Studio watchdog poller did not terminate within {}s, it may be blocked in a healthcheck probe or sending the Tomcat shutdown command",
                        POLLER_STOP_TIMEOUT_SECONDS);
            }
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static long getPollingInterval() {
        final String timerValue = System.getProperty(POLLING_INTERVAL,
                String.valueOf(DEFAULT_POLLING_INTERVAL_MILLIS));
        try {
            final long timer = Long.parseLong(timerValue);
            if (timer > 0) {
                return timer;
            }
        } catch (final NumberFormatException e) {
            // falls through to the same warning as an out-of-range value
        }
        // a negative value would crash Thread.sleep, zero would busy-loop the endpoint
        LOGGER.warn("{} property must be a valid timer value (> 0) but was '{}', falling back to {} ms",
                POLLING_INTERVAL, timerValue, DEFAULT_POLLING_INTERVAL_MILLIS);
        return DEFAULT_POLLING_INTERVAL_MILLIS;
    }

    /**
     * Polls until interrupted (context stop) or the healthcheck fails
     * {@link #HEALTHCHECK_FAILURE_STRIKES} times in a row: a single blip (a Studio GC pause, a
     * transient error status) must not take the runtime down.
     */
    private void poll(URL endpoint, long interval)
            throws IOException, InterruptedException {
        var strikes = 0;
        while (true) {
            try {
                probe(endpoint);
                strikes = 0;
                LOGGER.debug("Studio is healthy");
                Thread.sleep(interval);
            } catch (final IOException | RuntimeException e) {
                // an unexpected RuntimeException counts as a failed check: fail closed rather than
                // end the poller and orphan the runtime for the rest of the session
                strikes++;
                if (strikes >= HEALTHCHECK_FAILURE_STRIKES) {
                    throw e;
                }
                var retryDelay = Math.min(HEALTHCHECK_RETRY_DELAY_MILLIS, interval);
                LOGGER.warn("Studio healthcheck failed ({}/{}), retrying in {} ms", strikes,
                        HEALTHCHECK_FAILURE_STRIKES, retryDelay, e);
                Thread.sleep(retryDelay);
            }
        }
    }

    /** Throws when the Studio healthcheck does not answer 200; an interrupt ends {@link #poll}. */
    private void probe(final URL endpoint) throws IOException {
        // NO_PROXY: a user -Dhttp.nonProxyHosts (via TOMCAT_EXTRA_PARAMS) REPLACES the JDK
        // default covering loopback, and a proxied probe could answer 200 for a dead Studio
        var connection = (HttpURLConnection) endpoint.openConnection(Proxy.NO_PROXY);
        // published for contextDestroyed: getResponseCode is uninterruptible, so shutdownNow cannot
        // reach the poller while it is blocked in there
        inFlightProbe = connection;
        // a followed 3xx or a cached response could answer 200 for a dead Studio, and the scheme
        // allow-list gates only the first connection
        connection.setInstanceFollowRedirects(false);
        connection.setUseCaches(false);
        connection.setConnectTimeout(HEALTHCHECK_STAGE_TIMEOUT_MILLIS);
        connection.setReadTimeout(HEALTHCHECK_STAGE_TIMEOUT_MILLIS);
        try {
            var code = connection.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK) {
                // names the endpoint and the status: this is the evidence behind stopping a user
                // runtime, and it has to distinguish a wrong answer from a refused connect
                throw new IOException(
                        "Studio healthcheck at " + endpoint + " answered " + code + " instead of 200");
            }
            // closed, but deliberately NOT compared against what HealthCheckServerManager serves:
            // drift in that string would become three strikes and a stopped runtime, which fails in
            // the dangerous direction to close a hole whose worst case is a surviving orphan
            connection.getInputStream().close();
        } finally {
            inFlightProbe = null;
            connection.disconnect();
        }
    }

    /**
     * What the shutdown decision needs from Catalina, so the policy that decides whether this JVM
     * dies is testable without a JMX server.
     */
    record ShutdownTarget(boolean goingDown, String stateName, String address, int port, String command) {
    }

    /** Package-private test seam, like getPollingInterval: not an extension point. */
    ShutdownTarget catalinaShutdownTarget() throws Exception {
        // We expect to have only one MBean server. Get it.
        MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
        ObjectName name = new ObjectName("Catalina", "type", "Server");
        Server server = (Server) mBeanServer.getAttribute(name, "managedResource");
        return new ShutdownTarget(GOING_DOWN_STATES.contains(server.getState()), server.getStateName(),
                server.getAddress(), server.getPort(), server.getShutdown());
    }

    void shutdownTomcat() {
        if (stopping) {
            // guards a case the state read below cannot see: an undeploy leaves the container
            // STARTED, so goingDown() is false and the command would take the whole Tomcat down with
            // the webapp. Narrows the window rather than closing it - the flag can still be set
            // immediately after this check
            LOGGER.debug("Context is stopping, skipping the Tomcat shutdown command");
            return;
        }
        try {
            var target = catalinaShutdownTarget();
            if (target.goingDown()) {
                // Catalina is already going down (StandardServer.await returns before contexts
                // are destroyed, so the stopping flag cannot see this): a forced exit here
                // would truncate the engine stop in progress
                LOGGER.info("Catalina is already {}, no shutdown command needed", target.stateName());
                return;
            }
            if (target.port() <= 0) {
                // shutdown port disabled in server.xml: the socket path cannot work, so reach the
                // forced exit deliberately, with a cause naming the configuration
                shutdownTomcatForced(new IllegalStateException(
                        "The Tomcat shutdown port is disabled (port " + target.port()
                                + "), cannot send the shutdown command"));
                return;
            }
            // set as soon as the command is out, so the catch can tell a failure to DELIVER from a
            // failure after delivery: try-with-resources runs close() inside the try, and
            // StandardServer.await closing its side after reading the command makes that close throw.
            // Without it, the state re-read still sees STARTED and force-exits over a clean stop
            var delivered = new AtomicBoolean(false);
            try {
                sendShutdownCommand(target, delivered);
            } catch (IOException e) {
                if (delivered.get()) {
                    // Catalina has the command: no fallback is warranted, whatever failed after
                    LOGGER.info("Shutdown command delivered, the socket then failed to close cleanly", e);
                    return;
                }
                // the state check cannot see the whole window: StandardServer.await closes the
                // shutdown socket and returns while the state is still STARTED. A refused
                // connect is what that looks like, so re-read the state before killing the JVM
                // and truncating a stop already in progress
                try {
                    if (catalinaShutdownTarget().goingDown()) {
                        LOGGER.info("Catalina started going down while sending the shutdown command, no forced exit");
                        return;
                    }
                } catch (Exception reReadFailure) {
                    // a stopping container is what makes this re-read fail (empty findMBeanServer
                    // list, ObjectName gone mid-stop). Propagating it would replace the connect
                    // failure with a JMX symptom in the line that explains a System.exit(-1)
                    e.addSuppressed(reReadFailure);
                }
                throw e;
            }
        } catch (Exception e) {
            shutdownTomcatForced(e);
        }
    }

    /**
     * Reports delivery through {@code delivered} rather than a return value, because the close that
     * follows can throw and the caller still has to know the command got out. A seam because a real
     * socket cannot be made to fail its close on demand.
     */
    void sendShutdownCommand(ShutdownTarget target, AtomicBoolean delivered) throws IOException {
        // bounded connect: a blocking Socket constructor is neither interruptible nor time-limited.
        // A null address means the loopback, as new Socket((String) null, port) did
        try (Socket clientSocket = new Socket()) {
            clientSocket.connect(
                    target.address() != null ? new InetSocketAddress(target.address(), target.port())
                            : new InetSocketAddress(InetAddress.getLoopbackAddress(), target.port()),
                    SHUTDOWN_CONNECT_TIMEOUT_MILLIS);
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(target.command().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            delivered.set(true);
        }
    }

    /**
     * A seam, not an extension point: System.exit does not throw, so without it a regression on the
     * guard below takes the surefire JVM down without naming the test or the guard.
     */
    void exitJvm(int status) {
        System.exit(status);
    }

    void shutdownTomcatForced(Exception e) {
        if (stopping) {
            // Catalina is already on the way down: exiting the JVM here would truncate a stop
            // that was going to complete cleanly
            LOGGER.debug("Tomcat is already stopping, skipping the forced exit", e);
            return;
        }
        LOGGER.error(
                "Error while trying to shutdown Tomcat (using shutdown command) from watchdog web application. We will call now System.exit(-1)",
                e);
        exiting = true;
        exitJvm(-1);
    }

}
