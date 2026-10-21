/**
 * Copyright (C) 2026 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assume.assumeTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.bonitasoft.studio.common.repository.model.IRepository;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.IServerType;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BOSWebServerManagerTest {

    /** The WTP type ids a pre-Tomcat-10.1 Studio persisted in the workspace. */
    private static final String PREVIOUS_SERVER_TYPE = "org.eclipse.jst.server.tomcat.90";
    private static final String PREVIOUS_RUNTIME_TYPE = "org.eclipse.jst.server.tomcat.runtime.90";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Mock
    private IServer server;
    @Mock
    private IServerType serverType;
    @Mock
    private IRuntime runtime;
    @Mock
    private IRuntimeType runtimeType;

    private IServer persistedServer;
    private IRuntime persistedRuntime;

    private BOSWebServerManager manager;
    /**
     * Shared by every test reaching setupLaunchConfiguration: the production beginRule takes the
     * workspace root with an uncancellable monitor, and a regression there would hang the suite
     * instead of failing one test. One instance so beginRule and endRule land on the same mock.
     */
    private final IJobManager jobManager = mock(IJobManager.class);

    /**
     * The persisted WTP objects, answered by a stub of the registry rather than by spying the purger:
     * the paths under test here CREATE a server, and the lookups they make are the registry's.
     */
    private final WtpRegistry registry = new WtpRegistry() {

        @Override
        IServer findServer(String id) {
            return persistedServer;
        }

        @Override
        IRuntime findRuntime(String id) {
            return persistedRuntime;
        }
    };

    /**
     * Nested rather than the rule's root, and it matters: previousLogFile() is the SIBLING of the
     * bundle directory, so a root bundle directory resolves the kept log outside the folder the rule
     * cleans up, and deleteStaleBundle() on it would recursively delete that root. Nested also
     * mirrors production (<workspace>/tomcat) and cannot collide with TomcatBundleFixture, which
     * creates its own "tomcat" folder at the top level.
     */
    private File bundleDirectory;

    @Before
    public void createManager() throws IOException {
        bundleDirectory = temporaryFolder.newFolder("workspace", "tomcat");
        // no subclass: the collaborators are constructor arguments, so the WTP lookups are stubbed
        // on a real spy of the purger instead of being reached through an overridden accessor
        var install = installAt(bundleDirectory);
        manager = new BOSWebServerManager(install, registry, SCRATCH_CONFIGURATION_PROJECT,
                new RuntimeErrorReporter(), jobManager);
    }

    /**
     * Never the production name: a manager wired to a temp-folder install would otherwise carry a
     * purge aimed at the real workspace server_configuration project. No test here runs the purge
     * unstubbed, so nothing is deleted by it - the point is that it could not reach the real project
     * even if a fixture forgot to stub the purge. The create path DOES run, which is what the
     * cleanup below exists for.
     */
    private static final String SCRATCH_CONFIGURATION_PROJECT = "manager-test-server-configuration";

    /** Every disk-touching test installs into a temporary folder, never the real workspace. */
    private static TomcatInstall installAt(File bundleDirectory) {
        return new TomcatInstall(bundleDirectory, BOSWebServerManager.TOMCAT_MAJOR_MINOR);
    }

    @Test
    public void should_match_the_shipped_tomcat_bundle_against_the_adapter_constant() {
        // closes the drift the build-time assert cannot see: tomcat.major.minor in the pom and
        // bonita-runtime.version can be bumped together while TOMCAT_MAJOR_MINOR is missed -
        // green build, then every start purges and re-extracts before failing WTP validation.
        // The pom property reaches every tycho test runtime through the shared surefire
        // configuration in community/pom.xml (so no module can silently drop both the property
        // and this guard); the bundle-content side of the contract is asserted at build time by
        // the runtime-bundle configure-bundle.groovy scripts against the same property
        var expectedPrefix = System.getProperty("tomcat.expected.version.prefix");
        // skipped in an IDE plug-in-test launch, which carries no surefire configuration, rather
        // than red for everyone running this class in Eclipse. On CI the property is always
        // present, so its absence there IS the drift this guard exists for and must fail
        assumeTrue("tomcat.expected.version.prefix is not set: add it to the launch configuration"
                + " to check the constant against the pom locally",
                expectedPrefix != null || System.getenv("CI") != null);
        assertThat(expectedPrefix)
                .as("tomcat.expected.version.prefix must be passed to the test runtime"
                        + " (shared tycho-surefire configuration in community/pom.xml)")
                // the production derivation, read without fabricating an install: this assertion is
                // about a build-time property and a version constant, not about any directory
                .isEqualTo(TomcatInstall.serverInfoPrefix(BOSWebServerManager.TOMCAT_MAJOR_MINOR));
    }

    /**
     * Unconditional: these tests really do create the project, and one left behind by a failed run
     * would be seen as existing by every later run.
     */
    @After
    public void removeScratchConfigurationProject() throws Exception {
        var scratch = ResourcesPlugin.getWorkspace().getRoot().getProject(SCRATCH_CONFIGURATION_PROJECT);
        if (scratch.exists()) {
            scratch.delete(true, true, new NullProgressMonitor());
        }
    }

    @Test
    public void should_create_the_configuration_project_it_was_given() throws Exception {
        // the other half of injecting the name: it drove the purge but not the CREATE, so a manager
        // pointed at a scratch project purged that one and created the production one
        var scratch = ResourcesPlugin.getWorkspace().getRoot().getProject(SCRATCH_CONFIGURATION_PROJECT);
        assertThat(scratch.exists()).isFalse();

        var created = manager.createServerConfigurationProject(new NullProgressMonitor());

        assertThat(created.getName()).isEqualTo(SCRATCH_CONFIGURATION_PROJECT);
        assertThat(scratch.exists()).isTrue();
        assertThat(ResourcesPlugin.getWorkspace().getRoot().getProject(
                BOSWebServerManager.SERVER_CONFIGURATION_PROJECT).exists())
                        .as("the production project must not be touched by a test manager").isFalse();
    }

    @Test
    public void should_wire_the_production_install_under_the_workspace_root() {
        // every other test builds the manager through the injecting constructor, so the wiring
        // production actually uses is otherwise never asserted: a rename of the folder would break
        // every start with nothing red. Covers the install half; the ids and the configuration
        // project the no-arg constructor passes are still only exercised in production
        var workspaceRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();

        var productionInstall = BOSWebServerManager.workspaceInstall();

        assertThat(productionInstall.bundleDirectory()).isEqualTo(new File(workspaceRoot, "tomcat"));
        assertThat(productionInstall.installDirectory()).isEqualTo(new File(workspaceRoot, "tomcat/server"));
        assertThat(productionInstall.serverInfoPrefix())
                .isEqualTo(TomcatInstall.serverInfoPrefix(BOSWebServerManager.TOMCAT_MAJOR_MINOR));
    }

    @Test
    public void should_release_the_workspace_rule_when_the_setup_fails() {
        // a leaked workspace-wide rule deadlocks every later workspace operation. The job
        // manager is mocked rather than real: a JUnit 4 timeout abandons its thread, so a
        // regression on the root rule would hang the suite instead of failing this test
        var failingSetup = new TestManager(bundleDirectory) {

            @Override
            void purgeStaleServerState() throws CoreException {
                throw new CoreException(Status.error("purge failed"));
            }

        };

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(() -> failingSetup.setupLaunchConfiguration(new NullProgressMonitor()));

        var root = ResourcesPlugin.getWorkspace().getRoot();
        var inOrder = inOrder(jobManager);
        inOrder.verify(jobManager).beginRule(eq(root), any());
        inOrder.verify(jobManager).endRule(root);
    }

    @Test
    public void should_purge_before_the_copy_and_raise_when_the_copy_leaves_no_install() throws Exception {
        // the ordering is load-bearing (purging after the copy would delete the fresh install),
        // and a copy whose failure was swallowed (PlatformUtil.copyResource logs everything)
        // must surface as the extraction error, not as a runtime validation naming a path that
        // no longer exists
        var calls = new ArrayList<String>();
        var bundleDir = new File(temporaryFolder.getRoot(), "absent");
        var failingCopy = new TestManager(bundleDir) {

            @Override
            void purgeStaleServerState() {
                calls.add("purge");
            }


            @Override
            void configureBonitaClient() {
                // stubbed: the real one mutates JVM-wide APITypeManager state, an invisible
                // side effect in a test about call ordering
            }

            @Override
            public BundleCopy copyTomcatBundleInWorkspace(IProgressMonitor monitor) {
                calls.add("copy");
                return BundleCopy.EXTRACTED;
            }
        };

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(() -> failingCopy.setupLaunchConfiguration(new NullProgressMonitor()))
                // asserted on the bound path, not the resource text: locale-independent
                .withMessageContaining(bundleDir.getAbsolutePath());

        assertThat(calls).containsExactly("purge", "copy");
    }

    @Test
    public void should_raise_the_extraction_failure_when_the_copy_reports_success_without_the_marker_jar()
            throws Exception {
        // PlatformUtil.copyResource logs and swallows every exception, so a copy that died on a
        // full disk still returns EXTRACTED: without the marker-jar check the version check would
        // call that a wrong shipped bundle and send the user to reinstall the Studio
        var bundleDir = temporaryFolder.newFolder("tomcat");
        Files.createDirectories(bundleDir.toPath().resolve("server").resolve("lib"));
        var lyingCopy = new TestManager(bundleDir) {

            @Override
            void purgeStaleServerState() {
                // nothing to purge: the partial install is what the copy left behind
            }

            @Override
            void configureBonitaClient() {
                // stubbed: JVM-wide APITypeManager mutation
            }

            @Override
            public BundleCopy copyTomcatBundleInWorkspace(IProgressMonitor monitor) {
                return BundleCopy.EXTRACTED;
            }

        };

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(() -> lyingCopy.setupLaunchConfiguration(new NullProgressMonitor()))
                .withMessageContaining(bundleDir.getAbsolutePath());
    }

    @Test
    public void should_raise_the_extraction_failure_when_the_copy_dies_halfway() throws Exception {
        // a full disk right after the purge leaves server/lib without its marker jar: the version
        // check reads that as a shipped-bundle mismatch, which tells the user to reinstall the
        // Studio for what is a local I/O failure. FAILED must win over any partial content
        var bundleDir = temporaryFolder.newFolder("tomcat");
        Files.createDirectories(bundleDir.toPath().resolve("server").resolve("lib"));
        var failedCopy = new TestManager(bundleDir) {

            @Override
            void purgeStaleServerState() {
                // nothing to purge: the partial install is what the copy left behind
            }

            @Override
            void configureBonitaClient() {
                // stubbed: JVM-wide APITypeManager mutation
            }

            @Override
            public BundleCopy copyTomcatBundleInWorkspace(IProgressMonitor monitor) {
                return BundleCopy.FAILED;
            }

        };

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(() -> failedCopy.setupLaunchConfiguration(new NullProgressMonitor()))
                // the copy TARGET, not the install subdirectory: that is the folder the message
                // asks the user to check for locks and free space
                .withMessageContaining(bundleDir.getAbsolutePath());
    }

    @Test
    public void should_raise_the_mismatch_when_the_shipped_bundle_is_the_wrong_tomcat() throws Exception {
        // a hand-patched tomcat/ resource, or the constant and pom property bumped without the
        // bundle: fail with the actual cause instead of the opaque WTP validation error (the
        // next start still purges and re-extracts - the check buys the diagnostic)
        var bundleDir = TomcatBundleFixture.bundleWithCatalinaJar(temporaryFolder, "server.info=Apache Tomcat/9.0.90\n");
        var wrongBundleCopy = new TestManager(bundleDir) {

            @Override
            void purgeStaleServerState() {
                // the freshly 'extracted' wrong bundle is already on disk
            }


            @Override
            void configureBonitaClient() {
                // stubbed: JVM-wide APITypeManager mutation
            }

            @Override
            public BundleCopy copyTomcatBundleInWorkspace(IProgressMonitor monitor) {
                // the copy itself extracted: the shipped content is what is wrong
                return BundleCopy.EXTRACTED;
            }
        };

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(() -> wrongBundleCopy.setupLaunchConfiguration(new NullProgressMonitor()))
                .withMessageContaining(BOSWebServerManager.TOMCAT_MAJOR_MINOR);
    }

    @Test
    public void should_raise_the_damaged_install_when_lib_ext_is_missing() throws Exception {
        // lib present but lib/ext gone: Tomcat would start with broken logging and time out,
        // pointing the user at a log file that cannot be written - the launch must fail through
        // the recovery dialog instead, naming the install the check actually looked at
        var installDir = new File(bundleDirectory, "server");
        assertThat(new File(installDir, "lib").mkdirs()).isTrue();
        var damagedInstall = new TestManager(bundleDirectory);

        assertThatExceptionOfType(CoreException.class)
                .as("lib/ext missing entirely")
                .isThrownBy(() -> damagedInstall
                        .configureLaunchConfiguration(mock(ILaunchConfigurationWorkingCopy.class)))
                .withMessageContaining(installDir.getAbsolutePath());

        // an existing but jarless ext/ starts Tomcat without logging just the same: listFiles
        // returns an empty array there, not null, so it needs its own guard and its own case
        assertThat(new File(installDir, "lib/ext").mkdirs()).isTrue();
        assertThatExceptionOfType(CoreException.class)
                .as("lib/ext present but empty")
                .isThrownBy(() -> damagedInstall
                        .configureLaunchConfiguration(mock(ILaunchConfigurationWorkingCopy.class)))
                .withMessageContaining(installDir.getAbsolutePath());
    }

    @Test
    public void should_raise_a_core_exception_when_the_runtime_validation_fails() throws Exception {
        // every caller only handles CoreException: the old bare RuntimeException bypassed the
        // error dialog and leaked the workspace rule
        persistedRuntime = null;
        var expectedType = mock(IRuntimeType.class);
        var newRuntime = mock(IRuntimeWorkingCopy.class);
        var workingCopy = mock(IRuntimeWorkingCopy.class);
        when(expectedType.createRuntime(any(), any())).thenReturn(newRuntime);
        when(newRuntime.createWorkingCopy()).thenReturn(workingCopy);
        when(workingCopy.validate(any())).thenReturn(Status.error("wrong tomcat"));

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(() -> manager.createServerRuntime(expectedType, new NullProgressMonitor()))
                .withMessageContaining("wrong tomcat");
    }

    @Test
    public void should_resolve_the_tomcat101_adapter_from_the_target_platform() {
        // the whole change rests on these ids existing in the WTP shipped by the target platform:
        // pin the ids and the actual adapter resolution.
        //
        // The suffix is a constant in production because WTP's ids are an enumeration it owns, not a
        // formula. The correspondence still has to hold, and a test is where a coincidence belongs:
        // if WTP ever breaks the pattern, change this assertion deliberately
        assertThat(BOSWebServerManager.TOMCAT_ADAPTER_VERSION)
                .as("bump the WTP adapter suffix alongside the Tomcat line")
                .isEqualTo(BOSWebServerManager.TOMCAT_MAJOR_MINOR.replace(".", ""));
        assertThat(BOSWebServerManager.TOMCAT_SERVER_TYPE).isEqualTo("org.eclipse.jst.server.tomcat.101");
        assertThat(BOSWebServerManager.TOMCAT_RUNTIME_TYPE).isEqualTo("org.eclipse.jst.server.tomcat.runtime.101");
        assertThat(ServerCore.findServerType(BOSWebServerManager.TOMCAT_SERVER_TYPE)).isNotNull();
        assertThat(ServerCore.findRuntimeType(BOSWebServerManager.TOMCAT_RUNTIME_TYPE)).isNotNull();
    }

    @Test
    public void should_not_reuse_a_mistyped_persisted_server() throws Exception {
        persistedServer = server;
        when(server.getServerType()).thenReturn(serverType);
        when(serverType.getId()).thenReturn(PREVIOUS_SERVER_TYPE);
        doAnswer(invocation -> persistedServer = null).when(server).delete();
        var expectedType = mock(IServerType.class);
        when(expectedType.getId()).thenReturn(BOSWebServerManager.TOMCAT_SERVER_TYPE);
        var workingCopy = mock(IServerWorkingCopy.class);
        when(expectedType.createServer(any(), any(), any(), any())).thenReturn(workingCopy);

        manager.configureServer(runtime, expectedType, mock(IFile.class), mock(IFolder.class),
                new NullProgressMonitor());

        verify(server, never()).createWorkingCopy();
        verify(expectedType).createServer(eq(BOSWebServerManager.BONITA_TOMCAT_SERVER_ID), any(), eq(runtime), any());
    }

    @Test
    public void should_refuse_to_twin_a_mistyped_server_that_survives_deletion() throws Exception {
        // WTP's registry behavior on duplicate ids is not part of its contract: a survivor of a
        // failed deletion must surface the recovery instruction, never get a same-id twin
        persistedServer = server;
        when(server.getServerType()).thenReturn(serverType);
        when(serverType.getId()).thenReturn(PREVIOUS_SERVER_TYPE);
        doThrow(new CoreException(Status.error("locked"))).when(server).delete();
        var expectedType = mock(IServerType.class);
        when(expectedType.getId()).thenReturn(BOSWebServerManager.TOMCAT_SERVER_TYPE);

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(() -> manager.configureServer(runtime, expectedType, mock(IFile.class),
                        mock(IFolder.class), new NullProgressMonitor()));

        verify(expectedType, never()).createServer(any(), any(), any(), any());
    }

    @Test
    public void should_refuse_to_twin_a_mistyped_runtime_that_survives_deletion() throws Exception {
        persistedRuntime = runtime;
        when(runtime.getRuntimeType()).thenReturn(runtimeType);
        when(runtimeType.getId()).thenReturn(PREVIOUS_RUNTIME_TYPE);
        doThrow(new CoreException(Status.error("locked"))).when(runtime).delete();
        var expectedType = mock(IRuntimeType.class);
        when(expectedType.getId()).thenReturn(BOSWebServerManager.TOMCAT_RUNTIME_TYPE);

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(() -> manager.createServerRuntime(expectedType, new NullProgressMonitor()));

        verify(expectedType, never()).createRuntime(any(), any());
    }

    @Test
    public void should_recreate_the_runtime_when_the_stale_one_is_already_gone() throws Exception {
        // the deleteQuietly tolerance exists for this case: the conf project resource delta
        // already dropped the persisted object, so its delete() fails but nothing survives
        persistedRuntime = runtime;
        when(runtime.getRuntimeType()).thenReturn(runtimeType);
        when(runtimeType.getId()).thenReturn(PREVIOUS_RUNTIME_TYPE);
        doAnswer(invocation -> persistedRuntime = null).when(runtime).delete();
        var expectedType = mock(IRuntimeType.class);
        when(expectedType.getId()).thenReturn(BOSWebServerManager.TOMCAT_RUNTIME_TYPE);
        var newRuntime = mock(IRuntimeWorkingCopy.class);
        var workingCopy = mock(IRuntimeWorkingCopy.class);
        when(expectedType.createRuntime(any(), any())).thenReturn(newRuntime);
        when(newRuntime.createWorkingCopy()).thenReturn(workingCopy);
        when(workingCopy.validate(any())).thenReturn(Status.OK_STATUS);
        when(workingCopy.save(anyBoolean(), any())).thenReturn(newRuntime);

        assertThat(manager.createServerRuntime(expectedType, new NullProgressMonitor()))
                .isEqualTo(newRuntime);

        verify(expectedType).createRuntime(eq(BOSWebServerManager.BONITA_TOMCAT_RUNTIME_ID), any());
    }

    @Test
    public void should_clear_the_configuration_before_reporting_a_failed_project_open() {
        // the clear in the catch block is load-bearing: a half-configured tomcat field
        // short-circuits every later setup attempt, so it must run BEFORE the error dialog and it
        // must be more than the pre-setup clear - hence an ordered event list rather than two
        // independent verifies, which the pre-setup clear alone would satisfy
        var events = new ArrayList<String>();
        var failure = new CoreException(Status.error("setup failed"));
        // the recorder is injected, not returned from an overridden accessor: only the manager's
        // own behaviour is stubbed below
        var recordingReporter = new RuntimeErrorReporter() {

            @Override
            void report(CoreException e) {
                events.add("report:" + e.getMessage());
            }
        };
        var failingOpen = new TestManager(bundleDirectory, recordingReporter) {

            @Override
            public void clearConfiguration() {
                events.add("clear");
            }

            @Override
            public void setupLaunchConfiguration(IProgressMonitor monitor) throws CoreException {
                throw failure;
            }

            @Override
            public boolean isLazyModeEnabled() {
                return false;
            }
        };

        failingOpen.projectOpened(mock(IRepository.class), new NullProgressMonitor());

        assertThat(events).containsExactly("clear", "clear", "report:setup failed");
    }

    /**
     * The manager wired to a temp-folder install and the mocked job manager. A named class rather
     * than an inline constructor call because the fixtures below stub BEHAVIOUR on top of it and
     * would otherwise repeat the whole argument list; non-static so it can pass the jobManager
     * field, which is what lets those fixtures drop their own jobManager() overrides.
     */
    private class TestManager extends BOSWebServerManager {

        TestManager(File bundleDir) {
            this(bundleDir, new RuntimeErrorReporter());
        }

        TestManager(File bundleDir, RuntimeErrorReporter errorReporter) {
            this(installAt(bundleDir), errorReporter);
        }

        private TestManager(TomcatInstall install, RuntimeErrorReporter errorReporter) {
            super(install, registry, SCRATCH_CONFIGURATION_PROJECT, errorReporter, jobManager);
        }
    }

}
