/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.custom.migration.connector;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UpdateConnectorDefinitionMigrationTest {

    @Mock
    private Model model;

    @Mock
    private Metamodel metamodel;

    @Mock
    private UpdateConnectorDefinitionMigration updateConnectorVersionMigration;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_migrateAfter_update_definitionVersion_in_connector() throws Exception {
        //Given
        doCallRealMethod().when(updateConnectorVersionMigration).migrateAfter(model, metamodel);
        final EList<Instance> connectorInstanceList = connectorInstanceList("id1","id2");
        final Instance oldConnectorInstance = aConnectorInstance("id1", "0.9");
        connectorInstanceList.add(oldConnectorInstance);
        when(model.getAllInstances("process.Connector")).thenReturn(connectorInstanceList);
        when(model.getAllInstances("connectorconfiguration.ConnectorConfiguration")).thenReturn(new BasicEList<Instance>());
        when(updateConnectorVersionMigration.shouldUpdateVersion("id1")).thenReturn(true);
        when(updateConnectorVersionMigration.getOldDefinitionVersion()).thenReturn("1.0");
        when(updateConnectorVersionMigration.getNewDefinitionVersion()).thenReturn("2.0");


        //When
        updateConnectorVersionMigration.migrateAfter(model, metamodel);

        //Then
        final Instance id1Connector = connectorInstanceList.get(0);
        final Instance id2Connector = connectorInstanceList.get(1);
        verify(id1Connector).set(UpdateConnectorDefinitionMigration.DEFINITION_VERSION_FEATURE_NAME, "2.0");
        verify(id2Connector, never()).set(UpdateConnectorDefinitionMigration.DEFINITION_VERSION_FEATURE_NAME, "2.0");
        verify(oldConnectorInstance, never()).set(UpdateConnectorDefinitionMigration.DEFINITION_VERSION_FEATURE_NAME, "2.0");
    }

    @Test
    public void should_migrateAfter_update_version_in_connector_configuration() throws Exception {
        //Given
        doCallRealMethod().when(updateConnectorVersionMigration).migrateAfter(model, metamodel);
        final EList<Instance> connectorConfigInstanceList = connectorConfiguratiobInstanceList("id1", "id2");
        final Instance oldConnectorConfInstance = aConnectorConfigurationInstance("id1", "0.9");
        connectorConfigInstanceList.add(oldConnectorConfInstance);
        when(model.getAllInstances("connectorconfiguration.ConnectorConfiguration")).thenReturn(connectorConfigInstanceList);
        when(model.getAllInstances("process.Connector")).thenReturn(new BasicEList<Instance>());
        when(updateConnectorVersionMigration.shouldUpdateVersion("id1")).thenReturn(true);
        when(updateConnectorVersionMigration.getOldDefinitionVersion()).thenReturn("1.0");
        when(updateConnectorVersionMigration.getNewDefinitionVersion()).thenReturn("2.0");

        //When
        updateConnectorVersionMigration.migrateAfter(model, metamodel);

        //Then
        final Instance id1ConnectorConf = connectorConfigInstanceList.get(0);
        final Instance id2ConnectorConf = connectorConfigInstanceList.get(1);
        verify(id1ConnectorConf).set(UpdateConnectorDefinitionMigration.VERSION_FEATURE_NAME, "2.0");
        verify(id2ConnectorConf, never()).set(UpdateConnectorDefinitionMigration.VERSION_FEATURE_NAME, "2.0");
        verify(oldConnectorConfInstance, never()).set(UpdateConnectorDefinitionMigration.VERSION_FEATURE_NAME, "2.0");
    }

    private EList<Instance> connectorInstanceList(final String... defIds) {
        final BasicEList<Instance> instances = new BasicEList<Instance>();
        for (final String defId : defIds) {
            instances.add(aConnectorInstance(defId, "1.0"));
        }
        return instances;
    }

    private EList<Instance> connectorConfiguratiobInstanceList(final String... defIds) {
        final BasicEList<Instance> instances = new BasicEList<Instance>();
        for (final String defId : defIds) {
            instances.add(aConnectorConfigurationInstance(defId, "1.0"));
        }
        return instances;
    }

    private Instance aConnectorConfigurationInstance(final String definitionId, final String definitionVersion) {
        final Instance instance = mock(Instance.class);
        when(instance.get(UpdateConnectorDefinitionMigration.DEFINITION_ID_FEATURE_NAME)).thenReturn(definitionId);
        when(instance.get(UpdateConnectorDefinitionMigration.VERSION_FEATURE_NAME)).thenReturn(definitionVersion);
        return instance;
    }

    private Instance aConnectorInstance(final String definitionId, final String definitionVersion) {
        final Instance instance = mock(Instance.class);
        when(instance.get(UpdateConnectorDefinitionMigration.DEFINITION_ID_FEATURE_NAME)).thenReturn(definitionId);
        when(instance.get(UpdateConnectorDefinitionMigration.DEFINITION_VERSION_FEATURE_NAME)).thenReturn(definitionVersion);
        return instance;
    }
}
