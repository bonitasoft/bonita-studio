/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.connectors.util;

public interface DriverConstants {

    public static final String GENERIC_DEFINITION_ID = "database-jdbc";

    public static final String MYSQL_DEFINITION_ID = "database-mysql";
    public static final String MYSQL_DRIVER_JARNAME = "mysql-connector-java-5.1.23-bin.jar";

    public static final String POSTGRES_DEFINITION_84_ID = "database-postgresql84";
    public static final String POSTGRES_DEFINITION_ID = "database-postgresql92";
    public static final String POSTGRES_DRIVER_JARNAME = "postgresql-9.2-1002.jdbc4.jar";

    public static final String SQLSERVER_DEFINITION_ID = "database-mssqlserver";
    public static final String SQLSERVER_DRIVER_JARNAME = "sqljdbc4.jar";

    public static final String ORACLE_11G_DEFINITION_ID = "database-oracle11g";
    public static final String ORACLE_10G_DEFINITION_ID = "database-oracle10g";
    public static final String ORACLE_DRIVER_JARNAME = "ojdbc6.jar";

    public static final String H2_DEFINITION_ID = "database-h2";
    public static final String H2_DRIVER_JARNAME = "h2-1.3.170.jar";

    public static final String HSQLDB_DEFINITION_ID = "database-hsqldb";
    public static final String HSQLDB_DRIVER_JARNAME = "hsqldb.jar";
}
