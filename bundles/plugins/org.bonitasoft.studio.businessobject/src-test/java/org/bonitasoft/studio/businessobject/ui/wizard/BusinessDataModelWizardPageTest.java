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
package org.bonitasoft.studio.businessobject.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.core.difflog.NullDiffLogger;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
public class BusinessDataModelWizardPageTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Mock
    private BusinessObjectModel businessObjectModel;
    private BusinessDataModelWizardPage wizardPage;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        wizardPage = spy(new BusinessDataModelWizardPage(businessObjectModel, new NullDiffLogger()));
    }

    @Test
    public void should_validatePackage_returns_an_error_status_for_package_with_sp_reserved_prefix() throws Exception {
        doReturn(Status.OK_STATUS).when(wizardPage).javaPackageValidation(any(Object.class));
        IStatus status = wizardPage.validatePackageName("com.bonitasoft");

        assertThat(status.getSeverity()).isEqualTo(IStatus.ERROR);

        status = wizardPage.validatePackageName("com.bonitasoft.model");

        assertThat(status.getSeverity()).isEqualTo(IStatus.ERROR);
    }

    @Test
    public void should_validatePackage_returns_an_error_status_for_empty_package() throws Exception {
        doReturn(Status.OK_STATUS).when(wizardPage).javaPackageValidation(any(Object.class));
        final IStatus status = wizardPage.validatePackageName("");

        assertThat(status.getSeverity()).isEqualTo(IStatus.ERROR);
    }

    @Test
    public void should_validatePackage_returns_an_error_status_for_null_package() throws Exception {
        doReturn(Status.OK_STATUS).when(wizardPage).javaPackageValidation(any(Object.class));
        final IStatus status = wizardPage.validatePackageName(null);

        assertThat(status.getSeverity()).isEqualTo(IStatus.ERROR);
    }

    @Test
    public void should_validatePackage_returns_an_error_status_for_package_with_bos_reserved_prefix() throws Exception {
        doReturn(Status.OK_STATUS).when(wizardPage).javaPackageValidation(any(Object.class));
        final IStatus status = wizardPage.validatePackageName("org.bonitasoft.model");

        assertThat(status.getSeverity()).isEqualTo(IStatus.ERROR);
    }
}
