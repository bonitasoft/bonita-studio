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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.maven.artifact.Artifact;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.participant.configuration.ProcessConfigurationChange;
import org.bonitasoft.studio.application.operation.extension.participant.preview.ChangePreview;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.eclipse.emf.ecore.resource.Resource;

public class JarUpdatedChange implements ChangePreview, Runnable, ProcessConfigurationChange {

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
    public void run() {
        if (configurations != null) {
            configurations.stream().forEach(this::updateConfiguration);
        }
    }

    private void updateConfiguration(Configuration configuration) {
        var modelUpdater = new EMFModelUpdater<Configuration>().from(configuration);
        Configuration workingCopy = modelUpdater.getWorkingCopy();
        apply(workingCopy);
        getDetails().stream()
                .filter(ProcessConfigurationChange.class::isInstance)
                .map(ProcessConfigurationChange.class::cast)
                .forEach(c -> c.apply(workingCopy));
        Resource resource = configuration.eResource();
        boolean saveResourceAfterUpdate = resource != null && !resource.isModified();
        modelUpdater.update();
        if (saveResourceAfterUpdate) {
            try {
                resource.save(Collections.emptyMap());
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
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
    }

}
