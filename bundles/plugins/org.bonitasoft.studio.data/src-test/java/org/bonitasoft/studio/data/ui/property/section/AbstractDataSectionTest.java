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
package org.bonitasoft.studio.data.ui.property.section;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doCallRealMethod;

import org.bonitasoft.studio.businessobject.ui.wizard.AddBusinessObjectDataWizard;
import org.bonitasoft.studio.businessobject.ui.wizard.EditBusinessObjectDataWizard;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractDataSectionTest {

    @Mock
    private AbstractDataSection sectionUnderTest;

    @Mock
    private AddBusinessObjectDataWizard addWizard;

    @Mock
    private EditBusinessObjectDataWizard editWizard;

    @Mock
    private WizardDialog wizardDialog;

    @Test
    public void shouldHideBusinessObjectData_ReturnAViewerFilterThatHideBusinessData() throws Exception {
        doCallRealMethod().when(sectionUnderTest).hideBusinessObjectData();

        final ViewerFilter filter = sectionUnderTest.hideBusinessObjectData();
        assertThat(filter.select(null, null, ProcessFactory.eINSTANCE.createData())).isTrue();
        assertThat(filter.select(null, null, ProcessFactory.eINSTANCE.createBusinessObjectData())).isFalse();
    }

    @Test
    public void shouldSection_HaveAdescription() throws Exception {
        doCallRealMethod().when(sectionUnderTest).getSectionDescription();

        assertThat(sectionUnderTest.getSectionDescription()).isNotNull().isNotEmpty();
    }

    @Test
    public void shouldGetDataFeatureToCheckUniqueID_ContainsDataAwareDataFeature() throws Exception {
        doCallRealMethod().when(sectionUnderTest).getDataFeatureToCheckUniqueID();

        assertThat(sectionUnderTest.getDataFeatureToCheckUniqueID()).isNotNull().isNotEmpty().contains(ProcessPackage.Literals.DATA_AWARE__DATA);
    }

}
