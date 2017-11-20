/**
 * Copyright (C) 2017 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.la.application.handler;

import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplicationContainer;

import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.handler.NewFileHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class NewApplicationHandler extends NewFileHandler {

    public static final String XML_EXTENSION = ".xml";
    public static final String DEFAULT_FILE_NAME = "applicationDescriptorFile";
    public static final String DO_NOT_SHOW_HELP_MESSAGE_DIALOG = "DO_NOT_SHOW_HELP_MESSAGE_DIALOG";

    @Override
    protected void openHelpDialog(Shell activeShell) {
        IPreferenceStore preferenceStore = LivingApplicationPlugin.getDefault().getPreferenceStore();
        if (!preferenceStore.getBoolean(DO_NOT_SHOW_HELP_MESSAGE_DIALOG)) {
            MessageDialogWithPrompt.openWithDetails(MessageDialog.INFORMATION,
                    activeShell,
                    Messages.newApplicationDescriptorTitle,
                    Messages.applicationInfo,
                    org.bonitasoft.studio.ui.i18n.Messages.doNotShowMeAgain,
                    Messages.applicationDetails,
                    false,
                    preferenceStore,
                    DO_NOT_SHOW_HELP_MESSAGE_DIALOG,
                    SWT.NONE);
        }
    }

    @Override
    protected IRepositoryFileStore createFileStore(RepositoryAccessor repositoryAccessor, String fileName) {
        final ApplicationRepositoryStore repositoryStore = repositoryAccessor
                .getRepositoryStore(ApplicationRepositoryStore.class);

        final String fileNameTrimed = fileName.endsWith(XML_EXTENSION)
                ? fileName.substring(0, fileName.length() - XML_EXTENSION.length()) : fileName;
        final ApplicationFileStore fileStore = repositoryStore
                .createRepositoryFileStore(String.format("%s%s", fileNameTrimed, XML_EXTENSION));
        final ApplicationNodeContainer nodeContainer = newApplicationContainer().create();
        fileStore.save(nodeContainer);
        return fileStore;
    }

    @Override
    protected IRepositoryStore<? extends IRepositoryFileStore> getRepositoryStore(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class);
    }

    @Override
    protected String getDefaultFileName() {
        return DEFAULT_FILE_NAME;
    }

}
