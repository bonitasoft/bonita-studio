/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.BusinessObjectType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Composite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.bonitasoft.engine.bdm.model.BusinessObject;

/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BusinessObjectDataWizardPageTest extends AbstractSWTTestCase {

    private BusinessObjectDataWizardPage wizardPageUnderTest;

    @Mock
    private Composite mainComposite;

    @Mock
    BusinessObjectModelFileStore fileStore;

    private Composite parent;

    @Mock
    private IWizard wizardWithContainer;

    @Mock
    private IWizardContainer wizardContainer;


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        parent = createDisplayAndRealm();
        final BusinessObjectModelRepositoryStore store = mock(BusinessObjectModelRepositoryStore.class);
        when(store.getChildren()).thenReturn(Collections.singletonList(fileStore));
        final BusinessObjectType businessObjectType = ProcessFactory.eINSTANCE.createBusinessObjectType();
        businessObjectType.setName(DataTypeLabels.businessObjectType);
        final BusinessObjectData data = ProcessFactory.eINSTANCE.createBusinessObjectData();
        data.setDataType(businessObjectType);
        final Set<String> existingNames = new HashSet<String>();
        existingNames.add("data1");
        wizardPageUnderTest = new BusinessObjectDataWizardPage(data, store, existingNames);
        wizardPageUnderTest.setWizard(wizardWithContainer);
        when(wizardWithContainer.getContainer()).thenReturn(wizardContainer);
        when(wizardContainer.getShell()).thenReturn(parent.getShell());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }

    @Test
    public void shouldCreateControl_SetWizardPageControl() throws Exception {
        wizardPageUnderTest.createControl(parent);
        assertThat(wizardPageUnderTest.getControl()).isNotNull();
    }

    @Test
    public void shouldNewBusinessObjectData_HaveABusinessObjectType() throws Exception {
        assertThat(wizardPageUnderTest.getBusinessObjectData().getDataType()).isNotNull().isInstanceOf(BusinessObjectType.class);
    }

    @Test
    public void shouldValidateInputName_ReturnOKStatus() throws Exception {
        assertThat(wizardPageUnderTest.validateInputName("currentLeaveRequest").isOK()).isTrue();
    }

    @Test
    public void shouldValidateInputName_ReturnKOStatus() throws Exception {
        assertThat(wizardPageUnderTest.validateInputName("").isOK()).isFalse();
        assertThat(wizardPageUnderTest.validateInputName("data1").isOK()).overridingErrorMessage("data1 already exists and validation should not be OK")
                .isFalse();
        assertThat(wizardPageUnderTest.validateInputName("data1fsdjkfhjkdsfhsdkjfhsdjkfhsdjkfhdsjkfhdsjkhfjdkshfkjsdfhsdfsdfhezjkhze").isOK())
                .overridingErrorMessage("Maximum character allowed is 50")
                .isFalse();
        assertThat(wizardPageUnderTest.validateInputName("my current leave request").isOK()).overridingErrorMessage("Only valid Java identifier are allowed")
                .isFalse();
    }

    @Test
    public void shoudlGetAllBusinessObjects_ReturnAllAvailableEntities() throws Exception {
        final BusinessObject bo1 = new BusinessObject();
        final BusinessObject bo2 = new BusinessObject();
        final List<BusinessObject> entities = new ArrayList<BusinessObject>();
        entities.add(bo1);
        entities.add(bo2);
        when(fileStore.getBusinessObjects()).thenReturn(entities);
        assertThat(wizardPageUnderTest.getAllBusinessObjects()).containsOnly(bo1, bo2);
    }

}
