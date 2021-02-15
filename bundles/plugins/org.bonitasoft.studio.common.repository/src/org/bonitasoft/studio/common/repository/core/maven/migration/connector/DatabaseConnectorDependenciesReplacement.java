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


public class DatabaseConnectorDependenciesReplacement extends BonitaJarDependencyReplacement {
    
    public DatabaseConnectorDependenciesReplacement() {
        super(dependency(CONNECTOR_GROUP_ID, "bonita-connector-database", "2.0.3"),
                "bonita-connector-database-2.0.3.jar",
                "bonita-connector-database-2.0.1.jar",
                "bonita-connector-database-2.0.0.jar",
                "bonita-connector-database-1.2.2.jar",
                "bonita-connector-database-1.2.1.jar",
                "bonita-connector-database-1.2.0.jar",
                "bonita-connector-database-datasource-impl-.*.jar",
                "bonita-connector-database-access-impl-.*.jar",
                "bonita-connector-database-as400-impl-.*.jar",
                "bonita-connector-database-db2-impl-.*.jar",
                "bonita-connector-database-h2-impl-.*.jar",
                "bonita-connector-database-hsqldb-impl-.*.jar",
                "bonita-connector-database-informix-impl-.*.jar",
                "bonita-connector-database-ingres-impl-.*.jar",
                "bonita-connector-database-jdbc-impl-.*.jar",
                "bonita-connector-database-mssqlserver2008-impl-.*.jar",
                "bonita-connector-database-mssqlserver2012-impl-.*.jar",
                "bonita-connector-database-mysql-impl-.*.jar",
                "bonita-connector-database-oracle10g-impl-.*.jar",
                "bonita-connector-database-oracle11g-impl-.*.jar",
                "bonita-connector-database-postgresql84-impl-.*.jar",
                "bonita-connector-database-postgresql92-impl-.*.jar",
                "bonita-connector-database-sybase-impl-.*.jar",
                "bonita-connector-database-teradata-impl-.*.jar");
    }

}
