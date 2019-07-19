package org.bonitasoft.studio.application.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IBuildable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.junit.Before;
import org.junit.Test;

public class DeployProjectOperationTest {

    RepositoryAccessor repositoryAccessor;

    @Before
    public void init() {
        repositoryAccessor = mock(RepositoryAccessor.class);
        Repository repository = initRepository();
        when(repositoryAccessor.getCurrentRepository()).thenReturn(repository);
    }

    private Repository initRepository() {
        Repository repository = mock(Repository.class);
        List<IRepositoryStore<? extends IRepositoryFileStore>> stores = initRepositoryStores();
        when(repository.getAllStores()).thenReturn(stores);
        return repository;
    }

    private List<IRepositoryStore<? extends IRepositoryFileStore>> initRepositoryStores() {
        IRepositoryStore<BuildableFileStore> buildableRepositoryStore = mock(IRepositoryStore.class);
        BuildableFileStore buildableFileStore1 = mock(BuildableFileStore.class);
        when(buildableFileStore1.getName()).thenReturn("buildableFileStore1");
        BuildableFileStore buildableFileStore2 = mock(BuildableFileStore.class);
        when(buildableFileStore2.getName()).thenReturn("buildableFileStore2");
        when(buildableRepositoryStore.getChildren()).thenReturn(Arrays.asList(buildableFileStore1, buildableFileStore2));

        IRepositoryStore<NotBuildableFileStore> NotBuildableRepositoryStore = mock(IRepositoryStore.class);
        NotBuildableFileStore notBuildableFileStore = mock(NotBuildableFileStore.class);
        when(notBuildableFileStore.getName()).thenReturn("notBuildableFileStore");
        when(NotBuildableRepositoryStore.getChildren()).thenReturn(Arrays.asList(notBuildableFileStore));

        return Arrays.asList(buildableRepositoryStore, NotBuildableRepositoryStore);
    }

    @Test
    public void should_retrieve_file_stores_to_build() {
        List<IRepositoryFileStore> fileStoresToBuild = new DeployProjectOperation(repositoryAccessor)
                .retrieveFileStoresToBuild();

        assertThat(fileStoresToBuild).hasSize(2);
        assertThat(fileStoresToBuild).extracting(IRepositoryFileStore::getName)
                .containsExactlyInAnyOrder("buildableFileStore1", "buildableFileStore2");
    }

}

abstract class BuildableFileStore implements IBuildable, IRepositoryFileStore {
}

abstract class NotBuildableFileStore implements IRepositoryFileStore {
}
