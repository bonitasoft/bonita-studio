/**
 * Copyright (C) 2026 BonitaSoft S.A.
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
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.Profile;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;

/**
 * Moves the servlet API dependency of a REST API extension to the jakarta namespace.
 * <p>
 * Unlike {@link RuntimeBOMMigrationStep}, which only declares the servlet API on extension poms that do not import the
 * runtime BOM yet, this step applies to every shape an existing extension pom can have: the runtime BOM no longer
 * manages {@code javax.servlet:javax.servlet-api} from 12.0 on, so a javax dependency left behind either fails to
 * resolve, when Studio wrote it without a version, or resolves an API the runtime does not ship anymore.
 * <p>
 * Only the dependencies the pom declares unconditionally are rewritten. A {@code <profile>} holds an alternative
 * configuration by design -- a javax declaration there can be the deliberate variant of an extension still built
 * against an older runtime -- so such a declaration is reported for the user to handle, never rewritten.
 * <p>
 * Only the pom is migrated. The extension sources keep their {@code javax.servlet} imports, which are reported to the
 * user as a diagnostic instead (see the customer migration guide).
 */
public class JakartaServletMigrationStep implements MavenModelMigration {

    @Override
    public MigrationReport migrate(Model model, ProjectMetadata metadata) {
        var report = new MigrationReport();
        BonitaStudioLog.info(String.format("Starting %s on %s...", JakartaServletMigrationStep.class.getName(),
                metadata.getArtifactId()));

        // an explicit version pin, inherited from the pre-jakarta archetypes, would override the runtime BOM
        var dependencyManagement = model.getDependencyManagement();
        if (dependencyManagement != null
                && dependencyManagement.getDependencies().removeIf(ServletApi.JAVAX::matches)) {
            report.removed(String.format(
                    "The `%s` version managed by this pom has been removed: the runtime provides `%s` instead.",
                    ServletApi.JAVAX.ga(), ServletApi.JAKARTA.ga()));
        }

        migrateDeclaredServletApi(model.getDependencies(), report);
        reportServletApiDeclaredByProfiles(model, report);

        BonitaStudioLog.info(String.format("%s completed for %s.", JakartaServletMigrationStep.class.getName(),
                metadata.getArtifactId()));
        return report;
    }

    @Override
    public boolean appliesTo(Model model, ProjectMetadata metadata) {
        return ServletApi.forRuntime(metadata) == ServletApi.JAKARTA
                && allDeclarations(model).anyMatch(ServletApi.JAVAX::matches);
    }

    private void migrateDeclaredServletApi(List<Dependency> dependencies, MigrationReport report) {
        var javaxDeclarations = servletApiDeclarations(dependencies, ServletApi.JAVAX);
        if (javaxDeclarations.isEmpty()) {
            return;
        }
        var jakartaDeclarations = servletApiDeclarations(dependencies, ServletApi.JAKARTA);
        if (jakartaDeclarations.isEmpty()) {
            var migrated = javaxDeclarations.get(0);
            var previousVersion = migrated.getVersion();
            migrated.setGroupId(ServletApi.JAKARTA.groupId());
            migrated.setArtifactId(ServletApi.JAKARTA.artifactId());
            migrated.setVersion(null);
            report.updated(String.format(
                    "The `%s%s` dependency has been replaced by `%s`, whose version is managed by the runtime BOM: the Bonita runtime now implements the jakarta servlet API.",
                    ServletApi.JAVAX.ga(),
                    previousVersion != null ? ":" + previousVersion : "",
                    ServletApi.JAKARTA.ga()));
            remove(javaxDeclarations.subList(1, javaxDeclarations.size()), dependencies, report);
        } else {
            // the jakarta declaration this extension already carries is typically a hand-patch working around the
            // jakarta move, pinning a version at the default compile scope: keeping it as it is would build the
            // extension against the wrong API and package it in its jar
            jakartaDeclarations.forEach(jakartaServletApi -> {
                if (normalize(jakartaServletApi)) {
                    report.updated(String.format(
                            "The `%s` dependency declared by this extension now uses the provided scope and the version managed by the runtime BOM: the runtime ships the jakarta servlet API itself.",
                            ServletApi.JAKARTA.ga()));
                }
            });
            remove(javaxDeclarations, dependencies, report);
        }
        report.updated(
                "Action required: the sources of this extension must import `jakarta.servlet.*` instead of `javax.servlet.*` to compile against the servlet API provided by the runtime.");
    }

    /**
     * A profile is an alternative configuration, which this step must not silently rewrite: the user is told instead,
     * so the case is visible rather than turning into a build failure or a lost variant.
     */
    private void reportServletApiDeclaredByProfiles(Model model, MigrationReport report) {
        for (Profile profile : model.getProfiles()) {
            if (declarationsOf(profile.getDependencies(), profile.getDependencyManagement())
                    .anyMatch(ServletApi.JAVAX::matches)) {
                report.updated(String.format(
                        "Action required: the `%s` profile of this pom still declares `%s`, which the runtime no longer provides. Replace it with `%s` yourself if that profile targets a 12.0 runtime: a profile can hold the configuration of an older runtime, so this migration leaves it untouched.",
                        profile.getId(), ServletApi.JAVAX.ga(), ServletApi.JAKARTA.ga()));
            }
        }
    }

    /**
     * Makes a servlet API declaration the one the runtime expects: no version so the runtime BOM manages it, and the
     * provided scope so the API is not packaged in the extension jar.
     *
     * @return whether the declaration has been changed
     */
    private boolean normalize(Dependency jakartaServletApi) {
        boolean changed = jakartaServletApi.getVersion() != null
                || !Objects.equals(Artifact.SCOPE_PROVIDED, jakartaServletApi.getScope());
        jakartaServletApi.setVersion(null);
        jakartaServletApi.setScope(Artifact.SCOPE_PROVIDED);
        return changed;
    }

    private void remove(List<Dependency> toRemove, List<Dependency> dependencies, MigrationReport report) {
        for (Dependency dependency : toRemove) {
            // identity, not equality: maven's Dependency does not define equals, but nothing guarantees it never will
            dependencies.removeIf(declaration -> declaration == dependency);
            report.removed(String.format(
                    "The `%s` dependency has been removed: this extension declares the servlet API once, as `%s`.",
                    ServletApi.JAVAX.ga(), ServletApi.JAKARTA.ga()));
        }
    }

    /** @return every declaration of the given servlet API, in the order the pom declares them */
    private List<Dependency> servletApiDeclarations(Collection<Dependency> dependencies, ServletApi servletApi) {
        return dependencies.stream().filter(servletApi::matches).toList();
    }

    /** @return every dependency the pom declares or manages, including the ones nested in a {@code <profile>} */
    private Stream<Dependency> allDeclarations(Model model) {
        return Stream.concat(
                declarationsOf(model.getDependencies(), model.getDependencyManagement()),
                model.getProfiles().stream()
                        .flatMap(profile -> declarationsOf(profile.getDependencies(),
                                profile.getDependencyManagement())));
    }

    private Stream<Dependency> declarationsOf(List<Dependency> dependencies,
            DependencyManagement dependencyManagement) {
        return Stream.concat(dependencies.stream(), managedDependencies(dependencyManagement).stream());
    }

    private List<Dependency> managedDependencies(DependencyManagement dependencyManagement) {
        return dependencyManagement != null ? dependencyManagement.getDependencies() : List.of();
    }

}
