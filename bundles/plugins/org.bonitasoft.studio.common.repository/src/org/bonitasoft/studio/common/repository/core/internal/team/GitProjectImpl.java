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
package org.bonitasoft.studio.common.repository.core.internal.team;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.repository.core.team.GitProject;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.eclipse.egit.core.op.CommitOperation;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.egit.core.op.DisconnectProviderOperation;
import org.eclipse.egit.ui.internal.commit.CommitHelper;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.m2e.core.MavenPlugin;

public class GitProjectImpl implements GitProject {

    private IProject project;

    public GitProjectImpl(IProject project) {
        this.project = project;
    }

    @Override
    public IRunnableWithProgress newConnectProviderOperation() throws CoreException {
        return new IRunnableWithProgress() {

            @Override
            public void run(final IProgressMonitor monitor)
                    throws InvocationTargetException {
                File gitDir = getGitDir();
                try (Repository repository = FileRepositoryBuilder
                        .create(gitDir)) {
                    if (!repository.getDirectory().exists()) {
                        repository.create();
                        if (!gitDir.toString().contains("..")) //$NON-NLS-1$
                            project.refreshLocal(IResource.DEPTH_ONE,
                                    new NullProgressMonitor());
                        RepositoryUtil.INSTANCE.addConfiguredRepository(gitDir);
                        var gitIgnore = project.getFile(Constants.GITIGNORE_FILENAME);
                        if (!gitIgnore.exists()) {
                            gitIgnore.create(new ByteArrayInputStream(new byte[0]), true, new NullProgressMonitor());
                        }
                    }
                    var op = new ConnectProviderOperation(project);
                    op.execute(monitor);
                    for (var project : findEmbeddedProjects(project)) {
                        ConnectProviderOperation connectProviderOperation = new ConnectProviderOperation(project,
                                gitDir);
                        connectProviderOperation.execute(monitor);
                    }
                } catch (CoreException | IOException ce) {
                    throw new InvocationTargetException(ce);
                }
            }
        };
    }

    @Override
    public IRunnableWithProgress newDiconnectProviderOperation() throws CoreException {
        return new IRunnableWithProgress() {

            @Override
            public void run(final IProgressMonitor monitor)
                    throws InvocationTargetException {
                var projects = new ArrayList<IProject>();
                projects.add(project);
                findEmbeddedProjects(project).forEach(projects::add);
                var op = new DisconnectProviderOperation(projects);
                try {
                    op.execute(monitor);
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                }
            }
        };
    }

    @Override
    public void createDefaultIgnoreFile() throws CoreException {
        var facade = MavenPlugin.getMavenProjectRegistry().getProject(project);
        if (facade != null) {
            var mavenProject = facade.getMavenProject(new NullProgressMonitor());
            if (mavenProject.getModules().isEmpty()) {
                createDefaultAppIgnoreFile(project);
            } else {
                createDefaultParentIgnoreFile();
                var projectId = projectIdOf(mavenProject);
                var appProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectId + "-app");
                createDefaultAppIgnoreFile(appProject);
            }
        }

    }

    private CharSequence projectIdOf(MavenProject mavenProject) {
        var parentArtifact = mavenProject.getArtifactId();
        return parentArtifact.subSequence(0, parentArtifact.length() - "-parent".length());
    }

    private void createDefaultParentIgnoreFile() throws CoreException {
        var parentGitIgnoreFile = project.getFile(Constants.DOT_GIT_IGNORE);
        try (var is = GitProject.getParentGitIgnoreTemplate().openStream()) {
            if (parentGitIgnoreFile.exists()) {
                parentGitIgnoreFile.setContents(is, true, true, new NullProgressMonitor());
            } else {
                parentGitIgnoreFile.create(is, true, new NullProgressMonitor());
            }
        } catch (IOException e) {
            throw new CoreException(Status.error("Failed create parent project .gitignore file.", e));
        }
    }

    private void createDefaultAppIgnoreFile(IProject project) throws CoreException {
        IFile gitIgnoreFile = project.getFile(Constants.DOT_GIT_IGNORE);
        try (var content = GitProject.getGitignoreTemplateFileURL().openStream()) {
            if (gitIgnoreFile.exists()) {
                gitIgnoreFile.setContents(content, true, true, new NullProgressMonitor());
            } else {
                gitIgnoreFile.create(content, true, new NullProgressMonitor());
            }
        } catch (IOException e) {
            throw new CoreException(Status.error("Failed create project .gitignore file.", e));
        }
    }

    @Override
    public File getGitDir() {
        return new File(project.getLocation().toFile().getAbsolutePath(),
                Constants.DOT_GIT);
    }

    @Override
    public void commitAll(String commitMessage, IProgressMonitor monitor) throws CoreException {
        var repository = getAdapter(Repository.class);
        try (Git git = new Git(repository)) {
            git.add().addFilepattern(".").call();
            var commitHelper = new CommitHelper(repository);
            var commitOperation = new CommitOperation(repository,
                    commitHelper.getAuthor(),
                    commitHelper.getCommitter(),
                    commitMessage);
            commitOperation.setCommitAll(true);
            commitOperation.execute(monitor);
        } catch (GitAPIException e) {
            throw new CoreException(Status.error("Failed to commit", e));
        }
    }

    protected List<IProject> findEmbeddedProjects(IProject project) {
        IPath parentLocation = project.getLocation();
        return Stream.of(project.getWorkspace().getRoot().getProjects())
                .filter(Objects::nonNull)
                .filter(p -> p.isOpen())
                .filter(p -> !project.equals(p))
                .filter(p -> p.getLocation() != null)
                .filter(p -> parentLocation.isPrefixOf(p.getLocation()))
                .collect(Collectors.toList());
    }

    @SuppressWarnings({ "unchecked", "restriction" })
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (Repository.class.equals(adapter)) {
            return (T) ResourceUtil.getRepository(project);
        }
        return null;
    }

}
