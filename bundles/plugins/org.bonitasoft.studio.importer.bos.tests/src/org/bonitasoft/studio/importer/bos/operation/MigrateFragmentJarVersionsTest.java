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
package org.bonitasoft.studio.importer.bos.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.bonitasoft.bpm.model.util.FragmentTypes;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MigrateFragmentJarVersionsTest {

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private DependencyRepositoryStore dependencyStore;

    @Mock
    private ProcessConfigurationRepositoryStore configStore;

    @Mock
    private org.bonitasoft.studio.common.repository.model.IRepository repository;

    private ImportBosArchiveOperation operation;

    @BeforeEach
    void setUp() {
        when(repositoryAccessor.getCurrentRepository()).thenReturn(Optional.of(repository));
        when(repositoryAccessor.getRepositoryStore(DependencyRepositoryStore.class)).thenReturn(dependencyStore);
        when(repositoryAccessor.getRepositoryStore(ProcessConfigurationRepositoryStore.class)).thenReturn(configStore);
        operation = new ImportBosArchiveOperation(repositoryAccessor);
    }

    @Test
    void should_update_fragment_value_when_version_differs() throws Exception {
        // Given: a configuration with a fragment referencing an old jar version
        Configuration config = createConfigWithFragment("bcpkix-jdk18on-1.78.1.jar");
        var configFileStore = mockConfigFileStore(config);
        when(configStore.getChildren()).thenReturn(List.of(configFileStore));

        // The dependency store finds the jar with a different version
        DependencyFileStore depFileStore = mock(DependencyFileStore.class);
        when(depFileStore.getName()).thenReturn("bcpkix-jdk18on-1.79.jar");
        when(dependencyStore.getChild("bcpkix-jdk18on-1.78.1.jar", true)).thenReturn(depFileStore);

        // When
        operation.migrateFragmentJarVersions(new NullProgressMonitor());

        // Then: fragment value should be updated
        Fragment fragment = config.getProcessDependencies().get(0).getFragments().get(0);
        assertThat(fragment.getValue()).isEqualTo("bcpkix-jdk18on-1.79.jar");
        verify(configFileStore).save(config);
    }

    @Test
    void should_not_modify_fragment_when_exact_match() throws Exception {
        // Given: a configuration with a fragment matching exactly
        Configuration config = createConfigWithFragment("bcpkix-jdk18on-1.79.jar");
        var configFileStore = mockConfigFileStore(config);
        when(configStore.getChildren()).thenReturn(List.of(configFileStore));

        DependencyFileStore depFileStore = mock(DependencyFileStore.class);
        when(depFileStore.getName()).thenReturn("bcpkix-jdk18on-1.79.jar");
        when(dependencyStore.getChild("bcpkix-jdk18on-1.79.jar", true)).thenReturn(depFileStore);

        // When
        operation.migrateFragmentJarVersions(new NullProgressMonitor());

        // Then: fragment value should NOT be changed, no save
        Fragment fragment = config.getProcessDependencies().get(0).getFragments().get(0);
        assertThat(fragment.getValue()).isEqualTo("bcpkix-jdk18on-1.79.jar");
        verify(configFileStore, never()).save(any());
    }

    @Test
    void should_not_modify_fragment_when_dependency_not_found() throws Exception {
        // Given: a configuration with a fragment for which no dependency exists
        Configuration config = createConfigWithFragment("unknown-lib-1.0.jar");
        var configFileStore = mockConfigFileStore(config);
        when(configStore.getChildren()).thenReturn(List.of(configFileStore));

        when(dependencyStore.getChild("unknown-lib-1.0.jar", true)).thenReturn(null);

        // When
        operation.migrateFragmentJarVersions(new NullProgressMonitor());

        // Then: fragment value should NOT be changed, no save
        Fragment fragment = config.getProcessDependencies().get(0).getFragments().get(0);
        assertThat(fragment.getValue()).isEqualTo("unknown-lib-1.0.jar");
        verify(configFileStore, never()).save(any());
    }

    @Test
    void should_not_modify_non_jar_fragment() throws Exception {
        // Given: a configuration with a non-jar fragment
        Configuration config = createConfigWithFragment("some-groovy-script.groovy");
        var configFileStore = mockConfigFileStore(config);
        when(configStore.getChildren()).thenReturn(List.of(configFileStore));

        // When
        operation.migrateFragmentJarVersions(new NullProgressMonitor());

        // Then: fragment value should NOT be changed, no save
        Fragment fragment = config.getProcessDependencies().get(0).getFragments().get(0);
        assertThat(fragment.getValue()).isEqualTo("some-groovy-script.groovy");
        verify(configFileStore, never()).save(any());
    }

    @Test
    void should_walk_child_containers() throws Exception {
        // Given: a configuration with nested fragment containers
        Configuration config = ConfigurationFactory.eINSTANCE.createConfiguration();
        FragmentContainer connectorContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        connectorContainer.setId(FragmentTypes.CONNECTOR);
        config.getProcessDependencies().add(connectorContainer);

        // Child container (e.g., specific connector implementation)
        FragmentContainer childContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        childContainer.setId("mistral-ask-impl");
        connectorContainer.getChildren().add(childContainer);

        // Fragment in child container
        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setValue("apache-mime4j-core-0.8.11.jar");
        childContainer.getFragments().add(fragment);

        var configFileStore = mockConfigFileStore(config);
        when(configStore.getChildren()).thenReturn(List.of(configFileStore));

        DependencyFileStore depFileStore = mock(DependencyFileStore.class);
        when(depFileStore.getName()).thenReturn("apache-mime4j-core-0.8.13.jar");
        when(dependencyStore.getChild("apache-mime4j-core-0.8.11.jar", true)).thenReturn(depFileStore);

        // When
        operation.migrateFragmentJarVersions(new NullProgressMonitor());

        // Then: nested fragment should be updated
        assertThat(fragment.getValue()).isEqualTo("apache-mime4j-core-0.8.13.jar");
        verify(configFileStore).save(config);
    }

    @Test
    void should_update_multiple_fragments_in_same_config() throws Exception {
        // Given: a configuration with multiple fragments having version mismatches
        Configuration config = ConfigurationFactory.eINSTANCE.createConfiguration();
        FragmentContainer otherContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        otherContainer.setId(FragmentTypes.OTHER);
        config.getProcessDependencies().add(otherContainer);

        Fragment fragment1 = ConfigurationFactory.eINSTANCE.createFragment();
        fragment1.setValue("bcpkix-jdk18on-1.78.1.jar");
        otherContainer.getFragments().add(fragment1);

        Fragment fragment2 = ConfigurationFactory.eINSTANCE.createFragment();
        fragment2.setValue("apache-mime4j-core-0.8.11.jar");
        otherContainer.getFragments().add(fragment2);

        // Fragment that matches exactly - should not be changed
        Fragment fragment3 = ConfigurationFactory.eINSTANCE.createFragment();
        fragment3.setValue("guava-33.0.jar");
        otherContainer.getFragments().add(fragment3);

        var configFileStore = mockConfigFileStore(config);
        when(configStore.getChildren()).thenReturn(List.of(configFileStore));

        DependencyFileStore dep1 = mock(DependencyFileStore.class);
        when(dep1.getName()).thenReturn("bcpkix-jdk18on-1.79.jar");
        when(dependencyStore.getChild("bcpkix-jdk18on-1.78.1.jar", true)).thenReturn(dep1);

        DependencyFileStore dep2 = mock(DependencyFileStore.class);
        when(dep2.getName()).thenReturn("apache-mime4j-core-0.8.13.jar");
        when(dependencyStore.getChild("apache-mime4j-core-0.8.11.jar", true)).thenReturn(dep2);

        DependencyFileStore dep3 = mock(DependencyFileStore.class);
        when(dep3.getName()).thenReturn("guava-33.0.jar");
        when(dependencyStore.getChild("guava-33.0.jar", true)).thenReturn(dep3);

        // When
        operation.migrateFragmentJarVersions(new NullProgressMonitor());

        // Then
        assertThat(fragment1.getValue()).isEqualTo("bcpkix-jdk18on-1.79.jar");
        assertThat(fragment2.getValue()).isEqualTo("apache-mime4j-core-0.8.13.jar");
        assertThat(fragment3.getValue()).isEqualTo("guava-33.0.jar"); // unchanged
        verify(configFileStore).save(config);
    }

    @Test
    void should_walk_grandchild_containers() throws Exception {
        // Given: a configuration with 3 levels of nesting
        Configuration config = ConfigurationFactory.eINSTANCE.createConfiguration();
        FragmentContainer topContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        topContainer.setId(FragmentTypes.CONNECTOR);
        config.getProcessDependencies().add(topContainer);

        FragmentContainer childContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        childContainer.setId("connector-impl");
        topContainer.getChildren().add(childContainer);

        FragmentContainer grandchildContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        grandchildContainer.setId("nested-dep");
        childContainer.getChildren().add(grandchildContainer);

        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setValue("deep-lib-1.0.jar");
        grandchildContainer.getFragments().add(fragment);

        var configFileStore = mockConfigFileStore(config);
        when(configStore.getChildren()).thenReturn(List.of(configFileStore));

        DependencyFileStore depFileStore = mock(DependencyFileStore.class);
        when(depFileStore.getName()).thenReturn("deep-lib-2.0.jar");
        when(dependencyStore.getChild("deep-lib-1.0.jar", true)).thenReturn(depFileStore);

        // When
        operation.migrateFragmentJarVersions(new NullProgressMonitor());

        // Then: deeply nested fragment should be updated
        assertThat(fragment.getValue()).isEqualTo("deep-lib-2.0.jar");
        verify(configFileStore).save(config);
    }

    // ==================== Helper methods ====================

    private Configuration createConfigWithFragment(String jarValue) {
        Configuration config = ConfigurationFactory.eINSTANCE.createConfiguration();
        FragmentContainer container = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        container.setId(FragmentTypes.OTHER);
        config.getProcessDependencies().add(container);

        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setValue(jarValue);
        container.getFragments().add(fragment);

        return config;
    }

    @SuppressWarnings("unchecked")
    private ProcessConfigurationFileStore mockConfigFileStore(Configuration config) throws Exception {
        ProcessConfigurationFileStore fileStore = mock(ProcessConfigurationFileStore.class);
        when(fileStore.getContent()).thenReturn(config);
        return fileStore;
    }
}
