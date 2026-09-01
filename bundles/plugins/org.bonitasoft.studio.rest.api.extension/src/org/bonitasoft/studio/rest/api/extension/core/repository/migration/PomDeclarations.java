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

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.maven.model.BuildBase;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.model.Profile;
import org.codehaus.plexus.util.xml.Xpp3Dom;

/**
 * What a pom declares, as the extension migration steps need to read it.
 * <p>
 * Shared rather than repeated per step: "declared" has to mean the same thing to every step, or a net widened in one
 * of them silently stops matching what the others consider declared, and two steps then disagree about whether the
 * same pom needs migrating.
 */
final class PomDeclarations {

    private PomDeclarations() {
    }

    /**
     * Every dependency the pom declares: the plain ones, the managed ones, and the ones a profile contributes. Wider
     * than what reaches a classpath - a managed entry contributes nothing by itself - so it suits deciding whether a
     * pom mentions a coordinate at all, and reporting the entries a step chose not to rewrite.
     */
    static Stream<Dependency> all(Model model) {
        return Stream.concat(
                of(model.getDependencies(), model.getDependencyManagement()),
                model.getProfiles().stream()
                        .flatMap(profile -> of(profile.getDependencies(), profile.getDependencyManagement())));
    }

    /**
     * A declaration and where the pom declares it, so a report can tell two occurrences of one coordinate apart.
     * The location is prose for a user, not an identifier: it names the element, and the profile too when the
     * declaration belongs to one.
     */
    record Located(Dependency dependency, String location, boolean managed, List<Dependency> classpathSiblings) {
    }

    /**
     * The same declarations as {@link #all(Model)}, each carrying where it was found. A report built from
     * {@link #all(Model)} produces one string per coordinate however many places declare it, and
     * MigrationReport keeps only the first - so a user fixes the occurrence they find and never learns about
     * the other.
     */
    static Stream<Located> allLocated(Model model) {
        return Stream.concat(
                located(model.getDependencies(), model.getDependencyManagement(), null, model.getDependencies()),
                model.getProfiles().stream()
                        .flatMap(profile -> located(profile.getDependencies(), profile.getDependencyManagement(),
                                profile.getId(), classpathOf(model, profile))));
    }

    /**
     * What shares a classpath with a declaration a profile makes: the profile's own dependencies and the root's,
     * because a root declaration is there whichever profile is active. Scoping the question to the profile alone
     * would answer "nothing else declares that" about a pom whose root declares it.
     */
    private static List<Dependency> classpathOf(Model model, Profile profile) {
        return Stream.concat(model.getDependencies().stream(), profile.getDependencies().stream()).toList();
    }

    private static Stream<Located> located(List<Dependency> dependencies,
            DependencyManagement dependencyManagement, String profileId, List<Dependency> classpathSiblings) {
        return Stream.concat(
                dependencies.stream().map(dependency -> new Located(dependency, describe("`<dependencies>`",
                        profileId), false, classpathSiblings)),
                Optional.ofNullable(dependencyManagement)
                        .map(DependencyManagement::getDependencies)
                        .stream()
                        .flatMap(List::stream)
                        // a managed entry reaches no classpath by itself, so it has no siblings on one
                        .map(dependency -> new Located(dependency,
                                describe("`<dependencyManagement>`", profileId), true, List.of())));
    }

    private static String describe(String element, String profileId) {
        return profileId == null
                ? element
                : String.format("%s of the `%s` profile", element, profileId);
    }

