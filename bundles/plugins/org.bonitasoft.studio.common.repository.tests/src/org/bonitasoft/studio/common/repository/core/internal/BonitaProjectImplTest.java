/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.internal;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BooleanSupplier;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.team.GitProject;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.egit.core.GitProvider;
import org.eclipse.egit.core.op.DisconnectProviderOperation;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.team.core.RepositoryProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BonitaProjectImplTest {

    private IRepository repository;
    private NullProgressMonitor monitor = new NullProgressMonitor();
    private ProjectMetadata metadata;
    private BonitaProject project;
    private String projectId;

    @BeforeEach
    void setupDefaultProject() throws Exception {
        metadata = ProjectMetadata.defaultMetadata();
        projectId = "my-project-" + System.currentTimeMillis();
        metadata.setArtifactId(projectId);
        project = BonitaProject.create(projectId);
        if (project.exists()) {
            project.delete(monitor);
        }
        repository = RepositoryManager.getInstance().newRepository(projectId);
        RepositoryManager.getInstance().setCurrentRepository(repository);
        repository.create(metadata, monitor);
    }

    @AfterEach
    void deleteProject() throws Exception {
        if (project.exists()) {
            try {
                project.delete(monitor);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Test
    void projectReferences() throws Exception {
        var parentProject = project.getParentProject();

        assertThat(parentProject.exists()).isTrue();
        assertThat(parentProject.getName()).isEqualTo(metadata.getArtifactId());

        var appProject = project.getAdapter(IProject.class);
        assertThat(appProject.exists()).isTrue();

        assertThat(project.getDisplayName()).isEqualTo(metadata.getName());
        assertThat(project.getId()).isEqualTo(metadata.getArtifactId());
    }

    @Test
    void openCloseAllRelatedProjects() throws Exception {
        project.close(monitor);

        var eclipseProject = project.getAdapter(IProject.class);
        assertThat(eclipseProject.isOpen()).isFalse();
        assertThat(project.getParentProject().isOpen()).isFalse();

        project.open(monitor);

        eclipseProject = project.getAdapter(IProject.class);
        assertThat(eclipseProject.isOpen()).isTrue();
        assertThat(project.getParentProject().isOpen()).isTrue();
    }

    @Test
    void deleteAllRelatedProjects() throws Exception {
        project.delete(monitor);

        var eclipseProject = project.getAdapter(IProject.class);
        assertThat(eclipseProject.exists()).isFalse();
        assertThat(ResourcesPlugin.getWorkspace().getRoot().getProject(projectId).exists()).isFalse();
    }

    @Test
    void updateIdOfAllRelatedProjects() throws Exception {
        var updatedMetadata = metadata;
        updatedMetadata.setArtifactId("some-new-id");

        project.update(updatedMetadata, monitor);

        var currentMetadata = project.getProjectMetadata(monitor);
        assertThat(currentMetadata.getArtifactId()).isEqualTo("some-new-id");
        assertThat(ResourcesPlugin.getWorkspace().getRoot().getProject(projectId).exists()).isFalse();
    }

    @Test
    void updateVersionOfAllRelatedProjects() throws Exception {
        var updatedMetadata = metadata;
        updatedMetadata.setVersion("2.0.0");

        project.update(updatedMetadata, monitor);

        var currentMetadata = project.getProjectMetadata(monitor);
        assertThat(currentMetadata.getVersion()).isEqualTo("2.0.0");
    }

    @Test
    void gitConnect() throws Exception {
        var op = project.newConnectProviderOperation();

        op.run(monitor);

        var gitRepository = project.getAdapter(Repository.class);
        assertThat(gitRepository).isNotNull();
        assertThat(gitRepository.getDirectory())
                .isEqualTo(project.getParentProject().getLocation().append(".git").toFile());
        assertThat(RepositoryProvider.getProvider(project.getParentProject(), GitProvider.ID)).isNotNull();

        new DisconnectProviderOperation(project.getRelatedProjects())
                .execute(new NullProgressMonitor());

        assertThat(RepositoryProvider.getProvider(project.getParentProject(), GitProvider.ID)).isNull();
        waitUntil(() -> {
            try {
                FileUtil.deleteDir(project.getGitDir().toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return !project.getGitDir().exists();
        }, 10000, 500);
    }

    @Test
    void createDefaultIgnoreFile() throws Exception {
        var updatedMetadata = metadata;
        updatedMetadata.setVersion("2.0.0");

        project.createDefaultIgnoreFile();

        var gitignore = project.getAppProject().getFile(Constants.GITIGNORE_FILENAME);
        assertThat(gitignore.exists()).isTrue();
        assertThat(Files.readString(gitignore.getLocation().toFile().toPath()))
                .isEqualTo(Files.readString(new File(GitProject.getGitignoreTemplateFileURL().getFile()).toPath()));

        var parentGitignore = project.getParentProject().getFile(Constants.GITIGNORE_FILENAME);
        assertThat(parentGitignore.exists()).isTrue();
        assertThat(Files.readString(parentGitignore.getLocation().toFile().toPath()))
                .isEqualTo(Files.readString(
                        new File(GitProject.getParentGitIgnoreTemplate().getFile()).toPath()));
    }

    private static void waitUntil(BooleanSupplier condition, int timeout, int interval)
            throws TimeoutException, InterruptedException {
        var countDownLatch = new CountDownLatch(1);
        var shouldExitThread = new AtomicBoolean();
        shouldExitThread.set(false);
        new Thread(() -> {
            var result = false;
            while (!result && !shouldExitThread.get()) {
                result = condition.getAsBoolean();
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (result) {
                countDownLatch.countDown();
            }
        }, "Wait condition thread").start();

        if (!countDownLatch.await(timeout, TimeUnit.MILLISECONDS)) {
            shouldExitThread.set(true);
            throw new TimeoutException(String.format("Failed to evaluate condtion after %sms", timeout));
        }

    }

}
