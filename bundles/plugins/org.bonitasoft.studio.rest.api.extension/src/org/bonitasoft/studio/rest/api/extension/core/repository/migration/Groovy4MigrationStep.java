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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Profile;
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
 * <p>
 * It also corrects the Spock pin. Spock publishes one artifact per Groovy major version, so a pom whose Groovy
 * coordinates move to 4 while its {@code spock-core} stays on a {@code -groovy-3.0} variant compiles and then
 * fails its specifications at execution.
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

    /**
     * The Groovy version properties the generators and {@link Groovy3MigrationStep} wrote. Both the property list
     * this step deletes and the reference list it recognises are built from this, so a third one added here is
     * picked up by both rather than by whichever was remembered.
     */
    private static final List<String> GROOVY_VERSION_PROPERTIES = List.of(
            DefaultPluginVersions.GROOVY_VERSION_PROPERTY, DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY);

    /** Aliased from the step that writes these pins, as {@link Groovy3MigrationStep#SPOCK_VERSION} is. */
    private static final String SPOCK_GROUP_ID = Groovy3MigrationStep.SPOCK_GROUP_ID;
    private static final String SPOCK_CORE_ARTIFACT_ID = Groovy3MigrationStep.SPOCK_CORE_ARTIFACT_ID;
    private static final String SPOCK_REPORTS_GROUP_ID = Groovy3MigrationStep.SPOCK_REPORTS_GROUP_ID;
    private static final String SPOCK_REPORTS_ARTIFACT_ID = Groovy3MigrationStep.SPOCK_REPORTS_ARTIFACT_ID;
    /**
     * The property the archetypes wrote spock-core's version under, dead once Groovy3MigrationStep points the pin
     * at {@code spock.version} instead.
     */
    private static final String SPOCK_CORE_VERSION_PROPERTY = SPOCK_CORE_ARTIFACT_ID + ".version";

    /**
     * The version properties the generators and {@link Groovy3MigrationStep} wrote, which nothing needs once the
     * dependencies reading them take their version from the runtime BOM or carry it on the coordinate. Removed only
     * when nothing else in the pom still names them - {@code spock.version} in particular is shared with
     * {@code com.athaydes:spock-reports}, which this step does not rewrite.
     */
    private static final List<String> GENERATED_VERSION_PROPERTIES = Stream
            .concat(GROOVY_VERSION_PROPERTIES.stream(),
                    Stream.of(Groovy3MigrationStep.SPOCK_VERSION_PROPERTY, SPOCK_CORE_VERSION_PROPERTY))
            .toList();

    /**
     * The Groovy version references the generators wrote, derived from the property list above so the two cannot
     * drift. Only the Groovy ones: a {@code ${spock.version}} reference marks no Groovy coordinate, and treating
     * it as generated would let the coordinate rewrite reach a dependency it has no business in.
     */
    private static final List<String> GENERATED_VERSION_REFERENCES = GROOVY_VERSION_PROPERTIES.stream()
            .map(property -> "${" + property + "}")
            .toList();

    /**
     * The Spock variant the Groovy 4 runtime needs, and the pattern matching every other variant. An exception is
     * the only safe shape here: Spock published -groovy-2.0, -groovy-2.4, -groovy-2.5 and -groovy-3.0, and an
     * allowlist would stay silent on whichever one it forgot.
     */
    private static final Pattern SPOCK_GROOVY_VARIANT = Pattern.compile(".*-groovy-(\\d+\\.\\d+)$");
    private static final String GROOVY_4_VERSION = "4.0";

    /** Derived, so the qualifier and the version it names cannot drift apart. */
    private static final String GROOVY_4_SPOCK_QUALIFIER = "-groovy-" + GROOVY_4_VERSION;

    /**
     * Comfortably more than the spock.version / spock-core.version indirection these poms carry. The bound is
     * here so a cyclic pom terminates, not to police how deep a chain may legitimately be.
     */
    private static final int MAX_PROPERTY_HOPS = 5;

    /**
     * The Spock the Bonita 12 archetypes generate with - bonita-actorfilter-archetype and
     * bonita-rest-api-extension-archetype 2.0.0 both pin it - so a migrated project lands where a new one starts.
     */
    private static final String SPOCK_VERSION_FOR_GROOVY_4 = "2.4" + GROOVY_4_SPOCK_QUALIFIER;

    /**
     * Appended when the version being reported is one this migration produced rather than one the user chose.
     * Groovy3MigrationStep sets {@code spock.version} and points {@code com.athaydes:spock-reports} at it two
     * steps earlier in the same chain, so for the common path the value here is not a pre-existing pin. Which
     * release of that artifact matches Groovy 4 stays a per-artifact question this step has no table for - the
     * report says where the value came from instead of guessing at a replacement.
     * <p>
     * Only for the one value that chain writes, on the coordinate it writes it onto: the rest of
     * {@link #SPOCK_VERSIONS_BONITA_WROTE} came from archetypes, and the same version on any other Spock
     * artifact was set by hand, so {@link #noteFor} keys on both to keep the sentence off a pin nothing here
     * produced.
     */
    private static final String SPOCK_PIN_THIS_MIGRATION_WROTE = " Note that this migration set that version"
            + " itself, from the Spock pin the Groovy 3 step writes, rather than reading one you chose.";

    /**
     * Appended wherever a Spock 1 pin is named - corrected, reported at the root, or reported in a profile.
     * Three migration fixtures carry one: the 1.0.5, 1.1.1 and 1.3.1 archetypes pinned Spock 1, so the
     * correction moves those poms across a major version, and Groovy3MigrationStep links the same guide for the
     * same jump.
     * <p>
     * Worded for both paths: on the reported one nothing has been moved yet, and Spock 1 published nothing past
     * {@code -groovy-2.5}, so naming the qualifier alone would be advice that cannot be followed.
     */
    private static final String SPOCK_2_UPGRADE_NOTE = " Note that this crosses from Spock 1 to Spock 2, which"
            + " runs on the JUnit 5 platform - see the"
            + " https://spockframework.org/spock/docs/2.0/migration_guide.html#_migration_guide_2_0[migration"
            + " guide], since a Spock 1 specification does not necessarily run unchanged.";

    /**
     * The stale spock-core pins Bonita itself wrote, and the only versions this step replaces. A version outside
     * this list was set by hand, and a hand-set version is a decision: it is reported so the user makes the next
     * one, rather than silently overwritten.
     * <p>
     * Each entry is a value shipped by Bonita, not a guess - the migration fixtures in this bundle and the poms
     * the released archetypes generate are where they come from, and a new archetype pin has to be added here to
     * be corrected. The fixtures are also asserted directly, so a pin present in one but missing here fails
     * rather than being quietly reported.
     */
    private static final List<String> SPOCK_VERSIONS_BONITA_WROTE = List.of(
            // bonita-rest-api-extension-archetype 1.0.5 and 1.1.1, inline on the dependency
            "1.0-groovy-2.4",
            "1.2-groovy-2.4",
            // bonita-rest-api-extension-archetype 1.3.1, as spock-core.version
            "1.3-groovy-2.4",
            // a later archetype, same property
            "2.0-M4-groovy-3.0",
            // bonita-connector-archetype, as spock-core.version, for every 7.13 or later runtime. Reached
            // this step only once connectors joined the shared list
            "2.3-groovy-3.0",
            // archetype 1.5.0 and Groovy3MigrationStep, as spock.version. Referenced rather than copied: a
            // bump there cannot quietly turn this step's correction into an Action required line
            Groovy3MigrationStep.SPOCK_VERSION);

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
        changedTheModel |= migrateSpockVariant(model, report);

        // after the Spock pass, not before: correcting spock-core onto its coordinate can leave the property
        // it was declared with dead, and Groovy3MigrationStep writes spock.version whether the pom declares
        // Spock or not. The Groovy properties are equally orphaned either way, so nothing is lost by asking
        // once, at the end
        if (changedTheModel) {
            removeOrphanedVersionProperties(model, report);
        }

        // reported even when nothing was rewritten: a literal version resolves at build time and then runs
        // Groovy 3 code against the Groovy 4 runtime. dependencyManagement and profiles are scanned too, and
        // this pass only ever reports them - the generators never wrote a coordinate there, so those entries
        // are the user's. The Spock pass below does correct a managed entry, which the generators did write
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

    /**
     * Note this also gates the Spock check: a pom declaring no replaceable Groovy coordinate at all - a Java
     * extension whose tests happen to use Spock - is not visited. Widening it would turn a Groovy coordinate
     * migration into a general test-framework check, which is not what this step is for.
     * <p>
     * It also makes the Spock correction one-shot: this step is what removes the legacy coordinate, so a pom it has
     * migrated is never visited again, whatever a later Studio adds. The correction therefore cannot ship later
     * than the coordinate migration - a pom migrated without it is out of its reach for good.
     */
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
     * Spock publishes one artifact per Groovy major version, and the {@code -groovy-2.4} / {@code -groovy-3.0}
     * variants do not run on Groovy 4. Migrating the main dependencies without saying so would hand the user a pom
     * whose report reads clean and whose tests do not run.
     * <p>
     * A {@code spock-core} pin that Bonita wrote is corrected here; anything else is reported and left alone.
     * The rule and its reasons are at {@link #migrateRootSpockVariant} - which release of spock-reports or
     * spock-spring matches Groovy 4 is a per-artifact question this step has no table for, and a hand-set
     * version is the user's decision.
     * <p>
     * Order matters, and nothing but this comment enforces it: the root pass runs first so the profile pass sees
     * the corrected root value. A profile inheriting that value is then already right and stays unreported.
     */
    private static boolean migrateSpockVariant(Model model, MigrationReport report) {
        // read before the root pass writes anything: correcting a dependency onto its coordinate detaches it
        // from the property it was declared with, and a profile overriding that property then stops affecting
        // it. Asking afterwards cannot tell that apart from a profile that never overrode anything
        var overriddenInProfiles = spockPropertiesOverriddenInProfiles(model);
        // the root declarations are corrected in place; a profile is an alternative configuration this step
        // does not rewrite, matching JakartaServletMigrationStep and the rest of this class. No dedup set:
        // both messages below are keyed by coordinate and resolved version, and MigrationReport.updated
        // already drops a repeat.
        //
        // reduce rather than anyMatch: every declaration has to be visited, because the mapper corrects
        // versions and writes report lines as it goes, and anyMatch would stop at the first true
        var corrected = PomDeclarations.of(model.getDependencies(), model.getDependencyManagement())
                .filter(Groovy4MigrationStep::isSpockVariantCarrier)
                .map(dependency -> migrateRootSpockVariant(model, dependency, report, overriddenInProfiles))
                .reduce(false, Boolean::logicalOr);
        model.getProfiles().forEach(profile -> reportProfileSpockVariant(model, profile, report));
        return corrected;
    }

    /**
     * @return the profiles overriding each property a root Spock declaration reads, keyed by property name and
     *         each named with the version its override resolves to, less any whose override resolves to the
     *         version the correction writes. Read before the corrections, because a correction removes the
     *         reference that makes the override matter
     *         <p>
     *         The property a declaration names directly, where {@code resolved} follows a chain of them. A pom
     *         whose {@code spock.version} names a second property that a profile overrides is therefore not
     *         reported as detached - the correction is still right, and no archetype writes that chain
     */
    private static Map<String, List<String>> spockPropertiesOverriddenInProfiles(Model model) {
        Map<String, List<String>> overrides = new LinkedHashMap<>();
        PomDeclarations.of(model.getDependencies(), model.getDependencyManagement())
                .filter(Groovy4MigrationStep::isSpockVariantCarrier)
                .map(Dependency::getVersion)
                .filter(Groovy4MigrationStep::isPropertyReference)
                .map(Groovy4MigrationStep::propertyNameOf)
                .distinct()
                .forEach(property -> {
                    var profiles = model.getProfiles().stream()
                            .filter(profile -> profile.getProperties().getProperty(property) != null)
                            .map(profile -> overrideWorthReviewing(profile, property, model))
                            .flatMap(Optional::stream)
                            .toList();
                    if (!profiles.isEmpty()) {
                        overrides.put(property, profiles);
                    }
                });
        return overrides;
    }

    private static boolean migrateRootSpockVariant(Model model, Dependency dependency, MigrationReport report,
            Map<String, List<String>> overriddenInProfiles) {
        // the generators wrote the version as a property reference, and Groovy3MigrationStep rewrites it to
        // another one, so the qualifier is never on the dependency itself
        var declared = dependency.getVersion();
        var version = resolvedAtRoot(declared, model);
        if (version == null || version.contains("${")) {
            // a declaration with no version at all leaves it to dependencyManagement or an imported BOM,
            // which is legitimate and not this step's business
            if (declared == null) {
                return false;
            }
            // a property only a profile defines is the profile pass's business: it resolves this same
            // declaration in that profile's scope and reports it where the value the user must change lives.
            // Only when that pass will actually speak, though: it reports a stale variant and nothing else,
            // so deferring merely because some profile defines the property leaves a pom whose default scope
            // cannot resolve spock-core with no line from either pass
            if (isPropertyReference(declared) && someProfileResolvesToStaleVariant(model, dependency)) {
                return false;
            }
            // anything else unresolved is said out loud rather than skipped: silence is how a pom whose Spock
            // version cannot be read at all gets a clean report. Deliberately does not name what is missing -
            // a composite of several references has no single property to point at - and hedges on a parent,
            // because this reads the raw model: a property a parent pom defines resolves at build time unseen
            report.updated(String.format(
                    "Action required: `%s:%s` takes its version from `%s`, which this step cannot resolve for"
                            + " the default build from this pom alone, so the variant that build gets cannot be"
                            + " checked here. Unless a parent pom defines it, give the default build a value to"
                            + " read: define it here, or pin the coordinate to the matching `%s` release of that"
                            + " artifact, if it publishes one.",
                    dependency.getGroupId(), dependency.getArtifactId(), declared, GROOVY_4_SPOCK_QUALIFIER));
            return false;
        }
        if (!isVariantBuiltForOlderGroovy(version)) {
            return false;
        }
        // spock-core only, and only where the pin is one Bonita wrote. spock-core is the one Spock artifact
        // whose Groovy 4 counterpart is unambiguous, so it is the only one that can be corrected at all; and a
        // version Bonita did not write was set by hand, which makes it the user's to change.
        // Written on the coordinate rather than the property it was declared with: Groovy3MigrationStep points
        // com.athaydes:spock-reports at that same ${spock.version}, and the two artifacts do not share a
        // version stream - spock-reports is versioned on its own stream - so moving the property would leave that
        // dependency unresolvable
        if (isSpockCore(dependency) && SPOCK_VERSIONS_BONITA_WROTE.contains(version)) {
            dependency.setVersion(SPOCK_VERSION_FOR_GROOVY_4);
            report.updated(String.format(
                    "`%s:%s` has been moved from `%s` to `%s`: Spock publishes one artifact per"
                            + " Groovy major version, and the earlier variant does not run on the"
                            + " Groovy 4 the Bonita runtime ships.%s",
                    dependency.getGroupId(), dependency.getArtifactId(), version, SPOCK_VERSION_FOR_GROOVY_4,
                    isSpock1Pin(version) ? SPOCK_2_UPGRADE_NOTE : ""));
            // outside the message dedup on purpose: two declarations of one coordinate resolve to the same
            // version and so produce the same correction line, but only the one declared through the property
            // is detached from it
            reportDetachedProfileOverride(dependency, declared, overriddenInProfiles, report);
            return true;
        }
        // everything else keeps its version: which release of spock-reports or spock-spring matches Groovy 4
        // is a per-artifact question this step has no table for, and a hand-set spock-core pin is a decision
        // this step does not overrule
        report.updated(String.format(
                "Action required: `%s:%s` resolves to `%s`, a variant built for an older Groovy."
                        + " It does not run on the Groovy 4 the Bonita runtime ships - move it to the"
                        + " matching `%s` release of that artifact, if it publishes one.%s",
                dependency.getGroupId(), dependency.getArtifactId(), version, GROOVY_4_SPOCK_QUALIFIER,
                noteFor(dependency, version)));
        return false;
    }

    /**
     * @return the profile's id with the version its override resolves to, so the detachment line can say which
     *         kind of override was lost instead of sending the reader to the pom - or nothing for an override
     *         already resolving to what the correction writes: building with that profile gave the same version
     *         before and after, so nothing was detached from it that mattered, and a line would ask for a review
     *         of a no-op
     */
    private static Optional<String> overrideWorthReviewing(Profile profile, String property, Model model) {
        var resolved = resolvedInProfile(profile.getProperties().getProperty(property), profile, model);
        if (SPOCK_VERSION_FOR_GROOVY_4.equals(resolved)) {
            return Optional.empty();
        }
        return Optional.of(profile.getId() + " (" + (resolved == null ? "unresolved" : resolved) + ")");
    }

    /**
     * Whether a profile resolves this declaration to a variant the profile pass will report, which is the only
     * case where deferring to it is safe. Uses that pass's own predicate rather than a second copy of it.
     */
    private static boolean someProfileResolvesToStaleVariant(Model model, Dependency dependency) {
        return model.getProfiles().stream()
                .map(profile -> resolvedInProfile(dependency.getVersion(), profile, model))
                .filter(Objects::nonNull)
                .anyMatch(Groovy4MigrationStep::isVariantBuiltForOlderGroovy);
    }

    /**
     * Whether the pin is a Spock 1 one. The move to {@code 2.4-groovy-4.0} is then a major upgrade and not the
     * variant swap the correction message describes on its own: Spock 2 runs on the JUnit 5 platform, so a
     * Spock 1 specification does not necessarily run unchanged.
     * <p>
     * {@link #noteFor} applies this to any Spock-family artifact, including {@code com.athaydes:spock-reports},
     * whose versions are its own rather than Spock's. Its 1.x line does pair with Spock 1, so the major still
     * says what it needs to here.
     */
    private static boolean isSpock1Pin(String version) {
        return version.startsWith("1.");
    }

    /**
     * Says so when a correction detached a dependency from a property a profile overrides, whatever that
     * profile's own value is. Where the value cannot run on Groovy 4 the detachment costs nothing; where it is a
     * newer variant the build now resolves this step's version instead, which is a downgrade and the case most
     * worth a line. Filtering down to the harmless kind leaves the only lossy shape silent, and a profile that
     * no longer does what its name says either way.
     */
    private static void reportDetachedProfileOverride(Dependency dependency, String declared,
            Map<String, List<String>> overriddenInProfiles, MigrationReport report) {
        if (!isPropertyReference(declared)) {
            return;
        }
        var profiles = overriddenInProfiles.getOrDefault(propertyNameOf(declared), List.of());
        if (profiles.isEmpty()) {
            return;
        }
        report.updated(String.format(
                "`%s` no longer takes its version from `%s`, which these profiles override: %s. Building with"
                        + " one of them now resolves `%s` rather than the profile's - an override on a variant"
                        + " that cannot run on Groovy 4 is better lost, while a newer one is a downgrade.",
                coordinateOf(dependency), declared, String.join(", ", profiles), SPOCK_VERSION_FOR_GROOVY_4));
    }

    /**
     * A profile is reported, never rewritten - but only when the variant it resolves to is actually wrong. Reporting
     * every profile that mentions Spock would fire on a profile already pinned to the Groovy 4 variant, and on one
     * sharing a property the root correction just fixed: noise describing a pom this step did not produce.
     * <p>
     * One pass over two sets of declarations, because a profile breaks the build in two ways that land on the same
     * coordinate: it declares Spock itself, or it overrides the property a root dependency reads without declaring
     * Spock at all. Both feed one {@code reported} set, so a profile doing both gets one line rather than two, and
     * a profile doing only the second is still reported.
     */
    private static void reportProfileSpockVariant(Model model, Profile profile, MigrationReport report) {
        Set<String> reported = new HashSet<>();
        PomDeclarations.of(profile.getDependencies(), profile.getDependencyManagement())
                .filter(Groovy4MigrationStep::isSpockVariantCarrier)
                .forEach(dependency -> reportStaleInProfile(model, profile, dependency, report, reported));
        PomDeclarations.of(model.getDependencies(), model.getDependencyManagement())
                .filter(Groovy4MigrationStep::isSpockVariantCarrier)
                .filter(dependency -> resolvesDifferentlyIn(profile, model, dependency))
                .forEach(dependency -> reportStaleInProfile(model, profile, dependency, report, reported));
    }

    private static void reportStaleInProfile(Model model, Profile profile, Dependency dependency, MigrationReport report,
            Set<String> reported) {
        var version = resolvedInProfile(dependency.getVersion(), profile, model);
        if (version == null || !isVariantBuiltForOlderGroovy(version)) {
            return;
        }
        // keyed on the version as well as the coordinate, as the root pass is: a profile that both declares the
        // artifact and overrides the property a root declaration reads resolves both to the same version and gets
        // one line, while two different pins in one profile are two things to change
        if (!reported.add(coordinateOf(dependency) + "@" + version)) {
            return;
        }
        report.updated(String.format(
                "Action required: the `%s` profile resolves `%s` to `%s`, a variant built for an older Groovy."
                        + " Only the `%s` one runs on the Groovy 4 the Bonita runtime ships. The profile is left"
                        + " untouched, since it can hold the configuration of an older runtime on purpose.%s",
                profile.getId(), coordinateOf(dependency), version, GROOVY_4_SPOCK_QUALIFIER,
                // the Spock 1 note only. noteFor would add the provenance one, and Groovy3MigrationStep
                // rewrites model.getDependencies() alone - on a profile declaration the chain wrote nothing
                isSpock1Pin(version) ? SPOCK_2_UPGRADE_NOTE : ""));
    }

    /**
     * Whether this profile changes what a root declaration resolves to - by overriding the property it reads, or by
     * defining a property the root scope leaves dangling. When it does not, the root pass has already corrected or
     * reported that declaration, and repeating it per profile would be noise.
     */
    private static boolean resolvesDifferentlyIn(Profile profile, Model model, Dependency dependency) {
        var declared = dependency.getVersion();
        return !Objects.equals(resolvedInProfile(declared, profile, model), resolvedAtRoot(declared, model));
    }

    /**
     * @return the version resolved the way a build activating this profile would resolve it: the profile's own
     *         properties first, then the pom's. For reporting only - this step does not write to a profile
     */
    private static String resolvedInProfile(String declared, Profile profile, Model model) {
        return resolved(declared, property -> Optional
                .ofNullable(profile.getProperties().getProperty(property))
                .orElseGet(() -> model.getProperties().getProperty(property)));
    }

    /**
     * The artifacts pinned to a Spock Groovy variant, which is not the {@code org.spockframework} ones alone:
     * {@code com.athaydes:spock-reports} is versioned the same way and Groovy3MigrationStep points it at the same
     * property, so a groupId filter left it silently on a variant that cannot run. That one artifact and not its
     * whole groupId, which has nothing else to do with Spock.
     * <p>
     * Requires an artifactId, as the Groovy membership tests do: without one there is no coordinate to name, and
     * the report said {@code org.spockframework:null}.
     */
    private static boolean isSpockVariantCarrier(Dependency dependency) {
        return dependency.getArtifactId() != null
                && (SPOCK_GROUP_ID.equals(dependency.getGroupId())
                        || (SPOCK_REPORTS_GROUP_ID.equals(dependency.getGroupId())
                                && SPOCK_REPORTS_ARTIFACT_ID.equals(dependency.getArtifactId())));
    }

    private static boolean isSpockCore(Dependency dependency) {
        return SPOCK_GROUP_ID.equals(dependency.getGroupId())
                && SPOCK_CORE_ARTIFACT_ID.equals(dependency.getArtifactId());
    }

    private static boolean isSpockReports(Dependency dependency) {
        return SPOCK_REPORTS_GROUP_ID.equals(dependency.getGroupId())
                && SPOCK_REPORTS_ARTIFACT_ID.equals(dependency.getArtifactId());
    }

    /**
     * The note belonging on a version this step reports rather than corrects, or nothing.
     * <p>
     * A Spock 1 pin comes first whoever set it: the move it faces crosses the major version, and that is the
     * larger fact about it. Otherwise the one version the Groovy 3 step writes is named as such - and on this path that
     * can only be spock-reports: the chain writes two coordinates, and a spock-core at a version it wrote is
     * corrected rather than reported, so it never arrives here. The same version on {@code spock-spring} or any
     * other Spock artifact is the user's own, and saying otherwise would be false.
     */
    private static String noteFor(Dependency dependency, String version) {
        if (isSpock1Pin(version)) {
            return SPOCK_2_UPGRADE_NOTE;
        }
        if (Groovy3MigrationStep.SPOCK_VERSION.equals(version) && isSpockReports(dependency)) {
            return SPOCK_PIN_THIS_MIGRATION_WROTE;
        }
        return "";
    }

    /**
     * Older, not merely different: a variant qualified {@code -groovy-4.1} or {@code -groovy-5.0} runs on the
     * Groovy 4 the runtime ships or on something later, and telling the user to move it back to
     * {@code -groovy-4.0} would be asking for a downgrade.
     * <p>
     * A version carrying no qualifier at all - {@code 1.2.13}, a Groovy 2.4 build of spock-reports - answers
     * false and is not flagged. Nothing here can tell such a version from one that is fine, and the chain
     * reaches that artifact anyway: Groovy3MigrationStep rewrites it onto {@code ${spock.version}} first.
     */
    private static boolean isVariantBuiltForOlderGroovy(String version) {
        var qualifier = SPOCK_GROOVY_VARIANT.matcher(version);
        if (!qualifier.matches()) {
            return false;
        }
        return new DefaultArtifactVersion(qualifier.group(1))
                .compareTo(new DefaultArtifactVersion(GROOVY_4_VERSION)) < 0;
    }

    /**
     * @return the version with a single {@code ${property}} reference resolved against the pom's own properties, or
     *         null. Deliberately NOT resolved against a profile's properties: the caller writes the corrected
     *         version back to this same scope, and reading from a profile while writing to the root would add a
     *         root property the active profile then overrides - a no-op the report would call a success
     */
    private static String resolvedAtRoot(String version, Model model) {
        return resolved(version, property -> model.getProperties().getProperty(property));
    }

    /**
     * Follows a chain of property references, because these poms really do carry both {@code spock.version} and
     * {@code spock-core.version} and one can name the other - the orphaned-property cleanup iterates to a fixpoint
     * for the same reason. Bounded rather than looped-until-stable: a pom whose properties reference each other in
     * a cycle is unresolvable, and this returns null there rather than spinning.
     */
    private static String resolved(String version, UnaryOperator<String> lookup) {
        var resolvedVersion = version;
        for (int hop = 0; hop < MAX_PROPERTY_HOPS; hop++) {
            if (!isPropertyReference(resolvedVersion)) {
                return resolvedVersion;
            }
            resolvedVersion = lookup.apply(propertyNameOf(resolvedVersion));
            if (resolvedVersion == null) {
                return null;
            }
        }
        // tested after the loop as well, so a chain resolvable in exactly MAX_PROPERTY_HOPS steps resolves
        return isPropertyReference(resolvedVersion) ? null : resolvedVersion;
    }

    /**
     * Removes the generated version properties nothing names any more - the Groovy ones and Spock's.
     * {@link PomDeclarations#namesProperty} is the list of places checked - not everywhere a pom in general can
     * name one, so read its javadoc before adding a caller.
     * <p>
     * Reached only when this step changed the model, since a removal orphans a property as thoroughly as a
     * rewrite. Nothing supplies {@code groovy.version} once the parent is attached, so a surviving reference
     * resolves to nothing: {@link Groovy3MigrationStep} and {@link ParentMigrationStep} apply the same liveness
     * rule at either end of the pipeline, and this loop is what deletes the property in the common case.
     */
    private static void removeOrphanedVersionProperties(Model model, MigrationReport report) {
        // to a fixpoint: one of these properties can name the other, so a single pass would keep the second
        // alive on the strength of a value it is about to delete
        List<String> orphaned = new ArrayList<>();
        boolean removedOne;
        do {
            var dead = GENERATED_VERSION_PROPERTIES.stream()
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
                            + " now that the dependencies reading them take their version from the runtime BOM or"
                            + " carry it on the coordinate: %s.",
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

    /**
     * A version that opens a property reference, ends on its closing brace, and opens no second one. A version
     * composing two of them -
     * {@code ${spock.base}-${spock.qualifier}} - opens and closes like one, and treating it as one yields a
     * property name no pom defines, which then reads as "no scope defines this property" rather than "this step
     * cannot resolve this shape".
     */
    private static boolean isPropertyReference(String version) {
        return version != null && version.startsWith("${") && version.endsWith("}")
                && version.indexOf("${", 2) < 0;
    }

    private static String propertyNameOf(String reference) {
        return reference.substring(2, reference.length() - 1);
    }

    private static String coordinateOf(Dependency dependency) {
        return String.format("%s:%s", dependency.getGroupId(), dependency.getArtifactId());
    }

}
