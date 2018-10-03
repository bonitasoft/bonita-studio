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
package org.bonitasoft.studio.la.application.ui;

import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IAdaptable;

public class ApplicationPropertyTester extends PropertyTester {

    private static final String APP_FOLDER_PROPERTY = "isApplicationFolder";
    private static final String APP_FILE_PROPERTY = "isApplicationFile";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        ApplicationRepositoryStore repositoryStore = RepositoryManager.getInstance()
                .getRepositoryStore(ApplicationRepositoryStore.class);
        switch (property) {
            case APP_FILE_PROPERTY:
                return isApplicationFile((IAdaptable) receiver, repositoryStore);
            case APP_FOLDER_PROPERTY:
                return isApplicationFolder((IAdaptable) receiver, repositoryStore);
            default:
                return false;
        }
    }

    protected boolean isApplicationFolder(IAdaptable receiver, ApplicationRepositoryStore repositoryStore) {
        return Objects.equals(receiver.getAdapter(IFolder.class), repositoryStore.getResource());
    }

    protected boolean isApplicationFile(IAdaptable receiver, ApplicationRepositoryStore repositoryStore) {
        IFile file = receiver.getAdapter(IFile.class);
        if (file != null) {
            return repositoryStore.getChildren().stream()
                    .map(ApplicationFileStore::getResource)
                    .anyMatch(file::equals);
        }
        return false;
    }

}
