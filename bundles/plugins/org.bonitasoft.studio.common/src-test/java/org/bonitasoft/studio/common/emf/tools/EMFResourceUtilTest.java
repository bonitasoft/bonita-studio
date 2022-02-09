/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.emf.tools;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class EMFResourceUtilTest {

    private File modelFile;
    private InputStream inputStream;

    @Before
    public void setUp() throws Exception {
        modelFile = new File(
                EMFResourceUtilTest.class.getResource("/EP3.PR.N1001 Šalutiniai gyvūniniai produktai-1.0.proc")
                        .toURI());
    }

    @After
    public void tearDown() throws Exception {
        if (inputStream != null) {
            inputStream.close();
        }
    }


    @Test
    public void shouldGetFeatureValueFromEObjectType_Return_FeatureValue() throws Exception {
        assertThat(EMFResourceUtil.getFeatureValueFromEObjectType(Files.newInputStream(modelFile.toPath()), 
                "process:Pool", 
                ProcessPackage.Literals.ELEMENT__NAME))
                .containsOnly(
                        entry("_5NaOQKXvEeOHX5ykhwuMLg", new String[] { "Poolū" }),
                        entry("_8mseQKXvEeOHX5ykhwuMLg", new String[] { "Pool1" }));
        assertThat(
                EMFResourceUtil.getFeatureValueFromEObjectType(Files.newInputStream(modelFile.toPath()),
                        "process:MainProcess",
                        ProcessPackage.Literals.ELEMENT__NAME))
                                .containsOnly(
                                        entry("_5CIyYKXvEeOHX5ykhwuMLg",
                                                new String[] { "EP3.PR.N1001 Šalutiniai gyvūniniai produktai" }));
    }

}
