/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.validator;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Display;

public class ConnectorExtensionTypeValidator implements IValidator<File> {

    @Override
    public IStatus validate(File file) {
        boolean isConnector = false;
        if (file != null && file.getName().endsWith(".jar")) {
            try (JarFile jarFile = new JarFile(file)) {
                isConnector = jarFile.stream()
                        .anyMatch((entry -> entry.getName().endsWith(".def") || entry.getName().endsWith(".impl")));
            } catch (IOException e) {
                new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
                isConnector = false;
            }
        }
        return createStatus(isConnector);
    }

    protected IStatus createStatus(boolean isConnector) {
        return isConnector
                ? ValidationStatus.ok()
                : ValidationStatus.error(Messages.extensionIsNotAConnector);
    }

}
