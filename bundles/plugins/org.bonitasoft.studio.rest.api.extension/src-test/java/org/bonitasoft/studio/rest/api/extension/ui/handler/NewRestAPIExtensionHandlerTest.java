/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.handler;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.rest.api.extension.core.RestAPIAddressResolver;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.ui.wizard.NewRestAPIExtensionWizard;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class NewRestAPIExtensionHandlerTest {

    @Test
    public void should_open_a_wizard_dialog_with_new_rest_api_wizard() throws Exception {
        final RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        final NewRestAPIExtensionHandler handler = spy(new NewRestAPIExtensionHandler());
        final NewRestAPIExtensionWizard wizard = mock(NewRestAPIExtensionWizard.class);
        final WidgetFactory widgetFactory = mock(WidgetFactory.class);
        final IWorkspace workspace = mock(IWorkspace.class);
        doReturn(wizard).when(handler).newWizard(eq(repositoryAccessor), eq(widgetFactory), eq(workspace),
                notNull(RestAPIAddressResolver.class));
        final RestAPIExtensionFileStore fileStore = mock(RestAPIExtensionFileStore.class);
        when(wizard.getNewFileStore()).thenReturn(fileStore);
        final WizardDialog dialog = mock(WizardDialog.class);
        when(dialog.open()).thenReturn(IDialogConstants.OK_ID);
        doReturn(dialog).when(handler).newWizardDialog(wizard, Messages.create);
        final RestAPIAddressResolver addressResolver = mock(RestAPIAddressResolver.class);
        Shell shell = mock(Shell.class);
        handler.execute(shell, repositoryAccessor, widgetFactory, workspace, addressResolver);

        verify(dialog).open();
        verify(fileStore).open();
    }
}
