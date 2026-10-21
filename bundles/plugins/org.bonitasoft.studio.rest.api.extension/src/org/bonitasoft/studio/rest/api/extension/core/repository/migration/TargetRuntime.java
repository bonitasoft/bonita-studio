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

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;

/**
 * The runtime generation a project targets, as the extension migration steps need to know it.
 * <p>
 * Bonita 12 changed two things at once that the extension poms depend on: the servlet API moved to the jakarta
 * namespace, and Groovy moved to the {@code org.apache.groovy} coordinates of Groovy 4. Both questions are the same
 * question about the target runtime, so they are answered here rather than once per step - two copies would be free to
 * disagree, and a project sent down one branch for the servlet API and the other for Groovy would end up with a pom
 * that is half migrated.
 */
final class TargetRuntime {

    /** The runtime generation that moved to the jakarta namespace and to Groovy 4 at once. */
    private static final int BONITA_12_MAJOR_VERSION = 12;

    private TargetRuntime() {
    }

    /**
     * @param metadata the metadata of the project owning the extension
     * @return whether that project targets Bonita 12 or later
     */
    static boolean targetsBonita12OrLater(ProjectMetadata metadata) {
        var runtimeVersion = metadata.getBonitaRuntimeVersion();
        if (runtimeVersion == null || runtimeVersion.isBlank()) {
            // same fallback as ProjectMetadata when a pom does not declare the property
            runtimeVersion = ProductVersion.BONITA_RUNTIME_VERSION;
        }
        return majorVersionOf(runtimeVersion) >= BONITA_12_MAJOR_VERSION;
    }

    /**
     * {@link DefaultArtifactVersion} silently yields {@code 0} for a version whose first segment is not numeric, such
     * as an unresolved {@code ${bonita.runtime.version}} property reaching us from a raw model. Returning that
     * {@code 0} would send a 12.0 project down the pre-12 branch, leaving the extension on coordinates the runtime BOM
     * does not manage without telling anyone. Qualified versions such as {@code 12.0-SNAPSHOT} or {@code 12.0.0.RC1}
     * parse fine and are not concerned.
     *
     * @return the major version of {@code runtimeVersion}, or the major version of the runtime this Studio ships when
     *         that version cannot be parsed
     */
    private static int majorVersionOf(String runtimeVersion) {
        var majorVersion = new DefaultArtifactVersion(runtimeVersion).getMajorVersion();
        if (majorVersion == 0) {
            BonitaStudioLog.warning(String.format(
                    "Cannot read the major version of the Bonita runtime '%s': falling back on the runtime shipped with this Studio (%s) to decide whether this project targets Bonita 12 or later.",
                    runtimeVersion, ProductVersion.BONITA_RUNTIME_VERSION), TargetRuntime.class);
            return new DefaultArtifactVersion(ProductVersion.BONITA_RUNTIME_VERSION).getMajorVersion();
        }
        return majorVersion;
    }
}
