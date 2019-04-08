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
package org.bonitasoft.studio.designer.core.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.Collections;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.restlet.representation.Representation;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateFormOperationTest {

    @Mock
    private PageDesignerURLFactory pageDesignerURLFactory;

    private CreateFormOperation createFormOperation;

    @Mock
    private IProgressMonitor monitor;

    @Mock
    private RepositoryAccessor repositoryAccessor;
    
    @Mock
    private WebPageRepositoryStore pageStore;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(pageStore.getChildren()).thenReturn(Collections.emptyList());
        when(repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class)).thenReturn(pageStore);
        createFormOperation = spy(new CreateFormOperation(pageDesignerURLFactory, repositoryAccessor));
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "newPage");
        jsonObject.put("id", "page-id");
        doReturn(jsonObject.toString()).when(createFormOperation).doPost(any(URL.class), any(Representation.class));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = IllegalStateException.class)
    public void should_getNewPageId_throw_illegal_argument_exception_if_operation_has_not_been_executed() throws Exception {
        createFormOperation.getNewArtifactId();
    }

    @Test(expected = IllegalStateException.class)
    public void should_getNewPageName_throw_illegal_argument_exception_if_operation_has_not_been_executed()
            throws Exception {
        createFormOperation.getNewPageName();
    }

    @Test
    public void should_run() throws Exception {
        when(pageDesignerURLFactory.newPage()).thenReturn(new URL("http://localhost:8080/anUrl"));

        createFormOperation.run(monitor);

        verify(pageDesignerURLFactory).newPage();
        assertThat(createFormOperation.getNewArtifactId()).isEqualTo("page-id");
        assertThat(createFormOperation.getNewPageName()).isEqualTo("newPage");
    }
}
