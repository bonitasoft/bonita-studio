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

import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplication;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.business.application.Application;
import org.bonitasoft.engine.business.application.ApplicationSearchDescriptor;
import org.bonitasoft.engine.exception.DeletionException;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.search.impl.SearchResultImpl;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DeleteApplicationRunnableTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_delete_application_by_token() throws Exception {
        final ApplicationAPI applicationAPI = mock(ApplicationAPI.class);
        final Application application1 = applicationWithId(1);
        final Application application2 = applicationWithId(2);
        when(applicationAPI.searchApplications(notNull(SearchOptions.class)))
                .thenReturn(searchResult(Collections.emptyList()));
        when(applicationAPI.searchApplications(eq(withToken("myApp1"))))
                .thenReturn(searchResult(Arrays.asList(application1)));
        when(applicationAPI.searchApplications(eq(withToken("myApp2"))))
                .thenReturn(searchResult(Arrays.asList(application2)));

        final DeleteApplicationRunnable operation = new DeleteApplicationRunnable(applicationAPI,
                newApplication("myApp1", "My App", "1.0").create());

        operation.run(new NullProgressMonitor());

        verify(applicationAPI).deleteApplication(1);
        StatusAssert.assertThat(operation.getStatus()).isOK();
    }

    @Test
    public void should_fail_if_application_not_found() throws Exception {
        final ApplicationAPI applicationAPI = mock(ApplicationAPI.class);
        when(applicationAPI.searchApplications(notNull(SearchOptions.class)))
                .thenReturn(searchResult(Collections.emptyList()));

        final DeleteApplicationRunnable operation = new DeleteApplicationRunnable(applicationAPI,
                newApplication("unknownToken", "My App 2", "1.0").create());

        operation.run(new NullProgressMonitor());

        verify(applicationAPI, never()).deleteApplication(anyInt());
        StatusAssert.assertThat(operation.getStatus()).isNotOK();
    }

    @Test
    public void should_not_fail_if_application_not_found_with_ignore_option() throws Exception {
        final ApplicationAPI applicationAPI = mock(ApplicationAPI.class);
        when(applicationAPI.searchApplications(notNull(SearchOptions.class)))
                .thenReturn(searchResult(Collections.emptyList()));

        final DeleteApplicationRunnable operation = new DeleteApplicationRunnable(applicationAPI,
                newApplication("unknownToken", "My App 2", "1.0").create())
                        .ignoreErrors();

        operation.run(new NullProgressMonitor());

        verify(applicationAPI, never()).deleteApplication(anyInt());
        StatusAssert.assertThat(operation.getStatus()).isOK();
    }

    @Test
    public void should_fail_if_application_deletion_failed() throws Exception {
        final ApplicationAPI applicationAPI = mock(ApplicationAPI.class);
        doThrow(DeletionException.class).when(applicationAPI).deleteApplication(1);
        final Application application1 = applicationWithId(1);
        when(applicationAPI.searchApplications(notNull(SearchOptions.class)))
                .thenReturn(searchResult(Collections.emptyList()));
        when(applicationAPI.searchApplications(eq(withToken("myApp1"))))
                .thenReturn(searchResult(Arrays.asList(application1)));

        final DeleteApplicationRunnable operation = new DeleteApplicationRunnable(applicationAPI,
                newApplication("myApp1", "My App", "1.0").create());

        operation.run(new NullProgressMonitor());

        verify(applicationAPI).deleteApplication(1);
        StatusAssert.assertThat(operation.getStatus()).isNotOK();
    }

    private SearchResult<Application> searchResult(List<Application> resultAsList) {
        return new SearchResultImpl<>(resultAsList.size(), resultAsList);
    }

    protected SearchOptions withToken(String token) {
        return new SearchOptionsBuilder(0, 1).filter(ApplicationSearchDescriptor.TOKEN, token).done();
    }

    protected Application applicationWithId(long id) {
        final Application app = mock(Application.class);
        when(app.getId()).thenReturn(Long.valueOf(id));
        return app;
    }
}
