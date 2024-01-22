/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.IBonitaProjectListenerProvider;
import org.bonitasoft.studio.common.repository.core.maven.contribution.InstallBonitaMavenArtifactsOperation;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * @author Romain Bioteau
 */
public class RepositoryManager {

    private static final String REPOSITORY_FACTORY_IMPLEMENTATION_ID = "org.bonitasoft.studio.repositoryFactory";
    private static final String PROJECT_LISTENER_PROVIDER_ID = "org.bonitasoft.studio.common.repository.projectListenerProvider";
    private static final String PRIORITY = "priority";
    private static final String CLASS = "class";

    private static RepositoryManager INSTANCE;

    private List<IBonitaProjectListener> projectListeners = new ArrayList<>();
    private IRepository repository;
    private IPreferenceStore preferenceStore;
    private IConfigurationElement repositoryImplementationElement;
    private RepositoryAccessor repositoryAccessor;
    private boolean requiredDependenciesInstalled = false;

    private RepositoryManager() {
        final IConfigurationElement[] repositoryFactories = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(
                        REPOSITORY_FACTORY_IMPLEMENTATION_ID);
        final List<IConfigurationElement> sortedElems = sortByPriority(repositoryFactories);
        if (!sortedElems.isEmpty()) {
            repositoryImplementationElement = sortedElems.get(0); //Higher element
            preferenceStore = CommonRepositoryPlugin.getDefault().getPreferenceStore();
            IConfigurationElement[] listenerProviders = BonitaStudioExtensionRegistryManager.getInstance()
                    .getConfigurationElements(
                            PROJECT_LISTENER_PROVIDER_ID);
            for (IConfigurationElement elem : listenerProviders) {
                try {
                    IBonitaProjectListenerProvider provider = (IBonitaProjectListenerProvider) elem
                            .createExecutableExtension("class");
                    provider.getListeners().stream().forEach(this::addBonitaProjectListener);
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
            /*
             * Do not set current repository right now,because workbench may be not fully initiated yet.
             * Assign it lazily instead.
             */
        }
    }

    private List<IConfigurationElement> sortByPriority(final IConfigurationElement[] elements) {
        final List<IConfigurationElement> sortedConfigElems = new ArrayList<>();
        for (final IConfigurationElement elem : elements) {
            sortedConfigElems.add(elem);
        }

        Collections.sort(sortedConfigElems, (e1, e2) -> {
            int p1 = 0;
            int p2 = 0;
            try {
                p1 = Integer.parseInt(e1.getAttribute(PRIORITY));
            } catch (final NumberFormatException e3) {
                p1 = 0;
            }
            try {
                p2 = Integer.parseInt(e2.getAttribute(PRIORITY));
            } catch (final NumberFormatException e4) {
                p2 = 0;
            }
            return p2 - p1; //Highest Priority first
        });
        return sortedConfigElems;

    }

    public AbstractRepository newRepository(final String projectId) {
        try {
            final IRepositoryFactory repositoryFactory = (IRepositoryFactory) repositoryImplementationElement
                    .createExecutableExtension(CLASS);
            AbstractRepository newRepository = repositoryFactory.newRepository(projectId);
            for (IBonitaProjectListener listener : getBonitaProjectListeners()) {
                newRepository.addProjectListener(listener);
            }
            return newRepository;
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    public static synchronized RepositoryManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RepositoryManager();
        }
        return INSTANCE;
    }

    public IPreferenceStore getPreferenceStore() {
        return preferenceStore;
    }

    public Optional<IRepository> getCurrentRepository() {
        if (repository == null) {
            // try and initiate current repository
            String currentRepository = preferenceStore.getString(RepositoryPreferenceConstant.CURRENT_REPOSITORY);
            if (currentRepository != null && !currentRepository.isEmpty()) {
                repository = newRepository(currentRepository);
            }
        }
        return Optional.ofNullable(repository);
    }

    public <T> T getRepositoryStore(final Class<T> storeClass) {
        return getCurrentRepository()
                .map(repo -> repo.getRepositoryStore(storeClass))
                .map(storeClass::cast)
                .orElse(null);
    }

    public IRepository getRepository(final String projectId) {
        var currentRepository = getCurrentRepository()
                .filter(repo -> Objects.equals(repo.getProjectId(), projectId))
                .orElse(null);
        if (currentRepository != null) {
            return currentRepository;
        }

        var project = BonitaProject.create(projectId);
        var appProject = project.getAppProject();
        if (!appProject.exists()) {
            return null;
        }
        return newRepository(projectId);
    }

    public List<IRepository> getAllRepositories() {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        return Stream.of(workspace.getRoot().getProjects())
                .map(project -> BonitaProject.create(project.getName()))
                .filter(project -> isValidBonitaProject(project))
                .map(project -> toRepository(project))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private IRepository toRepository(BonitaProject project) {
        var repository = getCurrentRepository();
        if (repository.isEmpty() || !Objects.equals(project.getId(), repository.get().getProjectId())) {
            return newRepository(project.getId());
        } else {
            return repository.orElse(null);
        }
    }

    private boolean isValidBonitaProject(BonitaProject project) {
        return project.getAppProject().exists();
    }

    public void setRepository(final String projectId,
            final IProgressMonitor monitor) {
        var currentRepository = getCurrentRepository()
                .orElse(null);
        if (currentRepository != null && Objects.equals(currentRepository.getProjectId(), projectId)) {
            return;
        } else if (currentRepository != null && currentRepository.getProject().isOpen()) {
            try {
                getCurrentProject().orElseThrow().close(monitor);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        currentRepository = getRepository(projectId);
        if (currentRepository == null) {
            currentRepository = newRepository(projectId);
        }
        try {
            var metadata = ProjectMetadata.read(currentRepository.getProject(), monitor);
            monitor.setTaskName(Messages.creatingNewProject);
            currentRepository.create(metadata, monitor);
            setCurrentRepository(currentRepository);
            var project = BonitaProject.create(projectId);
            project.open(monitor);
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    public Optional<IRepositoryStore<? extends IRepositoryFileStore>> getRepositoryStore(Object element) {
        Object resource = element instanceof IJavaElement ? ((IJavaElement) element).getResource() : element;
        var currentRepository = getCurrentRepository().orElse(null);
        if (!hasActiveRepository() || !currentRepository.isLoaded()
                || !currentRepository.getProject().isOpen()) {
            return Optional.empty();
        }
        return currentRepository.getAllStores().stream()
                .filter(repo -> java.util.Objects.equals(resource, repo.getResource()))
                .findFirst();
    }

    public void addBonitaProjectListener(IBonitaProjectListener listener) {
        projectListeners.add(listener);
    }

    public List<IBonitaProjectListener> getBonitaProjectListeners() {
        return Collections.unmodifiableList(projectListeners);
    }

    public boolean hasActiveRepository() {
        return getCurrentRepository().isPresent();
    }

    public void setCurrentRepository(IRepository repository) {
        this.repository = repository;
    }

    public RepositoryAccessor getAccessor() {
        return repositoryAccessor;
    }

    public void setRepositoryAccessor(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    public synchronized IRepository switchToRepository(
            final String projectId,
            IProgressMonitor monitor) {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        var currentRepository = RepositoryManager.getInstance()
                .getCurrentRepository();
        if (currentRepository.filter(repo -> Objects.equals(repo.getProjectId(), projectId)).isPresent()) {
            return currentRepository.get();
        }
        monitor.beginTask(Messages.team_switchingProject,
                IProgressMonitor.UNKNOWN);
        try {
            WorkspaceModifyOperation workspaceModifyOperation = new WorkspaceModifyOperation() {

                @Override
                protected void execute(final IProgressMonitor monitor)
                        throws CoreException, InvocationTargetException, InterruptedException {
                    BonitaStudioLog.info("Switching project to "
                            + projectId, CommonRepositoryPlugin.PLUGIN_ID);
                    RepositoryManager.getInstance().setRepository(projectId, new NullProgressMonitor());
                    BonitaStudioLog.info(
                            "Project switched to " + projectId,
                            CommonRepositoryPlugin.PLUGIN_ID);
                }
            };
            Job.getJobManager().join(RepositoryManager.class, new NullProgressMonitor());
            workspaceModifyOperation.run(new NullProgressMonitor());
            Display.getDefault().asyncExec(
                    PlatformUtil::openDashboardIfNoOtherEditorOpen);
            Display.getDefault().asyncExec(
                    AbstractFileStore::refreshExplorerView);
        } catch (final InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        }

        return RepositoryManager.getInstance()
                .getCurrentRepository().orElseThrow();

    }

    public Optional<BonitaProject> getCurrentProject() {
        return getCurrentRepository()
                .map(repo -> BonitaProject.create(repo.getProjectId()))
                .filter(Objects::nonNull);
    }

    /**
     * Ensure Bonita artifacts are properly installed in user local repository before creating a project
     * 
     * @param monitor
     * @throws CoreException
     */
    public void installRequiredMavenDependencies(IProgressMonitor monitor) throws CoreException {
        // Ensure Bonita artifacts are properly installed in user local repository before creating a project
        if (!requiredDependenciesInstalled) {
            monitor.subTask("");
            monitor.beginTask("Install required artifacts in local repository...", IProgressMonitor.UNKNOWN);
            new InstallBonitaMavenArtifactsOperation(MavenPlugin.getMaven().getLocalRepository()).execute(monitor);
            requiredDependenciesInstalled = true;
        }
    }
}
