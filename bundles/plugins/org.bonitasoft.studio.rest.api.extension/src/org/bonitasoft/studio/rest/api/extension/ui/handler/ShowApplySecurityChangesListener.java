/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.handler;

import static org.bonitasoft.studio.maven.i18n.Messages.errorTitle;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.exception.UpdateException;
import org.bonitasoft.engine.platform.PlatformLoginException;
import org.bonitasoft.engine.platform.PlatformLogoutException;
import org.bonitasoft.engine.session.SessionNotFoundException;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.EditorPart;

import com.google.common.io.Files;

public class ShowApplySecurityChangesListener implements IPropertyListener {

    private static final String HIDE_DIALOG = "hideLogoutToApplySecuChangesDialog";

    private final IPreferenceStore preferenceStore;

    public ShowApplySecurityChangesListener(IPreferenceStore preferenceStore) {
        this.preferenceStore = preferenceStore;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IPropertyListener#propertyChanged(java.lang.Object, int)
     */
    @Override
    public void propertyChanged(final Object source, final int propId) {
        if (propId == IEditorPart.PROP_DIRTY) {
            if (source instanceof ISaveablePart && !((ISaveablePart) source).isDirty()) {
                updateChanges((ISaveablePart) source);
            }
            if (source instanceof ISaveablePart && !((ISaveablePart) source).isDirty()
                    && !preferenceStore.getBoolean(HIDE_DIALOG)) {
                MessageDialogWithPrompt.open(MessageDialog.INFORMATION,
                        Display.getDefault().getActiveShell(),
                        Messages.applySecurityChangesTitle,
                        NLS.bind(Messages.applySecurityChangesMsg, org.bonitasoft.studio.common.Messages.bosProductName),
                        Messages.doNotShowThisDialogAgain,
                        false,
                        preferenceStore,
                        HIDE_DIALOG,
                        SWT.NONE);
            }
        }
    }

    private void updateChanges(ISaveablePart source) {
        final EditorPart editor = (EditorPart) source;
        final FileStoreEditorInput editorInput = (FileStoreEditorInput) editor.getEditorInput();
        final URI uri = editorInput.getURI();
        try {
            final File file = new File(uri.toURL().getFile());
            BOSEngineManager.getInstance().updateTenantConfigResourceContent(
                    BOSEngineManager.CUSTOM_PERMISSIONS_MAPPING_PROPERTIES, Files.toByteArray(file));
        } catch (PlatformLoginException | BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException
                | UpdateException | PlatformLogoutException
                | SessionNotFoundException | IOException e) {
            BonitaStudioLog.error(e);
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), errorTitle, e.getMessage(), e).open();
        }
    }

}
