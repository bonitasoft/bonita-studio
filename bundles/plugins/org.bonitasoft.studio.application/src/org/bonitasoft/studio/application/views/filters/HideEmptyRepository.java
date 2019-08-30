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
package org.bonitasoft.studio.application.views.filters;

import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class HideEmptyRepository extends ViewerFilter {

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        IRepository repository = repositoryManager.getCurrentRepository();
        if (repositoryManager.hasActiveRepository() && repository.isLoaded()) {
            IRepositoryStore<?> store = repositoryManager.getRepositoryStore(element).orElse(null);
            if (store != null) {
                try {
                    IFolder resource = store.getResource();
                    IResource[] members = resource.members();
                    if (store instanceof WebWidgetRepositoryStore) {
                        return Stream.of(members)
                                .filter(r -> !r.getName().startsWith("."))
                                .anyMatch(r -> !r.getName().startsWith("pb"));

                    }
                    if (store instanceof WebPageRepositoryStore) {
                        return Stream.of(members)
                                .anyMatch(r -> !r.getName().startsWith("."));
                    }
                    if (store instanceof WebFragmentRepositoryStore) {
                        return Stream.of(members)
                                .anyMatch(r -> !r.getName().startsWith("."));
                    }
                    return members.length > 0;
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        return true;
    }

}
