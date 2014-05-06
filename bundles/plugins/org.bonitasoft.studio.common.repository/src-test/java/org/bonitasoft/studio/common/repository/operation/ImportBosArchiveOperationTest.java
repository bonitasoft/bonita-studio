package org.bonitasoft.studio.common.repository.operation;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.eclipse.core.resources.IContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ImportBosArchiveOperationTest {

    @Spy
    private ImportBosArchiveOperation operationUnserTest;

    @Mock
    private IRepository repostioty;

    private File archiveFile;

    @Mock
    private IContainer container;

    @Before
    public void setUp() throws Exception {
        archiveFile = new File(ImportBosArchiveOperationTest.class.getResource("/customer_support_2.0.bos").getFile());
        doNothing().when(operationUnserTest).cleanTmpProject();
        doReturn(container).when(operationUnserTest).createTempProject(archiveFile, Repository.NULL_PROGRESS_MONITOR);
        doReturn(container).when(operationUnserTest).getRootContainer(Mockito.any(IContainer.class), Mockito.any(Map.class));
        doReturn(new Properties()).when(operationUnserTest).getManifestInfo(container);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_refresh_repository_at_the_end_of_import() throws Exception {
        operationUnserTest.setCurrentRepository(repostioty);
        operationUnserTest.setArchiveFile(archiveFile.getAbsolutePath());
        operationUnserTest.run(Repository.NULL_PROGRESS_MONITOR);
        verify(repostioty).refresh(Repository.NULL_PROGRESS_MONITOR);
    }
}
