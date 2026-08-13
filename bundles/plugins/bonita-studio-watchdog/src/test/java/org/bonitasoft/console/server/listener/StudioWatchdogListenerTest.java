/**
 * Copyright (C) 2026 Bonitasoft S.A.
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

class StudioWatchdogListenerTest {

    // aliases of the production constants (package-private seams): a renamed property or a
    // changed default cannot silently desynchronize the tests
    private static final String ENDPOINT_PROPERTY = StudioWatchdogListener.HEALTHCHECK_ENDPOINT;
    private static final String INTERVAL_PROPERTY = StudioWatchdogListener.POLLING_INTERVAL;
    private static final long DEFAULT_INTERVAL_MS = StudioWatchdogListener.DEFAULT_POLLING_INTERVAL_MILLIS;
    private static final long POLLER_JOIN_TIMEOUT_MS = 10_000;

    /** Counts entries into the bounded wait: see returnPromptlyFromContextDestructionWhileExiting. */
    private final AtomicInteger pollerWaits = new AtomicInteger();
    private final AtomicInteger shutdownAttempts = new AtomicInteger();
    private final AtomicInteger forcedExits = new AtomicInteger();
    private final CountDownLatch shutdownRequested = new CountDownLatch(1);

    /** No test may ever reach the real shutdown path: it ends in System.exit(-1). */
    private final StudioWatchdogListener listener = new StudioWatchdogListener() {

        @Override
        void awaitPollerTermination() {
            pollerWaits.incrementAndGet();
            super.awaitPollerTermination();
        }

        @Override
        void shutdownTomcat() {
            shutdownAttempts.incrementAndGet();
            shutdownRequested.countDown();
        }

        @Override
        void shutdownTomcatForced(Exception e) {
            forcedExits.incrementAndGet();
        }
    };

    private HttpServer healthcheck;
    /** Released by cleanup: lets a test park the poller inside an uninterruptible probe. */
    private final CountDownLatch blockedHandler = new CountDownLatch(1);

    @AfterEach
    void cleanup() throws InterruptedException {
        // a failed assertion in the exiting test must not cascade: reset the flag and unblock
        // the parked handler BEFORE the destruction that releases the executor worker
        listener.exiting = false;
        blockedHandler.countDown();
        // releases the executor worker: a single-thread executor keeps its idle worker alive
        // forever, and the poller-thread assertions scan the whole JVM by name, so a leaked
        // worker from one test would fail another depending on method order
        var pollers = pollerThreads();
        try {
            listener.contextDestroyed(null);
        } finally {
            // unconditional: a leak reported by the assertion below must not also abandon the
            // JVM-global properties (turning the missing-endpoint case into a false pass and
            // poisoning the next test's interval) or the server
            System.clearProperty(ENDPOINT_PROPERTY);
            System.clearProperty(INTERVAL_PROPERTY);
            if (healthcheck != null) {
                healthcheck.stop(0);
            }
        }
        assertPollersTerminated(pollers);
    }

    /**
     * Joins the threads captured before the destruction: awaitTermination is signaled from
     * processWorkerExit, on the worker thread BEFORE Thread.run() unwinds, so the thread can
     * still be visible for a moment. Deterministic (no sleep loop) because the reference is
     * captured rather than rediscovered by name.
     */
    private void assertPollersTerminated(List<Thread> pollers) throws InterruptedException {
        for (var poller : pollers) {
            poller.join(POLLER_JOIN_TIMEOUT_MS);
            assertThat(poller.isAlive()).as("poller thread leaked past contextDestroyed").isFalse();
        }
    }

    @Test
    void stopThePollerOnContextDestructionWithoutShuttingTomcatDown() throws Exception {
        startHealthcheck();

        listener.contextInitialized(null);
        var pollers = pollerThreads();
        assertThat(pollers).hasSize(1);
        // daemon: the poller must never keep the JVM alive after Catalina stops
        assertThat(pollers.get(0).isDaemon()).isTrue();

        listener.contextDestroyed(null);
        pollers.get(0).join(5_000);

        assertThat(pollers.get(0).isAlive()).isFalse();
        assertThat(shutdownAttempts).hasValue(0);
        assertThat(forcedExits).hasValue(0);
    }

    @Test
    void shutTomcatDownWhenTheHealthcheckStopsAnswering() throws Exception {
        // the WAR's whole contract: Studio dies, the runtime must go down with it
        startHealthcheck();
        listener.contextInitialized(null);

        healthcheck.stop(0);
        healthcheck = null;

        assertThat(shutdownRequested.await(10, TimeUnit.SECONDS)).isTrue();
        assertThat(shutdownAttempts).hasValue(1);
        assertThat(forcedExits).hasValue(0);
    }

    @Test
    void shutTomcatDownWhenTheHealthcheckAnswersAnError() throws Exception {
        // an error status is the contract's second trigger, distinct from a dead endpoint - and
        // it must survive the strike counter (consecutive failures, no recovery in between)
        startHealthcheck(exchange -> {
            exchange.sendResponseHeaders(503, -1);
            exchange.close();
        });

        listener.contextInitialized(null);

        assertThat(shutdownRequested.await(10, TimeUnit.SECONDS)).isTrue();
        assertThat(shutdownAttempts).hasValue(1);
        assertThat(forcedExits).hasValue(0);
    }

    @Test
    void shutTomcatDownWhenTheHealthcheckRedirects() throws Exception {
        // the untested half of the redirect defence: a 3xx must be a failed probe (HttpClient's
        // Redirect.NEVER semantics), never followed to some other host's 200 - a fail-open
        // watchdog otherwise, and a scheme-allow-list bypass
        startHealthcheck(exchange -> {
            exchange.getResponseHeaders().add("Location", "http://127.0.0.1:1/nowhere");
            exchange.sendResponseHeaders(302, -1);
            exchange.close();
        });

        listener.contextInitialized(null);

        assertThat(shutdownRequested.await(10, TimeUnit.SECONDS)).isTrue();
        assertThat(shutdownAttempts).hasValue(1);
        assertThat(forcedExits).hasValue(0);
    }

    @Test
    void surviveTransientHealthcheckBlips() throws Exception {
        // the positive direction of the strike counter: 1-2 failures followed by a recovery
        // must leave the runtime alive - pins the strikes reset no shutdown test can catch
        var requests = new AtomicInteger();
        var recoveredAndPolledOn = new CountDownLatch(1);
        startHealthcheck(exchange -> {
            var request = requests.incrementAndGet();
            exchange.sendResponseHeaders(request <= 2 ? 503 : 200, -1);
            exchange.close();
            if (request >= 5) {
                recoveredAndPolledOn.countDown();
            }
        });

        listener.contextInitialized(null);

        // a positive signal (two failures, a recovery, two more probes) instead of a fixed
        // negative wait: a green run finishes as soon as the loop proves it kept polling
        assertThat(recoveredAndPolledOn.await(10, TimeUnit.SECONDS)).isTrue();
        assertThat(shutdownAttempts).hasValue(0);
        assertThat(forcedExits).hasValue(0);
    }

    @Test
    void skipTheForcedExitWhenTheContextIsStopping() {
        // guards the System.exit(-1), the most destructive line of the module. Only exitJvm is
        // redirected, so the guard under test is the real one: asserting the exit was never
        // REQUESTED beats asserting the exiting flag, which was only ever a proxy for it
        var exitStatuses = new ArrayList<Integer>();
        var realPolicy = new StudioWatchdogListener() {

            @Override
            void exitJvm(int status) {
                exitStatuses.add(status);
            }
        };
        realPolicy.contextDestroyed(null);

        assertThatCode(() -> realPolicy.shutdownTomcatForced(new Exception("undeploy race")))
                .doesNotThrowAnyException();
        assertThat(exitStatuses).as("a stopping context must never reach the forced exit").isEmpty();
        assertThat(realPolicy.exiting).isFalse();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("unusableEndpoints")
    void stayDisarmedOnAnUnusableEndpoint(String description, String endpoint) throws Exception {
        if (endpoint != null) {
            System.setProperty(ENDPOINT_PROPERTY, endpoint);
        }
        // 50ms interval: a missing guard would arm and strike out at ~150ms, before the poller
        // thread the wait below joins can terminate - so the shutdown counters discriminate
        System.setProperty(INTERVAL_PROPERTY, "50");

        listener.contextInitialized(null);

        // no poller-thread assertion here: a disarmed poller has usually already exited, so it would
        // iterate an empty list. The counters carry the signal, and @AfterEach checks for leaks
        assertThat(shutdownRequested.getCount()).isEqualTo(1);
        assertThat(shutdownAttempts).hasValue(0);
        assertThat(forcedExits).hasValue(0);
    }

    static Stream<Arguments> unusableEndpoints() {
        return Stream.of(
                arguments("malformed URL", "not a url"),
                // the shape an unset healthcheck port preference produces: parses fine, can
                // never be connected to - arming would kill a healthy user's runtime after the
                // strikes, with a false 'Studio has been killed' diagnosis
                arguments("port zero", "http://127.0.0.1:0/api/workspace/status/"),
                // no endpoint property means the WAR runs outside a Studio launch
                // (TomcatVmArgsBuilder always sets it): the watchdog must not stop a server no
                // Studio manages. At context initialization the shutdown port is not listening
                // yet, so the historical shutdown attempt could only end in System.exit(-1)
                // killing the container at startup
                arguments("missing endpoint property", null),
                // the security-relevant case: file:/jar: values must never reach openConnection
                arguments("non-http scheme", "file:///etc/passwd"));
    }

    @Test
    void returnPromptlyFromContextDestructionWhileExiting() throws Exception {
        // when our own System.exit is unwinding, contextDestroyed runs on the shutdown-hook thread
        // while the poller is parked in Shutdown.runHooks() and can never terminate: waiting would
        // burn the full timeout and log a misleading 'blocked' warning. The handler parked on a latch
        // reproduces that, by keeping the poller inside the uninterruptible getResponseCode
        var handlerEntered = new CountDownLatch(1);
        startHealthcheck(exchange -> {
            handlerEntered.countDown();
            try {
                blockedHandler.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            exchange.sendResponseHeaders(200, -1);
            exchange.close();
        });

        listener.contextInitialized(null);
        assertThat(handlerEntered.await(10, TimeUnit.SECONDS)).isTrue();
        listener.exiting = true;

        listener.contextDestroyed(null);

        // the BRANCH, not the duration: closing the in-flight probe made the poller terminate fast
        // either way, so timing contextDestroyed passed with or without the guard and the test had
        // stopped discriminating. What must hold is that the wait was never entered
        assertThat(pollerWaits).as("the exiting early-return must not await the poller").hasValue(0);
    }

    @Test
    void acceptAndRejectEndpointShapesDeterministically() {
        // direct parse assertions, not loop wiring: the previous arm-observation test required
        // that nothing useful listens on the machine's port 80, which fails on a developer box
        // running any local web server with a catch-all vhost
        assertThat(StudioWatchdogListener.parseEndpoint("http://127.0.0.1/api/workspace/status/"))
                .as("default-port http must arm").isNotNull();
        assertThat(StudioWatchdogListener.parseEndpoint("https://127.0.0.1/api/workspace/status/"))
                .as("https must arm").isNotNull();
        assertThat(StudioWatchdogListener.parseEndpoint("http:///api/workspace/status/"))
                .as("an empty host can never be connected to: arming would strike out into the"
                        + " false 'Studio has been killed' diagnosis")
                .isNull();
        assertThat(StudioWatchdogListener.parseEndpoint("http://[0:0:0:0:0:0:0:1]:8080/api/workspace/status/"))
                .as("the producer brackets an IPv6 loopback literal, and that shape must arm")
                .isNotNull();
        assertThat(StudioWatchdogListener.parseEndpoint("http://198.51.100.7/api/workspace/status/"))
                .as("a non-loopback host would let a remote 200 mask a dead Studio, and the probe"
                        + " pins NO_PROXY on the premise that the endpoint is local")
                .isNull();
        assertThat(StudioWatchdogListener.parseEndpoint("http://127.0.0.1:99999/api/workspace/status/"))
                .as("URI does not range-check the port and connect throws deterministically:"
                        + " arming would strike out on configuration alone")
                .isNull();
    }

    @Test
    void forceTheExitWhenTheShutdownCommandCannotBeSent() {
        // the other half of the same guard: outside a stop, a failed shutdown MUST reach the exit.
        // Previously unassertable - the real System.exit would have taken the surefire JVM with it
        var exitStatuses = new ArrayList<Integer>();
        var realPolicy = new StudioWatchdogListener() {

            @Override
            void exitJvm(int status) {
                exitStatuses.add(status);
            }
        };

        realPolicy.shutdownTomcatForced(new Exception("shutdown port refused"));

        assertThat(exitStatuses).containsExactly(-1);
        assertThat(realPolicy.exiting).isTrue();
    }

    @Test
    void skipTheShutdownCommandWhenCatalinaIsAlreadyGoingDown() throws Exception {
        // StandardServer.await returns before contexts are destroyed, so the stopping flag
        // cannot see a container-wide stop: without this guard the poller would connect to a
        // shutdown port that stopped listening and force-exit, truncating the engine stop.
        // a closed loopback port makes that counterfactual observable - the connect is refused,
        // which is what would force the exit if the state were not consulted first
        var policy = policyWith(() -> new StudioWatchdogListener.ShutdownTarget(true, "STOPPING_PREP", null,
                closedLoopbackPort(), "SHUTDOWN"));

        policy.shutdownTomcat();

        assertThat(forcedExits).hasValue(0);
    }

    @Test
    void forceTheExitWhenCatalinaHasFailed() throws Exception {
        // FAILED reports LifecycleState.isAvailable() == false although nothing is stopping: a
        // failed server is the case MOST in need of the forced exit, so the going-down decision
        // must be made on the state set, not on availability
        var policy = policyWith(() -> new StudioWatchdogListener.ShutdownTarget(false, "FAILED", null,
                closedLoopbackPort(), "SHUTDOWN"));

        policy.shutdownTomcat();

        assertThat(forcedExits).hasValue(1);
    }

    @Test
    void skipTheForcedExitWhenCatalinaStartsStoppingDuringTheSend() throws Exception {
        // the state check cannot cover the whole window: await() closes the shutdown socket while
        // the state is still STARTED, so a refused connect may mean 'already stopping'. The
        // second read is what a stop that began mid-send looks like
        var reads = new AtomicInteger();
        var deadPort = closedLoopbackPort();
        var policy = policyWith(() -> new StudioWatchdogListener.ShutdownTarget(reads.getAndIncrement() > 0,
                "STOPPING", null, deadPort, "SHUTDOWN"));

        policy.shutdownTomcat();

        assertThat(reads).hasValueGreaterThanOrEqualTo(2);
        assertThat(forcedExits).hasValue(0);
    }

    @Test
    void forceTheExitWhenTheCatalinaLookupFails() {
        // no MBean server, or the managed resource is not a Server: nothing can be sent, and the
        // orphan the WAR exists to prevent must not survive because a lookup failed
        var policy = policyWith(() -> {
            throw new IllegalStateException("no MBean server");
        });

        policy.shutdownTomcat();

        assertThat(forcedExits).hasValue(1);
    }

    @Test
    void skipTheForcedExitWhenTheCommandWasDeliveredBeforeTheFailure() throws Exception {
        // Catalina HAS the command and the close then failed - which is what StandardServer.await
        // closing its side after reading it looks like. Force-exiting there truncates the graceful
        // stop, and the state re-read cannot help: the container is still STARTED at that point
        var exitStatuses = new ArrayList<Integer>();
        var policy = new StudioWatchdogListener() {

            @Override
            StudioWatchdogListener.ShutdownTarget catalinaShutdownTarget() {
                return new StudioWatchdogListener.ShutdownTarget(false, "STARTED", null, 8005, "SHUTDOWN");
            }

            @Override
            void sendShutdownCommand(StudioWatchdogListener.ShutdownTarget target, AtomicBoolean delivered)
                    throws IOException {
                delivered.set(true);
                throw new IOException("connection reset by peer");
            }

            @Override
            void exitJvm(int status) {
                exitStatuses.add(status);
            }
        };

        policy.shutdownTomcat();

        assertThat(exitStatuses).as("a delivered command must never be followed by a forced exit").isEmpty();
        assertThat(policy.exiting).isFalse();
    }

    @Test
    void forceTheExitWhenTheCommandWasNeverDelivered() throws Exception {
        // the other direction: nothing got out, so the socket path failed and the forced exit IS the
        // designed fallback. Pins that the delivered-check above did not disarm it wholesale
        var exitStatuses = new ArrayList<Integer>();
        var policy = new StudioWatchdogListener() {

            @Override
            StudioWatchdogListener.ShutdownTarget catalinaShutdownTarget() {
                return new StudioWatchdogListener.ShutdownTarget(false, "STARTED", null, 8005, "SHUTDOWN");
            }

            @Override
            void sendShutdownCommand(StudioWatchdogListener.ShutdownTarget target, AtomicBoolean delivered)
                    throws IOException {
                throw new IOException("connection refused");
            }

            @Override
            void exitJvm(int status) {
                exitStatuses.add(status);
            }
        };

        policy.shutdownTomcat();

        assertThat(exitStatuses).containsExactly(-1);
    }

    @Test
    void sendTheShutdownCommandToTheConfiguredPort() throws Exception {
        // the bounded-connect rewrite and the UTF-8 write, against a real socket
        try (var listening = new ServerSocket(0, 1, InetAddress.getLoopbackAddress())) {
            var policy = policyWith(() -> new StudioWatchdogListener.ShutdownTarget(false, "STARTED",
                    listening.getInetAddress().getHostAddress(), listening.getLocalPort(), "SHUTDOWN"));

            policy.shutdownTomcat();

            try (var accepted = listening.accept()) {
                var received = new String(accepted.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                assertThat(received).isEqualTo("SHUTDOWN");
            }
        }
        assertThat(forcedExits).hasValue(0);
    }

    @Test
    void skipTheShutdownCommandWhenTheContextIsBeingUndeployed() throws Exception {
        // an undeploy or WAR reload is not a container stop: the state stays STARTED, so the
        // going-down check cannot catch it, and the command would take the whole Tomcat down with
        // the webapp. A real socket, because the assertion is that nothing connects to it
        try (var listening = new ServerSocket(0, 1, InetAddress.getLoopbackAddress())) {
            var policy = policyWith(() -> new StudioWatchdogListener.ShutdownTarget(false, "STARTED",
                    listening.getInetAddress().getHostAddress(), listening.getLocalPort(), "SHUTDOWN"));
            policy.contextDestroyed(null);

            policy.shutdownTomcat();

            // bounded accept rather than a sleep: a regression connects immediately, so this fails
            // fast in the direction that matters and only waits out the timeout when it passes
            listening.setSoTimeout(500);
            assertThatExceptionOfType(SocketTimeoutException.class).isThrownBy(listening::accept);
        }
        assertThat(forcedExits).hasValue(0);
    }

    /** A listener with only the shutdown seams redirected: the policy under test is the real one. */
    private StudioWatchdogListener policyWith(Callable<StudioWatchdogListener.ShutdownTarget> target) {
        return new StudioWatchdogListener() {

            @Override
            StudioWatchdogListener.ShutdownTarget catalinaShutdownTarget() throws Exception {
                return target.call();
            }

            @Override
            void shutdownTomcatForced(Exception e) {
                forcedExits.incrementAndGet();
            }
        };
    }

    /**
     * A port nothing listens on: bound then released, so the connect is refused rather than
     * dropped, without assuming anything about the privileged port 1.
     */
    private static int closedLoopbackPort() throws Exception {
        try (var socket = new ServerSocket(0, 1, InetAddress.getLoopbackAddress())) {
            return socket.getLocalPort();
        }
    }

    @Test
    void forceTheExitWhenTheShutdownPortIsDisabled() {
        // shutdown="-1" in server.xml: the socket path cannot work, so the forced exit is the
        // designed fallback - and it must carry a cause naming the configuration rather than a
        // generic connect failure
        var causes = new ArrayList<Exception>();
        var policy = new StudioWatchdogListener() {

            @Override
            StudioWatchdogListener.ShutdownTarget catalinaShutdownTarget() {
                return new StudioWatchdogListener.ShutdownTarget(false, "STARTED", null, 0, "SHUTDOWN");
            }

            @Override
            void shutdownTomcatForced(Exception e) {
                causes.add(e);
            }
        };

        policy.shutdownTomcat();

        assertThat(causes).singleElement().isInstanceOf(IllegalStateException.class);
    }

    @Test
    void useTheDefaultIntervalWhenThePropertyIsNotSet() {
        // the only path returning the default WITHOUT the warning: the property being unset is a
        // deployment outside a Studio launch, not a misconfiguration
        System.clearProperty(INTERVAL_PROPERTY);

        assertThat(StudioWatchdogListener.getPollingInterval()).isEqualTo(DEFAULT_INTERVAL_MS);
    }

    @Test
    void fallBackToTheDefaultIntervalOnInvalidValues() {
        System.setProperty(INTERVAL_PROPERTY, "-1");
        assertThat(StudioWatchdogListener.getPollingInterval()).isEqualTo(DEFAULT_INTERVAL_MS);

        // zero would busy-loop the healthcheck endpoint
        System.setProperty(INTERVAL_PROPERTY, "0");
        assertThat(StudioWatchdogListener.getPollingInterval()).isEqualTo(DEFAULT_INTERVAL_MS);

        System.setProperty(INTERVAL_PROPERTY, "not a number");
        assertThat(StudioWatchdogListener.getPollingInterval()).isEqualTo(DEFAULT_INTERVAL_MS);

        System.setProperty(INTERVAL_PROPERTY, "250");
        assertThat(StudioWatchdogListener.getPollingInterval()).isEqualTo(250);
    }

    private void startHealthcheck() throws Exception {
        startHealthcheck(exchange -> {
            exchange.sendResponseHeaders(200, -1);
            exchange.close();
        });
    }

    private void startHealthcheck(HttpHandler handler) throws Exception {
        var loopback = InetAddress.getLoopbackAddress();
        healthcheck = HttpServer.create(new InetSocketAddress(loopback, 0), 0);
        healthcheck.createContext("/healthcheck", handler);
        healthcheck.start();
        System.setProperty(ENDPOINT_PROPERTY, "http://" + loopback.getHostAddress() + ":"
                + healthcheck.getAddress().getPort() + "/healthcheck");
        System.setProperty(INTERVAL_PROPERTY, "100");
    }

    private List<Thread> pollerThreads() {
        // JVM-wide scan by name: correct only while this class runs in a single sequential
        // surefire JVM - revisit if forked or parallel execution is ever enabled
        return Thread.getAllStackTraces().keySet().stream()
                .filter(thread -> StudioWatchdogListener.POLLER_THREAD_NAME.equals(thread.getName()))
                .toList();
    }
}
