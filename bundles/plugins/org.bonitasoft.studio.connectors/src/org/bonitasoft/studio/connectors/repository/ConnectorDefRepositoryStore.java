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
package org.bonitasoft.studio.connectors.repository;

import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

/**
 * @author Romain Bioteau
 * @author Baptiste Mesta
 *         This extends SourceRepositoryStore in order to find message resources
 */
public class ConnectorDefRepositoryStore extends AbstractDefinitionRepositoryStore<ConnectorDefFileStore> {

    public static final String STORE_NAME = "connectors-def";

    private static final Set<String> extensions = new HashSet<String>();

    public static final String CONNECTOR_DEF_EXT = "def";
    static {
        extensions.add(CONNECTOR_DEF_EXT);
    }

    private DefinitionResourceProvider resourceProvider;

    @Override
    public ConnectorDefFileStore createRepositoryFileStore(final String fileName) {
        if (fileName.endsWith(CONNECTOR_DEF_EXT)) {
            return new ConnectorDefFileStore(fileName, this);
        }
        return null;
    }

    public DefinitionResourceProvider getResourceProvider() {
        if (resourceProvider == null) {
            resourceProvider = DefinitionResourceProvider.getInstance(this, getBundle());
            resourceProvider.loadDefinitionsCategories(null);
        }
        return resourceProvider;
    }

    @Override
    protected ConnectorDefFileStore doImportInputStream(final String fileName, final InputStream inputStream) {
        final ConnectorDefFileStore definition = super.doImportInputStream(fileName, inputStream);
        if (definition != null) {
            final DefinitionResourceProvider resourceProvider = DefinitionResourceProvider.getInstance(this, getBundle());
            reloadCategories(definition.getContent(), resourceProvider);
        }
        return definition;
    }

    private void reloadCategories(final org.bonitasoft.studio.connector.model.definition.ConnectorDefinition definition,
            final DefinitionResourceProvider messageProvider) {
        boolean reloadCategories = false;
        for (final Category c : definition.getCategory()) {
            if (!messageProvider.getAllCategories().contains(c)) {
                reloadCategories = true;
                break;
            }
        }
        if (reloadCategories) {
            messageProvider.loadDefinitionsCategories(null);
        }
    }

    @Override
    public String getName() {
        return STORE_NAME;
    }

    @Override
    public String getDisplayName() {
        return Messages.connectorDefRepositoryName;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("connector.png", ConnectorPlugin.getDefault());
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    protected ConnectorDefFileStore getDefFileStore(final URL url) {
        for (final String compatibleExtension : getCompatibleExtensions()) {
            if (url.getFile().endsWith(compatibleExtension)) {
                return new URLConnectorDefFileStore(url, this);
            }
        }
        return null;
    }

    @Override
    protected Bundle getBundle() {
        return ConnectorPlugin.getDefault().getBundle();
    }

    @Override
    public void migrate(final IProgressMonitor monitor) throws CoreException, MigrationException {
        super.migrate(monitor);
        if(PlatformUI.isWorkbenchRunning()) {
            getResourceProvider().loadDefinitionsCategories(null);
        }
    }

}
