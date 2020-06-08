/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.importer.bos.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.notNull;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.ZipException;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BosArchiveTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_not_fail_when_bosArchive_is_valid() throws Exception {
        final BosArchive bosArchive = newBosArchive(loadArchiveFile("/customer_support_2.0.bos"));

        final IStatus status = bosArchive.validate();

        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_fail_when_archiveFile_has_not_a_bos_archive_structure() throws Exception {
        final BosArchive bosArchive = newBosArchive(loadArchiveFile("/invalidArchive.bos"));

        final IStatus status = bosArchive.validate();

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_fail_when_archiveFile_version_is_incompatible() throws Exception {
        final BosArchive bosArchive = newBosArchive(loadArchiveFile("/archiveWithIncompatibleManifest.bos"));
        doReturn(false).when(bosArchive).canImport("600.1.1");
        
        final IStatus status = bosArchive.validate();

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_validate_beta_manifest() throws Exception {
        final BosArchive bosArchive = newBosArchive(loadArchiveFile("/archiveWithBetaManifest.bos"));

        final IStatus status = bosArchive.validate();

        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_build_an_importModel_from_bosArchive() throws Exception {
        final BosArchive bosArchive = newBosArchive(loadArchiveFile("/customer_support_2.0.bos"));

        final ImportArchiveModel importArchiveModel = bosArchive
                .toImportModel(createRepository(), new NullProgressMonitor());

        assertThat(importArchiveModel).isNotNull();
        assertThat(
                importArchiveModel.getStores().stream().map(folder -> folder.getFolderName()).collect(Collectors.toList()))
                        .contains("diagrams", "lib", "application_resources");
        final Optional<ImportStoreModel> diagramsStore = importArchiveModel.getStores().stream()
                .filter(folder -> Objects.equals(folder.getFolderName(), "diagrams")).findFirst();

        assertThat(diagramsStore.orElseThrow(() -> new Exception("diagram store not found")).getFiles().stream()
                .map(file -> file.getPath()).collect(Collectors.toList()))
                        .contains("CustomerSupportM2/diagrams/Customer Support-2.0.proc",
                                "CustomerSupportM2/diagrams/Customer Support - Set Up-2.0.proc");

        assertThat(diagramsStore.orElseThrow(() -> new Exception("diagram store not found")).getFiles().stream()
                .map(file -> file instanceof ImportFileStoreModel).collect(Collectors.toList()))
                        .contains(true, true);

        final Optional<ImportStoreModel> appResources = importArchiveModel.getStores().stream()
                .filter(folder -> Objects.equals(folder.getFolderName(), "application_resources")).findFirst();
        assertThat(
                appResources.orElseThrow(() -> new Exception("application_resources store not found")).getFolders().stream()
                        .map(folder -> folder.getFolderName()).collect(Collectors.toList()))
                                .contains("_B3L7AOlEEeK9W8AEREPbVg");
    }

    private BosArchive newBosArchive(File archiveFile) throws ZipException, IOException {
        final BosArchive bosArchive = spy(new BosArchive(archiveFile));
        doReturn(true).when(bosArchive).canImport(notNull(String.class));
        doReturn(Arrays.asList(createRepositoryStore("application_resources"), createRepositoryStore("diagrams"),
                createRepositoryStore("lib"))).when(bosArchive).allRepositoryStores();
        doReturn(Status.OK_STATUS).when(bosArchive).validateFile(any(), any());
        return bosArchive;
    }

    private IRepositoryStore<IRepositoryFileStore> createRepositoryStore(String name) {
        final IRepositoryStore<IRepositoryFileStore> store = mock(IRepositoryStore.class);
        when(store.getName()).thenReturn(name);
        return store;
    }

    private File loadArchiveFile(String filePath) throws UnsupportedEncodingException {
        return new File(URLDecoder.decode(BosArchiveTest.class.getResource(filePath).getFile(),"UTF-8"));
    }

    private Repository createRepository() throws CoreException {

        final IRepositoryFileStore fileStore = mock(IRepositoryFileStore.class);
        when(fileStore.getName()).thenReturn("Customer Support-2.0.proc");

        final List<IRepositoryFileStore> fileStoreList = new ArrayList<>();
        fileStoreList.add(fileStore);

        final IRepositoryStore<IRepositoryFileStore> diagramStore = mock(IRepositoryStore.class);
        when(diagramStore.getName()).thenReturn("diagrams");
        when(diagramStore.getChildren()).thenReturn(fileStoreList);

        final IRepositoryStore<IRepositoryFileStore> appRessourcesStore = mock(IRepositoryStore.class);
        when(appRessourcesStore.getName()).thenReturn("application_resources");
        when(appRessourcesStore.getChildren()).thenReturn(new ArrayList<IRepositoryFileStore>());

        final IRepositoryStore<IRepositoryFileStore> libStore = mock(IRepositoryStore.class);
        when(libStore.getName()).thenReturn("lib");
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

}
