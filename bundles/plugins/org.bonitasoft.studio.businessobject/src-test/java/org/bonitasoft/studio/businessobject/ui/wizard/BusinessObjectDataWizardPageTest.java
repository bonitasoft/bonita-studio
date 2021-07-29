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

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.builders.BusinessObjectDataTypeBuilder;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Text;
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
public class BusinessObjectDataWizardPageTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    private BusinessObjectDataWizardPage wizardPageUnderTest;

    @Mock
    private BusinessObjectModelFileStore fileStore;

    @Mock
    private IWizard wizardWithContainer;

    @Mock
    private IWizardContainer wizardContainer;

    @Mock
    private HintImageProvider hintImageProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final BusinessObjectModelRepositoryStore store = mock(BusinessObjectModelRepositoryStore.class);
        when(store.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true)).thenReturn(fileStore);
        final BusinessObjectData data = aBusinessData()
                .havingDataType(BusinessObjectDataTypeBuilder.aBusinessObjectDataType()).build();
        final Pool pool = aPool().build();
        pool.getData().add(data);
        wizardPageUnderTest = new BusinessObjectDataWizardPage(pool, data, store, newHashSet("data1"), hintImageProvider);
        doReturn(realmWithDisplay.createImage()).when(hintImageProvider).getHintImage();
        wizardPageUnderTest.setWizard(wizardWithContainer);
        when(wizardWithContainer.getContainer()).thenReturn(wizardContainer);
        when(wizardContainer.getShell()).thenReturn(realmWithDisplay.getShell());
    }

    @Test
    public void shouldCreateControl_SetWizardPageControl() throws Exception {
        wizardPageUnderTest.createControl(realmWithDisplay.createComposite());
        assertThat(wizardPageUnderTest.getControl()).isNotNull();
    }

    @Test
    public void should_accept_valid_data_name() throws Exception {
        final EMFDataBindingContext ctx = new EMFDataBindingContext();
        final Text nameText = wizardPageUnderTest.createNameControl(realmWithDisplay.createComposite(), ctx);

        nameText.setText("currentLeaveRequest");

        assertThat(validationStatuses(ctx))
                .extracting("validationStatus.value.severity").containsOnly(IStatus.OK);
    }

    @Test
    public void should_reject_empty_data_name() throws Exception {
        final EMFDataBindingContext ctx = new EMFDataBindingContext();
        final Text nameText = wizardPageUnderTest.createNameControl(realmWithDisplay.createComposite(), ctx);

        nameText.setText("");

        assertThat(validationStatuses(ctx))
                .extracting("validationStatus.value.severity").contains(IStatus.ERROR);
    }

    @Test
    public void should_reject_duplicated_data_name() throws Exception {
        final EMFDataBindingContext ctx = new EMFDataBindingContext();
        final Text nameText = wizardPageUnderTest.createNameControl(realmWithDisplay.createComposite(), ctx);

        nameText.setText("data1");

        assertThat(validationStatuses(ctx))
                .extracting("validationStatus.value.severity").contains(IStatus.ERROR);
    }

    @Test
    public void should_reject_data_name_longer_than_50_characters() throws Exception {
        final EMFDataBindingContext ctx = new EMFDataBindingContext();
        final Text nameText = wizardPageUnderTest.createNameControl(realmWithDisplay.createComposite(), ctx);

        nameText.setText("aBusinessDataNameWithWayToManyCharactersMakingTheValidationFail");

        assertThat(validationStatuses(ctx))
                .extracting("validationStatus.value.severity").contains(IStatus.ERROR);
    }

    @Test
    public void should_reject_data_name_not_java_compliant() throws Exception {
        final EMFDataBindingContext ctx = new EMFDataBindingContext();
        final Text nameText = wizardPageUnderTest.createNameControl(realmWithDisplay.createComposite(), ctx);

        nameText.setText("my current leave request");

        assertThat(validationStatuses(ctx))
                .extracting("validationStatus.value.severity").contains(IStatus.ERROR);
    }

    @SuppressWarnings("unchecked")
    private ArrayList<ValidationStatusProvider> validationStatuses(final EMFDataBindingContext ctx) {
        return newArrayList((Iterator<ValidationStatusProvider>) ctx.getValidationStatusProviders().iterator());
    }

    @Test
    public void shoudlGetAllBusinessObjects_ReturnAllAvailableEntities() throws Exception {
        final BusinessObject bo1 = new BusinessObject();
        final BusinessObject bo2 = new BusinessObject();
        when(fileStore.getBusinessObjects()).thenReturn(newArrayList(bo1, bo2));
        assertThat(wizardPageUnderTest.getAllBusinessObjects()).containsOnly(bo1, bo2);
    }

}
