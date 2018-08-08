/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.explorer.filters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.core.resources.IProject;
import org.junit.Test;

public class CurrentProjectFilterTest {

    @Test
    public void should_only_accept_current_project() {
        IProject currentProject = mock(IProject.class);
        IProject otherProject = mock(IProject.class);

        Repository currentRepo = mock(Repository.class);
        when(currentRepo.getProject()).thenReturn(currentProject);
        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        when(repositoryAccessor.getCurrentRepository()).thenReturn(currentRepo);

        CurrentProjectFilter filter = new CurrentProjectFilter(repositoryAccessor);
        assertThat(filter.select(null, null, otherProject)).isFalse();
        assertThat(filter.select(null, null, currentProject)).isTrue();
    }

}
