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
package org.bonitasoft.studio.application.operation.extension.participant.configuration.preview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.apache.maven.artifact.Artifact;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.participant.configuration.DatabaseConnectorConfigurationChange;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.configuration.ProcessConfigurationChange;
import org.bonitasoft.studio.common.repository.extension.update.preview.ChangePreview;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;

public class JarUpdatedChange
        implements ChangePreview, ProcessConfigurationChange, DatabaseConnectorConfigurationChange {

    private Collection<Configuration> configurations;
    private Artifact artifact;
    private Artifact previousArtifact;
    private List<ChangePreview> details = new ArrayList<>();
    private ChangePreview parent;

    public JarUpdatedChange(Artifact artifact,
            Artifact previousArtifact,
            Collection<Configuration> configurations,
            ChangePreview parent) {
        this.artifact = artifact;
        this.previousArtifact = previousArtifact;
        this.configurations = configurations;
        this.parent = parent;
    }

    public JarUpdatedChange(Artifact artifact,
            Artifact previousArtifact,
            Collection<Configuration> configurations) {
        this(artifact, previousArtifact, configurations, null);
    }

    @Override
    public String getDescription() {
        String updateMessage = String.format(Messages.jarFileUpdatedChangeDescription, artifact.getArtifactId(),
                previousArtifact.getVersion(), artifact.getVersion());
        if (configurations != null) {
            return updateMessage + ": " + String.format(Messages.configurationToUpdateFound, configurations.size());
        }
        return updateMessage;
    }

    @Override
    public List<ChangePreview> getDetails() {
        return details;
    }

    @Override
    public Kind getKind() {
        return Kind.UPDATE;
    }

    @Override
    public boolean hasBreakingChanges() {
        return false;
    }

    @Override
    public ChangePreview getParent() {
        return parent;
    }

    public void addChangeDetail(ChangePreview change) {
        details.add(change);
    }

    @Override
    public Collection<Configuration> getConfigurations() {
        return configurations;
    }

    @Override
    public void apply(Configuration configuration) {
        var otherJarFragmentContainer = getOtherJarFragmentContainer(configuration);
        otherJarFragmentContainer.getFragments().stream()
                .filter(f -> Objects.equals(f.getValue(), previousArtifact.getFile().getName()))
                .findFirst()
                .ifPresent(toUpdate -> {
                    toUpdate.setKey(artifact.getFile().getName());
                    toUpdate.setValue(artifact.getFile().getName());
                });
        getDetails().stream()
                .filter(ProcessConfigurationChange.class::isInstance)
                .map(ProcessConfigurationChange.class::cast)
                .forEach(c -> c.apply(configuration));
    }

    @Override
    public void apply(DatabaseConnectorPropertiesRepositoryStore dbConfStore) {
        var previousJarName = previousArtifact.getFile().getName();
        var updatedJarName = artifact.getFile().getName();
        dbConfStore.getChildren().stream()
                .filter(conf -> conf.getJarList().contains(previousJarName))
                .forEach(conf -> {
                    var jarList = conf.getJarList();
                    jarList.removeIf(previousJarName::equals);
                    jarList.add(updatedJarName);
                    conf.setJarList(jarList);
                    if (previousJarName.equals(conf.getDefault())) {
                        conf.setDefault(updatedJarName);
                    }
                });
    }
    
    @Override
    public boolean showInPreviewDialog() {
        return configurations != null && !configurations.isEmpty();
    }

}
