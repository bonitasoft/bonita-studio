package org.bonitasoft.studio.designer.core.operation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.Arrays;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;

public class MigrateUIDOperationTest {

    MigrateUIDOperation operation = new MigrateUIDOperation();

    @Test
    public void should_return_error_status_when_migration_fails() throws Exception {
        String migrationResponse = "{\"status\": \"error\"}";
        try (InputStream is = new ByteArrayInputStream(migrationResponse.getBytes())) {
            HttpResponse<InputStream> response = mock(HttpResponse.class);
            when(response.body()).thenReturn(is);
            StatusAssert.assertThat(operation.parseMigrationResponse("", response)).isError();
        }
    }

    @Test
    public void should_return_error_status_when_trying_to_migrate_incompatible_artifact() throws Exception {
        String migrationResponse = "{\"status\": \"incompatible\"}";
        try (InputStream is = new ByteArrayInputStream(migrationResponse.getBytes())) {
            HttpResponse<InputStream> response = mock(HttpResponse.class);
            when(response.body()).thenReturn(is);
            StatusAssert.assertThat(operation.parseMigrationResponse("", response)).isError();
        }
    }

    @Test
    public void should_return_warning_status_when_migration_succeed_with_warning() throws Exception {
        String migrationResponse = "{\"status\": \"warning\"}";
        try (InputStream is = new ByteArrayInputStream(migrationResponse.getBytes())) {
            HttpResponse<InputStream> response = mock(HttpResponse.class);
            when(response.body()).thenReturn(is);
            StatusAssert.assertThat(operation.parseMigrationResponse("", response)).isWarning();
        }
    }

    @Test
    public void should_return_ok_status_when_migration_succeed() throws Exception {
        String migrationResponse = "{\"status\": \"success\"}";
        try (InputStream is = new ByteArrayInputStream(migrationResponse.getBytes())) {
            HttpResponse<InputStream> response = mock(HttpResponse.class);
            when(response.body()).thenReturn(is);
            StatusAssert.assertThat(operation.parseMigrationResponse("", response)).isOK();
        }
    }

    @Test
    public void should_return_ok_status_when_no_migration_is_performed() throws Exception {
        String migrationResponse = "{\"status\": \"none\"}";
        try (InputStream is = new ByteArrayInputStream(migrationResponse.getBytes())) {
            HttpResponse<InputStream> response = mock(HttpResponse.class);
            when(response.body()).thenReturn(is);
            StatusAssert.assertThat(operation.parseMigrationResponse("", response)).isOK();
        }
    }

    @Test
    public void should_migrate_all_file_store_one_by_one() throws Exception {
        NullProgressMonitor monitor = new NullProgressMonitor();
        PageDesignerURLFactory urlBuilder = mock(PageDesignerURLFactory.class);
        MigrateUIDOperation op = spy(new MigrateUIDOperation());
        doReturn(ValidationStatus.ok()).when(op).migratePage(any(), any(), any());

        WebPageRepositoryStore repositoryStore = mock(WebPageRepositoryStore.class);
        WebPageFileStore fStore1 = mock(WebPageFileStore.class);
        WebPageFileStore fStore2 = mock(WebPageFileStore.class);
        WebPageFileStore fStore3 = mock(WebPageFileStore.class);
        when(repositoryStore.getChildren()).thenReturn(Arrays.asList(fStore1, fStore2, fStore3));
        op.migrate(monitor, urlBuilder, repositoryStore);
        verify(op).migratePage(eq(urlBuilder), eq(fStore1), notNull());
        verify(op).migratePage(eq(urlBuilder), eq(fStore1), notNull());
        verify(op).migratePage(eq(urlBuilder), eq(fStore1), notNull());
    }

}
