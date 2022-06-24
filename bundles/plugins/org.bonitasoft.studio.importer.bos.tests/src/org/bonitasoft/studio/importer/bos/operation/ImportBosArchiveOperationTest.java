package org.bonitasoft.studio.importer.bos.operation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Collections;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.status.ImportBosArchiveStatusBuilder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ImportBosArchiveOperationTest {

    private ImportBosArchiveOperation operationUnserTest;

    @Mock
    private AbstractRepository repostiory;

    private File archiveFile;

    @Mock
    private IProgressMonitor monitor;
    @Mock
    private ParseBosArchiveOperation parseOpeation;

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private WebPageRepositoryStore webPageRepositoryStore;

    @Mock
    private DiagramRepositoryStore diagramStore;

    @Before
    public void setUp() throws Exception {
        when(repositoryAccessor.getCurrentRepository()).thenReturn(Optional.of(repostiory));
        operationUnserTest = spy(new ImportBosArchiveOperation(repositoryAccessor));
        doNothing().when(operationUnserTest).migrateUID(any());
        doReturn(Collections.emptySet()).when(operationUnserTest).doMigrateToMavenDependencies(any(ImportArchiveModel.class), any(IProgressMonitor.class));
        doReturn(null).when(operationUnserTest).existingMavenModel(any());
        archiveFile = new File(ImportBosArchiveOperationTest.class.getResource("/customer_support_2.0.bos").getFile());
        when(parseOpeation.getImportArchiveModel()).thenReturn(mock(ImportArchiveModel.class));
        doReturn(parseOpeation).when(operationUnserTest).newParseBosOperation(Mockito.any(),
                Mockito.any());
        doReturn(Collections.emptyList()).when(operationUnserTest).getValidators();
        when(repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class)).thenReturn(diagramStore);
    }

    @Test
    public void should_compute_processes_at_the_start_of_the_import() throws Exception {
        operationUnserTest.setCurrentRepository(repostiory);
        operationUnserTest.setArchiveFile(archiveFile.getAbsolutePath());
        operationUnserTest.run(monitor);
        InOrder inOrder = inOrder(diagramStore);
        inOrder.verify(diagramStore).computeProcesses(monitor);
        inOrder.verify(diagramStore).resetComputedProcesses();
    }

    @Test
    public void should_refresh_repository_at_the_end_of_import() throws Exception {
        operationUnserTest.setCurrentRepository(repostiory);
        operationUnserTest.setArchiveFile(archiveFile.getAbsolutePath());
        operationUnserTest.run(monitor);
        verify(operationUnserTest).migrateUID(monitor);
    }

    @Test
    public void should_validate_all_imported_processes_at_the_end_of_import() throws Exception {
        ImportBosArchiveStatusBuilder importBosArchiveStatusBuilder = new ImportBosArchiveStatusBuilder();
        when(operationUnserTest.createStatusBuilder()).thenReturn(importBosArchiveStatusBuilder);
        operationUnserTest.setCurrentRepository(repostiory);
        operationUnserTest.setArchiveFile(archiveFile.getAbsolutePath());
        operationUnserTest.run(monitor);
        verify(operationUnserTest).validateAllAfterImport(monitor, importBosArchiveStatusBuilder);
        verify(operationUnserTest).migrateUID(monitor);
    }
}
