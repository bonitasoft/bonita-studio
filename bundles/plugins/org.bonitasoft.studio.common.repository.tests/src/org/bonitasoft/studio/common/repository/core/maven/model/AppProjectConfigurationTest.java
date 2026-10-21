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

import org.apache.maven.model.Dependency;
import org.junit.jupiter.api.Test;

class AppProjectConfigurationTest {

    @Test
    void doNotDetectDependenciesWithoutAnExplicitProvidedScope() {
        // a user pom dependency without <scope>provided</scope> is not matched as internal:
        // the GAV comparison includes the scope, and a raw model dependency has a null scope
        var dependency = new Dependency();
        dependency.setGroupId("org.apache.groovy");
        dependency.setArtifactId("groovy-json");
        assertThat(AppProjectConfiguration.isInternalDependency(dependency)).isFalse();
    }

    @Test
    void detectBothEngineCoordinatesAsInternalDependencies() {
        // both are added unconditionally, whatever the running product: this fences a re-cached
        // list or a dropped explicit add
        assertThat(AppProjectConfiguration
                .isInternalDependency(providedDependency("org.bonitasoft.engine", "bonita-common"))).isTrue();
        assertThat(AppProjectConfiguration
                .isInternalDependency(providedDependency("com.bonitasoft.engine", "bonita-common-sp"))).isTrue();
    }

    @Test
    void detectCurrentGroovyModulesAsInternalDependencies() {
        assertThat(AppProjectConfiguration.isInternalDependency(providedDependency("org.apache.groovy", "groovy-json")))
                .isTrue();
    }

    @Test
    void detectLegacyGroovyModulesAsInternalDependencies() {
        assertThat(AppProjectConfiguration
                .isInternalDependency(providedDependency("org.codehaus.groovy", "groovy-json"))).isTrue();
        assertThat(AppProjectConfiguration
                .isInternalDependency(providedDependency("org.codehaus.groovy", "groovy-servlet"))).isTrue();
    }

    @Test
    void doNotDetectRemovedOrForeignDependenciesAsInternal() {
        // groovy-servlet was removed from the runtime and is never generated with apache coordinates
        assertThat(AppProjectConfiguration
                .isInternalDependency(providedDependency("org.apache.groovy", "groovy-servlet"))).isFalse();
        assertThat(AppProjectConfiguration.isInternalDependency(providedDependency("com.acme", "some-lib"))).isFalse();
    }

    private Dependency providedDependency(String groupId, String artifactId) {
        var dependency = new Dependency();
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setScope("provided");
        return dependency;
    }
}
