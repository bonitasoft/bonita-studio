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
package org.bonitasoft.studio.connector.model.definition.migration;

import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.process.Connector;

public class ConnectorConfigurationMigrator {

    private ConnectorDefinition targetDefinition;
    private DefinitionChangesVisitor visitor;
    private String fromVersion;

    public ConnectorConfigurationMigrator(String fromVersion,
            DefinitionChangesVisitor visitor,
            ConnectorDefinition targetDefinition) {
        this.fromVersion = fromVersion;
        this.targetDefinition = targetDefinition;
        this.visitor = visitor;
    }

    public void migrate(ConnectorConfiguration configuration) {
        configuration.setVersion(targetDefinition.getVersion());
        visitor.getInputChanges().stream()
                .forEach(change -> change.apply(configuration, targetDefinition));
        findParentConnector(configuration).ifPresent(c -> {
            c.setDefinitionVersion(targetDefinition.getVersion());
            visitor.getOutputChanges().stream().forEach(change -> change.apply(c));
        });
    }
 
    private Optional<Connector> findParentConnector(ConnectorConfiguration configuration) {
        if (configuration.eContainer() instanceof Connector) {
            return Optional.of((Connector) configuration.eContainer());
        }
        return Optional.empty();
    }

    public ConnectorDefinition getTargetDefinition() {
        return targetDefinition;
    }
    
    public DefinitionChangesVisitor getVisitor() {
        return visitor;
    }
    
    public String getFromVersion() {
        return fromVersion;
    }

    public boolean hasBreakingChanges(ConnectorConfiguration conf) {
        return visitor.getInputChanges().stream().anyMatch(c -> c.isBreakingChange(conf))
                || findParentConnector(conf)
                        .map(connector -> visitor.getOutputChanges().stream()
                                .anyMatch(c -> c.isBreakingChange(connector)))
                        .orElse(false);
    }

    public boolean hasChanges() {
        return !Objects.equals(targetDefinition.getVersion(), fromVersion) 
                || !visitor.getInputChanges().isEmpty() 
                || !visitor.getOutputChanges().isEmpty();
    }

}
