package org.bonitasoft.studio.importer.bos.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.operation.DependenciesUpdateOperation;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.operation.DependenciesUpdateOperationFactory;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Importing a .bos exported from another project must not copy that project's
 * {@code <projectId>-bdm-model} dependency into the current project's app pom:
 * the artifact is not built by the current project and breaks the Maven build.
 */
@ExtendWith(MockitoExtension.class)
class ImportBosArchiveOperationDependenciesTest {

    private static final String CURRENT_APP_POM = """
            <?xml version="1.0" encoding="UTF-8"?>
            <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
              <modelVersion>4.0.0</modelVersion>
              <groupId>com.company</groupId>
              <artifactId>zulu</artifactId>
              <version>0.0.1</version>
              <dependencies>
                <dependency>
                  <groupId>${project.groupId}</groupId>
                  <artifactId>zulu-bdm-model</artifactId>
                  <version>${project.version}</version>
                  <scope>provided</scope>
                </dependency>
              </dependencies>
            </project>
            """;

    @TempDir
    Path tempDir;

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private AbstractRepository repository;

    @Mock
    private IProject appProject;

    @Mock
    private IFile pomFile;

    @Mock
    private DiagramRepositoryStore diagramStore;

    @Mock
    private ImportArchiveModel importArchiveModel;

    @Mock
    private DependenciesUpdateOperationFactory dependenciesUpdateOperationFactory;

    private Path currentPom;

    private ImportBosArchiveOperation operationUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        currentPom = tempDir.resolve(MavenProjectHelper.POM_FILE_NAME);
        Files.writeString(currentPom, CURRENT_APP_POM);

        when(repositoryAccessor.getCurrentRepository()).thenReturn(Optional.of(repository));
        when(repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class)).thenReturn(diagramStore);
        when(repository.getProject()).thenReturn(appProject);
        when(appProject.getFile(MavenProjectHelper.POM_FILE_NAME)).thenReturn(pomFile);
        when(pomFile.exists()).thenReturn(true);
        when(pomFile.getLocation()).thenReturn(org.eclipse.core.runtime.Path.fromOSString(currentPom.toString()));
        when(dependenciesUpdateOperationFactory.createDependencyUpdateOperation())
                .thenReturn(mock(DependenciesUpdateOperation.class));

        operationUnderTest = spy(new ImportBosArchiveOperation(new File("alpha.bos"),
                null,
                importArchiveModel,
                false,
                repositoryAccessor,
                dependenciesUpdateOperationFactory));
        operationUnderTest.manualDependencyResolution();
        doNothing().when(operationUnderTest).migrateUID(any());
        doNothing().when(operationUnderTest).migrateFragmentJarVersions(any());
    }

    @Test
    void should_not_merge_bdm_model_dependency_of_imported_project() throws Exception {
        var importedAppModel = new Model();
        // BDM model dependency as written by DeployedBDMEventHandler in the 'alpha' project
        importedAppModel.addDependency(
                dependency("alpha-bdm-model", "jar", Artifact.SCOPE_PROVIDED));
        // Extension module dependency as written by CreateExtensionProjectOperation
        importedAppModel.addDependency(dependency("alpha-rest-api", null, null));
        doReturn(importedAppModel).when(operationUnderTest).existingMavenModel(any());

        operationUnderTest.run(new NullProgressMonitor());

        assertThat(MavenProjectHelper.readModel(currentPom.toFile()).getDependencies())
                .extracting(Dependency::getArtifactId)
                .containsExactlyInAnyOrder("zulu-bdm-model", "alpha-rest-api");
    }

    private static Dependency dependency(String artifactId, String type, String scope) {
        var dependency = new Dependency();
        dependency.setGroupId("${project.groupId}");
        dependency.setArtifactId(artifactId);
        dependency.setVersion("${project.version}");
        dependency.setType(type);
        dependency.setScope(scope);
        return dependency;
    }

}
