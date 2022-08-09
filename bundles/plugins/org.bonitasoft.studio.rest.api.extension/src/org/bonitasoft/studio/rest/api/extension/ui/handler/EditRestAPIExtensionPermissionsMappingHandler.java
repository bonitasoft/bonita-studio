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
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.InvalidPlatformCredentialsException;
import org.bonitasoft.engine.platform.PlatformLoginException;
import org.bonitasoft.engine.platform.PlatformLogoutException;
import org.bonitasoft.engine.session.SessionNotFoundException;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.m2e.editor.pom.MavenPomEditor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.FileStoreEditorInput;

import com.google.common.io.Files;

public class EditRestAPIExtensionPermissionsMappingHandler {

    private FileStoreEditorInput input;

    @Execute
    public void execute(final IWorkbenchPage activePage) {
        try {
            input = loadInputFormDatabase();
            final IEditorPart editor = activePage.openEditor(input,"org.eclipse.jdt.ui.PropertiesFileEditor");
            editor.addPropertyListener(new ShowApplySecurityChangesListener(getPreferenceStore()));
        } catch (final CoreException | InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), errorTitle, e.getMessage(), e).open();
        }
    }

    protected FileStoreEditorInput loadInputFormDatabase() throws InvocationTargetException, InterruptedException {
        PlatformUI.getWorkbench().getProgressService().run(true, false, monitor -> {
            try {
                input = getFile(monitor);
            } catch (PlatformLoginException | BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException
                    | PlatformLogoutException | SessionNotFoundException e) {
                throw new InvocationTargetException(e);
            }
        });
        return input;
    }

    protected FileStoreEditorInput getFile(IProgressMonitor monitor)
            throws InvalidPlatformCredentialsException, PlatformLoginException, BonitaHomeNotSetException,
            ServerAPIException, UnknownAPITypeException, PlatformLogoutException, SessionNotFoundException {
        File tmp = null;
        try {
            tmp = File.createTempFile(BOSEngineManager.CUSTOM_PERMISSIONS_MAPPING_PROPERTIES, null);
            final byte[] content = BOSEngineManager.getInstance(monitor)
                    .getTenantConfigResourceContent(BOSEngineManager.CUSTOM_PERMISSIONS_MAPPING_PROPERTIES, monitor);
            if (tmp.exists()) {
                tmp.delete();
            }
            tmp.createNewFile();
            Files.write(content, tmp);
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
        return new FileStoreEditorInput(EFS.getLocalFileSystem().fromLocalFile(tmp)) {

            /*
             * (non-Javadoc)
             * @see org.eclipse.ui.ide.FileStoreEditorInput#getName()
             */
            @Override
            public String getName() {
                return BOSEngineManager.CUSTOM_PERMISSIONS_MAPPING_PROPERTIES;
            }
        };
    }

    protected IPreferenceStore getPreferenceStore() {
        return RestAPIExtensionActivator.getDefault().getPreferenceStore();
    }

}
