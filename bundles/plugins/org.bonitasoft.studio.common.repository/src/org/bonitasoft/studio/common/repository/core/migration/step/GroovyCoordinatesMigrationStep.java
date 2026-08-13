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
package org.bonitasoft.studio.common.repository.core.migration.step;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.maven.model.MavenDependency;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

/**
 * Rewrites the legacy Groovy dependencies of the user project to the current coordinates: the
 * {@code org.codehaus.groovy} dependencies Studio used to write - the {@code groovy-all} still
 * present in poms of 7.13-era projects as well as the module list written by Studio 7.13 to 11.x
 * - are replaced by the versionless {@code org.apache.groovy} modules provided by the Bonita
 * runtime, whose versions are managed by the {@code bonita-project} parent.
 * {@code groovy-servlet} is dropped: it is no longer provided by the Bonita runtime.
 * <p>
 * Both the root pom (single-module projects, up to 7.x) and the app module pom (multi-module
 * projects, 8.0.0 and later) are inspected: the multi-module split moves the dependency list into
 * the app module.
 * <p>
 * Only the dependencies Studio wrote are migrated: Studio always wrote them versionless with the
 * {@code provided} scope, so a user-added {@code org.codehaus.groovy} artifact outside that set
 * (e.g. {@code groovy-swing}, or the Groovy-Eclipse tooling which was never republished under
 * {@code org.apache.groovy}), a versioned entry or a non-provided scope is left untouched and
 * reported for manual review - even when nothing else needs migrating, since a fully
 * version-pinned legacy list still resolves at build time but runs against the Groovy 4 runtime.
 */
