/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bar.tests.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorSourceRepositoryStore;
import org.bonitasoft.studio.importer.bar.tests.BarImporterTestUtil;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class TestCustomConnectorMigrationUseCase {

    private static boolean disablepopup;

    @BeforeClass
    public static void disablePopup() {
        disablepopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
    }

    @AfterClass
    public static void resetdisablePopup() {
        FileActionDialog.setDisablePopup(disablepopup);
    }

    @Test
    public void testCustomonnectorMigration() throws Exception {
        final URL url = TestCustomConnectorMigrationUseCase.class
                .getResource("CustomConnectorMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);

        final ConnectorDefRepositoryStore defStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        assertNotNull(defStore.getDefinition("MyConnectorId", "1.0.0"));

        final ConnectorImplRepositoryStore implStore = (ConnectorImplRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorImplRepositoryStore.class);
        ConnectorImplementation implementation = implStore.getImplementation("MyConnectorId-impl", "1.0.0");
        assertNotNull(implementation);

        final ConnectorSourceRepositoryStore sourceStore = (ConnectorSourceRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorSourceRepositoryStore.class);
        assertNotNull(sourceStore.getChild(implementation.getImplementationClassname()));

        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 1, connectors.size());
        resource.unload();
    }

    @Test
    public void testCustomonnectorMigrationWithGetterCustom() throws Exception {
        final URL url = TestCustomConnectorMigrationUseCase.class
                .getResource("fab--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);

        final ConnectorDefRepositoryStore defStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        assertNotNull(defStore.getDefinition("fabulous_connector", "1.0.0"));

        final ConnectorImplRepositoryStore implStore = (ConnectorImplRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorImplRepositoryStore.class);
        ConnectorImplementation implementation = implStore.getImplementation("fabulous_connector-impl", "1.0.0");
        assertNotNull(implementation);

        final ConnectorSourceRepositoryStore sourceStore = (ConnectorSourceRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorSourceRepositoryStore.class);
        assertNotNull(sourceStore.getChild(implementation.getImplementationClassname()));

        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Invalid number of connector", 1, connectors.size());
        resource.unload();
    }

}
