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
package org.bonitasoft.studio.application.operation.extension.participant.definition.preview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.bonitasoft.studio.application.operation.extension.participant.preview.PreviewResultImpl;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationMigrator;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;

public class DefinitionUpdatePreviewResult extends PreviewResultImpl {

    private List<ConnectorDefinition> removedDefinitions = new ArrayList<>();
    private Map<String, ConnectorConfigurationMigrator> configurationMigrators = new HashMap<>();
    private List<ConnectorConfiguration> connectorConfigurations;

    public DefinitionUpdatePreviewResult(List<ConnectorConfiguration> connectorConfigurations) {
        this.connectorConfigurations = connectorConfigurations;
    }
    
    public List<ConnectorConfiguration> getConnectorConfigurations() {
        return connectorConfigurations;
    }

    public void addRemovedDefinition(ConnectorDefinition definition) {
        removedDefinitions.add(definition);
        addChange(new DefinitionRemovedChange(definition,
                countConfigurations(definition.getId(), all())));
    }
    
    public void addMigrator(String definitionId, ConnectorConfigurationMigrator migrator) {
        configurationMigrators.put(definitionId, migrator);
        addChange(new DefinitionVersionUpdateChange(migrator,
                countConfigurations(definitionId, all()), 
                countConfigurations(definitionId, migrator::hasBreakingChanges)));
    }
    
    public boolean hasMigrator(String definitionId) {
        return configurationMigrators.containsKey(definitionId);
    }

    public ConnectorConfigurationMigrator getMigrator(String definitionId) {
        return configurationMigrators.get(definitionId);
    }

    public boolean hasBeenRemoved(String definitionId, String version) {
        return removedDefinitions.stream()
                .anyMatch(def -> def.getId().equals(definitionId) && def.getVersion().equals(version));
    }

    private Predicate<ConnectorConfiguration> all() {
        return c -> Boolean.TRUE;
    }

    private long countConfigurations(String definitionId, Predicate<ConnectorConfiguration> filter) {
        return connectorConfigurations.stream()
                .filter(c -> c.getDefinitionId().equals(definitionId))
                .filter(filter)
                .count();
    }

}
