/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.datatools;

import java.util.Properties;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.db.generic.IDBConnectionProfileConstants;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.PropertySetImpl;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;

/**
 * @author Romain Bioteau
 */
public class ConnectionProfileUtil {

    private static final String CONNECTION_PROFILE_POSTGRES = "org.eclipse.datatools.enablement.postgresql.connectionProfile";
    private static final String DRIVERID_POSTGRES = "org.eclipse.datatools.enablement.postgresql.postgresqlDriverTemplate";
    private static final String CONNECTION_PROFILE_GENERIC = "org.eclipse.datatools.connectivity.db.generic.connectionProfile";
    private static final String DRIVERID_GENERIC = "org.eclipse.datatools.connectivity.db.generic.genericDriverTemplate";
    private static final String CONNECTION_PROFILE_HSQLDB = "org.eclipse.datatools.enablement.hsqldb.connectionProfile";
    private static final String DRIVERID_HSQL = "org.eclipse.datatools.enablement.hsqldb.1_8.driver";
    private static final String CONNECTION_PROFILE_SYBASE = "org.eclipse.datatools.enablement.sybase.ase.connectionProfile";
    private static final String DRIVERID_SYBASE = "org.eclipse.datatools.enablement.sybase.ase.15_0.other.driverTemplate";
    private static final String CONNECTION_PROFILE_ORACLE = "org.eclipse.datatools.enablement.oracle.connectionProfile";
    private static final String DRIVERID_ORACLE = "org.eclipse.datatools.enablement.oracle.10.other.driverTemplate";
    private static final String CONNECTION_PROFILE_INGRES = "org.eclipse.datatools.enablement.ingres.connectionProfile";
    private static final String DRIVERID_INGRES = "org.eclipse.datatools.enablement.ingres.2006.driverTemplate";
    private static final String CONNECTION_PROFILE_INFORMIX = "org.eclipse.datatools.enablement.ibm.informix.connectionProfile";
    private static final String DRIVERID_INFORMIX = "org.eclipse.datatools.enablement.ibm.informix.10_0.driverTemplate";
    private static final String CONNECTION_PROFILE_DB2_LUW = "org.eclipse.datatools.enablement.ibm.db2.luw.connectionProfile";
    private static final String DRIVERID_DB2_LUW = "org.eclipse.datatools.enablement.ibm.db2.luw.driverTemplate";
    private static final String CONNECTION_PROFILE_MYSQL = "org.eclipse.datatools.enablement.mysql.connectionProfile";
    private static final String DRIVERID_MYSQL = "org.eclipse.datatools.enablement.mysql.5_1.driverTemplate";
    private static final String CONNECTION_PROFILE_MSSQL = "org.eclipse.datatools.enablement.msft.sqlserver.connectionProfile";
    private static final String DRIVERID_MSSQL = "org.eclipse.datatools.enablement.msft.sqlserver.2008.driverTemplate";
    public static final String PROVIDER_ID = "providerId"; //$NON-NLS-1$
    public static final String DRIVER_NAME = "driverName"; //$NON-NLS-1$
    public static final String DRIVER_ID = "driverName"; //$NON-NLS-1$
    public static final String DRIVER_CLASSNAME = "driverClassName"; //$NON-NLS-1$
    public static final String PORT = "portNumber"; //$NON-NLS-1$
    public static final String HOST = "hostName"; //$NON-NLS-1$

    public static final String DEFAULT_PROFILE_NAME = "database"; //$NON-NLS-1$

    public static final String MYSQL_DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";
    public static final String POSTGRES_DRIVER_CLASSNAME = "org.postgresql.Driver";
    public static final String ORACLE_DRIVER_CLASSNAME = "oracle.jdbc.driver.OracleDriver";
    public static final String DB2_DRIVER_CLASSNAME = "com.ibm.db2.jcc.DB2Driver";
    public static final String SQLSERVER_DRIVER_CLASSNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String TERADATA_DRIVER_CLASSNAME = "com.ncr.teradata.TeraDriver";
    public static final String H2_DRIVER_CLASSNAME = "org.h2.Driver";
    private static final String SYBASE_DRIVER_CLASSNAME = "com.sybase.jdbc3.jdbc.SybDriver";
    private static final String HSQL_DRIVER_CLASSNAME = "org.hsqldb.jdbc.JDBCDriver";
    private static final String INFORMIX_DRIVER_CLASSNAME = "com.informix.jdbc.IfxDriver";
    private static final String INGRES_DRIVER_CLASSNAME = "com.ingres.jdbc.IngresDriver";
    private static final String DB2_ID = "database-db2";

