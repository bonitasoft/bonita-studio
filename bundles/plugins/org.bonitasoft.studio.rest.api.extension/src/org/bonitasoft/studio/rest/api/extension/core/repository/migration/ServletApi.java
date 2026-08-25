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

import java.util.Objects;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
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

    /** First Bonita runtime major version shipping the jakarta namespace. */
    private static final int JAKARTA_RUNTIME_MAJOR_VERSION = 12;

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
        var runtimeVersion = metadata.getBonitaRuntimeVersion();
        if (runtimeVersion == null || runtimeVersion.isBlank()) {
            // same fallback as ProjectMetadata when a pom does not declare the property
            runtimeVersion = ProductVersion.BONITA_RUNTIME_VERSION;
        }
        return majorVersionOf(runtimeVersion) >= JAKARTA_RUNTIME_MAJOR_VERSION
                ? JAKARTA
                : JAVAX;
    }

    /**
     * {@link DefaultArtifactVersion} silently yields {@code 0} for a version whose first segment is not numeric, such
     * as an unresolved {@code ${bonita.runtime.version}} property reaching us from a raw model. Returning that
     * {@code 0} would send a jakarta project down the javax branch, leaving the extension on coordinates the runtime
     * BOM does not manage without telling anyone. Qualified versions such as {@code 12.0-SNAPSHOT} or
     * {@code 12.0.0.RC1} parse fine and are not concerned.
     *
     * @return the major version of {@code runtimeVersion}, or the major version of the runtime this Studio ships when
     *         that version cannot be parsed
     */
    private static int majorVersionOf(String runtimeVersion) {
        var majorVersion = new DefaultArtifactVersion(runtimeVersion).getMajorVersion();
        if (majorVersion == 0) {
            BonitaStudioLog.warning(String.format(
                    "Cannot read the major version of the Bonita runtime '%s': falling back on the runtime shipped with this Studio (%s) to select the servlet API.",
                    runtimeVersion, ProductVersion.BONITA_RUNTIME_VERSION), ServletApi.class);
            return new DefaultArtifactVersion(ProductVersion.BONITA_RUNTIME_VERSION).getMajorVersion();
        }
        return majorVersion;
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
