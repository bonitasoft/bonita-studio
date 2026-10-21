/**
 * Copyright (C) 2026 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.IServerType;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

/**
 * The purge decision, exercised on the purger itself: which of the persisted server, runtime,
 * configuration project and install are dropped, and in which order.
 * <p>
 * Spies rather than subclasses, and a real {@link TomcatInstall} pointed at a temporary folder:
 * every case drives the purge directly.
 */
public class StaleServerStatePurgerTest {

    /** The WTP type ids a pre-Tomcat-10.1 Studio persisted in the workspace. */
    private static final String PREVIOUS_SERVER_TYPE = "org.eclipse.jst.server.tomcat.90";
    private static final String PREVIOUS_RUNTIME_TYPE = "org.eclipse.jst.server.tomcat.runtime.90";
    private static final String TOMCAT_SERVER_TYPE = BOSWebServerManager.TOMCAT_SERVER_TYPE;
    private static final String TOMCAT_RUNTIME_TYPE = BOSWebServerManager.TOMCAT_RUNTIME_TYPE;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    /**
     * Here for validateMockitoUsage: without a rule or runner a malformed stubbing surfaces in
     * whatever test Mockito touches next, possibly in another class. LENIENT rather than the strict
     * runner because the @Before stubs are shared and the early-return purge cases legitimately never
     * reach two of them - STRICT_STUBS would fail those tests for UnnecessaryStubbing.
     */
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule().strictness(Strictness.LENIENT);

    private final IServer server = mock(IServer.class);
    private final IServerType serverType = mock(IServerType.class);
    private final IRuntime runtime = mock(IRuntime.class);
    private final IRuntimeType runtimeType = mock(IRuntimeType.class);

    private IServer persistedServer;
    private IRuntime persistedRuntime;
    private boolean tomcatBundleDeleted;
    private boolean serverConfigurationProjectDeleted;
    private boolean failBundleDeletion;
    private boolean failConfProjectDeletion;
    private boolean installMatchesAdapter = true;

    private StaleServerStatePurger purger;
    private TomcatInstall install;

    /**
     * The scratch project the purge is pointed at. Injected rather than the production name, so a
     * test can drive the real deletion without touching the workspace's own server_configuration.
     */
    private static final String SCRATCH_CONFIGURATION_PROJECT = "purger-test-server-configuration";

    /**
     * Answers with the persisted objects instead of the real WTP registry. A stub of a small
     * collaborator rather than a spy on the class under test, so typeId and deleteQuietly stay real
     * and the id comparison and swallowed-deletion tolerance are genuinely exercised.
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

    @Before
    public void createPurger() throws Exception {
        install = spy(new TomcatInstall(temporaryFolder.newFolder("tomcat"),
                BOSWebServerManager.TOMCAT_MAJOR_MINOR));
        doAnswer(invocation -> installMatchesAdapter).when(install).matchesAdapter();
        doAnswer(invocation -> {
            if (failBundleDeletion) {
                throw new CoreException(Status.error("locked"));
            }
            tomcatBundleDeleted = true;
            return null;
        }).when(install).deleteStaleBundle();

        purger = spy(purgerFor(install));
        // still stubbed for the ordering tests below - they must not touch the workspace at all.
        // The real deletion has its own test, against the scratch project
        doAnswer(invocation -> {
            if (failConfProjectDeletion) {
                throw new CoreException(Status.error("locked"));
            }
            serverConfigurationProjectDeleted = true;
            return null;
        }).when(purger).deleteServerConfigurationProject();
    }

    private StaleServerStatePurger purgerFor(TomcatInstall install) {
        return new StaleServerStatePurger(install, registry, SCRATCH_CONFIGURATION_PROJECT,
                new WtpRegistry.PersistedIds(BOSWebServerManager.BONITA_TOMCAT_SERVER_ID,
                        TOMCAT_SERVER_TYPE, BOSWebServerManager.BONITA_TOMCAT_RUNTIME_ID, TOMCAT_RUNTIME_TYPE));
    }

    /**
     * Unconditional, because the test below creates a REAL workspace project: left behind by a failed
     * run it would make every later run fail at create() with "resource already exists", turning one
     * red test into a permanently red suite.
     */
    @After
    public void removeScratchConfigurationProject() throws Exception {
        var scratch = ResourcesPlugin.getWorkspace().getRoot().getProject(SCRATCH_CONFIGURATION_PROJECT);
        if (scratch.exists()) {
            scratch.delete(true, true, AbstractRepository.NULL_PROGRESS_MONITOR);
        }
    }

