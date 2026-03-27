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

public class BarExporterFlattenFragmentsTest {

    private BarExporter barExporter;
    private Configuration configuration;

    @Before
    public void setUp() {
        barExporter = BarExporter.getInstance();
        configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
    }

    @Test
    public void should_flatten_fragments_from_children_to_other_container() throws BuildBarException {
        // Given: Configuration with CONNECTOR container having a child with fragments
        FragmentContainer connectorContainer = createFragmentContainer("CONNECTOR");
        FragmentContainer childContainer = createFragmentContainer("scripting-groovy-script-impl-1.1.4");
        Fragment childFragment1 = createFragment("groovy-all-2.4.16.jar", true);
        Fragment childFragment2 = createFragment("commons-lang3-3.9.jar", false);
        childContainer.getFragments().add(childFragment1);
        childContainer.getFragments().add(childFragment2);
        connectorContainer.getChildren().add(childContainer);

        FragmentContainer otherContainer = createFragmentContainer("OTHER");
        configuration.getProcessDependencies().add(connectorContainer);
        configuration.getProcessDependencies().add(otherContainer);

        // When: Flatten fragments
        barExporter.flattenFragmentsToOther(configuration);

        // Then: Fragments are copied to OTHER container with exported flags preserved
        assertThat(otherContainer.getFragments()).hasSize(2);

        Fragment copiedFragment1 = otherContainer.getFragments().stream()
                .filter(f -> "groovy-all-2.4.16.jar".equals(f.getValue()))
                .findFirst()
                .orElse(null);
        assertThat(copiedFragment1).isNotNull();
        assertThat(copiedFragment1.isExported()).isTrue();
        assertThat(copiedFragment1.getKey()).isEqualTo("groovy-all-2.4.16.jar");
        assertThat(copiedFragment1.getType()).isEqualTo(FragmentTypes.JAR);

        Fragment copiedFragment2 = otherContainer.getFragments().stream()
                .filter(f -> "commons-lang3-3.9.jar".equals(f.getValue()))
                .findFirst()
                .orElse(null);
        assertThat(copiedFragment2).isNotNull();
        assertThat(copiedFragment2.isExported()).isFalse();
        assertThat(copiedFragment2.getKey()).isEqualTo("commons-lang3-3.9.jar");
        assertThat(copiedFragment2.getType()).isEqualTo(FragmentTypes.JAR);
    }

    @Test
    public void should_preserve_exported_flag_when_copying_fragment() throws BuildBarException {
        // Given: Configuration with child fragment having exported=false
        FragmentContainer connectorContainer = createFragmentContainer("CONNECTOR");
        FragmentContainer childContainer = createFragmentContainer("test-connector-1.0.0");
        Fragment childFragment = createFragment("test-lib.jar", false);
        childContainer.getFragments().add(childFragment);
        connectorContainer.getChildren().add(childContainer);

        FragmentContainer otherContainer = createFragmentContainer("OTHER");
        configuration.getProcessDependencies().add(connectorContainer);
        configuration.getProcessDependencies().add(otherContainer);

        // When: Flatten fragments
        barExporter.flattenFragmentsToOther(configuration);

        // Then: Exported flag is preserved
        assertThat(otherContainer.getFragments()).hasSize(1);
        Fragment copiedFragment = otherContainer.getFragments().get(0);
        assertThat(copiedFragment.isExported()).isFalse();
    }

    @Test
    public void should_throw_when_other_container_is_missing() {
        // Given: Configuration without OTHER container
        FragmentContainer connectorContainer = createFragmentContainer("CONNECTOR");
        FragmentContainer childContainer = createFragmentContainer("test-connector-1.0.0");
        Fragment childFragment = createFragment("test-lib.jar", true);
        childContainer.getFragments().add(childFragment);
        connectorContainer.getChildren().add(childContainer);
        configuration.getProcessDependencies().add(connectorContainer);

        // When/Then: Flatten fragments should throw BuildBarException
        assertThatThrownBy(() -> barExporter.flattenFragmentsToOther(configuration))
                .isInstanceOf(BuildBarException.class);
    }

