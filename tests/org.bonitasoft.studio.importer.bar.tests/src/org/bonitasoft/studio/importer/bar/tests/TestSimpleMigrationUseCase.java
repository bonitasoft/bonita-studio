/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bar.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class TestSimpleMigrationUseCase {


    private static boolean disablepopup;

    @BeforeClass
    public static void disablePopup(){
        disablepopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
    }

    @AfterClass
    public static void resetdisablePopup(){
        FileActionDialog.setDisablePopup(disablepopup);
    }


    @Test
    public void testDatatypesMigration() throws Exception{
        final URL url = TestSimpleMigrationUseCase.class.getResource("AllDatatypes--1.0.bar");
        final File migratedProc =  BarImporterTestUtil.migrateBar(url);
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);

        final List<DataType> datatypes =  ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.DATA_TYPE);
        assertEquals("Invalid number of datatypes", 9, datatypes.size()); //8 provided + 1 enum
        final DataTypeSwitch typesSwitch = new DataTypeSwitch(datatypes);
        typesSwitch.testDatatypesConsistency();
    }

    @Test
    public void testCallActivityMigration() throws Exception{
        final URL url = TestSimpleMigrationUseCase.class.getResource("Simple_Call_Activity--1.0.bar");
        final File migratedProc =  BarImporterTestUtil.migrateBar(url);
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        assertEquals("Call Activity is missing",1, ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CALL_ACTIVITY).size());
        BarImporterTestUtil.assertViewsAreConsistent(resource);
    }

}
