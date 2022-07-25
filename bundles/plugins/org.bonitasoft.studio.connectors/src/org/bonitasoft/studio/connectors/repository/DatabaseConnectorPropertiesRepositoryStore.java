/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

package org.bonitasoft.studio.connectors.repository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import org.bonitasoft.studio.common.repository.core.maven.migration.BonitaJarDependencyReplacement;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.util.DriverConstants;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.swt.graphics.Image;

public class DatabaseConnectorPropertiesRepositoryStore
        extends AbstractRepositoryStore<DatabaseConnectorPropertiesFileStore> {

    public static final String STORE_NAME = "database_connectors_properties";
    public static final String PROPERTIES_EXT = "properties";
    private static final Set<String> extensions = Set.of(PROPERTIES_EXT);

    private static final Map<String, String> DRIVER_CLASSNAME_TO_DEFININITION_ID = new HashMap<>();
    static {
        DRIVER_CLASSNAME_TO_DEFININITION_ID.put(DriverConstants.H2_DRIVER_CLASSNAME, "database-h2");
        DRIVER_CLASSNAME_TO_DEFININITION_ID.put(DriverConstants.DB2_DRIVER_CLASSNAME, "database-db2");
        DRIVER_CLASSNAME_TO_DEFININITION_ID.put(DriverConstants.ORACLE_DRIVER_CLASSNAME, "database-oracle11g");
        DRIVER_CLASSNAME_TO_DEFININITION_ID.put(DriverConstants.POSTGRES_DRIVER_CLASSNAME, "database-postgresql92");
        DRIVER_CLASSNAME_TO_DEFININITION_ID.put(DriverConstants.SQLSERVER_DRIVER_CLASSNAME, "database-mssqlserver");
        DRIVER_CLASSNAME_TO_DEFININITION_ID.put(DriverConstants.MYSQL_DRIVER_CLASSNAME, "database-mysql");
        DRIVER_CLASSNAME_TO_DEFININITION_ID.put(DriverConstants.INFORMIX_DRIVER_CLASSNAME, "database-informix");
        DRIVER_CLASSNAME_TO_DEFININITION_ID.put(DriverConstants.TERADATA_DRIVER_CLASSNAME, "database-teradata");
        DRIVER_CLASSNAME_TO_DEFININITION_ID.put(DriverConstants.HSQL_DRIVER_CLASSNAME, "database-hsqldb");
        DRIVER_CLASSNAME_TO_DEFININITION_ID.put(DriverConstants.INGRES_DRIVER_CLASSNAME, "database-ingres");
        DRIVER_CLASSNAME_TO_DEFININITION_ID.put(DriverConstants.SYBASE_DRIVER_CLASSNAME, "database-sybase");
    }

    @Override
    public String getName() {
        return STORE_NAME;
    }

    @Override
    public boolean canBeExported() {
        return super.canBeExported();
    }

    @Override
    public String getDisplayName() {
        return Messages.databaseConnectorsProperties;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("databases_driver.png", ConnectorPlugin.getDefault());
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    public DatabaseConnectorPropertiesFileStore createRepositoryFileStore(String fileName) {
        if (fileName != null && !fileName.endsWith("." + PROPERTIES_EXT)) {
            fileName = fileName + "." + PROPERTIES_EXT;
        }
        return new DatabaseConnectorPropertiesFileStore(fileName, this);
    }

    @Override
    protected InputStream handlePreImport(String fileName, InputStream inputStream)
            throws MigrationException, IOException {
        final InputStream is = super.handlePreImport(fileName, inputStream);
        Properties properties = new Properties();
        properties.load(is);
        String defaultJar = properties.getProperty(DatabaseConnectorPropertiesFileStore.DEFAULT);
        List<String> jars = DatabaseConnectorPropertiesFileStore
                .readJarLists(properties.getProperty(DatabaseConnectorPropertiesFileStore.JAR_LIST));
        if (defaultJar != null && !defaultJar.isBlank()) {
            BonitaJarDependencyReplacement
                    .getDatabaseDriverDependencyReplacements().stream()
                    .filter(bddDepReplacement -> Objects.equals(defaultJar, bddDepReplacement.getFileName()))
                    .findFirst()
                    .ifPresent(r -> {
                        properties.setProperty(DatabaseConnectorPropertiesFileStore.DEFAULT, r.getReplacementJarName());
                    });
        }
        if (!jars.isEmpty()) {
            BonitaJarDependencyReplacement
                    .getDatabaseDriverDependencyReplacements().stream()
                    .filter(bddDepReplacement -> jars.contains(bddDepReplacement.getFileName()))
                    .forEach(r -> {
                        jars.remove(r.getFileName());
                        if (!jars.contains(r.getReplacementJarName())) {
                            jars.add(r.getReplacementJarName());
                        }
                        properties.setProperty(DatabaseConnectorPropertiesFileStore.JAR_LIST,
                                DatabaseConnectorPropertiesFileStore.jarListsToString(jars));
                    });
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        properties.store(output, null);
        return new ByteArrayInputStream(output.toByteArray());
    }

    public Optional<DatabaseConnectorPropertiesFileStore> findByDriverClassName(String driverId) {
        return Optional.ofNullable(getChild(String.format("%s.%s", DRIVER_CLASSNAME_TO_DEFININITION_ID.get(driverId),
                DatabaseConnectorPropertiesRepositoryStore.PROPERTIES_EXT), true));
    }

    public void jarRemoved(String jar) {
        getChildren().stream().forEach(fStore -> fStore.jarRemoved(jar));
    }

}
