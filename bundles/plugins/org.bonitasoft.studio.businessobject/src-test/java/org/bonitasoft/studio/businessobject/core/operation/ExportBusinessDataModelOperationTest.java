/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.core.operation;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;

/**
 * @author Romain Bioteau
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class ExportBusinessDataModelOperationTest {

    @Spy
    private ExportBusinessDataModelOperation operationUnderTest;

    private File tempFile;

    @Mock
    private BusinessObjectModelFileStore bdmFileStore;

    @Mock
    private PackageFileStore packageFileStore;

    private BusinessObjectModel bom;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        tempFile = File.createTempFile("testBDMExport", ".zip");
        tempFile.delete();
        bom = new BusinessObjectModel();
        BusinessObject bo = new BusinessObject();
        bo.setQualifiedName("org.bonita.test.Employee");
        SimpleField firstName = new SimpleField();
        firstName.setName("firstName");
        firstName.setType(FieldType.STRING);
        bo.getFields().add(firstName);
        bom.getBusinessObjects().add(bo);
        operationUnderTest.setBdmFileStore(bdmFileStore);
        operationUnderTest.setDestinationFilePath(tempFile.getAbsolutePath());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        tempFile.delete();
    }

    @Test
    public void shouldRun_CallExport() throws Exception {
        operationUnderTest.run(new NullProgressMonitor());
        verify(bdmFileStore).export(tempFile.getAbsolutePath());
    }

}
