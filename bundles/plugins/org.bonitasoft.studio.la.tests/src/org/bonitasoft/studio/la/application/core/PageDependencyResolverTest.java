/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;
import org.mockito.Mockito;

public class PageDependencyResolverTest {

    @Test
    public void should_find_existing_pages_in_page_store() throws Exception {
        final WebPageRepositoryStore store = mock(WebPageRepositoryStore.class);
        when(store.findByPageId("myPage1")).thenReturn(Optional.of(mock(WebPageFileStore.class)));
        when(store.findByPageId("myPage2")).thenReturn(Optional.empty());
        final PageAPI pageApi = mock(PageAPI.class);

        BonitaPagesRegistry pageRegistry = mock(BonitaPagesRegistry.class);
        doReturn(Optional.empty()).when(pageRegistry).getPage(Mockito.anyString());
        doReturn(providedPage()).when(pageRegistry).getPage("caselistingadmin");
        final PageDependencyResolver pageDependencyResolver = new PageDependencyResolver(store, pageApi,pageRegistry);
       
        
        final Stream<IRunnableWithStatus> deployables = pageDependencyResolver
                .prepareDeployOperation(Stream.of("myPage1", "myPage2", "caselistingadmin"));

        assertThat(deployables).extracting(IRunnableWithStatus::getStatus)
                .extracting(IStatus::getSeverity)
                .contains(IStatus.OK, IStatus.ERROR);
    }

    private Optional<EntryPage> providedPage() {
        return Optional.of(new EntryPage("", "", "", true, false));
    }

}
