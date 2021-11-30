/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.designer.core.resources;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class RepositoryNotifierTest {

    @Mock
    private IRepository repository;
    private RepositoryNotifier repositoryNotifier;
    @Mock
    private IRepositoryFileStore fileStore;
    @Mock
    private IRepositoryStore<?> store;
    @Mock
    private IResource anExistingResource;
    @Mock
    private IResource aMissingResource;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        repositoryNotifier = new RepositoryNotifier(repository);
        doReturn(store).when(fileStore).getParentStore();
        doReturn(anExistingResource).when(fileStore).getResource();
        doReturn(true).when(anExistingResource).exists();
        doReturn(false).when(aMissingResource).exists();
        doReturn(Path.fromOSString("a/missing/path")).when(aMissingResource).getLocation();
    }

    @Test
    public void should_notifyFileStoreEvent_when_dispatching_a_preOpen_event() throws Exception {
        repositoryNotifier.dispatch(WorkspaceAPIEvent.PRE_OPEN, fileStore);

        verify(store).refresh();
        verify(repository).handleFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_OPEN, fileStore));
    }

    @Test
    public void should_notifyFileStoreEvent_when_dispatching_a_postClose_event() throws Exception {
        repositoryNotifier.dispatch(WorkspaceAPIEvent.POST_CLOSE, fileStore);

        verify(repository).handleFileStoreEvent(new FileStoreChangeEvent(EventType.POST_CLOSE, fileStore));
    }

    @Test
    public void should_notifyFileStoreEvent_when_dispatching_a_postDelete_event() throws Exception {
        repositoryNotifier.dispatch(WorkspaceAPIEvent.POST_DELETE, fileStore);

        verify(store).refresh();
        verify(repository).handleFileStoreEvent(new FileStoreChangeEvent(EventType.POST_DELETE, fileStore));
    }

    @Test
    public void should_delete_fileStore_when_dispatching_a_delete_event() throws Exception {
        repositoryNotifier.dispatch(WorkspaceAPIEvent.DELETE, fileStore);

        verify(fileStore).delete();
    }

    @Test
    public void should_notifyFileStoreEvent_when_dispatching_a_preImport_event() throws Exception {
        repositoryNotifier.dispatch(WorkspaceAPIEvent.PRE_IMPORT, fileStore);

        verify(repository).handleFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_IMPORT, null));
    }

    @Test
    public void should_notifyFileStoreEvent_when_dispatching_a_postImport_event() throws Exception {
        repositoryNotifier.dispatch(WorkspaceAPIEvent.POST_IMPORT, fileStore);

        verify(repository).handleFileStoreEvent(new FileStoreChangeEvent(EventType.POST_IMPORT, null));
    }

    @Test(expected = IllegalStateException.class)
    public void should_throw_an_IllegalArgumentException_if_event_is_not_supported() throws Exception {
        repositoryNotifier.dispatch(WorkspaceAPIEvent.POST_OPEN, fileStore);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_an_ResourceNotFoundException_if_fileStore_resource_is_missing() throws Exception {
        doReturn(aMissingResource).when(fileStore).getResource();

        repositoryNotifier.dispatch(WorkspaceAPIEvent.PRE_OPEN, fileStore);
    }
}
