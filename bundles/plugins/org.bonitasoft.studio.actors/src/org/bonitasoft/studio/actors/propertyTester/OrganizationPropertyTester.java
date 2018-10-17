/**
 * Copyright (C) 2018 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.propertyTester;

import java.util.Objects;

import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;


public class OrganizationPropertyTester extends PropertyTester {


    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (Objects.equals("isOrganizationFolder", property)) {
            IResource adapter = ((IAdaptable) receiver).getAdapter(IResource.class);
            return Objects.equals(
                    RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class).getResource(),
                    adapter);
        } else if (Objects.equals("isOrganizationFile", property)) {
            IResource adapter = ((IAdaptable) receiver).getAdapter(IResource.class);
            return adapter != null && RepositoryManager.getInstance().getCurrentRepository()
                    .getFileStore(adapter) instanceof OrganizationFileStore;

        }
        return false;

    }

}
