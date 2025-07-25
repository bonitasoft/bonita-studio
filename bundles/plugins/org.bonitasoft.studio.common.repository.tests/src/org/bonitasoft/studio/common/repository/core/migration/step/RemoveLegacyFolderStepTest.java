package org.bonitasoft.studio.common.repository.core.migration.step;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.bonitasoft.studio.common.FileUtil;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RemoveLegacyFolderStepTest {

    private Path project;

    @BeforeEach
    void setup() throws Exception {
        project = Files.createTempDirectory("projectWithLegacyFolders");

        Files.createDirectories(project.resolve("looknfeels"));
        Files.createDirectories(project.resolve("simulation"));
        Files.createFile(project.resolve(Path.of("simulation", "simulation.xml")));
    }

    @AfterEach
    void cleanup() throws Exception {
        FileUtil.deleteDir(project);
    }

    @Test
    void removeLegacyFolders() throws Exception {
        // given
        var step = new RemoveLegacyFolderStep();

        assertThat(project.resolve("looknfeels")).exists();
        assertThat(project.resolve("simulation")).exists();

        // when
        var report = step.run(project, new NullProgressMonitor());

        // then
        assertThat(project.resolve("looknfeels")).doesNotExist();
        assertThat(project.resolve("simulation")).doesNotExist();
        // no backup for these folders, so migration report should not contain a backup message
        assertThat(report.removals()).isEmpty();
        assertThat(project.resolve("backup")).doesNotExist();
    }

}
