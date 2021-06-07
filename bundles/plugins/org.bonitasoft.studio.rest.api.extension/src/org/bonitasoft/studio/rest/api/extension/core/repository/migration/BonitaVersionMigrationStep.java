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

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.migration.report.MigrationReport;
import org.bonitasoft.studio.rest.api.extension.core.repository.MavenModelMigration;

public class BonitaVersionMigrationStep implements MavenModelMigration {

    private static final String BONITA_VERSION_PROPERTY = "bonita.version";

    @Override
    public MigrationReport migrate(Model model) {
        MigrationReport report = new MigrationReport();
        Properties properties = model.getProperties();
        var existingVersion = properties.get(BONITA_VERSION_PROPERTY);
        properties.setProperty(BONITA_VERSION_PROPERTY, ProductVersion.mavenVersion());
        report.updated(String.format("`bonita.version` has been updated from `%s` to `%s`.", existingVersion,
                ProductVersion.mavenVersion()));
        return report;
    }

    @Override
    public boolean appliesTo(Model model) {
        var properties = model.getProperties();
        if (properties.containsKey(BONITA_VERSION_PROPERTY)) {
            return !Objects.equals(properties.get(BONITA_VERSION_PROPERTY), ProductVersion.mavenVersion());
        }
        return false;
    }

}
