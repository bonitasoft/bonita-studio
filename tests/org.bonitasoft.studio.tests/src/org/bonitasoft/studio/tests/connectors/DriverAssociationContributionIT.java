/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.connectors;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.Enumeration;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.connectors.util.DriverConstants;
import org.junit.Ignore;
import org.junit.Test;


@Ignore
public class DriverAssociationContributionIT {

    @Test
    public void testProvidedJarsWithCorrectName() {
        final String[] drivers = new String[] { DriverConstants.MYSQL_DRIVER_JARNAME,
                DriverConstants.POSTGRES_DRIVER_JARNAME,
                DriverConstants.SQLSERVER_DRIVER_JARNAME,
                DriverConstants.ORACLE_DRIVER_JARNAME,
                DriverConstants.H2_DRIVER_JARNAME,
                DriverConstants.HSQLDB_DRIVER_JARNAME };
        for (final String driver : drivers) {
            final Enumeration<URL> driverEntries = ConnectorPlugin.getDefault().getBundle().findEntries("drivers", driver, false);
            assertThat(driverEntries).as("Missing driver " + driver).isNotNull();
            assertThat(driverEntries.hasMoreElements()).as("Missing driver " + driver).isTrue();
        }
    }

    @Test
    public void testAssociationCreated() {
        final DatabaseConnectorPropertiesRepositoryStore repo = RepositoryManager.getInstance().getRepositoryStore(
                DatabaseConnectorPropertiesRepositoryStore.class);
        assertThat(repo.getChild(DriverConstants.POSTGRES_DEFINITION_ID + ".properties", true)).isNotNull();
        assertThat(repo.getChild(DriverConstants.ORACLE_11G_DEFINITION_ID + ".properties", true)).isNotNull();
    }

}
