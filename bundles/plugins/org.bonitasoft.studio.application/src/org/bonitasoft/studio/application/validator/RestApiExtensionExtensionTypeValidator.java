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
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Display;

public class RestApiExtensionExtensionTypeValidator implements IValidator<File> {

    private static final String API_EXTENSION_CONTENT_TYPE = "apiExtension";

    @Override
    public IStatus validate(File file) {
        boolean isRestApi = false;
        if (file != null && file.getName().endsWith(".zip")) {
            try (ZipFile zipFile = new ZipFile(file)) {
                isRestApi = zipFile.stream()
                        .filter(entry -> entry.getName().equals("page.properties"))
                        .findFirst()
                        .map(entry -> analyseProperties(zipFile, entry))
                        .orElse(false);
            } catch (IOException e) {
                new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
                isRestApi = false;
            }
        }
        return createStatus(isRestApi);
    }

    private boolean analyseProperties(ZipFile zipFile, ZipEntry entry) {
        try (Reader reader = new InputStreamReader(zipFile.getInputStream(entry),
                StandardCharsets.UTF_8)) {
            Properties prop = new Properties();
            prop.load(reader);
            String contentType = prop.getProperty("contentType");
            return Objects.equals(contentType, getExpectedContentType());
        } catch (IOException e) {
            new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
            return false;
        }
    }

    protected String getExpectedContentType() {
        return API_EXTENSION_CONTENT_TYPE;
    }

    protected IStatus createStatus(boolean isRestApi) {
        return isRestApi
                ? ValidationStatus.ok()
                : ValidationStatus.error(Messages.extensionIsNotARestApiExtension);
    }

}
