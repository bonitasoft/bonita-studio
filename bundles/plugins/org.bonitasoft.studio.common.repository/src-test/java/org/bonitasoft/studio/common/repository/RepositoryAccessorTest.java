/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class RepositoryAccessorTest {

    private RepositoryAccessor repositoryAccessor;
    @Mock
    private RepositoryManager repositoryManager;
    @Mock
    private IProgressMonitor monitor;
    @Mock
    private AbstractRepository repository;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        repositoryAccessor = new RepositoryAccessor(repositoryManager);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void should_access_a_repositoryStore() throws Exception {
        //When
        repositoryAccessor.getRepositoryStore(IRepositoryStore.class);

        //Then
        verify(repositoryManager).getRepositoryStore(IRepositoryStore.class);
    }


    @Test
    public void should_open_existing_repository_when_starting() throws Exception {
        //Given
        when(repositoryManager.getCurrentRepository()).thenReturn(repository);
        when(repository.exists()).thenReturn(true);

        //When
        repositoryAccessor.start(monitor);

        //Then
        verify(repositoryManager).getCurrentRepository();
        verify(repository).open(monitor);
    }

    @Test
    public void should_create_repository_when_starting() throws Exception {
        //Given
        when(repositoryManager.getCurrentRepository()).thenReturn(repository);
        when(repository.exists()).thenReturn(false);

        //When
        repositoryAccessor.start(monitor);

        //Then
        verify(repositoryManager).getCurrentRepository();
        verify(repository).create(monitor);
    }
}