    @Test
    public void should_delete_the_configuration_project_it_was_given() throws Exception {
        // the real deletion, which every other test here stubs out: the injected name is what makes
        // it safe to run. The CoreException wrapping around a FAILED delete stays uncovered - making
        // IProject.delete fail on demand is not something this suite can do reliably
        var scratch = ResourcesPlugin.getWorkspace().getRoot().getProject(SCRATCH_CONFIGURATION_PROJECT);
        scratch.create(AbstractRepository.NULL_PROGRESS_MONITOR);
        scratch.open(AbstractRepository.NULL_PROGRESS_MONITOR);
        assertThat(scratch.exists()).isTrue();

        purgerFor(install).deleteServerConfigurationProject();

        assertThat(scratch.exists()).isFalse();
    }

    @Test
    public void should_finish_the_purge_when_a_stale_wtp_deletion_fails() throws Exception {
        // the tolerance is load-bearing: the conf project deletion may already have dropped these
        // objects through the resource delta, so a failed delete means the state is ALREADY purged.
        // Without this test, removing the try/catch in WtpRegistry.deleteQuietly leaves the suite
        // green while turning a recoverable upgrade into a hard failure on every start
        persistedState(PREVIOUS_SERVER_TYPE, PREVIOUS_RUNTIME_TYPE);
        doThrow(new CoreException(Status.error("locked"))).when(server).delete();

        purger.purge();

        // the runtime is still reached: the failure was swallowed, not propagated
        verify(runtime).delete();
    }

    @Test
    public void should_do_nothing_when_no_server_state_is_persisted() throws Exception {
        persistedServer = null;
        persistedRuntime = null;

        purger.purge();

        assertThat(tomcatBundleDeleted).isFalse();
        assertThat(serverConfigurationProjectDeleted).isFalse();
    }

    @Test
    public void should_reuse_the_persisted_state_when_the_adapter_type_matches() throws Exception {
        persistedState(TOMCAT_SERVER_TYPE, TOMCAT_RUNTIME_TYPE);

        purger.purge();

        verify(server, never()).delete();
        verify(runtime, never()).delete();
        assertThat(tomcatBundleDeleted).isFalse();
    }

    @Test
    public void should_purge_the_persisted_state_and_the_tomcat_bundle_on_adapter_version_change() throws Exception {
        // the real .90 -> .101 upgrade shape: stale markers AND a stale install on disk
        persistedState(PREVIOUS_SERVER_TYPE, PREVIOUS_RUNTIME_TYPE);
        installMatchesAdapter = false;

        purger.purge();

        // the server references the runtime: it must be deleted first
        var inOrder = inOrder(server, runtime);
        inOrder.verify(server).delete();
        inOrder.verify(runtime).delete();
        assertThat(tomcatBundleDeleted).isTrue();
        assertThat(serverConfigurationProjectDeleted).isTrue();
    }

    @Test
    public void should_keep_the_current_install_when_only_the_wtp_markers_are_stale() throws Exception {
        // everything metadata-derived is recreated anyway (conf project, launch configuration):
        // a stale marker over a current install must not cost a full re-extraction
        persistedState(PREVIOUS_SERVER_TYPE, PREVIOUS_RUNTIME_TYPE);

        purger.purge();

        verify(server).delete();
        verify(runtime).delete();
        assertThat(tomcatBundleDeleted).isFalse();
        assertThat(serverConfigurationProjectDeleted).isTrue();
    }

    @Test
    public void should_purge_only_the_stale_server_when_the_runtime_matches() throws Exception {
        persistedState(PREVIOUS_SERVER_TYPE, TOMCAT_RUNTIME_TYPE);

        purger.purge();

        verify(server).delete();
        verify(runtime, never()).delete();
        // the install is current: only the metadata is recreated
        assertThat(tomcatBundleDeleted).isFalse();
        assertThat(serverConfigurationProjectDeleted).isTrue();
    }

