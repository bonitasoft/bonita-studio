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

import java.util.Objects;
import java.util.Properties;
import java.util.function.Predicate;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.model.Plugin;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.maven.model.MavenPlugin;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;

public class ParentMigrationStep implements MavenModelMigration {

    @Override
    public MigrationReport migrate(Model model, ProjectMetadata metadata) {
        MigrationReport report = new MigrationReport();
        BonitaStudioLog.info(String.format("Starting %s on %s...", ParentMigrationStep.class.getName(), metadata.getArtifactId()));
        var reportUpdateMessage = new StringBuilder(
                "This project is now a Maven module, a parent artifact is now defined:");
        reportUpdateMessage.append(System.lineSeparator());

        var parent = new Parent();
        parent.setGroupId(metadata.getGroupId());
        parent.setArtifactId(metadata.getProjectId() + "-extensions");
        parent.setVersion(metadata.getVersion());
        model.setParent(parent);

        // Use parent groupId and version
        model.setGroupId(null);
        model.setVersion(null);

        var configuration = new ProjectDefaultConfiguration(metadata.getBonitaRuntimeVersion());
        // Remove properties declared in parent
        var properties = model.getProperties();
        configuration.getProperties().keySet().forEach(key -> removeProperty(properties, key, reportUpdateMessage, String.format(
                "** `%s` property has been removed. This property is now inherited from the parent pom.",
                key)));
        
        // Using maven.compiler.release in parent
        removeProperty(properties,
                "maven.compiler.target",
                reportUpdateMessage, 
                "** `maven.compiler.target` property has been removed. The `maven.compiler.release` replacement property is now inherited from the parent pom.");
        removeProperty(properties,
                "maven.compiler.source",
                reportUpdateMessage, 
                "** `maven.compiler.source` property has been removed. The `maven.compiler.release` replacement property is now inherited from the parent pom.");
        removeProperty(properties,
                "java.version",
                reportUpdateMessage, 
                "** `java.version` property has been removed. The `maven-compiler-plugin` java version is now configured in the parent pom.");
        removeProperty(properties,
                "groovy.version",
                reportUpdateMessage, 
                "** `groovy.version` property has been removed. The `groovy.verison` property can now be inherited from the parent pom if needed.");

        // Remove obsolete bonita versions properties
        removeProperty(properties,
                "bonita.version",
                reportUpdateMessage, 
                "** `bonita.version` property has been removed. The `bonita.runtime.version` property can now be inherited from the parent pom if needed.");
        removeProperty(properties,
                "bonita-runtime.version",
                reportUpdateMessage, 
                "** `bonita-runtime.version` property has been removed. The `bonita.runtime.version` property can now be inherited from the parent pom if needed.");
        
        if (model.getDependencyManagement() != null && model.getDependencyManagement().getDependencies() != null) {
            if (model.getDependencyManagement().getDependencies().removeIf(isBonitaRuntimeBom())) {
                append(reportUpdateMessage,
                        "** `bonita-runtime-bom` has been removed from `dependencyManagement`. It is now inherited from the parent pom.");
            }
            if (model.getDependencyManagement().getDependencies().isEmpty()) {
                model.setDependencyManagement(null);
            }
        }

        if (model.getBuild() != null && model.getBuild().getPluginManagement() != null
                && model.getBuild().getPluginManagement().getPlugins() != null) {
            model.getBuild().getPluginManagement().getPlugins().removeIf(plugin -> configuration.getPlugins().stream()
                    .map(MavenPlugin::toManagedPlugin).anyMatch(matches(plugin)));
            if (model.getBuild().getPluginManagement().getPlugins().isEmpty()) {
                model.getBuild().setPluginManagement(null);
            }
        }

        if (model.getBuild() != null && model.getBuild().getPlugins() != null) {
            model.getBuild().getPlugins().stream()
                    .filter(plugin -> configuration.getPlugins().stream().map(MavenPlugin::toManagedPlugin)
                            .anyMatch(matches(plugin)))
                    .forEach(plugin -> plugin.setVersion(null));
        }

        if (model.getDependencies() != null) {
            model.getDependencies().stream()
                    .filter(d -> Objects.equals(metadata.getArtifactId() + "-bdm-model", d.getArtifactId()))
                    .findFirst()
                    .ifPresent(d -> {
                        d.setGroupId("${project.groupId}");
                        d.setVersion("${project.version}");
                        append(reportUpdateMessage, String.format(
                                "** `%s-bdm-model` dependency `groupId` and `version` has been updated to `${project.groupId}` and `${project.version}` respectivily.",
                                metadata.getArtifactId()));
                    });
        }

        report.updated(reportUpdateMessage.toString());
        
        BonitaStudioLog.info(String.format("%s completed for %s.", ParentMigrationStep.class.getName(), metadata.getArtifactId()));
        return report;
    }

    private static void append(StringBuilder reportUpdateMessage, String message) {
        reportUpdateMessage.append(message);
        reportUpdateMessage.append(System.lineSeparator());
    }

    private static void removeProperty(Properties properties,
            Object property,
            StringBuilder reportUpdateMessage,
            String message) {
        if (properties.remove(property) != null) {
            append(reportUpdateMessage, message);
        }
    }

    private Predicate<? super Dependency> isBonitaRuntimeBom() {
        return d -> Objects.equals(d.getGroupId(), DefaultPluginVersions.RUNTIME_BOM_GROUP_ID)
                && Objects.equals(d.getArtifactId(), DefaultPluginVersions.RUNTIME_BOM_ARTIFACT_ID);
    }

    private Predicate<? super Plugin> matches(Plugin plugin) {
        return p -> Objects.equals(plugin.getArtifactId(), p.getArtifactId())
                && Objects.equals(plugin.getGroupId(), p.getGroupId());
    }

    @Override
    public boolean appliesTo(Model model, ProjectMetadata metadata) {
        return model.getParent() == null;
    }

}
