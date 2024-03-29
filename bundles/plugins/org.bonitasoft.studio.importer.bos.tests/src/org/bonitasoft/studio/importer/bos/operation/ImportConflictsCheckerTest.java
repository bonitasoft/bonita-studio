package org.bonitasoft.studio.importer.bos.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.DefaultBosArchiveEntryHandler;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.junit.jupiter.api.Test;

class ImportConflictsCheckerTest {

    @Test
    void should_perform_conflict_analysis() throws Exception {
        final ImportConflictsChecker conflictsChecker = new ImportConflictsChecker(createRepository());
        final BosArchive bosArchive = newBosArchive(loadFile("/customer_support_2.0.bos"));

        final ImportArchiveModel archiveModel = conflictsChecker.checkConflicts(bosArchive, new NullProgressMonitor());
        final Optional<AbstractFolderModel> libStore = archiveModel.getStores().stream()
                .filter(folder -> Objects.equals(folder.getFolderName(), "lib")).findFirst();
        final Optional<AbstractFolderModel> diagramsStore = archiveModel.getStores().stream()
                .filter(folder -> Objects.equals(folder.getFolderName(), "diagrams")).findFirst();

        assertThat(isConflicting(libStore)).isFalse();
        assertThat(isConflicting(diagramsStore)).isTrue();
        assertThat(findFile(diagramsStore, "Customer Support-2.0.proc").isConflicting())
                .as("File with different checksum should be in conflict").isTrue();
        assertThat(findFile(diagramsStore, "Customer Support - Set Up-2.0.proc").isConflicting())
                .as("File with same checksum should not be in conflict").isFalse();
        assertThat(findFile(diagramsStore, "Customer Support - Set Up-2.0.proc").getImportAction())
                .as("File with same checksum should have KEEP import policy").isEqualTo(ImportAction.KEEP);
    }

    @Test
    void should_not_find_conflicts_on_databaseConnectors() throws Exception {
        final ImportConflictsChecker conflictsChecker = new ImportConflictsChecker(createRepository());
        DatabaseConnectorPropertiesRepositoryStore repositoryStore = createDatabaseConnectorPropertiesRepositoryStore();
        AbstractFolderModel folderModel = createAbstractFolderModelWithDatabaseConnectorProperties();
        try (InputStream inputstream = new FileInputStream(loadFile("/database-h2_bis.properties"))) {
            conflictsChecker.compareDatabaseConnectorProperties(repositoryStore,
                    folderModel, createBosArchiveWithdatabaseConnectorProperties(inputstream));
        }
        AbstractFileModel importedPropertiesFile = folderModel.getFiles().get(0);
        verify(importedPropertiesFile).setStatus(ConflictStatus.SAME_CONTENT);
    }

    private boolean isConflicting(Optional<AbstractFolderModel> store) throws Exception {
        return store.orElseThrow(() -> new Exception("store not found")).isConflicting();
    }

    private AbstractFileModel findFile(Optional<AbstractFolderModel> store, String fileName) throws Exception {
        return store.orElseThrow(() -> new Exception("store not found")).getFiles().stream()
                .filter(file -> Objects.equals(file.getFileName(), fileName)).findFirst()
                .orElseThrow(() -> new Exception(fileName + " diagram not found"));
    }

    private AbstractRepository createRepository() throws Exception {
        final IRepositoryFileStore fileStore = mock(IRepositoryFileStore.class);
        when(fileStore.getName()).thenReturn("Customer Support-2.0.proc");

        final List<IRepositoryFileStore> fileStoreList = new ArrayList<>();
        fileStoreList.add(fileStore);

        final IRepositoryStore diagramStore = createRepositoryStore("diagrams");
        when(diagramStore.getChildren()).thenReturn(fileStoreList);

        final IRepositoryStore appRessourcesStore = createRepositoryStore("application_resources");
        when(appRessourcesStore.getChildren()).thenReturn(new ArrayList<>());

        final IRepositoryStore libStore = createRepositoryStore("lib");
        when(libStore.getChildren()).thenReturn(new ArrayList<>());

        final List storeList = new ArrayList<>();
        storeList.add(diagramStore);
        storeList.add(appRessourcesStore);
        storeList.add(libStore);

        final AbstractRepository repo = mock(AbstractRepository.class);
        when(repo.getAllStores()).thenReturn(storeList);
        when(repo.getRepositoryStoreByName(anyString())).thenReturn(Optional.empty());
        when(repo.getRepositoryStoreByName("diagrams")).thenReturn(Optional.of(diagramStore));
        when(repo.getRepositoryStoreByName("application_resources")).thenReturn(Optional.of(appRessourcesStore));
        when(repo.getRepositoryStoreByName("lib")).thenReturn(Optional.of(libStore));
        when(repo.getProject()).thenReturn(mock(IProject.class));
        return repo;
    }

