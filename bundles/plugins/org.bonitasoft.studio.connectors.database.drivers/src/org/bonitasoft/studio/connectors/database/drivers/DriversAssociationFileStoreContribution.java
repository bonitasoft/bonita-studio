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
package org.bonitasoft.studio.connectors.database.drivers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.bonitasoft.studio.common.repository.model.IFileStoreContribution;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.connectors.util.DriverConstants;

public class DriversAssociationFileStoreContribution implements
        IFileStoreContribution {

    @Override
    public boolean appliesTo(
            final IRepositoryStore<? extends IRepositoryFileStore> repository) {
        return repository instanceof DatabaseConnectorPropertiesRepositoryStore;
    }

    @Override
    public void execute(final IRepositoryStore<? extends IRepositoryFileStore> repository) {
        createDatabaseDriverAssociation(repository, DriverConstants.MYSQL_DEFINITION_ID,
                DriverConstants.MYSQL_DRIVER_JARNAME);
        createDatabaseDriverAssociation(repository, DriverConstants.POSTGRES_DEFINITION_ID,
                DriverConstants.POSTGRES_DRIVER_JARNAME);
        createDatabaseDriverAssociation(repository, DriverConstants.POSTGRES_DEFINITION_84_ID,
                DriverConstants.POSTGRES_DRIVER_JARNAME);
        createDatabaseDriverAssociation(repository, DriverConstants.SQLSERVER_DEFINITION_ID,
                DriverConstants.SQLSERVER_DRIVER_JARNAME);
        createDatabaseDriverAssociation(repository, DriverConstants.ORACLE_11G_DEFINITION_ID,
                DriverConstants.ORACLE_DRIVER_JARNAME);
        createDatabaseDriverAssociation(repository, DriverConstants.ORACLE_10G_DEFINITION_ID,
                DriverConstants.ORACLE_DRIVER_JARNAME);
        createDatabaseDriverAssociation(repository, DriverConstants.H2_DEFINITION_ID, DriverConstants.H2_DRIVER_JARNAME);
        createDatabaseDriverAssociation(repository, DriverConstants.HSQLDB_DEFINITION_ID,
                DriverConstants.HSQLDB_DRIVER_JARNAME);
        createGenericDriverList(repository);
    }

    private void createGenericDriverList(final IRepositoryStore<? extends IRepositoryFileStore> repository) {
        final Enumeration<URL> drivers = ConnectorPlugin.getDefault().getBundle().findEntries("drivers", "*.jar", false);
        final List<String> jarList = new ArrayList<>();
        while (drivers.hasMoreElements()) {
            final URL url = drivers.nextElement();
            final String file = url.getFile();
            final String[] segments = file.split("/");
            final String name = segments[segments.length - 1];
            jarList.add(name);
        }

        final DatabaseConnectorPropertiesFileStore file = (DatabaseConnectorPropertiesFileStore) repository
                .createRepositoryFileStore(DriverConstants.GENERIC_DEFINITION_ID);
        file.setAutoAddDriver(false);
        file.setJarList(jarList);
    }

    private void createDatabaseDriverAssociation(final IRepositoryStore<? extends IRepositoryFileStore> repository,
            final String definitionId, final String driverJarname) {
        final Enumeration<URL> drivers = ConnectorPlugin.getDefault().getBundle().findEntries("drivers", driverJarname,
                false);
        if (drivers != null && drivers.hasMoreElements()) {
            final DatabaseConnectorPropertiesFileStore file = (DatabaseConnectorPropertiesFileStore) repository
                    .createRepositoryFileStore(definitionId);
            file.setAutoAddDriver(true);
            file.setDefault(driverJarname);
            file.setJarList(Collections.singletonList(driverJarname));
        }
    }

}
