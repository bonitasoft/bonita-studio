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
package org.bonitasoft.studio.ui.dialog;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.bonitasoft.studio.ui.validator.InputValidatorWrapper;
import org.bonitasoft.studio.ui.validator.TypedValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;

import com.google.common.base.Objects;

public class RenameXMLFileDialog {

    public static boolean open(Shell shell, IRepositoryFileStore fileStore, TypedValidator<String, IStatus> validator) {
        String currentFileName = fileStore.getDisplayName();
        final InputDialog dialog = new InputDialog(shell, Messages.rename, Messages.renameFile,
                currentFileName, new InputValidatorWrapper(validator));
        if (dialog.open() == Dialog.OK && !currentFileName.equals(stripXmlExtension(dialog.getValue()))) {
            fileStore.renameLegacy(stripXmlExtension(dialog.getValue()) + ".xml");
        }

        return !Objects.equal(currentFileName, fileStore.getDisplayName());
    }

    private static String stripXmlExtension(String name) {
        return name.toLowerCase().endsWith(".xml") ? name.replace(".xml", "") : name;
    }
}
