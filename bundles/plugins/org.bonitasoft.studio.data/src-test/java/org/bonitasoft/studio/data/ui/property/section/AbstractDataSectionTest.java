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
package org.bonitasoft.studio.data.ui.property.section;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.businessobject.ui.wizard.AddBusinessObjectDataWizard;
import org.bonitasoft.studio.businessobject.ui.wizard.EditBusinessObjectDataWizard;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
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
    private Shell shell;

    @Mock
    private WizardDialog wizardDialog;

    private BusinessObjectData businessObjectData;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(sectionUnderTest.createAddBusinessObjectDataWizard()).thenReturn(addWizard);

        businessObjectData = ProcessFactory.eINSTANCE.createBusinessObjectData();

        when(sectionUnderTest.createEditBusinessDataWizard(businessObjectData)).thenReturn(editWizard);
        when(sectionUnderTest.getActiveShell()).thenReturn(shell);
        when(sectionUnderTest.createWizardDialog(addWizard,IDialogConstants.FINISH_LABEL)).thenReturn(wizardDialog);
        when(sectionUnderTest.createWizardDialog(editWizard, IDialogConstants.OK_LABEL)).thenReturn(wizardDialog);

        final StructuredSelection selection = new StructuredSelection(businessObjectData);
        when(sectionUnderTest.getStructuredSelection((ISelectionProvider) anyObject())).thenReturn(selection);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldAcceptBusinessObjectData_ReturnAViewerFilterThatOnlyAcceptBusinessData() throws Exception {
        doCallRealMethod().when(sectionUnderTest).acceptBusinessObjectData();

        final ViewerFilter filter = sectionUnderTest.acceptBusinessObjectData();
        assertThat(filter.select(null, null, ProcessFactory.eINSTANCE.createData())).isFalse();
        assertThat(filter.select(null, null, ProcessFactory.eINSTANCE.createBusinessObjectData())).isTrue();
    }

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


    @Test
    public void shouldAddBusinessData_OpenAddBusinessDataWizard() throws Exception {
        doCallRealMethod().when(sectionUnderTest).addBusinessData();
        sectionUnderTest.addBusinessData();
        verify(sectionUnderTest).createAddBusinessObjectDataWizard();
        verify(wizardDialog).open();
    }

    @Test
    public void shouldEditBusinessData_OpenEditBusinessDataWizard() throws Exception {
        doCallRealMethod().when(sectionUnderTest).editBusinessData();
        doCallRealMethod().when(sectionUnderTest).onlyOneElementSelected(any(IStructuredSelection.class));
        sectionUnderTest.editBusinessData();
        verify(sectionUnderTest).createEditBusinessDataWizard(businessObjectData);
        verify(wizardDialog).open();
        verify(sectionUnderTest).refreshBusinessDataTableViewer();
    }

}
