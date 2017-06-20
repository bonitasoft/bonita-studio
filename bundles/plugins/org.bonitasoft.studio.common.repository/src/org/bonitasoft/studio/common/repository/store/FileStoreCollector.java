/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.store;

import static com.google.common.collect.Sets.newHashSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Romain Bioteau
 */
public class FileStoreCollector implements IResourceVisitor {

    private final List<IResource> collectedResources = new ArrayList<>();
    private final Set<String> fileExtensions;
    private final IResource root;

    public FileStoreCollector(final IResource root, final String... fileExtensions) {
        this.fileExtensions = fileExtensions != null ? newHashSet(fileExtensions) : Collections.<String> emptySet();
        this.root = root;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.resources.IResourceVisitor#visit(org.eclipse.core.resources.IResource)
     */
    @Override
    public boolean visit(final IResource resource) throws CoreException {
        if (root.equals(resource)) {
            return true;
        }
        if (fileExtensions.isEmpty()) {
            if (resource.isHidden() || resource.getName().startsWith(".")) {
                return false;//skip resource
            }
            return collectedResources.add(resource);
        } else if (fileExtensions.contains(resource.getFileExtension())) {
            return collectedResources.add(resource);
        }
        return true;
    }

    public List<IResource> toList() {
        return Collections.unmodifiableList(collectedResources);
    }

}
