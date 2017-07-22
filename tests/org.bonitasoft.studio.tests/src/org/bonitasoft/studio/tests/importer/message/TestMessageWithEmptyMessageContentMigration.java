/**
 * Copyright (C) 2014 BonitaSoft S.A.
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

package org.bonitasoft.studio.tests.importer.message;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.tests.importer.BarImporterTestUtil;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Florine Boudin
 */
public class TestMessageWithEmptyMessageContentMigration extends TestCase {

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
    public void testMessageWithEmptyMessageContentMigration() throws Exception {
        URL fileURL2 = FileLocator
                .toFileURL(TestMessageWithEmptyMessageContentMigration.class.getResource("PoolCKMM1--1.0.bar"));
        File migratedProcess = BarImporterTestUtil.migrateBar(fileURL2);
        migratedProcess.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProcess);
        assertNotNull("Fail to migrate bar file", migratedProcess.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProcess);
        final MainProcess mainProcess = BarImporterTestUtil.getMainProcess(resource);

        final List<Message> messages = ModelHelper.getAllItemsOfType(
                mainProcess, ProcessPackage.Literals.MESSAGE);
        assertEquals("Invalid number of connector", 1, messages.size());

        for (Message m : messages) {
            assertNotNull("ERROR: Message should not have an empty messageContent", m.getMessageContent());
        }
        resource.unload();
    }

}
