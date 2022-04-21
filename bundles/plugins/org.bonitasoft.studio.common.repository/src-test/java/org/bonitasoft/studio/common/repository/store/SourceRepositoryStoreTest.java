/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.store;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.junit.Test;

public class SourceRepositoryStoreTest {

    @Test
    public void should_not_use_super_implementation_if_there_is_no_compatible_extensions() throws Exception {
        final SourceRepositoryStore sourceRepositoryStore = mock(SourceRepositoryStore.class);
        doReturn(Collections.emptySet()).when(sourceRepositoryStore).getCompatibleExtensions();
        doCallRealMethod().when(sourceRepositoryStore).getChildren();
        final IFolder folder = mock(IFolder.class);
        when(folder.members()).thenReturn(new IResource[] {});
        doReturn(folder).when(sourceRepositoryStore).getResource();

        sourceRepositoryStore.getChildren();

        verify(sourceRepositoryStore).getResource();
    }

    @Test
    public void should_use_super_implementation_if_there_is_compatible_extensions() throws Exception {
        final SourceRepositoryStore sourceRepositoryStore = mock(SourceRepositoryStore.class);
        doReturn(Collections.singleton("groovy")).when(sourceRepositoryStore).getCompatibleExtensions();
        doCallRealMethod().when(sourceRepositoryStore).getChildren();
        when(sourceRepositoryStore.toFileStore()).thenReturn(resource -> null);

        sourceRepositoryStore.getChildren();

        verify(sourceRepositoryStore, never()).getResource();
    }

}
