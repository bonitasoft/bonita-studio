/**
 * Copyright (C) 2020 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

public class UpdateUIPathConnectorTo20 extends UpdateConnectorDefinitionMigration {

    private static final String UIPATH_CONNECTOR_START_JOB_DEF = "uipath-startjob";
    private static final String UIPATH_CONNECTOR_ADD_QUEUE_DEF = "uipath-add-queueItem";
    private static final String UIPATH_CONNECTOR_GET_JOB_DEF = "uipath-getjob";

    @Override
    protected boolean shouldUpdateVersion(String defId) {
        return UIPATH_CONNECTOR_ADD_QUEUE_DEF.equals(defId)
                || UIPATH_CONNECTOR_GET_JOB_DEF.equals(defId)
                || UIPATH_CONNECTOR_START_JOB_DEF.equals(defId);
    }

    @Override
    protected String getNewDefinitionVersion() {
        return "2.0.0";
    }

    @Override
    protected String getOldDefinitionVersion() {
        return "1.0.0";
    }

}
