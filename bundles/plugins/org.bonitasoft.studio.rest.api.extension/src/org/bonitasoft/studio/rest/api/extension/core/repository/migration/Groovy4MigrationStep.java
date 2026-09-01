/**
 * Copyright (C) 2026 Bonitasoft S.A.
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;

/**
 * Moves the Groovy dependencies of an extension to the {@code org.apache.groovy} coordinates of Groovy 4.
 * <p>
 * The Bonita runtime ships Groovy 4 from 12.0 on, and its BOM manages {@code org.apache.groovy} only. A
 * {@code org.codehaus.groovy} dependency left behind therefore fails to resolve when the archetype declared it without
 * a version, and when it carries a version it resolves a Groovy 3 artifact that is then absent at execution.
 * <p>
 * Only the dependencies the archetypes wrote are rewritten. Unlike the user project poms, an extension pom declares
 * them with a version reference rather than versionless: {@link Groovy3MigrationStep} writes
 * {@code ${groovy-all.version}} and the 7.13-era archetypes wrote {@code ${groovy.version}}, so a property reference
 * marks a generated entry just as an absent version does. A literal version or a scope other than provided is user
 * intent and is left alone.
 */
public class Groovy4MigrationStep implements MavenModelMigration {

    /**
     * The {@code org.codehaus.groovy} artifacts Groovy 4 did NOT move: groovy-eclipse-compiler and
     * groovy-eclipse-batch belong to the Groovy-Eclipse project, which never changed coordinates. Advising a move
     * would point the user at something {@code org.apache.groovy} does not publish.
     */
    private static final List<String> COORDINATES_THAT_NEVER_MOVED = List.of(
            DefaultPluginVersions.GROOVY_ECLIPSE_COMPILER_ARTIFACT_ID,
            DefaultPluginVersions.GROOVY_ECLIPSE_BATCH_ARTIFACT_ID);

    /**
     * Modules {@code groovy-all} brought transitively that the Bonita runtime does not provide. What the
     * monolith contained is a fact about how the Groovy project packaged it, not about anything in
     * {@link DefaultPluginVersions}, so it cannot be derived - {@code LEGACY_GROOVY_MODULES} holds the modules
     * Studio wrote into poms, which is a different and much smaller set. Filtered against
     * {@code GROOVY_MODULES} so the enumeration can never name a module the runtime does provide - and empty
     * only if the runtime ever provides every one of them, which is what the guard on it tests.
     */
    private static final List<String> MODULES_GROOVY_ALL_BROUGHT = List
            .of("groovy-servlet", DefaultPluginVersions.GROOVY_ANT_ARTIFACT_ID, "groovy-swing", "groovy-console")
            .stream()
            .filter(module -> !DefaultPluginVersions.GROOVY_MODULES.contains(module))
            .toList();

    /** The version properties the Groovy 3 generators wrote, which a migrated pom no longer needs. */
    private static final List<String> GROOVY_VERSION_PROPERTIES = List.of(
            DefaultPluginVersions.GROOVY_VERSION_PROPERTY, DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY);

    /**
     * The version references the generators wrote, derived so they cannot drift from the properties this step
     * also deletes. Any other reference is the user's own property and is treated like a literal version.
     */
    private static final List<String> GENERATED_VERSION_REFERENCES = GROOVY_VERSION_PROPERTIES.stream()
            .map(property -> "${" + property + "}")
            .toList();

    /** The only dependency type the generators wrote for a Groovy module. */
    private static final String JAR_TYPE = "jar";

