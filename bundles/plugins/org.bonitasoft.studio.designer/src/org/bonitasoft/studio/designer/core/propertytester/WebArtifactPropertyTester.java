/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.propertytester;

import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IAdaptable;

public class WebArtifactPropertyTester extends PropertyTester {

    public final static String WEB_PAGE_FOLDER_PROPERTY = "isWebPageFolder";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        WebPageRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(WebPageRepositoryStore.class);
        if (Objects.equals(property, WEB_PAGE_FOLDER_PROPERTY)) {
            return isWebPageFolder((IAdaptable) receiver, store);
        }
        return false;
    }

    protected boolean isWebPageFolder(IAdaptable receiver, WebPageRepositoryStore repositoryStore) {
        return Objects.equals(receiver.getAdapter(IFolder.class), repositoryStore.getResource());
    }

}