    @Test
    public void should_update_existing_fragment_with_metadata_but_preserve_exported_flag() throws BuildBarException {
        // Given: Fragment exists in OTHER with exported=true, child has same fragment with exported=false and metadata
        FragmentContainer otherContainer = createFragmentContainer("OTHER");
        Fragment existingFragment = createFragment("test-lib.jar", true);
        existingFragment.setKey("old-key");
        existingFragment.setType("OLD_TYPE");
        otherContainer.getFragments().add(existingFragment);

        FragmentContainer connectorContainer = createFragmentContainer("CONNECTOR");
        FragmentContainer childContainer = createFragmentContainer("test-connector-1.0.0");
        Fragment childFragment = createFragment("test-lib.jar", false);
        childFragment.setKey("new-connector-key");
        childFragment.setType("CONNECTOR");
        childContainer.getFragments().add(childFragment);
        connectorContainer.getChildren().add(childContainer);

        configuration.getProcessDependencies().add(otherContainer);
        configuration.getProcessDependencies().add(connectorContainer);

        // When: Flatten fragments
        barExporter.flattenFragmentsToOther(configuration);

        // Then: Existing fragment updated with new metadata but exported flag preserved
        assertThat(otherContainer.getFragments()).hasSize(1);
        Fragment updatedFragment = otherContainer.getFragments().get(0);
        assertThat(updatedFragment.isExported()).isTrue(); // Preserved from existing
        assertThat(updatedFragment.getKey()).isEqualTo("new-connector-key"); // Updated from child
        assertThat(updatedFragment.getType()).isEqualTo("CONNECTOR"); // Updated from child
        assertThat(updatedFragment.getValue()).isEqualTo("test-lib.jar");
    }

    @Test
    public void should_normalize_null_type_to_jar() throws BuildBarException {
        // Given: Fragment with type=null in OTHER container
        FragmentContainer otherContainer = createFragmentContainer("OTHER");
        Fragment fragmentWithNullType = ConfigurationFactory.eINSTANCE.createFragment();
        fragmentWithNullType.setValue("test-lib.jar");
        fragmentWithNullType.setKey("test-lib.jar");
        fragmentWithNullType.setExported(true);
        fragmentWithNullType.setType(null); // Explicitly set to null
        otherContainer.getFragments().add(fragmentWithNullType);

        configuration.getProcessDependencies().add(otherContainer);

        // When: Flatten fragments
        barExporter.flattenFragmentsToOther(configuration);

        // Then: Type is set to FragmentTypes.JAR
        assertThat(otherContainer.getFragments()).hasSize(1);
        Fragment normalizedFragment = otherContainer.getFragments().get(0);
        assertThat(normalizedFragment.getType()).isEqualTo(FragmentTypes.JAR);
    }

    @Test
    public void should_flatten_multiple_fragments_from_multiple_children() throws BuildBarException {
        // Given: Complex configuration with multiple containers and children
        FragmentContainer connectorContainer = createFragmentContainer("CONNECTOR");
        FragmentContainer child1 = createFragmentContainer("connector-impl-1");
        Fragment fragment1 = createFragment("lib1.jar", true);
        Fragment fragment2 = createFragment("lib2.jar", false);
        child1.getFragments().add(fragment1);
        child1.getFragments().add(fragment2);

        FragmentContainer child2 = createFragmentContainer("connector-impl-2");
        Fragment fragment3 = createFragment("lib3.jar", true);
        child2.getFragments().add(fragment3);

        connectorContainer.getChildren().add(child1);
        connectorContainer.getChildren().add(child2);

        FragmentContainer actorFilterContainer = createFragmentContainer("ACTOR_FILTER");
        FragmentContainer child3 = createFragmentContainer("filter-impl-1");
        Fragment fragment4 = createFragment("lib4.jar", false);
        child3.getFragments().add(fragment4);
        actorFilterContainer.getChildren().add(child3);

        FragmentContainer otherContainer = createFragmentContainer("OTHER");
        configuration.getProcessDependencies().add(connectorContainer);
        configuration.getProcessDependencies().add(actorFilterContainer);
        configuration.getProcessDependencies().add(otherContainer);

        // When: Flatten fragments
        barExporter.flattenFragmentsToOther(configuration);

        // Then: All fragments are copied to OTHER
        assertThat(otherContainer.getFragments()).hasSize(4);
        assertThat(otherContainer.getFragments())
                .extracting(Fragment::getValue)
                .containsExactlyInAnyOrder("lib1.jar", "lib2.jar", "lib3.jar", "lib4.jar");
    }

    @Test
    public void should_not_create_duplicates_when_same_fragment_in_multiple_children() throws BuildBarException {
        // Given: Same fragment in two different children
        FragmentContainer connectorContainer = createFragmentContainer("CONNECTOR");
        FragmentContainer child1 = createFragmentContainer("connector-impl-1");
        Fragment fragment1 = createFragment("shared-lib.jar", true);
        child1.getFragments().add(fragment1);

        FragmentContainer child2 = createFragmentContainer("connector-impl-2");
        Fragment fragment2 = createFragment("shared-lib.jar", false);
        child2.getFragments().add(fragment2);

        connectorContainer.getChildren().add(child1);
        connectorContainer.getChildren().add(child2);

        FragmentContainer otherContainer = createFragmentContainer("OTHER");
        configuration.getProcessDependencies().add(connectorContainer);
        configuration.getProcessDependencies().add(otherContainer);

        // When: Flatten fragments
        barExporter.flattenFragmentsToOther(configuration);

        // Then: Only one copy in OTHER (first one encountered wins for exported flag)
        assertThat(otherContainer.getFragments()).hasSize(1);
        Fragment copiedFragment = otherContainer.getFragments().get(0);
        assertThat(copiedFragment.getValue()).isEqualTo("shared-lib.jar");
    }

