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
package org.bonitasoft.studio.application.operation.extension.participant.definition;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.bpm.model.process.Connector;
import org.bonitasoft.plugin.analyze.report.model.Definition;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.participant.definition.preview.DefinitionUpdatePreviewResult;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;
import org.bonitasoft.studio.common.repository.extension.update.participant.ExtensionUpdateParticipant;
import org.bonitasoft.studio.common.repository.extension.update.preview.PreviewMessageProvider;
import org.bonitasoft.studio.common.repository.extension.update.preview.PreviewResult;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationMigratorFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

public class DefinitionUpdateParticipant implements ExtensionUpdateParticipant {

    private ArtifactDefinitionProvider artifactDefinitionProvider;
    private List<ConnectorConfiguration> connectorConfigurations;
    private List<ConnectorDefinition> currentConnectorDefinitions;
    private ConnectorDefinitionProviderFactory connectorDefinitionProviderFactory;
    private ConnectorConfigurationMigratorFactory migratorFactory;
    private ConnectorConfigurationCollector configurationCollector;
    private DefinitionUpdatePreviewResult previewResult;
    private List<DependencyUpdate> dependenciesUpdates;
    private Set<Resource> modifiedResources;

    public DefinitionUpdateParticipant(ArtifactDefinitionProvider artifactDefinitionProvider,
            List<DependencyUpdate> dependenciesUpdates,
            ConnectorDefinitionProviderFactory connectorDefinitionProviderFactory,
            ConnectorConfigurationMigratorFactory migratorFactory,
            ConnectorConfigurationCollector connectorConfigurationCollector) {
        this.artifactDefinitionProvider = artifactDefinitionProvider;
        this.dependenciesUpdates = dependenciesUpdates;
        this.connectorDefinitionProviderFactory = connectorDefinitionProviderFactory;
        this.migratorFactory = migratorFactory;
        this.configurationCollector = connectorConfigurationCollector;
    }

    public List<DependencyUpdate> getDependenciesUpdates() {
        return dependenciesUpdates;
    }

    @Override
    public void preUpdate(IProgressMonitor monitor) {
        monitor.beginTask(Messages.preparingDefinitionUpdate, IProgressMonitor.UNKNOWN);
        // Retrieve definitions provided by the current dependency
        var definitionProvider = connectorDefinitionProviderFactory.get();
        currentConnectorDefinitions = dependenciesUpdates.stream()
                .flatMap(update -> artifactDefinitionProvider.getDefinitions(update.getCurrentDependency()).stream())
                .map(def -> definitionProvider.find(def.getDefinitionId(), def.getDefinitionVersion()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        // Retrieve configurations using the above definitions
        connectorConfigurations = currentConnectorDefinitions.stream()
                .flatMap(def -> configurationCollector.getConfigurations(def.getId(), def.getVersion())
                        .stream())
                .collect(Collectors.toList());
    }

    @Override
    public DefinitionUpdatePreviewResult runPreview(IProgressMonitor monitor) {
        monitor.beginTask(Messages.updatePreview, IProgressMonitor.UNKNOWN);

        previewResult = new DefinitionUpdatePreviewResult(connectorConfigurations);

        // Retrieve definitions provided by the current dependency
        Collection<Definition> connectorDefinitions = dependenciesUpdates.stream()
                .filter(du -> du.getUpdatedDependency() != null)
                .flatMap(update -> artifactDefinitionProvider.getDefinitions(update.getUpdatedDependency()).stream())
                .collect(Collectors.toList());

        var definitionProvider = connectorDefinitionProviderFactory.get();
        for (ConnectorDefinition def : currentConnectorDefinitions) {
            if (connectorDefinitions.stream()
                    .noneMatch(newDef -> def.getId().equals(newDef.getDefinitionId()))) {
                // definition removed in new version of the extension
                previewResult.addRemovedDefinition(def);
            } else {
                // Create configuration migrators if new definitions are found after update
                connectorDefinitions.stream()
                        .filter(newDef -> def.getId().equals(newDef.getDefinitionId()))
                        .findFirst()
                        .map(newDef -> definitionProvider.find(newDef.getDefinitionId(), newDef.getDefinitionVersion()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .ifPresent(newDef -> {
                            var migrator = migratorFactory.create(def, newDef);
                            if (migrator.hasChanges()) {
                                previewResult.addMigrator(newDef.getId(), migrator);
                            }
                        });
            }
        }

        return previewResult;
    }

    @Override
    public PreviewResult getPreviewResult() {
        return previewResult;
    }

    @Override
    public Collection<Resource> getModifiedResources() {
        return modifiedResources;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.updatingConfiguration, connectorConfigurations.size());

        if (previewResult == null) {
            previewResult = runPreview(AbstractRepository.NULL_PROGRESS_MONITOR);
        }

        // Apply migration to configuration
        modifiedResources = connectorConfigurations.stream()
                .filter(conf -> previewResult.hasMigrator(conf.getDefinitionId()))
                .filter(conf -> !previewResult.getMigrator(conf.getDefinitionId()).hasBreakingChanges(conf))
                .map(conf -> applyMigration(conf, monitor))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        modifiedResources.addAll(connectorConfigurations.stream()
                .filter(conf -> previewResult.hasBeenRemoved(conf.getDefinitionId(), conf.getVersion()))
                .map(conf -> removeConfiguration(conf, monitor))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet()));
    }

    private Resource removeConfiguration(ConnectorConfiguration conf, IProgressMonitor monitor) {
        Resource resource = conf.eResource();
        if (resource != null) {
            if (conf.eContainer() instanceof Connector) {
                Connector connector = (Connector) conf.eContainer();
                TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(conf);
                if (editingDomain != null) {
                    editingDomain.getCommandStack().execute(DeleteCommand.create(editingDomain, connector));
                } else {
                    EcoreUtil.delete(connector);
                }
                monitor.worked(1);
                return resource;
            }
            try {
                resource.delete(Collections.emptyMap());
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
            monitor.worked(1);
        }
        return null;
    }

    private Resource applyMigration(ConnectorConfiguration conf, IProgressMonitor monitor) {
        var migrator = previewResult.getMigrator(conf.getDefinitionId());
        EObject source = conf.eContainer() instanceof Connector ? conf.eContainer() : conf;
        var modelUpdater = new EMFModelUpdater<>().from(source);
        EObject workingCopy = modelUpdater.getWorkingCopy();
        if (workingCopy instanceof Connector) {
            migrator.migrate(((Connector) workingCopy).getConfiguration());
        } else {
            migrator.migrate((ConnectorConfiguration) workingCopy);
        }
        var resource = conf.eResource();
        modelUpdater.update();
        monitor.worked(1);
        return resource;
    }

    @Override
    public PreviewMessageProvider getPreviewMessageProvider() {
        return new ProcessDefinitionPreviewMessageProvider();
    }

}
