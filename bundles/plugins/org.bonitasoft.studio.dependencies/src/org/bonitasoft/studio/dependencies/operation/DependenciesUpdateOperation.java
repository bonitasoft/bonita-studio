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
package org.bonitasoft.studio.dependencies.operation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesResolver;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.dependencies.DependentArtifactCollectorRegistry;
import org.bonitasoft.studio.dependencies.configuration.ProcessConfigurationUpdater;
import org.bonitasoft.studio.dependencies.connector.ConnectorImplementationUpdater;
import org.bonitasoft.studio.dependencies.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class DependenciesUpdateOperation implements IRunnableWithProgress {

    private DependentArtifactCollectorRegistry dependentArtifactCollectorRegistry;
    private ProcessConfigurationUpdater configurationUpdater;
    private ProjectDependenciesResolver dependencyResolver;
    private Map<String, String> updates = new HashMap<>();
    private Set<String> removed = new HashSet<>();
    private ConnectorImplementationUpdater connectorImplementationUpdater;

    public DependenciesUpdateOperation(DependentArtifactCollectorRegistry dependentArtifactCollectorRegistry,
            ProcessConfigurationUpdater configurationUpdater,
            ConnectorImplementationUpdater connectorImplementationUpdater,
            ProjectDependenciesResolver dependencyResolver) {
        this.dependentArtifactCollectorRegistry = dependentArtifactCollectorRegistry;
        this.configurationUpdater = configurationUpdater;
        this.connectorImplementationUpdater = connectorImplementationUpdater;
        this.dependencyResolver = dependencyResolver;
    }

    public DependenciesUpdateOperation addJarUpdateChange(String oldJarName, String newJarName) {
        this.updates.put(oldJarName, newJarName);
        return this;
    }

    public DependenciesUpdateOperation addJarRemovedChange(String jarName) {
        this.removed.add(jarName);
        return this;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        var subMonitor = SubMonitor.convert(monitor, Messages.updateProcessConfigurations,
                updates.size() + removed.size());
        applyRemovals(subMonitor);
        applyUpdates(subMonitor);
        subMonitor.done();
    }

    private void applyUpdates(IProgressMonitor monitor) {
        for (var entry : updates.entrySet()) {
            var jar = entry.getKey();
            monitor.subTask(String.format(Messages.updatingDependencyInConfigurations, jar));
            var configurationCollector = dependentArtifactCollectorRegistry.get(Configuration.class);
            Collection<Configuration> configurationsToUpdate = configurationCollector
                    .findArtifactDependingOn(jar);
            var implementationsCollector = dependentArtifactCollectorRegistry.get(ConnectorImplementation.class);
            Collection<ConnectorImplementation> implementationsToUpdate = implementationsCollector
                    .findArtifactDependingOn(jar);
            if (!configurationsToUpdate.isEmpty() || !implementationsToUpdate.isEmpty()) {
                var change = new JarUpdateChange(jar, updates.get(jar),
                        configurationsToUpdate, implementationsToUpdate);
                Collection<Resource> resources = configurationUpdater.update(change);
                connectorImplementationUpdater.update(change);
                resources.stream().forEach(resource -> {
                    try {
                        resource.save(Collections.emptyMap());
                    } catch (IOException e) {
                        BonitaStudioLog.error(e);
                    }
                });
            }
            monitor.worked(1);
        }
    }

    private void applyRemovals(IProgressMonitor monitor) {
        for (var jar : removed) {
            try {
                var dependency = dependencyResolver.findCompileDependency(jar, new NullProgressMonitor());
                if (dependency.isEmpty()) {
                    monitor.subTask(String.format(Messages.removingDependencyInConfigurations, jar));
                    var configurationCollector = dependentArtifactCollectorRegistry.get(Configuration.class);
                    Collection<Configuration> configurationsToUpdate = configurationCollector
                            .findArtifactDependingOn(jar);
                    var implementationsCollector = dependentArtifactCollectorRegistry
                            .get(ConnectorImplementation.class);
                    Collection<ConnectorImplementation> implementationsToUpdate = implementationsCollector
                            .findArtifactDependingOn(jar);
                    if (!configurationsToUpdate.isEmpty()) {
                        var change = new JarRemovedChange(jar,
                                configurationsToUpdate, implementationsToUpdate);
                        Collection<Resource> resources = configurationUpdater.update(change);
                        connectorImplementationUpdater.update(change);
                        resources.stream().forEach(resource -> {
                            try {
                                resource.save(Collections.emptyMap());
                            } catch (IOException e) {
                                BonitaStudioLog.error(e);
                            }
                        });
                    }
                }
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
            monitor.worked(1);
        }
    }

}
