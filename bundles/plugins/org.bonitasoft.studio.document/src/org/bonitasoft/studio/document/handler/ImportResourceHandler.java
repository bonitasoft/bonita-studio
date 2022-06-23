/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.document.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.inject.Named;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.document.core.repository.DocumentRepositoryStore;
import org.bonitasoft.studio.document.i18n.Messages;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class ImportResourceHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell, RepositoryAccessor repositoryAccessor) {
        String result = new FileDialog(shell).open();
        if (result != null) {
            DocumentRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(DocumentRepositoryStore.class);
            File file = new File(result);
            if (file.exists()) {
                try (FileInputStream inputStream = new FileInputStream(file)) {
                    repositoryStore.importInputStream(file.getName(), inputStream);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                MessageDialog.openError(shell, Messages.fileNotFoundTitle,
                        String.format(Messages.fileNotFound, file.getName()));
            }
        }
    }

}
