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
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
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
public class BusinessObjectBOSArchiveProviderTest {

    @Spy
    private BusinessObjectBOSArchiveProvider providerUnderTest;

    @Mock
    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store;

    @Mock
    private BusinessObjectModelFileStore fileStore;

    private Pool processWithABusinessObjectData;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn(store).when(providerUnderTest).getBusinessObjectDefinitionStore();
        processWithABusinessObjectData = ProcessFactory.eINSTANCE.createPool();
        BusinessObjectData businessData = ProcessFactory.eINSTANCE.createBusinessObjectData();
        businessData.setClassName("org.bonita.Car");
        processWithABusinessObjectData.getData().add(businessData);
    }

    @Test
    public void shouldGetFileStoreForConfiguration_ReturnBusinessObjectFileStore() throws Exception {
        when(store.getChildByQualifiedName("org.bonita.Car")).thenReturn(Optional.ofNullable(fileStore));
        assertThat(providerUnderTest.getFileStoreForConfiguration(processWithABusinessObjectData, null)).isNotEmpty()
                .hasSize(1).containsOnly(fileStore);
    }

    @Test
    public void shouldGetFileStoreForConfiguration_ReturnEmptySet() throws Exception {
        doReturn(Optional.empty()).when(store).getChildByQualifiedName(anyString());
        assertThat(providerUnderTest.getFileStoreForConfiguration(processWithABusinessObjectData, null)).isEmpty();
    }

}
