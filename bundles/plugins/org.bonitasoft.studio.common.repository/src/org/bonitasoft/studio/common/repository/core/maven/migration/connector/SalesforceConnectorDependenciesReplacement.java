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

import java.util.Set;

import org.bonitasoft.studio.common.repository.core.maven.migration.BonitaJarDependencyReplacement;


public class SalesforceConnectorDependenciesReplacement extends BonitaJarDependencyReplacement {
    
    private static final Set<String> DEFINITIONS = Set.of(
            "salesforce-createsobject",
            "salesforce-deletesobjects",
            "salesforce-querysobjects",
            "salesforce-retrievesobjects",
            "salesforce-updatesobject");
    
    public SalesforceConnectorDependenciesReplacement() {
        super(dependency(CONNECTOR_GROUP_ID, "bonita-connector-salesforce", "1.1.3"), 
                "bonita-connector-salesforce-1.1.3.jar",
                "bonita-connector-salesforce-1.1.2.jar",
                "bonita-connector-salesforce-1.1.1.jar",
                "bonita-connector-salesforce-1.1.0.jar",
                "bonita-connector-salesforce-createsobject-impl-.*.jar",
                "bonita-connector-salesforce-deletesobjects-impl-.*.jar",
                "bonita-connector-salesforce-querysobjects-impl-.*.jar",
                "bonita-connector-salesforce-retrievesobjects-impl-.*.jar",
                "bonita-connector-salesforce-updatesobject-impl-.*.jar");
    }
    
    @Override
    public boolean matchesDefinition(String definitionId) {
        return DEFINITIONS.contains(definitionId);
    }

}
