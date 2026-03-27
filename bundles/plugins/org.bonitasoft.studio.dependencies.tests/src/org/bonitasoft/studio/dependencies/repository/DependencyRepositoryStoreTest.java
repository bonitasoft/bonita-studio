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
package org.bonitasoft.studio.dependencies.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesResolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DependencyRepositoryStoreTest {

    private DependencyRepositoryStore store;

    @Before
    public void setUp() throws Exception {
        store = spy(new DependencyRepositoryStore());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getLibName_delegate_to_extractLibName() {
        assertThat(store.getLibName("bcpkix-jdk18on-1.78.1.jar")).isEqualTo("bcpkix-jdk18on");
        assertThat(store.getLibName("FastInfoset-1.2.15.jar")).isEqualTo("FastInfoset");
        assertThat(store.getLibName("asm-9.8.jar")).isEqualTo("asm");
        assertThat(store.getLibName("catalina.jar")).isEqualTo("catalina");
        assertThat(store.getLibName("bonita-common-6.2.0-SNAPSHOT.jar")).isEqualTo("bonita-common");
    }

    @Test
    public void should_getLibName_be_consistent_with_extractLibName() {
        var testCases = new String[] {
                "bcpkix-jdk18on-1.78.1.jar",
                "FastInfoset-1.2.15.jar",
                "asm-9.8.jar",
                "catalina.jar",
                "bonita-common-6.2.0-SNAPSHOT.jar",
                "h2-1.4.199.jar"
        };
        for (String jarName : testCases) {
            assertThat(store.getLibName(jarName))
                    .as("getLibName(%s)", jarName)
                    .isEqualTo(ProjectDependenciesResolver.extractLibName(jarName));
        }
    }

    @Test
    public void should_getLibVersion_still_work_after_refactoring() {
        assertThat(store.getLibVersion("slf4j-api-1.6.1.jar")).isEqualTo("1.6.1");
        assertThat(store.getLibVersion("catalina.jar")).isEqualTo("");
        assertThat(store.getLibVersion("bonita-common-6.2.0-SNAPSHOT.jar")).isEqualTo("6.2.0-SNAPSHOT");
        assertThat(store.getLibVersion("guava-31.1-jre.jar")).isEqualTo("");
        assertThat(store.getLibVersion("netty-buffer-4.1.100.Final.jar")).isEqualTo("4.1.100.Final");
        assertThat(store.getLibVersion("h2-1.4.199.jar")).isEqualTo("1.4.199");
        assertThat(store.getLibVersion("asm-9.8.jar")).isEqualTo("9.8");
    }

    @Test
    public void should_extractLibName_handle_classifier_jars() {
        assertThat(ProjectDependenciesResolver.extractLibName("guava-31.1-jre.jar")).isEqualTo("guava");
        assertThat(ProjectDependenciesResolver.extractLibName("netty-buffer-4.1.100.Final.jar"))
                .isEqualTo("netty-buffer");
    }

    @Test
    public void should_extractLibName_handle_versionless_jars() {
        assertThat(ProjectDependenciesResolver.extractLibName("mylib.jar")).isEqualTo("mylib");
        assertThat(ProjectDependenciesResolver.extractLibName("catalina.jar")).isEqualTo("catalina");
    }

    @Test
    public void should_extractLibName_handle_null_and_non_jar() {
        assertThat(ProjectDependenciesResolver.extractLibName(null)).isNull();
        assertThat(ProjectDependenciesResolver.extractLibName("not-a-jar")).isEqualTo("not-a-jar");
    }

    @Test
    public void should_extractLibName_handle_snapshot_jars() {
        assertThat(ProjectDependenciesResolver.extractLibName("bonita-common-10.2.0-SNAPSHOT.jar"))
                .isEqualTo("bonita-common");
        assertThat(ProjectDependenciesResolver.extractLibName("my-lib-1.0.0-SNAPSHOT.jar"))
                .isEqualTo("my-lib");
    }

    @Test
    public void should_isInRuntimeContainer_return_true_when_jar_in_runtime() {
        Map<String, String> runtime = new HashMap<>();
        runtime.put("slf4j-api", "1.7.36");
        doReturn(runtime).when(store).getRuntimeDependencies();
        assertThat(store.isInRuntimeContainer("slf4j-api-1.7.36.jar")).isTrue();
    }

    @Test
    public void should_isInRuntimeContainer_return_true_when_jar_in_runtime_with_different_version() {
        Map<String, String> runtime = new HashMap<>();
        runtime.put("slf4j-api", "1.7.36");
        doReturn(runtime).when(store).getRuntimeDependencies();
        assertThat(store.isInRuntimeContainer("slf4j-api-2.0.0.jar")).isTrue();
    }

    @Test
    public void should_isInRuntimeContainer_return_false_when_jar_not_in_runtime() {
        Map<String, String> runtime = new HashMap<>();
        runtime.put("slf4j-api", "1.7.36");
        doReturn(runtime).when(store).getRuntimeDependencies();
        assertThat(store.isInRuntimeContainer("guava-31.1-jre.jar")).isFalse();
    }

    @Test
    public void should_isInRuntimeContainer_return_false_for_null() {
        Map<String, String> runtime = new HashMap<>();
        runtime.put("slf4j-api", "1.7.36");
        doReturn(runtime).when(store).getRuntimeDependencies();
        assertThat(store.isInRuntimeContainer(null)).isFalse();
    }

    @Test
    public void should_isInRuntimeContainer_return_false_for_non_jar() {
        Map<String, String> runtime = new HashMap<>();
        runtime.put("slf4j-api", "1.7.36");
        doReturn(runtime).when(store).getRuntimeDependencies();
        assertThat(store.isInRuntimeContainer("slf4j-api")).isFalse();
    }

    @Test
    public void should_isInRuntimeContainer_return_false_when_runtime_empty() {
        doReturn(new HashMap<>()).when(store).getRuntimeDependencies();
        assertThat(store.isInRuntimeContainer("slf4j-api-1.7.36.jar")).isFalse();
    }

}
