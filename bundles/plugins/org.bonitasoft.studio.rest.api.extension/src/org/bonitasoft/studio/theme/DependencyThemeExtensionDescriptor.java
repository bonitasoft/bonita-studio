/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.theme;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.bonitasoft.plugin.analyze.report.model.Theme;
import org.bonitasoft.studio.common.extension.properties.ExtensionPagePropertiesReader;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.swt.widgets.Display;

public class DependencyThemeExtensionDescriptor extends ThemeExtensionDescriptor {

    private Theme theme;

    public DependencyThemeExtensionDescriptor(Theme theme) {
        super();
        this.theme = theme;
    }

    @Override
    public Properties getPageProperties() {
        File file = new File(theme.getArtifact().getFile());
        try {
            return ExtensionPagePropertiesReader.getPageProperties(file).orElseThrow();
        } catch (IOException e) {
            new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
            return new Properties();
        }
    }

}
