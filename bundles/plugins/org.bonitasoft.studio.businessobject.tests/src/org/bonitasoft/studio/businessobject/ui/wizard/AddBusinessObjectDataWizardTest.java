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
import static org.bonitasoft.studio.model.expression.assertions.ExpressionAssert.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.util.ProcessAdapterFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class AddBusinessObjectDataWizardTest {

    private TransactionalEditingDomain editingDomain;

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Mock
    private BusinessObjectModelRepositoryStore store;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        editingDomain = new TransactionalEditingDomainImpl(new ProcessAdapterFactory());

    }

    @Test
    public void shouldAddPages_AddAddBusinessObjectDataWizardPage() throws Exception {
        final AddBusinessObjectDataWizard wizardUnderTest = new AddBusinessObjectDataWizard(aPool().withName("Test Process").build(), store, editingDomain);

        assertThat(wizardUnderTest.getPages()).isEmpty();

        wizardUnderTest.addPages();

        assertThat(wizardUnderTest.getPages()).hasSize(1);
        assertThat(wizardUnderTest.getPage(BusinessObjectDataWizardPage.class.getName())).isNotNull();
    }

    @Test
    public void shouldCreateAddBusinessObjectDataWizardPage_SetPageTitleAndDescription() throws Exception {
        final AddBusinessObjectDataWizard wizardUnderTest = new AddBusinessObjectDataWizard(aPool().withName("Test Process").build(), store, editingDomain);

        assertThat(wizardUnderTest.getPages()).isEmpty();

        final BusinessObjectDataWizardPage page = wizardUnderTest.createAddBusinessObjectDataWizardPage();

        assertThat(page.getTitle()).isNotNull().isEqualTo(Messages.bind(Messages.addBusinessObjectDataTitle, "Test Process"));
        assertThat(page.getDescription()).isNotNull().isEqualTo(Messages.addBusinessObjectDataDescription);
    }

    @Test
    public void shouldPerformFinish_ReturnTrueIfABusinessObjectData_HasBeenAddedToContainer() throws Exception {
        final AddBusinessObjectDataWizard wizardUnderTest = new AddBusinessObjectDataWizard(aPool().withName("Test Process").build(), store, editingDomain);

        wizardUnderTest.addPages();

        assertThat(wizardUnderTest.performFinish()).isTrue();
        assertThat(((DataAware) wizardUnderTest.getBusinessObjectData().eContainer()).getData()).isNotNull().hasSize(1);
    }

    @Test
    public void add_a_default_value_on_business_data_workingCopy_if_not_present() throws Exception {
        final AddBusinessObjectDataWizard wizardUnderTest = new AddBusinessObjectDataWizard(aPool().withName("Test Process").build(),
                aBusinessData().build(),
                store, editingDomain);

        assertThat(wizardUnderTest.getBusinessObjectData().getDefaultValue())
                .isNotNull()
                .hasInterpreter(ExpressionConstants.GROOVY)
                .hasType(ExpressionConstants.SCRIPT_TYPE)
                .hasReturnType(Object.class.getName());
    }

    @Test
    public void keep_default_value_on_business_data_workingCopy_ift_present() throws Exception {
        final AddBusinessObjectDataWizard wizardUnderTest = new AddBusinessObjectDataWizard(aPool().withName("Test Process").build(),
                aBusinessData().havingDefaultValue(aGroovyScriptExpression().withContent("hello")).build(),
                store, editingDomain);

        assertThat(wizardUnderTest.getBusinessObjectData().getDefaultValue())
                .isNotNull()
                .hasInterpreter(ExpressionConstants.GROOVY)
                .hasType(ExpressionConstants.SCRIPT_TYPE)
                .hasContent("hello");
    }
}
