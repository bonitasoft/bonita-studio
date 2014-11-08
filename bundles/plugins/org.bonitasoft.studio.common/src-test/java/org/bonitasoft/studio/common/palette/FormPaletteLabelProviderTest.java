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

import org.bonitasoft.studio.model.form.FormPackage;
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
public class FormPaletteLabelProviderTest {

    private FormPaletteLabelProvider labelProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        labelProvider = new FormPaletteLabelProvider();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_getFormPaletteText_throw_IllegalArgumentException_for_null_argument() throws Exception {
        labelProvider.getFormPaletteText(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_getFormPaletteText_throw_IllegalArgumentException_for_eClass_not_representing_a_widget() throws Exception {
        labelProvider.getFormPaletteText(ProcessPackage.Literals.FLOW_ELEMENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_getFormPaletteDescription_throw_IllegalArgumentException_for_null_argument() throws Exception {
        labelProvider.getFormPaletteDescription(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_getFormPaletteDescription_throw_IllegalArgumentException_for_eClass_not_representing_a_widget() throws Exception {
        labelProvider.getFormPaletteDescription(ProcessPackage.Literals.FLOW_ELEMENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_getFormPaletteText_throw_IllegalArgumentException_for_combo_eClass() throws Exception {
        labelProvider.getFormPaletteText(FormPackage.Literals.COMBO_FORM_FIELD);
    }

    @Test
    public void should_all_widget_classifiers_supported() throws Exception {
        final EClass widgetEclass = FormPackage.Literals.WIDGET;
        for (final EClassifier eClass : FormPackage.eINSTANCE.getEClassifiers()) {
            if (eClass instanceof EClass
                    && widgetEclass.isSuperTypeOf((EClass) eClass)
                    && !((EClass) eClass).isAbstract()
                    //Unused widget
                    && !eClass.equals(FormPackage.Literals.COMBO_FORM_FIELD)
                    && !eClass.equals(FormPackage.Literals.INFO)) {
                assertThat(labelProvider.getFormPaletteText((EClass) eClass)).isNotNull();
                assertThat(labelProvider.getFormPaletteDescription((EClass) eClass)).isNotNull();
            }
        }
    }

}
