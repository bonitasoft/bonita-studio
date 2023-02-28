/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.engine.page.Page;
import org.bonitasoft.engine.page.PageNotFoundException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.engine.http.AddCustomPageRequest;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.http.LoginRequest;
import org.bonitasoft.studio.engine.http.UpdateCustomPageRequest;
import org.bonitasoft.studio.engine.http.UploadCustomPageRequest;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeployCustomPageOperationTest {

    @Mock
    private PageAPI pageAPI;
    @Mock
    private HttpClientFactory httpClientFactory;
    @Mock
    private AddCustomPageRequest addCustomPageRequest;
    @Mock
    private LoginRequest loginRequest;
    @Mock
    private UpdateCustomPageRequest updateCustomPageRequest;
    @Mock
    private UploadCustomPageRequest uploadCustomPageRequest;
    @Mock
    private RestAPIExtensionFileStore fileStore;
    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();
    @Mock
    private APISession apiSession;

    @Before
    public void setUp() throws Exception {
        when(httpClientFactory.newAddCustomPageRequest(notNull())).thenReturn(addCustomPageRequest);
        when(httpClientFactory.newLoginRequest()).thenReturn(loginRequest);
        when(httpClientFactory.newUploadCustomPageRequest(any(File.class))).thenReturn(uploadCustomPageRequest);
        when(uploadCustomPageRequest.execute()).thenReturn("token");
        when(httpClientFactory.newUpdateCustomPageRequest(notNull(), notNull()))
                .thenReturn(updateCustomPageRequest);
        when(fileStore.getPageId()).thenReturn("newPageId");
        when(fileStore.getContentType()).thenReturn("apiExtension");
        when(fileStore.getArchiveFile()).thenReturn(tmpFolder.newFile());
    }

    @Test
    public void should_find_existing_custom_page() throws Exception {
        var operation = new DeployCustomPageProjectOperation(pageAPI,
                httpClientFactory, fileStore);
        final Page existingPage = mock(Page.class);
        when(pageAPI.getPageByName("anExistingPage")).thenReturn(existingPage);
        final Page page = operation.findCustomPage("anExistingPage");

        assertThat(page).isEqualTo(existingPage);
    }

    @Test
    public void should_not_find_new_custom_page() throws Exception {
        var operation = new DeployCustomPageProjectOperation(pageAPI,
                httpClientFactory, fileStore);
        when(pageAPI.getPageByName("newPage")).thenThrow(PageNotFoundException.class);
        final Page page = operation.findCustomPage("newPage");

        assertThat(page).isNull();
    }

    @Test
    public void should_add_a_new_custom_page() throws Exception {
        var operation = new DeployCustomPageProjectOperation(pageAPI,
                httpClientFactory, fileStore);

        operation.deploy(new NullProgressMonitor());

        final InOrder order = inOrder(loginRequest, uploadCustomPageRequest, addCustomPageRequest);
        order.verify(loginRequest).execute();
        order.verify(uploadCustomPageRequest).execute();
        order.verify(addCustomPageRequest).execute();
    }

    @Test
    public void should_update_an_existing_custom_page() throws Exception {
        final Page existingPage = mock(Page.class);
        when(existingPage.getContentType()).thenReturn("apiExtension");
        when(pageAPI.getPageByName("anExistingPage")).thenReturn(existingPage);
        when(fileStore.getPageId()).thenReturn("anExistingPage");
        var operation = new DeployCustomPageProjectOperation(pageAPI,
                httpClientFactory, fileStore);

        operation.deploy(new NullProgressMonitor());

        final InOrder order = inOrder(loginRequest, uploadCustomPageRequest, updateCustomPageRequest);
        order.verify(loginRequest).execute();
        order.verify(uploadCustomPageRequest).execute();
        order.verify(updateCustomPageRequest).execute();
    }
    

}
