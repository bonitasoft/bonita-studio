/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.ui.editor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.la.ui.editor.layout.LayoutDescriptor;
import org.bonitasoft.studio.la.ui.editor.layout.LayoutProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;

public class LayoutPageListener implements IResourceChangeListener {

    private final IObservableList resourceList;
    private final WebPageRepositoryStore store;

    public LayoutPageListener(IObservableList resourceList, WebPageRepositoryStore store) {
        this.resourceList = resourceList;
        this.store = store;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
     */
    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        try {
            event.getDelta().accept(this::updateResourceList);
        } catch (final CoreException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean updateResourceList(IResourceDelta delta) {
        final IResource resource = delta.getResource();
        if ("json".equals(delta.getResource().getFileExtension())
                && store.getResource().getLocation().isPrefixOf(resource.getLocation())
                && (delta.getKind() == IResourceDelta.ADDED || delta.getKind() == IResourceDelta.REMOVED)) {
            final List<LayoutDescriptor> newDescriptors = store.getChildren().stream()
                    .filter(fStore -> WebPageFileStore.LAYOUT_TYPE.equals(fStore.getType()))
                    .map(fStore -> new LayoutDescriptor(LayoutProvider.CUSTOMPAGE_PREFIX + fStore.getDisplayName(),
                            fStore.getDisplayName(), true))
                    .collect(Collectors.toList());

            if (delta.getKind() == IResourceDelta.ADDED) {
                resourceList.getRealm().exec(() -> resourceList.addAll(
                        newDescriptors.stream().filter(ld -> !resourceList.contains(ld)).collect(Collectors.toList())));

            }

            if (delta.getKind() == IResourceDelta.REMOVED) {
                resourceList.getRealm().exec(() -> resourceList
                        .removeAll((Collection) resourceList.stream()
                                .filter(ld -> ((LayoutDescriptor) ld).isPersisted())
                                .filter(ld -> !newDescriptors.contains(ld))
                                .collect(Collectors.toList())));
            }

        }
        return true;
    }

}
