package org.bonitasoft.studio.application.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

public class DeployProjectHandlerTest {

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
        IRepositoryStore<DeployableFileStore> deployableRepositoryStore = mock(IRepositoryStore.class);
        DeployableFileStore deployableFileStore1 = mock(DeployableFileStore.class);
        when(deployableFileStore1.getName()).thenReturn("deployableFileStore1");
        DeployableFileStore deployableFileStore2 = mock(DeployableFileStore.class);
        when(deployableFileStore2.getName()).thenReturn("deployableFileStore2");
        when(deployableRepositoryStore.getChildren()).thenReturn(Arrays.asList(deployableFileStore1, deployableFileStore2));

        IRepositoryStore<NotDeployableFileStore> NotBuildableRepositoryStore = mock(IRepositoryStore.class);
        NotDeployableFileStore notDeployableFileStore = mock(NotDeployableFileStore.class);
        when(notDeployableFileStore.getName()).thenReturn("notDeployableeFileStore");
        when(NotBuildableRepositoryStore.getChildren()).thenReturn(Arrays.asList(notDeployableFileStore));

        return Arrays.asList(deployableRepositoryStore, NotBuildableRepositoryStore);
    }

    @Test
    public void should_retrieve_deployable_artifacts() {
        Collection<IRepositoryFileStore> fileStoresToBuild = new DeployProjectHandler()
                .retrieveArtifactsToDeploy(mock(Shell.class), repositoryAccessor);
        assertThat(fileStoresToBuild).hasSize(2);
        assertThat(fileStoresToBuild).extracting(IRepositoryFileStore::getName)
                .containsExactlyInAnyOrder("deployableFileStore1", "deployableFileStore2");
    }

}

abstract class DeployableFileStore implements IDeployable, IRepositoryFileStore {
}

abstract class NotDeployableFileStore implements IRepositoryFileStore {
}
