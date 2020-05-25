/**
 * Copyright (C) 2017 BonitaSoft S.A.
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
package org.bonitasoft.studio.la.application.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.junit.Test;

public class NewApplicationHandlerTest {

    @Test
    public void should_create_an_applicationFileStore() throws Exception {
        final NewApplicationHandler newApplicationHandler = new NewApplicationHandler();
        final RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        final ApplicationRepositoryStore applicationStore = mock(ApplicationRepositoryStore.class);
        final ApplicationFileStore applicationFileStore = mock(ApplicationFileStore.class);
        when(applicationStore.createRepositoryFileStore(NewApplicationHandler.DEFAULT_FILE_NAME + ".xml"))
                .thenReturn(applicationFileStore);
        when(repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class)).thenReturn(applicationStore);

        final Optional<IRepositoryFileStore> fileStore = Optional
                .ofNullable(newApplicationHandler.createFileStore(repositoryAccessor,
                        NewApplicationHandler.DEFAULT_FILE_NAME + ".xml"));

        assertThat(fileStore).isPresent();
    }

}
