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
package org.bonitasoft.studio.dependencies.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.junit.Before;
import org.junit.Test;

public class MissingDependenciesDecoratorTest {

    private DependencyRepositoryStore store;
    private MissingDependenciesDecorator decorator;

    @Before
    public void setUp() {
        store = mock(DependencyRepositoryStore.class);
        decorator = new MissingDependenciesDecorator(store);
    }

    // --- isInRuntimeContainer ---

    @Test
    public void should_isInRuntimeContainer_return_true_when_same_version_in_runtime() {
        Map<String, String> runtime = new HashMap<>();
        runtime.put("slf4j-api", "1.7.36");
        when(store.getRuntimeDependencies()).thenReturn(runtime);
        when(store.getLibName("slf4j-api-1.7.36.jar")).thenReturn("slf4j-api");
        when(store.getLibVersion("slf4j-api-1.7.36.jar")).thenReturn("1.7.36");

        assertThat(decorator.isInRuntimeContainer(mock(DependencyFileStore.class), "slf4j-api-1.7.36.jar")).isTrue();
    }

    @Test
    public void should_isInRuntimeContainer_return_false_when_different_version_in_runtime() {
        Map<String, String> runtime = new HashMap<>();
        runtime.put("slf4j-api", "1.7.36");
        when(store.getRuntimeDependencies()).thenReturn(runtime);
        when(store.getLibName("slf4j-api-2.0.0.jar")).thenReturn("slf4j-api");
        when(store.getLibVersion("slf4j-api-2.0.0.jar")).thenReturn("2.0.0");

        assertThat(decorator.isInRuntimeContainer(mock(DependencyFileStore.class), "slf4j-api-2.0.0.jar")).isFalse();
    }

    @Test
    public void should_isInRuntimeContainer_return_false_when_not_in_runtime() {
        Map<String, String> runtime = new HashMap<>();
        when(store.getRuntimeDependencies()).thenReturn(runtime);
        when(store.getLibName("guava-31.1-jre.jar")).thenReturn("guava");
        when(store.getLibVersion("guava-31.1-jre.jar")).thenReturn("31.1-jre");

        assertThat(decorator.isInRuntimeContainer(mock(DependencyFileStore.class), "guava-31.1-jre.jar")).isFalse();
    }

    // --- isInRuntimeContainerWithAnotherVersion ---

    @Test
    public void should_isInRuntimeContainerWithAnotherVersion_return_true_when_different_version() {
        Map<String, String> runtime = new HashMap<>();
        runtime.put("slf4j-api", "1.7.36");
        when(store.getRuntimeDependencies()).thenReturn(runtime);
        when(store.getLibName("slf4j-api-2.0.0.jar")).thenReturn("slf4j-api");
        when(store.getLibVersion("slf4j-api-2.0.0.jar")).thenReturn("2.0.0");

        assertThat(decorator.isInRuntimeContainerWithAnotherVersion(mock(DependencyFileStore.class), "slf4j-api-2.0.0.jar")).isTrue();
    }

    @Test
    public void should_isInRuntimeContainerWithAnotherVersion_return_false_when_same_version() {
        Map<String, String> runtime = new HashMap<>();
        runtime.put("slf4j-api", "1.7.36");
        when(store.getRuntimeDependencies()).thenReturn(runtime);
        when(store.getLibName("slf4j-api-1.7.36.jar")).thenReturn("slf4j-api");
        when(store.getLibVersion("slf4j-api-1.7.36.jar")).thenReturn("1.7.36");

        assertThat(decorator.isInRuntimeContainerWithAnotherVersion(mock(DependencyFileStore.class), "slf4j-api-1.7.36.jar")).isFalse();
    }

    @Test
    public void should_isInRuntimeContainerWithAnotherVersion_return_false_when_not_in_runtime() {
        Map<String, String> runtime = new HashMap<>();
        when(store.getRuntimeDependencies()).thenReturn(runtime);
        when(store.getLibName("guava-31.1-jre.jar")).thenReturn("guava");
        when(store.getLibVersion("guava-31.1-jre.jar")).thenReturn("31.1-jre");

        assertThat(decorator.isInRuntimeContainerWithAnotherVersion(mock(DependencyFileStore.class), "guava-31.1-jre.jar")).isFalse();
    }

    // --- isDependencyMissing ---

    @Test
    public void should_isDependencyMissing_return_false_when_dependency_found() {
        DependencyFileStore fileStore = mock(DependencyFileStore.class);

        Fragment fragment = createFragment("slf4j-api-1.7.36.jar");
        assertThat(decorator.isDependencyMissing(fileStore, "slf4j-api-1.7.36.jar", fragment)).isFalse();
    }

    @Test
    public void should_isDependencyMissing_return_true_when_dependency_not_found() {
        Fragment fragment = createFragment("missing-lib-1.0.jar");
        assertThat(decorator.isDependencyMissing(null, "missing-lib-1.0.jar", fragment)).isTrue();
    }

    @Test
    public void should_isDependencyMissing_return_false_for_generated_jar() {
        FragmentContainer container = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        container.setId("my-connector");
        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setValue("my-connector.jar");
        container.getFragments().add(fragment);

        assertThat(decorator.isDependencyMissing(null, "my-connector.jar", fragment)).isFalse();
    }

    // --- isGeneratedJar ---

    @Test
    public void should_isGeneratedJar_return_true_when_jar_matches_container_id() {
        FragmentContainer container = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        container.setId("scripting-groovy-script-impl-1.1.4");
        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setValue("scripting-groovy-script-impl-1.1.4.jar");
        container.getFragments().add(fragment);

        assertThat(decorator.isGeneratedJar("scripting-groovy-script-impl-1.1.4.jar", fragment)).isTrue();
    }

    @Test
    public void should_isGeneratedJar_return_false_when_jar_does_not_match_container_id() {
        FragmentContainer container = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        container.setId("some-connector");
        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setValue("other-lib-1.0.jar");
        container.getFragments().add(fragment);

        assertThat(decorator.isGeneratedJar("other-lib-1.0.jar", fragment)).isFalse();
    }

    @Test
    public void should_isGeneratedJar_return_false_when_fragment_has_no_container() {
        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setValue("some-lib.jar");

        assertThat(decorator.isGeneratedJar("some-lib.jar", fragment)).isFalse();
    }

    // --- decorateText ---

    @Test
    public void should_decorateText_return_null_for_non_fragment() {
        assertThat(decorator.decorateText("text", "not a fragment")).isNull();
    }

    @Test
    public void should_decorateText_return_text_when_dependency_exists_and_not_in_runtime() {
        DependencyFileStore fileStore = mock(DependencyFileStore.class);
        when(store.getChild("guava-31.1-jre.jar", true)).thenReturn(fileStore);
        when(store.getRuntimeDependencies()).thenReturn(new HashMap<>());
        when(store.getLibName("guava-31.1-jre.jar")).thenReturn("guava");
        when(store.getLibVersion("guava-31.1-jre.jar")).thenReturn("31.1-jre");

        Fragment fragment = createFragment("guava-31.1-jre.jar");
        assertThat(decorator.decorateText("guava-31.1-jre.jar", fragment)).isEqualTo("guava-31.1-jre.jar");
    }

    @Test
    public void should_decorateText_return_null_for_non_jar_fragment() {
        Fragment fragment = createFragment("some-value-without-jar-ext");
        assertThat(decorator.decorateText("some-value", fragment)).isNull();
    }

    private Fragment createFragment(String value) {
        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setValue(value);
        return fragment;
    }
}
