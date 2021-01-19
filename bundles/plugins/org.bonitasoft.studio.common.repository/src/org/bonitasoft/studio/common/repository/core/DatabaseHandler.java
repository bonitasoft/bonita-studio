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
package org.bonitasoft.studio.common.repository.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.eclipse.core.resources.IProject;

public class DatabaseHandler {

    private static final String BONITA_DB_VENDOR = "db.vendor";
    public static final String H2_DATABASE_FOLDER_NAME = "h2_database";
    public static final String DB_LOCATION_PROPERTY = "org.bonitasoft.h2.database.dir";
    public static final String BITRONIX_ROOT = "btm.root";
    static final String BONITA_DB_NAME_PROPERTY = "db.database.name";
    static final String BUSINESS_DATA_DB_NAME_PROPERTY = "bdm.db.database.name";
    public static final String DEFAULT_DB_VENDOR = "h2";
    private static final String BDM_DB_VENDOR = "bdm.db.vendor";
    private static final String DEFAULT_BUSINESS_DATA_DB_NAME = "business_data.db";

    private final IProject project;

    public DatabaseHandler(final IProject project) {
        this.project = project;
    }

    protected Properties readDatabaseProperties() throws IOException {
        Path rootPath = project.getLocation().toFile().getParentFile().toPath();
        File file = rootPath.resolve("tomcat").resolve("setup").resolve("database.properties").toFile();
        //        final URL databasePropertiesURL = FileLocator
        //                .toFileURL(ProjectUtil.getConsoleLibsBundle().getResource("tomcat/setup/database.properties"));
        //  final File file = new File(databasePropertiesURL.getFile());
      //  File file = databaseProperties.getLocation().toFile();
        final Properties properties = new Properties();
        try (InputStream inStream = new FileInputStream(file)) {
            properties.load(inStream);
        } 
        return properties;
    }

    public void removeEngineDatabase() throws IOException {
        deleteH2DbFiles(getBonitaDBName());
    }

    public String getBonitaDBName() throws IOException {
        final Properties databaseProperties = readDatabaseProperties();
        return databaseProperties.getProperty(BONITA_DB_NAME_PROPERTY);
    }

    public void removeBusinessDataDatabase() throws IOException {
        deleteH2DbFiles(getBusinessDataDBName());
    }

    protected void deleteH2DbFiles(final String dbFileName) {
        final File workDir = getDBLocation();
        if (workDir != null && workDir.exists()) {
            for (final File file : workDir.listFiles()) {
                final String fileName = file.getName();
                if (fileName.endsWith(".db") && fileName.startsWith(dbFileName)) {
                    PlatformUtil.delete(file, null);
                    if (file.exists()) {
                        BonitaStudioLog.info(fileName + " failed to be deleted", CommonRepositoryPlugin.PLUGIN_ID);
                    } else {
                        BonitaStudioLog.info(fileName + " has been deleted successfuly",
                                CommonRepositoryPlugin.PLUGIN_ID);
                    }
                }
            }
        }
    }

    public File getDBLocation() {
        return new File(project.getLocation().toFile(), H2_DATABASE_FOLDER_NAME);
    }

    public String getBonitaDBVendor() {
        Properties databaseProperties;
        try {
            databaseProperties = readDatabaseProperties();
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return DEFAULT_DB_VENDOR;
        }
        return databaseProperties.getProperty(BONITA_DB_VENDOR, DEFAULT_DB_VENDOR);
    }

    public String getBDMDBVendor() {
        Properties databaseProperties;
        try {
            databaseProperties = readDatabaseProperties();
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return DEFAULT_DB_VENDOR;
        }
        return databaseProperties.getProperty(BDM_DB_VENDOR, DEFAULT_DB_VENDOR);
    }

    public String getBusinessDataDBName() {
        try {
            Properties databaseProperties = readDatabaseProperties();
            return databaseProperties.getProperty(BUSINESS_DATA_DB_NAME_PROPERTY, DEFAULT_BUSINESS_DATA_DB_NAME);
        } catch (IOException e) {
            return DEFAULT_BUSINESS_DATA_DB_NAME;
        }
    }

}
