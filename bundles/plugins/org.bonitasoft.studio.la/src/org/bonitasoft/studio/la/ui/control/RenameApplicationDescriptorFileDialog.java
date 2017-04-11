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
package org.bonitasoft.studio.la.ui.control;

import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.ui.validator.ApplicationDescriptorFileNameValidator;
import org.bonitasoft.studio.ui.validator.InputValidatorWrapper;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;

import com.google.common.base.Objects;

public class RenameApplicationDescriptorFileDialog {

    public static boolean open(Shell shell, ApplicationRepositoryStore applicationRepositoryStore,
            ApplicationFileStore applicationFileStore) {
        String currentFileName = applicationFileStore.getDisplayName();
        final InputDialog dialog = new InputDialog(shell, Messages.rename, Messages.renameFile,
                currentFileName, new InputValidatorWrapper(
                        new ApplicationDescriptorFileNameValidator(applicationRepositoryStore, currentFileName)));
        if (dialog.open() == Dialog.OK && !currentFileName.equals(stripXmlExtension(dialog.getValue()))) {
            applicationFileStore.rename(stripXmlExtension(dialog.getValue()) + ".xml");
        }

        return !Objects.equal(currentFileName, applicationFileStore.getDisplayName());
    }

    private static String stripXmlExtension(String name) {
        return name.toLowerCase().endsWith(".xml") ? name.replace(".xml", "") : name;
    }
}
