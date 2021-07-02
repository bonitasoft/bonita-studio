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
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.model.validator.ModelNamespaceValidator;
import org.bonitasoft.studio.common.model.validator.XMLModelCompatibilityValidator;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.PostMigrationOperationCollector;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
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

    private static final Set<String> extensions = new HashSet<>();

    public static final String CONNECTOR_DEF_EXT = "def";
    static {
        extensions.add(CONNECTOR_DEF_EXT);
    }

    private DefinitionResourceProvider resourceProvider;

    @Override
    public void createRepositoryStore(IRepository repository) {
        super.createRepositoryStore(repository);
        resourceProvider = DefinitionResourceProvider.getInstance(this, getBundle());
    }

    @Override
    public List<ConnectorDefFileStore> getChildren() {
        List<ConnectorDefFileStore> defFileStores = super.getChildren();

        var projectDependenciesStore = getRepository().getProjectDependenciesStore();
        if (projectDependenciesStore != null) {
            projectDependenciesStore.getConnectorDefinitions().stream()
                    .map(def -> new DependencyConnectorDefFileStore(def, this))
                    .forEach(defFileStores::add);
        }

        return defFileStores;
    }

    @Override
    public ConnectorDefFileStore createRepositoryFileStore(final String fileName) {
        if (fileName.endsWith(CONNECTOR_DEF_EXT)) {
            return new ConnectorDefFileStore(fileName, this);
        }
        return null;
    }

    @Override
    public DefinitionResourceProvider getResourceProvider() {
        return resourceProvider;
    }

    @Override
    protected ConnectorDefFileStore doImportInputStream(final String fileName, final InputStream inputStream) {
        final ConnectorDefFileStore definition = super.doImportInputStream(fileName, inputStream);
        if (definition != null) {
            resourceProvider.loadDefinitionsCategories(null);
        }
        return definition;
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
    public void migrate(PostMigrationOperationCollector postMigrationOperationCollector, IProgressMonitor monitor)
            throws CoreException, MigrationException {
        super.migrate(postMigrationOperationCollector, monitor);
        if (PlatformUI.isWorkbenchRunning()) {
            getResourceProvider().loadDefinitionsCategories(null);
        }
    }

    @Override
    public IStatus validate(String filename, InputStream inputStream) {
        if (filename != null && filename.endsWith("." + CONNECTOR_DEF_EXT)) {
            return new XMLModelCompatibilityValidator(new ModelNamespaceValidator(
                    ModelVersion.CURRENT_CONNECTOR_DEFINITION_NAMESPACE,
                    String.format(org.bonitasoft.studio.common.Messages.incompatibleModelVersion, filename),
                    String.format(org.bonitasoft.studio.common.Messages.migrationWillBreakRetroCompatibility,
                            filename)))
                                    .validate(inputStream);
        }
        return super.validate(filename, inputStream);
    }
    
    @Override
    public int getImportOrder() {
        return 5;
    }
}