    @Override
    public MigrationReport migrate(Model model, ProjectMetadata metadata) {
        MigrationReport report = new MigrationReport();
        BonitaStudioLog.info(String.format("Starting %s on %s...", Groovy4MigrationStep.class.getName(),
                metadata.getArtifactId()));

        var groovyAllReplacement = replaceGroovyAll(model, report);
        var changedTheModel = groovyAllReplacement.replaced();

        List<String> migrated = new ArrayList<>();
        var dependencies = model.getDependencies().iterator();
        while (dependencies.hasNext()) {
            var dependency = dependencies.next();
            if (!isGeneratedLegacyModule(dependency)) {
                continue;
            }
            if (!DefaultPluginVersions.GROOVY_MODULES.contains(dependency.getArtifactId())) {
                // the runtime stopped shipping it: kept with the apache coordinates it would resolve from
                // the BOM at build time and then be missing at execution
                dependencies.remove();
                changedTheModel = true;
                // two calls, one per section, in either order: MigrationReport keeps the lists apart and
                // AsciidocMigrationReportWriter renders them separately. Every Action required line in
                // either bundle belongs under updates
                report.removed(String.format(
                        "The `%s:%s` dependency has been removed: the Bonita runtime no longer provides this"
                                + " Groovy module.",
                        DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, dependency.getArtifactId()));
                // the apache coordinates, not the ones just removed: a user following this instruction with
                // the legacy groupId would put a Groovy 3 artifact back on a Groovy 4 runtime
                report.updated(String.format(
                        "Action required: the `%s:%s` dependency was removed because the Bonita runtime no"
                                + " longer provides it. Declare `%s:%s` explicitly, with a version and a"
                                + " compile scope, if your code still requires it.",
                        DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, dependency.getArtifactId(),
                        DefaultPluginVersions.APACHE_GROOVY_GROUPID, dependency.getArtifactId()));
                continue;
            }
            if (declaresApacheModule(model.getDependencies(), dependency.getArtifactId())) {
                // the extension was hand-patched for Groovy 4 and the generated entry was left next to the
                // new one: rewriting it would declare the same module twice
                dependencies.remove();
                changedTheModel = true;
                report.removed(String.format(
                        "The `%s:%s` dependency has been removed: this extension already declares `%s:%s`.",
                        DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, dependency.getArtifactId(),
                        DefaultPluginVersions.APACHE_GROOVY_GROUPID, dependency.getArtifactId()));
                continue;
            }
            dependency.setGroupId(DefaultPluginVersions.APACHE_GROOVY_GROUPID);
            dependency.setVersion(null);
            dependency.setScope(Artifact.SCOPE_PROVIDED);
            changedTheModel = true;
            migrated.add(dependency.getArtifactId());
        }
        reportGroovyAllReplacement(model, report, groovyAllReplacement);
        if (!migrated.isEmpty()) {
            report.updated(String.format(
                    "The Groovy dependencies of this extension (%s) now use the `%s` coordinates, with the"
                            + " version managed by the runtime BOM: the Bonita runtime ships Groovy 4.",
                    String.join(", ", migrated), DefaultPluginVersions.APACHE_GROOVY_GROUPID));
        }
        if (changedTheModel) {
            removeOrphanedGroovyVersionProperties(model, report);
        }

        // reported even when nothing was rewritten: a literal version resolves at build time and then runs
        // Groovy 3 code against the Groovy 4 runtime. dependencyManagement and profiles are scanned too,
        // and only ever reported - the generators never wrote there, so those entries are the user's
        PomDeclarations.allLocated(model)
                .filter(located -> isReplaceableLegacyGroovy(located.dependency()))
                .map(Groovy4MigrationStep::manualReviewMessage)
                .forEach(report::updated);
        // every predicate above skips a codehaus declaration with no artifactId, so without this a pom
        // carrying one beside real legacy coordinates would have it silently ignored
        // located, like the net above: the message would otherwise be the same string for every such
        // declaration, and MigrationReport keeps one copy - so a pom carrying two would report one. There is
        // no coordinate to search for either, which leaves the location as the only handle the user has
        PomDeclarations.allLocated(model)
                .filter(located -> DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID
                        .equals(located.dependency().getGroupId())
                        && located.dependency().getArtifactId() == null)
                .forEach(located -> report.updated(String.format(
                        "Action required: a `%s` dependency declared in %s declares no `artifactId`, so it was"
                                + " left unchanged. Give it one, or remove it: the Bonita runtime ships Groovy 4"
                                + " under the `%s` coordinates.",
                        DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, located.location(),
                        DefaultPluginVersions.APACHE_GROOVY_GROUPID)));

        BonitaStudioLog.info(String.format("%s completed for %s.", Groovy4MigrationStep.class.getName(),
                metadata.getArtifactId()));
        return report;
    }