    /**
     * Whether anything in the pom still names {@code ${property}}. Covers dependency versions, the plugins, their
     * versions, their own dependencies and their configuration blocks, and the other property values - at root and
     * inside every profile.
     * <p>
     * Matched with {@code contains} rather than equality, because a version can embed the reference in a composite
     * such as {@code ${groovy.version}-01}. Callers delete the property on the strength of this answer, so a miss
     * corrupts a pom rather than merely adding noise - and one implementation keeps the two steps that ask this
     * question from answering it differently.
     * <p>
     * Not exhaustive, and the caller should not read it as such: {@code <reporting>} and {@code build.extensions}
     * are not scanned. The list above is what an extension pom generated or migrated by Studio can hold.
     * <p>
     * A caller that rewrites the property instead of deleting it needs
     * {@link #namesPropertyOutsideDependencyVersions} as well - the same question with the dependency versions
     * left out.
     */
    static boolean namesProperty(Model model, String property) {
        var reference = "${" + property + "}";
        return all(model).map(Dependency::getVersion).anyMatch(version -> names(version, reference))
                || namesPropertyOutsideDependencyVersions(model, property);
    }

    /**
     * Whether anything other than a dependency version names {@code ${property}}: a plugin's version, one of a
     * plugin's own dependencies, a configuration block, or another property's value.
     * <p>
     * The distinction is for callers that rewrite the property rather than delete it. A dependency version holds
     * the version of the artifact it belongs to, so a Groovy property named there holds a Groovy version. The
     * places above can hold something else: groovy-eclipse-compiler and groovy-eclipse-batch are published as
     * {@code 3.0.8-01}, so writing a plain Groovy version into a property one of them names would leave a
     * reference to an artifact that was never published.
     */
    static boolean namesPropertyOutsideDependencyVersions(Model model, String property) {
        var reference = "${" + property + "}";
        return propertyValues(model).anyMatch(value -> names(value, reference))
                || plugins(model).anyMatch(plugin -> names(plugin.getVersion(), reference)
                        || plugin.getDependencies().stream()
                                .map(Dependency::getVersion)
                                .anyMatch(version -> names(version, reference))
                        || namedByConfiguration(plugin, reference));
    }

    /**
     * A plugin's configuration, and each execution's, walked as the DOM it is: gmavenplus and the groovy-eclipse
     * compiler both take a Groovy version there, so a property named only inside one would otherwise be judged dead
     * and deleted.
     */
    private static boolean namedByConfiguration(Plugin plugin, String reference) {
        return Stream.concat(Stream.of(plugin.getConfiguration()),
                plugin.getExecutions().stream().map(PluginExecution::getConfiguration))
                .filter(Xpp3Dom.class::isInstance)
                .map(Xpp3Dom.class::cast)
                .anyMatch(configuration -> namesInDom(configuration, reference));
    }

    private static boolean namesInDom(Xpp3Dom node, String reference) {
        if (names(node.getValue(), reference)) {
            return true;
        }
        if (Stream.of(node.getAttributeNames()).anyMatch(name -> names(node.getAttribute(name), reference))) {
            return true;
        }
        return Stream.of(node.getChildren()).anyMatch(child -> namesInDom(child, reference));
    }

    private static boolean names(String value, String reference) {
        return value != null && value.contains(reference);
    }

    private static Stream<String> propertyValues(Model model) {
        return Stream.concat(model.getProperties().values().stream(),
                model.getProfiles().stream().flatMap(profile -> profile.getProperties().values().stream()))
                .map(String::valueOf);
    }

    /** The plugins a pom declares, in build and in pluginManagement, including the ones a profile contributes. */
    private static Stream<Plugin> plugins(Model model) {
        return Stream.concat(buildPlugins(model.getBuild()),
                model.getProfiles().stream().flatMap(profile -> buildPlugins(profile.getBuild())));
    }

    private static Stream<Plugin> buildPlugins(BuildBase build) {
        if (build == null) {
            return Stream.empty();
        }
        return Stream.concat(build.getPlugins().stream(),
                build.getPluginManagement() == null
                        ? Stream.empty()
                        : build.getPluginManagement().getPlugins().stream());
    }

    static Stream<Dependency> of(List<Dependency> dependencies, DependencyManagement dependencyManagement) {
        return Stream.concat(dependencies.stream(),
                Optional.ofNullable(dependencyManagement)
                        .map(DependencyManagement::getDependencies)
                        .stream()
                        .flatMap(List::stream));
    }
}
