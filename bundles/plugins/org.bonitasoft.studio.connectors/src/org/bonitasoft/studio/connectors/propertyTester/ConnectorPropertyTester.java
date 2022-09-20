/**
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.propertyTester;

import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorDefFileStore;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplFileStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;

public class ConnectorPropertyTester extends PropertyTester {

    public static final String CONNECTOR_DEF_FOLDER_PROPERTY = "isConnectorDefFolder";
    public static final String CONNECTOR_DEF_FILE_PROPERTY = "isConnectorDefFile";
    public static final String CONNECTOR_IMPL_FOLDER_PROPERTY = "isConnectorImplFolder";
    public static final String CONNECTOR_IMPL_FILE_PROPERTY = "isConnectorImplFile";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {

        RepositoryManager manager = RepositoryManager.getInstance();
        ConnectorDefRepositoryStore defRepositoryStore = manager.getRepositoryStore(ConnectorDefRepositoryStore.class);
        ConnectorImplRepositoryStore implRepositoryStore = manager.getRepositoryStore(ConnectorImplRepositoryStore.class);

        switch (property) {
            case CONNECTOR_DEF_FOLDER_PROPERTY:
                return isConnectorDefFolder((IAdaptable) receiver, defRepositoryStore);
            case CONNECTOR_DEF_FILE_PROPERTY:
                return isConnectorDefFile((IAdaptable) receiver, defRepositoryStore);
            case CONNECTOR_IMPL_FOLDER_PROPERTY:
                return isConnectorImplFolder((IAdaptable) receiver, implRepositoryStore);
            case CONNECTOR_IMPL_FILE_PROPERTY:
                return isConnectorImplFile((IAdaptable) receiver, implRepositoryStore);
            default:
                return false;
        }
    }

    private boolean isConnectorDefFolder(IAdaptable receiver, ConnectorDefRepositoryStore store) {
        return Objects.equals(store.getResource(), receiver.getAdapter(IResource.class));
    }

    private boolean isConnectorImplFolder(IAdaptable receiver, ConnectorImplRepositoryStore store) {
        return Objects.equals(store.getResource(), receiver.getAdapter(IResource.class));
    }

    private boolean isConnectorDefFile(IAdaptable receiver, ConnectorDefRepositoryStore store) {
        IFile file = receiver.getAdapter(IFile.class);
        if (file != null) {
            return store.getChildren().stream()
                    .map(ConnectorDefFileStore::getResource)
                    .anyMatch(file::equals);
        }
        return false;
    }

    private boolean isConnectorImplFile(IAdaptable receiver, ConnectorImplRepositoryStore store) {
        IFile file = receiver.getAdapter(IFile.class);
        if (file != null) {
            return store.getChildren().stream()
                    .map(ConnectorImplFileStore::getResource)
                    .anyMatch(file::equals);
        }
        return false;
    }
}
