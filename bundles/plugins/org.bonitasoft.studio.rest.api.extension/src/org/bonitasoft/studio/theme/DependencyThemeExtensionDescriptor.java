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
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;
import java.util.zip.ZipFile;

import org.bonitasoft.plugin.analyze.report.model.Theme;
import org.bonitasoft.studio.common.log.BonitaStudioLog;

public class DependencyThemeExtensionDescriptor extends ThemeExtensionDescriptor {

    private Theme theme;

    public DependencyThemeExtensionDescriptor(Theme theme) {
        super();
        this.theme = theme;
    }

    @Override
    public Properties getPageProperties() {
        File file = new File(theme.getFilePath());
        try {
            return findZipEntry(file, entry -> entry.getName().equals("page.properties"))
                    .map(entry -> {
                        try (ZipFile zipFile = new ZipFile(file);
                                Reader reader = new InputStreamReader(zipFile.getInputStream(entry),
                                        StandardCharsets.UTF_8)) {
                            Properties prop = new Properties();
                            prop.load(reader);
                            return prop;
                        } catch (IOException e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .orElse(new Properties());
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return new Properties();
        }
    }

}
