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
package org.bonitasoft.studio.businessobject.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.IWizardContainer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class EditBusinessObjectDataWizardTest {

    private EditBusinessObjectDataWizard wizardUnderTest;

    @Mock
    private BusinessObjectDataWizardPage wizardPage;

    private AbstractProcess container;

    private TransactionalEditingDomain editingDomain;

    @Mock
    private IWizardContainer wizardContainer;

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final BusinessObjectModelRepositoryStore store = mock(BusinessObjectModelRepositoryStore.class);
        final MainProcess diagram = ProcessFactory.eINSTANCE.createMainProcess();
        container = ProcessFactory.eINSTANCE.createPool();
        container.setName("Test Process");
        diagram.getElements().add(container);
        final DataType bType = ProcessFactory.eINSTANCE.createBusinessObjectType();
        bType.setName(DataTypeLabels.businessObjectType);
        diagram.getDatatypes().add(bType);
        final BusinessObjectData data = ProcessFactory.eINSTANCE.createBusinessObjectData();
        data.setName("testData");
        data.setDataType(bType);
        data.setBusinessDataRepositoryId("fake");
        data.setEClassName("Entity");
        data.setClassName("org.bonitasoft.test.Entity");
        container.getData().add(data);
        final BusinessObjectData data2 = ProcessFactory.eINSTANCE.createBusinessObjectData();
        data2.setName("testData2");
        data2.setDataType(bType);
        data2.setBusinessDataRepositoryId("fake");
        data2.setEClassName("Entity2");
        data2.setClassName("org.bonitasoft.test.Entity2");
        container.getData().add(data2);
        final Resource r = new ResourceImpl(URI.createFileURI("test.proc"));
        r.getContents().clear();
        r.getContents().add(diagram);
        final ResourceSet rSet = new ResourceSetImpl();
        rSet.getResources().add(r);
        editingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(rSet);
        wizardUnderTest = new EditBusinessObjectDataWizard(data, store, editingDomain);
        wizardUnderTest.setContainer(wizardContainer);
    }

    @Test
    public void shouldAddPages_CreateEditBusinessObjectDataWizardPage() throws Exception {
        assertThat(wizardUnderTest.getPages()).isEmpty();
        wizardUnderTest.addPages();
        assertThat(wizardUnderTest.getPages()).hasSize(1);
        assertThat(wizardUnderTest.getPage(BusinessObjectDataWizardPage.class.getName())).isNotNull();
    }

    @Test
    public void shouldCreateBusinessObjectDataWizardPage_SetPageTitleAndDescription() throws Exception {
        assertThat(wizardUnderTest.getPages()).isEmpty();
        final BusinessObjectDataWizardPage page = wizardUnderTest.createEditBusinessObjectDataWizardPage();
        assertThat(page.getTitle()).isNotNull().isEqualTo(Messages.editBusinessObjectDataTitle);
        assertThat(page.getDescription()).isNotNull().isEqualTo(Messages.editBusinessObjectDataDescription);
    }

    @Test
    public void shouldPerformFinish_ReturnTrueIfABusinessObjectData_HasBeenUpdated() throws Exception {
        final Data originalData = container.getData().get(0);
        wizardUnderTest.addPages();
        assertThat(wizardUnderTest.performFinish()).isTrue();
        assertThat(container.getData()).isNotNull().hasSize(2).contains(originalData);
    }

}
