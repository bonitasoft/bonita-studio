/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.definition.wizard;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 */
public class ConfigurationsContentProvider implements ITreeContentProvider {

    private final IRepositoryStore<? extends IRepositoryFileStore> store;
    private final String definitionVersion;
    private final String definitionId;

    public ConfigurationsContentProvider(final String definitionId, final String definitionVersion,
            final IRepositoryStore<? extends IRepositoryFileStore> configurationStore) {
        store = configurationStore;
        this.definitionVersion = definitionVersion;
        this.definitionId = definitionId;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.
     * Object)
     */
    @Override
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof ConnectorConfiguration) {
            final ConnectorConfiguration configuration = (ConnectorConfiguration) parentElement;
            return configuration.getParameters().toArray();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object
     * )
     */
    @Override
    public Object getParent(final Object element) {
        if (element instanceof ConnectorConfiguration) {
            return ((ConnectorConfiguration) element).eContainer();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.
     * Object)
     */
    @Override
    public boolean hasChildren(final Object element) {
        return element instanceof ConnectorConfiguration && !((ConnectorConfiguration) element).getParameters().isEmpty();
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java
     * .lang.Object)
     */
    @Override
    public Object[] getElements(final Object inputElement) {
        final List<ConnectorConfiguration> configurations = new ArrayList<ConnectorConfiguration>();
        for (final IRepositoryFileStore f : store.getChildren()) {
            try {
                final ConnectorConfiguration config = (ConnectorConfiguration) f.getContent();
                if (config != null) {
                    if (config.getDefinitionId().equals(definitionId) && config.getVersion().equals(definitionVersion)) {
                        configurations.add(config);
                    }
                }
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed to retrieve connector configuration", e);
            }

        }
        return configurations.toArray();
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
     * @see
     * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface
     * .viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

}
