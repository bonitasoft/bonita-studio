package org.bonitasoft.studio.importer.bos.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipException;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.importer.bos.handler.SwitchRepositoryStrategy;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.operation.ImportConflictsChecker;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Composite;
import org.junit.Rule;
import org.junit.Test;

public class ImportBosArchivePageTest {

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    @Test
    public void should_update_archive_model_correctly() throws Exception {
        final ImportBosArchivePage supplier = new ImportBosArchivePage(
                getRepositoryAccessor(), new SwitchRepositoryStrategy(getRepositoryAccessor()), null, null);
        final Composite parent = displayRule.createComposite();
        final IWizardContainer container = displayRule.wizardWithContainer().getContainer();

        supplier.createControl(parent, container, new DataBindingContext());

        final String path = URLDecoder
                .decode(ImportBosArchivePageTest.class.getResource("/customer_support_2.0.bos")
                        .getPath(), "UTF-8");
        supplier.setTextWidgetText(path);

        final ImportConflictsChecker conflictsChecker = new ImportConflictsChecker(createRepository("default"));
        final BosArchive bosArchive = newBosArchive(loadFile("/customer_support_2.0.bos"));
        final ImportArchiveModel archiveModel = conflictsChecker.checkConflicts(bosArchive, mock(IProgressMonitor.class));
        supplier.setArchiveModel(archiveModel);

        boolean isConflicting = archiveModel.getStores().stream().anyMatch(store -> store.isConflicting());
        assertThat(isConflicting).isTrue();
        supplier.updateArchiveModel("newRepo", mock(IProgressMonitor.class));
        isConflicting = supplier.getArchiveModel().getStores().stream().anyMatch(store -> store.isConflicting());
        assertThat(isConflicting).isFalse();
        supplier.updateArchiveModel("default", mock(IProgressMonitor.class));
        isConflicting = supplier.getArchiveModel().getStores().stream().anyMatch(store -> store.isConflicting());
        assertThat(isConflicting).isTrue();
    }

    private RepositoryAccessor getRepositoryAccessor() throws Exception {
        final RepositoryAccessor accessor = mock(RepositoryAccessor.class);
        final AbstractRepository defaultRepo = createRepository("default");
        when(accessor.getCurrentRepository()).thenReturn(defaultRepo);
        when(accessor.getRepository("default")).thenReturn(defaultRepo);

        return accessor;
    }

    private AbstractRepository createRepository(String name) throws Exception {
        final IRepositoryFileStore fileStore = mock(IRepositoryFileStore.class);
        when(fileStore.getName()).thenReturn("Customer Support-2.0.proc");

        final List fileStoreList = new ArrayList<>();
        fileStoreList.add(fileStore);

        final IRepositoryStore diagramStore = createRepositoryStore("diagrams");
        when(diagramStore.getChildren()).thenReturn(fileStoreList);

        final IRepositoryStore appRessourcesStore = createRepositoryStore("application_resources");
        when(appRessourcesStore.getChildren()).thenReturn(new ArrayList<IRepositoryFileStore>());

        final IRepositoryStore libStore = createRepositoryStore("lib");
        when(libStore.getChildren()).thenReturn(new ArrayList<IRepositoryFileStore>());

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
        when(repo.getName()).thenReturn(name);
        return repo;
    }

    private BosArchive newBosArchive(File archiveFile) throws ZipException, IOException {
        final BosArchive bosArchive = spy(new BosArchive(archiveFile));
        doReturn(Arrays.asList(createRepositoryStore("application_resources"), createRepositoryStore("diagrams"),
                createRepositoryStore("lib"))).when(bosArchive).allRepositoryStores();
        doReturn(Status.OK_STATUS).when(bosArchive).validateFile(any(), any());
        return bosArchive;
    }

    private IRepositoryStore createRepositoryStore(String name) throws UnsupportedEncodingException {
        final IRepositoryStore store = mock(IRepositoryStore.class);
        final IFolder folder = createIFolder(name);

        when(store.getName()).thenReturn(name);
        when(store.getResource()).thenReturn(folder);
        return store;
    }

    private IFolder createIFolder(String name) throws UnsupportedEncodingException {
        final IFolder folder = mock(IFolder.class);
        when(folder.getName()).thenReturn(name);
        final IPath path = createIPath(name);
        when(folder.getLocation()).thenReturn(path);
        return folder;
    }

    private IPath createIPath(String name) throws UnsupportedEncodingException {
        final IPath path = mock(IPath.class);
        final File file = createFile(name);
        when(path.toFile()).thenReturn(file);
        return path;
    }

    private File createFile(String name) throws UnsupportedEncodingException {
        return loadFile("/folder");
    }

    private File loadFile(String filePath) throws UnsupportedEncodingException {
        return new File(URLDecoder
                .decode(ImportBosArchivePageTest.class.getResource(filePath).getFile(), "UTF-8"));
    }

}