    public static IConnectionProfile createConnectionProfile(final String providerId,
            final String driverId,
            final String driverName,
            final String vendorId,
            final String vendorVersion,
            final String driverClassname,
            final String jdbcUrl,
            final String databaseName,
            final String user,
            final String password) throws ConnectionProfileException, ClassNotFoundException {

        final IConnectionProfile oldProfile = ProfileManager.getInstance().getProfileByName(DEFAULT_PROFILE_NAME);
        if (oldProfile != null) {
            ProfileManager.getInstance().deleteProfile(oldProfile);
        }

        final Properties p = new Properties();
        addCommonProperties(driverId, driverName, vendorId, vendorVersion, driverClassname, jdbcUrl, databaseName, user,
                password, p);
        p.setProperty(ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID,
                createDriverDefinition(driverName, driverId, driverClassname, password, vendorVersion, vendorId,
                        jdbcUrl, databaseName, user));

        return ProfileManager.getInstance().createProfile(DEFAULT_PROFILE_NAME, "", providerId, p); //$NON-NLS-1$
    }

    private static void addCommonProperties(final String driverId, final String driverName, final String vendorId,
            final String vendorVersion,
            final String driverClassname, final String jdbcUrl, final String databaseName, final String user,
            final String password, final Properties p)
            throws ClassNotFoundException {
        if (jdbcUrl != null) {
            p.setProperty(IDBConnectionProfileConstants.URL_PROP_ID, jdbcUrl);
        }
        p.setProperty(IDBConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID, "");
        if (user != null) {
            p.setProperty(IJDBCConnectionProfileConstants.USERNAME_PROP_ID, user);
        }
        if (password != null) {
            p.setProperty(IJDBCConnectionProfileConstants.PASSWORD_PROP_ID, password);
        }
        if (databaseName != null) {
            p.setProperty(IJDBCConnectionProfileConstants.DATABASE_NAME_PROP_ID, databaseName);
        }

        p.setProperty(IJDBCDriverDefinitionConstants.DATABASE_VENDOR_PROP_ID, vendorId);
        p.setProperty(IJDBCDriverDefinitionConstants.DATABASE_VERSION_PROP_ID, vendorVersion);
    }

