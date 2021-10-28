/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.importer.bos;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.dependencies.operation.DependenciesUpdateOperationFactory;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.operation.ImportConflictsChecker;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.junit.Before;
import org.junit.Test;

public class ImportBOSArchiveIT {

    RepositoryAccessor repositoryAccessor;
    private DependenciesUpdateOperationFactory dependenciesUpdateOperationFactory;

    @Before
    public void cleanDiagrams() throws Exception {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        var eclipseContext = EclipseContextFactory.create();
        dependenciesUpdateOperationFactory = ContextInjectionFactory
                .make(DependenciesUpdateOperationFactory.class, eclipseContext);
        final DiagramRepositoryStore repositoryStore = repositoryAccessor.getCurrentRepository()
                .getRepositoryStore(DiagramRepositoryStore.class);
        repositoryStore.getChildren().stream().forEach(IRepositoryFileStore::delete);
    }

    @Test
    public void testImportBOSArchiveLight() throws Exception {
        final ImportBosArchiveOperation operation = new ImportBosArchiveOperation(repositoryAccessor);
        final File file = new File(
                FileLocator.toFileURL(ImportBOSArchiveIT.class.getResource("MyDiagram_1_0.bos")).getFile());
        operation.setArchiveFile(file.getAbsolutePath());
        operation.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        operation.run(new NullProgressMonitor());
        assertThat(operation.getStatus()).isNotNull();
    }

    @Test
    public void testKeepUserChoices() throws Exception {
        ImportBosArchiveOperation operation = new ImportBosArchiveOperation(repositoryAccessor);
        final File file = loadArchiveFile("TestImportUserChoices-1.0.bos");
        operation.setArchiveFile(file.getAbsolutePath());
        operation.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        operation.run(new NullProgressMonitor());
        StatusAssert.assertThat(operation.getStatus()).isOK();
        DiagramRepositoryStore diagramStore = repositoryAccessor.getCurrentRepository()
                .getRepositoryStore(DiagramRepositoryStore.class);

        final byte[] firstDiagram = diagramStore.getDiagram("TestImportUserChoices", "1.0").toByteArray();

        final File conflictingFile = loadArchiveFile("testConflicts-toImport/TestImportUserChoices-1.0.bos");

        final ImportConflictsChecker conflictsChecker = new ImportConflictsChecker(
                repositoryAccessor.getCurrentRepository());
        final ImportArchiveModel archiveModel = conflictsChecker.checkConflicts(new BosArchive(conflictingFile),
                new NullProgressMonitor());
        archiveModel.getStores().stream()
                .filter(store -> Objects.equals(store.getFolderName(), "diagrams"))
                .forEach(store -> store.getFiles().stream()
                        .forEach(diagram -> diagram.setImportAction(ImportAction.KEEP)));

        operation = new ImportBosArchiveOperation(conflictingFile, new SkippableProgressMonitorJobsDialog(
                Display.getDefault().getActiveShell()), archiveModel, repositoryAccessor,
                dependenciesUpdateOperationFactory);
        operation.run(new NullProgressMonitor());
        StatusAssert.assertThat(operation.getStatus()).isOK();

        diagramStore = repositoryAccessor.getCurrentRepository()
                .getRepositoryStore(DiagramRepositoryStore.class);

        final byte[] newDiagram = diagramStore.getDiagram("TestImportUserChoices", "1.0").toByteArray();
        assertThat(firstDiagram).isEqualTo(newDiagram);
    }

    @Test
    public void testOverwriteUserChoices() throws Exception {
        ImportBosArchiveOperation operation = new ImportBosArchiveOperation(repositoryAccessor);
        final File file = loadArchiveFile("TestImportUserChoices-1.0.bos");
        operation.setArchiveFile(file.getAbsolutePath());
        operation.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        operation.run(new NullProgressMonitor());
        assertThat(operation.getStatus()).isNotNull();

        DiagramRepositoryStore diagramStore = repositoryAccessor.getCurrentRepository()
                .getRepositoryStore(DiagramRepositoryStore.class);

        final byte[] firstDiagram = diagramStore.getDiagram("TestImportUserChoices", "1.0").toByteArray();

        final File conflictingFile = loadArchiveFile("testConflicts-toImport/TestImportUserChoices-1.0.bos");
        operation = new ImportBosArchiveOperation(repositoryAccessor);
        operation.setArchiveFile(conflictingFile.getAbsolutePath());
        operation.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        operation.run(new NullProgressMonitor());
        assertThat(operation.getStatus()).isNotNull();

        diagramStore = repositoryAccessor.getCurrentRepository()
                .getRepositoryStore(DiagramRepositoryStore.class);

        final byte[] newDiagram = diagramStore.getDiagram("TestImportUserChoices", "1.0").toByteArray();
        assertThat(firstDiagram).isNotEqualTo(newDiagram);
    }

