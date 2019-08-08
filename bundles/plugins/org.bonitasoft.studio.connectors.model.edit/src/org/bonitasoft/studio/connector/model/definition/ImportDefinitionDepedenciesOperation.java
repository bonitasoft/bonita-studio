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
package org.bonitasoft.studio.connector.model.definition;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class ImportDefinitionDepedenciesOperation implements IRunnableWithProgress {

    private ConnectorDefinition definition;
    private DefinitionResourceProvider definitionResourceProvider;
    private DependencyRepositoryStore depStore;

    public ImportDefinitionDepedenciesOperation(ConnectorDefinition definition,
            DefinitionResourceProvider definitionResourceProvider,
            DependencyRepositoryStore depStore) {
        this.definition = definition;
        this.definitionResourceProvider = definitionResourceProvider;
        this.depStore = depStore;
    }


    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        if (definition.getJarDependency().stream()
                .noneMatch(dep -> depStore.getChild(dep) != null)) {
            boolean shouldBuild = false;
            for (final String jarName : definition.getJarDependency()) {
                if (depStore.getChild(jarName) == null) {
                    final InputStream is = definitionResourceProvider.getDependencyInputStream(jarName);
                    if (is != null) {
                        depStore.importInputStream(jarName, is);
                        shouldBuild = true;
                    }
                }
            }
            if (shouldBuild) {
                RepositoryManager.getInstance().getCurrentRepository().build(monitor);
            }
        }

    }
}
