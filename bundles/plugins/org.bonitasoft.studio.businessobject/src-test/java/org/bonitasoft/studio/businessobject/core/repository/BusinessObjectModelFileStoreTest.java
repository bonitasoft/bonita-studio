/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.core.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class BusinessObjectModelFileStoreTest {

    private BusinessObjectModelFileStore fileStoreUnderTest;

    @Mock
    private BusinessObjectModelRepositoryStore store;

    @Mock
    private DependencyRepositoryStore depStore;

    @Mock
    private DependencyFileStore depFileStore;

    private BusinessObjectModel bdm;

    @Mock
    private IFile iResource;

    @AfterClass
    public static void afterClass() throws Exception {

    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        bdm = new BusinessObjectModel();
        BusinessObject car = new BusinessObject();
        car.setQualifiedName("org.bonita.Car");
        SimpleField modelField = new SimpleField();
        modelField.setName("model");
        car.addField(modelField);
        bdm.addBusinessObject(car);
        BusinessObject employee = new BusinessObject();
        SimpleField firstNameField = new SimpleField();
        firstNameField.setName("firstName");
        employee.addField(firstNameField);
        employee.setQualifiedName("org.bonita.Employee");
        bdm.addBusinessObject(employee);
        fileStoreUnderTest = spy(new BusinessObjectModelFileStore("bdm.zip", store));
        doReturn(new BusinessObjectModelConverter()).when(fileStoreUnderTest).getConverter();
        doReturn(iResource).when(fileStoreUnderTest).getResource();
        doReturn(bdm).when(fileStoreUnderTest).getContent();
    }

    @Test
    public void shouldGetContent_ReturnABusinessObjectModel() throws Exception {
        BusinessObjectModel content = fileStoreUnderTest.getContent();
        assertThat(content).isNotNull();
        assertThat(content.getBusinessObjects()).hasSize(2);
    }

    @Test
    public void shouldGetDisplayName_ReturnBDMLabel() throws Exception {
        assertThat(fileStoreUnderTest.getDisplayName()).isEqualTo(Messages.businessDataModel);
    }

    @Test(expected = AssertionFailedException.class)
    public void shouldDoSave_CreateAEmptyEPackageWhenContentIsNull() throws Exception {
        fileStoreUnderTest.doSave(null);
        BusinessObjectModel content = fileStoreUnderTest.getContent();
        assertThat(content).isNotNull();
        assertThat(content.getBusinessObjects()).isEmpty();
    }

    @Test
    public void shouldDoSave_StoreABusinessObjectModel() throws Exception {
        BusinessObjectModel bdm = new BusinessObjectModel();
        BusinessObject bo = new BusinessObject();
        bo.setQualifiedName("TestClass");
        SimpleField f = new SimpleField();
        f.setName("f1");
        f.setType(FieldType.STRING);
        bo.addField(f);
        bdm.getBusinessObjects().add(bo);

        fileStoreUnderTest.doSave(bdm);
        verify(iResource).create(any(InputStream.class), eq(IResource.FORCE), any(IProgressMonitor.class));
    }

    @Test
    public void shouldDoDelete_DeleteDependency() throws Exception {
        doReturn(depStore).when(fileStoreUnderTest).getDependencyRepositoryStore();
        when(depStore.getChild(anyString(), any(Boolean.class))).thenReturn(depFileStore);
        fileStoreUnderTest.doDelete();
        verify(depFileStore).delete();
    }

    @Test
    public void shouldGetBusinessObjects_ReturnAllBusinessObjectsBusinessObjectModel() throws Exception {
        assertThat(fileStoreUnderTest.getBusinessObjects()).isNotEmpty().hasSize(2);
    }

    @Test
    public void shouldSameContentAs_ReturnTrue() throws Exception {
        BusinessObjectModel bdmToCompare = new BusinessObjectModel();
        BusinessObject car = new BusinessObject();
        car.setQualifiedName("org.bonita.Car");
        SimpleField modelField = new SimpleField();
        modelField.setName("model");
        car.addField(modelField);
        bdmToCompare.addBusinessObject(car);
        BusinessObject employee = new BusinessObject();
        SimpleField firstNameField = new SimpleField();
        firstNameField.setName("firstName");
        employee.addField(firstNameField);
        employee.setQualifiedName("org.bonita.Employee");
        bdmToCompare.addBusinessObject(employee);

        assertThat(fileStoreUnderTest.sameContentAs(bdmToCompare)).isTrue();
        doReturn(null).when(fileStoreUnderTest).getContent();
        assertThat(fileStoreUnderTest.sameContentAs(null)).isTrue();
        assertThat(fileStoreUnderTest.sameContentAs(bdmToCompare)).isFalse();
    }

    @Test
    public void shouldSameContentAs_ReturnFalse() throws Exception {
        assertThat(fileStoreUnderTest.sameContentAs(null)).isFalse();

        BusinessObjectModel bdmToCompare = new BusinessObjectModel();
        BusinessObject car = new BusinessObject();
        car.setQualifiedName("org.bonita.Car");
        SimpleField modelField = new SimpleField();
        modelField.setName("model");
        car.addField(modelField);
        bdmToCompare.addBusinessObject(car);
        BusinessObject employee = new BusinessObject();
        SimpleField firstNameField = new SimpleField();
        firstNameField.setName("firstName2");
        employee.addField(firstNameField);
        employee.setQualifiedName("org.bonita.Employee");
        bdmToCompare.addBusinessObject(employee);

        assertThat(fileStoreUnderTest.sameContentAs(bdmToCompare)).isFalse();
    }

}
