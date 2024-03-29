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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.model.validator.ModelNamespaceValidator;
import org.bonitasoft.studio.common.model.validator.XMLModelCompatibilityValidator;
import org.bonitasoft.studio.connector.model.implementation.AbstractConnectorImplRepositoryStore;
import org.eclipse.core.runtime.IStatus;

public class ConnectorImplRepositoryStore extends AbstractConnectorImplRepositoryStore<ConnectorImplFileStore> {

    public static final String CONNECTOR_IMPL_EXT = "impl";

    public static final String STORE_NAME = "connectors-impl";
    private static final Set<String> extensions = Set.of(CONNECTOR_IMPL_EXT);

    @Override
    public ConnectorImplFileStore createRepositoryFileStore(String fileName) {
        return new ConnectorImplFileStore(fileName, this);
    }

    @Override
    public String getName() {
        return STORE_NAME;
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    public List<ConnectorImplFileStore> getChildren() {
        var projectDependenciesStore = getRepository().getProjectDependenciesStore();
        if (projectDependenciesStore != null) {
           return projectDependenciesStore.getConnectorImplementations().stream()
                    .map(impl -> new DependencyConnectorImplFileStore(impl, this))
                    .collect(Collectors.toList());
        }
        return List.of();
    }


    @Override
    public IStatus validate(String filename, InputStream inputStream) {
        if (filename != null && filename.endsWith("." + CONNECTOR_IMPL_EXT)) {
            return new XMLModelCompatibilityValidator(new ModelNamespaceValidator(
                    ModelVersion.CURRENT_CONNECTOR_IMPLEMENTATION_NAMESPACE,
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
