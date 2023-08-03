package org.bonitasoft.studio.application.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteHandlerTest {

    DeleteHandler deleteHandler;

    @BeforeEach
    public void init() {
        deleteHandler = new DeleteHandler();
    }

    @Test
    void should_be_enable_if_file_store_can_be_deleted() {
        IResource resource = mock(IResource.class);
        IAdaptable selectionContent = mock(IAdaptable.class);
        when(selectionContent.getAdapter(IResource.class)).thenReturn(resource);
        IStructuredSelection selection = new StructuredSelection(selectionContent);

        IRepositoryFileStore fileStore = mock(IRepositoryFileStore.class);
        AbstractRepository currentRepository = mock(AbstractRepository.class);
        when(currentRepository.getFileStore(resource)).thenReturn(fileStore);

        when(fileStore.canBeDeleted()).thenReturn(true);
        assertThat(deleteHandler.selectionCanBeDeleted(selection, currentRepository)).isTrue();

        when(fileStore.canBeDeleted()).thenReturn(false);
        assertThat(deleteHandler.selectionCanBeDeleted(selection, currentRepository)).isFalse();
    }

    @Test
    void should_be_disable_if_selection_is_current_repository_project() {
        IProject resource = mock(IProject.class);
        IAdaptable selectionContent = mock(IAdaptable.class);
        when(selectionContent.getAdapter(IResource.class)).thenReturn(resource);
        IStructuredSelection selection = new StructuredSelection(selectionContent);

        AbstractRepository currentRepository = mock(AbstractRepository.class);

        when(currentRepository.getProject()).thenReturn(resource);
        assertThat(deleteHandler.selectionCanBeDeleted(selection, currentRepository)).isFalse();

        IProject currentRepoProject = mock(IProject.class);
        when(currentRepository.getProject()).thenReturn(currentRepoProject);
        assertThat(deleteHandler.selectionCanBeDeleted(selection, currentRepository)).isTrue();
    }

    @Test
    void should_be_disable_if_selection_is_a_repository_store() {
        IFolder resource = mock(IFolder.class);
        IAdaptable selectionContent = mock(IAdaptable.class);
        when(selectionContent.getAdapter(IResource.class)).thenReturn(resource);
        IStructuredSelection selection = new StructuredSelection(selectionContent);

        AbstractRepository currentRepository = mock(AbstractRepository.class);
        IRepositoryStore repositoryStore = mock(IRepositoryStore.class);

        when(currentRepository.getRepositoryStore(resource)).thenReturn(repositoryStore);
        assertThat(deleteHandler.selectionCanBeDeleted(selection, currentRepository)).isFalse();

        when(currentRepository.getRepositoryStore(resource)).thenReturn(null);
        assertThat(deleteHandler.selectionCanBeDeleted(selection, currentRepository)).isTrue();
    }

}
