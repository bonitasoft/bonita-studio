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
package org.bonitasoft.studio.dependencies.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class DependencyFileStoreTest {

    private DependencyRepositoryStore store;
    private DependencyFileStore fileStoreUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        store = spy(new DependencyRepositoryStore());
        final Map<String, String> deps = new HashMap<String, String>();
        deps.put("slf4j-api", "1.6.1");
        deps.put("catalina", "");
        deps.put("bonita-common", "6.2.0-SNAPSHOT");
        doReturn(deps).when(store).getRuntimeDependencies();
        fileStoreUnderTest = spy(new DependencyFileStore("org.apacahe.commons", store));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldExistsInRuntimeContainer_ReturnTrue() throws Exception {
        doReturn("slf4j-api-1.6.1.jar").when(fileStoreUnderTest).getName();
        assertThat(fileStoreUnderTest.existsInRuntimeContainer()).isTrue();
        doReturn("catalina.jar").when(fileStoreUnderTest).getName();
        assertThat(fileStoreUnderTest.existsInRuntimeContainer()).isTrue();
    }

    @Test
    public void shouldExistsInRuntimeContainer_ReturnFalse() throws Exception {
        doReturn("slf4j-api-1.6.2.jar").when(fileStoreUnderTest).getName();
        assertThat(fileStoreUnderTest.existsInRuntimeContainer()).isFalse();
        doReturn("bonita-common-6.2.1-SNAPSHOT.jar").when(fileStoreUnderTest).getName();
        assertThat(fileStoreUnderTest.existsInRuntimeContainer()).isFalse();
    }

    @Test
    public void shouldExistsInRuntimeContainerWithAnotherVersion_ReturnFalse() throws Exception {
        doReturn("slf4j-api-1.6.1.jar").when(fileStoreUnderTest).getName();
        assertThat(fileStoreUnderTest.existsInRuntimeContainerWithAnotherVersion()).isFalse();
        doReturn("bonita-common-sp-6.2.0-SNAPSHOT.jar").when(fileStoreUnderTest).getName();
        assertThat(fileStoreUnderTest.existsInRuntimeContainerWithAnotherVersion()).isFalse();
    }

    @Test
    public void shouldExistsInRuntimeContainerWithAnotherVersion_ReturnTrue() throws Exception {
        doReturn("slf4j-api-1.4.5.jar").when(fileStoreUnderTest).getName();
        assertThat(fileStoreUnderTest.existsInRuntimeContainerWithAnotherVersion()).isTrue();
        doReturn("bonita-common-6.2.1.jar").when(fileStoreUnderTest).getName();
        assertThat(fileStoreUnderTest.existsInRuntimeContainerWithAnotherVersion()).isTrue();
    }
}
