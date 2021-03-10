/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.maven;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.maven.archetype.catalog.Archetype;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.AbstractFolderRepositoryStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.internal.IMavenConstants;

public abstract class CustomPageProjectRepositoryStore<T extends CustomPageProjectFileStore>
        extends AbstractFolderRepositoryStore<T> {

    private MarkerUpdateListener markerUpdateListener;

    @Override
    public void createRepositoryStore(IRepository repository) {
        super.createRepositoryStore(repository);
        importProjects();
        markerUpdateListener = new MarkerUpdateListener(this);
        ResourcesPlugin.getWorkspace().addResourceChangeListener(markerUpdateListener);
    }
    
    private void importProjects() {
        getChildren().stream()
                .filter(c -> !c.getProject().exists())
                .forEach(c -> {
                    try {
                        c.importProject();
                    } catch (ImportProjectException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public T importArchiveData(String folderName, List<ImportArchiveData> importArchiveData,
            IProgressMonitor monitor) throws CoreException {
        final T fileStore = super.importArchiveData(folderName, importArchiveData, monitor);
        try {
            if (!fileStore.getProject().exists()) {
                fileStore.importProject();
            }
        } catch (final ImportProjectException e) {
            BonitaStudioLog.error(e);
        }
        return fileStore;
    }

    @Override
    public void close() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(markerUpdateListener);
        for (final CustomPageProjectFileStore child : getChildren()) {
            //Remove related project at workspace root
            child.removeProject();
        }
    }
    
    @Override
    public void repositoryUpdated() {
        importProjects();
    }

    @Override
    public void refresh() {
        final IFolder folder = getResource();
        if (!folder.isSynchronized(IResource.DEPTH_ONE)) {
            try {
                folder.refreshLocal(IResource.DEPTH_ONE, AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    public List<T> getChildren() {
        refresh();

        final List<T> result = new ArrayList<>();
        final IFolder folder = getResource();
        try {
            for (final IResource r : folder.members()) {
                if (r instanceof IFolder && !r.isHidden() && ((IFolder) r).getFile(IMavenConstants.POM_FILE_NAME).exists()) {
                    result.add(createRepositoryFileStore(r.getName()));
                }
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return result;
    }
    
    @Override
    protected List<IResource> listChildren() throws CoreException {
        return super.listChildren().stream()
                .filter(IFolder.class::isInstance)
                .collect(Collectors.toList());
    }

    public Optional<T> findByCustomPageId(String pageId) {
        return getChildren().stream()
                .filter(project -> Objects.equals(project.getPageId(), pageId))
                .findFirst();
    }

    public abstract void refreshMarkers() throws CoreException;

    public abstract Archetype getArchetype();

}
