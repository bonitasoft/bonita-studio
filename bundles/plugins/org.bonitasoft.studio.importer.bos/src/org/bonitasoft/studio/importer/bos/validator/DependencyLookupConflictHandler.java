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
package org.bonitasoft.studio.importer.bos.validator;

import static java.util.function.Predicate.not;

import java.io.File;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.ConflictVersion;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.ConflictVersion.Status;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.m2e.core.internal.IMavenConstants;

public class DependencyLookupConflictHandler implements IValidator<DependencyLookup> {

    private RepositoryAccessor repositoryAccessor;
    private String targetProject;
    private IObservableList<DependencyLookup> dependencies;
    private IObservableValue<IStatus> conflictStatusObservable = new WritableValue<>(ValidationStatus.ok(),
            IStatus.class);
    private IObservableValue<Boolean> conflictingObservable = new WritableValue<>(Boolean.FALSE,
            Boolean.class);

    public DependencyLookupConflictHandler(RepositoryAccessor repositoryAccessor,
            IObservableList<DependencyLookup> dependencies) {
        this.repositoryAccessor = repositoryAccessor;
        this.dependencies = dependencies;
    }

    public void setTargetProject(String project) {
        this.targetProject = project;
        resolve();
    }

    public void resolve() {
        dependencies.stream().forEach(d -> d.setConflict(null));
        if (targetProject != null) {
            findPomFile(targetProject).ifPresent(pomFile -> {
                try {
                    var targetProjectModel = MavenProjectHelper.readModel(pomFile);
                    computeDependenciesConflicts(targetProjectModel, dependencies);
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            });
        }
        updateConflictStatus();
    }

    private void computeDependenciesConflicts(Model model, Collection<DependencyLookup> dependencies) {
        for (var dl : dependencies) {
            model.getDependencies().stream()
                    .filter(not(ProjectDefaultConfiguration::isInternalDependency))
                    .filter(dep -> sameDependencyWithADifferentVersion(dl.getGAV(), dep) 
                            || sameLocalSnapshotDependency(dl, dep))
                    .findFirst()
                    .ifPresent(dep -> dl.setConflict(
                            new ConflictVersion(dl.getVersion(), dep.getVersion(), Status.CONFLICTING)));
        }
    }

    private boolean sameLocalSnapshotDependency(DependencyLookup dl, Dependency dep) {
        if(dl.getStatus() == DependencyLookup.Status.LOCAL) {
            return dl.getVersion().endsWith("-SNAPSHOT") && new GAV(dep).equals(dl.getGAV());
        }
        return false;
    }

    private Optional<File> findPomFile(String projectName) {
        if (Strings.isNullOrEmpty(projectName)) {
            return Optional.empty();
        }
        return Optional.ofNullable(getProject(projectName))
                .filter(IProject::exists)
                .map(p -> p.getFile(IMavenConstants.POM_FILE_NAME).getLocation().toFile())
                .filter(File::exists);
    }

    private IProject getProject(String projectName) {
        return repositoryAccessor.getWorkspace().getRoot().getProject(projectName);
    }

    @Override
    public IStatus validate(DependencyLookup dependency) {
        return dependency.isConflicting() ? ValidationStatus.error(Messages.anotherVersionAlreadyExists)
                : ValidationStatus.ok();
    }

    public void updateConflictStatus() {
        conflictStatusObservable.setValue(dependencies.stream().anyMatch(dep -> dep.isConflicting() && dep.isSelected())
                ? ValidationStatus.error("") : ValidationStatus.ok());
        conflictingObservable.setValue(dependencies.stream().map(DependencyLookup::getConflictVersion).anyMatch(Objects::nonNull));
    }

    private boolean sameDependencyWithADifferentVersion(GAV gav, Dependency dep) {
        return Objects.equals(gav.getGroupId(), dep.getGroupId())
                && Objects.equals(gav.getArtifactId(), dep.getArtifactId())
                && Objects.equals(gav.getClassifier(), dep.getClassifier())
                && Objects.equals(gav.getType(), dep.getType())
                && !Objects.equals(gav.getVersion(), dep.getVersion());
    }

    public IObservableValue<IStatus> getConflictValidationStatusObservable() {
        return conflictStatusObservable;
    }

    public IObservableValue<Boolean> getConflictObservable() {
        return conflictingObservable;
    }

}
