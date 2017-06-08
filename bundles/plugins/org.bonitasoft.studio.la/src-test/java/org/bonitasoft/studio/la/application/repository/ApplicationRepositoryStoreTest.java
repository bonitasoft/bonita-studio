/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplication;
import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplicationContainer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.junit.Test;

public class ApplicationRepositoryStoreTest {

    @Test
    public void should_only_accept_xml_files() throws Exception {
        final ApplicationRepositoryStore store = new ApplicationRepositoryStore();

        assertThat(store.getCompatibleExtensions()).containsOnly("xml");
    }

    @Test
    public void should_find_a_file_store_for_an_app_token() throws Exception {
        final ApplicationRepositoryStore store = spy(new ApplicationRepositoryStore());
        doReturn(asApplicationFileStores(newApplicationContainer()
                .havingApplications(newApplication("app1", "My App", "1.0")).create())).when(store)
                        .getChildren();

        assertThat(store.findFileStoreByToken("app2")).isNotPresent();
        assertThat(store.findFileStoreByToken("app1")).isPresent();
    }

    @Test
    public void should_find_an_application_for_a_profile() throws Exception {
        final ApplicationRepositoryStore store = spy(new ApplicationRepositoryStore());
        doReturn(asApplicationFileStores(newApplicationContainer()
                .havingApplications(newApplication("app1", "My App", "1.0").withProfile("User"),
                        newApplication("app2", "My App 2", "1.0").withProfile("Admin"))
                .create(),
                newApplicationContainer()
                        .havingApplications(newApplication("app3", "My App 3", "1.0").withProfile("User"))
                        .create())).when(store)
                                .getChildren();

        assertThat(store.findByProfile("User")).hasSize(2);
        assertThat(store.findByProfile("Admin")).hasSize(1);
        assertThat(store.findByProfile("Custom")).isEmpty();
    }

    private List<ApplicationFileStore> asApplicationFileStores(ApplicationNodeContainer... applicationNodeContainers) {
        return Stream.of(applicationNodeContainers)
                .map(container -> {
                    final ApplicationFileStore fStore = mock(ApplicationFileStore.class);
                    try {
                        doReturn(container).when(fStore).getContent();
                    } catch (final ReadFileStoreException e) {
                        e.printStackTrace();
                    }
                    return fStore;
                })
                .collect(Collectors.toList());

    }
}
