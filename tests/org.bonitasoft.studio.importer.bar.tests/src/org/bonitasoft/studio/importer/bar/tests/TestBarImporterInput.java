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
package org.bonitasoft.studio.importer.bar.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.importer.bar.exception.IncompatibleVersionException;
import org.bonitasoft.studio.importer.bar.factory.BarImporterFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class TestBarImporterInput {

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
    public void testNonBarFile() throws Exception {
        assertFalse(".bos should not applies to BarImporterFactory", new BarImporterFactory().appliesTo("toto.bos"));
    }

    @Test(expected = IncompatibleVersionException.class)
    public void testv58Bar() throws Exception {
        final URL url = TestBarImporterInput.class.getResource("v58Bar--1.0.bar");
        BarImporterTestUtil.migrateBar(url);
    }

    @Test(expected = FileNotFoundException.class)
    public void testv6Bar() throws Exception {
        final URL url = TestBarImporterInput.class.getResource("BarV6--1.0.bar");
        BarImporterTestUtil.migrateBar(url);
    }

    @Test
    public void testv59Bar() throws Exception {
        final URL url = TestBarImporterInput.class.getResource("v59Bar--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
    }

    @Test
    public void testv59BarWithDecisionTable() throws Exception {
        final URL url = TestBarImporterInput.class.getResource("Maladie--1.5.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
    }

    @Test
    public void testv59BarWithWidgetDependency() throws Exception {
        final URL url = TestBarImporterInput.class.getResource("RicezioneDocumento--1.1.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
    }

}
