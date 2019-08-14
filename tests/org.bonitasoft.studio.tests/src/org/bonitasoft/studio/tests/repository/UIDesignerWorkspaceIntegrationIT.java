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

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.designer.core.operation.CreateFormOperation;
import org.bonitasoft.studio.designer.core.repository.WebFragmentFileStore;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetFileStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.restlet.data.MediaType;
import org.restlet.ext.html.FormData;
import org.restlet.ext.html.FormDataSet;
import org.restlet.representation.FileRepresentation;
import org.restlet.resource.ClientResource;

/**
 * @author Romain Bioteau
 */
public class UIDesignerWorkspaceIntegrationIT {

    private IFile newPageResource;

    @Before
    @After
    public void clean_all_web_stores() throws Exception {
        final Repository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        final WebPageRepositoryStore webPageRepositoryStore = currentRepository
                .getRepositoryStore(WebPageRepositoryStore.class);
        for (final WebPageFileStore fStore : webPageRepositoryStore.getChildren()) {
            fStore.delete();
        }

        final WebFragmentRepositoryStore webFragRepositoryStore = currentRepository
                .getRepositoryStore(WebFragmentRepositoryStore.class);
        for (final WebFragmentFileStore fStore : webFragRepositoryStore.getChildren()) {
            fStore.delete();
        }

        final WebWidgetRepositoryStore webWidgetRepositoryStore = currentRepository
                .getRepositoryStore(WebWidgetRepositoryStore.class);
        for (final WebWidgetFileStore fStore : webWidgetRepositoryStore.getChildren()) {
            fStore.delete();
        }
    }

    @Test
    public void create_a_new_page_should_trigger_a_refresh_on_a_page_filestore() throws Exception {
        waitForServer();
        RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
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

        new ClientResource(String.format("http://localhost:%s/bonita/import/page", uidPort()))
                .post(formDataSetWithCustomPageZipFile());

        final WebPageRepositoryStore repositoryStore = RepositoryManager.getInstance()
                .getRepositoryStore(WebPageRepositoryStore.class);
        assertThat(repositoryStore.getChild("f3ae2099-6298-4b91-add3-bddb3af60b45", true).getResource()
                .getFile("f3ae2099-6298-4b91-add3-bddb3af60b45.json").exists())
                        .overridingErrorMessage(
                                "Workspace should be in sync with imported page file")
                        .isTrue();
    }

    private FormDataSet formDataSetWithCustomPageZipFile() throws URISyntaxException, IOException {
        final FormDataSet form = new FormDataSet();
        form.setMultipart(true);
        form.getEntries().add(new FormData("file", new FileRepresentation(customPageToImport(), MediaType.APPLICATION_ZIP)));
        return form;
    }

    private int uidPort() {
        return UIDesignerServerManager.getInstance().getPort();
    }

    private File customPageToImport() throws URISyntaxException, IOException {
        return Paths.get(
                FileLocator.toFileURL(UIDesignerWorkspaceIntegrationIT.class.getResource("/page-APageToImport.zip")).toURI())
                .toFile();
    }

    private void waitForServer() {
        BOSEngineManager.getInstance().start();
    }

}
