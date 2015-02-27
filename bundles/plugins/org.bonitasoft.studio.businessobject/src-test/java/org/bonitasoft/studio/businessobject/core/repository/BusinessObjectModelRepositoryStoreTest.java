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
package org.bonitasoft.studio.businessobject.core.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import org.bonitasoft.engine.bdm.model.BusinessObject;

/**
 * @author Romain Bioteau
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class BusinessObjectModelRepositoryStoreTest {

    @Spy
    private BusinessObjectModelRepositoryStore storeUnderTest;

    @Mock
    private BusinessObjectModelFileStore businessObjectFileStore;

    @Mock
    private BusinessObject bo;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(businessObjectFileStore.getBusinessObject("org.bonitasoft.poc.model.Car")).thenReturn(bo);
    }

    @Test
    public void shouldGetChildFromPackage_ReturnBusinessObjectFromPackageName() throws Exception {
        doReturn(Collections.singletonList(businessObjectFileStore)).when(storeUnderTest).getChildren();
        assertThat(storeUnderTest.getChildByQualifiedName("org.bonitasoft.poc.model.Car")).isNotNull().isEqualTo(businessObjectFileStore);
    }

    @Test
    public void shouldGetChildFromPackage_ReturnNullIfNoPackageFound() throws Exception {
        doReturn(Collections.singletonList(businessObjectFileStore)).when(storeUnderTest).getChildren();
        assertThat(storeUnderTest.getChildByQualifiedName("org.bonitasoft.notexists")).isNull();
    }
}
