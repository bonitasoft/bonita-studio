/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration.connector;

/**
 * @author Romain Bioteau
 */
public class UpdateWebServiceConnectorVersionTo110 extends UpdateConnectorDefinitionMigration {

    private static final String WEB_SERVICE_CONNECTOR_DEFINITION = "webservice";

    @Override
    protected String getNewDefinitionVersion() {
        return "1.0.1";
    }

    @Override
    protected String getOldDefinitionVersion() {
        return "1.0.0";
    }

    @Override
    protected boolean shouldUpdateVersion(final String defId) {
        return WEB_SERVICE_CONNECTOR_DEFINITION.equals(defId);
    }
}
