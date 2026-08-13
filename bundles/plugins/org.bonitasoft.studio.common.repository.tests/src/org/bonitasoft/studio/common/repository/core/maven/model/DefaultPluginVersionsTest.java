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
package org.bonitasoft.studio.common.repository.core.maven.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.eclipse.core.runtime.CoreException;
import org.junit.jupiter.api.Test;

class DefaultPluginVersionsTest {

    @Test
    void generateOnlyApacheGroovyModulesAsProvidedDependencies() {
        assertThat(DefaultPluginVersions.providedDependencies())
                .anyMatch(BonitaCommonDependency.class::isInstance);
        assertThat(DefaultPluginVersions.providedDependencies())
                .filteredOn(dependency -> !(dependency instanceof BonitaCommonDependency))
                .isNotEmpty()
                .allMatch(dependency -> "org.apache.groovy".equals(dependency.toGAV().getGroupId()))
                // not shipped by the runtime bundle: must never be generated as provided
                .noneMatch(dependency -> "groovy-ant".equals(dependency.toGAV().getArtifactId()))
                .noneMatch(dependency -> "groovy-servlet".equals(dependency.toGAV().getArtifactId()));
    }

    @Test
    void groovyModulesStayConsistentWithTheLocalRepositoryTemplatePoms() throws Exception {
        // enforces the DefaultPluginVersions javadoc contract; the template poms are not part of
        // the built bundles, so they are located through the source tree
        var communityTemplate = findAncestorResolving(
                Path.of("org.bonitasoft.studio.local.repository", "src", "template", "pom.xml"));
        if (isMavenRun()) {
            // tycho-surefire passes the module basedir explicitly: failing to locate the template
            // from there means the lookup drifted, not that the source tree is absent
            assertThat(communityTemplate)
                    .as("local.repository template pom not found from the tests module basedir")
                    .isPresent();
        }
        assumeTrue(communityTemplate.isPresent(),
                "local.repository template pom not reachable from the test working directory");
        assertTemplateMatchesGroovyModules(communityTemplate.orElseThrow());

        // the SP template only exists in the subscription checkout (absent from the community
        // repo) - but when the subscription tree is present, a missing template is a failure,
        // not a skip: an SP-side rename must not silently stop enforcing the contract
        var subscriptionPlugins = findAncestorResolving(Path.of("subscription", "bundles", "plugins"));
        if (subscriptionPlugins.isPresent()) {
            var spTemplate = subscriptionPlugins.orElseThrow()
                    .resolve(Path.of("org.bonitasoft.studio.local.repository.sp", "src", "template", "pom.xml"));
            assertThat(spTemplate)
                    .as("local.repository.sp template pom not found in the subscription tree")
                    .exists();
            assertTemplateMatchesGroovyModules(spTemplate);
        }
    }

    /** Set by the tycho-surefire configuration of this module; absent in IDE-launched runs. */
    private final Optional<String> mavenBasedir = Optional
            .ofNullable(System.getProperty("bonita.tests.basedir"));

    private boolean isMavenRun() {
        return mavenBasedir.isPresent();
    }

    private Path lookupStart() {
        return mavenBasedir.map(Path::of).orElseGet(() -> Path.of("").toAbsolutePath());
    }

    private void assertTemplateMatchesGroovyModules(Path templatePom) {
        Model model;
        try {
            model = MavenProjectHelper.readModel(templatePom.toFile());
        } catch (CoreException e) {
            throw new AssertionError("Cannot parse template pom " + templatePom, e);
        }
        assertThat(model.getDependencies())
                .as("template %s", templatePom)
                .noneMatch(dependency -> "org.codehaus.groovy".equals(dependency.getGroupId()));
        assertThat(model.getDependencies())
                .as("template %s", templatePom)
                .filteredOn(dependency -> "org.apache.groovy".equals(dependency.getGroupId()))
                .allMatch(dependency -> dependency.getVersion() == null)
                // losing the provided scope would ship Groovy into the user application assembly
                .allMatch(dependency -> "provided".equals(dependency.getScope()))
                .extracting(Dependency::getArtifactId)
                .containsExactlyInAnyOrderElementsOf(DefaultPluginVersions.GROOVY_MODULES);
    }

    private Optional<Path> findAncestorResolving(Path relativePath) {
        var dir = lookupStart();
        while (dir != null) {
            var candidate = dir.resolve(relativePath);
            if (Files.exists(candidate)) {
                return Optional.of(candidate);
            }
            if (Files.exists(dir.resolve(".git"))) {
                // the checkout root: probing beyond it cannot find project files
                return Optional.empty();
            }
            dir = dir.getParent();
        }
        return Optional.empty();
    }
}
