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
package org.bonitasoft.studio.common.repository.core.maven.migration.connector;

import org.bonitasoft.studio.common.repository.core.maven.migration.BonitaJarDependencyReplacement;


public class CMISConnectorDependenciesReplacement extends BonitaJarDependencyReplacement {
    
    public CMISConnectorDependenciesReplacement() {
        super(dependency(CONNECTOR_GROUP_ID, "bonita-connector-cmis", "3.0.5"),
                "bonita-connector-cmis-3.0.*.jar",
                "bonita-connector-cmis-2.0.1.jar",
                "bonita-connector-cmis-createfolder-impl-.*.jar",
                "bonita-connector-cmis-deletedocument-impl-.*.jar",
                "bonita-connector-cmis-deletefolder-impl-.*.jar",
                "bonita-connector-cmis-deleteversionofdocument-impl-.*.jar",
                "bonita-connector-cmis-downloaddocument-impl-.*.jar",
                "bonita-connector-cmis-uploadnewdocument-impl-.*.jar",
                "bonita-connector-cmis-uploadnewversionofdocument-impl-.*.jar"
                );
    }

}
