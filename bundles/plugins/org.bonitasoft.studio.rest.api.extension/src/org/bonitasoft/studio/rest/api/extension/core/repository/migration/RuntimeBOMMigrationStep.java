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
package org.bonitasoft.studio.rest.api.extension.core.repository.migration;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Predicate;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;

public class RuntimeBOMMigrationStep implements MavenModelMigration {

    private static final String JACKSON_VERSION_PROPERTY = "jackson.version";
    private static final String GROOVY_ALL_VERSION_PROPERTY = "groovy-all.version";
    private static final String SLF4J_API_VERSION_PROPERTY = "slf4j-api.version";

    @Override
    public MigrationReport migrate(Model model) {
        MigrationReport report = new MigrationReport();
        Properties properties = model.getProperties();

        properties.remove(GROOVY_ALL_VERSION_PROPERTY);
        if (properties.remove(SLF4J_API_VERSION_PROPERTY) != null) {
            report.removed(
                    String.format("Remove `%s` property and use the managed version provided by the runtime BOM.",
                            SLF4J_API_VERSION_PROPERTY));
        }
        if (properties.remove(JACKSON_VERSION_PROPERTY) != null) {
            report.removed(
                    String.format("Remove `%s` property and use the managed version provided by the runtime BOM.",
                            JACKSON_VERSION_PROPERTY));
        }

        var dependencyManagement = model.getDependencyManagement() != null ? model.getDependencyManagement()
                : new DependencyManagement();
        dependencyManagement.addDependency(runtimeBOMImportDependency());
        model.setDependencyManagement(dependencyManagement);

        findDependency(model.getDependencies(), "org.bonitasoft.web", "bonita-web-extensions")
                .ifPresent(dependency -> {
                    dependency.setVersion(null);
                    report.updated(
                            "Use the managed version provided by the runtime BOM for `or.bonitasoft.web:bonita-web-extensions`");
                });

        findDependency(model.getDependencies(), "com.bonitasoft.web", "bonita-web-extensions-sp")
                .ifPresent(dependency -> {
                    dependency.setVersion(null);
                    report.updated(
                            "Use the managed version provided by the runtime BOM for `com.bonitasoft.web:bonita-web-extensions-sp`");
                });

        findDependency(model.getDependencies(), "javax.servlet", "javax.servlet-api")
                .ifPresentOrElse(dependency -> {
                    dependency.setVersion(null);
                    report.updated(
                            "Use the managed version provided by the runtime BOM for `javax.servlet:javax.servlet-api`");
                }, () -> {
                    model.getDependencies().add(newDependency("javax.servlet", "javax.servlet-api", null, "provided"));
                    report.added("Declare `javax.servlet:javax.servlet-api` provided dependency.");
                });

        findDependency(model.getDependencies(), "org.slf4j", "slf4j-api")
                .ifPresent(dependency -> {
                    dependency.setVersion(null);
                    report.updated("Use the managed version provided by the runtime BOM for `org.slf4j:slf4j-api`");
                });

        findDependency(model.getDependencies(), "org.codehaus.groovy", "groovy-all")
                .ifPresent(dependency -> {
                    dependency.setVersion(null);
                });

        findDependency(model.getDependencies(), "org.codehaus.groovy", "groovy-dateutil")
                .ifPresent(dependency -> dependency.setVersion(null));

        findDependency(model.getDependencies(), "com.fasterxml.jackson.datatype", "jackson-datatype-jsr310")
                .ifPresent(dependency -> dependency.setVersion(null));

        return report;
    }

    private Dependency newDependency(String groupId, String artifactId, String version, String scope) {
        Dependency dependency = new Dependency();
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        dependency.setScope(scope);
        return dependency;
    }

    private Optional<Dependency> findDependency(Collection<Dependency> dependencies, String groupId,
            String artifactId) {
        return dependencies.stream()
                .filter(d -> Objects.equals(groupId, d.getGroupId()))
                .filter(d -> Objects.equals(artifactId, d.getArtifactId()))
                .findFirst();
    }

    @Override
    public boolean appliesTo(Model model) {
        if (model.getDependencyManagement() != null) {
            return model.getDependencyManagement().getDependencies()
                    .stream()
                    .noneMatch(isRuntimeBOM());
        }
        return true;
    }

    private Predicate<Dependency> isRuntimeBOM() {
        var runtimeBOMImportDependency = runtimeBOMImportDependency();
        return dep -> Objects.equals(dep.getGroupId(), runtimeBOMImportDependency.getGroupId())
                && Objects.equals(dep.getArtifactId(), runtimeBOMImportDependency.getArtifactId())
                && Objects.equals(dep.getType(), runtimeBOMImportDependency.getType())
                && Objects.equals(dep.getScope(), runtimeBOMImportDependency.getScope());
    }

    private static Dependency runtimeBOMImportDependency() {
        var runtimeBOM = ProjectDefaultConfiguration.runtimeBOMImportDependency();
        runtimeBOM.setVersion("${bonita.version}");
        return runtimeBOM;
    }
}
