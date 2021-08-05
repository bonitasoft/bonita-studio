/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.handler;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EditRestAPIExtensionPermissionsMappingHandlerTest {

    @Mock
    private IWorkbenchPage activePage;

    @Mock
    private IPreferenceStore preferenceStore;
    @Mock
    private IEditorPart editorPart;

    @Test
    public void should_open_custom_permission_file_in_properties_file_editor_when_executing_the_handler() throws Exception {
        final EditRestAPIExtensionPermissionsMappingHandler handler = newHandler();
        final FileStoreEditorInput input = mock(FileStoreEditorInput.class);
        doReturn(input).when(handler).loadInputFormDatabase();

        handler.execute(activePage);

        verify(activePage).openEditor(input,"org.eclipse.jdt.ui.PropertiesFileEditor");
    }

    private EditRestAPIExtensionPermissionsMappingHandler newHandler() throws Exception {
        when(activePage.openEditor(any(IEditorInput.class), anyString())).thenReturn(editorPart);
        final EditRestAPIExtensionPermissionsMappingHandler handler = spy(
                new EditRestAPIExtensionPermissionsMappingHandler());
        doReturn(preferenceStore).when(handler).getPreferenceStore();
        return handler;
    }
}
