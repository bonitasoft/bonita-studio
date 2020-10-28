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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.IBonitaProjectListenerProvider;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.preference.IPreferenceStore;

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
    private AbstractRepository repository;
    private IPreferenceStore preferenceStore;
    private IConfigurationElement repositoryImplementationElement;

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
            String currentRepository = preferenceStore.getString(RepositoryPreferenceConstant.CURRENT_REPOSITORY);
            if (currentRepository == null || currentRepository.isEmpty()) {
                currentRepository = Messages.defaultRepositoryName;
            }
            repository = createRepository(currentRepository, false);
        }
    }

    private List<IConfigurationElement> sortByPriority(final IConfigurationElement[] elements) {
        final List<IConfigurationElement> sortedConfigElems = new ArrayList<>();
        for (final IConfigurationElement elem : elements) {
            sortedConfigElems.add(elem);
        }

        Collections.sort(sortedConfigElems, new Comparator<IConfigurationElement>() {

            @Override
            public int compare(final IConfigurationElement e1, final IConfigurationElement e2) {
                int p1 = 0;
                int p2 = 0;
                try {
                    p1 = Integer.parseInt(e1.getAttribute(PRIORITY));
                } catch (final NumberFormatException e) {
                    p1 = 0;
                }
                try {
                    p2 = Integer.parseInt(e2.getAttribute(PRIORITY));
                } catch (final NumberFormatException e) {
                    p2 = 0;
                }
                return p2 - p1; //Highest Priority first
            }
        });
        return sortedConfigElems;

    }

    public AbstractRepository createRepository(final String name, final boolean migrationEnabled) {
        try {
            final IRepositoryFactory repositoryFactory = (IRepositoryFactory) repositoryImplementationElement
                    .createExecutableExtension(CLASS);
            AbstractRepository newRepository = repositoryFactory.newRepository(name, migrationEnabled);
            for (IBonitaProjectListener listener : projectListeners) {
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

    public AbstractRepository getCurrentRepository() {
        return repository;
    }

    public <T> T getRepositoryStore(final Class<T> storeClass) {
        return storeClass.cast(getCurrentRepository().getRepositoryStore(storeClass));
    }

    public AbstractRepository getRepository(final String repositoryName) {
        return getRepository(repositoryName, false);
    }

    public AbstractRepository getRepository(final String repositoryName, final boolean migrationEnabled) {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProject project = workspace.getRoot().getProject(repositoryName);
        if (project == null || !project.exists()) {
            return null;
        }
        boolean toClose = false;
        try {
            if (!project.isAccessible()) {
                project.open(AbstractRepository.NULL_PROGRESS_MONITOR);
                toClose = true;
            }
            if (!project.hasNature(BonitaProjectNature.NATURE_ID)) {
                return null;
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
            return null;
        } finally {
            if (toClose) {
                try {
                    project.close(AbstractRepository.NULL_PROGRESS_MONITOR);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        return createRepository(repositoryName, migrationEnabled);
    }

    public List<IRepository> getAllRepositories() {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final List<IRepository> result = new ArrayList<>();
        try {
            workspace.run(new IWorkspaceRunnable() {

                @Override
                public void run(final IProgressMonitor monitor) throws CoreException {
                    result.add(repository);
                    workspace.getRoot().refreshLocal(IResource.DEPTH_ONE, null);
                    for (final IProject p : workspace.getRoot().getProjects()) {
                        if (p.exists() && p.getLocation() != null && p.getLocation().toFile().exists()) {
                            try {
                                boolean close = false;
                                if (!p.isOpen()) {
                                    p.open(AbstractRepository.NULL_PROGRESS_MONITOR);
                                    close = true;
                                }
                                if (p.getDescription().hasNature(BonitaProjectNature.NATURE_ID)) {
                                    if (!p.getName().equals(repository.getName())) {
                                        result.add(createRepository(p.getName(), false));
                                    }
                                }
                                if (close) {
                                    p.close(AbstractRepository.NULL_PROGRESS_MONITOR);
                                }
                            } catch (final CoreException e) {
                                BonitaStudioLog.error(e);
                            }
                        }
                    }
                }
            }, AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
        }

        return result;
    }

    public void setRepository(final String repositoryName, final IProgressMonitor monitor) {
        setRepository(repositoryName, false, monitor);
    }

    public void setRepository(final String repositoryName, final boolean migrationEnabled, final IProgressMonitor monitor) {
        AbstractRepository currentRepository = getCurrentRepository();
        if (currentRepository != null && currentRepository.getName().equals(repositoryName)) {
            return;
        } else if (currentRepository != null) {
            currentRepository.close();
        }
        currentRepository = getRepository(repositoryName, migrationEnabled);
        if (currentRepository == null) {
            currentRepository = createRepository(repositoryName, migrationEnabled);
        }
        currentRepository.create(monitor);
        currentRepository.open(monitor);
        this.repository = currentRepository;
    }

    public Optional<IRepositoryStore<? extends IRepositoryFileStore>> getRepositoryStore(Object element) {
        Object resource = element instanceof IJavaElement ? ((IJavaElement) element).getResource() : element;
        return getCurrentRepository().getAllStores().stream()
                .filter(repo -> java.util.Objects.equals(resource, repo.getResource()))
                .findFirst();
    }

    public void addBonitaProjectListener(IBonitaProjectListener listener) {
        projectListeners.add(listener);
    }

    public List<IBonitaProjectListener> getBonitaProjectListeners() {
        return projectListeners;
    }

    public boolean hasActiveRepository() {
        return repository != null;
    }

    public void setCurrentRepository(AbstractRepository repository) {
        this.repository = repository;
    }

}
