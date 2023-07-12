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

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.maven.ExtensionProjectFileStore;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.operation.BuildAndExportCustomPageOperation;
import org.bonitasoft.studio.rest.api.extension.core.maven.CreateRestAPIExtensionProjectOperation;
import org.bonitasoft.studio.tests.util.InitialProjectRule;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ExportRestAPIExtensionProjectIT {

    private static final String pageName = "my-rest-api-test-export";
    private static final String pageName2 = "my-rest-api-test-export2";
    
    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @BeforeClass
    public static void setup() throws Exception {
        InitialProjectRule.ensureDefaultProjectExists();
        createApi(pageName);
        createApi(pageName2);
    }

    private static void createApi(String pageName) throws InterruptedException, InvocationTargetException, CoreException {
        var metadata = RepositoryManager.getInstance().getCurrentProject().orElseThrow().getProjectMetadata(new NullProgressMonitor());
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration
                .defaultArchetypeConfiguration(metadata);
        configuration.setJavaPackage("org.bonitasoft.test");
        configuration.setBonitaVersion(ProductVersion.BONITA_RUNTIME_VERSION);
        configuration.setLanguage(RestAPIExtensionArchetypeConfiguration.GROOVY_LANGUAGE);
        configuration.setPageName(pageName);
        final CreateRestAPIExtensionProjectOperation operation = new CreateRestAPIExtensionProjectOperation(
                RepositoryManager.getInstance().getRepositoryStore(ExtensionRepositoryStore.class),
                MavenPlugin.getProjectConfigurationManager(),
                new ProjectImportConfiguration(),
                configuration);

        PlatformUI.getWorkbench().getProgressService().run(true, false, operation.asWorkspaceModifyOperation());

        Job.getJobManager().join(CreateRestAPIExtensionProjectOperation.class, AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @Test
    public void testExport() throws Exception {
        var store = RepositoryManager.getInstance()
                .getRepositoryStore(ExtensionRepositoryStore.class);
        final String targetAbsoluteFilePath = tmp.newFolder().getAbsolutePath();

        store.getChild(pageName, true).export(targetAbsoluteFilePath);

        final File exportedFile = new File(targetAbsoluteFilePath + File.separator + pageName + ".zip");
        assertThat(exportedFile).exists();
        try (final ZipFile zipFile = new ZipFile(exportedFile)) {
            assertThat(zipFile.getEntry(pageName + "/.project")).isNotNull();
            assertThat(zipFile.getEntry(pageName + "/pom.xml")).isNotNull();
            assertThat(zipFile.getEntry(pageName + "/src/assembly/assembly.xml")).isNotNull();
            assertThat(zipFile.getEntry(pageName + "/src/main/groovy/org/bonitasoft/test/Index.groovy")).isNotNull();
            assertThat(zipFile.getEntry(pageName + "/target/classes")).isNull();
            assertThat(zipFile.getEntry(pageName + "/bin/pom.xml")).isNull();
        }
    }

    @Test
    public void buildAndExportMultipleRestApi() throws Exception {
        List<ExtensionProjectFileStore> apiToExport = RepositoryManager.getInstance()
                .getRepositoryStore(ExtensionRepositoryStore.class).getChildren().stream()
                .map(ExtensionProjectFileStore.class::cast).collect(Collectors.toList());
        String targetDir = tmp.newFolder().getAbsolutePath();

        BuildAndExportCustomPageOperation operation = new BuildAndExportCustomPageOperation();
        IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
        operation.run(apiToExport, targetDir, progressService);

        StatusAssert.assertThat(operation.getStatus()).isOK();
        assertThat(new File(targetDir + File.separator + pageName + "-0.0.1.zip")).exists();
        assertThat(new File(targetDir + File.separator + pageName2 + "-0.0.1.zip")).exists();
    }

}
