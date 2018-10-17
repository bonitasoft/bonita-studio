/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.implementation.provider;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 */
public class ConnectorImplementationContentProvider implements IStructuredContentProvider {

    private final IRepositoryStore<IRepositoryFileStore> store;
    private final boolean userImplementationOnly;
    private String definitionId;
    private String definitionVersion;

    public ConnectorImplementationContentProvider(IRepositoryStore<IRepositoryFileStore> store,
            boolean userImplementationOnly) {
        Assert.isTrue(store instanceof IImplementationRepositoryStore);
        this.store = store;
        this.userImplementationOnly = userImplementationOnly;
    }

    public ConnectorImplementationContentProvider(IRepositoryStore<IRepositoryFileStore> store, String definitionId,
            String definitionVersion) {
        Assert.isTrue(store instanceof IImplementationRepositoryStore);
        this.store = store;
        userImplementationOnly = false;
        this.definitionId = definitionId;
        this.definitionVersion = definitionVersion;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements(Object element) {
        final List<ConnectorImplementation> connectorImplList = ((IImplementationRepositoryStore) store)
                .getImplementations();
        if (userImplementationOnly) {
            List<ConnectorImplementation> toRemove = new ArrayList<>();
            for (ConnectorImplementation implementation : connectorImplList) {
                if (implementation != null && implementation.eResource() != null) {
                    String path = implementation.eResource().getURI().toFileString();
                    if (!path.contains(store.getResource().getLocation().toFile().getAbsolutePath())) {
                        toRemove.add(implementation);
                    }
                }
            }
            connectorImplList.removeAll(toRemove);
        }
        if (definitionId != null) {
            final List<ConnectorImplementation> toRemove = new ArrayList<>();
            for (ConnectorImplementation implementation : connectorImplList) {
                if (implementation != null) {
                    if (!implementation.getDefinitionId().equals(definitionId)
                            || !implementation.getDefinitionVersion().equals(definitionVersion)) {
                        toRemove.add(implementation);
                    }
                }
            }
            connectorImplList.removeAll(toRemove);
        }
        return connectorImplList.toArray(new ConnectorImplementation[connectorImplList.size()]);
    }

}
