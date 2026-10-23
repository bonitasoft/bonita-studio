/*******************************************************************************
 * Copyright (C) 2026 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.junit.Test;

/**
 * A null bonitaVersion reaches archetype:generate as a valueless property, which Maven reads as "true": the
 * connector archetype 2.0.0 rejects it, and older archetypes wrote it into bonita-runtime.version.
 */
public class ExtensionArchetypeConfigurationTest {

    private static final String BONITA_VERSION_PROPERTY = "bonitaVersion";

    @Test
    public void should_pass_the_runtime_version_to_the_connector_archetype() throws Exception {
        var configuration = ConnectorArchetypeConfiguration
                .defaultArchetypeConfiguration(ProjectMetadata.defaultMetadata());

        assertThat(configuration.toProperties()).containsEntry(BONITA_VERSION_PROPERTY,
                ProductVersion.BONITA_RUNTIME_VERSION);
    }

    @Test
    public void should_pass_the_runtime_version_to_the_actor_filter_archetype() throws Exception {
        var configuration = ActorFilterArchetypeConfiguration
                .defaultArchetypeConfiguration(ProjectMetadata.defaultMetadata());

        assertThat(configuration.toProperties()).containsEntry(BONITA_VERSION_PROPERTY,
                ProductVersion.BONITA_RUNTIME_VERSION);
    }

}
