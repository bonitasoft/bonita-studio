package org.bonitasoft.studio.importer.bos.operation;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Collections;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.status.ImportBosArchiveStatusBuilder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ImportBosArchiveOperationTest {

    private ImportBosArchiveOperation operationUnserTest;

    @Mock
    private Repository repostioty;

    private File archiveFile;

    @Mock
    private IProgressMonitor monitor;
    @Mock
    private ParseBosArchiveOperation parseOpeation;

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private WebPageRepositoryStore webPageRepositoryStore;

    @Before
    public void setUp() throws Exception {
        operationUnserTest = spy(new ImportBosArchiveOperation(repositoryAccessor));
        archiveFile = new File(ImportBosArchiveOperationTest.class.getResource("/customer_support_2.0.bos").getFile());
        when(parseOpeation.getImportArchiveModel()).thenReturn(mock(ImportArchiveModel.class));
        doReturn(parseOpeation).when(operationUnserTest).newParseBosOperation(Matchers.any(File.class),
                Matchers.any(Repository.class));
        doReturn(Collections.emptyList()).when(operationUnserTest).getValidators();
        doReturn(webPageRepositoryStore).when(repositoryAccessor).getRepositoryStore(WebPageRepositoryStore.class);
    }

    @Test
    public void should_refresh_repository_at_the_end_of_import() throws Exception {
        operationUnserTest.setCurrentRepository(repostioty);
        operationUnserTest.setArchiveFile(archiveFile.getAbsolutePath());
        operationUnserTest.run(monitor);
        verify(repostioty).build(monitor);
    }

    @Test
    public void should_validate_all_imported_processes_at_the_end_of_import() throws Exception {
        ImportBosArchiveStatusBuilder importBosArchiveStatusBuilder = new ImportBosArchiveStatusBuilder();
        when(operationUnserTest.createStatusBuilder()).thenReturn(importBosArchiveStatusBuilder);
        operationUnserTest.setCurrentRepository(repostioty);
        operationUnserTest.setArchiveFile(archiveFile.getAbsolutePath());
        operationUnserTest.run(monitor);
        verify(operationUnserTest).validateAllAfterImport(monitor, importBosArchiveStatusBuilder);
    }
}
