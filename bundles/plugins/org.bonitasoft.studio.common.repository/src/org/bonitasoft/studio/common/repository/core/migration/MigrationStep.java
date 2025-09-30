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
package org.bonitasoft.studio.common.repository.core.migration;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public interface MigrationStep {

    public static String POM_FILE_NAME = "pom.xml";

    MigrationReport run(Path projectRoot, IProgressMonitor monitor) throws CoreException;

    /**
     * Wether this {@link MigrationStep} should be executed for a given source version of Bonita
     * 
     * @param sourceVersion The source version of the project to migrate
     * @return true if this MigrationStep must be executed
     * @throws IllegalArgumentException When the sourceVersion is invalid
     */
    default boolean appliesToVersion(String sourceVersion) throws IllegalArgumentException {
        return true;
    }

    /**
     * Wether this {@link MigrationStep} should be executed depending on the project content
     * 
     * @param projectRoot The root path of the project to migrate
     * @return true if this MigrationStep must be executed
     * @throws CoreException When a prerequisite to execute this step is not met (e.g: a required file is missing)
     */
    default boolean appliesToProject(Path projectRoot) throws CoreException {
        return true;
    }

    default StepDescription getDescription() {
        return new StepDescription("Migration step", getClass().getName());
    }

    default Model loadMavenModel(Path project) throws CoreException {
        var pomFile = project.resolve(POM_FILE_NAME).toFile();
        return MavenProjectHelper.readModel(pomFile);
    }

    default Model loadParentMavenModel(Path project) throws CoreException {
        var parentPomFile = project.getParent().resolve(POM_FILE_NAME).toFile();
        return parentPomFile.exists() ? MavenProjectHelper.readModel(parentPomFile) : loadMavenModel(project);
    }

    default void saveMavenModel(Model model, Path project) throws CoreException {
        MavenProjectHelper.saveModel(project.resolve(POM_FILE_NAME), model);
    }

    default Predicate<Dependency> has(String groupId, String artifactId) {
        return dep -> Objects.equals(dep.getGroupId(), groupId) &&
                Objects.equals(dep.getArtifactId(), artifactId);
    }

    default Predicate<Dependency> has(String groupId) {
        return dep -> Objects.equals(dep.getGroupId(), groupId);
    }

    static MigrationStep lookup(String stepId) {
        var step = StepRegistry.get(stepId);
        if (step == null) {
            // Return a noop step when not found
            // to avoid inserting null in steps list
            // (mainly for unit test robustness)
            return new MigrationStep() {

                @Override
                public MigrationReport run(Path projectRoot, IProgressMonitor monitor) throws CoreException {
                    return MigrationReport.emptyReport();
                }

            };
        }
        return step;
    }

    static MigrationStep register(String stepId, MigrationStep migrationStep) {
        return StepRegistry.put(stepId, migrationStep);
    }

    class StepRegistry {

        private static final Map<String, MigrationStep> STEPS_REGISTRY = new HashMap<>();

        private StepRegistry() {

        }

        static MigrationStep get(String stepId) {
            return STEPS_REGISTRY.get(stepId);
        }

        static MigrationStep put(String stepId, MigrationStep migrationStep) {
            return STEPS_REGISTRY.put(stepId, migrationStep);
        }
    }
}
