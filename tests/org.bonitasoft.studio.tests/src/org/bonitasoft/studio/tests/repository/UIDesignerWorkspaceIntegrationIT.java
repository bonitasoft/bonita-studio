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
package org.bonitasoft.studio.tests.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.designer.core.operation.CreateFormOperation;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.tests.util.ProjectUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UIDesignerWorkspaceIntegrationIT {

    private IFile newPageResource;

    @Before
    @After
    public void clean() throws Exception {
        ProjectUtil.cleanProject();
    }

    @Test
    public void create_a_new_page_should_trigger_a_refresh_on_a_page_filestore() throws Exception {
        waitForServer();
        RepositoryAccessor repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        final CreateFormOperation createFormOperation = new CreateFormOperation(new PageDesignerURLFactory(
                InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID)), repositoryAccessor);
        createFormOperation.setArtifactName("MyNewForm");
        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        service.run(true, false, createFormOperation);

        final WebPageRepositoryStore repositoryStore = RepositoryManager.getInstance()
                .getRepositoryStore(WebPageRepositoryStore.class);
        newPageResource = repositoryStore.getChild(createFormOperation.getNewArtifactId(), true).getResource()
                .getFile(createFormOperation.getNewArtifactId() + ".json");
        assertThat(newPageResource.exists()).overridingErrorMessage(
                "Workspace should be in sync with new page file").isTrue();
    }

    @Test
    public void import_a_custom_page_trigger_a_refresh_on_the_workspace() throws Exception {
        waitForServer();

        try (var httpClient = HttpClients.createDefault()) {
            final HttpPost uploadFileRequest = new HttpPost(
                    String.format("http://localhost:%s/bonita/import/page", uidPort()));
            final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            File customPageToImport = customPageToImport();
            builder.addBinaryBody("file", customPageToImport, ContentType.APPLICATION_OCTET_STREAM,
                    customPageToImport.getName());
            uploadFileRequest.setEntity(builder.build());
            httpClient.execute(uploadFileRequest);
        }

        final WebPageRepositoryStore repositoryStore = RepositoryManager.getInstance()
                .getRepositoryStore(WebPageRepositoryStore.class);
        assertThat(repositoryStore.getChild("f3ae2099-6298-4b91-add3-bddb3af60b45", true).getResource()
                .getFile("f3ae2099-6298-4b91-add3-bddb3af60b45.json").exists())
                        .overridingErrorMessage(
                                "Workspace should be in sync with imported page file")
                        .isTrue();
    }

    private int uidPort() {
        return UIDesignerServerManager.getInstance().getPort();
    }

    private File customPageToImport() throws URISyntaxException, IOException {
        return Paths.get(
                FileLocator.toFileURL(UIDesignerWorkspaceIntegrationIT.class.getResource("/page-APageToImport.zip"))
                        .toURI())
                .toFile();
    }

    private void waitForServer() {
        BOSEngineManager.getInstance().start();
    }

}