    private DatabaseConnectorPropertiesRepositoryStore createDatabaseConnectorPropertiesRepositoryStore() throws Exception {

        Properties properties = new Properties();
        File propertiesFile = loadFile("/database-h2.properties");
        try (InputStream is = new FileInputStream(propertiesFile)) {
            properties.load(is);
        }
        final DatabaseConnectorPropertiesFileStore fileStore = mock(DatabaseConnectorPropertiesFileStore.class);
        when(fileStore.getName()).thenReturn("database-h2.properties");
        when(fileStore.getContent()).thenReturn(properties);

        final DatabaseConnectorPropertiesRepositoryStore repo = mock(DatabaseConnectorPropertiesRepositoryStore.class);
        when(repo.getChildren()).thenReturn(Arrays.asList(fileStore));
        return repo;
    }

    private AbstractFolderModel createAbstractFolderModelWithDatabaseConnectorProperties() {

        AbstractFileModel fileModel = mock(AbstractFileModel.class);
        when(fileModel.getPath()).thenReturn("path");
        when(fileModel.getFileName()).thenReturn("database-h2.properties");

        AbstractFolderModel folderModel = mock(AbstractFolderModel.class);
        when(folderModel.getFiles()).thenReturn(Arrays.asList(fileModel));

        return folderModel;
    }

    private BosArchive createBosArchiveWithdatabaseConnectorProperties(InputStream inputStream) throws Exception {
        ZipEntry entry = mock(ZipEntry.class);
        ZipFile zipFile = mock(ZipFile.class);
        when(zipFile.getInputStream(any())).thenReturn(inputStream);

        BosArchive bosArchive = mock(BosArchive.class);
        when(bosArchive.getEntry("path")).thenReturn(entry);
        when(bosArchive.getZipFile()).thenReturn(zipFile);

        return bosArchive;
    }

    private BosArchive newBosArchive(File archiveFile) throws ZipException, IOException {
        final BosArchive bosArchive = spy(new BosArchive(archiveFile));
        doReturn(true).when(bosArchive).canImport(notNull());
        var entryHandler = spy(new DefaultBosArchiveEntryHandler(archiveFile));
        doReturn(entryHandler).when(bosArchive).getBosArchiveEntryHandler();
        doReturn(Status.OK_STATUS).when(entryHandler).validateFile(any(), any());
        return bosArchive;
    }

    private IRepositoryStore createRepositoryStore(String name) throws IOException {
        final IRepositoryStore store = mock(IRepositoryStore.class);
        final IFolder folder = createIFolder(name);

        when(store.getName()).thenReturn(name);
        when(store.getResource()).thenReturn(folder);
        return store;
    }

    private IFolder createIFolder(String name) throws IOException {
        final IFolder folder = mock(IFolder.class);
        when(folder.getName()).thenReturn(name);
        final IPath path = createIPath(name);
        when(folder.getLocation()).thenReturn(path);
        return folder;
    }

    private IPath createIPath(String name) throws IOException {
        final IPath path = mock(IPath.class);
        final File file = createFile(name);
        when(path.toFile()).thenReturn(file);
        return path;
    }

    private File createFile(String name) throws IOException {
        if (Objects.equals(name, "diagrams")) {
            return loadFile("/folder");
        }
        return loadFile("/emptyFolder");
    }

    private File loadFile(String filePath) throws IOException {
        return new File(URLDecoder.decode(FileLocator.toFileURL(ImportConflictsCheckerTest.class.getResource(filePath)).getFile(), "UTF-8"));
    }
}