    @Override
    public boolean appliesTo(Model model, ProjectMetadata metadata) {
        return TargetRuntime.targetsBonita12OrLater(metadata)
                && PomDeclarations.all(model).anyMatch(Groovy4MigrationStep::isReplaceableLegacyGroovy);
    }

    /**
     * What {@link #replaceGroovyAll} did, so the sentence naming the resulting modules can be emitted once the
     * rewrite loop has finished. The list has to be read off the pom the step actually produces.
     * <p>
     * The two flags look redundant and are not. {@code replaced} answers "did the model change", which is what
     * the orphaned-property cleanup needs: removing groovy-all changes the pom even when every module it stood
     * for was already declared. {@code addedNothing} answers "were modules added", which is what picks the
     * message. Collapsing them into one drops the property cleanup on exactly that pom.
     */
    private record GroovyAllReplacement(boolean replaced, String scope, boolean addedNothing) {

        static final GroovyAllReplacement NONE = new GroovyAllReplacement(false, null, false);
    }

    /**
     * Replaces the pre-modules monolith by the modules the runtime provides. Migrated whatever its version or scope,
     * unlike a module: groovy-all cannot run on the Groovy 4 runtime at all, so a pin never means "keep me".
     * <p>
     * The full provided list rather than the two modules the current archetype generates: groovy-all put every module
     * on the compile classpath, so a narrower replacement would stop an extension using groovy-xml or groovy-sql from
     * compiling.
     */
    private static GroovyAllReplacement replaceGroovyAll(Model model, MigrationReport report) {
        var groovyAll = model.getDependencies().stream()
                .filter(Groovy4MigrationStep::isLegacyGroovyAll)
                .toList();
        if (groovyAll.isEmpty()) {
            return GroovyAllReplacement.NONE;
        }
        // the replacements take the position of the monolith, so the Groovy block stays where the reader of
        // the pom expects it. A module the loop rewrites keeps its own position instead - groovy-dateutil,
        // which Groovy3MigrationStep appends, therefore ends up at the tail rather than in the block
        var monolith = groovyAll.get(0);
        var position = indexOfDeclaration(model.getDependencies(), monolith);
        model.getDependencies().removeIf(declaration -> groovyAll.stream().anyMatch(g -> g == declaration));
        // an absent scope is compile to Maven, but read as provided here: the runtime provides Groovy, so
        // compile semantics only package a second copy beside the runtime's own
        var scope = monolith.getScope() == null ? Artifact.SCOPE_PROVIDED : monolith.getScope();
        // codehaus names count as already declared too: adding the apache module of a name the user already
        // pinned would put two Groovy cores on the classpath. The pin is reported instead
        var alreadyDeclared = model.getDependencies().stream()
                .filter(dependency -> DefaultPluginVersions.APACHE_GROOVY_GROUPID.equals(dependency.getGroupId())
                        || DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID.equals(dependency.getGroupId()))
                .map(Dependency::getArtifactId)
                .toList();
        var added = DefaultPluginVersions.GROOVY_MODULES.stream()
                .filter(module -> !alreadyDeclared.contains(module))
                .toList();
        model.getDependencies().addAll(position, added.stream()
                .map(module -> apacheModule(module, scope))
                .toList());
        if (added.isEmpty()) {
            // the names, not necessarily the coordinates: alreadyDeclared counts codehaus names too, so this
            // can be satisfied entirely by legacy pins the loop declines to rewrite. Those get their own
            // Action required line, and claiming the modules are declared would contradict it
            report.removed(String.format(
                    "The `%s:%s` dependency has been removed: this extension already declares those Groovy"
                            + " module names.",
                    DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID,
                    DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID));
        }
        if (!MODULES_GROOVY_ALL_BROUGHT.isEmpty()) {
            // no Action required marker: whether any of these matters depends on what the extension's Groovy
            // code imports, which a pom does not say. A marker that fires on every monolith and then concedes
            // there may be nothing to do is the permanently-on warning GroovyCoordinatesMigrationStep argues
            // against where it gates its own advisory
            report.updated(String.format(
                    "`%s` brought other Groovy modules transitively that the Bonita runtime does not provide"
                            + " (`%s`, among others). If your code imports any of them, declare it explicitly"
                            + " with a version and a compile scope.",
                    DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID,
                    String.join("`, `", MODULES_GROOVY_ALL_BROUGHT)));
        }
        return new GroovyAllReplacement(true, scope, added.isEmpty());
    }

