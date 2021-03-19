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
package org.bonitasoft.studio.businessobject.ui.handler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.ExportBusinessDataModelWizard;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ExportBusinessDataModelHandlerTest {

    @Spy
    private ExportBusinessDataModelHandler handlerUnderTest;

    @Mock
    private Shell shell;

    @Mock
    private BusinessObjectModelRepositoryStore businessStore;

    @Mock
    private ExportBusinessDataModelWizard wizard;

    @Mock
    private CustomWizardDialog wizardDialog;

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Before
    public void setUp() throws Exception {
        Mockito.doReturn(businessStore).when(repositoryAccessor)
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        doReturn(wizardDialog).when(handlerUnderTest).createWizardDialog(shell, Messages.export);
    }

    @Test
    public void shouldExecute_OpenWizardDialogWithExportWizard() throws Exception {
        handlerUnderTest.execute(shell, repositoryAccessor);
        verify(handlerUnderTest).createWizardDialog(shell, Messages.export);
        verify(wizardDialog).open();
    }

}
