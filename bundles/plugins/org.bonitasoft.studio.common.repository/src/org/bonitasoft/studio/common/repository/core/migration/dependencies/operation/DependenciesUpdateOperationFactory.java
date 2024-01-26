/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.migration.dependencies.operation;

import jakarta.inject.Inject;

import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesResolver;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.ConfigurationCollector;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.DependentArtifactCollectorRegistry;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.Synchronizer;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.configuration.ProcessConfigurationUpdater;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.connector.ConnectorImplementationUpdater;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.di.InjectionException;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class DependenciesUpdateOperationFactory {

    private DependentArtifactCollectorRegistry dependentArtifactCollectorRegistry;
    private ProcessConfigurationUpdater processConfigurationUpdater;
    private ConnectorImplementationUpdater connectorImplementationUpdater;
    private ProjectDependenciesResolver dependencyResolver;
    private Synchronizer configurationSynchronizer;
    private ConfigurationCollector configurationCollector;

    @Inject
    public DependenciesUpdateOperationFactory(DependentArtifactCollectorRegistry dependentArtifactCollectorRegistry,
            ProcessConfigurationUpdater processConfigurationUpdater,
            ConnectorImplementationUpdater connectorImplementationUpdater,
            ProjectDependenciesResolver dependencyResolver,
            Synchronizer configurationSynchronizer,
            ConfigurationCollector configurationCollector) {
        this.dependentArtifactCollectorRegistry = dependentArtifactCollectorRegistry;
        this.processConfigurationUpdater = processConfigurationUpdater;
        this.connectorImplementationUpdater = connectorImplementationUpdater;
        this.dependencyResolver = dependencyResolver;
        this.configurationSynchronizer = configurationSynchronizer;
        this.configurationCollector = configurationCollector;
    }

    public DependenciesUpdateOperation createDependencyUpdateOperation() {
        return new DependenciesUpdateOperation(dependentArtifactCollectorRegistry,
                processConfigurationUpdater,
                connectorImplementationUpdater,
                dependencyResolver);
    }

    public ConfigurationSynchronizationOperation createConfigurationSynchronizationOperation() {
        return new ConfigurationSynchronizationOperation(configurationCollector, configurationSynchronizer);
    }

    public static DependenciesUpdateOperationFactory get() {
        var eclipseContext = EclipseContextFactory.create();
        try {
            return ContextInjectionFactory
                    .make(DependenciesUpdateOperationFactory.class, eclipseContext);
        } catch (InjectionException e) {
            // Unit test workaround
            return new DependenciesUpdateOperationFactory(null, null, null, null, null, null);
        }
    }

}
