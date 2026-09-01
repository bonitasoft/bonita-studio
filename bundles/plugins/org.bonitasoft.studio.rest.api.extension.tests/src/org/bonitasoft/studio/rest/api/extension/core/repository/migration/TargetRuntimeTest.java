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

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.junit.Test;

/**
 * The gate both extension migration steps read. Tested directly rather than only through them: it was
 * extracted so the servlet and the Groovy migration could not disagree about which runtime a project
 * targets, which is worth exactly as much as the tests holding it in place.
 */
public class TargetRuntimeTest {

    @Test
    public void should_target_bonita_12_for_a_12_0_runtime() {
        assertThat(TargetRuntime.targetsBonita12OrLater(metadataForRuntime("12.0.0"))).isTrue();
    }

    @Test
    public void should_target_bonita_12_for_a_qualified_12_0_runtime() {
        // the version the poms actually carry between releases
        assertThat(TargetRuntime.targetsBonita12OrLater(metadataForRuntime("12.0-SNAPSHOT"))).isTrue();
    }

    @Test
    public void should_target_bonita_12_for_a_later_minor() {
        // the gate is "12 or later", not "exactly 12": a == would pass every other case in this class
        assertThat(TargetRuntime.targetsBonita12OrLater(metadataForRuntime("12.1.0"))).isTrue();
    }

    @Test
    public void should_target_bonita_12_for_a_later_major() {
        assertThat(TargetRuntime.targetsBonita12OrLater(metadataForRuntime("13.0.0"))).isTrue();
    }

    @Test
    public void should_not_target_bonita_12_for_the_last_pre_12_runtime() {
        assertThat(TargetRuntime.targetsBonita12OrLater(metadataForRuntime("11.9.9"))).isFalse();
    }

    @Test
    public void should_fall_back_on_the_runtime_of_this_studio_when_the_project_declares_none() {
        assertThat(TargetRuntime.targetsBonita12OrLater(metadataForRuntime(null)))
                .isEqualTo(shippedRuntimeIsBonita12OrLater());
    }

    @Test
    public void should_fall_back_on_the_runtime_of_this_studio_for_a_blank_version() {
        assertThat(TargetRuntime.targetsBonita12OrLater(metadataForRuntime("   ")))
                .isEqualTo(shippedRuntimeIsBonita12OrLater());
    }

    @Test
    public void should_fall_back_on_the_runtime_of_this_studio_for_an_unresolved_property() {
        // a raw model read from disk hands us the property text: DefaultArtifactVersion silently makes
        // that major version 0, which would send a 12.0 project down the pre-12 branch and leave the
        // extension on coordinates the runtime BOM does not manage, reporting nothing
        assertThat(TargetRuntime.targetsBonita12OrLater(metadataForRuntime("${bonita.runtime.version}")))
                .isEqualTo(shippedRuntimeIsBonita12OrLater());
    }

    /**
     * Asserted against the Studio's own runtime rather than a literal: the fallback is defined as "the
     * runtime this Studio ships", so hardcoding true here would silently stop testing the fallback the
     * day that version moves.
     */
    private boolean shippedRuntimeIsBonita12OrLater() {
        return TargetRuntime.targetsBonita12OrLater(metadataForRuntime(ProductVersion.BONITA_RUNTIME_VERSION));
    }

    private ProjectMetadata metadataForRuntime(String bonitaRuntimeVersion) {
        var metadata = ProjectMetadata.defaultMetadata();
        metadata.setBonitaRuntimeVersion(bonitaRuntimeVersion);
        return metadata;
    }
}
