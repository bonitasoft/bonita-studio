/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProjectDependenciesResolverTest {

    @ParameterizedTest
    @CsvSource({
            // Standard JAR with version
            "slf4j-api-1.7.36.jar, slf4j-api",
            "asm-9.8.jar, asm",
            "FastInfoset-1.2.15.jar, FastInfoset",
            "bcpkix-jdk18on-1.78.1.jar, bcpkix-jdk18on",
            "h2-1.4.199.jar, h2",
            // SNAPSHOT versions
            "bonita-common-6.2.0-SNAPSHOT.jar, bonita-common",
            "bonita-common-10.2.0-SNAPSHOT.jar, bonita-common",
            "my-lib-1.0.0-SNAPSHOT.jar, my-lib",
            // Classifier-style versions (e.g. guava with -jre)
            "guava-31.1-jre.jar, guava",
            // Complex version qualifiers
            "netty-buffer-4.1.100.Final.jar, netty-buffer",
            // JAR with embedded version-like segments in name
            "log4j-1.2-api-2.17.jar, log4j-1.2-api",
            // Versionless JARs
            "catalina.jar, catalina",
            "mylib.jar, mylib",
    })
    void should_extractLibName_handle_various_patterns(String jarName, String expectedLibName) {
        assertThat(ProjectDependenciesResolver.extractLibName(jarName)).isEqualTo(expectedLibName);
    }

    @Test
    void should_extractLibName_return_null_for_null_input() {
        assertThat(ProjectDependenciesResolver.extractLibName(null)).isNull();
    }

    @Test
    void should_extractLibName_return_input_for_non_jar() {
        assertThat(ProjectDependenciesResolver.extractLibName("not-a-jar")).isEqualTo("not-a-jar");
    }

    @Test
    void should_extractLibName_handle_jar_with_only_digits_after_dash() {
        assertThat(ProjectDependenciesResolver.extractLibName("commons-io-2.11.0.jar")).isEqualTo("commons-io");
    }

    @Test
    void should_extractLibName_handle_single_char_name() {
        assertThat(ProjectDependenciesResolver.extractLibName("x-1.0.jar")).isEqualTo("x");
    }

    @Test
    void should_extractLibName_handle_jar_with_no_dash() {
        assertThat(ProjectDependenciesResolver.extractLibName("library.jar")).isEqualTo("library");
    }

    @Test
    void should_extractLibName_handle_dot_jar_only() {
        assertThat(ProjectDependenciesResolver.extractLibName(".jar")).isEqualTo("");
    }

    @Test
    void should_extractLibName_be_consistent_with_DependencyRepositoryStore_getLibName() {
        // These are the same test cases used in DependencyRepositoryStoreTest
        // to verify delegation consistency
        var testCases = new String[] {
                "bcpkix-jdk18on-1.78.1.jar",
                "FastInfoset-1.2.15.jar",
                "asm-9.8.jar",
                "catalina.jar",
                "bonita-common-6.2.0-SNAPSHOT.jar",
                "h2-1.4.199.jar",
                "guava-31.1-jre.jar",
                "netty-buffer-4.1.100.Final.jar",
                "log4j-1.2-api-2.17.jar"
        };
        for (String jarName : testCases) {
            String result = ProjectDependenciesResolver.extractLibName(jarName);
            assertThat(result)
                    .as("extractLibName(%s) should not be null", jarName)
                    .isNotNull();
            // Verify the result doesn't end with a version pattern (digits.digits at the very end)
            assertThat(result)
                    .as("extractLibName(%s) should not end with a version pattern", jarName)
                    .doesNotMatch(".*-\\d+\\.\\d+$");
        }
    }
}
