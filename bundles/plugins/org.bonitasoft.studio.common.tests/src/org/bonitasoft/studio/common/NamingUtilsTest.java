/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class NamingUtilsTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link org.bonitasoft.studio.common.NamingUtils#getSimpleName(java.lang.String)}.
     */
    @Test
    public void shouldGetSimpleName_ReturnsValidSimpleNames() throws Exception {
        assertThat(NamingUtils.getSimpleName("org.bonita.Toto")).isEqualTo("Toto");
        assertThat(NamingUtils.getSimpleName("Toto")).isEqualTo("Toto");
    }
    
    @Test
    public void shouldGetPackageName_ReturnsValidPackageNames() throws Exception {
        assertThat(NamingUtils.getPackageName("org.bonita.Toto")).isEqualTo("org.bonita");
        assertThat(NamingUtils.getPackageName("Toto")).isEqualTo("");
    }

}
