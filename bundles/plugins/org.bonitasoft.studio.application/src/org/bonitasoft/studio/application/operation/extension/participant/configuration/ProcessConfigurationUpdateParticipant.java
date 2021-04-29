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
package org.bonitasoft.studio.application.operation.extension.participant.configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.UpdateExtensionOperationParticipant;
import org.bonitasoft.studio.application.operation.extension.participant.configuration.preview.JarAddedChange;
import org.bonitasoft.studio.application.operation.extension.participant.configuration.preview.JarRemovedChange;
import org.bonitasoft.studio.application.operation.extension.participant.configuration.preview.JarUpdatedChange;
import org.bonitasoft.studio.application.operation.extension.participant.definition.DependencyUpdate;
import org.bonitasoft.studio.application.operation.extension.participant.preview.ChangePreview;
import org.bonitasoft.studio.application.operation.extension.participant.preview.PreviewResult;
import org.bonitasoft.studio.application.operation.extension.participant.preview.PreviewResultImpl;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesResolver;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ArtifactKey;
import org.eclipse.m2e.core.project.IMavenProjectFacade;

public class ProcessConfigurationUpdateParticipant implements UpdateExtensionOperationParticipant {

    private ProcessConfigurationCollector configurationCollector;
    private List<DependencyUpdate> dependenciesUpdates;
    private IRepository repository;
    private ProjectDependenciesResolver projectDependenciesResolver;
    private PreviewResult previewResult;
    private Map<Artifact, Set<Artifact>> currentArtifacts;

    public ProcessConfigurationUpdateParticipant(List<DependencyUpdate> dependenciesUpdates,
            ProcessConfigurationCollector configurationCollector,
            ProjectDependenciesResolver projectDependenciesResolver,
            IRepository repository) {
        this.dependenciesUpdates = dependenciesUpdates;
        this.configurationCollector = configurationCollector;
        this.projectDependenciesResolver = projectDependenciesResolver;
        this.repository = repository;
    }

    public void preUpdate(IProgressMonitor monitor) {
        monitor.beginTask(Messages.preparingProcessConfigurationUpdate, IProgressMonitor.UNKNOWN);

        currentArtifacts = dependenciesUpdates.stream()
                .map(update -> toArtifact(update.getCurrentDependency()))
                .collect(Collectors.toMap(a -> a, this::transitiveDependencies));
    }

    @Override
    public PreviewResult runPreview(IProgressMonitor monitor) {
        monitor.beginTask(Messages.updatePreview, IProgressMonitor.UNKNOWN);

        previewResult = new PreviewResultImpl();

        var updatedArtifacts = dependenciesUpdates.stream()
                .map(update -> toArtifact(update.getUpdatedDependency()))
                .collect(Collectors.toSet());

        for (var artifact : currentArtifacts.keySet()) {
            var key = new ArtifactKey(artifact);
            updatedArtifacts.stream()
                    .filter(a -> existsInAnotherVersion(new ArtifactKey(a), key))
                    .findFirst()
                    .ifPresent(
                            updatedArtifact -> {
                                var configurations = configurationCollector
                                        .findConfigurationsDependingOn(artifact.getFile().getName());
                                if (!configurations.isEmpty()) {
                                    previewResult
                                            .addChange(createUpdateChanges(artifact, updatedArtifact, configurations));
                                }
                            });
        }
        return previewResult;
    }

    private ChangePreview createUpdateChanges(Artifact artifact, Artifact updatedArtifact,
            Collection<Configuration> configurations) {
        JarUpdatedChange change = new JarUpdatedChange(updatedArtifact, artifact, configurations);

        var artifactDependencyTree = currentArtifacts.get(artifact);
        var updatedArtifactDependencyTree = transitiveDependencies(updatedArtifact);

        for (var previousArtifactDependency : artifactDependencyTree) {
            var key = new ArtifactKey(previousArtifactDependency);
            if (updatedArtifactDependencyTree.stream().noneMatch(a -> existsInSameVersion(new ArtifactKey(a), key)
                    || existsInAnotherVersion(new ArtifactKey(a), key))) {
                // Artifact has been removed with the updated
                change.addChangeDetail(
                        new JarRemovedChange(previousArtifactDependency.getFile().getName(), change));
            } else {
                updatedArtifactDependencyTree.stream()
                        .filter(a -> existsInAnotherVersion(new ArtifactKey(a), key))
                        .findFirst()
                        .ifPresent(a ->
                        // Artifact has been updated with the updated
                        change.addChangeDetail(new JarUpdatedChange(a, previousArtifactDependency, null, change)));
            }
        }
        for (var newArtifactDependency : updatedArtifactDependencyTree) {
            var key = new ArtifactKey(newArtifactDependency);
            if (artifactDependencyTree.stream().noneMatch(a -> existsInSameVersion(new ArtifactKey(a), key)
                    || existsInAnotherVersion(new ArtifactKey(a), key))) {
                // New artifact has been added
                change.addChangeDetail(new JarAddedChange(newArtifactDependency, change));
            }
        }

        return change;
    }

    private Artifact toArtifact(Dependency currentDependency) {
        IMavenProjectFacade projectFacade = MavenPlugin.getMavenProjectRegistry().getProject(repository.getProject());
        ArtifactKey artifactKey = new ArtifactKey(currentDependency.getGroupId(), currentDependency.getArtifactId(),
                currentDependency.getVersion(), currentDependency.getClassifier());
        return projectFacade.getMavenProject().getArtifacts().stream()
                .filter(a -> Objects.equals(new ArtifactKey(a), artifactKey))
                .findFirst()
                .orElseThrow();
    }

    private Set<Artifact> transitiveDependencies(Artifact artifact) {
        try {
            return projectDependenciesResolver
                    .getTransitiveDependencies(repository.getProject(),
                            artifact,
                            AbstractRepository.NULL_PROGRESS_MONITOR)
                    .stream()
                    .collect(Collectors.toSet());
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        return Set.of();
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.updatingConfiguration, IProgressMonitor.UNKNOWN);

        if (previewResult == null) {
            previewResult = runPreview(AbstractRepository.NULL_PROGRESS_MONITOR);
        }

        previewResult.getChanges().stream()
                .filter(Runnable.class::isInstance)
                .map(Runnable.class::cast)
                .forEach(Runnable::run);
    }

    private boolean sameGAC(ArtifactKey updatedArtifact, ArtifactKey currentArtifact) {
        return currentArtifact.getGroupId().equals(updatedArtifact.getGroupId())
                && currentArtifact.getArtifactId().equals(updatedArtifact.getArtifactId())
                && Objects.equals(currentArtifact.getClassifier(), updatedArtifact.getClassifier());
    }

    private boolean existsInAnotherVersion(ArtifactKey updatedArtifact, ArtifactKey currentArtifact) {
        return sameGAC(updatedArtifact, currentArtifact)
                && !currentArtifact.getVersion().equals(updatedArtifact.getVersion());
    }

    private boolean existsInSameVersion(ArtifactKey updatedArtifact, ArtifactKey currentArtifact) {
        return sameGAC(updatedArtifact, currentArtifact)
                && currentArtifact.getVersion().equals(updatedArtifact.getVersion());
    }

    @Override
    public PreviewResult getPreviewResult() {
        return previewResult;
    }

}