    @Test
    public void should_use_last_child_metadata_when_same_fragment_in_multiple_children() throws BuildBarException {
        // Given: Same fragment value in two children with different metadata
        FragmentContainer connectorContainer = createFragmentContainer("CONNECTOR");
        FragmentContainer child1 = createFragmentContainer("connector-impl-1");
        Fragment fragment1 = createFragment("shared-lib.jar", true);
        fragment1.setKey("key-from-child1");
        fragment1.setType("CONNECTOR");
        child1.getFragments().add(fragment1);

        FragmentContainer child2 = createFragmentContainer("connector-impl-2");
        Fragment fragment2 = createFragment("shared-lib.jar", false);
        fragment2.setKey("key-from-child2");
        fragment2.setType("ACTOR_FILTER");
        child2.getFragments().add(fragment2);

        connectorContainer.getChildren().add(child1);
        connectorContainer.getChildren().add(child2);

        FragmentContainer otherContainer = createFragmentContainer("OTHER");
        configuration.getProcessDependencies().add(connectorContainer);
        configuration.getProcessDependencies().add(otherContainer);

        // When: Flatten fragments
        barExporter.flattenFragmentsToOther(configuration);

        // Then: Last child's metadata wins (child2), exported from first copy (child1)
        assertThat(otherContainer.getFragments()).hasSize(1);
        Fragment result = otherContainer.getFragments().get(0);
        assertThat(result.getValue()).isEqualTo("shared-lib.jar");
        assertThat(result.getKey()).isEqualTo("key-from-child2");
        assertThat(result.getType()).isEqualTo("ACTOR_FILTER");
        assertThat(result.isExported()).isTrue(); // From first copy (child1)
    }

    @Test
    public void should_create_independent_copy_via_copyFragment() throws BuildBarException {
        // Given: Configuration with a child fragment
        FragmentContainer connectorContainer = createFragmentContainer("CONNECTOR");
        FragmentContainer childContainer = createFragmentContainer("connector-impl-1");
        Fragment originalFragment = createFragment("original-lib.jar", true);
        originalFragment.setKey("original-key");
        originalFragment.setType("CONNECTOR");
        childContainer.getFragments().add(originalFragment);
        connectorContainer.getChildren().add(childContainer);

        FragmentContainer otherContainer = createFragmentContainer("OTHER");
        configuration.getProcessDependencies().add(connectorContainer);
        configuration.getProcessDependencies().add(otherContainer);

        // When: Flatten fragments
        barExporter.flattenFragmentsToOther(configuration);

        // Then: Modifying the copy does not affect the original
        Fragment copiedFragment = otherContainer.getFragments().get(0);
        copiedFragment.setKey("modified-key");
        copiedFragment.setExported(false);
        copiedFragment.setValue("modified-lib.jar");

        assertThat(originalFragment.getKey()).isEqualTo("original-key");
        assertThat(originalFragment.isExported()).isTrue();
        assertThat(originalFragment.getValue()).isEqualTo("original-lib.jar");
    }

    @Test
    public void should_handle_large_dataset_efficiently() throws BuildBarException {
        // Given: Configuration with many containers, children, and fragments
        FragmentContainer otherContainer = createFragmentContainer("OTHER");
        configuration.getProcessDependencies().add(otherContainer);

        for (int i = 0; i < 10; i++) {
            FragmentContainer container = createFragmentContainer("CONTAINER_" + i);
            for (int j = 0; j < 10; j++) {
                FragmentContainer child = createFragmentContainer("child-" + i + "-" + j);
                for (int k = 0; k < 10; k++) {
                    Fragment fragment = createFragment("lib-" + i + "-" + j + "-" + k + ".jar", k % 2 == 0);
                    child.getFragments().add(fragment);
                }
                container.getChildren().add(child);
            }
            configuration.getProcessDependencies().add(container);
        }

        // When: Flatten fragments (1000 unique fragments)
        barExporter.flattenFragmentsToOther(configuration);

        // Then: All 1000 fragments are in OTHER
        assertThat(otherContainer.getFragments()).hasSize(1000);
    }

    // Helper methods

    private FragmentContainer createFragmentContainer(String id) {
        FragmentContainer container = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        container.setId(id);
        return container;
    }

    private Fragment createFragment(String value, boolean exported) {
        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setValue(value);
        fragment.setExported(exported);
        fragment.setKey(value);
        fragment.setType(FragmentTypes.JAR);
        return fragment;
    }
}
