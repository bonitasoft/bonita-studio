package org.bonitasoft.studio.la.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ExportApplicationRunnableTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void should_export_applications() throws Exception {
        final File folder = temporaryFolder.newFolder();

        final ApplicationFileStore appication1 = mock(ApplicationFileStore.class);
        when(appication1.getName()).thenReturn("app1");
        final ApplicationFileStore appication2 = mock(ApplicationFileStore.class);
        when(appication2.getName()).thenReturn("app2");

        final List<IRepositoryFileStore> list = new ArrayList<>();
        list.add(appication1);
        list.add(appication2);

        final ExportApplicationRunnable operation = new ExportApplicationRunnable(folder.getPath(), list);
        operation.run(new NullProgressMonitor());

        verify(appication1).export(folder.getPath());
        verify(appication2).export(folder.getPath());
    }
}
