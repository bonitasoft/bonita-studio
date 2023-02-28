/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.restApiExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.zip.ZipFile;

import org.bonitasoft.engine.page.Page;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.operation.GetApiSessionOperation;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.operation.BuildCustomPageOperation;
import org.bonitasoft.studio.maven.operation.DeployCustomPageProjectOperation;
import org.bonitasoft.studio.rest.api.extension.core.maven.CreateRestAPIExtensionProjectOperation;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionDescriptor;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.ui.PlatformUI;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class BuildAndDeployRestAPIExtensionIT {

    private static final String ARTIFACT_ID = "myRestApiTestExportRuntime";
    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        final RestAPIExtensionRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(RestAPIExtensionRepositoryStore.class);
        if (store.getChild(ARTIFACT_ID, true) == null) {
            newRestAPIExtensionProject(ARTIFACT_ID, ARTIFACT_ID);
        }
    }

    private void newRestAPIExtensionProject(final String artifactId, final String pathTemplate)
            throws CoreException, OperationCanceledException, InterruptedException, InvocationTargetException {
        final RestAPIExtensionArchetypeConfiguration defaultArchetypeConfiguration = RestAPIExtensionArchetypeConfiguration
                .defaultArchetypeConfiguration();
        defaultArchetypeConfiguration.setPageName(artifactId);
        defaultArchetypeConfiguration.setPageDisplayName("My test Rest API");
        defaultArchetypeConfiguration.setPathTemplate(pathTemplate);
        defaultArchetypeConfiguration.setBonitaVersion(ProductVersion.BONITA_RUNTIME_VERSION);
        final CreateRestAPIExtensionProjectOperation operation = new CreateRestAPIExtensionProjectOperation(
                RepositoryManager.getInstance().getRepositoryStore(RestAPIExtensionRepositoryStore.class),
                MavenPlugin.getProjectConfigurationManager(),
                new ProjectImportConfiguration(),
                defaultArchetypeConfiguration);

        PlatformUI.getWorkbench().getProgressService().run(true, false, operation.asWorkspaceModifyOperation());
        StatusAssert.assertThat(operation.getStatus()).overridingErrorMessage(operation.getStatus().getMessage())
                .isOK();

        Job.getJobManager().join(CreateRestAPIExtensionProjectOperation.class,
                AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @Test
    public void should_build_rest_api_extension_archive_in_target_folder() throws Exception {
        final RestAPIExtensionRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(RestAPIExtensionRepositoryStore.class);
        final String targetAbsoluteFilePath = tmp.newFolder().getAbsolutePath();
        final File exportedFile = new File(targetAbsoluteFilePath + File.separator + ARTIFACT_ID + ".zip");

        final BuildCustomPageOperation operation = store.getChild(ARTIFACT_ID, true).newBuildOperation();
        PlatformUI.getWorkbench().getProgressService().run(true, false, operation.asWorkspaceModifyOperation());
        StatusAssert.assertThat(operation.getStatus()).overridingErrorMessage(operation.getStatus().getMessage())
                .isOK();

        FileUtil.copy(operation.getArchiveContent(), new FileOutputStream(exportedFile));

        assertThat(exportedFile).exists();
        try (final ZipFile zipFile = new ZipFile(exportedFile);) {
            assertThat(zipFile.getEntry("lib/myRestApiTestExportRuntime-1.0.0-SNAPSHOT.jar")).isNotNull();
            assertThat(zipFile.getEntry("configuration.properties")).isNotNull();
            assertThat(zipFile.getEntry("page.properties")).isNotNull();
        }
    }

    @Test
    public void should_deploy_rest_api_extension_in_portal() throws Exception {
        final RestAPIExtensionRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(RestAPIExtensionRepositoryStore.class);

        final RestAPIExtensionFileStore fileStore = store.getChild(ARTIFACT_ID, true);
        BuildCustomPageOperation operation = store.getChild(ARTIFACT_ID, true).newBuildOperation();
        PlatformUI.getWorkbench().getProgressService().run(true, false, operation.asWorkspaceModifyOperation());

        var sessionOperation = new GetApiSessionOperation();
        try {
            var deployRestAPIExtensionOperation = new DeployCustomPageProjectOperation(
                    BOSEngineManager.getInstance().getPageAPI(sessionOperation.execute()),
                    new HttpClientFactory(), fileStore);
            PlatformUI.getWorkbench().getProgressService().run(true, false, deployRestAPIExtensionOperation);
            StatusAssert.assertThat(deployRestAPIExtensionOperation.getStatus()).isOK();
            final Page deployedPage = deployRestAPIExtensionOperation.getDeployedPage();
            assertThat(deployedPage).isNotNull();
            assertThat(deployedPage.getDisplayName()).isEqualTo("My test Rest API");
        } finally {
            sessionOperation.logout();
        }

        final RestAPIExtensionDescriptor content = fileStore.getContent();
        final Properties pageProperties = content.getPageProperties();
        pageProperties.setProperty("displayName", "My updated test Rest API");
        content.savePageProperties(pageProperties);

        operation = store.getChild(ARTIFACT_ID, true).newBuildOperation();
        PlatformUI.getWorkbench().getProgressService().run(true, false, operation.asWorkspaceModifyOperation());
        StatusAssert.assertThat(operation.getStatus()).overridingErrorMessage(operation.getStatus().getMessage())
                .isOK();

        try {
            var deployRestAPIExtensionOperation = new DeployCustomPageProjectOperation(
                    BOSEngineManager.getInstance().getPageAPI(sessionOperation.execute()),
                    new HttpClientFactory(), fileStore);
            PlatformUI.getWorkbench().getProgressService().run(true, false, deployRestAPIExtensionOperation);
            StatusAssert.assertThat(deployRestAPIExtensionOperation.getStatus()).isOK();
            final Page updatedPage = deployRestAPIExtensionOperation.getDeployedPage();
            assertThat(updatedPage).isNotNull();
            assertThat(updatedPage.getDisplayName()).isEqualTo("My updated test Rest API");
        } finally {
            sessionOperation.logout();
        }
    }

    @Test
    public void should_fail_when_deploying_a_rest_api_extension_with_the_same_pathTemplate_as_an_existing_one()
            throws Exception {
        final RestAPIExtensionRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(RestAPIExtensionRepositoryStore.class);

        RestAPIExtensionFileStore fileStore = store.getChild(ARTIFACT_ID, true);
        BuildCustomPageOperation operation = store.getChild(ARTIFACT_ID, true).newBuildOperation();
        PlatformUI.getWorkbench().getProgressService().run(true, false, operation.asWorkspaceModifyOperation());

        var sessionOperation = new GetApiSessionOperation();
        try {
            var deployRestAPIExtensionOperation = new DeployCustomPageProjectOperation(
                    BOSEngineManager.getInstance().getPageAPI(sessionOperation.execute()),
                    new HttpClientFactory(), fileStore);
            PlatformUI.getWorkbench().getProgressService().run(true, false, deployRestAPIExtensionOperation);
            StatusAssert.assertThat(deployRestAPIExtensionOperation.getStatus()).isOK();
            final Page deployedPage = deployRestAPIExtensionOperation.getDeployedPage();
            assertThat(deployedPage).isNotNull();
        } finally {
            sessionOperation.logout();
        }

        newRestAPIExtensionProject("anotherRestAPIExtension", ARTIFACT_ID);

        fileStore = store.getChild("anotherRestAPIExtension", true);
        operation = fileStore.newBuildOperation();
        PlatformUI.getWorkbench().getProgressService().run(true, false, operation.asWorkspaceModifyOperation());
        assertThat(operation.getStatus()).overridingErrorMessage(operation.getStatus().getMessage())
                .isOK();

        try {
            var deployRestAPIExtensionOperation = new DeployCustomPageProjectOperation(
                    BOSEngineManager.getInstance().getPageAPI(sessionOperation.execute()),
                    new HttpClientFactory(), fileStore);
            PlatformUI.getWorkbench().getProgressService().run(true, false, deployRestAPIExtensionOperation);
            assertThat(deployRestAPIExtensionOperation.getStatus()).isNotOK();
        } finally {
            sessionOperation.logout();
        }

    }

}
