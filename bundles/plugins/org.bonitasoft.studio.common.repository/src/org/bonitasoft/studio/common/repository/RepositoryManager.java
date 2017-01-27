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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * @author Romain Bioteau
 */
public class RepositoryManager {

    private static final String REPOSITORY_FACTORY_IMPLEMENTATION_ID = "org.bonitasodt.studio.repositoryFactory";
    private static final String PRIORITY = "priority";
    private static final String CLASS = "class";

    private static RepositoryManager INSTANCE;

    private Repository repository;
    private IPreferenceStore preferenceStore;
    private IConfigurationElement repositoryImplementationElement;

    private RepositoryManager() {
        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                REPOSITORY_FACTORY_IMPLEMENTATION_ID);
        final List<IConfigurationElement> sortedElems = sortByPriority(elements);
        if (!sortedElems.isEmpty()) {
            repositoryImplementationElement = sortedElems.get(0); //Higher element
            preferenceStore = CommonRepositoryPlugin.getDefault().getPreferenceStore();
            final String currentRepository = preferenceStore.getString(RepositoryPreferenceConstant.CURRENT_REPOSITORY);
            repository = createRepository(currentRepository, false);
        }
    }

    private List<IConfigurationElement> sortByPriority(final IConfigurationElement[] elements) {
        final List<IConfigurationElement> sortedConfigElems = new ArrayList<IConfigurationElement>();
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

    public Repository createRepository(final String name, final boolean migrationEnabled) {
        try {
            final IRepositoryFactory repositoryFactory = (IRepositoryFactory) repositoryImplementationElement
                    .createExecutableExtension(CLASS);
            return repositoryFactory.newRepository(name, migrationEnabled);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    /**
     * @deprecated See {@link RepositoryAccessor}
     */
    @Deprecated
    public static RepositoryManager getInstance() {
        if (RepositoryManager.INSTANCE == null) {
            synchronized (RepositoryManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RepositoryManager();
                }
            }
        }
        return INSTANCE;
    }

    public IPreferenceStore getPreferenceStore() {
        return preferenceStore;
    }

    public Repository getCurrentRepository() {
        return repository;
    }

    public <T> T getRepositoryStore(final Class<T> storeClass) {
        return storeClass.cast(repository.getRepositoryStore(storeClass));
    }

    public Repository getRepository(final String repositoryName) {
        return getRepository(repositoryName, false);
    }

    public Repository getRepository(final String repositoryName, final boolean migrationEnabled) {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProject project = workspace.getRoot().getProject(repositoryName);
        if (project == null || !project.exists()) {
            return null;
        }
        boolean toClose = false;
        try {
            if (!project.isAccessible()) {
                project.open(Repository.NULL_PROGRESS_MONITOR);
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
                    project.close(Repository.NULL_PROGRESS_MONITOR);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        return createRepository(repositoryName, migrationEnabled);
    }

    public List<IRepository> getAllRepositories() {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final List<IRepository> result = new ArrayList<IRepository>();
        try {
            workspace.run(new IWorkspaceRunnable() {

                @Override
                public void run(final IProgressMonitor monitor) throws CoreException {
                    result.add(repository);
                    workspace.getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
                    workspace.getRoot().getProjects();
                    for (final IProject p : workspace.getRoot().getProjects()) {
                        if (p.exists() && p.getLocation() != null && p.getLocation().toFile().exists()) {
                            try {
                                boolean close = false;
                                if (!p.isOpen()) {
                                    p.open(Repository.NULL_PROGRESS_MONITOR);
                                    close = true;
                                }
                                if (p.getDescription().hasNature(BonitaProjectNature.NATURE_ID)) {
                                    if (!p.getName().equals(repository.getName())) {
                                        result.add(createRepository(p.getName(), false));
                                    }
                                }
                                if (close) {
                                    p.close(Repository.NULL_PROGRESS_MONITOR);
                                }
                            } catch (final CoreException e) {
                                BonitaStudioLog.error(e);
                            }
                        }
                    }
                }
            }, Repository.NULL_PROGRESS_MONITOR);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
        }

        return result;
    }

    public void setRepository(final String repositoryName, final IProgressMonitor monitor) {
        setRepository(repositoryName, false, monitor);
    }

    public void setRepository(final String repositoryName, final boolean migrationEnabled, final IProgressMonitor monitor) {
        if (repository != null && repository.getName().equals(repositoryName)) {
            return;
        } else {
            repository.close();
        }
        repository = getRepository(repositoryName, migrationEnabled);
        if (repository == null) {
            repository = createRepository(repositoryName, migrationEnabled);
        }
        repository.create(monitor);
        repository.open(monitor);
        preferenceStore.setValue(RepositoryPreferenceConstant.CURRENT_REPOSITORY, repositoryName);
        try {
            ((ScopedPreferenceStore) preferenceStore).save();
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
    }

}