    public static String createDriverDefinition(final String driverName, String driverId, final String driverClassname,
            final String password,
            final String vendorVersion, final String vendorId, final String jdbcUrl, final String databaseName,
            final String user)
            throws ClassNotFoundException {
        if (driverId == null) {
            driverId = "DriverId";
        }
        final IPropertySet propSet = new PropertySetImpl(driverName, driverId);
        final Properties driverProperties = new Properties();
        addCommonProperties(driverId, driverName, vendorId, vendorVersion, driverClassname, jdbcUrl, databaseName, user,
                password, driverProperties);
        driverProperties.setProperty(IDriverMgmtConstants.PROP_DEFN_TYPE, driverId);
        driverProperties.setProperty(IJDBCConnectionProfileConstants.DRIVER_CLASS_PROP_ID, driverClassname);
        driverProperties.setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST, createJarListFromRepository(driverId));
        propSet.setBaseProperties(driverProperties);
        DriverManager.getInstance().addDriverInstance(propSet);
        return driverId;
    }

    public static IConnectionProfile createMySQLConnectionProfile(final String jdbcUrl, final String user,
            final String password,
            String className, final String databaseName) throws ConnectionProfileException, ClassNotFoundException {
        if (className == null) {
            className = MYSQL_DRIVER_CLASSNAME;
        }
        return createConnectionProfile(CONNECTION_PROFILE_MYSQL, DRIVERID_MYSQL, "MySql", "MySql", "5.1", className,
                jdbcUrl, databaseName, user, password);
    }

    public static IConnectionProfile createPostresConnectionProfile(final String jdbcUrl, final String user,
            final String password, String className,
            final String databaseName) throws ConnectionProfileException, ClassNotFoundException {
        if (className == null) {
            className = POSTGRES_DRIVER_CLASSNAME;
        }
        return createConnectionProfile(CONNECTION_PROFILE_POSTGRES, DRIVERID_POSTGRES, "postgres", "postgres", "8.x",
                className, jdbcUrl, databaseName, user,
                password);
    }

    public static IConnectionProfile createOracleConnectionProfile(final String jdbcUrl, final String user,
            final String password, String className,
            final String databaseName) throws ConnectionProfileException, ClassNotFoundException {
        if (className == null) {
            className = ORACLE_DRIVER_CLASSNAME;
        }
        return createConnectionProfile(CONNECTION_PROFILE_ORACLE, DRIVERID_ORACLE, "Oracle", "Oracle", "10", className,
                jdbcUrl, databaseName == null ? user
                        : databaseName,
                user, password);
    }

    public static IConnectionProfile createSQLServerConnectionProfile(final String jdbcUrl, final String user,
            final String password, String className, final String databaseName)
            throws ConnectionProfileException, ClassNotFoundException {
        if (className == null) {
            className = SQLSERVER_DRIVER_CLASSNAME;
        }
        return createConnectionProfile(CONNECTION_PROFILE_MSSQL, DRIVERID_MSSQL, "sqlserver", "SQL Server", "2008",
                className, jdbcUrl, databaseName, user,
                password);
    }

    public static IConnectionProfile createH2ConnectionProfile(final String jdbcUrl, final String user,
            final String password, final String className,
            final String databaseName) throws ConnectionProfileException, ClassNotFoundException {
        return createGenericConnectionProfile(className, jdbcUrl, user, password, databaseName);
    }

    public static IConnectionProfile createDB2ConnectionProfile(final String jdbcUrl, final String user,
            final String password, String className,
            final String databaseName) throws ConnectionProfileException, ClassNotFoundException {
        if (className == null) {
            className = DB2_DRIVER_CLASSNAME;
        }
        return createConnectionProfile(CONNECTION_PROFILE_DB2_LUW, DRIVERID_DB2_LUW, "db2 udb", "DB2 UDB", "9.1",
                className, jdbcUrl, databaseName, user,
                password);
    }

    public static IConnectionProfile createTeraDataConnectionProfile(final String jdbcUrl, final String user,
            final String password, final String className,
            final String databaseName) throws ConnectionProfileException, ClassNotFoundException {
        return createGenericConnectionProfile(className, jdbcUrl, user, password, databaseName);
    }

    public static IConnectionProfile createSybaseConnectionProfile(final String jdbcUrl, final String user,
            final String password, String className,
            final String databaseName) throws ConnectionProfileException, ClassNotFoundException {
        if (className == null) {
            className = SYBASE_DRIVER_CLASSNAME;
        }
        return createConnectionProfile(CONNECTION_PROFILE_SYBASE, DRIVERID_SYBASE, "sybase", "Sybase_ASE", "15.x",
                className, jdbcUrl, databaseName, user,
                password);
    }

    public static IConnectionProfile createHSQLConnectionProfile(final String jdbcUrl, final String user,
            final String password, String className,
            final String databaseName) throws ConnectionProfileException, ClassNotFoundException {
        if (className == null) {
            className = HSQL_DRIVER_CLASSNAME;
        }
        return createConnectionProfile(CONNECTION_PROFILE_HSQLDB, DRIVERID_HSQL, "HSQLDB", "HSQLDB", "1.8", className,
                jdbcUrl, databaseName, user, password);
    }

    public static IConnectionProfile createInformixConnectionProfile(final String jdbcUrl, final String user,
            final String password, String className,
            final String databaseName) throws ConnectionProfileException, ClassNotFoundException {
        if (className == null) {
            className = INFORMIX_DRIVER_CLASSNAME;
        }
        return createConnectionProfile(CONNECTION_PROFILE_INFORMIX, DRIVERID_INFORMIX, "Informix", "Informix", "10",
                className, jdbcUrl, databaseName, user,
                password);
    }

    public static IConnectionProfile createIngresConnectionProfile(final String jdbcUrl, final String user,
            final String password, String className,
            final String databaseName) throws ConnectionProfileException, ClassNotFoundException {
        if (className == null) {
            className = INGRES_DRIVER_CLASSNAME;
        }
        return createConnectionProfile(CONNECTION_PROFILE_INGRES, DRIVERID_INGRES, "Ingres", "Ingres", "2006",
                className, jdbcUrl, databaseName, user, password);
    }

    public static IConnectionProfile createGenericConnectionProfile(final String className, final String jdbcUrl,
            final String user, final String password,
            final String databaseName) throws ConnectionProfileException, ClassNotFoundException {
        return createConnectionProfile(CONNECTION_PROFILE_GENERIC, DRIVERID_GENERIC, "Generic JDBC", "Generic JDBC",
                "1.0", className, jdbcUrl, databaseName,
                user, password);
    }

    private static String createJarListFromRepository(final String driverId) {
        final DatabaseConnectorPropertiesRepositoryStore dbStore = RepositoryManager.getInstance()
                .getRepositoryStore(DatabaseConnectorPropertiesRepositoryStore.class);
        final DatabaseConnectorPropertiesFileStore fileStore = dbStore.getChild(driverId + ".properties", true);
        final DependencyRepositoryStore depStore = RepositoryManager.getInstance()
                .getRepositoryStore(DependencyRepositoryStore.class);
        String defaultDriverJar = null;
        if (fileStore != null) {
            defaultDriverJar = fileStore.getDefault();
        }
        final StringBuilder sb = new StringBuilder();
        for (final DependencyFileStore f : depStore.getChildren()) {
            if (!DB2_ID.equals(driverId) && defaultDriverJar != null && defaultDriverJar.equals(f.getName())) {
                sb.append(f.getFile().getAbsolutePath());
                sb.append(IDriverMgmtConstants.PATH_DELIMITER);
            } else if (defaultDriverJar == null || DB2_ID.equals(driverId)) {
                sb.append(f.getFile().getAbsolutePath());
                sb.append(IDriverMgmtConstants.PATH_DELIMITER);
            }
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }

}
