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

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class EMFResourceUtilTest {

    private File modelFile;
    private EMFResourceUtil eObjectIDUtil;

    @Before
    public void setUp() throws Exception {
        modelFile = new File(
                EMFResourceUtilTest.class.getResource("/EP3.PR.N1001 Šalutiniai gyvūniniai produktai-1.0.proc").toURI());
        eObjectIDUtil = new EMFResourceUtil(modelFile);
    }

    @Test(expected = AssertionFailedException.class)
    public void shouldConstructor_ThrowAssertionFailedException() throws Exception {
        new EMFResourceUtil(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldConstructor_ThrowIllegalArgumentException() throws Exception {
        new EMFResourceUtil(new File("unknown.proc"));
    }

    @Test
    public void shouldGetFeatureValuesFromEObjectId_Return_FeatureValue_ForSingleFeatureName() throws Exception {
        assertThat(eObjectIDUtil.getFeatureValuesFromEObjectId("_5NaOQKXvEeOHX5ykhwuMLg", "process:Pool",
                new String[] { "name" })).containsExactly("Poolū");
        assertThat(eObjectIDUtil.getFeatureValuesFromEObjectId("_8mseQKXvEeOHX5ykhwuMLg", "process:Pool",
                new String[] { "name" })).containsExactly("Pool1");
        assertThat(eObjectIDUtil.getFeatureValuesFromEObjectId("_5yAwIKXvEeOHX5ykhwuMLg", "process:Pool",
                new String[] { "name" })).containsExactly("Start1");
        assertThat(eObjectIDUtil.getFeatureValuesFromEObjectId("_5CIyaqXvEeOHX5ykhwuMLg", "notation:Diagram",
                new String[] { "measurementUnit" }))
                .containsExactly("Pixel");
    }

    @Test
    public void shouldGetFeatureValuesFromEObjectId_Return_FeatureValue_ForSingleFeature() throws Exception {
        assertThat(eObjectIDUtil.getFeatureValuesFromEObjectId("_5NaOQKXvEeOHX5ykhwuMLg",
                ProcessPackage.Literals.ELEMENT__NAME)).containsExactly("Poolū");
        assertThat(eObjectIDUtil.getFeatureValuesFromEObjectId("_5CIyaqXvEeOHX5ykhwuMLg",
                NotationPackage.Literals.DIAGRAM__MEASUREMENT_UNIT)).containsExactly("Pixel");
    }

    @Test
    public void shouldGetFeatureValuesFromEObjectId_Return_DefaultFeatureValue_ForSingleFeature() throws Exception {
        assertThat(eObjectIDUtil.getFeatureValuesFromEObjectId("_5NaOQKXvEeOHX5ykhwuMLg",
                ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION)).containsExactly("1.0");
    }

    @Test
    public void shouldGetFeatureValuesFromEObjectId_Return_FeatureValues_ForMultipleFeatureNames() throws Exception {
        assertThat(eObjectIDUtil.getFeatureValuesFromEObjectId("_5NaOQKXvEeOHX5ykhwuMLg", "process:Pool",
                new String[] { "name", "basedOnLookAndFeel" }))
                .containsExactly("Poolū", "Default Application");
    }

    @Test
    public void shouldGetFeatureValuesFromEObjectId_Return_DefaultFeatureValues_ForMultipleFeatures() throws Exception {
        assertThat(eObjectIDUtil.getFeatureValuesFromEObjectId("_5NaOQKXvEeOHX5ykhwuMLg",
                ProcessPackage.Literals.ELEMENT__NAME, ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION))
                        .containsExactly("Poolū", "1.0");
    }

    @Test
    public void shouldGetFeatureValueFromEObjectType_Return_FeatureValue() throws Exception {
        assertThat(eObjectIDUtil.getFeatureValueFromEObjectType("process:Pool", ProcessPackage.Literals.ELEMENT__NAME))
                .containsOnly(
                        entry("_5NaOQKXvEeOHX5ykhwuMLg", new String[] { "Poolū" }),
                        entry("_8mseQKXvEeOHX5ykhwuMLg", new String[] { "Pool1" }));
        assertThat(
                eObjectIDUtil.getFeatureValueFromEObjectType("process:MainProcess", ProcessPackage.Literals.ELEMENT__NAME))
                        .containsOnly(
                                entry("_5CIyYKXvEeOHX5ykhwuMLg",
                                        new String[] { "EP3.PR.N1001 Šalutiniai gyvūniniai produktai" }));
    }

}
