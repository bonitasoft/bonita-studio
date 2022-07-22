/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.core.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.eclipse.core.runtime.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class BusinessObjectModelRepositoryStoreTest {

    @Spy
    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> storeUnderTest;

    @Mock
    private BusinessObjectModelFileStore businessObjectFileStore;

    @Mock
    private BusinessObject bo;

    @Mock
    private InputStream inputStream;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(businessObjectFileStore.getBusinessObject("org.bonitasoft.poc.model.Car")).thenReturn(bo);
    }

    @Test
    public void shouldGetChildFromPackage_ReturnBusinessObjectFromPackageName() throws Exception {
        doReturn(businessObjectFileStore).when(storeUnderTest).getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        assertThat(storeUnderTest.getChildByQualifiedName("org.bonitasoft.poc.model.Car")).isPresent()
                .isEqualTo(Optional.of(businessObjectFileStore));
    }

    @Test
    public void shouldGetChildFromPackage_ReturnNullIfNoPackageFound() throws Exception {
        doReturn(businessObjectFileStore).when(storeUnderTest).getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        assertThat(storeUnderTest.getChildByQualifiedName("org.bonitasoft.notexists")).isEmpty();
    }

    @Test
    public void import_should_generate_jar() {
        doReturn(businessObjectFileStore).when(storeUnderTest).superDoImportInputStream("bom.xml", inputStream);
        when(businessObjectFileStore.getName()).thenReturn("bom.xml");
        doReturn(Status.OK_STATUS).when(storeUnderTest).generateJar(businessObjectFileStore);

        storeUnderTest.doImportInputStream("bom.xml", inputStream);

        verify(storeUnderTest).generateJar(businessObjectFileStore);
    }
    
    @Test
    public void import_artifact_descriptor_should_not_generate_jar() {
        doReturn(businessObjectFileStore).when(storeUnderTest).superDoImportInputStream(".artifact-descriptor.properties", inputStream);
        when(businessObjectFileStore.getName()).thenReturn(".artifact-descriptor.properties");

        storeUnderTest.doImportInputStream(".artifact-descriptor.properties", inputStream);

        verify(storeUnderTest, never()).generateJar(businessObjectFileStore);
    }

}
