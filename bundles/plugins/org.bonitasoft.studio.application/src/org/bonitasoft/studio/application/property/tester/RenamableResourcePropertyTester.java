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
package org.bonitasoft.studio.application.property.tester;

import java.util.Objects;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;

public class RenamableResourcePropertyTester extends PropertyTester {

    private FileStoreFinder fileStoreFinder;

    public RenamableResourcePropertyTester() {
        fileStoreFinder = new FileStoreFinder();
    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        Repository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        IResource resource = ((IAdaptable) receiver).getAdapter(IResource.class);
        if (Objects.equals(resource, currentRepository.getProject())) {
            return true;
        }
        if (resource != null) {
            return fileStoreFinder.findElementToRename(resource, currentRepository).isPresent();
        }
        return false;
    }

}