    @Test
    public void testImportBOSArchiveFull() throws Exception {
        final ImportBosArchiveOperation operation = new ImportBosArchiveOperation(repositoryAccessor);
        final File file = new File(
                FileLocator.toFileURL(ImportBOSArchiveIT.class.getResource("testRepo_100912_1757.bos")).getFile());
        operation.setArchiveFile(file.getAbsolutePath());
        operation.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        PlatformUI.getWorkbench().getProgressService().run(true, false, operation);
        assertThat(operation.getStatus()).isNotNull();

        IProject project = repositoryAccessor.getCurrentRepository().getProject();
        var mavenProjectHelper = new MavenProjectHelper();
        var model = mavenProjectHelper.getMavenModel(project);

        // Provided dependencies
        if(PlatformUtil.isACommunityBonitaProduct()) {
            assertThat(mavenProjectHelper.findDependency(model, "org.bonitasoft.engine", "bonita-common")).isPresent();
        }else {
            assertThat(mavenProjectHelper.findDependency(model, "com.bonitasoft.engine", "bonita-common-sp")).isPresent();
        }
        assertThat(mavenProjectHelper.findDependency(model, "org.codehaus.groovy", "groovy")).isPresent();
        assertThat(mavenProjectHelper.findDependency(model, "org.codehaus.groovy", "groovy-dateutil")).isPresent();
        // Dependencies migrated from existing jars in lib folder
        assertThat(mavenProjectHelper.findDependency(model, dependency("org.apache.commons", "commons-exec", "1.1")))
                .isPresent();
    }

    private Dependency dependency(String groupId, String artifactId, String version) {
        Dependency dependency = new Dependency();
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        dependency.setType("jar");
        return dependency;
    }

    @Test
    public void testImportSeveralDiagrams() throws IOException, InvocationTargetException, InterruptedException {
        final ImportBosArchiveOperation operation = new ImportBosArchiveOperation(repositoryAccessor);
        final File file = new File(
                FileLocator.toFileURL(ImportBOSArchiveIT.class.getResource("severalDiagramsImportTest.bos"))
                        .getFile());
        operation.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        operation.setArchiveFile(file.getAbsolutePath());
        operation.run(new NullProgressMonitor());
        assertThat(operation.getStatus()).isNotNull();
        final DiagramRepositoryStore store = repositoryAccessor.getCurrentRepository()
                .getRepositoryStore(DiagramRepositoryStore.class);
        final DiagramFileStore diagram1 = store.getDiagram("diagram1", "1.0");
        assertThat(diagram1).isNotNull().as("diagram1 was not imported correctly");
        final DiagramFileStore diagram2 = store.getDiagram("diagram2", "1.0");
        assertThat(diagram2).isNotNull().as("diagram2 was not imported correctly");
        final DiagramFileStore diagram3 = store.getDiagram("diagram3", "1.0");
        assertThat(diagram3).isNotNull().as("diagram3 was not imported correctly");
    }

    @Test
    public void testImportBOSArchiveDemoProcess() throws Exception {
        final ImportBosArchiveOperation operation = new ImportBosArchiveOperation(repositoryAccessor);
        final File file = new File(
                FileLocator.toFileURL(ImportBOSArchiveIT.class.getResource("FillDBForDemo_1_0.bos")).getFile());
        operation.setArchiveFile(file.getAbsolutePath());
        operation.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        operation.run(new NullProgressMonitor());
        assertThat(operation.getStatus()).isNotNull();

        final boolean javaFileExists = ResourcesPlugin.getWorkspace().getRoot()
                .getProject(CommonRepositoryPlugin.getCurrentRepository())
                .getFolder("src-connectors").getFolder("org").getFolder("bonitasoft").getFolder("connector")
                .getFolder("demo").getFile("FillDBImpl.java")
                .exists();
        assertThat(javaFileExists).isTrue();
    }

    private File loadArchiveFile(String filePath) throws IOException {
        return new File(FileLocator.toFileURL(ImportBOSArchiveIT.class.getResource(filePath)).getFile());
    }
}
