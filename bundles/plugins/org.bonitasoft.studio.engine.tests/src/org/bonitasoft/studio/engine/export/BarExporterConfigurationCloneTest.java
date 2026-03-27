/**
 * Copyright (C) 2026 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.export;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.bonitasoft.bpm.model.util.FragmentTypes;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link BarExporter#cloneConfiguration(Configuration)} method.
 * <p>
 * These tests verify that the configuration cloning mechanism works correctly
 * and that the original configuration is not modified during BAR export.
 * </p>
 */
public class BarExporterConfigurationCloneTest {

    private BarExporter barExporter;

    @Before
    public void setUp() {
        barExporter = BarExporter.getInstance();
    }

    @Test
    public void should_throw_exception_when_configuration_is_null() {
        // When/Then
        assertThatThrownBy(() -> barExporter.cloneConfiguration(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Configuration cannot be null");
    }

    @Test
    public void should_create_independent_clone() throws BuildBarException {
        // Given
        Configuration original = createBasicConfiguration();

        // When
        Configuration clone = barExporter.cloneConfiguration(original);

        // Then
        assertThat(clone).isNotSameAs(original);
        assertThat(clone).isNotNull();
    }

    @Test
    public void should_clone_all_configuration_properties() throws BuildBarException {
        // Given
        Configuration original = createBasicConfiguration();
        original.setName("Test Configuration");
        original.setVersion("1.0.0");
        original.setUsername("testUser");
        original.setPassword("testPassword");

        // When
        Configuration clone = barExporter.cloneConfiguration(original);

        // Then
        assertThat(clone.getName()).isEqualTo(original.getName());
        assertThat(clone.getVersion()).isEqualTo(original.getVersion());
        assertThat(clone.getUsername()).isEqualTo(original.getUsername());
        assertThat(clone.getPassword()).isEqualTo(original.getPassword());
    }

    @Test
    public void should_clone_fragment_containers() throws BuildBarException {
        // Given
        Configuration original = createConfigurationWithFragmentContainers();

        // When
        Configuration clone = barExporter.cloneConfiguration(original);

        // Then
        assertThat(clone.getProcessDependencies())
                .hasSameSizeAs(original.getProcessDependencies())
                .allMatch(container -> container != null)
                .extracting(FragmentContainer::getId)
                .containsExactlyInAnyOrder(FragmentTypes.CONNECTOR, FragmentTypes.OTHER);
    }

    @Test
    public void should_clone_fragments_within_containers() throws BuildBarException {
        // Given
        Configuration original = createConfigurationWithFragments();
        FragmentContainer originalConnectorContainer = original.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.CONNECTOR.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Connector container not found"));

        // When
        Configuration clone = barExporter.cloneConfiguration(original);

        // Then
        FragmentContainer clonedConnectorContainer = clone.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.CONNECTOR.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Connector container not found"));

        assertThat(clonedConnectorContainer).isNotSameAs(originalConnectorContainer);
        assertThat(clonedConnectorContainer.getFragments())
                .hasSameSizeAs(originalConnectorContainer.getFragments())
                .extracting(Fragment::getValue)
                .containsExactlyInAnyOrder("test-connector-1.0.0.jar", "test-dependency-1.0.0.jar");
    }

    @Test
    public void should_preserve_fragment_properties() throws BuildBarException {
        // Given
        Configuration original = createConfigurationWithFragments();
        FragmentContainer originalContainer = original.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.CONNECTOR.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Connector container not found"));
        Fragment originalFragment = originalContainer.getFragments().get(0);

        // When
        Configuration clone = barExporter.cloneConfiguration(original);

        // Then
        FragmentContainer clonedContainer = clone.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.CONNECTOR.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Connector container not found"));
        Fragment clonedFragment = clonedContainer.getFragments().get(0);

        assertThat(clonedFragment).isNotSameAs(originalFragment);
        assertThat(clonedFragment.isExported()).isEqualTo(originalFragment.isExported());
        assertThat(clonedFragment.getKey()).isEqualTo(originalFragment.getKey());
        assertThat(clonedFragment.getValue()).isEqualTo(originalFragment.getValue());
        assertThat(clonedFragment.getType()).isEqualTo(originalFragment.getType());
    }

    @Test
    public void should_not_affect_original_when_modifying_clone() throws BuildBarException {
        // Given
        Configuration original = createConfigurationWithFragments();
        String originalName = original.getName();

        // When
        Configuration clone = barExporter.cloneConfiguration(original);
        clone.setName("Modified Name");
        clone.setUsername("Modified User");

        // Then: Original should be unchanged
        assertThat(original.getName()).isEqualTo(originalName);
        assertThat(original.getUsername()).isNotEqualTo("Modified User");
        assertThat(clone.getName()).isEqualTo("Modified Name");
    }

    @Test
    public void should_not_affect_original_fragments_when_modifying_clone() throws BuildBarException {
        // Given
        Configuration original = createConfigurationWithFragments();
        FragmentContainer originalContainer = original.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.CONNECTOR.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Connector container not found"));
        int originalFragmentCount = originalContainer.getFragments().size();

        // When
        Configuration clone = barExporter.cloneConfiguration(original);
        FragmentContainer clonedContainer = clone.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.CONNECTOR.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Connector container not found"));

        // Add a new fragment to the clone
        Fragment newFragment = ConfigurationFactory.eINSTANCE.createFragment();
        newFragment.setValue("new-jar.jar");
        newFragment.setExported(true);
        clonedContainer.getFragments().add(newFragment);

        // Then: Original should be unchanged
        assertThat(originalContainer.getFragments()).hasSize(originalFragmentCount);
        assertThat(clonedContainer.getFragments()).hasSize(originalFragmentCount + 1);
    }

    @Test
    public void should_clone_child_containers() throws BuildBarException {
        // Given
        Configuration original = createConfigurationWithChildContainers();
        FragmentContainer originalConnectorContainer = original.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.CONNECTOR.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Connector container not found"));
        int originalChildCount = originalConnectorContainer.getChildren().size();

        // When
        Configuration clone = barExporter.cloneConfiguration(original);

        // Then
        FragmentContainer clonedConnectorContainer = clone.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.CONNECTOR.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Connector container not found"));

        assertThat(clonedConnectorContainer).isNotSameAs(originalConnectorContainer);
        assertThat(clonedConnectorContainer.getChildren())
                .hasSize(originalChildCount)
                .allMatch(child -> child != null)
                .extracting(FragmentContainer::getId)
                .containsExactly("scripting-groovy-script-impl-1.1.4");
    }

    @Test
    public void should_handle_empty_configuration() throws BuildBarException {
        // Given
        Configuration original = ConfigurationFactory.eINSTANCE.createConfiguration();

        // When
        Configuration clone = barExporter.cloneConfiguration(original);

        // Then
        assertThat(clone).isNotNull();
        assertThat(clone).isNotSameAs(original);
        assertThat(clone.getProcessDependencies()).isEmpty();
        assertThat(clone.getDefinitionMappings()).isEmpty();
    }

    @Test
    public void should_handle_large_configuration() throws BuildBarException {
        // Given: Configuration with many fragments
        Configuration original = createConfigurationWithManyFragments(100);

        // When
        Configuration clone = barExporter.cloneConfiguration(original);

        // Then
        assertThat(clone).isNotSameAs(original);

        FragmentContainer originalOtherContainer = original.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.OTHER.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Other container not found"));
        FragmentContainer clonedOtherContainer = clone.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.OTHER.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Other container not found"));

        assertThat(clonedOtherContainer.getFragments())
                .hasSameSizeAs(originalOtherContainer.getFragments());
    }

    // ==================== Helper methods ====================

    private Configuration createBasicConfiguration() {
        Configuration config = ConfigurationFactory.eINSTANCE.createConfiguration();
        config.setName("Test Configuration");
        return config;
    }

    private Configuration createConfigurationWithFragmentContainers() {
        Configuration config = createBasicConfiguration();

        // Add CONNECTOR container
        FragmentContainer connectorContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        connectorContainer.setId(FragmentTypes.CONNECTOR);
        config.getProcessDependencies().add(connectorContainer);

        // Add OTHER container
        FragmentContainer otherContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        otherContainer.setId(FragmentTypes.OTHER);
        config.getProcessDependencies().add(otherContainer);

        return config;
    }

    private Configuration createConfigurationWithFragments() {
        Configuration config = createConfigurationWithFragmentContainers();

        // Add fragments to CONNECTOR container
        FragmentContainer connectorContainer = config.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.CONNECTOR.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Connector container not found"));

        Fragment fragment1 = ConfigurationFactory.eINSTANCE.createFragment();
        fragment1.setValue("test-connector-1.0.0.jar");
        fragment1.setKey("test-connector");
        fragment1.setType(FragmentTypes.CONNECTOR);
        fragment1.setExported(true);
        connectorContainer.getFragments().add(fragment1);

        Fragment fragment2 = ConfigurationFactory.eINSTANCE.createFragment();
        fragment2.setValue("test-dependency-1.0.0.jar");
        fragment2.setKey("test-dependency");
        fragment2.setType(FragmentTypes.CONNECTOR);
        fragment2.setExported(false);
        connectorContainer.getFragments().add(fragment2);

        return config;
    }

    private Configuration createConfigurationWithChildContainers() {
        Configuration config = createConfigurationWithFragmentContainers();

        // Add child container to CONNECTOR container
        FragmentContainer connectorContainer = config.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.CONNECTOR.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Connector container not found"));

        FragmentContainer childContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        childContainer.setId("scripting-groovy-script-impl-1.1.4");
        connectorContainer.getChildren().add(childContainer);

        // Add fragments to child container
        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setValue("bonita-connector-groovy-1.1.4.jar");
        fragment.setKey("scripting-groovy-script-impl -- 1.1.4");
        fragment.setType(FragmentTypes.CONNECTOR);
        fragment.setExported(true);
        childContainer.getFragments().add(fragment);

        return config;
    }

    private Configuration createConfigurationWithManyFragments(int count) {
        Configuration config = createConfigurationWithFragmentContainers();

        FragmentContainer otherContainer = config.getProcessDependencies().stream()
                .filter(c -> FragmentTypes.OTHER.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Other container not found"));

        for (int i = 0; i < count; i++) {
            Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
            fragment.setValue("test-jar-" + i + ".jar");
            fragment.setKey("test-jar-" + i);
            fragment.setType(FragmentTypes.JAR);
            fragment.setExported(i % 2 == 0); // Alternate exported flag
            otherContainer.getFragments().add(fragment);
        }

        return config;
    }
}