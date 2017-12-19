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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.ui.wizard.ManageBusinessDataModelWizard;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.jface.dialogs.IDialogConstants;
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
public class ManageBusinessObjectHandlerTest {

    @Spy
    private ManageBusinessObjectHandler handlerUnderTest;

    @Mock
    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessStore;

    @Mock
    private ManageBusinessDataModelWizard wizard;

    @Mock
    private CustomWizardDialog wizardDialog;

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private Shell shell;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        Mockito.doReturn(businessStore).when(repositoryAccessor)
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        Mockito.doReturn(wizard).when(handlerUnderTest).createWizard();
        when(wizardDialog.open()).thenReturn(IDialogConstants.OK_ID);
        Mockito.doReturn(wizardDialog).when(handlerUnderTest).createWizardDialog(wizard, IDialogConstants.FINISH_LABEL,
                shell);
        doNothing().when(handlerUnderTest).openSuccessDialog(shell);
    }

    @Test
    public void shouldExecute_SaveArtifactWithEditorContent() throws Exception {
        assertThat(handlerUnderTest.execute(repositoryAccessor, shell)).isEqualTo(IDialogConstants.OK_ID);
        verify(wizardDialog).open();
        verify(handlerUnderTest).openSuccessDialog(shell);
    }

}
