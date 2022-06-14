/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven;

import java.util.Objects;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public abstract class MavenModelOperation implements IWorkspaceRunnable {

    protected MavenProjectHelper helper = new MavenProjectHelper();

    private boolean disableAnalyze;

    protected boolean modelUpdated = false;

    protected Model readModel(IProject project) throws CoreException {
        return helper.getMavenModel(project);
    }

    protected void saveModel(IProject project, Model model, IProgressMonitor monitor) throws CoreException {
        if (modelUpdated) {
            helper.saveModel(project, model, false, monitor);

            if (!disableAnalyze && getRepositoryAccessor().hasActiveRepository()) {
                var projectDependenciesStore = getRepositoryAccessor().getCurrentRepository()
                        .map(IRepository::getProjectDependenciesStore)
                        .filter(Objects::nonNull)
                        .orElse(null);
                if (projectDependenciesStore != null) {
                    projectDependenciesStore.analyze(monitor);
                }
            }
            modelUpdated = false;
        }
    }

    public static void scheduleAnalyzeProjectDependenciesJob(RepositoryAccessor repositoryAccessor) {
        new WorkspaceJob("Analyze project dependencies") {

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                repositoryAccessor.getCurrentRepository()
                        .map(IRepository::getProjectDependenciesStore)
                        .ifPresent(projectDepStore -> projectDepStore.analyze(monitor));
                return Status.OK_STATUS;
            }

            @Override
            public boolean belongsTo(Object family) {
                return Objects.equals(ProjectDependenciesStore.ANALYZE_PPROJECT_DEPENDENCIES_FAMILY, family);
            }
        }.schedule();
    }

    public MavenModelOperation disableAnalyze() {
        this.disableAnalyze = true;
        return this;
    }

    protected IProject getCurrentProject() {
        return RepositoryManager.getInstance().getCurrentRepository().map(IRepository::getProject).orElse(null);
    }

    protected RepositoryAccessor getRepositoryAccessor() {
        return RepositoryManager.getInstance().getAccessor();
    }

    protected LocalDependenciesStore getLocalStore() {
        return getRepositoryAccessor().getCurrentRepository().map(IRepository::getLocalDependencyStore).orElse(null);
    }

    static Dependency createDependency(String groupId, String artifactId, String version, String scope) {
        Dependency dependency = new Dependency();
        dependency.setArtifactId(artifactId);
        dependency.setGroupId(groupId);
        dependency.setVersion(version);
        dependency.setScope(scope);
        return dependency;
    }

}