    /**
     * Names the modules the extension ends up declaring. Called after the rewrite loop, never from inside
     * {@link #replaceGroovyAll}: a module the loop is about to move onto the apache coordinates is still
     * codehaus while that method runs, and would be missing from a list the user reads as the result.
     */
    private static void reportGroovyAllReplacement(Model model, MigrationReport report,
            GroovyAllReplacement replacement) {
        if (!replacement.replaced() || replacement.addedNothing()) {
            // nothing added means every module was already declared, so there is no inventory to credit this
            // step with and no module carrying the monolith's scope: the advisory below would name modules
            // that do not exist. The rewrite loop set the pre-existing ones to provided already
            return;
        }
        var declaredModules = model.getDependencies().stream()
                .filter(dependency -> DefaultPluginVersions.APACHE_GROOVY_GROUPID.equals(dependency.getGroupId()))
                .map(Dependency::getArtifactId)
                // the membership test below throws on null instead of answering false, and this model is read
                // raw: without this, one hand-edited line sinks the whole extension migration
                .filter(Objects::nonNull)
                .filter(DefaultPluginVersions.GROOVY_MODULES::contains)
                .distinct()
                .toList();
        report.updated(String.format(
                "This extension declares the Groovy modules the Bonita runtime provides (%s), with their"
                        + " versions managed by the runtime BOM. The `%s:%s` dependency has been removed.",
                String.join(", ", declaredModules), DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID,
                DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID));
        if (!Artifact.SCOPE_PROVIDED.equals(replacement.scope())) {
            // only the modules added here carry this scope: the loop matches an absent or provided scope, so
            // a module the user scoped differently is reported rather than rewritten
            report.updated(String.format(
                    "The modules added in place of `%s` carry the `%s` scope it declared. The Bonita runtime"
                            + " provides Groovy itself, so consider the provided scope instead - otherwise"
                            + " those modules are packaged in the extension archive next to the runtime's own"
                            + " Groovy.",
                    DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID, replacement.scope()));
        }
    }

    private static Dependency apacheModule(String artifactId, String scope) {
        var dependency = new Dependency();
        dependency.setGroupId(DefaultPluginVersions.APACHE_GROOVY_GROUPID);
        dependency.setArtifactId(artifactId);
        dependency.setScope(scope);
        return dependency;
    }

    private static int indexOfDeclaration(List<Dependency> dependencies, Dependency declaration) {
        for (int index = 0; index < dependencies.size(); index++) {
            if (dependencies.get(index) == declaration) {
                return index;
            }
        }
        // the caller derived the declaration from this very list, so this is unreachable; a plausible
        // wrong answer would outlive the caller that makes it impossible
        throw new IllegalStateException("The declaration to locate is not the one this pom declares");
    }

