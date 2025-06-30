/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.extensions.handler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.ui.wizard.NewRestAPIExtensionWizard;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.junit.Test;

public class NewRestAPIExtensionHandlerTest {

    @Test
    public void should_open_a_wizard_dialog_with_new_rest_api_wizard() throws Exception {
        final NewExtensionHandler handler = spy(new NewExtensionHandler());
        final NewRestAPIExtensionWizard wizard = mock(NewRestAPIExtensionWizard.class);
        doReturn(wizard).when(handler).newWizard(eq(ArtifactType.REST_API), any());
        final RestAPIExtensionFileStore fileStore = mock(RestAPIExtensionFileStore.class);
        doReturn(fileStore).when(wizard).getNewFileStore();
        final WizardDialog dialog = mock(WizardDialog.class);
        when(dialog.open()).thenReturn(IDialogConstants.OK_ID);
        doReturn(dialog).when(handler).newWizardDialog(wizard, Messages.create);
        handler.execute(ArtifactType.REST_API.name());

        verify(dialog).open();
        verify(fileStore).open();
    }
}