    @Test
    public void should_purge_only_the_stale_runtime_when_the_server_matches() throws Exception {
        persistedState(TOMCAT_SERVER_TYPE, PREVIOUS_RUNTIME_TYPE);

        purger.purge();

        verify(server, never()).delete();
        verify(runtime).delete();
        assertThat(tomcatBundleDeleted).isFalse();
        assertThat(serverConfigurationProjectDeleted).isTrue();
    }

    @Test
    public void should_purge_a_persisted_object_whose_type_cannot_be_resolved() throws Exception {
        when(server.getServerType()).thenReturn(null);
        when(runtime.getRuntimeType()).thenReturn(null);
        persistedServer = server;
        persistedRuntime = runtime;

        purger.purge();

        verify(server).delete();
        verify(runtime).delete();
        assertThat(tomcatBundleDeleted).isFalse();
        assertThat(serverConfigurationProjectDeleted).isTrue();
    }

    @Test
    public void should_keep_the_persisted_state_when_the_bundle_cannot_be_deleted() throws Exception {
        // a failed disk purge must not delete the WTP objects: the conf project deletion (which
        // ran first) may already have dropped the persisted server through the resource delta,
        // so the retry on the next start rests on the surviving runtime and install staleness
        persistedState(PREVIOUS_SERVER_TYPE, PREVIOUS_RUNTIME_TYPE);
        installMatchesAdapter = false;
        failBundleDeletion = true;

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(purger::purge);

        verify(server, never()).delete();
        verify(runtime, never()).delete();
        assertThat(serverConfigurationProjectDeleted).isTrue();
    }

    @Test
    public void should_keep_the_bundle_when_the_configuration_project_cannot_be_deleted() throws Exception {
        // a failed conf project deletion must leave the bundle in place: deleting the bundle
        // would erase the install-staleness evidence and the stale tomcat_conf would never be
        // purged, then published into a freshly extracted install
        persistedState(TOMCAT_SERVER_TYPE, TOMCAT_RUNTIME_TYPE);
        installMatchesAdapter = false;
        failConfProjectDeletion = true;

        assertThatExceptionOfType(CoreException.class)
                .isThrownBy(purger::purge);

        assertThat(tomcatBundleDeleted).isFalse();
        verify(server, never()).delete();
        verify(runtime, never()).delete();
    }

    @Test
    public void should_purge_everything_when_the_installed_tomcat_does_not_match_the_adapter() throws Exception {
        // the WTP markers can match (or be invisible) while a stale install survives on disk,
        // e.g. after a half-failed workspace migration
        persistedState(TOMCAT_SERVER_TYPE, TOMCAT_RUNTIME_TYPE);
        installMatchesAdapter = false;

        purger.purge();

        verify(server).delete();
        verify(runtime).delete();
        assertThat(tomcatBundleDeleted).isTrue();
        assertThat(serverConfigurationProjectDeleted).isTrue();
    }

    @Test
    public void should_purge_the_wtp_objects_when_the_bundle_directory_is_already_gone() throws Exception {
        // the post-doWorkspaceMigration shape and the most common real upgrade: the migration
        // already deleted <ws>/tomcat, so TomcatInstall.matchesAdapter reports the absent
        // directory as fresh and only the stale WTP server and runtime are left to reset
        persistedState(PREVIOUS_SERVER_TYPE, PREVIOUS_RUNTIME_TYPE);
        installMatchesAdapter = true;

        purger.purge();

        var inOrder = inOrder(server, runtime);
        inOrder.verify(server).delete();
        inOrder.verify(runtime).delete();
        // no install to reset, so the expensive re-extraction is not paid
        assertThat(tomcatBundleDeleted).isFalse();
        assertThat(serverConfigurationProjectDeleted).isTrue();
    }

    private void persistedState(String serverTypeId, String runtimeTypeId) {
        when(server.getServerType()).thenReturn(serverType);
        when(serverType.getId()).thenReturn(serverTypeId);
        when(runtime.getRuntimeType()).thenReturn(runtimeType);
        when(runtimeType.getId()).thenReturn(runtimeTypeId);
        persistedServer = server;
        persistedRuntime = runtime;
    }
}