    /**
     * No null guard, unlike the other membership tests here: the only caller reaches this through
     * {@link #isReplaceableLegacyGroovy}, and {@link #hasCodehausGroupId} has already required an artifactId.
     * A guard on a path that cannot be taken makes the ones that can look decorative.
     */
    private static boolean isNamedRuntimeModule(Dependency dependency) {
        return DefaultPluginVersions.GROOVY_MODULES.contains(dependency.getArtifactId());
    }

    private static boolean declaresApacheModule(List<Dependency> declarations, String artifactId) {
        return declarations.stream()
                .anyMatch(dependency -> DefaultPluginVersions.APACHE_GROOVY_GROUPID.equals(dependency.getGroupId())
                        && artifactId.equals(dependency.getArtifactId()));
    }

    /**
     * Deliberately without the classifier and type guards {@link #isGeneratedLegacyModule} applies: those exist
     * because rewriting a classified module would name a coordinate org.apache.groovy does not publish, and there
     * is nothing to rewrite here. Every groovy-all is removed whatever it carries, because the monolith does not
     * run on Groovy 4 in any variant - an indy classifier included.
     */
    private static boolean isLegacyGroovyAll(Dependency dependency) {
        return hasCodehausGroupId(dependency)
                && DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID.equals(dependency.getArtifactId());
    }

    private static String manualReviewMessage(PomDeclarations.Located located) {
        var dependency = located.dependency();
        // the location is part of the message, not decoration: MigrationReport keeps one copy of a string, so
        // an unqualified line reports a coordinate once however many places declare it
        var message = String.format(
                "Action required: the `%s:%s` dependency declared in %s was left unchanged - it still uses the"
                        + " legacy %s coordinates, which the Bonita runtime no longer provides. Review it"
                        + " manually.",
                dependency.getGroupId(), dependency.getArtifactId(), located.location(),
                DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID);
        // nothing is said about a managed entry: it reaches no classpath by itself, so it is neither the only
        // copy of anything nor in conflict with one. Both branches below would be false of it
        if (!located.managed() && isNamedRuntimeModule(dependency)) {
            // two different outcomes, and the quiet one is the expensive one: replaceGroovyAll withholds the
            // apache module whose name a codehaus pin already holds, so on that pom the runtime's module is
            // not there to conflict with - the pin is the only copy, and it is a Groovy 3 jar
            message += declaresApacheModule(located.classpathSiblings(), dependency.getArtifactId())
                    ? String.format(" It may conflict with the %s module of the same name this pom also declares.",
                            DefaultPluginVersions.APACHE_GROOVY_GROUPID)
                    : String.format(" The %s module of that name was not declared alongside it, so this is the"
                            + " only `%s` on the classpath: it resolves at build time and then runs Groovy 3"
                            + " against a Groovy 4 runtime.",
                            DefaultPluginVersions.APACHE_GROOVY_GROUPID, dependency.getArtifactId());
        }
        // managed excluded for the reason given above, and one specific to this sentence: a management
        // entry needs a version of its own whatever the runtime manages, so the 12.0 BOM is not what broke it
        if (!located.managed() && dependency.getVersion() == null) {
            // a versionless legacy coordinate resolved from the pre-12 BOM, which manages org.apache.groovy
            // alone from 12.0: the build stops before anything runs
            message += " It declares no version of its own and the runtime BOM no longer manages that"
                    + " coordinate, so the build cannot resolve it until you give it one.";
        }
        return message;
    }

