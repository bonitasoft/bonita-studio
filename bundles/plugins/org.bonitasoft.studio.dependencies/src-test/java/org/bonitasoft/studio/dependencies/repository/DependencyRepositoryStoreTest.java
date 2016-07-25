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
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.File;
import java.net.URL;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class DependencyRepositoryStoreTest {

    private DependencyRepositoryStore storeUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        storeUnderTest = spy(new DependencyRepositoryStore());
        final URL resource = DependencyRepositoryStore.class.getResource("/tomcat");
        final File fakeTomcat = new File(resource.getFile());
        doReturn(fakeTomcat).when(storeUnderTest).getTomcatRootFile();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldGetRuntimeDependencies_ReturnAMapOfLibNameAndVersion() throws Exception {
        final Map<String, String> dependencies = storeUnderTest.getRuntimeDependencies();
        assertThat(dependencies).isNotNull().contains(entry("h2", "1.3.170"),
                entry("catalina", ""),
                entry("asm", "3.1"),
                entry("bonita-common-sp", "6.2.0-SNAPSHOT"),
                entry("bonita-font-signika", ""),
                entry("bdm-client-pojo", ""));

    }

    @Test
    public void shouldGetLibname_ReturnLibNameFromJarFilename() throws Exception {
        assertThat(storeUnderTest.getLibName("mylib.jar")).isEqualTo("mylib");
        assertThat(storeUnderTest.getLibName("mylib-4.5")).isEqualTo("mylib");
        assertThat(storeUnderTest.getLibName("mylib-4.5.2.5-SNAPSHOT")).isEqualTo("mylib");
    }

    @Test
    public void shouldGetLibversion_ReturnLibVersionFromJarFilename() throws Exception {
        assertThat(storeUnderTest.getLibVersion("mylib")).isEqualTo("");
        assertThat(storeUnderTest.getLibVersion("mylib-4.5")).isEqualTo("4.5");
        assertThat(storeUnderTest.getLibVersion("mylib-4.5.2.5-SNAPSHOT")).isEqualTo("4.5.2.5-SNAPSHOT");
    }

}
