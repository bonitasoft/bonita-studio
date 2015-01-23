/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.pagedesigner.core.WorkspaceResourceServerManager;
import org.bonitasoft.studio.pagedesigner.core.repository.WebFormFileStore;
import org.bonitasoft.studio.pagedesigner.core.repository.WebFormRepositoryStore;
import org.bonitasoft.studio.pagedesigner.core.resources.WorkspaceServerResource;
import org.bonitasoft.workspace.client.api.WorkspaceResourceHandler;
import org.bonitasoft.workspace.client.impl.StudioWorkspaceResourceHandler;
import org.bonitasoft.workspace.common.LockStatus;
import org.bonitasoft.workspace.common.ResourceNotFoundException;
import org.bonitasoft.workspace.common.WorkspaceAPIEvent;
import org.eclipse.jdt.launching.SocketUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Request;
import org.restlet.Response;

/**
 * @author Romain Bioteau
 *
 */
public class PageDesignerWorkspaceIntegrationIT {

    private String fileName;
    private WebFormFileStore fileStore;
    private WorkspaceResourceServerManager workspaceResourceServerManager;
    private int port;

    @Before
    public void setUp() throws Exception {
        FileActionDialog.setDisablePopup(true);
        fileName = "7aaad167-cc7e-4afc-a8e6-391a389b8b32.json";
        final InputStream resourceAsStream = PageDesignerWorkspaceIntegrationIT.class.getResourceAsStream(fileName);
        final WebFormRepositoryStore repositoryStore = RepositoryManager.getInstance().getRepositoryStore(WebFormRepositoryStore.class);
        fileStore = repositoryStore.importInputStream(fileName, resourceAsStream);

        workspaceResourceServerManager = WorkspaceResourceServerManager.getInstance();
        port = SocketUtil.findFreePort();
        workspaceResourceServerManager.start(port);
    }

    @After
    public void tearDown() throws Exception {
        if (workspaceResourceServerManager != null) {
            workspaceResourceServerManager.stop();
        }
    }

    @Test
    public void should_resolve_relative_Path_to_IRepositoryFileStore() throws Exception {
        final WorkspaceServerResource workspaceServerResource = new WorkspaceServerResource();
        final Request request = new Request();
        final HashMap<String, Object> attributes = new HashMap<String, Object>();
        attributes.put(WorkspaceServerResource.ACTION_ATTRIBUTE, WorkspaceAPIEvent.PRE_OPEN);
        request.setAttributes(attributes);
        final Response response = new Response(request);
        workspaceServerResource.init(null, request, response);
        workspaceServerResource.dispatch(Paths.get(WebFormRepositoryStore.WEB_FORM_REPOSITORY_NAME, fileName).toString());
    }

    @Test
    public void should_resolve_absolute_Path_to_IRepositoryFileStore() throws Exception {
        final WorkspaceServerResource workspaceServerResource = new WorkspaceServerResource();
        final Request request = new Request();
        final HashMap<String, Object> attributes = new HashMap<String, Object>();
        attributes.put(WorkspaceServerResource.ACTION_ATTRIBUTE, WorkspaceAPIEvent.PRE_OPEN);
        request.setAttributes(attributes);
        final Response response = new Response(request);
        workspaceServerResource.init(null, request, response);
        workspaceServerResource.dispatch(fileStore.getResource().getLocation().toFile().toPath().toString());
    }

    @Test
    public void should_a_client_preOpen_call_notify_repository() throws Exception {
        final WorkspaceResourceHandler resourceHandler = new StudioWorkspaceResourceHandler("localhost", String.valueOf(port));
        final Response response = resourceHandler.preOpen(Paths.get(WebFormRepositoryStore.WEB_FORM_REPOSITORY_NAME, fileName));
        assertThat(response.getStatus().isSuccess()).isTrue();
    }

    @Test
    public void should_a_client_getLockStatus_call_return_unlocked() throws Exception {
        final WorkspaceResourceHandler resourceHandler = new StudioWorkspaceResourceHandler("localhost", String.valueOf(port));
        assertThat(resourceHandler.getLockStatus(Paths.get(WebFormRepositoryStore.WEB_FORM_REPOSITORY_NAME, fileName))).isEqualTo(LockStatus.UNLOCKED);
    }

    @Test(expected=ResourceNotFoundException.class)
    public void should_a_client_getLockStatus_call_throw_a_ResourceNotFoundException() throws Exception {
        final WorkspaceResourceHandler resourceHandler = new StudioWorkspaceResourceHandler("localhost", String.valueOf(port));
        resourceHandler.getLockStatus(Paths.get(WebFormRepositoryStore.WEB_FORM_REPOSITORY_NAME, "notexisting"));
    }
}
