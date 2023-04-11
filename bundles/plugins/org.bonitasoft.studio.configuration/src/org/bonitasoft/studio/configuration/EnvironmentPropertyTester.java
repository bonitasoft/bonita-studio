/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.configuration;

import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IAdaptable;

public class EnvironmentPropertyTester extends PropertyTester {

    private static final String ENV_FOLDER_PROPERTY = "isEnvironmentFolder";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        var repositoryStore = RepositoryManager.getInstance()
                .getRepositoryStore(EnvironmentRepositoryStore.class);
        if (ENV_FOLDER_PROPERTY.equals(property)) {
            return isEnvFolder((IAdaptable) receiver, repositoryStore);
        }
        return false;
    }

    protected boolean isEnvFolder(IAdaptable receiver, EnvironmentRepositoryStore repositoryStore) {
        return Objects.equals(receiver.getAdapter(IFolder.class), repositoryStore.getResource());
    }

}
