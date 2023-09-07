package org.bonitasoft.studio.common.repository.core.migration.step;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.ZipUtil;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RemoveLegacyFolderStepTest {
    
    private Path project;

    @BeforeEach
    void setup() throws Exception {
        var zipFile = new File(
                FileLocator.toFileURL(RemoveLegacyFolderStepTest.class.getResource("/legacyConnectorsProject.zip"))
                        .getFile());
        project = ZipUtil.unzip(zipFile).resolve("legacyConnectorsProject");
    }

    @AfterEach
    void cleanup() throws Exception {
        deleteDir(project.getParent());
    }

    @Test
    void removeLegacyConnectorFolders() throws Exception {
        var step = new RemoveLegacyFolderStep();

        var report = step.run(project, new NullProgressMonitor());

        assertThat(project.resolve("connectors-def")).doesNotExist();
        assertThat(project.resolve("connectors-impl")).doesNotExist();
        assertThat(project.resolve("connectors-src")).doesNotExist();
        assertThat(report.removals()).contains(String.format(
                "Legacy connector and actor filters implementations and definitions have been removed. All related files have been backup in %s folder. Only connectors and actor filters used in the project extensions are supported. Consult the connector and actor filter maven archetype documentation to migrate to the supported format.",
                project.resolve("backup")));
    }

    @Test
    void removeLegacyFiltersFolders() throws Exception {
        var step = new RemoveLegacyFolderStep();

        assertThat(project.resolve("filters-def")).exists();
        assertThat(project.resolve("filters-impl")).exists();
        assertThat(project.resolve("filters-src")).doesNotExist();

        var report = step.run(project, new NullProgressMonitor());

        assertThat(project.resolve("filters-def")).doesNotExist();
        assertThat(project.resolve("filters-impl")).doesNotExist();
        assertThat(project.resolve("filters-src")).doesNotExist();
        assertThat(report.removals()).contains(String.format(
                "Legacy connector and actor filters implementations and definitions have been removed. All related files have been backup in %s folder. Only connectors and actor filters used in the project extensions are supported. Consult the connector and actor filter maven archetype documentation to migrate to the supported format.",
                project.resolve("backup")));
    }

	private static void deleteDir(Path directory) throws IOException {
		try (Stream<Path> pathStream = Files.walk(directory)) {
			pathStream.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
		}
	}
}