    /**
     * Removes the Groovy version properties nothing names any more. {@link PomDeclarations#namesProperty} is the
     * list of places checked - not everywhere a pom in general can name one, so read its javadoc before adding a
     * caller.
     * <p>
     * Reached only when this step changed the model, since a removal orphans a property as thoroughly as a
     * rewrite. Nothing supplies {@code groovy.version} once the parent is attached, so a surviving reference
     * resolves to nothing: {@link Groovy3MigrationStep} and {@link ParentMigrationStep} apply the same liveness
     * rule at either end of the pipeline, and this loop is what deletes the property in the common case.
     */
    private static void removeOrphanedGroovyVersionProperties(Model model, MigrationReport report) {
        // to a fixpoint: one of these properties can name the other, so a single pass would keep the second
        // alive on the strength of a value it is about to delete
        List<String> orphaned = new ArrayList<>();
        boolean removedOne;
        do {
            var dead = GROOVY_VERSION_PROPERTIES.stream()
                    .filter(model.getProperties()::containsKey)
                    .filter(property -> !PomDeclarations.namesProperty(model, property))
                    .findFirst();
            dead.ifPresent(property -> {
                model.getProperties().remove(property);
                orphaned.add(property);
            });
            removedOne = dead.isPresent();
        } while (removedOne);
        if (!orphaned.isEmpty()) {
            report.removed(String.format(
                    "The following properties have been removed, since nothing in this pom names them any more"
                            + " now that the Groovy dependencies take their version from the runtime BOM: %s.",
                    String.join(", ", orphaned)));
        }
    }

    /**
     * A dependency the archetypes or {@link Groovy3MigrationStep} wrote, and which this step may therefore rewrite.
     * A null scope is accepted: the generators always wrote the provided scope, but a hand-stripped scope still marks
     * a generated entry, and compile semantics on a runtime-provided module is not something to preserve.
     */
    private static boolean isGeneratedLegacyModule(Dependency dependency) {
        return hasCodehausGroupId(dependency)
                && DefaultPluginVersions.LEGACY_GROOVY_MODULES.contains(dependency.getArtifactId())
                // a classifier or a non-jar type is not something the generators wrote, and rewriting one would
                // produce a coordinate org.apache.groovy does not publish - the old indy variant, for instance
                && dependency.getClassifier() == null
                && (dependency.getType() == null || JAR_TYPE.equals(dependency.getType()))
                && (dependency.getScope() == null || Artifact.SCOPE_PROVIDED.equals(dependency.getScope()))
                && (dependency.getVersion() == null || isGeneratedVersionReference(dependency.getVersion()));
    }

    /**
     * A legacy coordinate that has somewhere to move to.
     * <p>
     * Groovy 4 moved every module to {@code org.apache.groovy}, so what has to be enumerated is the exceptions,
     * not the members: an allowlist of the modules the runtime provides would stay silent on groovy-testng,
     * groovy-swing and groovy-ant, which moved too and which a pom can legitimately declare. Silence there is
     * worse than noise - the extension keeps a Groovy 3 artifact on a Groovy 4 runtime while its report claims
     * it was migrated.
     */
    private static boolean isReplaceableLegacyGroovy(Dependency dependency) {
        return hasCodehausGroupId(dependency) && !COORDINATES_THAT_NEVER_MOVED.contains(dependency.getArtifactId());
    }

    /**
     * Guards the artifactId as well as the groupId, because both callers hand it straight to a
     * {@link List#of} membership test and those throw on null rather than answering false. A dependency with no
     * artifactId is invalid Maven, but the model is read raw, so a hand-edited pom would turn that into a
     * MigrationException that sinks the whole extension migration.
     */
    private static boolean hasCodehausGroupId(Dependency dependency) {
        return DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID.equals(dependency.getGroupId())
                && dependency.getArtifactId() != null;
    }

    /**
     * The two references {@link Groovy3MigrationStep} and the 7.13-era archetypes wrote, and only those. Any
     * property reference used to count, which made a version the user pinned through a property of their own
     * look generated: rewritten silently, and its property left behind dead because this step deletes only the
     * two it knows. A pin is a pin whichever way it is spelled, so those take the reported-not-rewritten path a
     * literal version takes.
     * <p>
     * Null-safe on purpose, and not redundantly: a dependency can leave its version to
     * {@code dependencyManagement} or an imported BOM, every caller reaches this with whatever the pom held, and
     * the reference list is immutable, so its {@code contains} throws on null rather than answering false.
     */
    private static boolean isGeneratedVersionReference(String version) {
        return version != null && GENERATED_VERSION_REFERENCES.contains(version);
    }

}
