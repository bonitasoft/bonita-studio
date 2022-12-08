/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
import java.util.Properties;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;

public class BonitaVersionMigrationStep implements MavenModelMigration {

    private static final String BONITA_VERSION_PROPERTY = "bonita.version";
    private static final String BONITA_RUNTIME_VERSION_PROPERTY = "bonita-runtime.version";

    @Override
    public MigrationReport migrate(Model model, ProjectMetadata metadata) {
        var report = new MigrationReport();
        var properties = model.getProperties();
        updateProperty(BONITA_VERSION_PROPERTY, report, properties);
        updateProperty(BONITA_RUNTIME_VERSION_PROPERTY, report, properties);
        return report;
    }

    private void updateProperty(String property, MigrationReport report, Properties properties) {
        if (properties.containsKey(property)) {
            var existingVersion = properties.getProperty(property);
            String currentBonitaRuntimeVersion = ProductVersion.BONITA_RUNTIME_VERSION;
            properties.setProperty(property, currentBonitaRuntimeVersion);
            if (existingVersion != null) {
                report.updated(String.format("`%s` property has been updated from `%s` to `%s`.",
                        property,
                        existingVersion,
                        currentBonitaRuntimeVersion));
            } else {
                report.added(String.format("`%s` property has been added with value `%s`.",
                        property,
                        currentBonitaRuntimeVersion));
            }
        }
    }

    @Override
    public boolean appliesTo(Model model, ProjectMetadata metadata) {
        var properties = model.getProperties();
        if (properties.containsKey(BONITA_VERSION_PROPERTY)) {
            return !Objects.equals(minorVersion(properties.getProperty(BONITA_VERSION_PROPERTY)), ProductVersion.minorVersion());
        }
        if (properties.containsKey(BONITA_RUNTIME_VERSION_PROPERTY)) {
            return !Objects.equals(minorVersion(properties.getProperty(BONITA_RUNTIME_VERSION_PROPERTY)),ProductVersion.minorVersion());
        }
        return false;
    }
    
    private static String minorVersion(String bonitaRuntimeVersion) {
        var version = new DefaultArtifactVersion(bonitaRuntimeVersion);
        return version.getMajorVersion() + "." + version.getMinorVersion();
    }

}
