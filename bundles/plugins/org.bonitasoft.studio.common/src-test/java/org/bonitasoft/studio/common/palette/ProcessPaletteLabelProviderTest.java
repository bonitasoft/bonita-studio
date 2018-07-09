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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.palette;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class ProcessPaletteLabelProviderTest {

    private ProcessPaletteLabelProvider labelProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        labelProvider = new ProcessPaletteLabelProvider();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_getProcessPaletteText_throw_IllegalArgumentException_for_null_argument() throws Exception {
        labelProvider.getProcessPaletteText(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_getProcessPaletteDescription_throw_IllegalArgumentException_for_null_argument() throws Exception {
        labelProvider.getProcessPaletteDescription(null);
    }

    @Test
    public void should_getProcessPaletteText_return_eClass_name_if_eClass_not_supported() throws Exception {
        assertThat(labelProvider.getProcessPaletteText(ProcessPackage.Literals.MESSAGE_FLOW)).isEqualTo(ProcessPackage.Literals.MESSAGE_FLOW.getName());
    }

    @Test
    public void should_all_flow_element_classifiers_supported() throws Exception {
        final EClass flowElementEClass = ProcessPackage.Literals.FLOW_ELEMENT;
        for (final EClassifier eClass : ProcessPackage.eINSTANCE.getEClassifiers()) {
            if (eClass instanceof EClass
                    && flowElementEClass.isSuperTypeOf((EClass) eClass)
                    && !((EClass) eClass).isAbstract()
                    && !eClass.equals(flowElementEClass)
                    //Should below eClasses be abstract ?
                    && !eClass.equals(ProcessPackage.Literals.GATEWAY)
                    && !eClass.equals(ProcessPackage.Literals.MESSAGE_EVENT)
                    && !eClass.equals(ProcessPackage.Literals.THROW_MESSAGE_EVENT)
                    && !eClass.equals(ProcessPackage.Literals.LINK_EVENT)) {
                assertThat(labelProvider.getProcessPaletteText((EClass) eClass)).isNotNull();
                assertThat(labelProvider.getProcessPaletteDescription((EClass) eClass)).isNotNull();
            }
        }
    }
}