public class GroovyCoordinatesMigrationStep implements MigrationStep {

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.groovyCoordinatesMigrationTitle,
                Messages.groovyCoordinatesMigrationDescription);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.groovyCoordinatesMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", GroovyCoordinatesMigrationStep.class.getName()));
        var report = new MigrationReport();
        var migrated = false;
        for (var module : modulePoms(project)) {
            migrated |= migratePom(module, report);
        }
        if (migrated) {
            ensureProvidedModulesDeclared(project, report);
        }
        // extension module poms have their own store migration, which still targets Groovy 3:
        // warn so the mixed state does not go unnoticed. Not gated on migrated -
        // a fully version-pinned project is report-only yet is exactly the shape this is about,
        // and run() is only reached when appliesToProject already found something. Gated on
        // actual extensions being present (see hasExtensionModules): the 8.0.0 layout creates
        // extensions/pom.xml unconditionally, and a permanently-on warning trains users to skip
        // the section
        if (hasExtensionModules(project)) {
            report.updated(
                    "Action required: the extension module poms are migrated separately and may still use Groovy 3 dependencies - review them against the Groovy version provided by the Bonita runtime.");
        }
        BonitaStudioLog.info(String.format("%s completed.", GroovyCoordinatesMigrationStep.class.getName()));
        return report;
    }

    /**
     * Extensions are visible in two historical shapes at this point of the migration (this step
     * runs before MultiModuleMigrationStep and ExtensionsModuleMigrationStep): the extensions
     * aggregator when it declares modules, and the legacy store folders of pre-extensions-module
     * projects - restAPIExtensions/ and themes/ at the project root (7.x) or under app/ (8.x).
     * Purely advisory: a probe failure must not sink a migration whose poms are already
     * rewritten on disk, so parse errors degrade to the legacy-folder check.
     */
    private boolean hasExtensionModules(Path project) {
        var extensionsPom = project.resolve(BonitaProject.EXTENSIONS_MODULE);
        if (Files.exists(extensionsPom.resolve(POM_FILE_NAME))) {
            try {
                if (!loadMavenModel(extensionsPom).getModules().isEmpty()) {
                    return true;
                }
            } catch (CoreException e) {
                BonitaStudioLog.warning(String.format(
                        "Cannot read %s to check for extension modules, relying on the legacy folder check: %s",
                        extensionsPom.resolve(POM_FILE_NAME), e.getMessage()),
                        GroovyCoordinatesMigrationStep.class);
            }
        }
        return hasLegacyExtensionProjects(project)
                || hasLegacyExtensionProjects(project.resolve(BonitaProject.APP_MODULE));
    }

    /** The predicate ExtensionsModuleMigrationStep uses to move projects into the module. */
    private static boolean hasLegacyExtensionProjects(Path container) {
        return Stream.of("restAPIExtensions", "themes")
                .map(name -> container.resolve(name).toFile())
                .filter(File::isDirectory)
                // listFiles also returns null on an I/O error (unreadable folder, concurrent
                // deletion): the probe promises it cannot sink an already-saved migration
                .flatMap(folder -> Optional.ofNullable(folder.listFiles()).stream().flatMap(Stream::of))
                .filter(File::isDirectory)
                .anyMatch(candidate -> new File(candidate, POM_FILE_NAME).exists());
    }

    /**
     * Completes the provided Groovy module list whenever the migration rewrote any pom: after the
     * pass, some module of the project must declare every runtime-provided Groovy module. This
     * covers the hoisted-aggregator-root shape (the remove-only treatment of the root would leave
     * the project without Groovy at all) but also normalize-only poms whose interim-build list
     * was incomplete. Missing modules are declared in the last module pom (the app module when
     * present) - never in an aggregator pom, where every sub-module would inherit them.
     */
    private void ensureProvidedModulesDeclared(Path project, MigrationReport report) throws CoreException {
        var modules = modulePoms(project);
        var models = new LinkedHashMap<Path, Model>();
        for (var module : modules) {
            models.put(module, loadMavenModel(module));
        }
        var declared = models.values().stream()
                .flatMap(model -> model.getDependencies().stream())
                .toList();
        var missing = missingProvidedGroovyModules(declared);
        if (missing.isEmpty()) {
            return;
        }
        var target = modules.get(modules.size() - 1);
        var model = models.get(target);
        if (!model.getModules().isEmpty()) {
            report.updated(
                    "Action required: no module of the project declares all the Groovy modules provided by the Bonita runtime anymore - declare them in the module that needs them.");
            return;
        }
        missing.forEach(model.getDependencies()::add);
        saveMavenModel(model, target);
        report.updated(String.format(
                "The Groovy modules provided by the Bonita runtime (%s) have been declared in `%s`: no module of the project declared them after the migration.",
                missing.stream().map(Dependency::getArtifactId).collect(Collectors.joining(", ")),
                project.relativize(target.resolve(POM_FILE_NAME)).toString().replace('\\', '/')));
    }

    private boolean migratePom(Path module, MigrationReport report) throws CoreException {
        var model = loadMavenModel(module);
        var normalized = normalizeApacheGroovyEntries(model, report);
        var legacyDependencies = model.getDependencies().stream()
                .filter(GroovyCoordinatesMigrationStep::isStudioWrittenLegacyGroovyDependency)
                .toList();
        var hasLegacy = !legacyDependencies.isEmpty();
        if (hasLegacy) {
            model.getDependencies().removeAll(legacyDependencies);
            // the aggregator root pom of a multi-module project declares no dependencies when
            // generated by Studio: the provided modules belong to the app module, which gets its
            // own pass. A pre-split 7.x root pom has no <modules> and receives the full migration.
            var aggregatorPom = !model.getModules().isEmpty();
            if (!aggregatorPom) {
                missingProvidedGroovyModules(model.getDependencies())
                        .forEach(model.getDependencies()::add);
            }
            report.updated(aggregatorPom
                    ? "The legacy Groovy dependencies have been removed from the root pom of the multi-module project."
                    : "Groovy dependencies now use the org.apache.groovy coordinates, with versions managed by the bonita-project parent.");
            if (legacyDependencies.stream()
                    .map(Dependency::getArtifactId)
                    .anyMatch(DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID::equals)) {
                report.updated(
                        "The groovy-all dependency has been replaced by the Groovy modules provided by the Bonita runtime. The other modules it used to bring transitively (groovy-servlet, groovy-ant, groovy-swing, groovy-console, ...) are no longer on the classpath - add them explicitly to your pom.xml if your code requires them.");
            }
            legacyDependencies.stream()
                    .map(Dependency::getArtifactId)
                    .distinct()
                    .filter(artifactId -> !DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID.equals(artifactId))
                    .filter(artifactId -> !DefaultPluginVersions.GROOVY_MODULES.contains(artifactId))
                    .forEach(artifactId -> report.removed(String.format(
                            "The %s dependency has been removed: it is no longer provided by the Bonita runtime. Add it explicitly to your pom.xml if your code still requires it.",
                            artifactId)));
            BonitaStudioLog
                    .info("org.codehaus.groovy dependencies have been replaced with the org.apache.groovy modules");
        }
        if (normalized || hasLegacy) {
            saveMavenModel(model, module);
        }
        // reported even when nothing was migrated: a fully version-pinned legacy list resolves at
        // build time but silently runs a Groovy 3 compilation against the Groovy 4 runtime.
        // dependencyManagement and profiles are scanned too - Studio never wrote there, so those
        // entries are user-authored and only ever reported, never rewritten. The report itself
        // deduplicates messages, so an entry present in several poms is reported once.
        allDeclaredDependencies(model)
                .filter(has(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID))
                .map(GroovyCoordinatesMigrationStep::manualReviewMessage)
                .forEach(report::updated);
        // the apache-side counterpart of the codehaus net above: a provided module the runtime
        // does not ship compiles and then fails with NoClassDefFoundError at execution - the
        // groovy-ant failure class, which reappears whenever a module leaves GROOVY_MODULES.
        // Reported only, never removed: unlike groovy-ant this is not a known interim-template
        // shape, so it may be user intent
        classpathDependencies(model)
                .filter(has(DefaultPluginVersions.APACHE_GROOVY_GROUPID))
                .filter(GroovyCoordinatesMigrationStep::explicitProvidedScope)
                .filter(dependency -> !DefaultPluginVersions.GROOVY_MODULES.contains(dependency.getArtifactId()))
                .forEach(dependency -> report.updated(String.format(
                        "Action required: the org.apache.groovy:%s dependency is declared with the provided scope but is not provided by the Bonita runtime - it will fail at execution unless its scope is changed or it is removed.",
                        dependency.getArtifactId())));
        return normalized || hasLegacy;
    }

    /**
     * The runtime-provided Apache Groovy modules absent from the given declarations, reconciled
     * per artifact: one declared module (e.g. a lone core groovy added by the user) must not mark
     * the whole list as covered, and an unrelated org.apache.groovy artifact (e.g. a user
     * compile-scoped groovy-ant) never counts as one of the provided modules.
     */
    private List<Dependency> missingProvidedGroovyModules(List<Dependency> declared) {
        return DefaultPluginVersions.providedDependencies()
                .stream()
                .map(MavenDependency::toDependency)
                .filter(has(DefaultPluginVersions.APACHE_GROOVY_GROUPID))
                .filter(module -> declared.stream()
                        .noneMatch(has(module.getGroupId(), module.getArtifactId())))
                .toList();
    }

    /**
     * Normalizes org.apache.groovy entries written by intermediate 12.0 builds: the interim
     * templates pinned the modules to an explicit version (silently missing the next runtime
     * Groovy bump once out of parent management) and declared a provided groovy-ant the runtime
     * does not ship. Only provided-scoped entries are touched: they are the Studio-written shape,
     * a user compile dependency on an apache module is left alone.
     */
    private boolean normalizeApacheGroovyEntries(Model model, MigrationReport report) {
        var changed = false;
        // both filters are the two shapes of isStaleApacheEntry: the detection (appliesToProject)
        // and the mutation must accept exactly the same entries or the step either no-ops on a
        // shape it triggered on, or rewrites entries it claims not to apply to
        var staleGroovyAnt = model.getDependencies().stream()
                .filter(GroovyCoordinatesMigrationStep::isStaleProvidedGroovyAnt)
                .toList();
        if (!staleGroovyAnt.isEmpty()) {
            model.getDependencies().removeAll(staleGroovyAnt);
            report.removed(
                    "The org.apache.groovy:groovy-ant dependency has been removed: it is not provided by the Bonita runtime. Add it explicitly to your pom.xml with an appropriate scope if your code still requires it.");
            changed = true;
        }
        var pinnedModules = model.getDependencies().stream()
                .filter(GroovyCoordinatesMigrationStep::isVersionPinnedProvidedModule)
                .toList();
        if (!pinnedModules.isEmpty()) {
            pinnedModules.forEach(dependency -> dependency.setVersion(null));
            report.updated(
                    "The version pins on the provided org.apache.groovy modules have been removed: versions are managed by the bonita-project parent.");
            changed = true;
        }
        return changed;
    }

    /**
     * Unlike the codehaus predicate, a scope-less apache entry is NOT treated as Studio-written:
     * the interim templates always wrote an explicit provided scope, and a scope-less entry has
     * compile semantics - user intent.
     */
    private static boolean explicitProvidedScope(Dependency dependency) {
        return Artifact.SCOPE_PROVIDED.equals(dependency.getScope());
    }

    /**
     * The dependencies that reach a classpath: plain plus profile-scoped. Narrower than
     * {@link #allDeclaredDependencies(Model)} - dependencyManagement contributes nothing to a
     * classpath by itself, so a net whose message promises an execution failure must skip it.
     */
    private static Stream<Dependency> classpathDependencies(Model model) {
        return Stream.concat(model.getDependencies().stream(),
                model.getProfiles().stream().flatMap(profile -> profile.getDependencies().stream()));
    }

    private static Stream<Dependency> allDeclaredDependencies(Model model) {
        var profileDependencies = model.getProfiles().stream()
                .flatMap(profile -> Stream.concat(profile.getDependencies().stream(),
                        managedDependencies(profile.getDependencyManagement())));
        return Stream.concat(
                Stream.concat(model.getDependencies().stream(), managedDependencies(model.getDependencyManagement())),
                profileDependencies);
    }

    private static Stream<Dependency> managedDependencies(DependencyManagement management) {
        return Optional.ofNullable(management)
                .map(DependencyManagement::getDependencies)
                .stream()
                .flatMap(List::stream);
    }

    private static String manualReviewMessage(Dependency dependency) {
        var message = String.format(
                "Action required: the %s:%s dependency was left unchanged - it still uses the legacy org.codehaus.groovy coordinates, please review it manually.",
                dependency.getGroupId(), dependency.getArtifactId());
        if (DefaultPluginVersions.GROOVY_MODULES.contains(dependency.getArtifactId())) {
            message += " It may conflict with the org.apache.groovy module of the same name provided by the Bonita runtime.";
        }
        return message;
    }

    private static List<Path> modulePoms(Path project) {
        return Stream.of(project, project.resolve(BonitaProject.APP_MODULE))
                .filter(module -> Files.exists(module.resolve(POM_FILE_NAME)))
                .toList();
    }

    private static boolean isStudioWrittenLegacyGroovyDependency(Dependency dependency) {
        if (!DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID.equals(dependency.getGroupId())) {
            return false;
        }
        // the pre-modules monolith is migrated whatever its version or scope: it cannot run on the
        // Groovy 4 runtime, so a pin never means "keep me" the way a pinned module does
        if (DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID.equals(dependency.getArtifactId())) {
            return true;
        }
        // unlike AppProjectConfiguration.isInternalDependency, a null scope is accepted here:
        // Studio wrote the provided scope, but a hand-stripped scope still marks a Studio-written entry
        return dependency.getVersion() == null
                && (dependency.getScope() == null || Artifact.SCOPE_PROVIDED.equals(dependency.getScope()))
                && DefaultPluginVersions.LEGACY_GROOVY_MODULES.contains(dependency.getArtifactId());
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.13.0")) >= 0;
    }

    @Override
    public boolean appliesToProject(Path projectRoot) throws CoreException {
        for (var module : modulePoms(projectRoot)) {
            var model = loadMavenModel(module);
            if (allDeclaredDependencies(model)
                    .anyMatch(has(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID))) {
                return true;
            }
            // plain dependencies only, unlike the codehaus arm above: this arm gates
            // normalizeApacheGroovyEntries, which never touches profiles or dependencyManagement
            if (model.getDependencies().stream().anyMatch(GroovyCoordinatesMigrationStep::isStaleApacheEntry)) {
                return true;
            }
        }
        return false;
    }

    /** Interim 12.0 template shapes: a provided groovy-ant, or a provided module carrying a version pin. */
    private static boolean isStaleApacheEntry(Dependency dependency) {
        return isStaleProvidedGroovyAnt(dependency) || isVersionPinnedProvidedModule(dependency);
    }

    private static boolean isStaleProvidedGroovyAnt(Dependency dependency) {
        return DefaultPluginVersions.APACHE_GROOVY_GROUPID.equals(dependency.getGroupId())
                && DefaultPluginVersions.GROOVY_ANT_ARTIFACT_ID.equals(dependency.getArtifactId())
                && explicitProvidedScope(dependency);
    }

    private static boolean isVersionPinnedProvidedModule(Dependency dependency) {
        return DefaultPluginVersions.APACHE_GROOVY_GROUPID.equals(dependency.getGroupId())
                && DefaultPluginVersions.GROOVY_MODULES.contains(dependency.getArtifactId())
                && explicitProvidedScope(dependency)
                && dependency.getVersion() != null;
    }

}
