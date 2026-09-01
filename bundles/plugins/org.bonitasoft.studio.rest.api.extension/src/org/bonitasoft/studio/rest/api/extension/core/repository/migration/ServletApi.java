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

import java.util.Objects;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;

/**
 * The servlet API a REST API extension must be built against, which depends on the runtime it targets.
 * <p>
 * The Bonita runtime moved to the jakarta namespace in 12.0: from that version on, the runtime BOM manages
 * {@code jakarta.servlet:jakarta.servlet-api} and no longer manages {@code javax.servlet:javax.servlet-api}. An
 * extension keeping the javax coordinates against a 12.0 runtime therefore does not even resolve, since Studio always
 * declares the servlet API without a version and relies on the BOM.
 */
enum ServletApi {

    JAVAX("javax.servlet", "javax.servlet-api"),

    JAKARTA("jakarta.servlet", "jakarta.servlet-api");

    private final String groupId;
    private final String artifactId;

    private ServletApi(String groupId, String artifactId) {
        this.groupId = groupId;
        this.artifactId = artifactId;
    }

    /**
     * @param metadata the metadata of the project owning the extension
     * @return the servlet API provided by the runtime this project targets
     */
    static ServletApi forRuntime(ProjectMetadata metadata) {
        return TargetRuntime.targetsBonita12OrLater(metadata) ? JAKARTA : JAVAX;
    }

    String groupId() {
        return groupId;
    }

    String artifactId() {
        return artifactId;
    }

    /** @return the {@code groupId:artifactId} form, as displayed in the migration report */
    String ga() {
        return groupId + ":" + artifactId;
    }

    boolean matches(Dependency dependency) {
        return Objects.equals(groupId, dependency.getGroupId())
                && Objects.equals(artifactId, dependency.getArtifactId());
    }

}
