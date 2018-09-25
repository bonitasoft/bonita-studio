/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.views.provider;

import java.io.IOException;
import java.util.Optional;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.internal.ui.navigator.JavaNavigatorLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public class BonitaExplorerLabelProvider extends JavaNavigatorLabelProvider {

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.internal.ui.navigator.JavaNavigatorLabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element) {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        if (!repositoryManager.getCurrentRepository().isLoaded()) {
            return super.getImage(element);
        }
        if (UIDArtifactFilters.isUIDArtifactFrom(element, "web_page")) {
            return repositoryManager.getRepositoryStore(WebPageRepositoryStore.class).getIcon();
        }
        if (UIDArtifactFilters.isUIDArtifactFrom(element, "web_widgets")) {
            return repositoryManager.getRepositoryStore(WebWidgetRepositoryStore.class).getIcon();
        }
        if (UIDArtifactFilters.isUIDArtifactFrom(element, "web_fragments")) {
            return repositoryManager.getRepositoryStore(WebFragmentRepositoryStore.class).getIcon();
        }
        Optional<IRepositoryStore<? extends IRepositoryFileStore>> repositoryStore = repositoryManager
                .getRepositoryStore(element);
        if (repositoryStore.isPresent()) {
            return repositoryStore.get().getIcon();
        }
        return super.getImage(element);
    }

    @Override
    public StyledString getStyledText(Object element) {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        if (!repositoryManager.getCurrentRepository().isLoaded()) {
            return super.getStyledText(element);
        }
        Optional<IRepositoryStore<? extends IRepositoryFileStore>> repositoryStore = repositoryManager
                .getRepositoryStore(element);
        if (repositoryStore.isPresent()) {
            return new StyledString(repositoryStore.get().getDisplayName());
        }
        IRepositoryFileStore fStore = asFileStore(element, repositoryManager);
        if (fStore != null) {
            return fStore.getStyledString();
        }
        return super.getStyledText(element);
    }

    private IRepositoryFileStore asFileStore(Object element, RepositoryManager repositoryManager) {
        try {
            if (element instanceof IResource) {
                return repositoryManager.getCurrentRepository()
                        .asRepositoryFileStore(((IResource) element).getLocation().toFile().toPath(), false);
            }
        } catch (IOException | CoreException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    public static boolean isFolder(Object element, String folderName) {
        return element instanceof IFolder
                && ((IFolder) element).getParent() != null
                && Path.fromOSString(folderName).equals(((IFolder) element).getProjectRelativePath())
                && !((IFolder) element).getName().startsWith(".");
    }

}
