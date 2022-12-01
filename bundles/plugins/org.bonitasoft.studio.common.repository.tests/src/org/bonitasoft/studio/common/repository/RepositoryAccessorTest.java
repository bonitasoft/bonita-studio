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

import java.util.Optional;

import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Romain Bioteau
 */
@ExtendWith(MockitoExtension.class)
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
    @BeforeEach
    public void setUp() throws Exception {
        repositoryAccessor = new RepositoryAccessor(repositoryManager);
    }

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
        when(repositoryManager.getCurrentRepository()).thenReturn(Optional.of(repository));
        when(repository.exists()).thenReturn(true);

        //When
        repositoryAccessor.start(monitor);

        //Then
        verify(repositoryManager).getCurrentRepository();
        verify(repository).open(AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @Test
    public void should_create_repository_when_starting() throws Exception {
        //Given
        when(repositoryManager.getCurrentRepository()).thenReturn(Optional.of(repository));
        when(repository.exists()).thenReturn(false);

        //When
        repositoryAccessor.start(monitor);

        //Then
        verify(repositoryManager).getCurrentRepository();
        verify(repository).create(ProjectMetadata.defaultMetadata(), AbstractRepository.NULL_PROGRESS_MONITOR);
    }
}
