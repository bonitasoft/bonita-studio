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
package org.bonitasoft.studio.rest.api.extension.core.repository.migration;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.junit.Test;

public class ServletApiTest {

    @Test
    public void should_provide_the_jakarta_servlet_api_from_the_12_0_runtime() throws Exception {
        assertThat(ServletApi.forRuntime(metadataForRuntime("12.0.0"))).isEqualTo(ServletApi.JAKARTA);
    }

    @Test
    public void should_provide_the_jakarta_servlet_api_for_a_qualified_12_0_runtime() throws Exception {
        // DefaultArtifactVersion orders 12.0-SNAPSHOT below 12.0, hence the comparison on the major version only
        assertThat(ServletApi.forRuntime(metadataForRuntime("12.0-SNAPSHOT"))).isEqualTo(ServletApi.JAKARTA);
    }

    @Test
    public void should_provide_the_javax_servlet_api_for_a_pre_jakarta_runtime() throws Exception {
        assertThat(ServletApi.forRuntime(metadataForRuntime("10.2.0"))).isEqualTo(ServletApi.JAVAX);
    }

    @Test
    public void should_fall_back_on_the_runtime_of_this_studio_when_the_project_declares_no_runtime() throws Exception {
        assertThat(ServletApi.forRuntime(metadataForRuntime(null))).isEqualTo(shippedServletApi());
        assertThat(ServletApi.forRuntime(metadataForRuntime(" "))).isEqualTo(shippedServletApi());
    }

    @Test
    public void should_fall_back_on_the_runtime_of_this_studio_when_the_runtime_version_cannot_be_read()
            throws Exception {
        // an unresolved property reaches us whenever the metadata is read from a raw model rather than a resolved one:
        // DefaultArtifactVersion yields major 0 for it, which must not silently select the javax servlet API
        assertThat(ServletApi.forRuntime(metadataForRuntime("${bonita.runtime.version}")))
                .isEqualTo(shippedServletApi());
    }

    /** @return the servlet API of the runtime this Studio ships, i.e. the documented fallback */
    private ServletApi shippedServletApi() {
        var metadata = ProjectMetadata.defaultMetadata();
        return ServletApi.forRuntime(metadata);
    }

    private ProjectMetadata metadataForRuntime(String bonitaRuntimeVersion) {
        var metadata = ProjectMetadata.defaultMetadata();
        metadata.setBonitaRuntimeVersion(bonitaRuntimeVersion);
        return metadata;
    }

}
