package org.bonitasoft.studio.importer.bos.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.ZipException;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.BosArchiveTest;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.model.ImportStoreModel;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Test;

public class ImportConflictsCheckerTest {

    @Test
    public void should_perform_conflict_analysis() throws Exception {
        final ImportConflictsChecker conflictsChecker = new ImportConflictsChecker(createRepository());
        final BosArchive bosArchive = newBosArchive(loadFile("/customer_support_2.0.bos"));

        final ImportArchiveModel archiveModel = conflictsChecker.checkConflicts(bosArchive, mock(IProgressMonitor.class));
        final Optional<ImportStoreModel> libStore = archiveModel.getStores().stream()
                .filter(folder -> Objects.equals(folder.getFolderName(), "lib")).findFirst();
        final Optional<ImportStoreModel> diagramsStore = archiveModel.getStores().stream()
                .filter(folder -> Objects.equals(folder.getFolderName(), "diagrams")).findFirst();

        assertThat(isConflicting(libStore)).isFalse();
        assertThat(isConflicting(diagramsStore)).isTrue();
        assertThat(isConflicting(diagramsStore, "Customer Support-2.0.proc")).isTrue();
        assertThat(isConflicting(diagramsStore, "Customer Support - Set Up-2.0.proc")).isFalse();
    }

    private boolean isConflicting(Optional<ImportStoreModel> store) throws Exception {
        return store.orElseThrow(() -> new Exception("store not found")).isConflicting();
    }

    private boolean isConflicting(Optional<ImportStoreModel> store, String fileName) throws Exception {
        return store.orElseThrow(() -> new Exception("store not found")).getFiles().stream()
                .filter(file -> Objects.equals(file.getFileName(), fileName)).findFirst()
                .orElseThrow(() -> new Exception(fileName + " diagram not found")).isConflicting();
    }

    private Repository createRepository() throws CoreException {
        final IRepositoryFileStore fileStore = mock(IRepositoryFileStore.class);
        when(fileStore.getName()).thenReturn("Customer Support-2.0.proc");

        final List<IRepositoryFileStore> fileStoreList = new ArrayList<>();
        fileStoreList.add(fileStore);

        final IRepositoryStore<IRepositoryFileStore> diagramStore = createRepositoryStore("diagrams");
        when(diagramStore.getChildren()).thenReturn(fileStoreList);

        final IRepositoryStore<IRepositoryFileStore> appRessourcesStore = createRepositoryStore("application_resources");
        when(appRessourcesStore.getChildren()).thenReturn(new ArrayList<IRepositoryFileStore>());

        final IRepositoryStore<IRepositoryFileStore> libStore = createRepositoryStore("lib");
        when(libStore.getChildren()).thenReturn(new ArrayList<IRepositoryFileStore>());

        final List<IRepositoryStore<? extends IRepositoryFileStore>> storeList = new ArrayList<>();
        storeList.add(diagramStore);
        storeList.add(appRessourcesStore);
        storeList.add(libStore);

        final Repository repo = mock(Repository.class);
        when(repo.getAllStores()).thenReturn(storeList);
        when(repo.getRepositoryStoreByName(anyString())).thenReturn(Optional.empty());
        when(repo.getRepositoryStoreByName("diagrams")).thenReturn(Optional.of(diagramStore));
        when(repo.getRepositoryStoreByName("application_resources")).thenReturn(Optional.of(appRessourcesStore));
        when(repo.getRepositoryStoreByName("lib")).thenReturn(Optional.of(libStore));

        return repo;
    }

    private BosArchive newBosArchive(File archiveFile) throws ZipException, IOException {
        final BosArchive bosArchive = spy(new BosArchive(archiveFile));
        doReturn(Arrays.asList(createRepositoryStore("application_resources"), createRepositoryStore("diagrams"),
                createRepositoryStore("lib"))).when(bosArchive).allRepositoryStores();

        return bosArchive;
    }

    private IRepositoryStore<IRepositoryFileStore> createRepositoryStore(String name) {
        final IRepositoryStore<IRepositoryFileStore> store = mock(IRepositoryStore.class);
        final IFolder folder = createIFolder(name);

        when(store.getName()).thenReturn(name);
        when(store.getResource()).thenReturn(folder);
        return store;
    }

    private IFolder createIFolder(String name) {
        final IFolder folder = mock(IFolder.class);
        when(folder.getName()).thenReturn(name);
        final IPath path = createIPath(name);
        when(folder.getLocation()).thenReturn(path);
        return folder;
    }

    private IPath createIPath(String name) {
        final IPath path = mock(IPath.class);
        final File file = createFile(name);
        when(path.toFile()).thenReturn(file);
        return path;
    }

    private File createFile(String name) {
        if (Objects.equals(name, "diagrams")) {
            return loadFile("/folder");
        }
        return loadFile("/emptyFolder");
    }

    private File loadFile(String filePath) {
        return new File(BosArchiveTest.class.getResource(filePath).getFile());
    }
}
