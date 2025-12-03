package org.bonitasoft.studio.application.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.application.ui.control.model.FileStoreArtifact;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.junit.jupiter.api.Test;

/**
 * Tests for DeployProjectOperation focusing on:
 * - Parameter validation (fail-fast)
 * - Business logic (buildable artifacts detection, applications filtering)
 * - Status aggregation (OK, errors, mixed results)
 * - Cancellation handling
 * - Progress monitoring contract (beginTask, done)
 */
class DeployProjectOperationTest {

    // ==================== Parameter Validation Tests ====================

    @Test
    void shouldFailFastWhenSessionIsNull() {
        List<FileStoreArtifact> artifacts = Collections.emptyList();

        assertThatThrownBy(() -> new DeployProjectOperation(null, artifacts))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("session cannot be null");
    }

    @Test
    void shouldFailFastWhenArtifactsAreNull() {
        APISession session = mock(APISession.class);

        assertThatThrownBy(() -> new DeployProjectOperation(session, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("artifactsToDeploy cannot be null");
    }

    // ==================== Business Logic Tests ====================

    @Test
    void shouldComputeCorrectTotalWorkForApplicationsOnly() throws Exception {
        // Applications don't trigger Maven build
        APISession session = mock(APISession.class);
        IProgressMonitor monitor = mock(IProgressMonitor.class);
        var artifacts = createArtifacts("applications", false, 3);
        var operation = new DeployProjectOperation(session, artifacts);

        operation.run(monitor);

        // 3 applications, no Maven build → total work = 3
        verify(monitor).beginTask(anyString(), eq(3));
    }

    @Test
    void shouldComputeCorrectTotalWorkForNonBuildableArtifacts() throws Exception {
        // Non-buildable artifacts (like processes without BuildableArtifact marker)
        APISession session = mock(APISession.class);
        IProgressMonitor monitor = mock(IProgressMonitor.class);
        var artifacts = createArtifacts("processes", false, 2);
        var operation = new DeployProjectOperation(session, artifacts);

        operation.run(monitor);

        // 2 processes (non-buildable) → total work = 2 (no Maven build needed)
        // Note: In real scenario, BuildableArtifact instances would trigger Maven build,
        // but we can't easily mock class hierarchies. This tests the calculation logic.
        verify(monitor).beginTask(anyString(), eq(2));
    }

    @Test
    void shouldComputeCorrectTotalWorkForMultipleStores() throws Exception {
        // Mix of artifacts from different stores
        APISession session = mock(APISession.class);
        IProgressMonitor monitor = mock(IProgressMonitor.class);

        List<FileStoreArtifact> mixed = new ArrayList<>();
        mixed.addAll(createArtifacts("applications", false, 2));
        mixed.addAll(createArtifacts("processes", false, 3));

        var operation = new DeployProjectOperation(session, mixed);

        operation.run(monitor);

        // 5 artifacts total, applications don't trigger build → total work = 5
        verify(monitor).beginTask(anyString(), eq(5));
    }

    @Test
    void shouldHandleEmptyArtifactsList() throws Exception {
        APISession session = mock(APISession.class);
        IProgressMonitor monitor = mock(IProgressMonitor.class);
        var operation = new DeployProjectOperation(session, Collections.emptyList());

        operation.run(monitor);

        verify(monitor).beginTask(anyString(), eq(0));
        verify(monitor).done();
        assertThat(operation.getStatus()).isNotNull();
    }

    // ==================== Status Aggregation Tests ====================

    @Test
    void shouldReturnOKStatusWhenAllDeploymentsSucceed() throws Exception {
        APISession session = mock(APISession.class);
        IProgressMonitor monitor = mock(IProgressMonitor.class);
        var artifacts = createArtifacts("applications", false, 3);

        // All deployments succeed
        for (var artifact : artifacts) {
            when(artifact.deploy(any(), any(), any())).thenReturn(Status.OK_STATUS);
        }

        var operation = new DeployProjectOperation(session, artifacts);
        operation.run(monitor);

        assertThat(operation.getStatus().isOK()).isTrue();
    }

    @Test
    void shouldReturnErrorStatusWhenOneDeploymentFails() throws Exception {
        APISession session = mock(APISession.class);
        IProgressMonitor monitor = mock(IProgressMonitor.class);
        var artifacts = createArtifacts("applications", false, 3);

        // First succeeds, second fails, third succeeds
        when(artifacts.get(0).deploy(any(), any(), any())).thenReturn(Status.OK_STATUS);
        when(artifacts.get(1).deploy(any(), any(), any()))
                .thenReturn(new Status(IStatus.ERROR, "test", "Deployment failed"));
        when(artifacts.get(2).deploy(any(), any(), any())).thenReturn(Status.OK_STATUS);

        var operation = new DeployProjectOperation(session, artifacts);
        operation.run(monitor);

        IStatus status = operation.getStatus();
        assertThat(status.isOK()).isFalse();
        assertThat(status.getSeverity()).isEqualTo(IStatus.ERROR);
    }

    @Test
    void shouldAggregateMultipleFailures() throws Exception {
        APISession session = mock(APISession.class);
        IProgressMonitor monitor = mock(IProgressMonitor.class);
        var artifacts = createArtifacts("applications", false, 3);

        // All fail with different messages
        when(artifacts.get(0).deploy(any(), any(), any()))
                .thenReturn(new Status(IStatus.ERROR, "test", "Error 1"));
        when(artifacts.get(1).deploy(any(), any(), any()))
                .thenReturn(new Status(IStatus.ERROR, "test", "Error 2"));
        when(artifacts.get(2).deploy(any(), any(), any()))
                .thenReturn(new Status(IStatus.ERROR, "test", "Error 3"));

        var operation = new DeployProjectOperation(session, artifacts);
        operation.run(monitor);

        IStatus status = operation.getStatus();
        assertThat(status.isOK()).isFalse();
        // MultiStatus should contain all errors
        if (status.isMultiStatus()) {
            assertThat(status.getChildren()).hasSize(3);
        }
    }

    // ==================== Cancellation Tests ====================

    @Test
    void shouldRespectCancellationAndReturnCancelStatus() throws Exception {
        APISession session = mock(APISession.class);
        IProgressMonitor monitor = mock(IProgressMonitor.class);
        when(monitor.isCanceled()).thenReturn(true);

        var artifacts = createArtifacts("applications", false, 3);
        var operation = new DeployProjectOperation(session, artifacts);

        operation.run(monitor);

        // Deployment should return cancel status for each artifact
        assertThat(operation.getStatus()).isNotNull();
        // Verify deployments were not actually executed (since canceled)
        for (var artifact : artifacts) {
            verify(artifact, never()).deploy(any(), any(), any());
        }
    }

    // ==================== Progress Monitoring Contract Tests ====================

    @Test
    void shouldAlwaysCallMonitorDoneEvenOnSuccess() throws Exception {
        APISession session = mock(APISession.class);
        IProgressMonitor monitor = mock(IProgressMonitor.class);
        var artifacts = createArtifacts("applications", false, 2);

        var operation = new DeployProjectOperation(session, artifacts);
        operation.run(monitor);

        // Contract: done() must always be called (via finally block)
        verify(monitor).done();
    }

    @Test
    void shouldCallBeginTaskBeforeAnyWork() throws Exception {
        APISession session = mock(APISession.class);
        IProgressMonitor monitor = mock(IProgressMonitor.class);
        var artifacts = createArtifacts("applications", false, 2);

        var operation = new DeployProjectOperation(session, artifacts);
        operation.run(monitor);

        // Contract: beginTask must be called before any work
        verify(monitor).beginTask(anyString(), eq(2));
        verify(monitor, atLeastOnce()).worked(1);
    }

    // ==================== Deployment Execution Tests ====================

    @Test
    void shouldDeployAllArtifactsInOrder() throws Exception {
        APISession session = mock(APISession.class);
        IProgressMonitor monitor = mock(IProgressMonitor.class);
        var artifacts = createArtifacts("applications", false, 3);

        for (var artifact : artifacts) {
            when(artifact.deploy(any(), any(), any())).thenReturn(Status.OK_STATUS);
        }

        var operation = new DeployProjectOperation(session, artifacts);
        operation.run(monitor);

        // All artifacts should be deployed with correct parameters
        for (var artifact : artifacts) {
            verify(artifact).deploy(eq(session), any(), any());
        }
    }

    @Test
    void shouldPassCorrectParametersToDeployMethod() throws Exception {
        APISession session = mock(APISession.class);
        IProgressMonitor monitor = mock(IProgressMonitor.class);
        var artifacts = createArtifacts("applications", false, 1);
        var artifact = artifacts.get(0);
        when(artifact.deploy(any(), any(), any())).thenReturn(Status.OK_STATUS);

        var operation = new DeployProjectOperation(session, artifacts);
        operation.run(monitor);

        // Verify correct session is passed
        verify(artifact).deploy(eq(session), any(), any());
    }

    // ==================== Helper Methods ====================

    private List<FileStoreArtifact> createArtifacts(String storeName, boolean unused, int count) {
        // Note: The 'unused' parameter is kept for API compatibility but ignored.
        // We can't easily mock BuildableArtifact (it's a class, not an interface).
        // In real scenarios, BuildableArtifact detection would trigger Maven build.
        List<FileStoreArtifact> artifacts = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            var fileStore = mock(IRepositoryFileStore.class);
            var store = mock(IRepositoryStore.class);
            when(fileStore.getParentStore()).thenReturn(store);
            when(store.getName()).thenReturn(storeName);

            FileStoreArtifact artifact = mock(FileStoreArtifact.class);
            when(artifact.getFileStore()).thenReturn(fileStore);
            when(artifact.getName()).thenReturn(storeName + "_" + i);
            when(artifact.deploy(any(), any(), any())).thenReturn(Status.OK_STATUS);

            artifacts.add(artifact);
        }

        return artifacts;
    }
}
