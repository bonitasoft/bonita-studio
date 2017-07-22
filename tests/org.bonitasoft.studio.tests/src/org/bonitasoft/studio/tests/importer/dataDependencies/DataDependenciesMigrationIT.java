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
package org.bonitasoft.studio.tests.importer.dataDependencies;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.tests.importer.BarImporterTestUtil;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class DataDependenciesMigrationIT {

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
    public void testDefaultValueContainingVarNameInAString() throws Exception {
        final MainProcess mainProcess = importBar("DefaultValueContainingVarNameInAString--1.0.bar");
        final Pool pool = (Pool) mainProcess.getElements().get(0);
        final Data data = pool.getData().get(0);
        assertThat(data.getDefaultValue().getReferencedElements()).isEmpty();
    }

    @Test
    @Ignore("usecase not covered")
    public void testDefaultValueContainingAnotherVarNameInAString() throws Exception {
        final MainProcess mainProcess = importBar("DefaultValueContainingAnotherVarNameInAString--1.0.bar");
        final Pool pool = (Pool) mainProcess.getElements().get(0);
        final Data data = pool.getData().get(0);
        assertThat(data.getDefaultValue().getReferencedElements()).isEmpty();
        final Data data2 = pool.getData().get(1);
        assertThat(data2.getDefaultValue().getReferencedElements()).isEmpty();
    }

    @Test
    public void testStepDataInitializedWithProcessData() throws Exception {
        final MainProcess mainProcess = importBar("StepDataInitializedWithProcessData--1.0.bar");
        final Pool pool = (Pool) mainProcess.getElements().get(0);
        final Data data = pool.getData().get(0);
        assertThat(data.getDefaultValue().getReferencedElements()).isEmpty();
        final Data stepData = ((Activity) pool.getElements().get(0)).getData().get(0);
        assertThat(stepData.getDefaultValue().getReferencedElements()).isNotEmpty();
    }

    protected MainProcess importBar(final String barName) throws IOException, Exception {
        final URL fileURL2 = FileLocator.toFileURL(DataDependenciesMigrationIT.class.getResource(barName)); //$NON-NLS-1$
        final File migratedProcess = BarImporterTestUtil.migrateBar(fileURL2);
        migratedProcess.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProcess);
        assertNotNull("Fail to migrate bar file", migratedProcess.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProcess);
        final MainProcess mainProcess = BarImporterTestUtil.getMainProcess(resource);
        new File(resource.getURI().toFileString()).deleteOnExit();
        return mainProcess;
    }
}
